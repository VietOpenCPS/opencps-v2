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

package org.opencps.auth.utils;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.NoSuchResourcePermissionException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ResourceAction;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.ResourcePermission;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.ResourceActionLocalServiceUtil;
import com.liferay.portal.kernel.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author trungnt
 */
public class DLFolderUtil {

	private static Log _log =
		LogFactoryUtil.getLog(DLFolderUtil.class.getName());

	public static DLFolder addFolder(
		long userId, long groupId, long repositoryId, boolean mountPoint,
		long parentFolderId, String name, String description, boolean hidden,
		ServiceContext serviceContext) {

		DLFolder dlFolder = null;
		try {
			if (hasFolder(groupId, parentFolderId, name)) {
				dlFolder = DLFolderLocalServiceUtil.getFolder(
					groupId, parentFolderId, name);
				setFolderPermissions(dlFolder);
			}
			else {
				User user =
					UserLocalServiceUtil.getUser(serviceContext.getUserId());
				PermissionChecker checker =
					PermissionCheckerFactoryUtil.create(user);
				PermissionThreadLocal.setPermissionChecker(checker);
				//serviceContext.setAddGroupPermissions(true);
				//serviceContext.setAddGuestPermissions(true);

				dlFolder = DLFolderLocalServiceUtil.addFolder(
					userId, groupId, repositoryId, mountPoint, parentFolderId,
					name, description, hidden, serviceContext);

				setFolderPermissions(dlFolder);
			}

		}
		catch (Exception e) {
			_log.info(e);
		}

		return dlFolder;
	}

	public static DLFolder getFolder(
		long userId, long groupId, long repositoryId, boolean mountPoint,
		long parentFolderId, String name, String description, boolean hidden,
		ServiceContext serviceContext) {

		DLFolder dlFolder = makeFolder(
			userId, groupId, repositoryId, mountPoint, parentFolderId, name,
			description, hidden, serviceContext);

		long folderId = 0;
		if (dlFolder != null) {
			folderId = dlFolder.getFolderId();
		}

		return getFolder(
			userId, groupId, repositoryId, mountPoint, folderId,
			name, description, hidden, serviceContext);

	}

	public static DLFolder getFolder(
		long groupId, long parentFolderId, String name) {

		DLFolder dlFolder = null;

		try {
			dlFolder = DLFolderLocalServiceUtil.fetchFolder(
				groupId, parentFolderId, name);
		}
		catch (SystemException e) {
			_log.debug(e);
			//_log.error(e);
		}

		return dlFolder;
	}

	public static DLFolder getTargetFolder(
		long userId, long groupId, long repositoryId, boolean mountPoint,
		long parentFolderId, String destination, String description,
		boolean hidden, ServiceContext serviceContext) throws Exception {

		DLFolder dlFolder = null;

		String[] folderNames = StringUtil.split(destination, StringPool.SLASH);

		if (folderNames != null && folderNames.length > 0) {
			String name = folderNames[0];

			dlFolder = makeFolder(
				userId, groupId, repositoryId, mountPoint, parentFolderId, name,
				description, hidden, serviceContext);

			//LamTV_Fix sonarqube
			if (dlFolder != null) {
				setFolderPermissions(dlFolder);

				folderNames = ArrayUtil.remove(folderNames, name);
				if (folderNames.length > 0) {
					dlFolder = getTargetFolder(
						userId, groupId, repositoryId, mountPoint,
						dlFolder.getFolderId(),
						StringUtil.merge(folderNames, StringPool.FORWARD_SLASH),
						description, hidden, serviceContext);
				}
			}
		}

		return dlFolder;
	}

	public static DLFolder getTargetFolder(
		long groupId, long parentFolderId, String destination) {

		DLFolder dlFolder = null;

		String[] folderNames =
			StringUtil.split(destination, StringPool.FORWARD_SLASH);

		if (folderNames != null && folderNames.length > 0) {
			String name = folderNames[0];
			dlFolder = getFolder(groupId, parentFolderId, name);
			folderNames = ArrayUtil.remove(folderNames, name);
			if (folderNames.length > 0 && dlFolder != null) {
				dlFolder = getTargetFolder(
					groupId, dlFolder.getFolderId(),
					StringUtil.merge(folderNames, StringPool.FORWARD_SLASH));
			}

		}

		return dlFolder;
	}

	public static boolean hasFolder(
		long groupId, long parentFolderId, String name) {

		boolean result = false;

		DLFolder dlFolder = null;

		try {
			dlFolder = DLFolderLocalServiceUtil.fetchFolder(
				groupId, parentFolderId, name);
		}
		catch (SystemException e) {
			_log.debug(e);
			//_log.error(e);
		}

		result = dlFolder != null ? true : false;

		return result;
	}

	public static DLFolder makeFolder(
		long userId, long groupId, long repositoryId, boolean mountPoint,
		long parentFolderId, String name, String description, boolean hidden,
		ServiceContext serviceContext) {

		if (hasFolder(groupId, parentFolderId, name)) {
			return getFolder(groupId, parentFolderId, name);
		}
		else {
			return addFolder(
				userId, groupId, repositoryId, mountPoint, parentFolderId, name,
				description, hidden, serviceContext);
		}
	}

	public static void setFilePermissions(FileEntry fileEntry)
		throws Exception {

		ResourcePermission resourcePermission = null;
		final Role guestMemberRole = RoleLocalServiceUtil.getRole(
			fileEntry.getCompanyId(), RoleConstants.GUEST);
		ResourceAction resourceAction =
			ResourceActionLocalServiceUtil.getResourceAction(
				DLFileEntry.class.getName(), ActionKeys.VIEW);
		String[] actionIdsGuest = new String[] {
			ActionKeys.VIEW, ActionKeys.ACCESS
		};
		try {
//			resourcePermission =
				ResourcePermissionLocalServiceUtil.getResourcePermission(
					fileEntry.getCompanyId(), DLFileEntry.class.getName(),
					ResourceConstants.SCOPE_INDIVIDUAL,
					String.valueOf(fileEntry.getPrimaryKey()),
					guestMemberRole.getRoleId());

			ResourcePermissionLocalServiceUtil.setResourcePermissions(
				fileEntry.getCompanyId(), DLFileEntry.class.getName(),
				ResourceConstants.SCOPE_INDIVIDUAL,
				String.valueOf(fileEntry.getPrimaryKey()),
				guestMemberRole.getRoleId(), actionIdsGuest);

			// if (Validator.isNotNull(resourcePermission)){
			// resourcePermission.setActionIds(resourceAction.getBitwiseValue());
			// ResourcePermissionLocalServiceUtil.updateResourcePermission(resourcePermission);
			// }
		}
		catch (NoSuchResourcePermissionException e) {
			_log.debug(e);
			//_log.error(e);
			resourcePermission =
				ResourcePermissionLocalServiceUtil.createResourcePermission(
					CounterLocalServiceUtil.increment());
			resourcePermission.setCompanyId(fileEntry.getCompanyId());
			resourcePermission.setName(DLFileEntry.class.getName());
			resourcePermission.setScope(ResourceConstants.SCOPE_INDIVIDUAL);
			resourcePermission.setPrimKey(
				String.valueOf(fileEntry.getPrimaryKey()));
			resourcePermission.setRoleId(guestMemberRole.getRoleId());
			resourcePermission.setActionIds(resourceAction.getBitwiseValue());// (ActionKeys.VIEW);
			ResourcePermissionLocalServiceUtil.addResourcePermission(
				resourcePermission);
		}
	}

	public static void setFolderPermissions(Folder folder)
		throws Exception {

		ResourcePermission resourcePermission = null;
		final Role guestMemberRole = RoleLocalServiceUtil.getRole(
			folder.getCompanyId(), RoleConstants.GUEST);
		final Role siteMemberRole = RoleLocalServiceUtil.getRole(
			folder.getCompanyId(), RoleConstants.SITE_MEMBER);
		ResourceAction resourceAction =
			ResourceActionLocalServiceUtil.getResourceAction(
				DLFolder.class.getName(), ActionKeys.VIEW);
		String[] actionIdsGuest = new String[] {
			ActionKeys.VIEW, ActionKeys.ACCESS
		};
		String[] actionIds = new String[] {
			ActionKeys.VIEW, ActionKeys.ACCESS, ActionKeys.SUBSCRIBE,
			ActionKeys.ADD_DOCUMENT, ActionKeys.UPDATE, ActionKeys.ADD_SHORTCUT,
			ActionKeys.ADD_SUBFOLDER, ActionKeys.PERMISSIONS, ActionKeys.DELETE
		};
		try {
			ResourcePermissionLocalServiceUtil.setResourcePermissions(
				folder.getCompanyId(), DLFolder.class.getName(),
				ResourceConstants.SCOPE_INDIVIDUAL,
				String.valueOf(folder.getPrimaryKey()),
				guestMemberRole.getRoleId(), actionIdsGuest);
			ResourcePermissionLocalServiceUtil.setResourcePermissions(
				folder.getCompanyId(), DLFolder.class.getName(),
				ResourceConstants.SCOPE_INDIVIDUAL,
				String.valueOf(folder.getPrimaryKey()),
				siteMemberRole.getRoleId(), actionIds);
		}
		catch (NoSuchResourcePermissionException e) {
			_log.debug(e);
			//_log.error(e);
			resourcePermission =
				ResourcePermissionLocalServiceUtil.createResourcePermission(
					CounterLocalServiceUtil.increment());
			resourcePermission.setCompanyId(folder.getCompanyId());
			resourcePermission.setName(DLFolder.class.getName());
			resourcePermission.setScope(ResourceConstants.SCOPE_INDIVIDUAL);
			resourcePermission.setPrimKey(
				String.valueOf(folder.getPrimaryKey()));
			resourcePermission.setRoleId(guestMemberRole.getRoleId());
			resourcePermission.setActionIds(resourceAction.getBitwiseValue());// (ActionKeys.VIEW);
			ResourcePermissionLocalServiceUtil.addResourcePermission(
				resourcePermission);
		}
	}

	public static void setFolderPermissions(DLFolder folder)
		throws Exception {

		ResourcePermission resourcePermission = null;
		final Role guestMemberRole = RoleLocalServiceUtil.getRole(
			folder.getCompanyId(), RoleConstants.GUEST);
		final Role siteMemberRole = RoleLocalServiceUtil.getRole(
			folder.getCompanyId(), RoleConstants.SITE_MEMBER);
		ResourceAction resourceAction =
			ResourceActionLocalServiceUtil.getResourceAction(
				DLFolder.class.getName(), ActionKeys.VIEW);
		String[] actionIdsGuest = new String[] {
			ActionKeys.VIEW, ActionKeys.ACCESS
		};
		String[] actionIds = new String[] {
			ActionKeys.VIEW, ActionKeys.ACCESS, ActionKeys.SUBSCRIBE,
			ActionKeys.ADD_DOCUMENT, ActionKeys.UPDATE, ActionKeys.ADD_SHORTCUT,
			ActionKeys.ADD_SUBFOLDER, ActionKeys.PERMISSIONS, ActionKeys.DELETE
		};
		try {
			ResourcePermissionLocalServiceUtil.setResourcePermissions(
				folder.getCompanyId(), DLFolder.class.getName(),
				ResourceConstants.SCOPE_INDIVIDUAL,
				String.valueOf(folder.getPrimaryKey()),
				guestMemberRole.getRoleId(), actionIdsGuest);
			ResourcePermissionLocalServiceUtil.setResourcePermissions(
				folder.getCompanyId(), DLFolder.class.getName(),
				ResourceConstants.SCOPE_INDIVIDUAL,
				String.valueOf(folder.getPrimaryKey()),
				siteMemberRole.getRoleId(), actionIds);
		}
		catch (NoSuchResourcePermissionException e) {
			_log.debug(e);
			//_log.error(e);
			resourcePermission =
				ResourcePermissionLocalServiceUtil.createResourcePermission(
					CounterLocalServiceUtil.increment());
			resourcePermission.setCompanyId(folder.getCompanyId());
			resourcePermission.setName(DLFolder.class.getName());
			resourcePermission.setScope(ResourceConstants.SCOPE_INDIVIDUAL);
			resourcePermission.setPrimKey(
				String.valueOf(folder.getPrimaryKey()));
			resourcePermission.setRoleId(guestMemberRole.getRoleId());
			resourcePermission.setActionIds(resourceAction.getBitwiseValue());// (ActionKeys.VIEW);
			ResourcePermissionLocalServiceUtil.addResourcePermission(
				resourcePermission);
		}
	}

}
