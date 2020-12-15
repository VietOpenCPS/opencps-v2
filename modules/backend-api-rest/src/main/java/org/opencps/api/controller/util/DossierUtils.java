package org.opencps.api.controller.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.swagger.models.auth.In;
import org.opencps.api.constants.ConstantUtils;
import org.opencps.api.dossier.model.*;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.opencps.datamgt.util.DateTimeUtils;
import org.opencps.datamgt.util.DueDateUtils;
import org.opencps.datamgt.util.HolidayUtils;
import org.opencps.dossiermgt.action.util.DossierContentGenerator;
import org.opencps.dossiermgt.action.util.DossierMgtUtils;
import org.opencps.dossiermgt.action.util.DossierOverDueUtils;
import org.opencps.dossiermgt.constants.DossierActionTerm;
import org.opencps.dossiermgt.input.model.DossierInputModel;
import org.opencps.dossiermgt.input.model.DossierMultipleInputModel;
import org.opencps.dossiermgt.input.model.DossierPublishModel;
import org.opencps.dossiermgt.constants.ConstantsTerm;
import org.opencps.dossiermgt.constants.DeliverableTerm;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.DossierActionUser;
import org.opencps.dossiermgt.model.DossierUser;
import org.opencps.dossiermgt.model.ProcessAction;
import org.opencps.dossiermgt.model.ProcessOption;
import org.opencps.dossiermgt.model.ProcessStep;
import org.opencps.dossiermgt.model.ServiceConfig;
import org.opencps.dossiermgt.model.ServiceProcess;
import org.opencps.dossiermgt.model.ServiceProcessRole;
import org.opencps.dossiermgt.service.DossierActionLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierActionUserLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierUserLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessActionLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessOptionLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessStepLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceConfigLocalServiceUtil;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.model.EmployeeJobPos;
import org.opencps.usermgt.model.JobPos;
import org.opencps.usermgt.service.EmployeeJobPosLocalServiceUtil;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;
import org.opencps.usermgt.service.JobPosLocalServiceUtil;

public class DossierUtils {

	private static final long VALUE_CONVERT_DATE_TIMESTAMP = 1000 * 60 * 60 * 24;
	private static final long VALUE_CONVERT_HOUR_TIMESTAMP = 1000 * 60 * 60;
	private static final long VALUE_HOUR_TO_DAY = 8;
	private static final String EXTEND_ONE_VALUE = ".0";
	private static final String EXTEND_TWO_VALUE = ".00";
	//Caculate dueDate by day
	private static final Boolean CALCULATE_DOSSIER_STATISTIC_DUEDATE_DAY_ENABLE = Validator.isNotNull(PropsUtil.get("opencps.statistic.dossier.dueDate.day.enable"))
			? Boolean.valueOf(PropsUtil.get("opencps.statistic.dossier.dueDate.day.enable")) : false;

	public static List<DossierDataModel> mappingForGetList(List<Document> docs, long  userId, Integer assigned, DossierSearchModel query) {
		List<DossierDataModel> ouputs = new ArrayList<DossierDataModel>();

		for (Document doc : docs) {
			//LamTV: Process Assigned dossier
			int originality = GetterUtil.getInteger(doc.get(DossierTerm.ORIGINALLITY));
			long dossierActionId = GetterUtil.getLong(doc.get(DossierTerm.DOSSIER_ACTION_ID));
			DossierActionUser dau = DossierActionUserLocalServiceUtil.getByDossierAndUser(dossierActionId, userId);
			User user = UserLocalServiceUtil.fetchUser(userId);
			boolean isAdministratorData = false;
//			int assignedCheck = 0;
			if (user != null) {
				List<Role> userRoles = user.getRoles();
				for (Role r : userRoles) {
					if (r.getName().startsWith(ConstantUtils.ROLE_ADMIN)) {
						isAdministratorData = true;
						break;
					}
				}
			}

//			if (isAdministratorData || originality == 9) {
//				assignedCheck = 1;
//			} else if (Validator.isNotNull(assigned)) {
//				if (dau != null && dau.getAssigned() != assigned) {
//					continue;
//				}
//				assignedCheck = assigned;
//			} else {
//				if (dau == null) {
//					continue;
//				}
//				assignedCheck = dau.getAssigned();
//			}

			//Process add dossier in result
			DossierDataModel model = new DossierDataModel();

			//model.setAssigned(assignedCheck);
			if (dau != null) {
				model.setAssigned(dau.getAssigned());
			} else {
				model.setAssigned(ConstantsTerm.NO_ASSINED);
			}
			
			if (isAdministratorData) {
				model.setAssigned(1);
			}

			model.setDossierIdCTN(doc.get(DossierTerm.DOSSIER_ID_CTN));
			model.setDossierId(GetterUtil.getInteger(doc.get(Field.ENTRY_CLASS_PK)));
			model.setDossierName(doc.get(DossierTerm.DOSSIER_NAME));
			model.setGroupId(GetterUtil.getInteger(doc.get(Field.GROUP_ID)));
			if (Validator.isNotNull(doc.get(DossierTerm.CREATE_DATE))) {
				Date createDate = APIDateTimeUtils.convertStringToDate(doc.get(DossierTerm.CREATE_DATE), APIDateTimeUtils._LUCENE_PATTERN);
				model.setCreateDate(APIDateTimeUtils.convertDateToString(createDate, APIDateTimeUtils._NORMAL_PARTTERN));
			} else {
				model.setCreateDate(doc.get(DossierTerm.CREATE_DATE));
			}
			if (Validator.isNotNull(doc.get(DossierTerm.MODIFIED_DATE))) {
				Date modifiedDate = APIDateTimeUtils.convertStringToDate(doc.get(DossierTerm.MODIFIED_DATE), APIDateTimeUtils._LUCENE_PATTERN);
				model.setModifiedDate(APIDateTimeUtils.convertDateToString(modifiedDate, APIDateTimeUtils._NORMAL_PARTTERN));
			} else {
			model.setModifiedDate(doc.get(DossierTerm.MODIFIED_DATE));
			}
			model.setReferenceUid(doc.get(DossierTerm.REFERENCE_UID));
			model.setCounter(GetterUtil.getInteger(doc.get(DossierTerm.COUNTER)));
			model.setServiceCode(doc.get(DossierTerm.SERVICE_CODE));
			model.setServiceName(doc.get(DossierTerm.SERVICE_NAME));
			model.setGovAgencyCode(doc.get(DossierTerm.GOV_AGENCY_CODE));
			model.setGovAgencyName(doc.get(DossierTerm.GOV_AGENCY_NAME));
			model.setApplicantName(doc.get(DossierTerm.APPLICANT_NAME) != null ? doc.get(DossierTerm.APPLICANT_NAME).toUpperCase().replace(";", "; ") : StringPool.BLANK);
			model.setApplicantNote(doc.get(DossierTerm.APPLICANT_NOTE));
			model.setApplicantIdType(doc.get(DossierTerm.APPLICANT_ID_TYPE));
			model.setApplicantIdNo(doc.get(DossierTerm.APPLICANT_ID_NO));
			model.setApplicantIdDate(doc.get(DossierTerm.APPLICANT_ID_DATE));
			model.setAddress(doc.get(DossierTerm.ADDRESS));
			model.setCityCode(doc.get(DossierTerm.CITY_CODE));
			model.setCityName(doc.get(DossierTerm.CITY_NAME));
			model.setDistrictCode(doc.get(DossierTerm.DISTRICT_CODE));
			model.setDistrictName(doc.get(DossierTerm.DISTRICT_NAME));
			model.setWardCode(doc.get(DossierTerm.WARD_CODE));
			model.setWardName(doc.get(DossierTerm.WARD_NAME));
			model.setContactName(doc.get(DossierTerm.CONTACT_NAME));
			model.setContactTelNo(doc.get(DossierTerm.CONTACT_TEL_NO));
			model.setContactEmail(doc.get(DossierTerm.CONTACT_EMAIL));
			model.setDossierNote(doc.get(DossierTerm.DOSSIER_NOTE));
			model.setSubmissionNote(doc.get(DossierTerm.SUBMISSION_NOTE));
			model.setBriefNote(doc.get(DossierTerm.BRIEF_NOTE));
			model.setDossierNo(doc.get(DossierTerm.DOSSIER_NO));
			model.setOriginality(originality);
//			model.setSubmitDate(doc.get(DossierTerm.SUBMIT_DATE));
//			_log.info("SUBMIT_DATE: "+doc.get(DossierTerm.SUBMIT_DATE));
			if (Validator.isNotNull(doc.get(DossierTerm.SUBMIT_DATE))) {
//				_log.info("SUBMIT_DATE_DATEEEEEE: "+doc.get(DossierTerm.SUBMIT_DATE));
				Date submitDate = APIDateTimeUtils.convertStringToDate(doc.get(DossierTerm.SUBMIT_DATE), APIDateTimeUtils._LUCENE_PATTERN);
//				_log.info("SUBMIT_DATE_DATEEEEEE: "+submitDate);
				model.setSubmitDate(APIDateTimeUtils.convertDateToString(submitDate, APIDateTimeUtils._NORMAL_PARTTERN));
//				_log.info("SUBMIT_DATE_CONVERT: "+APIDateTimeUtils.convertDateToString(submitDate, APIDateTimeUtils._NORMAL_PARTTERN));
			} else {
				model.setSubmitDate(doc.get(DossierTerm.SUBMIT_DATE));
			}
//			_log.info("RECEIVE_DATE: "+doc.get(DossierTerm.RECEIVE_DATE));
			if (Validator.isNotNull(doc.get(DossierTerm.RECEIVE_DATE))) {
				Date receiveDate = APIDateTimeUtils.convertStringToDate(doc.get(DossierTerm.RECEIVE_DATE), APIDateTimeUtils._LUCENE_PATTERN);
				model.setReceiveDate(APIDateTimeUtils.convertDateToString(receiveDate, APIDateTimeUtils._NORMAL_PARTTERN));
			} else {
				model.setReceiveDate(doc.get(DossierTerm.RECEIVE_DATE));
			}
//			_log.info("DUE_DATE: "+doc.get(DossierTerm.DUE_DATE));
//			if (Validator.isNotNull(doc.get(DossierTerm.DUE_DATE))) {
//				Date dueDate = APIDateTimeUtils.convertStringToDate(doc.get(DossierTerm.DUE_DATE), APIDateTimeUtils._LUCENE_PATTERN);
//				model.setDueDate(APIDateTimeUtils.convertDateToString(dueDate, APIDateTimeUtils._NORMAL_PARTTERN));
//			} else {
			model.setDueDate(doc.get(DossierTerm.DUE_DATE));
			model.setDueDateComing(doc.get(DossierTerm.DUE_DATE_COMING));
//			_log.info("DueDate: "+ doc.get(DossierTerm.DUE_DATE));
			model.setExtendDate(doc.get(DossierTerm.EXTEND_DATE));
//			_log.info("doc.get(DossierTerm.DUE_DATE): "+doc.get(DossierTerm.DUE_DATE));
//			}
			//Process OverDue
			String lockState = doc.get(DossierTerm.LOCK_STATE);
			String dossierStatus = doc.get(DossierTerm.DOSSIER_STATUS);
			Date now = new Date();
			long dateNowTimeStamp = now.getTime();
			Long dueDateTimeStamp = GetterUtil.getLong(doc.get(DossierTerm.DUE_DATE_TIMESTAMP));
			Long releaseDateTimeStamp = GetterUtil.getLong(doc.get(DossierTerm.RELEASE_DATE_TIMESTAMP));
//			Long extendDateTimeStamp = GetterUtil.getLong(doc.get(DossierTerm.EXTEND_DATE_TIMESTAMP));
//			Long finishDateTimeStamp = GetterUtil.getLong(doc.get(DossierTerm.FINISH_DATE_TIMESTAMP));
			int valueCompareRelease = GetterUtil.getInteger(doc.get(DossierTerm.VALUE_COMPARE_RELEASE));
			int valueCompareFinish = GetterUtil.getInteger(doc.get(DossierTerm.VALUE_COMPARE_FINISH));
			
			int durationUnit = (Validator.isNotNull(doc.get(DossierTerm.DURATION_UNIT))) ? Integer.valueOf(doc.get(DossierTerm.DURATION_UNIT)) : 1;
			double durationCount = (Validator.isNotNull(doc.get(DossierTerm.DURATION_COUNT))) ? Double.valueOf(doc.get(DossierTerm.DURATION_COUNT)) : 0;
			long groupId = GetterUtil.getLong(doc.get(Field.GROUP_ID));
			
			Date dueDateCalc = (dueDateTimeStamp != 0 ? new Date(dueDateTimeStamp) : null);
			
			//Check lockState
			if (checkWaiting(lockState, dossierStatus)){
				model.setDossierOverdue(MessageUtil.getMessage(ConstantUtils.DOSSIER_PAUSE_MESSAGE));
				model.setStepOverdue(MessageUtil.getMessage(ConstantUtils.DOSSIER_PAUSE_MESSAGE));
			} else if (checkReceiving(dossierStatus)){
				model.setDossierOverdue(StringPool.BLANK);
				model.setStepOverdue(StringPool.BLANK);
			} else if (!checkWaiting(lockState, dossierStatus) || !checkReceiving(dossierStatus)) {
				if (releaseDateTimeStamp != null && releaseDateTimeStamp > 0) {
//					if (processBeTime(releaseDateTimeStamp, dueDateTimeStamp, finishDateTimeStamp,
//							extendDateTimeStamp)) {
//						model.setDossierOverdue("Sớm hạn");
//					} 
//					if (dueDateTimeStamp != 0 && extendDateTimeStamp != 0 && 3 == valueCompareRelease) {
					if (dueDateTimeStamp != 0 && 3 == valueCompareRelease) {
						DueDateUtils dueDateUtil = new DueDateUtils(now, dueDateCalc, 1, groupId);
						model.setTimeOverdueText(dueDateUtil.getOverDueCalcToString());
						model.setDossierOverdue(MessageUtil.getMessage(ConstantUtils.DOSSIER_UNDUE_MESSAGE));
					}
					if (dueDateTimeStamp != 0 && 3 == valueCompareFinish) {
						DueDateUtils dueDateUtil = new DueDateUtils(now, dueDateCalc, 1, groupId);
						model.setTimeOverdueText(dueDateUtil.getOverDueCalcToString());
						model.setDossierFinishOverdue(MessageUtil.getMessage(ConstantUtils.DOSSIER_UNDUE_MESSAGE));
					}
//					if (processOnTime(releaseDateTimeStamp, dueDateTimeStamp, finishDateTimeStamp,
//							extendDateTimeStamp)){
//						if (dueDateTimeStamp!=0) {
//							model.setDossierOverdue("Đúng hạn");
//						} else {
//							model.setDossierOverdue(StringPool.BLANK);
//						}
//					}
					if (dueDateTimeStamp == 0 || 2 == valueCompareRelease) {
						model.setDossierOverdue(MessageUtil.getMessage(ConstantUtils.DOSSIER_ONTIME_MESSAGE));
					}
					if (dueDateTimeStamp == 0 || 2 == valueCompareFinish) {
						model.setDossierFinishOverdue(MessageUtil.getMessage(ConstantUtils.DOSSIER_ONTIME_MESSAGE));
					}
//					if (processOverTime(releaseDateTimeStamp, dueDateTimeStamp, finishDateTimeStamp,
//							extendDateTimeStamp)) {
//						model.setDossierOverdue("Quá hạn");
//					}
					if (1 == valueCompareRelease) {
						DueDateUtils dueDateUtil = new DueDateUtils(dueDateCalc, now, 1, groupId);
						model.setTimeOverdueText(dueDateUtil.getOverDueCalcToString());
						model.setDossierOverdue(MessageUtil.getMessage(ConstantUtils.DOSSIER_OVERDUE_MESSAGE));
					}
					if (1 == valueCompareFinish) {
						DueDateUtils dueDateUtil = new DueDateUtils(dueDateCalc, now, 1, groupId);
						model.setTimeOverdueText(dueDateUtil.getOverDueCalcToString());
						model.setDossierFinishOverdue(MessageUtil.getMessage(ConstantUtils.DOSSIER_OVERDUE_MESSAGE));
					}
				} else {
					if (dueDateTimeStamp != null && dueDateTimeStamp > 0) {
						// add by phuchn- caculate duedate by date
						if (CALCULATE_DOSSIER_STATISTIC_DUEDATE_DAY_ENABLE) {
							String dateNow = APIDateTimeUtils.convertDateToString(now, APIDateTimeUtils._NORMAL_PARTTERN);
							Date dateNowTem = APIDateTimeUtils.convertStringToDate(dateNow, APIDateTimeUtils._NORMAL_DATE);
							dateNowTimeStamp = dateNowTem.getTime();
														
							Date dueDateTem = APIDateTimeUtils.convertStringToDate(doc.get(DossierTerm.DUE_DATE), APIDateTimeUtils._NORMAL_DATE);
							dueDateTimeStamp = dueDateTem.getTime();
						}
						long subTimeStamp = dateNowTimeStamp - dueDateTimeStamp;
	//					_log.info("subTimeStamp: "+subTimeStamp);
						String strOverDue = calculatorOverDue(durationCount, durationUnit, subTimeStamp, dateNowTimeStamp,
								dueDateTimeStamp, groupId, false);
						
//						DueDateUtils overdue = new DueDateUtils(now, dueDateCalc, durationUnit, groupId);
//						_log.debug("NOW: " + now + ", DUE DATE: " + dueDateCalc + ", UNIT: " + durationUnit);
//						double overdueDate = overdue.getOverDueCalcToHours();
//						_log.debug("OVERDUE DATE HOURS: " + overdueDate);
//						int retval = Double.compare(durationUnit, 1.0);
//						if (retval < 0) {
//							overdueDate = overdue.getOverDueCalcToDate();
//						}
//						else {
//							overdueDate = overdue.getOverDueCalcToHours();
//						}
//						_log.debug("OVERDUE DATE: " + overdueDate);
//						String strOverDue = calculatorStepOverDue(durationCount, durationUnit, overdueDate, groupId);
						
						if (Validator.isNotNull(strOverDue)) {
							if (subTimeStamp > 0) {
	//							String strOverDue = calculatorOverDue(durationUnit, subTimeStamp, releaseDateTimeStamp,
	//									dueDateTimeStamp, groupId, true);
								String overdueText = String.format(MessageUtil.getMessage(ConstantUtils.DOSSIER_OVERDUE_WITHVARIABLE_MESSAGE), strOverDue);
								
								model.setDossierOverdue(overdueText);
							} else {
								String undueText = String.format(MessageUtil.getMessage(ConstantUtils.DOSSIER_UNDUE_WITHVARIABLE_MESSAGE), strOverDue);
								
								model.setDossierOverdue(undueText);
							}
						} else {
							model.setDossierOverdue(StringPool.BLANK);
						}
					} else {
						model.setDossierOverdue(StringPool.BLANK);
					}
				}
				//Process StepOverDue
//				double durationCount = (Validator.isNotNull(doc.get(DossierTerm.DURATION_COUNT))) ? Double.valueOf(doc.get(DossierTerm.DURATION_COUNT)) : 0.0;
//				if (Double.compare(durationCount, 0.0) != 0) {
//				_log.info("dossierActionId: "+dossierActionId);
				if (releaseDateTimeStamp != null && releaseDateTimeStamp > 0) {
					model.setStepOverdue(StringPool.BLANK);
				} else {
					DossierAction dAction = DossierActionLocalServiceUtil.fetchDossierAction(dossierActionId);
					if (dAction != null) {
						int state = dAction.getState();
//						if (model.getDossierId() == 9102) {
//							_log.info("ACTION OVERDUE: " + dAction.getActionOverdue());
//						}
						if (state > 0) {
							int actionOverDue = dAction.getActionOverdue();
							if (actionOverDue > 0) {
								String overdueText = String.format(MessageUtil.getMessage(ConstantUtils.PROCESSSEQUENCE_MESSAGE_OVERDUE), actionOverDue);
								model.setStepOverdue(overdueText);
							} else {
								model.setStepOverdue(StringPool.BLANK);
							}
						} else {
							Date dueDate = dAction.getDueDate();
//							_log.info("dueDate: "+dueDate);
							if (dueDate != null) {
								long dueDateActionTimeStamp = dueDate.getTime();
								long subTimeStamp = dateNowTimeStamp - dueDateActionTimeStamp;
//								_log.info("START STEP OVERDUE");
								String stepOverDue = calculatorOverDue(durationCount, durationUnit, subTimeStamp, dateNowTimeStamp,
										dueDateActionTimeStamp, groupId, true);
//								DueDateUtils overdue = new DueDateUtils(now, dueDate, durationUnit, groupId);
//								
//								double overdueDate = overdue.getOverDueCalcToHours();
//								int retval = Double.compare(durationUnit, 1.0);
//								if (retval < 0) {
//									overdueDate = overdue.getOverDueCalcToDate();
//								}
//								else {
//									overdueDate = overdue.getOverDueCalcToHours();
//								}
//								String stepOverDue = calculatorStepOverDue(durationCount, durationUnit, overdueDate, groupId);

								if (Validator.isNotNull(stepOverDue)) {
									if (subTimeStamp > 0) {
//										String strOverDue = calculatorOverDue(durationUnit, subTimeStamp, releaseDateTimeStamp,
//												dueDateTimeStamp, groupId, true);
										String overdueText = String.format(MessageUtil.getMessage(ConstantUtils.DOSSIER_OVERDUE_WITHVARIABLE_MESSAGE), stepOverDue);
										model.setStepOverdue(overdueText);
									} else {
										String undueText = String.format(MessageUtil.getMessage(ConstantUtils.DOSSIER_UNDUE_WITHVARIABLE_MESSAGE), stepOverDue);
										
										model.setStepOverdue(undueText);
									}
								} else {
									model.setStepOverdue(StringPool.BLANK);
								}
							} else {
								model.setStepOverdue(StringPool.BLANK);
							}
						}
					} else {
						model.setStepOverdue(StringPool.BLANK);
					}
				}
			}

			if (doc.hasField(DossierTerm.FINISH_DATE)) {
				model.setFinishDate(doc.get(DossierTerm.FINISH_DATE));				
			}
			if (doc.hasField(DossierTerm.RELEASE_DATE)) {
				model.setReleaseDate(doc.get(DossierTerm.RELEASE_DATE));				
			}
			if (doc.hasField(DossierTerm.CANCELLING_DATE)) {
				model.setCancellingDate(doc.get(DossierTerm.CANCELLING_DATE));
			}
			if (doc.hasField(DossierTerm.CORRECTING_DATE)) {
				model.setCorrectingDate(doc.get(DossierTerm.CORRECTING_DATE));
			}
			model.setDossierStatus(doc.get(DossierTerm.DOSSIER_STATUS));
			model.setDossierStatusText(doc.get(DossierTerm.DOSSIER_STATUS_TEXT));
			model.setDossierSubStatus(doc.get(DossierTerm.DOSSIER_SUB_STATUS));
			model.setDossierSubStatusText(doc.get(DossierTerm.DOSSIER_SUB_STATUS_TEXT));
//			model.setDossierOverdue(doc.get(DossierTerm.DOSSIER_OVER_DUE));
			model.setSubmitting(doc.get(DossierTerm.SUBMITTING));
//			model.setPermission(getPermission(GetterUtil.getLong(doc.get(Field.ENTRY_CLASS_PK))));
			String strPermission = GetterUtil.getString(doc.get(DossierTerm.MAPPING_PERMISSION));
			if (Validator.isNotNull(strPermission)) {
				String[] permissionArr = strPermission.split(StringPool.SPACE);
				if (permissionArr != null) {
					for (String permission: permissionArr) {
						if (Validator.isNotNull(permission) && permission.contains(String.valueOf(userId))) {
							model.setPermission(permission);
							break;
						} else {
							boolean isAdmin = false;
							List<Role> roles =
									RoleLocalServiceUtil.getUserRoles(userId);
							try {
								for (Role role : roles) {
									//LamTV_Fix sonarqube
									if (ConstantUtils.ROLE_ADMIN.equals(role.getName())) {

										isAdmin = true;
										break;

									}
								}

							}
							catch (Exception e) {
								_log.error(e);
							}			
							if (isAdmin) {
								model.setPermission(ConstantUtils.PERMISSION_READ);
							}
							else {
								model.setPermission(StringPool.BLANK);
							}
						}
					}
				} else {
					model.setPermission(StringPool.BLANK);
				}
			} else {
				model.setPermission(StringPool.BLANK);
			}
			if (isAdministratorData) {
				model.setPermission((Validator.isNotNull(model.getPermission()) ? model.getPermission() + StringPool.COMMA : StringPool.BLANK) + userId + ConstantUtils.UNDERLINE_PERMISSION_READ);
			}
			model.setLastActionDate(doc.get(DossierTerm.LAST_ACTION_DATE));
			model.setLastActionCode(doc.get(DossierTerm.LAST_ACTION_CODE));
			model.setLastActionName(doc.get(DossierTerm.LAST_ACTION_NAME));
			model.setLastActionUser(doc.get(DossierTerm.LAST_ACTION_USER) != null ? doc.get(DossierTerm.LAST_ACTION_USER).toUpperCase() : StringPool.BLANK);
			model.setLastActionNote(doc.get(DossierTerm.LAST_ACTION_NOTE));
			model.setCurrentActionUser(doc.get(DossierTerm.CURRENT_ACTION_USER));
			
			model.setLastActionUserId(GetterUtil.getLong(doc.get(DossierTerm.USER_DOSSIER_ACTION_ID)));
			model.setStepCode(doc.get(DossierTerm.STEP_CODE));
			model.setStepName(doc.get(DossierTerm.STEP_NAME));
			model.setStepDuedate(doc.get(DossierTerm.STEP_DUE_DATE));
//			model.setStepOverdue(StringPool.BLANK);
			model.setVisited(getVisisted(GetterUtil.getLong(doc.get(Field.ENTRY_CLASS_PK))));
			model.setPending(getPendding(GetterUtil.getLong(doc.get(Field.ENTRY_CLASS_PK))));
			model.setOnline(doc.get(DossierTerm.ONLINE));
			model.setHasPassword(doc.get(DossierTerm.SECRET));
			model.setDossierTemplateNo(doc.get(DossierTerm.DOSSIER_TEMPLATE_NO));
			model.setServerNo(doc.get(DossierTerm.SERVER_NO));
			
			model.setViaPostal(doc.get(DossierTerm.VIA_POSTAL));
			model.setPostalAddress(doc.get(DossierTerm.POSTAL_ADDRESS));
			model.setPostalCityCode(doc.get(DossierTerm.POSTAL_CITY_CODE));
			model.setPostalCityName(doc.get(DossierTerm.POSTAL_CITY_NAME));
			model.setPostalDistrictCode(doc.get(DossierTerm.POSTAL_DISTRICT_CODE));
			model.setPostalDistrictName(doc.get(DossierTerm.POSTAL_DISTRICT_NAME));
			model.setPostalTelNo(doc.get(DossierTerm.POSTAL_TEL_NO));
			model.setCertNo(doc.get(DeliverableTerm.SO_CHUNG_CHI));
			if (Validator.isNotNull(doc.get(DeliverableTerm.NGAY_KY_CC))) {
				Date certDate = APIDateTimeUtils.convertStringToDate(doc.get(DeliverableTerm.NGAY_KY_CC), APIDateTimeUtils._LUCENE_PATTERN);
				model.setCertDate(APIDateTimeUtils.convertDateToString(certDate, APIDateTimeUtils._NORMAL_DATE));
			} else {
				model.setCertDate(doc.get(DeliverableTerm.NGAY_KY_CC));
			}

			model.setEndorsementDate(doc.get(DossierTerm.ENDORSEMENT_DATE));
			model.setLockState(doc.get(DossierTerm.LOCK_STATE));
			model.setStatusReg(doc.get(DossierTerm.STATUS_REG));

			model.setDomainCode(doc.get(DossierTerm.DOMAIN_CODE));
			model.setDomainName(doc.get(DossierTerm.DOMAIN_NAME));

			if (Validator.isNotNull(doc.get(DossierTerm.DURATION_COUNT))) {
				model.setDurationCount(Double.valueOf(doc.get(DossierTerm.DURATION_COUNT)));				
			}
			else {
				model.setDurationCount(0d);
			}
			model.setDurationUnit(durationUnit);
			
			if (Validator.isNotNull(doc.get(DossierTerm.SAMPLE_COUNT))) {
				model.setSampleCount(Long.valueOf(doc.get(DossierTerm.SAMPLE_COUNT)));				
			}
			else {
				model.setSampleCount(0l);
			}

			model.setOrigin(doc.get(DossierTerm.ORIGIN));
			model.setOriginDossierId(GetterUtil.getInteger(doc.get(DossierTerm.ORIGIN_DOSSIER_ID)));
			model.setOriginDossierNo(doc.get(DossierTerm.ORIGIN_DOSSIER_NO));
			model.setDelegateName(doc.get(DossierTerm.DELEGATE_NAME));
			model.setDelegateIdNo(doc.get(DossierTerm.DELEGATE_ID_NO));
			model.setDelegateEmail(doc.get(DossierTerm.DELEGATE_EMAIL));
			model.setDelegateTelNo(doc.get(DossierTerm.DELEGATE_TELNO));
			model.setDelegateAddress(doc.get(DossierTerm.DELEGATE_ADDRESS));
			model.setDelegateCityCode(doc.get(DossierTerm.DELEGATE_CITYCODE));
			model.setDelegateCityName(doc.get(DossierTerm.DELEGATE_CITYNAME));
			model.setDelegateDistrictCode(doc.get(DossierTerm.DELEGATE_DISTRICTCODE));
			model.setDelegateDistrictName(doc.get(DossierTerm.DELEGATE_DISTRICTNAME));
			model.setDelegateWardCode(doc.get(DossierTerm.DELEGATE_WARDCODE));
			model.setDelegateWardName(doc.get(DossierTerm.DELEGATE_WARDNAME));
			model.setMetaData(doc.get(DossierTerm.META_DATA));
			model.setGroupDossierId(GetterUtil.getLong(doc.get(DossierTerm.GROUP_DOSSIER_ID)));
			if (Validator.isNotNull(doc.get(DossierTerm.GROUP_DOSSIER_ID_HS))) {
				model.setGroupDossierIdHs(GetterUtil.getLong(doc.get(DossierTerm.GROUP_DOSSIER_ID_HS)));
			}
			model.setDelegateType(GetterUtil.getInteger(doc.get(DossierTerm.DELEGATE_TYPE)));
			model.setDocumentNo(GetterUtil.getString(doc.get(DossierTerm.DOCUMENT_NO)));
			if (Validator.isNotNull(doc.get(DossierTerm.DOCUMENT_DATE))) {
				Date documentDate = APIDateTimeUtils.convertStringToDate(doc.get(DossierTerm.DOCUMENT_DATE), APIDateTimeUtils._LUCENE_PATTERN);
//				_log.info("SUBMIT_DATE_DATEEEEEE: "+submitDate);
				model.setDocumentDate(APIDateTimeUtils.convertDateToString(documentDate, APIDateTimeUtils._NORMAL_PARTTERN));
			}
			if (DossierTerm.PAUSE_OVERDUE_LOCK_STATE.equals(lockState)) {
				model.setLock(MessageUtil.getMessage(ConstantUtils.DOSSIER_LOCKOVER3_MESSAGE));
			}
			else {
				model.setLock(MessageUtil.getMessage(ConstantUtils.DOSSIER_LOCKUNDER3_MESSAGE));
			}
			
			model.setSystemId(GetterUtil.getInteger(doc.get(DossierTerm.SYSTEM_ID)));
			model.setDossierCounter(doc.get(DossierTerm.DOSSIER_COUNTER));
			if (Validator.isNotNull(doc.get(DossierTerm.SERVICE_LEVEL))) {
				model.setServiceLevel(Integer.parseInt(doc.get(DossierTerm.SERVICE_LEVEL)));
			}
			if (Validator.isNotNull(doc.get(DossierTerm.VNPOSTAL_STATUS))) {
				model.setVnpostalStatus(Integer.parseInt(doc.get(DossierTerm.VNPOSTAL_STATUS)));
				model.setVnpostalProfile(doc.get(DossierTerm.VNPOSTAL_PROFILE));
			}
			if (Validator.isNotNull(doc.get(DossierTerm.FROM_VIA_POSTAL))) {
				model.setFromViaPostal(Integer.parseInt(doc.get(DossierTerm.FROM_VIA_POSTAL)));
			} else {
				model.setFromViaPostal(0);
			}
//			Integer userIdDossier = Integer.parseInt(doc.get(DossierTerm.USER_ID));
//			if(Validator.isNotNull(query.getDonvigui())){
//				if(query.getDonvigui().equals(DossierTerm.SCOPE_)){
//					Employee employee = getEmployeeByG_User(model.getGroupId(), userIdDossier);
//					if(Validator.isNotNull(employee)){
//						model.setDonvigui(employee.getScope());
//					}
//				}else{
//						model.setDonvigui(doc.get(DossierTerm.DON_VI_GUI));
//				}
//			}
//			if(Validator.isNotNull(query.getDonvinhan())){
//				if(query.getDonvinhan().equals(DossierTerm.SCOPE_)){
//					Employee employee = getEmployeeByG_User(model.getGroupId(), userIdDossier);
//					if(Validator.isNotNull(employee)){
//						model.setDonvinhan(employee.getScope());
//					}
//				}else{
//					model.setDonvinhan(doc.get(DossierTerm.DON_VI_NHAN));
//				}
//			}
			model.setDonvigui(doc.get(DossierTerm.DON_VI_GUI));
			model.setDonvinhan(doc.get(DossierTerm.DON_VI_NHAN));
			model.setMetaData(doc.get(DossierTerm.META_DATA));
			model.setProcessNo(doc.get(DossierTerm.PROCESS_NO));
			model.setPostalCodeSend(doc.get(DossierTerm.POSTAL_CODE_SEND));
			model.setPostalCodeReceived(doc.get(DossierTerm.POSTAL_CODE_RECEIVED));
			model.setVotingCode1("");
			model.setVotingCode2("");
			model.setVotingCode3("");
			model.setVotingName1("");
			model.setVotingName2("");
			model.setVotingName3("");
			model.setResultVotingCode1(0);
			model.setResultVotingCode2(0);
			model.setResultVotingCode3(0);
			ouputs.add(model);
		}

		return ouputs;
	}
	private static Employee getEmployeeByG_User(Integer groupId, Integer userId){
		try {
			Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, userId);
			return employee;
		}catch (Exception e){
			_log.info("EXCEPTION" + e.getMessage());
			return null;
		}
	}

	public static String calculatorStepOverDue(double durationCount, int durationUnit, double overdue, long groupId) {
		String strOverDue;
		double overDue;
		int retval = Double.compare(durationUnit, 1.0);
		if (retval < 0) {
			strOverDue = " ngày";
			double dueCountReal = overdue;
			double subDueCount = (double) Math.round(dueCountReal * 100) / 100;
			overDue = (double) Math.ceil(subDueCount * 4) / 4;
			_log.debug("overdue: " + overdue);
			_log.debug("overDue: "+overDue);
			//TODO: Process a.0 = a
			boolean flagCeil = false;
			String strOverDueConvert = String.valueOf(overDue);
			if (Validator.isNotNull(strOverDueConvert)) {
				if (strOverDueConvert.contains(EXTEND_ONE_VALUE) || strOverDueConvert.contains(EXTEND_TWO_VALUE)) {
					flagCeil = true;
				}
			}
			//Check overDue
			if (Double.compare(overDue, 0.0) == 0) {
				return StringPool.BLANK;
			}
			if (!flagCeil) {
				//_log.info("flagCeil: "+flagCeil);
				//_log.info("overDue: "+overDue);
				//_log.info("durationCount: "+durationCount);
				if (Double.compare(durationCount, 0.0) > 0 && Double.compare(overDue, durationCount) > 0) {
					return (int)durationCount + strOverDue;
				} else {
					return overDue + strOverDue;
				}
			}
		} else {
			strOverDue = " giờ";
			double dueCountReal = overdue;
			overDue = (double) Math.round(dueCountReal);
			//
			if (Double.compare(overDue, 0.0) == 0) {
				return StringPool.BLANK;
			}
		}

		_log.debug("overDue: "+overDue);
		_log.debug("strOverDue: "+strOverDue);
		if (Double.compare(durationCount, 0.0) > 0 && Double.compare(overDue, durationCount) > 0) {
			return Math.abs((int)durationCount) + strOverDue;
		} else {
			return Math.abs((int)overDue) + strOverDue;
		}		
	}
	public static String calculatorOverDue(double durationCount, int durationUnit, long subTimeStamp, long releaseDateTimeStamp,
			long dueDateTimeStamp, long groupId, boolean flagStepDue) {

		//Process count day off work
		long dueCountLong = (long) subTimeStamp / VALUE_CONVERT_DATE_TIMESTAMP;
		int dueCountInt = (int) dueCountLong;
		double countDayHoliday = 0;
		if (dueCountInt > 0) {
//			_log.info("START 1");
			countDayHoliday = HolidayUtils.getCountDateByHoliday(dueDateTimeStamp, releaseDateTimeStamp,
					dueCountInt, groupId);
		} else if (dueCountInt < 0) {
//			_log.info("START 2");
			countDayHoliday = HolidayUtils.getCountDateByHoliday(releaseDateTimeStamp, dueDateTimeStamp,
					Math.abs(dueCountInt), groupId);
		}
//		_log.info("countDayHoliday: "+countDayHoliday);

		double countWork = 0;
		if (flagStepDue) {
			//Process balance
			double balance = (double) (subTimeStamp % VALUE_CONVERT_DATE_TIMESTAMP) / VALUE_CONVERT_HOUR_TIMESTAMP;
			if (balance > 1) {
				int countHours = HolidayUtils.getCountHoursByHoliday(dueDateTimeStamp, releaseDateTimeStamp, groupId);
//				_log.info("countHours: "+countHours);
				countWork += (double) countHours / VALUE_HOUR_TO_DAY;
			} else if (balance < -1){
				int countHours = HolidayUtils.getCountHoursByHoliday(releaseDateTimeStamp, dueDateTimeStamp, groupId);
//				_log.info("countHours: "+countHours);
				countWork += (double) countHours / VALUE_HOUR_TO_DAY;
			}
		}
		
//		_log.info("countDayHoliday: "+countDayHoliday);

		if (subTimeStamp < 0) {
			subTimeStamp = Math.abs(subTimeStamp);
		}

		String strOverDue;
		double dueCount;
		double overDue;
		int retval = Double.compare(durationUnit, 1.0);
		if (retval < 0) {
			strOverDue = " ngày";
			if (flagStepDue) {
				dueCount = (int) (subTimeStamp / VALUE_CONVERT_DATE_TIMESTAMP);
			} else {
				dueCount = (double) subTimeStamp / VALUE_CONVERT_DATE_TIMESTAMP;
			}
//			_log.debug("dueCount: "+dueCount);
//			_log.debug("countDayHoliday: "+countDayHoliday);
			double dueCountReal = dueCount - countDayHoliday + countWork;
			double subDueCount = (double) Math.round(dueCountReal * 100) / 100;
			overDue = (double) Math.ceil(subDueCount * 4) / 4;
//			_log.info("overDue: "+overDue);
			//TODO: Process a.0 = a
			boolean flagCeil = false;
			String strOverDueConvert = String.valueOf(overDue);
			if (Validator.isNotNull(strOverDueConvert)) {
				if (strOverDueConvert.contains(EXTEND_ONE_VALUE) || strOverDueConvert.contains(EXTEND_TWO_VALUE)) {
					flagCeil = true;
				}
			}
			//Check overDue
			if (Double.compare(overDue, 0.0) == 0) {
				return StringPool.BLANK;
			}
			if (!flagCeil) {
				//_log.info("flagCeil: "+flagCeil);
				//_log.info("overDue: "+overDue);
				//_log.info("durationCount: "+durationCount);
				// tính thời gian giải quyết còn lại của hồ sơ (overdue = true)
				if (Double.compare(durationCount, 0.0) > 0 && Double.compare(overDue, 0.0) > 0) {
					//return (int)durationCount + strOverDue;
					return overDue + strOverDue;
				}
//				else {
//					return overDue + strOverDue;
//				}
			}
		} else {
			strOverDue = " giờ";
			if (flagStepDue) {
				dueCount = (int) (subTimeStamp / VALUE_CONVERT_HOUR_TIMESTAMP);
			} else {
				dueCount = (double) subTimeStamp / VALUE_CONVERT_HOUR_TIMESTAMP;
			}
			double dueCountReal = dueCount - countDayHoliday * 8 + countWork * 8;
			overDue = (double) Math.round(dueCountReal);
			//
			if (Double.compare(overDue, 0.0) == 0) {
				return StringPool.BLANK;
			}
		}

//		_log.info("overDue: "+overDue);
//		_log.info("strOverDue: "+strOverDue);
		// tính thời gian giải quyết còn lại của hồ sơ
		if (Double.compare(durationCount, 0.0) > 0 && Double.compare(overDue, 0.0) > 0) {
//			return Math.abs((int)durationCount) + strOverDue;
			return overDue + strOverDue;
		} else {
			return durationCount + strOverDue;
		}
	}

	//TODO: Process get list Paging
	public static List<DossierDataModel> mappingForGetListPaging(List<Document> docs, int start, int end, long userId) {
		List<DossierDataModel> ouputs = new ArrayList<DossierDataModel>();
		int lengthDossier = docs.size();
		int endPage = 0;
		if (lengthDossier < end) {
			endPage = lengthDossier;
		} else {
			endPage = end;
		}
		for (int i = start; i < endPage; i++) {
			Document doc = docs.get(i);
//			_log.info("i: "+i);
			DossierDataModel model = new DossierDataModel();
			
			model.setDossierIdCTN(doc.get(DossierTerm.DOSSIER_ID_CTN));
			model.setDossierId(GetterUtil.getInteger(doc.get(Field.ENTRY_CLASS_PK)));
			model.setGroupId(GetterUtil.getInteger(doc.get(Field.GROUP_ID)));
			if (Validator.isNotNull(doc.get(DossierTerm.CREATE_DATE))) {
				Date createDate = APIDateTimeUtils.convertStringToDate(doc.get(DossierTerm.CREATE_DATE), APIDateTimeUtils._LUCENE_PATTERN);
				model.setSubmitDate(APIDateTimeUtils.convertDateToString(createDate, APIDateTimeUtils._NORMAL_PARTTERN));
			} else {
				model.setSubmitDate(doc.get(DossierTerm.CREATE_DATE));
			}
			if (Validator.isNotNull(doc.get(DossierTerm.MODIFIED_DATE))) {
				Date modifiedDate = APIDateTimeUtils.convertStringToDate(doc.get(DossierTerm.MODIFIED_DATE), APIDateTimeUtils._LUCENE_PATTERN);
				model.setSubmitDate(APIDateTimeUtils.convertDateToString(modifiedDate, APIDateTimeUtils._NORMAL_PARTTERN));
			} else {
				model.setSubmitDate(doc.get(DossierTerm.MODIFIED_DATE));
			}
			model.setReferenceUid(doc.get(DossierTerm.REFERENCE_UID));
			model.setCounter(GetterUtil.getInteger(doc.get(DossierTerm.COUNTER)));
			model.setServiceCode(doc.get(DossierTerm.SERVICE_CODE));
			model.setServiceName(doc.get(DossierTerm.SERVICE_NAME));
			model.setGovAgencyCode(doc.get(DossierTerm.GOV_AGENCY_CODE));
			model.setGovAgencyName(doc.get(DossierTerm.GOV_AGENCY_NAME));
			model.setApplicantName(doc.get(DossierTerm.APPLICANT_NAME));
			model.setApplicantNote(doc.get(DossierTerm.APPLICANT_NOTE));
			model.setApplicantIdType(doc.get(DossierTerm.APPLICANT_ID_TYPE));
			model.setApplicantIdNo(doc.get(DossierTerm.APPLICANT_ID_NO));
			model.setApplicantIdDate(doc.get(DossierTerm.APPLICANT_ID_DATE));
			model.setAddress(doc.get(DossierTerm.ADDRESS));
			model.setCityCode(doc.get(DossierTerm.CITY_CODE));
			model.setCityName(doc.get(DossierTerm.CITY_NAME));
			model.setDistrictCode(doc.get(DossierTerm.DISTRICT_CODE));
			model.setDistrictName(doc.get(DossierTerm.DISTRICT_NAME));
			model.setWardCode(doc.get(DossierTerm.WARD_CODE));
			model.setWardName(doc.get(DossierTerm.WARD_NAME));
			model.setContactName(doc.get(DossierTerm.CONTACT_NAME));
			model.setContactTelNo(doc.get(DossierTerm.CONTACT_TEL_NO));
			model.setContactEmail(doc.get(DossierTerm.CONTACT_EMAIL));
			model.setDossierNote(doc.get(DossierTerm.DOSSIER_NOTE));
			model.setSubmissionNote(doc.get(DossierTerm.SUBMISSION_NOTE));
			model.setBriefNote(doc.get(DossierTerm.BRIEF_NOTE));
			model.setDossierNo(doc.get(DossierTerm.DOSSIER_NO));
			model.setBriefNote(doc.get(DossierTerm.BRIEF_NOTE));
//			model.setSubmitDate(doc.get(DossierTerm.SUBMIT_DATE));
//			_log.info("SUBMIT_DATE: "+doc.get(DossierTerm.SUBMIT_DATE));
			if (Validator.isNotNull(doc.get(DossierTerm.SUBMIT_DATE))) {
//				_log.info("SUBMIT_DATE_DATEEEEEE: "+doc.get(DossierTerm.SUBMIT_DATE));
				Date submitDate = APIDateTimeUtils.convertStringToDate(doc.get(DossierTerm.SUBMIT_DATE), APIDateTimeUtils._LUCENE_PATTERN);
//				_log.info("SUBMIT_DATE_DATEEEEEE: "+submitDate);
				model.setSubmitDate(APIDateTimeUtils.convertDateToString(submitDate, APIDateTimeUtils._NORMAL_PARTTERN));
//				_log.info("SUBMIT_DATE_CONVERT: "+APIDateTimeUtils.convertDateToString(submitDate, APIDateTimeUtils._NORMAL_PARTTERN));
			} else {
			model.setSubmitDate(doc.get(DossierTerm.SUBMIT_DATE));
			}
//			_log.info("RECEIVE_DATE: "+doc.get(DossierTerm.RECEIVE_DATE));
			if (Validator.isNotNull(doc.get(DossierTerm.RECEIVE_DATE))) {
				Date receiveDate = APIDateTimeUtils.convertStringToDate(doc.get(DossierTerm.RECEIVE_DATE), APIDateTimeUtils._LUCENE_PATTERN);
				model.setReceiveDate(APIDateTimeUtils.convertDateToString(receiveDate, APIDateTimeUtils._NORMAL_PARTTERN));				
			} else {
			model.setReceiveDate(doc.get(DossierTerm.RECEIVE_DATE));
			}
//			if (Validator.isNotNull(doc.get(DossierTerm.DUE_DATE))) {
//				Date dueDate = APIDateTimeUtils.convertStringToDate(doc.get(DossierTerm.DUE_DATE), APIDateTimeUtils._LUCENE_PATTERN);
//				model.setReceiveDate(APIDateTimeUtils.convertDateToString(dueDate, APIDateTimeUtils._NORMAL_PARTTERN));
//			} else {
			model.setDueDate(doc.get(DossierTerm.DUE_DATE));
//			}
			if (Validator.isNotNull(doc.get(DossierTerm.FINISH_DATE))) {
				Date finishDate = APIDateTimeUtils.convertStringToDate(doc.get(DossierTerm.FINISH_DATE), APIDateTimeUtils._LUCENE_PATTERN);
				model.setReceiveDate(APIDateTimeUtils.convertDateToString(finishDate, APIDateTimeUtils._NORMAL_PARTTERN));
			} else {
				model.setReceiveDate(doc.get(DossierTerm.FINISH_DATE));
			}
			model.setCancellingDate(doc.get(DossierTerm.CANCELLING_DATE));
			model.setCorrectingDate(doc.get(DossierTerm.CORRECTING_DATE));
			model.setDossierStatus(doc.get(DossierTerm.DOSSIER_STATUS));
			model.setDossierStatusText(doc.get(DossierTerm.DOSSIER_STATUS_TEXT));
			model.setDossierSubStatus(doc.get(DossierTerm.DOSSIER_SUB_STATUS));
			model.setDossierSubStatusText(doc.get(DossierTerm.DOSSIER_SUB_STATUS_TEXT));
//			model.setDossierOverdue(doc.get(DossierTerm.DOSSIER_OVER_DUE));
			model.setSubmitting(doc.get(DossierTerm.SUBMITTING));
			model.setPermission(getPermission(GetterUtil.getLong(doc.get(Field.ENTRY_CLASS_PK)), userId));
			model.setLastActionDate(doc.get(DossierTerm.LAST_ACTION_DATE));
			model.setLastActionCode(doc.get(DossierTerm.LAST_ACTION_CODE));
			model.setLastActionName(doc.get(DossierTerm.LAST_ACTION_NAME));
			model.setLastActionUser(doc.get(DossierTerm.LAST_ACTION_USER));
			model.setLastActionNote(doc.get(DossierTerm.LAST_ACTION_NOTE));
			model.setStepCode(doc.get(DossierTerm.STEP_CODE));
			model.setStepName(doc.get(DossierTerm.STEP_NAME));
			model.setStepDuedate(doc.get(DossierTerm.STEP_DUE_DATE));
			//Process OverDue
//			Date now = new Date();
//			long dateNowTimeStamp = now.getTime();
//			Long dueDateTimeStamp = Long.valueOf(doc.get(DossierTerm.DUE_DATE_TIMESTAMP));
			int durationUnit = (Validator.isNotNull(doc.get(DossierTerm.DURATION_UNIT))) ? Integer.valueOf(doc.get(DossierTerm.DURATION_UNIT)) : 1;
//			double durationUnit = Double.valueOf(doc.get(DossierTerm.DURATION_UNIT));
//			if (dueDateTimeStamp != null && dueDateTimeStamp > 0) {
//				long subTimeStamp = dateNowTimeStamp - dueDateTimeStamp;
//				if (subTimeStamp > 0) {
//					String strOverDue = calculatorOverDue(durationUnit, subTimeStamp);
//					model.setDossierOverdue("Quá hạn "+strOverDue);
//				} else {
//					String strOverDue = calculatorOverDue(durationUnit, subTimeStamp);
//					model.setDossierOverdue("Còn "+strOverDue);
//				}
//			} else {
//				model.setDossierOverdue(StringPool.BLANK);
//			}
			model.setStepOverdue(StringPool.BLANK);
			model.setVisited(getVisisted(GetterUtil.getLong(doc.get(Field.ENTRY_CLASS_PK))));
			model.setPending(getPendding(GetterUtil.getLong(doc.get(Field.ENTRY_CLASS_PK))));
			model.setOnline(doc.get(DossierTerm.ONLINE));
			model.setHasPassword(doc.get(DossierTerm.SECRET));
			model.setDossierTemplateNo(doc.get(DossierTerm.DOSSIER_TEMPLATE_NO));
			model.setServerNo(doc.get(DossierTerm.SERVER_NO));
			
			model.setViaPostal(doc.get(DossierTerm.VIA_POSTAL));
			model.setPostalAddress(doc.get(DossierTerm.POSTAL_ADDRESS));
			model.setPostalCityCode(doc.get(DossierTerm.POSTAL_CITY_CODE));
			model.setPostalCityName(doc.get(DossierTerm.POSTAL_CITY_NAME));
			model.setPostalDistrictCode(doc.get(DossierTerm.POSTAL_DISTRICT_CODE));
			model.setPostalDistrictName(doc.get(DossierTerm.POSTAL_DISTRICT_NAME));
			model.setPostalTelNo(doc.get(DossierTerm.POSTAL_TEL_NO));
			
			String certNo = doc.get(DeliverableTerm.SO_CHUNG_CHI);
			String certDate = doc.get(DeliverableTerm.NGAY_KY_CC);
			if (Validator.isNotNull(certNo) && Validator.isNotNull(certDate)) {
				Date tempDate = APIDateTimeUtils.convertStringToDate(certDate, APIDateTimeUtils._LUCENE_PATTERN);
				model.setCertDate(APIDateTimeUtils.convertDateToString(tempDate, APIDateTimeUtils._NORMAL_DATE));
				model.setCertNo(doc.get(DeliverableTerm.SO_CHUNG_CHI));
			}

			model.setEndorsementDate(doc.get(DossierTerm.ENDORSEMENT_DATE));
			model.setLockState(doc.get(DossierTerm.LOCK_STATE));
			model.setStatusReg(doc.get(DossierTerm.STATUS_REG));
//			model.setDelegateAddress(DossierTerm.getDelegateAddress());
//			model.setDelegateCityCode(DossierTerm.getDelegateCityCode());
//			model.setDelegateCityName(DossierTerm.getDelegateCityName());
//			model.setDelegateDistrictCode(DossierTerm.getDelegateDistrictCode());
//			model.setDelegateDistrictName(DossierTerm.getDelegateDistrictName());
//			model.setDelegateEmail(DossierTerm.getDelegateEmail());
//			model.setDelegateIdNo(DossierTerm.getDelegateIdNo());
//			model.setDelegateName(DossierTerm.getDelegateName());
//			model.setDelegateTelNo(DossierTerm.getDelegateTelNo());
//			model.setDelegateWardCode(DossierTerm.getDelegateWardCode());
//			model.setDelegateWardName(DossierTerm.getDelegateWardName());
//			if (doc.hasField(DossierTerm.DURATION_COUNT)) {
//				model.setDurationCount(Double.valueOf(doc.get(DossierTerm.DURATION_COUNT)));				
//			}
			if (Validator.isNotNull(doc.get(DossierTerm.DURATION_COUNT))) {
				model.setDurationCount(Double.valueOf(doc.get(DossierTerm.DURATION_COUNT)));				
			}
			else {
				model.setDurationCount(0d);
			}

			if (doc.hasField(DossierTerm.DURATION_UNIT)) {
				model.setDurationUnit(durationUnit);				
			}
//			if (doc.hasField(DossierTerm.SAMPLE_COUNT)) {
//				model.setSampleCount(Long.valueOf(doc.get(DossierTerm.SAMPLE_COUNT)));				
//			}
			if (Validator.isNotNull(doc.get(DossierTerm.SAMPLE_COUNT))) {
				model.setSampleCount(Long.valueOf(doc.get(DossierTerm.SAMPLE_COUNT)));				
			}
			else {
				model.setSampleCount(0l);
			}
			model.setAssigned(GetterUtil.getInteger(doc.get(DossierTerm.ASSIGNED)));

			if (Validator.isNotNull(doc.get(DossierTerm.VNPOSTAL_STATUS))) {
				model.setVnpostalStatus(Integer.parseInt(doc.get(DossierTerm.VNPOSTAL_STATUS)));
				model.setVnpostalProfile(doc.get(DossierTerm.VNPOSTAL_PROFILE));
			}
			if (Validator.isNotNull(doc.get(DossierTerm.FROM_VIA_POSTAL))) {
				model.setFromViaPostal(Integer.parseInt(doc.get(DossierTerm.FROM_VIA_POSTAL)));
			} else {
				model.setFromViaPostal(0);
			}
			ouputs.add(model);
		}
//		_log.info("ouputs: "+ouputs.size());
		return ouputs;
	}

	static Log _log = LogFactoryUtil.getLog(DossierUtils.class);

	public static DossierDetailModel mappingForGetDetail(Dossier input, long userId) {

		DossierDetailModel model = new DossierDetailModel();
		
		if (Validator.isNull(input)) {
			return model;
		}
//		try {
//			Document dossierDoc = DossierLocalServiceUtil.getDossierById(input.getDossierId(), input.getCompanyId());
//			model.setDossierIdCTN(dossierDoc.get(DossierTerm.DOSSIER_ID_CTN));
//		} catch (Exception e) {
//			//_log.error(e);
//			_log.debug(e);
			model.setDossierIdCTN(StringPool.BLANK);
			model.setGroupId(input.getGroupId());
//		}
		
		model.setDossierId(GetterUtil.getInteger(input.getDossierId()));
		model.setDossierName(input.getDossierName());
		model.setUserId(GetterUtil.getInteger(input.getUserId()));
		model.setCreateDate(
				APIDateTimeUtils.convertDateToString(input.getCreateDate(), APIDateTimeUtils._NORMAL_PARTTERN));
		model.setModifiedDate(
				APIDateTimeUtils.convertDateToString(input.getModifiedDate(), APIDateTimeUtils._NORMAL_PARTTERN));
		model.setReferenceUid(input.getReferenceUid());
		model.setCounter(input.getCounter());
		model.setServiceCode(input.getServiceCode());
		model.setServiceName(input.getServiceName());
		model.setGovAgencyCode(input.getGovAgencyCode());
		model.setGovAgencyName(input.getGovAgencyName());
		model.setDossierTemplateNo(input.getDossierTemplateNo());
		model.setApplicantName(input.getApplicantName() != null ? input.getApplicantName().toUpperCase() : StringPool.BLANK);
		model.setApplicantIdType(input.getApplicantIdType());
		model.setApplicantIdNo(input.getApplicantIdNo());
		model.setApplicantIdDate(
				APIDateTimeUtils.convertDateToString(input.getApplicantIdDate(), APIDateTimeUtils._NORMAL_PARTTERN));
		model.setAddress(input.getAddress());
		model.setCityCode(input.getCityCode());
		model.setCityName(input.getCityName());
		model.setDistrictCode(input.getDistrictCode());
		model.setDistrictName(input.getDistrictName());
		model.setWardCode(input.getWardCode());
		model.setWardName(input.getWardName());
		model.setContactName(input.getContactName());
		model.setContactTelNo(input.getContactTelNo());
		model.setContactEmail(input.getContactEmail());
		model.setDossierNote(input.getDossierNote());
		if(Validator.isNotNull(input.getSubmissionNote())) {
			String submissionNote = DossierContentGenerator.getSubmissionNote(
					input.getDossierId(), input.getSubmissionNote());
			model.setSubmissionNote(submissionNote);
		}
		model.setBriefNote(input.getBriefNote());
		model.setDossierNo(input.getDossierNo());
		model.setSubmitting(Boolean.toString(input.getSubmitting()));
		model.setSubmitDate(
				APIDateTimeUtils.convertDateToString(input.getSubmitDate(), APIDateTimeUtils._NORMAL_PARTTERN));
		model.setReceiveDate(
				APIDateTimeUtils.convertDateToString(input.getReceiveDate(), APIDateTimeUtils._NORMAL_PARTTERN));
		model.setDueDate(APIDateTimeUtils.convertDateToString(input.getDueDate(), APIDateTimeUtils._NORMAL_PARTTERN));
		model.setReleaseDate(
				APIDateTimeUtils.convertDateToString(input.getReleaseDate(), APIDateTimeUtils._NORMAL_PARTTERN));
		model.setFinishDate(
				APIDateTimeUtils.convertDateToString(input.getFinishDate(), APIDateTimeUtils._NORMAL_PARTTERN));
		model.setCancellingDate(
				APIDateTimeUtils.convertDateToString(input.getCancellingDate(), APIDateTimeUtils._NORMAL_PARTTERN));
		model.setCorrectingDate(
				APIDateTimeUtils.convertDateToString(input.getCorrecttingDate(), APIDateTimeUtils._NORMAL_PARTTERN));
		model.setExtendDate(
				APIDateTimeUtils.convertDateToString(input.getExtendDate(), APIDateTimeUtils._NORMAL_PARTTERN));
		model.setDossierStatus(input.getDossierStatus());
		model.setDossierStatusText(input.getDossierStatusText());
		model.setDossierSubStatus(input.getDossierSubStatus());
		model.setDossierSubStatusText(input.getDossierSubStatusText());
		model.setViaPostal(Integer.toString(input.getViaPostal()));
		model.setPostalAddress(input.getPostalAddress());
		model.setPostalCityCode(input.getPostalCityCode());
		model.setPostalCityName(input.getPostalCityName());
		model.setPostalDistrictCode(input.getPostalDistrictCode());
		model.setPostalDistrictName(input.getPostalDistrictName());
		model.setPostalTelNo(input.getPostalTelNo());
		model.setPermission(getPermission(input.getPrimaryKey(), userId));

		if (input.getDossierActionId() != 0) {
			DossierAction dossierAction = DossierActionLocalServiceUtil.fetchDossierAction(input.getDossierActionId());
			if (dossierAction != null) {
				model.setLastActionDate(APIDateTimeUtils.convertDateToString(dossierAction.getCreateDate(),
						APIDateTimeUtils._NORMAL_PARTTERN));
				model.setLastActionName(dossierAction.getActionName());
				model.setLastActionUser(dossierAction.getActionUser() != null ? dossierAction.getActionUser().toUpperCase() : StringPool.BLANK);
				model.setLastActionNote(dossierAction.getActionNote());
				model.setLastActionCode(dossierAction.getActionCode());
				model.setLastActionUserId(dossierAction.getUserId());

				model.setStepCode(dossierAction.getStepCode());
				model.setStepName(dossierAction.getStepName());

				Date stepDuedate = DossierOverDueUtils.getStepOverDue(dossierAction.getGroupId(), dossierAction.getActionOverdue(), new Date());

				if (dossierAction.getActionOverdue() != 0) {
					model.setStepOverdue(StringPool.TRUE);
				} else {
					model.setStepOverdue(StringPool.TRUE);
				}

				model.setStepDuedate(APIDateTimeUtils.convertDateToString(stepDuedate, APIDateTimeUtils._NORMAL_PARTTERN));

				ProcessStep step = ProcessStepLocalServiceUtil.fetchBySC_GID(dossierAction.getStepCode(),
						dossierAction.getGroupId(), dossierAction.getServiceProcessId());

				model.setStepInstruction(step!= null ? step.getStepInstruction() : StringPool.BLANK);
			}
			// Check permission process dossier
			DictCollection dictCollection = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(ConstantUtils.DOSSIER_STATUS,
					input.getGroupId());
			String statusCode = input.getDossierStatus();
			String subStatusCode = input.getDossierSubStatus();
			if (Validator.isNotNull(statusCode) || Validator.isNotNull(subStatusCode)) {
				DictItem dictItem = null;
				if (Validator.isNotNull(subStatusCode)) {
					dictItem = DictItemLocalServiceUtil.fetchByF_dictItemCode(subStatusCode,
							dictCollection.getDictCollectionId(), input.getGroupId());
				} else {
					dictItem = DictItemLocalServiceUtil.fetchByF_dictItemCode(statusCode,
							dictCollection.getDictCollectionId(), input.getGroupId());
				}
				if (dictItem != null) {
//					_log.info("53");
					String metaData = dictItem.getMetaData();
					String specialStatus = StringPool.BLANK;
					if (Validator.isNotNull(metaData)) {
//						_log.info("metaData: " +metaData);
						try {
							JSONObject metaJson = JSONFactoryUtil.createJSONObject(metaData);
							specialStatus = metaJson.getString(DossierTerm.KEY_SPECIAL_STATUS);
//							_log.info("specialStatus: " +specialStatus);
							
						} catch (Exception e) {
							// TODO: handle exception
							//_log.error(e);
							_log.debug(e);
						}
					}
					if (Validator.isNotNull(specialStatus) && Boolean.parseBoolean(specialStatus)) {
			DossierActionUser dau = DossierActionUserLocalServiceUtil.getByDossierAndUser(input.getDossierActionId(), userId);
			if (dau != null) {
				model.setSpecialNo(dau.getModerator());
			} else {
				model.setSpecialNo(0);
			}
					} else {
						model.setSpecialNo(1);
					}
				}
			}
		}

		model.setVisited(getVisisted(input.getPrimaryKey()));
		model.setPending(getPendding(input.getPrimaryKey()));
		model.setApplicantNote(input.getApplicantNote());
		model.setNotification(Boolean.toString(input.getNotification()));
		model.setOnline(Boolean.toString(input.getOnline()));
		model.setLockState(input.getLockState());
		model.setDelegateAddress(input.getDelegateAddress());
		model.setDelegateCityCode(input.getDelegateCityCode());
		model.setDelegateCityName(input.getDelegateCityName());
		model.setDelegateDistrictCode(input.getDelegateDistrictCode());
		model.setDelegateDistrictName(input.getDelegateDistrictName());
		model.setDelegateEmail(input.getDelegateEmail());
		model.setDelegateIdNo(input.getDelegateIdNo());
		model.setDelegateName(input.getDelegateName());
		model.setDelegateTelNo(input.getDelegateTelNo());
		model.setDelegateWardCode(input.getDelegateWardCode());
		model.setDelegateWardName(input.getDelegateWardName());
		model.setDurationCount(input.getDurationCount());
		model.setDurationUnit(input.getDurationUnit());
		model.setSampleCount(input.getSampleCount());
		model.setOriginality(input.getOriginality());
		model.setOriginDossierId(input.getOriginDossierId());
		model.setOriginDossierNo(input.getOriginDossierNo());
		model.setMetaData(input.getMetaData());
		model.setGroupDossierId(input.getGroupDossierId());
		model.setDelegateType(input.getDelegateType());
		model.setDocumentNo(input.getDocumentNo());
		if (Validator.isNotNull(input.getDocumentDate())) {
			model.setDocumentDate(APIDateTimeUtils.convertDateToString(input.getDocumentDate(), APIDateTimeUtils._NORMAL_PARTTERN));
		}
		model.setServerNo(input.getServerNo());
		model.setSystemId(input.getSystemId());
		model.setDossierCounter(input.getDossierCounter());
		model.setVnpostalStatus(input.getVnpostalStatus());
		model.setVnpostalProfile(input.getVnpostalProfile());
		model.setFromViaPostal(input.getFromViaPostal());
		model.setPostalCodeSend(input.getPostalCodeSend());
		model.setProcessNo(input.getProcessNo());

		return model;
	}

	public static DossierDetailModel mappingForGetDetailSearch(Dossier input) {

		DossierDetailModel model = new DossierDetailModel();
		
		try {
			Document dossierDoc = DossierLocalServiceUtil.getDossierById(input.getDossierId(), input.getCompanyId());
			model.setDossierIdCTN(dossierDoc.get(DossierTerm.DOSSIER_ID+ConstantUtils.CTN));
		} catch (Exception e) {
			//_log.error(e);
			_log.debug(e);
			model.setDossierIdCTN("");
		}
		

		model.setUserName(input.getUserName());
		model.setGovAgencyName(input.getGovAgencyName());
		model.setDossierNo(input.getDossierNo());
		model.setSubmitDate(
				APIDateTimeUtils.convertDateToString(input.getSubmitDate(), APIDateTimeUtils._NORMAL_PARTTERN));
		model.setDueDate(APIDateTimeUtils.convertDateToString(input.getDueDate(), APIDateTimeUtils._NORMAL_PARTTERN));

		return model;
	}

	private static String getPermission(long dossierId, long userId) {
		// TODO add logic here
		// return list of permission, separate by the comma
		Indexer<Dossier> indexer = IndexerRegistryUtil
				.nullSafeGetIndexer(Dossier.class);

		Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
		
		if (dossier != null) {
			Document doc = null;
			try {
				doc = indexer.getDocument(dossier);
			} catch (SearchException e) {
				_log.debug(e);
			}
			
			if (doc == null) return StringPool.BLANK;
			
			String strPermission = GetterUtil.getString(doc.get(DossierTerm.MAPPING_PERMISSION));
			if (Validator.isNotNull(strPermission)) {
				String[] permissionArr = strPermission.split(StringPool.SPACE);
				if (permissionArr != null) {
					for (String permission: permissionArr) {
						if (Validator.isNotNull(permission) && permission.contains(String.valueOf(userId))) {
							return permission;
						} else {
							boolean isAdmin = false;
							List<Role> roles =
									RoleLocalServiceUtil.getUserRoles(userId);
							try {
								for (Role role : roles) {
									if (ConstantUtils.ROLE_ADMIN.equals(role.getName())) {
	
										isAdmin = true;
										break;
	
									}
								}
	
							}
							catch (Exception e) {
								_log.error(e);
							}			
							if (isAdmin) {
								return ConstantUtils.PERMISSION_READ;
							}
						}
					}
					
					return StringPool.BLANK;
				} else {
					return StringPool.BLANK;
				}
			} else {
				return StringPool.BLANK;
			}
		}
		else {
			return StringPool.BLANK;
		}
	}

	private static String getVisisted(long dossierId) {
		// TODO add logic here
		// return true or false in String type
		return StringPool.FALSE;
	}

	private static String getPendding(long dossierId) {
		// TODO add logic here
		// return true or false in String type
		return StringPool.FALSE;
	}

//	private static String getApplicationNote(long dossierId) {
//		// TODO add logic here
//		// return true or false in String type
//		return StringPool.BLANK;
//	}

	//LamTV: Process get dossier follow dossierId and groupId
	public static Dossier getDossier(String id, long groupId) {
		long dossierId = GetterUtil.getLong(id);
		if (dossierId > 0) {
			try {
				return DossierLocalServiceUtil.getDossier(dossierId);
			} catch (PortalException e) {
				return null;
			}
		} else {

			Dossier dossier = DossierLocalServiceUtil.getByDossierNo(groupId, id);
			if (dossier == null) {
				return DossierLocalServiceUtil.getByRef(groupId, id);
			} else {
				return dossier;
			}
		}
	}

	//LamTV: Process get process option
	public static ProcessOption getProcessOption(String serviceInfoCode, String govAgencyCode, String dossierTemplateNo,
			long groupId) throws PortalException {

		ServiceConfig config = ServiceConfigLocalServiceUtil.getBySICodeAndGAC(groupId, serviceInfoCode, govAgencyCode);
		if (config != null) {
			return ProcessOptionLocalServiceUtil.getByDTPLNoAndServiceCF(groupId, dossierTemplateNo,
					config.getServiceConfigId());
		} else {
			return null;
		}
	}

	//LamTV: Process get process action
	public static ProcessAction getProcessAction(User user, long groupId, Dossier dossier, String actionCode,
			long serviceProcessId) throws PortalException {

		_log.debug("GET PROCESS ACTION____");
		ProcessAction action = null;
		DossierAction dossierAction = DossierActionLocalServiceUtil.fetchDossierAction(dossier.getDossierActionId());
		
		try {
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
				if(actionCode.equals(DossierActionTerm.OUTSIDE_ACTION_PAYMENT)){
					if(Validator.isNull(act.getPreStepCode()) && Validator.isNull(act.getPostStepCode())){
						action = act;
						break;
					}
				}else {
					_log.debug("LamTV_preStepCode: " + preStepCode );
					if (Validator.isNotNull(curStepCode) && !preStepCode.contentEquals(curStepCode)) continue;
				}
				ProcessStep step = ProcessStepLocalServiceUtil.fetchBySC_GID(preStepCode, groupId, serviceProcessId);
//				_log.info("LamTV_ProcessStep: "+step);

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
		} catch (Exception e) {
			_log.debug("NOT PROCESS ACTION");
			_log.debug(e);
		}

		return action;
	}
	
	public static void createDossierUsers(long groupId, Dossier dossier, ServiceProcess process, List<ServiceProcessRole> lstProcessRoles) {
		List<DossierUser> lstDaus = DossierUserLocalServiceUtil.findByDID(dossier.getDossierId());
		int count = 0;
		long[] roleIds = new long[lstProcessRoles.size()];
		for (ServiceProcessRole spr : lstProcessRoles) {
			long roleId = spr.getRoleId();
			roleIds[count++] = roleId;
		}
		List<JobPos> lstJobPoses = JobPosLocalServiceUtil.findByF_mappingRoleIds(groupId, roleIds);
		Map<Long, JobPos> mapJobPoses = new HashMap<>();
		long[] jobPosIds = new long[lstJobPoses.size()];
		count = 0;
		for (JobPos jp : lstJobPoses) {
			mapJobPoses.put(jp.getJobPosId(), jp);
			jobPosIds[count++] = jp.getJobPosId();
		}
		List<EmployeeJobPos> lstTemp = EmployeeJobPosLocalServiceUtil.findByF_G_jobPostIds(groupId, jobPosIds);
		Map<Long, List<EmployeeJobPos>> mapEJPS = new HashMap<>();
		for (EmployeeJobPos ejp : lstTemp) {
			if (mapEJPS.get(ejp.getJobPostId()) != null) {
				mapEJPS.get(ejp.getJobPostId()).add(ejp);
			}
			else {
				List<EmployeeJobPos> lstEJPs = new ArrayList<>();
				lstEJPs.add(ejp);
				mapEJPS.put(ejp.getJobPostId(), lstEJPs);
			}
		}
		for (ServiceProcessRole spr : lstProcessRoles) {
			long roleId = spr.getRoleId();
			int moderator = spr.getModerator() ? 1 : 0;
//			JobPos jp = JobPosLocalServiceUtil.fetchByF_mappingRoleId(groupId, roleId);
			JobPos jp = mapJobPoses.get(roleId);
			
			if (jp != null) {
//				List<EmployeeJobPos> lstEJPs = EmployeeJobPosLocalServiceUtil.getByJobPostId(groupId, jp.getJobPosId());
				List<EmployeeJobPos> lstEJPs = mapEJPS.get(jp.getJobPosId());
				long[] employeeIds = new long[lstEJPs.size()];
				int countEmp = 0;
				for (EmployeeJobPos ejp : lstEJPs) {
					employeeIds[countEmp++] = ejp.getEmployeeId();
				}
				List<Employee> lstEmpls = EmployeeLocalServiceUtil.findByG_EMPID(groupId, employeeIds);
				HashMap<Long, Employee> mapEmpls = new HashMap<>();
				for (Employee e : lstEmpls) {
					mapEmpls.put(e.getEmployeeId(), e);
				}
				List<Employee> lstEmployees = new ArrayList<>();
//				for (EmployeeJobPos ejp : lstEJPs) {
//					Employee employee = EmployeeLocalServiceUtil.fetchEmployee(ejp.getEmployeeId());
//					if (employee != null) {
//						lstEmployees.add(employee);
//					}
//				}
				for (EmployeeJobPos ejp : lstEJPs) {
					if (mapEmpls.get(ejp.getEmployeeId()) != null) {
						lstEmployees.add(mapEmpls.get(ejp.getEmployeeId()));
					}
				}		
				HashMap<Long, DossierUser> mapDaus = new HashMap<>();
				for (DossierUser du : lstDaus) {
					mapDaus.put(du.getUserId(), du);
				}
				for (Employee e : lstEmployees) {
//					DossierUserPK pk = new DossierUserPK();
//					pk.setDossierId(dossier.getDossierId());
//					pk.setUserId(e.getMappingUserId());
//					DossierUser ds = DossierUserLocalServiceUtil.fetchDossierUser(pk);
					if (mapDaus.get(e.getMappingUserId()) == null) {
//					if (ds == null) {
						DossierUserLocalServiceUtil.addDossierUser(groupId, dossier.getDossierId(), e.getMappingUserId(), moderator, Boolean.FALSE);						
					}
					else {
						DossierUser ds = mapDaus.get(e.getMappingUserId());
						
						if (moderator == 1 && ds.getModerator() == 0) {
							ds.setModerator(1);
							DossierUserLocalServiceUtil.updateDossierUser(ds);
						}
					}
				}
			}
		}
	}

	public static DossierActionDetailModel mappingDossierAction(DossierAction dAction, long dossierDocumentId) {
		DossierActionDetailModel model = new DossierActionDetailModel();

		model.setDossierActionId(dAction.getDossierActionId());
		model.setDossierId(dAction.getDossierId());
		model.setDossierDocumentId(dossierDocumentId);
		model.setFromStepCode(dAction.getFromStepCode());
		model.setNextActionId(dAction.getNextActionId());
		model.setGroupId(dAction.getGroupId());
		model.setPreviousActionId(dAction.getPreviousActionId());
		model.setSequenceNo(dAction.getSequenceNo());
		model.setServiceProcessId(dAction.getServiceProcessId());
		model.setRollbackable(dAction.getRollbackable());

		return model;
	}

	public static JSONObject mappingDossierJSON(DossierAction dAction, long dossierDocumentId,JSONObject elmData) {
		elmData.put(DossierActionTerm.DOSSIERACTION_ID, dAction.getDossierActionId());
		elmData.put(DossierActionTerm.DOSSIER_ID,dAction.getDossierId());
		elmData.put(DossierActionTerm.DOSSIER_DOCUMENT_ID,dossierDocumentId);
		elmData.put(DossierActionTerm.FROM_STEP_CODE,dAction.getFromStepCode());
		elmData.put(DossierActionTerm.NEXT_ACTION_ID,dAction.getNextActionId());
		elmData.put(DossierActionTerm.GROUP_ID,dAction.getGroupId());
		elmData.put(DossierActionTerm.PREVIOUS_ACTION_ID,dAction.getPreviousActionId());
		elmData.put(DossierActionTerm.SEQUENCE_NO,dAction.getSequenceNo());
		elmData.put(DossierActionTerm.SERVICE_PROCESS_ID,dAction.getServiceProcessId());
		elmData.put(DossierActionTerm.ROLLBACK_ABLE,dAction.getRollbackable());

		return elmData;
	}

	//Mapping publish dossier
	public static List<DossierDataPublishModel> mappingForGetPublishList(List<Document> docs) {
		List<DossierDataPublishModel> ouputs = new ArrayList<DossierDataPublishModel>();

		for (Document doc : docs) {
			DossierDataPublishModel model = new DossierDataPublishModel();
			model.setServiceCode(doc.get(DossierTerm.SERVICE_CODE));
			model.setServiceName(doc.get(DossierTerm.SERVICE_NAME));
			model.setApplicantName(doc.get(DossierTerm.APPLICANT_NAME));
			model.setApplicantIdNo(doc.get(DossierTerm.APPLICANT_ID_NO));
			model.setDossierNo(doc.get(DossierTerm.DOSSIER_NO));
			if (Validator.isNotNull(doc.get(DossierTerm.RECEIVE_DATE))) {
				Date receiveDate = APIDateTimeUtils.convertStringToDate(doc.get(DossierTerm.RECEIVE_DATE), APIDateTimeUtils._LUCENE_PATTERN);
				model.setReceiveDate(APIDateTimeUtils.convertDateToString(receiveDate, APIDateTimeUtils._NORMAL_PARTTERN));
			} else {
				model.setReceiveDate(doc.get(DossierTerm.RECEIVE_DATE));
			}
			model.setDueDate(doc.get(DossierTerm.DUE_DATE));
//			_log.info("DueDate: "+ doc.get(DossierTerm.DUE_DATE));
			model.setFinishDate(doc.get(DossierTerm.FINISH_DATE));
//			_log.info("FINISH_DATE: "+ doc.get(DossierTerm.FINISH_DATE));
			model.setReleaseDate(doc.get(DossierTerm.RELEASE_DATE));
			model.setDossierStatus(doc.get(DossierTerm.DOSSIER_STATUS));
			model.setDossierStatusText(doc.get(DossierTerm.DOSSIER_STATUS_TEXT));
			model.setDossierSubStatus(doc.get(DossierTerm.DOSSIER_SUB_STATUS));
			model.setDossierSubStatusText(doc.get(DossierTerm.DOSSIER_SUB_STATUS_TEXT));

			ouputs.add(model);
		}

		return ouputs;
	}

	public static DossierInputModel convertFormModelToInputModel(org.opencps.api.dossier.model.DossierInputModel input) {
		DossierInputModel model = new DossierInputModel();
		model.setReferenceUid(input.getReferenceUid());
		model.setServiceCode(input.getServiceCode());
		model.setGovAgencyCode(input.getGovAgencyCode());
		model.setDossierTemplateNo(input.getDossierTemplateNo());
		model.setApplicantName(input.getApplicantName());
		model.setApplicantIdType(input.getApplicantIdType());
		model.setApplicantIdNo(input.getApplicantIdNo());
		model.setApplicantIdDate(input.getApplicantIdDate());
		model.setAddress(input.getAddress());
		model.setCityCode(input.getCityCode());
		model.setDistrictCode(input.getDistrictCode());
		model.setWardCode(input.getWardCode());
		model.setContactName(input.getContactName());
		model.setContactEmail(input.getContactEmail());
		model.setContactTelNo(input.getContactTelNo());
		model.setPassword(input.getPassword());
		model.setOnline(input.getOnline());
		model.setNotification(input.getNotification());
		model.setApplicantNote(input.getApplicantNote());
		model.setViaPostal(input.getViaPostal());
		model.setPostalAddress(input.getPostalAddress());
		model.setPostalCityCode(input.getPostalCityCode());
		model.setPostalTelNo(input.getPostalTelNo());
		model.setOriginality(input.getOriginality());
		model.setDossierNo(input.getDossierNo());
		model.setSubmitDate(input.getSubmitDate());
		model.setReceiveDate(input.getReceiveDate());
		model.setDueDate(input.getDueDate());
		model.setDossierStatus(input.getDossierStatus());
		model.setDossierStatusText(input.getDossierStatusText());
		model.setDossierSubStatus(input.getDossierSubStatus());
		model.setDossierSubStatusText(input.getDossierSubStatusText());
		model.setSameAsApplicant(input.isSameAsApplicant());
		model.setDelegateName(input.getDelegateName());
		model.setDelegateIdNo(input.getDelegateIdNo());
		model.setDelegateTelNo(input.getDelegateTelNo());
		model.setDelegateEmail(input.getDelegateEmail());
		model.setDelegateAddress(input.getDelegateAddress());
		model.setDelegateCityCode(input.getDelegateCityCode());
		model.setDelegateCityName(input.getDelegateCityName());
		model.setDelegateDistrictCode(input.getDelegateDistrictCode());
		model.setDelegateDistrictName(input.getDelegateDistrictName());
		model.setDelegateWardCode(input.getDelegateWardCode());
		model.setDelegateWardName(input.getDelegateWardName());
		model.setSampleCount(input.getSampleCount());
		model.setBriefNote(input.getBriefNote());
		model.setServiceName(input.getServiceName());
		model.setDossierName(input.getDossierName());
		model.setPostalCityName(input.getPostalCityName());
		model.setPostalServiceCode(input.getPostalServiceCode());
		model.setPostalServiceName(input.getPostalServiceName());
		model.setPostalDistrictCode(input.getPostalDistrictCode());
		model.setPostalDistrictName(input.getPostalDistrictName());
		model.setPostalWardCode(input.getPostalWardCode());
		model.setPostalWardName(input.getPostalWardName());
		model.setOriginDossierNo(input.getOriginDossierNo());
		model.setServerNo(input.getServerNo());
		model.setMetaData(input.getMetaData());
		model.setDelegateType(input.getDelegateType());
		model.setDocumentNo(input.getDocumentNo());
		model.setDocumentDate(input.getDocumentDate());
		model.setSystemId(input.getSystemId() != null ? input.getSystemId() : 0);
		model.setDossierCounter(input.getDossierCounter());
		model.setVnpostalStatus(input.getVnpostalStatus());
		model.setVnpostalProfile(input.getVnpostalProfile());
		model.setFromViaPostal(input.getFromViaPostal());
		model.setFormMeta(input.getFormMeta());

		return model;
	}

	//Process multiple dossier
	public static DossierMultipleInputModel convertFormModelToMultipleInputModel(
			org.opencps.api.dossier.model.DossierMultipleInputModel input) {
		DossierMultipleInputModel model = new DossierMultipleInputModel();

		model.setServiceCode(input.getServiceCode());
		model.setGovAgencyCode(input.getGovAgencyCode());
		model.setDossierTemplateNo(input.getDossierTemplateNo());
		model.setOriginality(input.getOriginality());
		model.setDossiers(input.getDossiers());
		model.setDossierFileArr(input.getDossierFileArr());
		model.setDossierMarkArr(input.getDossierMarkArr());
		model.setPayment(input.getPayment());
		model.setSystemId(input.getSystemId() != null ? input.getSystemId() : 0);

		return model;
	}

	public static DossierPublishModel convertFormModelToPublishModel(org.opencps.api.dossier.model.DossierPublishModel input) {
		DossierPublishModel model = new DossierPublishModel();
		model.setAddress(input.getAddress());
		model.setApplicantIdDate(input.getApplicantIdDate());
		model.setApplicantIdNo(input.getApplicantIdNo());
		model.setApplicantIdType(input.getApplicantIdType());
		model.setApplicantName(input.getApplicantName());
		model.setApplicantNote(input.getApplicantNote());
		model.setCancellingDate(input.getCancellingDate());
		model.setCityCode(input.getCityCode());
		model.setCityName(input.getCityName());
		model.setContactEmail(input.getContactEmail());
		model.setContactName(input.getContactName());
		model.setContactTelNo(input.getContactTelNo());
		model.setCorrectingDate(input.getCorrecttingDate());
		model.setCounter(input.getCounter());
		model.setCreateDate(input.getCreateDate());
		model.setDelegateAddress(input.getDelegateAddress());
		model.setDelegateCityCode(input.getDelegateCityCode());
		model.setDelegateCityName(input.getDelegateCityName());
		model.setDelegateDistrictCode(input.getDelegateDistrictCode());
		model.setDelegateDistrictName(input.getDelegateDistrictName());
		model.setDelegateEmail(input.getDelegateEmail());
		model.setDelegateIdNo(input.getDelegateIdNo());
		model.setDelegateWardCode(input.getDelegateWardCode());
		model.setDelegateWardName(input.getDelegateWardName());
		model.setDistrictCode(input.getDistrictCode());
		model.setDistrictName(input.getDistrictName());
		model.setDossierActionId(input.getDossierActionId());
		model.setDossierName(input.getDossierName());
		model.setDossierNo(input.getDossierNo());
		model.setDossierStatus(input.getDossierStatus());
		model.setDossierStatusText(input.getDossierStatusText());
		model.setDossierSubStatus(input.getDossierSubStatus());
		model.setDossierSubStatusText(input.getDossierSubStatusText());
		model.setDossierTemplateNo(input.getDossierTemplateNo());
		model.setDueDate(input.getDueDate());
		model.setDurationCount(input.getDurationCount());
		model.setDurationUnit(input.getDurationUnit());
		model.setEndorsementDate(input.getEndorsementDate());
		model.setExtendDate(input.getExtendDate());
		model.setFinishDate(input.getFinishDate());
		model.setGovAgencyCode(input.getGovAgencyCode());
		model.setGovAgencyName(input.getGovAgencyName());
		model.setLockState(input.getLockState());
		model.setModifiedDate(input.getModifiedDate());
		model.setNotification(input.getNotification());
		model.setOnline(input.getOnline());
		model.setOriginality(input.getOriginality());
		model.setPassword(input.getPassword());
		model.setPostalAddress(input.getPostalAddress());
		model.setPostalCityCode(input.getPostalCityCode());
		model.setPostalCityName(input.getPostalCityName());
		model.setPostalDistrictCode(input.getPostalDistrictCode());
		model.setPostalDistrictName(input.getPostalDistrictName());
		model.setPostalTelNo(input.getPostalTelNo());
		model.setProcessDate(input.getProcessDate());
		model.setProcessNo(input.getProcessNo());
		model.setReceiveDate(input.getReceiveDate());
		model.setReferenceUid(input.getReferenceUid());
		model.setReleaseDate(input.getReleaseDate());
		model.setSameAsApplicant(input.isSameAsApplicant());
		model.setSampleCount(input.getSampleCount());
		model.setServiceCode(input.getServiceCode());
		model.setServiceName(input.getServiceName());
		model.setSubmissionNote(input.getSubmissionNote());
		model.setSubmitDate(input.getSubmitDate());
		model.setViaPostal(input.getViaPostal());
		model.setWardCode(input.getWardCode());
		model.setWardName(input.getWardName());
		model.setMetaData(input.getMetaData());
		model.setDossierCounter(input.getDossierCounter());
		model.setVnpostalStatus(input.getVnpostalStatus());
		model.setVnpostalProfile(input.getVnpostalProfile());
		model.setFromViaPostal(input.getFromViaPostal());
		
		return model;
	}
	
//	private static boolean processBeTime(long releaseDate, long dueDate, long finishDate, long extendDate) {
//		return (releaseDate!=0 && dueDate!=0 && 
//				((releaseDate<dueDate && extendDate!=0) || (finishDate!=0 && finishDate<dueDate )));
//	}
//
//	private static boolean processOnTime(long releaseDate, long dueDate, long finishDate, long extendDate) {
//		return (releaseDate != 0 && (dueDate == 0
//				|| (releaseDate < dueDate && extendDate == 0 && (finishDate == 0 || finishDate >= dueDate))));
//	}
//
//	private static boolean processOverTime(long releaseDate, long dueDate, long finishDate, long extendDate) {
//		return (releaseDate!=0 && dueDate!=0 && releaseDate>=dueDate);
//	}

	private static boolean checkWaiting(String lockState, String dossierStatus) {
		return (DossierTerm.DOSSIER_STATUS_WAITING.equals(dossierStatus)
				 || (Validator.isNotNull(lockState) && DossierTerm.PAUSE_STATE.equals(lockState))
				 || (Validator.isNotNull(lockState) && DossierTerm.PAUSE_OVERDUE_LOCK_STATE.equals(lockState)));
	}

	private static boolean checkReceiving(String dossierStatus) {
		return (DossierTerm.DOSSIER_STATUS_RECEIVING.equals(dossierStatus));
	}
	public static List<Long> mappingForListCongVan(List<Document> docs) {
		List<DossierDataModel> ouputs = new ArrayList<DossierDataModel>();
		if(Validator.isNotNull(docs)) {
			for (Document doc : docs) {
				int originality = GetterUtil.getInteger(doc.get(DossierTerm.ORIGINALLITY));
				DossierDataModel model = new DossierDataModel();
				model.setDossierIdCTN(doc.get(DossierTerm.DOSSIER_ID_CTN));
				model.setDossierId(GetterUtil.getInteger(doc.get(Field.ENTRY_CLASS_PK)));
				model.setDossierName(doc.get(DossierTerm.DOSSIER_NAME));
				model.setGroupId(GetterUtil.getInteger(doc.get(Field.GROUP_ID)));
				model.setServiceCode(doc.get(DossierTerm.SERVICE_CODE));
				model.setServiceName(doc.get(DossierTerm.SERVICE_NAME));
				model.setGovAgencyCode(doc.get(DossierTerm.GOV_AGENCY_CODE));
				model.setGovAgencyName(doc.get(DossierTerm.GOV_AGENCY_NAME));
				model.setApplicantName(doc.get(DossierTerm.APPLICANT_NAME) != null ? doc.get(DossierTerm.APPLICANT_NAME).toUpperCase().replace(";", "; ") : StringPool.BLANK);
				model.setDossierNo(doc.get(DossierTerm.DOSSIER_NO));
				model.setOriginality(originality);
				model.setDueDate(doc.get(DossierTerm.DUE_DATE));
				model.setDueDateComing(doc.get(DossierTerm.DUE_DATE_COMING));
				model.setExtendDate(doc.get(DossierTerm.EXTEND_DATE));
				model.setDossierStatus(doc.get(DossierTerm.DOSSIER_STATUS));
				model.setStepCode(doc.get(DossierTerm.STEP_CODE));
				model.setStepDuedate(doc.get(DossierTerm.STEP_DUE_DATE));
				model.setDossierTemplateNo(doc.get(DossierTerm.DOSSIER_TEMPLATE_NO));
				model.setServerNo(doc.get(DossierTerm.SERVER_NO));
				String groupDossierId = "";
				if (Validator.isNotNull(doc.get(DossierTerm.GROUP_DOSSIER_ID))) {
					String[] idGroup = doc.get(DossierTerm.GROUP_DOSSIER_ID).split(StringPool.SPACE);
					for (String key : idGroup) {
						groupDossierId += key + ",";
					}
					model.setGroupDossierIds(groupDossierId);
				}
				model.setDocumentNo(GetterUtil.getString(doc.get(DossierTerm.DOCUMENT_NO)));

				ouputs.add(model);
			}
		}
		List<Long> lstId = new ArrayList<>();
		if(ouputs !=null){
			for(DossierDataModel item : ouputs) {
				//GroupDossierId : id,id,id
				if (Validator.isNotNull(item.getGroupDossierIds())) {
					String groupDossierIds = String.valueOf(item.getGroupDossierIds());
					String[] id = groupDossierIds.split(StringPool.COMMA);
					for (String key : id) {
						if(!key.contains(lstId.toString())){
							lstId.add(Long.valueOf(key));
						}
					}
				}
			}
		}

		return lstId;
	}
	public static List<DossierDataModel> mappingForListDossier(List<Dossier> docs) {
		List<DossierDataModel> ouputs = new ArrayList<DossierDataModel>();
		for (Dossier doc : docs) {
			int originality = GetterUtil.getInteger(doc.getOriginality());
			DossierDataModel model = new DossierDataModel();
 			model.setDossierId(GetterUtil.getInteger(doc.getDossierId()));
			model.setDossierName(doc.getDossierName());
			model.setGroupId(GetterUtil.getInteger(doc.getGroupId()));
			model.setServiceCode(doc.getServiceCode());
			model.setServiceName(doc.getServiceName());
			model.setGovAgencyCode(doc.getGovAgencyCode());
			model.setGovAgencyName(doc.getGovAgencyName());
			model.setApplicantName(doc.getApplicantName() != null ? doc.getApplicantName().toUpperCase().replace(";", "; ") : StringPool.BLANK);
			model.setDossierNo(doc.getDossierNo());
			model.setOriginality(originality);
			model.setDueDate(doc.getDueDate().toGMTString());
			if(Validator.isNotNull(doc.getExtendDate())) {
				model.setExtendDate(doc.getExtendDate().toGMTString());
			}
			model.setDossierStatus(doc.getDossierStatus());
 			model.setDossierTemplateNo(doc.getDossierTemplateNo());
			model.setDossierTemplateName(doc.getDossierTemplateName());
			model.setServerNo(doc.getServerNo());
			model.setGroupDossierId(GetterUtil.getLong(doc.getGroupDossierId()));
			model.setDocumentNo(GetterUtil.getString(doc.getDocumentNo()));
			if (Validator.isNotNull(doc.getDocumentDate())) {
				model.setDocumentDate(APIDateTimeUtils.convertDateToString(doc.getDocumentDate(), APIDateTimeUtils._NORMAL_PARTTERN));
			}
//			model.setDocumentDate(GetterUtil.getString(doc.getDocumentDate()));
			if(Validator.isNotNull(doc.getMetaData())) {
				model.setMetaData(GetterUtil.getString(doc.getMetaData()));
			}
			ouputs.add(model);
		}

		return ouputs;
	}

}
