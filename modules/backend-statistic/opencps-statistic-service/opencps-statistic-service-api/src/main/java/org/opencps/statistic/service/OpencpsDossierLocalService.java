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

package org.opencps.statistic.service;

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
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.statistic.model.OpencpsDossier;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for OpencpsDossier. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author khoavu
 * @see OpencpsDossierLocalServiceUtil
 * @see org.opencps.statistic.service.base.OpencpsDossierLocalServiceBaseImpl
 * @see org.opencps.statistic.service.impl.OpencpsDossierLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface OpencpsDossierLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link OpencpsDossierLocalServiceUtil} to access the opencps dossier local service. Add custom service methods to {@link org.opencps.statistic.service.impl.OpencpsDossierLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
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

	/**
	* Returns the number of opencps dossiers.
	*
	* @return the number of opencps dossiers
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getOpencpsDossiersCount();

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.statistic.model.impl.OpencpsDossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.statistic.model.impl.OpencpsDossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the opencps dossiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.statistic.model.impl.OpencpsDossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of opencps dossiers
	* @param end the upper bound of the range of opencps dossiers (not inclusive)
	* @return the range of opencps dossiers
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<OpencpsDossier> getOpencpsDossiers(int start, int end);

	/**
	* Returns all the opencps dossiers matching the UUID and company.
	*
	* @param uuid the UUID of the opencps dossiers
	* @param companyId the primary key of the company
	* @return the matching opencps dossiers, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<OpencpsDossier> getOpencpsDossiersByUuidAndCompanyId(
		java.lang.String uuid, long companyId);

	/**
	* Returns a range of opencps dossiers matching the UUID and company.
	*
	* @param uuid the UUID of the opencps dossiers
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of opencps dossiers
	* @param end the upper bound of the range of opencps dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching opencps dossiers, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<OpencpsDossier> getOpencpsDossiersByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<OpencpsDossier> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<OpencpsDossier> searchDossier(long groupId,
		java.lang.String keyword, java.lang.String registerBookCode,
		java.lang.String processNo, java.lang.String serviceCode,
		java.lang.String govAgencyCode, java.lang.String applicantIdType,
		java.lang.String applicantIdNo, java.lang.String cityCode,
		java.lang.String districtCode, java.lang.String wardCode,
		java.lang.String contactTelNo, java.lang.String contactEmail,
		java.lang.String delegateIdNo, java.lang.String delegateTelNo,
		java.lang.String dossierStatus, java.lang.String dossierSubStatus,
		long dossierActionId, int viaPostal, boolean online, int originality,
		java.lang.String serverNo, long originDossierId, boolean order,
		java.lang.String orderBy, int start, int end)
		throws PortalException, SystemException;

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

	/**
	* Adds the opencps dossier to the database. Also notifies the appropriate model listeners.
	*
	* @param opencpsDossier the opencps dossier
	* @return the opencps dossier that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public OpencpsDossier addOpencpsDossier(OpencpsDossier opencpsDossier);

	/**
	* Creates a new opencps dossier with the primary key. Does not add the opencps dossier to the database.
	*
	* @param dossierId the primary key for the new opencps dossier
	* @return the new opencps dossier
	*/
	public OpencpsDossier createOpencpsDossier(long dossierId);

	/**
	* Deletes the opencps dossier with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierId the primary key of the opencps dossier
	* @return the opencps dossier that was removed
	* @throws PortalException if a opencps dossier with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public OpencpsDossier deleteOpencpsDossier(long dossierId)
		throws PortalException;

	/**
	* Deletes the opencps dossier from the database. Also notifies the appropriate model listeners.
	*
	* @param opencpsDossier the opencps dossier
	* @return the opencps dossier that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public OpencpsDossier deleteOpencpsDossier(OpencpsDossier opencpsDossier);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public OpencpsDossier fetchOpencpsDossier(long dossierId);

	/**
	* Returns the opencps dossier matching the UUID and group.
	*
	* @param uuid the opencps dossier's UUID
	* @param groupId the primary key of the group
	* @return the matching opencps dossier, or <code>null</code> if a matching opencps dossier could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public OpencpsDossier fetchOpencpsDossierByUuidAndGroupId(
		java.lang.String uuid, long groupId);

	/**
	* Returns the opencps dossier with the primary key.
	*
	* @param dossierId the primary key of the opencps dossier
	* @return the opencps dossier
	* @throws PortalException if a opencps dossier with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public OpencpsDossier getOpencpsDossier(long dossierId)
		throws PortalException;

	/**
	* Returns the opencps dossier matching the UUID and group.
	*
	* @param uuid the opencps dossier's UUID
	* @param groupId the primary key of the group
	* @return the matching opencps dossier
	* @throws PortalException if a matching opencps dossier could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public OpencpsDossier getOpencpsDossierByUuidAndGroupId(
		java.lang.String uuid, long groupId) throws PortalException;

	/**
	* Updates the opencps dossier in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param opencpsDossier the opencps dossier
	* @return the opencps dossier that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public OpencpsDossier updateOpencpsDossier(OpencpsDossier opencpsDossier);
}