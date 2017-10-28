package org.opencps.dossiermgt.scheduler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;

import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseSchedulerEntryMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import com.liferay.portal.kernel.scheduler.TriggerFactoryUtil;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;

@Component(immediate = true, service = DossierPullScheduler.class)
public class DossierPullScheduler extends BaseSchedulerEntryMessageListener{
    private final String baseUrl = "http://localhost:8080/o/rest/v2/";
    private final String username = "test@liferay.com";
    private final String password = "test";
    private final String serectKey = "OPENCPSV2";
    
	@Override
	protected void doReceive(Message message) throws Exception {
		
		_log.info("OpenCPS get DOSSIERS.....");
		
		Company company = CompanyLocalServiceUtil.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setCompanyId(company.getCompanyId());
		
		try {
			String path = "dossiers?submitting=true&secetKey=" + serectKey;
			URL url = new URL(baseUrl + path);
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			String authString = username + ":" + password;
			
			String authStringEnc = new String(Base64.getEncoder().encodeToString(authString.getBytes()));
			conn.setRequestProperty("Authorization", "Basic " + authStringEnc);

			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;
			
			StringBuffer sb = new StringBuffer();
			
			while ((output = br.readLine()) != null) {
				sb.append(output);
				
			}
			
			JSONObject jsData = JSONFactoryUtil.createJSONObject(sb.toString());
			
			JSONArray array = JSONFactoryUtil.createJSONArray(jsData.getString("data"));
			
			for (int i = 0; i < array.length(); i ++) {
				JSONObject object = array.getJSONObject(i);
				
				
			}
			
			System.out.println(sb.toString());
			
			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
			
		}

	}
	
	private void pullDossier(JSONObject object) {
		long dossierId = GetterUtil.getLong(object.get(DossierTerm.DOSSIER_ID));
		
		Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
		
		long srvGroupId = dossier.getGroupId();
		
		
		
		if (Validator.isNotNull(dossier)) {
			String serviceInfoCode = dossier.getServiceCode();
			String govAgencyCode = dossier.getGovAgencyCode();
			String dossierTemplateCode = dossier.getDossierTemplateNo();
			
		}
	}
	
	@Activate
	@Modified
	protected void activate() {
		schedulerEntryImpl.setTrigger(
				TriggerFactoryUtil.createTrigger(getEventListenerClass(), getEventListenerClass(), 1, TimeUnit.MINUTE));
		_schedulerEngineHelper.register(this, schedulerEntryImpl, DestinationNames.SCHEDULER_DISPATCH);
	}

	@Deactivate
	protected void deactivate() {
		_schedulerEngineHelper.unregister(this);
	}

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	protected void setModuleServiceLifecycle(ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	@Reference(unbind = "-")
	protected void setSchedulerEngineHelper(SchedulerEngineHelper schedulerEngineHelper) {

		_schedulerEngineHelper = schedulerEngineHelper;
	}

	@Reference(unbind = "-")
	protected void setTriggerFactory(TriggerFactory triggerFactory) {
	}

	private SchedulerEngineHelper _schedulerEngineHelper;
	
	private Log _log = LogFactoryUtil.getLog(DossierPullScheduler.class);
}
