
package org.opencps.api.controller.util;

import java.util.ArrayList;
import java.util.List;

import org.opencps.api.deliverabletype.model.DeliverableTypesModel;
import org.opencps.api.dossierlog.model.DossierLogSearchIdModel;
import org.opencps.dossiermgt.constants.DeliverableTypesTerm;
import org.opencps.dossiermgt.constants.DossierLogTerm;
import org.opencps.dossiermgt.model.DeliverableType;

import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

public class DeliverableTypesUtils {

	public static List<DeliverableTypesModel> mappingToDeliverableTypesData(List<DeliverableType> lstdeliverableType) {

		List<DeliverableTypesModel> outputs = new ArrayList<DeliverableTypesModel>();

		for (DeliverableType deliverableType : lstdeliverableType) {

			DeliverableTypesModel model = mappingToDeliverableTypesModel(deliverableType);

			outputs.add(model);
		}

		return outputs;
	}

	public static DeliverableTypesModel mappingToDeliverableTypesModel(DeliverableType deliverableType) {

		if (deliverableType == null) {
			return null;
		}
		DeliverableTypesModel model = new DeliverableTypesModel();

		model.setDeliverableType(deliverableType.getDeliverableType_());
		model.setDeliverableName(deliverableType.getDeliverableName());
		model.setCodePattern(deliverableType.getCodePattern());
		model.setCounter(Long.valueOf(deliverableType.getCounter()));

		return model;
	}

	public static List<DeliverableTypesModel> mappingToDeliverableTypesResultsModel(
			List<Document> documents) {
		List<DeliverableTypesModel> outputs = new ArrayList<DeliverableTypesModel>();
		if(Validator.isNotNull(documents)){
			for (Document document : documents) {
	
				DeliverableTypesModel model = new DeliverableTypesModel();
	
				long deliverableTypeId = GetterUtil.getLong(document.get("entryClassPK"));
				long counter = GetterUtil.getLong(document.get(DeliverableTypesTerm.COUNTER));
	
				model.setDeliverableTypeId(deliverableTypeId);
				model.setCreateDate(document.get(DeliverableTypesTerm.CREATE_DATE));
				model.setModifiedDate(document.get(DeliverableTypesTerm.MODIFIED_DATE));
				model.setDeliverableType(document.get(DeliverableTypesTerm.DELIVERABLE_TYPE));
				model.setDeliverableName(document.get(DeliverableTypesTerm.DELIVERABLE_NAME));
				model.setCodePattern(document.get(DeliverableTypesTerm.CODEPATTERN));
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
