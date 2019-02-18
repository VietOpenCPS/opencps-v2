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

package org.opencps.dossiermgt.service.impl;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.cache.thread.local.ThreadLocalCachable;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.BooleanQueryFactoryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.IndexSearcherHelperUtil;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.generic.MultiMatchQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.datamgt.constants.DataMGTConstants;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.utils.DictCollectionUtils;
import org.opencps.dossiermgt.constants.ServiceConfigTerm;
import org.opencps.dossiermgt.exception.HasExsistException;
import org.opencps.dossiermgt.exception.RequiredAgencyCodeException;
import org.opencps.dossiermgt.exception.RequiredServiceCodeException;
import org.opencps.dossiermgt.exception.ServiceLevelException;
import org.opencps.dossiermgt.exception.ServiceURLOnlineException;
import org.opencps.dossiermgt.model.ProcessOption;
import org.opencps.dossiermgt.model.ServiceConfig;
import org.opencps.dossiermgt.model.ServiceInfo;
import org.opencps.dossiermgt.model.impl.ServiceConfigImpl;
import org.opencps.dossiermgt.service.base.ServiceConfigLocalServiceBaseImpl;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the service config local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.dossiermgt.service.ServiceConfigLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see ServiceConfigLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.ServiceConfigLocalServiceUtil
 */
@ProviderType
public class ServiceConfigLocalServiceImpl extends ServiceConfigLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.dossiermgt.service.ServiceConfigLocalServiceUtil} to access the
	 * service config local service.
	 */
	private Log _log = LogFactoryUtil.getLog(ServiceConfigLocalServiceImpl.class);

	@Indexable(type = IndexableType.DELETE)
	public ServiceConfig removeServiceConfigById(long serviceConfigId) throws PortalException {

		validateRemove(serviceConfigId);

		List<ProcessOption> processOptions = processOptionPersistence.findBySC_ID(serviceConfigId);

		for (ProcessOption po : processOptions) {
			processOptionLocalService.removeProcessOption(po.getPrimaryKey());
		}

		ServiceConfig config = serviceConfigPersistence.findByPrimaryKey(serviceConfigId);

		serviceConfigPersistence.remove(config);

		return config;
	}

	public List<ServiceConfig> getByGroupId(long groupId) throws PortalException, SystemException {

		return serviceConfigPersistence.findByG_(groupId);

	}

	@ThreadLocalCachable
	public ServiceConfig getBySICodeAndGAC(long groupId, String serviceInfoCode, String govAgencyCode)
			throws PortalException {

		ServiceInfo info = serviceInfoPersistence.findBySC_GI(serviceInfoCode, groupId);

		return serviceConfigPersistence.fetchByGID_SI_GAC(groupId, info.getServiceInfoId(), govAgencyCode);
	}

	private void validateRemove(long serviceConfigId) throws PortalException {
		// TODO: add more business logic
	}

	@Indexable(type = IndexableType.REINDEX)
	public ServiceConfig updateServiceConfig(long serviceConfigId, long userId, long groupId, long serviceInfoId,
			String govAgencyCode, String serviceInstruction, int serviceLevel, String serviceUrl, boolean forCitizen,
			boolean forBusiness, boolean postalService, boolean registration, ServiceContext context)
			throws PortalException {

		JSONObject objName = JSONFactoryUtil.createJSONObject();

		validate(groupId, serviceConfigId, serviceInfoId, govAgencyCode, serviceLevel, serviceUrl, objName);

		ServiceConfig serviceConfig = null;

		Date now = new Date();

		User auditUser = userPersistence.fetchByPrimaryKey(userId);

		if (serviceConfigId == 0) {

			serviceConfigId = counterLocalService.increment(ServiceConfig.class.getName());

			serviceConfig = serviceConfigPersistence.create(serviceConfigId);

			serviceConfig.setCreateDate(now);
			serviceConfig.setModifiedDate(now);
			serviceConfig.setCompanyId(context.getCompanyId());
			serviceConfig.setGroupId(groupId);
			serviceConfig.setUserId(userId);
			serviceConfig.setUserName(auditUser.getFullName());

			serviceConfig.setGovAgencyCode(govAgencyCode);
			serviceConfig.setGovAgencyName(objName.getString("agencyName"));
			serviceConfig.setServiceInstruction(serviceInstruction);
			serviceConfig.setServiceLevel(serviceLevel);
			serviceConfig.setServiceUrl(serviceUrl);
			serviceConfig.setForBusiness(forBusiness);
			serviceConfig.setForCitizen(forCitizen);
			serviceConfig.setPostService(postalService);
			serviceConfig.setRegistration(registration);
			serviceConfig.setServiceInfoId(serviceInfoId);

		} else {

			serviceConfig = serviceConfigPersistence.fetchByPrimaryKey(serviceConfigId);

			serviceConfig.setUserId(userId);
			serviceConfig.setUserName(auditUser.getFullName());
			serviceConfig.setModifiedDate(now);

			if (Validator.isNotNull(govAgencyCode)) {
				serviceConfig.setGovAgencyCode(govAgencyCode);
				serviceConfig.setGovAgencyName(objName.getString("agencyName"));
			}

			if (Validator.isNotNull(serviceInstruction))
				serviceConfig.setServiceInstruction(serviceInstruction);

			serviceConfig.setServiceLevel(serviceLevel);
			serviceConfig.setServiceUrl(serviceUrl);

			serviceConfig.setForBusiness(forBusiness);
			serviceConfig.setForCitizen(forCitizen);
			serviceConfig.setPostService(postalService);
			serviceConfig.setRegistration(registration);

		}

		serviceConfig.setServiceInfoId(serviceInfoId);

		ServiceInfo si = serviceInfoPersistence.fetchByPrimaryKey(serviceInfoId);

		si.setMaxLevel(serviceLevel);

		serviceInfoPersistence.update(si);

		serviceConfigPersistence.update(serviceConfig);

		return serviceConfig;
	}

	private void validate(long groupId, long serviceConfigId, long serviceInfoId, String govAgencyCode,
			int serviceLevel, String serviceUrl, JSONObject objName) throws PortalException {

		DictItem agc = DictCollectionUtils.getDictItemByCode(DataMGTConstants.GOVERNMENT_AGENCY, govAgencyCode,
				groupId);

		if (Validator.isNull(agc)) {
			throw new RequiredAgencyCodeException("RequiredAgencyCodeException");
		} else {
			objName.put("agencyName", agc.getItemName());
		}

		if (serviceLevel <= 1 || serviceLevel > 4) {
			throw new ServiceLevelException();
		}

		if (Validator.isNotNull(serviceUrl) && !Validator.isUrl(serviceUrl)) {
			throw new ServiceURLOnlineException("ServiceURLOnlineException");
		}

		try {
			serviceInfoPersistence.fetchByPrimaryKey(serviceInfoId);

		} catch (Exception e) {
			_log.error(e);
			throw new RequiredServiceCodeException("RequiredServiceCodeException");
		}

		ServiceConfig config = serviceConfigPersistence.fetchByGID_SI_GAC(groupId, serviceInfoId, govAgencyCode);

		if (serviceConfigId == 0) {

			if (Validator.isNotNull(config)) {
				throw new HasExsistException("ServiceConfigHasExsist");
			}
		} else {

			if (Validator.isNotNull(config) && config.getPrimaryKey() != serviceConfigId) {
				throw new HasExsistException("ServiceConfigHasExsist");
			}
		}

	}

	public Hits searchLucene(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {

		String keywords = (String) params.get(Field.KEYWORD_SEARCH);
		String groupId = (String) params.get(Field.GROUP_ID);

		Indexer<ServiceConfig> indexer = IndexerRegistryUtil.nullSafeGetIndexer(ServiceConfig.class);

		searchContext.addFullQueryEntryClassName(CLASS_NAME);
		searchContext.setEntryClassNames(new String[] { CLASS_NAME });
		searchContext.setAttribute("paginationType", "regular");
		searchContext.setLike(true);
		searchContext.setStart(start);
		searchContext.setEnd(end);
		searchContext.setAndSearch(true);
		searchContext.setSorts(sorts);

		BooleanQuery booleanQuery = null;

		if (Validator.isNotNull(keywords)) {
			booleanQuery = BooleanQueryFactoryUtil.create(searchContext);
		} else {
			booleanQuery = indexer.getFullQuery(searchContext);
		}

		if (Validator.isNotNull(keywords)) {

			String[] keyword = keywords.split(StringPool.SPACE);

			for (String string : keyword) {

				MultiMatchQuery query = new MultiMatchQuery(string);

				query.addFields(new String[] { ServiceConfigTerm.SERVICE_NAME, ServiceConfigTerm.GOVAGENCY_NAME,
						ServiceConfigTerm.DOMAIN_NAME });

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(Field.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		// Extra fields
		String level = GetterUtil.getString(params.get(ServiceConfigTerm.SERVICE_LEVEL));
		String agency = GetterUtil.getString(params.get(ServiceConfigTerm.GOVAGENCY_CODE));
		String service = String.valueOf((params.get(ServiceConfigTerm.SERVICE_CODE)));
		String domain = String.valueOf((params.get(ServiceConfigTerm.DOMAIN_CODE)));

		String applicant = String.valueOf((params.get(ServiceConfigTerm.APPICATION_TYPE)));

		if (Validator.isNotNull(level)) {
			MultiMatchQuery query = new MultiMatchQuery(level);

			query.addFields(ServiceConfigTerm.SERVICE_LEVEL);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(agency)) {
			MultiMatchQuery query = new MultiMatchQuery(agency);

			query.addFields(ServiceConfigTerm.GOVAGENCY_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(service)) {
			MultiMatchQuery query = new MultiMatchQuery(service);

			query.addFields(ServiceConfigTerm.SERVICE_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(domain)) {
			MultiMatchQuery query = new MultiMatchQuery(domain);

			query.addFields(ServiceConfigTerm.DOMAIN_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (applicant.contentEquals(org.opencps.auth.api.keys.ActionKeys.APPLICANT_BUSINESS)) {
			MultiMatchQuery query = new MultiMatchQuery(Boolean.toString(true));

			query.addFields(ServiceConfigTerm.FOR_BUSINESS);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}

		if (applicant.contentEquals(org.opencps.auth.api.keys.ActionKeys.APPLICANT_CTZ)) {
			MultiMatchQuery query = new MultiMatchQuery(Boolean.toString(true));

			query.addFields(ServiceConfigTerm.FOR_CITIZEN);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);
	}

	public long countLucene(LinkedHashMap<String, Object> params, SearchContext searchContext)
			throws ParseException, SearchException {

		String keywords = (String) params.get(Field.KEYWORD_SEARCH);
		String groupId = (String) params.get(Field.GROUP_ID);

		Indexer<ServiceConfig> indexer = IndexerRegistryUtil.nullSafeGetIndexer(ServiceConfig.class);

		searchContext.addFullQueryEntryClassName(CLASS_NAME);
		searchContext.setEntryClassNames(new String[] { CLASS_NAME });
		searchContext.setAttribute("paginationType", "regular");
		searchContext.setLike(true);
		searchContext.setAndSearch(true);

		BooleanQuery booleanQuery = null;

		if (Validator.isNotNull(keywords)) {
			booleanQuery = BooleanQueryFactoryUtil.create(searchContext);
		} else {
			booleanQuery = indexer.getFullQuery(searchContext);
		}

		if (Validator.isNotNull(keywords)) {

			String[] keyword = keywords.split(StringPool.SPACE);

			for (String string : keyword) {

				MultiMatchQuery query = new MultiMatchQuery(string);

				query.addFields(new String[] { ServiceConfigTerm.SERVICE_NAME, ServiceConfigTerm.GOVAGENCY_NAME,
						ServiceConfigTerm.DOMAIN_NAME });

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(Field.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		// Extra fields
		String level = GetterUtil.getString(params.get(ServiceConfigTerm.SERVICE_LEVEL));
		String agency = GetterUtil.getString(params.get(ServiceConfigTerm.GOVAGENCY_CODE));
		String service = String.valueOf((params.get(ServiceConfigTerm.SERVICE_CODE)));
		String domain = String.valueOf((params.get(ServiceConfigTerm.DOMAIN_CODE)));

		String applicant = String.valueOf((params.get(ServiceConfigTerm.APPICATION_TYPE)));

		if (Validator.isNotNull(level)) {
			MultiMatchQuery query = new MultiMatchQuery(level);

			query.addFields(ServiceConfigTerm.SERVICE_LEVEL);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(agency)) {
			MultiMatchQuery query = new MultiMatchQuery(agency);

			query.addFields(ServiceConfigTerm.GOVAGENCY_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(service)) {
			MultiMatchQuery query = new MultiMatchQuery(service);

			query.addFields(ServiceConfigTerm.SERVICE_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(domain)) {
			MultiMatchQuery query = new MultiMatchQuery(domain);

			query.addFields(ServiceConfigTerm.DOMAIN_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (applicant.contentEquals(org.opencps.auth.api.keys.ActionKeys.APPLICANT_BUSINESS)) {
			MultiMatchQuery query = new MultiMatchQuery(Boolean.toString(true));

			query.addFields(ServiceConfigTerm.FOR_BUSINESS);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}

		if (applicant.contentEquals(org.opencps.auth.api.keys.ActionKeys.APPLICANT_CTZ)) {
			MultiMatchQuery query = new MultiMatchQuery(Boolean.toString(true));

			query.addFields(ServiceConfigTerm.FOR_CITIZEN);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);
	}

	// LamTV_ Process output ServiceConfig to DB
	@Indexable(type = IndexableType.REINDEX)
	public ServiceConfig updateServiceConfigDB(long userId, long groupId, long serviceInfoId, String govAgencyCode,
			String govAgencyName, String serviceInstruction, Integer serviceLevel, String serviceUrl,
			boolean forCitizen, boolean forBusiness, boolean postalService, boolean registration,
			ServiceContext context) {

		Date now = new Date();
		User auditUser = userPersistence.fetchByPrimaryKey(userId);

		long serviceConfigId = counterLocalService.increment(ServiceConfig.class.getName());
		ServiceConfig serviceConfig = serviceConfigPersistence.create(serviceConfigId);

		serviceConfig.setCreateDate(now);
		serviceConfig.setModifiedDate(now);
		serviceConfig.setCompanyId(context.getCompanyId());
		serviceConfig.setGroupId(groupId);
		serviceConfig.setUserId(userId);
		serviceConfig.setUserName(auditUser.getFullName());

		serviceConfig.setGovAgencyCode(govAgencyCode);
		serviceConfig.setGovAgencyName(govAgencyName);
		serviceConfig.setServiceInstruction(serviceInstruction);
		serviceConfig.setServiceLevel(serviceLevel);
		serviceConfig.setServiceUrl(serviceUrl);
		serviceConfig.setForBusiness(forBusiness);
		serviceConfig.setForCitizen(forCitizen);
		serviceConfig.setPostService(postalService);
		serviceConfig.setRegistration(registration);
		serviceConfig.setServiceInfoId(serviceInfoId);
		serviceConfig.setServiceLevel(serviceLevel);

		return serviceConfigPersistence.update(serviceConfig);
	}

	// LamTV_Process get list ServiceConfig by ServiceInfo
	public List<ServiceConfig> getByServiceInfo(long groupId, long serviceInfoId) {
		return serviceConfigPersistence.findByF_GID_SID(groupId, serviceInfoId);
	}

	public List<ServiceConfig> getByGovAgencyCode(String govAgencyCode) {
		return serviceConfigPersistence.findByF_GAC(govAgencyCode);
	}

	public List<ServiceConfig> getByLevel(long groupId, int level) {
		return serviceConfigPersistence.findByGID_LEVEL(groupId, level);
	}

	public long countByGovAgency(String keyword, String govAgencyCode, long groupId) {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ServiceConfigImpl.class);
		if (Validator.isNotNull(keyword)) {
			dynamicQuery.add(RestrictionsFactoryUtil.like(ServiceConfigTerm.GOVAGENCY_NAME, keyword));
		}
		dynamicQuery.add(RestrictionsFactoryUtil.eq(ServiceConfigTerm.GOVAGENCY_CODE, govAgencyCode));
		dynamicQuery.add(RestrictionsFactoryUtil.eq(Field.GROUP_ID, groupId));

		return serviceConfigPersistence.countWithDynamicQuery(dynamicQuery);
	}

	public List<ServiceConfig> searchByGovAgency(String keyword, String govAgencyCode, long groupId, int start,
			int end) {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ServiceConfigImpl.class);
		if (Validator.isNotNull(keyword)) {
			dynamicQuery.add(RestrictionsFactoryUtil.like(ServiceConfigTerm.GOVAGENCY_NAME, keyword));
		}
		dynamicQuery.add(RestrictionsFactoryUtil.eq(ServiceConfigTerm.GOVAGENCY_CODE, govAgencyCode));
		dynamicQuery.add(RestrictionsFactoryUtil.eq(Field.GROUP_ID, groupId));

		return serviceConfigPersistence.findWithDynamicQuery(dynamicQuery, start, end);
	}

	// super_admin Generators
	@Indexable(type = IndexableType.DELETE)
	public ServiceConfig adminProcessDelete(Long id) {

		ServiceConfig object = serviceConfigPersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(object)) {
			return null;
		} else {
			serviceConfigPersistence.remove(object);
		}

		return object;
	}

	@Indexable(type = IndexableType.REINDEX)
	public ServiceConfig adminProcessData(JSONObject objectData) {

		ServiceConfig object = null;

		if (objectData.getLong("serviceConfigId") > 0) {

			object = serviceConfigPersistence.fetchByPrimaryKey(objectData.getLong("serviceConfigId"));

			object.setModifiedDate(new Date());

		} else {

			long id = CounterLocalServiceUtil.increment(ServiceConfig.class.getName());

			object = serviceConfigPersistence.create(id);

			object.setGroupId(objectData.getLong("groupId"));
			object.setCompanyId(objectData.getLong("companyId"));
			object.setCreateDate(new Date());

		}

		object.setUserId(objectData.getLong("userId"));
		object.setUserName(objectData.getString("userName"));

		object.setGovAgencyCode(objectData.getString("govAgencyCode"));
		object.setGovAgencyName(objectData.getString("govAgencyName"));
		object.setServiceInstruction(objectData.getString("serviceInstruction"));
		object.setServiceLevel(objectData.getInt("serviceLevel"));
		object.setServiceUrl(objectData.getString("serviceUrl"));
		object.setForBusiness(objectData.getBoolean("forBusiness"));
		object.setForCitizen(objectData.getBoolean("forCitizen"));
		object.setPostService(objectData.getBoolean("postService"));
		object.setRegistration(objectData.getBoolean("registration"));
		object.setServiceInfoId(objectData.getLong("serviceInfoId"));
		object.setServiceLevel(objectData.getInt("serviceLevel"));

		serviceConfigPersistence.update(object);

		return object;
	}

	public static final String CLASS_NAME = ServiceConfig.class.getName();

}