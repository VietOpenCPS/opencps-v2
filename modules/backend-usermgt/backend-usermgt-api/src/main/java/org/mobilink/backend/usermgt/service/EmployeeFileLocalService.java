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

package org.mobilink.backend.usermgt.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.exportimport.kernel.lar.PortletDataContext;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.mobilink.backend.usermgt.model.EmployeeFile;

import java.io.File;
import java.io.Serializable;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Provides the local service interface for EmployeeFile. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Binhth
 * @see EmployeeFileLocalServiceUtil
 * @see org.mobilink.backend.usermgt.service.base.EmployeeFileLocalServiceBaseImpl
 * @see org.mobilink.backend.usermgt.service.impl.EmployeeFileLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface EmployeeFileLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link EmployeeFileLocalServiceUtil} to access the employee file local service. Add custom service methods to {@link org.mobilink.backend.usermgt.service.impl.EmployeeFileLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	public DynamicQuery dynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	public Hits luceneSearchEngine(
		LinkedHashMap<java.lang.String, java.lang.Object> params, Sort[] sorts,
		int start, int end, SearchContext searchContext)
		throws ParseException, SearchException;

	/**
	* Returns the number of employee files.
	*
	* @return the number of employee files
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getEmployeeFilesCount();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.usermgt.model.impl.EmployeeFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.usermgt.model.impl.EmployeeFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	/**
	* Returns a range of all the employee files.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.usermgt.model.impl.EmployeeFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of employee files
	* @param end the upper bound of the range of employee files (not inclusive)
	* @return the range of employee files
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<EmployeeFile> getEmployeeFiles(int start, int end);

	/**
	* Returns all the employee files matching the UUID and company.
	*
	* @param uuid the UUID of the employee files
	* @param companyId the primary key of the company
	* @return the matching employee files, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<EmployeeFile> getEmployeeFilesByUuidAndCompanyId(
		java.lang.String uuid, long companyId);

	/**
	* Returns a range of employee files matching the UUID and company.
	*
	* @param uuid the UUID of the employee files
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of employee files
	* @param end the upper bound of the range of employee files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching employee files, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<EmployeeFile> getEmployeeFilesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<EmployeeFile> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	@Indexable(type = IndexableType.REINDEX)
	public EmployeeFile addEmployeeFile(long userId, long groupId,
		long employeeId, File file, java.lang.String documentName,
		java.lang.String title, java.lang.String mimeType,
		ServiceContext serviceContext) throws PortalException;

	/**
	* Adds the employee file to the database. Also notifies the appropriate model listeners.
	*
	* @param employeeFile the employee file
	* @return the employee file that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public EmployeeFile addEmployeeFile(EmployeeFile employeeFile);

	/**
	* Creates a new employee file with the primary key. Does not add the employee file to the database.
	*
	* @param employeeFileId the primary key for the new employee file
	* @return the new employee file
	*/
	public EmployeeFile createEmployeeFile(long employeeFileId);

	/**
	* Deletes the employee file with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param employeeFileId the primary key of the employee file
	* @return the employee file that was removed
	* @throws PortalException if a employee file with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public EmployeeFile deleteEmployeeFile(long employeeFileId)
		throws PortalException;

	@Indexable(type = IndexableType.DELETE)
	public EmployeeFile deleteEmployeeFile(long employeeFileId,
		ServiceContext serviceContext) throws java.lang.Exception;

	/**
	* Deletes the employee file from the database. Also notifies the appropriate model listeners.
	*
	* @param employeeFile the employee file
	* @return the employee file that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public EmployeeFile deleteEmployeeFile(EmployeeFile employeeFile);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public EmployeeFile fetchEmployeeFile(long employeeFileId);

	/**
	* Returns the employee file matching the UUID and group.
	*
	* @param uuid the employee file's UUID
	* @param groupId the primary key of the group
	* @return the matching employee file, or <code>null</code> if a matching employee file could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public EmployeeFile fetchEmployeeFileByUuidAndGroupId(
		java.lang.String uuid, long groupId);

	/**
	* Returns the employee file with the primary key.
	*
	* @param employeeFileId the primary key of the employee file
	* @return the employee file
	* @throws PortalException if a employee file with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public EmployeeFile getEmployeeFile(long employeeFileId)
		throws PortalException;

	/**
	* Returns the employee file matching the UUID and group.
	*
	* @param uuid the employee file's UUID
	* @param groupId the primary key of the group
	* @return the matching employee file
	* @throws PortalException if a matching employee file could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public EmployeeFile getEmployeeFileByUuidAndGroupId(java.lang.String uuid,
		long groupId) throws PortalException;

	/**
	* Updates the employee file in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param employeeFile the employee file
	* @return the employee file that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public EmployeeFile updateEmployeeFile(EmployeeFile employeeFile);
}