package org.opencps.dossiermgt.action.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;
import java.io.InputStream;

import javax.activation.DataHandler;

import org.apache.commons.io.FilenameUtils;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;

public class CheckFileUtils {

	public static boolean checkFileUpload(Attachment attachment, String extentFile) {
//		_log.info("START CHECK FILE UPLOAD");
		
		if (attachment == null) {
//			_log.info("START CHECK FILE UPLOAD ATTACHMENT NULL");
			return false;
		}

		InputStream stream = null;
		DataHandler handle = attachment.getDataHandler();
//		_log.info("UPLOAD ZIP FILE HANDLE: " + handle);
		if (handle == null) {
			return false;
		}
		if (Validator.isNull(extentFile) || !checkExtentionFile(extentFile)) {
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
				ReadFilePropertiesUtils.get(ConstantUtils.EXTENTION_XML),
				ReadFilePropertiesUtils.get(ConstantUtils.EXTENTION_ZIP)};
//		_log.info("EXTENSION: " + handle.getName());
		
		String extentFile = FilenameUtils.getExtension(handle.getName());
		_log.info("extentFile: "+extentFile);
		for (String extend : extentionArr) {
			if (extentFile.equalsIgnoreCase(extend)) {
				return true;
			}
		}
		return false;
	}

	private static boolean checkExtentionFile(String extentFile) {
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
				ReadFilePropertiesUtils.get(ConstantUtils.EXTENTION_ZIP)};
//		_log.info("EXTENSION: " + handle.getName());
		
		for (String extend : extentionArr) {
			if (extentFile.equalsIgnoreCase(extend)) {
				return true;
			}
		}
		return false;
	}


}