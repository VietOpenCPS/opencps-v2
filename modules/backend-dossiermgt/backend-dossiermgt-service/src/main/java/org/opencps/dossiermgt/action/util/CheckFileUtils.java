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
			return false;
		}
		return true;
	}

	private static boolean checkExtentionFile(DataHandler handle) {
		//return false;
		String[] extentionArr = { "png", "pdf", "doc", "docx", "xls", "xlsx", "jpg", "jpeg", "txt", "rtf", "xml", "zip", "rar" };
		
		String extentFile = FilenameUtils.getExtension(handle.getName());
		for (String extend : extentionArr) {
			if (extentFile.equalsIgnoreCase(extend)) {
				return true;
			}
		}
		return false;
	}


}