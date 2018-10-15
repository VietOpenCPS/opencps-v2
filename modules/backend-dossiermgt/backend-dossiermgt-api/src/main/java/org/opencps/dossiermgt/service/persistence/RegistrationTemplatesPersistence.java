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

package org.opencps.dossiermgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.dossiermgt.exception.NoSuchRegistrationTemplatesException;
import org.opencps.dossiermgt.model.RegistrationTemplates;

/**
 * The persistence interface for the registration templates service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see org.opencps.dossiermgt.service.persistence.impl.RegistrationTemplatesPersistenceImpl
 * @see RegistrationTemplatesUtil
 * @generated
 */
@ProviderType
public interface RegistrationTemplatesPersistence extends BasePersistence<RegistrationTemplates> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link RegistrationTemplatesUtil} to access the registration templates persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the registration templateses where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching registration templateses
	*/
	public java.util.List<RegistrationTemplates> findByUuid(String uuid);

	/**
	* Returns a range of all the registration templateses where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationTemplatesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of registration templateses
	* @param end the upper bound of the range of registration templateses (not inclusive)
	* @return the range of matching registration templateses
	*/
	public java.util.List<RegistrationTemplates> findByUuid(String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the registration templateses where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationTemplatesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of registration templateses
	* @param end the upper bound of the range of registration templateses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching registration templateses
	*/
	public java.util.List<RegistrationTemplates> findByUuid(String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationTemplates> orderByComparator);

	/**
	* Returns an ordered range of all the registration templateses where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationTemplatesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of registration templateses
	* @param end the upper bound of the range of registration templateses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching registration templateses
	*/
	public java.util.List<RegistrationTemplates> findByUuid(String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationTemplates> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first registration templates in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration templates
	* @throws NoSuchRegistrationTemplatesException if a matching registration templates could not be found
	*/
	public RegistrationTemplates findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationTemplates> orderByComparator)
		throws NoSuchRegistrationTemplatesException;

	/**
	* Returns the first registration templates in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration templates, or <code>null</code> if a matching registration templates could not be found
	*/
	public RegistrationTemplates fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationTemplates> orderByComparator);

	/**
	* Returns the last registration templates in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration templates
	* @throws NoSuchRegistrationTemplatesException if a matching registration templates could not be found
	*/
	public RegistrationTemplates findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationTemplates> orderByComparator)
		throws NoSuchRegistrationTemplatesException;

	/**
	* Returns the last registration templates in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration templates, or <code>null</code> if a matching registration templates could not be found
	*/
	public RegistrationTemplates fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationTemplates> orderByComparator);

	/**
	* Returns the registration templateses before and after the current registration templates in the ordered set where uuid = &#63;.
	*
	* @param registrationTemplateId the primary key of the current registration templates
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next registration templates
	* @throws NoSuchRegistrationTemplatesException if a registration templates with the primary key could not be found
	*/
	public RegistrationTemplates[] findByUuid_PrevAndNext(
		long registrationTemplateId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationTemplates> orderByComparator)
		throws NoSuchRegistrationTemplatesException;

	/**
	* Removes all the registration templateses where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of registration templateses where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching registration templateses
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the registration templates where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchRegistrationTemplatesException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching registration templates
	* @throws NoSuchRegistrationTemplatesException if a matching registration templates could not be found
	*/
	public RegistrationTemplates findByUUID_G(String uuid, long groupId)
		throws NoSuchRegistrationTemplatesException;

	/**
	* Returns the registration templates where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching registration templates, or <code>null</code> if a matching registration templates could not be found
	*/
	public RegistrationTemplates fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the registration templates where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching registration templates, or <code>null</code> if a matching registration templates could not be found
	*/
	public RegistrationTemplates fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the registration templates where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the registration templates that was removed
	*/
	public RegistrationTemplates removeByUUID_G(String uuid, long groupId)
		throws NoSuchRegistrationTemplatesException;

	/**
	* Returns the number of registration templateses where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching registration templateses
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns the registration templates where groupId = &#63; and registrationTemplateId = &#63; or throws a {@link NoSuchRegistrationTemplatesException} if it could not be found.
	*
	* @param groupId the group ID
	* @param registrationTemplateId the registration template ID
	* @return the matching registration templates
	* @throws NoSuchRegistrationTemplatesException if a matching registration templates could not be found
	*/
	public RegistrationTemplates findByG_REGID(long groupId,
		long registrationTemplateId)
		throws NoSuchRegistrationTemplatesException;

	/**
	* Returns the registration templates where groupId = &#63; and registrationTemplateId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param registrationTemplateId the registration template ID
	* @return the matching registration templates, or <code>null</code> if a matching registration templates could not be found
	*/
	public RegistrationTemplates fetchByG_REGID(long groupId,
		long registrationTemplateId);

	/**
	* Returns the registration templates where groupId = &#63; and registrationTemplateId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param registrationTemplateId the registration template ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching registration templates, or <code>null</code> if a matching registration templates could not be found
	*/
	public RegistrationTemplates fetchByG_REGID(long groupId,
		long registrationTemplateId, boolean retrieveFromCache);

	/**
	* Removes the registration templates where groupId = &#63; and registrationTemplateId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param registrationTemplateId the registration template ID
	* @return the registration templates that was removed
	*/
	public RegistrationTemplates removeByG_REGID(long groupId,
		long registrationTemplateId)
		throws NoSuchRegistrationTemplatesException;

	/**
	* Returns the number of registration templateses where groupId = &#63; and registrationTemplateId = &#63;.
	*
	* @param groupId the group ID
	* @param registrationTemplateId the registration template ID
	* @return the number of matching registration templateses
	*/
	public int countByG_REGID(long groupId, long registrationTemplateId);

	/**
	* Returns all the registration templateses where groupId = &#63; and formNo = &#63;.
	*
	* @param groupId the group ID
	* @param formNo the form no
	* @return the matching registration templateses
	*/
	public java.util.List<RegistrationTemplates> findByFNO(long groupId,
		String formNo);

	/**
	* Returns a range of all the registration templateses where groupId = &#63; and formNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationTemplatesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param formNo the form no
	* @param start the lower bound of the range of registration templateses
	* @param end the upper bound of the range of registration templateses (not inclusive)
	* @return the range of matching registration templateses
	*/
	public java.util.List<RegistrationTemplates> findByFNO(long groupId,
		String formNo, int start, int end);

	/**
	* Returns an ordered range of all the registration templateses where groupId = &#63; and formNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationTemplatesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param formNo the form no
	* @param start the lower bound of the range of registration templateses
	* @param end the upper bound of the range of registration templateses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching registration templateses
	*/
	public java.util.List<RegistrationTemplates> findByFNO(long groupId,
		String formNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationTemplates> orderByComparator);

	/**
	* Returns an ordered range of all the registration templateses where groupId = &#63; and formNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationTemplatesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param formNo the form no
	* @param start the lower bound of the range of registration templateses
	* @param end the upper bound of the range of registration templateses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching registration templateses
	*/
	public java.util.List<RegistrationTemplates> findByFNO(long groupId,
		String formNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationTemplates> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first registration templates in the ordered set where groupId = &#63; and formNo = &#63;.
	*
	* @param groupId the group ID
	* @param formNo the form no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration templates
	* @throws NoSuchRegistrationTemplatesException if a matching registration templates could not be found
	*/
	public RegistrationTemplates findByFNO_First(long groupId, String formNo,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationTemplates> orderByComparator)
		throws NoSuchRegistrationTemplatesException;

	/**
	* Returns the first registration templates in the ordered set where groupId = &#63; and formNo = &#63;.
	*
	* @param groupId the group ID
	* @param formNo the form no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration templates, or <code>null</code> if a matching registration templates could not be found
	*/
	public RegistrationTemplates fetchByFNO_First(long groupId, String formNo,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationTemplates> orderByComparator);

	/**
	* Returns the last registration templates in the ordered set where groupId = &#63; and formNo = &#63;.
	*
	* @param groupId the group ID
	* @param formNo the form no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration templates
	* @throws NoSuchRegistrationTemplatesException if a matching registration templates could not be found
	*/
	public RegistrationTemplates findByFNO_Last(long groupId, String formNo,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationTemplates> orderByComparator)
		throws NoSuchRegistrationTemplatesException;

	/**
	* Returns the last registration templates in the ordered set where groupId = &#63; and formNo = &#63;.
	*
	* @param groupId the group ID
	* @param formNo the form no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration templates, or <code>null</code> if a matching registration templates could not be found
	*/
	public RegistrationTemplates fetchByFNO_Last(long groupId, String formNo,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationTemplates> orderByComparator);

	/**
	* Returns the registration templateses before and after the current registration templates in the ordered set where groupId = &#63; and formNo = &#63;.
	*
	* @param registrationTemplateId the primary key of the current registration templates
	* @param groupId the group ID
	* @param formNo the form no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next registration templates
	* @throws NoSuchRegistrationTemplatesException if a registration templates with the primary key could not be found
	*/
	public RegistrationTemplates[] findByFNO_PrevAndNext(
		long registrationTemplateId, long groupId, String formNo,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationTemplates> orderByComparator)
		throws NoSuchRegistrationTemplatesException;

	/**
	* Removes all the registration templateses where groupId = &#63; and formNo = &#63; from the database.
	*
	* @param groupId the group ID
	* @param formNo the form no
	*/
	public void removeByFNO(long groupId, String formNo);

	/**
	* Returns the number of registration templateses where groupId = &#63; and formNo = &#63;.
	*
	* @param groupId the group ID
	* @param formNo the form no
	* @return the number of matching registration templateses
	*/
	public int countByFNO(long groupId, String formNo);

	/**
	* Returns all the registration templateses where groupId = &#63; and govAgencyCode = &#63;.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @return the matching registration templateses
	*/
	public java.util.List<RegistrationTemplates> findByGOVCODE(long groupId,
		String govAgencyCode);

	/**
	* Returns a range of all the registration templateses where groupId = &#63; and govAgencyCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationTemplatesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param start the lower bound of the range of registration templateses
	* @param end the upper bound of the range of registration templateses (not inclusive)
	* @return the range of matching registration templateses
	*/
	public java.util.List<RegistrationTemplates> findByGOVCODE(long groupId,
		String govAgencyCode, int start, int end);

	/**
	* Returns an ordered range of all the registration templateses where groupId = &#63; and govAgencyCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationTemplatesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param start the lower bound of the range of registration templateses
	* @param end the upper bound of the range of registration templateses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching registration templateses
	*/
	public java.util.List<RegistrationTemplates> findByGOVCODE(long groupId,
		String govAgencyCode, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationTemplates> orderByComparator);

	/**
	* Returns an ordered range of all the registration templateses where groupId = &#63; and govAgencyCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationTemplatesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param start the lower bound of the range of registration templateses
	* @param end the upper bound of the range of registration templateses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching registration templateses
	*/
	public java.util.List<RegistrationTemplates> findByGOVCODE(long groupId,
		String govAgencyCode, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationTemplates> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first registration templates in the ordered set where groupId = &#63; and govAgencyCode = &#63;.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration templates
	* @throws NoSuchRegistrationTemplatesException if a matching registration templates could not be found
	*/
	public RegistrationTemplates findByGOVCODE_First(long groupId,
		String govAgencyCode,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationTemplates> orderByComparator)
		throws NoSuchRegistrationTemplatesException;

	/**
	* Returns the first registration templates in the ordered set where groupId = &#63; and govAgencyCode = &#63;.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration templates, or <code>null</code> if a matching registration templates could not be found
	*/
	public RegistrationTemplates fetchByGOVCODE_First(long groupId,
		String govAgencyCode,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationTemplates> orderByComparator);

	/**
	* Returns the last registration templates in the ordered set where groupId = &#63; and govAgencyCode = &#63;.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration templates
	* @throws NoSuchRegistrationTemplatesException if a matching registration templates could not be found
	*/
	public RegistrationTemplates findByGOVCODE_Last(long groupId,
		String govAgencyCode,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationTemplates> orderByComparator)
		throws NoSuchRegistrationTemplatesException;

	/**
	* Returns the last registration templates in the ordered set where groupId = &#63; and govAgencyCode = &#63;.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration templates, or <code>null</code> if a matching registration templates could not be found
	*/
	public RegistrationTemplates fetchByGOVCODE_Last(long groupId,
		String govAgencyCode,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationTemplates> orderByComparator);

	/**
	* Returns the registration templateses before and after the current registration templates in the ordered set where groupId = &#63; and govAgencyCode = &#63;.
	*
	* @param registrationTemplateId the primary key of the current registration templates
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next registration templates
	* @throws NoSuchRegistrationTemplatesException if a registration templates with the primary key could not be found
	*/
	public RegistrationTemplates[] findByGOVCODE_PrevAndNext(
		long registrationTemplateId, long groupId, String govAgencyCode,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationTemplates> orderByComparator)
		throws NoSuchRegistrationTemplatesException;

	/**
	* Removes all the registration templateses where groupId = &#63; and govAgencyCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	*/
	public void removeByGOVCODE(long groupId, String govAgencyCode);

	/**
	* Returns the number of registration templateses where groupId = &#63; and govAgencyCode = &#63;.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @return the number of matching registration templateses
	*/
	public int countByGOVCODE(long groupId, String govAgencyCode);

	/**
	* Returns the registration templates where groupId = &#63; and formNo = &#63; and govAgencyCode = &#63; or throws a {@link NoSuchRegistrationTemplatesException} if it could not be found.
	*
	* @param groupId the group ID
	* @param formNo the form no
	* @param govAgencyCode the gov agency code
	* @return the matching registration templates
	* @throws NoSuchRegistrationTemplatesException if a matching registration templates could not be found
	*/
	public RegistrationTemplates findByGOVCODE_FORMNO(long groupId,
		String formNo, String govAgencyCode)
		throws NoSuchRegistrationTemplatesException;

	/**
	* Returns the registration templates where groupId = &#63; and formNo = &#63; and govAgencyCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param formNo the form no
	* @param govAgencyCode the gov agency code
	* @return the matching registration templates, or <code>null</code> if a matching registration templates could not be found
	*/
	public RegistrationTemplates fetchByGOVCODE_FORMNO(long groupId,
		String formNo, String govAgencyCode);

	/**
	* Returns the registration templates where groupId = &#63; and formNo = &#63; and govAgencyCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param formNo the form no
	* @param govAgencyCode the gov agency code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching registration templates, or <code>null</code> if a matching registration templates could not be found
	*/
	public RegistrationTemplates fetchByGOVCODE_FORMNO(long groupId,
		String formNo, String govAgencyCode, boolean retrieveFromCache);

	/**
	* Removes the registration templates where groupId = &#63; and formNo = &#63; and govAgencyCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param formNo the form no
	* @param govAgencyCode the gov agency code
	* @return the registration templates that was removed
	*/
	public RegistrationTemplates removeByGOVCODE_FORMNO(long groupId,
		String formNo, String govAgencyCode)
		throws NoSuchRegistrationTemplatesException;

	/**
	* Returns the number of registration templateses where groupId = &#63; and formNo = &#63; and govAgencyCode = &#63;.
	*
	* @param groupId the group ID
	* @param formNo the form no
	* @param govAgencyCode the gov agency code
	* @return the number of matching registration templateses
	*/
	public int countByGOVCODE_FORMNO(long groupId, String formNo,
		String govAgencyCode);

	/**
	* Returns all the registration templateses where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching registration templateses
	*/
	public java.util.List<RegistrationTemplates> findByGROUPID(long groupId);

	/**
	* Returns a range of all the registration templateses where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationTemplatesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of registration templateses
	* @param end the upper bound of the range of registration templateses (not inclusive)
	* @return the range of matching registration templateses
	*/
	public java.util.List<RegistrationTemplates> findByGROUPID(long groupId,
		int start, int end);

	/**
	* Returns an ordered range of all the registration templateses where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationTemplatesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of registration templateses
	* @param end the upper bound of the range of registration templateses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching registration templateses
	*/
	public java.util.List<RegistrationTemplates> findByGROUPID(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationTemplates> orderByComparator);

	/**
	* Returns an ordered range of all the registration templateses where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationTemplatesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of registration templateses
	* @param end the upper bound of the range of registration templateses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching registration templateses
	*/
	public java.util.List<RegistrationTemplates> findByGROUPID(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationTemplates> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first registration templates in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration templates
	* @throws NoSuchRegistrationTemplatesException if a matching registration templates could not be found
	*/
	public RegistrationTemplates findByGROUPID_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationTemplates> orderByComparator)
		throws NoSuchRegistrationTemplatesException;

	/**
	* Returns the first registration templates in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration templates, or <code>null</code> if a matching registration templates could not be found
	*/
	public RegistrationTemplates fetchByGROUPID_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationTemplates> orderByComparator);

	/**
	* Returns the last registration templates in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration templates
	* @throws NoSuchRegistrationTemplatesException if a matching registration templates could not be found
	*/
	public RegistrationTemplates findByGROUPID_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationTemplates> orderByComparator)
		throws NoSuchRegistrationTemplatesException;

	/**
	* Returns the last registration templates in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration templates, or <code>null</code> if a matching registration templates could not be found
	*/
	public RegistrationTemplates fetchByGROUPID_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationTemplates> orderByComparator);

	/**
	* Returns the registration templateses before and after the current registration templates in the ordered set where groupId = &#63;.
	*
	* @param registrationTemplateId the primary key of the current registration templates
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next registration templates
	* @throws NoSuchRegistrationTemplatesException if a registration templates with the primary key could not be found
	*/
	public RegistrationTemplates[] findByGROUPID_PrevAndNext(
		long registrationTemplateId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationTemplates> orderByComparator)
		throws NoSuchRegistrationTemplatesException;

	/**
	* Removes all the registration templateses where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGROUPID(long groupId);

	/**
	* Returns the number of registration templateses where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching registration templateses
	*/
	public int countByGROUPID(long groupId);

	/**
	* Caches the registration templates in the entity cache if it is enabled.
	*
	* @param registrationTemplates the registration templates
	*/
	public void cacheResult(RegistrationTemplates registrationTemplates);

	/**
	* Caches the registration templateses in the entity cache if it is enabled.
	*
	* @param registrationTemplateses the registration templateses
	*/
	public void cacheResult(
		java.util.List<RegistrationTemplates> registrationTemplateses);

	/**
	* Creates a new registration templates with the primary key. Does not add the registration templates to the database.
	*
	* @param registrationTemplateId the primary key for the new registration templates
	* @return the new registration templates
	*/
	public RegistrationTemplates create(long registrationTemplateId);

	/**
	* Removes the registration templates with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param registrationTemplateId the primary key of the registration templates
	* @return the registration templates that was removed
	* @throws NoSuchRegistrationTemplatesException if a registration templates with the primary key could not be found
	*/
	public RegistrationTemplates remove(long registrationTemplateId)
		throws NoSuchRegistrationTemplatesException;

	public RegistrationTemplates updateImpl(
		RegistrationTemplates registrationTemplates);

	/**
	* Returns the registration templates with the primary key or throws a {@link NoSuchRegistrationTemplatesException} if it could not be found.
	*
	* @param registrationTemplateId the primary key of the registration templates
	* @return the registration templates
	* @throws NoSuchRegistrationTemplatesException if a registration templates with the primary key could not be found
	*/
	public RegistrationTemplates findByPrimaryKey(long registrationTemplateId)
		throws NoSuchRegistrationTemplatesException;

	/**
	* Returns the registration templates with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param registrationTemplateId the primary key of the registration templates
	* @return the registration templates, or <code>null</code> if a registration templates with the primary key could not be found
	*/
	public RegistrationTemplates fetchByPrimaryKey(long registrationTemplateId);

	@Override
	public java.util.Map<java.io.Serializable, RegistrationTemplates> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the registration templateses.
	*
	* @return the registration templateses
	*/
	public java.util.List<RegistrationTemplates> findAll();

	/**
	* Returns a range of all the registration templateses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationTemplatesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of registration templateses
	* @param end the upper bound of the range of registration templateses (not inclusive)
	* @return the range of registration templateses
	*/
	public java.util.List<RegistrationTemplates> findAll(int start, int end);

	/**
	* Returns an ordered range of all the registration templateses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationTemplatesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of registration templateses
	* @param end the upper bound of the range of registration templateses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of registration templateses
	*/
	public java.util.List<RegistrationTemplates> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationTemplates> orderByComparator);

	/**
	* Returns an ordered range of all the registration templateses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationTemplatesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of registration templateses
	* @param end the upper bound of the range of registration templateses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of registration templateses
	*/
	public java.util.List<RegistrationTemplates> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationTemplates> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the registration templateses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of registration templateses.
	*
	* @return the number of registration templateses
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}