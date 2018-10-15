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

import org.opencps.usermgt.model.Applicant;

import java.io.Serializable;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Provides the local service interface for Applicant. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author khoavu
 * @see ApplicantLocalServiceUtil
 * @see org.opencps.usermgt.service.base.ApplicantLocalServiceBaseImpl
 * @see org.opencps.usermgt.service.impl.ApplicantLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface ApplicantLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ApplicantLocalServiceUtil} to access the applicant local service. Add custom service methods to {@link org.opencps.usermgt.service.impl.ApplicantLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Indexable(type = IndexableType.REINDEX)
	public Applicant activateApplicant(long applicantId, ServiceContext context)
		throws PortalException;

	/**
	* Adds the applicant to the database. Also notifies the appropriate model listeners.
	*
	* @param applicant the applicant
	* @return the applicant that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Applicant addApplicant(Applicant applicant);

	public long countLucene(LinkedHashMap<String, Object> params,
		SearchContext searchContext) throws ParseException, SearchException;

	/**
	* Creates a new applicant with the primary key. Does not add the applicant to the database.
	*
	* @param applicantId the primary key for the new applicant
	* @return the new applicant
	*/
	@Transactional(enabled = false)
	public Applicant createApplicant(long applicantId);

	/**
	* Deletes the applicant from the database. Also notifies the appropriate model listeners.
	*
	* @param applicant the applicant
	* @return the applicant that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public Applicant deleteApplicant(Applicant applicant);

	/**
	* Deletes the applicant with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param applicantId the primary key of the applicant
	* @return the applicant that was removed
	* @throws PortalException if a applicant with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public Applicant deleteApplicant(long applicantId)
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.ApplicantModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.ApplicantModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public Applicant fetchApplicant(long applicantId);

	/**
	* Returns the applicant matching the UUID and group.
	*
	* @param uuid the applicant's UUID
	* @param groupId the primary key of the group
	* @return the matching applicant, or <code>null</code> if a matching applicant could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Applicant fetchApplicantByUuidAndGroupId(String uuid, long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Applicant fetchByAppId(String appId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Applicant fetchByEmail(String email);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Applicant fetchByMappingID(long mappingID);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Applicant fetchByTelNo(String telNo);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	/**
	* Returns the applicant with the primary key.
	*
	* @param applicantId the primary key of the applicant
	* @return the applicant
	* @throws PortalException if a applicant with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Applicant getApplicant(long applicantId) throws PortalException;

	/**
	* Returns the applicant matching the UUID and group.
	*
	* @param uuid the applicant's UUID
	* @param groupId the primary key of the group
	* @return the matching applicant
	* @throws PortalException if a matching applicant could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Applicant getApplicantByUuidAndGroupId(String uuid, long groupId)
		throws PortalException;

	/**
	* Returns a range of all the applicants.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.ApplicantModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of applicants
	* @param end the upper bound of the range of applicants (not inclusive)
	* @return the range of applicants
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Applicant> getApplicants(int start, int end);

	/**
	* Returns all the applicants matching the UUID and company.
	*
	* @param uuid the UUID of the applicants
	* @param companyId the primary key of the company
	* @return the matching applicants, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Applicant> getApplicantsByUuidAndCompanyId(String uuid,
		long companyId);

	/**
	* Returns a range of applicants matching the UUID and company.
	*
	* @param uuid the UUID of the applicants
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of applicants
	* @param end the upper bound of the range of applicants (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching applicants, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Applicant> getApplicantsByUuidAndCompanyId(String uuid,
		long companyId, int start, int end,
		OrderByComparator<Applicant> orderByComparator);

	/**
	* Returns the number of applicants.
	*
	* @return the number of applicants
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getApplicantsCount();

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

	@Indexable(type = IndexableType.REINDEX)
	public Applicant lockoutApplicant(long applicantId)
		throws PortalException;

	@Indexable(type = IndexableType.DELETE)
	public Applicant removeApplicant(long applicantId)
		throws PortalException, SystemException;

	@Indexable(type = IndexableType.REINDEX)
	public Applicant removeProfile(long applicantId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Hits searchLucene(LinkedHashMap<String, Object> params,
		Sort[] sorts, int start, int end, SearchContext searchContext)
		throws ParseException, SearchException;

	/**
	* Updates the applicant in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param applicant the applicant
	* @return the applicant that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Applicant updateApplicant(Applicant applicant);

	@Indexable(type = IndexableType.REINDEX)
	public Applicant updateApplicant(long groupId, long userId, long companyId,
		String applicantName, String applicantIdType, String applicantIdNo,
		Date applicantIdDate, String address, String cityCode, String cityName,
		String districtCode, String districtName, String wardCode,
		String wardName, String contactName, String contactTelNo,
		String contactEmail);

	/**
	* @param context
	* @param appicantId
	* @param applicantName
	* @param applicantIdNo
	* @param applicantIdDate
	* @param address
	* @param cityCode
	* @param cityName
	* @param districtCode
	* @param districtName
	* @param wardCode
	* @param wardName
	* @param contactName
	* @param contactTelNo
	* @param contactEmail
	* @param mappingUserId
	* @param profile
	* @return
	* @throws PortalException
	* @throws SystemException
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Applicant updateApplication(ServiceContext context, long groupId,
		long applicantId, String applicantName, String applicantIdType,
		String applicantIdNo, String applicantIdDate, String address,
		String cityCode, String cityName, String districtCode,
		String districtName, String wardCode, String wardName,
		String contactName, String contactTelNo, String contactEmail,
		String profile, String password)
		throws PortalException, SystemException;
}