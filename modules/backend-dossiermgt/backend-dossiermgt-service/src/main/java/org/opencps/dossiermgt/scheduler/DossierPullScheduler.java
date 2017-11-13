package org.opencps.dossiermgt.scheduler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.action.DossierActions;
import org.opencps.dossiermgt.action.impl.DossierActionsImpl;
import org.opencps.dossiermgt.action.util.MultipartUtility;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.ProcessAction;
import org.opencps.dossiermgt.model.ProcessOption;
import org.opencps.dossiermgt.model.ServiceConfig;
import org.opencps.dossiermgt.model.ServiceProcess;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessActionLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessOptionLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceProcessLocalServiceUtil;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseSchedulerEntryMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import com.liferay.portal.kernel.scheduler.TriggerFactoryUtil;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.HttpMethods;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

@Component(immediate = true, service = DossierPullScheduler.class)
public class DossierPullScheduler extends BaseSchedulerEntryMessageListener {
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

		User systemUser = UserLocalServiceUtil.getUserByEmailAddress(company.getCompanyId(), username);

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

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;

			StringBuffer sb = new StringBuffer();

			while ((output = br.readLine()) != null) {
				sb.append(output);

			}

			JSONObject jsData = JSONFactoryUtil.createJSONObject(sb.toString());

			JSONArray array = JSONFactoryUtil.createJSONArray(jsData.getString("data"));

			for (int i = 0; i < array.length(); i++) {
				JSONObject object = array.getJSONObject(i);

				pullDossier(company, object, systemUser);
			}

			System.out.println(sb.toString());

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	private void pullDossier(Company company, JSONObject object, User systemUser) {
		long dossierId = GetterUtil.getLong(object.get(DossierTerm.DOSSIER_ID));

		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setCompanyId(company.getCompanyId());

		Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);

		long sourceGroupId = dossier.getGroupId();

		ServerConfig server = ServerConfigLocalServiceUtil.getGroupId(sourceGroupId);

		String serverno = StringPool.BLANK;

		if (Validator.isNotNull(server))
			serverno = server.getServerNo();

		List<ServiceProcess> processes = ServiceProcessLocalServiceUtil.getByServerNo(serverno);

		List<ServiceProcess> syncProcesses = new ArrayList<ServiceProcess>();

		String serviceInfoCode = dossier.getServiceCode();
		String govAgencyCode = dossier.getGovAgencyCode();
		String dossierTemplateCode = dossier.getDossierTemplateNo();

		for (ServiceProcess process : processes) {

			long desGroupId = process.getGroupId();

			try {
				ServiceConfig config = ServiceConfigLocalServiceUtil.getBySICodeAndGAC(desGroupId, serviceInfoCode,
						govAgencyCode);

				ProcessOption option = null;

				if (Validator.isNotNull(config)) {
					option = ProcessOptionLocalServiceUtil.getByDTPLNoAndServiceCF(desGroupId, dossierTemplateCode,
							config.getServiceConfigId());
				}

				if (Validator.isNotNull(option)) {

					ServiceProcess desServiceProcess = ServiceProcessLocalServiceUtil
							.getServiceProcess(option.getServiceProcessId());

					syncProcesses.add(desServiceProcess);
				}

			} catch (Exception e) {
				_log.info("NOProcess");
			}

			// ProcessOption option = ProcessOptionLocalServiceUtil.get
		}

		for (ServiceProcess syncServiceProcess : syncProcesses) {
			// Create DOSSIER for destination PROCESS
			// Get AutoEvent
			// Call nextAction

			Dossier desDossier = DossierLocalServiceUtil.getByRef(syncServiceProcess.getGroupId(),
					dossier.getReferenceUid());

			if (Validator.isNull(desDossier)) {
				// Create DOSSIER
				desDossier = dossier;

				long desDossierId = CounterLocalServiceUtil.increment(Dossier.class.getName());

				desDossier.setDossierId(desDossierId);
				desDossier.setGroupId(syncServiceProcess.getGroupId());
				desDossier.setSubmitting(false);

				// Process doAction (with autoEvent = SUBMIT)
				try {
					DossierLocalServiceUtil.syncDossier(desDossier);

					ProcessAction processAction = ProcessActionLocalServiceUtil
							.fetchBySPI_PRESC_AEV(syncServiceProcess.getServiceProcessId(), StringPool.BLANK, "SUBMIT");

					DossierActions actions = new DossierActionsImpl();

					actions.doAction(syncServiceProcess.getGroupId(), desDossierId, desDossier.getReferenceUid(),
							processAction.getActionCode(), processAction.getProcessActionId(),
							systemUser.getFullName() + "_SYSTEM", "SYNC_ACTION_BY_SYSTEM", 0l, systemUser.getUserId(),
							serviceContext);

				} catch (Exception e) {
					_log.info("SyncDossierUnsuccessfuly" + dossier.getDossierId());
				}

				// Update DossierFile

			} else {

				if (Validator.isNotNull(dossier.getCancellingDate())) {
					// Update cancellingDate
					desDossier.setCancellingDate(dossier.getCancellingDate());

				}

				if (Validator.isNotNull(dossier.getCorrecttingDate())) {
					// Update correctingDate
					desDossier.setCorrecttingDate(dossier.getCorrecttingDate());
				}

			}

			// TODO add sync DOSSIERFILE and PAYMENTFILE

			List<JSONObject> lsFileSync = new ArrayList<>();

			// get the list of file of source dossier need to sync
			getDossierFiles(sourceGroupId, dossierId, lsFileSync);

			pullDossierFiles(desDossier.getGroupId(), desDossier.getDossierId(), lsFileSync, sourceGroupId, dossierId);

		}

		// ResetDossier

		resetDossier(dossier.getGroupId(), dossier.getReferenceUid());

	}

	private void resetDossier(long groupId, String refId) {
		try {
			String path = "dossiers/" + refId + "/reset";
			URL url = new URL(baseUrl + path);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			String authString = username + ":" + password;

			String authStringEnc = new String(Base64.getEncoder().encodeToString(authString.getBytes()));
			conn.setRequestProperty("Authorization", "Basic " + authStringEnc);

			conn.setRequestMethod(HttpMethods.GET);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("groupId", String.valueOf(groupId));

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;

			StringBuffer sb = new StringBuffer();

			while ((output = br.readLine()) != null) {
				sb.append(output);

			}

			System.out.println(sb.toString());

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();

		}
	}

	private void getDossierFiles(long groupId, long dossierId, List<JSONObject> lsFileSync) {

		try {
			String path = "dossiers/" + dossierId + "/files";

			URL url = new URL(baseUrl + path);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			String authString = username + ":" + password;

			String authStringEnc = new String(Base64.getEncoder().encodeToString(authString.getBytes()));
			conn.setRequestProperty("Authorization", "Basic " + authStringEnc);

			conn.setRequestMethod(HttpMethods.GET);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("groupId", String.valueOf(groupId));

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;

			StringBuffer sb = new StringBuffer();

			while ((output = br.readLine()) != null) {
				sb.append(output);

			}

			JSONObject jsData = JSONFactoryUtil.createJSONObject(sb.toString());

			JSONArray array = JSONFactoryUtil.createJSONArray(jsData.getString("data"));

			for (int i = 0; i < array.length(); i++) {
				JSONObject object = array.getJSONObject(i);

				if (GetterUtil.getBoolean(object.get("isNew"))) {
					lsFileSync.add(object);
				}

			}

			System.out.println(sb.toString());

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void pullDossierFiles(long desGroupId, long dossierId, List<JSONObject> lsFileSync, long srcGroupId,
			long srcDossierId) {

		for (JSONObject ref : lsFileSync) {

			try {

				String path = "dossiers/" + srcDossierId + "/files/" + ref.getString("referenceUid");

				String requestURL = baseUrl + "dossiers/" + dossierId + "/files/";

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
				// "multipart/form-data");
				conn.setRequestProperty("groupId", String.valueOf(srcGroupId));

				if (conn.getResponseCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
				}

				InputStream is = conn.getInputStream();

				byte[] buffer = new byte[is.available()];
				is.read(buffer);
				
				
				
				File tempFile = File.createTempFile(String.valueOf(System.currentTimeMillis()), ".tmp");

				OutputStream outStream = new FileOutputStream(tempFile);

				outStream.write(buffer);

				pullDossierFile(requestURL, "UTF-8", desGroupId, dossierId, authStringEnc, tempFile,
						ref.getString("dossierTemplateNo"), ref.getString("dossierPartNo"),
						ref.getString("fileTemplateNo"), ref.getString("displayName"));

				conn.disconnect();

			} catch (MalformedURLException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();

			}

		}

	}

	private void pullDossierFile(String requestURL, String charset, long desGroupId, long dossierId,
			String authStringEnc, File file, String dossierTemplateNo, String dossierPartNo, String fileTemplateNo,
			String displayName) {

		try {
			MultipartUtility multipart = new MultipartUtility(requestURL, charset, desGroupId, authStringEnc);

			multipart.addFilePart("file", file);
			multipart.addFormField("referenceUid", PortalUUIDUtil.generate());
			multipart.addFormField("dossierTemplateNo", dossierTemplateNo);
			multipart.addFormField("dossierPartNo", dossierPartNo);
			multipart.addFormField("fileTemplateNo", fileTemplateNo);
			multipart.addFormField("displayName", displayName);

			List<String> response = multipart.finish();

			System.out.println("SERVER REPLIED:");

			for (String line : response) {
				System.out.println(line);
			}

		} catch (Exception e) {
			_log.error(e);
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
