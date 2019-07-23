package org.opencps.api.controller.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.opencps.api.constants.ConstantUtils;
import org.opencps.api.v21.model.ActionConfigList;
import org.opencps.api.v21.model.ActionConfigList.ActionConfig;
import org.opencps.api.v21.model.Actions;
import org.opencps.api.v21.model.Actions.ProcessAction;
import org.opencps.api.v21.model.ApplicantList;
import org.opencps.api.v21.model.ApplicantList.Applicant;
import org.opencps.api.v21.model.Configs;
import org.opencps.api.v21.model.Configs.ServiceConfig;
import org.opencps.api.v21.model.DeliverableTypeList;
import org.opencps.api.v21.model.DeliverableTypeList.DeliverableType;
import org.opencps.api.v21.model.DeliverableTypeRoleList;
import org.opencps.api.v21.model.DeliverableTypeRoleList.DeliverableTypeRole;
import org.opencps.api.v21.model.DictCollection;
import org.opencps.api.v21.model.DocumentTypeList;
import org.opencps.api.v21.model.DocumentTypeList.DocumentType;
import org.opencps.api.v21.model.DossierTemplate;
import org.opencps.api.v21.model.DynamicReportList;
import org.opencps.api.v21.model.DynamicReportList.DynamicReport;
import org.opencps.api.v21.model.FileTemplates;
import org.opencps.api.v21.model.FileTemplates.FileTemplate;
import org.opencps.api.v21.model.Groups;
import org.opencps.api.v21.model.Groups.DictGroup;
import org.opencps.api.v21.model.HolidayList;
import org.opencps.api.v21.model.HolidayList.Holiday;
import org.opencps.api.v21.model.Items;
import org.opencps.api.v21.model.Items.DictItem;
import org.opencps.api.v21.model.MenuConfigList;
import org.opencps.api.v21.model.MenuConfigList.MenuConfig;
import org.opencps.api.v21.model.NotificationTemplateList;
import org.opencps.api.v21.model.NotificationTemplateList.NotificationTemplate;
import org.opencps.api.v21.model.Parts;
import org.opencps.api.v21.model.Parts.DossierPart;
import org.opencps.api.v21.model.PaymentConfigList;
import org.opencps.api.v21.model.PaymentConfigList.PaymentConfig;
import org.opencps.api.v21.model.Processes;
import org.opencps.api.v21.model.Processes.ProcessOption;
import org.opencps.api.v21.model.Sequences;
import org.opencps.api.v21.model.Sequences.ProcessSequence;
import org.opencps.api.v21.model.ServerConfigList;
import org.opencps.api.v21.model.ServerConfigList.ServerConfig;
import org.opencps.api.v21.model.ServiceInfo;
import org.opencps.api.v21.model.ServiceProcess;
import org.opencps.api.v21.model.ServiceProcess.Roles;
import org.opencps.api.v21.model.ServiceProcess.Roles.ProcessRole;
import org.opencps.api.v21.model.StepConfigList;
import org.opencps.api.v21.model.StepConfigList.StepConfig;
import org.opencps.api.v21.model.Steps;
import org.opencps.api.v21.model.Steps.ProcessStep;
import org.opencps.api.v21.model.Steps.ProcessStep.Roles.StepRole;
import org.opencps.api.v21.model.UserManagement;
import org.opencps.api.v21.model.UserManagement.Roles.JobPos;
import org.opencps.api.v21.model.UserManagement.Users.Employee;
import org.opencps.api.v21.model.WorkingTimeList;
import org.opencps.api.v21.model.WorkingTimeList.WorkingTime;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.communication.action.NotificationTemplateInterface;
import org.opencps.communication.action.impl.NotificationTemplateActions;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.datamgt.action.DictcollectionInterface;
import org.opencps.datamgt.action.HolidayInterface;
import org.opencps.datamgt.action.WorkTimeInterface;
import org.opencps.datamgt.action.impl.DictCollectionActions;
import org.opencps.datamgt.action.impl.HolidayActions;
import org.opencps.datamgt.action.impl.WorkTimeActions;
import org.opencps.datamgt.model.FileAttach;
import org.opencps.datamgt.service.FileAttachLocalServiceUtil;
import org.opencps.dossiermgt.action.ActionConfigActions;
import org.opencps.dossiermgt.action.DeliverableTypesActions;
import org.opencps.dossiermgt.action.DocumentTypeActions;
import org.opencps.dossiermgt.action.DossierTemplateActions;
import org.opencps.dossiermgt.action.DynamicReportActions;
import org.opencps.dossiermgt.action.FileUploadUtils;
import org.opencps.dossiermgt.action.MenuConfigActions;
import org.opencps.dossiermgt.action.PaymentConfigActions;
import org.opencps.dossiermgt.action.ServiceConfigActions;
import org.opencps.dossiermgt.action.ServiceInfoActions;
import org.opencps.dossiermgt.action.ServiceProcessActions;
import org.opencps.dossiermgt.action.StepConfigActions;
import org.opencps.dossiermgt.action.impl.ActionConfigActionsImpl;
import org.opencps.dossiermgt.action.impl.DeliverableTypesActionsImpl;
import org.opencps.dossiermgt.action.impl.DocumentTypeActionsImpl;
import org.opencps.dossiermgt.action.impl.DossierTemplateActionsImpl;
import org.opencps.dossiermgt.action.impl.DynamicReportActionsImpl;
import org.opencps.dossiermgt.action.impl.MenuConfigActionsImpl;
import org.opencps.dossiermgt.action.impl.PaymentConfigActionsImpl;
import org.opencps.dossiermgt.action.impl.ServiceConfigActionImpl;
import org.opencps.dossiermgt.action.impl.ServiceInfoActionsImpl;
import org.opencps.dossiermgt.action.impl.ServiceProcessActionsImpl;
import org.opencps.dossiermgt.action.impl.StepConfigActionsImpl;
import org.opencps.dossiermgt.service.DeliverableTypeLocalServiceUtil;
import org.opencps.usermgt.action.ApplicantActions;
import org.opencps.usermgt.action.EmployeeInterface;
import org.opencps.usermgt.action.JobposInterface;
import org.opencps.usermgt.action.impl.ApplicantActionsImpl;
import org.opencps.usermgt.action.impl.EmployeeActions;
import org.opencps.usermgt.action.impl.JobposActions;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;
import org.opencps.usermgt.service.JobPosLocalServiceUtil;

public class ProcessUpdateDBUtils {

	private static Log _log = LogFactoryUtil.getLog(ProcessUpdateDBUtils.class);
	//LamTV_Update ActionConfig to DB
	public static boolean processUpdateActionConfig(ActionConfigList actList, String folderPath, long groupId,
			long userId, ServiceContext serviceContext) {
		try {
			ActionConfigActions actions = new ActionConfigActionsImpl();
			//Create table ActionConfig
			List<ActionConfig> actConfigList = actList.getActionConfig();
			if (actConfigList != null && actConfigList.size() > 0) {
				for (ActionConfig actConfig : actConfigList) {
					String actionCode = actConfig.getActionCode();
					String actionName = actConfig.getActionName();
					Boolean extraForm = actConfig.isExtraForm();
					String formConfig = actConfig.getFormConfig();
					String sampleData = actConfig.getSampleData();
					Boolean insideProcess = actConfig.isInsideProcess();
					Integer userNote = actConfig.getUserNote();
					Integer syncType = actConfig.getSyncType();
					Integer eventType = actConfig.getEventType();
					Integer infoType = actConfig.getInfoType();
					Boolean rollbackable = actConfig.isRollbackable();
					String notificationType = actConfig.getNotificationType();
					String documentType = actConfig.getDocumentType();
					String mappingAction = actConfig.getMappingAction();
					Integer dateOption = actConfig.getDateOption();
					if (dateOption == null) {
						dateOption = 0;
					}

					if (Validator.isNotNull(actionCode)) {
						// Check record exits DB
						actions.updateActionConfigDB(userId, groupId, actionCode, actionName, extraForm, sampleData,
								insideProcess, userNote, syncType, eventType, infoType, rollbackable,
								notificationType, documentType, formConfig, mappingAction, dateOption);
					}
				}
			}
		} catch (Exception e) {
			_log.error(e);
			return false;
		}
		return true;
	}

	//LamTV_Update StepConfig to DB
	public static boolean processUpdateStepConfig(StepConfigList stepList, long groupId,
			long userId, ServiceContext serviceContext) {

		try {
			StepConfigActions actions = new StepConfigActionsImpl();
			List<StepConfig> stepConfigList = stepList.getStepConfig();
			if (stepConfigList != null && stepConfigList.size() > 0) {
				for (StepConfig stepConfig : stepConfigList) {
					String stepCode = stepConfig.getStepCode();
					String stepName = stepConfig.getStepName();
					Integer stepType = stepConfig.getStepType();
					String dossierStatus = stepConfig.getDossierStatus();
					String dossierSubStatus = stepConfig.getDossierSubStatus();
					String menuGroup = stepConfig.getMenuGroup();
					String menuStepName = stepConfig.getMenuStepName();
					String buttonConfig = stepConfig.getButtonConfig();
					if (Validator.isNotNull(stepCode)) {
						// Check record exits DB
						actions.updateStepConfigDB(userId, groupId, stepCode, stepName, stepType, dossierStatus, dossierSubStatus,
								menuGroup, menuStepName, buttonConfig);
					}
				}
			}
		} catch (Exception e) {
			_log.error(e);
			return false;
		}
		return true;
	}

	//LamTV_Update MenuConfig to DB
	public static boolean processUpdateMenuConfig(MenuConfigList menuList, long groupId,
			long userId, ServiceContext serviceContext) {

		try {
			MenuConfigActions actions = new MenuConfigActionsImpl();
			//Delete all table StepConfig
			 if (menuList != null) {
				List<MenuConfig> menuConfigList = menuList.getMenuConfig();
				if (menuConfigList != null && menuConfigList.size() > 0) {
					for (MenuConfig menuConfig : menuConfigList) {
						String menuGroup = menuConfig.getMenuGroup();
						String menuName = menuConfig.getMenuName();
						Integer order = menuConfig.getOrder();
						Integer menuType = menuConfig.getMenuType();
						String queryParams = menuConfig.getQueryParams();
						String tableConfig = menuConfig.getTableConfig();
						String buttonConfig = menuConfig.getButtonConfig();
						String icon = menuConfig.getIcon();
						String roles = menuConfig.getRoles();
						if (Validator.isNotNull(menuGroup)) {
							// Check record exits DB
							long menuConfigId = actions.updateMenuConfigDB(userId, groupId, menuGroup, menuName, order, menuType, queryParams,
									tableConfig, buttonConfig, icon);
							if (menuConfigId > 0) {
								actions.updateMenuRoles(groupId, menuConfigId, roles);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			_log.error(e);
			return false;
		}
		return true;
	}

	//LamTV_Update DocumentType to DB
	public static boolean processUpdateDocumentType(DocumentTypeList docList, String folderPath, long groupId,
			long userId, ServiceContext serviceContext) {

		try {
			if (docList != null) {
				List<DocumentType> docTypeList = docList.getDocumentType();
				if (docTypeList != null && docTypeList.size() > 0) {
					for (DocumentType docType : docTypeList) {
						String typeCode = docType.getTypeCode();
						String documentName = docType.getDocumentName();
						String codePattern = docType.getCodePattern();
						Integer docSync = docType.getDocSync();
						if (Validator.isNotNull(typeCode)) {
							String filePath = folderPath + ConstantUtils.SOURCE_REPORTS + StringPool.FORWARD_SLASH
									+ typeCode + ConstantUtils.EXTENTION_XML;
							File xmlfile = new File(filePath);
							String documentScript = StringPool.BLANK;
							if (xmlfile.exists() && !xmlfile.isDirectory()) {
								documentScript = ReadXMLFileUtils.convertFiletoString(xmlfile);
							}
							// Check record exits DB
							DocumentTypeActions actions = new DocumentTypeActionsImpl();
							actions.updateDocumentTypeDB(userId, groupId, typeCode, 0, documentName, codePattern, docSync,
									documentScript);
						}
					}
				}
			}
		} catch (Exception e) {
			_log.error(e);
			return false;
		}
		return true;
	}

	//LamTV_Update DeliverableType to DB
	public static boolean processUpdateDeliverableType(DeliverableTypeList deliTypeList, String folderPath, long groupId,
			long userId, ServiceContext serviceContext) {

		try {
			if (deliTypeList != null) {
				List<DeliverableType> deliverableTypeList = deliTypeList.getDeliverableType();
				if (deliverableTypeList != null && deliverableTypeList.size() > 0) {
					for (DeliverableType deliType : deliverableTypeList) {
						String typeCode = deliType.getTypeCode();
						String typeName = deliType.getTypeName();
						String codePattern = deliType.getCodePattern();
						Integer docSync = deliType.getDocSync();
						String mappingData = deliType.getMappingData();
						String dataConfig = deliType.getDataConfig();
						String tableConfig = deliType.getTableConfig();
						String govAgencies = deliType.getGovAgencies();
						if (Validator.isNotNull(typeCode)) {
							String filePathReport = folderPath + ConstantUtils.SOURCE_REPORTS + StringPool.FORWARD_SLASH
									+ typeCode + ConstantUtils.EXTENTION_XML;
							String filePathForm = folderPath + ConstantUtils.SOURCE_FORMS + StringPool.FORWARD_SLASH
									+ typeCode + ConstantUtils.EXTENTION_JSON;
							File xmlFile = new File(filePathReport);
							File jsonFile = new File(filePathForm);
							String formScript = StringPool.BLANK;
							String formReport = StringPool.BLANK;
							if (xmlFile.exists() && !xmlFile.isDirectory()) {
								formReport = ReadXMLFileUtils.convertFiletoString(xmlFile);
							}
							if (jsonFile.exists() && !jsonFile.isDirectory()) {
								formScript = ReadXMLFileUtils.convertFiletoString(jsonFile);
							}
							
							// Process file Report
							FileEntry fileEntryReport = FileUploadUtils.uploadDossierFile(userId, groupId, xmlFile, 
									UUID.randomUUID() + "_" + xmlFile.getName(), serviceContext);
							
							FileEntry fileEntryScript = FileUploadUtils.uploadDossierFile(userId, groupId, jsonFile, 
									UUID.randomUUID() + "_" + jsonFile.getName(), serviceContext);

							// Check record exits DB
							DeliverableTypesActions actions = new DeliverableTypesActionsImpl();
							org.opencps.dossiermgt.model.DeliverableType deliverableType = actions
									.updateDeliverableTypeDB(userId, groupId, typeCode, typeName, codePattern, docSync,
											mappingData, govAgencies, formReport, formScript, dataConfig, tableConfig,
											fileEntryReport != null ? fileEntryReport.getFileEntryId() : 0,
											fileEntryScript != null ? fileEntryScript.getFileEntryId() : 0);
							if (deliverableType != null) {
								DeliverableTypeRoleList roleList = deliType.getDeliverableTypeRoleList();
								if (roleList != null) {
									List<DeliverableTypeRole> deliRoleList = roleList.getDeliverableTypeRole();
									if (deliRoleList != null && deliRoleList.size() > 0) {
										for (DeliverableTypeRole roleType : deliRoleList) {
											String roleCode = roleType.getRoleCode();
											String moderator = roleType.getModerator();
											boolean moderatorBool = Validator.isNotNull(moderator) ? Boolean.valueOf(moderator) : false;
											if (Validator.isNotNull(roleCode)) {
												org.opencps.usermgt.model.JobPos jobPos = JobPosLocalServiceUtil
														.getByJobCode(groupId, roleCode);
												if (jobPos != null) {
													actions.updateDeliverableTypeRoleDB(userId, groupId,
															deliverableType.getDeliverableTypeId(),
															jobPos.getMappingRoleId(), moderatorBool);
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			_log.error(e);
			return false;
		}
		return true;
	}

	//LamTV_Update PaymentConfig to DB
	public static boolean processUpdatePaymentConfig(PaymentConfigList paymentList, long groupId, long userId,
			ServiceContext serviceContext) {

		try {
			//Update table PaymentConfig
			if (paymentList != null) {
				PaymentConfigActions actions = new PaymentConfigActionsImpl();
				//
				List<PaymentConfig> paymentConfigList = paymentList.getPaymentConfig();
				if (paymentConfigList != null && paymentConfigList.size() > 0) {
					String govAgencyCode;
					String govAgencyName;
					String govAgencyTaxNo;
					String invoiceTemplateNo;
					String invoiceIssueNo;
					String invoiceLastNo;
					String bankInfo;
					String epaymentConfig;
					for (PaymentConfig paymentConfig : paymentConfigList) {
						govAgencyCode = paymentConfig.getGovAgencyCode();
						govAgencyName = paymentConfig.getGovAgencyName();
						govAgencyTaxNo = paymentConfig.getGovAgencyTaxNo();
						invoiceTemplateNo = paymentConfig.getInvoiceTemplateNo();
						invoiceIssueNo = paymentConfig.getInvoiceIssueNo();
						invoiceLastNo = paymentConfig.getInvoiceLastNo();
						bankInfo = paymentConfig.getBankInfo();
						epaymentConfig = paymentConfig.getEpaymentConfig();
						if (Validator.isNotNull(govAgencyCode)) {
							// Check record exits DB
							actions.updatePaymentConfigDB(userId, groupId, govAgencyCode, govAgencyName, govAgencyTaxNo, invoiceTemplateNo, invoiceIssueNo,
									invoiceLastNo, bankInfo, epaymentConfig, serviceContext);
						}
					}
				}
			}
		} catch (Exception e) {
			_log.error(e);
			return false;
		}
		return true;
	}

	//LamTV_Update ServerConfig to DB
	public static boolean processUpdateServerConfig(ServerConfigList serverList, long groupId, long userId,
			ServiceContext serviceContext) {

		try {
			//Delete all table ServerConfig
//			List<org.opencps.communication.model.ServerConfig> configList = ServerConfigLocalServiceUtil
//					.getGroupId(groupId);
//			if (configList != null && configList.size() > 0) {
//				ServerConfigLocalServiceUtil.deleteByGroupId(groupId, userId, serviceContext);
//			}
			//Update table ServerConfig
			if (serverList != null) {
				List<ServerConfig> serverConfigList = serverList.getServerConfig();
				if (serverConfigList != null && serverConfigList.size() > 0) {
					String govAgencyCode;
					String serverNo;
					String serverName;
					String protocol;
					String configs;
					for (ServerConfig serverConfig : serverConfigList) {
						govAgencyCode = serverConfig.getGovAgencyCode();
						serverNo = serverConfig.getServerNo();
						serverName = serverConfig.getServerName();
						protocol = serverConfig.getProtocol();
						configs = serverConfig.getConfigs();
						if (Validator.isNotNull(serverNo)) {
							// Check record exits DB
							ServerConfigLocalServiceUtil.updateServerConfigDB(groupId, govAgencyCode, serverNo,
									serverName, protocol, configs, null, serviceContext);
						}
					}
				}
			}
		} catch (Exception e) {
			_log.error(e);
			return false;
		}
		return true;
	}

	//LamTV_Update NotificationTemplate to DB
	public static boolean processUpdateNotificationTemplate(NotificationTemplateList notiTempList, long groupId,
			long userId, ServiceContext serviceContext) {

		try {
			//Delete all table NotificationTemplate
			//boolean flagTemp = actions.deleteAllNotificationTemplate(groupId, userId, serviceContext);
			//Update table NotificationTemplate
			if (notiTempList != null) {
				NotificationTemplateInterface actions = new NotificationTemplateActions();
				//
				List<NotificationTemplate> notiTemplateList = notiTempList.getNotificationTemplate();
				if (notiTemplateList != null && notiTemplateList.size() > 0) {
					String notificationType;
					Boolean sendEmail = false;
					String emailSubject;
					String emailBody;
					String textMessage;
					Boolean sendSMS = false;
					Integer expireDuration = 0;
					String interval;
					for (NotificationTemplate notiTemplate : notiTemplateList) {
						notificationType = notiTemplate.getNotificationType();
						sendEmail = notiTemplate.isSendEmail();
						emailSubject = notiTemplate.getEmailSubject();
						emailBody = notiTemplate.getEmailBody();
						textMessage = notiTemplate.getTextMessage();
						sendSMS = notiTemplate.isSendSMS();
						expireDuration = notiTemplate.getExpireDuration();
						interval = notiTemplate.getInterval();
						if (Validator.isNotNull(notificationType)) {
							// Check record exits DB
							actions.updateNotificationTemplateDB(userId, groupId, notificationType, sendEmail, emailSubject, emailBody, textMessage,
									sendSMS, expireDuration, interval, serviceContext);
						}
					}
				}
			}
		} catch (Exception e) {
			_log.error(e);
			return false;
		}
		return true;
	}

	//LamTV_Update UserManagement to DB
	public static boolean processUpdateUser(UserManagement userManagement, long groupId, long userId,
			ServiceContext serviceContext) {
		boolean flagUser = true;
		try {
//			//Delete all table NotificationTemplate
//			boolean flagUser = actions.deleteAllNotificationTemplate(groupId, userId, serviceContext);
//			//Update table NotificationTemplate
			if (userManagement != null) {
				org.opencps.api.v21.model.UserManagement.Roles roles = userManagement.getRoles();
				if (roles != null) {
					flagUser = processUpdateJobPos(userId, groupId, roles, serviceContext);
					if (!flagUser) {
						return flagUser;
					}
				}
				org.opencps.api.v21.model.UserManagement.Users users = userManagement.getUsers();
				if (users != null) {
					_log.info("Process Employee");
					flagUser = processUpdateEmployee(userId, groupId, users, serviceContext);
				}
			}
		} catch (Exception e) {
			_log.error(e);
			return false;
		}
		return flagUser;
	}

	private static boolean processUpdateEmployee(long userId, long groupId,
			org.opencps.api.v21.model.UserManagement.Users users, ServiceContext serviceContext) {
		try {
			List<Employee> employeeList = users.getEmployee();
			if (employeeList != null && employeeList.size() > 0) {
				_log.info("employeeList size: "+employeeList.size());
				EmployeeInterface actionEmployee = new EmployeeActions();
				String employeeNo;
				String fullname;
				String title;
				Integer gender = 0;
				String birthdate;
				String telNo;
				String email;
				Integer workingStatus = 1;
				String jobTitle;
				String roles;
				for (Employee employee : employeeList) {
					employeeNo = employee.getEmployeeNo();
					fullname = employee.getFullname();
					title = employee.getTitle();
					gender = employee.getGender();
					birthdate = employee.getBirthdate();
					telNo = employee.getTelNo();
					email = employee.getEmail();
					workingStatus = employee.getWorkingStatus();
					jobTitle = employee.getJobTitle();
					roles = employee.getRoles();
					if (Validator.isNotNull(employeeNo)) {
						_log.info("employeeNo: "+employeeNo);
						// Check record exits DB
						actionEmployee.updateEmployeeDB(userId, groupId, employeeNo, fullname, title, gender, birthdate,
								telNo, email, workingStatus, jobTitle, roles, serviceContext);
					}
				}
			}
		} catch (Exception e) {
			_log.error(e);
			return false;
		}
		return true;
	}

	private static boolean processUpdateJobPos(long userId, long groupId,
			org.opencps.api.v21.model.UserManagement.Roles roles, ServiceContext serviceContext) {

		try {
			List<JobPos> jobPosList = roles.getJobPos();
			if (jobPosList != null && jobPosList.size() > 0) {
				JobposInterface actionJob = new JobposActions();
				String jobCode;
				String title;
				String description;
				for (JobPos jobPos : jobPosList) {
					jobCode = jobPos.getCode();
					title = jobPos.getTitle();
					description = jobPos.getDescription();
					if (Validator.isNotNull(jobCode)) {
						// Check record exits DB
						actionJob.updateJobPosDB(userId, groupId, jobCode, title, description, serviceContext);
					}
				}
			}
		} catch (Exception e) {
			_log.error(e);
			return false;
		}
		return true;

	}

	//LamTV_Update DynamicReport to DB
	public static boolean processUpdateDynamicReport(DynamicReportList reportList, String folderPath, long groupId,
			long userId, ServiceContext serviceContext) {
		try {
			DynamicReportActions actions = new DynamicReportActionsImpl();
			//Create table ActionConfig
			List<DynamicReport> dynamicReportList = reportList.getDynamicReport();
			if (dynamicReportList != null && dynamicReportList.size() > 0) {
				for (DynamicReport report : dynamicReportList) {
					String reportCode = report.getReportCode();
					String reportName = report.getReportName();
					Integer sharing = report.getSharing();
					if (sharing == null) {
						sharing = 1;
					}
					String filterConfig = report.getFilterConfig();
					String tableConfig = report.getTableConfig();
					String userConfig = report.getUserConfig();
					String reportType = report.getReportType();

					if (Validator.isNotNull(reportCode)) {
						// Check record exits DB
						actions.updateDynamicReportDB(userId, groupId, reportCode, reportName, sharing, filterConfig,
								tableConfig, userConfig, reportType);
					}
				}
			}
		} catch (Exception e) {
			_log.error(e);
			return false;
		}
		return true;
	}

	//LamTV_Update DynamicReport to DB
	@SuppressWarnings("unused")
	public static boolean processUpdateHoliday(HolidayList holidayList, String folderPath, long groupId,
			long userId, ServiceContext serviceContext) {
		try {
			HolidayInterface actions = new HolidayActions();
			//Create table ActionConfig
			List<Holiday> lstHoliday = holidayList.getHoliday();
			if (lstHoliday != null && lstHoliday.size() > 0) {
				for (Holiday holiday : lstHoliday) {
					String strHolidayDate = holiday.getHolidayDate();
					String description = holiday.getDescription();
					Integer holidayType = holiday.getHolidayType();
					if (holidayType == null) {
						holidayType = 0;
					}

					if (Validator.isNotNull(strHolidayDate)) {
						Date holidayDate = APIDateTimeUtils.convertStringToDate(strHolidayDate,
								APIDateTimeUtils._NORMAL_DATE);
						if (holidayDate != null) {
							Calendar cal = Calendar.getInstance();
							cal.setTime(holidayDate);
							cal.set(Calendar.HOUR_OF_DAY, 0);
							cal.set(Calendar.MINUTE, 0);
							cal.set(Calendar.SECOND, 0);
							// Check record exits DB
							actions.updateHolidayDB(userId, groupId, cal.getTime(), description, holidayType);
						}
					}
				}
			}
		} catch (Exception e) {
			_log.error(e);
			return false;
		}
		return true;
	}

	//LamTV_Update WorkingTime to DB
	public static boolean processUpdateWorkingTime(WorkingTimeList workingTimeList, String folderPath, long groupId,
			long userId, ServiceContext serviceContext) {
		try {
			WorkTimeInterface actions = new WorkTimeActions();
			//Create table ActionConfig
			List<WorkingTime> workTimeList = workingTimeList.getWorkingTime();
			if (workTimeList != null && workTimeList.size() > 0) {
				for (WorkingTime workTime : workTimeList) {
					int workTimeDay = workTime.getWorkTimeDay();
					String workTimeHours = workTime.getWorkTimeHours();

					if (Validator.isNotNull(workTimeDay) && workTimeDay > 0 && Validator.isNotNull(workTimeHours)) {
						// Check record exits DB
						actions.updateWorkTimeDB(userId, groupId, workTimeDay, workTimeHours);
					}
				}
			}
		} catch (Exception e) {
			_log.error(e);
			return false;
		}
		return true;
	}

	//LamTV_Update DynamicReport to DB
	public static boolean processUpdateApplicant(ApplicantList applicantList, String folderPath, long groupId,
			long userId, ServiceContext serviceContext) {
		try {
			ApplicantActions actions = new ApplicantActionsImpl();
			//Create applicant
			List<Applicant> appList = applicantList.getApplicant();
			if (appList != null && appList.size() > 0) {
				for (Applicant applicant : appList) {
					String applicantIdNo = applicant.getApplicantIdNo();
					String appliantName = applicant.getApplicantName();
					String applicantIdType = applicant.getApplicantIdType();
					String strApplicantIdDate = applicant.getApplicantIdDate();
					Date applicantIdDate = null;
					if (Validator.isNotNull(applicantIdDate)) {
						applicantIdDate = APIDateTimeUtils.convertStringToDate(strApplicantIdDate,
								APIDateTimeUtils._NORMAL_DATE);
					}
					String contactEmail = applicant.getContactEmail();
					String contactTelNo = applicant.getContactTelNo();

					if (Validator.isNotNull(applicantIdNo)) {
						// Check record exits DB
						actions.updateApplicantDB(userId, groupId, applicantIdNo, appliantName, applicantIdType,
								applicantIdDate, contactEmail, contactTelNo, serviceContext);
					}
				}
			}
		} catch (Exception e) {
			_log.error(e);
			return false;
		}
		return true;
	}

	//LamTV_Update Dictcollection to DB
	public static boolean processUpdateDictCollection(DictCollection dicts, long groupId, long userId, ServiceContext serviceContext) {

		boolean flagDict = true;
		try {
			if (dicts != null) {
				String collectionCode = dicts.getCollectionCode();
				_log.info("collectionCode: "+collectionCode);
				String collectionName = dicts.getCollectionName();
				String collectionNameEN = dicts.getCollectionNameEN();
				String description = dicts.getDescription();
				Integer status = dicts.getStatus();
				DictcollectionInterface actionCollection = new DictCollectionActions();
				long dictCollectionId = actionCollection.updateDictCollectionDB(userId, groupId, collectionCode,
						collectionName, collectionNameEN, description, status);
				if (dictCollectionId > 0) {
					flagDict = processUpdateDictItem(userId, groupId, dictCollectionId, dicts, actionCollection);
					if (!flagDict) {
						return flagDict;
					}
					processUpdateDictGroup(userId, groupId, dictCollectionId, dicts, actionCollection, serviceContext);
				}
			}
		} catch (Exception e) {
			_log.error(e);
			return false;
		}
		return true;
	}

	//LamTV_ Process service to DB
	public static boolean processUpdateServiceInfo(ServiceInfo service, String folderPath, String folderParentPath,
			long groupId, long userId, ServiceContext serviceContext) {

		boolean flagService = true;
		try {
			if (service != null) {
				String serviceCode = service.getServiceCode();
				String serviceName = service.getServiceName();
				String processText = service.getProcessText();
				String methodText = service.getMethodText();
				String dossierText = service.getDossierText();
				String conditionText = service.getConditionText();
				String durationText = service.getDurationText();
				String applicantText = service.getApplicantText();
				String resultText = service.getResultText();
				String regularText = service.getRegularText();
				String feeText = service.getFeeText();
				String administrationCode = service.getAdministrationCode();
				String administrationName = service.getAdministrationName();
				String domainCode = service.getDomainCode();
				String domainName = service.getDomainName();
				Integer maxLevel = service.getMaxLevel();
				boolean public_ = Validator.isNotNull(service.isPublic()) ? service.isPublic() : true;
				// Update serviceInfo
				ServiceInfoActions actionService = new ServiceInfoActionsImpl();
				long serviceInfoId = actionService.updateServiceInfoDB(userId, groupId, serviceCode, serviceName, processText, methodText,
						dossierText, conditionText, durationText, applicantText, resultText, regularText, feeText,
						administrationCode, administrationName, domainCode, domainName, maxLevel, public_);
				// Update fileName
				FileTemplates fileTemplate = service.getFileTemplates();
				if (fileTemplate != null) {
					flagService = processFileTemplate(userId, groupId, serviceInfoId, fileTemplate, folderParentPath,
							actionService, serviceContext);
					if (!flagService) {
						return flagService;
					}
				}
				// Add serviceConfig
				Configs configs = service.getConfigs();
				if (configs != null) {
					flagService = processServiceConfig(userId, groupId, serviceInfoId, configs, actionService, serviceContext);
				}
			}
		} catch (Exception e) {
			_log.error(e);
			return false;
		}
		return flagService;
	}

	//LamTV_Process service to DB
	public static boolean processUpdateDossierTemplate(DossierTemplate template, String folderPath, String folderParentPath, long groupId,
			long userId, ServiceContext serviceContext) {

		boolean flagTemp = true;
		try {
			if (template != null) {
				String templateNo = template.getTemplateNo();
				String templateName = template.getTemplateName();
				String description = template.getDescription();
				String newFormScript = template.getNewFormScript();
				
				// Update serviceInfo
				DossierTemplateActions actionTemp = new DossierTemplateActionsImpl();
				actionTemp.updateDossierTemplateDB(userId, groupId, templateNo, templateName, description, newFormScript, serviceContext);
				// Update fileName
				Parts parts = template.getParts();
				if (parts != null) {
					flagTemp = processDossierPart(userId, groupId, parts, actionTemp, folderParentPath, templateNo,
							serviceContext);
				}
			}
		} catch (Exception e) {
			_log.error(e);
			return false;
		}
		return flagTemp;
	}

	public static boolean processUpdateServiceProcess(ServiceProcess process, String folderPath, long groupId,
			long userId, ServiceContext serviceContext) {

		boolean flagProcess = true;
		try {
			if (process != null) {
				String processNo = process.getProcessNo();
				String processName = process.getProcessName();
				String description = process.getDescription();
//				Double durationCount = process.getDurationCount();
//				_log.info("durationCount: "+durationCount);
//				Double durationCountConvert = 0d;
//				if (Validator.isNotNull(durationCount)) {
//					String strDurationCount = String.valueOf(durationCount).replaceAll(StringPool.COMMA,
//							StringPool.PERIOD);
//					_log.info("strDurationCount: "+strDurationCount);
//					durationCountConvert = Double.valueOf(strDurationCount);
//					_log.info("durationCountConvert: "+durationCountConvert);
//				}
				String durationCount = process.getDurationCount();
				_log.info("durationCount: "+durationCount);
				Double durationCountConvert = 0d;
				if (Validator.isNotNull(durationCount)) {
					durationCount = durationCount.replaceAll(StringPool.COMMA, StringPool.PERIOD);
					_log.info("strDurationCount: "+durationCount);
					durationCountConvert = Double.valueOf(durationCount);
					_log.info("durationCountConvert: "+durationCountConvert);
				}
				Integer durationUnit = process.getDurationUnit();
				boolean generatePassword = process.isGeneratePassword();
				String serverNo = process.getServerNo();
				String serverName = process.getServerName();
				String dossierNoPattern = process.getDossierNoPattern();
				String dueDatePattern = process.getDueDatePattern();
				// Update serviceInfo
				ServiceProcessActions actionService = new ServiceProcessActionsImpl();
				long serviceProcessId = actionService.updateServiceProcessDB(userId, groupId, processNo, processName,
						description, durationCountConvert, durationUnit, generatePassword, serverNo, serverName,
						dossierNoPattern, dueDatePattern, serviceContext);
				//Delete all record ServiceFileTemplate with serviceInfoId
				Roles processRoles = process.getRoles();
				if (processRoles != null) {
					flagProcess = processProcessRole(userId, groupId, serviceProcessId, processRoles, actionService,
							serviceContext);
					if (!flagProcess) {
						return flagProcess;
					}
				}
				// Process step
				Steps steps = process.getSteps();
				if (steps != null) {
					flagProcess = processProcessStep(userId, groupId, serviceProcessId, steps, actionService,
							serviceContext);
					if (!flagProcess) {
						return flagProcess;
					}
				}
				// Process actions
				Actions actions = process.getActions();
				if (actions != null) {
					flagProcess = processProcessAction(userId, groupId, serviceProcessId, actions, actionService,
							serviceContext);
					if (!flagProcess) {
						return flagProcess;
					}
				}
				// Process processsequence
				Sequences sequences = process.getSequences();
				if (sequences != null) {
					flagProcess = processProcessSequence(userId, groupId, serviceProcessId, sequences, actionService, serviceContext);
				}
			}
		} catch (Exception e) {
			_log.error(e);
			return false;
		}
		return flagProcess;
	}

	//LamTV_ Process output ServiceFileTemplate to DB
	private static boolean processFileTemplate(long userId, long groupId, long serviceInfoId, FileTemplates fileTemplate,
			String folderParentPath, ServiceInfoActions actionService, ServiceContext serviceContext) {

		try {
			// Delete all ServiceFileTemplate with serviceInfoId
			boolean flagTemplate = actionService.deleteAllFileTemplate(userId, groupId, serviceInfoId, serviceContext);
			_log.info("flagTemplate: "+flagTemplate);
			// Add list file serviceFileTemplate
			List<FileTemplate> fileTempList = fileTemplate.getFileTemplate();
			if (fileTempList != null && fileTempList.size() > 0 && flagTemplate) {
				String fileTemplateNo;
				String fileTemplateName;
				String fileName;
				String eFormNoPattern;
				String eFormNamePattern;
				for (FileTemplate fileTemp : fileTempList) {
					fileTemplateNo = fileTemp.getFileTemplateNo();
					fileTemplateName = fileTemp.getTemplateName();
					fileName = fileTemp.getFilename();
					eFormNoPattern = fileTemp.getEFormNoPattern();
					eFormNamePattern = fileTemp.getEFormNamePattern();
					if (Validator.isNotNull(fileTemplateNo)) {
						String filePathTemplate = folderParentPath + ConstantUtils.SOURCE_FILES + StringPool.FORWARD_SLASH
								+ fileName;
						_log.info("filePathTemplate: "+filePathTemplate);
						File file = new File(filePathTemplate);
						FileEntry fileEntry = null;
						if (file.exists() && !file.isDirectory()) {
							fileEntry = FileUploadUtils.uploadDossierFile(userId, groupId, file, fileName, serviceContext);
						}
						//TODO
						String filePathReport = folderParentPath + ConstantUtils.SOURCE_FILE_REPORTS + StringPool.FORWARD_SLASH
								+ fileTemplateNo + ConstantUtils.EXTENTION_XML;
						String filePathForm = folderParentPath + ConstantUtils.SOURCE_FILE_FORMS + StringPool.FORWARD_SLASH
								+ fileTemplateNo + ConstantUtils.EXTENTION_JSON;

						File reportFile = new File(filePathReport);
						File scriptFile = new File(filePathForm);
						FileEntry fileEntryReport = null;
						FileEntry fileEntryScript = null;
						if (reportFile.exists() && !reportFile.isDirectory()) {
							fileEntryReport = FileUploadUtils.uploadDossierFile(userId, groupId, reportFile, reportFile.getName(), serviceContext);
						}
						if (scriptFile.exists() && !scriptFile.isDirectory()) {
							fileEntryScript = FileUploadUtils.uploadDossierFile(userId, groupId, scriptFile, scriptFile.getName(), serviceContext);
						}

						if (fileEntry != null) {
							long fileEntryId = fileEntry.getFileEntryId();
							if (fileEntryReport != null && fileEntryScript != null) {
								actionService.updateServiceFileTemplateDB(serviceInfoId, fileTemplateNo, fileTemplateName,
										fileName, fileEntryId, true, fileEntryScript.getFileEntryId(),
										fileEntryReport.getFileEntryId(), eFormNoPattern, eFormNamePattern);
							} else {
								actionService.updateServiceFileTemplateDB(serviceInfoId, fileTemplateNo, fileTemplateName,
										fileName, fileEntryId, false, 0, 0, eFormNoPattern, eFormNamePattern);
							}
							
						}
					}
				}
			}
		} catch (Exception e) {
			_log.error(e);
			return false;
		}
		return true;
	}

	//LamTV_Process output ServiceConfig to DB
	private static boolean processServiceConfig(long userId, long groupId, long serviceInfoId, Configs configs,
			ServiceInfoActions actionService, ServiceContext serviceContext) {

		boolean flagService = true;
		try {
			// Delete all ServiceFileTemplate with serviceInfoId
			boolean flagConfig = actionService.deleteAllServiceConfig(userId, groupId, serviceInfoId, serviceContext);
			// Add list file serviceFileTemplate
			List<ServiceConfig> configList = configs.getServiceConfig();
			if (configList != null && configList.size() > 0 && flagConfig) {
				String govAgencyCode;
				String govAgencyName;
				String serviceInstruction;
				Integer serviceLevel = 0;
				String serviceUrl;
				boolean forCitizen = false;
				boolean forBusiness = false;
				boolean postalService = false;
				boolean registration = false;
				for (ServiceConfig config : configList) {
					govAgencyCode = config.getGovAgencyCode();
					govAgencyName = config.getGovAgencyName();
					serviceInstruction = config.getServiceInstruction();
					serviceLevel = config.getServiceLevel();
					serviceUrl = config.getServiceUrl();
					forCitizen = config.isForCitizen();
					forBusiness = config.isForBusiness();
					postalService = config.isPostalService();
					registration = config.isRegistration();
					//
					ServiceConfigActions actionConfig = new ServiceConfigActionImpl();
					
					long serviceConfigId = actionConfig.updateServiceConfigDB(userId, groupId, serviceInfoId, govAgencyCode, govAgencyName,
							serviceInstruction, serviceLevel, serviceUrl, forCitizen, forBusiness, postalService, registration, serviceContext);
					// Process ProcessOption
					if (serviceConfigId > 0) {
						Processes process = config.getProcesses();
						if (process != null) {
							flagService = processProcessOption(userId, groupId, serviceConfigId, process, actionConfig,
									serviceContext);
							
						}
					}
				}
			}
		} catch (Exception e) {
			_log.error(e);
			return false;
		}
		return flagService;
	}

	//LamTV_Process output ProcessOption to DB
	private static boolean processProcessOption(long userId, long groupId, long serviceConfigId, Processes process,
			ServiceConfigActions actionConfig, ServiceContext serviceContext) {
		try {
			List<ProcessOption> optionList = process.getProcessOption();
			if (optionList != null && optionList.size() > 0) {
				_log.info("optionList: "+optionList.size());
				String optionCode;
				String optionName;
				Integer seqOrder = 0;
				String autoSelect;
				String instructionNote;
				String submissionNote;
				String templateNo;
				String templateName;
				String processNo;
				String processName;
				String registerBookCode;
				Integer sampleCount = 0;
				for (ProcessOption option : optionList) {
					optionCode = option.getOptionCode();
					optionName = option.getOptionName();
					seqOrder = option.getSeqOrder();
					autoSelect = option.getAutoSelect();
					instructionNote = option.getInstructionNote();
					submissionNote = option.getSubmissionNote();
					templateNo = option.getTemplateNo();
					templateName = option.getTemplateName();
					processNo = option.getProcessNo();
					processName = option.getProcessName();
					registerBookCode = option.getRegisterBookCode();
					sampleCount = option.getSampleCount();
					//
					actionConfig.updateOptionDB(userId, groupId, optionCode, optionName, serviceConfigId, seqOrder,
							autoSelect, instructionNote, submissionNote, templateNo, templateName, processNo, processName,
							registerBookCode, sampleCount, serviceContext);
				}
			}
		} catch (Exception e) {
			_log.error(e);
			return false;
		}
		return true;
	}

	//LamTV_Process DossierPart to DB
	private static boolean processDossierPart(long userId, long groupId, Parts parts, DossierTemplateActions actionTemp,
			String folderParentPath, String templateNo, ServiceContext serviceContext) throws PortalException {
		try {
			// Delete all ServiceFileTemplate with serviceInfoId
			boolean flagPart = actionTemp.deleteAllDossierPart(userId, groupId, templateNo, serviceContext);
			// Add list file serviceFileTemplate
			List<DossierPart> dossierPartList = parts.getDossierPart();
			if (dossierPartList != null && dossierPartList.size() > 0 && flagPart) {
				String partNo;
				String partName;
				String partTip;
				Integer partType = 0;
				boolean multiple = false;
				boolean required = false;
				boolean esign = false;
				String fileTemplateNo;
				String deliverableType;
				Integer deliverableAction = 0;
				boolean eForm = false;
				String sampleData;
				String formScript = StringPool.BLANK;
				String formReport = StringPool.BLANK;
				Integer fileMark = 0;
				for (DossierPart dossierPart : dossierPartList) {
					partNo = dossierPart.getPartNo();
					partName = dossierPart.getPartName();
					partTip = dossierPart.getPartTip();
					partType = dossierPart.getPartType();
					multiple = dossierPart.isMultiple();
					required = dossierPart.isRequired();
					esign = dossierPart.isEsign();
					fileTemplateNo = dossierPart.getFileTemplateNo();
					deliverableType = dossierPart.getDeliverableType();
					deliverableAction = dossierPart.getDeliverableAction();
					eForm = dossierPart.isEForm();
					sampleData = dossierPart.getSampleData();
					fileMark = dossierPart.getFileMark();
					//
					if (eForm) {
						_log.info("eform: "+eForm);
						String filePathReport = folderParentPath + ConstantUtils.SOURCE_REPORTS + StringPool.FORWARD_SLASH
								+ templateNo + StringPool.UNDERLINE + partNo + ConstantUtils.EXTENTION_XML;
						String filePathReportTemp = folderParentPath + ConstantUtils.SOURCE_REPORTS + StringPool.FORWARD_SLASH
								+ fileTemplateNo + ConstantUtils.EXTENTION_XML;
						String filePathForm = folderParentPath + ConstantUtils.SOURCE_FORMS + StringPool.FORWARD_SLASH
								+ templateNo + StringPool.UNDERLINE + partNo + ConstantUtils.EXTENTION_JSON;
						String filePathFormTemp = folderParentPath + ConstantUtils.SOURCE_FORMS + StringPool.FORWARD_SLASH
								+ fileTemplateNo + ConstantUtils.EXTENTION_JSON;
						File xmlFile = new File(filePathReport);
						File jsonFile = new File(filePathForm);
						if (xmlFile.exists() && !xmlFile.isDirectory()) {
							formReport = ReadXMLFileUtils.convertFiletoString(xmlFile);
						} else {
							File xmlFileTemp = new File(filePathReportTemp);
							if (xmlFileTemp.exists() && !xmlFileTemp.isDirectory()) {
								formReport = ReadXMLFileUtils.convertFiletoString(xmlFileTemp);
							}
						}
						if (jsonFile.exists() && !jsonFile.isDirectory()) {
							formScript = ReadXMLFileUtils.convertFiletoString(jsonFile);
						} else {
							File jsonFileTemp = new File(filePathFormTemp);
							if (jsonFileTemp.exists() && !jsonFileTemp.isDirectory()) {
								formScript = ReadXMLFileUtils.convertFiletoString(jsonFileTemp);
							}
						}
					} else {
						formScript = StringPool.BLANK;
						String filePathReport = folderParentPath + ConstantUtils.SOURCE_REPORTS + StringPool.FORWARD_SLASH
								+ templateNo + StringPool.UNDERLINE + partNo + ConstantUtils.EXTENTION_XML;
						String filePathReportTemp = folderParentPath + ConstantUtils.SOURCE_REPORTS + StringPool.FORWARD_SLASH
								+ fileTemplateNo + ConstantUtils.EXTENTION_XML;
						File xmlFile = new File(filePathReport);
						if (xmlFile.exists() && !xmlFile.isDirectory()) {
							formReport = ReadXMLFileUtils.convertFiletoString(xmlFile);
						} else {
							File xmlFileTemp = new File(filePathReportTemp);
							if (xmlFileTemp.exists() && !xmlFileTemp.isDirectory()) {
								formReport = ReadXMLFileUtils.convertFiletoString(xmlFileTemp);
							}
						}
					}
					//
					actionTemp.updateDossierPartDB(userId, groupId, templateNo, partNo, partName, partTip, partType,
							multiple, formScript, formReport, required, esign, fileTemplateNo, deliverableType,
							deliverableAction, eForm, sampleData, fileMark, serviceContext);
				}
			}
		} catch (Exception e) {
			_log.error(e);
			return false;
		}
		return true;
	}

	//LamTV_Process output ProcessAction to DB
	private static boolean processProcessAction(long userId, long groupId, long serviceProcessId, Actions actions,
			ServiceProcessActions actionService, ServiceContext serviceContext) throws PortalException {
		try {
			// Delete all ServiceFileTemplate with serviceInfoId
			boolean flagProAction = actionService.deleteAllProcessAction(userId, groupId, serviceProcessId, serviceContext);
			// Add list file serviceFileTemplate
			List<ProcessAction> processActionList = actions.getProcessAction();
			if (processActionList != null && processActionList.size() > 0 && flagProAction) {
				String actionCode;
				String actionName;
				String preStepCode;
				String postStepCode;
				String autoEvent;
				String preCondition;
				int allowAssignUser;
				long assignUserId = 0;
				String assignUserName;
				Integer requestPayment = 0;
				String paymentFee;
				String createDossierFiles;
				String returnDossierFiles;
				boolean eSignature = false;
				String signatureType;
				String createDossiers;
				for (ProcessAction processAction : processActionList) {
					actionCode = processAction.getActionCode();
					actionName = processAction.getActionName();
					preStepCode = processAction.getPreStepCode();
					postStepCode = processAction.getPostStepCode();
					autoEvent = processAction.getAutoEvent();
					preCondition = processAction.getPreCondition();
					allowAssignUser = processAction.getAllowAssignUser();
					assignUserId = processAction.getAssignUserId();
					assignUserName = processAction.getAssignUserName();
					requestPayment = processAction.getRequestPayment();
					paymentFee = processAction.getPaymentFee();
					createDossierFiles = processAction.getCreateDossierFiles();
					returnDossierFiles = processAction.getReturnDossierFiles();
					eSignature = processAction.isESignature();
					signatureType = processAction.getSignatureType();
					createDossiers = processAction.getCreateDossiers();
					//
					actionService.updateProcessActionDB(userId, groupId, serviceProcessId, actionCode,
							actionName, preStepCode, postStepCode, autoEvent, preCondition, allowAssignUser,
							assignUserId, assignUserName, requestPayment, paymentFee, createDossierFiles, returnDossierFiles,
							eSignature, signatureType, createDossiers, serviceContext);
				}
			}
		} catch (Exception e) {
			_log.error(e);
			return false;
		}
		return true;
	}

	//LamTV_Process output ProcessRole to DB
	private static boolean processProcessRole(long userId, long groupId, long serviceProcessId, Roles processRoles,
			ServiceProcessActions actionService, ServiceContext serviceContext) {
		try {
			// Delete all ServiceFileTemplate with serviceInfoId
			boolean flagProRole = actionService.deleteAllProcessRole(userId, groupId, serviceProcessId, serviceContext);
			// Add list file serviceFileTemplate
			List<ProcessRole> processRoleList = processRoles.getProcessRole();
			if (processRoleList != null && processRoleList.size() > 0 && flagProRole) {
				String roleCode;
				String roleName;
				boolean moderator = false;
				String condition;
				for (ProcessRole processRole : processRoleList) {
					roleCode = processRole.getRoleCode();
					roleName = processRole.getRoleName();
					moderator = processRole.isModerator();
					condition = processRole.getCondition();
					//
					if (Validator.isNotNull(roleCode)) {
						long roleId = actionService.getByRoleCode(groupId, roleCode);
						actionService.updateServiceProcessRoleDB(userId, groupId, serviceProcessId, roleId, roleCode, roleName,
								moderator, condition, serviceContext);
					}
				}
			}
		} catch (Exception e) {
			_log.error(e);
			return false;
		}
		return true;
	}

	//LamTV_ Process output ProcessStep to DB
	private static boolean processProcessStep(long userId, long groupId, long serviceProcessId, Steps steps,
			ServiceProcessActions actionService, ServiceContext serviceContext) throws PortalException {
		try {
			// Delete all ServiceFileTemplate with serviceInfoId
			boolean flagStep = actionService.deleteAllProcessStep(userId, groupId, serviceProcessId, serviceContext);
			_log.info("flagStep: "+flagStep);
			// Add list file serviceFileTemplate
			List<ProcessStep> proStepList = steps.getProcessStep();
			if (proStepList != null && proStepList.size() > 0 && flagStep) {
				String stepCode;
				String stepName;
				String sequenceNo;
				String groupName;
				String dossierStatus;
				String dossierSubStatus;
//				Double durationCount = 0d;
				String durationCount;
				String instructionNote;
				String briefNote;
				String roleAsStep;
				Integer checkInput = 0;
				for (ProcessStep step : proStepList) {
					stepCode = step.getStepCode();
					stepName = step.getStepName();
					sequenceNo = step.getSequenceNo();
					groupName = step.getGroupName();
					dossierStatus = step.getDossierStatus();
					dossierSubStatus = step.getDossierSubStatus();
//					durationCount = step.getDurationCount();
//					_log.info("durationCount: "+durationCount);
//					Double durationCountConvert = 0d;
//					if (Validator.isNotNull(durationCount)) {
//						String strDurationCount = String.valueOf(durationCount).replaceAll(StringPool.COMMA,
//								StringPool.PERIOD);
//						_log.info("strDurationCount: "+strDurationCount);
//						durationCountConvert = Double.valueOf(strDurationCount);
//						_log.info("durationCountConvert: "+durationCountConvert);
//					}
					durationCount = step.getDurationCount();
					_log.info("durationCount: "+durationCount);
					Double durationCountConvert = 0d;
					if (Validator.isNotNull(durationCount)) {
						durationCount = durationCount.replaceAll(StringPool.COMMA, StringPool.PERIOD);
						_log.info("strDurationCount: "+durationCount);
						durationCountConvert = Double.valueOf(durationCount);
						_log.info("durationCountConvert: "+durationCountConvert);
					}
					instructionNote = step.getInstructionNote();
					briefNote = step.getBriefNote();
					roleAsStep = step.getRoleAsStep();
					checkInput = step.getCheckInput();
					//
					long processStepId = actionService.updateProcessStepDB(userId, groupId, serviceProcessId, stepCode,
							stepName, sequenceNo, groupName, dossierStatus, dossierSubStatus, durationCountConvert,
							instructionNote, briefNote, roleAsStep, checkInput, serviceContext);
					//
					org.opencps.api.v21.model.Steps.ProcessStep.Roles stepRoles = step.getRoles();
					if (stepRoles != null) {
						processStepRole(userId, groupId, processStepId, stepRoles, actionService,
								serviceContext);
					}
				}
			}
		} catch (Exception e) {
			_log.error(e);
			return false;
		}
		return true;
	}

	//LamTV_ Process output ProcessSequence to DB
	private static boolean processProcessSequence(long userId, long groupId, long serviceProcessId, Sequences sequences,
			ServiceProcessActions actionService, ServiceContext serviceContext) throws PortalException {
		try {
			// Delete all ServiceFileTemplate with serviceInfoId
			boolean flagSequence = actionService.deleteAllProcessSequence(userId, groupId, serviceProcessId, serviceContext);
			_log.info("flagSequence"+flagSequence);
			// Add list file serviceFileTemplate
			List<ProcessSequence> sequenceList = sequences.getProcessSequence();
			if (sequenceList != null && sequenceList.size() > 0 && flagSequence) {
				String sequenceNo;
				String sequenceName;
				String sequenceRole;
				//Integer durationCount = 0;
				String durationCount;
				for (ProcessSequence sequence : sequenceList) {
					sequenceNo = sequence.getSequenceNo();
					sequenceName = sequence.getSequenceName();
					sequenceRole = sequence.getSequenceRole();
					durationCount = sequence.getDurationCount();
					_log.info("durationCount: "+durationCount);
					Double durationCountConvert = 0d;
					if (Validator.isNotNull(durationCount)) {
						durationCount = durationCount.replaceAll(StringPool.COMMA, StringPool.PERIOD);
						_log.info("strDurationCount: "+durationCount);
						durationCountConvert = Double.valueOf(durationCount);
						_log.info("durationCountConvert: "+durationCountConvert);
					}
					//
					actionService.updateProcessSequenceDB(userId, groupId, serviceProcessId, sequenceNo, sequenceName,
							sequenceRole, durationCountConvert, serviceContext);
				}
			}
		} catch (Exception e) {
			_log.error(e);
			return false;
		}
		return true;
	}

	//LamTV_ Process output ProcessStepRole to DB
	private static boolean processStepRole(long userId, long groupId, long processStepId,
			org.opencps.api.v21.model.Steps.ProcessStep.Roles stepRoles, ServiceProcessActions actionService,
			ServiceContext serviceContext) {
		try {
			List<StepRole> stepRoleList = stepRoles.getStepRole();
			if (stepRoleList != null && stepRoleList.size() > 0) {
				String roleCode;
				String roleName;
				boolean moderator = false;
				String condition;
				for (StepRole stepRole : stepRoleList) {
					roleCode = stepRole.getRoleCode();
					roleName = stepRole.getRoleName();
					moderator = stepRole.isModerator();
					condition = stepRole.getCondition();
					//
					if (Validator.isNotNull(roleCode)) {
						long roleId = actionService.getByRoleCode(groupId, roleCode);
						actionService.updateProcessStepRoleDB(userId, groupId, processStepId, roleId, roleCode, roleName,
								moderator, condition, serviceContext);
					}
				}
			}
		} catch (Exception e) {
			_log.error(e);
			return false;
		}
		return true;
	}

	//LamTV_ Process DictItem
	private static boolean processUpdateDictItem(long userId, long groupId, long dictCollectionId, DictCollection dicts,
			DictcollectionInterface actionCollection) throws NoSuchUserException {
		try {
			Items itemList = dicts.getItems();
			if (itemList != null) {
				// Delete all DictItem with dictCollectionId
				boolean flagItem = actionCollection.deleteAllDictItem(userId, groupId, dictCollectionId);
				_log.info("flagItem: "+flagItem);
				// Add list file serviceFileTemplate
				List<DictItem> dictItemList = itemList.getDictItem();
				if (dictItemList != null && dictItemList.size() > 0 && flagItem) {
					String itemCode;
					String itemName;
					String itemNameEN;
					String itemDescription;
					String parent;
					Integer level = 0;
					String sibling;
					String metadata;
					for (DictItem dictItem : dictItemList) {
						itemCode = dictItem.getItemCode();
						itemName = dictItem.getItemName();
						itemNameEN = dictItem.getItemNameEN();
						itemDescription = dictItem.getItemDescription();
						parent = dictItem.getParent();
						level = dictItem.getLevel();
						sibling = dictItem.getSibling();
						metadata = dictItem.getMetadata();
						//
						long dictItemParentId = actionCollection.getDictItemByItemCode(dictCollectionId, parent, groupId);
						actionCollection.updateDictItemDB(userId, groupId, dictCollectionId, itemCode, itemName, itemNameEN,
								itemDescription, dictItemParentId, level, sibling, metadata);
					}
				}
			}
		} catch (Exception e) {
			_log.error(e);
			return false;
		}
		return true;
	}

	//LamTV_ Process DictGroup
	private static boolean processUpdateDictGroup(long userId, long groupId, long dictCollectionId, DictCollection dicts,
			DictcollectionInterface actionCollection, ServiceContext serviceContext) throws NoSuchUserException {
		try {
			Groups groupList = dicts.getGroups();
			if (groupList != null) {
				// Delete all DictItem with dictCollectionId
				boolean flagGroup = actionCollection.deleteAllDictGroup(userId, groupId, dictCollectionId);
				// Add list file serviceFileTemplate
				List<DictGroup> dictGroupList = groupList.getDictGroup();
				if (dictGroupList != null && dictGroupList.size() > 0 && flagGroup) {
					String groupCode;
					String groupName;
					String groupNameEN;
					String groupDescription;
					for (DictGroup dictGroup : dictGroupList) {
						groupCode = dictGroup.getGroupCode();
						groupName = dictGroup.getGroupName();
						groupNameEN = dictGroup.getGroupNameEN();
						groupDescription = dictGroup.getGroupDescription();
						//
						actionCollection.updateDictGroupDB(userId, groupId, dictCollectionId, groupCode, groupName, groupNameEN,
								groupDescription, serviceContext);
					}
				}
			}
		} catch (Exception e) {
			_log.error(e);
			return false;
		}
		return true;
	}

}
