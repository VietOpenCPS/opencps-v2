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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.dossiermgt.model.ServiceFileTemplate;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the service file template service. This utility wraps {@link org.opencps.dossiermgt.service.persistence.impl.ServiceFileTemplatePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see ServiceFileTemplatePersistence
 * @see org.opencps.dossiermgt.service.persistence.impl.ServiceFileTemplatePersistenceImpl
 * @generated
 */
@ProviderType
public class ServiceFileTemplateUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(ServiceFileTemplate serviceFileTemplate) {
		getPersistence().clearCache(serviceFileTemplate);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ServiceFileTemplate> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ServiceFileTemplate> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ServiceFileTemplate> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ServiceFileTemplate> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ServiceFileTemplate update(
		ServiceFileTemplate serviceFileTemplate) {
		return getPersistence().update(serviceFileTemplate);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ServiceFileTemplate update(
		ServiceFileTemplate serviceFileTemplate, ServiceContext serviceContext) {
		return getPersistence().update(serviceFileTemplate, serviceContext);
	}

	/**
	* Returns all the service file templates where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching service file templates
	*/
	public static List<ServiceFileTemplate> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

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
	public static List<ServiceFileTemplate> findByUuid(String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static List<ServiceFileTemplate> findByUuid(String uuid, int start,
		int end, OrderByComparator<ServiceFileTemplate> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

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
	public static List<ServiceFileTemplate> findByUuid(String uuid, int start,
		int end, OrderByComparator<ServiceFileTemplate> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first service file template in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service file template
	* @throws NoSuchServiceFileTemplateException if a matching service file template could not be found
	*/
	public static ServiceFileTemplate findByUuid_First(String uuid,
		OrderByComparator<ServiceFileTemplate> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchServiceFileTemplateException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first service file template in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service file template, or <code>null</code> if a matching service file template could not be found
	*/
	public static ServiceFileTemplate fetchByUuid_First(String uuid,
		OrderByComparator<ServiceFileTemplate> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last service file template in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service file template
	* @throws NoSuchServiceFileTemplateException if a matching service file template could not be found
	*/
	public static ServiceFileTemplate findByUuid_Last(String uuid,
		OrderByComparator<ServiceFileTemplate> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchServiceFileTemplateException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last service file template in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service file template, or <code>null</code> if a matching service file template could not be found
	*/
	public static ServiceFileTemplate fetchByUuid_Last(String uuid,
		OrderByComparator<ServiceFileTemplate> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the service file templates before and after the current service file template in the ordered set where uuid = &#63;.
	*
	* @param serviceFileTemplatePK the primary key of the current service file template
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next service file template
	* @throws NoSuchServiceFileTemplateException if a service file template with the primary key could not be found
	*/
	public static ServiceFileTemplate[] findByUuid_PrevAndNext(
		ServiceFileTemplatePK serviceFileTemplatePK, String uuid,
		OrderByComparator<ServiceFileTemplate> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchServiceFileTemplateException {
		return getPersistence()
				   .findByUuid_PrevAndNext(serviceFileTemplatePK, uuid,
			orderByComparator);
	}

	/**
	* Removes all the service file templates where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of service file templates where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching service file templates
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns all the service file templates where serviceInfoId = &#63;.
	*
	* @param serviceInfoId the service info ID
	* @return the matching service file templates
	*/
	public static List<ServiceFileTemplate> findByServiceInfoId(
		long serviceInfoId) {
		return getPersistence().findByServiceInfoId(serviceInfoId);
	}

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
	public static List<ServiceFileTemplate> findByServiceInfoId(
		long serviceInfoId, int start, int end) {
		return getPersistence().findByServiceInfoId(serviceInfoId, start, end);
	}

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
	public static List<ServiceFileTemplate> findByServiceInfoId(
		long serviceInfoId, int start, int end,
		OrderByComparator<ServiceFileTemplate> orderByComparator) {
		return getPersistence()
				   .findByServiceInfoId(serviceInfoId, start, end,
			orderByComparator);
	}

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
	public static List<ServiceFileTemplate> findByServiceInfoId(
		long serviceInfoId, int start, int end,
		OrderByComparator<ServiceFileTemplate> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByServiceInfoId(serviceInfoId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first service file template in the ordered set where serviceInfoId = &#63;.
	*
	* @param serviceInfoId the service info ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service file template
	* @throws NoSuchServiceFileTemplateException if a matching service file template could not be found
	*/
	public static ServiceFileTemplate findByServiceInfoId_First(
		long serviceInfoId,
		OrderByComparator<ServiceFileTemplate> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchServiceFileTemplateException {
		return getPersistence()
				   .findByServiceInfoId_First(serviceInfoId, orderByComparator);
	}

	/**
	* Returns the first service file template in the ordered set where serviceInfoId = &#63;.
	*
	* @param serviceInfoId the service info ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service file template, or <code>null</code> if a matching service file template could not be found
	*/
	public static ServiceFileTemplate fetchByServiceInfoId_First(
		long serviceInfoId,
		OrderByComparator<ServiceFileTemplate> orderByComparator) {
		return getPersistence()
				   .fetchByServiceInfoId_First(serviceInfoId, orderByComparator);
	}

	/**
	* Returns the last service file template in the ordered set where serviceInfoId = &#63;.
	*
	* @param serviceInfoId the service info ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service file template
	* @throws NoSuchServiceFileTemplateException if a matching service file template could not be found
	*/
	public static ServiceFileTemplate findByServiceInfoId_Last(
		long serviceInfoId,
		OrderByComparator<ServiceFileTemplate> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchServiceFileTemplateException {
		return getPersistence()
				   .findByServiceInfoId_Last(serviceInfoId, orderByComparator);
	}

	/**
	* Returns the last service file template in the ordered set where serviceInfoId = &#63;.
	*
	* @param serviceInfoId the service info ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service file template, or <code>null</code> if a matching service file template could not be found
	*/
	public static ServiceFileTemplate fetchByServiceInfoId_Last(
		long serviceInfoId,
		OrderByComparator<ServiceFileTemplate> orderByComparator) {
		return getPersistence()
				   .fetchByServiceInfoId_Last(serviceInfoId, orderByComparator);
	}

	/**
	* Returns the service file templates before and after the current service file template in the ordered set where serviceInfoId = &#63;.
	*
	* @param serviceFileTemplatePK the primary key of the current service file template
	* @param serviceInfoId the service info ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next service file template
	* @throws NoSuchServiceFileTemplateException if a service file template with the primary key could not be found
	*/
	public static ServiceFileTemplate[] findByServiceInfoId_PrevAndNext(
		ServiceFileTemplatePK serviceFileTemplatePK, long serviceInfoId,
		OrderByComparator<ServiceFileTemplate> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchServiceFileTemplateException {
		return getPersistence()
				   .findByServiceInfoId_PrevAndNext(serviceFileTemplatePK,
			serviceInfoId, orderByComparator);
	}

	/**
	* Removes all the service file templates where serviceInfoId = &#63; from the database.
	*
	* @param serviceInfoId the service info ID
	*/
	public static void removeByServiceInfoId(long serviceInfoId) {
		getPersistence().removeByServiceInfoId(serviceInfoId);
	}

	/**
	* Returns the number of service file templates where serviceInfoId = &#63;.
	*
	* @param serviceInfoId the service info ID
	* @return the number of matching service file templates
	*/
	public static int countByServiceInfoId(long serviceInfoId) {
		return getPersistence().countByServiceInfoId(serviceInfoId);
	}

	/**
	* Caches the service file template in the entity cache if it is enabled.
	*
	* @param serviceFileTemplate the service file template
	*/
	public static void cacheResult(ServiceFileTemplate serviceFileTemplate) {
		getPersistence().cacheResult(serviceFileTemplate);
	}

	/**
	* Caches the service file templates in the entity cache if it is enabled.
	*
	* @param serviceFileTemplates the service file templates
	*/
	public static void cacheResult(
		List<ServiceFileTemplate> serviceFileTemplates) {
		getPersistence().cacheResult(serviceFileTemplates);
	}

	/**
	* Creates a new service file template with the primary key. Does not add the service file template to the database.
	*
	* @param serviceFileTemplatePK the primary key for the new service file template
	* @return the new service file template
	*/
	public static ServiceFileTemplate create(
		ServiceFileTemplatePK serviceFileTemplatePK) {
		return getPersistence().create(serviceFileTemplatePK);
	}

	/**
	* Removes the service file template with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param serviceFileTemplatePK the primary key of the service file template
	* @return the service file template that was removed
	* @throws NoSuchServiceFileTemplateException if a service file template with the primary key could not be found
	*/
	public static ServiceFileTemplate remove(
		ServiceFileTemplatePK serviceFileTemplatePK)
		throws org.opencps.dossiermgt.exception.NoSuchServiceFileTemplateException {
		return getPersistence().remove(serviceFileTemplatePK);
	}

	public static ServiceFileTemplate updateImpl(
		ServiceFileTemplate serviceFileTemplate) {
		return getPersistence().updateImpl(serviceFileTemplate);
	}

	/**
	* Returns the service file template with the primary key or throws a {@link NoSuchServiceFileTemplateException} if it could not be found.
	*
	* @param serviceFileTemplatePK the primary key of the service file template
	* @return the service file template
	* @throws NoSuchServiceFileTemplateException if a service file template with the primary key could not be found
	*/
	public static ServiceFileTemplate findByPrimaryKey(
		ServiceFileTemplatePK serviceFileTemplatePK)
		throws org.opencps.dossiermgt.exception.NoSuchServiceFileTemplateException {
		return getPersistence().findByPrimaryKey(serviceFileTemplatePK);
	}

	/**
	* Returns the service file template with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param serviceFileTemplatePK the primary key of the service file template
	* @return the service file template, or <code>null</code> if a service file template with the primary key could not be found
	*/
	public static ServiceFileTemplate fetchByPrimaryKey(
		ServiceFileTemplatePK serviceFileTemplatePK) {
		return getPersistence().fetchByPrimaryKey(serviceFileTemplatePK);
	}

	public static java.util.Map<java.io.Serializable, ServiceFileTemplate> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the service file templates.
	*
	* @return the service file templates
	*/
	public static List<ServiceFileTemplate> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<ServiceFileTemplate> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<ServiceFileTemplate> findAll(int start, int end,
		OrderByComparator<ServiceFileTemplate> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<ServiceFileTemplate> findAll(int start, int end,
		OrderByComparator<ServiceFileTemplate> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the service file templates from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of service file templates.
	*
	* @return the number of service file templates
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static java.util.Set<String> getCompoundPKColumnNames() {
		return getPersistence().getCompoundPKColumnNames();
	}

	public static ServiceFileTemplatePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ServiceFileTemplatePersistence, ServiceFileTemplatePersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ServiceFileTemplatePersistence.class);

		ServiceTracker<ServiceFileTemplatePersistence, ServiceFileTemplatePersistence> serviceTracker =
			new ServiceTracker<ServiceFileTemplatePersistence, ServiceFileTemplatePersistence>(bundle.getBundleContext(),
				ServiceFileTemplatePersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}