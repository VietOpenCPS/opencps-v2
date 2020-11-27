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
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
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

import org.opencps.dossiermgt.exception.NoSuchDeliverableException;
import org.opencps.dossiermgt.model.Deliverable;

import java.io.Serializable;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Provides the local service interface for Deliverable. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author huymq
 * @see DeliverableLocalServiceUtil
 * @see org.opencps.dossiermgt.service.base.DeliverableLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.DeliverableLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface DeliverableLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DeliverableLocalServiceUtil} to access the deliverable local service. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.DeliverableLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the deliverable to the database. Also notifies the appropriate model listeners.
	*
	* @param deliverable the deliverable
	* @return the deliverable that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Deliverable addDeliverable(Deliverable deliverable);

	@Indexable(type = IndexableType.REINDEX)
	public Deliverable addDeliverable(long groupId, String deliverableType,
		String deliverableCode, String govAgencyCode, String govAgencyName,
		String applicationIdNo, String applicationName, String subject,
		String issueDate, String expireDate, String revalidate,
		String deliverableState, long dossierId, long fileEntryId,
		ServiceContext serviceContext);

	@Indexable(type = IndexableType.REINDEX)
	public Deliverable addDeliverable(long groupId, String deliverableType,
		String deliverableCode, String govAgencyCode, String govAgencyName,
		String applicationIdNo, String applicationName, String subject,
		String issueDate, String expireDate, String revalidate,
		String deliverableState, ServiceContext serviceContext);

	@Indexable(type = IndexableType.REINDEX)
	public Deliverable addDeliverableSign(long groupId, String deliverableType,
		String deliverableName, String deliverableCode, String govAgencyCode,
		String govAgencyName, String applicationIdNo, String applicationName,
		String subject, String issueDate, String expireDate, String revalidate,
		String deliverableState, long dossierId, long fileEntryId,
		long formScriptFileId, long formReportFileId, String formData,
		String fileAttachs, ServiceContext serviceContext);

	@Indexable(type = IndexableType.REINDEX)
	public Deliverable adminProcessData(JSONObject objectData);

	@Indexable(type = IndexableType.DELETE)
	public Deliverable adminProcessDelete(Long id);

	public long countLucene(LinkedHashMap<String, Object> params,
		SearchContext searchContext) throws ParseException, SearchException;

	public long countLucene(String keywords, String groupId, String type,
		Map<String, String> mapFilter, SearchContext searchContext, long userId)
		throws ParseException, SearchException;

	/**
	* Creates a new deliverable with the primary key. Does not add the deliverable to the database.
	*
	* @param deliverableId the primary key for the new deliverable
	* @return the new deliverable
	*/
	@Transactional(enabled = false)
	public Deliverable createDeliverable(long deliverableId);

	/**
	* Deletes the deliverable from the database. Also notifies the appropriate model listeners.
	*
	* @param deliverable the deliverable
	* @return the deliverable that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public Deliverable deleteDeliverable(Deliverable deliverable);

	/**
	* Deletes the deliverable with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param deliverableId the primary key of the deliverable
	* @return the deliverable that was removed
	* @throws PortalException if a deliverable with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public Deliverable deleteDeliverable(long deliverableId)
		throws PortalException;

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public Deliverable fetchByGID_DID(long groupId, long dossierId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Deliverable fetchDeliverable(long deliverableId);

	/**
	* Returns the deliverable matching the UUID and group.
	*
	* @param uuid the deliverable's UUID
	* @param groupId the primary key of the group
	* @return the matching deliverable, or <code>null</code> if a matching deliverable could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Deliverable fetchDeliverableByUuidAndGroupId(String uuid,
		long groupId);

	public List<Deliverable> findDeliverableByState(String strDeliverableCode,
		int state);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Deliverable getByCode(String deliverableCode);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Deliverable getByCodeAndState(String deliverableCode, int state);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Deliverable getByF_GID_DCODE(long groupId, String deliverableCode);

	/**
	* Returns the deliverable with the primary key.
	*
	* @param deliverableId the primary key of the deliverable
	* @return the deliverable
	* @throws PortalException if a deliverable with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Deliverable getDeliverable(long deliverableId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Deliverable> getDeliverableByModifiedDate(String synsDate,
		String deliverableType, long deliverableState);

	/**
	* Returns the deliverable matching the UUID and group.
	*
	* @param uuid the deliverable's UUID
	* @param groupId the primary key of the group
	* @return the matching deliverable
	* @throws PortalException if a matching deliverable could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Deliverable getDeliverableByUuidAndGroupId(String uuid, long groupId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Deliverable getDeliverableDetail(long id)
		throws NoSuchDeliverableException;

	/**
	* Returns a range of all the deliverables.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of deliverables
	* @param end the upper bound of the range of deliverables (not inclusive)
	* @return the range of deliverables
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Deliverable> getDeliverables(int start, int end);

	/**
	* Returns all the deliverables matching the UUID and company.
	*
	* @param uuid the UUID of the deliverables
	* @param companyId the primary key of the company
	* @return the matching deliverables, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Deliverable> getDeliverablesByUuidAndCompanyId(String uuid,
		long companyId);

	/**
	* Returns a range of deliverables matching the UUID and company.
	*
	* @param uuid the UUID of the deliverables
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of deliverables
	* @param end the upper bound of the range of deliverables (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching deliverables, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Deliverable> getDeliverablesByUuidAndCompanyId(String uuid,
		long companyId, int start, int end,
		OrderByComparator<Deliverable> orderByComparator);

	/**
	* Returns the number of deliverables.
	*
	* @return the number of deliverables
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getDeliverablesCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Deliverable getDetailById(long id);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Deliverable> getListDeliverable(int deliverableState,
		String govAgencyCode, String deliverableType, String applicant);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<BooleanClauseOccur> getOccurs();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public String getOSGiServiceIdentifier();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<String> getParamNames();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Object> getParams();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Class<?>> getParamTypes();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public String getPattern();

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public BooleanQuery getQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SearchContext getSearchContext();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<String> getSubPatterns();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<BooleanQuery> getSubQueries();

	public void LuceneQuery(String pattern, String paramValues,
		String paramTypes, SearchContext searchContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Hits searchLucene(LinkedHashMap<String, Object> params,
		Sort[] sorts, int start, int end, SearchContext searchContext)
		throws ParseException, SearchException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Hits searchLucene(String keywords, String groupId, String type,
		Map<String, String> mapFilter, Sort[] sorts, int start, int end,
		SearchContext searchContext, long userId)
		throws ParseException, SearchException;

	public void setOccurs(List<BooleanClauseOccur> occurs);

	public void setParamNames(List<String> paramNames);

	public void setParams(List<Object> params);

	public void setParamTypes(List<Class<?>> paramTypes);

	public void setPattern(String pattern);

	public void setQuery(BooleanQuery query);

	public void setSearchContext(SearchContext searchContext);

	public void setSubPatterns(List<String> subPatterns);

	public void setSubQueries(List<BooleanQuery> subQueries);

	/**
	* Updates the deliverable in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param deliverable the deliverable
	* @return the deliverable that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Deliverable updateDeliverable(Deliverable deliverable);

	@Indexable(type = IndexableType.REINDEX)
	public Deliverable updateDeliverable(long groupId, long id, String subject,
		String issueDate, String expireDate, String revalidate,
		String deliverableState, String deliverableAction,
		ServiceContext serviceContext);

	@Indexable(type = IndexableType.REINDEX)
	public Deliverable updateFormData(long groupId, long id, String formData,
		ServiceContext serviceContext) throws NoSuchDeliverableException;
}