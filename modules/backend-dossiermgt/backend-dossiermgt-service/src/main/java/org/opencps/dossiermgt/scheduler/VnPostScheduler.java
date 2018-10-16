package org.opencps.dossiermgt.scheduler;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
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

import java.net.HttpURLConnection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

//@Component(immediate = true, service = VnPostScheduler.class)
public class VnPostScheduler extends BaseSchedulerEntryMessageListener {
	private static final String VNPOST_GETORDERTRACKING = "/postal/getOrderTracking";
	@Override
	protected void doReceive(Message message) throws Exception {
		_log.info("VNPOST GETORDERTRACKING IS STARTING : " + APIDateTimeUtils.convertDateToString(new Date()));
		
		List<Dossier> lstDossier = DossierLocalServiceUtil.findByVIAPOSTAL(3);

		Company company = CompanyLocalServiceUtil.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));

		User systemUser = UserLocalServiceUtil.getUserByEmailAddress(company.getCompanyId(),
				RESTFulConfiguration.SERVER_USER);

		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setCompanyId(company.getCompanyId());
		serviceContext.setUserId(systemUser.getUserId());

		InvokeREST rest = new InvokeREST();
		
		HashMap<String, String> properties = new HashMap<String, String>();
		
		for (Dossier dossier : lstDossier) {
			try {
				Map<String, Object> params = new HashMap<>();
				
				params.put("pageSize", "50");	//Mã khách hàng do VNPOST cung cấp
				params.put("lastId", "000000000000000000000000");
				params.put("orderNumber", dossier.getDossierNo());
				
				JSONObject resDossierSearch = rest.callPostAPI(0l, HttpMethods.POST, "application/json",
						RESTFulConfiguration.SERVER_PATH_BASE, VNPOST_GETORDERTRACKING, RESTFulConfiguration.SERVER_USER,
						RESTFulConfiguration.SERVER_PASS, properties, params, serviceContext);
	
				if (GetterUtil.getInteger(resDossierSearch.get(RESTFulConfiguration.STATUS)) != HttpURLConnection.HTTP_OK) {
					throw new RuntimeException("Failed : HTTP error code : "
							+ GetterUtil.getInteger(resDossierSearch.get(RESTFulConfiguration.STATUS)));
				}
	
				String statusCode = resDossierSearch.getString("statusCode");
	
				String statusMessage = resDossierSearch.getString("statusMessage");
				
				int sttCode = Integer.parseInt(statusCode);
				if(sttCode == 4) {
					Dossier doss = DossierLocalServiceUtil.fetchDossier(dossier.getDossierId());
					doss.setViaPostal(4);
					DossierLocalServiceUtil.updateDossier(doss);
				}
	
			} catch (Exception e) {
				_log.error(e);
	//			e.printStackTrace();
	
			}
		}
	}
	//TODO
	@Activate
	@Modified
	protected void activate() {
		schedulerEntryImpl.setTrigger(TriggerFactoryUtil.createTrigger(getEventListenerClass(), getEventListenerClass(),
				12, TimeUnit.HOUR));
		_schedulerEngineHelper.register(this, schedulerEntryImpl, DestinationNames.SCHEDULER_ENGINE);
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

	private Log _log = LogFactoryUtil.getLog(VnPostScheduler.class);	
}
