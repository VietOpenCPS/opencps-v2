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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SavePickFieldLocalService}.
 *
 * @author khoavu
 * @see SavePickFieldLocalService
 * @generated
 */
@ProviderType
public class SavePickFieldLocalServiceWrapper
	implements SavePickFieldLocalService,
		ServiceWrapper<SavePickFieldLocalService> {
	public SavePickFieldLocalServiceWrapper(
		SavePickFieldLocalService savePickFieldLocalService) {
		_savePickFieldLocalService = savePickFieldLocalService;
	}

	/**
	* Adds the save pick field to the database. Also notifies the appropriate model listeners.
	*
	* @param savePickField the save pick field
	* @return the save pick field that was added
	*/
	@Override
	public org.opencps.usermgt.model.SavePickField addSavePickField(
		org.opencps.usermgt.model.SavePickField savePickField) {
		return _savePickFieldLocalService.addSavePickField(savePickField);
	}

	/**
	* Creates a new save pick field with the primary key. Does not add the save pick field to the database.
	*
	* @param fieldPickId the primary key for the new save pick field
	* @return the new save pick field
	*/
	@Override
	public org.opencps.usermgt.model.SavePickField createSavePickField(
		long fieldPickId) {
		return _savePickFieldLocalService.createSavePickField(fieldPickId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _savePickFieldLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the save pick field with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param fieldPickId the primary key of the save pick field
	* @return the save pick field that was removed
	* @throws PortalException if a save pick field with the primary key could not be found
	*/
	@Override
	public org.opencps.usermgt.model.SavePickField deleteSavePickField(
		long fieldPickId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _savePickFieldLocalService.deleteSavePickField(fieldPickId);
	}

	/**
	* Deletes the save pick field from the database. Also notifies the appropriate model listeners.
	*
	* @param savePickField the save pick field
	* @return the save pick field that was removed
	*/
	@Override
	public org.opencps.usermgt.model.SavePickField deleteSavePickField(
		org.opencps.usermgt.model.SavePickField savePickField) {
		return _savePickFieldLocalService.deleteSavePickField(savePickField);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _savePickFieldLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _savePickFieldLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.SavePickFieldModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _savePickFieldLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.SavePickFieldModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _savePickFieldLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _savePickFieldLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _savePickFieldLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.usermgt.model.SavePickField fetchSavePickField(
		long fieldPickId) {
		return _savePickFieldLocalService.fetchSavePickField(fieldPickId);
	}

	/**
	* Returns the save pick field matching the UUID and group.
	*
	* @param uuid the save pick field's UUID
	* @param groupId the primary key of the group
	* @return the matching save pick field, or <code>null</code> if a matching save pick field could not be found
	*/
	@Override
	public org.opencps.usermgt.model.SavePickField fetchSavePickFieldByUuidAndGroupId(
		String uuid, long groupId) {
		return _savePickFieldLocalService.fetchSavePickFieldByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _savePickFieldLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _savePickFieldLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _savePickFieldLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _savePickFieldLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public org.opencps.usermgt.model.SavePickField getPickField(long groupId,
		long userId, String classPK) {
		return _savePickFieldLocalService.getPickField(groupId, userId, classPK);
	}

	/**
	* Returns the save pick field with the primary key.
	*
	* @param fieldPickId the primary key of the save pick field
	* @return the save pick field
	* @throws PortalException if a save pick field with the primary key could not be found
	*/
	@Override
	public org.opencps.usermgt.model.SavePickField getSavePickField(
		long fieldPickId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _savePickFieldLocalService.getSavePickField(fieldPickId);
	}

	/**
	* Returns the save pick field matching the UUID and group.
	*
	* @param uuid the save pick field's UUID
	* @param groupId the primary key of the group
	* @return the matching save pick field
	* @throws PortalException if a matching save pick field could not be found
	*/
	@Override
	public org.opencps.usermgt.model.SavePickField getSavePickFieldByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _savePickFieldLocalService.getSavePickFieldByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns a range of all the save pick fields.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.SavePickFieldModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of save pick fields
	* @param end the upper bound of the range of save pick fields (not inclusive)
	* @return the range of save pick fields
	*/
	@Override
	public java.util.List<org.opencps.usermgt.model.SavePickField> getSavePickFields(
		int start, int end) {
		return _savePickFieldLocalService.getSavePickFields(start, end);
	}

	/**
	* Returns the number of save pick fields.
	*
	* @return the number of save pick fields
	*/
	@Override
	public int getSavePickFieldsCount() {
		return _savePickFieldLocalService.getSavePickFieldsCount();
	}

	@Override
	public org.opencps.usermgt.model.SavePickField updatePickField(
		long userId, long groupId, long fieldPickId, String formData,
		String classPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _savePickFieldLocalService.updatePickField(userId, groupId,
			fieldPickId, formData, classPK);
	}

	/**
	* Updates the save pick field in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param savePickField the save pick field
	* @return the save pick field that was updated
	*/
	@Override
	public org.opencps.usermgt.model.SavePickField updateSavePickField(
		org.opencps.usermgt.model.SavePickField savePickField) {
		return _savePickFieldLocalService.updateSavePickField(savePickField);
	}

	@Override
	public SavePickFieldLocalService getWrappedService() {
		return _savePickFieldLocalService;
	}

	@Override
	public void setWrappedService(
		SavePickFieldLocalService savePickFieldLocalService) {
		_savePickFieldLocalService = savePickFieldLocalService;
	}

	private SavePickFieldLocalService _savePickFieldLocalService;
}