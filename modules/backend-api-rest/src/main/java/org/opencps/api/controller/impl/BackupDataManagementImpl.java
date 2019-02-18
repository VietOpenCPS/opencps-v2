package org.opencps.api.controller.impl;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.util.List;
import java.util.Locale;

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
import org.opencps.api.v21.model.BusinessList;
import org.opencps.api.v21.model.BusinessList.Business;
import org.opencps.api.v21.model.CitizenList;
import org.opencps.api.v21.model.CitizenList.Citizen;
import org.opencps.api.v21.model.Configs;
import org.opencps.api.v21.model.FileTemplates;
import org.opencps.api.v21.model.FileTemplates.FileTemplate;
import org.opencps.api.v21.model.Items;
import org.opencps.api.v21.model.MenuConfigList;
import org.opencps.api.v21.model.Processes;
import org.opencps.api.v21.model.StepConfigList;
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
import org.opencps.dossiermgt.model.DossierTemplate;
import org.opencps.dossiermgt.model.MenuConfig;
import org.opencps.dossiermgt.model.MenuRole;
import org.opencps.dossiermgt.model.ProcessOption;
import org.opencps.dossiermgt.model.ServiceConfig;
import org.opencps.dossiermgt.model.ServiceFileTemplate;
import org.opencps.dossiermgt.model.ServiceInfo;
import org.opencps.dossiermgt.model.ServiceProcess;
import org.opencps.dossiermgt.model.StepConfig;
import org.opencps.dossiermgt.service.ActionConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierTemplateLocalServiceUtil;
import org.opencps.dossiermgt.service.MenuConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.MenuRoleLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessOptionLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceFileTemplateLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceInfoLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceProcessLocalServiceUtil;
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

}
