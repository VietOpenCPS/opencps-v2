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

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.dossiermgt.action.util.SpecialCharacterUtils;
import org.opencps.dossiermgt.constants.DeliverableTerm;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.exception.NoSuchDeliverableException;
import org.opencps.dossiermgt.model.Deliverable;
import org.opencps.dossiermgt.model.DeliverableType;
import org.opencps.dossiermgt.service.base.DeliverableLocalServiceBaseImpl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
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

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the deliverable local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.dossiermgt.service.DeliverableLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see DeliverableLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.DeliverableLocalServiceUtil
 */
@ProviderType
public class DeliverableLocalServiceImpl extends DeliverableLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.dossiermgt.service.DeliverableLocalServiceUtil} to access the
	 * deliverable local service.
	 */
	
	public Deliverable getByCode(String deliverableCode) {
		return deliverablePersistence.fetchByFB_DCODE(deliverableCode);
	}
	
	public List<Deliverable> getListDeliverable(String deliverableState, String govAgencyCode, String deliverableType,
			String applicant) {
		List<Deliverable> listDeliverable = deliverablePersistence.findByG_ID(deliverableState, govAgencyCode,
				deliverableType, applicant);
		return listDeliverable;
	}

	@Indexable(type = IndexableType.REINDEX)
	public Deliverable addDeliverable(long groupId, String deliverableType, String deliverableCode,
			String govAgencyCode, String applicationIdNo, String applicationName, String subject,
			String issueDate, String expireDate,String revalidate, String deliverableState,
			ServiceContext serviceContext) {
		// TODO Add RegistrationForm
		long userId = serviceContext.getUserId();

		Date now = new Date();

//		User userAction = userLocalService.getUser(userId);
		
//		referenceUid = UUID.randomUUID().toString();

		long deliverableId = counterLocalService.increment(Deliverable.class.getName());

		Deliverable object = deliverablePersistence.create(deliverableId);
		
		DeliverableType dlvType = deliverableTypePersistence.fetchByG_DLT(groupId, deliverableType);

		/// Add audit fields
		object.setGroupId(groupId);
		object.setCreateDate(now);
		object.setModifiedDate(now);
		object.setUserId(userId);
		
		// Add other fields
		if (Validator.isNotNull(dlvType)) {
			object.setDeliverableName(dlvType.getTypeName());
		}
		object.setDeliverableId(deliverableId);
		object.setDeliverableType(deliverableType);
		object.setDeliverableCode(deliverableCode);
		object.setGovAgencyCode(govAgencyCode);
		object.setApplicantIdNo(applicationIdNo);
		object.setApplicantName(applicationName);
		object.setSubject(subject);
		object.setIssueDate(APIDateTimeUtils.convertStringToDate(issueDate, APIDateTimeUtils._NORMAL_PARTTERN));
		object.setExpireDate(APIDateTimeUtils.convertStringToDate(expireDate, APIDateTimeUtils._NORMAL_PARTTERN));
		object.setRevalidate(APIDateTimeUtils.convertStringToDate(revalidate, APIDateTimeUtils._NORMAL_PARTTERN));
		object.setDeliverableState(String.valueOf(deliverableState));

		return deliverablePersistence.update(object);
	}

	public Hits searchLucene(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {

		String keywords = (String) params.get(Field.KEYWORD_SEARCH);
//		String groupId = (String) params.get(Field.GROUP_ID);

		Indexer<Deliverable> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Deliverable.class);
		
		// Search elastic
		String pattern = String.valueOf(params.get("pattern"));
		String paramValues = String.valueOf(params.get("paramValues"));
		String paramTypes = String.valueOf(params.get("paramTypes"));
		//Query elastic
//		List<BooleanQuery> _subQueries = null;
//		List<BooleanClauseOccur> _occurs = null;
		if (Validator.isNotNull(pattern) && Validator.isNotNull(paramValues) && Validator.isNotNull(paramTypes)) {
			LuceneQuery( pattern, paramValues, paramTypes, searchContext);
		} else {
			this.setOccurs(null);
			this.setParams(null);
			this.setPattern(null);
			this.setQuery(null);
			this.setSubPatterns(null);
			this.setSubQueries(null);
			this.setSearchContext(null);
			this.setParamNames(null);
			this.setParamTypes(null);
		}

		// Set value header
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

				query.addFields(DeliverableTerm.DELIVERABLE_NAME);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

//		if (Validator.isNotNull(groupId)) {
//			MultiMatchQuery query = new MultiMatchQuery(groupId);
//
//			query.addFields(Field.GROUP_ID);
//
//			booleanQuery.add(query, BooleanClauseOccur.MUST);
//		}

		// Extra fields
		String state = GetterUtil.getString(params.get(DeliverableTerm.DELIVERABLE_STATE));
		String agency = GetterUtil.getString(params.get(DeliverableTerm.GOV_AGENCY_CODE));
		String type = GetterUtil.getString(params.get(DeliverableTerm.DELIVERABLE_TYPE));
		String applicant = GetterUtil.getString(params.get(DeliverableTerm.APPLICANT_ID_NO));
		String deliverableId = String.valueOf(GetterUtil.getLong(params.get(DeliverableTerm.DELIVERABLE_ID)));
		String owner = GetterUtil.getString(params.get(DossierTerm.OWNER));
		long userId = GetterUtil.getLong(params.get(DossierTerm.USER_ID));

		if (Validator.isNotNull(state)) {
			MultiMatchQuery query = new MultiMatchQuery(state);

			query.addFields(DeliverableTerm.DELIVERABLE_STATE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(agency)) {
			MultiMatchQuery query = new MultiMatchQuery(agency);

			query.addFields(DeliverableTerm.GOV_AGENCY_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(type)) {
			MultiMatchQuery query = new MultiMatchQuery(type);

			query.addFields(DeliverableTerm.DELIVERABLE_TYPE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(applicant)) {
			MultiMatchQuery query = new MultiMatchQuery(applicant);

			query.addFields(DeliverableTerm.APPLICANT_ID_NO);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(deliverableId) && !deliverableId.equalsIgnoreCase("0")) {
			MultiMatchQuery query = new MultiMatchQuery(deliverableId);

			query.addFields(DeliverableTerm.DELIVERABLE_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(owner) && Boolean.parseBoolean(owner.toLowerCase()) && userId > 0) {
			MultiMatchQuery query = new MultiMatchQuery(String.valueOf(userId));

			query.addField(DossierTerm.USER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);
	}

	public long countLucene(LinkedHashMap<String, Object> params, SearchContext searchContext)
			throws ParseException, SearchException {

		String keywords = (String) params.get(Field.KEYWORD_SEARCH);
//		String groupId = (String) params.get(Field.GROUP_ID);

		Indexer<Deliverable> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Deliverable.class);

		String pattern = String.valueOf(params.get("pattern"));
		String paramValues = String.valueOf(params.get("paramValues"));
		String paramTypes = String.valueOf(params.get("paramTypes"));
		_log.info("pattern:" +pattern +"------paramValues: "+paramValues+"----paramTypes: "+paramTypes);
		//Query elastic
//		List<BooleanQuery> _subQueries = null;
//		List<BooleanClauseOccur> _occurs = null;
		if (Validator.isNotNull(pattern) && Validator.isNotNull(paramValues) && Validator.isNotNull(paramTypes)) {
			LuceneQuery( pattern, paramValues, paramTypes, searchContext);
		} else {
			this.setOccurs(null);
			this.setParams(null);
			this.setPattern(null);
			this.setQuery(null);
			this.setSubPatterns(null);
			this.setSubQueries(null);
			this.setSearchContext(null);
			this.setParamNames(null);
			this.setParamTypes(null);
		}

		// Set value header
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
		_log.info("_subQueries: "+_subQueries);
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

				query.addFields(DeliverableTerm.DELIVERABLE_NAME);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

//		if (Validator.isNotNull(groupId)) {
//			MultiMatchQuery query = new MultiMatchQuery(groupId);
//
//			query.addFields(Field.GROUP_ID);
//
//			booleanQuery.add(query, BooleanClauseOccur.MUST);
//		}

		// Extra fields
		String state = GetterUtil.getString(params.get(DeliverableTerm.DELIVERABLE_STATE));
		String agency = GetterUtil.getString(params.get(DeliverableTerm.GOV_AGENCY_CODE));
		String type = GetterUtil.getString(params.get(DeliverableTerm.DELIVERABLE_TYPE));
		String applicant = GetterUtil.getString(params.get(DeliverableTerm.APPLICANT_ID_NO));

		if (Validator.isNotNull(state)) {
			MultiMatchQuery query = new MultiMatchQuery(state);

			query.addFields(DeliverableTerm.DELIVERABLE_STATE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(agency)) {
			MultiMatchQuery query = new MultiMatchQuery(agency);

			query.addFields(DeliverableTerm.GOV_AGENCY_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(type)) {
			MultiMatchQuery query = new MultiMatchQuery(type);

			query.addFields(DeliverableTerm.DELIVERABLE_TYPE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(applicant)) {
			MultiMatchQuery query = new MultiMatchQuery(applicant);

			query.addFields(DeliverableTerm.APPLICANT_ID_NO);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);
	}

	public static final String CLASS_NAME = Deliverable.class.getName();

	public Deliverable getDeliverableDetail(long id) throws NoSuchDeliverableException {
		return deliverablePersistence.fetchByDID(id);
	}

	public Deliverable getDetailById(long id) {
		return deliverablePersistence.fetchByDID(id);
	}

	//4
	@Indexable(type=IndexableType.REINDEX)
	public Deliverable updateDeliverable(long groupId, long id, String subject, String issueDate, String expireDate,
			String revalidate, String deliverableState, String deliverableAction, ServiceContext serviceContext) {
		long userId = serviceContext.getUserId();

		Date now = new Date();
		
		Deliverable object = null;
		try {
			object = deliverablePersistence.findByG_DID(groupId, id);
			
			/// Add audit fields
			object.setGroupId(groupId);
			object.setModifiedDate(now);
			object.setUserId(userId);

			// Add other fields
			object.setSubject(subject);
			object.setIssueDate(APIDateTimeUtils.convertStringToDate(issueDate, APIDateTimeUtils._NORMAL_PARTTERN));
			object.setExpireDate(APIDateTimeUtils.convertStringToDate(expireDate, APIDateTimeUtils._NORMAL_PARTTERN));
			object.setRevalidate(APIDateTimeUtils.convertStringToDate(revalidate, APIDateTimeUtils._NORMAL_PARTTERN));
			object.setDeliverableState(String.valueOf(deliverableState));
		} catch (Exception e) {
			 //TODO
		}
		//TODO:????
		try {
//			DeliverableLog deliverableLog = deliverableLogPersistence.fi(groupId, id);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return deliverablePersistence.update(object);
	}

	//5
	@Indexable(type=IndexableType.DELETE)
	public Deliverable deleteDeliverable(long id) throws NoSuchDeliverableException {
		return deliverablePersistence.remove(id);
	}

	//7
	@Indexable(type=IndexableType.REINDEX)
	public Deliverable updateFormData(long groupId, long id, String formData,ServiceContext serviceContext) throws NoSuchDeliverableException {
		long userId = serviceContext.getUserId();

		Date now = new Date();

		Deliverable object = null;
		object = deliverablePersistence.findByG_DID(groupId, id);
		
		/// Add audit fields
		object.setGroupId(groupId);
		object.setModifiedDate(now);
		object.setUserId(userId);

		// Add other fields
		object.setFormData(formData);

		return deliverablePersistence.update(object);
	}
	/////////////////////
	public List<Deliverable> findDeliverableByState(String strDeliverableCode, String state) {
		return deliverableFinder.findDeliverableByState(strDeliverableCode, state);
	}

	//Get info Output DB
	public List<Deliverable> getDeliverableByModifiedDate(String synsDate, String deliverableType,
			long deliverableState) {
		return deliverableFinder.findDeliverableByModifiedDate(synsDate, deliverableType, deliverableState);
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
					String strValueArr = StringPool.BLANK;
					if (Validator.isNotNull(arrParamValue[i])) {
						strValueArr = SpecialCharacterUtils.splitSpecial(arrParamValue[i].toString().toLowerCase());
					} else {
						strValueArr = arrParamValue[i];
					}
					Object param = null;
					Class<?> clazz = null;
					switch (paramType) {
					case "long":
						param = GetterUtil.getLong(strValueArr);
						clazz = long.class;
						break;
					case "integer":
						param = GetterUtil.getInteger(strValueArr);
						clazz = int.class;
						break;
					case "int":
						param = GetterUtil.getInteger(strValueArr);
						clazz = int.class;
						break;
					case "short":
						param = GetterUtil.getShort(strValueArr);
						clazz = short.class;
						break;
					case "double":
						param = GetterUtil.getDouble(strValueArr);
						clazz = double.class;
						break;
					case "float":
						param = GetterUtil.getFloat(strValueArr);
						clazz = float.class;
						break;
					case "boolean":
						param = GetterUtil.getBoolean(strValueArr);
						clazz = boolean.class;
						break;
					case "date":
//						param = DateTimeUtil
//								.convertStringToDate(strValueArr);
						clazz = Date.class;
						break;
					case "string":
						param = GetterUtil.getString(strValueArr);
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

	private static Log _log = LogFactoryUtil.getLog(DeliverableLocalServiceImpl.class);
 }