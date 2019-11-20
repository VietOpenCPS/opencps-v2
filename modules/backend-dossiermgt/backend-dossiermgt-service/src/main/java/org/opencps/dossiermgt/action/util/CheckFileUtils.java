package org.opencps.dossiermgt.action.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.activation.DataHandler;
import javax.activation.MimeType;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;

public class CheckFileUtils {

	private static final Log log = LogFactoryUtil.getLog(CheckFileUtils.class);

	public static boolean checkFileUpload(Attachment attachment) {
		if (attachment == null) {
			return false;
		}
		
		String typeAtt = attachment.getContentType().toString();
		if (typeAtt != null) {
			try {
				new MimeType(typeAtt);
			} catch (Exception e) {
				return false;
			}
		} else {
			return false;
		}

		String contentDispositionHeaderValue = attachment.getHeader(ReadFilePropertiesUtils.get(ConstantUtils.TYPE_DISPOSITON));
		if (contentDispositionHeaderValue != null) {
			contentDispositionHeaderValue = contentDispositionHeaderValue.trim();
			Map<String, String> contentDispositionHeaderValueMap = processContentDispositionHeader(contentDispositionHeaderValue);
			String dispositionName = contentDispositionHeaderValueMap
					.get(ReadFilePropertiesUtils.get(ConstantUtils.VALUE_NAME));
			InputStream stream = null;
			DataHandler handle = attachment.getDataHandler();
			if (handle == null) {
				return false;
			}
			// Check extention
			if (!checkExtentionFile(handle)) {
				return false;
			}
			
			try {
				stream = handle.getInputStream();
				if (stream == null || (stream != null && stream.available() == 0)) {
					return false;
				}
			} catch (IOException e1) {
				return false;
			}
			if (ReadFilePropertiesUtils.get(ConstantUtils.VALUE_NAME).equals(dispositionName)
					|| ReadFilePropertiesUtils.get(ConstantUtils.VALUE_TYPE).equals(dispositionName)
					|| ReadFilePropertiesUtils.get(ConstantUtils.VALUE_DESCRIPTION).equals(dispositionName)) {
				try {
					getAttachmentStream(stream);
				} catch (IOException e) {
					return false;
				}
			}
		}
		return true;
	}

	private static Map<String, String> processContentDispositionHeader(String headerValue) {

		boolean debugEnabled = log.isDebugEnabled();
		if (!headerValue.endsWith(StringPool.SEMICOLON)) {
			headerValue += StringPool.SEMICOLON;
		}

		byte[] headerValueByteArray = headerValue.trim().substring("form_data".length() + 1).getBytes();

		int beginIndex = 0;
		int length = 0;
		String key = null;
		String value;
		boolean keyFound = false;
		//boolean valueFound = false;

		Map<String, String> contentDispositionHeaderMap = new HashMap<String, String>();

		for (byte byte1 : headerValueByteArray) {

			++length;

			if (!keyFound) {

				if (byte1 == '=') {

					keyFound = true;
					key = new String(headerValueByteArray, beginIndex, length - 1).trim();
					beginIndex += length;
					length = 0;
					if (debugEnabled) {
						log.debug("KEY:" + key);
					}
				}
			} else {
				if (byte1 == '\n' || byte1 == '\r' || byte1 == ';') {

					value = new String(headerValueByteArray, beginIndex, length - 1);
					value = value.replaceAll(StringPool.QUOTE, StringPool.BLANK).trim();
					keyFound = false;
					beginIndex += length;
					length = 0;
					if (debugEnabled) {
						log.debug("header value:" + value);
					}

					contentDispositionHeaderMap.put(key, value);
					key = null;
					value = null;
				}
			}
		}
		return contentDispositionHeaderMap;
	}

	private static OutputStream getAttachmentStream(InputStream inputStream) throws IOException {

		if (inputStream != null) {
			CachedOutputStream cachedOutputStream = new CachedOutputStream();
			IOUtils.copy(inputStream, cachedOutputStream);
			cachedOutputStream.close();

			return cachedOutputStream.getOut();
		}

		return null;
	}

	private static boolean checkExtentionFile(DataHandler handle) {
		//return false;
		String[] extentionArr = { ReadFilePropertiesUtils.get(ConstantUtils.EXTENTION_PNG),
				ReadFilePropertiesUtils.get(ConstantUtils.EXTENTION_PDF),
				ReadFilePropertiesUtils.get(ConstantUtils.EXTENTION_DOC),
				ReadFilePropertiesUtils.get(ConstantUtils.EXTENTION_DOCX),
				ReadFilePropertiesUtils.get(ConstantUtils.EXTENTION_XLS),
				ReadFilePropertiesUtils.get(ConstantUtils.EXTENTION_XLSX),
				ReadFilePropertiesUtils.get(ConstantUtils.EXTENTION_JPG),
				ReadFilePropertiesUtils.get(ConstantUtils.EXTENTION_JPEG),
				ReadFilePropertiesUtils.get(ConstantUtils.EXTENTION_TXT),
				ReadFilePropertiesUtils.get(ConstantUtils.EXTENTION_RTF),
				ReadFilePropertiesUtils.get(ConstantUtils.EXTENTION_XML),
				"zip", "rar"};
		
		String extentFile = FilenameUtils.getExtension(handle.getName());
		for (String extend : extentionArr) {
			if (extentFile.equalsIgnoreCase(extend)) {
				return true;
			}
		}
		return false;
	}


}