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

package org.opencps.dossiermgt.service;

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

import org.opencps.dossiermgt.model.ProcessAction;

import java.io.Serializable;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Provides the local service interface for ProcessAction. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author huymq
 * @see ProcessActionLocalServiceUtil
 * @see org.opencps.dossiermgt.service.base.ProcessActionLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.ProcessActionLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface ProcessActionLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ProcessActionLocalServiceUtil} to access the process action local service. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.ProcessActionLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the process action to the database. Also notifies the appropriate model listeners.
	*
	* @param processAction the process action
	* @return the process action that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ProcessAction addProcessAction(ProcessAction processAction);

	/**
	* Creates a new process action with the primary key. Does not add the process action to the database.
	*
	* @param processActionId the primary key for the new process action
	* @return the new process action
	*/
	@Transactional(enabled = false)
	public ProcessAction createProcessAction(long processActionId);

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	/**
	* Deletes the process action with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param processActionId the primary key of the process action
	* @return the process action that was removed
	* @throws PortalException if a process action with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public ProcessAction deleteProcessAction(long processActionId)
		throws PortalException;

	/**
	* Deletes the process action from the database. Also notifies the appropriate model listeners.
	*
	* @param processAction the process action
	* @return the process action that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public ProcessAction deleteProcessAction(ProcessAction processAction);

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public ProcessAction fetchBySPI_PRESC_AEV(long serviceProcessId,
		String preStepCode, String autoEvent);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ProcessAction fetchBySPID_AC(long serviceProcessId, String actionCode);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ProcessAction fetchProcessAction(long processActionId);

	/**
	* Returns the process action matching the UUID and group.
	*
	* @param uuid the process action's UUID
	* @param groupId the primary key of the group
	* @return the matching process action, or <code>null</code> if a matching process action could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ProcessAction fetchProcessActionByUuidAndGroupId(String uuid,
		long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ProcessAction> getByActionCode(long groupId, String actionCode)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ProcessAction> getByActionCode(long groupId, String actionCode,
		long serviceProcessId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ProcessAction> getByGroupAndAutoEvent(long groupId,
		String autoEvent, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ProcessAction getByNameActionNo(long serviceProcessId,
		String actionCode, String actionName);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ProcessAction getByServiceProcess(long serviceProcessId,
		String actionCode);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ProcessAction> getByServiceStepCode(long groupId,
		long serviceProcessId, String preStepCode);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

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
	* Returns the process action with the primary key.
	*
	* @param processActionId the primary key of the process action
	* @return the process action
	* @throws PortalException if a process action with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ProcessAction getProcessAction(long processActionId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ProcessAction> getProcessActionByG_SPID_PRESC(long groupId,
		long serviceProcessId, String preStepCode) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ProcessAction> getProcessActionbyServiceProcessId(
		long serviceProcessId) throws PortalException;

	/**
	* Returns the process action matching the UUID and group.
	*
	* @param uuid the process action's UUID
	* @param groupId the primary key of the group
	* @return the matching process action
	* @throws PortalException if a matching process action could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ProcessAction getProcessActionByUuidAndGroupId(String uuid,
		long groupId) throws PortalException;

	/**
	* Returns a range of all the process actions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ProcessActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of process actions
	* @param end the upper bound of the range of process actions (not inclusive)
	* @return the range of process actions
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ProcessAction> getProcessActions(int start, int end);

	/**
	* Returns all the process actions matching the UUID and company.
	*
	* @param uuid the UUID of the process actions
	* @param companyId the primary key of the company
	* @return the matching process actions, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ProcessAction> getProcessActionsByUuidAndCompanyId(
		String uuid, long companyId);

	/**
	* Returns a range of process actions matching the UUID and company.
	*
	* @param uuid the UUID of the process actions
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of process actions
	* @param end the upper bound of the range of process actions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching process actions, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ProcessAction> getProcessActionsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<ProcessAction> orderByComparator);

	/**
	* Returns the number of process actions.
	*
	* @return the number of process actions
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getProcessActionsCount();

	@Indexable(type = IndexableType.DELETE)
	public ProcessAction removeProcessAction(long processActionId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long searchCount(LinkedHashMap<String, Object> params,
		SearchContext searchContext) throws ParseException, SearchException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Hits searchLucene(LinkedHashMap<String, Object> params,
		Sort[] sorts, int start, int end, SearchContext searchContext)
		throws ParseException, SearchException;

	@Deprecated
	@Indexable(type = IndexableType.REINDEX)
	public ProcessAction updateProcessAction(long groupId,
		long processActionId, long serviceProcessId, String preStepCode,
		String postStepCode, String autoEvent, String preCondition,
		String actionCode, String actionName, int allowAssignUser,
		long assignUserId, Integer requestPayment, String paymentFee,
		String createDossierFiles, String returnDossierFiles,
		String makeBriefNote, String syncActionCode, boolean rollbackable,
		boolean createDossierNo, boolean eSignature, ServiceContext context)
		throws PortalException;

	@Indexable(type = IndexableType.REINDEX)
	public ProcessAction updateProcessAction(long groupId,
		long processActionId, long serviceProcessId, String preStepCode,
		String postStepCode, String autoEvent, String preCondition,
		String actionCode, String actionName, int allowAssignUser,
		long assignUserId, Integer requestPayment, String paymentFee,
		String createDossierFiles, String returnDossierFiles,
		String makeBriefNote, String syncActionCode, boolean rollbackable,
		boolean createDossierNo, boolean eSignature, String configNote,
		String dossierTemplateNo, ServiceContext context)
		throws PortalException;

	@Deprecated
	@Indexable(type = IndexableType.REINDEX)
	public ProcessAction updateProcessAction(long groupId,
		long processActionId, long serviceProcessId, String preStepCode,
		String postStepCode, String autoEvent, String preCondition,
		String actionCode, String actionName, int allowAssignUser,
		long assignUserId, Integer requestPayment, String paymentFee,
		String createDossierFiles, String returnDossierFiles,
		String makeBriefNote, String syncActionCode, boolean rollbackable,
		ServiceContext context) throws PortalException;

	/**
	* Updates the process action in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param processAction the process action
	* @return the process action that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ProcessAction updateProcessAction(ProcessAction processAction);

	@Indexable(type = IndexableType.REINDEX)
	public ProcessAction updateProcessActionDB(long userId, long groupId,
		long serviceProcessId, String actionCode, String actionName,
		String preStepCode, String postStepCode, String autoEvent,
		String preCondition, int allowAssignUser, long assignUserId,
		String assignUserName, Integer requestPayment, String paymentFee,
		String createDossierFiles, String returnDossierFiles,
		boolean eSignature, String signatureType, String createDossiers,
		ServiceContext serviceContext) throws PortalException;
}