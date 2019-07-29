package org.opencps.dossiermgt.service.indexer;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexWriterHelperUtil;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.opencps.dossiermgt.action.util.SpecialCharacterUtils;
import org.opencps.dossiermgt.constants.DeliverableTerm;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.constants.ModelKeysDeliverable;
import org.opencps.dossiermgt.model.Deliverable;
import org.opencps.dossiermgt.service.DeliverableLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

@Component(
    immediate = true,
    service = BaseIndexer.class
)
public class DeliverableIndexer extends BaseIndexer<Deliverable> {
	public static final String CLASS_NAME = Deliverable.class.getName();

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	protected void doDelete(Deliverable object) throws Exception {
		deleteDocument(object.getCompanyId(), object.getPrimaryKey());
	}

	@Override
	protected Document doGetDocument(Deliverable object) throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, object);

		// Indexer of audit fields
		document.addNumberSortable(Field.COMPANY_ID, object.getCompanyId());
		document.addNumberSortable(Field.GROUP_ID, object.getGroupId());
		document.addDateSortable(Field.MODIFIED_DATE, object.getCreateDate());
		document.addDateSortable(Field.CREATE_DATE, object.getModifiedDate());
		document.addNumberSortable(Field.USER_ID, object.getUserId());
		document.addKeywordSortable(Field.USER_NAME, String.valueOf(object.getUserName()));
		document.addKeywordSortable(Field.ENTRY_CLASS_NAME, CLASS_NAME);
		document.addNumberSortable(Field.ENTRY_CLASS_PK, object.getPrimaryKey());

		// add number fields
		document.addNumberSortable(DeliverableTerm.DELIVERABLE_ID, object.getDeliverableId());
		if (Validator.isNotNull(object.getIssueDate())) {
			document.addDateSortable(DeliverableTerm.ISSUE_DATE, object.getIssueDate());			
		}
		if (Validator.isNotNull(object.getExpireDate())) {
			document.addDateSortable(DeliverableTerm.EXPIRE_DATE, object.getExpireDate());			
		}
		
		if (Validator.isNotNull(object.getRevalidate())) {
			document.addDateSortable(DeliverableTerm.REVALIDATE, object.getRevalidate());			
		}

		// add text fields
		String deliverableCode = object.getDeliverableCode();
		if (Validator.isNotNull(deliverableCode)) {
			document.addTextSortable(DeliverableTerm.DELIVERABLE_CODE, deliverableCode);
			document.addTextSortable(DeliverableTerm.DELIVERABLE_CODE_SEARCH, SpecialCharacterUtils.splitSpecial(deliverableCode));
		} else {
			document.addTextSortable(DeliverableTerm.DELIVERABLE_CODE, StringPool.BLANK);
			document.addTextSortable(DeliverableTerm.DELIVERABLE_CODE_SEARCH, StringPool.BLANK);
		}
		
		if (Validator.isNotNull(object.getDeliverableName())) {
			document.addTextSortable(DeliverableTerm.DELIVERABLE_NAME, object.getDeliverableName());			
		}
		if (Validator.isNotNull(object.getDeliverableType())) {
			document.addTextSortable(DeliverableTerm.DELIVERABLE_TYPE, object.getDeliverableType());			
		}
		if (Validator.isNotNull(object.getGovAgencyCode())) {
			document.addTextSortable(DeliverableTerm.GOV_AGENCY_CODE, object.getGovAgencyCode());			
		}
		if (Validator.isNotNull(object.getGovAgencyName())) {
			document.addTextSortable(DeliverableTerm.GOV_AGENCY_NAME, object.getGovAgencyName());			
		}
		if (Validator.isNotNull(object.getApplicantIdNo())) {
			document.addTextSortable(DeliverableTerm.APPLICANT_ID_NO, object.getApplicantIdNo());			
		}
		if (Validator.isNotNull(object.getApplicantName())) {
			document.addTextSortable(DeliverableTerm.APPLICANT_NAME, object.getApplicantName());			
		}
		if (Validator.isNotNull(object.getSubject())) {
			document.addTextSortable(DeliverableTerm.SUBJECT, object.getSubject());			
		}
		if (Validator.isNotNull(object.getFormData())) {
			document.addTextSortable(DeliverableTerm.FORM_DATA, object.getFormData());			
		}

		document.addNumberSortable(ModelKeysDeliverable.ISSUEDATE, Validator.isNotNull(object.getIssueDate()) ? object.getIssueDate().getTime() : null);
		document.addNumberSortable(ModelKeysDeliverable.EXPIREDATE, Validator.isNotNull(object.getExpireDate()) ? object.getExpireDate().getTime(): null);
		document.addNumberSortable(ModelKeysDeliverable.REVALIDATE, Validator.isNotNull(object.getRevalidate()) ? object.getRevalidate().getTime() : null);
		
		document.addDateSortable(ModelKeysDeliverable.ISSUEDATE + "_date", object.getIssueDate());
		document.addDateSortable(ModelKeysDeliverable.EXPIREDATE + "_date", object.getExpireDate());
		document.addDateSortable(ModelKeysDeliverable.REVALIDATE + "_date", object.getRevalidate());
		
		document.addNumberSortable(ModelKeysDeliverable.DELIVERABLESTATE, object.getDeliverableState());
		document.addNumberSortable(ModelKeysDeliverable.FILEENTRYID, object.getFileEntryId());
		document.addNumberSortable(ModelKeysDeliverable.DOCSYNC, object.getDocSync());
		document.addNumberSortable(ModelKeysDeliverable.DOSSIERID, object.getDossierId());
		
		// add form data detail
		String formData = object.getFormData();
		if (Validator.isNotNull(formData)) {
			List<Object[]> keyValues = new ArrayList<Object[]>();

			keyValues = getKeyValues(formData, keyValues);

			if (keyValues != null) {
				for (Object[] keyValue : keyValues) {
//					_log.info("=========DELIVERABLE_INDEX_FORM_DATA========:" + keyValue[0] + "_" + keyValue[1]);
//                    document.addKeyword(
//                        keyValue[0].toString() + "_data", keyValue[1].toString());
					document.addKeyword(keyValue[0].toString().toLowerCase()+ "_data",
							keyValue[1].toString().toLowerCase());
				}
			}
		}

		try {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject(object.getFormData());

			Iterator<String> keys = jsonObject.keys();

			while(keys.hasNext()) {
			    String key = keys.next();
			    document.addTextSortable(key+ "_data", jsonObject.getString(key));
			}
		} catch (Exception e) {
			_log.error(e);
		}
		
		if (Validator.isNotNull(object.getFormScript())) {
			document.addTextSortable(DeliverableTerm.FORM_SCRIPT, object.getFormScript());			
		}
		if (Validator.isNotNull(object.getFormReport())) {
			document.addTextSortable(DeliverableTerm.FORM_REPORT, object.getFormReport());			
		}
		if (Validator.isNotNull(object.getDeliverableState())) {
			document.addNumberSortable(DeliverableTerm.DELIVERABLE_STATE, object.getDeliverableState());			
		}

		return document;
	}

	@Override
	protected Summary doGetSummary(Document document, Locale locale, String snippet, PortletRequest portletRequest,
			PortletResponse portletResponse) throws Exception {
		Summary summary = createSummary(document);

		summary.setMaxContentLength(QueryUtil.ALL_POS);

		return summary;
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		Deliverable object = DeliverableLocalServiceUtil.getDetailById(classPK);
		doReindex(object);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);
		reindex(companyId);
	}

	@Override
	protected void doReindex(Deliverable object) throws Exception {
		Document document = getDocument(object);
		IndexWriterHelperUtil.updateDocument(getSearchEngineId(), object.getCompanyId(), document,
				isCommitImmediately());

	}

	protected void reindex(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = DeliverableLocalServiceUtil
				.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery
				.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<Deliverable>() {

					@Override
					public void performAction(Deliverable object) {
						try {
							Document document = getDocument(object);

							indexableActionableDynamicQuery.addDocuments(document);
						} catch (PortalException pe) {
							if (_log.isWarnEnabled()) {
								_log.warn("Unable to index contact " + object.getPrimaryKey(), pe);
							}
						}
					}

				});
		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		indexableActionableDynamicQuery.performActions();
	}

	Log _log = LogFactoryUtil.getLog(DeliverableIndexer.class);

	protected List<Object[]> getKeyValues(String formData,
			List<Object[]> keyValues) {

		try {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject(formData);
			parseJSONObject(keyValues, jsonObject);
		} catch (Exception e) {
			_log.error(e);
			_log.info("Can not parse json object from FormData: =>"
					+ " : Cause " + e.getCause());
		}

		return keyValues;

	}

	/**
	 * @param keyValues
	 * @param JSONObject
	 * @return
	 * @throws JSONException
	 */
	protected List<Object[]> parseJSONObject(List<Object[]> keyValues, JSONObject json) {

//		List<Object[]> objects = new ArrayList<Object[]>();
		if (json != null) {
			Iterator<String> itr = json.keys();
			while (itr.hasNext()) {
				String key = itr.next();
				String strObject = String.valueOf(json.get(key));
				// check json
				try {
					JSONObject valueObject = JSONFactoryUtil.createJSONObject(strObject);
					Object[] keyValue = new Object[2];
					keyValue[0] = key;
					if (Validator.isNotNull(valueObject.toString())) {
						keyValue[1] = SpecialCharacterUtils.splitSpecial(valueObject.toString());
//						keyValue[1] = valueObject.toString().replaceAll(Pattern.quote("/"), "_").replaceAll(Pattern.quote("-"), "_");
					} else {
						keyValue[1] = valueObject.toString();
					}
					keyValues.add(keyValue);
					parseJSONObjectIndex(keyValues, json.getJSONObject(key), key);
				} catch (JSONException e) {
					_log.debug(e);
					// string
					Object[] keyValue = new Object[2];
					keyValue[0] = key;
					if (Validator.isNotNull(strObject.toString())) {
//						keyValue[1] = strObject.toString().replaceAll(Pattern.quote("/"), "_").replaceAll(Pattern.quote("-"), "_");
						keyValue[1] = SpecialCharacterUtils.splitSpecial(strObject.toString());
					} else {
						keyValue[1] = strObject.toString();
					}
					keyValues.add(keyValue);
				}
			}
		}

		return keyValues;
	}

	//
	protected List<Object[]> parseJSONObjectIndex(List<Object[]> keyValues, JSONObject json, String keyJson) {

//		List<Object[]> objects = new ArrayList<Object[]>();

		if (json != null) {
			Iterator<String> itr = json.keys();
			while (itr.hasNext()) {
				String key = itr.next();
				String strObject = String.valueOf(json.get(key));
				// check json
				try {
					JSONObject valueObject = JSONFactoryUtil.createJSONObject(strObject);
					Object[] keyValue = new Object[2];
					keyValue[0] = keyJson + "@" + key;
					if (Validator.isNotNull(valueObject.toString())) {
//						keyValue[1] = valueObject.toString().replaceAll(Pattern.quote("/"), "_").replaceAll(Pattern.quote("-"), "_");
						keyValue[1] = SpecialCharacterUtils.splitSpecial(valueObject.toString());
					} else {
						keyValue[1] = valueObject.toString();
					}
					keyValues.add(keyValue);
					parseJSONObjectIndex(keyValues, json.getJSONObject(key), keyValue[0].toString());
				} catch (JSONException e) {
					_log.error(e);
					// string
					Object[] keyValue = new Object[2];
					keyValue[0] = keyJson + "@" + key;
					if (Validator.isNotNull(strObject.toString())) {
//						keyValue[1] = strObject.toString().replaceAll(Pattern.quote("/"), "_").replaceAll(Pattern.quote("-"), "_");
						keyValue[1] = SpecialCharacterUtils.splitSpecial(strObject.toString());
					} else {
						keyValue[1] = strObject.toString();
					}
					keyValues.add(keyValue);
				}
			}
		}

		return keyValues;
	}

//	protected List<Object[]> parseJSONObject(List<Object[]> keyValues, JSONArray jsonArray) throws JSONException {
//
//		if (jsonArray != null && jsonArray.length() > 0) {
//			for (int i = 0; i < jsonArray.length(); i++) {
//				String tempObject = String.valueOf(jsonArray.get(i));
//				try {
//					JSONObject valueObject = JSONFactoryUtil.createJSONObject(tempObject);
//					parseJSONObject(keyValues, valueObject);
//				} catch (JSONException e) {
//					// check json array
//					try {
//						JSONArray jsonArr = jsonArray.getJSONArray(i);
//						parseJSONObject(keyValues, jsonArr);
//					} catch (JSONException e1) {
//						// Tinh chung cho key cha.
//					}
//				}
//			}
//		}
//		return keyValues;
//	}
	
}
