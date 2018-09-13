package org.opencps.datamgt.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.portal.kernel.exception.NoSuchResourcePermissionException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ResourceAction;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.ResourcePermission;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ResourceActionLocalServiceUtil;
import com.liferay.portal.kernel.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;

public class DocumentUtils {

	private static Log _log = LogFactoryUtil.getLog(DocumentUtils.class);

	public static boolean isFolderExist(long groupId, long parentFolderId, String folderName){

		boolean folderExist = false;
		try {
			DLAppLocalServiceUtil.getFolder(groupId, 
					parentFolderId, 
					folderName);
			folderExist = true;
		} catch (Exception e) {
			_log.error(e);
		}
		return folderExist;
	}
	
	public static Folder createFolder(long groupId, long parentFolderId, String folderName, String description, ServiceContext serviceContext) throws PortalException{
		boolean folderExist = isFolderExist(groupId, parentFolderId, folderName);
		Folder folder = null;
		if (!folderExist) {
			try {
				
				folder = DLAppLocalServiceUtil.addFolder(serviceContext.getUserId(), groupId, parentFolderId, folderName, description, serviceContext);
				
				setFolderPermissions(folder);
			} catch (Exception e) {
				_log.error(e);
			}
		}else{
			folder = DLAppLocalServiceUtil.getFolder(groupId,
					parentFolderId, 
					folderName);
		}
		return folder;
	}
	
	public static long fileUpload(File file, String fileName, String title, String mimeType, String description,
			long groupId, long parentFolderId, String folderName,
			ServiceContext serviceContext){
		long fileEntryId = 0;
		InputStream is = null;
		try {
			Folder folder = DLAppLocalServiceUtil.getFolder(groupId,
					parentFolderId, 
					folderName);
			
			is = new FileInputStream(file);
			
			FileEntry fileEntry = DLAppLocalServiceUtil.addFileEntry(serviceContext.getUserId(), groupId, folder.getFolderId(), fileName, mimeType,
					title, description, "", is, file.length(), serviceContext);
			
			setFilePermissions(fileEntry);
			
			fileEntryId = fileEntry.getFileEntryId();
		} catch (FileNotFoundException e) {
			_log.error(e);
		} catch (Exception e) {
			_log.error(e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					_log.error(e);
				}
			}
		}
		
		return fileEntryId;
	}
	
	public static void setFilePermissions(FileEntry fileEntry) throws Exception{
		ResourcePermission resourcePermission = null;
		final Role guestMemberRole = RoleLocalServiceUtil.getRole(fileEntry.getCompanyId(), RoleConstants.GUEST);
		ResourceAction resourceAction = ResourceActionLocalServiceUtil.getResourceAction(DLFileEntry.class.getName(), ActionKeys.VIEW);
		String[] actionIdsGuest = new String[] { ActionKeys.VIEW, ActionKeys.ACCESS };
		try{
//			resourcePermission = 
			ResourcePermissionLocalServiceUtil.getResourcePermission(fileEntry.getCompanyId(),
					DLFileEntry.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL,
					String.valueOf(fileEntry.getPrimaryKey()), guestMemberRole.getRoleId());

			ResourcePermissionLocalServiceUtil.setResourcePermissions(fileEntry.getCompanyId(), 
					DLFileEntry.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, 
					 String.valueOf(fileEntry.getPrimaryKey()), guestMemberRole.getRoleId(), actionIdsGuest);
			
//			if (Validator.isNotNull(resourcePermission)){
//				resourcePermission.setActionIds(resourceAction.getBitwiseValue());
//				ResourcePermissionLocalServiceUtil.updateResourcePermission(resourcePermission);
//			}
		} catch (NoSuchResourcePermissionException e){
			_log.error(e);
			resourcePermission = ResourcePermissionLocalServiceUtil
			.createResourcePermission(CounterLocalServiceUtil.increment());
			resourcePermission.setCompanyId(fileEntry.getCompanyId());
			resourcePermission.setName(DLFileEntry.class.getName());
			resourcePermission.setScope(ResourceConstants.SCOPE_INDIVIDUAL);
			resourcePermission.setPrimKey(String.valueOf(fileEntry.getPrimaryKey()));
			resourcePermission.setRoleId(guestMemberRole.getRoleId());
			resourcePermission.setActionIds(resourceAction.getBitwiseValue());// (ActionKeys.VIEW);
			ResourcePermissionLocalServiceUtil.addResourcePermission(resourcePermission);
		}
	} 
	public static void setFolderPermissions(Folder folder) throws Exception{
		ResourcePermission resourcePermission = null;
		final Role guestMemberRole = RoleLocalServiceUtil.getRole(folder.getCompanyId(), RoleConstants.GUEST);
		final Role siteMemberRole = RoleLocalServiceUtil.getRole(folder.getCompanyId(), RoleConstants.SITE_MEMBER);
		ResourceAction resourceAction = ResourceActionLocalServiceUtil.getResourceAction(DLFolder.class.getName(), ActionKeys.VIEW);
		String[] actionIdsGuest = new String[] { ActionKeys.VIEW, ActionKeys.ACCESS };
		String[] actionIds = new String[] { ActionKeys.VIEW, ActionKeys.ACCESS, ActionKeys.SUBSCRIBE, ActionKeys.ADD_DOCUMENT, ActionKeys.UPDATE, ActionKeys.ADD_SHORTCUT, ActionKeys.ADD_SUBFOLDER, ActionKeys.PERMISSIONS, ActionKeys.DELETE };
		try{
			 ResourcePermissionLocalServiceUtil.setResourcePermissions(folder.getCompanyId(), 
					 DLFolder.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, 
					 String.valueOf(folder.getPrimaryKey()), guestMemberRole.getRoleId(), actionIdsGuest);
			 ResourcePermissionLocalServiceUtil.setResourcePermissions(folder.getCompanyId(), 
					 DLFolder.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, 
					 String.valueOf(folder.getPrimaryKey()), siteMemberRole.getRoleId(), actionIds);
		} catch (NoSuchResourcePermissionException e){
			_log.error(e);
			resourcePermission = ResourcePermissionLocalServiceUtil
			.createResourcePermission(CounterLocalServiceUtil.increment());
			resourcePermission.setCompanyId(folder.getCompanyId());
			resourcePermission.setName(DLFolder.class.getName());
			resourcePermission.setScope(ResourceConstants.SCOPE_INDIVIDUAL);
			resourcePermission.setPrimKey(String.valueOf(folder.getPrimaryKey()));
			resourcePermission.setRoleId(guestMemberRole.getRoleId());
			resourcePermission.setActionIds(resourceAction.getBitwiseValue());// (ActionKeys.VIEW);
			ResourcePermissionLocalServiceUtil.addResourcePermission(resourcePermission);
		}
	} 
}
