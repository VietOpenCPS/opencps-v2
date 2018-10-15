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

import org.opencps.dossiermgt.model.DossierPart;

import java.io.Serializable;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Provides the local service interface for DossierPart. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author huymq
 * @see DossierPartLocalServiceUtil
 * @see org.opencps.dossiermgt.service.base.DossierPartLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.DossierPartLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface DossierPartLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DossierPartLocalServiceUtil} to access the dossier part local service. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.DossierPartLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the dossier part to the database. Also notifies the appropriate model listeners.
	*
	* @param dossierPart the dossier part
	* @return the dossier part that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public DossierPart addDossierPart(DossierPart dossierPart);

	public long countLucene(LinkedHashMap<String, Object> params,
		SearchContext searchContext) throws ParseException, SearchException;

	/**
	* Creates a new dossier part with the primary key. Does not add the dossier part to the database.
	*
	* @param dossierPartId the primary key for the new dossier part
	* @return the new dossier part
	*/
	@Transactional(enabled = false)
	public DossierPart createDossierPart(long dossierPartId);

	/**
	* Deletes the dossier part from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierPart the dossier part
	* @return the dossier part that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public DossierPart deleteDossierPart(DossierPart dossierPart);

	/**
	* Deletes the dossier part with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierPartId the primary key of the dossier part
	* @return the dossier part that was removed
	* @throws PortalException if a dossier part with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public DossierPart deleteDossierPart(long dossierPartId)
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DossierPartModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DossierPartModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public DossierPart fetchByTemplatePartNo(long groupId, String templateNo,
		String partNo) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DossierPart fetchDossierPart(long dossierPartId);

	/**
	* Returns the dossier part matching the UUID and group.
	*
	* @param uuid the dossier part's UUID
	* @param groupId the primary key of the group
	* @return the matching dossier part, or <code>null</code> if a matching dossier part could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DossierPart fetchDossierPartByUuidAndGroupId(String uuid,
		long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DossierPart getByFileTemplateNo(long groupId, String fileTemplateNo);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DossierPart getByPartTypeEsign(String templateNo, String partNo,
		int partType, boolean eSign);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DossierPart getByTempAndPartNo(long groupId, String templateNo,
		String partNo);

	/**
	* @param groupId
	* @param templateNo
	* @return
	* @throws PortalException
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DossierPart> getByTemplateNo(long groupId, String templateNo)
		throws PortalException;

	/**
	* @param dossierPartId
	* @param contentType
	* @return
	* @throws PortalException
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public String getContent(long dossierPartId, int contentType)
		throws PortalException;

	/**
	* Returns the dossier part with the primary key.
	*
	* @param dossierPartId the primary key of the dossier part
	* @return the dossier part
	* @throws PortalException if a dossier part with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DossierPart getDossierPart(long dossierPartId)
		throws PortalException;

	/**
	* Returns the dossier part matching the UUID and group.
	*
	* @param uuid the dossier part's UUID
	* @param groupId the primary key of the group
	* @return the matching dossier part
	* @throws PortalException if a matching dossier part could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DossierPart getDossierPartByUuidAndGroupId(String uuid, long groupId)
		throws PortalException;

	/**
	* Returns a range of all the dossier parts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DossierPartModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier parts
	* @param end the upper bound of the range of dossier parts (not inclusive)
	* @return the range of dossier parts
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DossierPart> getDossierParts(int start, int end);

	/**
	* Returns all the dossier parts matching the UUID and company.
	*
	* @param uuid the UUID of the dossier parts
	* @param companyId the primary key of the company
	* @return the matching dossier parts, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DossierPart> getDossierPartsByUuidAndCompanyId(String uuid,
		long companyId);

	/**
	* Returns a range of dossier parts matching the UUID and company.
	*
	* @param uuid the UUID of the dossier parts
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of dossier parts
	* @param end the upper bound of the range of dossier parts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching dossier parts, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DossierPart> getDossierPartsByUuidAndCompanyId(String uuid,
		long companyId, int start, int end,
		OrderByComparator<DossierPart> orderByComparator);

	/**
	* Returns the number of dossier parts.
	*
	* @return the number of dossier parts
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getDossierPartsCount();

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

	@Indexable(type = IndexableType.DELETE)
	public DossierPart removeDossierPart(long dossierPartId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Hits searchLucene(LinkedHashMap<String, Object> params,
		Sort[] sorts, int start, int end, SearchContext searchContext)
		throws ParseException, SearchException;

	/**
	* @param dossierPartId
	* @param contentType
	* @param input
	* @param context
	* @return
	* @throws PortalException
	*/
	@Indexable(type = IndexableType.REINDEX)
	public String updateContent(long dossierPartId, int contentType,
		String input, ServiceContext context) throws PortalException;

	/**
	* Updates the dossier part in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dossierPart the dossier part
	* @return the dossier part that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public DossierPart updateDossierPart(DossierPart dossierPart);

	@Indexable(type = IndexableType.REINDEX)
	public DossierPart updateDossierPart(long groupId, long dossierPartId,
		String templateNo, String partNo, String partName, String partTip,
		int partType, boolean multiple, String formScript, String formReport,
		String sampleData, boolean required, String fileTemplateNo,
		boolean eSign, ServiceContext context) throws PortalException;

	@Indexable(type = IndexableType.REINDEX)
	public DossierPart updateDossierPart(long groupId, long dossierPartId,
		String templateNo, String partNo, String partName, String partTip,
		int partType, boolean multiple, String formScript, String formReport,
		String sampleData, boolean required, String fileTemplateNo,
		boolean eSign, String deliverableType, int deliverableAction,
		ServiceContext context) throws PortalException;

	@Indexable(type = IndexableType.REINDEX)
	public DossierPart updateDossierPartDB(long userId, long groupId,
		String templateNo, String partNo, String partName, String partTip,
		Integer partType, boolean multiple, String formScript,
		String formReport, boolean required, boolean esign,
		String fileTemplateNo, String deliverableType,
		Integer deliverableAction, boolean eForm, String sampleData,
		Integer fileMark, ServiceContext serviceContext)
		throws PortalException;
}