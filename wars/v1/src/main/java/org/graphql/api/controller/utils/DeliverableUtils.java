
package org.graphql.api.controller.utils;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.graphql.api.model.DeliverableTypeDynamic;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.dossiermgt.action.DeliverableTypesActions;
import org.opencps.dossiermgt.action.impl.DeliverableTypesActionsImpl;
import org.opencps.dossiermgt.constants.DeliverableTerm;
import org.opencps.dossiermgt.constants.DeliverableTypesTerm;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.constants.EFormTerm;
import org.opencps.dossiermgt.model.Deliverable;
import org.opencps.dossiermgt.model.DeliverableModel;
import org.opencps.dossiermgt.model.DeliverableType;
import org.opencps.dossiermgt.model.DeliverableTypeRole;
import org.opencps.dossiermgt.service.DeliverableTypeLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class DeliverableUtils {

//	public static List<DeliverableModel> mappingToDeliverableResultModel(
//		List<Document> documents) {
//
//		List<DeliverableModel> data = new ArrayList<DeliverableModel>();
//
//		for (Document doc : documents) {
//			DeliverableModel model = mappingToDeliverable(doc);
//			data.add(model);
//		}
//
//		return data;
//	}

	public static JSONArray mappingToDeliverableResult(List<Document> documents, long userId, long groupId) {

		JSONArray data = JSONFactoryUtil.createJSONArray();

		for (Document doc : documents) {
			JSONObject model = mappingToDeliverable(doc, userId, groupId);
			data.put(model);
		}

		return data;
	}

	public static JSONObject mappingToDeliverable(Document doc, long userId, long groupId) {

		JSONObject model = JSONFactoryUtil.createJSONObject();
		DeliverableTypesActions actions = new DeliverableTypesActionsImpl();
		try {
			User user = UserLocalServiceUtil.getUser(userId);
			Long[] longObjects = ArrayUtils.toObject(user.getRoleIds());
			List<Long> roleIds = Arrays.asList(longObjects);
			Map<String, Field> mapDoc = doc.getFields();
			if (mapDoc != null && mapDoc.size() > 0) {
				for (Map.Entry<String, Field> entry : mapDoc.entrySet()) {
					String key = entry.getKey();
					String value = entry.getValue().getValue();
					if (key.equals(DeliverableTerm.DELIVERABLE_TYPE)) {
						DeliverableType deliverableType = DeliverableTypeLocalServiceUtil.fetchByG_DLT(groupId, value);
						if (Validator.isNotNull(deliverableType)) {
							List<DeliverableTypeRole> deliverableTypeRoles = actions.getRolesByType(deliverableType.getDeliverableTypeId());
							if (deliverableTypeRoles != null && deliverableTypeRoles.size() > 0) {
								for (DeliverableTypeRole deliverableTypeRole : deliverableTypeRoles) {
									if (roleIds.contains(deliverableTypeRole.getRoleId())) {
										if (deliverableTypeRole.getModerator()) {
											model.put(DeliverableTerm.MODERATOR, deliverableTypeRole.getModerator());
										}
									}
								}
							}
						}
					}
					model.put(entry.getKey(), entry.getValue().getValue());
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return model;
	}


	public static JSONObject mappingToDeliverable(Deliverable deliverable) {

		JSONObject model = JSONFactoryUtil.createJSONObject();

		model.put(DeliverableTerm.DELIVERABLE_ID,deliverable.getDeliverableId());
		model.put(DossierTerm.USER_ID,deliverable.getUserId());
		model.put(DossierTerm.GROUP_ID,deliverable.getGroupId());
		model.put(Field.USER_NAME,deliverable.getUserName());
		model.put(Field.CREATE_DATE,deliverable.getCreateDate());
		model.put(Field.MODIFIED_DATE,deliverable.getModifiedDate());
		model.put(DeliverableTerm.DELIVERABLE_CODE,deliverable.getDeliverableCode());
		model.put(DeliverableTerm.DELIVERABLE_NAME,deliverable.getDeliverableName());
		model.put(DeliverableTerm.DELIVERABLE_TYPE,deliverable.getDeliverableType());
		model.put(DeliverableTerm.GOV_AGENCY_CODE,deliverable.getGovAgencyCode());
		model.put(DeliverableTerm.GOV_AGENCY_NAME,deliverable.getGovAgencyName());
		model.put(DeliverableTerm.APPLICANT_ID_NO,deliverable.getApplicantIdNo());
		model.put(DeliverableTerm.APPLICANT_NAME,deliverable.getApplicantName());
		model.put(DeliverableTerm.SUBJECT,deliverable.getSubject());
		Date strIssueDate = deliverable.getIssueDate();
		if (Validator.isNotNull(strIssueDate)) {
			model.put(DeliverableTerm.ISSUE_DATE, strIssueDate);
		}
		else {
			model.put(DeliverableTerm.ISSUE_DATE,strIssueDate);
		}

		Date strExpireDate = deliverable.getExpireDate();
		if (Validator.isNotNull(strExpireDate)) {
			model.put(DeliverableTerm.EXPIRE_DATE,
					strExpireDate);
		}
		else {
			model.put(DeliverableTerm.EXPIRE_DATE, strExpireDate);
		}

		model.put(DeliverableTerm.REVALIDATE,deliverable.getRevalidate());
		model.put(DeliverableTerm.DELIVERABLE_STATE, deliverable.getDeliverableState());
		model.put(DeliverableTerm.FORM_DATA, deliverable.getFormData());
		model.put(DeliverableTerm.FORM_REPORT,deliverable.getFormReport());
		model.put(EFormTerm.FORM_REPORT_FILE_ID,deliverable.getFormReportFileId());
		model.put(DeliverableTerm.FORM_SCRIPT, deliverable.getFormScript());
		model.put(EFormTerm.FORM_SCRIPT_FILE_ID, deliverable.getFormScriptFileId());
		model.put(DeliverableTerm.FILE_ENTRY_ID,deliverable.getFileEntryId());
		model.put(DeliverableTerm.FILE_ATTACHS,deliverable.getFileAttachs());
		model.put(DeliverableTypesTerm.DOC_SYNC,deliverable.getDocSync());
		model.put(DossierTerm.DOSSIER_ID,deliverable.getDossierId());

		return model;
	}

	private static Log _log = LogFactoryUtil.getLog(DeliverableUtils.class);

}
