package org.opencps.api.controller.util;

import java.util.ArrayList;
import java.util.List;

import org.opencps.api.dossiertemplate.model.DossierPartInputModel;
import org.opencps.api.dossiertemplate.model.DossierTemplateDataModel;
import org.opencps.api.dossiertemplate.model.DossierTemplateDetailModel;
import org.opencps.api.dossiertemplate.model.DossierTemplateInputModel;
import org.opencps.api.dossiertemplate.model.DossierTemplatePartDataModel;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.dossiermgt.constants.DossierPartTerm;
import org.opencps.dossiermgt.constants.DossierTemplateTerm;
import org.opencps.dossiermgt.model.DossierPart;
import org.opencps.dossiermgt.model.DossierTemplate;
import org.opencps.dossiermgt.service.DossierPartLocalServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

public class DossierTemplateUtils {
	private static final Log _log = LogFactoryUtil.getLog(DossierTemplateUtils.class);
	public static List<DossierTemplateDataModel> mappingToDossierTemplateList(List<Document> documents) {
		List<DossierTemplateDataModel> outputs = new ArrayList<DossierTemplateDataModel>();

		for (Document doc : documents) {
			DossierTemplateDataModel model = new DossierTemplateDataModel();

			model.setDossierTemplateId(GetterUtil.getLong(doc.get(Field.ENTRY_CLASS_PK)));
			model.setCreateDate(doc.get(Field.CREATE_DATE));
			model.setModifiedDate(doc.get(Field.MODIFIED_DATE));
			model.setTemplateName(doc.get(DossierTemplateTerm.TEMPLATE_NAME));
			model.setTemplateNo(doc.get(DossierTemplateTerm.TEMPLATE_NO));
			model.setDescription(doc.get(DossierTemplateTerm.DESCRIPTION));
			model.setNewFormScript(doc.get(DossierTemplateTerm.NEWFORM_SCRIPT));

			outputs.add(model);
		}

		return outputs;
	}

	public static DossierTemplateInputModel mappingForTemplatePOST(DossierTemplate dossierTemplate) {
		DossierTemplateInputModel output = new DossierTemplateInputModel();
		
		output.setDossierTemplateId(dossierTemplate.getPrimaryKey());
		output.setDescription(dossierTemplate.getDescription());
		output.setTemplateName(dossierTemplate.getTemplateName());
		output.setTemplateNo(dossierTemplate.getTemplateNo());
		output.setNewFormScript(dossierTemplate.getNewFormScript());

		return output;
	}

	public static DossierTemplateDetailModel mappingForTemplateGetDetail(DossierTemplate dossierTemplate) {
		DossierTemplateDetailModel output = new DossierTemplateDetailModel();

		output.setDossierTemplateId(dossierTemplate.getPrimaryKey());
		output.setDescription(dossierTemplate.getDescription());
		output.setTemplateName(dossierTemplate.getTemplateName());
		output.setTemplateNo(dossierTemplate.getTemplateNo());
		output.setCreateDate(APIDateTimeUtils.convertDateToString(dossierTemplate.getCreateDate(),
				APIDateTimeUtils._NORMAL_PARTTERN));
		output.setModifiedDate(APIDateTimeUtils.convertDateToString(dossierTemplate.getModifiedDate(),
				APIDateTimeUtils._NORMAL_PARTTERN));
		output.setNewFormScript(dossierTemplate.getNewFormScript());
		
		List<DossierTemplatePartDataModel> inputs = new ArrayList<DossierTemplatePartDataModel>();

		try {

			List<DossierPart> dossierParts;

			dossierParts = DossierPartLocalServiceUtil.getByTemplateNo(dossierTemplate.getGroupId(),
					dossierTemplate.getTemplateNo());

			for (DossierPart dp : dossierParts) {
				DossierTemplatePartDataModel elm = new DossierTemplatePartDataModel();

				elm.setPartNo(dp.getPartNo());
				elm.setPartName(dp.getPartName());
				elm.setPartTip(dp.getPartTip());
				elm.setPartType(dp.getPartType());
				elm.setMultiple(Boolean.toString(dp.getMultiple()));
				elm.setRequired(Boolean.toString(dp.getRequired()));
				elm.setEsign(Boolean.toString(dp.getESign()));
				elm.setFileTemplateNo(dp.getFileTemplateNo());
				elm.setFileMark(dp.getFileMark());

//				boolean hasForm = false;
//
//				if (Validator.isNotNull(dp.getFormScript())) {
//					hasForm = true;
//				}

				elm.setHasForm(Boolean.toString(dp.isEForm()));

				inputs.add(elm);
			}

		} catch (Exception e) {
			_log.error(e);
		}

		output.getDossierParts().addAll(inputs);

		return output;
	}
	
	
	public static List<DossierTemplatePartDataModel> mappingToDossierPartList(List<Document> documents) {
		
		List<DossierTemplatePartDataModel> outputs = new ArrayList<DossierTemplatePartDataModel>();

		for (Document doc : documents) {
			DossierTemplatePartDataModel model = new DossierTemplatePartDataModel();

			model.setPartNo(doc.get(DossierPartTerm.PART_NO));
			model.setPartName(doc.get(DossierPartTerm.PART_NAME));
			model.setPartTip(doc.get(DossierPartTerm.PART_TIP));
			model.setPartType(GetterUtil.getInteger(doc.get(DossierPartTerm.PART_TYPE)));
			model.setMultiple(doc.get(DossierPartTerm.MULTIPLE));
			model.setRequired(doc.get(DossierPartTerm.REQUIRED));
			model.setEsign(doc.get(DossierPartTerm.ESIGN));
			model.setFileTemplateNo(doc.get(DossierPartTerm.FILE_TEMPLATE_NO));
			model.setTypeCode(doc.get(DossierPartTerm.DELIVERABLE_TYPE));
			model.setDeliverableAction(GetterUtil.getInteger(doc.get(DossierPartTerm.DELIVERABLE_ACTION)));
			model.seteForm(doc.get(DossierPartTerm.EFORM));
			
			boolean hasForm = false;

			if (Validator.isNotNull(doc.get(DossierPartTerm.FORM_SCRIPT))) {
				hasForm = true;
			}

			model.setHasForm(Boolean.toString(hasForm));

			outputs.add(model);
		}

		return outputs;
	}
	
	
	public static DossierPartInputModel mappingForPartPOST(DossierPart object) {
		DossierPartInputModel output = new DossierPartInputModel();

		output.setPartNo(object.getPartNo());
		output.setPartName(object.getPartName());
		output.setPartTip(object.getPartTip());
		output.setPartType(object.getPartType());
		output.setMultiple(Boolean.toString(object.getMultiple()));
		output.setRequired(Boolean.toString(object.getRequired()));
		output.setEsign(Boolean.toString(object.getESign()));
		output.setFileTemplateNo(object.getFileTemplateNo());
		
		output.setTypeCode(object.getDeliverableType());
		output.setDeliverableAction(object.getDeliverableAction());
		output.seteForm(Boolean.toString(object.getEForm()));

		return output;
	}

}
