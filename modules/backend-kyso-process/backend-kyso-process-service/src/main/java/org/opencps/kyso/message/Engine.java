package org.opencps.kyso.message;

import java.awt.image.BufferedImage;
import java.io.File;
import java.security.cert.Certificate;
import java.util.Base64;

import org.opencps.kyso.utils.BCYSignatureUtil;
import org.opencps.kyso.utils.CertUtil;
import org.opencps.kyso.utils.ExtractTextLocations;
import org.opencps.kyso.utils.ImageUtil;

import com.itextpdf.text.pdf.PdfSignatureAppearance;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import backend.kyso.process.service.util.ConfigProps;
import vgca.svrsigner.ServerSigner;

public class Engine implements MessageListener {

	@Override
	public void receive(Message message) throws MessageListenerException {
		// TODO Auto-generated method stub
		try {
			_doReceiveJasperRequest(message);
		} catch (Exception e) {
			_log.error("Unable to process message " + message, e);
		}
	}

	private void _doReceiveJasperRequest(Message message) {
		// TODO Auto-generated method stub
		_log.info("KYSO processing .............................");
		JSONObject msgData = (JSONObject) message.get("msgToEngine");

		try {

			long fileEntryId = msgData.getLong("fileEntryId");

			long userId = msgData.getLong("userId");

			boolean eSign = msgData.getBoolean("eSign");
			long dossierFileId = msgData.getLong("dossierFileId");
			
			if (eSign) {
				
				User user = UserLocalServiceUtil.fetchUser(userId);
				
				String emailUser = StringPool.BLANK;
				
				if (Validator.isNotNull(user)){
					emailUser = user.getEmailAddress();
				}
				
				float offsetX = 1;
				float imageZoom = 1;
				float offsetY = 1;
				
//				byte[] inHash = null;
				byte[] signature = null;
				String fieldName = "";
//				String certBase64 = "";

//				String rootPath = "/Users/binhth/Downloads/apache-tomcat-8.0.45/webapps/WebApplication1/";
//				String fullPath = rootPath + "TestFile.pdf";
				String rootPath = PropsUtil.get(ConfigProps.CER_HOME)+"/";
//				String fullPath = StringPool.BLANK;
				
				if (fileEntryId > 0) {
					DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.fetchDLFileEntry(fileEntryId);
					File fileTemp = FileUtil.createTempFile(dlFileEntry.getContentStream());
					File file = new File(rootPath + dlFileEntry.getFileName());
					FileUtil.move(fileTemp, file);
					String fullPath = file.getAbsolutePath();

					String signImagePath = new File(rootPath + emailUser + ".png").getAbsolutePath();
					String imageBase64 = ImageUtil.getSignatureImageBase64ByPath(signImagePath);
	
					BufferedImage bufferedImage = ImageUtil.getImageByPath(signImagePath);
	
					Certificate cert = CertUtil.getCertificateByPath(new File(rootPath + emailUser + ".cer").getAbsolutePath());
	
					boolean showSignatureInfo = true;
	
					ServerSigner signer = BCYSignatureUtil.getServerSigner(fullPath, cert, imageBase64, showSignatureInfo);
	
					signer.setSignatureGraphic(imageBase64);
					signer.setSignatureAppearance(PdfSignatureAppearance.RenderingMode.GRAPHIC_AND_DESCRIPTION);
	
					ExtractTextLocations textLocation = new ExtractTextLocations(fullPath);
	
					_log.info("*********************************" + textLocation.getAnchorX() + "-"
							+ textLocation.getAnchorY() + "********************************");
	
					_log.info("*********************************" + textLocation.getPageLLX() + "-"
							+ textLocation.getPageURX() + "-" + textLocation.getPageLLY() + "-" + textLocation.getPageURY()
							+ "*******************************");
	
					// tinh kich thuoc cua anh
	
					int signatureImageWidth = (bufferedImage != null && bufferedImage.getWidth() > 0)
							? bufferedImage.getWidth() : 80;
	
					int signatureImageHeight = (bufferedImage != null && bufferedImage.getHeight() > 0)
							? bufferedImage.getHeight() : 80;
					
					float llx = textLocation.getAnchorX() + offsetX;
	
					float lly = textLocation.getAnchorY() - signatureImageHeight * imageZoom + offsetY;
	
					if (textLocation.getAnchorX() > 200) {
						llx = llx - 100;
					}
					if (textLocation.getAnchorY() > 420) {
						lly = lly - 420;
					}
//
					if (lly < 0) {
						lly = 0;
					}
					if (llx < 0) {
						llx = 0;
					}
					float urx = llx + signatureImageWidth * imageZoom;
					float ury = lly + signatureImageHeight * imageZoom;
					
					_log.info("********************************* llx " + llx);
	
					_log.info("********************************* lly " + lly);
					
					_log.info("********************************* urx " + urx);
					
					_log.info("********************************* ury " + ury);
					
					_log.info("********************************* signatureImageWidth " + signatureImageWidth);
					
					_log.info("********************************* signatureImageHeight " + signatureImageHeight);
					
//					inHash = signer.computeHash(new Rectangle(0, 0, urx, ury), 1);
					// TODO # location fixed
//					inHash = signer.computeHash(new Rectangle(llx, lly , urx, ury), 1);
					fieldName = signer.getSignatureName();
	
					signature = Base64.getDecoder().decode("");
					signer.completeSign(signature, fieldName);
	
					File fileSigned = new File(fullPath.replace(".pdf", ".signed.pdf"));
	
					ServiceContext serviceContext = new ServiceContext();
	
					DLAppLocalServiceUtil.updateFileEntry(userId, dlFileEntry.getFileEntryId(), dlFileEntry.getTitle(),
							dlFileEntry.getMimeType(), dlFileEntry.getTitle(), dlFileEntry.getDescription(),
							StringPool.BLANK, false, fileSigned, serviceContext);
					
					// turnOn DossierFile Sync
					
					JSONObject msgDataIn = JSONFactoryUtil.createJSONObject();
					msgDataIn.put("dossierFileId", dossierFileId);
					msgDataIn.put("dossierFileSync", eSign);
					msgDataIn.put("userId", userId);
					
					message.put("msgToEngine", msgDataIn);
					MessageBusUtil.sendMessage("jasper/dossier/in/destination", message);
					
//					FileUtil.delete(file);
//					FileUtil.delete(fileSigned);
					
				}

			}

		} catch (Exception e) {
			_log.error(e);
		}

	}

	private Log _log = LogFactoryUtil.getLog(Engine.class);
}
