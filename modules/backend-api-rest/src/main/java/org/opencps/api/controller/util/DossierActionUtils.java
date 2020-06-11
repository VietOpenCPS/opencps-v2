
package org.opencps.api.controller.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.opencps.api.constants.ConstantUtils;
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
import org.opencps.api.dossiertemplate.model.DossierTemplatePartDataModel;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.dossiermgt.constants.DeliverableTerm;
import org.opencps.dossiermgt.constants.DossierActionTerm;
import org.opencps.dossiermgt.constants.DossierFileTerm;
import org.opencps.dossiermgt.constants.DossierPartTerm;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.constants.PaymentFileTerm;
import org.opencps.dossiermgt.constants.ProcessActionTerm;
import org.opencps.dossiermgt.constants.ProcessStepRoleTerm;
import org.opencps.dossiermgt.model.ActionConfig;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.DossierActionUser;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.DossierPart;
import org.opencps.dossiermgt.model.DossierTemplate;
import org.opencps.dossiermgt.model.ProcessAction;
import org.opencps.dossiermgt.model.ProcessOption;
import org.opencps.dossiermgt.model.ProcessStep;
import org.opencps.dossiermgt.model.ProcessStepRole;
import org.opencps.dossiermgt.model.ServiceConfig;
import org.opencps.dossiermgt.model.ServiceProcess;
import org.opencps.dossiermgt.model.ServiceProcessRole;
import org.opencps.dossiermgt.service.ActionConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierActionUserLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierPartLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierTemplateLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessOptionLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessStepRoleLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceProcessLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceProcessRoleLocalServiceUtil;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.model.JobPos;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;
import org.opencps.usermgt.service.JobPosLocalServiceUtil;

import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.petra.string.StringPool;
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
import com.liferay.portal.kernel.util.Validator;

public class DossierActionUtils {

	private static Log _log = LogFactoryUtil.getLog(DossierActionUtils.class);

	public static List<User> processRoleAsStepDonedListUser(
		Dossier dossier, String stepCode, long serviceProcessId,
		ProcessStep processStep) {

		List<User> lstUser = null;
		// Check roles
		List<DossierActionUser> lstDaus =
			DossierActionUserLocalServiceUtil.getByDossierAndStepCode(
				dossier.getDossierId(), stepCode);

		lstUser = new ArrayList<User>();
		List<ProcessStepRole> processStepRoleList =
			ProcessStepRoleLocalServiceUtil.findByP_S_ID(
				processStep.getProcessStepId());
		for (ProcessStepRole role : processStepRoleList) {
			List<User> lstUsers =
				UserLocalServiceUtil.getRoleUsers(role.getRoleId());
			for (User u : lstUsers) {
				for (DossierActionUser dau : lstDaus) {
					if (dau.getUserId() == u.getUserId()) {
						User user =
							UserLocalServiceUtil.fetchUser(dau.getUserId());

						HashMap<String, Object> assigned = new HashMap<>();
						assigned.put(
							ProcessStepRoleTerm.ASSIGNED, dau.getAssigned());
						HashMap<String, Object> moderator = new HashMap<>();
						moderator.put(
							ProcessStepRoleTerm.MODERATOR,
							dau.getModerator() == 1 ? true : false);
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

	public static List<User> processRoleAsStepListUser(
		Dossier dossier, String stepCode, long serviceProcessId,
		ProcessStep processStep) {

		List<User> lstUser = null;
		// Check roles
		lstUser = new ArrayList<User>();

		List<ProcessStepRole> processStepRoleList =
			ProcessStepRoleLocalServiceUtil.findByP_S_ID(
				processStep.getProcessStepId());
		for (ProcessStepRole role : processStepRoleList) {
			List<User> lstUsers =
				UserLocalServiceUtil.getRoleUsers(role.getRoleId());
			for (User u : lstUsers) {
				HashMap<String, Object> assigned = new HashMap<>();
				assigned.put(ProcessStepRoleTerm.ASSIGNED, 0);
				HashMap<String, Object> moderator = new HashMap<>();
				moderator.put(
					ProcessStepRoleTerm.MODERATOR, role.getModerator());
				u.setModelAttributes(moderator);
				u.setModelAttributes(assigned);

				lstUser.add(u);
			}
		}

		return lstUser;
	}

	public static List<User> processRoleListUser(
		List<ProcessStepRole> processStepRoleList, long serviceProcessId) {

		List<User> lstUser = null;
		// Check roles
		_log.info("processStepRoleList: " + processStepRoleList);
		if (processStepRoleList != null && processStepRoleList.size() > 0) {
			_log.info(
				"processStepRoleList.size(): " + processStepRoleList.size());
			lstUser = new ArrayList<User>();
			for (ProcessStepRole processStepRole : processStepRoleList) {
				List<User> users = UserLocalServiceUtil.getRoleUsers(
					processStepRole.getRoleId());
				if (users != null && users.size() > 0) {
					_log.info("users.size(): " + users.size());
					HashMap<String, Object> assigned = new HashMap<>();
					assigned.put(ProcessStepRoleTerm.ASSIGNED, 0);
					for (User user : users) {
						HashMap<String, Object> moderator = new HashMap<>();
						moderator.put(
							ProcessStepRoleTerm.MODERATOR,
							processStepRole.getModerator());
						user.setModelAttributes(moderator);
						user.setModelAttributes(assigned);
					}
					lstUser.addAll(users);
				}
			}
		}
		else {
			// Search in ServiceProcessRole
			List<ServiceProcessRole> serviceProcessRoleList =
				ServiceProcessRoleLocalServiceUtil.findByS_P_ID(
					serviceProcessId);
			if (serviceProcessRoleList != null &&
				serviceProcessRoleList.size() > 0) {
				lstUser = new ArrayList<User>();
				for (ServiceProcessRole serviceProcessRole : serviceProcessRoleList) {
					List<User> users = UserLocalServiceUtil.getRoleUsers(
						serviceProcessRole.getRoleId());
					if (users != null && users.size() > 0) {
						for (User user : users) {
							HashMap<String, Object> moderator = new HashMap<>();
							moderator.put(
								ConstantUtils.MODERATOR, serviceProcessRole.getModerator());
							user.setModelAttributes(moderator);
						}
						lstUser.addAll(users);
					}
				}
			}
		}

		return lstUser;
	}

	public static List<DossierActionNextActionModel> mappingToDoListActions(
		List<ProcessAction> lstProcessAction, List<User> lstUser) {

		List<DossierActionNextActionModel> outputs =
			new ArrayList<DossierActionNextActionModel>();

		for (ProcessAction processAction : lstProcessAction) {

			DossierActionNextActionModel model =
				mappingToDoActionModel(processAction);
			outputs.add(model);

		}
		return outputs;
	}

	public static DossierActionNextActionModel mappingToDoActionModel(
		ProcessAction processAction) {

		DossierActionNextActionModel model = new DossierActionNextActionModel();

		model.setActionCode(processAction.getActionCode());
		model.setActionName(processAction.getActionName());
		model.setPostStepCode(processAction.getPostStepCode());
		model.setAutoEvent(processAction.getAutoEvent());
		model.setAssignUserId((processAction.getAssignUserId()));

		return model;

	}

	@SuppressWarnings("unchecked")
	public static List<DossierActionNextActionModel> mappingToDoListReadNextActions(
		JSONArray jsonData) {

		List<DossierActionNextActionModel> outputs =
			new ArrayList<DossierActionNextActionModel>();

		if (jsonData != null) {
			for (int i = 0; i < jsonData.length(); i++) {

				JSONObject jsonObject = jsonData.getJSONObject(i);

				ProcessAction processAction =
					(ProcessAction) jsonObject.get(ProcessActionTerm.PROCESS_ACTION);

				List<User> lstUser = (List<User>) jsonObject.get(DossierActionTerm.LST_USER);

				JSONArray createFiles = jsonObject.getJSONArray(DossierActionTerm.CREATE_FILES);

				boolean pending = jsonObject.getBoolean(DossierActionTerm.PENDING);

				DossierActionNextActionModel model =
					new DossierActionNextActionModel();

				long processActionId =
					GetterUtil.getLong(processAction.getProcessActionId());
				long assignUserId =
					GetterUtil.getLong(processAction.getAssignUserUuid());

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

				model.setConfigNote(processAction.getConfigNote());

				List<DossierActionNextActiontoUser> outputUsers =
					new ArrayList<DossierActionNextActiontoUser>();

				for (User user : lstUser) {
					DossierActionNextActiontoUser modelUser =
						new DossierActionNextActiontoUser();

					Map<String, Object> attr = user.getModelAttributes();

					long userId = GetterUtil.getLong(user.getUserId());

					boolean moderator = false;

					if (attr != null && attr.containsKey(ConstantUtils.MODERATOR)) {
						moderator =
							GetterUtil.getBoolean(attr.get(ConstantUtils.MODERATOR));
					}

					modelUser.setUserId(userId);

					modelUser.setUserName(user.getFullName());

					modelUser.setModerator(moderator);

					outputUsers.add(modelUser);
				}

				model.getToUsers().addAll(outputUsers);

				List<DossierActionNextActioncreateFiles> outputCreeateFiles =
					new ArrayList<DossierActionNextActioncreateFiles>();

				if (createFiles != null && createFiles.length() > 0) {
					for (int f = 0; f < createFiles.length(); f++) {
						JSONObject createFile = createFiles.getJSONObject(f);
						DossierActionNextActioncreateFiles dossierActionNextActioncreateFile =
							new DossierActionNextActioncreateFiles();
						dossierActionNextActioncreateFile.setDossierPartId(
							createFile.getLong(DossierPartTerm.DOSSIERPART_ID));
						dossierActionNextActioncreateFile.setEForm(
							createFile.getBoolean(DossierPartTerm.EFORM));
						dossierActionNextActioncreateFile.setFormData(
							createFile.getString(DossierPartTerm.FORM_DATA));
						dossierActionNextActioncreateFile.setFormScript(
							createFile.getString(DossierPartTerm.FORM_SCRIPT));
						dossierActionNextActioncreateFile.setMultiple(
							createFile.getBoolean(DossierPartTerm.MULTIPLE));
						dossierActionNextActioncreateFile.setPartName(
							createFile.getString(DossierPartTerm.PART_NAME));
						dossierActionNextActioncreateFile.setPartNo(
							createFile.getString(DossierPartTerm.PART_NO));
						dossierActionNextActioncreateFile.setPartTip(
							createFile.getString(DossierPartTerm.PART_TIP));
						dossierActionNextActioncreateFile.setTemplateFileNo(
							createFile.getString(DossierPartTerm.TEMPLATE_FILE_NO));
						dossierActionNextActioncreateFile.setReferenceUid(
							createFile.getString(DossierFileTerm.REFERENCE_UID));
						dossierActionNextActioncreateFile.setCounter(
							createFile.getInt(DossierFileTerm.COUNTER));
						dossierActionNextActioncreateFile.setReturned(
							createFile.getBoolean(DossierFileTerm.RETURNED));
						dossierActionNextActioncreateFile.setDossierFileId(
							createFile.getLong(DossierFileTerm.DOSSIER_FILE_ID));
						outputCreeateFiles.add(
							dossierActionNextActioncreateFile);

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

	// LamTV_Process mapping getNextAction
	public static List<DossierNextActionModel> mappingToNextActions(
		JSONArray jsonData) {

		List<DossierNextActionModel> outputs =
			new ArrayList<DossierNextActionModel>();

		if (jsonData != null && jsonData.length() > 0) {
			for (int i = 0; i < jsonData.length(); i++) {

				JSONObject jsonObject = jsonData.getJSONObject(i);
				DossierNextActionModel model = new DossierNextActionModel();

				model.setProcessActionId(
					Long.valueOf(
						jsonObject.getString(
							ProcessActionTerm.PROCESS_ACTION_ID)));
				model.setActionCode(
					jsonObject.getString(ProcessActionTerm.ACTION_CODE));
				model.setActionName(
					jsonObject.getString(ProcessActionTerm.ACTION_NAME));
				model.setPreStepCode(
					jsonObject.getString(ProcessActionTerm.PRESTEP_CODE));
				model.setPostStepCode(
					jsonObject.getString(ProcessActionTerm.POSTSTEP_CODE));
				model.setAutoEvent(
					jsonObject.getString(ProcessActionTerm.AUTO_EVENT));
				model.setPreCondition(
					jsonObject.getString(ProcessActionTerm.PRE_CONDITION));
				model.setEnable(
					Integer.valueOf(
						jsonObject.getString(ProcessActionTerm.ENABLE)));
				model.setAllowDelegacyUser(
					jsonObject.getString(ProcessActionTerm.ALLOW_ASSIGN_USER));

				outputs.add(model);
			}
		}

		return outputs;
	}

	// LamTV_Mapping detail Next Action
	@SuppressWarnings("unchecked")
	public static DossierDetailNextActionModel mappingToDetailNextActions(
		long groupId,
		JSONObject jsonData)
		throws PortalException {

		DossierDetailNextActionModel model = null;
		try {

			if (jsonData != null) {
				model = new DossierDetailNextActionModel();

				ProcessAction processAction =
					(ProcessAction) jsonData.get(ProcessActionTerm.PROCESS_ACTION);
				List<User> lstUser = (List<User>) jsonData.get(DossierActionTerm.LST_USER);
//				 _log.info("LAmTV_List user: "+lstUser);
				JSONArray createFiles = jsonData.getJSONArray(DossierActionTerm.CREATE_FILES);
				List<DossierFile> returnFiles =
					(List<DossierFile>) jsonData.get(DossierActionTerm.RETURN_FILES);

				DossierActionPaymentModel payment =
					new DossierActionPaymentModel();
				if (processAction != null) {
					model.setProcessActionId(
						processAction.getProcessActionId());
					model.setActionCode(processAction.getActionCode());
					model.setActionName(processAction.getActionName());
					model.setPreStepCode(processAction.getPreStepCode());
					model.setPostStepCode(processAction.getPostStepCode());
					model.setAutoEvent(processAction.getAutoEvent());
					model.setPreCondition(processAction.getPreCondition());
					model.setAllowAssignUser(
						processAction.getAllowAssignUser());
					model.seteSignature(processAction.getESignature());
					model.setSignatureType(processAction.getSignatureType());
					// model.setCheckInput(processAction.getCheckInput());
					model.setConfigNote(processAction.getConfigNote());
					ActionConfig act = ActionConfigLocalServiceUtil.getByCode(
						processAction.getGroupId(),
						processAction.getActionCode());
					if (act != null) {
						model.setUserNote(act.getUserNote());
						model.setExtraForm(act.getExtraForm());
					}
					else {
						model.setUserNote(0);
						model.setExtraForm(false);
					}
					// _log.info("Payment object: " + jsonData.toJSONString());
					// _log.info("Payment object: " +
					// jsonData.getJSONObject("payment").toJSONString());
					JSONObject paymentObject =
						jsonData.getJSONObject(PaymentFileTerm.PAYMENT);
					if (paymentObject != null) {
						if (paymentObject.has(PaymentFileTerm.PAYMENT_FEE)) {
							payment.setPaymentFee(
								paymentObject.getString(PaymentFileTerm.PAYMENT_FEE));
						}
						if (paymentObject.has(PaymentFileTerm.PAYMENT_NOTE)) {
							payment.setPaymentNote(
								paymentObject.getString(PaymentFileTerm.PAYMENT_NOTE));
						}
						if (paymentObject.has(PaymentFileTerm.PAYMENT_REQUEST)) {
							payment.setRequestPayment(
								paymentObject.getInt(PaymentFileTerm.PAYMENT_REQUEST));
						}
						if (paymentObject.has(PaymentFileTerm.ADVANCE_AMOUNT)) {
							payment.setAdvanceAmount(
								paymentObject.getLong(PaymentFileTerm.ADVANCE_AMOUNT));
						}
						if (paymentObject.has(PaymentFileTerm.FEE_AMOUNT)) {
							payment.setFeeAmount(
								paymentObject.getLong(PaymentFileTerm.FEE_AMOUNT));
						}
						if (paymentObject.has(PaymentFileTerm.SERVICE_AMOUNT)) {
							payment.setServiceAmount(
								paymentObject.getLong(PaymentFileTerm.SERVICE_AMOUNT));
						}
						if (paymentObject.has(PaymentFileTerm.SHIP_AMOUNT)) {
							payment.setShipAmount(
								paymentObject.getLong(PaymentFileTerm.SHIP_AMOUNT));
						}
						if (paymentObject.has(PaymentFileTerm.PAYMENT_AMOUNT)) {
							payment.setPaymentAmount(
								paymentObject.getLong(PaymentFileTerm.PAYMENT_AMOUNT));
						}
						if (paymentObject.has(PaymentFileTerm.EDITABLE)) {
							payment.setEditable(
								paymentObject.getInt(PaymentFileTerm.EDITABLE));
						}
					}

					model.setPayment(payment);

					// _log.info("Payment: " + payment);
					//
					// String strPaymentFee = processAction.getPaymentFee();
					// _log.info("String payment fee: " + strPaymentFee);
					// if (Validator.isNotNull(strPaymentFee)) {
					// JSONObject paymentFee =
					// JSONFactoryUtil.createJSONObject(strPaymentFee);
					// if (paymentFee != null) {
					// //
					// payment.setRequestPayment(paymentFee.getInt("requestPayment"));
					// payment.setAdvanceAmount(paymentFee.getLong("advanceAmount"));
					// payment.setFeeAmount(paymentFee.getLong("feeAmount"));
					// payment.setServiceAmount(paymentFee.getLong("serviceAmount"));
					// payment.setShipAmount(paymentFee.getLong("shipAmount"));
					// payment.setEditable(paymentFee.getBoolean("editable"));
					//
					// model.setPayment(payment);
					// }
					// }
				}
				else {
					model.setPayment(payment);
				}

				JSONObject receivingObj = jsonData.getJSONObject(ConstantUtils.RECEIVING);
				// _log.info("Receiving object: " +
				// receivingObj.toJSONString());
				if (receivingObj != null) {
					ReceivingModel receiving = new ReceivingModel();
					receiving.setDueDate(
						receivingObj.getLong(DossierTerm.DUE_DATE));
					receiving.setReceiveDate(
						receivingObj.getLong(DossierTerm.RECEIVE_DATE));
					receiving.setEditable(receivingObj.getBoolean(ConstantUtils.EDITABLE));

					model.setReceiving(receiving);
				}

				List<DossierActionNextActiontoUser> outputUsers =
					new ArrayList<DossierActionNextActiontoUser>();
				DossierActionNextActiontoUser modelUser = null;
				
				if (lstUser != null && lstUser.size() > 0) {
					boolean moderator = false;
					int assigned = 0;
					long[] jobPosIds = new long[lstUser.size()];
					int count = 0;
					long[] mUserIds = new long[lstUser.size()];
					for (User user : lstUser) {
						long userId = user.getUserId();
						mUserIds[count++] = userId;
					}
					List<Employee> lstEmps = EmployeeLocalServiceUtil.findByG_MUSERID(groupId, mUserIds);
					Map<Long, Employee> mapEmps = new HashMap<Long, Employee>();
					for (Employee e : lstEmps) {
						mapEmps.put(e.getMappingUserId(), e);
					}
					count = 0;
					
					for (User user : lstUser) {
						long userId = user.getUserId();
						Employee employee =
							mapEmps.get(userId);
						_log.debug("employee1: " + employee);
						if (employee != null &&
							employee.getWorkingStatus() == 1) {
							jobPosIds[count++] = employee.getMainJobPostId();
						}
					}
					
					List<JobPos> lstJps = JobPosLocalServiceUtil.findByF_jobPosIds(groupId, jobPosIds);					
					Map<Long, String> mapJps = new HashMap<Long, String>();
					for (JobPos jp : lstJps) {
						mapJps.put(jp.getJobPosId(), jp.getTitle());
					}

					for (User user : lstUser) {
						long userId = user.getUserId();
						Employee employee =
							mapEmps.get(userId);
						_log.debug("employee1: " + employee);
						if (employee != null &&
							employee.getWorkingStatus() == 1) {
							modelUser = new DossierActionNextActiontoUser();
							Map<String, Object> attr =
								user.getModelAttributes();

							moderator = false;
							assigned = 0;
							if (attr != null) {
								if (attr.containsKey(
									ProcessStepRoleTerm.MODERATOR)) {
									moderator = GetterUtil.getBoolean(
										attr.get(
											ProcessStepRoleTerm.MODERATOR));
								}
								if (attr.containsKey(
									ProcessStepRoleTerm.ASSIGNED)) {
									assigned = GetterUtil.getInteger(
										attr.get(ProcessStepRoleTerm.ASSIGNED));
								}
							}

							modelUser.setUserId(userId);

							if (employee != null) {
								modelUser.setUserName(employee.getFullName());
							}
							// else {
							// modelUser.setUserName(user.getFullName());
							// }

							modelUser.setModerator(moderator);
							modelUser.setAssigned(assigned);
							// Check JobPostTitle
							if (Validator.isNotNull(employee.getJobPosTitle())) {
								modelUser.setJobPosTitle(employee.getJobPosTitle());
							} else {
								if (Validator.isNotNull(user.getJobTitle())) {
									modelUser.setJobPosTitle(user.getJobTitle());
								} else {
									if (mapJps.containsKey(employee.getMainJobPostId())) {
										String jobPosTitle = mapJps.get(employee.getMainJobPostId());
										modelUser.setJobPosTitle(jobPosTitle);
									}
								}
							}

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
							}
							else {
								outputUsers.add(modelUser);
							}
						}
					}
										
//					for (User user : lstUser) {
//						long userId = user.getUserId();
//						Employee employee =
//							EmployeeLocalServiceUtil.fetchByFB_MUID(userId);
//						_log.debug("employee1: " + employee);
//						if (employee != null &&
//							employee.getWorkingStatus() == 1) {
//							modelUser = new DossierActionNextActiontoUser();
//							Map<String, Object> attr =
//								user.getModelAttributes();
//							jobPosIds[count++] = employee.getMainJobPostId();
//							
//							moderator = false;
//							assigned = 0;
//							if (attr != null) {
//								if (attr.containsKey(
//									ProcessStepRoleTerm.MODERATOR)) {
//									moderator = GetterUtil.getBoolean(
//										attr.get(
//											ProcessStepRoleTerm.MODERATOR));
//								}
//								if (attr.containsKey(
//									ProcessStepRoleTerm.ASSIGNED)) {
//									assigned = GetterUtil.getInteger(
//										attr.get(ProcessStepRoleTerm.ASSIGNED));
//								}
//							}
//
//							modelUser.setUserId(userId);
//
//							if (employee != null) {
//								modelUser.setUserName(employee.getFullName());
//							}
//							// else {
//							// modelUser.setUserName(user.getFullName());
//							// }
//
//							modelUser.setModerator(moderator);
//							modelUser.setAssigned(assigned);
//							boolean flag = true;
//							if (outputUsers != null && !outputUsers.isEmpty()) {
//								for (DossierActionNextActiontoUser doUserAct : outputUsers) {
//									if (userId == doUserAct.getUserId()) {
//										flag = false;
//										break;
//									}
//								}
//								if (flag) {
//									outputUsers.add(modelUser);
//								}
//							}
//							else {
//								outputUsers.add(modelUser);
//							}
//						}
//					}					
				}
				model.setToUsers(outputUsers);

				List<DossierActionNextActioncreateFiles> outputCreeateFiles =
					null;
				if (createFiles != null && createFiles.length() > 0) {
					outputCreeateFiles =
						new ArrayList<DossierActionNextActioncreateFiles>();
					for (int j = 0; j < createFiles.length(); j++) {
						JSONObject createFile = createFiles.getJSONObject(j);
						DossierActionNextActioncreateFiles dossierActionNextActioncreateFile =
							new DossierActionNextActioncreateFiles();
						dossierActionNextActioncreateFile.setDossierPartId(
							createFile.getLong(DossierPartTerm.DOSSIERPART_ID));
						dossierActionNextActioncreateFile.setEForm(
							createFile.getBoolean(DossierPartTerm.EFORM));
						dossierActionNextActioncreateFile.setFormData(
							createFile.getString(DossierPartTerm.FORM_DATA));
						dossierActionNextActioncreateFile.setFormScript(
							createFile.getString(DossierPartTerm.FORM_SCRIPT));
						dossierActionNextActioncreateFile.setMultiple(
							createFile.getBoolean(DossierPartTerm.MULTIPLE));
						dossierActionNextActioncreateFile.setPartName(
							createFile.getString(DossierPartTerm.PART_NAME));
						dossierActionNextActioncreateFile.setPartNo(
							createFile.getString(DossierPartTerm.PART_NO));
						dossierActionNextActioncreateFile.setPartType(
							createFile.getInt(DossierPartTerm.PART_TYPE));
						dossierActionNextActioncreateFile.setPartTip(
							createFile.getString(DossierPartTerm.PART_TIP));
						dossierActionNextActioncreateFile.setTemplateFileNo(
							createFile.getString(
								DossierFileTerm.FILE_TEMPLATE_NO));
						dossierActionNextActioncreateFile.setReferenceUid(
							createFile.getString(
								DossierPartTerm.REFERENCE_UID));
						dossierActionNextActioncreateFile.setCounter(
							createFile.getInt(DossierPartTerm.COUNTER));
						dossierActionNextActioncreateFile.setDossierFileId(
							createFile.getLong(
								DossierPartTerm.DOSSIER_FILE_ID));
						outputCreeateFiles.add(
							dossierActionNextActioncreateFile);
						dossierActionNextActioncreateFile.setDeliverableType(createFile.getString(DeliverableTerm.DELIVERABLE_TYPE));
					}
					model.getCreateFiles().addAll(outputCreeateFiles);
				}

				List<DossierActionNextActionReturnFiles> outputReturnFiles =
					null;
				if (returnFiles != null && returnFiles.size() > 0) {
					outputReturnFiles =
						new ArrayList<DossierActionNextActionReturnFiles>();
					for (DossierFile dossierFile : returnFiles) {
						DossierActionNextActionReturnFiles dActionReturnFile =
							new DossierActionNextActionReturnFiles();

						dActionReturnFile.setCreateDate(
							APIDateTimeUtils.convertDateToString(
								dossierFile.getCreateDate(),
								APIDateTimeUtils._TIMESTAMP));
						dActionReturnFile.setModifiedDate(
							APIDateTimeUtils.convertDateToString(
								dossierFile.getModifiedDate(),
								APIDateTimeUtils._TIMESTAMP));
						dActionReturnFile.setReferenceUid(
							dossierFile.getReferenceUid());
						dActionReturnFile.setDossierTemplateNo(
							dossierFile.getDossierTemplateNo());
						dActionReturnFile.setDossierPartNo(
							dossierFile.getDossierPartNo());
						dActionReturnFile.setFileTemplateNo(
							dossierFile.getFileTemplateNo());
						dActionReturnFile.setDisplayName(
							dossierFile.getDisplayName());
						dActionReturnFile.setDeliverableCode(
							dossierFile.getDeliverableCode());
						dActionReturnFile.setSignInfo(
							dossierFile.getSignInfo());
						dActionReturnFile.setSignCheck(
							dossierFile.getSignCheck());
						//
						long fileEntryId = dossierFile.getFileEntryId();
						if (fileEntryId > 0) {
							FileEntry file =
								DLAppServiceUtil.getFileEntry(fileEntryId);
							if (file != null) {
								dActionReturnFile.setFileType(
									file.getMimeType());
								dActionReturnFile.setFileSize(file.getSize());
								dActionReturnFile.setVersion(file.getVersion());
							}
						}
						outputReturnFiles.add(dActionReturnFile);

					}
					model.getReturnFiles().addAll(outputReturnFiles);
				}
			}
		}
		catch (Exception e) {
			_log.info(e);
		}

		return model;
	}

	public static List<DossierTemplatePartDataModel> getDossierPartCrossDossier(
		long groupId, ProcessAction proAction, Dossier dossier)
		throws PortalException {

		List<DossierTemplatePartDataModel> inputs =
			new ArrayList<DossierTemplatePartDataModel>();
		if (Validator.isNotNull(proAction.getCreateDossiers())) {

			String createDossiers = proAction.getCreateDossiers();
			String govAgencyCode = StringPool.BLANK;
//			String serviceCode = dossier.getServiceCode();
			String dossierTemplateNo = dossier.getDossierTemplateNo();
			if (createDossiers.contains(StringPool.POUND)) {
				String[] splitCDs = createDossiers.split(StringPool.POUND);
				if (splitCDs.length == 2) {
					govAgencyCode = splitCDs[0];

					if (splitCDs[1].contains(StringPool.AT)) {
						if (splitCDs[1].split(StringPool.AT).length != 2) {
							throw new PortalException(
								"Cross dossier config error");
						}
						else {
							dossierTemplateNo =
								splitCDs[1].split(StringPool.AT)[0];
//							serviceCode = splitCDs[1].split(StringPool.AT)[1];
						}
					}
					else {
						govAgencyCode = splitCDs[0];
						dossierTemplateNo = splitCDs[1];
					}
				}
			}
			else {
				if (createDossiers.contains(StringPool.AT)) {
					if (createDossiers.split(StringPool.AT).length != 2) {
						throw new PortalException("Cross dossier config error");
					}
					else {
						govAgencyCode = createDossiers.split(StringPool.AT)[0];
//						serviceCode = createDossiers.split(StringPool.AT)[1];
					}
				}
				else {
					govAgencyCode = createDossiers;
				}
			}

			ServiceConfig serviceConfig =
				ServiceConfigLocalServiceUtil.getBySICodeAndGAC(
					groupId, dossier.getServiceCode(), govAgencyCode);
			if (serviceConfig != null) {
				List<ProcessOption> lstOptions =
					ProcessOptionLocalServiceUtil.getByServiceProcessId(
						serviceConfig.getServiceConfigId());
				//

				ProcessOption foundOption = null;
				if (createDossiers.contains(StringPool.POUND)) {
					for (ProcessOption po : lstOptions) {
						DossierTemplate dt =
							DossierTemplateLocalServiceUtil.fetchDossierTemplate(
								po.getDossierTemplateId());
						if (dt.getTemplateNo().equals(dossierTemplateNo)) {
							foundOption = po;
							break;
						}
					}
				}
				else {
					if (lstOptions.size() > 0) {
						foundOption = lstOptions.get(0);
					}
				}
				if (foundOption != null) {
					DossierTemplate dossierTemplate =
						DossierTemplateLocalServiceUtil.fetchDossierTemplate(
							foundOption.getDossierTemplateId());

					List<DossierPart> dossierParts =
						DossierPartLocalServiceUtil.getByTemplateNo(
							dossierTemplate.getGroupId(),
							dossierTemplate.getTemplateNo());

					for (DossierPart dp : dossierParts) {
						DossierTemplatePartDataModel elm =
							new DossierTemplatePartDataModel();

						elm.setPartNo(dp.getPartNo());
						elm.setPartName(dp.getPartName());
						elm.setPartTip(dp.getPartTip());
						elm.setPartType(dp.getPartType());
						elm.setMultiple(Boolean.toString(dp.getMultiple()));
						elm.setRequired(Boolean.toString(dp.getRequired()));
						elm.setEsign(Boolean.toString(dp.getESign()));
						elm.setFileTemplateNo(dp.getFileTemplateNo());
						elm.setFileMark(dp.getFileMark());
						elm.setHasForm(Boolean.toString(dp.isEForm()));

						inputs.add(elm);
					}
				}
			}
		}

		return inputs;
	}

	public static List<org.opencps.api.dossieraction.model.DossierActionModel> mappingToDoListReadActionExecuted(
		List<Document> documents) {

		List<org.opencps.api.dossieraction.model.DossierActionModel> outputs =
			new ArrayList<org.opencps.api.dossieraction.model.DossierActionModel>();
		for (Document document : documents) {
			org.opencps.api.dossieraction.model.DossierActionModel model =
				new org.opencps.api.dossieraction.model.DossierActionModel();

			long dossierActionId =
				GetterUtil.getLong(document.get("entryClassPK"));
			long userId =
				GetterUtil.getLong(document.get(DossierActionTerm.USER_ID));
			long actionOverDue = GetterUtil.getLong(
				document.get(DossierActionTerm.ACTION_OVER_DUE));
			long actionNote = GetterUtil.getLong(
				document.get(DossierActionTerm.ACTION_OVER_DUE));

			model.setDossierActionId(dossierActionId);
			model.setUserId(userId);
			model.setCreateDate(document.get(DossierActionTerm.CREATE_DATE));
			model.setActionCode(document.get(DossierActionTerm.ACTION_CODE));
			model.setActionUser(document.get(DossierActionTerm.ACTION_USER));
			model.setActionName(document.get(DossierActionTerm.ACTION_NAME));
			model.setActionNote(actionNote);
			model.setActionOverdue(actionOverDue);

			long serviceProcessId = GetterUtil.getLong(
				document.get(DossierActionTerm.SERVICE_PROCESS_ID));
			ServiceProcess serviceProcess =
				ServiceProcessLocalServiceUtil.fetchServiceProcess(
					serviceProcessId);

			model.setDurationUnit(serviceProcess.getDurationUnit());
			model.setRollbackable(
				document.get(DossierActionTerm.ROLLBACK_ABLE));
			model.setStepCode(document.get(DossierActionTerm.STEP_CODE));
			model.setStepName(document.get(DossierActionTerm.STEP_NAME));
			model.setStepInstruction(
				document.get(DossierActionTerm.STEP_INSTRUCTION));
			model.setPayload(document.get(DossierActionTerm.PAYLOAD));
			model.setDueDate(document.get(DossierActionTerm.DUE_DATE));

			outputs.add(model);
		}
		return outputs;
	}

	public static ActionExecutedModel mappingToDoActionExecutedModel(
		DossierAction dossierAction) {

		if (dossierAction == null) {
			return null;
		}

		ActionExecutedModel model = new ActionExecutedModel();

		model.setDossierActionId((int) dossierAction.getDossierActionId());
		model.setUserId((int) dossierAction.getUserId());
		model.setCreateDate(
			APIDateTimeUtils.convertDateToString(dossierAction.getDueDate()));
		model.setActionCode(dossierAction.getActionCode());
		model.setActionUser(dossierAction.getActionUser());
		model.setActionName(dossierAction.getActionName());
		model.setActionNote(dossierAction.getActionNote());
		model.setActionOverdue(dossierAction.getActionOverdue());

		long serviceProcessId = dossierAction.getServiceProcessId();
		ServiceProcess serviceProcess =
			ServiceProcessLocalServiceUtil.fetchServiceProcess(
				serviceProcessId);

		model.setDurationUnit(serviceProcess.getDurationUnit());
		model.setRollbackable("" + dossierAction.getRollbackable());
		model.setStepCode(dossierAction.getStepCode());
		model.setStepName(dossierAction.getStepName());
		model.setStepInstruction(dossierAction.getStepInstruction());
		model.setPayload(dossierAction.getPayload());
		model.setDueDate(
			APIDateTimeUtils.convertDateToString(dossierAction.getDueDate()));

		return model;

	}

	public static List<ListContacts> mappingToDoListContacts(
		List<Dossier> dossiers) {

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

	public static List<DossierPayLoadModel> mappingToPayLoadNextActions(
		JSONArray jsonData) {

		List<DossierPayLoadModel> outputs =
			new ArrayList<DossierPayLoadModel>();
		if (jsonData != null && jsonData.length() > 0) {
			for (int i = 0; i < jsonData.length(); i++) {
				JSONObject jsonObject = jsonData.getJSONObject(i);
				DossierPayLoadModel model = new DossierPayLoadModel();

				model.setFieldLabel(
					jsonObject.getString(ProcessActionTerm.FIELD_LABEL));
				model.setFieldName(
					jsonObject.getString(ProcessActionTerm.FIELD_NAME));
				model.setFieldType(
					jsonObject.getString(ProcessActionTerm.FIELD_TYPE));
				model.setValue(jsonObject.getString(ProcessActionTerm.VALUE));
				if (jsonObject.has(ConstantUtils.REQUIRED)) {
					model.setRequired(jsonObject.getBoolean(ConstantUtils.REQUIRED));
				}
				outputs.add(model);
			}
		}

		return outputs;
	}
}
