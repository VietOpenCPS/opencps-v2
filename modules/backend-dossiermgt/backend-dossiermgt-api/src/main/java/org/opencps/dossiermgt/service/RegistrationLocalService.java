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

import org.opencps.dossiermgt.model.Registration;

import java.io.Serializable;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Provides the local service interface for Registration. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author huymq
 * @see RegistrationLocalServiceUtil
 * @see org.opencps.dossiermgt.service.base.RegistrationLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.RegistrationLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface RegistrationLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link RegistrationLocalServiceUtil} to access the registration local service. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.RegistrationLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the registration to the database. Also notifies the appropriate model listeners.
	*
	* @param registration the registration
	* @return the registration that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Registration addRegistration(Registration registration);

	@Indexable(type = IndexableType.REINDEX)
	public Registration adminProcessData(JSONObject objectData);

	@Indexable(type = IndexableType.DELETE)
	public Registration adminProcessDelete(Long id);

	public long countLucense(long userId, LinkedHashMap<String, Object> params,
		Sort[] sorts, int start, int end, SearchContext searchContext)
		throws ParseException, SearchException;

	/**
	* Creates a new registration with the primary key. Does not add the registration to the database.
	*
	* @param registrationId the primary key for the new registration
	* @return the new registration
	*/
	@Transactional(enabled = false)
	public Registration createRegistration(long registrationId);

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	/**
	* Deletes the registration with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param registrationId the primary key of the registration
	* @return the registration that was removed
	* @throws PortalException if a registration with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public Registration deleteRegistration(long registrationId)
		throws PortalException;

	/**
	* Deletes the registration from the database. Also notifies the appropriate model listeners.
	*
	* @param registration the registration
	* @return the registration that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public Registration deleteRegistration(Registration registration);

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.RegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.RegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public Registration fetchRegistration(long registrationId);

	/**
	* Returns the registration matching the UUID and group.
	*
	* @param uuid the registration's UUID
	* @param groupId the primary key of the group
	* @return the matching registration, or <code>null</code> if a matching registration could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Registration fetchRegistrationByUuidAndGroupId(String uuid,
		long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	/**
	* Get registration of applicant has registrationState in use
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Registration getByApplicantAndAgency(long groupId,
		String applicantNo, String agencyNo);

	/**
	* Get registration form ApplicantIdNo using output DB
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Registration getByApplicantIdNo(String applicantIdNo);

	/**
	* Get list registrations have state = 2
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Registration> getByRegistrationState(long groupId, long userId,
		int state);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Registration> getdByF_submitting(long groupId,
		boolean submitting);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getfileEntryId(String formdata, String formScript,
		String formReport);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Registration getLastSubmittingByUser(long groupId, long userId,
		boolean submitting);

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
	* Returns the registration with the primary key.
	*
	* @param registrationId the primary key of the registration
	* @return the registration
	* @throws PortalException if a registration with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Registration getRegistration(long registrationId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Registration getRegistrationByG_REGID(long groupId,
		long registrationId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Registration> getRegistrationByGID_UID(long groupId, long userId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Registration getRegistrationByGID_UID_Last(long groupId, long userId);

	/**
	* Returns the registration matching the UUID and group.
	*
	* @param uuid the registration's UUID
	* @param groupId the primary key of the group
	* @return the matching registration
	* @throws PortalException if a matching registration could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Registration getRegistrationByUuidAndGroupId(String uuid,
		long groupId) throws PortalException;

	/**
	* Returns a range of all the registrations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.RegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of registrations
	* @param end the upper bound of the range of registrations (not inclusive)
	* @return the range of registrations
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Registration> getRegistrations(int start, int end);

	/**
	* Returns all the registrations matching the UUID and company.
	*
	* @param uuid the UUID of the registrations
	* @param companyId the primary key of the company
	* @return the matching registrations, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Registration> getRegistrationsByUuidAndCompanyId(String uuid,
		long companyId);

	/**
	* Returns a range of registrations matching the UUID and company.
	*
	* @param uuid the UUID of the registrations
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of registrations
	* @param end the upper bound of the range of registrations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching registrations, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Registration> getRegistrationsByUuidAndCompanyId(String uuid,
		long companyId, int start, int end,
		OrderByComparator<Registration> orderByComparator);

	/**
	* Returns the number of registrations.
	*
	* @return the number of registrations
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getRegistrationsCount();

	@Indexable(type = IndexableType.REINDEX)
	public Registration insert(long groupId, long companyId,
		String applicantName, String applicantIdType, String applicantIdNo,
		String applicantIdDate, String address, String cityCode,
		String cityName, String districtCode, String districtName,
		String wardCode, String wardName, String contactName,
		String contactTelNo, String contactEmail, String govAgencyCode,
		String govAgencyName, int registrationState, String registrationClass,
		String representativeEnterprise, ServiceContext serviceContext)
		throws PortalException, SystemException;

	@Indexable(type = IndexableType.REINDEX)
	public Registration registrationSync(long groupId, String uuid,
		String applicantName, String applicantIdType, String applicantIdNo,
		String applicantIdDate, String address, String cityCode,
		String cityName, String districtCode, String districtName,
		String wardCode, String wardName, String contactName,
		String contactTelNo, String contactEmail, String govAgencyCode,
		String govAgencyName, int registrationState, String registrationClass,
		String representativeEnterprise, ServiceContext serviceContext)
		throws PortalException, SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Hits searchLucene(long userId, LinkedHashMap<String, Object> params,
		Sort[] sorts, int start, int end, SearchContext searchContext)
		throws ParseException, SearchException;

	@Indexable(type = IndexableType.REINDEX)
	public Registration updateRegistration(long groupId, long registrationId,
		String applicantName, String applicantIdType, String applicantIdNo,
		String applicantIdDate, String address, String cityCode,
		String cityName, String districtCode, String districtName,
		String wardCode, String wardName, String contactName,
		String contactTelNo, String contactEmail, String govAgencyCode,
		String govAgencyName, int registrationState, String registrationClass,
		String representativeEnterprise, ServiceContext serviceContext)
		throws PortalException, SystemException;

	/**
	* Updates the registration in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param registration the registration
	* @return the registration that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Registration updateRegistration(Registration registration);

	@Indexable(type = IndexableType.REINDEX)
	public Registration updateSubmitting(long registrationId, boolean submitting)
		throws PortalException;
}