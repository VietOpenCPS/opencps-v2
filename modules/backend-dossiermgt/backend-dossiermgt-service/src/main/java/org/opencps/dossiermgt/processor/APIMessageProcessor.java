package org.opencps.dossiermgt.processor;

import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.action.util.DossierMgtUtils;
import org.opencps.dossiermgt.constants.DossierDocumentTerm;
import org.opencps.dossiermgt.constants.DossierFileTerm;
import org.opencps.dossiermgt.constants.DossierPartTerm;
import org.opencps.dossiermgt.constants.DossierSyncTerm;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.constants.ProcessActionTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.DossierDocument;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.DossierMark;
import org.opencps.dossiermgt.model.DossierPart;
import org.opencps.dossiermgt.model.DossierSync;
import org.opencps.dossiermgt.model.DossierTemplate;
import org.opencps.dossiermgt.model.PaymentFile;
import org.opencps.dossiermgt.model.ProcessAction;
import org.opencps.dossiermgt.model.ProcessOption;
import org.opencps.dossiermgt.model.ServiceConfig;
import org.opencps.dossiermgt.model.ServiceProcess;
import org.opencps.dossiermgt.rest.model.DossierDetailModel;
import org.opencps.dossiermgt.rest.model.DossierDocumentModel;
import org.opencps.dossiermgt.rest.model.DossierFileModel;
import org.opencps.dossiermgt.rest.model.DossierInputModel;
import org.opencps.dossiermgt.rest.model.DossierMarkInputModel;
import org.opencps.dossiermgt.rest.model.ExecuteOneAction;
import org.opencps.dossiermgt.rest.model.PaymentFileInputModel;
import org.opencps.dossiermgt.rest.utils.OpenCPSConverter;
import org.opencps.dossiermgt.rest.utils.OpenCPSRestClient;
import org.opencps.dossiermgt.service.DossierActionLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierDocumentLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierMarkLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierPartLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierSyncLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierTemplateLocalServiceUtil;
import org.opencps.dossiermgt.service.PaymentFileLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessActionLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessOptionLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceProcessLocalServiceUtil;

public class APIMessageProcessor extends BaseMessageProcessor {
	private static final int N_OF_RETRIES = 10;
	
	private Log _log = LogFactoryUtil.getLog(APIMessageProcessor.class);
	private OpenCPSRestClient client;
	public APIMessageProcessor(DossierSync ds) {
		super(ds);
		
		ServerConfig sc = ServerConfigLocalServiceUtil.getByCode(ds.getGroupId(), ds.getServerNo());
		try {
			client = OpenCPSRestClient.fromJSONObject(JSONFactoryUtil.createJSONObject(sc.getConfigs()));
		} catch (JSONException e) {
//			e.printStackTrace();
			_log.error(e);
		}
		
	}
	
	@Override
	public void processRequest() {
//		if (dossierSync.getActionCode().equals(ActionConfigTerm.ACTION_CODE_1300)) {
			dossierSync.setState(DossierSyncTerm.STATE_ALREADY_SENT);
			DossierSyncLocalServiceUtil.updateDossierSync(dossierSync);
			if (syncRequest()) {
				dossierSync.setState(DossierSyncTerm.STATE_RECEIVED_ACK);
				DossierSyncLocalServiceUtil.updateDossierSync(dossierSync);
				DossierAction dossierAction = DossierActionLocalServiceUtil.fetchDossierAction(dossierSync.getDossierActionId());
				dossierAction.setPending(false);
				DossierActionLocalServiceUtil.updateDossierAction(dossierAction);
			}
			else {
				int retry = dossierSync.getRetry();
				retry++;
				if (retry < DossierSyncTerm.MAX_RETRY) {
					dossierSync.setRetry(retry);
					dossierSync.setState(DossierSyncTerm.STATE_WAITING_SYNC);
					DossierSyncLocalServiceUtil.updateDossierSync(dossierSync);						
				}
				else {
					dossierSync.setState(DossierSyncTerm.STATE_ACK_ERROR);
					DossierSyncLocalServiceUtil.updateDossierSync(dossierSync);
				}
			}
//		}
	}	
	
	@Override
	public void processInform() {
		dossierSync.setState(DossierSyncTerm.STATE_ALREADY_SENT);
		DossierSyncLocalServiceUtil.updateDossierSync(dossierSync);
		if (syncInform()) {
			dossierSync.setState(DossierSyncTerm.STATE_RECEIVED_ACK);
			DossierSyncLocalServiceUtil.updateDossierSync(dossierSync);
			DossierAction dossierAction = DossierActionLocalServiceUtil.fetchDossierAction(dossierSync.getDossierActionId());
			dossierAction.setPending(false);
			DossierActionLocalServiceUtil.updateDossierAction(dossierAction);
		}
		else {
			int retry = dossierSync.getRetry();
			retry++;
			if (retry < DossierSyncTerm.MAX_RETRY) {
				dossierSync.setRetry(retry);
				dossierSync.setState(DossierSyncTerm.STATE_WAITING_SYNC);
				DossierSyncLocalServiceUtil.updateDossierSync(dossierSync);						
			}
			else {
				dossierSync.setState(DossierSyncTerm.STATE_ACK_ERROR);
				DossierSyncLocalServiceUtil.updateDossierSync(dossierSync);	
			}
		}
	}
	
	private boolean syncInform() {
		Dossier dossier = DossierLocalServiceUtil.getByRef(dossierSync.getGroupId(), dossierSync.getDossierRefUid());
		if (dossier == null) {
			return false;
		}
		StringBuilder messageText = new StringBuilder();
		StringBuilder acknowlegement = new StringBuilder(); 
		
		String payload = dossierSync.getPayload();
		try {
			JSONObject payloadObj = JSONFactoryUtil.createJSONObject(payload);
			if (payloadObj.has(DossierSyncTerm.PAYLOAD_SYNC_FILES)) {
				JSONArray fileArrs = payloadObj.getJSONArray(DossierSyncTerm.PAYLOAD_SYNC_FILES);
				for (int i = 0; i < fileArrs.length(); i++) {
					JSONObject fileObj = fileArrs.getJSONObject(i);
					if (fileObj.has(DossierFileTerm.REFERENCE_UID)) {
						DossierFile df = DossierFileLocalServiceUtil.getDossierFileByReferenceUid(dossier.getDossierId(), fileObj.getString(DossierFileTerm.REFERENCE_UID));
						if (df != null) {
							if (df.getFileEntryId() > 0) {
								FileEntry fileEntry;
								try {
									fileEntry = DLAppLocalServiceUtil.getFileEntry(df.getFileEntryId());
									File file = DLFileEntryLocalServiceUtil.getFile(fileEntry.getFileEntryId(), fileEntry.getVersion(),
											true);
									DossierFileModel dfModel = new DossierFileModel();
									dfModel.setReferenceUid(df.getReferenceUid());
									dfModel.setModifiedDate(String.valueOf(df.getModifiedDate().getTime()));
									dfModel.setDossierPartNo(df.getDossierPartNo());
									dfModel.setDisplayName(df.getDisplayName());
									dfModel.setDossierTemplateNo(df.getDossierTemplateNo());
									dfModel.setFileTemplateNo(df.getFileTemplateNo());
									dfModel.setFormData(df.getFormData());
									dfModel.setFileType(fileEntry.getMimeType());
									dfModel.setRemoved(df.getRemoved());
									dfModel.setEForm(df.getEForm());
									DossierFileModel dfResult = client.postDossierFile(file, dossier.getReferenceUid(), dfModel);
									messageText.append("POST /dossierfiles");
									messageText.append("\n");
									messageText.append(JSONFactoryUtil.looseSerialize(dfModel));
									messageText.append("\n");
									if (dfResult != null) {
										acknowlegement.append(JSONFactoryUtil.looseSerialize(dfResult));
										acknowlegement.append("\n");
									}
									if (dfResult == null) {
										if (client.isWriteLog()) {
											dossierSync.setMessageText(messageText.toString());
											dossierSync.setAcknowlegement(acknowlegement.toString());
											DossierSyncLocalServiceUtil.updateDossierSync(dossierSync);
										}
										return false;
									}
//									dossierSync.setAcknowlegement(OpenCPSConverter.convertFileInputModelToJSON(dfResult).toJSONString());
//									DossierSyncLocalServiceUtil.updateDossierSync(dossierSync);
								} catch (PortalException e) {
//									e.printStackTrace();
									_log.error(e);
								}

							}
							
							DossierMark dossierMark = DossierMarkLocalServiceUtil.getDossierMarkbyDossierId(dossier.getGroupId(), dossier.getDossierId(), df.getDossierPartNo());
							if (dossierMark != null) {
								DossierMarkInputModel markInputModel = new DossierMarkInputModel();
								markInputModel.setFileCheck(dossierMark.getFileCheck());
								markInputModel.setFileMark(dossierMark.getFileMark());
								markInputModel.setFileComment(dossierMark.getFileComment());
								
								client.postDossierMark(String.valueOf(dossier.getDossierId()), df.getDossierPartNo(), markInputModel);
							}
						}
					}
				}
			}
			
			if (payloadObj.has(DossierSyncTerm.PAYLOAD_SYNC_DOCUMENTS)) {
				JSONArray fileArrs = payloadObj.getJSONArray(DossierSyncTerm.PAYLOAD_SYNC_DOCUMENTS);
				for (int i = 0; i < fileArrs.length(); i++) {
					JSONObject fileObj = fileArrs.getJSONObject(i);
					if (fileObj.has(DossierDocumentTerm.REFERENCE_UID)) {
						DossierDocument dossierDocument = DossierDocumentLocalServiceUtil.getDocByReferenceUid(dossier.getGroupId(), dossier.getDossierId(), fileObj.getString(DossierDocumentTerm.REFERENCE_UID));
						if (dossierDocument != null) {
							int retry = 0;
							
							File file = null;
							
							while (dossierDocument.getDocumentFileId() == 0) {
								try {
									Thread.sleep(1000l);
									dossierDocument = DossierDocumentLocalServiceUtil.getDocByReferenceUid(dossier.getGroupId(), dossier.getDossierId(), fileObj.getString(DossierDocumentTerm.REFERENCE_UID));
									retry++;
									if (retry > N_OF_RETRIES) break;
								} catch (InterruptedException e) {
//									e.printStackTrace();
									_log.error(e);
								}
								
							}
							if (dossierDocument.getDocumentFileId() > 0) {
								FileEntry fileEntry;
								try {
									fileEntry = DLAppLocalServiceUtil.getFileEntry(dossierDocument.getDocumentFileId());
									file = DLFileEntryLocalServiceUtil.getFile(fileEntry.getFileEntryId(), fileEntry.getVersion(),
											true);
								} catch (PortalException e) {
//									e.printStackTrace();
									_log.error(e);
								}

							}
							DossierDocumentModel model = OpenCPSConverter.convertDossierDocument(dossierDocument);
							DossierDocumentModel ddResult = client.postDossierDocument(file, dossier.getReferenceUid(), model);
							if (ddResult == null) {
								return false;
							}
						}						
					}
				}
			}
		} catch (JSONException e) {
//			e.printStackTrace();
			_log.debug(e);
			//_log.error(e);
			return false;
		}
		
		//Process action
		try {
			DossierAction dossierAction = DossierActionLocalServiceUtil.fetchDossierAction(dossierSync.getDossierActionId());
			ProcessAction processAction = ProcessActionLocalServiceUtil.fetchByF_GID_SID_AC_PRE_POST(dossierAction.getGroupId(), dossierAction.getServiceProcessId(), dossierAction.getActionCode(), dossierAction.getFromStepCode(), dossierAction.getStepCode());
			if (processAction != null && (processAction.getRequestPayment() == ProcessActionTerm.REQUEST_PAYMENT_YEU_CAU_NOP_TAM_UNG)) {
				_log.debug("OpenCPS START SYNC PAYMENTFILE FROM SYNCINFORM REQUESTPAYMENT = 1: "
						+ APIDateTimeUtils.convertDateToString(new Date()));
				PaymentFile paymentFile = PaymentFileLocalServiceUtil.fectPaymentFile(dossier.getDossierId(), dossierSync.getDossierRefUid());
				PaymentFileInputModel pfiModel = new PaymentFileInputModel();
				pfiModel.setApplicantIdNo(dossier.getApplicantIdNo());
				pfiModel.setApplicantName(dossier.getApplicantName());
				pfiModel.setBankInfo(StringPool.BLANK);
				pfiModel.setEpaymentProfile(StringPool.BLANK);
				pfiModel.setGovAgencyCode(dossier.getGovAgencyCode());
				pfiModel.setGovAgencyName(dossier.getGovAgencyName());
				pfiModel.setPaymentAmount(GetterUtil.getLong(processAction.getPaymentFee()));
				pfiModel.setPaymentFee(processAction.getPaymentFee());
				if (paymentFile != null) {
					pfiModel.setPaymentNote(paymentFile.getPaymentNote());
				}
				else {
					pfiModel.setPaymentNote(StringPool.BLANK);
				}
				pfiModel.setReferenceUid(StringPool.BLANK);
				
				client.postPaymentFiles(dossier.getReferenceUid(), pfiModel);
				_log.debug("OpenCPS END SYNC PAYMENTFILE FROM SYNCINFORM REQUESTPAYMENT = 1: "
						+ APIDateTimeUtils.convertDateToString(new Date()));
			} else if (processAction != null && (processAction
					.getRequestPayment() == ProcessActionTerm.REQUEST_PAYMENT_YEU_CAU_QUYET_TOAN_PHI)) {
				_log.debug("OpenCPS START SYNC PAYMENTFILE FROM SYNCINFORM REQUESTPAYMENT = 2: "
						+ APIDateTimeUtils.convertDateToString(new Date()));
				PaymentFile paymentFile = PaymentFileLocalServiceUtil.fectPaymentFile(dossier.getDossierId(), dossierSync.getDossierRefUid());
				//_log.debug("SONDT SYNC INFORM REQUESTPAYMENT = 2 PAYMENT FILE ======================== " + JSONFactoryUtil.looseSerialize(paymentFile));
				//_log.debug("DOSSIERID SYNC ======================== " + JSONFactoryUtil.looseSerialize(dossierSync));
				String paymentFee = null; 
				String paymentNote = null;
				
				JSONObject paymentObj = JSONFactoryUtil.createJSONObject(processAction.getPaymentFee());
				//_log.debug("SONDT SYNC INFORM Payment object: " + paymentObj);
				if (paymentObj.has("paymentFee")) {
					paymentFee = paymentObj.getString("paymentFee");
				}
				if (paymentObj.has("paymentNote")) {
					paymentNote = paymentObj.getString("paymentNote");
				}
				else {
					paymentNote = paymentFile != null ? paymentFile.getPaymentNote() : StringPool.BLANK;
				}
				PaymentFileInputModel pfiModel = new PaymentFileInputModel();
				pfiModel.setApplicantIdNo(dossier.getApplicantIdNo());
				pfiModel.setApplicantName(dossier.getApplicantName());
				pfiModel.setBankInfo(paymentFile.getBankInfo());
				pfiModel.setEpaymentProfile(paymentFile.getEpaymentProfile());
				pfiModel.setGovAgencyCode(dossier.getGovAgencyCode());
				pfiModel.setGovAgencyName(dossier.getGovAgencyName());
				pfiModel.setPaymentAmount(paymentFile.getPaymentAmount());
				pfiModel.setPaymentFee(paymentFee != null ? paymentFee : StringPool.BLANK);
				pfiModel.setPaymentNote(paymentNote != null ? paymentNote : StringPool.BLANK);
				pfiModel.setReferenceUid(dossier.getReferenceUid());
				pfiModel.setFeeAmount(paymentFile.getFeeAmount());
				pfiModel.setInvoiceTemplateNo(paymentFile.getInvoiceTemplateNo());
				pfiModel.setPaymentStatus(paymentFile.getPaymentStatus());
				pfiModel.setAdvanceAmount(paymentFile.getAdvanceAmount());
				pfiModel.setServiceAmount(paymentFile.getServiceAmount());
				pfiModel.setShipAmount(paymentFile.getShipAmount());
				
				//_log.debug("SONDT PAYMENT PFIMODEL SYNC INFORM ======================== " + JSONFactoryUtil.looseSerialize(pfiModel));
				client.postPaymentFiles(dossier.getReferenceUid(), pfiModel);
				_log.debug("OpenCPS END SYNC PAYMENTFILE FROM SYNCINFORM REQUESTPAYMENT = 2: "
						+ APIDateTimeUtils.convertDateToString(new Date()));
			} else if (processAction != null && (processAction
					.getRequestPayment() == ProcessActionTerm.REQUEST_PAYMENT_XAC_NHAN_HOAN_THANH_THU_PHI)) {
				_log.debug("OpenCPS START SYNC PAYMENTFILE FROM SYNCINFORM REQUESTPAYMENT = 5: "
						+ APIDateTimeUtils.convertDateToString(new Date()));
				PaymentFile paymentFile = PaymentFileLocalServiceUtil.fectPaymentFile(dossier.getDossierId(), dossierSync.getDossierRefUid());
				
				PaymentFileInputModel pfiModel = new PaymentFileInputModel();
				
				pfiModel.setPaymentStatus(paymentFile.getPaymentStatus());
				pfiModel.setEinvoice(paymentFile.getEinvoice());
				pfiModel.setPaymentMethod(paymentFile.getPaymentMethod());
				String paymentNote = paymentFile != null ? paymentFile.getPaymentNote() : StringPool.BLANK;
				pfiModel.setPaymentNote(paymentNote);
				client.postPaymentFiles(dossier.getReferenceUid(), pfiModel);
				
				_log.debug("OpenCPS END SYNC PAYMENTFILE FROM SYNCINFORM REQUESTPAYMENT = 5: "
						+ APIDateTimeUtils.convertDateToString(new Date()));
			}
			if (processAction.getPreCondition().contains("payok")
					|| processAction.getPreCondition().toLowerCase().contains("sendinvoice=1")) {
				PaymentFile paymentFile = PaymentFileLocalServiceUtil.fectPaymentFile(dossier.getDossierId(), dossierSync.getDossierRefUid());
				//_log.debug("SONDT PAYMENT FILE SYNC ======================== " + JSONFactoryUtil.looseSerialize(paymentFile));
//				_log.debug("DOSSIERID SYNC ======================== " + JSONFactoryUtil.looseSerialize(dossierSync));
				PaymentFileInputModel pfiModel = new PaymentFileInputModel();
				pfiModel.setApplicantIdNo(dossier.getApplicantIdNo());
				pfiModel.setApplicantName(dossier.getApplicantName());
				pfiModel.setBankInfo(paymentFile.getBankInfo());
				pfiModel.setEpaymentProfile(paymentFile.getEpaymentProfile());
				pfiModel.setGovAgencyCode(dossier.getGovAgencyCode());
				pfiModel.setGovAgencyName(dossier.getGovAgencyName());
				pfiModel.setPaymentAmount(paymentFile.getPaymentAmount());
				pfiModel.setPaymentFee(processAction.getPaymentFee());
				pfiModel.setPaymentNote(paymentFile.getPaymentNote());
				pfiModel.setReferenceUid(dossier.getReferenceUid());
				pfiModel.setFeeAmount(paymentFile.getFeeAmount());
				pfiModel.setInvoiceTemplateNo(paymentFile.getInvoiceTemplateNo());
				pfiModel.setPaymentStatus(paymentFile.getPaymentStatus());
				pfiModel.setEinvoice(paymentFile.getEinvoice());
				pfiModel.setPaymentMethod(paymentFile.getPaymentMethod());
				
				client.postPaymentFiles(dossier.getReferenceUid(), pfiModel);
			}
		}
		catch (Exception e) {
			_log.debug(e);
			//_log.error(e);
		}
		ExecuteOneAction actionModel = new ExecuteOneAction();
		actionModel.setActionCode(dossierSync.getActionCode());
		actionModel.setActionUser(dossierSync.getActionUser());
		actionModel.setActionNote(dossierSync.getActionNote());
		actionModel.setActionNote(dossierSync.getActionNote());
		//actionModel.setPayload(dossierSync.getPayload());
		
		//Test submitsionNote
		try {
			JSONObject payloadData = JSONFactoryUtil.createJSONObject(payload);
			String serviceCode = dossier.getServiceCode();
			ServiceProcess serviceProcess = ServiceProcessLocalServiceUtil.getServiceByCode(dossier.getGroupId(),
					serviceCode, dossier.getGovAgencyCode(), dossier.getDossierTemplateNo());
			if (serviceProcess != null) {
				JSONObject submissionNoteObj = DossierMgtUtils.getDossierProcessSequencesJSON(dossier.getGroupId(),
						dossier, serviceProcess);
				payloadData.put(DossierTerm.SUBMISSION_NOTE, submissionNoteObj.toJSONString());
			}
			actionModel.setPayload(payloadData.toJSONString());
		} catch (Exception e) {
			actionModel.setPayload(dossierSync.getPayload());
			_log.debug(e);
		}

		ExecuteOneAction actionResult = client.postDossierAction(dossier.getReferenceUid(), actionModel);
		if (client.isWriteLog()) {
			dossierSync.setMessageText(messageText.toString());
			dossierSync.setAcknowlegement(acknowlegement.toString());
			DossierSyncLocalServiceUtil.updateDossierSync(dossierSync);
		}
		if (actionResult != null) {
//			dossierSync.setAcknowlegement(OpenCPSConverter.convertExecuteOneActionToJSON(actionResult).toJSONString());
			return true;
		}
		else {
			return false;
		}		
	}
	
	private boolean syncRequest() {
		Dossier dossier = DossierLocalServiceUtil.getByRef(dossierSync.getGroupId(), dossierSync.getDossierRefUid());
		if (dossier == null) {
			return false;
		}
		
		DossierInputModel model = OpenCPSConverter.convertDossierToInputModel(dossier);
		if (dossier.getOriginDossierId() != 0 || Validator.isNotNull(dossier.getOriginDossierNo())) {
//			model.setOriginality(DossierTerm.ORIGINALITY_HSLT);
			model.setOriginality(DossierTerm.ORIGINALITY_LIENTHONG);
		}
		else {
			model.setOriginality(DossierTerm.ORIGINALITY_LIENTHONG);
		}
//		model.setOnline("true");
		DossierDetailModel result = client.postDossier(model);
		StringBuilder messageText = new StringBuilder();
		messageText.append("POST /dossiers\n");
		messageText.append(JSONFactoryUtil.looseSerialize(model));
		messageText.append("\n");
		StringBuilder acknowlegement = new StringBuilder(); 
		acknowlegement.append(JSONFactoryUtil.looseSerialize(result));
		acknowlegement.append("\n");
		
		if (result == null || Validator.isNull(result.getDossierId())) {
			if (client.isWriteLog()) {
				dossierSync.setMessageText(messageText.toString());
				dossierSync.setAcknowlegement(messageText.toString());
				DossierSyncLocalServiceUtil.updateDossierSync(dossierSync);
			}
			return false;
		}
		
		String payload = dossierSync.getPayload();
		if (dossier.getOriginDossierId() == 0) {
			try {
				JSONObject payloadObj = JSONFactoryUtil.createJSONObject(payload);
				if (payloadObj.has(DossierSyncTerm.PAYLOAD_SYNC_FILES)) {
					JSONArray fileArrs = payloadObj.getJSONArray(DossierSyncTerm.PAYLOAD_SYNC_FILES);
					
					List<DossierFileModel> lstFiles = client.getAllFilesByDossier(dossier.getReferenceUid());
					List<String> lstRefs = new ArrayList<>();
					
					for (int i = 0; i < fileArrs.length(); i++) {
						JSONObject fileObj = fileArrs.getJSONObject(i);
						if (fileObj.has(DossierFileTerm.REFERENCE_UID)) {
							lstRefs.add(fileObj.getString(DossierFileTerm.REFERENCE_UID));
						}
					}
					for (DossierFileModel df : lstFiles) {
						if (df.getDossierPartType() == DossierPartTerm.DOSSIER_PART_TYPE_INPUT
								&& !lstRefs.contains(df.getReferenceUid())) {
							//Remove file from server
							
						}
					}
					
					
					for (int i = 0; i < fileArrs.length(); i++) {
						JSONObject fileObj = fileArrs.getJSONObject(i);
						if (fileObj.has(DossierFileTerm.REFERENCE_UID)) {
							DossierFile df = DossierFileLocalServiceUtil.getDossierFileByReferenceUid(dossier.getDossierId(), fileObj.getString(DossierFileTerm.REFERENCE_UID));
							if (df != null && !df.getEForm()) {
								if (df.getFileEntryId() > 0) {
									FileEntry fileEntry;
									try {
										fileEntry = DLAppLocalServiceUtil.getFileEntry(df.getFileEntryId());
										File file = DLFileEntryLocalServiceUtil.getFile(fileEntry.getFileEntryId(), fileEntry.getVersion(),
												true);
										DossierFileModel dfModel = new DossierFileModel();
										dfModel.setReferenceUid(df.getReferenceUid());
										dfModel.setModifiedDate(String.valueOf(df.getModifiedDate().getTime()));
										dfModel.setDossierPartNo(df.getDossierPartNo());
										dfModel.setDisplayName(df.getDisplayName());
										dfModel.setDossierTemplateNo(df.getDossierTemplateNo());
										dfModel.setFileTemplateNo(df.getFileTemplateNo());
										dfModel.setFormData(df.getFormData());
										dfModel.setFileType(fileEntry.getMimeType());
										dfModel.setRemoved(df.getRemoved());
										dfModel.setEForm(df.getEForm());
										messageText.append("POST /dossierfiles");
										messageText.append("\n");
										messageText.append(JSONFactoryUtil.looseSerialize(dfModel));
										messageText.append("\n");
										DossierFileModel dfResult = client.postDossierFile(file, dossier.getReferenceUid(), dfModel);
										if (dfResult != null) {
											acknowlegement.append(JSONFactoryUtil.looseSerialize(dfResult));
											acknowlegement.append("\n");
										}
										if (dfResult == null) {
											if (client.isWriteLog()) {
												dossierSync.setMessageText(messageText.toString());
												dossierSync.setAcknowlegement(messageText.toString());
												DossierSyncLocalServiceUtil.updateDossierSync(dossierSync);
											}
											return false;
										}
									} catch (PortalException e) {
//										e.printStackTrace();
										_log.error(e);
									}
	
								}
								else {
									
								}
							}
							else if (df != null && df.getEForm()) {
								DossierFileModel dfModel = new DossierFileModel();
								dfModel.setReferenceUid(df.getReferenceUid());
								dfModel.setModifiedDate(String.valueOf(df.getModifiedDate().getTime()));
								dfModel.setDossierPartNo(df.getDossierPartNo());
								dfModel.setDisplayName(df.getDisplayName());
								dfModel.setDossierTemplateNo(df.getDossierTemplateNo());
								dfModel.setFileTemplateNo(df.getFileTemplateNo());
								dfModel.setFormData(df.getFormData());
								dfModel.setRemoved(df.getRemoved());
								dfModel.setEForm(df.getEForm());
								if (df.getFileEntryId() > 0) {
									FileEntry fileEntry;
									try {
										fileEntry = DLAppLocalServiceUtil.getFileEntry(df.getFileEntryId());
										File file = DLFileEntryLocalServiceUtil.getFile(fileEntry.getFileEntryId(), fileEntry.getVersion(),
												true);
										dfModel.setFileType(fileEntry.getMimeType());
										dfModel.setDisplayName(fileEntry.getFileName());
										
										DossierFileModel dfResult = client.postDossierFileEForm(file, dossier.getReferenceUid(), dfModel);
										messageText.append("POST /dossierfiles");
										messageText.append("\n");
										messageText.append(JSONFactoryUtil.looseSerialize(dfModel));
										messageText.append("\n");
										if (dfResult != null) {
											acknowlegement.append(JSONFactoryUtil.looseSerialize(dfResult));
											acknowlegement.append("\n");
										}

										if (dfResult == null) {
											if (client.isWriteLog()) {
												dossierSync.setMessageText(messageText.toString());
												dossierSync.setAcknowlegement(messageText.toString());
												DossierSyncLocalServiceUtil.updateDossierSync(dossierSync);
											}
											return false;
										}
									} catch (PortalException e) {
//										e.printStackTrace();
										_log.error(e);
									}
	
								}
								else {
									DossierFileModel dfResult = client.postDossierFileEForm(null, dossier.getReferenceUid(), dfModel);
									messageText.append("POST /dossierfiles");
									messageText.append("\n");
									messageText.append(JSONFactoryUtil.looseSerialize(dfModel));
									messageText.append("\n");
									if (dfResult != null) {
										acknowlegement.append(JSONFactoryUtil.looseSerialize(dfResult));
										acknowlegement.append("\n");
									}

									if (dfResult == null) {
										if (client.isWriteLog()) {
											dossierSync.setMessageText(messageText.toString());
											dossierSync.setAcknowlegement(messageText.toString());
											DossierSyncLocalServiceUtil.updateDossierSync(dossierSync);
										}
										return false;
									}
								}
								
							}
							
							if (df != null) {
								DossierMark dossierMark = DossierMarkLocalServiceUtil.getDossierMarkbyDossierId(dossier.getGroupId(), dossier.getDossierId(), df.getDossierPartNo());
								if (dossierMark != null) {
									DossierMarkInputModel markInputModel = new DossierMarkInputModel();
									markInputModel.setFileCheck(dossierMark.getFileCheck());
									markInputModel.setFileMark(dossierMark.getFileMark());
									markInputModel.setFileComment(dossierMark.getFileComment());
									
									client.postDossierMark(String.valueOf(dossier.getDossierId()), df.getDossierPartNo(), markInputModel);
								}		
							}
						}
					}
				}			
			} catch (JSONException e) {
//				e.printStackTrace();
				_log.debug(e);
				//_log.error(e);
				return false;
			}	
		}
		else {
			//HSLT
			try {
				JSONObject payloadObj = JSONFactoryUtil.createJSONObject(payload);
				if (payloadObj.has(DossierSyncTerm.PAYLOAD_SYNC_FILES)) {
					JSONArray fileArrs = payloadObj.getJSONArray(DossierSyncTerm.PAYLOAD_SYNC_FILES);
					
					List<DossierFileModel> lstFiles = client.getAllFilesByDossier(dossier.getReferenceUid());
					List<String> lstRefs = new ArrayList<>();
					
					for (int i = 0; i < fileArrs.length(); i++) {
						JSONObject fileObj = fileArrs.getJSONObject(i);
						if (fileObj.has(DossierFileTerm.REFERENCE_UID)) {
							lstRefs.add(fileObj.getString(DossierFileTerm.REFERENCE_UID));
						}
					}
					for (DossierFileModel df : lstFiles) {
						if (df.getDossierPartType() == DossierPartTerm.DOSSIER_PART_TYPE_INPUT
								&& !lstRefs.contains(df.getReferenceUid())) {
							//Remove file from server
							
						}
					}
					
					
					for (int i = 0; i < fileArrs.length(); i++) {
						JSONObject fileObj = fileArrs.getJSONObject(i);
						if (fileObj.has(DossierFileTerm.REFERENCE_UID)) {
							DossierFile df = DossierFileLocalServiceUtil.getDossierFileByReferenceUid(dossier.getOriginDossierId(), fileObj.getString(DossierFileTerm.REFERENCE_UID));
							String dossierPartNo = StringPool.BLANK;
							String dossierTemplateNo = StringPool.BLANK;
							String fileTemplateNo = StringPool.BLANK;
							
							ServiceConfig serviceConfig;
							try {
								serviceConfig = ServiceConfigLocalServiceUtil.getBySICodeAndGAC(dossier.getGroupId(), dossier.getServiceCode(), dossier.getGovAgencyCode());
								List<ProcessOption> lstOptions = ProcessOptionLocalServiceUtil.getByServiceProcessId(serviceConfig.getServiceConfigId());
								if (serviceConfig != null) {
									if (lstOptions.size() > 0) {
										ProcessOption processOption = lstOptions.get(0);
										
										DossierTemplate dossierTemplate = DossierTemplateLocalServiceUtil.fetchDossierTemplate(processOption.getDossierTemplateId());
										try {
											List<DossierPart> lstParts = DossierPartLocalServiceUtil.getByTemplateNo(dossier.getGroupId(), dossierTemplate.getTemplateNo());
											for (DossierPart dp : lstParts) {
												if (dp.getPartNo().equals(df.getDossierPartNo())) {
													dossierPartNo = dp.getPartNo();
													dossierTemplateNo = dp.getTemplateNo();
													fileTemplateNo = dp.getFileTemplateNo();
												}
											}
										} catch (PortalException e) {
//											e.printStackTrace();
											_log.debug(e);
											//_log.error(e);
										}
										
										
									}
								}
							} catch (PortalException e) {
//								e.printStackTrace();
								_log.error(e);
							}
							
							if (df != null && !df.getEForm()) {
								if (df.getFileEntryId() > 0) {
									FileEntry fileEntry;
									try {
										fileEntry = DLAppLocalServiceUtil.getFileEntry(df.getFileEntryId());
										File file = DLFileEntryLocalServiceUtil.getFile(fileEntry.getFileEntryId(), fileEntry.getVersion(),
												true);
										DossierFileModel dfModel = new DossierFileModel();
										dfModel.setReferenceUid(df.getReferenceUid());
										dfModel.setModifiedDate(String.valueOf(df.getModifiedDate().getTime()));
										dfModel.setDossierPartNo(dossierPartNo);
										dfModel.setDisplayName(df.getDisplayName());
										dfModel.setDossierTemplateNo(dossierTemplateNo);
										dfModel.setFileTemplateNo(fileTemplateNo);
										dfModel.setFormData(df.getFormData());
										dfModel.setFileType(fileEntry.getMimeType());
										dfModel.setRemoved(df.getRemoved());
										DossierFileModel dfResult = client.postDossierFile(file, dossier.getReferenceUid(), dfModel);
										messageText.append("POST /dossierfiles");
										messageText.append("\n");
										messageText.append(JSONFactoryUtil.looseSerialize(dfModel));
										messageText.append("\n");
										if (dfResult != null) {
											acknowlegement.append(JSONFactoryUtil.looseSerialize(dfResult));
											acknowlegement.append("\n");
										}

										if (dfResult == null) {
											if (client.isWriteLog()) {
												dossierSync.setMessageText(messageText.toString());
												dossierSync.setAcknowlegement(messageText.toString());
												DossierSyncLocalServiceUtil.updateDossierSync(dossierSync);
											}
											return false;
										}
									} catch (PortalException e) {
//										e.printStackTrace();
										_log.error(e);
									}
	
								}
								else {
									
								}
							}
							else if (df != null && df.getEForm()) {
								DossierFileModel dfModel = new DossierFileModel();
								dfModel.setReferenceUid(df.getReferenceUid());
								dfModel.setModifiedDate(String.valueOf(df.getModifiedDate().getTime()));
								dfModel.setDossierPartNo(dossierPartNo);
								dfModel.setDisplayName(df.getDisplayName());
								dfModel.setDossierTemplateNo(dossierTemplateNo);
								dfModel.setFileTemplateNo(fileTemplateNo);
								dfModel.setFormData(df.getFormData());
								dfModel.setRemoved(df.getRemoved());
								if (df.getFileEntryId() > 0) {
									FileEntry fileEntry;
									try {
										fileEntry = DLAppLocalServiceUtil.getFileEntry(df.getFileEntryId());
										File file = DLFileEntryLocalServiceUtil.getFile(fileEntry.getFileEntryId(), fileEntry.getVersion(),
												true);
										dfModel.setFileType(fileEntry.getMimeType());
										dfModel.setDisplayName(fileEntry.getFileName());
										
										DossierFileModel dfResult = client.postDossierFileEForm(file, dossier.getReferenceUid(), dfModel);
										messageText.append("POST /dossierfiles");
										messageText.append("\n");
										messageText.append(JSONFactoryUtil.looseSerialize(dfModel));
										messageText.append("\n");
										if (dfResult != null) {
											acknowlegement.append(JSONFactoryUtil.looseSerialize(dfResult));
											acknowlegement.append("\n");
										}

										if (dfResult == null) {
											if (client.isWriteLog()) {
												dossierSync.setMessageText(messageText.toString());
												dossierSync.setAcknowlegement(messageText.toString());
												DossierSyncLocalServiceUtil.updateDossierSync(dossierSync);
											}
											return false;
										}
									} catch (PortalException e) {
//										e.printStackTrace();
										_log.error(e);
									}
	
								}
								else {
									DossierFileModel dfResult = client.postDossierFileEForm(null, dossier.getReferenceUid(), dfModel);
									messageText.append("POST /dossierfiles");
									messageText.append("\n");
									messageText.append(JSONFactoryUtil.looseSerialize(dfModel));
									messageText.append("\n");
									if (dfResult != null) {
										acknowlegement.append(JSONFactoryUtil.looseSerialize(dfResult));
										acknowlegement.append("\n");
									}

									if (dfResult == null) {
										if (client.isWriteLog()) {
											dossierSync.setMessageText(messageText.toString());
											dossierSync.setAcknowlegement(messageText.toString());
											DossierSyncLocalServiceUtil.updateDossierSync(dossierSync);
										}
										return false;
									}
								}
								
							}
							
							DossierMark dossierMark = DossierMarkLocalServiceUtil.getDossierMarkbyDossierId(df.getGroupId(), dossier.getOriginDossierId(), df.getDossierPartNo());
							if (dossierMark != null) {
								DossierMarkInputModel markInputModel = new DossierMarkInputModel();
								markInputModel.setFileCheck(dossierMark.getFileCheck());
								markInputModel.setFileMark(dossierMark.getFileMark());
								markInputModel.setFileComment(dossierMark.getFileComment());
								
								client.postDossierMark(String.valueOf(dossier.getOriginDossierId()), df.getDossierPartNo(), markInputModel);
							}							
						}
					}
				}			
			} catch (JSONException e) {
//				e.printStackTrace();
				_log.debug(e);
				//_log.error(e);
				return false;
			}	
		}
		//Process action
		DossierAction dossierAction = DossierActionLocalServiceUtil.fetchDossierAction(dossierSync.getDossierActionId());
		//_log.debug("SONDT DOSSIER ACTION SYNC PAYMENT REQUEST ======================== " + JSONFactoryUtil.looseSerialize(dossierAction));
		ProcessAction processAction = ProcessActionLocalServiceUtil.fetchByF_GID_SID_AC_PRE_POST(dossierAction.getGroupId(), dossierAction.getServiceProcessId(), dossierAction.getActionCode(), dossierAction.getFromStepCode(), dossierAction.getStepCode());
		//_log.debug("SONDT PROCESS ACTION SYNC PAYMENT REQUEST ======================== " + JSONFactoryUtil.looseSerialize(processAction));
		//_log.debug("SONDT DOSSIERID PAYMENT REQUEST ================"+ dossier.getDossierId());
		_log.debug("OpenCPS SYNC PAYMENTFILE FROM SYNCREQUEST : " + APIDateTimeUtils.convertDateToString(new Date()));
		if (processAction != null && (ProcessActionTerm.REQUEST_PAYMENT_YEU_CAU_NOP_TAM_UNG == processAction.getRequestPayment())) {
			PaymentFile paymentFile = PaymentFileLocalServiceUtil.fectPaymentFile(dossier.getDossierId(), dossierSync.getDossierRefUid());
			_log.debug("OpenCPS START SYNC PAYMENTFILE FROM SYNCREQUEST REQUESTPAYMENT = 1: "
					+ APIDateTimeUtils.convertDateToString(new Date()));
			
			PaymentFileInputModel pfiModel = new PaymentFileInputModel();
			
			pfiModel.setApplicantIdNo(dossier.getApplicantIdNo());
			pfiModel.setApplicantName(dossier.getApplicantName());
			pfiModel.setBankInfo(StringPool.BLANK);
			pfiModel.setEpaymentProfile(StringPool.BLANK);
			pfiModel.setGovAgencyCode(dossier.getGovAgencyCode());
			pfiModel.setGovAgencyName(dossier.getGovAgencyName());
			pfiModel.setPaymentAmount(GetterUtil.getLong(processAction.getPaymentFee()));
			pfiModel.setPaymentFee(processAction.getPaymentFee());
			if (paymentFile != null) {
				pfiModel.setPaymentNote(paymentFile.getPaymentNote());
			}
			else {
				pfiModel.setPaymentNote(StringPool.BLANK);
			}
			pfiModel.setReferenceUid(StringPool.BLANK);
			
			client.postPaymentFiles(dossier.getReferenceUid(), pfiModel);
			
			_log.debug("OpenCPS END SYNC PAYMENTFILE FROM SYNCREQUEST REQUESTPAYMENT = 1 : " + APIDateTimeUtils.convertDateToString(new Date()));
		}else if(processAction != null && (processAction.getRequestPayment() == ProcessActionTerm.REQUEST_PAYMENT_BAO_DA_NOP_PHI)){
			_log.debug("OpenCPS START SYNC PAYMENTFILE FROM SYNCREQUEST REQUESTPAYMENT = 3: "
					+ APIDateTimeUtils.convertDateToString(new Date()));
			PaymentFile paymentFile = PaymentFileLocalServiceUtil.fectPaymentFile(dossier.getDossierId(), dossierSync.getDossierRefUid());
			
			//_log.debug("SONDT DOSSIER REQUEST ======================== " + JSONFactoryUtil.looseSerialize(dossier));
			//_log.debug("SONDT DOSSIERSYNC REQUEST ======================== " + JSONFactoryUtil.looseSerialize(dossierSync));
			
			//_log.debug("SONDT PAYMENTFILE SYNC REQUEST ======================== " + JSONFactoryUtil.looseSerialize(paymentFile));
			
			PaymentFileInputModel pfiModel = new PaymentFileInputModel();
			pfiModel.setApplicantIdNo(dossier.getApplicantIdNo());
			pfiModel.setApplicantName(dossier.getApplicantName());
			pfiModel.setBankInfo(paymentFile.getBankInfo());
			pfiModel.setEpaymentProfile(paymentFile.getEpaymentProfile());
			pfiModel.setGovAgencyCode(dossier.getGovAgencyCode());
			pfiModel.setGovAgencyName(dossier.getGovAgencyName());
			pfiModel.setPaymentAmount(paymentFile.getPaymentAmount());
			pfiModel.setPaymentFee(paymentFile.getPaymentFee());
			pfiModel.setPaymentNote(paymentFile.getPaymentNote());
			pfiModel.setReferenceUid(dossier.getReferenceUid());
			pfiModel.setFeeAmount(paymentFile.getFeeAmount());
			pfiModel.setPaymentStatus(paymentFile.getPaymentStatus());
			pfiModel.setInvoiceTemplateNo(paymentFile.getInvoiceTemplateNo());
			pfiModel.setConfirmFileEntryId(paymentFile.getConfirmFileEntryId());
			pfiModel.setPaymentMethod(paymentFile.getPaymentMethod());
			
			client.postPaymentFiles(dossier.getReferenceUid(), pfiModel);
			
			_log.debug("OpenCPS END SYNC PAYMENTFILE FROM SYNCREQUEST REQUESTPAYMENT = 3: " + APIDateTimeUtils.convertDateToString(new Date()));
		}
		else if(processAction != null && (processAction.getRequestPayment() == ProcessActionTerm.REQUEST_PAYMENT_XAC_NHAN_HOAN_THANH_THU_PHI)){
			_log.debug("OpenCPS START SYNC PAYMENTFILE FROM SYNCREQUEST REQUESTPAYMENT = 5: "
					+ APIDateTimeUtils.convertDateToString(new Date()));
			PaymentFile paymentFile = PaymentFileLocalServiceUtil.fectPaymentFile(dossier.getDossierId(), dossierSync.getDossierRefUid());
			
			//_log.debug("SONDT DOSSIER REQUEST ======================== " + JSONFactoryUtil.looseSerialize(dossier));
			//_log.debug("SONDT DOSSIERSYNC REQUEST ======================== " + JSONFactoryUtil.looseSerialize(dossierSync));
			
			//_log.debug("SONDT PAYMENTFILE SYNC REQUEST ======================== " + JSONFactoryUtil.looseSerialize(paymentFile));
			
			PaymentFileInputModel pfiModel = new PaymentFileInputModel();
			pfiModel.setApplicantIdNo(dossier.getApplicantIdNo());
			pfiModel.setApplicantName(dossier.getApplicantName());
			pfiModel.setBankInfo(paymentFile.getBankInfo());
			pfiModel.setEpaymentProfile(paymentFile.getEpaymentProfile());
			pfiModel.setGovAgencyCode(dossier.getGovAgencyCode());
			pfiModel.setGovAgencyName(dossier.getGovAgencyName());
			pfiModel.setPaymentAmount(paymentFile.getPaymentAmount());
			pfiModel.setPaymentFee(paymentFile.getPaymentFee());
			pfiModel.setPaymentNote(paymentFile.getPaymentNote());
			pfiModel.setReferenceUid(dossier.getReferenceUid());
			pfiModel.setFeeAmount(paymentFile.getFeeAmount());
			pfiModel.setPaymentStatus(paymentFile.getPaymentStatus());
			pfiModel.setInvoiceTemplateNo(paymentFile.getInvoiceTemplateNo());
			pfiModel.setConfirmFileEntryId(paymentFile.getConfirmFileEntryId());
			pfiModel.setPaymentMethod(paymentFile.getPaymentMethod());
			
			client.postPaymentFiles(dossier.getReferenceUid(), pfiModel);
			
			_log.debug("OpenCPS END SYNC PAYMENTFILE FROM SYNCREQUEST REQUESTPAYMENT = 5: " + APIDateTimeUtils.convertDateToString(new Date()));
		}
		//
		if (processAction != null && (processAction.getPreCondition().contains("payok")
				|| processAction.getPreCondition().toLowerCase().contains("sendinvoice=1"))) {
			PaymentFile paymentFile = PaymentFileLocalServiceUtil.fectPaymentFile(dossier.getDossierId(), dossierSync.getDossierRefUid());
			//_log.debug("SONDT PAYMENT FILE SYNC ======================== " + JSONFactoryUtil.looseSerialize(paymentFile));
//			_log.debug("DOSSIERID SYNC ======================== " + JSONFactoryUtil.looseSerialize(dossierSync));
			PaymentFileInputModel pfiModel = new PaymentFileInputModel();
			pfiModel.setApplicantIdNo(dossier.getApplicantIdNo());
			pfiModel.setApplicantName(dossier.getApplicantName());
			pfiModel.setBankInfo(paymentFile.getBankInfo());
			pfiModel.setEpaymentProfile(paymentFile.getEpaymentProfile());
			pfiModel.setGovAgencyCode(dossier.getGovAgencyCode());
			pfiModel.setGovAgencyName(dossier.getGovAgencyName());
			pfiModel.setPaymentAmount(paymentFile.getPaymentAmount());
			pfiModel.setPaymentFee(processAction.getPaymentFee());
			pfiModel.setPaymentNote(paymentFile.getPaymentNote());
			pfiModel.setReferenceUid(dossier.getReferenceUid());
			pfiModel.setFeeAmount(paymentFile.getFeeAmount());
			pfiModel.setInvoiceTemplateNo(paymentFile.getInvoiceTemplateNo());
			pfiModel.setPaymentStatus(paymentFile.getPaymentStatus());
			pfiModel.setEinvoice(paymentFile.getEinvoice());
			pfiModel.setPaymentMethod(paymentFile.getPaymentMethod());
			
			client.postPaymentFiles(dossier.getReferenceUid(), pfiModel);			
		}
		ExecuteOneAction actionModel = new ExecuteOneAction();
		actionModel.setActionCode(dossierSync.getActionCode());
		actionModel.setActionUser(dossierSync.getActionUser());
		actionModel.setActionNote(dossierSync.getActionNote());
		actionModel.setPayload(dossierSync.getPayload());
		
		ExecuteOneAction actionResult = client.postDossierAction(String.valueOf(result.getDossierId()), actionModel);
		if (actionResult != null) {
			if (client.isWriteLog()) {
				dossierSync.setMessageText(messageText.toString());
				dossierSync.setAcknowlegement(messageText.toString());
				DossierSyncLocalServiceUtil.updateDossierSync(dossierSync);
			}
			return true;
		}
		else {
			if (client.isWriteLog()) {
				dossierSync.setMessageText(messageText.toString());
				dossierSync.setAcknowlegement(messageText.toString());
				DossierSyncLocalServiceUtil.updateDossierSync(dossierSync);
			}
			return false;
		}		
	}
}