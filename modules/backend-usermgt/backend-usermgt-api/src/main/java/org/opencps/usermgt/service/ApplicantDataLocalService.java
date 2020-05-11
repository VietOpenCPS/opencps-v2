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

import org.opencps.usermgt.model.ApplicantData;

import java.io.InputStream;
import java.io.Serializable;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Provides the local service interface for ApplicantData. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author khoavu
 * @see ApplicantDataLocalServiceUtil
 * @see org.opencps.usermgt.service.base.ApplicantDataLocalServiceBaseImpl
 * @see org.opencps.usermgt.service.impl.ApplicantDataLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface ApplicantDataLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ApplicantDataLocalServiceUtil} to access the applicant data local service. Add custom service methods to {@link org.opencps.usermgt.service.impl.ApplicantDataLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public ApplicantData active(long applicantDataId);

	/**
	* Adds the applicant data to the database. Also notifies the appropriate model listeners.
	*
	* @param applicantData the applicant data
	* @return the applicant data that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ApplicantData addApplicantData(ApplicantData applicantData);

	public long countLucene(LinkedHashMap<String, Object> params,
		SearchContext searchContext) throws ParseException, SearchException;

	/**
	* Creates a new applicant data with the primary key. Does not add the applicant data to the database.
	*
	* @param applicantDataId the primary key for the new applicant data
	* @return the new applicant data
	*/
	@Transactional(enabled = false)
	public ApplicantData createApplicantData(long applicantDataId);

	@Indexable(type = IndexableType.REINDEX)
	public ApplicantData createApplicantData(long groupId,
		String fileTemplateNo, String fileNo, String fileName,
		String applicantIdNo, String sourceFileName, InputStream inputStream,
		ServiceContext serviceContext) throws PortalException, SystemException;

	@Indexable(type = IndexableType.REINDEX)
	public ApplicantData createApplicantData(ServiceContext context,
		long groupId, String fileTemplateNo, String fileNo, String fileName,
		long fileEntryId, String metadata, int status, String applicantIdNo,
		int applicantDataType) throws PortalException, SystemException;

	/**
	* Deletes the applicant data from the database. Also notifies the appropriate model listeners.
	*
	* @param applicantData the applicant data
	* @return the applicant data that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public ApplicantData deleteApplicantData(ApplicantData applicantData);

	/**
	* Deletes the applicant data with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param applicantDataId the primary key of the applicant data
	* @return the applicant data that was removed
	* @throws PortalException if a applicant data with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public ApplicantData deleteApplicantData(long applicantDataId)
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.ApplicantDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.ApplicantDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public ApplicantData fetchApplicantData(long applicantDataId);

	/**
	* Returns the applicant data matching the UUID and group.
	*
	* @param uuid the applicant data's UUID
	* @param groupId the primary key of the group
	* @return the matching applicant data, or <code>null</code> if a matching applicant data could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ApplicantData fetchApplicantDataByUuidAndGroupId(String uuid,
		long groupId);

	public ApplicantData findByG_DN_FTN_AIN(long groupId, String dossierNo,
		String fileTemplateNo, String applicantIdNo);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	/**
	* Returns the applicant data with the primary key.
	*
	* @param applicantDataId the primary key of the applicant data
	* @return the applicant data
	* @throws PortalException if a applicant data with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ApplicantData getApplicantData(long applicantDataId)
		throws PortalException;

	/**
	* Returns the applicant data matching the UUID and group.
	*
	* @param uuid the applicant data's UUID
	* @param groupId the primary key of the group
	* @return the matching applicant data
	* @throws PortalException if a matching applicant data could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ApplicantData getApplicantDataByUuidAndGroupId(String uuid,
		long groupId) throws PortalException;

	/**
	* Returns a range of all the applicant datas.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.ApplicantDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of applicant datas
	* @param end the upper bound of the range of applicant datas (not inclusive)
	* @return the range of applicant datas
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ApplicantData> getApplicantDatas(int start, int end);

	/**
	* Returns all the applicant datas matching the UUID and company.
	*
	* @param uuid the UUID of the applicant datas
	* @param companyId the primary key of the company
	* @return the matching applicant datas, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ApplicantData> getApplicantDatasByUuidAndCompanyId(
		String uuid, long companyId);

	/**
	* Returns a range of applicant datas matching the UUID and company.
	*
	* @param uuid the UUID of the applicant datas
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of applicant datas
	* @param end the upper bound of the range of applicant datas (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching applicant datas, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ApplicantData> getApplicantDatasByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<ApplicantData> orderByComparator);

	/**
	* Returns the number of applicant datas.
	*
	* @return the number of applicant datas
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getApplicantDatasCount();

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

	public ApplicantData inActive(long applicantDataId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Hits searchLucene(LinkedHashMap<String, Object> params,
		Sort[] sorts, int start, int end, SearchContext searchContext)
		throws ParseException, SearchException;

	/**
	* Updates the applicant data in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param applicantData the applicant data
	* @return the applicant data that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ApplicantData updateApplicantData(ApplicantData applicantData);

	@Indexable(type = IndexableType.REINDEX)
	public ApplicantData updateApplicantData(long groupId,
		long applicantDataId, String fileTemplateNo, String fileNo,
		String fileName, String applicantIdNo, String sourceFileName,
		InputStream inputStream, ServiceContext serviceContext)
		throws PortalException, SystemException;

	@Indexable(type = IndexableType.REINDEX)
	public ApplicantData updateApplicantData(ServiceContext context,
		long groupId, long applicantDataId, String fileTemplateNo,
		String fileNo, String fileName, long fileEntryId, String metadata,
		int status, String applicantIdNo, int applicantDataType)
		throws PortalException, SystemException;

	@Indexable(type = IndexableType.REINDEX)
	public ApplicantData updateApplicantData(ServiceContext context,
		long groupId, String fileTemplateNo, String fileName, long fileEntryId,
		String metadata, int status, String applicantIdNo,
		int applicantDataType, String dossierNo, String log)
		throws PortalException, SystemException;
}