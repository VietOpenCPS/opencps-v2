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

package org.opencps.datamgt.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for FileAttach. This utility wraps
 * {@link org.opencps.datamgt.service.impl.FileAttachLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author khoavu
 * @see FileAttachLocalService
 * @see org.opencps.datamgt.service.base.FileAttachLocalServiceBaseImpl
 * @see org.opencps.datamgt.service.impl.FileAttachLocalServiceImpl
 * @generated
 */
@ProviderType
public class FileAttachLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.datamgt.service.impl.FileAttachLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the file attach to the database. Also notifies the appropriate model listeners.
	*
	* @param fileAttach the file attach
	* @return the file attach that was added
	*/
	public static org.opencps.datamgt.model.FileAttach addFileAttach(
		org.opencps.datamgt.model.FileAttach fileAttach) {
		return getService().addFileAttach(fileAttach);
	}

	public static org.opencps.datamgt.model.FileAttach addFileAttach(
		long userId, long groupId, String className, String classPK,
		String fullName, String email, long fileEntryId, String source,
		String sourceUrl, long docFileId, String fileName,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			com.liferay.portal.kernel.exception.NoSuchUserException {
		return getService()
				   .addFileAttach(userId, groupId, className, classPK,
			fullName, email, fileEntryId, source, sourceUrl, docFileId,
			fileName, serviceContext);
	}

	public static org.opencps.datamgt.model.FileAttach adminProcessData(
		com.liferay.portal.kernel.json.JSONObject objectData) {
		return getService().adminProcessData(objectData);
	}

	public static org.opencps.datamgt.model.FileAttach adminProcessDelete(
		Long id) {
		return getService().adminProcessDelete(id);
	}

	public static org.opencps.datamgt.model.FileAttach copyFileAttach(
		long userId, long groupId, long docFileId,
		org.opencps.datamgt.model.FileAttach fileAttach,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws java.io.IOException,
			com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .copyFileAttach(userId, groupId, docFileId, fileAttach,
			serviceContext);
	}

	public static long countLuceneSearchEngine(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return getService().countLuceneSearchEngine(params, searchContext);
	}

	/**
	* Creates a new file attach with the primary key. Does not add the file attach to the database.
	*
	* @param fileAttachId the primary key for the new file attach
	* @return the new file attach
	*/
	public static org.opencps.datamgt.model.FileAttach createFileAttach(
		long fileAttachId) {
		return getService().createFileAttach(fileAttachId);
	}

	/**
	* Deletes the file attach from the database. Also notifies the appropriate model listeners.
	*
	* @param fileAttach the file attach
	* @return the file attach that was removed
	*/
	public static org.opencps.datamgt.model.FileAttach deleteFileAttach(
		org.opencps.datamgt.model.FileAttach fileAttach) {
		return getService().deleteFileAttach(fileAttach);
	}

	/**
	* Deletes the file attach with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param fileAttachId the primary key of the file attach
	* @return the file attach that was removed
	* @throws PortalException if a file attach with the primary key could not be found
	*/
	public static org.opencps.datamgt.model.FileAttach deleteFileAttach(
		long fileAttachId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteFileAttach(fileAttachId);
	}

	/**
	* @param dictCollectionId
	* @param serviceContext
	* @return
	* @throws Exception
	*/
	public static org.opencps.datamgt.model.FileAttach deleteFileAttach(
		long fileAttachId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			org.opencps.auth.api.exception.NotFoundException {
		return getService().deleteFileAttach(fileAttachId, serviceContext);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.datamgt.model.impl.FileAttachModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.datamgt.model.impl.FileAttachModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.opencps.datamgt.model.FileAttach fetchFileAttach(
		long fileAttachId) {
		return getService().fetchFileAttach(fileAttachId);
	}

	public static java.util.List<org.opencps.datamgt.model.FileAttach> findByF_className_classPK(
		long groupId, String className, String classPK) {
		return getService()
				   .findByF_className_classPK(groupId, className, classPK);
	}

	public static java.util.List<org.opencps.datamgt.model.FileAttach> findByF_docFileId(
		long groupId, String className, String classPK, long docFileId) {
		return getService()
				   .findByF_docFileId(groupId, className, classPK, docFileId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Returns the file attach with the primary key.
	*
	* @param fileAttachId the primary key of the file attach
	* @return the file attach
	* @throws PortalException if a file attach with the primary key could not be found
	*/
	public static org.opencps.datamgt.model.FileAttach getFileAttach(
		long fileAttachId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getFileAttach(fileAttachId);
	}

	/**
	* Returns a range of all the file attachs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.datamgt.model.impl.FileAttachModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of file attachs
	* @param end the upper bound of the range of file attachs (not inclusive)
	* @return the range of file attachs
	*/
	public static java.util.List<org.opencps.datamgt.model.FileAttach> getFileAttachs(
		int start, int end) {
		return getService().getFileAttachs(start, end);
	}

	/**
	* Returns the number of file attachs.
	*
	* @return the number of file attachs
	*/
	public static int getFileAttachsCount() {
		return getService().getFileAttachsCount();
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

	public static com.liferay.portal.kernel.search.Hits luceneSearchEngine(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.Sort[] sorts, int start, int end,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return getService()
				   .luceneSearchEngine(params, sorts, start, end, searchContext);
	}

	/**
	* Updates the file attach in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param fileAttach the file attach
	* @return the file attach that was updated
	*/
	public static org.opencps.datamgt.model.FileAttach updateFileAttach(
		org.opencps.datamgt.model.FileAttach fileAttach) {
		return getService().updateFileAttach(fileAttach);
	}

	public static org.opencps.datamgt.model.FileAttach updateFileAttach(
		long userId, long fileAttachId, String className, String classPK,
		String fullName, String email, long fileEntryId, String source,
		String sourceUrl, long docFileId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			org.opencps.auth.api.exception.NotFoundException,
			com.liferay.portal.kernel.exception.NoSuchUserException {
		return getService()
				   .updateFileAttach(userId, fileAttachId, className, classPK,
			fullName, email, fileEntryId, source, sourceUrl, docFileId,
			serviceContext);
	}

	public static org.opencps.datamgt.model.FileAttach updateFileAttach(
		long userId, long fileAttachId, String className, String classPK,
		String fullName, String email, long fileEntryId, String source,
		String sourceUrl, long docFileId, String fileName,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			org.opencps.auth.api.exception.NotFoundException,
			com.liferay.portal.kernel.exception.NoSuchUserException {
		return getService()
				   .updateFileAttach(userId, fileAttachId, className, classPK,
			fullName, email, fileEntryId, source, sourceUrl, docFileId,
			fileName, serviceContext);
	}

	public static FileAttachLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<FileAttachLocalService, FileAttachLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(FileAttachLocalService.class);

		ServiceTracker<FileAttachLocalService, FileAttachLocalService> serviceTracker =
			new ServiceTracker<FileAttachLocalService, FileAttachLocalService>(bundle.getBundleContext(),
				FileAttachLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}