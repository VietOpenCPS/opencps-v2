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

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.communication.model.ServerConfig;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

/**
 * Provides the local service interface for ServerConfig. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author khoavd
 * @see ServerConfigLocalServiceUtil
 * @see org.opencps.communication.service.base.ServerConfigLocalServiceBaseImpl
 * @see org.opencps.communication.service.impl.ServerConfigLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface ServerConfigLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ServerConfigLocalServiceUtil} to access the server config local service. Add custom service methods to {@link org.opencps.communication.service.impl.ServerConfigLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the server config to the database. Also notifies the appropriate model listeners.
	*
	* @param serverConfig the server config
	* @return the server config that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ServerConfig addServerConfig(ServerConfig serverConfig);

	@Indexable(type = IndexableType.REINDEX)
	public ServerConfig adminProcessData(JSONObject objectData);

	@Indexable(type = IndexableType.DELETE)
	public ServerConfig adminProcessDelete(Long id);

	/**
	* Creates a new server config with the primary key. Does not add the server config to the database.
	*
	* @param serverConfigId the primary key for the new server config
	* @return the new server config
	*/
	@Transactional(enabled = false)
	public ServerConfig createServerConfig(long serverConfigId);

	public void deleteByGroupId(long groupId, long userId,
		ServiceContext serviceContext);

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	/**
	* Deletes the server config with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param serverConfigId the primary key of the server config
	* @return the server config that was removed
	* @throws PortalException if a server config with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public ServerConfig deleteServerConfig(long serverConfigId)
		throws PortalException;

	/**
	* Deletes the server config from the database. Also notifies the appropriate model listeners.
	*
	* @param serverConfig the server config
	* @return the server config that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public ServerConfig deleteServerConfig(ServerConfig serverConfig);

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.communication.model.impl.ServerConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.communication.model.impl.ServerConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public ServerConfig fetchServerConfig(long serverConfigId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ServerConfig getByCode(long groupId, String serverNo);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ServerConfig getByCode(String serverNo);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ServerConfig> getByProtocol(long groupId, String protocol);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ServerConfig> getByProtocol(String protocol);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ServerConfig> getByServerAndProtocol(String serverNo,
		String protocol);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ServerConfig getByServerNO_PROTOCOL(String serverNo,
		String protocol, long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ServerConfig getByServerNoAndProtocol(long groupId, String serverNo,
		String protocol);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ServerConfig getByServerNoAndProtocol(long groupId,
		String govAgencyCode, String serverNo, String protocol);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ServerConfig> getGroupId(long groupId);

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

	/**
	* Returns the server config with the primary key.
	*
	* @param serverConfigId the primary key of the server config
	* @return the server config
	* @throws PortalException if a server config with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ServerConfig getServerConfig(long serverConfigId)
		throws PortalException;

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ServerConfig> getServerConfigs(int start, int end);

	/**
	* Returns the number of server configs.
	*
	* @return the number of server configs
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getServerConfigsCount();

	public void removeAllServer();

	public ServerConfig updateLastSync(long serverConfigId, Date lastSync,
		ServiceContext context) throws PortalException;

	public ServerConfig updateServerConfig(long groupId, long serverConfigId,
		String govAgencyCode, String serverNo, String serverName,
		String protocol, String configs, Date lastSync, ServiceContext context)
		throws PortalException;

	/**
	* Updates the server config in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param serverConfig the server config
	* @return the server config that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ServerConfig updateServerConfig(ServerConfig serverConfig);

	public ServerConfig updateServerConfigDB(long groupId,
		String govAgencyCode, String serverNo, String serverName,
		String protocol, String configs, Date lastSync, ServiceContext context)
		throws PortalException;
}