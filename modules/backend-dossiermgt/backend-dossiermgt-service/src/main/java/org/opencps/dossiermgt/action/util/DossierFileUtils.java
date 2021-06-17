package org.opencps.dossiermgt.action.util;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

public class DossierFileUtils {
	public static InputStream base64ToInputStream(String fileName, String base64) throws IOException {

		File file = FileUtil.createTempFile(fileName);

		InputStream inputStream = null;

		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);

			byte[] decoder = Base64.getDecoder().decode(base64);

			fos.write(decoder);

			inputStream = new FileInputStream(file);

		} catch (Exception e) {
			_log.error(e);
		} finally {
			if (fos != null) {
				fos.close();
			}
		}
		return inputStream;
	}

	public static String fileToBase64(File file) throws Exception{
		try {
			byte[] bytes = Base64.getEncoder().encode(Files.readAllBytes(file.toPath()));
			return new String(bytes);
		} catch (Exception e) {
			_log.error(e);
			throw new Exception(e.getMessage());
		}
	}

	public static File urlDownloadToFile(String url) throws Exception{
		InputStream inputStream = null;
		try {
			inputStream = new URL(url).openStream();
			return FileUtil.createTempFile(inputStream);
		} catch (Exception e) {
			_log.error(e);
			throw new Exception(e.getMessage());
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
	}

	public static File base64ToFile(String fileName, String base64) throws IOException {

		File file = FileUtil.createTempFile(fileName);

		FileOutputStream fos = null;

		try {
			fos = new FileOutputStream(file);

			byte[] decoder = Base64.getDecoder().decode(base64);

			fos.write(decoder);

		} catch (Exception e) {
			_log.error(e);
		} finally {
			if (fos != null) {
				fos.close();
			}
		}
		return file;
	}

	public static InputStream fileToInputStream(File file) throws IOException {

		InputStream inputStream = null;

		if (file != null) {
			try {
				inputStream = new FileInputStream(file);

			} catch (Exception e) {
				_log.error(e);
			}

		}
		return inputStream;
	}

	public static JSONObject getFileMetadata(File file) {

		JSONObject fileMetadata = JSONFactoryUtil.createJSONObject();
		if (file != null) {
			try {
				Path path = file.toPath();
				String mimeType = Files.probeContentType(path);
				fileMetadata.put("mimeType", mimeType);
				fileMetadata.put("fileSize", file.length());
				fileMetadata.put("fileName", file.getName());

			} catch (Exception e) {
				_log.error(e);
			}
		}

		return fileMetadata;
	}

	private static Log _log = LogFactoryUtil.getLog(DossierFileUtils.class.getName());
}
