package org.opencps.api.controller.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ImportZipFileUtils {

	private static Log _log = LogFactoryUtil.getLog(ImportZipFileUtils.class);
	private static final int BUFFER_SIZE = 4096;

	//LamTV_ Process Unzip file
	public static void unzip(InputStream fileInputStream, String destDirectory) {
		File destDir = new File(destDirectory);
		if (!destDir.exists()) {
			destDir.mkdir();
		}
		ZipInputStream zipIn = null;
		ZipEntry entry = null;
		try {
			zipIn = new ZipInputStream(fileInputStream);
			entry = zipIn.getNextEntry();
			// iterates over entries in the zip file
			String filePath = StringPool.BLANK;
			while (entry != null) {
				filePath = destDirectory + File.separator + entry.getName();
				_log.info("LamTV_filePath: " + filePath);
				if (!entry.isDirectory()) {
					// if the entry is a file, extracts it
					extractFile(zipIn, filePath);
				} else {
					// if the entry is a directory, make the directory
					File dir = new File(filePath);
					_log.info("LamTV_AbsolutePath: " + dir.getAbsolutePath());
					_log.info("LamTV_File Path: " + dir.getPath());
					dir.mkdir();
					_log.info("LamTV_dir.mkdir(): " + dir.mkdir());
				}
				entry = zipIn.getNextEntry();
			}
		} catch (Exception e) {
			_log.error(e);
		} finally {
			try {
				if (zipIn != null) {
					zipIn.closeEntry();
					zipIn.close();
				}
			} catch (IOException e1) {
				//_log.error(e1);
				_log.debug(e1);
			}
		}

	}

	//LamTV_Process get source folder destination
	public static String getFolderPath(String fileName, String destDirectory) {
		String sourceFolder = StringPool.BLANK;
		String subFileName = getSubFileName(fileName);
		if (Validator.isNotNull(subFileName)) {
			sourceFolder = destDirectory + StringPool.FORWARD_SLASH + subFileName;
		}
		return sourceFolder;
	}

	//LamTV_Process get sub fileName
	public static String getSubFileName(String fileName) {
		String subNameFile = StringPool.BLANK;
		if (Validator.isNotNull(fileName)) {
			int index = fileName.lastIndexOf(StringPool.PERIOD);
			subNameFile = fileName.substring(0, index);
		}
		return subNameFile;
	}

	//LamTV_Process get sub fileName
	public static String getExtendFileName(String fileName) {
		String subNameFile = StringPool.BLANK;
		if (Validator.isNotNull(fileName)) {
			int index = fileName.lastIndexOf(StringPool.PERIOD);
			subNameFile = fileName.substring(index + 1);
		}
		return subNameFile;
	}

    /**
     * Extracts a zip entry (file entry)
     * @param zipIn
     * @param filePath
     * @throws IOException
     */
	private static void extractFile(ZipInputStream zipIn, String filePath) {
		BufferedOutputStream bos = null;
		try {
			bos = new BufferedOutputStream(new FileOutputStream(filePath));
			byte[] bytesIn = new byte[BUFFER_SIZE];
			int read = 0;
			while ((read = zipIn.read(bytesIn)) != -1) {
				bos.write(bytesIn, 0, read);
			}
		} catch (Exception e) {
			_log.error(e);
		} finally {
			try {
				if (bos != null)
					bos.close();
			} catch (IOException e1) {
				_log.debug(e1);
				//_log.error(e1);
			}
		}
	}

	public static String compress(String str) throws IOException {
	    if (str == null || str.length() == 0) {
	        return str;
	    }
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    GZIPOutputStream gzip = new GZIPOutputStream(out);
	    gzip.write(str.getBytes());
	    gzip.close();
	    String outStr = out.toString();
	    return outStr;
	 }	
}