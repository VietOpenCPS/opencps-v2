package org.opencps.api.controller.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.opencps.api.dossier.model.DossierDetailModel;
import org.opencps.api.eform.model.EFormDataModel;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.dossiermgt.constants.BookingTerm;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.constants.EFormTerm;
import org.opencps.dossiermgt.model.EForm;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;

public class EFormUtils {

	public static List<EFormDataModel> mappingForGetList(List<Document> docs) {
		List<EFormDataModel> ouputs = new ArrayList<EFormDataModel>();

		if (docs != null) {
			for (Document doc : docs) {
				EFormDataModel model = new EFormDataModel();
				
				model.seteFormId(GetterUtil.getLong(doc.get(EFormTerm.EFORM_ID)));
				
				if (Validator.isNotNull(doc.get(EFormTerm.CREATE_DATE))) {
					Date createDate = APIDateTimeUtils.convertStringToDate(doc.get(BookingTerm.CREATE_DATE),
							APIDateTimeUtils._LUCENE_PATTERN);
					model.setCreateDate(createDate.getTime());
				} else {
					model.setCreateDate(0l);
				}
				if (Validator.isNotNull(doc.get(Field.MODIFIED_DATE))) {
					Date modifiedDate = APIDateTimeUtils.convertStringToDate(doc.get(Field.MODIFIED_DATE),
							APIDateTimeUtils._LUCENE_PATTERN);
					model.setModifiedDate(modifiedDate.getTime());
				} else {
					model.setModifiedDate(0l);
				}
				
				model.seteFormNo(doc.get(EFormTerm.EFORM_NO));
				model.setServiceCode(doc.get(EFormTerm.SERVICE_CODE));
				model.setFileTemplateNo(doc.get(EFormTerm.FILE_TEMPLATE_NO));
				model.seteFormName(doc.get(EFormTerm.EFORM_NAME));
				model.setFormScriptFileId(GetterUtil.getLong(doc.get(EFormTerm.FORM_SCRIPT_FILE_ID)));
				model.setFormReportFileId(GetterUtil.getLong(doc.get(EFormTerm.FORM_REPORT_FILE_ID)));
				model.seteFormData(doc.get(EFormTerm.EFORM_DATA));
				model.setEmail(doc.get(EFormTerm.EMAIL));
				model.setSecret(doc.get(EFormTerm.SECRET));
				model.setCheckinDate(doc.get(EFormTerm.CHECK_IN_DATE));
				model.setGateNumber(doc.get(EFormTerm.GATE_NUMBER));
				model.setState(GetterUtil.getInteger(doc.get(EFormTerm.STATE)));
			}
		}
		return ouputs;
	}

	public static EFormDataModel mappingForGetDetail(EForm input) {

		EFormDataModel model = new EFormDataModel();

		if (Validator.isNull(input)) {
			return model;
		}

		model.seteFormId(input.getEFormId());
		model.setCreateDate(input.getCreateDate() != null ? input.getCreateDate().getTime() : 0l);
		model.setModifiedDate(input.getModifiedDate() != null ? input.getModifiedDate().getTime() : 0l);
		model.seteFormNo(input.getEFormNo());
		model.setServiceCode(input.getServiceCode());
		model.setFileTemplateNo(input.getFileTemplateNo());
		model.seteFormName(input.getEFormName());
		model.setFormScriptFileId(input.getFormScriptFileId());
		model.setFormReportFileId(input.getFormReportFileId());
		model.seteFormData(input.getEFormData());
		model.setEmail(input.getEmail());
		model.setSecret(input.getSecret());
//		model.setCheckinDate(
//				APIDateTimeUtils.convertDateToString(input.getModifiedDate(), APIDateTimeUtils._NORMAL_PARTTERN));
//		model.setGateNumber(input.getGateNumber());
//		model.setState(input.getState());

		return model;
	}

}
