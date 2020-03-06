package org.graphql.api.controller.utils;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;

import java.io.IOException;
import java.io.InputStream;

import javax.activation.MimeType;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class CheckFileUtils {

	private static final Log _log = LogFactoryUtil.getLog(CheckFileUtils.class);

	public static boolean checkFileUpload(CommonsMultipartFile multipartFile) {
		if (multipartFile == null) {
			return false;
		}
		
		String typeAtt = multipartFile.getContentType().toString();
		if (typeAtt != null) {
			try {
				new MimeType(typeAtt);
			} catch (Exception e) {
				_log.debug(e);
				return false;
			}
		} else {
			return false;
		}

		InputStream stream = null;
		try {
			stream = multipartFile.getInputStream();
			if (stream == null || (stream != null && stream.available() == 0)) {
				return false;
			}
		} catch (IOException e1) {
			_log.debug(e1);
			return false;
		}
		
		// Check extention
		if (!checkExtentionFile(multipartFile)) {
			return false;
		}

		return true;
	}

	public static boolean checkDLFileEntryUpload(DLFileEntry fileEntry) {
		if (fileEntry == null) {
			return false;
		}

		// Check extention
		if (!checkExtentionFile(fileEntry)) {
			return false;
		}

		return true;
	}

	public static boolean checkFileEntryUpload(FileEntry fileEntry) {
		if (fileEntry == null) {
			return false;
		}

		// Check extention
		if (!checkExtentionFile(fileEntry)) {
			return false;
		}

		return true;
	}

//	private static Map<String, String> processContentDispositionHeader(String headerValue) {
//
//		boolean debugEnabled = log.isDebugEnabled();
//		if (!headerValue.endsWith(StringPool.SEMICOLON)) {
//			headerValue += StringPool.SEMICOLON;
//		}
//
//		byte[] headerValueByteArray = headerValue.trim().substring("form_data".length() + 1).getBytes();
//
//		int beginIndex = 0;
//		int length = 0;
//		String key = null;
//		String value;
//		boolean keyFound = false;
//		//boolean valueFound = false;
//
//		Map<String, String> contentDispositionHeaderMap = new HashMap<String, String>();
//
//		for (byte byte1 : headerValueByteArray) {
//
//			++length;
//
//			if (!keyFound) {
//
//				if (byte1 == '=') {
//
//					keyFound = true;
//					key = new String(headerValueByteArray, beginIndex, length - 1).trim();
//					beginIndex += length;
//					length = 0;
//					if (debugEnabled) {
//						log.debug("KEY:" + key);
//					}
//				}
//			} else {
//				if (byte1 == '\n' || byte1 == '\r' || byte1 == ';') {
//
//					value = new String(headerValueByteArray, beginIndex, length - 1);
//					value = value.replaceAll(StringPool.QUOTE, StringPool.BLANK).trim();
//					keyFound = false;
//					beginIndex += length;
//					length = 0;
//					if (debugEnabled) {
//						log.debug("header value:" + value);
//					}
//
//					contentDispositionHeaderMap.put(key, value);
//					key = null;
//					value = null;
//				}
//			}
//		}
//		return contentDispositionHeaderMap;
//	}

//	private static OutputStream getAttachmentStream(InputStream inputStream) throws IOException {
//
//		if (inputStream != null) {
//			CachedOutputStream cachedOutputStream = new CachedOutputStream();
//			IOUtils.copy(inputStream, cachedOutputStream);
//			cachedOutputStream.close();
//
//			return cachedOutputStream.getOut();
//		}
//
//		return null;
//	}

	private static boolean checkExtentionFile(CommonsMultipartFile multipartFile) {
		//return false;
		String[] extentionArr = { "png", "pdf", "doc", "docx", "xls", "xlsx", "jpg", "jpeg", "txt", "rtf", "xml", "zip", "rar", "cer" };
		
		String extentFile = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
//		System.out.println("multipartFile.getOriginalFilename(): "+multipartFile.getOriginalFilename());
//		System.out.println("extentFile: "+extentFile);
		for (String extend : extentionArr) {
			if (extentFile.equalsIgnoreCase(extend)) {
				return true;
			}
		}
		return false;
	}

	private static boolean checkExtentionFile(DLFileEntry fileEntry) {
		//return false;
		String[] extentionArr = { "png", "pdf", "doc", "docx", "xls", "xlsx", "jpg", "jpeg", "txt", "rtf", "xml", "zip", "rar", "cer" };
		
		String extentFile = FilenameUtils.getExtension(fileEntry.getFileName());
//		System.out.println("multipartFile.getOriginalFilename(): "+multipartFile.getOriginalFilename());
//		System.out.println("extentFile: "+extentFile);
		for (String extend : extentionArr) {
			if (extentFile.equalsIgnoreCase(extend)) {
				return true;
			}
		}
		return false;
	}

	private static boolean checkExtentionFile(FileEntry fileEntry) {
		//return false;
		String[] extentionArr = { "png", "pdf", "doc", "docx", "xls", "xlsx", "jpg", "jpeg", "txt", "rtf", "xml", "zip", "rar", "cer" };
		
		String extentFile = FilenameUtils.getExtension(fileEntry.getFileName());
//		System.out.println("multipartFile.getOriginalFilename(): "+multipartFile.getOriginalFilename());
//		System.out.println("extentFile: "+extentFile);
		for (String extend : extentionArr) {
			if (extentFile.equalsIgnoreCase(extend)) {
				return true;
			}
		}
		return false;
	}


}