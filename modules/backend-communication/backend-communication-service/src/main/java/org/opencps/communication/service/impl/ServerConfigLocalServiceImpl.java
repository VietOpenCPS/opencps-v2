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

package org.opencps.communication.service.impl;

import java.util.Date;
import java.util.List;

import org.opencps.auth.api.exception.NotFoundException;
import org.opencps.communication.exception.ServerNameDuplicateException;
import org.opencps.communication.exception.ServerNoDuplicateException;
import org.opencps.communication.exception.ServerNoException;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.base.ServerConfigLocalServiceBaseImpl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the server config local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.communication.service.ServerConfigLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author khoavd
 * @see ServerConfigLocalServiceBaseImpl
 * @see org.opencps.communication.service.ServerConfigLocalServiceUtil
 */
@ProviderType
public class ServerConfigLocalServiceImpl extends ServerConfigLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.communication.service.ServerConfigLocalServiceUtil} to access
	 * the server config local service.
	 */
	
	public ServerConfig updateLastSync(long serverConfigId, Date lastSync, ServiceContext context) throws PortalException {
		
		ServerConfig serverConfig = serverConfigPersistence.fetchByPrimaryKey(serverConfigId);
		
		Date now = new Date();
		long userId = context.getUserId();
		
		User auditUser = userPersistence.fetchByPrimaryKey(userId);

		serverConfig.setUserId(userId);
		serverConfig.setUserName(auditUser.getFullName());
		serverConfig.setModifiedDate(now);

		serverConfig.setLastSync(lastSync);
		
		serverConfigPersistence.update(serverConfig);
		
		return serverConfig;
	}

	public ServerConfig updateServerConfig(long groupId, long serverConfigId, String serverNo, String serverName,
			String protocol, String configs, Date lastSync, ServiceContext context) throws PortalException {

		validateAdd(serverConfigId, serverNo, serverName, protocol, configs, lastSync);

		Date now = new Date();
		long userId = context.getUserId();
		User auditUser = userPersistence.fetchByPrimaryKey(userId);

		ServerConfig serverConfig = null;

		if (serverConfigId == 0) {

			serverConfigId = counterLocalService.increment(ServerConfig.class.getName());

			serverConfig = serverConfigPersistence.create(serverConfigId);

			serverConfig.setCreateDate(now);
			serverConfig.setModifiedDate(now);
			serverConfig.setCompanyId(context.getCompanyId());
			serverConfig.setGroupId(groupId);
			serverConfig.setUserId(userId);
			serverConfig.setUserName(auditUser.getFullName());

			serverConfig.setServerNo(serverNo);
			serverConfig.setServerName(serverName);
			serverConfig.setProtocol(protocol);
			serverConfig.setConfigs(configs);
			// serverConfig.setLastSync(lastSync);

		} else {
			serverConfig = serverConfigPersistence.fetchByPrimaryKey(serverConfigId);

			serverConfig.setUserId(userId);
			serverConfig.setUserName(auditUser.getFullName());
			serverConfig.setModifiedDate(now);

			serverConfig.setServerNo(serverNo);
			serverConfig.setServerName(serverName);
			serverConfig.setProtocol(protocol);
			serverConfig.setConfigs(configs);
		}
		
		serverConfigPersistence.update(serverConfig);
		
		return serverConfig;

	}

	private void validateAdd(long serverConfigId, String serverNo, String serverName, String protocol,
			String configs, Date lastSync) throws PortalException {
		if (Validator.isNull(serverName)) {
			throw new ServerNoException("ServerNameIsNull");
		}

		if (Validator.isNull(serverNo)) {
			throw new ServerNoException("ServerNoIsNull");
		}

		if (serverConfigId == 0) {

			ServerConfig serverConfigNo = serverConfigPersistence.fetchByCF_CD(serverNo);

			if (Validator.isNotNull(serverConfigNo)) {
				throw new ServerNoDuplicateException("ServerNoDuplicateException");
			}
			ServerConfig serverConfigName = serverConfigPersistence.fetchByCF_NM(serverName);

			if (Validator.isNotNull(serverConfigName)) {
				throw new ServerNameDuplicateException("ServerNameDuplicateException");
			}
		} else {

			ServerConfig oldSC = serverConfigPersistence.fetchByPrimaryKey(serverConfigId);

			if (Validator.isNull(oldSC)) {
				throw new NotFoundException("ServerConfigNotFoundException");
			} else {

				ServerConfig scByNo = serverConfigPersistence.fetchByCF_CD(serverNo);

				if (Validator.isNotNull(scByNo) && (scByNo.getPrimaryKey() != serverConfigId)) {
					throw new ServerNoDuplicateException("ServerNoDuplicateException");
				}

				ServerConfig scByName = serverConfigPersistence.fetchByCF_NM(serverName);

				if (Validator.isNotNull(scByName) && scByName.getPrimaryKey() != serverConfigId) {
					throw new ServerNameDuplicateException("ServerNameDuplicateException");
				}
			}
		}

		// TODO add more business logic in here

	}
	
	public ServerConfig getByCode(String serverNo) {
		return serverConfigPersistence.fetchByCF_CD(serverNo);
	}
	
	public List<ServerConfig> getGroupId(long groupId) {
		return serverConfigPersistence.findByCF_GID(groupId);
	}

}