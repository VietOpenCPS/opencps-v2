package org.opencps.dossiermgt.scheduler;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.SchedulerEntryImpl;
import com.liferay.portal.kernel.scheduler.SchedulerException;
import com.liferay.portal.kernel.scheduler.StorageType;
import com.liferay.portal.kernel.scheduler.StorageTypeAware;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.servlet.HttpMethods;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.opencps.dossiermgt.constants.RegistrationTerm;
import org.opencps.dossiermgt.exception.NoSuchRegistrationException;
import org.opencps.dossiermgt.model.Registration;
import org.opencps.dossiermgt.model.RegistrationForm;
import org.opencps.dossiermgt.service.RegistrationFormLocalServiceUtil;
import org.opencps.dossiermgt.service.RegistrationLocalServiceUtil;
import org.opencps.kernel.scheduler.StorageTypeAwareSchedulerEntryImpl;
import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.service.ApplicantLocalServiceUtil;
import org.opencps.usermgt.utils.DateTimeUtils;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

//@Component(immediate = true, service = RegistrationSyncScheduler.class)
public class RegistrationSyncScheduler extends BaseMessageListener {
	private volatile boolean isRunning = false;
	@Override
	protected void doReceive(Message message) throws Exception {
		if (!isRunning) {
			isRunning = true;
		}
		else {
			return;
		}
//		_log.info("OpenCPS SYNC Registration IS STARTING : " + APIDateTimeUtils.convertDateToString(new Date()));
		
		Company company = CompanyLocalServiceUtil.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setCompanyId(company.getCompanyId());

		InvokeREST rest = new InvokeREST();

		// Get all SERVER NO need to DOSSIER sync to

		HashMap<String, String> properties = new HashMap<String, String>();

		String serverConfigEndpoint = "serverconfigs";

		JSONObject resServerConfig = rest.callAPI(0l, HttpMethods.GET, "application/json",
				RESTFulConfiguration.SERVER_PATH_BASE, serverConfigEndpoint, RESTFulConfiguration.SERVER_USER,
				RESTFulConfiguration.SERVER_PASS, properties, serviceContext);
		
		List<String> lsServerNo = getListServerNo(resServerConfig);
//		_log.info("resServerConfig lsServerNo ---------- :" + lsServerNo);
		for (String serverNo : lsServerNo) {

			String dossierSyncEndpoint = "serverconfigs/" + serverNo;

			JSONObject resDossierSync = rest.callAPI(0l, HttpMethods.GET, "application/json",
					RESTFulConfiguration.CLIENT_PATH_BASE, dossierSyncEndpoint, RESTFulConfiguration.CLIENT_USER,
					RESTFulConfiguration.CLIENT_PASS, properties, serviceContext);

			if (resDossierSync.getInt(RESTFulConfiguration.STATUS) == 200) {
				

				long groupId = getGroupId(resDossierSync);
//				_log.info("resServerConfig groupId ---------- :" + groupId);
				
				// TODO GROUP EMPLOYEE
				long desGroupId = 55301;

				// listener submiting of server
				List<Registration> registrations = RegistrationLocalServiceUtil.getdByF_submitting(groupId, Boolean.TRUE);
//				_log.info("resServerConfig registrations ---------- :" + registrations);
				String registrationEndpoint = "registrations/syncs";
				String registrationFormEndpoint = "registrations/syncs/form";
				
				for (Registration registration : registrations) {
					
					Map<String, Object> params = getParamsPostRegistration(registration);
					
					JSONObject registrationPOSTrespone = rest.callPostAPI(desGroupId, HttpMethods.POST, "application/json", 
							RESTFulConfiguration.SERVER_PATH_BASE, registrationEndpoint, RESTFulConfiguration.SERVER_USER,
							RESTFulConfiguration.SERVER_PASS,
							properties, params, serviceContext);
					
					if (registrationPOSTrespone.getInt(RESTFulConfiguration.STATUS) == HttpURLConnection.HTTP_OK) {
						
						registration.setSubmitting(Boolean.FALSE);
						RegistrationLocalServiceUtil.updateRegistration(registration);
						
						// TODO sync registrationForm
						List<RegistrationForm> registrationForms = RegistrationFormLocalServiceUtil.findByG_REGID_ISNEW(
						    registration.getRegistrationId(), Boolean.TRUE);
						
						for (RegistrationForm registrationForm : registrationForms) {
							
							Map<String, Object> paramsForm = getParamsPostRegistrationForm(registrationForm, registration.getUuid());
							
							JSONObject registrationFormPOSTrespone = rest.callPostAPI(desGroupId, HttpMethods.POST, "application/json", 
									RESTFulConfiguration.SERVER_PATH_BASE, registrationFormEndpoint, RESTFulConfiguration.SERVER_USER,
									RESTFulConfiguration.SERVER_PASS,
									properties, paramsForm, serviceContext);
							
							if (registrationFormPOSTrespone.getInt(RESTFulConfiguration.STATUS) == HttpURLConnection.HTTP_OK) {
								
								registrationForm.setIsNew(Boolean.FALSE);
								RegistrationFormLocalServiceUtil.updateRegistrationForm(registrationForm);
								
							}
						}
						
					}
					
				}
				
				// listener submiting of client
				List<Registration> registrationClients = RegistrationLocalServiceUtil.getdByF_submitting(desGroupId, Boolean.TRUE);
				
				for (Registration registrationClient : registrationClients) {
				    Registration registrationServer = RegistrationLocalServiceUtil.fetchRegistrationByUuidAndGroupId(
				        registrationClient.getUuid(), groupId);
				    
				    if(registrationServer != null) {
    				    try {
    				        Map<String, Object> params = getParamsPostRegistration(registrationClient);
                            
                            JSONObject clientRegistrationPOSTrespone = rest.callPostAPI(groupId, HttpMethods.POST, "application/json", 
                                    RESTFulConfiguration.SERVER_PATH_BASE, registrationEndpoint, RESTFulConfiguration.SERVER_USER,
                                    RESTFulConfiguration.SERVER_PASS,
                                    properties, params, serviceContext);
                            
                            if (clientRegistrationPOSTrespone.getInt(RESTFulConfiguration.STATUS) == HttpURLConnection.HTTP_OK) {
                                registrationClient.setSubmitting(Boolean.FALSE);
                                
                                RegistrationLocalServiceUtil.updateRegistration(registrationClient);
                                
                                if(registrationClient.getRegistrationState() == 2) {
                                    try {
                                        //Update application info
                                        Applicant applicant = ApplicantLocalServiceUtil.fetchByAppId(registrationClient.getApplicantIdNo());
                                        
                                        if(applicant != null) {
                                            applicant.setContactTelNo(registrationClient.getContactTelNo());
                                            applicant.setContactName(registrationClient.getContactName());
                                            applicant.setApplicantName(registrationClient.getApplicantName());
                                            applicant.setApplicantIdType(registrationClient.getApplicantIdType());
                                            applicant.setApplicantIdNo(registrationClient.getApplicantIdNo());
                                            applicant.setApplicantIdDate(registrationClient.getApplicantIdDate());
                                            applicant.setAddress(registrationClient.getAddress());
                                            applicant.setCityCode(registrationClient.getCityCode());
                                            applicant.setCityName(registrationClient.getCityName());
                                            applicant.setDistrictCode(registrationClient.getDistrictCode());
                                            applicant.setDistrictName(registrationClient.getDistrictName());
                                            applicant.setWardCode(registrationClient.getWardCode());
                                            applicant.setWardName(registrationClient.getWardName());
                                            applicant.setContactEmail(registrationClient.getContactEmail());
                                            applicant.setRepresentativeEnterprise(registrationClient.getRepresentativeEnterprise());
                                            
                                            ApplicantLocalServiceUtil.updateApplicant(applicant);
                                            Indexer<Applicant> indexApplicant = IndexerRegistryUtil.nullSafeGetIndexer(Applicant.class);
                                            indexApplicant.reindex(applicant);
                                        }
                                    } catch(Exception e) {
                                        _log.error(e);
                                    }
                                }
                                
                            }
    				    } catch (NoSuchRegistrationException nsge) {
    				    	_log.error(nsge);
    				    }
				    }
				}
				
			}
		}
		
//		_log.info("OpenCPS SYNC Registration HAS BEEN DONE : " + APIDateTimeUtils.convertDateToString(new Date()));
		isRunning = false;
	}
	
	private List<String> getListServerNo(JSONObject response) {
		List<String> lsServer = new ArrayList<>();

		try {

			if (response.getInt(RESTFulConfiguration.STATUS) == 200) {

				JSONObject jsData = JSONFactoryUtil.createJSONObject(response.getString(RESTFulConfiguration.MESSAGE));

				JSONArray jsArrayData = JSONFactoryUtil.createJSONArray(jsData.getString("data"));

				for (int i = 0; i < jsArrayData.length(); i++) {
					JSONObject elm = jsArrayData.getJSONObject(i);

					if (Validator.isNotNull(elm.getString("serverNo"))) {
						lsServer.add(elm.getString("serverNo"));
					}
				}

			}

		} catch (JSONException e) {
//			e.printStackTrace();
			_log.error(e);
		}

		return lsServer;
	}
	private Map<String, Object> getParamsPostRegistration(Registration registration) throws PortalException {

		Map<String, Object> params = new HashMap<String, Object>();

		try {

			params.put(RegistrationTerm.UUID, registration.getUuid());
			params.put(RegistrationTerm.REGISTRATION_ID, registration.getRegistrationId());
			params.put(RegistrationTerm.GROUP_ID, registration.getGroupId());
			params.put(RegistrationTerm.USER_ID, registration.getUserId());
			params.put(RegistrationTerm.CREATE_DATE, registration.getCreateDate());
			params.put(RegistrationTerm.MODIFIED_DATE, registration.getModifiedDate());
			params.put(RegistrationTerm.COMPANY_ID, registration.getCompanyId());
			params.put(RegistrationTerm.APPLICATION_NAME, registration.getApplicantName());
			params.put(RegistrationTerm.APPLICATION_ID_TYPE, registration.getApplicantIdType());
			params.put(RegistrationTerm.APPLICATION_ID_NO, registration.getApplicantIdNo());
			params.put(RegistrationTerm.APPLICATION_ID_DATE, DateTimeUtils.convertDateToString(
			    registration.getApplicantIdDate(), DateTimeUtils._TIMESTAMP));
			params.put(RegistrationTerm.ADDRESS, registration.getAddress());
			params.put(RegistrationTerm.CITY_CODE, registration.getCityCode());
			params.put(RegistrationTerm.CITY_NAME, registration.getCityName());
			params.put(RegistrationTerm.DISTRICT_CODE, registration.getDistrictCode());
			params.put(RegistrationTerm.DISTRICT_NAME, registration.getDistrictName());
			params.put(RegistrationTerm.WARD_CODE, registration.getWardCode());
			params.put(RegistrationTerm.WARD_NAME, registration.getWardName());
			params.put(RegistrationTerm.CONTACT_NAME, registration.getContactName());
			params.put(RegistrationTerm.CONTACT_TEL_NO, registration.getContactTelNo());
			params.put(RegistrationTerm.CONTACT_EMAIL, registration.getContactEmail());
			params.put(RegistrationTerm.GOV_AGENCY_CODE, registration.getGovAgencyCode());
			params.put(RegistrationTerm.GOV_AGENCY_NAME, registration.getGovAgencyName());
			params.put(RegistrationTerm.REGISTRATIONSTATE, registration.getRegistrationState());
			params.put(RegistrationTerm.REGISTRATION_CLASS, registration.getRegistrationClass());
			params.put(RegistrationTerm.REPRESENTATIVE_ENTERPRISE, registration.getRepresentativeEnterprise());
			params.put(RegistrationTerm.SUBMITTING, registration.getSubmitting());
			
			
		} catch (Exception e) {
			_log.error(e);
			throw new PortalException("RegistrationNotFound");
		}

		return params;
	}
	
	private Map<String, Object> getParamsPostRegistrationForm(RegistrationForm registrationForm, String registrationUUID) throws PortalException {

		Map<String, Object> params = new HashMap<String, Object>();

		try {

			params.put(RegistrationTerm.UUID, registrationUUID);
			params.put("referenceUid", registrationForm.getReferenceUid());
			params.put("formNo", registrationForm.getFormNo());
			params.put("formName", registrationForm.getFormName());
			params.put("formData", registrationForm.getFormData());
			params.put("formScript", registrationForm.getFormScript());
			params.put("formReport", registrationForm.getFormReport());
			params.put("fileEntryId", registrationForm.getFileEntryId());
			params.put("removed", Boolean.valueOf(registrationForm.getRemoved()));
			
		} catch (Exception e) {
			_log.error(e);
			throw new PortalException("RegistrationFormNotFound");
		}

		return params;
	}
	
	private long getGroupId(JSONObject response) {
		long groupId = 0l;

		try {

			if (response.getInt(RESTFulConfiguration.STATUS) == 200) {

				JSONObject jsData = JSONFactoryUtil.createJSONObject(response.getString(RESTFulConfiguration.MESSAGE));
				groupId = jsData.getLong("groupId");
			}

		} catch (Exception e) {
			// TODO: handle exception
			_log.error(e);
		}

		return groupId;
	}

	  @Activate
	  @Modified
	  protected void activate(Map<String,Object> properties) throws SchedulerException {
		  String listenerClass = getClass().getName();
		  Trigger jobTrigger = _triggerFactory.createTrigger(listenerClass, listenerClass, new Date(), null, 1, TimeUnit.MINUTE);

		  _schedulerEntryImpl = new SchedulerEntryImpl(getClass().getName(), jobTrigger);
		  _schedulerEntryImpl = new StorageTypeAwareSchedulerEntryImpl(_schedulerEntryImpl, StorageType.MEMORY_CLUSTERED);
		  
//		  _schedulerEntryImpl.setTrigger(jobTrigger);

		  if (_initialized) {
			  deactivate();
		  }

	    _schedulerEngineHelper.register(this, _schedulerEntryImpl, DestinationNames.SCHEDULER_DISPATCH);
	    _initialized = true;
	  }
	  
	@Deactivate
	protected void deactivate() {
		if (_initialized) {
			try {
				_schedulerEngineHelper.unschedule(_schedulerEntryImpl, getStorageType());
		    } catch (SchedulerException se) {
		        if (_log.isWarnEnabled()) {
		        	_log.warn("Unable to unschedule trigger", se);
		        }
		    }

		      _schedulerEngineHelper.unregister(this);
		}
		_initialized = false;
	}

	/**
	 * getStorageType: Utility method to get the storage type from the scheduler entry wrapper.
	 * @return StorageType The storage type to use.
	*/
	protected StorageType getStorageType() {
	    if (_schedulerEntryImpl instanceof StorageTypeAware) {
	    	return ((StorageTypeAware) _schedulerEntryImpl).getStorageType();
	    }
	    
	    return StorageType.MEMORY_CLUSTERED;
	}
	  
	/**
	   * setModuleServiceLifecycle: So this requires some explanation...
	   * 
	   * OSGi will start a component once all of it's dependencies are satisfied.  However, there
	   * are times where you want to hold off until the portal is completely ready to go.
	   * 
	   * This reference declaration is waiting for the ModuleServiceLifecycle's PORTAL_INITIALIZED
	   * component which will not be available until, surprise surprise, the portal has finished
	   * initializing.
	   * 
	   * With this reference, this component activation waits until portal initialization has completed.
	   * @param moduleServiceLifecycle
	   */
	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	protected void setModuleServiceLifecycle(ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	@Reference(unbind = "-")
	protected void setTriggerFactory(TriggerFactory triggerFactory) {
		_triggerFactory = triggerFactory;
	}

	@Reference(unbind = "-")
	protected void setSchedulerEngineHelper(SchedulerEngineHelper schedulerEngineHelper) {
		_schedulerEngineHelper = schedulerEngineHelper;
	}
	
	private SchedulerEngineHelper _schedulerEngineHelper;
	private TriggerFactory _triggerFactory;
	private volatile boolean _initialized;
	private SchedulerEntryImpl _schedulerEntryImpl = null;

	private Log _log = LogFactoryUtil.getLog(RegistrationSyncScheduler.class);

}
