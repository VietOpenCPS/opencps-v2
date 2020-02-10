package org.opencps.dossiermgt.action.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.IOException;
import java.io.InputStream;

import javax.activation.DataHandler;
import javax.activation.MimeType;

import org.apache.commons.io.FilenameUtils;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;

public class CheckFileUtils {

	public static boolean checkFileUpload(Attachment attachment) {
		if (attachment == null) {
			return false;
		}

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
			_log.debug(e1);
			return false;
		}
		return true;
	}

	private static final Log _log = LogFactoryUtil.getLog(CheckFileUtils.class);
	
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
				ReadFilePropertiesUtils.get(ConstantUtils.EXTENTION_XML)};
		
		String extentFile = FilenameUtils.getExtension(handle.getName());
		for (String extend : extentionArr) {
			if (extentFile.equalsIgnoreCase(extend)) {
				return true;
			}
		}
		return false;
	}


}