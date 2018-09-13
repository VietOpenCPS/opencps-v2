package org.opencps.dossiermgt.service.indexer;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.opencps.dossiermgt.action.util.SpecialCharacterUtils;
import org.opencps.dossiermgt.constants.StepConfigTerm;
import org.opencps.dossiermgt.model.StepConfig;
import org.opencps.dossiermgt.service.StepConfigLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

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

@Component(
    immediate = true,
    service = BaseIndexer.class
)
public class StepConfigIndexer extends BaseIndexer<StepConfig> {
	public static final String CLASS_NAME = StepConfig.class.getName();

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	protected void doDelete(StepConfig object) throws Exception {
		deleteDocument(object.getCompanyId(), object.getPrimaryKey());
	}

	@Override
	protected Document doGetDocument(StepConfig object) throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, object);
		// Indexer of audit fields
		document.addNumberSortable(Field.COMPANY_ID, object.getCompanyId());
		document.addNumberSortable(Field.GROUP_ID, object.getGroupId());
		document.addDateSortable(Field.MODIFIED_DATE, object.getCreateDate());
		document.addDateSortable(Field.CREATE_DATE, object.getModifiedDate());
		document.addNumberSortable(Field.USER_ID, object.getUserId());
		document.addKeywordSortable(Field.ENTRY_CLASS_NAME, CLASS_NAME);
		document.addNumberSortable(Field.ENTRY_CLASS_PK, object.getPrimaryKey());

		// Indexer of custom fields
		document.addTextSortable(StepConfigTerm.STEP_CODE, object.getStepCode());
		document.addTextSortable(StepConfigTerm.STEP_NAME, object.getStepName());
		document.addNumberSortable(StepConfigTerm.STEP_TYPE, object.getStepType());
		document.addTextSortable(StepConfigTerm.DOSSIER_STATUS, object.getDossierStatus());
		document.addTextSortable(StepConfigTerm.DOSSIER_SUB_STATUS, object.getDossierSubStatus());
		document.addTextSortable(StepConfigTerm.MENU_GROUP, object.getMenuGroup());
		document.addTextSortable(StepConfigTerm.MENU_STEP_NAME, object.getMenuStepName());
		document.addTextSortable(StepConfigTerm.BUTTON_CONFIG, object.getButtonConfig());
		
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
		StepConfig object = StepConfigLocalServiceUtil.fetchStepConfig(classPK);
		doReindex(object);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);
		reindex(companyId);
	}

	@Override
	protected void doReindex(StepConfig object) throws Exception {
		Document document = getDocument(object);
		IndexWriterHelperUtil.updateDocument(getSearchEngineId(), object.getCompanyId(), document,
				isCommitImmediately());

	}

	protected void reindex(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = StepConfigLocalServiceUtil
				.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery
				.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<StepConfig>() {

					@Override
					public void performAction(StepConfig object) {
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

	Log _log = LogFactoryUtil.getLog(StepConfigIndexer.class);

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
					_log.error(e);
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
