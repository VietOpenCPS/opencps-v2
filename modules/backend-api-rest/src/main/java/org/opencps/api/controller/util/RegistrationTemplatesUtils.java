
package org.opencps.api.controller.util;

import java.util.ArrayList;
import java.util.List;

import org.opencps.api.dossierlog.model.DossierLogSearchIdModel;
import org.opencps.api.registrationtemplate.model.RegistrationTemplateDetailModel;
import org.opencps.api.registrationtemplate.model.RegistrationTemplatesModel;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.dossiermgt.constants.DossierLogTerm;
import org.opencps.dossiermgt.model.RegistrationTemplates;

import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

public class RegistrationTemplatesUtils {

	public static List<RegistrationTemplateDetailModel> mappingToRegistrationTemplatesData(
			List<RegistrationTemplates> lstregistrationTemplates) {

		List<RegistrationTemplateDetailModel> outputs = new ArrayList<RegistrationTemplateDetailModel>();

		for (RegistrationTemplates registrationTemplates : lstregistrationTemplates) {

			RegistrationTemplateDetailModel model = mappingToRegistrationTemplateModel(registrationTemplates);

			outputs.add(model);
		}

		return outputs;
	}

	public static RegistrationTemplateDetailModel mappingToRegistrationTemplateModel(
			RegistrationTemplates registrationTemplates) {

		if (registrationTemplates == null) {
			return null;
		}
		long registrationTemplateId = GetterUtil.getLong(registrationTemplates.getRegistrationTemplateId());
		RegistrationTemplateDetailModel model = new RegistrationTemplateDetailModel();

		model.setRegistrationTemplateId(registrationTemplateId);
		model.setGovAgencyCode(registrationTemplates.getGovAgencyCode());
		model.setFormName(registrationTemplates.getFormName());
		model.setFormNo(registrationTemplates.getFormNo());
		model.setMultiple(registrationTemplates.getMultiple());

		return model;
	}

	public static List<RegistrationTemplatesModel> mappingToRegistrationTemplatesResultsModel(
			List<RegistrationTemplates> lstRegistrationTemplate) {
		List<RegistrationTemplatesModel> outputs = new ArrayList<RegistrationTemplatesModel>();
		if (Validator.isNotNull(lstRegistrationTemplate)) {
			for (RegistrationTemplates registrationTemplates : lstRegistrationTemplate) {

				RegistrationTemplatesModel model = new RegistrationTemplatesModel();

				long registrationTemplateId = GetterUtil.getLong(registrationTemplates.getRegistrationTemplateId());

				model.setRegistrationTemplateId(registrationTemplateId);
				model.setCreateDate(APIDateTimeUtils.convertDateToString(registrationTemplates.getCreateDate()));
				model.setModifiedDate(APIDateTimeUtils.convertDateToString(registrationTemplates.getModifiedDate()));
				model.setGovAgencyCode(registrationTemplates.getGovAgencyCode());
				model.setGovAgencyName(registrationTemplates.getGovAgencyName());
				model.setFormNo(registrationTemplates.getFormNo());
				model.setFormName(registrationTemplates.getFormName());
				model.setMultiple(registrationTemplates.getMultiple());

				outputs.add(model);
			}
		}
		return outputs;
	}

	public static List<DossierLogSearchIdModel> mappingToDeliverableTypesSearchByIdResultsModel(
			List<Document> documents) {

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
