
package org.opencps.kernel.util;

import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;

import backend.utils.DLFolderUtil;

public class FileUploadUtil {

	public static FileEntry uploadFile(
		long userId, long companyId, long groupId, InputStream inputStream,
		String fileName, String fileType, long fileSize, String destination,
		String desc, ServiceContext serviceContext)
		throws Exception {

		if (inputStream != null && fileSize > 0 &&
			Validator.isNotNull(fileName)) {

			serviceContext.setAddGroupPermissions(true);
			serviceContext.setAddGuestPermissions(true);

//			Calendar calendar = Calendar.getInstance();
//			calendar.setTime(new Date());
//			destination += calendar.get(Calendar.YEAR) + StringPool.SLASH;
//			destination += calendar.get(Calendar.MONTH) + StringPool.SLASH;
//			destination += calendar.get(Calendar.DAY_OF_MONTH);

			destination = getTimeDestination(destination);
			DLFolder dlFolder = DLFolderUtil.getTargetFolder(userId, groupId, groupId, false, 0, destination, desc,
					false, serviceContext);
//			User user = UserLocalServiceUtil.getUser(serviceContext.getUserId());
//			PermissionChecker checker = PermissionCheckerFactoryUtil.create(user);
//			PermissionThreadLocal.setPermissionChecker(checker);

			processFileEntry(serviceContext);
			FileEntry fileEntry = DLAppLocalServiceUtil.addFileEntry(
				userId, groupId, dlFolder.getFolderId(), fileName, fileType,
				System.currentTimeMillis() + StringPool.DASH + fileName, desc,
				StringPool.BLANK, inputStream, fileSize, serviceContext);

			return fileEntry;
		}

		return null;
	}

	public static FileEntry uploadFile(
		long userId, long groupId, File file, String fileName, String fileType,
		String destination, String desc, ServiceContext serviceContext)
		throws Exception {

		FileEntry fileEntry = null;

		if (file != null && Validator.isNotNull(fileName)) {

			serviceContext.setAddGroupPermissions(true);
			serviceContext.setAddGuestPermissions(true);

//			Calendar calendar = Calendar.getInstance();
//			calendar.setTime(new Date());
//			destination += calendar.get(Calendar.YEAR) + StringPool.SLASH;
//			destination += calendar.get(Calendar.MONTH) + StringPool.SLASH;
//			destination += calendar.get(Calendar.DAY_OF_MONTH);
			destination = getTimeDestination(destination);

			DLFolder dlFolder = DLFolderUtil.getTargetFolder(
				userId, groupId, groupId, false, 0, destination, desc, false,
				serviceContext);

//			User user = UserLocalServiceUtil.getUser(serviceContext.getUserId());
//			PermissionChecker checker = PermissionCheckerFactoryUtil.create(user);
//			PermissionThreadLocal.setPermissionChecker(checker);
			processFileEntry(serviceContext);

			fileEntry = DLAppLocalServiceUtil.addFileEntry(
				userId, groupId, dlFolder.getFolderId(), fileName, fileType,
				System.currentTimeMillis() + StringPool.DASH + fileName, desc,
				StringPool.BLANK, file, serviceContext);

		}

		return fileEntry;
	}

	public static FileEntry updateFile(
		long userId, long companyId, long groupId, long fileEntryId,
		InputStream inputStream, String fileName, String fileType,
		long fileSize, String destination, String desc,
		ServiceContext serviceContext)
		throws Exception {

		FileEntry fileEntry = null;

		if (inputStream != null && fileSize > 0 &&
			Validator.isNotNull(fileName)) {

			serviceContext.setAddGroupPermissions(true);
			serviceContext.setAddGuestPermissions(true);

//			User user = UserLocalServiceUtil.getUser(serviceContext.getUserId());
//			PermissionChecker checker = PermissionCheckerFactoryUtil.create(user);
//			PermissionThreadLocal.setPermissionChecker(checker);
			processFileEntry(serviceContext);

			fileEntry = DLAppLocalServiceUtil.updateFileEntry(
				userId, fileEntryId, fileName, fileType,
				System.currentTimeMillis() + StringPool.DASH + fileName, desc,
				StringPool.BLANK, true, inputStream, fileSize, serviceContext);
		}

		return fileEntry;
	}

	private static String getTimeDestination(String destination) {

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(new Date());

		destination += calendar.get(Calendar.YEAR) + StringPool.SLASH;
		destination += calendar.get(Calendar.MONTH) + StringPool.SLASH;
		destination += calendar.get(Calendar.DAY_OF_MONTH);

		return destination;
	}

	//
	private static void processFileEntry(ServiceContext serviceContext) throws Exception {
		User user = UserLocalServiceUtil.getUser(serviceContext.getUserId());

		PermissionChecker checker = PermissionCheckerFactoryUtil.create(user);
		PermissionThreadLocal.setPermissionChecker(checker);

	}
}
