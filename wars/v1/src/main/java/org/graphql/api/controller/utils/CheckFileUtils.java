package org.graphql.api.controller.utils;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;

import java.io.IOException;
import java.io.InputStream;

import javax.activation.MimeType;

import org.apache.commons.io.FilenameUtils;
import org.opencps.dossiermgt.action.util.ConstantUtils;
import org.opencps.dossiermgt.action.util.ReadFilePropertiesUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class CheckFileUtils {

	public static boolean checkFileUpload(CommonsMultipartFile multipartFile) {
		if (multipartFile == null) {
			return false;
		}
		
		String typeAtt = multipartFile.getContentType().toString();
		if (typeAtt != null) {
			try {
				new MimeType(typeAtt);
			} catch (Exception e) {
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

	private static boolean checkExtentionFile(CommonsMultipartFile multipartFile) {
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