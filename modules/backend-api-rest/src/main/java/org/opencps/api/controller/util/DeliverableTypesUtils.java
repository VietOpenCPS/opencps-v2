
package org.opencps.api.controller.util;

import java.util.ArrayList;
import java.util.List;

import org.opencps.api.deliverabletype.model.DeliverableTypeDetailModel;
import org.opencps.api.deliverabletype.model.DeliverableTypesModel;
import org.opencps.api.dossierlog.model.DossierLogSearchIdModel;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.dossiermgt.constants.DossierLogTerm;
import org.opencps.dossiermgt.model.DeliverableType;

import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

public class DeliverableTypesUtils {

	public static List<DeliverableTypeDetailModel> mappingToDeliverableTypesData(List<DeliverableType> lstdeliverableType) {

		List<DeliverableTypeDetailModel> outputs = new ArrayList<DeliverableTypeDetailModel>();

		for (DeliverableType deliverableType : lstdeliverableType) {

			DeliverableTypeDetailModel model = mappingToDeliverableTypesModel(deliverableType);

			outputs.add(model);
		}

		return outputs;
	}

	public static DeliverableTypeDetailModel mappingToDeliverableTypesModel(DeliverableType deliverableType) {

		if (deliverableType == null) {
			return null;
		}
		DeliverableTypeDetailModel model = new DeliverableTypeDetailModel();

		model.setDeliverableType(deliverableType.getTypeCode());
		model.setDeliverableName(deliverableType.getTypeName());
		model.setCodePattern(deliverableType.getCodePattern());
		model.setCounter(Long.valueOf(deliverableType.getCounter()));

		return model;
	}

	public static List<DeliverableTypesModel> mappingToDeliverableTypesResultsModel(
			List<DeliverableType> lstdeliverableType) {
		List<DeliverableTypesModel> outputs = new ArrayList<DeliverableTypesModel>();
		if(Validator.isNotNull(lstdeliverableType)){
			for (DeliverableType deliverableType : lstdeliverableType) {
	
				DeliverableTypesModel model = new DeliverableTypesModel();
	
				long deliverableTypeId = GetterUtil.getLong(deliverableType.getDeliverableTypeId());
				long counter = GetterUtil.getLong(deliverableType.getCounter());
	
				model.setDeliverableTypeId(deliverableTypeId);
				model.setCreateDate(APIDateTimeUtils.convertDateToString(
						deliverableType.getCreateDate()));
				model.setModifiedDate(APIDateTimeUtils.convertDateToString(
						deliverableType.getModifiedDate()));
				model.setDeliverableType(deliverableType.getTypeCode());
				model.setDeliverableName(deliverableType.getTypeName());
				model.setCodePattern(deliverableType.getCodePattern());
				model.setCounter(counter);
				
				outputs.add(model);
			}
		}
		return outputs;
	}

	public static List<DossierLogSearchIdModel> mappingToDeliverableTypesSearchByIdResultsModel(List<Document> documents) {

		List<DossierLogSearchIdModel> outputs = new ArrayList<DossierLogSearchIdModel>();

		for (Document document : documents) {

			DossierLogSearchIdModel model = new DossierLogSearchIdModel();

			long dossierLogId = GetterUtil.getLong(document.get("entryClassPK"));
			// int notificationType =
			// GetterUtil.getInteger(document.get(DossierLogTerm.NOTIFICATION_TYPE));

			model.setDossierLogId(dossierLogId);
			model.setAuthor(document.get(DossierLogTerm.AUTHOR));
			model.setContent(document.get(DossierLogTerm.CONTENT));
			model.setCreateDate(document.get(DossierLogTerm.CREATE_DATE));
			model.setNotificationType(document.get(DossierLogTerm.NOTIFICATION_TYPE));
			model.setPayload(document.get(DossierLogTerm.PAYLOAD));

			outputs.add(model);
		}

		return outputs;

	}
}
