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

import aQute.bnd.annotation.ProviderType;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import org.opencps.dossiermgt.constants.RegistrationFormTerm;
import org.opencps.dossiermgt.model.Registration;
import org.opencps.dossiermgt.model.RegistrationForm;
import org.opencps.dossiermgt.service.base.RegistrationFormLocalServiceBaseImpl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
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
import com.liferay.portal.kernel.search.TermQuery;
import com.liferay.portal.kernel.search.TermQueryFactoryUtil;
import com.liferay.portal.kernel.search.generic.MultiMatchQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

/**
 * The implementation of the registration form local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.dossiermgt.service.RegistrationFormLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see RegistrationFormLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.RegistrationFormLocalServiceUtil
 */
@ProviderType
public class RegistrationFormLocalServiceImpl extends RegistrationFormLocalServiceBaseImpl {

	public List<RegistrationForm> getFormsbyRegId(long groupId, long registrationId) throws PortalException {

		List<RegistrationForm> lstRegistrationForm = registrationFormPersistence.findByG_REGID(groupId, registrationId);

		return lstRegistrationForm;
	}

	@Indexable(type = IndexableType.REINDEX)
	public RegistrationForm addRegistrationForm(long groupId, long companyId, long registrationId, String referenceUid,
			String formNo, String formName, String formData, String formScript, String formReport, long fileEntryId,
			boolean isNew, boolean removed, ServiceContext serviceContext) throws PortalException {
		// TODO Add RegistrationForm
		long userId = serviceContext.getUserId();

		Date now = new Date();

		User userAction = userLocalService.getUser(userId);
		
		referenceUid = UUID.randomUUID().toString();

		long registrationFormId = counterLocalService.increment(RegistrationForm.class.getName());

		RegistrationForm object = registrationFormPersistence.create(registrationFormId);

		/// Add audit fields
		object.setCompanyId(companyId);
		object.setGroupId(groupId);
		object.setCreateDate(now);
		object.setModifiedDate(now);
		object.setUserId(userAction.getUserId());

		// Add other fields
		object.setRegistrationId(registrationId);
		object.setReferenceUid(referenceUid);
		object.setFormNo(formNo);
		object.setFormName(formName);
		object.setFormData(formData);
		object.setFormScript(formScript);
		object.setFormReport(formReport);
		object.setFileEntryId(fileEntryId);
		object.setIsNew(true);
		object.setRemoved(removed);

		return registrationFormPersistence.update(object);
	}
	
	@Indexable(type = IndexableType.REINDEX)
	public RegistrationForm updateRegistrationForm(long groupId, long registrationId, String referenceUid,
			String formNo, String formName, String formData, String formScript, String formReport, long fileEntryId,
			boolean isNew, boolean removed, ServiceContext serviceContext) throws PortalException {

		Date now = new Date();

		RegistrationForm object = registrationFormPersistence.fetchByG_REGID_REFID(groupId, registrationId,
				referenceUid);

		/// Add audit fields
		object.setModifiedDate(now);

		// Add other fields
		object.setRegistrationId(registrationId);
		object.setReferenceUid(referenceUid);
		object.setFormNo(formNo);
		object.setFormName(formName);
		object.setFormData(formData);
		object.setFormScript(formScript);
		object.setFormReport(formReport);
		object.setFileEntryId(fileEntryId);
		object.setIsNew(true);
		object.setRemoved(removed);

		return registrationFormPersistence.update(object);
	}
	
	@Indexable(type = IndexableType.REINDEX)
	public RegistrationForm deleteRegistrationForm(long groupId, long registrationId, String referenceUid)
			throws PortalException {

		RegistrationForm object = registrationFormPersistence.findByG_REGID_REFID(groupId, registrationId,
				referenceUid);

		object.setRemoved(true);
		object.setIsNew(true);
		object.setModifiedDate(new Date());

		return registrationFormPersistence.update(object);
	}

	@Indexable(type = IndexableType.REINDEX)
	public List<RegistrationForm> deleteRegistrationForms(long groupId, long registrationId) {

		List<RegistrationForm> lstRegistrationForm = registrationFormPersistence.findByG_REGID(groupId, registrationId);

		for (RegistrationForm registrationForm : lstRegistrationForm) {
			registrationForm.setRemoved(true);
			registrationForm.setIsNew(true);
			registrationFormPersistence.update(registrationForm);
		}

		return lstRegistrationForm;
	}
	
	public RegistrationForm findFormbyRegidRefid(long groupId, long registrationId, String referenceUid){
		return registrationFormPersistence.fetchByG_REGID_REFID(groupId, registrationId, referenceUid);
	}
	
	//binhth
	public List<RegistrationForm> findByG_REGID_ISNEW(long registrationId, boolean isNew) {
		return registrationFormPersistence.findByG_REGID_ISNEW(registrationId, isNew);
	}
	
	@Indexable(type = IndexableType.REINDEX)
    public RegistrationForm registrationFormSync(
        long groupId, String uuidRegistration, String referenceUid,
        String formNo, String formName, String formData, String formScript,
        String formReport, Boolean removed, ServiceContext serviceContext)
			throws PortalException, SystemException {

		Date now = new Date();
		long userId = serviceContext.getUserId();
		User userAction = userLocalService.getUser(userId);

		
		Registration registration = registrationPersistence.fetchByUUID_G(uuidRegistration, groupId);
		RegistrationForm registrationForm = registrationFormPersistence.fetchByG_REGID_REFID(groupId, registration.getRegistrationId(), referenceUid);
		
		if (Validator.isNotNull(registrationForm)) {
			registrationForm.setModifiedDate(now);
			
			registrationForm.setFormNo(formNo);
			registrationForm.setFormName(formName);
			registrationForm.setFormData(formData);
			registrationForm.setFormScript(formScript);
			registrationForm.setFormReport(formReport);
			
			if(removed != null) {
			    registrationForm.setRemoved(removed.booleanValue());
			}
			
			if(Validator.isNotNull(formData) && Validator.isNotNull(formReport)) {
                Message message = new Message();
    
                JSONObject msgData = JSONFactoryUtil.createJSONObject();
                msgData.put("className", RegistrationForm.class.getName());
                msgData.put("classPK", registrationForm.getPrimaryKey());
                msgData.put("jrxmlTemplate", formReport);
                msgData.put("formData", formData);
                msgData.put("userId", serviceContext.getUserId());
    
                message.put("msgToEngine", msgData);
                MessageBusUtil.sendMessage("jasper/engine/out/destination", message);
            }
			
			registrationForm = registrationFormPersistence.update(registrationForm);
		} else {
			
			long registrationFormId = counterLocalService.increment(RegistrationForm.class.getName());
			
			registrationForm = registrationFormPersistence.create(registrationFormId);
			
			registrationForm.setGroupId(groupId);
			registrationForm.setCreateDate(now);
			registrationForm.setModifiedDate(now);
			registrationForm.setUserId(userAction.getUserId());
			
			registrationForm.setRegistrationId(registration.getRegistrationId());
			registrationForm.setReferenceUid(referenceUid);
			registrationForm.setFormNo(formNo);
			registrationForm.setFormName(formName);
			registrationForm.setFormData(formData);
			registrationForm.setFormScript(formScript);
			registrationForm.setFormReport(formReport);
			
			if(removed != null) {
                registrationForm.setRemoved(removed.booleanValue());
            }
			
			if(Validator.isNotNull(formData) && Validator.isNotNull(formReport)) {
                Message message = new Message();
    
                JSONObject msgData = JSONFactoryUtil.createJSONObject();
                msgData.put("className", RegistrationForm.class.getName());
                msgData.put("classPK", registrationForm.getPrimaryKey());
                msgData.put("jrxmlTemplate", formReport);
                msgData.put("formData", formData);
                msgData.put("userId", serviceContext.getUserId());
    
                message.put("msgToEngine", msgData);
                MessageBusUtil.sendMessage("jasper/engine/out/destination", message);
            }
			
			registrationForm = registrationFormPersistence.update(registrationForm);
			
		}

		return registrationForm;
	}
	
	@Indexable(type = IndexableType.REINDEX)
	public RegistrationForm updateFormData(long groupId, long registrationId, String referenceUid, String formData,
			ServiceContext serviceContext) 
		throws PortalException, SystemException {

		RegistrationForm registrationForm = registrationFormPersistence.findByG_REGID_REFID(groupId, registrationId, referenceUid);

		String jrxmlTemplate = registrationForm.getFormReport();

		registrationForm.setFormData(formData);
		registrationForm.setIsNew(true);

		Message message = new Message();

		JSONObject msgData = JSONFactoryUtil.createJSONObject();
		msgData.put("className", RegistrationForm.class.getName());
		msgData.put("classPK", registrationForm.getPrimaryKey());
		msgData.put("jrxmlTemplate", jrxmlTemplate);
		msgData.put("formData", formData);
		msgData.put("userId", serviceContext.getUserId());

		message.put("msgToEngine", msgData);
		MessageBusUtil.sendMessage("jasper/engine/out/destination", message);

		return registrationFormPersistence.update(registrationForm);
	}
	
	@Indexable(type = IndexableType.REINDEX)
	public RegistrationForm updateIsNew(long groupId, long registrationId, String referenceUid, boolean isNew,
			ServiceContext serviceContext) throws PortalException, SystemException {

		RegistrationForm registrationForm = registrationFormPersistence.findByG_REGID_REFID(groupId, registrationId,
				referenceUid);

		registrationForm.setIsNew(isNew);

		return registrationFormPersistence.update(registrationForm);
	}

	public Hits searchLucene(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {

		String keywords = (String) params.get(Field.KEYWORD_SEARCH);
		String groupId = (String) params.get(Field.GROUP_ID);

		Indexer<RegistrationForm> indexer = IndexerRegistryUtil.nullSafeGetIndexer(RegistrationForm.class);

		// Search elastic
		String pattern = String.valueOf(params.get("pattern"));
		String paramValues = String.valueOf(params.get("paramValues"));
		String paramTypes = String.valueOf(params.get("paramTypes"));
		//Query elastic
		if (Validator.isNotNull(pattern) && Validator.isNotNull(paramValues) && Validator.isNotNull(paramTypes)) {
			LuceneQuery( pattern, paramValues, paramTypes, searchContext);
		}

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

		// Add params query
		int count = 0;
		if (_subQueries != null && _subQueries.size() > 0) {
			for (BooleanQuery boolQuery : _subQueries) {
				if (count == 0) {
					booleanQuery.add(boolQuery, BooleanClauseOccur.MUST);
				} else {
					booleanQuery.add(boolQuery, _occurs.get(count - 1));
				}
				count++;
			}
		}

		if (Validator.isNotNull(keywords)) {

			String[] keyword = keywords.split(StringPool.SPACE);

			for (String string : keyword) {

				MultiMatchQuery query = new MultiMatchQuery(string);

				query.addFields(RegistrationFormTerm.REGISTRATION_ID);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(Field.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}
		String referenceUid = GetterUtil.getString(params.get(RegistrationFormTerm.REFERENCE_UID));
		String registrationId = GetterUtil.getString(params.get(RegistrationFormTerm.REGISTRATION_ID));

		if (Validator.isNotNull(referenceUid)) {
			MultiMatchQuery query = new MultiMatchQuery(referenceUid);

			query.addFields(RegistrationFormTerm.REFERENCE_UID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(registrationId)) {
			MultiMatchQuery query = new MultiMatchQuery(registrationId);

			query.addFields(RegistrationFormTerm.REGISTRATION_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}
		
		String formNo = GetterUtil.getString(params.get(RegistrationFormTerm.FORM_NO));
		if (Validator.isNotNull(formNo)) {
            MultiMatchQuery query = new MultiMatchQuery(formNo.toLowerCase());

            query.addFields(RegistrationFormTerm.FORM_NO);

            booleanQuery.add(query, BooleanClauseOccur.MUST);
        }

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);
	}

	public long countLucene(LinkedHashMap<String, Object> params, SearchContext searchContext)
			throws ParseException, SearchException {

		String keywords = (String) params.get(Field.KEYWORD_SEARCH);
		String groupId = (String) params.get(Field.GROUP_ID);

		Indexer<RegistrationForm> indexer = IndexerRegistryUtil.nullSafeGetIndexer(RegistrationForm.class);

		// Search elastic
//		String pattern = "thiet_bi_san_xuat_chinh = ?";
//		String paramValues = "11111";
//		String paramTypes = "String";
		String pattern = String.valueOf(params.get("pattern"));
		String paramValues = String.valueOf(params.get("paramValues"));
		String paramTypes = String.valueOf(params.get("paramTypes"));
		//Query elastic
		if (Validator.isNotNull(pattern) && Validator.isNotNull(paramValues) && Validator.isNotNull(paramTypes)) {
			LuceneQuery( pattern, paramValues, paramTypes, searchContext);
		}

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

		// Add params query
		int count = 0;
		if (_subQueries != null && _subQueries.size() > 0) {
			for (BooleanQuery boolQuery : _subQueries) {
				if (count == 0) {
					booleanQuery.add(boolQuery, BooleanClauseOccur.MUST);
				} else {
					booleanQuery.add(boolQuery, _occurs.get(count - 1));
				}
				count++;
			}
		}

		if (Validator.isNotNull(keywords)) {

			String[] keyword = keywords.split(StringPool.SPACE);

			for (String string : keyword) {

				MultiMatchQuery query = new MultiMatchQuery(string);

				query.addFields(RegistrationFormTerm.REGISTRATION_ID);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(Field.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}
		String referenceUid = GetterUtil.getString(params.get(RegistrationFormTerm.REFERENCE_UID));
		String registrationId = GetterUtil.getString(params.get(RegistrationFormTerm.REGISTRATION_ID));

		if (Validator.isNotNull(referenceUid)) {
			MultiMatchQuery query = new MultiMatchQuery(referenceUid);

			query.addFields(RegistrationFormTerm.REFERENCE_UID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(registrationId)) {
			MultiMatchQuery query = new MultiMatchQuery(registrationId);

			query.addFields(RegistrationFormTerm.REGISTRATION_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}
		
		String formNo = GetterUtil.getString(params.get(RegistrationFormTerm.FORM_NO));
        if (Validator.isNotNull(formNo)) {
            MultiMatchQuery query = new MultiMatchQuery(formNo.toLowerCase());

            query.addFields(RegistrationFormTerm.FORM_NO);

            booleanQuery.add(query, BooleanClauseOccur.MUST);
        }

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);
	}

	public static final String CLASS_NAME = RegistrationForm.class.getName();

	//18
	public List<RegistrationForm> getFormDataByFormNo(long groupId, long registrationId, String formNo) {
		
//		return registrationFormPersistence.fetchByG_REGID_FORMNO(groupId, registrationId, formNo);
		return registrationFormPersistence.findByG_REGID_FORMNO(groupId, registrationId, formNo);
	}

	/**
	 * @param pattern
	 * @return
	 */
	protected static List<String> getSplitIndex(String pattern) {
		List<String> splitIndexs = new ArrayList<String>();
		int eliminateParenthesis = 0;
		int startIndex = 0;
		int endIndex = 0;

		for (int i = 0; i < pattern.length(); i++) {

			Character c = pattern.charAt(i);

			if (c.toString().equals(StringPool.OPEN_PARENTHESIS)) {
				eliminateParenthesis += 1;
			} else if (c.toString().equals(StringPool.CLOSE_PARENTHESIS)) {
				eliminateParenthesis += -1;
			}

			if (eliminateParenthesis == 1
					&& c.toString().equals(StringPool.OPEN_PARENTHESIS)) {
				startIndex = i;
			}

			if (eliminateParenthesis == 0
					&& c.toString().equals(StringPool.CLOSE_PARENTHESIS)) {
				endIndex = i;

			}

			if (!splitIndexs.contains(startIndex + StringPool.DASH + endIndex)
					&& startIndex < endIndex) {

				splitIndexs.add(startIndex + StringPool.DASH + endIndex);
			}
		}

		return splitIndexs;
	}

	/**
	 * @param pattern
	 * @param subQueries
	 * @return
	 * @throws ParseException
	 */
	public static List<String> getSubQueries(String pattern,
			List<String> subQueries) throws ParseException {

		pattern = validPattern(pattern);

		// if (Validator.isNull(pattern)) {
		// return null;
		// }

		List<String> splitIndexs = getSplitIndex(pattern);

		if (splitIndexs != null) {
			if (splitIndexs.isEmpty()) {
				subQueries.add(pattern);
			} else {
				for (String splitIndex : splitIndexs) {

					int[] splitIndexsTemp = StringUtil.split(splitIndex,
							StringPool.DASH, 0);
					String subQuery = pattern.substring(splitIndexsTemp[0],
							splitIndexsTemp[1] + 1);
					if (subQuery.contains("[and]") || subQuery.contains("[or]")
							|| subQuery.contains("[not]")) {
						getSubQueries(subQuery, subQueries);
					} else {
						subQuery = subQuery.replaceAll("\\(", StringPool.BLANK);

						subQuery = subQuery.replaceAll("\\)", StringPool.BLANK);

						subQueries.add(subQuery);

					}
				}
			}

		}

		return subQueries;
	}
	
	
	/**
	 * @param pattern
	 * @return
	 */
	public static String validPattern(String pattern) {
		int eliminateParenthesis = 0;
		int startParenthesisIndex = 0;
		int endParenthesisIndex = 0;
		// pattern = pattern.trim().toLowerCase();
		for (int i = 0; i < pattern.length(); i++) {

			Character c = pattern.charAt(i);

			if (c.toString().equals(StringPool.OPEN_PARENTHESIS)) {
				eliminateParenthesis += 1;
			} else if (c.toString().equals(StringPool.CLOSE_PARENTHESIS)) {
				eliminateParenthesis += -1;
			}

			if (eliminateParenthesis == 1
					&& c.toString().equals(StringPool.OPEN_PARENTHESIS)) {
				startParenthesisIndex = i;
			}

			if (eliminateParenthesis == 0
					&& c.toString().equals(StringPool.CLOSE_PARENTHESIS)) {
				endParenthesisIndex = i;
			}

		}

		if (eliminateParenthesis != 0) {
			return StringPool.BLANK;
		}

		if (endParenthesisIndex == pattern.length() - 1
				&& startParenthesisIndex == 0) {
			pattern = pattern.substring(startParenthesisIndex + 1,
					endParenthesisIndex);

			pattern = validPattern(pattern);

		}

		return pattern;
	}

	/////////////
	public void LuceneQuery(String pattern, String paramValues, String paramTypes,
			SearchContext searchContext) {

		BooleanQuery query = BooleanQueryFactoryUtil.create(searchContext);
		List<String> subPatterns = new ArrayList<String>();
		List<String> paramNames = new ArrayList<String>();
		List<BooleanClauseOccur> occurs = null;
		List<BooleanQuery> subQueries = null;
		List<Object> params = new ArrayList<Object>();
		List<Class<?>> clazzs = new ArrayList<Class<?>>();

		String[] arrParamValue = Validator.isNotNull(paramValues) ? StringUtil
				.split(paramValues, StringPool.POUND) : null;
		String[] arrParamTypes = Validator.isNotNull(paramTypes) ? StringUtil
				.split(paramTypes) : null;

		if (arrParamValue != null && arrParamTypes != null
				&& arrParamTypes.length > 0 && arrParamValue.length > 0
				&& arrParamValue.length == arrParamTypes.length) {
			try {
//				pattern = LuceneQueryUtil.validPattern(pattern);

				if (Validator.isNull(pattern)) {
					throw new Exception();
				}

				for (int i = 0; i < arrParamValue.length; i++) {
					String paramType = arrParamTypes[i].toLowerCase();
					Object param = null;
					Class<?> clazz = null;
					switch (paramType) {
					case "long":
						param = GetterUtil.getLong(arrParamValue[i]);
						clazz = long.class;
						break;
					case "integer":
						param = GetterUtil.getInteger(arrParamValue[i]);
						clazz = int.class;
						break;
					case "int":
						param = GetterUtil.getInteger(arrParamValue[i]);
						clazz = int.class;
						break;
					case "short":
						param = GetterUtil.getShort(arrParamValue[i]);
						clazz = short.class;
						break;
					case "double":
						param = GetterUtil.getDouble(arrParamValue[i]);
						clazz = double.class;
						break;
					case "float":
						param = GetterUtil.getFloat(arrParamValue[i]);
						clazz = float.class;
						break;
					case "boolean":
						param = GetterUtil.getBoolean(arrParamValue[i]);
						clazz = boolean.class;
						break;
					case "date":
//						param = DateTimeUtil
//								.convertStringToDate(arrParamValue[i]);
						clazz = Date.class;
						break;
					case "string":
						param = GetterUtil.getString(arrParamValue[i]);
						clazz = String.class;
						break;
					case "null":
						param = null;
						clazz = null;
						break;
					case "":
						param = null;
						clazz = null;
						break;
					case " ":
						param = null;
						clazz = null;
						break;
					default:
						break;
					}

					params.add(param);
					clazzs.add(clazz);
				}

				getSubQueries(pattern, subPatterns);

				if (subPatterns != null && !subPatterns.isEmpty()) {
					subQueries = createBooleanQueries(
							subPatterns, params, paramNames, searchContext);

					occurs = getBooleanClauseOccurs(pattern,
							subPatterns);

					if (subQueries.size() - 1 != occurs.size()) {
						throw new Exception();
					}
					int count = 0;
					for (BooleanQuery booleanQuery : subQueries) {
						if (count == 0) {
							query.add(booleanQuery, BooleanClauseOccur.MUST);
						} else {
							query.add(booleanQuery, occurs.get(count - 1));
						}

						count++;
					}
				}

			} catch (Exception e) {
				try {
					throw new Exception();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} finally {
				this.setOccurs(occurs);
				this.setParams(params);
				this.setPattern(pattern);
				this.setQuery(query);
				this.setSubPatterns(subPatterns);
				this.setSubQueries(subQueries);
				this.setSearchContext(searchContext);
				this.setParamNames(paramNames);
				this.setParamTypes(clazzs);
			}
		} else {
			//TODO
		}

	}
	private SearchContext _searchContext;
	private String _pattern;
	private BooleanQuery _query;
	private List<BooleanQuery> _subQueries;
	private List<String> _subPatterns;
	private List<String> _paramNames;
	private List<Object> _params;
	private List<BooleanClauseOccur> _occurs;
	private List<Class<?>> _paramTypes;

	public List<Class<?>> getParamTypes() {
		return _paramTypes;
	}

	public void setParamTypes(List<Class<?>> paramTypes) {
		this._paramTypes = paramTypes;
	}

	public SearchContext getSearchContext() {
		return _searchContext;
	}

	public void setSearchContext(SearchContext searchContext) {
		this._searchContext = searchContext;
	}

	public String getPattern() {
		return _pattern;
	}

	public void setPattern(String pattern) {
		this._pattern = pattern;
	}

	public BooleanQuery getQuery() {
		return _query;
	}

	public void setQuery(BooleanQuery query) {
		this._query = query;
	}

	public List<BooleanQuery> getSubQueries() {
		return _subQueries;
	}

	public void setSubQueries(List<BooleanQuery> subQueries) {
		this._subQueries = subQueries;
	}

	public List<String> getSubPatterns() {
		return _subPatterns;
	}

	public void setSubPatterns(List<String> subPatterns) {
		this._subPatterns = subPatterns;
	}

	public List<String> getParamNames() {
		return _paramNames;
	}

	public void setParamNames(List<String> paramNames) {
		this._paramNames = paramNames;
	}

	public List<Object> getParams() {
		return _params;
	}

	public void setParams(List<Object> params) {
		this._params = params;
	}

	public List<BooleanClauseOccur> getOccurs() {
		return _occurs;
	}

	public void setOccurs(List<BooleanClauseOccur> occurs) {
		this._occurs = occurs;
	}
	
	public static List<BooleanClauseOccur> getBooleanClauseOccurs(
			String pattern, List<String> subQueries) {
		List<BooleanClauseOccur> booleanClauseOccurs = new ArrayList<BooleanClauseOccur>();
		pattern = pattern.replaceAll(Pattern.quote("("), StringPool.BLANK);

		pattern = pattern.replaceAll("\\)", StringPool.BLANK);

		pattern = pattern.replaceAll(StringPool.SPACE, StringPool.BLANK);
		for (String subQuery : subQueries) {
			subQuery = subQuery.replaceAll(StringPool.SPACE, StringPool.BLANK);
			pattern = pattern.replace(subQuery, StringPool.BLANK);
		}

		pattern = pattern.replaceAll("\\]\\[", StringPool.COMMA);

		pattern = pattern.replaceAll("\\[", StringPool.BLANK);

		pattern = pattern.replaceAll("\\]", StringPool.BLANK);

		String[] conditions = StringUtil.split(pattern);

		if (conditions != null && conditions.length > 0) {
			for (int c = 0; c < conditions.length; c++) {
				if (conditions[c].equalsIgnoreCase("and")) {
					booleanClauseOccurs.add(BooleanClauseOccur.MUST);
				} else if (conditions[c].equalsIgnoreCase("or")) {
					booleanClauseOccurs.add(BooleanClauseOccur.SHOULD);
				} else if (conditions[c].equalsIgnoreCase("not")) {
					booleanClauseOccurs.add(BooleanClauseOccur.MUST_NOT);
				}
			}
		}

		return booleanClauseOccurs;
	}
	
	
	public static List<BooleanQuery> createBooleanQueries(
			List<String> subQueries, List<Object> params,
			List<String> paramNames, SearchContext searchContext)
			throws ParseException {
		List<BooleanQuery> booleanQueries = new ArrayList<BooleanQuery>();
		if (subQueries != null) {
			for (String subQuery : subQueries) {
				String[] terms = StringUtil.split(subQuery);
				if (terms != null && terms.length > 0) {
					BooleanQuery query = BooleanQueryFactoryUtil
							.create(searchContext);
					for (int t = 0; t < terms.length; t++) {
						int paramPossition = subQueries.indexOf(subQuery)
								* terms.length + t;
						// String term = terms[t].trim().toLowerCase();
						String term = terms[t].trim();
						String key = StringPool.BLANK;
						if (term.contains((StringPool.EQUAL.toLowerCase()))) {
							key = term
									.substring(
											0,
											term.indexOf(StringPool.EQUAL
													.toLowerCase())).trim();
							// addExactTerm(query, key,
							// params.get(paramPossition));

							TermQuery termQuery = null;

							Object tempValue = params.get(paramPossition);

							if (tempValue instanceof Long) {
								termQuery = TermQueryFactoryUtil.create(
										searchContext, key, (long) tempValue);
							} else {
								termQuery = TermQueryFactoryUtil.create(
										searchContext, key,
										String.valueOf(tempValue));
							}

							if (termQuery != null) {
								query.add(termQuery, BooleanClauseOccur.MUST);
							}
						} else if (term.contains(StringPool.LIKE.toLowerCase())) {
							key = term
									.substring(
											0,
											term.indexOf(StringPool.LIKE
													.toLowerCase())).trim();

							query.addTerm(key, params.get(paramPossition)
									.toString(), true);

						} else if (term.contains(StringPool.BETWEEN
								.toLowerCase())) {
							key = term.substring(
									0,
									term.indexOf(StringPool.BETWEEN
											.toLowerCase())).trim();
//							query = addRangeTerm(query, key,
//									params.get(paramPossition));
						}

						if (Validator.isNotNull(key)) {
							paramNames.add(key);
						}

					}

					booleanQueries.add(query);
				}
			}
		}
		return booleanQueries;
	}
}