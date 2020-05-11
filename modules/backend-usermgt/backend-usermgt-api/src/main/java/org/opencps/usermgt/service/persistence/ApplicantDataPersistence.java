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

package org.opencps.usermgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.usermgt.exception.NoSuchApplicantDataException;
import org.opencps.usermgt.model.ApplicantData;

/**
 * The persistence interface for the applicant data service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see org.opencps.usermgt.service.persistence.impl.ApplicantDataPersistenceImpl
 * @see ApplicantDataUtil
 * @generated
 */
@ProviderType
public interface ApplicantDataPersistence extends BasePersistence<ApplicantData> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ApplicantDataUtil} to access the applicant data persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the applicant datas where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching applicant datas
	*/
	public java.util.List<ApplicantData> findByUuid(String uuid);

	/**
	* Returns a range of all the applicant datas where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApplicantDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of applicant datas
	* @param end the upper bound of the range of applicant datas (not inclusive)
	* @return the range of matching applicant datas
	*/
	public java.util.List<ApplicantData> findByUuid(String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the applicant datas where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApplicantDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of applicant datas
	* @param end the upper bound of the range of applicant datas (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching applicant datas
	*/
	public java.util.List<ApplicantData> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ApplicantData> orderByComparator);

	/**
	* Returns an ordered range of all the applicant datas where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApplicantDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of applicant datas
	* @param end the upper bound of the range of applicant datas (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching applicant datas
	*/
	public java.util.List<ApplicantData> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ApplicantData> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first applicant data in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching applicant data
	* @throws NoSuchApplicantDataException if a matching applicant data could not be found
	*/
	public ApplicantData findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ApplicantData> orderByComparator)
		throws NoSuchApplicantDataException;

	/**
	* Returns the first applicant data in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching applicant data, or <code>null</code> if a matching applicant data could not be found
	*/
	public ApplicantData fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ApplicantData> orderByComparator);

	/**
	* Returns the last applicant data in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching applicant data
	* @throws NoSuchApplicantDataException if a matching applicant data could not be found
	*/
	public ApplicantData findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ApplicantData> orderByComparator)
		throws NoSuchApplicantDataException;

	/**
	* Returns the last applicant data in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching applicant data, or <code>null</code> if a matching applicant data could not be found
	*/
	public ApplicantData fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ApplicantData> orderByComparator);

	/**
	* Returns the applicant datas before and after the current applicant data in the ordered set where uuid = &#63;.
	*
	* @param applicantDataId the primary key of the current applicant data
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next applicant data
	* @throws NoSuchApplicantDataException if a applicant data with the primary key could not be found
	*/
	public ApplicantData[] findByUuid_PrevAndNext(long applicantDataId,
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ApplicantData> orderByComparator)
		throws NoSuchApplicantDataException;

	/**
	* Removes all the applicant datas where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of applicant datas where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching applicant datas
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the applicant data where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchApplicantDataException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching applicant data
	* @throws NoSuchApplicantDataException if a matching applicant data could not be found
	*/
	public ApplicantData findByUUID_G(String uuid, long groupId)
		throws NoSuchApplicantDataException;

	/**
	* Returns the applicant data where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching applicant data, or <code>null</code> if a matching applicant data could not be found
	*/
	public ApplicantData fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the applicant data where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching applicant data, or <code>null</code> if a matching applicant data could not be found
	*/
	public ApplicantData fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the applicant data where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the applicant data that was removed
	*/
	public ApplicantData removeByUUID_G(String uuid, long groupId)
		throws NoSuchApplicantDataException;

	/**
	* Returns the number of applicant datas where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching applicant datas
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the applicant datas where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching applicant datas
	*/
	public java.util.List<ApplicantData> findByUuid_C(String uuid,
		long companyId);

	/**
	* Returns a range of all the applicant datas where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApplicantDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of applicant datas
	* @param end the upper bound of the range of applicant datas (not inclusive)
	* @return the range of matching applicant datas
	*/
	public java.util.List<ApplicantData> findByUuid_C(String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the applicant datas where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApplicantDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of applicant datas
	* @param end the upper bound of the range of applicant datas (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching applicant datas
	*/
	public java.util.List<ApplicantData> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ApplicantData> orderByComparator);

	/**
	* Returns an ordered range of all the applicant datas where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApplicantDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of applicant datas
	* @param end the upper bound of the range of applicant datas (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching applicant datas
	*/
	public java.util.List<ApplicantData> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ApplicantData> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first applicant data in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching applicant data
	* @throws NoSuchApplicantDataException if a matching applicant data could not be found
	*/
	public ApplicantData findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ApplicantData> orderByComparator)
		throws NoSuchApplicantDataException;

	/**
	* Returns the first applicant data in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching applicant data, or <code>null</code> if a matching applicant data could not be found
	*/
	public ApplicantData fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ApplicantData> orderByComparator);

	/**
	* Returns the last applicant data in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching applicant data
	* @throws NoSuchApplicantDataException if a matching applicant data could not be found
	*/
	public ApplicantData findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ApplicantData> orderByComparator)
		throws NoSuchApplicantDataException;

	/**
	* Returns the last applicant data in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching applicant data, or <code>null</code> if a matching applicant data could not be found
	*/
	public ApplicantData fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ApplicantData> orderByComparator);

	/**
	* Returns the applicant datas before and after the current applicant data in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param applicantDataId the primary key of the current applicant data
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next applicant data
	* @throws NoSuchApplicantDataException if a applicant data with the primary key could not be found
	*/
	public ApplicantData[] findByUuid_C_PrevAndNext(long applicantDataId,
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ApplicantData> orderByComparator)
		throws NoSuchApplicantDataException;

	/**
	* Removes all the applicant datas where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of applicant datas where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching applicant datas
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns the applicant data where groupId = &#63; and dossierNo = &#63; and fileTemplateNo = &#63; and applicantIdNo = &#63; or throws a {@link NoSuchApplicantDataException} if it could not be found.
	*
	* @param groupId the group ID
	* @param dossierNo the dossier no
	* @param fileTemplateNo the file template no
	* @param applicantIdNo the applicant ID no
	* @return the matching applicant data
	* @throws NoSuchApplicantDataException if a matching applicant data could not be found
	*/
	public ApplicantData findByG_DN_FTN_AIN(long groupId, String dossierNo,
		String fileTemplateNo, String applicantIdNo)
		throws NoSuchApplicantDataException;

	/**
	* Returns the applicant data where groupId = &#63; and dossierNo = &#63; and fileTemplateNo = &#63; and applicantIdNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param dossierNo the dossier no
	* @param fileTemplateNo the file template no
	* @param applicantIdNo the applicant ID no
	* @return the matching applicant data, or <code>null</code> if a matching applicant data could not be found
	*/
	public ApplicantData fetchByG_DN_FTN_AIN(long groupId, String dossierNo,
		String fileTemplateNo, String applicantIdNo);

	/**
	* Returns the applicant data where groupId = &#63; and dossierNo = &#63; and fileTemplateNo = &#63; and applicantIdNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param dossierNo the dossier no
	* @param fileTemplateNo the file template no
	* @param applicantIdNo the applicant ID no
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching applicant data, or <code>null</code> if a matching applicant data could not be found
	*/
	public ApplicantData fetchByG_DN_FTN_AIN(long groupId, String dossierNo,
		String fileTemplateNo, String applicantIdNo, boolean retrieveFromCache);

	/**
	* Removes the applicant data where groupId = &#63; and dossierNo = &#63; and fileTemplateNo = &#63; and applicantIdNo = &#63; from the database.
	*
	* @param groupId the group ID
	* @param dossierNo the dossier no
	* @param fileTemplateNo the file template no
	* @param applicantIdNo the applicant ID no
	* @return the applicant data that was removed
	*/
	public ApplicantData removeByG_DN_FTN_AIN(long groupId, String dossierNo,
		String fileTemplateNo, String applicantIdNo)
		throws NoSuchApplicantDataException;

	/**
	* Returns the number of applicant datas where groupId = &#63; and dossierNo = &#63; and fileTemplateNo = &#63; and applicantIdNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierNo the dossier no
	* @param fileTemplateNo the file template no
	* @param applicantIdNo the applicant ID no
	* @return the number of matching applicant datas
	*/
	public int countByG_DN_FTN_AIN(long groupId, String dossierNo,
		String fileTemplateNo, String applicantIdNo);

	/**
	* Caches the applicant data in the entity cache if it is enabled.
	*
	* @param applicantData the applicant data
	*/
	public void cacheResult(ApplicantData applicantData);

	/**
	* Caches the applicant datas in the entity cache if it is enabled.
	*
	* @param applicantDatas the applicant datas
	*/
	public void cacheResult(java.util.List<ApplicantData> applicantDatas);

	/**
	* Creates a new applicant data with the primary key. Does not add the applicant data to the database.
	*
	* @param applicantDataId the primary key for the new applicant data
	* @return the new applicant data
	*/
	public ApplicantData create(long applicantDataId);

	/**
	* Removes the applicant data with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param applicantDataId the primary key of the applicant data
	* @return the applicant data that was removed
	* @throws NoSuchApplicantDataException if a applicant data with the primary key could not be found
	*/
	public ApplicantData remove(long applicantDataId)
		throws NoSuchApplicantDataException;

	public ApplicantData updateImpl(ApplicantData applicantData);

	/**
	* Returns the applicant data with the primary key or throws a {@link NoSuchApplicantDataException} if it could not be found.
	*
	* @param applicantDataId the primary key of the applicant data
	* @return the applicant data
	* @throws NoSuchApplicantDataException if a applicant data with the primary key could not be found
	*/
	public ApplicantData findByPrimaryKey(long applicantDataId)
		throws NoSuchApplicantDataException;

	/**
	* Returns the applicant data with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param applicantDataId the primary key of the applicant data
	* @return the applicant data, or <code>null</code> if a applicant data with the primary key could not be found
	*/
	public ApplicantData fetchByPrimaryKey(long applicantDataId);

	@Override
	public java.util.Map<java.io.Serializable, ApplicantData> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the applicant datas.
	*
	* @return the applicant datas
	*/
	public java.util.List<ApplicantData> findAll();

	/**
	* Returns a range of all the applicant datas.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApplicantDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of applicant datas
	* @param end the upper bound of the range of applicant datas (not inclusive)
	* @return the range of applicant datas
	*/
	public java.util.List<ApplicantData> findAll(int start, int end);

	/**
	* Returns an ordered range of all the applicant datas.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApplicantDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of applicant datas
	* @param end the upper bound of the range of applicant datas (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of applicant datas
	*/
	public java.util.List<ApplicantData> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ApplicantData> orderByComparator);

	/**
	* Returns an ordered range of all the applicant datas.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApplicantDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of applicant datas
	* @param end the upper bound of the range of applicant datas (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of applicant datas
	*/
	public java.util.List<ApplicantData> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ApplicantData> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the applicant datas from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of applicant datas.
	*
	* @return the number of applicant datas
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}