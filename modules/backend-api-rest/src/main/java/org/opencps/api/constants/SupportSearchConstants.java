package org.opencps.api.constants;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import org.opencps.api.controller.util.DateTimeUtil;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.DossierSync;

import java.util.List;

public class SupportSearchConstants {


    public static final String HO_SO_DVC = "HoSoDVC";
    public static final String HO_SO_MCDT = "HoSoMCDT";
    //    ===================
    public static final String INFO = "info";

    //    ===================
    public static final String DOSSIER_SYNC = "dossierSync";
    public static final String ACTION_CODE = "actionCode";
    public static final String SYNC_TYPE = "syncType";
    public static final String PAYLOAD = "payload";
    public static final String STATE_ = "state_";
    public static final String RETRY = "retry";
    public static final String CREATE_DATE = "createDate";
    public static final String MODIFIED_DATE = "modifiedDate";
    public static final String SUDMIT_DATE = "submitDate";
    public static final String RECEIVE_DATE= "receiveDate";
    public static final String DUE_DATE = "dueDate";
    public static final String RELEASE_DATE = "releaseDate";
    public static final String FINISH_DATE = "finishDate";
    public static final String LOCK_STATE = "lockState";
    public static final String REGISTER_BOOK_CODE = "registerBookCode";
    public static final String PROCESS_NO = "processNo";
    public static final String GOV_AGENCY_CODE = "govAgencyCode";

//    ===================
    public static final String DOSSIER = "dossier";
    public static final String DOSSIER_ID = "dossierID";
    public static final String DOSSIER_NO = "dossierNO";
    public static final String REFERENCE_UID = "referenceUid";
    public static final String DOSSIER_STATUS = "dossierStatus";
    public static final String DOSSIER_STATUS_TEXT = "dossierStatusText";
    public static final String DOSSIER_SUB_STATUS_= "dossierSubStatus";
    public static final String DOSSIER_SUB_STATUS_TEXT_= "dossierSubStatusText";
    public static final String DOSSIER_ACTION_ID = "dossierActionId";
    public static final String ORIGINALITY = "originality";
    public static final String ONLINE = "online_";
    public static final String ORIGIN_DOSSIER_NO= "originDossierNo";
    public static final String ORIGIN_DOSSIER_ID= "originDossierId";

//    ===================
    public static final String DOSSIER_ACTION = "dossierAction";
    public static final String FROM_STEP_CODE = "fromStepCode";
    public static final String FROM_STEP_NAME = "fromStepName";
    public static final String ACTION_NAME = "actionName";
    public static final String STEP_CODE = "stepCode";
    public static final String STEP_NAME = "stepName";
    public static final String PENDING = "pending";

//  =======================
    public static final String DOSSIER_BETWEEN = "dossierBetween";
    public static final String DOSSIER_TRANFER = "dossierTranfer";

//  =======================
    public static final String DOSSIER_FILE = "dossierFile";
    public static final String URL_DOSSIER_FILE = "url";

    public static JSONObject convertDossierSyncToObject(DossierSync dossierSync){

        JSONObject entityDossierSync = JSONFactoryUtil.createJSONObject();

        entityDossierSync.put(SupportSearchConstants.ACTION_CODE, dossierSync.getDossierId());
        entityDossierSync.put(SupportSearchConstants.SYNC_TYPE, dossierSync.getSyncType());
        entityDossierSync.put(SupportSearchConstants.STATE_, dossierSync.getState());
        entityDossierSync.put(SupportSearchConstants.RETRY, dossierSync.getRetry());
        entityDossierSync.put(SupportSearchConstants.CREATE_DATE, DateTimeUtil.convertDatetoDateTimeString(dossierSync.getCreateDate()));
        entityDossierSync.put(SupportSearchConstants.MODIFIED_DATE, DateTimeUtil.convertDatetoDateTimeString(dossierSync.getModifiedDate()));
        entityDossierSync.put(SupportSearchConstants.PAYLOAD, dossierSync.getPayload());

        return entityDossierSync;
    }
    public static JSONObject convertDossierActionToObject(DossierAction dossierAction){
        JSONObject entityDossierAction = JSONFactoryUtil.createJSONObject();

        entityDossierAction.put(SupportSearchConstants.DOSSIER_ID, dossierAction.getDossierId());
        entityDossierAction.put(SupportSearchConstants.FROM_STEP_CODE, dossierAction.getFromStepCode());
        entityDossierAction.put(SupportSearchConstants.FROM_STEP_NAME, dossierAction.getFromStepName());
        entityDossierAction.put(SupportSearchConstants.ACTION_CODE, dossierAction.getActionCode());
        entityDossierAction.put(SupportSearchConstants.ACTION_NAME, dossierAction.getActionName());
        entityDossierAction.put(SupportSearchConstants.STEP_CODE, dossierAction.getStepCode());
        entityDossierAction.put(SupportSearchConstants.STEP_NAME, dossierAction.getStepName());
        entityDossierAction.put(SupportSearchConstants.PENDING, dossierAction.getPending());
        entityDossierAction.put(SupportSearchConstants.DOSSIER_ACTION_ID, dossierAction.getDossierActionId());
        entityDossierAction.put(SupportSearchConstants.CREATE_DATE, DateTimeUtil.convertDatetoDateTimeString(dossierAction.getCreateDate()));
        entityDossierAction.put(SupportSearchConstants.MODIFIED_DATE, DateTimeUtil.convertDatetoDateTimeString(dossierAction.getModifiedDate()));

        return entityDossierAction;
    }

    public static JSONArray convertListSyncToArray(List<DossierSync> ListDossierSync){
        JSONArray DossierSyncArray = JSONFactoryUtil.createJSONArray();
        for(DossierSync dossierSync : ListDossierSync){
            JSONObject entityDossierSync = JSONFactoryUtil.createJSONObject();
            entityDossierSync.put(SupportSearchConstants.ACTION_CODE, dossierSync.getActionCode());
            entityDossierSync.put(SupportSearchConstants.SYNC_TYPE, dossierSync.getSyncType());
            entityDossierSync.put(SupportSearchConstants.STATE_, dossierSync.getState());
            entityDossierSync.put(SupportSearchConstants.RETRY, dossierSync.getRetry());
            entityDossierSync.put(SupportSearchConstants.CREATE_DATE, DateTimeUtil.convertDatetoDateTimeString(dossierSync.getCreateDate()));
            entityDossierSync.put(SupportSearchConstants.MODIFIED_DATE, DateTimeUtil.convertDatetoDateTimeString(dossierSync.getModifiedDate()));
            entityDossierSync.put(SupportSearchConstants.PAYLOAD, dossierSync.getPayload());

            DossierSyncArray.put(entityDossierSync);
        }
        return DossierSyncArray;
    }

    public static JSONArray convertListActionToArray(List<DossierAction> ListDossierAction){
        JSONArray DossierActionArray = JSONFactoryUtil.createJSONArray();
        for(DossierAction dossierAction : ListDossierAction){
            JSONObject entityDossierAction = JSONFactoryUtil.createJSONObject();

            entityDossierAction.put(SupportSearchConstants.DOSSIER_ID, dossierAction.getDossierId());
            entityDossierAction.put(SupportSearchConstants.FROM_STEP_CODE, dossierAction.getFromStepCode());
            entityDossierAction.put(SupportSearchConstants.FROM_STEP_NAME, dossierAction.getFromStepName());
            entityDossierAction.put(SupportSearchConstants.ACTION_CODE, dossierAction.getActionCode());
            entityDossierAction.put(SupportSearchConstants.ACTION_NAME, dossierAction.getActionName());
            entityDossierAction.put(SupportSearchConstants.STEP_CODE, dossierAction.getStepCode());
            entityDossierAction.put(SupportSearchConstants.STEP_NAME, dossierAction.getStepName());
            entityDossierAction.put(SupportSearchConstants.PENDING, dossierAction.getPending());
            entityDossierAction.put(SupportSearchConstants.DOSSIER_ACTION_ID, dossierAction.getDossierActionId());
            entityDossierAction.put(SupportSearchConstants.CREATE_DATE, DateTimeUtil.convertDatetoDateTimeString(dossierAction.getCreateDate()));
            entityDossierAction.put(SupportSearchConstants.MODIFIED_DATE, DateTimeUtil.convertDatetoDateTimeString(dossierAction.getModifiedDate()));

            DossierActionArray.put(entityDossierAction);
        }
        return DossierActionArray;
    }
    public static JSONObject convertDossierToJSONObject (Dossier dossier){

        JSONObject dossierObject = JSONFactoryUtil.createJSONObject();

        dossierObject.put(SupportSearchConstants.DOSSIER_ID, dossier.getDossierId());
        dossierObject.put(SupportSearchConstants.DOSSIER_NO, dossier.getDossierNo());
        dossierObject.put(SupportSearchConstants.REFERENCE_UID, dossier.getReferenceUid());
        dossierObject.put(SupportSearchConstants.DOSSIER_STATUS, dossier.getDossierStatus());
        dossierObject.put(SupportSearchConstants.DOSSIER_STATUS_TEXT, dossier.getDossierStatusText());
        dossierObject.put(SupportSearchConstants.DOSSIER_SUB_STATUS_, dossier.getDossierSubStatus());
        dossierObject.put(SupportSearchConstants.DOSSIER_SUB_STATUS_TEXT_, dossier.getDossierSubStatusText());
        dossierObject.put(SupportSearchConstants.DOSSIER_ACTION_ID, dossier.getDossierActionId());
        dossierObject.put(SupportSearchConstants.CREATE_DATE, DateTimeUtil.convertDatetoDateTimeString(dossier.getCreateDate()));
        dossierObject.put(SupportSearchConstants.MODIFIED_DATE, DateTimeUtil.convertDatetoDateTimeString(dossier.getModifiedDate()));
        dossierObject.put(SupportSearchConstants.ORIGINALITY, dossier.getOriginality());
        dossierObject.put(SupportSearchConstants.ONLINE, dossier.getOnline());
        dossierObject.put(SupportSearchConstants.ORIGIN_DOSSIER_ID, dossier.getOriginDossierId());
        dossierObject.put(SupportSearchConstants.ORIGIN_DOSSIER_NO, dossier.getOriginDossierNo());
        dossierObject.put(SupportSearchConstants.SUDMIT_DATE, DateTimeUtil.convertDatetoDateTimeString(dossier.getSubmitDate()));
        dossierObject.put(SupportSearchConstants.RECEIVE_DATE, DateTimeUtil.convertDatetoDateTimeString(dossier.getReceiveDate()));
        dossierObject.put(SupportSearchConstants.DUE_DATE, DateTimeUtil.convertDatetoDateTimeString(dossier.getDueDate()));
        dossierObject.put(SupportSearchConstants.RELEASE_DATE, DateTimeUtil.convertDatetoDateTimeString(dossier.getReleaseDate()));
        dossierObject.put(SupportSearchConstants.FINISH_DATE, DateTimeUtil.convertDatetoDateTimeString(dossier.getFinishDate()));
        dossierObject.put(SupportSearchConstants.LOCK_STATE, dossier.getLockState());
        dossierObject.put(SupportSearchConstants.REGISTER_BOOK_CODE, dossier.getRegisterBookCode());
        dossierObject.put(SupportSearchConstants.PROCESS_NO, dossier.getProcessNo());
        dossierObject.put(SupportSearchConstants.GOV_AGENCY_CODE, dossier.getGovAgencyCode());
        return dossierObject;
    }
}
