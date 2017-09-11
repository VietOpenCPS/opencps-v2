package org.opencps.api.controller.impl;

import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.HttpHeaders;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.opencps.api.controller.ServiceInfo;
import org.opencps.api.controller.util.ServiceFileTemplateUtils;
import org.opencps.api.controller.util.ServiceInfoUtils;
import org.opencps.api.serviceinfo.model.FileTemplateModel;
import org.opencps.api.serviceinfo.model.FileTemplateResultModel;
import org.opencps.api.serviceinfo.model.FileTemplateResultsModel;
import org.opencps.api.serviceinfo.model.ServiceInfoInputModel;
import org.opencps.api.serviceinfo.model.ServiceInfoModel;
import org.opencps.api.serviceinfo.model.ServiceInfoResultModel;
import org.opencps.api.serviceinfo.model.ServiceInfoResultsModel;
import org.opencps.api.serviceinfo.model.ServiceInfoSearchModel;
import org.opencps.dossiermgt.exception.RequiredAdministrationCodeException;
import org.opencps.dossiermgt.exception.RequiredServiceCodeException;
import org.opencps.dossiermgt.exception.RequiredServiceNameException;
import org.opencps.dossiermgt.model.ServiceFileTemplate;
import org.opencps.dossiermgt.service.ServiceFileTemplateLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceInfoLocalServiceUtil;
import org.opencps.exception.model.ExceptionModel;

import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

public class ServiceInfoImpl implements ServiceInfo {

	@Override
	public Response getServiceInfos(HttpServletRequest request, HttpHeaders header, ServiceContext serviceContext,
			ServiceInfoSearchModel search) {
		
		ServiceInfoResultsModel result = new ServiceInfoResultsModel();
		
		int total = ServiceInfoLocalServiceUtil.countServiceInfosByGroupId(20143);
		List<org.opencps.dossiermgt.model.ServiceInfo> serviceInfos = 
				ServiceInfoLocalServiceUtil.getServiceInfosByGroupId(20143);
		
		List<ServiceInfoResultModel> serviceInfoModels = ServiceInfoUtils.getServiceInfoResultsModel(serviceInfos);
		
		result.setTotal(total);
		result.getData().addAll(serviceInfoModels);
		
		return Response.ok(result).build();
	}

	@Override
	public Response addServiceInfo(HttpServletRequest request, HttpHeaders header, ServiceContext serviceContext,
			ServiceInfoInputModel input) {
		
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		long userId = serviceContext.getUserId();
		
		org.opencps.dossiermgt.model.ServiceInfo serviceInfo = null;
		ExceptionModel exception = null;
		
		try {
			serviceInfo = ServiceInfoLocalServiceUtil.addServiceInfo(userId, groupId, input.getServiceCode(), 
				input.getServiceName(),
				input.getProcessText(), input.getMethodText(), input.getDossierText(), input.getConditionText(),
				input.getDurationText(), input.getApplicantText(), input.getResultText(), input.getRegularText(),
				input.getFeeText(), input.getAdministrationCode(), input.getDomainCode(), input.getMaxLevel(),
				input.getActiveStatus(), serviceContext);
		} catch(Exception e) {
			_log.error(e);
			exception = new ExceptionModel();
			
			if(e instanceof RequiredServiceCodeException) {
				
				exception.setCode(Status.BAD_REQUEST.getStatusCode());
				exception.setMessage(RequiredServiceCodeException.class.getName());
				exception.setDescription(RequiredServiceCodeException.class.getName());
			} else if(e instanceof RequiredServiceNameException) {
				
				exception.setCode(Status.BAD_REQUEST.getStatusCode());
				exception.setMessage(RequiredServiceNameException.class.getName());
				exception.setDescription(RequiredServiceNameException.class.getName());
			} else if(e instanceof RequiredAdministrationCodeException) {
				
				exception.setCode(Status.BAD_REQUEST.getStatusCode());
				exception.setMessage(RequiredAdministrationCodeException.class.getName());
				exception.setDescription(RequiredAdministrationCodeException.class.getName());
			} else {
				exception.setCode(Status.INTERNAL_SERVER_ERROR.getStatusCode());
			}
		}
		
		if(serviceInfo != null) {
			ServiceInfoModel serviceInfoModel = ServiceInfoUtils.getServiceInfoModel(serviceInfo);
			return Response.ok(serviceInfoModel).build();
		} else {
			return Response.status(exception.getCode()).entity(exception).build();
		}
	}

	@Override
	public Response getDetailServiceInfo(HttpServletRequest request, HttpHeaders header, ServiceContext serviceContext,
			String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response updateServiceInfo(HttpServletRequest request, HttpHeaders header, ServiceContext serviceContext, long id,
			ServiceInfoInputModel input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response deleteServiceInfo(HttpServletRequest request, HttpHeaders header, ServiceContext serviceContext,
			String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getFileTemplatesOfServiceInfo(HttpServletRequest request, HttpHeaders header,
			ServiceContext serviceContext, String id) {
		
		FileTemplateResultsModel result = null;
		ExceptionModel exception = new ExceptionModel();
		
		long serviceInfoId = GetterUtil.getLong(id);
		
		if (serviceInfoId > 0) {
			try {
				int total = ServiceFileTemplateLocalServiceUtil.countByServiceInfoId(serviceInfoId);
				
				List<ServiceFileTemplate> serviceFileTemplates = 
						ServiceFileTemplateLocalServiceUtil.getByServiceInfoId(serviceInfoId);
				
				List<FileTemplateResultModel> fileTemplateResultsModel = ServiceFileTemplateUtils.getFileTemplateResultsModel(serviceFileTemplates);
				
				result = new FileTemplateResultsModel() ;
				result.setTotal(total);
				result.getData().addAll(fileTemplateResultsModel);
			} catch(Exception e) {
				_log.error(e);
				
				exception.setCode(Status.BAD_REQUEST.getStatusCode());
				exception.setDescription(e.getClass().getName());
				exception.setMessage(e.getClass().getName());
			}
		}
		
		if(result != null) {
			return Response.ok(result).build();
		} else {
			return Response.status(exception.getCode()).entity(exception).build();
		}
	}

	@Override
	public Response addFileTemplateToServiceInfo(HttpServletRequest request, HttpHeaders header,
			ServiceContext serviceContext, Attachment file, String id,
			String fileTemplateNo, String templateName) {
		
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		long userId = serviceContext.getUserId();
		
		ServiceFileTemplate serviceFileTemplate = null;
		ExceptionModel exception = new ExceptionModel();
		
		try {
		
			String fileName = file.getContentDisposition().getParameter("filename");
			InputStream is = file.getDataHandler().getInputStream();
			
			_log.info("===fileName===" + fileName);
			
			serviceFileTemplate = ServiceFileTemplateLocalServiceUtil.addServiceFileTemplate(
				userId, groupId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, 
				GetterUtil.getLong(id), fileTemplateNo, 
				templateName, fileName, 
				is, serviceContext);
		} catch (Exception e) {
			_log.error(e);
			
			exception.setCode(Status.BAD_REQUEST.getStatusCode());
			exception.setDescription(e.getClass().getName());
			exception.setMessage(e.getClass().getName());
		}
		
		if(serviceFileTemplate != null) {
			FileTemplateModel fileTemplateModel = ServiceFileTemplateUtils.getFileTemplateModel(serviceFileTemplate);
			return Response.ok(fileTemplateModel).build();
		} else {
			return Response.status(exception.getCode()).entity(exception).build();
		}
	}

	@Override
	public Response downloadFileTemplateOfServiceInfo(HttpServletRequest request, HttpHeaders header,
			ServiceContext serviceContext, String id, String templateNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response deleteFileTemplateOfServiceInfo(HttpServletRequest request, HttpHeaders header,
			ServiceContext serviceContext, String id, String templateNo) {
		// TODO Auto-generated method stub
		return null;
	}

	private static Log _log = LogFactoryUtil.getLog(ServiceInfoImpl.class);

}
