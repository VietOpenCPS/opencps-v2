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
 * Provides a wrapper for {@link LGSPTokenLocalService}.
 *
 * @author khoavu
 * @see LGSPTokenLocalService
 * @generated
 */
@ProviderType
public class LGSPTokenLocalServiceWrapper implements LGSPTokenLocalService,
	ServiceWrapper<LGSPTokenLocalService> {
	public LGSPTokenLocalServiceWrapper(
		LGSPTokenLocalService lgspTokenLocalService) {
		_lgspTokenLocalService = lgspTokenLocalService;
	}

	/**
	* Adds the lgsp token to the database. Also notifies the appropriate model listeners.
	*
	* @param lgspToken the lgsp token
	* @return the lgsp token that was added
	*/
	@Override
	public org.opencps.usermgt.model.LGSPToken addLGSPToken(
		org.opencps.usermgt.model.LGSPToken lgspToken) {
		return _lgspTokenLocalService.addLGSPToken(lgspToken);
	}

	/**
	* Creates a new lgsp token with the primary key. Does not add the lgsp token to the database.
	*
	* @param tokenId the primary key for the new lgsp token
	* @return the new lgsp token
	*/
	@Override
	public org.opencps.usermgt.model.LGSPToken createLGSPToken(long tokenId) {
		return _lgspTokenLocalService.createLGSPToken(tokenId);
	}

	/**
	* Deletes the lgsp token from the database. Also notifies the appropriate model listeners.
	*
	* @param lgspToken the lgsp token
	* @return the lgsp token that was removed
	*/
	@Override
	public org.opencps.usermgt.model.LGSPToken deleteLGSPToken(
		org.opencps.usermgt.model.LGSPToken lgspToken) {
		return _lgspTokenLocalService.deleteLGSPToken(lgspToken);
	}

	/**
	* Deletes the lgsp token with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param tokenId the primary key of the lgsp token
	* @return the lgsp token that was removed
	* @throws PortalException if a lgsp token with the primary key could not be found
	*/
	@Override
	public org.opencps.usermgt.model.LGSPToken deleteLGSPToken(long tokenId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lgspTokenLocalService.deleteLGSPToken(tokenId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lgspTokenLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _lgspTokenLocalService.dynamicQuery();
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
		return _lgspTokenLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.LGSPTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _lgspTokenLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.LGSPTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _lgspTokenLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
		return _lgspTokenLocalService.dynamicQueryCount(dynamicQuery);
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
		return _lgspTokenLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public org.opencps.usermgt.model.LGSPToken fetchByTokenType(
		String tokenType) {
		return _lgspTokenLocalService.fetchByTokenType(tokenType);
	}

	@Override
	public org.opencps.usermgt.model.LGSPToken fetchLGSPToken(long tokenId) {
		return _lgspTokenLocalService.fetchLGSPToken(tokenId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _lgspTokenLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _lgspTokenLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the lgsp token with the primary key.
	*
	* @param tokenId the primary key of the lgsp token
	* @return the lgsp token
	* @throws PortalException if a lgsp token with the primary key could not be found
	*/
	@Override
	public org.opencps.usermgt.model.LGSPToken getLGSPToken(long tokenId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lgspTokenLocalService.getLGSPToken(tokenId);
	}

	/**
	* Returns a range of all the lgsp tokens.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.LGSPTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of lgsp tokens
	* @param end the upper bound of the range of lgsp tokens (not inclusive)
	* @return the range of lgsp tokens
	*/
	@Override
	public java.util.List<org.opencps.usermgt.model.LGSPToken> getLGSPTokens(
		int start, int end) {
		return _lgspTokenLocalService.getLGSPTokens(start, end);
	}

	/**
	* Returns the number of lgsp tokens.
	*
	* @return the number of lgsp tokens
	*/
	@Override
	public int getLGSPTokensCount() {
		return _lgspTokenLocalService.getLGSPTokensCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _lgspTokenLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lgspTokenLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the lgsp token in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param lgspToken the lgsp token
	* @return the lgsp token that was updated
	*/
	@Override
	public org.opencps.usermgt.model.LGSPToken updateLGSPToken(
		org.opencps.usermgt.model.LGSPToken lgspToken) {
		return _lgspTokenLocalService.updateLGSPToken(lgspToken);
	}

	@Override
	public LGSPTokenLocalService getWrappedService() {
		return _lgspTokenLocalService;
	}

	@Override
	public void setWrappedService(LGSPTokenLocalService lgspTokenLocalService) {
		_lgspTokenLocalService = lgspTokenLocalService;
	}

	private LGSPTokenLocalService _lgspTokenLocalService;
}