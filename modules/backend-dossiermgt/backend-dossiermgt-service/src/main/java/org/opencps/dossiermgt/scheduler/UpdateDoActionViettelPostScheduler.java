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
import org.opencps.dossiermgt.action.DossierActions;
import org.opencps.dossiermgt.action.impl.DossierActionsImpl;
import org.opencps.dossiermgt.action.util.DossierMgtUtils;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.constants.ProcessActionTerm;
import org.opencps.dossiermgt.constants.PublishQueueTerm;
import org.opencps.dossiermgt.model.*;
import org.opencps.dossiermgt.service.*;
import org.opencps.kernel.context.MBServiceContextFactoryUtil;
import org.opencps.kernel.scheduler.StorageTypeAwareSchedulerEntryImpl;
import org.osgi.service.component.annotations.*;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component(immediate = true, service = UpdateDoActionViettelPostScheduler.class)
public class UpdateDoActionViettelPostScheduler extends BaseMessageListener {
    private volatile boolean isRunning = false;

    @Override
    protected void doReceive(Message message) throws Exception {
        _log.info("-----Start job update Viettel post schedule---");

        if (!isRunning) {
            isRunning = true;
        }
        else {
            return;
        }

        try {
            _log.info("Viettel post schedule at : " + APIDateTimeUtils.convertDateToString(new Date()));
            List<PostConnect> listPostConnect = PostConnectLocalServiceUtil.getBySyncState(PublishQueueTerm.STATE_WAITING_SYNC);

            if(Validator.isNull(listPostConnect) || listPostConnect.isEmpty()) {
                isRunning = false;
                return;
            }
            long dossierId;
            long groupId;
            long userId;
            long companyId;
            LinkedHashMap<String, Object> params = null;
            DossierActions actions = new DossierActionsImpl();
            JSONArray jsonData;
            String preCondition;
            Integer postStatus;
            Integer postStatusInPre = null;
            String actionCode;
            Dossier dossier;
            boolean updateStatus;
            ServiceContext serviceContext;
            User user;
            Company company;

            for(PostConnect postConnect : listPostConnect) {
                updateStatus = false;
                dossierId  = postConnect.getDossierId();
                groupId    = postConnect.getGroupId();
                userId     = postConnect.getUserId();
                companyId  = postConnect.getCompanyId();
                postStatus = postConnect.getPostStatus();
                dossier    = DossierLocalServiceUtil.getDossier(dossierId);

                user = UserLocalServiceUtil.getUser(userId);
                if(Validator.isNull(dossier)) {
                    _log.error("Not found dossierID: " + dossierId);
                    postConnect.setSyncState(PublishQueueTerm.STATE_ACK_ERROR);
                    PostConnectLocalServiceUtil.updatePostConnect(postConnect);
                    continue;
                }

                serviceContext = MBServiceContextFactoryUtil.create(companyId, groupId, userId);

                params = new LinkedHashMap<>();
                params.put(Field.GROUP_ID, String.valueOf(groupId));
                params.put(DossierTerm.DOSSIER_ID, String.valueOf(dossierId));
                _log.info("Data for get next action GROUP_ID: " + params.get(Field.GROUP_ID)
                        + ", DOSSIER_ID: " + params.get(DossierTerm.DOSSIER_ID) +  ", UserId: " + userId
                        + ",companyId: " + companyId);
                jsonData = actions.getNextActionList(
                        userId, companyId, groupId, params,
                        null, 0, 10, serviceContext);

                if(Validator.isNull(jsonData) || jsonData.length() == 0) {
                    _log.error("List next action null for dossierId: " + dossierId);
                    postConnect.setSyncState(PublishQueueTerm.STATE_ACK_ERROR);
                    PostConnectLocalServiceUtil.updatePostConnect(postConnect);
                    continue;
                }
                _log.info("List next action: " + jsonData);
                for (int i = 0; i < jsonData.length(); i++) {
                    preCondition = jsonData.getJSONObject(i).getString(ProcessActionTerm.PRE_CONDITION);
                    _log.info("Precondition before parse: " + preCondition);
                    if (preCondition.contains(DossierTerm.CONTAIN_POST_STATUS)) {
                        String[] splitCodes = preCondition.split(StringPool.COMMA);
                        _log.info("Precondition after parse: " + splitCodes);
                        for(String oneSplitComma : splitCodes) {
                            _log.info("Precondition in loop: " + oneSplitComma);
                            _log.info("splitCodes.length: " + splitCodes.length);

                            if(oneSplitComma.contains(DossierTerm.CONTAIN_POST_STATUS)) {
                                _log.info("oneSplitComma before parse equal: " + oneSplitComma);
                                String[] keyValuePostStatus = oneSplitComma.split(StringPool.EQUAL);
                                _log.info("oneSplitComma after parse equal: " + keyValuePostStatus);
                                if (keyValuePostStatus.length == 2) {
                                    postStatusInPre = Validator.isNotNull(keyValuePostStatus[1]) ? Integer.valueOf(keyValuePostStatus[1]) : null;
                                }
                            }
                        }

                        if(Validator.isNull(postStatusInPre)) {
                            _log.error("PostStatus In Precondition is null with dossierId: " + dossierId);
                            postConnect.setSyncState(PublishQueueTerm.STATE_ACK_ERROR);
                            PostConnectLocalServiceUtil.updatePostConnect(postConnect);
                            continue;
                        }

                        _log.info("PostStatus In Precondition : " + postStatusInPre);
                        if(postStatus.equals(postStatusInPre)) {
                            actionCode = jsonData.getJSONObject(i).getString(ProcessActionTerm.ACTION_CODE);
                            //call do action
                            if(Validator.isNotNull(actionCode)){
                                updateStatus = doAction(actionCode, groupId, dossier, user, null, serviceContext);
                                break;
                            }
                        }
                    }
                }

                if (updateStatus) {
                    postConnect.setSyncState(PublishQueueTerm.STATE_RECEIVED_ACK);
                } else {
                    postConnect.setSyncState(PublishQueueTerm.STATE_ACK_ERROR);
                }
                PostConnectLocalServiceUtil.updatePostConnect(postConnect);
            }
            _log.info("End Viettel post schedule!!!");
        } catch (Exception e){
            _log.error("Error Viettel post schedule: " + e.getMessage());
        }
        isRunning = false;
    }

    private boolean doAction(String actionCode, long groupId, Dossier dossier, User user
            , Company company, ServiceContext serviceContext) {
        try {
            _log.info("----VIETTEL POST: doing action");
            DossierActions actions = new DossierActionsImpl();
            ErrorMsgModel errorModel = new ErrorMsgModel();
            DossierAction dossierResult = null;

            ActionConfig actionConfig = ActionConfigLocalServiceUtil.getByCode(groupId, actionCode);
            String serviceCode = dossier.getServiceCode();
            String govAgencyCode = dossier.getGovAgencyCode();
            String dossierTempNo = dossier.getDossierTemplateNo();

            //case action config null
            if(Validator.isNull(actionConfig)) {
                _log.info("----VIETTEL POST: case action config null");
                ServiceConfig config = ServiceConfigLocalServiceUtil.getBySICodeAndGAC(groupId, serviceCode, govAgencyCode);
                if (Validator.isNull(config)) {
                    throw new Exception("Server config not found for serviceCode "
                            + serviceCode + ", govAgencyCode " + govAgencyCode);
                }
                ProcessOption option = ProcessOptionLocalServiceUtil.getByDTPLNoAndServiceCF(groupId, dossierTempNo,
                        config.getServiceConfigId());

                if (Validator.isNull(option)) {
                    throw new Exception("ProcessOption not found for dossierTempNo " + dossierTempNo
                            + ", ServiceConfigId " + config.getServiceConfigId());
                }
                long serviceProcessId = option.getServiceProcessId();
                ProcessAction proAction = getProcessAction(user, groupId, dossier, actionCode, serviceProcessId);
                if(Validator.isNull(proAction)) {
                    throw new Exception("ProcessAction not found for actionCode " + actionCode
                            + ", serviceProcessId " + serviceProcessId);
                }

                dossierResult = actions.doAction(
                        groupId, user.getUserId(), dossier, option, proAction,
                        actionCode, "" ,
                        "", "",
                        "", "",
                        0, serviceContext, errorModel);
                return Validator.isNotNull(dossierResult);
            }

            //case action config not null
            _log.info("----VIETTEL POST: case action config not null");
            boolean insideProcess = actionConfig.getInsideProcess();
            ServiceConfig config = ServiceConfigLocalServiceUtil.getBySICodeAndGAC(groupId, serviceCode, govAgencyCode);
            if(Validator.isNull(config)){
                throw new Exception("Server config not found for serviceCode "
                        + serviceCode + ", govAgencyCode " + govAgencyCode);
            }
            ProcessOption option = ProcessOptionLocalServiceUtil.getByDTPLNoAndServiceCF(groupId, dossierTempNo,
                    config.getServiceConfigId());

            if(!insideProcess) {
                dossierResult = actions.doAction(
                        groupId, user.getUserId(), dossier, option, null,
                        actionCode, "", "",
                        "", "",
                        "", actionConfig.getSyncType(),
                        serviceContext, errorModel);
                return Validator.isNotNull(dossierResult);
            }

            if (dossier.getDossierActionId() == 0) {
                if (Validator.isNull(option)) {
                    throw new Exception("ProcessOption not found for dossierTempNo " + dossierTempNo
                            + ", ServiceConfigId " + config.getServiceConfigId());
                }
                long serviceProcessId = option.getServiceProcessId();
                ProcessAction proAction = getProcessAction(user, groupId, dossier, actionCode, serviceProcessId);
                if(Validator.isNull(proAction)) {
                    throw new Exception("ProcessAction not found for actionCode " + actionCode
                            + ", serviceProcessId " + serviceProcessId);
                }
                dossierResult = actions.doAction(
                        groupId, user.getUserId(), dossier, option, proAction,
                        actionCode, "" ,
                        "", "",
                        "", "",
                        0, serviceContext, errorModel);
                return Validator.isNotNull(dossierResult);
            }

            DossierAction dossierAction = DossierActionLocalServiceUtil.fetchDossierAction(
                    dossier.getDossierActionId());

            if(Validator.isNull(dossierAction)) {
                throw new Exception("Not found dossierAction");
            }

            long serviceProcessId = dossierAction.getServiceProcessId();
            DossierTemplate dossierTemplate = DossierTemplateLocalServiceUtil.getByTemplateNo(
                            groupId,
                            dossier.getDossierTemplateNo());

            ProcessOption oldOption = ProcessOptionLocalServiceUtil.fetchBySP_DT(
                            serviceProcessId,
                            dossierTemplate.getDossierTemplateId());

            ProcessAction proAction = getProcessAction(user, groupId, dossier, actionCode, serviceProcessId);
            if(Validator.isNull(proAction)) {
                throw new Exception("ProcessAction not found for actionCode " + actionCode
                        + ", serviceProcessId " + serviceProcessId);
            }

            dossierResult = actions.doAction(
                    groupId, user.getUserId(), dossier, oldOption, proAction,
                    actionCode, "" ,
                    "", "",
                    "", "",
                    actionConfig.getSyncType(), serviceContext, errorModel);
            _log.info("----VIETTEL POST: End do action for dossier " + dossier.getDossierId());
            return Validator.isNotNull(dossierResult);
        } catch (Exception e) {
            _log.error("Error doAction Viettel post schedule: " + e.getMessage());
            return false;
        }
    }

    private ProcessAction getProcessAction(User user, long groupId, Dossier dossier, String actionCode,
                             long serviceProcessId) throws Exception{
        _log.info("----VIETTEL POST: getting process action");
        try {
            ProcessAction action = null;
            DossierAction dossierAction = DossierActionLocalServiceUtil.fetchDossierAction(dossier.getDossierActionId());

            List<ProcessAction> actions = ProcessActionLocalServiceUtil.getByActionCode(groupId, actionCode,
                    serviceProcessId);
            _log.debug("GET PROCESS ACTION____" + groupId + "," + actionCode + "," + serviceProcessId);
            String dossierStatus = dossier.getDossierStatus();
            String dossierSubStatus = dossier.getDossierSubStatus();
            String preStepCode;
            String curStepCode = StringPool.BLANK;
            if (dossier.getDossierActionId() > 0) {
                DossierAction curAction = DossierActionLocalServiceUtil.fetchDossierAction(dossier.getDossierActionId());
                if (curAction != null) {
                    curStepCode = curAction.getStepCode();
                }
            }
            for (ProcessAction act : actions) {

                preStepCode = act.getPreStepCode();
                _log.debug("LamTV_preStepCode: "+preStepCode);
                if (Validator.isNotNull(curStepCode) && !preStepCode.contentEquals(curStepCode)) continue;

                ProcessStep step = ProcessStepLocalServiceUtil.fetchBySC_GID(preStepCode, groupId, serviceProcessId);

                if (Validator.isNull(step) && dossierAction == null) {
                    action = act;
                    break;
                } else {
                    String stepStatus = step != null ? step.getDossierStatus() : StringPool.BLANK;
                    String stepSubStatus = step != null ?  step.getDossierSubStatus() : StringPool.BLANK;
                    boolean flagCheck = false;

                    if (dossierAction != null) {
                        if (act.getPreStepCode().equals(dossierAction.getStepCode())) {
                            flagCheck = true;
                        }
                    }
                    else {
                        flagCheck = true;
                    }
                    _log.debug("LamTV_preStepCode: "+stepStatus + "," + stepSubStatus + "," + dossierStatus + "," + dossierSubStatus + "," + act.getPreCondition() + "," + flagCheck);
                    if (stepStatus.contentEquals(dossierStatus)
                            && StringUtil.containsIgnoreCase(stepSubStatus, dossierSubStatus)
                            && flagCheck) {
                        if (Validator.isNotNull(act.getPreCondition()) && DossierMgtUtils.checkPreCondition(act.getPreCondition().split(StringPool.COMMA), dossier, user)) {
                            action = act;
                            break;
                        }
                        else if (Validator.isNull(act.getPreCondition())) {
                            action = act;
                            break;
                        }
                    }
                }
            }
            _log.info("----VIETTEL POST: End get process action");

            return action;
        } catch (Exception e) {
            _log.error("Error doAction Viettel post schedule: " + e.getMessage());
            return null;
        }
    }

    @Activate
    @Modified
    protected void activate(Map<String,Object> properties) throws SchedulerException {
        String listenerClass = getClass().getName();
        Trigger jobTrigger = _triggerFactory.createTrigger(listenerClass, listenerClass, new Date(), null, 10, TimeUnit.MINUTE);

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

    private Log _log = LogFactoryUtil.getLog(UpdateDoActionViettelPostScheduler.class);

}