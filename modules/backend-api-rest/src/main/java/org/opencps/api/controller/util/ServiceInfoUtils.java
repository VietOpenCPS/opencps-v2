package org.opencps.api.controller.util;

import java.util.ArrayList;
import java.util.List;

import org.opencps.api.serviceinfo.model.ServiceInfoModel;
import org.opencps.api.serviceinfo.model.ServiceInfoResultModel;
import org.opencps.dossiermgt.model.ServiceInfo;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class ServiceInfoUtils {
	public static List<ServiceInfoResultModel> getServiceInfoResultsModel(List<ServiceInfo> serviceInfos) {
		List<ServiceInfoResultModel> serviceInfoResultsModel = new ArrayList<ServiceInfoResultModel>();

		try {
			for (ServiceInfo serviceInfo : serviceInfos) {

				ServiceInfoResultModel serviceInfoResultModel = new ServiceInfoResultModel();
				
				serviceInfoResultModel.setServiceInfoId(serviceInfo.getServiceInfoId());
				serviceInfoResultModel.setCreateDate(serviceInfo.getCreateDate().toString());
				serviceInfoResultModel.setModifiedDate(serviceInfo.getModifiedDate().toString());
				serviceInfoResultModel.setServiceCode(serviceInfo.getServiceCode());
				serviceInfoResultModel.setServiceName(serviceInfo.getServiceName());
				serviceInfoResultModel.setProcessText(serviceInfo.getProcessText());
				serviceInfoResultModel.setMethodText(serviceInfo.getMethodText());
				serviceInfoResultModel.setDossierText(serviceInfo.getDossierText());
				serviceInfoResultModel.setConditionText(serviceInfo.getConditionText());
				serviceInfoResultModel.setDurationText(serviceInfo.getDurationText());
				serviceInfoResultModel.setApplicantText(serviceInfo.getApplicantText());
				serviceInfoResultModel.setResultText(serviceInfo.getResultText());
				serviceInfoResultModel.setRegularText(serviceInfo.getRegularText());
				serviceInfoResultModel.setFeeText(serviceInfo.getFeeText());
				serviceInfoResultModel.setAdministrationCode(serviceInfo.getAdministrationCode());
				serviceInfoResultModel.setAdministrationName(serviceInfo.getAdministrationName());
				serviceInfoResultModel.setDomainCode(serviceInfo.getDomainCode());
				serviceInfoResultModel.setDomainName(serviceInfo.getDomainName());
				
				serviceInfoResultsModel.add(serviceInfoResultModel);
			}
		} catch (Exception e) {
			_log.error(e);
		}

		return serviceInfoResultsModel;
	}
	
	public static ServiceInfoModel getServiceInfoModel(ServiceInfo serviceInfo) {
		ServiceInfoModel serviceInfoModel = new ServiceInfoModel();

		if(serviceInfo != null) {
			serviceInfoModel.setServiceInfoId(serviceInfo.getServiceInfoId());
			serviceInfoModel.setServiceCode(serviceInfo.getServiceCode());
			serviceInfoModel.setServiceName(serviceInfo.getServiceName());
			serviceInfoModel.setProcessText(serviceInfo.getProcessText());
			serviceInfoModel.setMethodText(serviceInfo.getMethodText());
			serviceInfoModel.setDossierText(serviceInfo.getDossierText());
			serviceInfoModel.setConditionText(serviceInfo.getConditionText());
			serviceInfoModel.setDurationText(serviceInfo.getDurationText());
			serviceInfoModel.setApplicantText(serviceInfo.getApplicantText());
			serviceInfoModel.setResultText(serviceInfo.getResultText());
			serviceInfoModel.setRegularText(serviceInfo.getRegularText());
			serviceInfoModel.setFeeText(serviceInfo.getFeeText());
			serviceInfoModel.setAdministrationCode(serviceInfo.getAdministrationCode());
			serviceInfoModel.setDomainCode(serviceInfo.getDomainCode());
		}

		return serviceInfoModel;
	}

	private static Log _log = LogFactoryUtil.getLog(ServiceInfoUtils.class);
}
