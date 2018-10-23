package org.opencps.api.controller.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.opencps.api.dossier.model.DossierActionDetailModel;
import org.opencps.api.dossier.model.DossierDataModel;
import org.opencps.api.dossier.model.DossierDataPublishModel;
import org.opencps.api.dossier.model.DossierDetailModel;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.opencps.datamgt.util.HolidayUtils;
import org.opencps.dossiermgt.action.util.DossierMgtUtils;
import org.opencps.dossiermgt.action.util.DossierOverDueUtils;
import org.opencps.dossiermgt.constants.ConstantsTerm;
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
import org.opencps.dossiermgt.service.persistence.DossierUserPK;
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

	public static List<DossierDataModel> mappingForGetList(List<Document> docs, long  userId) {
		List<DossierDataModel> ouputs = new ArrayList<DossierDataModel>();

		for (Document doc : docs) {
			DossierDataModel model = new DossierDataModel();
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
			model.setApplicantName(doc.get(DossierTerm.APPLICANT_NAME) != null ? doc.get(DossierTerm.APPLICANT_NAME).toUpperCase() : StringPool.BLANK);
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
			Date now = new Date();
			long dateNowTimeStamp = now.getTime();
			Long dueDateTimeStamp = Long.valueOf(doc.get(DossierTerm.DUE_DATE_TIMESTAMP));
			Long releaseDateTimeStamp = Long.valueOf(doc.get(DossierTerm.RELEASE_DATE_TIMESTAMP));
//			_log.info("dueDateTimeStamp: "+dueDateTimeStamp);
//			_log.info("releaseDateTimeStamp: "+releaseDateTimeStamp);
			int durationUnit = (Validator.isNotNull(doc.get(DossierTerm.DURATION_UNIT))) ? Integer.valueOf(doc.get(DossierTerm.DURATION_UNIT)) : 1;
			long groupId = GetterUtil.getLong(doc.get(Field.GROUP_ID));
			if (releaseDateTimeStamp != null && releaseDateTimeStamp > 0) {
				if (dueDateTimeStamp != null && dueDateTimeStamp > 0) {
					long subTimeStamp = releaseDateTimeStamp - dueDateTimeStamp;
					String strOverDue = calculatorOverDue(durationUnit, subTimeStamp, releaseDateTimeStamp,
							dueDateTimeStamp, groupId, false);
					if (Validator.isNotNull(strOverDue)) {
						if (subTimeStamp > 0) {
//							String strOverDue = calculatorOverDue(durationUnit, subTimeStamp, releaseDateTimeStamp,
//									dueDateTimeStamp, groupId, true);
							model.setDossierOverdue("Quá hạn " + strOverDue);
						} else {
							model.setDossierOverdue("Còn " + strOverDue);
						}
					} else {
						model.setDossierOverdue(StringPool.BLANK);
					}
				} else {
					model.setDossierOverdue(StringPool.BLANK);
				}
			} else {
				if (dueDateTimeStamp != null && dueDateTimeStamp > 0) {
					long subTimeStamp = dateNowTimeStamp - dueDateTimeStamp;
//					_log.info("subTimeStamp: "+subTimeStamp);
					String strOverDue = calculatorOverDue(durationUnit, subTimeStamp, dateNowTimeStamp,
							dueDateTimeStamp, groupId, false);
					if (Validator.isNotNull(strOverDue)) {
						if (subTimeStamp > 0) {
//							String strOverDue = calculatorOverDue(durationUnit, subTimeStamp, releaseDateTimeStamp,
//									dueDateTimeStamp, groupId, true);
							model.setDossierOverdue("Quá hạn " + strOverDue);
						} else {
							model.setDossierOverdue("Còn " + strOverDue);
						}
					} else {
						model.setDossierOverdue(StringPool.BLANK);
					}
				} else {
					model.setDossierOverdue(StringPool.BLANK);
				}
			}

			model.setFinishDate(doc.get(DossierTerm.FINISH_DATE));
			model.setReleaseDate(doc.get(DossierTerm.RELEASE_DATE));
			//Process StepOverDue
//			double durationCount = (Validator.isNotNull(doc.get(DossierTerm.DURATION_COUNT))) ? Double.valueOf(doc.get(DossierTerm.DURATION_COUNT)) : 0.0;
//			if (Double.compare(durationCount, 0.0) != 0) {
			long dossierActionId = GetterUtil.getLong(doc.get(DossierTerm.DOSSIER_ACTION_ID));
//			_log.info("dossierActionId: "+dossierActionId);
			DossierAction dAction = DossierActionLocalServiceUtil.fetchDossierAction(dossierActionId);
			if (dAction != null) {
				int state = dAction.getState();
//				if (model.getDossierId() == 9102) {
//					_log.info("ACTION OVERDUE: " + dAction.getActionOverdue());
//				}
				if (state > 0) {
					int actionOverDue = dAction.getActionOverdue();
					if (actionOverDue > 0) {
						model.setStepOverdue("Quá hạn "+actionOverDue + " ngày");
					} else {
						model.setStepOverdue(StringPool.BLANK);
					}
				} else {
					Date dueDate = dAction.getDueDate();
//					_log.info("dueDate: "+dueDate);
					if (dueDate != null) {
						long dueDateActionTimeStamp = dueDate.getTime();
						long subTimeStamp = dateNowTimeStamp - dueDateActionTimeStamp;
//						_log.info("START STEP OVERDUE");
						String stepOverDue = calculatorOverDue(durationUnit, subTimeStamp, dateNowTimeStamp,
								dueDateActionTimeStamp, groupId, true);
						if (Validator.isNotNull(stepOverDue)) {
							if (subTimeStamp > 0) {
//								String strOverDue = calculatorOverDue(durationUnit, subTimeStamp, releaseDateTimeStamp,
//										dueDateTimeStamp, groupId, true);
								model.setStepOverdue("Quá hạn " + stepOverDue);
							} else {
								model.setStepOverdue("Còn " + stepOverDue);
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
//				if (dAction != null) {
//					long serviceProcessId = dAction.getServiceProcessId();
//					String postStep = dAction.getStepCode();
//				if (Validator.isNotNull(postStep)) {
//					ProcessStep step = ProcessStepLocalServiceUtil.fetchBySC_GID(postStep, groupId, serviceProcessId);
//					if (step != null) {
//						Double durationCountStep = step.getDurationCount();
////						_log.info("durationCountStep: "+durationCountStep);
//						Date dueDateStep = null;
//						if (Validator.isNotNull(durationCountStep) && durationCountStep > 0) {
//							dueDateStep = HolidayUtils.getDueDate(dAction.getCreateDate(), durationCountStep,
//									durationUnit, groupId);
//						} else {
//							model.setStepOverdue(StringPool.BLANK);
//						}
//						if (dueDateStep != null) {
//							long dueDateStepTimeStamp = dueDateStep.getTime();
//							if (dueDateStepTimeStamp > 0) {
//								long subTimeStepStamp = dateNowTimeStamp - dueDateStepTimeStamp;
//								if (subTimeStepStamp > 0) {
//									String stepOverDue = calculatorOverDue(durationUnit, subTimeStepStamp);
//									model.setStepOverdue("Quá hạn "+stepOverDue);
////									_log.info("setStepOverdue Qua Han: "+model.getStepOverdue());
//								} else {
//									String stepOverDue = calculatorOverDue(durationUnit, subTimeStepStamp);
//									model.setStepOverdue("Còn "+stepOverDue);
////									_log.info("setStepOverdue Còn Han: "+model.getStepOverdue());
//								}
//							} else {
//								model.setStepOverdue(StringPool.BLANK);
//							}
//						}  else {
//							model.setStepOverdue(StringPool.BLANK);
//						}
//					}
//				}  else {
//					model.setStepOverdue(StringPool.BLANK);
//				}
//			} else {
//				model.setStepOverdue(StringPool.BLANK);
//			}
			//LamTV: Process Assigned dossier
			DossierActionUser dau = DossierActionUserLocalServiceUtil.getByDossierAndUser(dossierActionId, userId);
//			_log.info("ASSIGNED" + dau);
			if (dau != null) {
				model.setAssigned(dau.getAssigned());
			} else {
				model.setAssigned(ConstantsTerm.NO_ASSINED);
			}
			
			model.setCancellingDate(doc.get(DossierTerm.CANCELLING_DATE));
			model.setCorrectingDate(doc.get(DossierTerm.CORRECTING_DATE));
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
									if ("Administrator".equals(role.getName())) {

										isAdmin = true;
										break;

									}
								}

							}
							catch (Exception e) {
								_log.error(e);
							}			
							if (isAdmin) {
								model.setPermission("read");
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
			model.setPostalTelNo(doc.get(DossierTerm.POSTAL_TEL_NO));
			model.setCertNo(doc.get("so_chung_chi"));
			if (Validator.isNotNull(doc.get("ngay_ky_cc"))) {
				Date certDate = APIDateTimeUtils.convertStringToDate(doc.get("ngay_ky_cc"), APIDateTimeUtils._LUCENE_PATTERN);
				model.setCertDate(APIDateTimeUtils.convertDateToString(certDate, APIDateTimeUtils._NORMAL_DATE));
			} else {
				model.setCertDate(doc.get("ngay_ky_cc"));
			}

			model.setEndorsementDate(doc.get(DossierTerm.ENDORSEMENT_DATE));
			model.setLockState(doc.get(DossierTerm.LOCK_STATE));
			model.setStatusReg(doc.get(DossierTerm.STATUS_REG));

//			int processBlock = 0;
//			int processUnit = 0;
//			try {
//				ServiceConfig serviceConfig = ServiceConfigLocalServiceUtil.getBySICodeAndGAC(GetterUtil.getLong(doc.get(DossierTerm.GROUP_ID)), doc.get(DossierTerm.SERVICE_CODE) ,doc.get(DossierTerm.GOV_AGENCY_CODE));
//				
//				ProcessOption processOption = ProcessOptionLocalServiceUtil.getByDTPLNoAndServiceCF(GetterUtil.getLong(doc.get(DossierTerm.GROUP_ID)), doc.get(DossierTerm.DOSSIER_TEMPLATE_NO), serviceConfig.getServiceConfigId());
//				
//				ServiceProcess serviceProcess = ServiceProcessLocalServiceUtil.fetchServiceProcess(processOption.getServiceProcessId());
//				
//				processBlock = serviceProcess.getDurationCount();
//				
//				processUnit = serviceProcess.getDurationUnit();
//				
//			} catch (Exception e) {
//			}
//			model.setProcessBlock(processBlock);
//			model.setProcessUnit(processUnit);
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

			ouputs.add(model);
		}

		return ouputs;
	}

	public static String calculatorOverDue(int durationUnit, long subTimeStamp, long releaseDateTimeStamp,
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
//			_log.info("dueCount: "+dueCount);
//			_log.info("countDayHoliday: "+countDayHoliday);
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
//				_log.info("flagCeil: "+flagCeil);
//				_log.info("overDue: "+overDue);
//				_log.info("strOverDue: "+strOverDue);
				return overDue + strOverDue;
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
		return (int)overDue + strOverDue;
	}

	public static void main(String []args) {
		if (Double.compare(0.5, 0.0) == 0) {
			System.out.println(true);
		} else {
			System.out.println(false);
		}
	}
	
	//TODO: Process get list Paging
	public static List<DossierDataModel> mappingForGetListPaging(List<Document> docs, int start, int end) {
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
			model.setPermission(getPermission(GetterUtil.getLong(doc.get(Field.ENTRY_CLASS_PK))));
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
			model.setPostalTelNo(doc.get(DossierTerm.POSTAL_TEL_NO));
			
			String certNo = doc.get("so_chung_chi");
			String certDate = doc.get("ngay_ky_cc");
			if (Validator.isNotNull(certNo) && Validator.isNotNull(certDate)) {
				Date tempDate = APIDateTimeUtils.convertStringToDate(certDate, APIDateTimeUtils._LUCENE_PATTERN);
				model.setCertDate(APIDateTimeUtils.convertDateToString(tempDate, APIDateTimeUtils._NORMAL_DATE));
				model.setCertNo(doc.get("so_chung_chi"));
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

			ouputs.add(model);
		}
//		_log.info("ouputs: "+ouputs.size());
		return ouputs;
	}

	static Log _log = LogFactoryUtil.getLog(DossierUtils.class);

	public static DossierDetailModel mappingForGetDetail(Dossier input, long userId) {

		DossierDetailModel model = new DossierDetailModel();
		
		try {
			Document dossierDoc = DossierLocalServiceUtil.getDossierById(input.getDossierId(), input.getCompanyId());
			model.setDossierIdCTN(dossierDoc.get(DossierTerm.DOSSIER_ID_CTN));
		} catch (Exception e) {
			//_log.error(e);
			_log.debug(e);
			model.setDossierIdCTN(StringPool.BLANK);
		}
		
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
		model.setSubmissionNote(input.getSubmissionNote());
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
		model.setPostalTelNo(input.getPostalTelNo());
		model.setPermission(getPermission(input.getPrimaryKey()));

		if (input.getDossierActionId() != 0) {
			DossierAction dossierAction = DossierActionLocalServiceUtil.fetchDossierAction(input.getDossierActionId());

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

			model.setStepInstruction(step.getStepInstruction());

			// Check permission process dossier
			DictCollection dictCollection = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode("DOSSIER_STATUS",
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
							specialStatus = metaJson.getString("specialStatus");
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

//		try {
//			long groupId = input.getGroupId();
//			ServiceConfig serviceConfig = ServiceConfigLocalServiceUtil.getBySICodeAndGAC(groupId, input.getServiceCode(), input.getGovAgencyCode());
//			ProcessOption processOption = ProcessOptionLocalServiceUtil.getByDTPLNoAndServiceCF(groupId,input.getDossierTemplateNo(), serviceConfig.getServiceConfigId());
//			ServiceProcess serviceProcess = ServiceProcessLocalServiceUtil.fetchServiceProcess(processOption.getServiceProcessId());
//
//			double durationCount = serviceProcess.getDurationCount();
//			double durationUnit = serviceProcess.getDurationUnit();
//			model.setDurationCount(durationCount);
//			model.setDurationUnit(durationUnit);
//		} catch (Exception e) {
//			model.setDurationCount(0d);
//			model.setDurationUnit(0d);
//		}

		return model;
	}

	public static DossierDetailModel mappingForGetDetailSearch(Dossier input) {

		DossierDetailModel model = new DossierDetailModel();
		
		try {
			Document dossierDoc = DossierLocalServiceUtil.getDossierById(input.getDossierId(), input.getCompanyId());
			model.setDossierIdCTN(dossierDoc.get(DossierTerm.DOSSIER_ID+"CTN"));
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

	private static String getPermission(long dossierId) {
		// TODO add logic here
		// return list of permission, separate by the comma
		return StringPool.BLANK;
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
			return DossierLocalServiceUtil.fetchDossier(dossierId);
		} else {
			return DossierLocalServiceUtil.getByRef(groupId, id);
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
	public static ProcessAction getProcessAction(long groupId, Dossier dossier, String actionCode,
			long serviceProcessId) throws PortalException {

		_log.info("GET PROCESS ACTION____");
		ProcessAction action = null;
		DossierAction dossierAction = DossierActionLocalServiceUtil.fetchDossierAction(dossier.getDossierActionId());
		
		try {
			List<ProcessAction> actions = ProcessActionLocalServiceUtil.getByActionCode(groupId, actionCode,
					serviceProcessId);

			String dossierStatus = dossier.getDossierStatus();
			String dossierSubStatus = dossier.getDossierSubStatus();
			String preStepCode;
			for (ProcessAction act : actions) {

				preStepCode = act.getPreStepCode();
				_log.info("LamTV_preStepCode: "+preStepCode);

				ProcessStep step = ProcessStepLocalServiceUtil.fetchBySC_GID(preStepCode, groupId, serviceProcessId);
				_log.info("LamTV_ProcessStep: "+step);

				if (Validator.isNull(step) && dossierAction == null) {
					action = act;
					break;
				} else {
					String stepStatus = step.getDossierStatus();
					String stepSubStatus = step.getDossierSubStatus();
					boolean flagCheck = false;
					
					if (dossierAction != null) {
						if (act.getPreStepCode().equals(dossierAction.getStepCode())) {
							flagCheck = true;
						}
					}
					else {
						flagCheck = true;
					}
					
					if (stepStatus.contentEquals(dossierStatus)
							&& StringUtil.containsIgnoreCase(stepSubStatus, dossierSubStatus)
							&& flagCheck) {
						if (DossierMgtUtils.checkPreCondition(act.getPreCondition().split(StringPool.COMMA), dossier)) {
							action = act;
							break;							
						}
					}
				}
			}
		} catch (Exception e) {
			_log.info("NOT PROCESS ACTION");
			_log.info(e);
		}

		return action;
	}
	
	public static void createDossierUsers(long groupId, Dossier dossier, ServiceProcess process, List<ServiceProcessRole> lstProcessRoles) {
		for (ServiceProcessRole spr : lstProcessRoles) {
			long roleId = spr.getRoleId();
			int moderator = spr.getModerator() ? 1 : 0;
			JobPos jp = JobPosLocalServiceUtil.fetchByF_mappingRoleId(groupId, roleId);
			
			if (jp != null) {
				List<EmployeeJobPos> lstEJPs = EmployeeJobPosLocalServiceUtil.getByJobPostId(groupId, jp.getJobPosId());
				List<Employee> lstEmployees = new ArrayList<>();
				for (EmployeeJobPos ejp : lstEJPs) {
					Employee employee = EmployeeLocalServiceUtil.fetchEmployee(ejp.getEmployeeId());
					if (employee != null) {
						lstEmployees.add(employee);
					}
				}
				
				for (Employee e : lstEmployees) {
					DossierUserPK pk = new DossierUserPK();
					pk.setDossierId(dossier.getDossierId());
					pk.setUserId(e.getMappingUserId());
					DossierUser ds = DossierUserLocalServiceUtil.fetchDossierUser(pk);
					if (ds == null) {
						DossierUserLocalServiceUtil.addDossierUser(groupId, dossier.getDossierId(), e.getMappingUserId(), moderator, Boolean.FALSE);						
					}
					else {
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
}
