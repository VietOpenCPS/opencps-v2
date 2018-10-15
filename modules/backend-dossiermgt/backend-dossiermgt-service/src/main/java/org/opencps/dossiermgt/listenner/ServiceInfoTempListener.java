package org.opencps.dossiermgt.listenner;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.util.Validator;

import org.apache.commons.lang3.StringEscapeUtils;
import org.opencps.datamgt.constants.DataMGTConstants;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.utils.DictCollectionUtils;
import org.opencps.dossiermgt.model.ServiceInfo;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = ModelListener.class)
public class ServiceInfoTempListener extends BaseModelListener<ServiceInfo> {

	@Override
	public void onAfterCreate(ServiceInfo model) throws ModelListenerException {
	
	}

	@Override
	public void onAfterUpdate(ServiceInfo model) throws ModelListenerException {
	}

	@Override
	public void onBeforeCreate(ServiceInfo model) throws ModelListenerException {
//		try {
//			model.setServiceCode(StringEscapeUtils.escapeHtml4(model.getServiceCode()));
//			model.setServiceName(StringEscapeUtils.escapeHtml4(model.getServiceName()));
//			model.setProcessText(StringEscapeUtils.escapeHtml4(model.getProcessText()));
//			model.setMethodText(StringEscapeUtils.escapeHtml4(model.getMethodText()));
//			model.setDossierText(StringEscapeUtils.escapeHtml4(model.getDossierText()));
//			model.setConditionText(StringEscapeUtils.escapeHtml4(model.getConditionText()));
//			model.setDurationText(StringEscapeUtils.escapeHtml4(model.getDurationText()));
//			model.setApplicantText(StringEscapeUtils.escapeHtml4(model.getApplicantText()));
//			model.setResultText(StringEscapeUtils.escapeHtml4(model.getResultText()));
//			model.setRegularText(StringEscapeUtils.escapeHtml4(model.getRegularText()));
//			model.setFeeText(StringEscapeUtils.escapeHtml4(model.getFeeText()));
//			model.setAdministrationCode(StringEscapeUtils.escapeHtml4(model.getAdministrationCode()));
//			model.setDomainCode(StringEscapeUtils.escapeHtml4(model.getDomainCode()));
//
//			DictItem adm = DictCollectionUtils.getDictItemByCode(DataMGTConstants.ADMINTRATION_CODE,
//					model.getAdministrationCode(), model.getGroupId());
//
//			DictItem dom = DictCollectionUtils.getDictItemByCode(DataMGTConstants.SERVICE_DOMAIN, model.getDomainCode(),
//					model.getGroupId());
//
//			if (Validator.isNotNull(adm)) {
//				model.setAdministrationName(StringEscapeUtils.escapeHtml4(adm.getItemName()));
//				model.setAdministrationIndex(StringEscapeUtils.escapeHtml4(adm.getTreeIndex()));
//			}
//
//			if (Validator.isNotNull(dom)) {
//				model.setDomainName(StringEscapeUtils.escapeHtml4(dom.getItemName()));
//				model.setDomainIndex(StringEscapeUtils.escapeHtml4(dom.getTreeIndex()));
//			}
//
//			model.setPublic_(Boolean.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getPublic_()))));
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}

	@Override
	public void onBeforeUpdate(ServiceInfo model) throws ModelListenerException {
//		try {
//			model.setServiceCode(StringEscapeUtils.escapeHtml4(model.getServiceCode()));
//			model.setServiceName(StringEscapeUtils.escapeHtml4(model.getServiceName()));
//			model.setProcessText(StringEscapeUtils.escapeHtml4(model.getProcessText()));
//			model.setMethodText(StringEscapeUtils.escapeHtml4(model.getMethodText()));
//			model.setDossierText(StringEscapeUtils.escapeHtml4(model.getDossierText()));
//			model.setConditionText(StringEscapeUtils.escapeHtml4(model.getConditionText()));
//			model.setDurationText(StringEscapeUtils.escapeHtml4(model.getDurationText()));
//			model.setApplicantText(StringEscapeUtils.escapeHtml4(model.getApplicantText()));
//			model.setResultText(StringEscapeUtils.escapeHtml4(model.getResultText()));
//			model.setRegularText(StringEscapeUtils.escapeHtml4(model.getRegularText()));
//			model.setFeeText(StringEscapeUtils.escapeHtml4(model.getFeeText()));
//			model.setAdministrationCode(StringEscapeUtils.escapeHtml4(model.getAdministrationCode()));
//			model.setDomainCode(StringEscapeUtils.escapeHtml4(model.getDomainCode()));
//
//			DictItem adm = DictCollectionUtils.getDictItemByCode(DataMGTConstants.ADMINTRATION_CODE,
//					model.getAdministrationCode(), model.getGroupId());
//
//			DictItem dom = DictCollectionUtils.getDictItemByCode(DataMGTConstants.SERVICE_DOMAIN, model.getDomainCode(),
//					model.getGroupId());
//
//			if (Validator.isNotNull(adm)) {
//				model.setAdministrationName(StringEscapeUtils.escapeHtml4(adm.getItemName()));
//				model.setAdministrationIndex(StringEscapeUtils.escapeHtml4(adm.getTreeIndex()));
//			}
//
//			if (Validator.isNotNull(dom)) {
//				model.setDomainName(StringEscapeUtils.escapeHtml4(dom.getItemName()));
//				model.setDomainIndex(StringEscapeUtils.escapeHtml4(dom.getTreeIndex()));
//			}
//			model.setPublic_(Boolean.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getPublic_()))));
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}


	private Log _log = LogFactoryUtil.getLog(ServiceInfoTempListener.class.getName());
}
