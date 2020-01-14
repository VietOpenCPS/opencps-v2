package org.opencps.auth.utils;

import java.io.File;
import java.io.InputStream;

import org.apache.commons.io.FilenameUtils;

public class CheckFileUtils {

	public static boolean checkFileUpload(InputStream inputStream, String fileName) {
		if (inputStream == null) {
			return false;
		}
		
			// Check extention
		if (!checkExtentionFile(fileName)) {
			return false;
		}
		return true;
	}

	public static boolean checkFileUpload(File file) {
		if (file == null) {
			return false;
		}
		
			// Check extention
		if (!checkExtentionFile(file)) {
			return false;
		}
		return true;
	}

	private static boolean checkExtentionFile(String fileName) {
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
		
		String extentFile = FilenameUtils.getExtension(fileName);
		for (String extend : extentionArr) {
			if (extentFile.equalsIgnoreCase(extend)) {
				return true;
			}
		}
		return false;
	}

	private static boolean checkExtentionFile(File file) {
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
		
		String extentFile = FilenameUtils.getExtension(file.getName());
		for (String extend : extentionArr) {
			if (extentFile.equalsIgnoreCase(extend)) {
				return true;
			}
		}
		return false;
	}
}