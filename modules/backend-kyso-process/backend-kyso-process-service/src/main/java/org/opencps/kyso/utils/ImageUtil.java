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

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;

import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.Base64;

/**
 * @author trungnt
 */
public class ImageUtil {

	/**
	 * @param url
	 * @return
	 */
	public static String getSignatureImageBase64(String url) {

		String base64 = StringPool.BLANK;

		InputStream is = null;

		ByteArrayOutputStream os = null;

		try {

			is = new URL(url).openStream();

			os = new ByteArrayOutputStream();

			byte[] buffer = new byte[1024];

			byte[] imageBuff = null;

			int length;

			while ((length = is.read(buffer)) != -1)
				os.write(buffer, 0, length); // copy streams

			imageBuff = os.toByteArray();

			base64 = Base64.encode(imageBuff);

		} catch (Exception e) {
			_log.error(e);
		} finally {
			try {
				if (is != null) {
					is.close();
				}
				if (os != null) {
					os.close();
				}
			} catch (IOException e) {
				_log.error(e);
			}
		}
		return base64;
	}

	/**
	 * @param fullPath
	 * @return
	 */
	public static String getSignatureImageBase64ByPath(String fullPath) {

		String base64 = StringPool.BLANK;

		InputStream is = null;

		ByteArrayOutputStream os = null;

		try {
			is = new FileInputStream(new File(fullPath));
			os = new ByteArrayOutputStream();

			byte[] buffer = new byte[1024];

			byte[] imageBuff = null;

			int length;

			while ((length = is.read(buffer)) != -1)
				os.write(buffer, 0, length); // copy streams

			imageBuff = os.toByteArray();

			base64 = Base64.encode(imageBuff);

		} catch (Exception e) {
			_log.error(e);
		} finally {
			try {
				if (is != null) {
					is.close();
				}
				if (os != null) {
					os.close();
				}
			} catch (IOException e) {
				_log.error(e);
			}
		}
		return base64;
	}

	/**
	 * @param url
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException
	 */

	public static BufferedImage getImage(String url) throws IOException,
			URISyntaxException {

		InputStream is = null;
		BufferedImage bimg = null;
		try {

			is = new URL(url).openStream();
			bimg = ImageIO.read(is);
		} catch (Exception e) {
			_log.error(e);
		} finally {
			if (is != null) {
				is.close();

			}
		}

		return bimg;
	}

	/**
	 * @param fullPath
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public static BufferedImage getImageByPath(String fullPath)
			throws IOException, URISyntaxException {

		InputStream is = null;
		BufferedImage bimg = null;
		try {
			is = new FileInputStream(new File(fullPath));

			bimg = ImageIO.read(is);
		} catch (Exception e) {
			_log.error(e);
		} finally {
			if (is != null) {
				is.close();

			}
		}

		return bimg;
	}

	public static String saveAsImage(String strURL, String dest, String email,
			String ext, long fileId) {

		InputStream is = null;
		OutputStream os = null;
		try {

			FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(fileId);
			is = fileEntry.getContentStream();
			// image = ImageIO.read(is);
			String imagePath = dest + email + "." + ext;
			// ImageIO.write(image, ext, new File(fileName));

			os = new FileOutputStream(imagePath);

			byte[] b = new byte[1024];
			int length;

			while ((length = is.read(b)) != -1) {
				os.write(b, 0, length);
			}

			return imagePath;
		} catch (Exception e) {
			_log.error(e);
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e1) {
				_log.error(e1);
			} finally {
				try {
					if (os != null) {
						os.close();
					}
				} catch (IOException e2) {
					_log.error(e2);
				}
			}
		}
		return StringPool.BLANK;
	}

	private static Log _log = LogFactoryUtil.getLog(ImageUtil.class);

}
