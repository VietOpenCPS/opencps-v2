/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package org.opencps.usermgt.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for FileItem. This utility wraps
 * {@link org.opencps.usermgt.service.impl.FileItemLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author khoavu
 * @see FileItemLocalService
 * @see org.opencps.usermgt.service.base.FileItemLocalServiceBaseImpl
 * @see org.opencps.usermgt.service.impl.FileItemLocalServiceImpl
 * @generated
 */
@ProviderType
public class FileItemLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.usermgt.service.impl.FileItemLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the file item to the database. Also notifies the appropriate model listeners.
	*
	* @param fileItem the file item
	* @return the file item that was added
	*/
	public static org.opencps.usermgt.model.FileItem addFileItem(
		org.opencps.usermgt.model.FileItem fileItem) {
		return getService().addFileItem(fileItem);
	}

	public static org.opencps.usermgt.model.FileItem adminProcessData(
		com.liferay.portal.kernel.json.JSONObject objectData) {
		return getService().adminProcessData(objectData);
	}

	public static org.opencps.usermgt.model.FileItem adminProcessDelete(Long id) {
		return getService().adminProcessDelete(id);
	}

	/**
	* Creates a new file item with the primary key. Does not add the file item to the database.
	*
	* @param fileItemId the primary key for the new file item
	* @return the new file item
	*/
	public static org.opencps.usermgt.model.FileItem createFileItem(
		long fileItemId) {
		return getService().createFileItem(fileItemId);
	}

	public static org.opencps.usermgt.model.FileItem createFileItem(
		com.liferay.portal.kernel.service.ServiceContext context, long groupId,
		String fileTemplateNo, String name, int status, int size,
		String fileType, String log)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .createFileItem(context, groupId, fileTemplateNo, name,
			status, size, fileType, log);
	}

	/**
	* Deletes the file item from the database. Also notifies the appropriate model listeners.
	*
	* @param fileItem the file item
	* @return the file item that was removed
	*/
	public static org.opencps.usermgt.model.FileItem deleteFileItem(
		org.opencps.usermgt.model.FileItem fileItem) {
		return getService().deleteFileItem(fileItem);
	}

	/**
	* Deletes the file item with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param fileItemId the primary key of the file item
	* @return the file item that was removed
	* @throws PortalException if a file item with the primary key could not be found
	*/
	public static org.opencps.usermgt.model.FileItem deleteFileItem(
		long fileItemId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteFileItem(fileItemId);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.FileItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.FileItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static org.opencps.usermgt.model.FileItem fetchFileItem(
		long fileItemId) {
		return getService().fetchFileItem(fileItemId);
	}

	/**
	* Returns the file item matching the UUID and group.
	*
	* @param uuid the file item's UUID
	* @param groupId the primary key of the group
	* @return the matching file item, or <code>null</code> if a matching file item could not be found
	*/
	public static org.opencps.usermgt.model.FileItem fetchFileItemByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchFileItemByUuidAndGroupId(uuid, groupId);
	}

	public static org.opencps.usermgt.model.FileItem findByG_FTN(long groupId,
		String fileTemplateNo) {
		return getService().findByG_FTN(groupId, fileTemplateNo);
	}

	public static java.util.List<org.opencps.usermgt.model.FileItem> findByG_FTNS(
		long groupId, String[] fileTemplateNos) {
		return getService().findByG_FTNS(groupId, fileTemplateNos);
	}

	public static java.util.List<org.opencps.usermgt.model.FileItem> findByG_S(
		long groupId, int status) {
		return getService().findByG_S(groupId, status);
	}

	public static java.util.List<org.opencps.usermgt.model.FileItem> findByG_S(
		long groupId, int status, int start, int end) {
		return getService().findByG_S(groupId, status, start, end);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	/**
	* Returns the file item with the primary key.
	*
	* @param fileItemId the primary key of the file item
	* @return the file item
	* @throws PortalException if a file item with the primary key could not be found
	*/
	public static org.opencps.usermgt.model.FileItem getFileItem(
		long fileItemId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getFileItem(fileItemId);
	}

	/**
	* Returns the file item matching the UUID and group.
	*
	* @param uuid the file item's UUID
	* @param groupId the primary key of the group
	* @return the matching file item
	* @throws PortalException if a matching file item could not be found
	*/
	public static org.opencps.usermgt.model.FileItem getFileItemByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getFileItemByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the file items.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.FileItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of file items
	* @param end the upper bound of the range of file items (not inclusive)
	* @return the range of file items
	*/
	public static java.util.List<org.opencps.usermgt.model.FileItem> getFileItems(
		int start, int end) {
		return getService().getFileItems(start, end);
	}

	/**
	* Returns all the file items matching the UUID and company.
	*
	* @param uuid the UUID of the file items
	* @param companyId the primary key of the company
	* @return the matching file items, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.usermgt.model.FileItem> getFileItemsByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService().getFileItemsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of file items matching the UUID and company.
	*
	* @param uuid the UUID of the file items
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of file items
	* @param end the upper bound of the range of file items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching file items, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.usermgt.model.FileItem> getFileItemsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.usermgt.model.FileItem> orderByComparator) {
		return getService()
				   .getFileItemsByUuidAndCompanyId(uuid, companyId, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of file items.
	*
	* @return the number of file items
	*/
	public static int getFileItemsCount() {
		return getService().getFileItemsCount();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the file item in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param fileItem the file item
	* @return the file item that was updated
	*/
	public static org.opencps.usermgt.model.FileItem updateFileItem(
		org.opencps.usermgt.model.FileItem fileItem) {
		return getService().updateFileItem(fileItem);
	}

	public static org.opencps.usermgt.model.FileItem updateFileItem(
		com.liferay.portal.kernel.service.ServiceContext context, long groupId,
		long fileItemId, String fileTemplateNo, String name, int status,
		int size, String fileType, String log)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateFileItem(context, groupId, fileItemId,
			fileTemplateNo, name, status, size, fileType, log);
	}

	public static FileItemLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<FileItemLocalService, FileItemLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(FileItemLocalService.class);

		ServiceTracker<FileItemLocalService, FileItemLocalService> serviceTracker =
			new ServiceTracker<FileItemLocalService, FileItemLocalService>(bundle.getBundleContext(),
				FileItemLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}