package org.opencps.dossiermgt.scheduler;

import backend.auth.api.exception.ErrorMsgModel;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.*;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.action.DossierActions;
import org.opencps.dossiermgt.action.FrequencyIntegrationAction;
import org.opencps.dossiermgt.action.impl.DossierActionsImpl;
import org.opencps.dossiermgt.action.impl.FrequencyIntegrationActionImpl;
import org.opencps.dossiermgt.action.util.DossierMgtUtils;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.constants.FrequencyOfficeConstants;
import org.opencps.dossiermgt.constants.ProcessActionTerm;
import org.opencps.dossiermgt.constants.PublishQueueTerm;
import org.opencps.dossiermgt.input.model.ProfileReceiver;
import org.opencps.dossiermgt.model.*;
import org.opencps.dossiermgt.service.*;
import org.opencps.kernel.context.MBServiceContextFactoryUtil;
import org.opencps.kernel.scheduler.StorageTypeAwareSchedulerEntryImpl;
import org.osgi.service.component.annotations.*;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component(immediate = true, service = CrawlDossierFrequencyScheduler.class)
public class CrawlDossierFrequencyScheduler extends BaseMessageListener {
    private volatile boolean isRunning = false;

    @Override
    protected void doReceive(Message message) throws Exception {
        _log.info("-----Start job crawl dossier frequency---");
        //todo remove this
        if(true) {
            return;
        }

        if (!isRunning) {
            isRunning = true;
        }
        else {
            return;
        }

        try {
            _log.info("Crawl dossier frequency at : " + APIDateTimeUtils.convertDateToString(new Date()));
            List<ServerConfig> listConfig = ServerConfigLocalServiceUtil.getByServerAndProtocol(
                    FrequencyOfficeConstants.SERVER_NO, FrequencyOfficeConstants.PROTOCOL);

            if(Validator.isNull(listConfig) || listConfig.isEmpty()) {
                throw new Exception("No server config");
            }

            ServerConfig serverConfig = listConfig.get(0);
            if(Validator.isNull(serverConfig)) {
                throw new Exception("No server config");
            }

            FrequencyIntegrationAction integrationAction = new FrequencyIntegrationActionImpl(serverConfig);
            String token = integrationAction.getToken();
            List<ProfileReceiver> listDossier = integrationAction.getDossiers(token);
            System.out.println(1111);
//            integrationAction.crawlDossierLGSP(null);

            _log.info("End crawl dossier frequency!!!");
        } catch (Exception e){
            _log.error("Error crawl dossier frequency: " + e.getMessage());
        }
        isRunning = false;
    }


    @Activate
    @Modified
    protected void activate(Map<String,Object> properties) throws SchedulerException {
        String listenerClass = getClass().getName();
        Trigger jobTrigger = _triggerFactory.createTrigger(listenerClass, listenerClass, new Date(), null, 2, TimeUnit.MINUTE);

        _schedulerEntryImpl = new SchedulerEntryImpl(getClass().getName(), jobTrigger);
        _schedulerEntryImpl = new StorageTypeAwareSchedulerEntryImpl(_schedulerEntryImpl, StorageType.MEMORY_CLUSTERED);

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
        isRunning = false;
    }

    protected StorageType getStorageType() {
        if (_schedulerEntryImpl instanceof StorageTypeAware) {
            return ((StorageTypeAware) _schedulerEntryImpl).getStorageType();
        }

        return StorageType.PERSISTED;
    }

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

    private Log _log = LogFactoryUtil.getLog(CrawlDossierFrequencyScheduler.class);

}