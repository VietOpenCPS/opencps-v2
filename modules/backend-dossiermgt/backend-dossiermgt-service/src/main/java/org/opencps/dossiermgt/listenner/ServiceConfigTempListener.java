package org.opencps.dossiermgt.listenner;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;

import org.apache.commons.lang3.StringEscapeUtils;
import org.opencps.dossiermgt.model.ServiceConfig;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = ModelListener.class)
public class ServiceConfigTempListener extends BaseModelListener<ServiceConfig> {

	@Override
	public void onAfterCreate(ServiceConfig model) throws ModelListenerException {
	}

	@Override
	public void onAfterUpdate(ServiceConfig model) throws ModelListenerException {
	}

	@Override
	public void onBeforeCreate(ServiceConfig model) throws ModelListenerException {
//		try {
//
//			model.setGovAgencyCode(StringEscapeUtils.escapeHtml4(model.getGovAgencyCode()));
//			model.setGovAgencyName(StringEscapeUtils.escapeHtml4(model.getGovAgencyName()));
//			model.setServiceInstruction(StringEscapeUtils.escapeHtml4(model.getServiceInstruction()));
//			model.setServiceLevel(Integer.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getServiceLevel()))));
//			model.setServiceUrl(StringEscapeUtils.escapeHtml4(model.getServiceUrl()));
//			model.setForBusiness(Boolean.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getForBusiness()))));
//			model.setForCitizen(Boolean.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getForCitizen()))));
//			model.setPostService(Boolean.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getPostService()))));
//			model.setRegistration(Boolean.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getRegistration()))));
//			model.setServiceInfoId(Integer.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getServiceInfoId()))));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}

	@Override
	public void onBeforeUpdate(ServiceConfig model) throws ModelListenerException {
//		try {
//
//			model.setGovAgencyCode(StringEscapeUtils.escapeHtml4(model.getGovAgencyCode()));
//			model.setGovAgencyName(StringEscapeUtils.escapeHtml4(model.getGovAgencyName()));
//			model.setServiceInstruction(StringEscapeUtils.escapeHtml4(model.getServiceInstruction()));
//			model.setServiceLevel(Integer.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getServiceLevel()))));
//			model.setServiceUrl(StringEscapeUtils.escapeHtml4(model.getServiceUrl()));
//			model.setForBusiness(Boolean.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getForBusiness()))));
//			model.setForCitizen(Boolean.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getForCitizen()))));
//			model.setPostService(Boolean.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getPostService()))));
//			model.setRegistration(Boolean.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getRegistration()))));
//			model.setServiceInfoId(Integer.valueOf(StringEscapeUtils.escapeHtml4(String.valueOf(model.getServiceInfoId()))));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}


	private Log _log = LogFactoryUtil.getLog(DossierTemplateTempListener.class.getName());
}
