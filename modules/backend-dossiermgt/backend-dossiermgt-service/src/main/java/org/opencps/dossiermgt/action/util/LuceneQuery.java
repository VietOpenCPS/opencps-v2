package org.opencps.dossiermgt.action.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.BooleanQueryFactoryUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.opencps.auth.utils.APIDateTimeUtils;

public class LuceneQuery {
	private SearchContext _searchContext;
	private String _pattern;
	private BooleanQuery _query;
	private List<BooleanQuery> _subQueries;
	private List<String> _subPatterns;
	private List<String> _paramNames;
	private List<Object> _params;
	private List<BooleanClauseOccur> _occurs;
	private List<Class<?>> _paramTypes;

	public LuceneQuery(String pattern, List<Object> params,
			SearchContext searchContext) {
		BooleanQuery query = BooleanQueryFactoryUtil.create(searchContext);
		List<String> subPatterns = new ArrayList<String>();
		List<String> paramNames = new ArrayList<String>();
		List<BooleanClauseOccur> occurs = null;
		List<BooleanQuery> subQueries = null;
		try {
			pattern = LuceneQueryUtil.validPattern(pattern);

			if (Validator.isNull(pattern)) {
				throw new Exception();
			}

			LuceneQueryUtil.getSubQueries(pattern, subPatterns);

			if (subPatterns != null && !subPatterns.isEmpty()) {
				subQueries = LuceneQueryUtil.createBooleanQueries(subPatterns,
						params, paramNames, searchContext);

				occurs = LuceneQueryUtil.getBooleanClauseOccurs(pattern,
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
			_log.debug(e);
			//_log.error(e);
		} finally {
			this.setOccurs(occurs);
			this.setParams(params);
			this.setPattern(pattern);
			this.setQuery(query);
			this.setSubPatterns(subPatterns);
			this.setSubQueries(subQueries);
			this.setSearchContext(searchContext);
			this.setParamNames(paramNames);
		}
	}


	public LuceneQuery(String pattern, String paramValues, String paramTypes,
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
				pattern = LuceneQueryUtil.validPattern(pattern);

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
						param = APIDateTimeUtils.convertStringToDate(arrParamValue[i], APIDateTimeUtils._TIMESTAMP);
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

				LuceneQueryUtil.getSubQueries(pattern, subPatterns);

				if (subPatterns != null && !subPatterns.isEmpty()) {
					subQueries = LuceneQueryUtil.createBooleanQueries(
							subPatterns, params, paramNames, searchContext);

					occurs = LuceneQueryUtil.getBooleanClauseOccurs(pattern,
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
				_log.debug(e);
				//_log.error(e);
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
			_log.info("########################################## Can not compare menu pattern "
					+ pattern
					+ " : because paramNames and paramTypes not proportional");
		}

	}

//	public LuceneQuery(String accountType, long ownerOrganizationId,
//			long ownerUserId, String pattern, String paramValues,
//			String paramTypes, SearchContext searchContext) {
//
//		BooleanQuery query = BooleanQueryFactoryUtil.create(searchContext);
//		List<String> subPatterns = new ArrayList<String>();
//		List<String> paramNames = new ArrayList<String>();
//		List<BooleanClauseOccur> occurs = null;
//		List<BooleanQuery> subQueries = null;
//		List<Object> params = new ArrayList<Object>();
//		List<Class<?>> clazzs = new ArrayList<Class<?>>();
//
//		String[] arrParamValue = Validator.isNotNull(paramValues) ? StringUtil
//				.split(paramValues, StringPool.POUND) : null;
//		String[] arrParamTypes = Validator.isNotNull(paramTypes) ? StringUtil
//				.split(paramTypes) : null;
//
//		if (arrParamValue != null && arrParamTypes != null
//				&& arrParamTypes.length > 0 && arrParamValue.length > 0
//				&& arrParamValue.length == arrParamTypes.length) {
//			try {
//				pattern = LuceneQueryUtil.validPattern(pattern);
//
//				if (Validator.isNull(pattern)) {
//					throw new Exception();
//				}
//
//				for (int i = 0; i < arrParamValue.length; i++) {
//					String paramType = arrParamTypes[i].toLowerCase();
//					Object param = null;
//					Class<?> clazz = null;
//					switch (paramType) {
//					case "long":
//						param = GetterUtil.getLong(arrParamValue[i]);
//						clazz = long.class;
//						break;
//					case "integer":
//						param = GetterUtil.getInteger(arrParamValue[i]);
//						clazz = int.class;
//						break;
//					case "int":
//						param = GetterUtil.getInteger(arrParamValue[i]);
//						clazz = int.class;
//						break;
//					case "short":
//						param = GetterUtil.getShort(arrParamValue[i]);
//						clazz = short.class;
//						break;
//					case "double":
//						param = GetterUtil.getDouble(arrParamValue[i]);
//						clazz = double.class;
//						break;
//					case "float":
//						param = GetterUtil.getFloat(arrParamValue[i]);
//						clazz = float.class;
//						break;
//					case "boolean":
//						param = GetterUtil.getBoolean(arrParamValue[i]);
//						clazz = boolean.class;
//						break;
//					case "date":
////						param = DateTimeUtil
////								.convertStringToDate(arrParamValue[i]);
//						clazz = Date.class;
//						break;
//					case "string":
//						param = GetterUtil.getString(arrParamValue[i]);
//						clazz = String.class;
//						break;
//					case "null":
//						param = null;
//						clazz = null;
//						break;
//					case "":
//						param = null;
//						clazz = null;
//						break;
//					case " ":
//						param = null;
//						clazz = null;
//						break;
//
//					default:
//						break;
//					}
//
//					params.add(param);
//					clazzs.add(clazz);
//				}
//
//				LuceneQueryUtil.getSubQueries(pattern, subPatterns);
//
//				if (subPatterns != null && !subPatterns.isEmpty()) {
//					subQueries = LuceneQueryUtil.createBooleanQueries(
//							subPatterns, params, paramNames, searchContext);
//
//					occurs = LuceneQueryUtil.getBooleanClauseOccurs(pattern,
//							subPatterns);
//
//					if (subQueries.size() - 1 != occurs.size()) {
//						throw new Exception();
//					}
//					int count = 0;
//					for (BooleanQuery booleanQuery : subQueries) {
//						if (count == 0) {
//							query.add(booleanQuery, BooleanClauseOccur.MUST);
//						} else {
//							query.add(booleanQuery, occurs.get(count - 1));
//						}
//
//						count++;
//					}
//
//					BooleanQuery ownerQuery = BooleanQueryFactoryUtil
//							.create(searchContext);
//
//					if (accountType
//							.equals(PortletPropsValues.USERMGT_USERGROUP_NAME_CITIZEN)) {
//
//						ownerQuery.addTerm(DossierDisplayTerms.USER_ID,
//								ownerUserId);
//
//					} else if (accountType
//							.equals(PortletPropsValues.USERMGT_USERGROUP_NAME_BUSINESS)) {
//						ownerQuery.addTerm(
//								DossierDisplayTerms.OWNERORGANIZATION_ID,
//								ownerOrganizationId);
//
//					} else if (accountType
//							.equals(PortletPropsValues.USERMGT_USERGROUP_NAME_EMPLOYEE)) {
//						ownerQuery.addTerm(DossierDisplayTerms.USER_ID,
//								ownerUserId);
//					}
//
//					query.add(ownerQuery, BooleanClauseOccur.MUST);
//				}
//
//			} catch (Exception e) {
//				_log.error(e);
//			} finally {
//				this.setOccurs(occurs);
//				this.setParams(params);
//				this.setPattern(pattern);
//				this.setQuery(query);
//				this.setSubPatterns(subPatterns);
//				this.setSubQueries(subQueries);
//				this.setSearchContext(searchContext);
//				this.setParamNames(paramNames);
//				this.setParamTypes(clazzs);
//			}
//		} else {
//			_log.info("########################################## Can not compare menu pattern "
//					+ pattern
//					+ " : because paramNames and paramTypes not proportional");
//		}
//
//	}

//	public LuceneQuery(long groupId, String accountType, long userId,
//			String pattern, String paramValues, String paramTypes,
//			SearchContext searchContext) {
//
//		BooleanQuery query = BooleanQueryFactoryUtil.create(searchContext);
//		List<String> subPatterns = new ArrayList<String>();
//		List<String> paramNames = new ArrayList<String>();
//		List<BooleanClauseOccur> occurs = null;
//		List<BooleanQuery> subQueries = null;
//		List<Object> params = new ArrayList<Object>();
//		List<Class<?>> clazzs = new ArrayList<Class<?>>();
//
//		String[] arrParamValue = Validator.isNotNull(paramValues) ? StringUtil
//				.split(paramValues, StringPool.POUND) : null;
//		String[] arrParamTypes = Validator.isNotNull(paramTypes) ? StringUtil
//				.split(paramTypes) : null;
//
//		if (arrParamValue != null
//				&& arrParamTypes != null
//				&& arrParamTypes.length > 0
//				&& arrParamValue.length > 0
//				&& arrParamValue.length == arrParamTypes.length
//				&& accountType
//						.equals(PortletPropsValues.USERMGT_USERGROUP_NAME_EMPLOYEE)) {
//			try {
//				pattern = LuceneQueryUtil.validPattern(pattern);
//
//				if (Validator.isNull(pattern)) {
//					throw new LuceneQueryFormatException();
//				}
//
//				for (int i = 0; i < arrParamValue.length; i++) {
//					String paramType = arrParamTypes[i].toLowerCase();
//					Object param = null;
//					Class<?> clazz = null;
//					switch (paramType) {
//					case "long":
//						param = GetterUtil.getLong(arrParamValue[i]);
//						clazz = long.class;
//						break;
//					case "integer":
//						param = GetterUtil.getInteger(arrParamValue[i]);
//						clazz = int.class;
//						break;
//					case "int":
//						param = GetterUtil.getInteger(arrParamValue[i]);
//						clazz = int.class;
//						break;
//					case "short":
//						param = GetterUtil.getShort(arrParamValue[i]);
//						clazz = short.class;
//						break;
//					case "double":
//						param = GetterUtil.getDouble(arrParamValue[i]);
//						clazz = double.class;
//						break;
//					case "float":
//						param = GetterUtil.getFloat(arrParamValue[i]);
//						clazz = float.class;
//						break;
//					case "boolean":
//						param = GetterUtil.getBoolean(arrParamValue[i]);
//						clazz = boolean.class;
//						break;
//					case "date":
//						param = DateTimeUtil
//								.convertStringToDate(arrParamValue[i]);
//						clazz = Date.class;
//						break;
//					case "string":
//						param = GetterUtil.getString(arrParamValue[i]);
//						clazz = String.class;
//						break;
//					case "null":
//						param = null;
//						clazz = null;
//						break;
//					case "":
//						param = null;
//						clazz = null;
//						break;
//					case " ":
//						param = null;
//						clazz = null;
//						break;
//
//					default:
//						break;
//					}
//
//					params.add(param);
//					clazzs.add(clazz);
//				}
//
//				LuceneQueryUtil.getSubQueries(pattern, subPatterns);
//
//				if (subPatterns != null && !subPatterns.isEmpty()) {
//					subQueries = LuceneQueryUtil.createBooleanQueries(
//							subPatterns, params, paramNames, searchContext);
//
//					occurs = LuceneQueryUtil.getBooleanClauseOccurs(pattern,
//							subPatterns);
//
//					if (subQueries.size() - 1 != occurs.size()) {
//						throw new LuceneQuerySyntaxException();
//					}
//					int count = 0;
//					for (BooleanQuery booleanQuery : subQueries) {
//						if (count == 0) {
//							query.add(booleanQuery, BooleanClauseOccur.MUST);
//						} else {
//							query.add(booleanQuery, occurs.get(count - 1));
//						}
//
//						count++;
//					}
//
//					BooleanQuery ownerQuery = BooleanQueryFactoryUtil
//							.create(searchContext);
//
//					BooleanQuery rolesQuery = null;
//
//					long[] roleIds = getRolesFromSigninUser(userId, groupId);
//
//					if (roleIds != null && roleIds.length > 0) {
//
//						rolesQuery = BooleanQueryFactoryUtil
//								.create(searchContext);
//						BooleanQuery roleQuery = BooleanQueryFactoryUtil
//								.create(searchContext);
//						for (int r = 0; r < roleIds.length; r++) {
//							TermQuery roleTerm = TermQueryFactoryUtil.create(
//									searchContext, Role.class.getName()
//											.toLowerCase(), roleIds[r]);
//							roleQuery.add(roleTerm, BooleanClauseOccur.SHOULD);
//						}
//
//						// groupQuery1.add(assignessIdQuery,
//						// BooleanClauseOccur.MUST);
//						rolesQuery.add(roleQuery, BooleanClauseOccur.MUST);
//					}
//
//					BooleanQuery assignUserQuery = BooleanQueryFactoryUtil
//							.create(searchContext);
//					TermQuery assignessTerm = TermQueryFactoryUtil.create(
//							searchContext,
//							ProcessOrderDisplayTerms.ASSIGN_TO_USER_ID, userId);
//
//					assignUserQuery.add(assignessTerm, BooleanClauseOccur.MUST);
//
//					ownerQuery.add(rolesQuery, BooleanClauseOccur.SHOULD);
//					ownerQuery.add(assignUserQuery, BooleanClauseOccur.SHOULD);
//					query.add(ownerQuery, BooleanClauseOccur.MUST);
//				}
//
//			} catch (Exception e) {
//				_log.error(e);
//			} finally {
//				this.setOccurs(occurs);
//				this.setParams(params);
//				this.setPattern(pattern);
//				this.setQuery(query);
//				this.setSubPatterns(subPatterns);
//				this.setSubQueries(subQueries);
//				this.setSearchContext(searchContext);
//				this.setParamNames(paramNames);
//				this.setParamTypes(clazzs);
//			}
//		} else {
//			_log.info("########################################## Can not compare menu pattern "
//					+ pattern
//					+ " : because paramNames and paramTypes not proportional");
//		}
//
//	}

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

	protected long[] getRolesFromSigninUser(long userId, long groupId) {

		List<Role> rolesUsers = new ArrayList<Role>();

		List<Role> roleGroups;

		List<Role> resultRoles = new ArrayList<Role>();

		try {
			rolesUsers = RoleLocalServiceUtil.getUserRoles(userId);
			resultRoles.addAll(rolesUsers);
		} catch (SystemException e) {
			_log.error(e);
		}

		try {
			roleGroups = RoleLocalServiceUtil
					.getUserGroupRoles(userId, groupId);
			resultRoles.addAll(roleGroups);
		} catch (Exception e) {
			_log.error(e);
		}

		long[] result = new long[resultRoles.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = resultRoles.get(i).getRoleId();
		}

		return result;
	}

	private Log _log = LogFactoryUtil.getLog(LuceneQuery.class.getName());
}
