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
//			model.setGovAgencyCode(HtmlUtil.escape(model.getGovAgencyCode()));
//			model.setGovAgencyName(HtmlUtil.escape(model.getGovAgencyName()));
//			model.setServiceInstruction(HtmlUtil.escape(model.getServiceInstruction()));
//			model.setServiceLevel(Integer.valueOf(HtmlUtil.escape(String.valueOf(model.getServiceLevel()))));
//			model.setServiceUrl(HtmlUtil.escape(model.getServiceUrl()));
//			model.setForBusiness(Boolean.valueOf(HtmlUtil.escape(String.valueOf(model.getForBusiness()))));
//			model.setForCitizen(Boolean.valueOf(HtmlUtil.escape(String.valueOf(model.getForCitizen()))));
//			model.setPostService(Boolean.valueOf(HtmlUtil.escape(String.valueOf(model.getPostService()))));
//			model.setRegistration(Boolean.valueOf(HtmlUtil.escape(String.valueOf(model.getRegistration()))));
//			model.setServiceInfoId(Integer.valueOf(HtmlUtil.escape(String.valueOf(model.getServiceInfoId()))));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}

	@Override
	public void onBeforeUpdate(ServiceConfig model) throws ModelListenerException {
//		try {
//
//			model.setGovAgencyCode(HtmlUtil.escape(model.getGovAgencyCode()));
//			model.setGovAgencyName(HtmlUtil.escape(model.getGovAgencyName()));
//			model.setServiceInstruction(HtmlUtil.escape(model.getServiceInstruction()));
//			model.setServiceLevel(Integer.valueOf(HtmlUtil.escape(String.valueOf(model.getServiceLevel()))));
//			model.setServiceUrl(HtmlUtil.escape(model.getServiceUrl()));
//			model.setForBusiness(Boolean.valueOf(HtmlUtil.escape(String.valueOf(model.getForBusiness()))));
//			model.setForCitizen(Boolean.valueOf(HtmlUtil.escape(String.valueOf(model.getForCitizen()))));
//			model.setPostService(Boolean.valueOf(HtmlUtil.escape(String.valueOf(model.getPostService()))));
//			model.setRegistration(Boolean.valueOf(HtmlUtil.escape(String.valueOf(model.getRegistration()))));
//			model.setServiceInfoId(Integer.valueOf(HtmlUtil.escape(String.valueOf(model.getServiceInfoId()))));
//
//		} catch (Exception e) {
//			_log.error(e);
//		}
	}


	private Log _log = LogFactoryUtil.getLog(DossierTemplateTempListener.class.getName());
}
