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
//			model.setServiceCode(HtmlUtil.escape(model.getServiceCode()));
//			model.setServiceName(HtmlUtil.escape(model.getServiceName()));
//			model.setProcessText(HtmlUtil.escape(model.getProcessText()));
//			model.setMethodText(HtmlUtil.escape(model.getMethodText()));
//			model.setDossierText(HtmlUtil.escape(model.getDossierText()));
//			model.setConditionText(HtmlUtil.escape(model.getConditionText()));
//			model.setDurationText(HtmlUtil.escape(model.getDurationText()));
//			model.setApplicantText(HtmlUtil.escape(model.getApplicantText()));
//			model.setResultText(HtmlUtil.escape(model.getResultText()));
//			model.setRegularText(HtmlUtil.escape(model.getRegularText()));
//			model.setFeeText(HtmlUtil.escape(model.getFeeText()));
//			model.setAdministrationCode(HtmlUtil.escape(model.getAdministrationCode()));
//			model.setDomainCode(HtmlUtil.escape(model.getDomainCode()));
//
//			DictItem adm = DictCollectionUtils.getDictItemByCode(DataMGTConstants.ADMINTRATION_CODE,
//					model.getAdministrationCode(), model.getGroupId());
//
//			DictItem dom = DictCollectionUtils.getDictItemByCode(DataMGTConstants.SERVICE_DOMAIN, model.getDomainCode(),
//					model.getGroupId());
//
//			if (Validator.isNotNull(adm)) {
//				model.setAdministrationName(HtmlUtil.escape(adm.getItemName()));
//				model.setAdministrationIndex(HtmlUtil.escape(adm.getTreeIndex()));
//			}
//
//			if (Validator.isNotNull(dom)) {
//				model.setDomainName(HtmlUtil.escape(dom.getItemName()));
//				model.setDomainIndex(HtmlUtil.escape(dom.getTreeIndex()));
//			}
//
//			model.setPublic_(Boolean.valueOf(HtmlUtil.escape(String.valueOf(model.getPublic_()))));
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}

	@Override
	public void onBeforeUpdate(ServiceInfo model) throws ModelListenerException {
//		try {
//			model.setServiceCode(HtmlUtil.escape(model.getServiceCode()));
//			model.setServiceName(HtmlUtil.escape(model.getServiceName()));
//			model.setProcessText(HtmlUtil.escape(model.getProcessText()));
//			model.setMethodText(HtmlUtil.escape(model.getMethodText()));
//			model.setDossierText(HtmlUtil.escape(model.getDossierText()));
//			model.setConditionText(HtmlUtil.escape(model.getConditionText()));
//			model.setDurationText(HtmlUtil.escape(model.getDurationText()));
//			model.setApplicantText(HtmlUtil.escape(model.getApplicantText()));
//			model.setResultText(HtmlUtil.escape(model.getResultText()));
//			model.setRegularText(HtmlUtil.escape(model.getRegularText()));
//			model.setFeeText(HtmlUtil.escape(model.getFeeText()));
//			model.setAdministrationCode(HtmlUtil.escape(model.getAdministrationCode()));
//			model.setDomainCode(HtmlUtil.escape(model.getDomainCode()));
//
//			DictItem adm = DictCollectionUtils.getDictItemByCode(DataMGTConstants.ADMINTRATION_CODE,
//					model.getAdministrationCode(), model.getGroupId());
//
//			DictItem dom = DictCollectionUtils.getDictItemByCode(DataMGTConstants.SERVICE_DOMAIN, model.getDomainCode(),
//					model.getGroupId());
//
//			if (Validator.isNotNull(adm)) {
//				model.setAdministrationName(HtmlUtil.escape(adm.getItemName()));
//				model.setAdministrationIndex(HtmlUtil.escape(adm.getTreeIndex()));
//			}
//
//			if (Validator.isNotNull(dom)) {
//				model.setDomainName(HtmlUtil.escape(dom.getItemName()));
//				model.setDomainIndex(HtmlUtil.escape(dom.getTreeIndex()));
//			}
//			model.setPublic_(Boolean.valueOf(HtmlUtil.escape(String.valueOf(model.getPublic_()))));
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}


	private Log _log = LogFactoryUtil.getLog(ServiceInfoTempListener.class.getName());
}
