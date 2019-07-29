/**
 * OpenCPS is the open source Core Public Services software
 * Copyright (C) 2016-present OpenCPS community

 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>
 */

package org.opencps.kyso.utils;

import java.security.SignatureException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.opencps.pki.PdfPkcs7Signer;
import org.opencps.pki.PdfSigner;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import kysovanban.signature.DocContent;
import kysovanban.signature.PdfContent;
import kysovanban.signature.Signer;
import kysovanban.signature.SignerInfo;

/**
 * @author trungnt
 */

public class SignatureUtil {

	/**
	 * @param filePath
	 * @param certPath
	 * @param tempFilePath
	 * @param signedFilePath
	 * @param isVisible
	 * @param imagePath
	 * @return
	 */
	public static PdfSigner getPdfSigner(String filePath, String certPath,
			String tempFilePath, String signedFilePath, boolean isVisible,
			String imagePath) {

		X509Certificate cert = null;
		PdfSigner pdfSigner = null;
		try {
			cert = CertUtil.getX509CertificateByPath(certPath);
		} catch (Exception e) {
			_log.error(e);
		}

		if (cert != null) {
			pdfSigner = new PdfSigner(filePath, cert, tempFilePath,
					signedFilePath, isVisible);

			if (Validator.isNotNull(imagePath)) {
				pdfSigner.setSignatureGraphic(imagePath);
			}

		}

		return pdfSigner;
	}

	public static PdfPkcs7Signer getPdfPkcs7Signer(String filePath,
			String certPath, String tempFilePath, String signedFilePath,
			boolean isVisible, String imagePath) {

		X509Certificate cert = null;
		PdfPkcs7Signer pdfSigner = null;
		try {
			cert = CertUtil.getX509CertificateByPath(certPath);
		} catch (Exception e) {
			_log.error(e);
		}

		if (cert != null) {
			pdfSigner = new PdfPkcs7Signer(filePath, cert, tempFilePath,
					signedFilePath, isVisible);

			if (Validator.isNotNull(imagePath)) {
				pdfSigner.setSignatureGraphic(imagePath);
			}

		}

		return pdfSigner;
	}

	/**
	 * @param path
	 * @return
	 * @throws Exception
	 */
	@Deprecated
	public static String verifyPdfSignature(String path) throws Exception {

		PdfContent pdfcontent = new PdfContent(path);
		Signer signer = new Signer();
		StringBuffer buffer = new StringBuffer();
		if (signer.verify(pdfcontent)) {
			ArrayList<SignerInfo> signerInfos = signer
					.getSignatureInfos(pdfcontent);
			for (Iterator<SignerInfo> iterator = signerInfos.iterator(); iterator
					.hasNext();) {
				SignerInfo info = iterator.next();

				buffer.append(info.toJSON());
			}
		}

		return buffer.toString();
	}

	/**
	 * @param path
	 * @return
	 * @throws Exception
	 */
	@Deprecated
	public static String verifyDocxSignature(String path) throws Exception {

		DocContent doccontent = new DocContent(path);
		Signer signer = new Signer();
		StringBuffer buffer = new StringBuffer();
		if (signer.verify(doccontent)) {
			ArrayList<SignerInfo> signerInfos = signer
					.getSignatureInfos(doccontent);
			for (Iterator<SignerInfo> iterator = signerInfos.iterator(); iterator
					.hasNext();) {
				SignerInfo info = iterator.next();

				buffer.append(info.toJSON());
			}
		}

		return buffer.toString();
	}

	/**
	 * @param path
	 * @param extension
	 * @return
	 * @throws Exception
	 */
	public static int getSignCheck(String path, String extension)
			throws Exception {
//		int signCheck = 0;
//		List<SignerInfo> signerInfos = new ArrayList<SignerInfo>();

		List<SignerInfo> signerInfos = getSignerInfoAcrossExtension(path, extension);

//		if (signerInfos.size() == 0) {
//			signCheck = 0;
//		} else {
		if (signerInfos != null && signerInfos.size() > 0) {
			if (isTrust(signerInfos)) {
//				signCheck = 1;
				return 1;
			} else {
//				signCheck = 2;
				return 2;
			}
		}
			
//		}

//		return signCheck;
		return 0;
	}

	/**
	 * @param path
	 * @param extension
	 * @return
	 * @throws Exception
	 */
	public static String getSignInfo(String path, String extension)
			throws Exception {
//		String signInfoStr = StringPool.BLANK;
//		List<SignerInfo> signerInfos = new ArrayList<SignerInfo>();
//		
//		JSONArray arraySigInfo = JSONFactoryUtil.createJSONArray();
//		// JSONArray arraySigInfo = JSONFactoryUtil.createJSONArray();
//		signerInfos = getSignerInfoAcrossExtension(path, extension);
//
//		for (Iterator<SignerInfo> iterator = signerInfos.iterator(); iterator
//				.hasNext();) {
//			SignerInfo info = iterator.next();
//			// arraySigInfo.put(info.toJSON());
//			JsonElement jsonElement = new JsonParser().parse(info.toJSON());
//			arraySigInfo.add(jsonElement);
//		}
//
//		Gson gson = new Gson();
//
//		if (arraySigInfo.size() > 0) {
//			signInfoStr = gson.toJson(arraySigInfo);
//		}

		//return signInfoStr;
		return "";
	}

	public static String getSignerInfo(long dossierFileId) {

		try {

			JSONArray jsonArray = JSONFactoryUtil
					.createJSONArray();

//			String signInfoStr = StringPool.BLANK;
			StringBuilder stringBuilder = new StringBuilder();
			for (int i = 0; i < jsonArray.length(); i++) {
				String signInfoStr = jsonArray.getJSONObject(i).getString("SubjectDN");

				stringBuilder.append(signInfoStr);
				stringBuilder.append("; ");
			}

			return stringBuilder.toString();
		} catch (Exception e) {
			_log.error(e);
		}

		return StringPool.BLANK;

	}

	/**
	 * @param path
	 * @param extension
	 * @return
	 * @throws Exception
	 */
	private static List<SignerInfo> getSignerInfoAcrossExtension(String path,
			String extension) {
		PdfContent pdfcontent = null;
		DocContent doccontent = null;
		Signer signer = new Signer();
		List<SignerInfo> signerInfos = new ArrayList<SignerInfo>();
		
		try {
			if ("doc".equalsIgnoreCase(extension)
					|| "docx".equalsIgnoreCase(extension)) {
				doccontent = new DocContent(path);
				if (signer.verify(doccontent)) {
					signerInfos = signer.getSignatureInfos(doccontent);
				}

			} else if ("pdf".equalsIgnoreCase(extension)) {
				pdfcontent = new PdfContent(path);

				if (signer.verify(pdfcontent)) {
					signerInfos = signer.getSignatureInfos(pdfcontent);
				}

			}
		} catch (Exception e) {
			_log.error(e);
		}
		
		return signerInfos;
	}

	/**
	 * @param signerInfos
	 * @return
	 */
	private static boolean isTrust(List<SignerInfo> signerInfos) {
		boolean trust = true;
		for (SignerInfo signerInfo : signerInfos) {
			if (!signerInfo.isValidity()) {
				trust = false;
				break;
			}
		}

		return trust;
	}

	/**
	 * @param pdfSigner
	 * @param llx
	 * @param lly
	 * @param urx
	 * @param ury
	 * @return
	 * @throws SignatureException
	 */
	public static byte[] computerHash(PdfSigner pdfSigner, float llx,
			float lly, float urx, float ury) throws SignatureException {

		return pdfSigner.computeHash(llx, lly, urx, ury);
	}

	private static Log _log = LogFactoryUtil.getLog(SignatureUtil.class
			.getName());

}
