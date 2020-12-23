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

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.List;

import org.opencps.auth.api.exception.NotFoundException;
import org.opencps.communication.constants.ServerConfigTerm;
import org.opencps.communication.exception.ServerNoDuplicateException;
import org.opencps.communication.exception.ServerNoException;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.base.ServerConfigLocalServiceBaseImpl;

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
	 * org.opencps.communication.service.ServerConfigLocalServiceUtil} to access the
	 * server config local service.
	 */

	private static Log _log = LogFactoryUtil.getLog(ServerConfigLocalServiceImpl.class);

	public ServerConfig updateLastSync(long serverConfigId, Date lastSync, ServiceContext context)
			throws PortalException {

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

	public ServerConfig updateServerConfig(long groupId, long serverConfigId, String govAgencyCode, String serverNo,
			String serverName, String protocol, String configs, Date lastSync, ServiceContext context)
			throws PortalException {

//		System.out.println("ServerConfigLocalServiceImpl.updateServerConfig()" + context);
		validateAdd(groupId, serverConfigId, serverNo, serverName, protocol, configs, lastSync);
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

			serverConfig.setGovAgencyCode(govAgencyCode);
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

	private void validateAdd(long groupId, long serverConfigId, String serverNo, String serverName, String protocol,
			String configs, Date lastSync) throws PortalException {
		if (Validator.isNull(serverName)) {
			throw new ServerNoException(ServerConfigTerm.SERVER_NAME_IS_NULL_EX_MES);
		}

		if (Validator.isNull(serverNo)) {
			throw new ServerNoException(ServerConfigTerm.SERVER_NAME_IS_NULL_EX_MES);
		}

		if (serverConfigId == 0) {

			ServerConfig serverConfigNo = serverConfigPersistence.fetchByG_CF_CD(groupId, serverNo);

			if (Validator.isNotNull(serverConfigNo)) {
				throw new ServerNoDuplicateException(ServerConfigTerm.SERVER_NO_DUPLICATE_EX_MES);
			}
			// ServerConfig serverConfigName =
			// serverConfigPersistence.fetchByCF_NM(serverName);
			//
			// if (Validator.isNotNull(serverConfigName)) {
			// throw new ServerNameDuplicateException("ServerNameDuplicateException");
			// }
		} else {

			ServerConfig oldSC = serverConfigPersistence.fetchByPrimaryKey(serverConfigId);

			if (Validator.isNull(oldSC)) {
				throw new NotFoundException(ServerConfigTerm.SERVER_CONFIG_NOT_FOUND_EX_MES);
			} else {

				ServerConfig scByNo = serverConfigPersistence.fetchByG_CF_CD(groupId, serverNo);

				if (Validator.isNotNull(scByNo) && (scByNo.getPrimaryKey() != serverConfigId)) {
					throw new ServerNoDuplicateException("ServerNoDuplicateException");
				}

				// ServerConfig scByName = serverConfigPersistence.fetchByCF_NM(serverName);

				// if (Validator.isNotNull(scByName) && scByName.getPrimaryKey() !=
				// serverConfigId) {
				// throw new ServerNameDuplicateException("ServerNameDuplicateException");
				// }
			}
		}

		// TODO add more business logic in here

	}

	public ServerConfig getByCode(String serverNo) {
		return serverConfigPersistence.fetchByCF_CD(serverNo);
	}

	public List<ServerConfig> getGroupId(long groupId) {
		try {
			return serverConfigPersistence.findByCF_GID(groupId);
		} catch (Exception e) {
			_log.debug(e);
			// _log.error(e);
			return null;
		}

	}

	// LamTV_Remove all record
	public void removeAllServer() {
		serverConfigPersistence.removeAll();
	}

	// LamTV_Remove all record by groupId
	public void deleteByGroupId(long groupId, long userId, ServiceContext serviceContext) {
		serverConfigPersistence.removeByCF_GID(groupId);
	}

	public ServerConfig getByCode(long groupId, String serverNo) {
		return serverConfigPersistence.fetchByG_CF_CD(groupId, serverNo);
	}

	public List<ServerConfig> getByProtocol(long groupId, String protocol) {
		return serverConfigPersistence.findByG_P(groupId, protocol);
	}

	public ServerConfig getByServerNoAndProtocol(long groupId, String serverNo, String protocol) {
		return serverConfigPersistence.fetchByF_G_S_P(groupId, serverNo, protocol);
	}

	// super_admin Generators
	@Indexable(type = IndexableType.DELETE)
	public ServerConfig adminProcessDelete(Long id) {

		ServerConfig object = serverConfigPersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(object)) {
			return null;
		} else {
			serverConfigPersistence.remove(object);
		}

		return object;
	}

	@Indexable(type = IndexableType.REINDEX)
	public ServerConfig adminProcessData(JSONObject objectData) {

		ServerConfig object = null;

		if (objectData.getLong(ServerConfigTerm.SERVER_CONFIG_ID) > 0) {

			object = serverConfigPersistence.fetchByPrimaryKey(objectData.getLong(ServerConfigTerm.SERVER_CONFIG_ID));

			object.setModifiedDate(new Date());

		} else {

			long id = CounterLocalServiceUtil.increment(ServerConfig.class.getName());

			object = serverConfigPersistence.create(id);

			object.setGroupId(objectData.getLong(Field.GROUP_ID));
			object.setCompanyId(objectData.getLong(ServerConfigTerm.COMPANY_ID));
			object.setCreateDate(new Date());

		}

		object.setUserId(objectData.getLong(ServerConfigTerm.USER_ID));

		object.setGovAgencyCode(objectData.getString(ServerConfigTerm.GOV_AGENCY_CODE));
		object.setServerNo(objectData.getString(ServerConfigTerm.SERVER_NO));
		object.setServerName(objectData.getString(ServerConfigTerm.SERVER_NAME));
		object.setProtocol(objectData.getString(ServerConfigTerm.PROTOCOL));
		object.setConfigs(objectData.getString(ServerConfigTerm.CONFIGS));
		object.setLastSync(new Date(objectData.getLong(ServerConfigTerm.LAST_SYNC)));

		serverConfigPersistence.update(object);

		return object;
	}
	//Update DB
	public ServerConfig updateServerConfigDB(long groupId, String govAgencyCode, String serverNo, String serverName,
			String protocol, String configs, Date lastSync, ServiceContext context) throws PortalException {

		Date now = new Date();
		long userId = context.getUserId();
		User auditUser = userPersistence.fetchByPrimaryKey(userId);

		ServerConfig serverConfig = serverConfigPersistence.fetchByG_CF_CD(groupId, serverNo);

		if (serverConfig == null) {

			long serverConfigId = counterLocalService.increment(ServerConfig.class.getName());
			serverConfig = serverConfigPersistence.create(serverConfigId);

			serverConfig.setCreateDate(now);
			serverConfig.setModifiedDate(now);
			serverConfig.setCompanyId(context.getCompanyId());
			serverConfig.setGroupId(groupId);
			serverConfig.setUserId(userId);
			serverConfig.setUserName(auditUser.getFullName());

			serverConfig.setGovAgencyCode(govAgencyCode);
			serverConfig.setServerNo(serverNo);
			serverConfig.setServerName(serverName);
			serverConfig.setProtocol(protocol);
			serverConfig.setConfigs(configs);
			// serverConfig.setLastSync(lastSync);

		} else {

			serverConfig.setUserId(userId);
			serverConfig.setUserName(auditUser.getFullName());
			serverConfig.setModifiedDate(now);

			serverConfig.setServerNo(serverNo);
			serverConfig.setServerName(serverName);
			serverConfig.setProtocol(protocol);
			serverConfig.setConfigs(configs);
		}
		
		return serverConfigPersistence.update(serverConfig);

	}

	public List<ServerConfig> getByProtocol(String protocol) {
		return serverConfigPersistence.findByP(protocol);
	}

	public List<ServerConfig> getByServerAndProtocol(String serverNo, String protocol) {
		return serverConfigPersistence.findBySNO_PT(serverNo, protocol);
	}
	
	public ServerConfig getByServerNoAndProtocol(long groupId, String govAgencyCode, String serverNo, String protocol) {
		
		ServerConfig serverConfig = null;
		
		if(Validator.isNotNull(govAgencyCode)) {
			
			try {
				serverConfig = serverConfigPersistence.fetchByF_G_S_P(groupId, govAgencyCode, protocol);
				
				
			}catch(Exception e) {
				
			}
			
		}
		
		if(Validator.isNull(govAgencyCode) || Validator.isNull(serverConfig)) {
			
			try {
				serverConfig = serverConfigPersistence.fetchByF_G_S_P(groupId, serverNo, protocol);
			}catch(Exception e) {
				
			}
			
		}
		
		if(Validator.isNull(serverConfig)) {
			
			try {
				
				List<ServerConfig> serverConfigs = serverConfigPersistence.findByG_P(groupId, protocol);
				
				if(serverConfigs.size() > 0) {
					serverConfig = serverConfigs.get(0);
				}
			}catch(Exception e) {
				
			}
		}
		if(Validator.isNull(serverConfig)) {
			
			try {
				serverConfig = serverConfigPersistence.fetchByCF_CD(serverNo);
			}catch(Exception e) {
				
			}
		}
		
		return serverConfig;
	}

}