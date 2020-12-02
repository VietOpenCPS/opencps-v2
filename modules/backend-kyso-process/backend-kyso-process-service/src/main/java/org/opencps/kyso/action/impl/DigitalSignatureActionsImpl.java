package org.opencps.kyso.action.impl;

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
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.viettel.signature.pdf.TimestampConfig;
import com.viettel.signature.plugin.SignPdfFile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.kyso.action.DigitalSignatureActions;
import org.opencps.kyso.model.impl.kysoServerConfigModel;
import org.opencps.kyso.utils.BCYSignatureUtil;
import org.opencps.kyso.utils.CertUtil;
import org.opencps.kyso.utils.ExtractTextLocations;
import org.opencps.kyso.utils.ImageUtil;
import org.opencps.kyso.utils.KysoTerm;
import org.opencps.kyso.utils.ViettelCaUtil;
import org.opencps.kyso.utils.X509ExtensionUtil;
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
	private static final String DEFAULT_SERVER_CONFIG = "KYSO";
	private static final String DEFAULT_TYPE_KYSO = "1032,1050,3000,6100,4000";
	private static final String DEFAULT_TYPE_DONGDAU = "1032,1050,3000,6100,4000";
	
	@Override
	public JSONObject createHashSignature(String email, long fileEntryId, String typeSignature, String postStepCode, long groupId) {
		
		kysoServerConfigModel config = getServerConfig(groupId, DEFAULT_SERVER_CONFIG);
		if(Validator.isNotNull(config)){
			TYPE_KYSO = config.getType_kyso();
			TYPE_DONGDAU = config.getType_dongdau();	
		}else {
			TYPE_KYSO = DEFAULT_TYPE_KYSO;
			TYPE_DONGDAU = DEFAULT_TYPE_DONGDAU;
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
			String realPath = PropsUtil.get(ConfigProps.CER_HOME)+StringPool.SLASH;
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
				String signImagePath = new File(realPath + email + PNG_EXTENSION).getAbsolutePath();
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

				Certificate cert = CertUtil.getCertificateByPath(new File(realPath + email + CER_EXTENSION).getAbsolutePath());

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
			
			jsonFeed.put(HASH_COMPUTERS, hashComputers);
			jsonFeed.put(SIGN_FIELD_NAMES, signFieldNames);
			jsonFeed.put(FILE_NAMES, fileNames);
			jsonFeed.put(MSG, messages);
			jsonFeed.put(FULL_PATH_SIGNED, fullPathOfSignedFiles);
			jsonFeed.put(FILE_ENTRY_ID, fileEntryId);
			_log.info("=====CHECK END kyDuyetYCGiamDinh=====" + jsonFeed.toString());

			return jsonFeed;

		
	}

	private static final String PNG_EXTENSION = ".png";
	private static final String CER_EXTENSION = ".cer";
	private static final String PDF_EXTENSION = ".pdf";
	private static final String HASH_COMPUTERS = "hashComputers";
	private static final String SIGN_FIELD_NAMES = "signFieldNames";
	private static final String FILE_NAMES = "fileNames";
	private static final String MSG = "msg";
	private static final String FULL_PATH_SIGNED = "fullPathSigned";
	private static final String FILE_ENTRY_ID = "fileEntryId";
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";
	private static final String SIGNED_FILE = "signedFile";
	private static final String FULLPATH = "fullPath";
	
	@Override
	public JSONObject completeSignature(String sign, String signFieldName, String fileName) {

		JSONObject jsonFeed = JSONFactoryUtil.createJSONObject();
		
		if (Validator.isNotNull(sign) && Validator.isNotNull(fileName)) {
			byte[] signature = Base64.decode(sign);

			String realPath = PropsUtil.get(ConfigProps.CER_HOME)+StringPool.SLASH;
			String fullPath = StringPool.BLANK;

			try {
				fileName = fileName.substring(0,
						fileName.lastIndexOf(StringPool.PERIOD));
				_log.info("fileName: "+fileName);
				
				ServerSigner signer = new ServerSigner(realPath
						+ fileName + PDF_EXTENSION, null);

				signer.completeSign(signature, signFieldName);

				String signedFile = signer.getSignedFile();
				_log.info("signedFile: "+signedFile.toString());

				fullPath = realPath + fileName + PDF_EXTENSION;
				_log.info("fullPath: "+fullPath.toString());
				
				jsonFeed.put(SIGNED_FILE, signedFile);
				jsonFeed.put(FULLPATH, fullPath);
				jsonFeed.put(MSG, SUCCESS);
			} catch (Exception e) {
				_log.error(e);
				jsonFeed.put(MSG, ERROR);
			}
		} else {
			jsonFeed.put(MSG, ERROR);
			_log.info("Cannot sign the file: " + fileName);
		}
		
		return jsonFeed;
	}

	@Override
	public JSONObject hashFile(long fileEntryId, String certChainBase64, HttpServletRequest request) {
		
		JSONObject results = JSONFactoryUtil.createJSONObject();

		String realPath = PropsUtil.get(ConfigProps.CER_HOME) + "/";
		String fullPath = "";
		SignPdfFile signPdfFile = new SignPdfFile();
		try {
			
			DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.fetchDLFileEntry(fileEntryId);			
			File fileTemp = FileUtil.createTempFile(dlFileEntry.getContentStream());			
			File file = new File(realPath + dlFileEntry.getFileName());			
			FileUtil.move(fileTemp, file);			
			fullPath = file.getAbsolutePath();
			
			String folderRootCA = realPath + "RootCA";
	        X509Certificate[] certChain = X509ExtensionUtil.getCertChainOfCert(certChainBase64, folderRootCA);
	        String fontPath = realPath + "font/times.ttf";
	        
	        String base64Hash = ViettelCaUtil.getHashTypeRectangleText(signPdfFile, fullPath, certChain, fontPath);
	        
	        if (base64Hash == null) {
	        	results.put("status", 403);
	        	results.put("message", "Tao hash khong thanh cong");
	        }
	        
        	results.put("status", 200);
        	results.put("message", "success");
        	results.put("serialNumber", ((X509Certificate) certChain[0]).getSerialNumber().toString(16));
        	results.put("base64Hash", base64Hash);
        	request.getSession().setAttribute("PDFSignature", signPdfFile);
	        	        
		} catch (Exception e) {
			_log.error(e);
		}
		return results;
	}

	@Override
	public JSONObject insertSignnature(String signatureBase64, String signFileName, SignPdfFile signPdfFile) {
		
		JSONObject results = JSONFactoryUtil.createJSONObject();
		
		String realPath = PropsUtil.get(ConfigProps.CER_HOME)+ "/";
		try {
			Base64.decode(signatureBase64);
			String name = signFileName.split(".pdf")[0] + "_signed";
	        String ext = "pdf";
	        String filePath = realPath;

	        File fileDes = new File(filePath + "/" + name + "." + ext);
	        if (fileDes.exists()) {
	            int index = 1;
	            String name_2 = name + "_" + index;
	            String path = filePath + "/" + name_2 + "." + ext;
	            fileDes = new File(path);
	            while (fileDes.exists()) {
	                index++;
	                name_2 = name + "_" + index;
	                path = filePath + "/" + name_2 + "." + ext;
	                fileDes = new File(path);
	            }
	            name = name_2;
	        }
	        
	        TimestampConfig timestampConfig = new TimestampConfig();
	        timestampConfig.setUseTimestamp(false);		        		        
	        signPdfFile.insertSignature(signatureBase64, filePath + "/" + name + "." + ext, timestampConfig);
	                
	        results.put("signedFileName", name + "." + ext);
	        results.put("status", 200);
	        results.put("message", "success");
	        results.put("signedFileFullPath", filePath);
	        results.put("fileSigned", filePath + "/" + name + "." + ext);
			
			} catch (Exception e) {
			_log.error(e);
		}
		return results;
	}

}
