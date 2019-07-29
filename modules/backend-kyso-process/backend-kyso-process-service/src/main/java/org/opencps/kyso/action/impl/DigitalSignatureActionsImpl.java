package org.opencps.kyso.action.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.security.cert.Certificate;
import java.util.List;

import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.kyso.action.DigitalSignatureActions;
import org.opencps.kyso.model.impl.kysoServerConfigModel;
import org.opencps.kyso.utils.BCYSignatureUtil;
import org.opencps.kyso.utils.CertUtil;
import org.opencps.kyso.utils.ExtractTextLocations;
import org.opencps.kyso.utils.ImageUtil;
import org.opencps.kyso.utils.KysoTerm;

import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfSignatureAppearance;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import backend.kyso.process.service.util.ConfigProps;
import vgca.svrsigner.ServerSigner;

public class DigitalSignatureActionsImpl implements DigitalSignatureActions{

	private static Log _log = LogFactoryUtil.getLog(DigitalSignatureActionsImpl.class);
//	private static final String TYPE_KYSO = "1135, 1158, 1160, 1032";
//	private static final String TYPE_DONGDAU = "1137, 1160, 1162";
//	private static final String STEPCODE_KYSO = "300, 301, 105";
//	private static final String STEPCODE_DONGDAU = "400";
	
	
	public kysoServerConfigModel fromJSONObject(JSONObject configObj) {
		if (configObj.has(KysoTerm.SERVER_TYPEDONGDAU) && configObj.has(KysoTerm.SERVER_TYPEKYSO)
				&& configObj.has(KysoTerm.SERVER_ACTIVE)) {
			return new kysoServerConfigModel(configObj.getString(KysoTerm.SERVER_TYPEDONGDAU),
					configObj.getString(KysoTerm.SERVER_TYPEKYSO),
					configObj.getBoolean(KysoTerm.SERVER_ACTIVE));
		} else {
			return null;
		}
	}
	
	public kysoServerConfigModel getServerConfig(long groupId, String protocol) {
		kysoServerConfigModel config = null;

		List<ServerConfig> lstsc = ServerConfigLocalServiceUtil.getByProtocol(groupId, protocol);
		if (lstsc == null) {
			return null;
		} else {
			try {
				for (ServerConfig sc : lstsc) {
					kysoServerConfigModel check = fromJSONObject(JSONFactoryUtil.createJSONObject(sc.getConfigs()));
					if (check.getActive()) {
						config = check;
					}
				}
				return config;
			} catch (JSONException e) {
				_log.error(e);
				return null;
			}
		}
	}
	
	private String TYPE_KYSO = ""; //la cac actionCode KYSO
	private String TYPE_DONGDAU = ""; //la cac actionCode KYSO

	@Override
	public JSONObject createHashSignature(String email, long fileEntryId, String typeSignature, String postStepCode, long groupId) {
		
		kysoServerConfigModel config = getServerConfig(groupId, "KYSO");
		if(Validator.isNotNull(config)){
			TYPE_KYSO = config.getType_kyso();
			TYPE_DONGDAU = config.getType_dongdau();	
		}else {
			TYPE_KYSO = "1032,1050,3000,6100,4000";
			TYPE_DONGDAU = "1032,1050,3000,6100,4000";
		}
		
		
			byte[] inHash = null;
			String fieldName = StringPool.BLANK;
			String fullPathSigned = StringPool.BLANK;
			JSONObject jsonFeed = JSONFactoryUtil.createJSONObject();
			JSONArray hashComputers = JSONFactoryUtil.getJSONFactory()
					.createJSONArray();
			JSONArray signFieldNames = JSONFactoryUtil.getJSONFactory()
					.createJSONArray();
			JSONArray fileNames = JSONFactoryUtil.getJSONFactory()
					.createJSONArray();
			JSONArray messages = JSONFactoryUtil.getJSONFactory().createJSONArray();
			JSONArray fullPathOfSignedFiles = JSONFactoryUtil.getJSONFactory()
					.createJSONArray();

//			if (typeSignature == TYPE_KYSO) {
			String realPath = PropsUtil.get(ConfigProps.CER_HOME)+"/";
//			_log.info("realPath_Kyso: "+realPath);
			_log.info("realPath_Kyso: "+realPath);
//			} else {
//				realPath = PropsUtil.get(ConfigProps.CER_HOME)+"/condau/";
//				_log.info("realPath_Dongdau: "+realPath);
//			}
			//==
			String fullPath = StringPool.BLANK;

			try {
//				float offsetX = 1;
//				float imageZoom = 1;
//				float offsetY = 1;

				DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.fetchDLFileEntry(fileEntryId);
				
				File fileTemp = FileUtil.createTempFile(dlFileEntry.getContentStream());
				_log.info("fileTemp URL: "+fileTemp.getAbsolutePath());
				
				File file = new File(realPath + dlFileEntry.getFileName());
				
				FileUtil.move(fileTemp, file);
				
				fullPath = file.getAbsolutePath();
				_log.info("fullPath: "+fullPath);

//				String signImagePath = StringPool.BLANK;
				_log.info("====***typeSignature+===: "+typeSignature);
				_log.info("====***postStepCode+===: "+postStepCode);
//				if (TYPE_KYSO.contains(typeSignature) && STEPCODE_KYSO.contains(postStepCode)) {
				String signImagePath = new File(realPath + email + ".png").getAbsolutePath();
				_log.info("signImagePath_Kyso: "+realPath);
//				} else if (TYPE_DONGDAU.contains(typeSignature) && STEPCODE_DONGDAU.equals(postStepCode)){
//					signImagePath = PropsUtil.get(ConfigProps.CER_HOME)+"/condau/nguyenadmin.png";
//					_log.info("signImagePath_Dongdau: "+realPath);
//				}
//				String signImagePath = new File(realPath + email + ".png").getAbsolutePath();
				String imageBase64 = ImageUtil.getSignatureImageBase64ByPath(signImagePath);
//				_log.info("signImagePath: "+signImagePath);
//				_log.info("imageBase64: "+imageBase64);

				BufferedImage bufferedImage = ImageUtil.getImageByPath(signImagePath);

				Certificate cert = CertUtil.getCertificateByPath(new File(realPath + email + ".cer").getAbsolutePath());

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

//				int signatureImageWidth = (bufferedImage != null && bufferedImage.getWidth() > 0)
//						? bufferedImage.getWidth()/2 : 80;
//
//				int signatureImageHeight = (bufferedImage != null && bufferedImage.getHeight() > 0)
//						? bufferedImage.getHeight() : 80;
//				
//				float llx = textLocation.getAnchorX() + offsetX;
//
//				float lly = textLocation.getAnchorY() - signatureImageHeight * imageZoom + offsetY;
//
//				if (textLocation.getAnchorX() > 200) {
//					llx = llx - 100;
//				}
//				if (textLocation.getAnchorY() > 420) {
//					lly = lly - 420;
//				}
//
//				if (lly < 0) {
//					lly = 0;
//				}
//				if (llx < 0) {
//					llx = 0;
//				}
//				float urx = llx + signatureImageWidth * imageZoom;
//				float ury = lly + signatureImageHeight * imageZoom;
				// tinh kich thuoc cua anh
				int signatureImageWidth = (bufferedImage != null && bufferedImage
						.getWidth() > 0) ? bufferedImage.getWidth() : 80;
				int signatureImageHeight = (bufferedImage != null && bufferedImage
						.getHeight() > 0) ? bufferedImage
						.getHeight() : 80;
				float llx = textLocation.getAnchorX();
				float urx = llx + signatureImageWidth / 3;

				float lly = textLocation.getPageURY()
						- textLocation.getAnchorY()
						- signatureImageHeight / 3;

				float ury = lly + signatureImageHeight / 3;

//				inHash = signer.computeHash(new Rectangle(llx, lly , urx, ury), 1);
//				inHash = signer.computeHash(
//						new Rectangle(llx + 20, lly - 105,
//								urx + 94, ury - 70), 1);
//				inHash = signer.computeHash(new Rectangle(llx + 22, lly - 145, urx + 94, ury - 70), 1);
//				if (TYPE_KYSO.contains(typeSignature) && STEPCODE_KYSO.contains(postStepCode)) {
					inHash = signer.computeHash(new Rectangle(llx + 10, lly - 15, urx + 90, ury), 1);
//				if (TYPE_KYSO.contains(typeSignature) && STEPCODE_KYSO.contains(postStepCode)) {
//					inHash = signer.computeHash(new Rectangle(llx + 10, lly - 15, urx + 90, ury), 1);
//					_log.info("inHash_Kyso: "+inHash);
//				} else if (TYPE_DONGDAU.contains(typeSignature) && STEPCODE_DONGDAU.equals(postStepCode)) {
//					inHash = signer.computeHash(new Rectangle(llx + 10, lly - 115, urx + 90, ury-95), 1);
//					_log.info("inHash_Dongdau: "+inHash);
//				}
				if (TYPE_KYSO.contains(typeSignature)) {
					inHash = signer.computeHash(new Rectangle(llx + 10, lly - 15, urx + 90, ury), 1);
					_log.info("inHash_Kyso: "+inHash);
				} else if (TYPE_DONGDAU.contains(typeSignature)) {
					inHash = signer.computeHash(new Rectangle(llx + 10, lly - 115, urx + 90, ury-95), 1);
					_log.info("inHash_Dongdau: "+inHash);
				}
				
				if (TYPE_KYSO.contains(typeSignature)) {
					inHash = signer.computeHash(new Rectangle(llx + 10, lly - 15, urx + 90, ury),textLocation.getPageSize());
					_log.info("inHash_Kyso: "+inHash);
				} else if (TYPE_DONGDAU.contains(typeSignature)) {
					inHash = signer.computeHash(new Rectangle(llx + 10, lly - 115, urx + 90, ury-95), textLocation.getPageSize());
					_log.info("inHash_Dongdau: "+inHash);
				}
//				inHash = signer.computeHash(new Rectangle(llx + 10, lly - 15, urx + 90, ury), 1);
				_log.info("********************************* llx " + llx);

				_log.info("********************************* lly " + lly);
			
				_log.info("********************************* urx " + urx);
			
				_log.info("********************************* ury " + ury);
			
				_log.info("********************************* signatureImageWidth " + signatureImageWidth);
			
				_log.info("********************************* signatureImageHeight " + signatureImageHeight);

//							signature = Base64.getDecoder().decode("");
//							signer.completeSign(signature, fieldName);
//							File fileSigned = new File(fullPath.replace(".pdf", ".signed.pdf"));
//							fieldName = signer.getSignatureName();
//							fileNames.put(urlFile);

				fieldName = signer.getSignatureName();
				fullPathSigned = signer.getSignedFile();
				hashComputers.put(Base64.encode(inHash));
				signFieldNames.put(fieldName);
				fileNames.put(dlFileEntry.getFileName());
				messages.put("success");
				fullPathOfSignedFiles.put(fullPathSigned);
				
				_log.info("hashComputers: "+hashComputers);
				_log.info("signFieldNames: "+signFieldNames);
				_log.info("fullPathOfSignedFiles: "+fullPathOfSignedFiles);
				_log.info("fileNames: "+fileNames);
				_log.info("messages: "+messages);
				
				_log.info("===KY XONG YCGIAMDIDNH===: "+ fullPathSigned);

			} catch (Exception e) {
				hashComputers.put(StringPool.BLANK);
				signFieldNames.put(StringPool.BLANK);
				fileNames.put(StringPool.BLANK);
				fullPathOfSignedFiles.put(StringPool.BLANK);
				messages.put(e.getClass().getName());
				_log.error(e);
			}
			
			jsonFeed.put("hashComputers", hashComputers);
			jsonFeed.put("signFieldNames", signFieldNames);
			jsonFeed.put("fileNames", fileNames);
			jsonFeed.put("msg", messages);
			jsonFeed.put("fullPathSigned", fullPathOfSignedFiles);
			jsonFeed.put("fileEntryId", fileEntryId);
			_log.info("=====CHECK END kyDuyetYCGiamDinh=====" + jsonFeed.toString());

			return jsonFeed;

		
	}

	@Override
	public JSONObject completeSignature(String sign, String signFieldName, String fileName) {

		JSONObject jsonFeed = JSONFactoryUtil.createJSONObject();
		
		if (Validator.isNotNull(sign) && Validator.isNotNull(fileName)) {
			byte[] signature = Base64.decode(sign);

			String realPath = PropsUtil.get(ConfigProps.CER_HOME)+"/";
			String fullPath = StringPool.BLANK;

			try {
				fileName = fileName.substring(0,
						fileName.lastIndexOf(StringPool.PERIOD));
				_log.info("fileName: "+fileName);
				
				ServerSigner signer = new ServerSigner(realPath
						+ fileName + ".pdf", null);

				signer.completeSign(signature, signFieldName);

				String signedFile = signer.getSignedFile();
				_log.info("signedFile: "+signedFile.toString());

				fullPath = realPath + fileName + ".pdf";
				_log.info("fullPath: "+fullPath.toString());
				
				jsonFeed.put("signedFile", signedFile);
				jsonFeed.put("fullPath", fullPath);
				jsonFeed.put("msg", "success");
			} catch (Exception e) {
				_log.error(e);
				jsonFeed.put("msg", "error");
			}
		} else {
			jsonFeed.put("msg", "error");
			_log.info("Cannot sign the file: " + fileName);
		}
		
		return jsonFeed;
	}

}
