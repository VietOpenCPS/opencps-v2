package org.opencps.api.controller.util;

import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.opencps.api.dossier.model.ActionExecutedModel;
import org.opencps.api.dossier.model.ListContacts;
import org.opencps.api.dossieraction.model.DossierActionNextActionModel;
import org.opencps.api.dossieraction.model.DossierActionNextActionReturnFiles;
import org.opencps.api.dossieraction.model.DossierActionNextActioncreateFiles;
import org.opencps.api.dossieraction.model.DossierActionNextActiontoUser;
import org.opencps.api.dossieraction.model.DossierActionPaymentModel;
import org.opencps.api.dossieraction.model.DossierDetailNextActionModel;
import org.opencps.api.dossieraction.model.DossierNextActionModel;
import org.opencps.api.dossieraction.model.DossierPayLoadModel;
import org.opencps.api.dossieraction.model.ReceivingModel;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.dossiermgt.constants.DossierActionTerm;
import org.opencps.dossiermgt.constants.DossierFileTerm;
import org.opencps.dossiermgt.constants.DossierPartTerm;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.constants.ProcessActionTerm;
import org.opencps.dossiermgt.constants.ProcessStepRoleTerm;
import org.opencps.dossiermgt.model.ActionConfig;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.DossierActionUser;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.ProcessAction;
import org.opencps.dossiermgt.model.ProcessStep;
import org.opencps.dossiermgt.model.ProcessStepRole;
import org.opencps.dossiermgt.model.ServiceProcess;
import org.opencps.dossiermgt.model.ServiceProcessRole;
import org.opencps.dossiermgt.service.ActionConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierActionUserLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessStepRoleLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceProcessLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceProcessRoleLocalServiceUtil;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;

public class DossierActionUtils {

	private static Log _log = LogFactoryUtil.getLog(DossierActionUtils.class);
	
	public static List<User> processRoleAsStepDonedListUser(Dossier dossier, String stepCode, long serviceProcessId, ProcessStep processStep) {
		List<User> lstUser = null;
		// Check roles
		List<DossierActionUser> lstDaus = DossierActionUserLocalServiceUtil.getByDossierAndStepCode(dossier.getDossierId(), stepCode);
		
		lstUser = new ArrayList<User>();
		List<ProcessStepRole> processStepRoleList = ProcessStepRoleLocalServiceUtil
				.findByP_S_ID(processStep.getProcessStepId());
		for (ProcessStepRole role : processStepRoleList) {
			List<User> lstUsers = UserLocalServiceUtil.getRoleUsers(role.getRoleId());
			for (User u : lstUsers) {
				for (DossierActionUser dau : lstDaus) {
					if (dau.getUserId() == u.getUserId()) {
						User user = UserLocalServiceUtil.fetchUser(dau.getUserId());
						
						HashMap<String, Object> assigned = new HashMap<>();
						assigned.put(ProcessStepRoleTerm.ASSIGNED, dau.getAssigned());
						HashMap<String, Object> moderator = new HashMap<>();
						moderator.put(ProcessStepRoleTerm.MODERATOR, dau.getModerator() == 1 ? true : false);
						user.setModelAttributes(moderator);
						user.setModelAttributes(assigned);

						lstUser.add(user);
						break;
					}
				}
			}
		}

		return lstUser;
	}
	
	public static List<User> processRoleAsStepListUser(Dossier dossier, String stepCode, long serviceProcessId, ProcessStep processStep) {
		List<User> lstUser = null;
		// Check roles		
		lstUser = new ArrayList<User>();

		List<ProcessStepRole> processStepRoleList = ProcessStepRoleLocalServiceUtil
				.findByP_S_ID(processStep.getProcessStepId());
		for (ProcessStepRole role : processStepRoleList) {
			List<User> lstUsers = UserLocalServiceUtil.getRoleUsers(role.getRoleId());
			for (User u : lstUsers) {
				HashMap<String, Object> assigned = new HashMap<>();
				assigned.put(ProcessStepRoleTerm.ASSIGNED, 0);	
				HashMap<String, Object> moderator = new HashMap<>();
				moderator.put(ProcessStepRoleTerm.MODERATOR, role.getModerator());
				u.setModelAttributes(moderator);
				u.setModelAttributes(assigned);

				lstUser.add(u);
			}
		}

		return lstUser;
	}
	
	public static List<User> processRoleListUser(List<ProcessStepRole> processStepRoleList, long serviceProcessId) {
		List<User> lstUser = null;
		// Check roles
		_log.info("processStepRoleList: "+processStepRoleList);
		if (processStepRoleList != null && processStepRoleList.size() > 0) {
			_log.info("processStepRoleList.size(): "+processStepRoleList.size());
			lstUser = new ArrayList<User>();
			for (ProcessStepRole processStepRole : processStepRoleList) {
				List<User> users = UserLocalServiceUtil.getRoleUsers(processStepRole.getRoleId());
				if (users != null && users.size() > 0) {
					_log.info("users.size(): "+users.size());
					HashMap<String, Object> assigned = new HashMap<>();
					assigned.put(ProcessStepRoleTerm.ASSIGNED, 0);
					for (User user : users) {
						HashMap<String, Object> moderator = new HashMap<>();
						moderator.put(ProcessStepRoleTerm.MODERATOR, processStepRole.getModerator());
						user.setModelAttributes(moderator);
						user.setModelAttributes(assigned);
					}
					lstUser.addAll(users);
				}
			}
		} else {
			// Search in ServiceProcessRole
			List<ServiceProcessRole> serviceProcessRoleList = ServiceProcessRoleLocalServiceUtil
					.findByS_P_ID(serviceProcessId);
			if (serviceProcessRoleList != null && serviceProcessRoleList.size() > 0) {
				lstUser = new ArrayList<User>();
				for (ServiceProcessRole serviceProcessRole : serviceProcessRoleList) {
					List<User> users = UserLocalServiceUtil
							.getRoleUsers(serviceProcessRole.getRoleId());
					if (users != null && users.size() > 0) {
						for (User user : users) {
							HashMap<String, Object> moderator = new HashMap<>();
							moderator.put("moderator", serviceProcessRole.getModerator());
							user.setModelAttributes(moderator);
						}
						lstUser.addAll(users);
					}
				}
			}
		}

		return lstUser;
	}
	
	public static List<DossierActionNextActionModel> mappingToDoListActions(List<ProcessAction> lstProcessAction,
			List<User> lstUser) {

		List<DossierActionNextActionModel> outputs = new ArrayList<DossierActionNextActionModel>();

		for (ProcessAction processAction : lstProcessAction) {

			DossierActionNextActionModel model = mappingToDoActionModel(processAction);
			outputs.add(model);

		}
		return outputs;
	}

	public static DossierActionNextActionModel mappingToDoActionModel(ProcessAction processAction) {

		DossierActionNextActionModel model = new DossierActionNextActionModel();

		model.setActionCode(processAction.getActionCode());
		model.setActionName(processAction.getActionName());
		model.setPostStepCode(processAction.getPostStepCode());
		model.setAutoEvent(processAction.getAutoEvent());
		model.setAssignUserId((processAction.getAssignUserId()));

		return model;

	}

	@SuppressWarnings("unchecked")
	public static List<DossierActionNextActionModel> mappingToDoListReadNextActions(JSONArray jsonData) {
		List<DossierActionNextActionModel> outputs = new ArrayList<DossierActionNextActionModel>();

		if (jsonData != null) {
			for (int i = 0; i < jsonData.length(); i++) {
				
				JSONObject jsonObject = jsonData.getJSONObject(i);
				
				ProcessAction processAction = (ProcessAction) jsonObject.get("processAction");

				List<User> lstUser = (List<User>) jsonObject.get("lstUser");

				JSONArray createFiles = jsonObject.getJSONArray("createFiles");
				
				boolean pending = jsonObject.getBoolean("pending");

				DossierActionNextActionModel model = new DossierActionNextActionModel();

				long processActionId = GetterUtil.getLong(processAction.getProcessActionId());
				long assignUserId = GetterUtil.getLong(processAction.getAssignUserUuid());

				model.setProcessActionId(processActionId);
				model.setActionCode(processAction.getActionCode());
				model.setActionName(processAction.getActionName());
				model.setPreStepCode(processAction.getPreStepCode());
				model.setPostStepCode(processAction.getPostStepCode());
				model.setAutoEvent(processAction.getAutoEvent());
				model.setPreCondition(processAction.getPreCondition());
				model.setAllowAssignUser(processAction.getAllowAssignUser());
				model.setPending(pending);
				model.setAssignUserId(assignUserId);
				
				model.setConfigNote(processAction.getConfigNote() );
				
				List<DossierActionNextActiontoUser> outputUsers = new ArrayList<DossierActionNextActiontoUser>();

				for (User user : lstUser) {
					DossierActionNextActiontoUser modelUser = new DossierActionNextActiontoUser();

					Map<String, Object> attr = user.getModelAttributes();

					long userId = GetterUtil.getLong(user.getUserId());

					boolean moderator = false;

					if (attr != null && attr.containsKey("moderator")) {
						moderator = GetterUtil.getBoolean(attr.get("moderator"));
					}

					modelUser.setUserId(userId);

					modelUser.setUserName(user.getFullName());

					modelUser.setModerator(moderator);

					outputUsers.add(modelUser);
				}

				model.getToUsers().addAll(outputUsers);

				List<DossierActionNextActioncreateFiles> outputCreeateFiles = new ArrayList<DossierActionNextActioncreateFiles>();

				if (createFiles != null && createFiles.length() > 0) {
					for (int f = 0; f < createFiles.length(); f++) {
						JSONObject createFile = createFiles.getJSONObject(f);
						DossierActionNextActioncreateFiles dossierActionNextActioncreateFile = new DossierActionNextActioncreateFiles();
						dossierActionNextActioncreateFile.setDossierPartId(createFile.getLong("dossierPartId"));
						dossierActionNextActioncreateFile.setEForm(createFile.getBoolean("eform"));
						dossierActionNextActioncreateFile.setFormData(createFile.getString("formData"));
						dossierActionNextActioncreateFile.setFormScript(createFile.getString("formScript"));
						dossierActionNextActioncreateFile.setMultiple(createFile.getBoolean("multiple"));
						dossierActionNextActioncreateFile.setPartName(createFile.getString("partName"));
						dossierActionNextActioncreateFile.setPartNo(createFile.getString("partNo"));
						dossierActionNextActioncreateFile.setPartTip(createFile.getString("partTip"));
						dossierActionNextActioncreateFile.setTemplateFileNo(createFile.getString("templateFileNo"));
						dossierActionNextActioncreateFile.setReferenceUid(createFile.getString("referenceUid"));
						dossierActionNextActioncreateFile.setCounter(createFile.getInt("counter"));
						dossierActionNextActioncreateFile.setReturned(createFile.getBoolean("returned"));
						dossierActionNextActioncreateFile.setDossierFileId(createFile.getLong("dossierFileId"));
						outputCreeateFiles.add(dossierActionNextActioncreateFile);

					}
				}

				model.getCreateFiles().addAll(outputCreeateFiles);
				model.setCreateDossierNo(processAction.getCreateDossierNo());
				model.seteSignature(processAction.getESignature());
				outputs.add(model);
			}
		}

		return outputs;
	}

	//LamTV_Process mapping getNextAction
	public static List<DossierNextActionModel> mappingToNextActions(JSONArray jsonData) {

		List<DossierNextActionModel> outputs = new ArrayList<DossierNextActionModel>();

		if (jsonData != null && jsonData.length() > 0) {
			for (int i = 0; i < jsonData.length(); i++) {

				JSONObject jsonObject = jsonData.getJSONObject(i);
				DossierNextActionModel model = new DossierNextActionModel();

				model.setProcessActionId(Long.valueOf(jsonObject.getString(ProcessActionTerm.PROCESS_ACTION_ID)));
				model.setActionCode(jsonObject.getString(ProcessActionTerm.ACTION_CODE));
				model.setActionName(jsonObject.getString(ProcessActionTerm.ACTION_NAME));
				model.setPreStepCode(jsonObject.getString(ProcessActionTerm.PRESTEP_CODE));
				model.setPostStepCode(jsonObject.getString(ProcessActionTerm.POSTSTEP_CODE));
				model.setAutoEvent(jsonObject.getString(ProcessActionTerm.AUTO_EVENT));
				model.setPreCondition(jsonObject.getString(ProcessActionTerm.PRE_CONDITION));
				model.setEnable(Integer.valueOf(jsonObject.getString(ProcessActionTerm.ENABLE)));
				model.setAllowDelegacyUser(jsonObject.getString(ProcessActionTerm.ALLOW_ASSIGN_USER));

				outputs.add(model);
			}
		}

		return outputs;
	}

	//LamTV_Mapping detail Next Action
	@SuppressWarnings("unchecked")
	public static DossierDetailNextActionModel mappingToDetailNextActions(JSONObject jsonData) throws PortalException {
		DossierDetailNextActionModel model = null;
		try{
		

		if (jsonData != null) {
			model = new DossierDetailNextActionModel();

			ProcessAction processAction = (ProcessAction) jsonData.get("processAction");
			List<User> lstUser = (List<User>) jsonData.get("lstUser");
//			_log.info("LAmTV_List user: "+lstUser);
			JSONArray createFiles = jsonData.getJSONArray("createFiles");
			List<DossierFile> returnFiles = (List<DossierFile>) jsonData.get("returnFiles");

			DossierActionPaymentModel payment = new DossierActionPaymentModel();
			if (processAction != null) {
				model.setProcessActionId(processAction.getProcessActionId());
				model.setActionCode(processAction.getActionCode());
				model.setActionName(processAction.getActionName());
				model.setPreStepCode(processAction.getPreStepCode());
				model.setPostStepCode(processAction.getPostStepCode());
				model.setAutoEvent(processAction.getAutoEvent());
				model.setPreCondition(processAction.getPreCondition());
				model.setAllowAssignUser(processAction.getAllowAssignUser());
				model.seteSignature(processAction.getESignature());
				model.setSignatureType(processAction.getSignatureType());
//				model.setCheckInput(processAction.getCheckInput());
				model.setConfigNote(processAction.getConfigNote());
				ActionConfig act = ActionConfigLocalServiceUtil.getByCode(processAction.getGroupId(),
						processAction.getActionCode());
				if (act != null) {
					model.setUserNote(act.getUserNote());
					model.setExtraForm(act.getExtraForm());
				} else {
					model.setUserNote(0);
					model.setExtraForm(false);
				}
//				_log.info("Payment object: " + jsonData.toJSONString());
//				_log.info("Payment object: " + jsonData.getJSONObject("payment").toJSONString());
				JSONObject paymentObject = jsonData.getJSONObject("payment");
				if (paymentObject != null) {
					if (paymentObject.has("paymentFee")) {
						payment.setPaymentFee(paymentObject.getString("paymentFee"));
					}
					if (paymentObject.has("paymentNote")) {
						payment.setPaymentNote(paymentObject.getString("paymentNote"));
					}
					if (paymentObject.has("requestPayment")) {
						payment.setRequestPayment(paymentObject.getInt("requestPayment"));
					}
					if (paymentObject.has("advanceAmount")) {
						payment.setAdvanceAmount(paymentObject.getLong("advanceAmount"));
					}
					if (paymentObject.has("feeAmount")) {
						payment.setFeeAmount(paymentObject.getLong("feeAmount"));
					}
					if (paymentObject.has("serviceAmount")) {
						payment.setServiceAmount(paymentObject.getLong("serviceAmount"));
					}
					if (paymentObject.has("shipAmount")) {
						payment.setShipAmount(paymentObject.getLong("shipAmount"));
					}
					if (paymentObject.has("editable")) {
						payment.setEditable(paymentObject.getInt("editable"));
					}
				}
				
				model.setPayment(payment);
				
//				_log.info("Payment: " + payment);
				//
//				String strPaymentFee = processAction.getPaymentFee();
//				_log.info("String payment fee: " + strPaymentFee);
//				if (Validator.isNotNull(strPaymentFee)) {
//					JSONObject paymentFee = JSONFactoryUtil.createJSONObject(strPaymentFee);
//					if (paymentFee != null) {
//						//
//						payment.setRequestPayment(paymentFee.getInt("requestPayment"));
//						payment.setAdvanceAmount(paymentFee.getLong("advanceAmount"));
//						payment.setFeeAmount(paymentFee.getLong("feeAmount"));
//						payment.setServiceAmount(paymentFee.getLong("serviceAmount"));
//						payment.setShipAmount(paymentFee.getLong("shipAmount"));
//						payment.setEditable(paymentFee.getBoolean("editable"));
//						
//						model.setPayment(payment);
//					}
//				}
			} else {
				model.setPayment(payment);
			}

			JSONObject receivingObj = jsonData.getJSONObject("receiving");
//			_log.info("Receiving object: " + receivingObj.toJSONString());
			if (receivingObj != null) {
				ReceivingModel receiving = new ReceivingModel();
				receiving.setDueDate(receivingObj.getLong(DossierTerm.DUE_DATE));
				receiving.setReceiveDate(receivingObj.getLong(DossierTerm.RECEIVE_DATE));
				receiving.setEditable(receivingObj.getBoolean("editable"));
				
				model.setReceiving(receiving);
			}
			
			List<DossierActionNextActiontoUser> outputUsers = new ArrayList<DossierActionNextActiontoUser>();
			DossierActionNextActiontoUser modelUser = null;
			if (lstUser != null && lstUser.size() > 0) {
				boolean moderator = false;
				int assigned = 0;
				for (User user: lstUser) {
					long userId = user.getUserId();
					Employee employee = EmployeeLocalServiceUtil.fetchByFB_MUID(userId);
					_log.debug("employee1: "+employee);
					if (employee != null && employee.getWorkingStatus() == 1) {
						modelUser = new DossierActionNextActiontoUser();
						Map<String, Object> attr = user.getModelAttributes();
	
						moderator = false;
						assigned = 0;
						if (attr != null) {
							if (attr.containsKey(ProcessStepRoleTerm.MODERATOR)) {
								moderator = GetterUtil.getBoolean(attr.get(ProcessStepRoleTerm.MODERATOR));
							}
							if (attr.containsKey(ProcessStepRoleTerm.ASSIGNED)) {
								assigned = GetterUtil.getInteger(attr.get(ProcessStepRoleTerm.ASSIGNED));
							}
						}
	
						modelUser.setUserId(userId);
	
						if (employee != null) {
							modelUser.setUserName(employee.getFullName());
						}
						//else {
						//	modelUser.setUserName(user.getFullName());
						//}
						
						modelUser.setModerator(moderator);
						modelUser.setAssigned(assigned);
						boolean flag = true;
						if (outputUsers != null && !outputUsers.isEmpty()) {
							for (DossierActionNextActiontoUser doUserAct : outputUsers) {
								if (userId == doUserAct.getUserId()) {
									flag = false;
									break;
								}
							}
							if (flag) {
								outputUsers.add(modelUser);
							}
						} else {
							outputUsers.add(modelUser);
						}
					}
				}
			}
			model.setToUsers(outputUsers);

			List<DossierActionNextActioncreateFiles> outputCreeateFiles = null;
			if (createFiles != null && createFiles.length() > 0) {
				outputCreeateFiles = new ArrayList<DossierActionNextActioncreateFiles>();
				for (int j = 0; j < createFiles.length(); j++) {
					JSONObject createFile = createFiles.getJSONObject(j);
					DossierActionNextActioncreateFiles dossierActionNextActioncreateFile = new DossierActionNextActioncreateFiles();
					dossierActionNextActioncreateFile.setDossierPartId(createFile.getLong(DossierPartTerm.DOSSIERPART_ID));
					dossierActionNextActioncreateFile.setEForm(createFile.getBoolean(DossierPartTerm.EFORM));
					dossierActionNextActioncreateFile.setFormData(createFile.getString(DossierPartTerm.FORM_DATA));
					dossierActionNextActioncreateFile.setFormScript(createFile.getString(DossierPartTerm.FORM_SCRIPT));
					dossierActionNextActioncreateFile.setMultiple(createFile.getBoolean(DossierPartTerm.MULTIPLE));
					dossierActionNextActioncreateFile.setPartName(createFile.getString(DossierPartTerm.PART_NAME));
					dossierActionNextActioncreateFile.setPartNo(createFile.getString(DossierPartTerm.PART_NO));
					dossierActionNextActioncreateFile.setPartType(createFile.getInt(DossierPartTerm.PART_TYPE));
					dossierActionNextActioncreateFile.setPartTip(createFile.getString(DossierPartTerm.PART_TIP));
					dossierActionNextActioncreateFile.setTemplateFileNo(createFile.getString(DossierFileTerm.FILE_TEMPLATE_NO));
					dossierActionNextActioncreateFile.setReferenceUid(createFile.getString(DossierPartTerm.REFERENCE_UID));
					dossierActionNextActioncreateFile.setCounter(createFile.getInt(DossierPartTerm.COUNTER));
					dossierActionNextActioncreateFile.setDossierFileId(createFile.getLong(DossierPartTerm.DOSSIER_FILE_ID));
					outputCreeateFiles.add(dossierActionNextActioncreateFile);
				}
				model.getCreateFiles().addAll(outputCreeateFiles);
			}

				List<DossierActionNextActionReturnFiles> outputReturnFiles = null;
				if (returnFiles != null && returnFiles.size() > 0) {
					outputReturnFiles = new ArrayList<DossierActionNextActionReturnFiles>();
					for (DossierFile dossierFile: returnFiles) {
						DossierActionNextActionReturnFiles dActionReturnFile = new DossierActionNextActionReturnFiles();

					dActionReturnFile.setCreateDate(APIDateTimeUtils.convertDateToString(dossierFile.getCreateDate(),
							APIDateTimeUtils._TIMESTAMP));
					dActionReturnFile.setModifiedDate(APIDateTimeUtils.convertDateToString(dossierFile.getModifiedDate(),
							APIDateTimeUtils._TIMESTAMP));
					dActionReturnFile.setReferenceUid(dossierFile.getReferenceUid());
					dActionReturnFile.setDossierTemplateNo(dossierFile.getDossierTemplateNo());
					dActionReturnFile.setDossierPartNo(dossierFile.getDossierPartNo());
					dActionReturnFile.setFileTemplateNo(dossierFile.getFileTemplateNo());
					dActionReturnFile.setDisplayName(dossierFile.getDisplayName());
					dActionReturnFile.setDeliverableCode(dossierFile.getDeliverableCode());
					dActionReturnFile.setSignInfo(dossierFile.getSignInfo());
					dActionReturnFile.setSignCheck(dossierFile.getSignCheck());
					//
					long fileEntryId = dossierFile.getFileEntryId();
					if (fileEntryId > 0) {
						FileEntry file = DLAppServiceUtil.getFileEntry(fileEntryId);
						if (file != null) {
							dActionReturnFile.setFileType(file.getMimeType());
							dActionReturnFile.setFileSize(file.getSize());
							dActionReturnFile.setVersion(file.getVersion());
						}
					}
					outputReturnFiles.add(dActionReturnFile);

					}
					model.getReturnFiles().addAll(outputReturnFiles);
				}
		}
		}catch (Exception e) {
			_log.info(e);
		}

		return model;
	}

	public static List<org.opencps.api.dossieraction.model.DossierActionModel> mappingToDoListReadActionExecuted(
			List<Document> documents) {
		List<org.opencps.api.dossieraction.model.DossierActionModel> outputs = new ArrayList<org.opencps.api.dossieraction.model.DossierActionModel>();
		for (Document document : documents) {
			org.opencps.api.dossieraction.model.DossierActionModel model = new org.opencps.api.dossieraction.model.DossierActionModel();

			long dossierActionId = GetterUtil.getLong(document.get("entryClassPK"));
			long userId = GetterUtil.getLong(document.get(DossierActionTerm.USER_ID));
			long actionOverDue = GetterUtil.getLong(document.get(DossierActionTerm.ACTION_OVER_DUE));
			long actionNote = GetterUtil.getLong(document.get(DossierActionTerm.ACTION_OVER_DUE));

			model.setDossierActionId(dossierActionId);
			model.setUserId(userId);
			model.setCreateDate(document.get(DossierActionTerm.CREATE_DATE));
			model.setActionCode(document.get(DossierActionTerm.ACTION_CODE));
			model.setActionUser(document.get(DossierActionTerm.ACTION_USER));
			model.setActionName(document.get(DossierActionTerm.ACTION_NAME));
			model.setActionNote(actionNote);
			model.setActionOverdue(actionOverDue);

			long serviceProcessId = GetterUtil.getLong(document.get(DossierActionTerm.SERVICE_PROCESS_ID));
			ServiceProcess serviceProcess = ServiceProcessLocalServiceUtil.fetchServiceProcess(serviceProcessId);

			model.setDurationUnit(serviceProcess.getDurationUnit());
			model.setRollbackable(document.get(DossierActionTerm.ROLLBACK_ABLE));
			model.setStepCode(document.get(DossierActionTerm.STEP_CODE));
			model.setStepName(document.get(DossierActionTerm.STEP_NAME));
			model.setStepInstruction(document.get(DossierActionTerm.STEP_INSTRUCTION));
			model.setPayload(document.get(DossierActionTerm.PAYLOAD));
			model.setDueDate(document.get(DossierActionTerm.DUE_DATE));

			outputs.add(model);
		}
		return outputs;
	}

	public static ActionExecutedModel mappingToDoActionExecutedModel(DossierAction dossierAction) {
		if (dossierAction == null) {
			return null;
		}

		ActionExecutedModel model = new ActionExecutedModel();

		model.setDossierActionId((int) dossierAction.getDossierActionId());
		model.setUserId((int) dossierAction.getUserId());
		model.setCreateDate(APIDateTimeUtils.convertDateToString(dossierAction.getDueDate()));
		model.setActionCode(dossierAction.getActionCode());
		model.setActionUser(dossierAction.getActionUser());
		model.setActionName(dossierAction.getActionName());
		model.setActionNote(dossierAction.getActionNote());
		model.setActionOverdue(dossierAction.getActionOverdue());

		long serviceProcessId = dossierAction.getServiceProcessId();
		ServiceProcess serviceProcess = ServiceProcessLocalServiceUtil.fetchServiceProcess(serviceProcessId);

		model.setDurationUnit(serviceProcess.getDurationUnit());
		model.setRollbackable("" + dossierAction.getRollbackable());
		model.setStepCode(dossierAction.getStepCode());
		model.setStepName(dossierAction.getStepName());
		model.setStepInstruction(dossierAction.getStepInstruction());
		model.setPayload(dossierAction.getPayload());
		model.setDueDate(APIDateTimeUtils.convertDateToString(dossierAction.getDueDate()));

		return model;

	}

	public static List<ListContacts> mappingToDoListContacts(List<Dossier> dossiers) {
		List<ListContacts> listContacts = new ArrayList<>();
		ListContacts contact;
		for (Dossier dossier : dossiers) {

			contact = new ListContacts();
			contact.setUserId((int) dossier.getUserId());
			contact.setUserName(dossier.getUserName());
			contact.setTelNo(dossier.getContactTelNo());
			contact.setEmail(dossier.getContactEmail());
			listContacts.add(contact);
		}
		return listContacts;
	}

	public static List<DossierPayLoadModel> mappingToPayLoadNextActions(JSONArray jsonData) {

		List<DossierPayLoadModel> outputs = new ArrayList<DossierPayLoadModel>();
		if (jsonData != null && jsonData.length() > 0) {
			for (int i = 0; i < jsonData.length(); i++) {
				JSONObject jsonObject = jsonData.getJSONObject(i);
				DossierPayLoadModel model = new DossierPayLoadModel();

				model.setFieldLabel(jsonObject.getString(ProcessActionTerm.FIELD_LABEL));
				model.setFieldName(jsonObject.getString(ProcessActionTerm.FIELD_NAME));
				model.setFieldType(jsonObject.getString(ProcessActionTerm.FIELD_TYPE));
				model.setValue(jsonObject.getString(ProcessActionTerm.VALUE));
				if (jsonObject.has("required")) {
					model.setRequired(jsonObject.getBoolean("required"));
				}
				outputs.add(model);
			}
		}

		return outputs;
	}
}
