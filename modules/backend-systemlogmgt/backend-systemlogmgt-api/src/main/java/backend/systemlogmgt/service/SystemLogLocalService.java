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

package backend.systemlogmgt.service;

import aQute.bnd.annotation.ProviderType;

import backend.systemlogmgt.model.SystemLog;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.text.ParseException;

import java.util.Date;
import java.util.List;

/**
 * Provides the local service interface for SystemLog. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see SystemLogLocalServiceUtil
 * @see backend.systemlogmgt.service.base.SystemLogLocalServiceBaseImpl
 * @see backend.systemlogmgt.service.impl.SystemLogLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface SystemLogLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SystemLogLocalServiceUtil} to access the system log local service. Add custom service methods to {@link backend.systemlogmgt.service.impl.SystemLogLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the system log to the database. Also notifies the appropriate model listeners.
	*
	* @param systemLog the system log
	* @return the system log that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public SystemLog addSystemLog(SystemLog systemLog);

	/**
	* Creates a new system log with the primary key. Does not add the system log to the database.
	*
	* @param logId the primary key for the new system log
	* @return the new system log
	*/
	@Transactional(enabled = false)
	public SystemLog createSystemLog(long logId);

	public SystemLog createSystemLog(long groupId, String className,
		String moduleName, String message, String type, int line);

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	/**
	* Deletes the system log with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param logId the primary key of the system log
	* @return the system log that was removed
	* @throws PortalException if a system log with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public SystemLog deleteSystemLog(long logId) throws PortalException;

	/**
	* Deletes the system log from the database. Also notifies the appropriate model listeners.
	*
	* @param systemLog the system log
	* @return the system log that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public SystemLog deleteSystemLog(SystemLog systemLog);

	public DynamicQuery dynamicQuery();

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link backend.systemlogmgt.model.impl.SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link backend.systemlogmgt.model.impl.SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SystemLog fetchSystemLog(long logId);

	/**
	* Returns the system log matching the UUID and group.
	*
	* @param uuid the system log's UUID
	* @param groupId the primary key of the group
	* @return the matching system log, or <code>null</code> if a matching system log could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SystemLog fetchSystemLogByUuidAndGroupId(String uuid, long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SystemLog> getAllSystemLog();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public String getOSGiServiceIdentifier();

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Returns the system log with the primary key.
	*
	* @param logId the primary key of the system log
	* @return the system log
	* @throws PortalException if a system log with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SystemLog getSystemLog(long logId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SystemLog getSystemLogByLogId(long logId) throws PortalException;

	/**
	* Returns the system log matching the UUID and group.
	*
	* @param uuid the system log's UUID
	* @param groupId the primary key of the group
	* @return the matching system log
	* @throws PortalException if a matching system log could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SystemLog getSystemLogByUuidAndGroupId(String uuid, long groupId)
		throws PortalException;

	/**
	* Returns a range of all the system logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link backend.systemlogmgt.model.impl.SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @return the range of system logs
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SystemLog> getSystemLogs(int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SystemLog> getSystemLogsByClassName(String className);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SystemLog> getSystemLogsByDate(long fromDate, long toDate)
		throws ParseException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SystemLog> getSystemLogsByGroupId(long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SystemLog> getSystemLogsByLine(int line);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SystemLog> getSystemLogsByModuleName(String moduleName);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SystemLog> getSystemLogsByType(String type);

	/**
	* Returns the number of system logs.
	*
	* @return the number of system logs
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getSystemLogsCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SystemLog> getSystemLogsFromDateToDate(Date fromDate,
		Date toDate) throws ParseException;

	public SystemLog updateSystemLog(long logId, long groupId,
		String className, String moduleName, String message, String type,
		int line) throws PortalException;

	/**
	* Updates the system log in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param systemLog the system log
	* @return the system log that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public SystemLog updateSystemLog(SystemLog systemLog);
}