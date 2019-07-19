package org.opencps.api.controller.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.api.controller.DossierActionManagement;
import org.opencps.api.controller.util.DossierActionUtils;
import org.opencps.api.controller.util.DossierUtils;
import org.opencps.api.dossier.model.ListContacts;
import org.opencps.api.dossieraction.model.DossierActionNextActiontoUser;
import org.opencps.api.dossieraction.model.DossierActionSearchModel;
import org.opencps.api.dossieraction.model.DossierDetailNextActionModel;
import org.opencps.api.dossieraction.model.DossierNextActionResultsModel;
import org.opencps.api.dossieraction.model.DossierResultPayLoadModel;
import org.opencps.api.processsequence.model.ActionModel;
import org.opencps.api.processsequence.model.DossierActionResult21Model;
import org.opencps.api.processsequence.model.ProcessSequenceModel;
import org.opencps.api.processsequence.model.StepModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.datamgt.utils.DateTimeUtils;
import org.opencps.dossiermgt.action.DeliverableActions;
import org.opencps.dossiermgt.action.DossierActions;
import org.opencps.dossiermgt.action.impl.DeliverableActionsImpl;
import org.opencps.dossiermgt.action.impl.DossierActionsImpl;
import org.opencps.dossiermgt.action.util.DossierOverDueUtils;
import org.opencps.dossiermgt.constants.DossierActionTerm;
import org.opencps.dossiermgt.constants.DossierActionUserTerm;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.constants.ProcessActionTerm;
import org.opencps.dossiermgt.constants.ProcessStepRoleTerm;
import org.opencps.dossiermgt.model.Deliverable;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.DossierActionUser;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.ProcessAction;
import org.opencps.dossiermgt.model.ProcessSequence;
import org.opencps.dossiermgt.model.ProcessStep;
import org.opencps.dossiermgt.model.ServiceProcess;
import org.opencps.dossiermgt.service.DossierActionLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierActionUserLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessActionLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessSequenceLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessStepLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceProcessLocalServiceUtil;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;

import backend.auth.api.exception.BusinessExceptionImpl;

public class DossierActionManagementImpl implements DossierActionManagement {

	Log _log = LogFactoryUtil.getLog(DossierActionManagementImpl.class);

	@Override
	public Response getListActions(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DossierActionSearchModel query, String id) {

		try {
			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			long dossierId = GetterUtil.getLong(id);
			_log.debug("groupId: "+groupId+ "| dossierId: "+dossierId);

//			String referenceUid = StringPool.BLANK;
//
//			if (dossierId == 0) {
//				referenceUid = id;
//			}

			if (query.getEnd() == 0) {
				query.setStart(-1);
				query.setEnd(-1);
			}

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(DossierTerm.DOSSIER_ID, String.valueOf(dossierId));
			params.put(DossierActionTerm.ACTION_CODE, query.getActionCode());
			params.put(DossierActionTerm.AUTO, query.getAuto());

			Sort[] sorts = new Sort[] { SortFactoryUtil.create(query.getSort() + "_sortable", Sort.STRING_TYPE,
					GetterUtil.getBoolean(query.getOrder())) };

			DossierActions actions = new DossierActionsImpl();
			DossierNextActionResultsModel result = new DossierNextActionResultsModel();

			JSONArray jsonData = actions.getNextActionList(user.getUserId(), company.getCompanyId(), groupId, params,
					sorts, query.getStart(), query.getEnd(), serviceContext);

			if (jsonData != null && jsonData.length() > 0) {
				result.setTotal(jsonData.length());
				result.getData().addAll(DossierActionUtils.mappingToNextActions(jsonData));
				Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
				DossierAction dossierAction = null;
				
				if (dossier != null) {
					long serviceProcessId = 0;
					String stepCode = StringPool.BLANK;
					long dossierActionId = dossier.getDossierActionId();
					if (dossierActionId > 0) {
						dossierAction = DossierActionLocalServiceUtil.fetchDossierAction(dossierActionId);
					}

					if (dossierAction != null) {
						serviceProcessId = dossierAction.getServiceProcessId();
						stepCode = dossierAction.getStepCode();
					}

					if (Validator.isNotNull(stepCode)  && serviceProcessId > 0) {
						ProcessStep processStep = ProcessStepLocalServiceUtil.fetchBySC_GID(stepCode, groupId, serviceProcessId);
						if (processStep != null) {
							result.setCheckInput(processStep.getCheckInput());
							result.setStepCode(processStep.getStepCode());
							result.setStepName(processStep.getStepName());
							
							Date now = new Date();
							long dateNowTimeStamp = now.getTime();
			
							Date stepDuedate = DossierOverDueUtils.getStepOverDue(groupId, dossierAction.getActionOverdue(), dossierAction.getDueDate());

							result.setStepDueDate(stepDuedate != null ? stepDuedate.getTime() : 0l);
							Long releaseDateTimeStamp = (dossier.getReleaseDate() != null ? dossier.getReleaseDate() .getTime(): 0l);
							
							Long dueDateTimeStamp = stepDuedate != null ? stepDuedate.getTime() : 0l;
							if (releaseDateTimeStamp != null && releaseDateTimeStamp > 0) {
								if (dueDateTimeStamp != null && dueDateTimeStamp > 0) {
									long subTimeStamp = releaseDateTimeStamp - dueDateTimeStamp;
									String strOverDue = DossierUtils.calculatorOverDue(dossier.getDurationCount(),
											dossier.getDurationUnit(), subTimeStamp, releaseDateTimeStamp,
											dueDateTimeStamp, groupId, true);
									if (Validator.isNotNull(strOverDue)) {
										if (subTimeStamp > 0) {
											result.setStepOverdue("Quá hạn " + strOverDue);
										} else {
											result.setStepOverdue("Còn " + strOverDue);
										}
									} else {
										result.setStepOverdue(StringPool.BLANK);
									}
								} else {
									result.setStepOverdue(StringPool.BLANK);
								}
							} else {
								if (dueDateTimeStamp != null && dueDateTimeStamp > 0) {
									long subTimeStamp = dateNowTimeStamp - dueDateTimeStamp;
									String strOverDue = DossierUtils.calculatorOverDue(dossier.getDurationCount(),
											dossier.getDurationUnit(), subTimeStamp, dateNowTimeStamp, dueDateTimeStamp,
											groupId, true);
									if (Validator.isNotNull(strOverDue)) {
										if (subTimeStamp > 0) {
											result.setStepOverdue("Quá hạn " + strOverDue);
										} else {
											result.setStepOverdue("Còn " + strOverDue);
										}
									} else {
										result.setStepOverdue(StringPool.BLANK);
									}
								} else {
									result.setStepOverdue(StringPool.BLANK);
								}
							}
						}
					}
	
					ProcessStep processStep = ProcessStepLocalServiceUtil.fetchBySC_GID(stepCode, groupId,
							serviceProcessId);
					List<User> lstUser = new ArrayList<>();

					if (processStep != null) {	
//						if (processAction != null && processAction.getAllowAssignUser() == ProcessActionTerm.NOT_ASSIGNED) {
//							if (Validator.isNotNull(processStep.getRoleAsStep())) {
//								String[] steps = StringUtil.split(processStep.getRoleAsStep());
//								for (String sc : steps) {
//									if (sc.startsWith("!")) {
//										int index = sc.indexOf("!");
//										String stepCodePunc = sc.substring(index + 1);
//										lstUser.addAll(DossierActionUtils.processRoleAsStepDonedListUser(dossier, stepCodePunc, serviceProcessId, processStep));
//									}
//									else {
//										lstUser.addAll(DossierActionUtils.processRoleAsStepListUser(dossier, sc, serviceProcessId, processStep));								
//									}
//								}							
//							}
//							else {
//								if (processStepRoleList != null && !processStepRoleList.isEmpty()) {
//									lstUser.addAll(DossierActionUtils.processRoleListUser(processStepRoleList, serviceProcessId));
//								}							
//							}
//							if (lstUser != null && !lstUser.isEmpty()) {
//							}
//						}
//						else if (processAction != null) {
//							List<DossierActionUser> assignedUsers = DossierActionUserLocalServiceUtil.getByDossierAndStepCode(dossierId, stepCode);
//							for (DossierActionUser dau : assignedUsers) {
//								if (dau.getAssigned() == DossierActionUserTerm.ASSIGNED_TH
//										&& dau.getModerator() == 1) {
//									User u = UserLocalServiceUtil.fetchUser(dau.getUserId());
//									lstUser.add(u);
//								}
//							}
//						}
						List<DossierActionUser> assignedUsers = DossierActionUserLocalServiceUtil.getByDossierAndStepCode(dossierId, stepCode);
						for (DossierActionUser dau : assignedUsers) {
							if (dau.getAssigned() == DossierActionUserTerm.ASSIGNED_TH
									&& dau.getModerator() == 1) {
								User u = UserLocalServiceUtil.fetchUser(dau.getUserId());
								if (u != null) {
									if (!u.isLockout() && u.isActive()) {
										lstUser.add(u);
									}
								}
							}
						}
					}
					
					List<DossierActionNextActiontoUser> outputUsers = new ArrayList<DossierActionNextActiontoUser>();
					DossierActionNextActiontoUser modelUser = null;
					if (lstUser != null && lstUser.size() > 0) {
						boolean moderator = false;
						int assigned = 0;
						for (User u: lstUser) {
							if (!u.isLockout() && u.isActive()) {
								modelUser = new DossierActionNextActiontoUser();
								Map<String, Object> attr = u.getModelAttributes();
								long userId = GetterUtil.getLong(u.getUserId());
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
								//TODO: Not update user
								Employee emp = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, userId);
								if (emp != null) {
									modelUser.setUserName(emp.getFullName() != null ? emp.getFullName().toUpperCase()
											: StringPool.BLANK);
								} else {
									modelUser.setUserName(
											u.getFullName() != null ? u.getFullName().toUpperCase() : StringPool.BLANK);
								}
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
					
					if (DossierTerm.DOSSIER_STATUS_NEW.equals(dossier.getDossierStatus())
							&& dossier.getUserId() == user.getUserId()) {
						boolean exists = false;
						for (DossierActionNextActiontoUser daau : outputUsers) {
							if (daau.getUserId() == user.getUserId()) {
								exists = true;
								break;
							}
						}
						if (!exists) {
							modelUser = new DossierActionNextActiontoUser();
							long userId = GetterUtil.getLong(user.getUserId());
	
							modelUser.setUserId(userId);
							modelUser.setUserName(user.getFullName() != null ? user.getFullName().toUpperCase() : StringPool.BLANK);
							modelUser.setModerator(true);
							modelUser.setAssigned(1);
							
							outputUsers.add(modelUser);
						}
					}
					result.setUsers(outputUsers);					
				}
			} else {
				result.setTotal(0);
			}

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getActionDetail(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DossierActionSearchModel query, String id, String actionId) {
		try {
			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			long dossierId = GetterUtil.getLong(id);
			_log.debug("groupId: "+groupId+ "| dossierId: "+dossierId+ "| actionId: "+actionId);

			if (query.getEnd() == 0) {
				query.setStart(-1);
				query.setEnd(-1);
			}

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(DossierTerm.DOSSIER_ID, String.valueOf(dossierId));
			params.put(ProcessActionTerm.PROCESS_ACTION_ID, actionId);
			params.put(DossierActionTerm.ACTION_CODE, query.getActionCode());
			params.put(DossierActionTerm.AUTO, query.getAuto());

			Sort[] sorts = new Sort[] { SortFactoryUtil.create(query.getSort() + "_sortable", Sort.STRING_TYPE,
					GetterUtil.getBoolean(query.getOrder())) };

			DossierActions actions = new DossierActionsImpl();

			JSONObject jsonData = actions.getDetailNextActions(user.getUserId(), company.getCompanyId(), groupId, params,
					sorts, query.getStart(), query.getEnd(), serviceContext);

			DossierDetailNextActionModel result = DossierActionUtils.mappingToDetailNextActions(jsonData);
			//Check if user is delegate user
			Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
			if (dossier != null) {
				ProcessAction pa = ProcessActionLocalServiceUtil.fetchProcessAction(GetterUtil.getLong(actionId));
				
				DossierActionUser dau = DossierActionUserLocalServiceUtil.getByD_DID_UID_SC(dossierId, dossier.getDossierActionId(), user.getUserId(), pa.getPreStepCode());
				if (dau != null && dau.getDelegacy() == 1 && (pa.getAllowAssignUser() > 2)) {
					result.setAllowAssignUser(2);
				}
			}
			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getActionPayload(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DossierActionSearchModel query, String id, String actionId) {
		try {
			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			long dossierId = GetterUtil.getLong(id);
			_log.debug("groupId: "+groupId+ "| dossierId: "+dossierId+ "| actionId: "+actionId);

			if (query.getEnd() == 0) {
				query.setStart(-1);
				query.setEnd(-1);
			}
			ProcessAction proAction = ProcessActionLocalServiceUtil.fetchProcessAction(GetterUtil.getInteger(actionId));
			String actionCode = (proAction != null) ? proAction.getActionCode() : actionId;
			
			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(DossierTerm.DOSSIER_ID, String.valueOf(dossierId));
			params.put(ProcessActionTerm.PROCESS_ACTION_ID, actionId);
			params.put(DossierActionTerm.ACTION_CODE, actionCode);
			params.put(DossierActionTerm.AUTO, query.getAuto());

			Sort[] sorts = new Sort[] { SortFactoryUtil.create(query.getSort() + "_sortable", Sort.STRING_TYPE,
					GetterUtil.getBoolean(query.getOrder())) };

			DossierActions actions = new DossierActionsImpl();

			JSONArray jsonData = actions.getPayloadNextActions(user.getUserId(), company.getCompanyId(), groupId, params,
					sorts, query.getStart(), query.getEnd(), serviceContext);

			DossierResultPayLoadModel result = new DossierResultPayLoadModel();
			if (jsonData != null && jsonData.length() > 0) {
				result.setTotal(jsonData.length());
				result.getData().addAll(DossierActionUtils.mappingToPayLoadNextActions(jsonData));
			} else {
				result.setTotal(0);
			}
			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getListActionsExecuted(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, DossierActionSearchModel query, String id) {

//		DossierActions actions = new DossierActionsImpl();
		DossierActionResult21Model result = new DossierActionResult21Model();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			long dossierId = GetterUtil.getLong(id);
			Dossier dossier = null;
			dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
			if (dossier == null) {
				dossier = DossierLocalServiceUtil.getByRef(groupId, id);
			}
			
			if (dossier != null) {
				String processNo = dossier.getProcessNo();
				ServiceProcess serviceProcess = ServiceProcessLocalServiceUtil.getByG_PNO(groupId, processNo);
				if (serviceProcess != null) {
					result.setProcessNo(processNo);
					result.setDurationCount(serviceProcess.getDurationCount());
					result.setDurationUnit(serviceProcess.getDurationUnit());
					
					List<ProcessSequenceModel> datas = new ArrayList<>();
					
					List<ProcessSequence> lstSequences = ProcessSequenceLocalServiceUtil.findByG_SN(groupId, serviceProcess.getProcessNo());
					for (ProcessSequence ps : lstSequences) {
						ProcessSequenceModel psModel = new ProcessSequenceModel();
						psModel.setSequenceNo(ps.getSequenceNo());
						psModel.setSequenceName(ps.getSequenceName());
						psModel.setDurationCount(ps.getDurationCount());
						
						List<StepModel> lstStepModels = new ArrayList<>();
						
						List<ProcessStep> lstSteps = ProcessStepLocalServiceUtil.findByG_SP_SNO(groupId, serviceProcess.getServiceProcessId(), ps.getSequenceNo());
						
						for (ProcessStep processStep : lstSteps) {
							StepModel sm = new StepModel();
							sm.setFromStepCode(processStep.getStepCode());
							sm.setFromStepName(processStep.getStepName());
							sm.setDurationCount(processStep.getDurationCount());
							sm.setGroupName(processStep.getGroupName());
							
							lstSteps.add(processStep);
							
							List<DossierActionUser> lstAUs = DossierActionUserLocalServiceUtil.getByDossierAndStepCode(dossier.getDossierId(), processStep.getStepCode());
							
							for (DossierActionUser dau : lstAUs) {
								ActionModel am = new ActionModel();
								DossierAction da = DossierActionLocalServiceUtil.fetchDossierAction(dau.getDossierActionId());
								
								am.setActionCode(da.getActionCode());
								am.setActionName(da.getActionName());
								am.setActionNote(da.getActionNote());
								am.setUserId(da.getUserId());
								am.setCreateDate(DateTimeUtils.convertDateToString(da.getCreateDate(), DateTimeUtils._TIMESTAMP));
								am.setActionUser(da.getActionUser());
								am.setActionOverdue(da.getActionOverdue());
								am.setPayload(da.getPayload());
								am.setStepCode(da.getStepCode());
								am.setStepName(da.getStepName());
								am.setState(0);
							}
							
							List<ActionModel> lstActions = new ArrayList<>();
							
							sm.getActions().addAll(lstActions);
						}
						
						psModel.getSteps().addAll(lstStepModels);
						
						datas.add(psModel);
					}
					result.getData().addAll(datas);
				}
				return Response.status(200).entity(result).build();				
			}
			else {
				return Response.status(403).entity(null).build();	
			}
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getListContacts(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id) {

		DossierActions actions = new DossierActionsImpl();
		List<ListContacts> listContacts = new ArrayList<ListContacts>();

		try {
			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			long dossierId = GetterUtil.getLong(id);
			String referenceUid = null;
			if (dossierId == 0) {
				referenceUid = id + "";
			}

			JSONObject jsonData = (JSONObject) actions.getContacts(groupId, dossierId, referenceUid);

			listContacts = DossierActionUtils.mappingToDoListContacts((List<Dossier>) jsonData.get("ListContacts"));

			return Response.status(200).entity(listContacts).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getVisited(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getRollback(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getByDeliverableState(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, Long id, String state) {
		BackendAuth auth = new BackendAuthImpl();

//		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			
			List<DossierFile> dossierFileList = DossierFileLocalServiceUtil.getDossierFilesByDossierId(id);
//			_log.info("dossier File: "+ dossierFileList.get);
			StringBuilder sb = new StringBuilder();
			String deliverableCode;
			if (dossierFileList != null && dossierFileList.size() > 0) {
				int length = dossierFileList.size();
//				_log.info("Size dossier File: "+ length);
				int ii = 0;
				for (int i = 0; i < length; i++) {
					DossierFile dossierFile = dossierFileList.get(i);
					deliverableCode = dossierFile.getDeliverableCode();
//					_log.info("deliverableCode: "+ deliverableCode);
					if (Validator.isNotNull(deliverableCode)) {
//						_log.info("deliverableCode Check: "+ deliverableCode);
						ii += 1;
						if (ii == 1) {
							sb.append(StringPool.APOSTROPHE);
							sb.append(deliverableCode);
							sb.append(StringPool.APOSTROPHE);
						} else {
							sb.append(StringPool.COMMA);
							sb.append(StringPool.APOSTROPHE);
							sb.append(deliverableCode);
							sb.append(StringPool.APOSTROPHE);
						}
					}
				}
//				_log.info("Str Dossier Id: "+ sb.toString());
			}

			DeliverableActions action = new DeliverableActionsImpl();
			//
			//
			List<Deliverable> deliverableList = action.getDeliverableByState(sb.toString(), Integer.valueOf(state));
//			_log.info("Str list deliverable: "+ deliverableList);
			JSONArray results = JSONFactoryUtil.createJSONArray();
			if (deliverableList != null && deliverableList.size() > 0) {
//				int lengthDeliver = deliverableList.size();
//				_log.info("Size list deliverable: "+ deliverableList.size());
				String formData;
				for (Deliverable deliverable : deliverableList) {
					JSONObject formDetail = JSONFactoryUtil.createJSONObject();
					formData = deliverable.getFormData();
//					_log.info("formData: "+ formData);
					try {
						JSONObject jsonData = JSONFactoryUtil.createJSONObject(formData);
						formDetail.put("so_chung_chi", jsonData.get("so_chung_chi"));
						formDetail.put("nguoi_ky_cc", jsonData.get("nguoi_ky_cc"));
						formDetail.put("ngay_ky_cc", jsonData.get("ngay_ky_cc"));
						formDetail.put("ten_doanh_nghiep", jsonData.get("ten_doanh_nghiep"));
						formDetail.put("ma_so_doanh_nghiep", jsonData.get("ma_so_doanh_nghiep"));
						formDetail.put("ma_ho_so", jsonData.get("ma_ho_so"));
						formDetail.put("so_ho_so", jsonData.get("so_ho_so"));
						formDetail.put("ngay_tiep_nhan", jsonData.get("ngay_tiep_nhan"));
						formDetail.put("ngay_gui", jsonData.get("ngay_gui"));
						formDetail.put("loai_san_pham", jsonData.get("loai_san_pham"));
						formDetail.put("nhan_hieu", jsonData.get("nhan_hieu"));
						formDetail.put("ma_kieu_loai", jsonData.get("ma_kieu_loai"));
						formDetail.put("ten_thuong_mai", jsonData.get("ten_thuong_mai"));

						String strReport = String.valueOf(jsonData.get("bien_ban"));
						try {
							JSONObject jsonReportData = JSONFactoryUtil.createJSONObject(strReport);
							formDetail.put("bien_ban@hinh_thuc_cap_giay_text", jsonReportData.get("hinh_thuc_cap_giay_text"));
							formDetail.put("bien_ban@so_bien_ban", jsonReportData.get("so_bien_ban"));
							formDetail.put("bien_ban@dang_kiem_vien_chinh", jsonReportData.get("dang_kiem_vien_chinh"));
							results.put(formDetail);
						} catch (Exception e) {
							_log.error(e);
						}
					} catch (Exception e) {
						_log.error(e);
					}
					
				}
			}

//			_log.info("Result: "+ results);
			return Response.status(200).entity(JSONFactoryUtil.looseSerialize(results)).build();
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}


}
