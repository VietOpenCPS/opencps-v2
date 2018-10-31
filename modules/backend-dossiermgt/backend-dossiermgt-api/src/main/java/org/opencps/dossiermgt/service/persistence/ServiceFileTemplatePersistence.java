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

import org.opencps.dossiermgt.exception.NoSuchServiceFileTemplateException;
import org.opencps.dossiermgt.model.ServiceFileTemplate;

/**
 * The persistence interface for the service file template service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see org.opencps.dossiermgt.service.persistence.impl.ServiceFileTemplatePersistenceImpl
 * @see ServiceFileTemplateUtil
 * @generated
 */
@ProviderType
public interface ServiceFileTemplatePersistence extends BasePersistence<ServiceFileTemplate> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ServiceFileTemplateUtil} to access the service file template persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the service file templates where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching service file templates
	*/
	public java.util.List<ServiceFileTemplate> findByUuid(String uuid);

	/**
	* Returns a range of all the service file templates where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceFileTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of service file templates
	* @param end the upper bound of the range of service file templates (not inclusive)
	* @return the range of matching service file templates
	*/
	public java.util.List<ServiceFileTemplate> findByUuid(String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the service file templates where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceFileTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of service file templates
	* @param end the upper bound of the range of service file templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching service file templates
	*/
	public java.util.List<ServiceFileTemplate> findByUuid(String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceFileTemplate> orderByComparator);

	/**
	* Returns an ordered range of all the service file templates where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceFileTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of service file templates
	* @param end the upper bound of the range of service file templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching service file templates
	*/
	public java.util.List<ServiceFileTemplate> findByUuid(String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceFileTemplate> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first service file template in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service file template
	* @throws NoSuchServiceFileTemplateException if a matching service file template could not be found
	*/
	public ServiceFileTemplate findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceFileTemplate> orderByComparator)
		throws NoSuchServiceFileTemplateException;

	/**
	* Returns the first service file template in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service file template, or <code>null</code> if a matching service file template could not be found
	*/
	public ServiceFileTemplate fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceFileTemplate> orderByComparator);

	/**
	* Returns the last service file template in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service file template
	* @throws NoSuchServiceFileTemplateException if a matching service file template could not be found
	*/
	public ServiceFileTemplate findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceFileTemplate> orderByComparator)
		throws NoSuchServiceFileTemplateException;

	/**
	* Returns the last service file template in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service file template, or <code>null</code> if a matching service file template could not be found
	*/
	public ServiceFileTemplate fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceFileTemplate> orderByComparator);

	/**
	* Returns the service file templates before and after the current service file template in the ordered set where uuid = &#63;.
	*
	* @param serviceFileTemplatePK the primary key of the current service file template
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next service file template
	* @throws NoSuchServiceFileTemplateException if a service file template with the primary key could not be found
	*/
	public ServiceFileTemplate[] findByUuid_PrevAndNext(
		ServiceFileTemplatePK serviceFileTemplatePK, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceFileTemplate> orderByComparator)
		throws NoSuchServiceFileTemplateException;

	/**
	* Removes all the service file templates where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of service file templates where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching service file templates
	*/
	public int countByUuid(String uuid);

	/**
	* Returns all the service file templates where serviceInfoId = &#63;.
	*
	* @param serviceInfoId the service info ID
	* @return the matching service file templates
	*/
	public java.util.List<ServiceFileTemplate> findByServiceInfoId(
		long serviceInfoId);

	/**
	* Returns a range of all the service file templates where serviceInfoId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceFileTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param serviceInfoId the service info ID
	* @param start the lower bound of the range of service file templates
	* @param end the upper bound of the range of service file templates (not inclusive)
	* @return the range of matching service file templates
	*/
	public java.util.List<ServiceFileTemplate> findByServiceInfoId(
		long serviceInfoId, int start, int end);

	/**
	* Returns an ordered range of all the service file templates where serviceInfoId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceFileTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param serviceInfoId the service info ID
	* @param start the lower bound of the range of service file templates
	* @param end the upper bound of the range of service file templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching service file templates
	*/
	public java.util.List<ServiceFileTemplate> findByServiceInfoId(
		long serviceInfoId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceFileTemplate> orderByComparator);

	/**
	* Returns an ordered range of all the service file templates where serviceInfoId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceFileTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param serviceInfoId the service info ID
	* @param start the lower bound of the range of service file templates
	* @param end the upper bound of the range of service file templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching service file templates
	*/
	public java.util.List<ServiceFileTemplate> findByServiceInfoId(
		long serviceInfoId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceFileTemplate> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first service file template in the ordered set where serviceInfoId = &#63;.
	*
	* @param serviceInfoId the service info ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service file template
	* @throws NoSuchServiceFileTemplateException if a matching service file template could not be found
	*/
	public ServiceFileTemplate findByServiceInfoId_First(long serviceInfoId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceFileTemplate> orderByComparator)
		throws NoSuchServiceFileTemplateException;

	/**
	* Returns the first service file template in the ordered set where serviceInfoId = &#63;.
	*
	* @param serviceInfoId the service info ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service file template, or <code>null</code> if a matching service file template could not be found
	*/
	public ServiceFileTemplate fetchByServiceInfoId_First(long serviceInfoId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceFileTemplate> orderByComparator);

	/**
	* Returns the last service file template in the ordered set where serviceInfoId = &#63;.
	*
	* @param serviceInfoId the service info ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service file template
	* @throws NoSuchServiceFileTemplateException if a matching service file template could not be found
	*/
	public ServiceFileTemplate findByServiceInfoId_Last(long serviceInfoId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceFileTemplate> orderByComparator)
		throws NoSuchServiceFileTemplateException;

	/**
	* Returns the last service file template in the ordered set where serviceInfoId = &#63;.
	*
	* @param serviceInfoId the service info ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service file template, or <code>null</code> if a matching service file template could not be found
	*/
	public ServiceFileTemplate fetchByServiceInfoId_Last(long serviceInfoId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceFileTemplate> orderByComparator);

	/**
	* Returns the service file templates before and after the current service file template in the ordered set where serviceInfoId = &#63;.
	*
	* @param serviceFileTemplatePK the primary key of the current service file template
	* @param serviceInfoId the service info ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next service file template
	* @throws NoSuchServiceFileTemplateException if a service file template with the primary key could not be found
	*/
	public ServiceFileTemplate[] findByServiceInfoId_PrevAndNext(
		ServiceFileTemplatePK serviceFileTemplatePK, long serviceInfoId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceFileTemplate> orderByComparator)
		throws NoSuchServiceFileTemplateException;

	/**
	* Removes all the service file templates where serviceInfoId = &#63; from the database.
	*
	* @param serviceInfoId the service info ID
	*/
	public void removeByServiceInfoId(long serviceInfoId);

	/**
	* Returns the number of service file templates where serviceInfoId = &#63;.
	*
	* @param serviceInfoId the service info ID
	* @return the number of matching service file templates
	*/
	public int countByServiceInfoId(long serviceInfoId);

	/**
	* Returns the service file template where serviceInfoId = &#63; and fileTemplateNo = &#63; or throws a {@link NoSuchServiceFileTemplateException} if it could not be found.
	*
	* @param serviceInfoId the service info ID
	* @param fileTemplateNo the file template no
	* @return the matching service file template
	* @throws NoSuchServiceFileTemplateException if a matching service file template could not be found
	*/
	public ServiceFileTemplate findByF_serviceInfoId_fileTemplateNo(
		long serviceInfoId, String fileTemplateNo)
		throws NoSuchServiceFileTemplateException;

	/**
	* Returns the service file template where serviceInfoId = &#63; and fileTemplateNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param serviceInfoId the service info ID
	* @param fileTemplateNo the file template no
	* @return the matching service file template, or <code>null</code> if a matching service file template could not be found
	*/
	public ServiceFileTemplate fetchByF_serviceInfoId_fileTemplateNo(
		long serviceInfoId, String fileTemplateNo);

	/**
	* Returns the service file template where serviceInfoId = &#63; and fileTemplateNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param serviceInfoId the service info ID
	* @param fileTemplateNo the file template no
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching service file template, or <code>null</code> if a matching service file template could not be found
	*/
	public ServiceFileTemplate fetchByF_serviceInfoId_fileTemplateNo(
		long serviceInfoId, String fileTemplateNo, boolean retrieveFromCache);

	/**
	* Removes the service file template where serviceInfoId = &#63; and fileTemplateNo = &#63; from the database.
	*
	* @param serviceInfoId the service info ID
	* @param fileTemplateNo the file template no
	* @return the service file template that was removed
	*/
	public ServiceFileTemplate removeByF_serviceInfoId_fileTemplateNo(
		long serviceInfoId, String fileTemplateNo)
		throws NoSuchServiceFileTemplateException;

	/**
	* Returns the number of service file templates where serviceInfoId = &#63; and fileTemplateNo = &#63;.
	*
	* @param serviceInfoId the service info ID
	* @param fileTemplateNo the file template no
	* @return the number of matching service file templates
	*/
	public int countByF_serviceInfoId_fileTemplateNo(long serviceInfoId,
		String fileTemplateNo);

	/**
	* Caches the service file template in the entity cache if it is enabled.
	*
	* @param serviceFileTemplate the service file template
	*/
	public void cacheResult(ServiceFileTemplate serviceFileTemplate);

	/**
	* Caches the service file templates in the entity cache if it is enabled.
	*
	* @param serviceFileTemplates the service file templates
	*/
	public void cacheResult(
		java.util.List<ServiceFileTemplate> serviceFileTemplates);

	/**
	* Creates a new service file template with the primary key. Does not add the service file template to the database.
	*
	* @param serviceFileTemplatePK the primary key for the new service file template
	* @return the new service file template
	*/
	public ServiceFileTemplate create(
		ServiceFileTemplatePK serviceFileTemplatePK);

	/**
	* Removes the service file template with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param serviceFileTemplatePK the primary key of the service file template
	* @return the service file template that was removed
	* @throws NoSuchServiceFileTemplateException if a service file template with the primary key could not be found
	*/
	public ServiceFileTemplate remove(
		ServiceFileTemplatePK serviceFileTemplatePK)
		throws NoSuchServiceFileTemplateException;

	public ServiceFileTemplate updateImpl(
		ServiceFileTemplate serviceFileTemplate);

	/**
	* Returns the service file template with the primary key or throws a {@link NoSuchServiceFileTemplateException} if it could not be found.
	*
	* @param serviceFileTemplatePK the primary key of the service file template
	* @return the service file template
	* @throws NoSuchServiceFileTemplateException if a service file template with the primary key could not be found
	*/
	public ServiceFileTemplate findByPrimaryKey(
		ServiceFileTemplatePK serviceFileTemplatePK)
		throws NoSuchServiceFileTemplateException;

	/**
	* Returns the service file template with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param serviceFileTemplatePK the primary key of the service file template
	* @return the service file template, or <code>null</code> if a service file template with the primary key could not be found
	*/
	public ServiceFileTemplate fetchByPrimaryKey(
		ServiceFileTemplatePK serviceFileTemplatePK);

	@Override
	public java.util.Map<java.io.Serializable, ServiceFileTemplate> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the service file templates.
	*
	* @return the service file templates
	*/
	public java.util.List<ServiceFileTemplate> findAll();

	/**
	* Returns a range of all the service file templates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceFileTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of service file templates
	* @param end the upper bound of the range of service file templates (not inclusive)
	* @return the range of service file templates
	*/
	public java.util.List<ServiceFileTemplate> findAll(int start, int end);

	/**
	* Returns an ordered range of all the service file templates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceFileTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of service file templates
	* @param end the upper bound of the range of service file templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of service file templates
	*/
	public java.util.List<ServiceFileTemplate> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceFileTemplate> orderByComparator);

	/**
	* Returns an ordered range of all the service file templates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceFileTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of service file templates
	* @param end the upper bound of the range of service file templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of service file templates
	*/
	public java.util.List<ServiceFileTemplate> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceFileTemplate> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the service file templates from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of service file templates.
	*
	* @return the number of service file templates
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();

	public java.util.Set<String> getCompoundPKColumnNames();
}