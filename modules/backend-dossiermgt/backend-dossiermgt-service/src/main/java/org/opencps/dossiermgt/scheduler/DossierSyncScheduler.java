package org.opencps.dossiermgt.scheduler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
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
import com.liferay.portal.kernel.servlet.HttpMethods;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;

@Component(immediate = true, service = DossierSyncScheduler.class)
public class DossierSyncScheduler extends BaseSchedulerEntryMessageListener {
	private final String baseUrl = "http://localhost:8080/o/rest/v2/";
	private final String username = "test@liferay.com";
	private final String password = "test";
	private final String serectKey = "OPENCPSV2";

	@Override
	protected void doReceive(Message message) throws Exception {
		_log.info("OpenCPS SYNC DOSSIERS IS STARTING : " + APIDateTimeUtils.convertDateToString(new Date()));

		Company company = CompanyLocalServiceUtil.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setCompanyId(company.getCompanyId());

		List<ServerConfig> servers = ServerConfigLocalServiceUtil.getServerConfigs(QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);

		for (ServerConfig server : servers) {
			// Call API GET the list of DOSSIER need to synchronize

			try {
				String path = "dossiersyncs/server/" + server.getServerNo();

				URL url = new URL(baseUrl + path);

				HttpURLConnection conn = (HttpURLConnection) url.openConnection();

				String authString = username + ":" + password;

				String authStringEnc = new String(Base64.getEncoder().encodeToString(authString.getBytes()));
				conn.setRequestProperty("Authorization", "Basic " + authStringEnc);

				conn.setRequestMethod(HttpMethods.GET);
				conn.setDoInput(true);
				conn.setDoOutput(true);
				conn.setRequestProperty("Accept", "application/json");
				// conn.setRequestProperty("Content-Type",
				// "application/x-www-form-urlencoded");
				// conn.setRequestProperty("groupId", String.valueOf(groupId));

				if (conn.getResponseCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
				}

				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

				String output;

				StringBuilder sb = new StringBuilder();

				while ((output = br.readLine()) != null) {
					sb.append(output);
				}

				JSONObject jsData = JSONFactoryUtil.createJSONObject(sb.toString());

				JSONArray array = JSONFactoryUtil.createJSONArray(jsData.getString("data"));

				for (int i = 0; i < array.length(); i++) {
					JSONObject jsonDossierSync = array.getJSONObject(i);

					//_log.info("DOSSIER_SYNC_STARTTING" + jsonDossierSync.get("dossierSyncId"));

					try {
						
						long dossierSyncId = GetterUtil.getLong(jsonDossierSync.get("dossierSyncId"));
						
						//DossierSync dossierSync = DossierSyncLocalServiceUtil.fetchDossierSync(dossierSyncId);
						
						// Sending DossierSync
						String sendingPath = "dossiersyncs/" + dossierSyncId
								+ "/sending";

						URL sendingUrl = new URL(baseUrl + sendingPath);

						HttpURLConnection sendingConn = (HttpURLConnection) sendingUrl.openConnection();

						sendingConn.setRequestProperty("Authorization", "Basic " + authStringEnc);

						sendingConn.setRequestMethod(HttpMethods.GET);
						sendingConn.setDoInput(true);
						sendingConn.setDoOutput(true);
						sendingConn.setRequestProperty("Accept", "application/json");
						sendingConn.setRequestProperty("groupId", String.valueOf(server.getGroupId()));
						
						if (sendingConn.getResponseCode() != 200) {
							throw new RuntimeException("Failed : HTTP error code : " + sendingConn.getResponseCode());
						} else {
							
							//_log.info("DOSSIER_SYNC_DONE" + jsonDossierSync.get("dossierSyncId"));
							
							//Remove DossierSync
							
							//DossierSyncLocalServiceUtil.deleteDossierSync(dossierSync);
						}

						sendingConn.disconnect();

					} catch (Exception e) {
						e.printStackTrace();
					}

				}

				conn.disconnect();

			} catch (MalformedURLException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();

			}

		}
		
		_log.info("OpenCPS SYNC DOSSIERS HAS BEEN DONE : " + APIDateTimeUtils.convertDateToString(new Date()));

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

	private Log _log = LogFactoryUtil.getLog(DossierSyncScheduler.class);

}
