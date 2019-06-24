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
import com.liferay.portal.kernel.exception.PortalException;
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
import com.liferay.portal.kernel.search.WildcardQuery;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.search.generic.MultiMatchQuery;
import com.liferay.portal.kernel.search.generic.WildcardQueryImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.datamgt.constants.DataMGTConstants;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.utils.DictCollectionUtils;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.constants.ServiceInfoTerm;
import org.opencps.dossiermgt.exception.DataConflictException;
import org.opencps.dossiermgt.exception.DuplicateServiceCodeException;
import org.opencps.dossiermgt.exception.RequiredAdministrationCodeException;
import org.opencps.dossiermgt.exception.RequiredServiceCodeException;
import org.opencps.dossiermgt.exception.RequiredServiceNameException;
import org.opencps.dossiermgt.model.ServiceFileTemplate;
import org.opencps.dossiermgt.model.ServiceInfo;
import org.opencps.dossiermgt.service.base.ServiceInfoLocalServiceBaseImpl;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the service info local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.dossiermgt.service.ServiceInfoLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see ServiceInfoLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.ServiceInfoLocalServiceUtil
 */
@ProviderType
public class ServiceInfoLocalServiceImpl extends ServiceInfoLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.dossiermgt.service.ServiceInfoLocalServiceUtil} to access the
	 * service info local service.
	 */
	private Log _log = LogFactoryUtil.getLog(ServiceInfoLocalServiceImpl.class);

	@Indexable(type = IndexableType.DELETE)
	public ServiceInfo removeServiceInfo(long serviceInfoId) throws PortalException {
		ServiceInfo serviceInfo = serviceInfoPersistence.fetchByPrimaryKey(serviceInfoId);

		// Validate
		valdiateRemove(serviceInfoId);

		serviceInfoPersistence.remove(serviceInfo);

		List<ServiceFileTemplate> fileTemplates = serviceFileTemplateLocalService.getByServiceInfoId(serviceInfoId);

		for (ServiceFileTemplate fileTemplate : fileTemplates) {
			if (fileTemplate.getFileEntryId() != 0) {
				serviceFileTemplateLocalService.removeServiceFileTemplate(serviceInfoId,
						fileTemplate.getFileTemplateNo());
			}
		}

		return serviceInfo;
	}

	@ThreadLocalCachable
	public ServiceInfo getByCode(long groupId, String serviceCode) throws PortalException {
		return serviceInfoPersistence.fetchBySC_GI(serviceCode, groupId);
	}

	@Indexable(type = IndexableType.REINDEX)
	public ServiceInfo addServiceInfo(long userId, long groupId, String serviceCode, String serviceName,
			String processText, String methodText, String dossierText, String conditionText, String durationText,
			String applicantText, String resultText, String regularText, String feeText, String administrationCode,
			String domainCode, int maxLevel, boolean activeStatus, ServiceContext serviceContext)
			throws PortalException {

		User user = userLocalService.getUser(userId);

		Date now = new Date();

		// TODO: validate
		valdiate(serviceCode, serviceName, administrationCode, domainCode, groupId);

		long serviceInfoId = counterLocalService.increment(ServiceInfo.class.getName());

		ServiceInfo serviceInfo = serviceInfoPersistence.create(serviceInfoId);

		serviceInfo.setGroupId(groupId);
		serviceInfo.setCompanyId(user.getCompanyId());
		serviceInfo.setUserId(user.getUserId());
		serviceInfo.setUserName(user.getFullName());
		serviceInfo.setCreateDate(now);
		serviceInfo.setModifiedDate(now);

		serviceInfo.setServiceCode(serviceCode);
		serviceInfo.setServiceName(serviceName);
		serviceInfo.setProcessText(processText);
		serviceInfo.setMethodText(methodText);
		serviceInfo.setDossierText(dossierText);
		serviceInfo.setConditionText(conditionText);
		serviceInfo.setDurationText(durationText);
		serviceInfo.setApplicantText(applicantText);
		serviceInfo.setResultText(resultText);
		serviceInfo.setRegularText(regularText);
		serviceInfo.setFeeText(feeText);
		serviceInfo.setAdministrationCode(administrationCode);
		serviceInfo.setDomainCode(domainCode);
		serviceInfo.setMaxLevel(maxLevel);

		DictItem adm = DictCollectionUtils.getDictItemByCode(DataMGTConstants.ADMINTRATION_CODE, administrationCode,
				groupId);

		DictItem dom = DictCollectionUtils.getDictItemByCode(DataMGTConstants.SERVICE_DOMAIN, domainCode, groupId);

		if (Validator.isNotNull(adm)) {
			serviceInfo.setAdministrationName(adm.getItemName());
			serviceInfo.setAdministrationIndex(adm.getTreeIndex());
		}

		if (Validator.isNotNull(dom)) {
			serviceInfo.setDomainName(dom.getItemName());
			serviceInfo.setDomainIndex(dom.getTreeIndex());
		}

		serviceInfo.setPublic_(activeStatus);

		serviceInfoPersistence.update(serviceInfo);

		return serviceInfo;
	}

	public int countServiceInfosByGroupId(long groupId) {
		return serviceInfoPersistence.countByGroupId(groupId);
	}

	public List<ServiceInfo> getServiceInfosByGroupId(long groupId) {
		return serviceInfoPersistence.findByGroupId(groupId);
	}

	public List<ServiceInfo> getServiceInfosByGroupId(long groupId, int start, int end) {
		return serviceInfoPersistence.findByGroupId(groupId, start, end);
	}

	public List<ServiceInfo> getByF_GID_SC(long groupId, String[] serviceArr) {
		return serviceInfoPersistence.findByF_GID_SC(groupId, serviceArr);
	}

	@Indexable(type = IndexableType.REINDEX)
	public ServiceInfo updateServiceInfo(long groupId, long serviceInfoId, String serviceCode, String serviceName,
			String processText, String methodText, String dossierText, String conditionText, String durationText,
			String applicantText, String resultText, String regularText, String feeText, String administrationCode,
			String domainCode, int maxLevel, boolean activeStatus, ServiceContext serviceContext)
			throws PortalException {

		Date now = new Date();

		ServiceInfo serviceInfo = serviceInfoPersistence.findByPrimaryKey(serviceInfoId);

		// TODO: validate

		serviceInfo.setModifiedDate(now);

		if (Validator.isNotNull(serviceCode))
			serviceInfo.setServiceCode(serviceCode);

		if (Validator.isNotNull(serviceName))
			serviceInfo.setServiceName(serviceName);

		if (Validator.isNotNull(processText))
			serviceInfo.setProcessText(processText);

		if (Validator.isNotNull(methodText))
			serviceInfo.setMethodText(methodText);

		if (Validator.isNotNull(dossierText))
			serviceInfo.setDossierText(dossierText);

		if (Validator.isNotNull(conditionText))
			serviceInfo.setConditionText(conditionText);

		if (Validator.isNotNull(durationText))
			serviceInfo.setDurationText(durationText);

		if (Validator.isNotNull(applicantText))
			serviceInfo.setApplicantText(applicantText);

		if (Validator.isNotNull(resultText))
			serviceInfo.setResultText(resultText);

		if (Validator.isNotNull(regularText))
			serviceInfo.setRegularText(regularText);

		if (Validator.isNotNull(feeText))
			serviceInfo.setFeeText(feeText);

		if (Validator.isNotNull(administrationCode))
			serviceInfo.setAdministrationCode(administrationCode);

		if (Validator.isNotNull(domainCode))
			serviceInfo.setDomainCode(domainCode);

		if (Validator.isNotNull(maxLevel))
			serviceInfo.setMaxLevel(maxLevel);

		serviceInfo.setPublic_(activeStatus);

		DictItem adm = DictCollectionUtils.getDictItemByCode(DataMGTConstants.ADMINTRATION_CODE, administrationCode,
				groupId);
		DictItem dom = DictCollectionUtils.getDictItemByCode(DataMGTConstants.SERVICE_DOMAIN, domainCode, groupId);

		if (Validator.isNotNull(adm)) {
			serviceInfo.setAdministrationName(adm.getItemName());
			serviceInfo.setAdministrationIndex(adm.getTreeIndex());
		}

		if (Validator.isNotNull(dom)) {
			serviceInfo.setDomainName(dom.getItemName());
			serviceInfo.setDomainIndex(dom.getTreeIndex());
		}

		serviceInfoPersistence.update(serviceInfo);

		return serviceInfo;
	}

	private void valdiate(String serviceCode, String serviceName, String administrationCode, String domainCode,
			long groupId) throws PortalException {

		if (Validator.isNull(serviceCode)) {
			throw new RequiredServiceCodeException();
		}

		if (Validator.isNull(serviceName)) {
			throw new RequiredServiceNameException();
		}

		if (Validator.isNull(administrationCode)) {
			throw new RequiredAdministrationCodeException();
		}

		ServiceInfo si = null;

		try {
			si = serviceInfoPersistence.findBySC_GI(serviceCode, groupId);
		} catch (Exception e) {
			_log.error(e);
		}

		if (Validator.isNotNull(si)) {
			throw new DuplicateServiceCodeException();
		}
	}

	private void valdiateRemove(long serviceInfoId) throws PortalException {
		// TODO implement
	}

	public List<ServiceInfo> fetchByDomain(long groupId, String domainCode) {
		return serviceInfoPersistence.findByGI_DC(domainCode, groupId);
	}

	public Hits searchLucene(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {

		String keywords = (String) params.get(Field.KEYWORD_SEARCH);
		String groupId = (String) params.get(Field.GROUP_ID);

		Indexer<ServiceInfo> indexer = IndexerRegistryUtil.nullSafeGetIndexer(ServiceInfo.class);

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

		// if (Validator.isNotNull(keywords)) {
		//
		// String[] keyword = keywords.split(StringPool.SPACE);
		//
		// for (String string : keyword) {
		//
		// MultiMatchQuery query = new MultiMatchQuery(string);
		//
		// query.addFields(ServiceInfoTerm.SERVICE_NAME);
		//
		// booleanQuery.add(query, BooleanClauseOccur.MUST);
		//
		// }
		// }
		// LamTV: Process search LIKE
		// if (Validator.isNotNull(keywords)) {
		// String[] keywordArr = keywords.split(StringPool.SPACE);
		// BooleanQuery query = new BooleanQueryImpl();
		// for (String key : keywordArr) {
		// WildcardQuery wildQuery = new WildcardQueryImpl(DossierTerm.SERVICE_NAME,
		// key.toLowerCase() + StringPool.STAR);
		// query.add(wildQuery, BooleanClauseOccur.MUST);
		// }
		// booleanQuery.add(query, BooleanClauseOccur.MUST);
		// }
		//
		if (Validator.isNotNull(keywords)) {
			BooleanQuery queryBool = new BooleanQueryImpl();
			String[] subQuerieArr = new String[] { ServiceInfoTerm.SERVICE_NAME_SEARCH, ServiceInfoTerm.SERVICE_NAME,
					ServiceInfoTerm.SERVICE_CODE_SEARCH };

			String[] keywordArr = keywords.split(StringPool.SPACE);
			for (String fieldSearch : subQuerieArr) {
				BooleanQuery query = new BooleanQueryImpl();
				for (String key : keywordArr) {
					WildcardQuery wildQuery = new WildcardQueryImpl(fieldSearch,
							StringPool.STAR + key.toLowerCase() + StringPool.STAR);
					query.add(wildQuery, BooleanClauseOccur.MUST);
				}
				queryBool.add(query, BooleanClauseOccur.SHOULD);
			}
			booleanQuery.add(queryBool, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(Field.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		// Extra fields
		String administration = GetterUtil.getString(params.get(ServiceInfoTerm.ADMINISTRATION_CODE));
		String domain = GetterUtil.getString(params.get(ServiceInfoTerm.DOMAIN_CODE));
		String level = String.valueOf((params.get(ServiceInfoTerm.MAX_LEVEL)));
		String public_ = String.valueOf((params.get(ServiceInfoTerm.PUBLIC_)));

		if (Validator.isNotNull(administration)) {
			MultiMatchQuery query = new MultiMatchQuery(administration);

			query.addFields(ServiceInfoTerm.ADMINISTRATION_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(domain)) {
			MultiMatchQuery query = new MultiMatchQuery(domain);

			query.addFields(ServiceInfoTerm.DOMAIN_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		// if (!"0".equalsIgnoreCase(level) && Validator.isNotNull(level)) {
		// MultiMatchQuery query = new MultiMatchQuery(level);
		//
		// query.addFields(ServiceInfoTerm.MAX_LEVEL);
		//
		// booleanQuery.add(query, BooleanClauseOccur.MUST);
		// }
		if (!"0".equalsIgnoreCase(level) && Validator.isNotNull(level)) {
			String[] lstStatus = StringUtil.split(level);

			if (lstStatus != null && lstStatus.length > 0) {
				BooleanQuery subQuery = new BooleanQueryImpl();
				for (int i = 0; i < lstStatus.length; i++) {
					MultiMatchQuery query = new MultiMatchQuery(lstStatus[i]);
					query.addField(ServiceInfoTerm.MAX_LEVEL);
					subQuery.add(query, BooleanClauseOccur.SHOULD);
				}
				booleanQuery.add(subQuery, BooleanClauseOccur.MUST);
			} else {
				MultiMatchQuery query = new MultiMatchQuery(level);
				query.addFields(ServiceInfoTerm.MAX_LEVEL);
				booleanQuery.add(query, BooleanClauseOccur.MUST);
			}
		}

		if (Validator.isNotNull(public_)) {
			MultiMatchQuery query = new MultiMatchQuery(public_);

			query.addFields(ServiceInfoTerm.PUBLIC_);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);
	}

	public long countLucene(LinkedHashMap<String, Object> params, SearchContext searchContext)
			throws ParseException, SearchException {

		String keywords = (String) params.get(Field.KEYWORD_SEARCH);
		String groupId = (String) params.get(Field.GROUP_ID);

		Indexer<ServiceInfo> indexer = IndexerRegistryUtil.nullSafeGetIndexer(ServiceInfo.class);

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

		// if (Validator.isNotNull(keywords)) {
		//
		// String[] keyword = keywords.split(StringPool.SPACE);
		//
		// for (String string : keyword) {
		//
		// MultiMatchQuery query = new MultiMatchQuery(string);
		//
		// query.addFields(ServiceInfoTerm.SERVICE_NAME);
		//
		// booleanQuery.add(query, BooleanClauseOccur.MUST);
		//
		// }
		// }

		// LamTV: Process search LIKE
		// if (Validator.isNotNull(keywords)) {
		// String[] keywordArr = keywords.split(StringPool.SPACE);
		// BooleanQuery query = new BooleanQueryImpl();
		// for (String key : keywordArr) {
		// WildcardQuery wildQuery = new WildcardQueryImpl(DossierTerm.SERVICE_NAME,
		// key.toLowerCase() + StringPool.STAR);
		// query.add(wildQuery, BooleanClauseOccur.MUST);
		// }
		// booleanQuery.add(query, BooleanClauseOccur.MUST);
		// }
		//
		if (Validator.isNotNull(keywords)) {
			BooleanQuery queryBool = new BooleanQueryImpl();
			String[] subQuerieArr = new String[] { ServiceInfoTerm.SERVICE_NAME_SEARCH, ServiceInfoTerm.SERVICE_NAME,
					ServiceInfoTerm.SERVICE_CODE_SEARCH };

			String[] keywordArr = keywords.split(StringPool.SPACE);
			for (String fieldSearch : subQuerieArr) {
				BooleanQuery query = new BooleanQueryImpl();
				for (String key : keywordArr) {
					WildcardQuery wildQuery = new WildcardQueryImpl(fieldSearch,
							StringPool.STAR + key.toLowerCase() + StringPool.STAR);
					query.add(wildQuery, BooleanClauseOccur.MUST);
				}
				queryBool.add(query, BooleanClauseOccur.SHOULD);
			}
			booleanQuery.add(queryBool, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(Field.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		// Extra fields

		String administration = GetterUtil.getString(params.get(ServiceInfoTerm.ADMINISTRATION_CODE));
		String domain = GetterUtil.getString(params.get(ServiceInfoTerm.DOMAIN_CODE));
		String level = String.valueOf((params.get(ServiceInfoTerm.MAX_LEVEL)));
		String public_ = String.valueOf((params.get(ServiceInfoTerm.PUBLIC_)));

		if (Validator.isNotNull(administration)) {
			MultiMatchQuery query = new MultiMatchQuery(administration);

			query.addFields(ServiceInfoTerm.ADMINISTRATION_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(domain)) {
			MultiMatchQuery query = new MultiMatchQuery(domain);

			query.addFields(ServiceInfoTerm.DOMAIN_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		// if (!"0".equalsIgnoreCase(level) && Validator.isNotNull(level)) {
		// MultiMatchQuery query = new MultiMatchQuery(level);
		//
		// query.addFields(ServiceInfoTerm.MAX_LEVEL);
		//
		// booleanQuery.add(query, BooleanClauseOccur.MUST);
		// }

		if (!"0".equalsIgnoreCase(level) && Validator.isNotNull(level)) {
			String[] lstStatus = StringUtil.split(level);

			if (lstStatus != null && lstStatus.length > 0) {
				BooleanQuery subQuery = new BooleanQueryImpl();
				for (int i = 0; i < lstStatus.length; i++) {
					MultiMatchQuery query = new MultiMatchQuery(lstStatus[i]);
					query.addField(ServiceInfoTerm.MAX_LEVEL);
					subQuery.add(query, BooleanClauseOccur.SHOULD);
				}
				booleanQuery.add(subQuery, BooleanClauseOccur.MUST);
			} else {
				MultiMatchQuery query = new MultiMatchQuery(level);
				query.addFields(ServiceInfoTerm.MAX_LEVEL);
				booleanQuery.add(query, BooleanClauseOccur.MUST);
			}
		}

		if (Validator.isNotNull(public_)) {
			MultiMatchQuery query = new MultiMatchQuery(public_);

			query.addFields(ServiceInfoTerm.PUBLIC_);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);
	}

	// LamTV_ Process output ServiceInfo
	@Indexable(type = IndexableType.REINDEX)
	public ServiceInfo updateServiceInfoDB(long userId, long groupId, String serviceCode, String serviceName,
			String processText, String methodText, String dossierText, String conditionText, String durationText,
			String applicantText, String resultText, String regularText, String feeText, String administrationCode,
			String administrationName, String domainCode, String domainName, Integer maxLevel, boolean public_)
			throws PortalException {

		User user = userLocalService.getUser(userId);
		Date now = new Date();
		// valdiate(serviceCode, serviceName, administrationCode, domainCode, groupId);
		ServiceInfo serviceInfo = serviceInfoPersistence.fetchBySC_GI(serviceCode, groupId);
		if (serviceInfo != null) {
			serviceInfo.setModifiedDate(now);
			// Other field
			serviceInfo.setServiceCode(serviceCode);
			serviceInfo.setServiceName(serviceName);
			serviceInfo.setProcessText(processText);
			serviceInfo.setMethodText(methodText);
			serviceInfo.setDossierText(dossierText);
			serviceInfo.setConditionText(conditionText);
			serviceInfo.setDurationText(durationText);
			serviceInfo.setApplicantText(applicantText);
			serviceInfo.setResultText(resultText);
			serviceInfo.setRegularText(regularText);
			serviceInfo.setFeeText(feeText);
			serviceInfo.setAdministrationCode(administrationCode);
			serviceInfo.setAdministrationName(administrationName);
			serviceInfo.setDomainCode(domainCode);
			serviceInfo.setDomainName(domainName);
			serviceInfo.setMaxLevel(maxLevel);
			serviceInfo.setPublic_(public_);
		} else {
			long serviceInfoId = counterLocalService.increment(ServiceInfo.class.getName());

			serviceInfo = serviceInfoPersistence.create(serviceInfoId);

			serviceInfo.setGroupId(groupId);
			serviceInfo.setCompanyId(user.getCompanyId());
			serviceInfo.setUserId(user.getUserId());
			serviceInfo.setUserName(user.getFullName());
			serviceInfo.setCreateDate(now);
			serviceInfo.setModifiedDate(now);

			serviceInfo.setServiceCode(serviceCode);
			serviceInfo.setServiceName(serviceName);
			serviceInfo.setProcessText(processText);
			serviceInfo.setMethodText(methodText);
			serviceInfo.setDossierText(dossierText);
			serviceInfo.setConditionText(conditionText);
			serviceInfo.setDurationText(durationText);
			serviceInfo.setApplicantText(applicantText);
			serviceInfo.setResultText(resultText);
			serviceInfo.setRegularText(regularText);
			serviceInfo.setFeeText(feeText);
			serviceInfo.setAdministrationCode(administrationCode);
			serviceInfo.setAdministrationName(administrationName);
			serviceInfo.setDomainCode(domainCode);
			serviceInfo.setDomainName(domainName);
			serviceInfo.setMaxLevel(maxLevel);
			serviceInfo.setPublic_(public_);
		}

		DictItem adm = DictCollectionUtils.getDictItemByCode(DataMGTConstants.ADMINTRATION_CODE, administrationCode,
				groupId);
		DictItem dom = DictCollectionUtils.getDictItemByCode(DataMGTConstants.SERVICE_DOMAIN, domainCode, groupId);

		if (Validator.isNotNull(adm)) {
			serviceInfo.setAdministrationIndex(adm.getTreeIndex());
		}
		if (Validator.isNotNull(dom)) {
			serviceInfo.setDomainIndex(dom.getTreeIndex());
		}

		return serviceInfoPersistence.update(serviceInfo);

	}

	public List<ServiceInfo> findByGroup(long groupId) {
		return serviceInfoPersistence.findByGroupId(groupId);
	}

	public List<ServiceInfo> findByGroupAndPublic(long groupId, boolean public_) {
		return serviceInfoPersistence.findByGI_PUB(groupId, public_);
	}

	// super_admin Generators
	@Indexable(type = IndexableType.DELETE)
	public ServiceInfo adminProcessDelete(Long id) throws Exception {

		ServiceInfo object = serviceInfoPersistence.fetchByPrimaryKey(id);
		if (Validator.isNull(object)) {
			return null;
		} else {
			int countDossier = dossierLocalService.countByG_NOTS_O_SC(object.getGroupId(), new String[] { DossierTerm.DOSSIER_STATUS_DONE, DossierTerm.DOSSIER_STATUS_CANCELLED, DossierTerm.DOSSIER_STATUS_DENIED, DossierTerm.DOSSIER_STATUS_UNRESOLVED }, 1, object.getServiceCode());
			if (countDossier > 0) {
				throw new DataConflictException("Have dossiers use this service info");
			}
			
			serviceInfoPersistence.remove(object);

			List<ServiceFileTemplate> fileTemplates = serviceFileTemplateLocalService.getByServiceInfoId(id);

			for (ServiceFileTemplate fileTemplate : fileTemplates) {
				if (fileTemplate.getFileEntryId() != 0) {
					try {
						serviceFileTemplateLocalService.removeServiceFileTemplate(id, fileTemplate.getFileTemplateNo());
					} catch (PortalException e) {
						_log.error(e);
					}
				}
			}

		}

		return object;
	}

	@Indexable(type = IndexableType.REINDEX)
	public ServiceInfo adminProcessData(JSONObject objectData) {

		ServiceInfo object = null;

		if (objectData.getLong("serviceInfoId") > 0) {

			object = serviceInfoPersistence.fetchByPrimaryKey(objectData.getLong("serviceInfoId"));

			object.setModifiedDate(new Date());

		} else {

			try {
				valdiate(objectData.getString("serviceCode"), objectData.getString("serviceName"),
						objectData.getString("administrationCode"), objectData.getString("domainCode"),
						objectData.getLong("groupId"));
			} catch (PortalException e) {
				_log.error(e);
				return null;
			}

			long id = CounterLocalServiceUtil.increment(ServiceInfo.class.getName());

			object = serviceInfoPersistence.create(id);

			object.setGroupId(objectData.getLong("groupId"));
			object.setCompanyId(objectData.getLong("companyId"));
			object.setCreateDate(new Date());

		}

		object.setUserId(objectData.getLong("userId"));
		object.setUserName(objectData.getString("userName"));

		object.setServiceCode(objectData.getString("serviceCode"));
		object.setServiceName(objectData.getString("serviceName"));
		object.setProcessText(objectData.getString("processText"));
		object.setMethodText(objectData.getString("methodText"));
		object.setDossierText(objectData.getString("dossierText"));
		object.setConditionText(objectData.getString("conditionText"));
		object.setDurationText(objectData.getString("durationText"));
		object.setApplicantText(objectData.getString("applicantText"));
		object.setResultText(objectData.getString("resultText"));
		object.setRegularText(objectData.getString("regularText"));
		object.setFeeText(objectData.getString("feeText"));
		object.setMaxLevel(objectData.getInt("maxLevel"));
		object.setPublic_(objectData.getBoolean("public_"));

		object.setAdministrationCode(objectData.getString("administrationCode"));
		object.setDomainCode(objectData.getString("domainCode"));

		DictItem adm = DictCollectionUtils.getDictItemByCode(DataMGTConstants.ADMINTRATION_CODE,
				objectData.getString("administrationCode"), objectData.getLong("groupId"));
		DictItem dom = DictCollectionUtils.getDictItemByCode(DataMGTConstants.SERVICE_DOMAIN,
				objectData.getString("domainCode"), objectData.getLong("groupId"));

		if (Validator.isNotNull(adm)) {
			object.setAdministrationName(adm.getItemName());
			object.setAdministrationIndex(adm.getTreeIndex());
		}

		if (Validator.isNotNull(dom)) {
			object.setDomainName(dom.getItemName());
			object.setDomainIndex(dom.getTreeIndex());
		}

		serviceInfoPersistence.update(object);

		return object;
	}

	public static final String CLASS_NAME = ServiceInfo.class.getName();
}