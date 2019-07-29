package org.opencps.api.controller.impl;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.opencps.api.backupdata.model.DataInputModel;
import org.opencps.api.constants.ConstantUtils;
import org.opencps.api.controller.BackupDataManagement;
import org.opencps.api.controller.util.ReadXMLFileUtils;
import org.opencps.api.v21.model.ActionConfigList;
import org.opencps.api.v21.model.Actions;
import org.opencps.api.v21.model.BusinessList;
import org.opencps.api.v21.model.BusinessList.Business;
import org.opencps.api.v21.model.CitizenList;
import org.opencps.api.v21.model.CitizenList.Citizen;
import org.opencps.api.v21.model.Configs;
import org.opencps.api.v21.model.FileTemplates;
import org.opencps.api.v21.model.FileTemplates.FileTemplate;
import org.opencps.api.v21.model.Items;
import org.opencps.api.v21.model.MenuConfigList;
import org.opencps.api.v21.model.Parts;
import org.opencps.api.v21.model.Processes;
import org.opencps.api.v21.model.Sequences;
import org.opencps.api.v21.model.Sequences.ProcessSequence;
import org.opencps.api.v21.model.ServiceProcess.Roles;
import org.opencps.api.v21.model.ServiceProcess.Roles.ProcessRole;
import org.opencps.api.v21.model.StepConfigList;
import org.opencps.api.v21.model.Steps;
import org.opencps.api.v21.model.Steps.ProcessStep.Roles.StepRole;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.opencps.dossiermgt.action.DossierFileActions;
import org.opencps.dossiermgt.action.impl.DossierFileActionsImpl;
import org.opencps.dossiermgt.action.util.AccentUtils;
import org.opencps.dossiermgt.model.ActionConfig;
import org.opencps.dossiermgt.model.DeliverableType;
import org.opencps.dossiermgt.model.DocumentType;
import org.opencps.dossiermgt.model.DossierPart;
import org.opencps.dossiermgt.model.DossierTemplate;
import org.opencps.dossiermgt.model.MenuConfig;
import org.opencps.dossiermgt.model.MenuRole;
import org.opencps.dossiermgt.model.ProcessAction;
import org.opencps.dossiermgt.model.ProcessOption;
import org.opencps.dossiermgt.model.ProcessStep;
import org.opencps.dossiermgt.model.ProcessStepRole;
import org.opencps.dossiermgt.model.ServiceConfig;
import org.opencps.dossiermgt.model.ServiceFileTemplate;
import org.opencps.dossiermgt.model.ServiceInfo;
import org.opencps.dossiermgt.model.ServiceProcess;
import org.opencps.dossiermgt.model.ServiceProcessRole;
import org.opencps.dossiermgt.model.StepConfig;
import org.opencps.dossiermgt.service.ActionConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.DeliverableTypeLocalServiceUtil;
import org.opencps.dossiermgt.service.DocumentTypeLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierPartLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierTemplateLocalServiceUtil;
import org.opencps.dossiermgt.service.MenuConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.MenuRoleLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessActionLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessOptionLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessSequenceLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessStepLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessStepRoleLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceFileTemplateLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceInfoLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceProcessLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceProcessRoleLocalServiceUtil;
import org.opencps.dossiermgt.service.StepConfigLocalServiceUtil;
import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.model.JobPos;
import org.opencps.usermgt.service.ApplicantLocalServiceUtil;
import org.opencps.usermgt.service.JobPosLocalServiceUtil;

import backend.auth.api.exception.BusinessExceptionImpl;

public class BackupDataManagementImpl implements BackupDataManagement{
	private static Log _log = LogFactoryUtil.getLog(BackupDataManagementImpl.class);

	@Override
	public Response exportDataToXML(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DataInputModel input) {
		
		_log.info("export DB to XML");
		BackendAuth auth = new BackendAuthImpl();
		backend.auth.api.BackendAuth auth2 = new backend.auth.api.BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		//long userId = user.getUserId();
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			if (!auth2.isAdmin(serviceContext, "admin")) {
				return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity("User not permission process!").build();
			}

			String dataCode = input.getDataCode();
			String dataType = input.getDataType();

			if (Validator.isNotNull(dataCode)) {
				/** Export citizen and business **/
				if (dataCode.equals(ConstantUtils.EXPORT_APPLICANT)) {
					if (ConstantUtils.EXPORT_CITIZEN.equals(dataType)) {
						List<Applicant> appList = ApplicantLocalServiceUtil.getApplicantByType(0l, dataType);
						if (appList != null && appList.size() > 0) {
							CitizenList citizenList = new CitizenList();
							for (Applicant applicant : appList) {
								Citizen citizen = new Citizen();
								if (Validator.isNotNull(applicant.getApplicantIdNo())
										&& applicant.getApplicantIdNo().length() < 12) {
									citizen.setLoaiThe(0);
								} else {
									citizen.setLoaiThe(1);
								}
								citizen.setSoCMND(applicant.getApplicantIdNo());
								citizen.setSoDinhDanh(1);
								citizen.setHoVaTen(applicant.getApplicantName());
								citizen.setThuDienTu(applicant.getContactEmail());
								citizen.setGioiTinh(0);
								citizen.setDanToc("Không rõ");
								citizen.setSoDienThoaiBan(StringPool.BLANK);
								citizen.setSoDienThoai(applicant.getContactTelNo());
								citizen.setTonGiao(StringPool.BLANK);
								citizen.setTinhTrangHonNhan(0);
								citizen.setNhomMau("00");
								citizen.setNgayThangNamSinh(StringPool.BLANK);
								citizen.setNoiDangKyKhaiSinh(StringPool.BLANK);
								citizen.setQueQuan(applicant.getCityName());
								citizen.setThuongTru(applicant.getCityName());
								String noiohientai = applicant.getAddress() + StringPool.DASH + applicant.getDistrictName() + StringPool.DASH +
										applicant.getCityName();
								citizen.setNoiOHienTai(noiohientai);
								citizen.setQuocTich("Viet Nam");
								citizen.setCha(StringPool.BLANK);
								citizen.setMe(StringPool.BLANK);
								citizen.setVoChong(StringPool.BLANK);
								citizen.setNguoiDaiDien(StringPool.BLANK);
								citizen.setChuHo(applicant.getApplicantName());
								citizen.setTrangThai(1);
								//
								citizenList.getCitizen().add(citizen);
							}
							//Method which uses JAXB to convert object to XML
							File file = ReadXMLFileUtils.convertCitizenToXML(citizenList);
							//
							ResponseBuilder responseBuilder = Response.ok((Object) file);

							responseBuilder.header("Content-Disposition",
									"attachment; filename=\"" + file.getName() + "\"");
							responseBuilder.header("Content-Type", "application/xml");

							return responseBuilder.build();
						}
					} else if(ConstantUtils.EXPORT_BUSINESS.equals(dataType)){
						List<Applicant> appList = ApplicantLocalServiceUtil.getApplicantByType(0l, dataType);
						if (appList != null && appList.size() > 0) {
							BusinessList businessList = new BusinessList();
							for (Applicant applicant : appList) {
								Business business = new Business();
								
								business.setMsdn(applicant.getApplicantIdNo());
								business.setEnterpriseId((int) applicant.getApplicantId());
								business.setEnterpriseCode(applicant.getApplicantIdNo());
								business.setEnterpriseGdtCode(applicant.getApplicantIdNo());
								business.setEnterpriseTypeId(0);
								business.setName(applicant.getApplicantName());
								business.setShortName(applicant.getApplicantName());
								business.setNameF(AccentUtils.removeAccent(applicant.getApplicantName()));
								business.setFoundingDate(APIDateTimeUtils.convertDateToString(
										applicant.getApplicantIdDate(), APIDateTimeUtils._NSW_PATTERN));
								business.setEnterpriseStatus(1);
								business.setLegalName(StringPool.BLANK);
								business.setSiteId(0);
								business.setSubunitParentEntId(0);
								business.setHOAddress(StringPool.BLANK);
								business.setBusinessActivity(StringPool.BLANK);
								business.setEmail(applicant.getContactEmail());
								business.setWebsite(StringPool.BLANK);
								business.setMobile(applicant.getContactTelNo());
								business.setPhone(StringPool.BLANK);
								business.setFax(StringPool.BLANK);
								//
								businessList.getBusiness().add(business);
							}
							//Method which uses JAXB to convert object to XML
							File file = ReadXMLFileUtils.convertBusinessToXML(businessList);
							//
							ResponseBuilder responseBuilder = Response.ok((Object) file);

							responseBuilder.header("Content-Disposition",
									"attachment; filename=\"" + file.getName() + "\"");
							responseBuilder.header("Content-Type", "application/xml");

							return responseBuilder.build();
						}
					}
				}

				/** Export dictCollection **/
				if (dataCode.equals(ConstantUtils.EXPORT_DICT_COLLECTION)) {
					_log.info("START EXPORT DICT====");
					org.opencps.api.v21.model.DictCollection dictCollection = new org.opencps.api.v21.model.DictCollection();
					if (Validator.isNotNull(dataType)) {
						DictCollection dict = null;
						if (ConstantUtils.EXPORT_ADMINISTRATIVE_REGION.equals(dataType)) {
							dict = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(dataType, 0l);
						} else {
							dict = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(dataType, groupId);
						}
						if (dict != null) {
							List<DictItem> dictItemList = DictItemLocalServiceUtil
									.findByF_dictCollectionId(dict.getDictCollectionId());
							if (dictItemList != null && dictItemList.size() > 0) {
								Items itemList = new Items();
								for (DictItem dictItem : dictItemList) {
									org.opencps.api.v21.model.Items.DictItem item = new org.opencps.api.v21.model.Items.DictItem();
									//
									item.setItemCode(dictItem.getItemCode());
									item.setItemName(dictItem.getItemName());
									item.setItemNameEN(dictItem.getItemNameEN());
									item.setItemDescription(dictItem.getItemDescription());
									DictItem itemParent = DictItemLocalServiceUtil.fetchDictItem(dictItem.getParentItemId());
									if (itemParent != null) {
										item.setParent(itemParent.getItemCode());
									}
									item.setLevel(dictItem.getLevel());
									item.setSibling(dictItem.getSibling());
									item.setMetadata(dictItem.getMetaData());
									//
									itemList.getDictItem().add(item);
								}
								//
								dictCollection.setItems(itemList);
								dictCollection.setCollectionCode(dataType);
								dictCollection.setCollectionName(dict.getCollectionName());
								dictCollection.setCollectionNameEN(dict.getCollectionNameEN());
								dictCollection.setDescription(dict.getDescription());
								dictCollection.setStatus(dict.getStatus());
							}
						}
					}

					_log.info("START EXPORT DICT====");
					//Method which uses JAXB to convert object to XML
					File file = ReadXMLFileUtils.convertDictCollectionToXML(dictCollection, dataType);
					//
					_log.info("file: "+file.getAbsolutePath());
					ResponseBuilder responseBuilder = Response.ok((Object) file);
					responseBuilder.header("Content-Disposition",
							"attachment; filename=\"" + file.getName() + "\"");
					responseBuilder.header("Content-Type", "application/xml");

					return responseBuilder.build();
				}

				/** Export ServiceInfo **/
				if (dataCode.equals(ConstantUtils.EXPORT_SERVICE_INFO)) {
					_log.info("START EXPORT DICT====");
					List<ServiceInfo> serviceList = ServiceInfoLocalServiceUtil.findByGroup(groupId);
					if (serviceList != null && serviceList.size() > 0) {
						String pathFolder = ConstantUtils.DEST_DIRECTORY_EXPORT + StringPool.FORWARD_SLASH + "services";
						File fileOld = new File(pathFolder);
						_log.info("fileOld: "+fileOld);
						if (fileOld.exists()) {
							boolean flag = ReadXMLFileUtils.deleteFilesForParentFolder(fileOld);
							_log.info("LamTV_Delete DONE: "+flag);
						}
						//Create new folder
						File newFolder = new File(pathFolder);
						if (!newFolder.exists()) {
							newFolder.mkdirs();
						}
						//
						DossierFileActions action = new DossierFileActionsImpl();
						
						for (ServiceInfo serviceInfo : serviceList) {
							org.opencps.api.v21.model.ServiceInfo serviceInfoExport = new org.opencps.api.v21.model.ServiceInfo();
							//
							serviceInfoExport.setServiceCode(serviceInfo.getServiceCode());
							serviceInfoExport.setServiceName(serviceInfo.getServiceName());
							serviceInfoExport.setProcessText(serviceInfo.getProcessText());
							serviceInfoExport.setMethodText(serviceInfo.getMethodText());
							serviceInfoExport.setDossierText(serviceInfo.getDossierText());
							serviceInfoExport.setConditionText(serviceInfo.getConditionText());
							serviceInfoExport.setDurationText(serviceInfo.getDurationText());
							serviceInfoExport.setApplicantText(serviceInfo.getApplicantText());
							serviceInfoExport.setResultText(serviceInfo.getResultText());
							serviceInfoExport.setRegularText(serviceInfo.getRegularText());
							serviceInfoExport.setFeeText(serviceInfo.getFeeText());
							serviceInfoExport.setAdministrationCode(serviceInfo.getAdministrationCode());
							serviceInfoExport.setAdministrationName(serviceInfo.getAdministrationName());
							serviceInfoExport.setDomainCode(serviceInfo.getDomainCode());
							serviceInfoExport.setDomainName(serviceInfo.getDomainName());
							serviceInfoExport.setMaxLevel(serviceInfo.getMaxLevel());
							// Process ServiceFileTemplate
							long serviceInfoId = serviceInfo.getServiceInfoId();
							if (serviceInfoId > 0) {
								List<ServiceFileTemplate> fileServiceList = ServiceFileTemplateLocalServiceUtil
										.getByServiceInfoId(serviceInfoId);
								if (fileServiceList != null && fileServiceList.size() > 0) {
									FileTemplates fileTempList = new FileTemplates();
									for (ServiceFileTemplate serviceFileTemplate : fileServiceList) {
										FileTemplate fileTemp = new FileTemplate();
										//
										fileTemp.setFileTemplateNo(serviceFileTemplate.getFileTemplateNo());
										fileTemp.setTemplateName(serviceFileTemplate.getTemplateName());
										//Get file name
										long fileEntryId = serviceFileTemplate.getFileEntryId();
										if (fileEntryId > 0) {
											DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.fetchDLFileEntry(fileEntryId);
											if (fileEntry != null) {
												fileTemp.setFilename(fileEntry.getFileName());
											}
										}
										//add FileTemplate in list
										fileTempList.getFileTemplate().add(fileTemp);
									}
									// add FileTemplates to serviceInfo
									serviceInfoExport.setFileTemplates(fileTempList);
								} else {
									FileTemplate fileTemp = new FileTemplate();
									fileTemp.setFileTemplateNo(StringPool.BLANK);
									fileTemp.setTemplateName(StringPool.BLANK);
									fileTemp.setFilename(StringPool.BLANK);
								}
								//
								List<ServiceConfig> serviceConfigList = ServiceConfigLocalServiceUtil
										.getByServiceInfo(groupId, serviceInfoId);
								if (serviceConfigList != null && serviceConfigList.size() > 0) {
									Configs configList = new Configs();
									for (ServiceConfig serviceConfig : serviceConfigList) {
										org.opencps.api.v21.model.Configs.ServiceConfig config = new org.opencps.api.v21.model.Configs.ServiceConfig();
										//
										config.setGovAgencyCode(serviceConfig.getGovAgencyCode());
										config.setGovAgencyName(serviceConfig.getGovAgencyName());
										config.setServiceInstruction(serviceConfig.getServiceInstruction());
										config.setServiceLevel(serviceConfig.getServiceLevel());
										config.setServiceUrl(serviceConfig.getServiceUrl());
										config.setForCitizen(serviceConfig.getForCitizen());
										config.setForBusiness(serviceConfig.getForBusiness());
										config.setPostalService(serviceConfig.getPostService());
										config.setRegistration(serviceConfig.getRegistration());
										//Get Process Option
										long serviceConfigId = serviceConfig.getServiceConfigId();
										if (serviceConfigId > 0) {
											List<ProcessOption> processOptionList = ProcessOptionLocalServiceUtil
													.getByServiceProcessId(serviceConfigId);
											if (processOptionList != null && processOptionList.size() > 0) {
												Processes processes = new Processes();
												for (ProcessOption processOption : processOptionList) {
													org.opencps.api.v21.model.Processes.ProcessOption option = new org.opencps.api.v21.model.Processes.ProcessOption();
													//
													option.setOptionName(processOption.getOptionName());
													option.setSeqOrder(processOption.getOptionOrder());
													option.setSampleCount((int) processOption.getSampleCount());
													option.setAutoSelect(processOption.getAutoSelect());
													option.setInstructionNote(processOption.getInstructionNote());
													option.setSubmissionNote(processOption.getSubmissionNote());
													//Get templateNo
													DossierTemplate template = DossierTemplateLocalServiceUtil.fetchDossierTemplate(processOption.getDossierTemplateId());
													if (template != null) {
														option.setTemplateNo(template.getTemplateNo());
														option.setTemplateName(template.getTemplateName());
													}
													// Get ServiceProcess
													ServiceProcess process = ServiceProcessLocalServiceUtil.fetchServiceProcess(processOption.getServiceProcessId());
													if (process != null) {
														option.setProcessNo(process.getProcessNo());
														option.setProcessName(process.getProcessName());
													}
													option.setRegisterBookCode(processOption.getRegisterBookCode());
													// Add option to list
													processes.getProcessOption().add(option);
												}
												// Add list option to serviceConfig
												config.setProcesses(processes);
											}
										}
										//add serviceConfig in list
										configList.getServiceConfig().add(config);
									}
									// add FileTemplates to serviceInfo
									serviceInfoExport.setConfigs(configList);
								}
							}
							// Create file in new folder
							ReadXMLFileUtils.convertServiceInfoToXML(serviceInfoExport,
									serviceInfo.getServiceCode(), newFolder.getAbsolutePath());
							//_log.info("newFolder: "+newFolder.getAbsolutePath());
//							_log.info("file: "+file.getPath());
//							String pathFileName ="/opt/phutho/tomcat-9.0.6/"+ pathFolder + "/" + file.getName();
//							_log.info("pathFileName: "+pathFileName);
//							File dir = new File(pathFolder);
//							if (!dir.exists()) {
//								dir.mkdirs();
//							}
//							action.copyFile(file.getPath(), pathFileName);
						}
						// zip file exported.zip
//						int endIndex = 0;
//						int midleIndex = 0;
//						if (Validator.isNotNull(newFolder.getAbsolutePath())) {
//							endIndex += newFolder.getAbsolutePath().lastIndexOf(StringPool.FORWARD_SLASH);
//							midleIndex += newFolder.getAbsolutePath().substring(0, endIndex).lastIndexOf(StringPool.FORWARD_SLASH);
//						}
//						String pathZip = StringPool.BLANK;
//						String nameZip = StringPool.BLANK;
//						if (endIndex > 0) {
//							pathZip = 
//									newFolder.getAbsolutePath().substring(0, endIndex);
//						}
//						if (midleIndex > 0) {
//							nameZip =
//									newFolder.getAbsolutePath().substring(midleIndex, endIndex);
//						}
						
						//_log.info("pathZip: "+ pathZip);
						action.zipDirectory(newFolder, pathFolder + ".zip");
						File fi = new File(pathFolder + ".zip");
						//Method which uses JAXB to convert object to XML
						//File file = ReadXMLFileUtils.convertDictCollectionToXML(dictCollection, dataType);
						//
						//_log.info("file: "+file.getAbsolutePath());
						ResponseBuilder responseBuilder = Response.ok((Object) fi);
						responseBuilder.header("Content-Disposition",
								"attachment; filename=\"" + fi.getName() + "\"");
						responseBuilder.header("Content-Type", "application/zip");

						return responseBuilder.build();
					}
				}
				/** Export ServiceInfo **/

				/** Export ActionConfig - START **/
				if (dataCode.equals(ConstantUtils.EXPORT_ACTION_CONFIG)) {
					_log.info("START EXPORT ActionConfig====");
					List<ActionConfig> actConfigList = ActionConfigLocalServiceUtil.getByGroupId(groupId);
					if (actConfigList != null && actConfigList.size() > 0) {
						ActionConfigList configList = new ActionConfigList();
						for (ActionConfig act : actConfigList) {
							org.opencps.api.v21.model.ActionConfigList.ActionConfig config = new org.opencps.api.v21.model.ActionConfigList.ActionConfig();
							if (Validator.isNotNull(act.getActionCode())) {
								config.setActionCode(act.getActionCode());
								config.setActionName(act.getActionName());
								config.setExtraForm(act.getExtraForm());
								config.setFormConfig(act.getFormConfig());
								config.setSampleData(act.getSampleData());
								config.setInsideProcess(act.getInsideProcess());
								config.setUserNote(act.getUserNote());
								config.setSyncType(act.getSyncType());
								config.setEventType(act.getEventType());
								config.setInfoType(act.getInfoType());
								config.setRollbackable(act.getRollbackable());
								config.setNotificationType(act.getNotificationType());
								config.setDocumentType(act.getDocumentType());
								config.setMappingAction(act.getMappingAction());
								config.setDateOption(act.getDateOption());
								//
								configList.getActionConfig().add(config);
							}
							//Method which uses JAXB to convert object to XML
							File file = ReadXMLFileUtils.convertActionConfigToXML(configList);
							//
							ResponseBuilder responseBuilder = Response.ok((Object) file);

							responseBuilder.header("Content-Disposition",
									"attachment; filename=\"" + file.getName() + "\"");
							responseBuilder.header("Content-Type", "application/xml");

							return responseBuilder.build();
						}
					}
				}
				/** Export ActionConfig - END **/

				/** Export StepConfig - START **/
				if (dataCode.equals(ConstantUtils.EXPORT_STEP_CONFIG)) {
					_log.info("START EXPORT StepConfig====");
					List<StepConfig> stepConfigList = StepConfigLocalServiceUtil.getStepByGroupId(groupId);
					if (stepConfigList != null && stepConfigList.size() > 0) {
						StepConfigList configList = new StepConfigList();
						for (StepConfig step : stepConfigList) {
							org.opencps.api.v21.model.StepConfigList.StepConfig config = new org.opencps.api.v21.model.StepConfigList.StepConfig();
							if (Validator.isNotNull(step.getStepCode())) {
								config.setStepCode(step.getStepCode());
								config.setStepName(step.getStepName());
								config.setStepType(step.getStepType());
								config.setDossierStatus(step.getDossierStatus());
								config.setDossierSubStatus(step.getDossierSubStatus());
								config.setMenuGroup(step.getMenuGroup());
								config.setMenuStepName(step.getMenuStepName());
								config.setButtonConfig(step.getButtonConfig());
								//
								configList.getStepConfig().add(config);
							}
							//Method which uses JAXB to convert object to XML
							File file = ReadXMLFileUtils.convertStepConfigToXML(configList);
							//
							ResponseBuilder responseBuilder = Response.ok((Object) file);

							responseBuilder.header("Content-Disposition",
									"attachment; filename=\"" + file.getName() + "\"");
							responseBuilder.header("Content-Type", "application/xml");

							return responseBuilder.build();
						}
					}
				}
				/** Export StepConfig - END **/

				/** Export MenuConfig - START **/
				if (dataCode.equals(ConstantUtils.EXPORT_MENU_CONFIG)) {
					_log.info("START EXPORT MenuConfig====");
					List<MenuConfig> menuConfigList = MenuConfigLocalServiceUtil.getByGroupId(groupId);
					if (menuConfigList != null && menuConfigList.size() > 0) {
						MenuConfigList configList = new MenuConfigList();
						for (MenuConfig menu : menuConfigList) {
							org.opencps.api.v21.model.MenuConfigList.MenuConfig config = new org.opencps.api.v21.model.MenuConfigList.MenuConfig();
							if (Validator.isNotNull(menu.getMenuGroup())) {
								config.setMenuGroup(menu.getMenuGroup());
								config.setMenuName(menu.getMenuName());
								config.setOrder(menu.getOrder());
								config.setMenuType(menu.getMenuType());
								config.setQueryParams(menu.getQueryParams());
								config.setTableConfig(menu.getTableConfig());
								config.setButtonConfig(menu.getButtonConfig());
								//
								long menuConfigId = menu.getMenuConfigId();
								if (menuConfigId > 0) {
									List<MenuRole> roleList = MenuRoleLocalServiceUtil.getByMenuConfig(menuConfigId);
									if (roleList != null && roleList.size() > 0) {
										long[] roleArr = new long[roleList.size()];
										for (int i = 0; i < roleList.size(); i++) {
											roleArr[i] = roleList.get(i).getRoleId();
										}
										//
										List<JobPos> jobPosList = JobPosLocalServiceUtil.findByF_mappingRoleIds(groupId, roleArr);
										StringBuilder sb = new StringBuilder();
										if (jobPosList != null && jobPosList.size() > 0) {
											for (int i = 0; i < jobPosList.size(); i++) {
												if (i == 0) {
													sb.append(jobPosList.get(i).getJobPosCode());
												} else {
													sb.append(StringPool.COMMA);
													sb.append(jobPosList.get(i).getJobPosCode());
												}
											}
										}
										config.setRoles(sb.toString());
									}
								}
								
								//
								configList.getMenuConfig().add(config);
							}
							//Method which uses JAXB to convert object to XML
							File file = ReadXMLFileUtils.convertMenuConfigToXML(configList);
							//
							ResponseBuilder responseBuilder = Response.ok((Object) file);

							responseBuilder.header("Content-Disposition",
									"attachment; filename=\"" + file.getName() + "\"");
							responseBuilder.header("Content-Type", "application/xml");

							return responseBuilder.build();
						}
					}
				}
				/** Export MenuConfig - END **/

			}
		} catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}

		return null;
	}

	@Override
	public Response backupMasterDataZip(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext) {
		_log.info("export DB to XML");
		BackendAuth auth = new BackendAuthImpl();
		backend.auth.api.BackendAuth auth2 = new backend.auth.api.BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		//long userId = user.getUserId();
		ZipOutputStream zos = null;
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			if (!auth2.isAdmin(serviceContext, "admin")) {
				return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity("User not permission process!").build();
			}

			ByteArrayOutputStream actionConfigXMLStream = ReadXMLFileUtils.exportActionConfigToXMLStream(groupId);
			ByteArrayOutputStream stepConfigXMLStream = ReadXMLFileUtils.exportStepConfigToXMLStream(groupId);
			ByteArrayOutputStream menuConfigXMLStream = ReadXMLFileUtils.exportMenuConfigToXMLStream(groupId);
			ByteArrayOutputStream documentTypeXMLStream = ReadXMLFileUtils.exportDocumentTypeToXMLStream(groupId);
			ByteArrayOutputStream deliverableTypeXMLStream = ReadXMLFileUtils.exportDeliverableTypeToXMLStream(groupId);
			ByteArrayOutputStream paymentConfigXMLStream = ReadXMLFileUtils.exportPaymentConfigToXMLStream(groupId);
			ByteArrayOutputStream serverConfigXMLStream = ReadXMLFileUtils.exportServerConfigToXMLStream(groupId);
			ByteArrayOutputStream notificationTemplateXMLStream = ReadXMLFileUtils.exportNotificationTemplateToXMLStream(groupId);
			ByteArrayOutputStream userManagementXMLStream = ReadXMLFileUtils.exportUserManagementToXMLStream(groupId);
			
			byte[] input = actionConfigXMLStream.toByteArray();
			
			File file = new File("backupmaster" + System.currentTimeMillis() + ".zip");
			
			FileOutputStream fos = new FileOutputStream(file);
		    zos = new ZipOutputStream(fos);
		    
		    ZipEntry entry = new ZipEntry("ActionConfig.xml");
		    entry.setSize(input.length);
		    zos.putNextEntry(entry);
		    zos.write(input);
		    zos.closeEntry();
		    actionConfigXMLStream.close();
		    
		    input = stepConfigXMLStream.toByteArray();
		    entry = new ZipEntry("StepConfig.xml");
		    entry.setSize(input.length);
		    zos.putNextEntry(entry);
		    zos.write(input);
		    zos.closeEntry();
		    stepConfigXMLStream.close();

		    input = menuConfigXMLStream.toByteArray();
		    entry = new ZipEntry("MenuConfig.xml");
		    entry.setSize(input.length);
		    zos.putNextEntry(entry);
		    zos.write(input);
		    zos.closeEntry();
		    menuConfigXMLStream.close();

		    input = documentTypeXMLStream.toByteArray();
		    entry = new ZipEntry("DocumentType.xml");
		    entry.setSize(input.length);
		    zos.putNextEntry(entry);
		    zos.write(input);
		    zos.closeEntry();
		    documentTypeXMLStream.close();
		    
		    input = deliverableTypeXMLStream.toByteArray();
		    entry = new ZipEntry("DeliverableType.xml");
		    entry.setSize(input.length);
		    zos.putNextEntry(entry);
		    zos.write(input);
		    zos.closeEntry();
		    deliverableTypeXMLStream.close();

		    input = paymentConfigXMLStream.toByteArray();
		    entry = new ZipEntry("PaymentConfig.xml");
		    entry.setSize(input.length);
		    zos.putNextEntry(entry);
		    zos.write(input);
		    zos.closeEntry();
		    paymentConfigXMLStream.close();

		    input = serverConfigXMLStream.toByteArray();
		    entry = new ZipEntry("ServerConfig.xml");
		    entry.setSize(input.length);
		    zos.putNextEntry(entry);
		    zos.write(input);
		    zos.closeEntry();
		    serverConfigXMLStream.close();

		    input = notificationTemplateXMLStream.toByteArray();
		    entry = new ZipEntry("NotificationTemplate.xml");
		    entry.setSize(input.length);
		    zos.putNextEntry(entry);
		    zos.write(input);
		    zos.closeEntry();
		    notificationTemplateXMLStream.close();

		    input = userManagementXMLStream.toByteArray();
		    entry = new ZipEntry("Users.xml");
		    entry.setSize(input.length);
		    zos.putNextEntry(entry);
		    zos.write(input);
		    zos.closeEntry();
		    userManagementXMLStream.close();

		    ZipEntry dicts = new ZipEntry("dicts/");
		    zos.putNextEntry(dicts);
		    List<DictCollection> lstCollections = DictCollectionLocalServiceUtil.findByG(groupId);
		    for (DictCollection collection : lstCollections) {
		    	ByteArrayOutputStream dictCollectionXMLStream = null;
				org.opencps.api.v21.model.DictCollection dictCollection = new org.opencps.api.v21.model.DictCollection();
				List<DictItem> dictItemList = DictItemLocalServiceUtil
					.findByF_dictCollectionId(collection.getDictCollectionId());
				if (dictItemList != null && dictItemList.size() > 0) {
					Items itemList = new Items();
					for (DictItem dictItem : dictItemList) {
						org.opencps.api.v21.model.Items.DictItem item = new org.opencps.api.v21.model.Items.DictItem();
						item.setItemCode(dictItem.getItemCode());
						item.setItemName(dictItem.getItemName());
						item.setItemNameEN(dictItem.getItemNameEN());
						item.setItemDescription(dictItem.getItemDescription());
						DictItem itemParent = DictItemLocalServiceUtil.fetchDictItem(dictItem.getParentItemId());
						if (itemParent != null) {
							item.setParent(itemParent.getItemCode());
						}
						item.setLevel(dictItem.getLevel());
						item.setSibling(dictItem.getSibling());
						item.setMetadata(dictItem.getMetaData());
						//
						itemList.getDictItem().add(item);
					}
					//
					dictCollection.setItems(itemList);
					dictCollection.setCollectionCode(collection.getCollectionCode());
					dictCollection.setCollectionName(collection.getCollectionName());
					dictCollection.setCollectionNameEN(collection.getCollectionNameEN());
					dictCollection.setDescription(collection.getDescription());
					dictCollection.setStatus(collection.getStatus());
				}
				
				dictCollectionXMLStream = ReadXMLFileUtils.convertDictCollectionToXMLStream(dictCollection);
	
				input = dictCollectionXMLStream.toByteArray();
			    entry = new ZipEntry("dicts/" + collection.getCollectionCode() + ".xml");
			    entry.setSize(input.length);
			    zos.putNextEntry(entry);
			    zos.write(input);
			    dictCollectionXMLStream.close();
			    zos.closeEntry();				
		    }
	
		    ZipEntry forms = new ZipEntry("forms/");
		    zos.putNextEntry(forms);
		    zos.closeEntry();

		    ZipEntry files = new ZipEntry("files/");
		    zos.putNextEntry(files);
		    zos.closeEntry();
		    
		    ZipEntry reports = new ZipEntry("reports/");
		    zos.putNextEntry(reports);
		    zos.closeEntry();
		    
		    List<DocumentType> lstDocumentTypes = DocumentTypeLocalServiceUtil.findByG(groupId);
		    for (DocumentType dt : lstDocumentTypes) {
		    	if (Validator.isNotNull(dt.getDocumentScript())) {
					input = dt.getDocumentScript().getBytes();
				    entry = new ZipEntry("reports/" + dt.getTypeCode() + ".xml");
				    entry.setSize(input.length);
				    zos.putNextEntry(entry);
				    zos.write(input);		    		
		    	}
		    }
		    
		    List<org.opencps.dossiermgt.model.DossierPart> lstParts = DossierPartLocalServiceUtil.findByG(groupId);
		    for (org.opencps.dossiermgt.model.DossierPart dp : lstParts) {
		    	if (Validator.isNotNull(dp.getFormReport())) {
					input = dp.getFormReport().getBytes();
				    entry = new ZipEntry("reports/" + dp.getTemplateNo() + "_" + dp.getPartNo() + ".xml");
				    entry.setSize(input.length);
				    zos.putNextEntry(entry);
				    zos.write(input);		    		
		    	}
			    
		    	if (Validator.isNotNull(dp.getFormScript())) {
					input = dp.getFormScript().getBytes();
				    entry = new ZipEntry("forms/" + dp.getTemplateNo() + "_" + dp.getPartNo() + ".json");
				    entry.setSize(input.length);
				    zos.putNextEntry(entry);
				    zos.write(input);		    		
		    	}
		    }
		    	
		    List<DeliverableType> lstDeliverableTypes = DeliverableTypeLocalServiceUtil.findByG(groupId);
		    for (DeliverableType dt : lstDeliverableTypes) {
		    	if (Validator.isNotNull(dt.getFormReport())) {
					input = dt.getFormReport().getBytes();
				    entry = new ZipEntry("reports/" + dt.getTypeCode() + ".xml");
				    entry.setSize(input.length);
				    zos.putNextEntry(entry);
				    zos.write(input);		    		
		    	}
			    
		    	if (Validator.isNotNull(dt.getFormScript())) {
					input = dt.getFormScript().getBytes();
				    entry = new ZipEntry("forms/" + dt.getTypeCode() + ".json");
				    entry.setSize(input.length);
				    zos.putNextEntry(entry);
				    zos.write(input);		    		
		    	}
		    }
		    
		    ZipEntry services = new ZipEntry("services/");
		    zos.putNextEntry(services);
			List<ServiceInfo> serviceList = ServiceInfoLocalServiceUtil.findByGroup(groupId);
			ByteArrayOutputStream serviceInfoXMLStream;
			
			if (serviceList != null && serviceList.size() > 0) {
				//Create new folder
				for (ServiceInfo serviceInfo : serviceList) {
					org.opencps.api.v21.model.ServiceInfo serviceInfoExport = new org.opencps.api.v21.model.ServiceInfo();
					//
					serviceInfoExport.setServiceCode(serviceInfo.getServiceCode());
					serviceInfoExport.setServiceName(serviceInfo.getServiceName());
					serviceInfoExport.setProcessText(serviceInfo.getProcessText());
					serviceInfoExport.setMethodText(serviceInfo.getMethodText());
					serviceInfoExport.setDossierText(serviceInfo.getDossierText());
					serviceInfoExport.setConditionText(serviceInfo.getConditionText());
					serviceInfoExport.setDurationText(serviceInfo.getDurationText());
					serviceInfoExport.setApplicantText(serviceInfo.getApplicantText());
					serviceInfoExport.setResultText(serviceInfo.getResultText());
					serviceInfoExport.setRegularText(serviceInfo.getRegularText());
					serviceInfoExport.setFeeText(serviceInfo.getFeeText());
					serviceInfoExport.setAdministrationCode(serviceInfo.getAdministrationCode());
					serviceInfoExport.setAdministrationName(serviceInfo.getAdministrationName());
					serviceInfoExport.setDomainCode(serviceInfo.getDomainCode());
					serviceInfoExport.setDomainName(serviceInfo.getDomainName());
					serviceInfoExport.setMaxLevel(serviceInfo.getMaxLevel());
					// Process ServiceFileTemplate
					long serviceInfoId = serviceInfo.getServiceInfoId();
					if (serviceInfoId > 0) {
						List<ServiceFileTemplate> fileServiceList = ServiceFileTemplateLocalServiceUtil
								.getByServiceInfoId(serviceInfoId);
						if (fileServiceList != null && fileServiceList.size() > 0) {
							FileTemplates fileTempList = new FileTemplates();
							for (ServiceFileTemplate serviceFileTemplate : fileServiceList) {
								FileTemplate fileTemp = new FileTemplate();
								//
								fileTemp.setFileTemplateNo(serviceFileTemplate.getFileTemplateNo());
								fileTemp.setTemplateName(serviceFileTemplate.getTemplateName());
								//Get file name
								long fileEntryId = serviceFileTemplate.getFileEntryId();
								if (fileEntryId > 0) {
									DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.fetchDLFileEntry(fileEntryId);
									if (fileEntry != null) {
										fileTemp.setFilename(fileEntry.getFileName());
									}
								}
								//add FileTemplate in list
								fileTempList.getFileTemplate().add(fileTemp);
							}
							// add FileTemplates to serviceInfo
							serviceInfoExport.setFileTemplates(fileTempList);
						} else {
							FileTemplate fileTemp = new FileTemplate();
							fileTemp.setFileTemplateNo(StringPool.BLANK);
							fileTemp.setTemplateName(StringPool.BLANK);
							fileTemp.setFilename(StringPool.BLANK);
						}
						//
						List<ServiceConfig> serviceConfigList = ServiceConfigLocalServiceUtil
								.getByServiceInfo(groupId, serviceInfoId);
						if (serviceConfigList != null && serviceConfigList.size() > 0) {
							Configs configList = new Configs();
							for (ServiceConfig serviceConfig : serviceConfigList) {
								org.opencps.api.v21.model.Configs.ServiceConfig config = new org.opencps.api.v21.model.Configs.ServiceConfig();
								//
								config.setGovAgencyCode(serviceConfig.getGovAgencyCode());
								config.setGovAgencyName(serviceConfig.getGovAgencyName());
								config.setServiceInstruction(serviceConfig.getServiceInstruction());
								config.setServiceLevel(serviceConfig.getServiceLevel());
								config.setServiceUrl(serviceConfig.getServiceUrl());
								config.setForCitizen(serviceConfig.getForCitizen());
								config.setForBusiness(serviceConfig.getForBusiness());
								config.setPostalService(serviceConfig.getPostService());
								config.setRegistration(serviceConfig.getRegistration());
								//Get Process Option
								long serviceConfigId = serviceConfig.getServiceConfigId();
								if (serviceConfigId > 0) {
									List<ProcessOption> processOptionList = ProcessOptionLocalServiceUtil
											.getByServiceProcessId(serviceConfigId);
									if (processOptionList != null && processOptionList.size() > 0) {
										Processes processes = new Processes();
										for (ProcessOption processOption : processOptionList) {
											org.opencps.api.v21.model.Processes.ProcessOption option = new org.opencps.api.v21.model.Processes.ProcessOption();
											//
											option.setOptionName(processOption.getOptionName());
											option.setSeqOrder(processOption.getOptionOrder());
											option.setSampleCount((int) processOption.getSampleCount());
											option.setAutoSelect(processOption.getAutoSelect());
											option.setInstructionNote(processOption.getInstructionNote());
											option.setSubmissionNote(processOption.getSubmissionNote());
											//Get templateNo
											DossierTemplate template = DossierTemplateLocalServiceUtil.fetchDossierTemplate(processOption.getDossierTemplateId());
											if (template != null) {
												option.setTemplateNo(template.getTemplateNo());
												option.setTemplateName(template.getTemplateName());
											}
											// Get ServiceProcess
											ServiceProcess process = ServiceProcessLocalServiceUtil.fetchServiceProcess(processOption.getServiceProcessId());
											if (process != null) {
												option.setProcessNo(process.getProcessNo());
												option.setProcessName(process.getProcessName());
											}
											option.setRegisterBookCode(processOption.getRegisterBookCode());
											// Add option to list
											processes.getProcessOption().add(option);
										}
										// Add list option to serviceConfig
										config.setProcesses(processes);
									}
								}
								//add serviceConfig in list
								configList.getServiceConfig().add(config);
							}
							// add FileTemplates to serviceInfo
							serviceInfoExport.setConfigs(configList);
						}
					}
					serviceInfoXMLStream = ReadXMLFileUtils.convertServiceInfoToXMLStream(serviceInfoExport);
					input = serviceInfoXMLStream.toByteArray();
				    entry = new ZipEntry("services/" + serviceInfo.getServiceCode() + ".xml");
				    entry.setSize(input.length);
				    zos.putNextEntry(entry);
				    zos.write(input);
				    serviceInfoXMLStream.close();
				    
				    List<ServiceFileTemplate> sfts = ServiceFileTemplateLocalServiceUtil.getByServiceInfoId(serviceInfo.getServiceInfoId());
				    for (ServiceFileTemplate sft : sfts) {
				    	DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.fetchDLFileEntry(sft.getFileEntryId());
				    	if (fileEntry != null) {
				    		try {
					    		InputStream is = fileEntry.getContentStream();
					    		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
	
					    		int nRead;
					    		byte[] data = new byte[16384];
	
					    		while ((nRead = is.read(data, 0, data.length)) != -1) {
					    		  buffer.write(data, 0, nRead);
					    		}
	
								input = buffer.toByteArray();
							    entry = new ZipEntry("files/" + fileEntry.getFileName());
							    entry.setSize(input.length);
							    zos.putNextEntry(entry);
							    zos.write(input);
							    buffer.close();		
				    		}
				    		catch (Exception e) {
				    			_log.debug(e);
				    		}
				    	}
				    }
				}		
			}
		    zos.closeEntry();
		    
		    ZipEntry templates = new ZipEntry("templates/");
		    zos.putNextEntry(templates);
		    zos.closeEntry();
		    
		    List<DossierTemplate> lstTemplates = DossierTemplateLocalServiceUtil.findByG(groupId);
		    for (DossierTemplate dt : lstTemplates) {
		    	org.opencps.api.v21.model.DossierTemplate dossierTemplate = new org.opencps.api.v21.model.DossierTemplate();
		    	dossierTemplate.setDescription(dt.getDescription());
		    	dossierTemplate.setTemplateName(dt.getTemplateName());
		    	dossierTemplate.setTemplateNo(dt.getTemplateNo());
		    	
		    	List<DossierPart> parts = DossierPartLocalServiceUtil.getByTemplateNo(groupId, dt.getTemplateNo());
		    	Parts tempParts = new Parts();
		    	
		    	for (DossierPart dp : parts) {
		    		org.opencps.api.v21.model.Parts.DossierPart dossierPart = new org.opencps.api.v21.model.Parts.DossierPart();
		    		dossierPart.setPartNo(dp.getPartNo());
		    		dossierPart.setPartName(dp.getPartName());
		    		dossierPart.setPartTip(dp.getPartTip());
		    		dossierPart.setPartType(dp.getPartType());
		    		dossierPart.setMultiple(dp.getMultiple());
		    		dossierPart.setRequired(dp.getRequired());
		    		dossierPart.setEsign(dp.getESign());
		    		dossierPart.setFileTemplateNo(dp.getFileTemplateNo());
		    		dossierPart.setDeliverableType(dp.getDeliverableType());
		    		dossierPart.setDeliverableAction(dp.getDeliverableAction());
		    		dossierPart.setEForm(dp.getEForm());
		    		dossierPart.setSampleData(dp.getSampleData());
		    		
		    		tempParts.getDossierPart().add(dossierPart);
		    	}
		    	
		    	dossierTemplate.setParts(tempParts);
		    	
	    		ByteArrayOutputStream buffer = ReadXMLFileUtils.convertDossierTemplateToXMLStream(dossierTemplate);
	    		
				input = buffer.toByteArray();
			    entry = new ZipEntry("templates/" + dt.getTemplateNo() + ".xml");
			    entry.setSize(input.length);
			    zos.putNextEntry(entry);
			    zos.write(input);
			    buffer.close();		
		    	
		    }
		    ZipEntry processes = new ZipEntry("processes/");
		    zos.putNextEntry(processes);
		    zos.closeEntry();
		    
		    List<ServiceProcess> lstProcesses = ServiceProcessLocalServiceUtil.getByG_ID(groupId);
		    for (ServiceProcess sp : lstProcesses) {
		    	org.opencps.api.v21.model.ServiceProcess serviceProcess = new org.opencps.api.v21.model.ServiceProcess();
		    	serviceProcess.setProcessNo(sp.getProcessNo());
		    	serviceProcess.setProcessName(sp.getProcessName());
		    	serviceProcess.setDescription(sp.getDescription());
		    	serviceProcess.setDurationCount(String.valueOf(sp.getDurationCount()));
		    	serviceProcess.setDurationUnit(sp.getDurationUnit());
		    	serviceProcess.setGeneratePassword(sp.getGeneratePassword());
		    	serviceProcess.setServerNo(sp.getServerNo());
		    	serviceProcess.setServerName(sp.getServerName());
		    	serviceProcess.setDossierNoPattern(sp.getDossierNoPattern());
		    	serviceProcess.setDueDatePattern(sp.getDueDatePattern());
		    	List<ServiceProcessRole> lstProcessRoles = ServiceProcessRoleLocalServiceUtil.findByS_P_ID(sp.getServiceProcessId());
		    	Roles roles = new Roles();
		    	for (ServiceProcessRole role : lstProcessRoles) {
		    		ProcessRole processRole = new ProcessRole();
		    		processRole.setRoleCode(role.getRoleCode());
		    		processRole.setRoleName(role.getRoleName());
		    		processRole.setModerator(role.getModerator());
		    		processRole.setCondition(role.getCondition());
		    		
		    		roles.getProcessRole().add(processRole);
		    	}
		    	
		    	serviceProcess.setRoles(roles);
		    	List<org.opencps.dossiermgt.model.ProcessSequence> lstSequences = ProcessSequenceLocalServiceUtil.getByServiceProcess(groupId, sp.getServiceProcessId());
		    	Sequences sequences = new Sequences();
		    	for (org.opencps.dossiermgt.model.ProcessSequence ps : lstSequences) {
		    		ProcessSequence sequence = new ProcessSequence();
		    		sequence.setSequenceNo(ps.getSequenceNo());
		    		sequence.setSequenceName(ps.getSequenceName());
		    		sequence.setSequenceRole(ps.getSequenceRole());
		    		sequence.setDurationCount(String.valueOf(ps.getDurationCount()));
		    		
		    		sequences.getProcessSequence().add(sequence);
		    	}
		    	serviceProcess.setSequences(sequences);
		    	List<ProcessStep> lstSteps = ProcessStepLocalServiceUtil.getProcessStepbyServiceProcessId(sp.getServiceProcessId());
		    	Steps steps = new Steps();
		    	for (ProcessStep ps : lstSteps) {
		    		org.opencps.api.v21.model.Steps.ProcessStep step = new org.opencps.api.v21.model.Steps.ProcessStep();
		    		step.setStepCode(ps.getStepCode());
		    		step.setStepName(ps.getStepName());
		    		step.setSequenceNo(ps.getSequenceNo());
		    		step.setDossierStatus(ps.getDossierStatus());
		    		step.setDossierSubStatus(ps.getDossierSubStatus());
		    		step.setDurationCount(String.valueOf(ps.getDurationCount()));
		    		step.setBriefNote(ps.getBriefNote());
		    		step.setRoleAsStep(ps.getRoleAsStep());
		    		
		    		List<ProcessStepRole> lstStepRoles = ProcessStepRoleLocalServiceUtil.findByP_S_ID(ps.getProcessStepId());
		    		org.opencps.api.v21.model.Steps.ProcessStep.Roles rs = new org.opencps.api.v21.model.Steps.ProcessStep.Roles();
		    		for (ProcessStepRole sr : lstStepRoles) {
		    			StepRole stepRole = new StepRole();
		    			stepRole.setCondition(sr.getCondition());
		    			stepRole.setModerator(sr.getModerator());
		    			stepRole.setRoleCode(sr.getRoleCode());
		    			stepRole.setRoleName(sr.getRoleName());
		    			
		    			rs.getStepRole().add(stepRole);
		    		}
		    		step.setRoles(rs);
		    		steps.getProcessStep().add(step);
		    	}
		    	serviceProcess.setSteps(steps);
		    	
		    	Actions actions = new Actions();
		    	List<ProcessAction> lstActions = ProcessActionLocalServiceUtil.getProcessActionbyServiceProcessId(sp.getServiceProcessId());
		    	for (ProcessAction pa : lstActions) {
		    		org.opencps.api.v21.model.Actions.ProcessAction paction = new org.opencps.api.v21.model.Actions.ProcessAction();
		    		paction.setActionCode(pa.getActionCode());
		    		paction.setActionName(pa.getActionName());
		    		paction.setPreStepCode(pa.getPreStepCode());
		    		paction.setPostStepCode(pa.getPostStepCode());
		    		paction.setAutoEvent(pa.getAutoEvent());
		    		paction.setPreCondition(pa.getPreCondition());
		    		paction.setAllowAssignUser(pa.getAllowAssignUser());
		    		paction.setRequestPayment(pa.getRequestPayment());
		    		User u = UserLocalServiceUtil.fetchUser(pa.getAssignUserId());
		    		if (u != null) {
		    			paction.setAssignUserName(u.getFullName());
		    		}
		    		paction.setPaymentFee(pa.getPaymentFee());
		    		paction.setCreateDossierFiles(pa.getCreateDossierFiles());
		    		paction.setReturnDossierFiles(pa.getReturnDossierFiles());
		    		paction.setESignature(pa.getESignature());
		    		paction.setSignatureType(pa.getSignatureType());
		    		paction.setCreateDossiers(pa.getCreateDossiers());
		    		
		    		actions.getProcessAction().add(paction);
		    	}
		    	serviceProcess.setActions(actions);
		    	
	    		ByteArrayOutputStream buffer = ReadXMLFileUtils.convertServiceProcessToXMLStream(serviceProcess);
	    		
				input = buffer.toByteArray();
			    entry = new ZipEntry("processes/" + sp.getProcessNo() + ".xml");
			    entry.setSize(input.length);
			    zos.putNextEntry(entry);
			    zos.write(input);
			    buffer.close();		

		    }
		    zos.close();
		    
			ResponseBuilder responseBuilder = Response.ok((Object) file);

			responseBuilder.header("Content-Disposition",
					"attachment; filename=\"" + "backupmaster.zip" + "\"");
			responseBuilder.header("Content-Type", "application/xml");

			return responseBuilder.build();			
		} catch (Exception e) {
			_log.debug(e);
			return BusinessExceptionImpl.processException(e);
		} finally {
			if (zos != null) {
				try {
					zos.close();
				} catch (IOException e) {
					_log.debug(e);
				}
			}
		}
	}

}
