
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
import org.opencps.dossiermgt.action.DeliverableTypesActions;
import org.opencps.dossiermgt.action.impl.DeliverableTypesActionsImpl;
import org.opencps.dossiermgt.constants.DeliverableTerm;
import org.opencps.dossiermgt.model.DeliverableType;
import org.opencps.dossiermgt.model.DeliverableTypeRole;
import org.opencps.dossiermgt.service.DeliverableTypeLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Arrays;
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

//	public static DeliverableModel mappingToDeliverable(Document doc) {
//
//		DeliverableModel model = new DeliverableModel();
//
//		doc.getFields();
//		
//		
//		
//		model.setDeliverableId(
//			Long.valueOf(doc.get(DeliverableTerm.DELIVERABLE_ID)));
//		model.setUserId(Long.valueOf(doc.get(Field.USER_ID)));
//		model.setUserName(doc.get(Field.USER_NAME));
//		// Convert Date to String
//		String strCreateDate = doc.get(Field.CREATE_DATE);
//		Date createDate = null;
//		if (Validator.isNotNull(strCreateDate)) {
//			createDate = APIDateTimeUtils.convertStringToDate(
//				strCreateDate, APIDateTimeUtils._LUCENE_PATTERN);
//		}
//		model.setCreateDate(
//			createDate != null
//				? APIDateTimeUtils.convertDateToString(
//					createDate, APIDateTimeUtils._TIMESTAMP)
//				: strCreateDate);
//
//		String strModifiedDate = doc.get(Field.MODIFIED_DATE);
//		Date modifiedDate = null;
//		if (Validator.isNotNull(strModifiedDate)) {
//			modifiedDate = APIDateTimeUtils.convertStringToDate(
//				strModifiedDate, APIDateTimeUtils._LUCENE_PATTERN);
//		}
//		model.setModifiedDate(
//			modifiedDate != null
//				? APIDateTimeUtils.convertDateToString(
//					modifiedDate, APIDateTimeUtils._TIMESTAMP)
//				: strModifiedDate);
//
//		model.setDeliverableCode(doc.get(DeliverableTerm.DELIVERABLE_CODE));
//		model.setDeliverableName(doc.get(DeliverableTerm.DELIVERABLE_NAME));
//		model.setDeliverableType(doc.get(DeliverableTerm.DELIVERABLE_TYPE));
//		model.setGovAgencyCode(doc.get(DeliverableTerm.GOV_AGENCY_CODE));
//		model.setGovAgencyName(doc.get(DeliverableTerm.GOV_AGENCY_NAME));
//		model.setApplicantIdNo(doc.get(DeliverableTerm.APPLICANT_ID_NO));
//		model.setApplicantName(doc.get(DeliverableTerm.APPLICANT_NAME));
//		model.setSubject(doc.get(DeliverableTerm.SUBJECT));
//		// Convert Date to String
//		String strIssueDate = doc.get(DeliverableTerm.ISSUE_DATE);
//		if (Validator.isNotNull(strIssueDate)) {
//			Date issueDate = APIDateTimeUtils.convertStringToDate(
//				strIssueDate, APIDateTimeUtils._LUCENE_PATTERN);
//			model.setIssueDate(
//				APIDateTimeUtils.convertDateToString(
//					issueDate, APIDateTimeUtils._NORMAL_PARTTERN));
//		}
//		else {
//			model.setIssueDate(strIssueDate);
//		}
//
//		String strExpireDate = doc.get(DeliverableTerm.EXPIRE_DATE);
//		if (Validator.isNotNull(strExpireDate)) {
//			Date expireDate = APIDateTimeUtils.convertStringToDate(
//				strExpireDate, APIDateTimeUtils._LUCENE_PATTERN);
//			model.setExpireDate(
//				APIDateTimeUtils.convertDateToString(
//					expireDate, APIDateTimeUtils._NORMAL_PARTTERN));
//		}
//		else {
//			model.setExpireDate(strExpireDate);
//		}
//
//		String strRevalidate = doc.get(DeliverableTerm.REVALIDATE);
//		Date revalidate = null;
//		if (Validator.isNotNull(strRevalidate)) {
//			revalidate = APIDateTimeUtils.convertStringToDate(
//				strRevalidate, APIDateTimeUtils._LUCENE_PATTERN);
//		}
//		model.setRevalidate(
//			revalidate != null
//				? APIDateTimeUtils.convertDateToString(
//					revalidate, APIDateTimeUtils._TIMESTAMP)
//				: strRevalidate);
//
//		model.setDeliverableState(doc.get(DeliverableTerm.DELIVERABLE_STATE));
//
//		return model;
//	}

	private static Log _log = LogFactoryUtil.getLog(DeliverableUtils.class);

}
