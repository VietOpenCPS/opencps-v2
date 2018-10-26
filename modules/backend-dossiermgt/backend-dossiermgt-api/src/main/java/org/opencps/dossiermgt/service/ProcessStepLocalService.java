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
import com.liferay.portal.kernel.json.JSONObject;
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

import org.opencps.dossiermgt.model.ProcessStep;

import java.io.Serializable;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Provides the local service interface for ProcessStep. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author huymq
 * @see ProcessStepLocalServiceUtil
 * @see org.opencps.dossiermgt.service.base.ProcessStepLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.ProcessStepLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface ProcessStepLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ProcessStepLocalServiceUtil} to access the process step local service. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.ProcessStepLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the process step to the database. Also notifies the appropriate model listeners.
	*
	* @param processStep the process step
	* @return the process step that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ProcessStep addProcessStep(ProcessStep processStep);

	@Indexable(type = IndexableType.REINDEX)
	public ProcessStep adminProcessData(JSONObject objectData);

	@Indexable(type = IndexableType.DELETE)
	public ProcessStep adminProcessDelete(Long id);

	public long countLucene(LinkedHashMap<String, Object> params,
		SearchContext searchContext) throws ParseException, SearchException;

	/**
	* Creates a new process step with the primary key. Does not add the process step to the database.
	*
	* @param processStepId the primary key for the new process step
	* @return the new process step
	*/
	@Transactional(enabled = false)
	public ProcessStep createProcessStep(long processStepId);

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	/**
	* Deletes the process step with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param processStepId the primary key of the process step
	* @return the process step that was removed
	* @throws PortalException if a process step with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public ProcessStep deleteProcessStep(long processStepId)
		throws PortalException;

	/**
	* Deletes the process step from the database. Also notifies the appropriate model listeners.
	*
	* @param processStep the process step
	* @return the process step that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public ProcessStep deleteProcessStep(ProcessStep processStep);

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ProcessStepModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ProcessStepModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public ProcessStep fetchBySC_GID(String stepCode, long groupId,
		long serviceProcessId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ProcessStep fetchProcessStep(long processStepId);

	/**
	* Returns the process step matching the UUID and group.
	*
	* @param uuid the process step's UUID
	* @param groupId the primary key of the group
	* @return the matching process step, or <code>null</code> if a matching process step could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ProcessStep fetchProcessStepByUuidAndGroupId(String uuid,
		long groupId);

	public List<ProcessStep> findByG_SP_SNO(long groupId,
		long serviceProcessId, String sequenceNo);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ProcessStep> getBySC_SPID(String stepCode, long serviceProcessId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ProcessStep> getByStatusAnsSubStatus(String dossierStatus,
		String dossierSubStatus, long groupId);

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
	* Returns the process step with the primary key.
	*
	* @param processStepId the primary key of the process step
	* @return the process step
	* @throws PortalException if a process step with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ProcessStep getProcessStep(long processStepId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ProcessStep> getProcessStepbyServiceProcessId(
		long serviceProcessId);

	/**
	* Returns the process step matching the UUID and group.
	*
	* @param uuid the process step's UUID
	* @param groupId the primary key of the group
	* @return the matching process step
	* @throws PortalException if a matching process step could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ProcessStep getProcessStepByUuidAndGroupId(String uuid, long groupId)
		throws PortalException;

	/**
	* Returns a range of all the process steps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ProcessStepModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of process steps
	* @param end the upper bound of the range of process steps (not inclusive)
	* @return the range of process steps
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ProcessStep> getProcessSteps(int start, int end);

	/**
	* Returns all the process steps matching the UUID and company.
	*
	* @param uuid the UUID of the process steps
	* @param companyId the primary key of the company
	* @return the matching process steps, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ProcessStep> getProcessStepsByUuidAndCompanyId(String uuid,
		long companyId);

	/**
	* Returns a range of process steps matching the UUID and company.
	*
	* @param uuid the UUID of the process steps
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of process steps
	* @param end the upper bound of the range of process steps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching process steps, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ProcessStep> getProcessStepsByUuidAndCompanyId(String uuid,
		long companyId, int start, int end,
		OrderByComparator<ProcessStep> orderByComparator);

	/**
	* Returns the number of process steps.
	*
	* @return the number of process steps
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getProcessStepsCount();

	@Indexable(type = IndexableType.DELETE)
	public ProcessStep removeProcessStep(long processStepId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Hits searchLucene(LinkedHashMap<String, Object> params,
		Sort[] sorts, int start, int end, SearchContext searchContext)
		throws ParseException, SearchException;

	@Indexable(type = IndexableType.REINDEX)
	public ProcessStep updateProcessStep(long groupId, long processStepId,
		String stepCode, String stepName, long serviceProcessId,
		String sequenceNo, String dossierStatus, String dossierSubStatus,
		int durationCount, String customProcessUrl, String stepInstruction,
		String briefNote, boolean editable, String lockState,
		ServiceContext context) throws PortalException;

	/**
	* Updates the process step in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param processStep the process step
	* @return the process step that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ProcessStep updateProcessStep(ProcessStep processStep);

	@Indexable(type = IndexableType.REINDEX)
	public ProcessStep updateProcessStepDB(long userId, long groupId,
		long serviceProcessId, String stepCode, String stepName,
		String sequenceNo, String groupName, String dossierStatus,
		String dossierSubStatus, Double durationCount, String instructionNote,
		String briefNote, String roleAsStep, Integer checkInput,
		ServiceContext serviceContext) throws PortalException;
}