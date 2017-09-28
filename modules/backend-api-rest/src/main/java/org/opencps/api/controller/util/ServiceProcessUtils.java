package org.opencps.api.controller.util;

import java.util.List;

import org.opencps.api.serviceprocess.model.ProcessActionDataModel;
import org.opencps.api.serviceprocess.model.ProcessActionInputModel;
import org.opencps.api.serviceprocess.model.ProcessStepDataModel;
import org.opencps.api.serviceprocess.model.ProcessStepInputModel;
import org.opencps.api.serviceprocess.model.RoleDataModel;
import org.opencps.api.serviceprocess.model.RoleInputModel;
import org.opencps.api.serviceprocess.model.ServiceProcessDataModel;
import org.opencps.api.serviceprocess.model.ServiceProcessDetailModel;
import org.opencps.api.serviceprocess.model.ServiceProcessInputModel;
import org.opencps.dossiermgt.model.ProcessAction;
import org.opencps.dossiermgt.model.ProcessStep;
import org.opencps.dossiermgt.model.ProcessStepRole;
import org.opencps.dossiermgt.model.ServiceProcess;
import org.opencps.dossiermgt.model.ServiceProcessRole;

import com.liferay.portal.kernel.search.Document;

public class ServiceProcessUtils {

	public static List<ServiceProcessDataModel> mappingToServiceProcessData(List<Document> documents) {
		return null;
	}

	public static ServiceProcessInputModel mappingToPOST(ServiceProcess serviceProcess) {
		return null;
	}

	public static ServiceProcessDetailModel mappingToDetail(ServiceProcess serviceProcess) {
		return null;
	}

	public static List<RoleDataModel> mappingToServiceRole(List<ServiceProcessRole> processRoles) {
		return null;
	}

	public static List<RoleDataModel> mappingToStepRole(List<ProcessStepRole> stepRoles) {
		return null;
	}
	
	public static RoleInputModel mappingToServiceRoleInput(ServiceProcessRole processRole) {
		return null;
	}
	
	public static RoleInputModel mappingToServiceRoleInput(ProcessStepRole stepRole) {
		return null;
	}
	
	public static List<ProcessStepDataModel> mappingToProcessStepData(List<Document> documents) {
		return null;
	}
	
	public static List<ProcessActionDataModel> mappingToProcessActionData(List<Document> documents) {
		return null;
	}
	
	public static ProcessStepInputModel mapptingToStepPOST(ProcessStep step) {
		return null;
	}
	
	public static ProcessActionInputModel mappingToActionPOST(ProcessAction action) {
		return null;
	}
}
