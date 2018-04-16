package org.opencps.thirdparty.system.scheduler;

import java.util.Date;
import java.util.List;

import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.dossiermgt.constants.DossierStatusConstants;
import org.opencps.dossiermgt.model.Deliverable;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.service.DeliverableLocalService;
import org.opencps.dossiermgt.service.DossierFileLocalService;
import org.opencps.dossiermgt.service.DossierLocalService;
import org.opencps.thirdparty.system.model.ThirdPartyDossierSync;
import org.opencps.thirdparty.system.nsw.model.Envelope;
import org.opencps.thirdparty.system.nsw.model.From;
import org.opencps.thirdparty.system.nsw.model.Header;
import org.opencps.thirdparty.system.nsw.model.NSWRequest;
import org.opencps.thirdparty.system.nsw.model.RequestPayload;
import org.opencps.thirdparty.system.service.ThirdPartyDossierSyncLocalService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseSchedulerEntryMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import com.liferay.portal.kernel.scheduler.TriggerFactoryUtil;

@Component(immediate = true, service = OutsideSystemSyncScheduler.class)
public class OutsideSystemSyncScheduler extends BaseSchedulerEntryMessageListener {
	@Override
	protected void doReceive(Message message) throws Exception {
		_log.info("Starting sync with third party system is starting at  : " + APIDateTimeUtils.convertDateToString(new Date()));
		
		NSWRequest nswRequest = new NSWRequest();
		nswRequest.setOfficeCode("BGTVT");
		nswRequest.setDocumentType("BGTVT0600001");

		RequestPayload requestPayload = new RequestPayload();
		
		nswRequest.setRequestPayload(requestPayload);
		Envelope envelope = new Envelope();
		Header header = new Header();
		envelope.setHeader(header);
		requestPayload.setEnvelope(envelope);
		From from = new From();
		from.setCountryCode("VN");
		from.setIdentity("John");
		from.setMinistryCode("BGTVT");
		header.setFrom(from);

		_log.info("Request: " + "<officeCode>" + nswRequest.getOfficeCode() + "</officeCode><documentType>" + nswRequest.getDocumentType() + "</documentType>" + SOAPConverter.convertNSWRequest(nswRequest.getRequestPayload()));

		List<ThirdPartyDossierSync> lstSyncs = _thirdPartyDossierSyncLocalService.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		for (ThirdPartyDossierSync sync : lstSyncs) {
			Dossier dossier = _dossierLocalService.fetchDossier(sync.getDossierId());
			DossierFile dossierFile = null;
			Deliverable deliverable = null;
			
			if (dossier.getDossierStatus().equals(DossierStatusConstants.HANDOVER)) {
				dossierFile = _dossierFileLocalService.getDossierFileByReferenceUid(sync.getDossierId(), sync.getFileReferenceUid());
			}
		}
		
		_log.info("Sync with third party system finished at  : " + APIDateTimeUtils.convertDateToString(new Date()));
	}
	
	@Activate
	@Modified
	protected void activate() {
		schedulerEntryImpl.setTrigger(
				TriggerFactoryUtil.createTrigger(getEventListenerClass(), getEventListenerClass(), 15, TimeUnit.SECOND));
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

	@Reference
	private DossierLocalService _dossierLocalService;
	
	@Reference
	private ThirdPartyDossierSyncLocalService _thirdPartyDossierSyncLocalService;
	
	@Reference
	private DossierFileLocalService _dossierFileLocalService;
	
	@Reference
	private DeliverableLocalService _deliverableLocalService;
	
	private Log _log = LogFactoryUtil.getLog(OutsideSystemSyncScheduler.class);	
}
