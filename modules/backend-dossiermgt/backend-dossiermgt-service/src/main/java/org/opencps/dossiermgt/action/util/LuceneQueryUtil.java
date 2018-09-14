package org.opencps.dossiermgt.action.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.BooleanQueryFactoryUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.TermQuery;
import com.liferay.portal.kernel.search.TermQueryFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.opencps.dossiermgt.model.Dossier;

/**
 * @author trungnt
 *
 */
public class LuceneQueryUtil {

	private static Log _log = LogFactoryUtil.getLog(LuceneQueryUtil.class
			.getName());

	/**
	 * @param query
	 * @param key
	 * @param param
	 * @return
	 */
	protected static BooleanQuery addExactTerm(BooleanQuery query, String key,
			Object param) {
		if (param instanceof Long) {
			query.addExactTerm(key, (long) (param));
		} else if (param instanceof Integer) {
			query.addExactTerm(key, (int) (param));
		} else if (param instanceof Short) {
			query.addExactTerm(key, (short) (param));
		} else if (param instanceof Double) {
			query.addExactTerm(key, (double) (param));
		} else if (param instanceof Float) {
			query.addExactTerm(key, (float) (param));
		} else if (param instanceof Boolean) {
			query.addExactTerm(key, (boolean) (param));
		} else if (param instanceof String) {
			query.addExactTerm(key, (String) (param));
		}

		return query;
	}

	/**
	 * @param query
	 * @param key
	 * @param param
	 * @return
	 */
	protected static BooleanQuery addRangeTerm(BooleanQuery query, String key,
			Object param) {
		if (param instanceof long[]) {
			long[] temp = (long[]) param;
			if (temp != null && temp.length == 2) {
				query.addRangeTerm(key, temp[0], temp[1]);
			}

		} else if (param instanceof int[]) {
			int[] temp = (int[]) param;
			if (temp != null && temp.length == 2) {
				query.addRangeTerm(key, temp[0], temp[1]);
			}

		} else if (param instanceof short[]) {
			short[] temp = (short[]) param;
			if (temp != null && temp.length == 2) {
				query.addRangeTerm(key, temp[0], temp[1]);
			}

		} else if (param instanceof Date[]) {
			Date[] temp = (Date[]) param;
			if (temp != null && temp.length == 2) {
				query.addRangeTerm(key, temp[0].getTime(), temp[1].getTime());
			}

		}
		return query;
	}

	/**
	 * @param pattern
	 * @param params
	 * @param searchContext
	 * @return
	 */

	public static BooleanQuery buildQuerySearch(String pattern,
			List<Object> params, SearchContext searchContext) {
		searchContext
				.setEntryClassNames(new String[] { Dossier.class.getName() });

		Indexer indexer = IndexerRegistryUtil.getIndexer(Dossier.class
				.getName());

		searchContext.setSearchEngineId(indexer.getSearchEngineId());

		BooleanQuery query = BooleanQueryFactoryUtil.create(searchContext);
		List<String> subQueries = new ArrayList<String>();
		try {
			pattern = validPattern(pattern);

			if (Validator.isNull(pattern)) {
				throw new Exception();
			}
			getSubQueries(pattern, subQueries);

			if (subQueries != null && !subQueries.isEmpty()) {
				List<BooleanQuery> booleanQueries = createBooleanQueries(
						subQueries, params, searchContext);

				List<BooleanClauseOccur> conditions = getBooleanClauseOccurs(
						pattern, subQueries);

				if (booleanQueries.size() - 1 != conditions.size()) {
					throw new Exception();
				}
				int count = 0;
				for (BooleanQuery booleanQuery : booleanQueries) {
					if (count == 0) {
						query.add(booleanQuery, BooleanClauseOccur.MUST);
					} else {
						query.add(booleanQuery, conditions.get(count - 1));
					}

					count++;
				}
			}

			TermQuery entryClassNameTerm = TermQueryFactoryUtil.create(
					searchContext, "entryClassName", Dossier.class.getName());

			query.add(entryClassNameTerm, BooleanClauseOccur.MUST);

		} catch (Exception e) {
			_log.error(e);
		}

		return query;
	}

	/**
	 * @param subQueries
	 * @param params
	 * @param searchContext
	 * @return
	 * @throws ParseException
	 */
	public static List<BooleanQuery> createBooleanQueries(
			List<String> subQueries, List<Object> params,
			SearchContext searchContext) throws ParseException {
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
						String key;
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
							} else if (tempValue instanceof String) {
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
							query = addRangeTerm(query, key,
									params.get(paramPossition));
						}

					}

					booleanQueries.add(query);
				}
			}
		}
		return booleanQueries;
	}

	/**
	 * @param subQueries
	 * @param params
	 * @param paramNames
	 * @param searchContext
	 * @return
	 * @throws ParseException
	 */
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
							query = addRangeTerm(query, key,
									params.get(paramPossition));
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

	public static List<BooleanClauseOccur> getBooleanClauseOccurs(
			String pattern, List<String> subQueries) {
		List<BooleanClauseOccur> booleanClauseOccurs = new ArrayList<BooleanClauseOccur>();
		pattern = pattern.replaceAll("\\(", StringPool.BLANK);

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
				if ("and".equalsIgnoreCase(conditions[c])) {
					booleanClauseOccurs.add(BooleanClauseOccur.MUST);
				} else if ("or".equalsIgnoreCase(conditions[c])) {
					booleanClauseOccurs.add(BooleanClauseOccur.SHOULD);
				} else if ("not".equalsIgnoreCase(conditions[c])) {
					booleanClauseOccurs.add(BooleanClauseOccur.MUST_NOT);
				}
			}
		}

		return booleanClauseOccurs;
	}

	/**
	 * @param levels
	 * @param names
	 * @param patterns
	 * @param params
	 * @param paramTypes
	 * @return
	 */
//	public static List<LuceneMenuSchema> getLuceneMenuSchemas(String[] levels,
//			String[] names, String[] patterns, String[] params,
//			String[] paramTypes) {
//		List<LuceneMenuSchema> luceneMenuSchemas = new ArrayList<LuceneMenuSchema>();
//		if (levels != null && names != null && patterns != null
//				&& params != null && paramTypes != null) {
//			int length = levels.length;
//			if (names.length == length && patterns.length == length
//					&& params.length == length && paramTypes.length == length) {
//				for (int i = 0; i < length; i++) {
//					int level = GetterUtil.getInteger(levels[i]);
//					LuceneMenuSchema luceneMenuSchema = new LuceneMenuSchema(
//							names[i], level, patterns[i], params[i],
//							paramTypes[i]);
//					luceneMenuSchemas.add(luceneMenuSchema);
//				}
//			}
//		}
//
//		return luceneMenuSchemas;
//	}

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

}
