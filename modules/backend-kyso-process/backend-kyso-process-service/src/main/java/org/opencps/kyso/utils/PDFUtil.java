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

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;

/**
 * @author trungnt
 */
public class PDFUtil {

	public static String saveAsPdf(String dest, long fileEntryId)
		throws IOException {

		InputStream is = null;
		OutputStream os = null;
		String imagePath = StringPool.BLANK;
		try {

			DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(fileEntryId);
			
			if (fileEntry == null) {
				return StringPool.BLANK;
			}

			if (!fileEntry
				.getExtension().equals("pdf")) {
				return StringPool.BLANK;
			}

			is = fileEntry
				.getContentStream();

			imagePath = dest + fileEntry
				.getTitle() + StringPool.DASH + System
					.currentTimeMillis() +
				"_tmp." + fileEntry
					.getExtension();

			os = new FileOutputStream(imagePath);

			byte[] b = new byte[1024];
			int length;

			while ((length = is
				.read(b)) != -1) {
				os
					.write(b, 0, length);
			}
		}
		catch (Exception e) {
			_log
				.error(e);
		}
		finally {
			if (is != null) {
				is
					.close();
			}
			if (os != null) {
				os
					.close();
			}
		}
		return imagePath;
	}

	private static Log _log = LogFactoryUtil
		.getLog(PDFUtil.class);
}
