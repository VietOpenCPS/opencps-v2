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

package org.opencps.communication.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ServerConfigLocalService}.
 *
 * @author khoavd
 * @see ServerConfigLocalService
 * @generated
 */
@ProviderType
public class ServerConfigLocalServiceWrapper implements ServerConfigLocalService,
	ServiceWrapper<ServerConfigLocalService> {
	public ServerConfigLocalServiceWrapper(
		ServerConfigLocalService serverConfigLocalService) {
		_serverConfigLocalService = serverConfigLocalService;
	}

	/**
	* Adds the server config to the database. Also notifies the appropriate model listeners.
	*
	* @param serverConfig the server config
	* @return the server config that was added
	*/
	@Override
	public org.opencps.communication.model.ServerConfig addServerConfig(
		org.opencps.communication.model.ServerConfig serverConfig) {
		return _serverConfigLocalService.addServerConfig(serverConfig);
	}

	@Override
	public org.opencps.communication.model.ServerConfig adminProcessData(
		com.liferay.portal.kernel.json.JSONObject objectData) {
		return _serverConfigLocalService.adminProcessData(objectData);
	}

	@Override
	public org.opencps.communication.model.ServerConfig adminProcessDelete(
		Long id) {
		return _serverConfigLocalService.adminProcessDelete(id);
	}

	/**
	* Creates a new server config with the primary key. Does not add the server config to the database.
	*
	* @param serverConfigId the primary key for the new server config
	* @return the new server config
	*/
	@Override
	public org.opencps.communication.model.ServerConfig createServerConfig(
		long serverConfigId) {
		return _serverConfigLocalService.createServerConfig(serverConfigId);
	}

	@Override
	public void deleteByGroupId(long groupId, long userId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {
		_serverConfigLocalService.deleteByGroupId(groupId, userId,
			serviceContext);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _serverConfigLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the server config with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param serverConfigId the primary key of the server config
	* @return the server config that was removed
	* @throws PortalException if a server config with the primary key could not be found
	*/
	@Override
	public org.opencps.communication.model.ServerConfig deleteServerConfig(
		long serverConfigId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _serverConfigLocalService.deleteServerConfig(serverConfigId);
	}

	/**
	* Deletes the server config from the database. Also notifies the appropriate model listeners.
	*
	* @param serverConfig the server config
	* @return the server config that was removed
	*/
	@Override
	public org.opencps.communication.model.ServerConfig deleteServerConfig(
		org.opencps.communication.model.ServerConfig serverConfig) {
		return _serverConfigLocalService.deleteServerConfig(serverConfig);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _serverConfigLocalService.dynamicQuery();
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
		return _serverConfigLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.communication.model.impl.ServerConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _serverConfigLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.communication.model.impl.ServerConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _serverConfigLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _serverConfigLocalService.dynamicQueryCount(dynamicQuery);
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
		return _serverConfigLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.communication.model.ServerConfig fetchServerConfig(
		long serverConfigId) {
		return _serverConfigLocalService.fetchServerConfig(serverConfigId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _serverConfigLocalService.getActionableDynamicQuery();
	}

	@Override
	public org.opencps.communication.model.ServerConfig getByCode(
		long groupId, String serverNo) {
		return _serverConfigLocalService.getByCode(groupId, serverNo);
	}

	@Override
	public org.opencps.communication.model.ServerConfig getByCode(
		String serverNo) {
		return _serverConfigLocalService.getByCode(serverNo);
	}

	@Override
	public java.util.List<org.opencps.communication.model.ServerConfig> getByProtocol(
		long groupId, String protocol) {
		return _serverConfigLocalService.getByProtocol(groupId, protocol);
	}

	@Override
	public java.util.List<org.opencps.communication.model.ServerConfig> getByProtocol(
		String protocol) {
		return _serverConfigLocalService.getByProtocol(protocol);
	}

	@Override
	public java.util.List<org.opencps.communication.model.ServerConfig> getByServerAndProtocol(
		String serverNo, String protocol) {
		return _serverConfigLocalService.getByServerAndProtocol(serverNo,
			protocol);
	}

	@Override
	public org.opencps.communication.model.ServerConfig getByServerNO_PROTOCOL(
		String serverNo, String protocol, long groupId) {
		return _serverConfigLocalService.getByServerNO_PROTOCOL(serverNo,
			protocol, groupId);
	}

	@Override
	public org.opencps.communication.model.ServerConfig getByServerNoAndProtocol(
		long groupId, String serverNo, String protocol) {
		return _serverConfigLocalService.getByServerNoAndProtocol(groupId,
			serverNo, protocol);
	}

	@Override
	public org.opencps.communication.model.ServerConfig getByServerNoAndProtocol(
		long groupId, String govAgencyCode, String serverNo, String protocol) {
		return _serverConfigLocalService.getByServerNoAndProtocol(groupId,
			govAgencyCode, serverNo, protocol);
	}

	@Override
	public java.util.List<org.opencps.communication.model.ServerConfig> getGroupId(
		long groupId) {
		return _serverConfigLocalService.getGroupId(groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _serverConfigLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _serverConfigLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _serverConfigLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the server config with the primary key.
	*
	* @param serverConfigId the primary key of the server config
	* @return the server config
	* @throws PortalException if a server config with the primary key could not be found
	*/
	@Override
	public org.opencps.communication.model.ServerConfig getServerConfig(
		long serverConfigId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _serverConfigLocalService.getServerConfig(serverConfigId);
	}

	/**
	* Returns a range of all the server configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.communication.model.impl.ServerConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of server configs
	* @param end the upper bound of the range of server configs (not inclusive)
	* @return the range of server configs
	*/
	@Override
	public java.util.List<org.opencps.communication.model.ServerConfig> getServerConfigs(
		int start, int end) {
		return _serverConfigLocalService.getServerConfigs(start, end);
	}

	/**
	* Returns the number of server configs.
	*
	* @return the number of server configs
	*/
	@Override
	public int getServerConfigsCount() {
		return _serverConfigLocalService.getServerConfigsCount();
	}

	@Override
	public void removeAllServer() {
		_serverConfigLocalService.removeAllServer();
	}

	@Override
	public org.opencps.communication.model.ServerConfig updateLastSync(
		long serverConfigId, java.util.Date lastSync,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _serverConfigLocalService.updateLastSync(serverConfigId,
			lastSync, context);
	}

	@Override
	public org.opencps.communication.model.ServerConfig updateServerConfig(
		long groupId, long serverConfigId, String govAgencyCode,
		String serverNo, String serverName, String protocol, String configs,
		java.util.Date lastSync,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _serverConfigLocalService.updateServerConfig(groupId,
			serverConfigId, govAgencyCode, serverNo, serverName, protocol,
			configs, lastSync, context);
	}

	/**
	* Updates the server config in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param serverConfig the server config
	* @return the server config that was updated
	*/
	@Override
	public org.opencps.communication.model.ServerConfig updateServerConfig(
		org.opencps.communication.model.ServerConfig serverConfig) {
		return _serverConfigLocalService.updateServerConfig(serverConfig);
	}

	@Override
	public org.opencps.communication.model.ServerConfig updateServerConfigDB(
		long groupId, String govAgencyCode, String serverNo, String serverName,
		String protocol, String configs, java.util.Date lastSync,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _serverConfigLocalService.updateServerConfigDB(groupId,
			govAgencyCode, serverNo, serverName, protocol, configs, lastSync,
			context);
	}

	@Override
	public ServerConfigLocalService getWrappedService() {
		return _serverConfigLocalService;
	}

	@Override
	public void setWrappedService(
		ServerConfigLocalService serverConfigLocalService) {
		_serverConfigLocalService = serverConfigLocalService;
	}

	private ServerConfigLocalService _serverConfigLocalService;
}