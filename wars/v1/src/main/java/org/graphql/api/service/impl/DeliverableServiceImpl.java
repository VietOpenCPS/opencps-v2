package org.graphql.api.service.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.BooleanQueryFactoryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.IndexSearcherHelperUtil;
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
import com.liferay.portal.kernel.util.Validator;

import org.opencps.dossiermgt.action.util.SpecialCharacterUtils;
import org.opencps.dossiermgt.constants.ConstantsTerm;
import org.opencps.dossiermgt.constants.DeliverableTerm;
import org.opencps.dossiermgt.model.Deliverable;
import org.opencps.dossiermgt.service.base.DossierLocalServiceBaseImpl;

/**
 * The implementation of the dossier local service. <p> All custom service
 * methods should be put in this class. Whenever methods are added, rerun
 * ServiceBuilder to copy their definitions into the
 * {@link org.opencps.dossiermgt.service.DossierLocalService} interface. <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM. </p>
 *
 * @author huymq
 * @see DossierLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.DossierLocalServiceUtil
 */
public class DeliverableServiceImpl {

	protected Log _log = LogFactoryUtil.getLog(DeliverableServiceImpl.class);

	@SuppressWarnings("deprecation")
	public Hits searchLucene(String keywords, String groupId, String type, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {

		Indexer<Deliverable> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Deliverable.class);

		searchContext.addFullQueryEntryClassName(CLASS_NAME);
		searchContext.setEntryClassNames(new String[] { CLASS_NAME });
		searchContext.setAttribute(ConstantsTerm.PAGINATION_TYPE, ConstantsTerm.REGULAR);
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

		// Search follow params default
		BooleanQuery booleanCommon = processSearchCommon(
			keywords, groupId, type, booleanQuery);
		// Search follow param input
		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.search(searchContext, booleanCommon);
	}

	@SuppressWarnings("deprecation")
	public long countLucene(
		String keywords, String groupId, String type, SearchContext searchContext)
		throws ParseException, SearchException {

		Indexer<Deliverable> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Deliverable.class);

		searchContext.addFullQueryEntryClassName(CLASS_NAME);
		searchContext.setEntryClassNames(new String[] { CLASS_NAME });
		searchContext.setAttribute(ConstantsTerm.PAGINATION_TYPE, ConstantsTerm.REGULAR);
		searchContext.setLike(true);
		searchContext.setAndSearch(true);

		BooleanQuery booleanQuery = null;

		if (Validator.isNotNull(keywords)) {
			booleanQuery = BooleanQueryFactoryUtil.create(searchContext);
		} else {
			booleanQuery = indexer.getFullQuery(searchContext);
		}

		// Search follow params default
		BooleanQuery booleanCommon = processSearchCommon(
			keywords, groupId, type, booleanQuery);
		// Search follow param input
		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanCommon);
	}

	private BooleanQuery processSearchCommon(String keywords, String groupId, String type, BooleanQuery booleanQuery)
			throws ParseException {

		// LamTV: Process search LIKE
		if (Validator.isNotNull(keywords)) {
			BooleanQuery queryBool = new BooleanQueryImpl();
			String[] subQuerieArr = new String[] {
					DeliverableTerm.DELIVERABLE_TYPE, DeliverableTerm.DELIVERABLE_NAME,
					DeliverableTerm.GOV_AGENCY_NAME, DeliverableTerm.APPLICANT_NAME,
					DeliverableTerm.DELIVERABLE_CODE_SEARCH
			};

			String keySearch = SpecialCharacterUtils.splitSpecial(keywords);
			String[] keywordArr = keySearch.split(StringPool.SPACE);
			for (String fieldSearch : subQuerieArr) {
				BooleanQuery query = new BooleanQueryImpl();
				for (String key : keywordArr) {
					WildcardQuery wildQuery = new WildcardQueryImpl(
						fieldSearch,
						StringPool.STAR + key.toLowerCase() + StringPool.STAR);
					query.add(wildQuery, BooleanClauseOccur.MUST);
				}
				queryBool.add(query, BooleanClauseOccur.SHOULD);
			}
			booleanQuery.add(queryBool, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(String.valueOf(groupId));
			query.addFields(Field.GROUP_ID);
			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(type)) {
			MultiMatchQuery query = new MultiMatchQuery(type);
			query.addFields(DeliverableTerm.DELIVERABLE_TYPE);
			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		return booleanQuery;
	}

	private static final String CLASS_NAME = Deliverable.class.getName();
}