package org.opencps.dossiermgt.action;

import org.opencps.dossiermgt.model.ProcessAction;
import org.opencps.dossiermgt.model.ProcessStep;
import org.opencps.dossiermgt.model.ProcessStepRole;
import org.opencps.dossiermgt.model.ServiceProcess;
import org.opencps.dossiermgt.model.ServiceProcessRole;

import com.liferay.portal.kernel.json.JSONObject;

public interface ServiceProcessActions {
	public JSONObject getServiceProcess();
		
	public ServiceProcess getServiceProcessDetail();
	
	public ServiceProcess updateServiceProcess();
	
	public ServiceProcess removeServiceProcess();
	
	public JSONObject getServiceProcessRoles();
	
	public ServiceProcessRole updateServiceProcessRole();
	
	public ServiceProcessRole removeServiceProcessRole();
	
	public JSONObject getProcessSteps();
	
	public ProcessStep getProcessStepDetail();
	
	public ProcessStep updateProcessStep();

	public ProcessStep deleteProcessStep();
	
	public JSONObject getProcessStepRoles();

	public ProcessStepRole getProcessStepRoleDetail();

	public ProcessStepRole updateProcessStepRole();

	public ProcessStepRole deleteProcessStepRole();
	
	public JSONObject getProcessActions();

	public ProcessAction getProcessActionDetail();

	public ProcessAction updateProcessAction();

	public ProcessAction deleteProcessAction();

}
