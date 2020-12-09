package org.opencps.api.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.servlet.HttpMethods;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import javax.activation.DataHandler;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import io.swagger.util.Json;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.util.HttpURLConnection;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.opencps.api.constants.ConstantUtils;
import org.opencps.api.constants.DossierManagementConstants;
import org.opencps.api.controller.DossierManagement;
import org.opencps.api.controller.util.ConvertDossierFromV1Dot9Utils;
import org.opencps.api.controller.util.DossierFileUtils;
import org.opencps.api.controller.util.DossierMarkUtils;
import org.opencps.api.controller.util.DossierUtils;
import org.opencps.api.controller.util.MessageUtil;
import org.opencps.api.dossier.model.*;
import org.opencps.api.dossieraction.model.DossierActionNextActionModel;
import org.opencps.api.dossierfile.model.DossierFileModel;
import org.opencps.api.dossiermark.model.DossierMarkInputModel;
import org.opencps.api.dossiermark.model.DossierMarkModel;
import org.opencps.api.dossiermark.model.DossierMarkResultDetailModel;
import org.opencps.api.dossiermark.model.DossierMarkResultsModel;
import org.opencps.api.reassign.model.ReAssign;
import org.opencps.api.reassign.model.ToUsers;
import org.opencps.api.v21.dossiersync.model.DossierSyncV21DataModel;
import org.opencps.api.v21.dossiersync.model.DossierSyncV21ResultsModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.NotFoundException;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.opencps.datamgt.util.BetimeUtils;
import org.opencps.datamgt.util.DueDateUtils;
import org.opencps.datamgt.util.HolidayUtils;
import org.opencps.dossiermgt.action.*;

import org.opencps.dossiermgt.action.impl.*;
import org.opencps.dossiermgt.action.util.AutoFillFormData;
import org.opencps.dossiermgt.action.util.DossierActionUtils;
import org.opencps.dossiermgt.action.util.DossierMgtUtils;
import org.opencps.dossiermgt.action.util.DossierNumberGenerator;
import org.opencps.dossiermgt.action.util.NotarizationCounterNumberGenerator;
import org.opencps.dossiermgt.action.util.OpenCPSConfigUtil;
import org.opencps.dossiermgt.action.util.SpecialCharacterUtils;
import org.opencps.dossiermgt.constants.*;
import org.opencps.dossiermgt.model.*;
import org.opencps.dossiermgt.model.DossierActionUser;
import org.opencps.dossiermgt.rest.utils.SyncServerTerm;
import org.opencps.dossiermgt.scheduler.InvokeREST;
import org.opencps.dossiermgt.scheduler.RESTFulConfiguration;
import org.opencps.dossiermgt.service.*;
import org.opencps.dossiermgt.service.persistence.DossierActionUserPK;
import org.opencps.dossiermgt.service.persistence.DossierFileUtil;
import org.opencps.dossiermgt.service.persistence.ProcessActionUtil;
import org.opencps.dossiermgt.service.persistence.ServiceProcessUtil;
import org.opencps.usermgt.action.ApplicantActions;
import org.opencps.usermgt.action.impl.ApplicantActionsImpl;
import org.opencps.usermgt.constants.ApplicantTerm;
import org.opencps.usermgt.constants.UserTerm;
import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.service.ApplicantLocalServiceUtil;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;

import backend.auth.api.exception.BusinessExceptionImpl;
import backend.auth.api.exception.ErrorMsgModel;
import org.springframework.http.HttpEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import uk.org.okapibarcode.backend.Code128;
import uk.org.okapibarcode.backend.HumanReadableLocation;
import uk.org.okapibarcode.backend.QrCode;
import uk.org.okapibarcode.backend.Symbol;
import uk.org.okapibarcode.output.Java2DRenderer;

public class DossierManagementImpl implements DossierManagement {

	public static final String RT_CANCELLING = "cancelling";
	public static final String RT_CORRECTING = "correcting";
	public static final String RT_SUBMITTING = "submitting";

	public static final String ALL_AGENCY = "all";

	private static final String CONFIG_DVCQG_INTEGRATION = "DVCQG_INTEGRATION";
	private static final String ERROR_CODE = "error_code";
	private static final String ERROR_MESSAGE = "message";


	private String getBodyError(String code, String message) {
		JSONObject body = JSONFactoryUtil.createJSONObject();
		body.put(ERROR_CODE, code);
		body.put(ERROR_MESSAGE, message);
		return body.toString();
	}

	@Override
	public Response getDirectDossiers(HttpServletRequest request, HttpHeaders header,
									  Company company, Locale locale, User user, ServiceContext serviceContext,
									  DossierRequestDVCQGModel dossierRequestDVCQGModel) {
		try {
			JSONObject body = JSONFactoryUtil.createJSONObject();
			String errorBody;
			ServerConfig serverConfig = null;
			List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil.getByProtocol(CONFIG_DVCQG_INTEGRATION);

			if (serverConfigs != null && !serverConfigs.isEmpty()) {
				serverConfig = serverConfigs.get(0);
			}

			if (serverConfig == null) {
				_log.error("------ Log Exception ------ " + " " + "No server config");
				errorBody = this.getBodyError("-1", "No server config");
				return Response.status(HttpURLConnection.HTTP_OK).entity(errorBody).build();
			}

			long groupId = serverConfig.getGroupId();
			Dossier dossier = DossierLocalServiceUtil.getByDossierNo(groupId, dossierRequestDVCQGModel.getMasohoso());
			if(Validator.isNull(dossier)){
				_log.error("------ Log Exception ------ " + " " + "masohoso not found");
				errorBody = this.getBodyError("-1", "masohoso not found");
				return Response.status(HttpURLConnection.HTTP_OK).entity(errorBody).build();
			}

			String dossierNo   = dossier.getDossierNo() != null ? dossier.getDossierNo() : "";
			String serviceCode = dossier.getServiceCode() != null ? dossier.getServiceCode() : "";
			String serviceName = dossier.getServiceName() != null ? dossier.getServiceName() : "";
			String dossierStatus = dossier.getDossierStatus() != null ? dossier.getDossierStatus() : "";
			String isOnline = dossier.getOnline() ? "1" : "0";
			String govAgencyName = dossier.getGovAgencyName() != null ? dossier.getGovAgencyName() : "";

			ServiceInfo serviceInfo = ServiceInfoLocalServiceUtil.getByCode(groupId, serviceCode);
			if(Validator.isNull(serviceInfo)) {
				_log.error("------ Log Exception ------ " + " " + "MaLinhVuc not found");
				errorBody = this.getBodyError("-1", "MaLinhVuc not found");
				return Response.status(HttpURLConnection.HTTP_OK).entity(errorBody).build();
			}

			String domainCode = serviceInfo.getDomainCode() != null ? serviceInfo.getDomainCode() : "";
			String domainName = serviceInfo.getDomainName() != null ? serviceInfo.getDomainName() : "";

			body.put("MaHoSo", dossierNo);
			body.put("MaTTHC", serviceCode);
			body.put("TenTTHC", serviceName);
			body.put("MaLinhVuc", domainCode);
			body.put("TenLinhVuc", domainName);
			//todo LoaiDoiTuong dang ko biet loai nao, can` ban` lai voi a Duan
			body.put("LoaiDoiTuong", "3");
			body.put("TrangThaiHoSo", DossierManagementConstants.mappingDossierStatusWithDVCQG(dossierStatus));
			body.put("HinhThuc", isOnline);
			body.put("DonViXuLy", govAgencyName);

			return Response.status(HttpURLConnection.HTTP_OK).entity(body.toJSONString()).build();
		} catch (Exception e) {
			_log.error("------ Log Exception ------ " + " " + e.getMessage());
			return BusinessExceptionImpl.processException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getDossiers(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		DossierSearchModel query) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		long userId = user.getUserId();
		String emailLogin = user.getEmailAddress();
		DossierActions actions = new DossierActionsImpl();
		DossierResultsModel results = null;

		try {
			boolean isViaPostal = query.isIstheViaPostal();
			// boolean isCitizen = false;
			if (Validator.isNull(query.getEnd()) || query.getEnd() == 0) {
				query.setStart(QueryUtil.ALL_POS);
				query.setEnd(QueryUtil.ALL_POS);
			}
			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
			params.put(Field.GROUP_ID, String.valueOf(groupId));

			Sort[] sorts = null;
			if (Validator.isNull(query.getSort())) {
				String dateSort = String.format(MessageUtil.getMessage(ConstantUtils.QUERY_NUMBER_SORT), DossierTerm.CREATE_DATE);
				sorts = new Sort[]{ SortFactoryUtil.create(dateSort, Sort.LONG_TYPE,
								GetterUtil.getBoolean(query.getOrder()))
				};
			} else {
				String querySort = String.format(MessageUtil.getMessage(ConstantUtils.QUERY_STRING_SORT), query.getSort());
				sorts = new Sort[]{ SortFactoryUtil.create( querySort, Sort.STRING_TYPE,
								GetterUtil.getBoolean(query.getOrder()))
				};
			}
			backend.auth.api.BackendAuth auth2 =
					new backend.auth.api.BackendAuthImpl();
			if (!auth2.isAdmin(serviceContext, ConstantUtils.ROLE_ADMIN_LOWER)) {
				params.put(DossierTerm.USER_ID, user.getUserId());
			}

            if(isViaPostal){
				try {
					String dossierNo = query.getDossierNo();
					 _log.info("dossierIdNo: "+dossierNo);
					String dossierNoSearch = StringPool.BLANK;
					if (Validator.isNotNull(dossierNo)) {
						dossierNoSearch = SpecialCharacterUtils.splitSpecial(dossierNo);
					}
					params.put(DossierTerm.DOSSIER_NO, dossierNoSearch);

					_log.info("params: "+params);
					JSONObject jsonData = actions.getDossiers(
							user.getUserId(), company.getCompanyId(), groupId, params,
							sorts, query.getStart(), query.getEnd(), serviceContext);

					if (jsonData.getInt(ConstantUtils.TOTAL) > 0) {
						results = new DossierResultsModel();
						results.setTotal(jsonData.getInt(ConstantUtils.TOTAL));
						results.getData().addAll(
								DossierUtils.mappingForGetList(
										(List<Document>) jsonData.get(ConstantUtils.DATA), userId,
										query.getAssigned(), query));
					}
					else {
						ServerConfig sc = ServerConfigLocalServiceUtil.getByCode(DossierTerm.VNPOST_CLS);
						if (Validator.isNotNull(sc)) {
							JSONObject config = JSONFactoryUtil.createJSONObject(sc.getConfigs());

							HashMap<String, String> properties = new HashMap<String, String>();
							InvokeREST callRest = new InvokeREST();

							if (Validator.isNotNull(dossierNo)) {
								//URL + token
								String urlVNPOST = config.getString(DossierTerm.URL_VIA_POST);
								//Path : Token + Code
								String endPointVNPOST = StringPool.FORWARD_SLASH + config.getString(DossierTerm.TOKEN_VN_POST) + StringPool.FORWARD_SLASH + dossierNo;

								JSONObject resultObj = callRest.callAPI(groupId, HttpMethods.GET, MediaType.APPLICATION_JSON,
										urlVNPOST, endPointVNPOST, "", "", properties, serviceContext);

								_log.info("URLVNPOST===========" + urlVNPOST + " | ENDPOINT: " + endPointVNPOST);
								if (Validator.isNotNull(resultObj)) {
									if (GetterUtil.getInteger(resultObj.get(RESTFulConfiguration.STATUS)) != HttpURLConnection.HTTP_OK) {
										results = new DossierResultsModel();
										results.setTotal(0);
									} else {
										JSONArray arrayData = JSONFactoryUtil.createJSONArray(resultObj.getString(RESTFulConfiguration.MESSAGE));
										_log.info("arrayData: "+arrayData);

										for (int i = 0; i < arrayData.length(); i++) {
											JSONObject object = arrayData.getJSONObject(i);
											String soCongvan = object.getString(DossierFileTerm.SO_CONG_VAN);
											String status = object.getString(DossierFileTerm.STATUS_CONG_VAN);
											// Trạng thái 1 || 2 || 3 là mã tờ khai
											// Trạng thái 4 || 5 || 6 || 7 || 8 là mã hồ sơ
											_log.info("soCongvan: "+soCongvan);
											if (Validator.isNotNull(status) && Validator.isNotNull(soCongvan)) {
												String eformCode = SpecialCharacterUtils.splitSpecial(soCongvan);
												if ("1".equals(status) || "2".equals(status) || "3".equals(status)) {
													//dossier = DossierLocalServiceUtil.findDossierByDeclarationCode(soCongvan, groupId);
													_log.info("dossier_1_2_3: ");
													params.put(DossierTerm.DOSSIER_NO, StringPool.BLANK);
													params.put(DossierTerm.MA_TO_KHAI, eformCode);
												} else {
													_log.info("!dossier_1_2_3: ");
													params.put(DossierTerm.DOSSIER_NO, eformCode);
													//dossier = DossierLocalServiceUtil.fetchByDO_NO_GROUP(soCongvan, groupId);
												}
												_log.info("params: " + params);
												JSONObject jsonDataEform = actions.getDossiers(
														user.getUserId(), company.getCompanyId(), groupId, params,
														sorts, query.getStart(), query.getEnd(), serviceContext);

												_log.info("jsonData11.getInt(ConstantUtils.TOTAL): " + jsonDataEform.getInt(ConstantUtils.TOTAL));
												if (jsonDataEform.getInt(ConstantUtils.TOTAL) > 0) {
													results = new DossierResultsModel();
													results.setTotal(jsonDataEform.getInt(ConstantUtils.TOTAL));
													List<Document> dataList = (List<Document>) jsonDataEform.get(ConstantUtils.DATA);
													// Update Dossier khi truyền mã bưu gửi tìm trên API ==>
													if (dataList != null && dataList.size() > 0) {
														for (Document doc : dataList) {
															long dossierId = GetterUtil.getLong(doc.get(DossierTerm.DOSSIER_ID));
															Dossier dossier = dossierId > 0 ? DossierLocalServiceUtil.fetchDossier(dossierId) : null;
															if (dossier != null) {
																dossier.setPostalCodeSend(dossierNo);
																DossierLocalServiceUtil.updateDossier(dossier);
															}
														}
													}
													results.getData().addAll(
															DossierUtils.mappingForGetList(dataList, userId, query.getAssigned(), query));
												}
											}
										}
											// Update Dossier khi truyền mã bưu gửi tìm trên API ==>
											/*if(Validator.isNotNull(dossier)){
												dossier.setPostalCodeSend(dossierNo);
												DossierLocalServiceUtil.updateDossier(dossier);
											}*/
									}
								}
							}
						}
					}
				} catch (Exception e) {
					return BusinessExceptionImpl.processException(e);
				}
			}
            else {
				// LamTV_Process search LIKE
				String keywordSearch = query.getKeyword();
				String keySearch = StringPool.BLANK;
				if (Validator.isNotNull(keywordSearch)) {
					keySearch = SpecialCharacterUtils.splitSpecial(keywordSearch);
				}
				params.put(Field.KEYWORD_SEARCH, keySearch);

				String status = query.getStatus();
				String substatus = query.getSubstatus();
				String agencys = query.getAgency();
				boolean notAgencysScope = query.isNotAgencysScope();
				if (ALL_AGENCY.equals(agencys) ) {
					agencys = StringPool.BLANK;
				}
				if (Validator.isNull(agencys) & !notAgencysScope) {
					Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, userId);
					if (employee != null && Validator.isNotNull(employee.getScope())) {
						agencys = employee.getScope();
					}
				}

				String serviceCode = query.getService();
				String service = StringPool.BLANK;
				if (Validator.isNotNull(serviceCode)) {
					service = SpecialCharacterUtils.splitSpecial(serviceCode);
				}
				String templateNo = query.getTemplate();
				String template = StringPool.BLANK;
				if (Validator.isNotNull(templateNo)) {
					template = SpecialCharacterUtils.splitSpecial(templateNo);
				}
				String dossierCounter = query.getDossierCounter();
				String dossierCounterSearch = StringPool.BLANK;
				if (Validator.isNotNull(dossierCounter)) {
					dossierCounterSearch = SpecialCharacterUtils.splitSpecial(dossierCounter);
				}
				// Integer originality =
				// GetterUtil.getInteger(query.getOriginality());
				// String originality = query.getOriginality();
				// if (originality == -1) {
				// owner = String.valueOf(false);
				// } else {
				// If user is citizen then default owner true
				// if (isCitizen) {
				// owner = String.valueOf(true);
				// }
				// }

				String step = query.getStep();
				String submitting = query.getSubmitting();
				// Process Top using statistic
				int year = query.getYear();
				int month = query.getMonth();
				String fromStatisticDate =
						APIDateTimeUtils.convertNormalDateToLuceneDate(
								query.getFromStatisticDate());
				String toStatisticDate =
						APIDateTimeUtils.convertNormalDateToLuceneDate(
								query.getToStatisticDate());
				String top = query.getTop();
				if (Validator.isNotNull(top) &&
						DossierTerm.STATISTIC.equals(top.toLowerCase())) {
					if ((year > 0 || month > 0) ||
							(Validator.isNotNull(fromStatisticDate) ||
									Validator.isNotNull(toStatisticDate))) {
						// if (Validator.isNotNull(fromStatisticDate) ||
						// Validator.isNotNull(toStatisticDate)) {
						//
						// }
					} else {
						Calendar baseDateCal = Calendar.getInstance();
						baseDateCal.setTime(new Date());
						if (month == 0) {
							month = baseDateCal.get(Calendar.MONTH) + 1;
						}
						if (year == 0) {
							year = baseDateCal.get(Calendar.YEAR);
						}
					}
				}

				String state = query.getState();
				String dossierIdNo = query.getDossierNo();
				String dossierNoSearch = StringPool.BLANK;
				if (Validator.isNotNull(dossierIdNo)) {
					dossierNoSearch =
							SpecialCharacterUtils.splitSpecial(dossierIdNo);
				}
				String soChungChi = query.getSoChungChi();
				String certNo = StringPool.BLANK;
				if (Validator.isNotNull(soChungChi)) {
					certNo = SpecialCharacterUtils.splitSpecial(soChungChi);
				}

				String fromReceiveDate =
						APIDateTimeUtils.convertNormalDateToLuceneDate(
								query.getFromReceiveDate());

				String toReceiveDate =
						APIDateTimeUtils.convertNormalDateToLuceneDate(
								query.getToReceiveDate());

				String fromCertDate =
						APIDateTimeUtils.convertNormalDateToLuceneDate(
								query.getTuNgayKyCc());

				String toCertDate = APIDateTimeUtils.convertNormalDateToLuceneDate(
						query.getDenNgayKyCc());

				String dossierIdCTN = query.getDossierIdCTN();
				String fromSubmitDate =
						APIDateTimeUtils.convertNormalDateToLuceneDate(
								query.getFromSubmitDate());
				String toSubmitDate =
						APIDateTimeUtils.convertNormalDateToLuceneDate(
								query.getToSubmitDate());
				
				String fromDueDate =
						APIDateTimeUtils.convertNormalDateToLuceneDate(
							query.getFromDueDate());
				String toDueDate =
					APIDateTimeUtils.convertNormalDateToLuceneDate(
						query.getToDueDate());
				// Process Statistic
				String fromReleaseDate =
						APIDateTimeUtils.convertNormalDateToLuceneDate(
								query.getFromReleaseDate());
				String toReleaseDate =
						APIDateTimeUtils.convertNormalDateToLuceneDate(
								query.getToReleaseDate());

				String fromFinishDate =
						APIDateTimeUtils.convertNormalDateToLuceneDate(
								query.getFromFinishDate());
				String toFinishDate =
						APIDateTimeUtils.convertNormalDateToLuceneDate(
								query.getToFinishDate());

				// _log.info("fromFinishDate: "+fromFinishDate);
				// _log.info("toFinishDate: "+toFinishDate);

				String fromReceiveNotDoneDate =
						APIDateTimeUtils.convertNormalDateToLuceneDate(
								query.getFromReceiveNotDoneDate());
				String toReceiveNotDoneDate =
						APIDateTimeUtils.convertNormalDateToLuceneDate(
								query.getToReceiveNotDoneDate());

				// LamTV:Get info case abnormal
				Long statusRegNo = null;
				if (Validator.isNotNull(query.getStatusReg())) {
					statusRegNo = Long.valueOf(query.getStatusReg());
				}

				Long notStatusRegNo = null;
				if (Validator.isNotNull(query.getNotStatusReg())) {
					notStatusRegNo = Long.valueOf(query.getNotStatusReg());
				}

				String online = query.getOnline();
				String domain = query.getDomain();
				String domainName = query.getDomainName();
				String applicantName = query.getApplicantName();
				String applicantIdNo = query.getApplicantIdNo();
				String serviceName = query.getServiceName();
				Integer originDossierId = query.getOriginDossierId();
				String owner = query.getOwner();
				String follow = query.getFollow();
				String applicantFollowIdNo = null;
				if (Boolean.valueOf(follow)) {
					if (userId > 0) {
						Applicant applicant = ApplicantLocalServiceUtil.fetchByMappingID(userId);
						if (applicant != null) {
							applicantFollowIdNo = applicant.getApplicantIdNo();
						}
					}
				}

				String permission = query.getPermission();
				if (Validator.isNotNull(permission)) {
					String permissionUserId = StringPool.BLANK;
					if (permission.contains(StringPool.COMMA)) {
						String[] permissionArr = permission.split(StringPool.COMMA);
						if (permissionArr != null && permissionArr.length > 0) {
							List<String> permissionList = new ArrayList<>();
							for (String permissionDetail : permissionArr) {
								if (Validator.isNotNull(permissionDetail)) {
									permissionList.add(
											user.getUserId() + StringPool.UNDERLINE +
													permissionDetail.toLowerCase());
								}
							}
							//
							permissionUserId =
									StringUtil.merge(permissionList, StringPool.COMMA);
						}
					} else {
						permissionUserId = user.getUserId() + StringPool.UNDERLINE +
								permission.toLowerCase();
					}

					params.put(DossierTerm.MAPPING_PERMISSION, permissionUserId);
				}

				// SystemId
				String strSystemId = query.getSystemId();
				if (Validator.isNotNull(strSystemId)) {
					params.put(DossierTerm.SYSTEM_ID, strSystemId);
				} else {
					params.put(DossierTerm.SYSTEM_ID, 0);
				}
				//ViaPostal
				String viaPostal = query.getViapostal();
				if (viaPostal != null) {
					params.put(DossierTerm.VIA_POSTAL, viaPostal);
				}
				params.put(DossierTerm.ONLINE, online);
				params.put(DossierTerm.STATUS, status);
				params.put(DossierTerm.SUBSTATUS, substatus);
				params.put(DossierTerm.AGENCYS, agencys);
				params.put(DossierTerm.SERVICE, service);
				params.put(DossierTerm.TEMPLATE, template);
				if (year != 0) {
					params.put(DossierTerm.YEAR, year);
				}
				if (month != 0) {
					params.put(DossierTerm.MONTH, month);
				}
				params.put(DossierTerm.DAY, query.getDay());
				if (Validator.isNotNull(step) && step.contains(DossierTerm.STEP_X)) {
					String stepCode = query.getStep();
//				_log.info("STEPCODE: "+stepCode);
					if (Validator.isNotNull(stepCode)) {
						String[] stepArr = stepCode.split(StringPool.COMMA);
						if (stepArr != null && stepArr.length > 0) {
							List<StepConfig> lstSteps = StepConfigLocalServiceUtil.findByG_SCS(groupId, stepArr);
							StringBuilder stepBuilder = new StringBuilder();
							for (StepConfig sc : lstSteps) {
								if (sc.getStepCode().contains(DossierTerm.STEP_X)) {
									for (int i = 0; i <= 9; i++) {
										String stepCodeRep = sc.getStepCode().replace(DossierTerm.STEP_X, i + StringPool.BLANK);
										if (!StringPool.BLANK.contentEquals(stepBuilder.toString())) {
											stepBuilder.append(StringPool.COMMA);
										}
										stepBuilder.append(stepCodeRep);
									}
								} else {
									if (!StringPool.BLANK.contentEquals(stepBuilder.toString())) {
										stepBuilder.append(StringPool.COMMA);
									}
									stepBuilder.append(sc.getStepCode());
								}
							}
							params.put(DossierTerm.STEP, stepBuilder.toString());
						}
					} else {
					}
				} else {
					params.put(DossierTerm.STEP, step);
				}
				params.put(DossierTerm.OWNER, owner);
				params.put(
						DossierTerm.APPLICANT_FOLLOW_ID_NO,
						Validator.isNotNull(applicantFollowIdNo)
								? applicantFollowIdNo : StringPool.BLANK);
				params.put(DossierTerm.SUBMITTING, submitting);

				params.put(DossierTerm.FOLLOW, follow);
				params.put(DossierTerm.TOP, top);

				params.put(DossierTerm.SECRET_KEY, query.getSecetKey());
				params.put(DossierTerm.STATE, state);
				params.put(DossierTerm.DOSSIER_NO, dossierNoSearch);
				params.put(DossierTerm.CERT_NO, certNo);
				params.put(DossierTerm.FROM_RECEIVEDATE, fromReceiveDate);
				params.put(DossierTerm.TO_RECEIVEDATE, toReceiveDate);
				params.put(DossierTerm.FROM_DUEDATE, fromDueDate);
				params.put(DossierTerm.TO_DUEDATE, toDueDate);
				params.put(DossierTerm.FROM_CERT_DATE, fromCertDate);
				params.put(DossierTerm.TO_CERT_DATE, toCertDate);
				params.put(DossierTerm.DOSSIER_ID_CTN, dossierIdCTN);
				params.put(DossierTerm.FROM_SUBMIT_DATE, fromSubmitDate);
				params.put(DossierTerm.TO_SUBMIT_DATE, toSubmitDate);
				params.put(DossierTerm.STATUS_REG, statusRegNo);
				params.put(DossierTerm.NOT_STATUS_REG, notStatusRegNo);
				if (Validator.isNotNull(domain)) {
					params.put(DossierTerm.DOMAIN_CODE, domain);
				}
				params.put(DossierTerm.DOMAIN_NAME, domainName);
				params.put(DossierTerm.APPLICANT_NAME, applicantName);
				params.put(DossierTerm.APPLICANT_ID_NO, applicantIdNo);
				params.put(DossierTerm.SERVICE_NAME, serviceName);
				// Check guest search
				params.put(DossierTerm.EMAIL_USER_LOGIN, emailLogin);
				params.put(DossierTerm.ORIGINALLITY, query.getOriginality());
				//
				params.put(DossierTerm.FROM_RELEASE_DATE, fromReleaseDate);
				params.put(DossierTerm.TO_RELEASE_DATE, toReleaseDate);
				params.put(DossierTerm.FROM_FINISH_DATE, fromFinishDate);
				params.put(DossierTerm.TO_FINISH_DATE, toFinishDate);
				params.put(
						DossierTerm.FROM_RECEIVE_NOTDONE_DATE, fromReceiveNotDoneDate);
				params.put(
						DossierTerm.TO_RECEIVE_NOTDONE_DATE, toReceiveNotDoneDate);
				params.put(
						PaymentFileTerm.PAYMENT_STATUS, query.getPaymentStatus());
				params.put(DossierTerm.FROM_STATISTIC_DATE, fromStatisticDate);
				params.put(DossierTerm.TO_STATISTIC_DATE, toStatisticDate);
				params.put(DossierTerm.ORIGIN, query.getOrigin());
				params.put(DossierTerm.TIME, query.getTime());
				//Undue time
				params.put(DossierTerm.UNDUE_TIME, query.getUndueTime());

				params.put(DossierTerm.REGISTER, query.getRegister());
				params.put(DossierTerm.DOSSIER_COUNTER_SEARCH, dossierCounterSearch);

				params.put(DossierTerm.TO_BACKLOGDATE, query.getToBacklogDate());
				params.put(DossierTerm.BACKLOG, query.getBacklog());
				if (Validator.isNotNull(query.getAssignedUserIdSearch())) {
					params.put(DossierTerm.ASSIGNED_USER_ID_SEARCH, query.getAssignedUserIdSearch());
				}

				params.put(DossierTerm.GROUP_DOSSIER_ID, query.getGroupDossierId());
				// Delegate
				if (Validator.isNotNull(query.getDelegateType())) {
					params.put(DossierTerm.DELEGATE_TYPE, query.getDelegateType());
				}
				if (Validator.isNotNull(query.getDocumentNo())) {
					params.put(DossierTerm.DOCUMENT_NO, query.getDocumentNo());
				}
				if (Validator.isNotNull(query.getDocumentDate())) {
					params.put(DossierTerm.DOCUMENT_DATE, query.getDocumentDate());
				}

				// Search theo tu tuong moi
				// params.put(DossierTerm.ORIGINALLITY_TEST, strOriginality);
				if (Validator.isNotNull(originDossierId))
					params.put(DossierTerm.ORIGIN_DOSSIER_ID, originDossierId);

				if (Validator.isNotNull(query.getVnpostalStatus())) {
					params.put(DossierTerm.VNPOSTAL_STATUS, query.getVnpostalStatus());
				}

				Integer fromViaPostal = query.getFromViaPostal();
				if (fromViaPostal != null) {
					params.put(DossierTerm.FROM_VIA_POSTAL, fromViaPostal);
				}
				// Nếu donvigui == _scope ==> Get Employee lấy được _scope gán giá trị cho param
				Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, userId);
				String donvigui = query.getDonvigui();
				if (Validator.isNotNull(donvigui)) {
					String[] donviguiArr = donvigui.split(StringPool.COMMA);
					//Task update: Nếu input có firtscope ==> lấy đơn vị đầu tiên của Employee
					boolean firtScopeDVG = false;
					for (String key : donviguiArr) {
						if (key.equals(DossierTerm.FIRSTSCOPE)) {
							firtScopeDVG = true;
						}
					}
					if (firtScopeDVG) {
						if (Validator.isNotNull(employee)) {
							String[] employeeArr = employee.getScope().split(StringPool.COMMA);
							params.put(DossierTerm.DON_VI_GUI, employeeArr[0]);
						}
					} else {
						if (query.getDonvigui().equals(DossierTerm.SCOPE_)) {
							if (Validator.isNotNull(employee)) {
								params.put(DossierTerm.DON_VI_GUI, employee.getScope());
							}
						} else {
							params.put(DossierTerm.DON_VI_GUI, donvigui);
						}
					}
				}
				//Don vi nhan
				String donvinhan = query.getDonvinhan();
				if (Validator.isNotNull(donvinhan)) {
					String[] donvinhanArr = donvinhan.split(StringPool.COMMA);
					boolean firtScopeDVN = false;
					for (String key : donvinhanArr) {
						if (key.equals(DossierTerm.FIRSTSCOPE)) {
							firtScopeDVN = true;
						}
					}
					if (firtScopeDVN) {
						if (Validator.isNotNull(employee)) {
							String[] employeeArr = employee.getScope().split(StringPool.COMMA);
							params.put(DossierTerm.DON_VI_NHAN, employeeArr[0]);
						}
					} else {
						if (query.getDonvinhan().equals(DossierTerm.SCOPE_)) {
							if (Validator.isNotNull(employee)) {
								params.put(DossierTerm.DON_VI_NHAN, employee.getScope());
							}
						} else {
							params.put(DossierTerm.DON_VI_NHAN, donvinhan);
						}
					}
				}

				if (Validator.isNotNull(top)) {
					String querySort;
					switch (top) {
						case DossierTerm.RECEIVE:
							querySort = String.format(MessageUtil.getMessage(ConstantUtils.QUERY_NUMBER_SORT), DossierTerm.RECEIVE_DATE_TIMESTAMP);
							sorts = new Sort[]{
									SortFactoryUtil.create(
											querySort,
											Sort.LONG_TYPE, false)
							};
							break;
						case DossierTerm.OVERDUE:
							querySort = String.format(MessageUtil.getMessage(ConstantUtils.QUERY_NUMBER_SORT), DossierTerm.DUE_DATE_TIMESTAMP);
							sorts = new Sort[]{
									SortFactoryUtil.create(
											querySort,
											Sort.LONG_TYPE, false)
							};
							break;
						case DossierTerm.RELEASE:
							querySort = String.format(MessageUtil.getMessage(ConstantUtils.QUERY_NUMBER_SORT), DossierTerm.RELEASE_DATE_TIMESTAMP);
							sorts = new Sort[]{
									SortFactoryUtil.create(
											querySort,
											Sort.LONG_TYPE, false)
							};
							break;
						case DossierTerm.CANCELLING:
							querySort = String.format(MessageUtil.getMessage(ConstantUtils.QUERY_NUMBER_SORT), DossierTerm.CANCELLING_DATE_TIMESTAMP);
							sorts = new Sort[]{
									SortFactoryUtil.create(
											querySort,
											Sort.LONG_TYPE, false)
							};
							break;
						case DossierTerm.CORRECTING:
							querySort = String.format(MessageUtil.getMessage(ConstantUtils.QUERY_NUMBER_SORT), DossierTerm.CORRECTING_DATE_TIMESTAMP);
							sorts = new Sort[]{
									SortFactoryUtil.create(
											querySort,
											Sort.LONG_TYPE, false)
							};
							break;
						default:
							break;
					}
				}

				results = new DossierResultsModel();

				_log.info("params: "+params);
				JSONObject jsonData = actions.getDossiers(
						user.getUserId(), company.getCompanyId(), groupId, params,
						sorts, query.getStart(), query.getEnd(), serviceContext);

				results.setTotal(jsonData.getInt(ConstantUtils.TOTAL));

				results.getData().addAll(
						DossierUtils.mappingForGetList(
								(List<Document>) jsonData.get(ConstantUtils.DATA), userId,
								query.getAssigned(), query));

			}
            //Add voting data
			if(Validator.isNull(query.getIsGetVotingData())
					|| query.getIsGetVotingData().isEmpty()
					|| query.getIsGetVotingData().equals("false")) {
				//case default
				return Response.status(HttpURLConnection.HTTP_OK).entity(results).build();
			}

			List<DossierDataModel> dossiers = results.getData();
			List<String> listDossierId = new ArrayList<>();
			for(DossierDataModel oneDossier : dossiers) {
				if(Validator.isNotNull(oneDossier.getDossierId())) {
					listDossierId.add(String.valueOf(oneDossier.getDossierId()));
				}
			}

			if(listDossierId.size() > 0) {
				List<Object[]> listVoting = DossierLocalServiceUtil.getListVotingByDossier(groupId, listDossierId);
				if(Validator.isNull(listVoting) || listVoting.size() == 0) {
					return Response.status(HttpURLConnection.HTTP_OK).entity(results).build();
				}
				int size = listVoting.size();
				long classPK;
				String votingCode;
				Integer votingId;
				String votingName;
				Integer point;
				long dossierIdFromDossier;
				int indexVoting = 1;

				for(DossierDataModel dossierModel : dossiers) {
					//each dossier
					dossierIdFromDossier = dossierModel.getDossierId();
					indexVoting = 1;
					for (int i = 0; i < size; i++) {
						//each voting
						if(Validator.isNull(listVoting.get(i))) {
							continue;
						}

						if(Validator.isNull(listVoting.get(i)[0])) {
							//No classPK
							continue;
						}

						classPK = GetterUtil.getLong(listVoting.get(i)[0]);
						if(classPK == 0) {
							//classPK is string or = 0
							continue;
						}
						if(dossierIdFromDossier == classPK) {
							votingCode = Validator.isNotNull(listVoting.get(i)[1]) ? (String) listVoting.get(i)[1] : "";
							votingName = Validator.isNotNull(listVoting.get(i)[3]) ? (String) listVoting.get(i)[3] : "";
							point      = Validator.isNotNull(listVoting.get(i)[4]) ? (Integer) listVoting.get(i)[4] : 0;

							if(indexVoting == 1) {
								dossierModel.setVotingCode1(votingCode);
								dossierModel.setVotingName1(votingName);
								dossierModel.setResultVotingCode1(point);
							} else if(indexVoting == 2) {
								dossierModel.setVotingCode2(votingCode);
								dossierModel.setVotingName2(votingName);
								dossierModel.setResultVotingCode2(point);
							} else if(indexVoting == 3) {
								dossierModel.setVotingCode3(votingCode);
								dossierModel.setVotingName3(votingName);
								dossierModel.setResultVotingCode3(point);
								break;
							}
							indexVoting++;
						}
					}
				}
			}

			return Response.status(HttpURLConnection.HTTP_OK).entity(results).build();
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	// LamTV: Process dossierTodo
	@SuppressWarnings({
		"unchecked"
	})
	@Override
	public Response getDossierProcessList(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		DossierSearchModel query) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		long userId = user.getUserId();
		// _log.info("userId: "+userId);
		BackendAuth auth = new BackendAuthImpl();
		DossierPermission dossierPermission = new DossierPermission();
		DossierActions actions = new DossierActionsImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			boolean isCitizen = dossierPermission.isCitizen(user.getUserId());
			dossierPermission.hasGetDossiers(
				groupId, user.getUserId(), query.getSecetKey());

			if (query.getEnd() == 0) {
				query.setStart(-1);
				query.setEnd(-1);
			}

			LinkedHashMap<String, Object> params =
				new LinkedHashMap<String, Object>();
			params.put(Field.GROUP_ID, String.valueOf(groupId));
			// LamTV_Process search LIKE
			String keywordSearch = query.getKeyword();
			String keySearch = StringPool.BLANK;
			if (Validator.isNotNull(keywordSearch)) {
				keySearch = SpecialCharacterUtils.splitSpecial(keywordSearch);
			}
			params.put(Field.KEYWORD_SEARCH, keySearch);

			String status = query.getStatus();
			String substatus = query.getSubstatus();
			String step = query.getStep();
			// _log.info("step: "+step);
			StringBuilder strStatusStep = null;
			StringBuilder strSubStatusStep = null;
			if (Validator.isNotNull(step)) {
				strStatusStep = new StringBuilder();
				strSubStatusStep = new StringBuilder();
				String[] stepArr = step.split(StringPool.COMMA);
				if (stepArr != null && stepArr.length > 0) {
					String statusStep;
					String subStatusStep;
					for (int i = 0; i < stepArr.length; i++) {
						StepConfig stepConfig =
							StepConfigLocalServiceUtil.getByCode(
								groupId, stepArr[i]);
						if (stepConfig != null) {
							statusStep = stepConfig.getDossierStatus();
							subStatusStep = stepConfig.getDossierSubStatus();
							if (i == 0) {
								strStatusStep.append(statusStep);
								if (Validator.isNotNull(subStatusStep)) {
									strSubStatusStep.append(subStatusStep);
								}
								else {
									strSubStatusStep.append(DossierTerm.EMPTY);
								}
							}
							else {
								strStatusStep.append(StringPool.COMMA);
								strStatusStep.append(statusStep);
								strSubStatusStep.append(StringPool.COMMA);
								if (Validator.isNotNull(subStatusStep)) {
									strSubStatusStep.append(subStatusStep);
								}
								else {
									strSubStatusStep.append(DossierTerm.EMPTY);
								}
							}
						}
					}
				}
			}

			Integer assigned = query.getAssigned();
			StringBuilder strAssignedUserId = null;
			if (assigned != null && Validator.isNotNull(step)) {
				String[] stepArr = step.split(StringPool.COMMA);
				strAssignedUserId = new StringBuilder();
				if (stepArr != null && stepArr.length > 0) {
					for (int i = 0; i < stepArr.length; i++) {
						if (i == 0) {
							StringBuilder sbParams = new StringBuilder();
							if (stepArr[i].contains(DossierTerm.STEP_X)) {
								for (int j = 0; j < 9; j++) {
									if (j == 0) {
										sbParams.append(
											userId + StringPool.UNDERLINE +
												stepArr[i].replace(
													DossierTerm.STEP_X, String.valueOf(j)) +
												StringPool.UNDERLINE + assigned);
									}
									else {
										sbParams.append(StringPool.COMMA);
										sbParams.append(
											userId + StringPool.UNDERLINE +
												stepArr[i].replace(
													DossierTerm.STEP_X, String.valueOf(j)) +
												StringPool.UNDERLINE + assigned);
									}
								}
							}
							else {
								sbParams.append(
									userId + StringPool.UNDERLINE + stepArr[i] + StringPool.UNDERLINE + assigned);
							}
							//
							strAssignedUserId.append(sbParams.toString());
						}
						else {
							strAssignedUserId.append(StringPool.COMMA);
							//
							StringBuilder sbParams = new StringBuilder();
							if (stepArr[i].contains(DossierTerm.STEP_X)) {
								for (int j = 0; j < 9; j++) {
									if (j == 0) {
										sbParams.append(
											userId + StringPool.UNDERLINE +
												stepArr[i].replace(
													DossierTerm.STEP_X, String.valueOf(j)) +
												StringPool.UNDERLINE + assigned);
									}
									else {
										sbParams.append(StringPool.COMMA);
										sbParams.append(
											userId + StringPool.UNDERLINE +
												stepArr[i].replace(
													DossierTerm.STEP_X, String.valueOf(j)) +
												StringPool.UNDERLINE + assigned);
									}
								}
							}
							else {
								sbParams.append(
									userId + StringPool.UNDERLINE + stepArr[i] + StringPool.UNDERLINE + assigned);
							}
							//
							strAssignedUserId.append(sbParams.toString());
						}
					}
				}
				params.put(
					DossierTerm.ASSIGNED_USER_ID, strAssignedUserId.toString());
			}

			String agencys = query.getAgency();
			String serviceCode = query.getService();
			String service = StringPool.BLANK;
			if (Validator.isNotNull(serviceCode)) {
				service = SpecialCharacterUtils.splitSpecial(serviceCode);
			}
			String templateNo = query.getTemplate();
			String template = StringPool.BLANK;
			if (Validator.isNotNull(templateNo)) {
				template = SpecialCharacterUtils.splitSpecial(templateNo);
			}
			String owner = query.getOwner();
			// If user is citizen then default owner true
			if (isCitizen) {
				owner = String.valueOf(true);
			}
			else {
				if (Validator.isNull(agencys)) {
					Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, userId);
					if (employee != null && Validator.isNotNull(employee.getScope())) {
						agencys = employee.getScope();
					}
				}
			}
			if (Boolean.valueOf(query.getSpecialKey())) {
				owner = String.valueOf(false);
			}
			String follow = query.getFollow();
			String submitting = query.getSubmitting();
			// Process Top using statistic
			int year = query.getYear();
			int month = query.getMonth();
			String top = query.getTop();
			if (Validator.isNotNull(top) &&
				DossierTerm.STATISTIC.equals(top.toLowerCase())) {
				Calendar baseDateCal = Calendar.getInstance();
				baseDateCal.setTime(new Date());
				if (month == 0) {
					month = baseDateCal.get(Calendar.MONTH) + 1;
				}
				if (year == 0) {
					year = baseDateCal.get(Calendar.YEAR);
				}
			}
			// _log.info("month: "+month);
			// _log.info("year: "+year);

			String state = query.getState();
			String dossierIdNo = query.getDossierNo();
			// _log.info("dossierIdNo: "+dossierIdNo);
			String dossierNoSearch = StringPool.BLANK;
			if (Validator.isNotNull(dossierIdNo)) {
				dossierNoSearch =
					SpecialCharacterUtils.splitSpecial(dossierIdNo);
			}
			// _log.info("dossierNoSearch: "+dossierNoSearch);
			String soChungChi = query.getSoChungChi();
			String certNo = StringPool.BLANK;
			if (Validator.isNotNull(soChungChi)) {
				certNo = SpecialCharacterUtils.splitSpecial(soChungChi);
			}

			String fromReceiveDate =
				APIDateTimeUtils.convertNormalDateToLuceneDate(
					query.getFromReceiveDate());
			String toReceiveDate =
				APIDateTimeUtils.convertNormalDateToLuceneDate(
					query.getToReceiveDate());
			String fromCertDate =
				APIDateTimeUtils.convertNormalDateToLuceneDate(
					query.getTuNgayKyCc());
			String toCertDate = APIDateTimeUtils.convertNormalDateToLuceneDate(
				query.getDenNgayKyCc());
			String fromSubmitDate =
				APIDateTimeUtils.convertNormalDateToLuceneDate(
					query.getFromSubmitDate());
			String toSubmitDate =
				APIDateTimeUtils.convertNormalDateToLuceneDate(
					query.getToSubmitDate());

			String fromDueDate =
					APIDateTimeUtils.convertNormalDateToLuceneDate(
						query.getFromDueDate());
			String toDueDate =
				APIDateTimeUtils.convertNormalDateToLuceneDate(
					query.getToDueDate());

			String fromReleaseDate =
					APIDateTimeUtils.convertNormalDateToLuceneDate(
						query.getFromReleaseDate());
			String toReleaseDate =
				APIDateTimeUtils.convertNormalDateToLuceneDate(
					query.getToReleaseDate());

			String fromFinishDate =
					APIDateTimeUtils.convertNormalDateToLuceneDate(
						query.getFromFinishDate());
			String toFinishDate =
				APIDateTimeUtils.convertNormalDateToLuceneDate(
					query.getToFinishDate());

			String dossierIdCTN = query.getDossierIdCTN();
			String domain = query.getDomain();
			String domainName = query.getDomainName();
			String applicantName = query.getApplicantName();
			String applicantIdNo = query.getApplicantIdNo();
			String serviceName = query.getServiceName();

			params.put(DossierTerm.ONLINE, query.getOnline());
			params.put(DossierTerm.STATUS, status);
			params.put(DossierTerm.SUBSTATUS, substatus);
			params.put(DossierTerm.AGENCYS, agencys);
			params.put(DossierTerm.SERVICE, service);
			params.put(DossierTerm.TEMPLATE, template);
			params.put(DossierTerm.OWNER, owner);
			params.put(DossierTerm.SUBMITTING, submitting);
			params.put(DossierTerm.FOLLOW, follow);
			params.put(DossierTerm.TOP, top);
			params.put(DossierTerm.YEAR, year);
			params.put(DossierTerm.MONTH, month);
			params.put(DossierTerm.DAY, query.getDay());
			// backend.auth.api.BackendAuth auth2 = new
			// backend.auth.api.BackendAuthImpl();
			// Check role follow dossier
			boolean isAdmin = false;
			List<Role> roles =
				RoleLocalServiceUtil.getUserRoles(user.getUserId());
			if (roles != null && roles.size() > 0) {
				for (Role role : roles) {
					// LamTV_Fix sonarqube
					if (ConstantUtils.ROLE_ADMIN.equals(role.getName())) {
						isAdmin = true;
						break;
					}
					if (ConstantUtils.ROLE_ADMIN_DATA.equals(role.getName())) {
						isAdmin = true;
						break;
					}
				}
			}
			if (isAdmin) {
			}
			else {
				params.put(DossierTerm.USER_ID, user.getUserId());
			}

			// SystemId
			String strSystemId = query.getSystemId();
			if (Validator.isNotNull(strSystemId)) {
				params.put(DossierTerm.SYSTEM_ID, strSystemId);
			}
			else {
				params.put(DossierTerm.SYSTEM_ID, 0);
			}

			//ViaPostal
			String viaPostal = query.getViapostal();
			if (viaPostal != null) {
				params.put(DossierTerm.VIA_POSTAL, viaPostal);
			}
			params.put(DossierTerm.SECET_KEY, query.getSecetKey());
			params.put(DossierTerm.STATE, state);
			params.put(DossierTerm.DOSSIER_NO, dossierNoSearch);
			params.put(DossierTerm.CERT_NO, certNo);
			params.put(DossierTerm.FROM_RECEIVEDATE, fromReceiveDate);
			params.put(DossierTerm.TO_RECEIVEDATE, toReceiveDate);
			params.put(DossierTerm.FROM_DUEDATE, fromDueDate);
			params.put(DossierTerm.TO_DUEDATE, toDueDate);

			params.put(DossierTerm.FROM_RELEASE_DATE, fromReleaseDate);
			params.put(DossierTerm.TO_RELEASE_DATE, toReleaseDate);
			params.put(DossierTerm.FROM_FINISH_DATE, fromFinishDate);
			params.put(DossierTerm.TO_FINISH_DATE, toFinishDate);

			params.put(DossierTerm.FROM_CERT_DATE, fromCertDate);
			params.put(DossierTerm.TO_CERT_DATE, toCertDate);
			params.put(DossierTerm.DOSSIER_ID_CTN, dossierIdCTN);
			params.put(DossierTerm.FROM_SUBMIT_DATE, fromSubmitDate);
			params.put(DossierTerm.TO_SUBMIT_DATE, toSubmitDate);
			if (Validator.isNotNull(domain)) {
				params.put(DossierTerm.DOMAIN_CODE, domain);
			}
			params.put(DossierTerm.DOMAIN_NAME, domainName);
			params.put(DossierTerm.APPLICANT_NAME, applicantName);
			params.put(DossierTerm.APPLICANT_ID_NO, applicantIdNo);
			params.put(DossierTerm.SERVICE_NAME, serviceName);
			params.put(
				PaymentFileTerm.PAYMENT_STATUS, query.getPaymentStatus());
			// Process follow StepCode
			if (Validator.isNotNull(strStatusStep)) {
				params.put(
					DossierTerm.DOSSIER_STATUS_STEP, strStatusStep.toString());
			}
			else {
				params.put(DossierTerm.DOSSIER_STATUS_STEP, StringPool.BLANK);
			}
			if (Validator.isNotNull(strSubStatusStep)) {
				params.put(
					DossierTerm.DOSSIER_SUBSTATUS_STEP,
					strSubStatusStep.toString());
			}
			else {
				params.put(
					DossierTerm.DOSSIER_SUBSTATUS_STEP, StringPool.BLANK);
			}
			// if (auth2.isAdmin(serviceContext, "admin")) {
			if (isAdmin) {
			}
			else {
				String permission =
					user.getUserId() + StringPool.UNDERLINE + ConstantUtils.PERMISSION_WRITE;
				params.put(DossierTerm.MAPPING_PERMISSION, permission);
			}

			// Add param original
			params.put(DossierTerm.ORIGINALLITY, query.getOriginality());
			params.put(DossierTerm.GROUP_DOSSIER_ID, query.getGroupDossierId());
			if(Validator.isNotNull(query.getGroupDossierIdHs())){
				params.put(DossierTerm.GROUP_DOSSIER_ID_HS, query.getGroupDossierIdHs());
			}
			params.put(DossierTerm.REGISTER, query.getRegister());

			String vnpostalStatus = query.getVnpostalStatus();
			if (vnpostalStatus != null) {
				params.put(DossierTerm.VNPOSTAL_STATUS, vnpostalStatus);
			}

			Integer fromViaPostal = query.getFromViaPostal();
			if (fromViaPostal != null) {
				params.put(DossierTerm.FROM_VIA_POSTAL, fromViaPostal);
			}

			Sort[] sorts = null;
			if (Validator.isNull(query.getSort())) {
				String dateSort = String.format(MessageUtil.getMessage(ConstantUtils.QUERY_NUMBER_SORT), DossierTerm.CREATE_DATE);
				sorts = new Sort[] {
					SortFactoryUtil.create(
						dateSort, Sort.LONG_TYPE,
						GetterUtil.getBoolean(query.getOrder()))
				};
			}
			else {
				String querySort = String.format(MessageUtil.getMessage(ConstantUtils.QUERY_STRING_SORT), query.getSort());
				sorts = new Sort[] {
					SortFactoryUtil.create(
						querySort, Sort.STRING_TYPE,
						GetterUtil.getBoolean(query.getOrder()))
				};
			}

			if (Validator.isNotNull(top)) {
				String querySort ="";
				switch (top) {
				case DossierTerm.RECEIVE:
					querySort = String.format(MessageUtil.getMessage(ConstantUtils.QUERY_NUMBER_SORT), DossierTerm.RECEIVE_DATE_TIMESTAMP);
					sorts = new Sort[] {
						SortFactoryUtil.create(
							querySort,
							Sort.LONG_TYPE, false)
					};
					break;
				case DossierTerm.OVERDUE:
					querySort = String.format(MessageUtil.getMessage(ConstantUtils.QUERY_NUMBER_SORT), DossierTerm.DUE_DATE_TIMESTAMP);
					sorts = new Sort[] {
						SortFactoryUtil.create(
							querySort,
							Sort.LONG_TYPE, false)
					};
					break;
				case DossierTerm.RELEASE:
					querySort = String.format(MessageUtil.getMessage(ConstantUtils.QUERY_NUMBER_SORT), DossierTerm.RELEASE_DATE_TIMESTAMP);
					sorts = new Sort[] {
						SortFactoryUtil.create(
							querySort,
							Sort.LONG_TYPE, false)
					};
					break;
				case DossierTerm.CANCELLING:
					querySort = String.format(MessageUtil.getMessage(ConstantUtils.QUERY_NUMBER_SORT), DossierTerm.CANCELLING_DATE_TIMESTAMP);
					sorts = new Sort[] {
						SortFactoryUtil.create(
							querySort,
							Sort.LONG_TYPE, false)
					};
					break;
				case DossierTerm.CORRECTING:
					querySort = String.format(MessageUtil.getMessage(ConstantUtils.QUERY_NUMBER_SORT), DossierTerm.CORRECTING_DATE_TIMESTAMP);
					sorts = new Sort[] {
						SortFactoryUtil.create(
							querySort,
							Sort.LONG_TYPE, false)
					};
					break;
				default:
					break;
				}
			}

			JSONObject jsonData = actions.getDossierProcessList(
				user.getUserId(), company.getCompanyId(), groupId, params,
				sorts, query.getStart(), query.getEnd(), serviceContext);

			DossierResultsModel results = new DossierResultsModel();
			if (jsonData != null && jsonData.getInt(ConstantUtils.TOTAL) > 0) {
				results.setTotal(jsonData.getInt(ConstantUtils.TOTAL));
				results.getData().addAll(
					DossierUtils.mappingForGetList(
						(List<Document>) jsonData.get(ConstantUtils.DATA), userId,
						query.getAssigned(),query));
			}
			else {
				results.setTotal(0);
			}

			return Response.status(HttpURLConnection.HTTP_OK).entity(results).build();

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response addDossier(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		DossierInputModel input) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		try {
			Dossier dossier = CPSDossierBusinessLocalServiceUtil.addDossier(
				groupId, company, user, serviceContext,
				DossierUtils.convertFormModelToInputModel(input));
			DossierDetailModel result =
				DossierUtils.mappingForGetDetail(dossier, user.getUserId());
			return Response.status(HttpStatus.SC_OK).entity(result).build();
		}
		catch (Exception e) {
			_log.debug(e);
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getDetailDossier(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, String id,
		String secretKey) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		String secretCode =
			GetterUtil.getString(header.getHeaderString(DossierTerm.SECRET_CODE));
		// _log.info("secretCode: "+secretCode);
		// _log.info("secretKey: "+secretKey);
		DossierPermission dossierPermission = new DossierPermission();
		BackendAuth auth = new BackendAuthImpl();

		try {

			if (Validator.isNotNull(secretKey)) {
				try {
					Dossier dossier = DossierUtils.getDossier(id, groupId);

					List<Role> userRoles = user.getRoles();
					boolean isAdmin = false;
					for (Role r : userRoles) {
						if (r.getName().startsWith(ConstantUtils.ROLE_ADMIN)) {
							isAdmin = true;
							break;
						}
					}

					if (!isAdmin) {
						dossierPermission.checkPassword(dossier, secretKey);
					}

					DossierDetailModel result =
						DossierUtils.mappingForGetDetail(
							dossier, user.getUserId());

					return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();
				}
				catch (Exception e) {
					_log.debug(e);
					return Response.status(
						HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(
							MessageUtil.getMessage(ConstantUtils.DOSSIER_MESSAGE_SECRETKEYNOTSUCCESS)).build();
				}

			}
			else if (Validator.isNotNull(secretCode)) {
				try {
					Dossier dossier = DossierUtils.getDossier(id, groupId);

					dossierPermission.checkPassword(dossier, secretCode);

					DossierDetailModel result =
						DossierUtils.mappingForGetDetail(
							dossier, user.getUserId());

					return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();
				}
				catch (Exception e) {
					_log.debug(e);
					return Response.status(
						HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(
								MessageUtil.getMessage(ConstantUtils.DOSSIER_MESSAGE_SECRETKEYNOTSUCCESS)).build();
				}
			}
			else {
				// _log.info("START");
				if (!auth.isAuth(serviceContext)) {
					throw new UnauthenticationException();
				}

				Dossier dossier = DossierUtils.getDossier(id, groupId);
				// _log.info("dossier: "+dossier);

				// ProcessOption option =
				// getProcessOption(dossier.getServiceCode(),
				// dossier.getGovAgencyCode(),
				// dossier.getDossierTemplateNo(), groupId);
				// dossierPermission.hasGetDetailDossier(groupId,
				// user.getUserId(), dossier, option.getServiceProcessId());

				DossierDetailModel result =
					DossierUtils.mappingForGetDetail(dossier, user.getUserId());

				return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

			}

		}
		catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response updateDossier(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, long id,
		DossierInputModel input) {

		_log.info("TRACE_LOG_INFO INPUT: "+JSONFactoryUtil.looseSerialize(input));
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		BackendAuth auth = new BackendAuthImpl();

		DossierActions actions = new DossierActionsImpl();
		DossierPermission dossierPermission = new DossierPermission();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			// if (!auth.hasResource(serviceContext,
			// DossierTemplate.class.getName(), ActionKeys.ADD_ENTRY)) {
			// throw new UnauthorizationException();
			// }

			dossierPermission.hasCreateDossier(
				groupId, user.getUserId(), input.getServiceCode(),
				input.getGovAgencyCode(), input.getDossierTemplateNo());

			// int counter = 0;
			// String referenceUid = StringPool.BLANK;
			//
			// ProcessOption option = getProcessOption(input.getServiceCode(),
			// input.getGovAgencyCode(),
			// input.getDossierTemplateNo(), groupId);
			//
			// ServiceProcess process =
			// ServiceProcessLocalServiceUtil.getServiceProcess(option.getServiceProcessId());

			// if (referenceUid.trim().length() == 0)
			// referenceUid =
			// DossierNumberGenerator.generateReferenceUID(groupId);

			// String serviceName = getServiceName(input.getServiceCode(),
			// groupId);

			// String govAgencyName = getDictItemName(groupId,
			// GOVERNMENT_AGENCY, input.getGovAgencyCode());

			String cityName = StringPool.BLANK;
			String districtName = StringPool.BLANK;
			String wardName = StringPool.BLANK;
			String postalCityName = StringPool.BLANK;
			String postalDistrictName = StringPool.BLANK;

			if (Validator.isNotNull(input.getCityCode()))
				cityName = getDictItemName(
					groupId, ADMINISTRATIVE_REGION, input.getCityCode());
			if (Validator.isNotNull(input.getDistrictCode()))
				districtName = getDictItemName(
					groupId, ADMINISTRATIVE_REGION, input.getDistrictCode());
			if (Validator.isNotNull(input.getWardCode()))
				wardName = getDictItemName(
					groupId, ADMINISTRATIVE_REGION, input.getWardCode());

			if (Validator.isNotNull(input.getPostalCityCode())) {
				postalCityName = getDictItemName(
					groupId, VNPOST_CITY_CODE, input.getPostalCityCode());
			}
			if (Validator.isNotNull(input.getPostalDistrictCode())) {
				postalDistrictName = getDictItemName(
					groupId, VNPOST_CITY_CODE, input.getPostalDistrictCode());
			}
			Integer delegateType =
				(input.getDelegateType() != null ? input.getDelegateType() : 0);
			String documentNo = input.getDocumentNo();
			Date documentDate = null;
			if (input.getDocumentDate() != null &&
				Validator.isNotNull(input.getDocumentDate())) {
				documentDate = new Date(input.getDocumentDate());
			}

			String vnpostalProfile = input.getVnpostalProfile();
			Integer vnpostalStatus = input.getVnpostalStatus();
			if (Validator.isNotNull(vnpostalProfile) && Validator.isNull(vnpostalStatus)) {
				vnpostalStatus = VnpostCollectionTerm.VNPOSTAL_STAUS_1;
			}
			//
			int systemId = input.getSystemId() != null ? input.getSystemId() : 0;


			int durationCount = 0;
			try {
				String metaData = input.getMetaData();
				if (Validator.isNotNull(metaData)) {
					JSONObject metaJSON = JSONFactoryUtil.createJSONObject(metaData);
					if (metaJSON.has("durationCountMeta") && metaJSON.get("durationCountMeta") != null) {
						durationCount = metaJSON.getInt("durationCountMeta");
						if (durationCount == 2 || durationCount == 6) durationCount -= 1;
						if (durationCount == 4) durationCount += 1;
					}
				}
			}
			catch (JSONException ex) {
				_log.debug(ex);
			}
			// Dossier dossier = actions.initDossier(groupId, id, referenceUid,
			// counter, input.getServiceCode(),
			// StringPool.BLANK, StringPool.BLANK, StringPool.BLANK,
			// input.getApplicantName(),
			// input.getApplicantIdType(), input.getApplicantIdNo(),
			// input.getApplicantIdDate(),
			// input.getAddress(), input.getCityCode(), cityName,
			// input.getDistrictCode(), districtName,
			// input.getWardCode(), wardName, input.getContactName(),
			// input.getContactTelNo(),
			// input.getContactEmail(), input.getDossierTemplateNo(), password,
			// input.getViaPostal(),
			// input.getPostalAddress(), input.getPostalCityCode(),
			// postalCityName, input.getPostalTelNo(), online,
			// true, input.getApplicantNote(),
			// Integer.valueOf(input.getOriginality()), serviceContext);
			//
			_log.debug("UPDATE DOSSIER: " + input.getCityCode());
			
			Dossier dossier = actions.initUpdateDossierFull(groupId, id, input.getApplicantName(),
					input.getApplicantIdType(), input.getApplicantIdNo(), input.getApplicantIdDate(),
					input.getAddress(), input.getCityCode(), cityName, input.getDistrictCode(), districtName,
					input.getWardCode(), wardName, input.getContactName(), input.getContactTelNo(),
					input.getContactEmail(), input.getDossierTemplateNo(), input.getViaPostal(),
					input.getPostalAddress(), input.getPostalCityCode(), postalCityName, input.getPostalDistrictCode(),
					postalDistrictName, input.getPostalTelNo(), input.getApplicantNote(), input.isSameAsApplicant(),
					input.getDelegateName(), input.getDelegateIdNo(), input.getDelegateTelNo(),
					input.getDelegateEmail(), input.getDelegateAddress(), input.getDelegateCityCode(),
					input.getDelegateDistrictCode(), input.getDelegateWardCode(), input.getSampleCount(),
					input.getDossierName(), input.getBriefNote(), delegateType, documentNo, documentDate, systemId,
					vnpostalStatus, vnpostalProfile, input.getFromViaPostal(), input.getFormMeta(), input.getDueDate(),
					durationCount, serviceContext);

			DossierDetailModel result =
				DossierUtils.mappingForGetDetail(dossier, user.getUserId());
			_log.info("TRACE_LOG_INFO RESULT: "+JSONFactoryUtil.looseSerialize(result));

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response updateDossierByDossierNo(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
											 ServiceContext serviceContext, String dossierNo, DossierInputModel input) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		BackendAuth auth = new BackendAuthImpl();
		DossierDetailModel result = new DossierDetailModel();
		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			_log.info("===================== groupId " + groupId);

			_log.info("===================== dossierNo " + dossierNo);

			_log.info("===================== postalCodeSend " + input.getPostalCodeSend());

			String postalCodeSend = input.getPostalCodeSend();
			int viaPostal = input.getViaPostal();

			 Dossier dossier  = DossierLocalServiceUtil.fetchByDO_NO_GROUP(dossierNo, groupId);

			_log.debug("UPDATE DOSSIER: " + dossier.getDossierNo());

			if ( Validator.isNotNull(viaPostal)) {
				dossier.setViaPostal(viaPostal);
			}
			if ( Validator.isNotNull(postalCodeSend)) {
				dossier.setPostalCodeSend(postalCodeSend);
			}

			if(dossier != null) {
				Dossier dossierUpdate = DossierLocalServiceUtil.updateDossier(dossier);
				if(dossierUpdate !=null) {
					result = DossierUtils.mappingForGetDetail(dossierUpdate, user.getUserId());
				}
				_log.info("TRACE_LOG_INFO result upadte Dossier: "+ JSONFactoryUtil.looseSerialize(result));
			}
			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

		}catch (Exception e){
			e.printStackTrace();
			_log.error(e.getMessage());
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response removeDossier(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, String id) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		BackendAuth auth = new BackendAuthImpl();
		DossierActions actions = new DossierActionsImpl();
		// DossierPermission dossierPermission = new DossierPermission();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			Dossier dossier = DossierUtils.getDossier(id, groupId);
			if (dossier != null) {
				int originality = dossier.getOriginality();
				Dossier removeDossier = null;
				if (originality > 0) {
					dossier.setOriginality(-originality);
					removeDossier =
						DossierLocalServiceUtil.updateDossier(dossier);
					if (removeDossier != null &&
						originality > DossierTerm.ORIGINALITY_DVCTT) {
						DossierMgtUtils.processSyncDeleteDossier(
							removeDossier, originality);
					}
				}
				else {
					removeDossier = actions.removeDossier(
						groupId, dossier.getDossierId(),
						dossier.getReferenceUid());
				}

				DossierDetailModel result = null;
				if (removeDossier != null) {
					result = DossierUtils.mappingForGetDetail(
						removeDossier, user.getUserId());
				}
				return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();
			}
			else {
				return Response.status(HttpServletResponse.SC_FORBIDDEN).entity(
					MessageUtil.getMessage(ConstantUtils.DOSSIER_MESSAGE_NOTFOUND)).build();
			}
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response cancellingDossier(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, String id) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		BackendAuth auth = new BackendAuthImpl();
		DossierActions actions = new DossierActionsImpl();

		// DossierPermission dossierPermission = new DossierPermission();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			Dossier dossier = DossierUtils.getDossier(id, groupId);

			// dossierPermission.allowSubmitting(user.getUserId(),
			// dossier.getDossierId());

			Dossier cancellingDossier = actions.cancelDossier(
				groupId, dossier.getDossierId(), dossier.getReferenceUid(),
				serviceContext);

			DossierDetailModel result = DossierUtils.mappingForGetDetail(
				cancellingDossier, user.getUserId());

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response correctingDossier(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, String id) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		BackendAuth auth = new BackendAuthImpl();
		DossierActions actions = new DossierActionsImpl();

		// DossierPermission dossierPermission = new DossierPermission();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			Dossier dossier = DossierUtils.getDossier(id, groupId);

			// dossierPermission.allowSubmitting(user.getUserId(),
			// dossier.getDossierId());

			Dossier correctingDossier = actions.correctDossier(
				groupId, dossier.getDossierId(), dossier.getReferenceUid(),
				serviceContext);

			DossierDetailModel result = DossierUtils.mappingForGetDetail(
				correctingDossier, user.getUserId());

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response submittingDossier(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, String id) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		BackendAuth auth = new BackendAuthImpl();

		DossierActions actions = new DossierActionsImpl();

		DossierPermission dossierPermission = new DossierPermission();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			Dossier dossier = DossierUtils.getDossier(id, groupId);

			dossierPermission.allowSubmitting(
				user.getUserId(), dossier.getDossierId());

			Dossier submittedDossier = actions.submitDossier(
				groupId, dossier.getDossierId(), dossier.getReferenceUid(),
				serviceContext);

			DossierDetailModel result = DossierUtils.mappingForGetDetail(
				submittedDossier, user.getUserId());

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response resetDossier(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, String id) {

		// RESET submitting
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		// DossierPermission dossierPermission = new DossierPermission();
		// BackendAuth auth = new BackendAuthImpl();
		DossierActions actions = new DossierActionsImpl();
		try {
			// isSyncAction equal 1 that is the action was processed by
			// DossierPushScheduler
			Dossier dossier = DossierUtils.getDossier(id, groupId);

			Dossier dossierResetted = actions.resetDossier(
				groupId, dossier.getDossierId(), dossier.getReferenceUid(),
				serviceContext);

			return Response.status(HttpURLConnection.HTTP_OK).entity(
				DossierUtils.mappingForGetDetail(
					dossierResetted, user.getUserId())).build();

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	// LamTV: Process DoAction
	@Override
	public Response doAction(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, String id,
		DoActionModel input, Long dueDate) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		long userId = user.getUserId();
		// DossierPermission dossierPermission = new DossierPermission();
		BackendAuth auth = new BackendAuthImpl();
		DossierActions actions = new DossierActionsImpl();
		DossierAction dossierResult = null;
		ProcessAction processActionCurrent = null;
		ErrorMsgModel errorModel = new ErrorMsgModel();
		String actionUser = input.getActionUser();
		Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(
			groupId, user.getUserId());
		if (employee != null) {
			actionUser = employee.getFullName();
		}
		else {
			if (Validator.isNull(actionUser)) {
				actionUser = user.getFullName();
			}
		}

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			Dossier dossier = DossierUtils.getDossier(id, groupId);

			_log.info("TRACE_LOG_INFO doAction dueDate: "+dueDate);
			_log.info(1111);
			_log.info(dossier);
			_log.info(111111111);
			_log.debug("LamTV-input: " + JSONFactoryUtil.looseSerialize(input));
			_log.debug(
				"LamTV-Call in groupId: " + groupId + "|dossierId: " + id +
					" |userId: " + userId);

			if (dossier != null) {
				_log.info(2222);
				_log.debug(
					"Dossier: " + dossier + ", action code: " +
						input.getActionCode());

				if (Validator.isNotNull(dueDate)) {
					DossierLocalServiceUtil.updateDueDate(
						groupId, dossier.getDossierId(),
						dossier.getReferenceUid(), new Date(dueDate),
						serviceContext);
				}
				String actionCode = input.getActionCode();
				// add by phuchn - thao tac gop
				List<String> actionCodes = null;
				if (Validator.isNotNull(actionCode) && actionCode.split(",").length > 1) {
					actionCodes = Arrays.asList(actionCode.split(","));
					actionCode = getActionCode(groupId, actionCodes, dossier, user);
				}
				_log.info(3333);
				if (Validator.isNotNull(actionCode)) {
					_log.info(44444);
					ActionConfig actConfig =
						ActionConfigLocalServiceUtil.getByCode(
							groupId, actionCode);
					_log.debug("Action config: " + actConfig);
					String serviceCode = dossier.getServiceCode();
					String govAgencyCode = dossier.getGovAgencyCode();
					String dossierTempNo = dossier.getDossierTemplateNo();
					if (actConfig != null) {
						_log.info(5555);
						boolean insideProcess = actConfig.getInsideProcess();
						ProcessOption option = DossierUtils.getProcessOption(
							serviceCode, govAgencyCode, dossierTempNo, groupId);
						if (insideProcess) {
							if (dossier.getDossierActionId() == 0) {
								if (option != null) {
									long serviceProcessId =
										option.getServiceProcessId();
									ProcessAction proAction =
										DossierUtils.getProcessAction(
											user,
											groupId, dossier, actionCode,
											serviceProcessId);
									if (proAction != null) {
										processActionCurrent = proAction;
										_log.debug(
											"DO ACTION: " +
												proAction.getActionCode());
										dossierResult = actions.doAction(
											groupId, userId, dossier, option,
											proAction, actionCode, actionUser,
											input.getActionNote(),
											input.getPayload(),
											input.getAssignUsers(),
											input.getPayment(),
											actConfig.getSyncType(),
											serviceContext, errorModel);
									}
									else {
										// TODO: Error
									}
								}
							}
							else {
								DossierAction dossierAction =
									DossierActionLocalServiceUtil.fetchDossierAction(
										dossier.getDossierActionId());
								if (dossierAction != null) {
									long serviceProcessId =
										dossierAction.getServiceProcessId();
									DossierTemplate dossierTemplate =
										DossierTemplateLocalServiceUtil.getByTemplateNo(
											groupId,
											dossier.getDossierTemplateNo());

									ProcessOption oldOption =
										ProcessOptionLocalServiceUtil.fetchBySP_DT(
											serviceProcessId,
											dossierTemplate.getDossierTemplateId());

									ProcessAction proAction =
										DossierUtils.getProcessAction(
											user,
											groupId, dossier, actionCode,
											serviceProcessId);
									if (proAction != null) {
										processActionCurrent = proAction;
										_log.debug(
											"DO ACTION: " +
												proAction.getActionCode());
										dossierResult = actions.doAction(
											groupId, userId, dossier, oldOption,
											proAction, actionCode, actionUser,
											input.getActionNote(),
											input.getPayload(),
											input.getAssignUsers(),
											input.getPayment(),
											actConfig.getSyncType(),
											serviceContext, errorModel);
									}
									else {
										// TODO: Error
									}
								}
							}
							// if (dossierResult != null) {
							// String actionCodeResult =
							// dossierResult.getActionCode();
							// _log.info("actionCodeResult: "+actionCodeResult);
							// if (Validator.isNotNull(actionCodeResult)) {
							// ActionConfig actConfigResult =
							// ActionConfigLocalServiceUtil.getByCode(groupId,
							// actionCodeResult);
							// int dateOption = actConfigResult.getDateOption();
							// _log.info("dateOption: "+dateOption);
							// _log.info("dossierResult.getDossierActionId():
							// "+dossierResult.getDossierActionId());
							// if (dateOption ==
							// DossierTerm.DATE_OPTION_CAL_WAITING) {
							// DossierAction dActEnd =
							// DossierActionLocalServiceUtil
							// .fetchDossierAction(dossierResult.getDossierActionId());
							// if (dActEnd != null) {
							// _log.info("dActEnd.getPreviousActionId():
							// "+dActEnd.getPreviousActionId());
							// DossierAction dActStart =
							// DossierActionLocalServiceUtil
							// .fetchDossierAction(dActEnd.getPreviousActionId());
							// if (dActStart != null) {
							// long createEnd =
							// dActEnd.getCreateDate().getTime();
							// long createStart =
							// dActStart.getCreateDate().getTime();
							// _log.info("createStart: "+createStart);
							// _log.info("createEnd: "+createEnd);
							// if (createEnd > createStart) {
							// long extendDateTimeStamp =
							// ExtendDueDateUtils.getTimeWaitingByHoliday(createStart,
							// createEnd, groupId);
							// _log.info("extendDateTimeStamp:
							// "+extendDateTimeStamp);
							// if (extendDateTimeStamp > 0) {
							// dossier =
							// DossierLocalServiceUtil.fetchDossier(dossierResult.getDossierId());
							// long hoursCount = (long) (extendDateTimeStamp /
							// (1000 * 60 * 60));
							// _log.info("hoursCount: "+hoursCount);
							// //_log.info("dossier.getExtendDate():
							// "+dossier.getExtendDate());
							// List<Holiday> holidayList =
							// HolidayLocalServiceUtil
							// .getHolidayByGroupIdAndType(groupId, 0);
							// List<Holiday> extendWorkDayList =
							// HolidayLocalServiceUtil
							// .getHolidayByGroupIdAndType(groupId, 1);
							//
							// Date dueDateExtend =
							// HolidayUtils.getEndDate(groupId,
							// dossier.getDueDate(), hoursCount, holidayList,
							// extendWorkDayList);
							// _log.info("dueDateExtend: "+dueDateExtend);
							// if (dueDateExtend != null) {
							// dossier.setDueDate(dueDateExtend);
							// //dossier.setCorrecttingDate(null);
							// DossierLocalServiceUtil.updateDossier(dossier);
							// }
							// }
							// }
							// }
							// }
							// } else if (dateOption ==
							// DossierTerm.DATE_OPTION_CHANGE_DUE_DATE) {
							// dossier =
							// DossierLocalServiceUtil.fetchDossier(dossierResult.getDossierId());
							// if (dossier.getDueDate() != null) {
							// //dossier.setCorrecttingDate(dossier.getDueDate());
							// //dossier.setDueDate(null);
							// dossier.setLockState(DossierTerm.PAUSE_STATE);
							// DossierLocalServiceUtil.updateDossier(dossier);
							// }
							// }
							// else if (dateOption ==
							// DossierTerm.DATE_OPTION_RESET_DUE_DATE) {
							// dossier =
							// DossierLocalServiceUtil.fetchDossier(dossierResult.getDossierId());
							// if (dossier.getDueDate() != null) {
							// ServiceProcess serviceProcess =
							// ServiceProcessLocalServiceUtil
							// .fetchServiceProcess(dossierResult.getServiceProcessId());
							// if (serviceProcess != null) {
							// Date newDueDate = HolidayUtils.getDueDate(new
							// Date(),
							// serviceProcess.getDurationCount(),
							// serviceProcess.getDurationUnit(), groupId);
							// if (newDueDate != null) {
							// dossier.setDueDate(newDueDate);
							// DossierLocalServiceUtil.updateDossier(dossier);
							// }
							// }
							//
							// }
							// }
							// }
							// }
						}
						else {
							dossierResult = actions.doAction(
								groupId, userId, dossier, option, null,
								actionCode, actionUser, input.getActionNote(),
								input.getPayload(), input.getAssignUsers(),
								input.getPayment(), actConfig.getSyncType(),
								serviceContext, errorModel);
						}
						// Process send email or sms
						// if (dossierResult != null) {
						// String notificationType =
						// actConfig.getNotificationType();
						//
						// if (Validator.isNotNull(notificationType)) {
						// long notificationQueueId =
						// CounterLocalServiceUtil.increment(NotificationQueue.class.getName());
						//
						// NotificationQueue queue =
						// NotificationQueueLocalServiceUtil.createNotificationQueue(notificationQueueId);
						// Process add notification queue
						// Date now = new Date();
						//
						// Calendar cal = Calendar.getInstance();
						// cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) + 1);
						//
						// queue.setCreateDate(now);
						// queue.setModifiedDate(now);
						// queue.setGroupId(groupId);
						// queue.setCompanyId(company.getCompanyId());
						//
						// queue.setNotificationType(notificationType);
						// queue.setClassName(Dossier.class.getName());
						// queue.setClassPK(String.valueOf(dossier.getPrimaryKey()));
						// queue.setToUsername(dossier.getUserName());
						// queue.setToUserId(dossier.getUserId());
						// queue.setToEmail(dossier.getContactEmail());
						// queue.setToTelNo(dossier.getContactTelNo());
						// if (notificationType.contains("APLC")) {
						// if (dossier.getOriginality() == 3) {
						// queue.setToEmail(dossier.getDelegateEmail());
						// queue.setToTelNo(dossier.getDelegateTelNo());
						// } else {
						// queue.setToEmail(dossier.getContactEmail());
						// queue.setToTelNo(dossier.getContactTelNo());
						// }
						// } else if (notificationType.contains("EPLC")) {
						// Employee employee =
						// EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId,
						// dossier.getUserId());
						// if (employee != null) {
						// queue.setToEmail(employee.getEmail());
						// queue.setToTelNo(employee.getTelNo());
						// }
						// } else {
						// queue.setToEmail(dossier.getContactEmail());
						// queue.setToTelNo(dossier.getContactTelNo());
						// }
						//
						// JSONObject payload =
						// JSONFactoryUtil.createJSONObject();
						// try {
						// _log.info("START PAYLOAD: ");
						// payload.put(
						// "Dossier", JSONFactoryUtil.createJSONObject(
						// JSONFactoryUtil.looseSerialize(dossier)));
						// }
						// catch (JSONException parse) {
						// _log.error(parse);
						// }
						// _log.info("payloadTest: "+payload.toJSONString());
						// queue.setPayload(payload.toJSONString());
						// queue.setExpireDate(cal.getTime());
						//
						// NotificationQueueLocalServiceUtil.addNotificationQueue(queue);
						// }
						// }
					}
					else {
						ProcessOption option = DossierUtils.getProcessOption(
							serviceCode, govAgencyCode, dossierTempNo, groupId);
						if (option != null) {
							long serviceProcessId =
								option.getServiceProcessId();
							ProcessAction proAction =
								DossierUtils.getProcessAction(user,
									groupId, dossier, actionCode,
									serviceProcessId);
							if (proAction != null) {
								processActionCurrent = proAction;
								dossierResult = actions.doAction(
									groupId, userId, dossier, option, proAction,
									actionCode, actionUser,
									input.getActionNote(), input.getPayload(),
									input.getAssignUsers(), input.getPayment(),
									0, serviceContext, errorModel);
							}
							else {
								// TODO: Error
							}

						}
					}
				}
				// update notarization
				List<Notarization> list = NotarizationLocalServiceUtil.findByG_DID(groupId, dossier.getDossierId());
				if (list != null) {
					for(Notarization notarization : list) {
						if (notarization.getNotarizationNo() == 0) {
							long notarizationNoNumber = NotarizationCounterNumberGenerator.countByServiceCode(
									dossier.getServiceCode(), dossier.getGovAgencyCode());
							notarization.setNotarizationNo(notarizationNoNumber);
							notarization = NotarizationLocalServiceUtil.updateNotarization(notarization);
						}
					}
				}
				// add delegate to applicant (Thêm thông tin người nộp cho chủ hs doanh nghiệp)
				JSONObject payloadObj = JSONFactoryUtil.createJSONObject(input.getPayload());
				if(payloadObj.has("applicantIdNo") && payloadObj.has("delegateIdNo")) {
					String delegateIdNo = payloadObj.getString("delegateIdNo");
					String applicantIdNo = payloadObj.getString("applicantIdNo");
					String delegateName = payloadObj.getString("delegateName");
					String delegateAddress = payloadObj.getString("delegateAddress");
					String delegateCityCode = payloadObj.getString("delegateCityCode");
					String delegateDistrictCode = payloadObj.getString("delegateDistrictCode");
					String delegateWardCode = payloadObj.getString("delegateWardCode");
					String delegateEmail = payloadObj.getString("delegateEmail");
					String delegateTelNo = payloadObj.getString("delegateTelNo");
					String applicantIdType = "citizen";
					String applicantIdDate = "1970-01-01";
					String password = "Dvc@2020#";


					if (!delegateIdNo.equals(applicantIdNo)) {
						Applicant applicant = ApplicantLocalServiceUtil.fetchByAppId(delegateIdNo);
						if (Validator.isNull(applicant)) {
							new ApplicantActionsImpl().register(serviceContext, groupId,
									delegateName, applicantIdType, delegateIdNo, applicantIdDate,
									delegateEmail, delegateAddress, delegateCityCode, StringPool.BLANK, delegateDistrictCode, 
									StringPool.BLANK, delegateWardCode, StringPool.BLANK, StringPool.BLANK, delegateTelNo,
									StringPool.BLANK, password);
						}
					}
				}
				
			}

			// DossierAction dossierAction = actions.doAction(groupId, dossier,
			// option, proAction,
			// actionCode, input.getActionUser(),
			// input.getActionNote(), input.getPayload(),
			// input.getAssignUsers(), serviceContext);

			// DossierAction dossierAction = actions.doAction(groupId,
			// dossier.getDossierId(),
			// dossier.getReferenceUid(), input.getActionCode(), 0l,
			// input.getActionUser(),
			// input.getActionNote(), input.getAssignUserId(), 0l, subUsers,
			// serviceContext);

			// return
			// Response.status(HttpURLConnection.HTTP_OK).entity(JSONFactoryUtil.looseSerializeDeep(dossierAction)).build();
			if (dossierResult != null) {
				System.out.println("TRUEEEEEEEEEEEEEEEEEEEEEEEEE");
				long dossierActionId = dossierResult.getDossierActionId();
				DossierDocument doc =
					DossierDocumentLocalServiceUtil.getByActiocId(
						groupId, dossierActionId);
				long dossierDocumentId = 0;
				if (doc != null) {
					dossierDocumentId = doc.getDossierDocumentId();
				}

				DossierActionDetailModel dAction =
					DossierUtils.mappingDossierAction(
						dossierResult, dossierDocumentId);
				//add post action
				System.out.println("Adding post action");
				System.out.println(input);
				_log.info(input);
				System.out.println("Adding post action 11");
				System.out.println(dossierResult);
				System.out.println("Adding post action 222");

				System.out.println("Adding post action 3333");
				if(Validator.isNotNull(processActionCurrent)){
				String postAction = processActionCurrent.getPostAction();
				System.out.println("Adding post action 5555");
				System.out.println("Post action: " + postAction);
				System.out.println("processActionCurrent: " + processActionCurrent.getProcessActionId());
				if (Validator.isNotNull(postAction) && !postAction.isEmpty()) {
					System.out.println("Adding post action 6666");
					String result = processPostAction(postAction, groupId, dossier);
					System.out.println("Result: " + result);
				}
				System.out.println("Done add post action");
				}

				// String strDossierResult =
				// JSONFactoryUtil.looseSerializeDeep(dossierResult);
				// JSONObject jsonData = null;
				// if (Validator.isNotNull(strDossierResult)) {
				// jsonData =
				// JSONFactoryUtil.createJSONObject(strDossierResult);
				// jsonData.put(DossierActionTerm.DOSSIER_DOCUMENT_ID,
				// dossierDocumentId);
				// }
				return Response.status(HttpURLConnection.HTTP_OK).entity(dAction).build();
			}
			else {
				System.out.println("Wronggggg");
				JSONObject errorJson = JSONFactoryUtil.createJSONObject();
				errorJson.put(ConstantUtils.API_DOSSIER_JSON_ERROR_KEY, MessageUtil.getMessage(ConstantUtils.DOSSIER_MESSAGE_CANNOT_DO_ACTION));
				return Response.status(HttpURLConnection.HTTP_BAD_METHOD).entity(
					errorJson.toJSONString()).build();
			}
			// ProcessOption option = getProcessOption(dossier.getServiceCode(),
			// dossier.getGovAgencyCode(),
			// dossier.getDossierTemplateNo(), groupId);

			// ProcessAction action = getProcessAction(dossier,
			// input.getActionCode(), option.getServiceProcessId());

			// dossierPermission.hasPermitDoAction(groupId,
			// user.getUserId(), dossier, option.getServiceProcessId(),
			// action);

			// DossierAction dossierAction = actions.doAction(groupId,
			// dossier.getDossierId(),
			// dossier.getReferenceUid(), input.getActionCode(),
			// action.getProcessActionId(),
			// input.getActionUser(), input.getActionNote(),
			// input.getAssignUserId(), user.getUserId(),
			// subUsers, serviceContext);

		}
		catch (Exception e) {
			_log.info("ERROrrr: " + e.getMessage());
			_log.debug(e);
			return BusinessExceptionImpl.processException(e);
		}
	}

	protected ProcessOption getProcessOption(
		String serviceInfoCode, String govAgencyCode, String dossierTemplateNo,
		long groupId)
		throws PortalException {

		ServiceConfig config = ServiceConfigLocalServiceUtil.getBySICodeAndGAC(
			groupId, serviceInfoCode, govAgencyCode);

		return ProcessOptionLocalServiceUtil.getByDTPLNoAndServiceCF(
			groupId, dossierTemplateNo, config.getServiceConfigId());
	}

	private String processPostAction(String postAction, long groupId, Dossier dossier) {
		try {
			JSONObject jsonPostData = JSONFactoryUtil.createJSONObject(postAction);
			if (jsonPostData != null) {
				JSONObject jsonCallAPI = JSONFactoryUtil.createJSONObject(jsonPostData.getString("CALL_API"));
				if (jsonCallAPI != null && jsonCallAPI.has(DossierTerm.SERVER_NO)) {
					String serverNo = jsonCallAPI.getString(DossierTerm.SERVER_NO);
					if (Validator.isNotNull(serverNo)) {
						ServerConfig serverConfig = ServerConfigLocalServiceUtil.getByCode(groupId, serverNo);
						if (serverConfig != null) {
							JSONObject configObj = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());
							//
							String method = StringPool.BLANK;
							if (configObj != null && configObj.has(KeyPayTerm.METHOD)) {
								method = configObj.getString(KeyPayTerm.METHOD);
								System.out.println("method: " + method);
							}
							//params
							JSONObject jsonParams = null;
							if (configObj != null && configObj.has(KeyPayTerm.PARAMS)) {
								jsonParams = JSONFactoryUtil
										.createJSONObject(configObj.getString(KeyPayTerm.PARAMS));
							}
							if (jsonParams != null) {
								JSONObject jsonHeader = JSONFactoryUtil
										.createJSONObject(jsonParams.getString(KeyPayTerm.HEADER));
								JSONObject jsonBody = JSONFactoryUtil
										.createJSONObject(jsonParams.getString(KeyPayTerm.BODY));

								String authStrEnc = StringPool.BLANK;
								String apiUrl = StringPool.BLANK;
								StringBuilder sb = new StringBuilder();
								try {
									URL urlVal = null;
									String groupIdRequest = StringPool.BLANK;
									StringBuilder postData = new StringBuilder();
									Iterator<?> keys = jsonBody.keys();
									while (keys.hasNext()) {
										String key = (String) keys.next();
										if (!StringPool.BLANK.equals(postData.toString())) {
											postData.append(StringPool.AMPERSAND);
										}
										postData.append(key);
										postData.append(StringPool.EQUAL);
										postData.append(jsonBody.get(key));
									}

									if (configObj.has(SyncServerTerm.SERVER_USERNAME)
											&& configObj.has(SyncServerTerm.SERVER_SECRET)
											&& Validator
											.isNotNull(configObj.getString(SyncServerTerm.SERVER_USERNAME))
											&& Validator
											.isNotNull(configObj.getString(SyncServerTerm.SERVER_SECRET))) {
										authStrEnc = Base64.getEncoder()
												.encodeToString((configObj.getString(SyncServerTerm.SERVER_USERNAME)
														+ StringPool.COLON
														+ configObj.getString(SyncServerTerm.SERVER_SECRET))
														.getBytes());
									}
									if (configObj.has(SyncServerTerm.SERVER_URL)) {
										apiUrl = configObj.getString(SyncServerTerm.SERVER_URL);
										if (apiUrl.contains("{_dossierId}")) {
											apiUrl = apiUrl.replace("{_dossierId}", String.valueOf(dossier.getDossierId()));
										}
										if (apiUrl.contains("{_dossierCounter}")) {
											apiUrl = apiUrl.replace("{_dossierCounter}",
													String.valueOf(dossier.getDossierCounter()));
										}
										if (apiUrl.contains("{_dossierNo}")) {
											apiUrl = apiUrl.replace("{_dossierNo}",
													String.valueOf(dossier.getDossierNo()));
										}
									}
									if (configObj.has(SyncServerTerm.SERVER_GROUP_ID)) {
										groupIdRequest = configObj.getString(SyncServerTerm.SERVER_GROUP_ID);
									}
									if (jsonHeader != null && Validator.isNotNull(groupIdRequest)) {
										if (jsonHeader.has(Field.GROUP_ID)) {
											groupIdRequest = String.valueOf(jsonHeader.getLong(Field.GROUP_ID));
										}
									}

									if (HttpMethods.GET.equals(method)) {
										if (Validator.isNotNull(postData.toString())) {
											urlVal = new URL(apiUrl + StringPool.QUESTION + postData.toString());
										} else {
											urlVal = new URL(apiUrl);
										}
									} else {
										urlVal = new URL(apiUrl);
									}
									_log.debug("API URL: " + apiUrl);
									java.net.HttpURLConnection conn = (java.net.HttpURLConnection) urlVal
											.openConnection();
									conn.setRequestProperty(Field.GROUP_ID, groupIdRequest);
									conn.setRequestMethod(method);
									conn.setRequestProperty(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
									if (Validator.isNotNull(authStrEnc)) {
										conn.setRequestProperty(HttpHeaders.AUTHORIZATION, "Basic " + authStrEnc);
									}
									if (HttpMethods.POST.equals(method) || HttpMethods.PUT.equals(method)) {
										conn.setRequestProperty(HttpHeaders.CONTENT_TYPE,
												MediaType.APPLICATION_FORM_URLENCODED);
										conn.setRequestProperty(HttpHeaders.CONTENT_LENGTH, StringPool.BLANK
												+ Integer.toString(postData.toString().getBytes().length));

										conn.setUseCaches(false);
										conn.setDoInput(true);
										conn.setDoOutput(true);
										_log.debug("POST DATA: " + postData.toString());
										OutputStream os = conn.getOutputStream();
										os.write(postData.toString().getBytes());
										os.close();
									}

									BufferedReader brf = new BufferedReader(
											new InputStreamReader(conn.getInputStream()));

									int cp;
									while ((cp = brf.read()) != -1) {
										sb.append((char) cp);
									}
									_log.debug("RESULT PROXY: " + sb.toString());
									return sb.toString();
								} catch (IOException e) {
									_log.debug(e);
									//_log.debug("Something went wrong while reading/writing in stream!!");
								}
							}
						}
					}
				}
			}
			return "";
		} catch (Exception e) {
			System.out.println("Error when process post action: " + e.getMessage());
			return "";
		}
	}

	protected String getDictItemName(
		long groupId, String collectionCode, String itemCode) {

		DictCollection dc =
			DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(
				collectionCode, groupId);

		if (Validator.isNotNull(dc)) {
			DictItem it = DictItemLocalServiceUtil.fetchByF_dictItemCode(
				itemCode, dc.getPrimaryKey(), groupId);
			if (Validator.isNotNull(it)) {
				return it.getItemName();
			}
			else {
				return StringPool.BLANK;
			}
		}
		else {
			return StringPool.BLANK;
		}

	}

	protected String getDictItemName(
		long groupId, DictCollection dc, String itemCode) {

		if (Validator.isNotNull(dc)) {
			DictItem it = DictItemLocalServiceUtil.fetchByF_dictItemCode(
				itemCode, dc.getPrimaryKey(), groupId);
			if (Validator.isNotNull(it)) {
				return it.getItemName();
			}
			else {
				return StringPool.BLANK;
			}
		}
		else {
			return StringPool.BLANK;
		}

	}

	protected String getServiceName(
		String serviceCode, String templateNo, long groupId)
		throws PortalException {

		try {
			// ServiceInfo service =
			// ServiceInfoLocalServiceUtil.getByCode(groupId, serviceCode);
			// if (service != null) {
			// List<ServiceConfig> configList =
			// ServiceConfigLocalServiceUtil.getByServiceInfo(groupId,
			// service.getServiceInfoId());
			// if (configList != null && configList.size() > 0) {
			// for (ServiceConfig config : configList) {
			// ProcessOption option =
			// ProcessOptionLocalServiceUtil.getByDTPLNoAndServiceCF(groupId,
			// templateNo, config.getServiceConfigId());
			// if (option != null) {
			// return option.getOptionName();
			// }
			// }
			// }
			// }
			ServiceInfo service =
				ServiceInfoLocalServiceUtil.getByCode(groupId, serviceCode);
			if (service != null) {
				return service.getServiceName();
			}
		}
		catch (Exception e) {
			_log.debug(e);
			throw new NotFoundException(MessageUtil.getMessage(ConstantUtils.API_MESSAGE_NOTFOUND));
		}

		return StringPool.BLANK;
	}

	protected String getDossierTemplateName(
		String dossierTemplateCode, long groupId)
		throws PortalException {

		try {
			DossierTemplate template =
				DossierTemplateLocalServiceUtil.getByTemplateNo(
					groupId, dossierTemplateCode);

			return template.getTemplateName();
		}
		catch (Exception e) {
			_log.error(e);
			throw new NotFoundException(MessageUtil.getMessage(ConstantUtils.API_MESSAGE_NOTFOUND));
		}

	}

	public static final String GOVERNMENT_AGENCY = "GOVERNMENT_AGENCY";
	public static final String ADMINISTRATIVE_REGION = "ADMINISTRATIVE_REGION";
	public static final String VNPOST_CITY_CODE = "VNPOST_CITY_CODE";
	public static final String REGISTER_BOOK = "REGISTER_BOOK";

	// public static final int LENGHT_DOSSIER_PASSWORD = 4;
	// public static final String DEFAULT_PATTERN_PASSWORD = "0123";

	@Override
	public Response getContactsDossier(
		HttpHeaders header, ServiceContext serviceContext, Long dossierId,
		String referenceUid) {

		DossierActions action = new DossierActionsImpl();
		BackendAuth auth = new BackendAuthImpl();
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			long groupId =
				GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			JSONObject result =
				action.getContacts(groupId, dossierId, referenceUid);
			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();
		}
		catch (PortalException e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response cloneDossier(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, long dossierId,
		String referenceUid) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DossierActions actions = new DossierActionsImpl();

			Dossier dossier =
				actions.cloneDossier(groupId, dossierId, serviceContext);

			DossierDetailModel result =
				DossierUtils.mappingForGetDetail(dossier, user.getUserId());

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response updateDossierNo(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, String id) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		String dossierno =
			GetterUtil.getString(header.getHeaderString("dossierno"));

		BackendAuth auth = new BackendAuthImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			_log.info("===================== dossierId " + id);

			_log.info("===================== groupId " + groupId);

			_log.info("===================== dossierno " + dossierno);

			Dossier dossier = DossierUtils.getDossier(id, groupId);

			if (dossier != null && Validator.isNull(dossier.getDossierNo())) {
				dossier.setDossierNo(dossierno);

				DossierLocalServiceUtil.updateDossier(dossier);
			}

			return Response.status(HttpURLConnection.HTTP_OK).entity(ConstantUtils.DOSSIER_OK).build();

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);

		}

	}

	Log _log = LogFactoryUtil.getLog(DossierManagementImpl.class);

	// Get dossier by certificate Number
	@SuppressWarnings("unchecked")
	@Override
	public Response getDossierByCertificateNumber(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		String certificateNumber) {

		_log.info("START*********1");
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		_log.info("groupId: " + groupId);
		BackendAuth auth = new BackendAuthImpl();
		DossierActions actions = new DossierActionsImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			LinkedHashMap<String, Object> params =
				new LinkedHashMap<String, Object>();

			_log.info("certificateNumber: " + certificateNumber);
			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(DossierTerm.DOSSIER_ID + DossierTerm.DOSSIER_CTN, certificateNumber);
			String dateSort = String.format(MessageUtil.getMessage(ConstantUtils.QUERY_SORT), DossierTerm.MODIFIED_DATE);
			Sort[] sorts = new Sort[] {
				SortFactoryUtil.create(
					dateSort, Sort.STRING_TYPE, false)
			};

			JSONObject jsonData = actions.getDossiers(
				user.getUserId(), company.getCompanyId(), groupId, params,
				sorts, QueryUtil.ALL_POS, QueryUtil.ALL_POS, serviceContext);

			JSONObject results = JSONFactoryUtil.createJSONObject();

			Document data = ((List<Document>) jsonData.get(ConstantUtils.DATA)).get(0);
			results.put(DossierTerm.DOSSIER_ID, data.get(DossierTerm.DOSSIER_ID));

			return Response.status(HttpURLConnection.HTTP_OK).entity(
				JSONFactoryUtil.looseSerialize(results)).build();

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response addDossierMark(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, long dossierId,
		String dossierPartNo, DossierMarkInputModel input) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DossierMarkActions actions = new DossierMarkActionsImpl();

			DossierMark dossierMark = actions.addDossierMark(
				groupId, dossierId, dossierPartNo, input.getFileMark(),
				input.getFileCheck(), input.getFileComment(),
				input.getRecordCount(), serviceContext);

			DossierMarkResultDetailModel result =
				DossierMarkUtils.mappingDossierMarkDetailModel(dossierMark);

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getDossierMarks(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		long dossierId) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DossierMarkActions actions = new DossierMarkActionsImpl();

			DossierMarkResultsModel result = new DossierMarkResultsModel();

			List<DossierMark> lstDossierMark =
				actions.getDossierMarks(groupId, dossierId);

			List<DossierMarkModel> outputs =
				DossierMarkUtils.mappingDossierMarks(lstDossierMark);

			result.setTotal(lstDossierMark.size());
			result.getData().addAll(outputs);

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getDossierMarkbyDossierId(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, long dossierId,
		String dossierPartNo) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DossierMarkActions actions = new DossierMarkActionsImpl();

			DossierMark dossierMark = actions.getDossierMarkbyDossierId(
				groupId, dossierId, dossierPartNo);

			DossierMarkResultDetailModel result =
				DossierMarkUtils.mappingDossierMarkDetailModel(dossierMark);

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response cancellingRequestDossier(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, String id,
		String body) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		BackendAuth auth = new BackendAuthImpl();
		DossierActions actions = new DossierActionsImpl();

		// DossierPermission dossierPermission = new DossierPermission();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			// 1. Update cancellingDate in dossier

			Dossier dossier = DossierUtils.getDossier(id, groupId);

			serviceContext.setScopeGroupId(groupId);

			Dossier cancellingDossier = actions.cancelDossier(
				groupId, dossier.getDossierId(), dossier.getReferenceUid(),
				serviceContext);

			// 2. update requestDossier

			String referenceUid = PortalUUIDUtil.generate();
			int status = 3;

			DossierRequestUDLocalServiceUtil.updateDossierRequest(
				0, dossier.getDossierId(), referenceUid, RT_CANCELLING, body, 1,
				status, serviceContext);

			DossierDetailModel result = DossierUtils.mappingForGetDetail(
				cancellingDossier, user.getUserId());

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response correctingRequestDossier(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, String id,
		String body) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		BackendAuth auth = new BackendAuthImpl();
		DossierActions actions = new DossierActionsImpl();

		// DossierPermission dossierPermission = new DossierPermission();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			// 1. Update cancellingDate in dossier

			Dossier dossier = DossierUtils.getDossier(id, groupId);

			serviceContext.setScopeGroupId(groupId);

			Dossier correctingDossier = actions.correctDossier(
				groupId, dossier.getDossierId(), dossier.getReferenceUid(),
				serviceContext);

			// 2. update requestDossier

			String referenceUid = PortalUUIDUtil.generate();
			int status = 3;

			DossierRequestUDLocalServiceUtil.updateDossierRequest(
				0, dossier.getDossierId(), referenceUid, RT_CORRECTING, body, 1,
				status, serviceContext);

			DossierDetailModel result = DossierUtils.mappingForGetDetail(
				correctingDossier, user.getUserId());

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response submittingDossierPOST(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, String id,
		String body) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		BackendAuth auth = new BackendAuthImpl();
		DossierActions actions = new DossierActionsImpl();

		// DossierPermission dossierPermission = new DossierPermission();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			// 1. Update cancellingDate in dossier

			Dossier dossier = DossierUtils.getDossier(id, groupId);

			serviceContext.setScopeGroupId(groupId);

			Dossier endorsementDossier = actions.submitPostDossier(
				groupId, dossier.getDossierId(), dossier.getReferenceUid(),
				serviceContext);

			// 2. update requestDossier

			String referenceUid = PortalUUIDUtil.generate();
			int status = 3;

			DossierRequestUDLocalServiceUtil.updateDossierRequest(
				0, dossier.getDossierId(), referenceUid, RT_SUBMITTING, body, 1,
				status, serviceContext);

			DossierDetailModel result = DossierUtils.mappingForGetDetail(
				endorsementDossier, user.getUserId());

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response getProcessStepByDossierId(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, String id) {

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			long groupId =
				GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			long dossierId = GetterUtil.getLong(id);

			Dossier dossier = DossierLocalServiceUtil.getDossier(dossierId);
			long dossierActionId = 0;
			DossierAction dossierAction = null;
			long serviceProcessId = 0;
			String actionCode = StringPool.BLANK;
			if (dossier != null) {
				dossierActionId = dossier.getDossierActionId();
				if (dossierActionId > 0) {
					dossierAction =
						DossierActionLocalServiceUtil.fetchDossierAction(
							dossierActionId);
				}
			}
			if (dossierAction != null) {
				serviceProcessId = dossierAction.getServiceProcessId();
				actionCode = dossierAction.getActionCode();
			}

			ProcessStep proStep = ProcessStepLocalServiceUtil.fetchBySC_GID(
				actionCode, groupId, serviceProcessId);
			String restrictDossier = StringPool.BLANK;
			if (proStep != null) {
				restrictDossier = proStep.getRestrictDossier();
			}

			return Response.status(HttpURLConnection.HTTP_OK).entity(restrictDossier).build();

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response submitDossier(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, String id,
		String body) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		BackendAuth auth = new BackendAuthImpl();

		DossierActions actions = new DossierActionsImpl();

		// DossierPermission dossierPermission = new DossierPermission();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			// 1. Update cancellingDate in dossier

			Dossier dossier = DossierUtils.getDossier(id, groupId);

			serviceContext.setScopeGroupId(groupId);

			Dossier cancellingDossier = actions.submitPostDossier(
				groupId, dossier.getDossierId(), dossier.getReferenceUid(),
				serviceContext);

			// 2. update requestDossier

			String referenceUid = PortalUUIDUtil.generate();
			int status = 0;

			DossierRequestUDLocalServiceUtil.updateDossierRequest(
				0, dossier.getDossierId(), referenceUid, RT_SUBMITTING, body, 1,
				status, serviceContext);

			DossierDetailModel result = DossierUtils.mappingForGetDetail(
				cancellingDossier, user.getUserId());

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	public Response getDossierPenddingByDossierId(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		String referenceUid) {

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			// TODO: Fix port process
			long groupId = 55301;

			Dossier dossier =
				DossierLocalServiceUtil.getByRef(groupId, referenceUid);
			long dossierActionId = 0;
			DossierAction dossierAction = null;
			boolean pendding = false;
			if (dossier != null) {
				dossierActionId = dossier.getDossierActionId();
				if (dossierActionId > 0) {
					dossierAction =
						DossierActionLocalServiceUtil.fetchDossierAction(
							dossierActionId);
				}
			}
			if (dossierAction != null) {
				pendding = dossierAction.getPending();
			}

			return Response.status(HttpURLConnection.HTTP_OK).entity(pendding).build();

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@SuppressWarnings({
		"unchecked"
	})
	@Override
	public Response getDossiersPendingList(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		DossierSearchModel query) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		long userId = user.getUserId();
		BackendAuth auth = new BackendAuthImpl();
		DossierPermission dossierPermission = new DossierPermission();
		DossierActions actions = new DossierActionsImpl();

		try {
			boolean isCitizen = false;

			if (!query.getSecetKey().contentEquals(DossierTerm.OPENCPSV2)) {

				if (!auth.isAuth(serviceContext)) {
					throw new UnauthenticationException();
				}

				isCitizen = dossierPermission.isCitizen(user.getUserId());

				dossierPermission.hasGetDossiers(
					groupId, user.getUserId(), query.getSecetKey());
			}

			if (query.getEnd() == 0) {

				query.setStart(QueryUtil.ALL_POS);

				query.setEnd(QueryUtil.ALL_POS);

			}

			LinkedHashMap<String, Object> params =
				new LinkedHashMap<String, Object>();
			DossierResultsModel results = new DossierResultsModel();

			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(Field.KEYWORD_SEARCH, query.getKeyword());

			String owner = query.getOwner();
			// If user is citizen then default owner true
			if (isCitizen) {
				owner = String.valueOf(true);
			}
			// Get info input params
			String submitting = query.getSubmitting();
			String pendding = query.getPendding();
			String applicantIdNo = query.getApplicantIdNo();

			params.put(DossierTerm.OWNER, owner);
			params.put(DossierTerm.SUBMITTING, submitting);
			params.put(DossierTerm.PENDING, pendding);
			params.put(DossierTerm.APPLICANT_ID_NO, applicantIdNo);
			String querySort = String.format(MessageUtil.getMessage(ConstantUtils.QUERY_SORT), query.getSort());
			Sort[] sorts = new Sort[] {
				SortFactoryUtil.create(
					querySort, Sort.STRING_TYPE,
					GetterUtil.getBoolean(query.getOrder()))
			};

			// _log.info("START 1");
			// _log.info("START Applicant: "+query.getApplicantIdNo());

			if (Boolean.parseBoolean(pendding)) {
				long groupIdCXL = 55301;
				JSONObject jsonDataPending = null;

				List<DossierAction> dActionList =
					DossierActionLocalServiceUtil.getDossiersPending(
						groupIdCXL, pendding);

				if (dActionList != null && dActionList.size() > 0) {
					LinkedHashMap<String, Object> paramPending =
						new LinkedHashMap<String, Object>();
					_log.info("dActionList: " + dActionList.size());
					int length = dActionList.size();
					StringBuilder sb = new StringBuilder();
					for (int i = 0; i < length; i++) {
						DossierAction dAct = dActionList.get(i);
						long dActId = dAct.getDossierActionId();
						if (i == 0) {
							sb.append(dActId);
						}
						else {
							sb.append(StringPool.COMMA);
							sb.append(dActId);

						}
					}
					_log.info("DOSSIER_ACTION_ID_PENDING: " + sb.toString());

					paramPending.put(
						Field.GROUP_ID, String.valueOf(groupIdCXL));
					paramPending.put(DossierTerm.OWNER, String.valueOf(false));
					paramPending.put(
						DossierTerm.APPLICANT_ID_NO, query.getApplicantIdNo());
					paramPending.put(
						DossierTerm.DOSSIER_ACTION_ID_PENDING, sb.toString());

					jsonDataPending = actions.getDossiers(
						user.getUserId(), company.getCompanyId(), groupIdCXL,
						paramPending, sorts, QueryUtil.ALL_POS, QueryUtil.ALL_POS, serviceContext);
					_log.info("jsonDataPending: " + jsonDataPending);
				}
				if (jsonDataPending != null) {
					List<Document> docs =
						(List<Document>) jsonDataPending.get(ConstantUtils.DATA);
					if (docs != null && docs.size() > 0) {
						StringBuilder sb1 = new StringBuilder();
						int length = docs.size();
						for (int i = 0; i < length; i++) {
							Document doc = docs.get(i);
							String referenceUid =
								doc.get(DossierTerm.REFERENCE_UID);
							if (i == 0) {
								sb1.append(referenceUid);
							}
							else {
								sb1.append(StringPool.COMMA);
								sb1.append(referenceUid);
							}
						}
						_log.info("REFERENCE_UID: " + sb1.toString());
						params.put(DossierTerm.REFERENCE_UID, sb1.toString());
					}
				}

			}

			JSONObject jsonData = actions.getDossiers(
				user.getUserId(), company.getCompanyId(), groupId, params,
				sorts, QueryUtil.ALL_POS, QueryUtil.ALL_POS, serviceContext);

			if (jsonData == null) {
				jsonData = JSONFactoryUtil.createJSONObject();
			}

			results.setTotal(jsonData.getInt(ConstantUtils.TOTAL));

			results.getData().addAll(
				DossierUtils.mappingForGetList(
					(List<Document>) jsonData.get(ConstantUtils.DATA), userId,
					query.getAssigned(),query));

			return Response.status(HttpURLConnection.HTTP_OK).entity(results).build();

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getDossiersInfoList(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		DossierSearchModel query) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		long userId = user.getUserId();
		BackendAuth auth = new BackendAuthImpl();
		DossierPermission dossierPermission = new DossierPermission();
		DossierActions actions = new DossierActionsImpl();

		try {
			boolean isCitizen = false;

			if (!query.getSecetKey().contentEquals(DossierTerm.OPENCPSV2)) {

				if (!auth.isAuth(serviceContext)) {
					throw new UnauthenticationException();
				}

				isCitizen = dossierPermission.isCitizen(user.getUserId());

				dossierPermission.hasGetDossiers(
					groupId, user.getUserId(), query.getSecetKey());
			}

			if (query.getEnd() == 0) {

				query.setStart(QueryUtil.ALL_POS);

				query.setEnd(QueryUtil.ALL_POS);

			}

			LinkedHashMap<String, Object> params =
				new LinkedHashMap<String, Object>();
			DossierResultsModel results = new DossierResultsModel();

			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(Field.KEYWORD_SEARCH, query.getKeyword());

			String owner = query.getOwner();
			// If user is citizen then default owner true
			if (isCitizen) {
				owner = String.valueOf(true);
			}

			// Test API new
			String dossierArr = query.getDossierArr();
			String status = query.getStatus();
			// Get info input params
			// String submitting = query.getSubmitting();
			// String pendding = query.getPendding();
			String applicantIdNo = query.getApplicantIdNo();

			params.put(DossierTerm.OWNER, owner);
			// params.put(DossierTerm.SUBMITTING, submitting);
			// params.put("pendding", pendding);
			params.put(DossierTerm.APPLICANT_ID_NO, applicantIdNo);
			params.put(DossierTerm.STATUS, status);
			params.put(ConstantUtils.API_DOSSIER_JSON_DOSSIERARR, dossierArr);
			String querySort = String.format(MessageUtil.getMessage(ConstantUtils.QUERY_SORT), query.getSort());
			Sort[] sorts = new Sort[] {
				SortFactoryUtil.create(
					querySort, Sort.STRING_TYPE,
					GetterUtil.getBoolean(query.getOrder()))
			};

			// if (Boolean.parseBoolean(pendding)) {
			// long groupIdCXL = 55301;
			// JSONObject jsonDataPending = null;
			//
			// List<DossierAction> dActionList =
			// DossierActionLocalServiceUtil.getDossiersPending(groupIdCXL,
			// pendding);
			//
			// if (dActionList != null && dActionList.size() > 0) {
			// LinkedHashMap<String, Object> paramPending = new
			// LinkedHashMap<String, Object>();
			// _log.info("dActionList: "+dActionList.size());
			// int length = dActionList.size();
			// StringBuilder sb = new StringBuilder();
			// for (int i = 0; i < length; i ++) {
			// DossierAction dAct = dActionList.get(i);
			// long dActId = dAct.getDossierActionId();
			// if (i == 0) {
			// sb.append(dActId);
			// } else {
			// sb.append(StringPool.COMMA);
			// sb.append(dActId);
			//
			// }
			// }
			// _log.info("DOSSIER_ACTION_ID_PENDING: "+sb.toString());
			//
			// paramPending.put(Field.GROUP_ID, String.valueOf(groupIdCXL));
			// paramPending.put(DossierTerm.OWNER, String.valueOf(false));
			// paramPending.put(DossierTerm.APPLICANT_ID_NO,
			// query.getApplicantIdNo());
			// paramPending.put(DossierTerm.DOSSIER_ACTION_ID_PENDING,
			// sb.toString());
			//
			// jsonDataPending = actions.getDossiers(user.getUserId(),
			// company.getCompanyId(), groupIdCXL, paramPending, sorts,
			// -1, -1, serviceContext);
			// _log.info("jsonDataPending: "+jsonDataPending);
			// }
			// if (jsonDataPending != null) {
			// List<Document> docs = (List<Document>)
			// jsonDataPending.get(ConstantUtils.DATA);
			// if (docs != null && docs.size() > 0) {
			// StringBuilder sb1 = new StringBuilder();
			// int length = docs.size();
			// for (int i = 0; i < length; i ++) {
			// Document doc = docs.get(i);
			// String referenceUid = doc.get(DossierTerm.REFERENCE_UID);
			// if (i == 0) {
			// sb1.append(referenceUid);
			// } else {
			// sb1.append(StringPool.COMMA);
			// sb1.append(referenceUid);
			// }
			// }
			// _log.info("REFERENCE_UID: "+sb1.toString());
			// params.put(DossierTerm.REFERENCE_UID, sb1.toString());
			// }
			// }
			//
			// }

			JSONObject jsonData = actions.getDossiers(
				user.getUserId(), company.getCompanyId(), groupId, params,
				sorts, -1, -1, serviceContext);

			if (jsonData == null) {
				jsonData = JSONFactoryUtil.createJSONObject();
			}

			results.setTotal(jsonData.getInt(ConstantUtils.TOTAL));

			results.getData().addAll(
				DossierUtils.mappingForGetList(
					(List<Document>) jsonData.get(ConstantUtils.DATA), userId,
					query.getAssigned(),query));

			return Response.status(HttpURLConnection.HTTP_OK).entity(results).build();

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getReassignUsers(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, long dossierId,
		ServiceContext serviceContext) {

		ReAssign reAssign = new ReAssign();

		List<ToUsers> lstUsers = new ArrayList<>();

		Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
		if (dossier != null) {
			DossierAction dossierAction =
				DossierActionLocalServiceUtil.fetchDossierAction(
					dossier.getDossierActionId());
			if (dossierAction != null) {
				String stepCode = dossierAction.getStepCode();
				List<org.opencps.dossiermgt.model.DossierActionUser> lstDossierActionUsers =
					DossierActionUserLocalServiceUtil.getByDossierAndStepCode(
						dossierId, stepCode);
				for (org.opencps.dossiermgt.model.DossierActionUser dau : lstDossierActionUsers) {
					ToUsers toUsers = new ToUsers();
					toUsers.setAssigned(dau.getAssigned());
					toUsers.setUserId(dau.getUserId());
					User u =
						UserLocalServiceUtil.fetchUser(toUsers.getUserId());
					toUsers.setModerator(
						dau.getModerator() == 1 ? true : false);
					//
					Employee employee =
						EmployeeLocalServiceUtil.fetchByF_mappingUserId(
							dossier.getGroupId(), u.getUserId());
					if (employee != null) {
						toUsers.setUserName(employee.getFullName());
					}
					else {
						toUsers.setUserName(u.getFullName());
					}
					lstUsers.add(toUsers);
				}
			}
		}

		reAssign.getToUsers().addAll(lstUsers);

		return Response.status(HttpURLConnection.HTTP_OK).entity(reAssign).build();
	}

	@Override
	public Response updateReassignUsers(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, long dossierId,
		String toUsers) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		// _log.info("groupId: "+groupId);
		// _log.info("toUsers: "+toUsers);
		DossierActionUser oldDau = null;

		try {
			Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
			ReAssign reAssign = new ReAssign();
			if (dossier != null) {
				DossierAction dossierAction =
					DossierActionLocalServiceUtil.fetchDossierAction(
						dossier.getDossierActionId());
				if (dossierAction != null) {
					String stepCode = dossierAction.getStepCode();
					long dossierActionId = dossier.getDossierActionId();
					DossierActionUserLocalServiceUtil.deleteByDossierAndStepCode(
						dossierId, stepCode);
					//
					if (Validator.isNotNull(toUsers)) {
						JSONArray userArr =
							JSONFactoryUtil.createJSONArray(toUsers);
						if (userArr != null && userArr.length() > 0) {
							long userId = 0;
							int assigned = 0;
							int moderator = 0;
							for (int i = 0; i < userArr.length(); i++) {
								JSONObject jsonUser = userArr.getJSONObject(i);
								if (jsonUser != null) {
									userId = jsonUser.getLong(Field.USER_ID);
									assigned = jsonUser.getInt(DossierTerm.ASSIGNED);
									if (assigned > 0) {
										moderator = 1;
									}
									DossierActionUserPK pk =
										new DossierActionUserPK();
									pk.setDossierActionId(dossierActionId);
									pk.setUserId(userId);

									oldDau =
										DossierActionUserLocalServiceUtil.fetchDossierActionUser(
											pk);

									if (oldDau == null) {
										DossierActionUserLocalServiceUtil.addDossierActionUser(
											userId, groupId, dossierActionId,
											dossierId, stepCode, moderator,
											assigned, true);
									}
									else {
										oldDau.setModerator(moderator);
										DossierActionUserLocalServiceUtil.updateDossierActionUser(
											oldDau);
									}

									if (moderator == 1) {
										DossierUser du =
											DossierUserLocalServiceUtil.findByDID_UD(
												dossierId, userId);
										if (du != null) {
											if (du.getUserId() == userId &&
												du.getModerator() == 0 &&
												moderator == 1) {
												du.setModerator(moderator);
												DossierUserLocalServiceUtil.updateDossierUser(
													dossierId, du.getUserId(),
													moderator, true);
											}
										}
										else {
											DossierUserLocalServiceUtil.addDossierUser(
												groupId, dossierId, userId,
												moderator, true);
										}
									}
									else {
										DossierUser du =
											DossierUserLocalServiceUtil.findByDID_UD(
												dossierId, userId);
										if (du != null) {
										}
										else {
											DossierUserLocalServiceUtil.addDossierUser(
												groupId, dossierId, userId,
												moderator, true);
										}
									}
								}
							}
							List<org.opencps.dossiermgt.model.DossierActionUser> lstDossierActionUsers =
								DossierActionUserLocalServiceUtil.getByDossierAndStepCode(
									dossierId, stepCode);
							List<ToUsers> lstUsers = new ArrayList<>();
							for (org.opencps.dossiermgt.model.DossierActionUser dau : lstDossierActionUsers) {
								ToUsers tu = new ToUsers();
								tu.setAssigned(dau.getAssigned());
								tu.setUserId(dau.getUserId());
								User u = UserLocalServiceUtil.fetchUser(
									tu.getUserId());
								tu.setModerator(
									dau.getModerator() == 1 ? true : false);
								Employee employee =
									EmployeeLocalServiceUtil.fetchByF_mappingUserId(
										dossier.getGroupId(), u.getUserId());
								if (employee != null) {
									tu.setUserName(employee.getFullName());
								}
								else {
									tu.setUserName(u.getFullName());
								}
								lstUsers.add(tu);
							}
							reAssign.getToUsers().addAll(lstUsers);
						}
					}
				}
			}
			return Response.status(HttpURLConnection.HTTP_OK).entity(reAssign).build();
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response rollback(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, String id) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		//Dossier dossier = null;

		Dossier dossier = DossierUtils.getDossier(id, groupId);
//		if (Validator.isNumber(id)) {
//			dossier = DossierLocalServiceUtil.fetchDossier(Integer.parseInt(id));
//		} else {
//			dossier = DossierLocalServiceUtil.getByRef(groupId, id);
//		}
		JSONObject result = JSONFactoryUtil.createJSONObject();
		if (dossier == null) {
			result.put("result", "ERROR");
			return Response.status(HttpStatus.SC_NOT_FOUND).entity(null).build();
		}
		List<Role> userRoles = user.getRoles();
		boolean isAdmin = false;
		for (Role r : userRoles) {
			if (r.getName().startsWith(ConstantUtils.ROLE_ADMIN)) {
				isAdmin = true;
				break;
			}
		}

//		if (dossier != null) {
			DossierAction dossierAction =
				DossierActionLocalServiceUtil.fetchDossierAction(
					dossier.getDossierActionId());
			//if (dossierAction != null && isAdmin) {
			if (dossierAction != null) {
				DossierActionLocalServiceUtil.updateState(
					dossierAction.getDossierActionId(),
					DossierActionTerm.STATE_ROLLBACK);

				DossierAction previousAction =
					DossierActionLocalServiceUtil.fetchDossierAction(
						dossierAction.getPreviousActionId());
				if (previousAction != null) {
					DossierActionLocalServiceUtil.updateState(
						previousAction.getDossierActionId(),
						DossierActionTerm.STATE_WAITING_PROCESSING);
					try {
						DossierActionLocalServiceUtil.updateNextActionId(
							previousAction.getDossierActionId(), 0);
						DossierLocalServiceUtil.rollback(
							dossier, previousAction);
					}
					catch (PortalException e) {
						return BusinessExceptionImpl.processException(e);
					}
				}

				DossierSync ds = DossierSyncLocalServiceUtil.getByDID_DAD(
					groupId, dossier.getDossierId(),
					dossierAction.getDossierActionId());
				if (ds != null &&
					((ds.getSyncType() == DossierSyncTerm.SYNCTYPE_INFORM &&
						dossier.getOriginality() == DossierTerm.ORIGINALITY_LIENTHONG) ||
						(ds.getSyncType() == DossierSyncTerm.SYNCTYPE_REQUEST &&
							dossier.getOriginality() == DossierTerm.ORIGINALITY_DVCTT))) {
					DossierMgtUtils.processSyncRollbackDossier(dossier);
				}
			}
//			else if (dossierAction != null && isAdmin) {
//				DossierActionLocalServiceUtil.updateState(
//					dossierAction.getDossierActionId(),
//					DossierActionTerm.STATE_ROLLBACK);
//
//				DossierAction previousAction =
//					DossierActionLocalServiceUtil.fetchDossierAction(
//						dossierAction.getPreviousActionId());
//				if (previousAction != null) {
//					DossierActionLocalServiceUtil.updateState(
//						previousAction.getDossierActionId(),
//						DossierActionTerm.STATE_WAITING_PROCESSING);
//					try {
//						DossierActionLocalServiceUtil.updateNextActionId(
//							previousAction.getDossierActionId(), 0);
//						DossierLocalServiceUtil.rollback(
//							dossier, previousAction);
//					}
//					catch (PortalException e) {
//						return BusinessExceptionImpl.processException(e);
//					}
//				}
//
//				DossierSync ds = DossierSyncLocalServiceUtil.getByDID_DAD(
//					groupId, dossier.getDossierId(),
//					dossierAction.getDossierActionId());
//				if (ds != null &&
//					((ds.getSyncType() == DossierSyncTerm.SYNCTYPE_INFORM &&
//						dossier.getOriginality() == DossierTerm.ORIGINALITY_LIENTHONG) ||
//						(ds.getSyncType() == DossierSyncTerm.SYNCTYPE_REQUEST &&
//							dossier.getOriginality() == DossierTerm.ORIGINALITY_DVCTT))) {
//					DossierMgtUtils.processSyncRollbackDossier(dossier);
//				}
//			}
		result.put("result", "SUCCESSFUL");
		return Response.status(HttpURLConnection.HTTP_OK).entity(JSONFactoryUtil.looseSerialize(result)).build();
//		}
//		else {
//			return Response.status(404).entity(null).build();
//		}
	}

	@Override
	public Response getDossierSequences(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, String id) {

		// BackendAuth auth = new BackendAuthImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		try {

			// if (!auth.isAuth(serviceContext)) {
			// throw new UnauthenticationException();
			// }
			// if (!auth.hasResource(serviceContext,
			// ProcessSequence.class.getName(), ActionKeys.ADD_ENTRY)) {
			// throw new UnauthorizationException("UnauthorizationException");
			// }

			Dossier dossier = null;
			long dossierId = GetterUtil.getLong(id);
			dossier = DossierLocalServiceUtil.getDossier(dossierId);
			if (dossier == null) {
				dossier = DossierLocalServiceUtil.getByRef(groupId, id);
			}
			if (dossier == null) {
				throw new Exception(MessageUtil.getMessage(ConstantUtils.DOSSIER_MESSAGE_KHONGTIMTHAY));
			}
			//
			List<DossierAction> dActionList =
				DossierActionLocalServiceUtil.findByG_DID(groupId, dossierId);
			ServiceProcess serviceProcess = null;
			if (dActionList != null && dActionList.size() > 0) {
				long serviceProcessId =
					dActionList.get(0).getServiceProcessId();
				if (serviceProcessId > 0) {
					serviceProcess =
						ServiceProcessLocalServiceUtil.fetchServiceProcess(
							serviceProcessId);
				}
			}
			else {
				serviceProcess =
					ServiceProcessLocalServiceUtil.getServiceByCode(
						groupId, dossier.getServiceCode(),
						dossier.getGovAgencyCode(),
						dossier.getDossierTemplateNo());
			}

			if (serviceProcess == null) {
				throw new Exception(MessageUtil.getMessage(ConstantUtils.DOSSIER_MESSAGE_KHONGTIMTHAY));
			}

			JSONObject result = getDossierProcessSequencesJSON(
				groupId, dossier, serviceProcess);
			return Response.status(HttpURLConnection.HTTP_OK).entity(result.toJSONString()).build();
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	private JSONObject getDossierProcessSequencesJSON(
		long groupId, Dossier dossier, ServiceProcess serviceProcess) {

		JSONObject result = JSONFactoryUtil.createJSONObject();
		List<ProcessSequence> lstSequences =
			ProcessSequenceLocalServiceUtil.getByServiceProcess(
				groupId, serviceProcess.getServiceProcessId());

		result.put(
			ServiceProcessTerm.PROCESS_NO, serviceProcess.getProcessNo());
		result.put(
			ServiceProcessTerm.DURATION_UNIT, serviceProcess.getDurationUnit());
		result.put(
			ServiceProcessTerm.DURATION_COUNT,
			serviceProcess.getDurationCount());
		result.put(ConstantUtils.TOTAL, lstSequences.size());
		JSONArray sequenceArr = JSONFactoryUtil.createJSONArray();
		DossierAction lastDA = DossierActionLocalServiceUtil.fetchDossierAction(
			dossier.getDossierActionId());
		List<DossierActionUser> lstDus =
			DossierActionUserLocalServiceUtil.getListUser(
				dossier.getDossierActionId());
		List<DossierLog> lstLogs = new ArrayList<>();

		try {
			lstLogs = DossierLogLocalServiceUtil.getByDossierAndType(
				dossier.getDossierId(), DossierLogTerm.PROCESS_TYPE, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);
		}
		catch (PortalException e) {
			_log.debug(e);
		}
		Map<Long, JSONArray> mapFiles = new HashMap<>();

		for (DossierLog log : lstLogs) {
			JSONObject payload;
			try {
				payload = JSONFactoryUtil.createJSONObject(log.getPayload());
				if (payload.has(DossierActionTerm.DOSSIERACTION_ID)) {
					mapFiles.put(
						payload.getLong(DossierActionTerm.DOSSIERACTION_ID),
						payload.getJSONArray(DossierActionTerm.FILES));
				}
			}
			catch (JSONException e) {
				_log.debug(e);
			}
		}

		List<DossierAction> dossierActionListCheck =
			DossierActionLocalServiceUtil.findByG_DID(
				groupId, dossier.getDossierId());
		if (dossierActionListCheck != null &&
			dossierActionListCheck.size() == 1 &&
			DossierActionTerm.DONE_STEP.equals(dossierActionListCheck.get(0).getStepCode())) {
		}
		else {
			for (ProcessSequence ps : lstSequences) {
				JSONObject sequenceObj = JSONFactoryUtil.createJSONObject();
				sequenceObj.put(ProcessSequenceTerm.SEQUENCE_NO, ps.getSequenceNo());
				sequenceObj.put(ProcessSequenceTerm.SEQUENCE_NAME, ps.getSequenceName());
				sequenceObj.put(ProcessSequenceTerm.SEQUENCE_ROLE, ps.getSequenceRole());
				sequenceObj.put(ProcessSequenceTerm.DURATION_COUNT, ps.getDurationCount());

				if (lastDA != null &&
					lastDA.getSequenceNo().equals(ps.getSequenceNo())) {
					String statusText = String.format(MessageUtil.getMessage(ConstantUtils.PROCESSSEQUENCE_MESSAGE_PROCESSING), lastDA.getStepName());
					sequenceObj.put(
						ConstantUtils.API_PROCESSSEQUENCE_JSON_STATUS_TEXT,
						statusText);
				}
				List<DossierAction> lstDossierActions =
					DossierActionLocalServiceUtil.findDossierActionByG_DID_FSN(
						groupId, dossier.getDossierId(), ps.getSequenceNo());
				List<DossierAction> lstPrevDossierActions =
					DossierActionLocalServiceUtil.findDossierActionByG_DID_SN(
						groupId, dossier.getDossierId(), ps.getSequenceNo());

				DossierAction lastAction = lstPrevDossierActions.size() > 0
					? lstPrevDossierActions.get(
						lstPrevDossierActions.size() - 1)
					: null;
				if (lastAction != null) {
					sequenceObj.put(
						ProcessSequenceTerm.START_DATE, lastAction.getCreateDate().getTime());
				}

				if (lstDossierActions.size() > 0) {
					DossierAction lastDASequence =
						lstDossierActions.get(lstDossierActions.size() - 1);
					if (lastDASequence.getActionOverdue() != 0) {
						String overdueFormat = (lastDASequence.getActionOverdue() > 0) ? MessageUtil.getMessage(ConstantUtils.PROCESSSEQUENCE_MESSAGE_UNDUE) : MessageUtil.getMessage(ConstantUtils.PROCESSSEQUENCE_MESSAGE_OVERDUE);
						String overdueText = String.format(overdueFormat, lastDASequence.getActionOverdue());
						
						sequenceObj.put(
							ConstantUtils.API_PROCESSSEQUENCE_JSON_OVERDUE_TEXT, overdueText);
					}
				}
				JSONArray assignUserArr = JSONFactoryUtil.createJSONArray();
				List<Long> lstUsers = new ArrayList<>();

				if (lastDA.getSequenceNo().equals(ps.getSequenceNo())) {
					for (DossierActionUser dau : lstDus) {
						User u =
							UserLocalServiceUtil.fetchUser(dau.getUserId());
						if (u != null) {
							if (!lstUsers.contains(dau.getUserId()) &&
								dau.getModerator() == DossierActionUserTerm.ASSIGNED_TH) {
								JSONObject assignUserObj =
									JSONFactoryUtil.createJSONObject();
								lstUsers.add(dau.getUserId());
								assignUserObj.put(Field.USER_ID, dau.getUserId());
								// TODO: Not update user
								Employee emp =
									EmployeeLocalServiceUtil.fetchByF_mappingUserId(
										groupId, u.getUserId());
								if (emp != null) {
									assignUserObj.put(
										Field.USER_NAME, emp.getFullName());
								}
								else {
									assignUserObj.put(
										Field.USER_NAME, u.getFullName());
								}

								assignUserArr.put(assignUserObj);
							}
						}
					}
				}
				for (DossierAction da : lstDossierActions) {
					if (!lstUsers.contains(da.getUserId())) {
						JSONObject assignUserObj =
							JSONFactoryUtil.createJSONObject();
						lstUsers.add(da.getUserId());
						assignUserObj.put(Field.USER_ID, da.getUserId());
						// TODO: Not update user
						Employee emp =
							EmployeeLocalServiceUtil.fetchByF_mappingUserId(
								groupId, da.getUserId());
						if (emp != null) {
							assignUserObj.put(Field.USER_NAME, emp.getFullName());
						}
						else {
							assignUserObj.put(Field.USER_NAME, da.getUserName());
						}

						assignUserArr.put(assignUserObj);
					}
				}

				sequenceObj.put(DossierActionTerm.ASSIGN_USERS, assignUserArr);

				JSONArray actionsArr = JSONFactoryUtil.createJSONArray();
				for (DossierAction da : lstDossierActions) {
					JSONObject actionObj = JSONFactoryUtil.createJSONObject();

					actionObj.put(DossierActionTerm.USER_ID, da.getUserId());
					actionObj.put(DossierActionTerm.FROM_STEP_CODE, da.getFromStepCode());
					actionObj.put(DossierActionTerm.FROM_STEP_NAME, da.getFromStepName());
					actionObj.put(ProcessSequenceTerm.SEQUENCE_NO, da.getSequenceNo());
					actionObj.put(DossierActionTerm.DOSSIER_ID, da.getDossierId());
					actionObj.put(DossierActionTerm.SERVICE_PROCESS_ID, da.getServiceProcessId());
					actionObj.put(DossierActionTerm.PREVIOUS_ACTION_ID, da.getPreviousActionId());
					actionObj.put(DossierActionTerm.ACTION_CODE, da.getActionCode());
					actionObj.put(DossierActionTerm.ACTION_NAME, da.getActionName());
					actionObj.put(DossierActionTerm.ACTION_NOTE, da.getActionNote());
					actionObj.put(DossierActionTerm.ACTION_USER, da.getActionUser());
					actionObj.put(DossierActionTerm.ACTION_OVER_DUE, da.getActionOverdue());
					actionObj.put(DossierActionTerm.PAYLOAD, da.getPayload());
					actionObj.put(DossierActionTerm.PENDING, da.getPending());
					actionObj.put(DossierActionTerm.ROLLBACK_ABLE, da.getRollbackable());
					actionObj.put(
						Field.CREATE_DATE, da.getCreateDate() != null
							? da.getCreateDate().getTime() : 0l);
					actionObj.put(
						DossierActionTerm.MODIFIED_DATE, da.getModifiedDate() != null
							? da.getModifiedDate().getTime() : 0l);
					actionObj.put(
						DossierActionTerm.DUE_DATE, da.getDueDate() != null
							? da.getDueDate().getTime() : 0l);
					actionObj.put(DossierActionTerm.NEXT_ACTION_ID, da.getNextActionId());
					actionObj.put(DossierActionTerm.STATE, da.getState());
					actionObj.put(DossierActionTerm.STEP_CODE, da.getStepCode());
					actionObj.put(DossierActionTerm.STEP_NAME, da.getStepName());
					actionObj.put(Field.USER_ID, da.getUserId());
					if (mapFiles.containsKey(da.getPreviousActionId())) {
						actionObj.put(
							DossierActionTerm.FILES, mapFiles.get(da.getPreviousActionId()));
					}
					_log.info("Action obj: " + actionObj.toJSONString());
					actionsArr.put(actionObj);
				}

				sequenceObj.put(DossierActionTerm.ACTIONS, actionsArr);

				sequenceArr.put(sequenceObj);

			}
		}

		result.put(ConstantUtils.DATA, sequenceArr);
		return result;
	}

	// private DossierSequenceResultModel getDossierProcessSequences(long
	// groupId, Dossier dossier, ServiceProcess serviceProcess) {
	// DossierSequenceResultModel result = new DossierSequenceResultModel();
	// List<ProcessSequence> lstSequences =
	// ProcessSequenceLocalServiceUtil.getByServiceProcess(groupId,
	// serviceProcess.getServiceProcessId());
	// result.setProcessNo(serviceProcess.getProcessNo());
	// result.setDurationUnit(serviceProcess.getDurationUnit());
	// result.setDurationCount(serviceProcess.getDurationCount());
	// result.setTotal(lstSequences.size());
	//
	// List<DossierSequenceModel> lstDsms = new ArrayList<>();
	// for (ProcessSequence ps : lstSequences) {
	// DossierSequenceModel dsm = new DossierSequenceModel();
	// dsm.setSequenceNo(ps.getSequenceNo());
	// dsm.setSequenceName(ps.getSequenceName());
	// dsm.setSequenceRole(ps.getSequenceRole());
	// dsm.setDurationCount(ps.getDurationCount());
	//
	// List<DossierAction> lstDossierActions =
	// DossierActionLocalServiceUtil.findDossierActionByDID_FSN(dossier.getDossierId(),
	// dsm.getSequenceNo());
	// List<ActionModel> lstActionModels = new ArrayList<>();
	//
	// for (DossierAction da : lstDossierActions) {
	// ActionModel am = new ActionModel();
	// am.setUserId(da.getUserId());
	// am.setFromStepCode(da.getFromStepCode());
	// am.setFromStepName(da.getFromStepName());
	// am.setSequenceNo(da.getSequenceNo());
	// am.setDossierId(da.getDossierId());
	// am.setServiceProcessId(da.getServiceProcessId());
	// am.setPreviousActionId(da.getPreviousActionId());
	// am.setActionCode(da.getActionCode());
	// am.setActionName(da.getActionName());
	// am.setActionNote(da.getActionNote());
	// am.setActionUser(da.getActionUser());
	// am.setActionOverdue(da.getActionOverdue());
	// am.setPayload(da.getPayload());
	// am.setPending(da.getPending());
	// am.setRoolbackable(da.getRollbackable());
	// am.setCreateDate(DateTimeUtils.convertDateToString(da.getCreateDate(),
	// DateTimeUtils._TIMESTAMP));
	// am.setModifiedDate(DateTimeUtils.convertDateToString(da.getModifiedDate(),
	// DateTimeUtils._TIMESTAMP));
	// am.setDueDate(DateTimeUtils.convertDateToString(da.getDueDate(),
	// DateTimeUtils._TIMESTAMP));
	// am.setNextActionId(da.getNextActionId());
	// am.setState(da.getState());
	// am.setStepCode(da.getStepCode());
	// am.setStepName(da.getStepName());
	// am.setUserId(da.getUserId());
	//
	// lstActionModels.add(am);
	// }
	//
	// dsm.getActions().addAll(lstActionModels);
	//
	// lstDsms.add(dsm);
	// }
	//
	// result.getData().addAll(lstDsms);
	// return result;
	// }

	@Override
	public Response addDossierFileByEForm(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		Attachment file, String id, String partNo, String removed, String eForm,
		String formData) {

		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		_log.info("In dossier file create by eform");

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			long dossierId = GetterUtil.getLong(id);

			Dossier dossier = null;

			if (dossierId != 0) {
				dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
				if (Validator.isNull(dossier)) {
					dossier = DossierLocalServiceUtil.getByRef(groupId, id);
				}
			}
			else {
				dossier = DossierLocalServiceUtil.getByRef(groupId, id);
			}

			String referenceUid = UUID.randomUUID().toString();
			DossierFile dossierFile = null;
			DossierFileActions action = new DossierFileActionsImpl();
			DossierPart dossierPart =
				DossierPartLocalServiceUtil.fetchByTemplatePartNo(
					groupId, dossier.getDossierTemplateNo(), partNo);
			_log.info("__file:" + file);
			DataHandler dataHandler =
				(file != null) ? file.getDataHandler() : null;

			if (dataHandler != null && dataHandler.getInputStream() != null) {
				_log.info("__Start add file at:" + new Date());

				dossierFile =
					DossierFileLocalServiceUtil.getByGID_DID_PART_EFORM(
						groupId, dossier.getDossierId(), partNo, true, false);
				if (dossierFile == null) {
					dossierFile = action.addDossierFileEForm(
						groupId, dossier.getDossierId(), referenceUid,
						dossier.getDossierTemplateNo(), partNo,
						dossierPart.getFileTemplateNo(),
						dossierPart.getPartName(), dataHandler.getName(), 0,
						dataHandler.getInputStream(), StringPool.BLANK, DossierFileTerm.IS_SYNC_TRUE,
						serviceContext);
				}
				else {
					try {
						FileEntry fileEntry = FileUploadUtils.uploadDossierFile(
							user.getUserId(), groupId,
							dataHandler.getInputStream(),
							dossierPart.getPartName(), StringPool.BLANK, 0,
							serviceContext);

						if (fileEntry != null) {
							dossierFile.setFileEntryId(
								fileEntry.getFileEntryId());
						}
					}
					catch (Exception e) {
						_log.debug(e);
					}
				}

				_log.info("__End add file at:" + new Date());
			}
			else {
				dossierFile =
					DossierFileLocalServiceUtil.getByGID_DID_PART_EFORM(
						groupId, dossier.getDossierId(), partNo, true, false);
				if (dossierFile == null) {
					_log.info("dossierFile NULL");
					dossierFile = action.addDossierFileEForm(
						groupId, dossier.getDossierId(), referenceUid,
						dossier.getDossierTemplateNo(), partNo,
						dossierPart.getFileTemplateNo(),
						dossierPart.getPartName(), dossierPart.getPartName(), 0,
						null, StringPool.BLANK, DossierFileTerm.IS_SYNC_TRUE, serviceContext);
				}
			}

			if (Validator.isNotNull(formData)) {
				dossierFile.setFormData(formData);
			}
			if (Validator.isNotNull(removed)) {
				dossierFile.setRemoved(Boolean.parseBoolean(removed));
			}
			if (Validator.isNotNull(eForm)) {
				dossierFile.setEForm(Boolean.parseBoolean(eForm));
			}

			_log.info("__Start update dossier file at:" + new Date());

			DossierFileLocalServiceUtil.updateDossierFile(dossierFile);

			dossierFile = action.updateDossierFileFormData(
						groupId, dossier.getDossierId(), dossierFile.getReferenceUid(),
						Validator.isNotNull(formData) ? formData : dossierFile.getFormData(), serviceContext);

			_log.info("__End update dossier file at:" + new Date());

			DossierFileModel result =
				DossierFileUtils.mappingToDossierFileModel(dossierFile);

			_log.info("__End bind to dossierFile" + new Date());

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getSampleDataByEForm(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, String id,
		String partNo) {

		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		_log.info("In dossier file create");

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			long dossierId = GetterUtil.getLong(id);

			Dossier dossier = null;

			if (dossierId != 0) {
				dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
				if (Validator.isNull(dossier)) {
					dossier = DossierLocalServiceUtil.getByRef(groupId, id);
				}
			}
			else {
				dossier = DossierLocalServiceUtil.getByRef(groupId, id);
			}

			DossierPart dossierPart =
				DossierPartLocalServiceUtil.fetchByTemplatePartNo(
					groupId, dossier.getDossierTemplateNo(), partNo);
			// _log.info("Dossier part sample data: " +
			// dossierPart.getSampleData());
			String formData = AutoFillFormData.sampleDataBinding(
				dossierPart.getSampleData(), dossier.getDossierId(),
				serviceContext);

			return Response.status(HttpURLConnection.HTTP_OK).entity(formData).build();

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response removeDossierLucene(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, String id) {

		// Indexer<Dossier> indexer = IndexerRegistryUtil
		// .nullSafeGetIndexer(Dossier.class);
		return null;
	}

	@Override
	public Response removeConflictDossier(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext) {

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getConflictDossier(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		// long userId = user.getUserId();
		DossierActions actions = new DossierActionsImpl();
		// String authorizationHeader =
		// header.getHeaderString(HttpHeaders.AUTHORIZATION);
		// if (authorizationHeader == null ||
		// !authorizationHeader.startsWith("Bearer ")) {
		// throw new NotAuthorizedException("Authorization header must be
		// provided");
		// }
		//
		// String token =
		// authorizationHeader.substring("Bearer".length()).trim();
		// KeyGenerator keyGenerator = new OpenCPSKeyGenerator();
		try {

			// Validate the token
			// Key key = keyGenerator.generateKey();
			// Jwts.parser().setSigningKey(key).parseClaimsJws(token);

			LinkedHashMap<String, Object> params =
				new LinkedHashMap<String, Object>();
			params.put(Field.GROUP_ID, String.valueOf(groupId));

			// DossierResultsModel results = new DossierResultsModel();

			JSONObject jsonData = actions.getDossiers(
				user.getUserId(), company.getCompanyId(), groupId, params, null,
				-1, -1, serviceContext);

			// List<Dossier> lstInDbs =
			// DossierLocalServiceUtil.getDossiers(QueryUtil.ALL_POS,
			// QueryUtil.ALL_POS);

			long total = jsonData.getLong(ConstantUtils.TOTAL);
			JSONArray dossierArr = JSONFactoryUtil.createJSONArray();

			if (total > 0) {
				List<Document> lstDocuments =
					(List<Document>) jsonData.get(ConstantUtils.DATA);
				for (Document document : lstDocuments) {
					long dossierId = GetterUtil.getLong(
						document.get(DossierTerm.DOSSIER_ID));
					Dossier oldDossier =
						DossierLocalServiceUtil.fetchDossier(dossierId);
					if (oldDossier == null) {
						JSONObject dossierObj =
							JSONFactoryUtil.createJSONObject();
						dossierObj.put(DossierTerm.DOSSIER_ID, dossierId);
						dossierArr.put(dossierObj);
					}
				}
			}
			else {

			}

			return Response.status(HttpURLConnection.HTTP_OK).entity(
				dossierArr.toJSONString()).build();
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public Response resolveConflictDossier(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		// long userId = user.getUserId();
		DossierActions actions = new DossierActionsImpl();
		Indexer<Dossier> indexer =
			IndexerRegistryUtil.nullSafeGetIndexer(Dossier.class);

		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();
		params.put(Field.GROUP_ID, String.valueOf(groupId));

		// DossierResultsModel results = new DossierResultsModel();

		JSONObject jsonData = actions.getDossiers(
			user.getUserId(), company.getCompanyId(), groupId, params, null, -1,
			-1, serviceContext);

		// List<Dossier> lstInDbs =
		// DossierLocalServiceUtil.getDossiers(QueryUtil.ALL_POS,
		// QueryUtil.ALL_POS);

		long total = jsonData.getLong(ConstantUtils.TOTAL);
		// JSONArray dossierArr = JSONFactoryUtil.createJSONArray();

		if (total > 0) {
			List<Document> lstDocuments = (List<Document>) jsonData.get(ConstantUtils.DATA);
			for (Document document : lstDocuments) {
				long dossierId =
					GetterUtil.getLong(document.get(DossierTerm.DOSSIER_ID));
				long companyId =
					GetterUtil.getLong(document.get(Field.COMPANY_ID));
				String uid = document.get(Field.UID);
				Dossier oldDossier =
					DossierLocalServiceUtil.fetchDossier(dossierId);
				if (oldDossier == null) {
					try {
						indexer.delete(companyId, uid);
					}
					catch (SearchException e) {
						_log.error(e);
					}
				}
			}
		}

		params.put(DossierTerm.ORIGINALLITY, String.valueOf(9));
		JSONObject jsonDataGroup = actions.getDossiers(
			user.getUserId(), company.getCompanyId(), groupId, params, null, -1,
			-1, serviceContext);

		long totalGroup = jsonDataGroup.getLong(ConstantUtils.TOTAL);
		// JSONArray dossierArr = JSONFactoryUtil.createJSONArray();

		if (totalGroup > 0) {
			List<Document> lstDocuments =
				(List<Document>) jsonDataGroup.get(ConstantUtils.DATA);
			for (Document document : lstDocuments) {
				long dossierId =
					GetterUtil.getLong(document.get(DossierTerm.DOSSIER_ID));
				long companyId =
					GetterUtil.getLong(document.get(Field.COMPANY_ID));
				String uid = document.get(Field.UID);
				Dossier oldDossier =
					DossierLocalServiceUtil.fetchDossier(dossierId);
				if (oldDossier == null) {
					try {
						indexer.delete(companyId, uid);
					}
					catch (SearchException e) {
						_log.error(e);
					}
				}
			}
		}

		return Response.status(HttpURLConnection.HTTP_OK).entity(ConstantUtils.API_JSON_EMPTY).build();
	}

	@Override
	public Response addDossierPublish(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		DossierPublishModel input) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		try {
			Dossier dossier =
				CPSDossierBusinessLocalServiceUtil.addDossierPublish(
					groupId, company, user, serviceContext,
					DossierUtils.convertFormModelToPublishModel(input));
			//add by TrungNT
			if(dossier.getOriginality() == 0) {
				DVCQGIntegrationActionImpl actionImpl = new DVCQGIntegrationActionImpl();
				String mappingDossierStatus = actionImpl.getMappingStatus(dossier.getGroupId(), dossier);
				if(Validator.isNotNull(mappingDossierStatus)) {
					List<ServerConfig> lstScs = ServerConfigLocalServiceUtil.getByProtocol(dossier.getGroupId(), ServerConfigTerm.DVCQG_INTEGRATION);
					
					for (ServerConfig sc : lstScs) {
						try {
							List<PublishQueue> lstQueues = PublishQueueLocalServiceUtil.getByG_DID_SN_ST(dossier.getGroupId(), dossier.getDossierId(), sc.getServerNo(), new int[] { PublishQueueTerm.STATE_WAITING_SYNC, PublishQueueTerm.STATE_ALREADY_SENT });
							if (lstQueues == null || lstQueues.isEmpty()) {
								PublishQueueLocalServiceUtil.updatePublishQueue(dossier.getGroupId(), 0, dossier.getDossierId(), sc.getServerNo(), PublishQueueTerm.STATE_WAITING_SYNC, 0, serviceContext);					
							}

						} catch (PortalException e) {
							_log.debug(e);
						}
					}
				}
			}

			return Response.status(HttpURLConnection.HTTP_OK).entity(
				JSONFactoryUtil.looseSerializeDeep(dossier)).build();
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getDossierBarcode(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, String id) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		try {
			long dossierId = GetterUtil.getLong(id);

			Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);

			if (dossier == null) {
				dossier = DossierLocalServiceUtil.getByRef(groupId, id);
			}
			Code128 barcode = new Code128();
			barcode.setFontName(ConstantUtils.MONOSPACED);
			barcode.setFontSize(16);
			barcode.setModuleWidth(2);
			barcode.setBarHeight(50);
			barcode.setHumanReadableLocation(HumanReadableLocation.BOTTOM);
			barcode.setContent(dossier.getDossierNo());

			int width = barcode.getWidth();
			int height = barcode.getHeight();

			BufferedImage image =
				new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
			Graphics2D g2d = image.createGraphics();
			Java2DRenderer renderer =
				new Java2DRenderer(g2d, 1, Color.WHITE, Color.BLACK);
			renderer.render(barcode);
			File destDir = new File(ConstantUtils.BARCODE);
			if (!destDir.exists()) {
				destDir.mkdir();
			}
			String fileName = String.format(MessageUtil.getMessage(ConstantUtils.BARCODE_FILENAME), String.valueOf(dossier.getDossierId()));
			File file = new File(fileName);
			if (!file.exists()) {
				file.createNewFile();
			}

			if (file.exists()) {
				ImageIO.write(image, ConstantUtils.PNG, file);
				// String fileType = Files.probeContentType(file.toPath());
				ResponseBuilder responseBuilder = Response.ok((Object) file);
				String attachmentFilename = String.format(MessageUtil.getMessage(ConstantUtils.ATTACHMENT_FILENAME), file.getName());
				
				responseBuilder.header(
					ConstantUtils.CONTENT_DISPOSITION,
					attachmentFilename);
				responseBuilder.header(HttpHeaders.CONTENT_TYPE, ConstantUtils.MEDIA_TYPE_PNG);

				return responseBuilder.build();
			}
			else {
				return Response.status(
					HttpURLConnection.HTTP_NO_CONTENT).build();
			}

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getDossierQRcode(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, String id) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		try {
			long dossierId = GetterUtil.getLong(id);

			Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);

			if (dossier == null) {
				dossier = DossierLocalServiceUtil.getByRef(groupId, id);
			}
			QrCode qrcode = new QrCode();
			qrcode.setHumanReadableLocation(HumanReadableLocation.BOTTOM);
			qrcode.setDataType(Symbol.DataType.HIBC);
			qrcode.setPreferredVersion(40);
			qrcode.setContent(dossier.getDossierNo());

			int width = qrcode.getWidth();
			int height = qrcode.getHeight();

			BufferedImage image =
				new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
			Graphics2D g2d = image.createGraphics();
			Java2DRenderer renderer =
				new Java2DRenderer(g2d, 1, Color.WHITE, Color.BLACK);
			renderer.render(qrcode);
			File destDir = new File(ConstantUtils.BARCODE);
			if (!destDir.exists()) {
				destDir.mkdir();
			}
			String fileName = String.format(MessageUtil.getMessage(ConstantUtils.BARCODE_FILENAME), String.valueOf(dossier.getDossierId()));
			
			File file = new File(fileName);
			if (!file.exists()) {
				file.createNewFile();
			}

			if (file.exists()) {
				ImageIO.write(image, ConstantUtils.PNG, file);
				// String fileType = Files.probeContentType(file.toPath());
				ResponseBuilder responseBuilder = Response.ok((Object) file);
				String attachmentFilename = String.format(MessageUtil.getMessage(ConstantUtils.ATTACHMENT_FILENAME), file.getName());
				
				responseBuilder.header(
					ConstantUtils.CONTENT_DISPOSITION,
					attachmentFilename);
				responseBuilder.header(HttpHeaders.CONTENT_TYPE, ConstantUtils.MEDIA_TYPE_PNG);

				return responseBuilder.build();
			}
			else {
				return Response.status(
					HttpURLConnection.HTTP_NO_CONTENT).build();
			}

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getDossierSyncsByDossier(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, String id,
		Integer info, Integer start, Integer end) {

		BackendAuth auth = new BackendAuthImpl();
		DossierSyncActions actions = new DossierSyncActionsImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			if (start == null || start == 0) {
				start = QueryUtil.ALL_POS;
				end = QueryUtil.ALL_POS;
			}

			long groupId =
				GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			JSONObject jsonData = null;

			if (info == null) {
				jsonData = actions.getDossierSyncByDossiers(
					groupId, id, start, end, serviceContext);
			}
			else {
				jsonData = actions.getDossierSyncByDossierAndInfo(
					groupId, id, info, start, end, serviceContext);
			}
			DossierSyncV21ResultsModel results =
				new DossierSyncV21ResultsModel();

			results.setTotal(jsonData.getInt(ConstantUtils.TOTAL));
			if (jsonData != null && jsonData.getInt(ConstantUtils.TOTAL) > 0) {
				List<DossierSyncV21DataModel> lstDatas = new ArrayList<>();
				List<DossierSync> lstSyncs =
					(List<DossierSync>) jsonData.get(ConstantUtils.DATA);
				for (DossierSync ds : lstSyncs) {
					DossierSyncV21DataModel model =
						new DossierSyncV21DataModel();
					model.setActionCode(ds.getActionCode());
					model.setActionName(ds.getActionName());
					model.setActionNote(ds.getActionNote());
					model.setActionUser(ds.getActionUser());
					model.setCreateDate(ds.getCreateDate().getTime());
					model.setDossierId(ds.getDossierId());
					model.setDossierRefUid(ds.getDossierRefUid());
					model.setDossierSyncId(ds.getDossierSyncId());
					model.setInfoType(ds.getInfoType());
					model.setPayload(ds.getPayload());
					model.setSyncRefUid(ds.getSyncRefUid());
					model.setSyncType(ds.getSyncType());
					model.setUserId(ds.getUserId());

					lstDatas.add(model);
				}
				results.getData().addAll(lstDatas);
			}

			return Response.status(HttpURLConnection.HTTP_OK).entity(results).build();

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getDossierRelaseList(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		DossierSearchModel query) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		DossierActions actions = new DossierActionsImpl();
		long userId = user.getUserId();
		String emailLogin = user.getEmailAddress();
		_log.info("userId: " + userId);
		_log.info("emailLogin: " + emailLogin);

		try {
			if (Validator.isNull(query.getEnd()) || query.getEnd() == 0) {
				query.setStart(QueryUtil.ALL_POS);
				query.setEnd(QueryUtil.ALL_POS);
			}

			LinkedHashMap<String, Object> params =
				new LinkedHashMap<String, Object>();
			params.put(Field.GROUP_ID, String.valueOf(groupId));

			String step = query.getStep();
			// _log.info("step: "+step);
			StringBuilder strStatusStep = null;
			StringBuilder strSubStatusStep = null;
			if (Validator.isNotNull(step)) {
				strStatusStep = new StringBuilder();
				strSubStatusStep = new StringBuilder();
				String[] stepArr = step.split(StringPool.COMMA);
				if (stepArr != null && stepArr.length > 0) {
					String statusStep;
					String subStatusStep;
					for (int i = 0; i < stepArr.length; i++) {
						StepConfig stepConfig =
							StepConfigLocalServiceUtil.getByCode(
								groupId, stepArr[i]);
						if (stepConfig != null) {
							statusStep = stepConfig.getDossierStatus();
							subStatusStep = stepConfig.getDossierSubStatus();
							if (i == 0) {
								strStatusStep.append(statusStep);
								if (Validator.isNotNull(subStatusStep)) {
									strSubStatusStep.append(subStatusStep);
								}
								else {
									strSubStatusStep.append(DossierTerm.EMPTY);
								}
							}
							else {
								strStatusStep.append(StringPool.COMMA);
								strStatusStep.append(statusStep);
								strSubStatusStep.append(StringPool.COMMA);
								if (Validator.isNotNull(subStatusStep)) {
									strSubStatusStep.append(subStatusStep);
								}
								else {
									strSubStatusStep.append(DossierTerm.EMPTY);
								}
							}
						}
					}
				}
			}

			String fromReleaseDate =
				APIDateTimeUtils.convertNormalDateToLuceneDate(
					query.getFromReleaseDate());
			String toReleaseDate =
				APIDateTimeUtils.convertNormalDateToLuceneDate(
					query.getToReleaseDate());

			params.put(DossierTerm.EMAIL_USER_LOGIN, emailLogin);
			params.put(DossierTerm.FROM_RELEASE_DATE, fromReleaseDate);
			params.put(DossierTerm.TO_RELEASE_DATE, toReleaseDate);
			// Process follow StepCode
			if (Validator.isNotNull(strStatusStep)) {
				params.put(
					DossierTerm.DOSSIER_STATUS_STEP, strStatusStep != null
						? strStatusStep.toString() : StringPool.BLANK);
			}
			else {
				params.put(DossierTerm.DOSSIER_STATUS_STEP, StringPool.BLANK);
			}
			if (Validator.isNotNull(strSubStatusStep)) {
				params.put(
					DossierTerm.DOSSIER_SUBSTATUS_STEP, strSubStatusStep != null
						? strSubStatusStep.toString() : StringPool.BLANK);
			}
			else {
				params.put(
					DossierTerm.DOSSIER_SUBSTATUS_STEP, StringPool.BLANK);
			}

			Sort[] sorts = null;
			String querySort;
			if (Validator.isNull(query.getSort())) {
				querySort = String.format(MessageUtil.getMessage(ConstantUtils.QUERY_SORT), DossierTerm.CREATE_DATE);
				sorts = new Sort[] {
					SortFactoryUtil.create(
						querySort, Sort.STRING_TYPE,
						GetterUtil.getBoolean(query.getOrder()))
				};
			}
			else {
				querySort = String.format(MessageUtil.getMessage(ConstantUtils.QUERY_SORT), query.getSort());
				sorts = new Sort[] {
					SortFactoryUtil.create(
						querySort, Sort.STRING_TYPE,
						GetterUtil.getBoolean(query.getOrder()))
				};
			}

			DossierResultPublishModel results = new DossierResultPublishModel();

			JSONObject jsonData = actions.getDossiers(
				user.getUserId(), company.getCompanyId(), groupId, params,
				sorts, query.getStart(), query.getEnd(), serviceContext);

			results.setTotal(jsonData.getInt(ConstantUtils.TOTAL));

			results.getData().addAll(
				DossierUtils.mappingForGetPublishList(
					(List<Document>) jsonData.get(ConstantUtils.DATA)));

			return Response.status(HttpURLConnection.HTTP_OK).entity(results).build();

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	private String sequenceOf(List<ProcessStep> lstSteps, String stepCode) {

		for (ProcessStep ps : lstSteps) {
			if (ps.getStepCode().equals(stepCode)) {
				return ps.getSequenceNo();
			}
		}

		return StringPool.BLANK;
	}

	@Override
	public Response getMermaidGraphDetailDossier(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, String id) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		BackendAuth auth = new BackendAuthImpl();

		try {

			Dossier dossier = DossierUtils.getDossier(id, groupId);

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			if (dossier != null && dossier.getDossierActionId() != 0) {
				DossierAction lastAction =
					DossierActionLocalServiceUtil.fetchDossierAction(
						dossier.getDossierActionId());
				ServiceProcess serviceProcess =
					ServiceProcessLocalServiceUtil.getServiceProcess(
						lastAction.getServiceProcessId());
				if (serviceProcess != null) {
					StringBuilder result = new StringBuilder();
					result.append(DossierManagementConstants.START_GRAPH);
					List<ProcessSequence> lstSequences =
						ProcessSequenceLocalServiceUtil.getByServiceProcess(
							groupId, serviceProcess.getServiceProcessId());
					List<ProcessStep> lstSteps =
						ProcessStepLocalServiceUtil.getProcessStepbyServiceProcessId(
							serviceProcess.getServiceProcessId());

					for (ProcessSequence ps : lstSequences) {
						result.append(ps.getSequenceNo());
						result.append(DossierManagementConstants.START_NODE);
						result.append(ps.getSequenceRole());
						result.append(DossierManagementConstants.END_NOTE_TITLE);
						result.append(ps.getSequenceName());
						result.append(DossierManagementConstants.END_NODE);
						for (ProcessSequence psOther : lstSequences) {
							List<String> arcs = new ArrayList<>();

							if (!psOther.getSequenceNo().equals(
								ps.getSequenceNo())) {
								List<ProcessAction> lstActions =
									ProcessActionLocalServiceUtil.getProcessActionbyServiceProcessId(
										serviceProcess.getServiceProcessId());
								for (ProcessAction pa : lstActions) {
									if (sequenceOf(
										lstSteps, pa.getPreStepCode()).equals(
											ps.getSequenceNo()) &&
										sequenceOf(
											lstSteps,
											pa.getPostStepCode()).equals(
												psOther.getSequenceNo()) &&
										!arcs.contains(pa.getActionName())) {
										result.append(ps.getSequenceNo());
										result.append(DossierManagementConstants.START_ARROW);
										result.append(pa.getActionName());
										result.append(DossierManagementConstants.END_ARROW);
										result.append(psOther.getSequenceNo());
										result.append(DossierManagementConstants.CR);
										if (lastAction.getStepCode().equals(
											pa.getPreStepCode())) {
											String sequenceStyle = String.format(DossierManagementConstants.GRAPH_STYLE, ps.getSequenceNo());
											result.append(sequenceStyle);
											result.append(DossierManagementConstants.CR);
										}
										arcs.add(pa.getActionName());
									}
								}
							}
						}
					}

					return Response.status(HttpURLConnection.HTTP_OK).entity(
						result.toString()).build();
				}
				else {
					return Response.status(HttpURLConnection.HTTP_OK).entity(
						StringPool.BLANK).build();
				}
			}
			else {
				return Response.status(HttpURLConnection.HTTP_OK).entity(StringPool.BLANK).build();
			}
		}
		catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getAssignUsers(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, String id) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		BackendAuth auth = new BackendAuthImpl();
		DossierActions actions = new DossierActionsImpl();

		try {

			Dossier dossier = DossierUtils.getDossier(id, groupId);

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			if (dossier != null && dossier.getDossierActionId() != 0) {
				JSONObject result = JSONFactoryUtil.createJSONObject();
				DossierAction da =
					DossierActionLocalServiceUtil.fetchDossierAction(
						dossier.getDossierActionId());
				ProcessStep ps = ProcessStepLocalServiceUtil.fetchBySC_GID(
					da.getStepCode(), groupId, da.getServiceProcessId());

				List<User> lstUsers = actions.getAssignUsersByStep(user.getUserId(), dossier, ps);
				result.put(ConstantUtils.TOTAL, lstUsers.size());
				JSONArray userArr = JSONFactoryUtil.createJSONArray();

				for (User u : lstUsers) {
					JSONObject userObj = JSONFactoryUtil.createJSONObject();
					userObj.put(Field.USER_ID, u.getUserId());
					// userObj.put(Field.USER_NAME, u.getFullName());
					Employee employee =
						EmployeeLocalServiceUtil.fetchByF_mappingUserId(
							dossier.getGroupId(), u.getUserId());
					if (employee != null) {
						userObj.put(Field.USER_NAME, employee.getFullName());
					}
					else {
						userObj.put(Field.USER_NAME, u.getFullName());
					}
					DossierActionUserPK pk = new DossierActionUserPK();
					pk.setDossierActionId(dossier.getDossierActionId());
					pk.setUserId(u.getUserId());
					DossierActionUser dau =
						DossierActionUserLocalServiceUtil.fetchDossierActionUser(
							pk);
					if (dau != null) {
						userObj.put(DossierActionUserTerm.ASSIGNED, dau.getAssigned());
					}
					else {
						userObj.put(DossierActionUserTerm.ASSIGNED, 0);
					}

					userArr.put(userObj);
				}

				result.put(ConstantUtils.DATA, userArr);

				return Response.status(HttpURLConnection.HTTP_OK).entity(
					result.toJSONString()).build();
			}
			else {
				return Response.status(HttpURLConnection.HTTP_OK).entity(StringPool.BLANK).build();
			}
		}
		catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response assignUsers(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, String id,
		String assignUsers, Integer delegacy) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		BackendAuth auth = new BackendAuthImpl();
		org.opencps.dossiermgt.action.DossierActionUser actions =
			new DossierActionUserImpl();

		try {

			Dossier dossier = DossierUtils.getDossier(id, groupId);

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			if (dossier != null && dossier.getDossierActionId() != 0) {
				JSONArray assignUserArr =
					JSONFactoryUtil.createJSONArray(assignUsers);
				DossierAction da =
					DossierActionLocalServiceUtil.fetchDossierAction(
						dossier.getDossierActionId());

				actions.assignDossierActionUser(
					dossier, 1, da, user.getUserId(), groupId, 0, assignUserArr,
					delegacy);
				// Reindex dossier
				Indexer<Dossier> indexer =
					IndexerRegistryUtil.nullSafeGetIndexer(Dossier.class);
				indexer.reindex(dossier);

				return Response.status(HttpURLConnection.HTTP_OK).entity(StringPool.BLANK).build();
			}
			else {
				return Response.status(HttpURLConnection.HTTP_OK).entity(StringPool.BLANK).build();
			}
		}
		catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response refreshDossier(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, String id) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		BackendAuth auth = new BackendAuthImpl();
		try {

			Dossier dossier = DossierUtils.getDossier(id, groupId);

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			if (dossier != null && dossier.getDossierActionId() != 0) {
				publishEvent(dossier);
				return Response.status(HttpURLConnection.HTTP_OK).entity(StringPool.BLANK).build();
			}
			else {
				return Response.status(HttpURLConnection.HTTP_OK).entity(StringPool.BLANK).build();
			}
		}
		catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}
	}

	private void publishEvent(Dossier dossier) {

		Message message = new Message();
		JSONObject msgData = JSONFactoryUtil.createJSONObject();

		message.put(DossierManagementConstants.MSG_TO_ENGINE, msgData);
		message.put(
			DossierManagementConstants.DOSSIER_KEY, DossierMgtUtils.convertDossierToJSON(
				dossier, dossier.getDossierActionId()));

		MessageBusUtil.sendMessage(
			DossierTerm.PUBLISH_DOSSIER_DESTINATION, message);

		Message lgspMessage = new Message();
		JSONObject lgspMsgData = msgData;

		lgspMessage.put(DossierManagementConstants.MSG_TO_ENGINE, lgspMsgData);
		lgspMessage.put(
			DossierManagementConstants.DOSSIER_KEY, DossierMgtUtils.convertDossierToJSON(
				dossier, dossier.getDossierActionId()));

		MessageBusUtil.sendMessage(
			DossierTerm.LGSP_DOSSIER_DESTINATION, lgspMessage);
	}

	@Override
	public Response fixDossierSync(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, String id) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		BackendAuth auth = new BackendAuthImpl();
		try {

			Dossier dossier = DossierUtils.getDossier(id, groupId);

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			if (dossier != null && dossier.getDossierActionId() != 0) {
				List<DossierAction> lstActions =
					DossierActionLocalServiceUtil.findByG_DID(
						groupId, dossier.getDossierId());
				ServiceProcess serviceProcess = null;

				for (DossierAction da : lstActions) {
					ActionConfig ac = ActionConfigLocalServiceUtil.getByCode(
						groupId, da.getActionCode());
					if (serviceProcess == null) {
						serviceProcess =
							ServiceProcessLocalServiceUtil.fetchServiceProcess(
								da.getServiceProcessId());
					}
					ProcessAction proAction =
						ProcessActionLocalServiceUtil.fetchByF_GID_SID_AC_PRE_POST(
							groupId,
							serviceProcess != null
								? serviceProcess.getServiceProcessId() : 0l,
							da.getActionCode(), da.getFromStepCode(),
							da.getStepCode());
					// ProcessAction proAction =
					// ProcessActionLocalServiceUtil.fetchBySPID_AC(serviceProcess
					// != null ? serviceProcess.getServiceProcessId() : 0l,
					// da.getActionCode());

					if (ac != null) {
						if (ac.getSyncType() > 0) {
							String dossierRefUid = dossier.getReferenceUid();
							String syncRefUid = UUID.randomUUID().toString();

							int state = DossierActionUtils.getSyncState(
								ac.getSyncType(), dossier);

							JSONObject payloadObject =
								JSONFactoryUtil.createJSONObject(
									da.getPayload());

							// Update payload

							JSONArray dossierFilesArr =
								JSONFactoryUtil.createJSONArray();
							if (ac.getSyncType() == DossierSyncTerm.SYNCTYPE_REQUEST) {
								if (dossier.getOriginDossierId() == 0) {
									List<DossierFile> lstFiles =
										DossierFileLocalServiceUtil.findByDID(
											dossier.getDossierId());
									if (lstFiles.size() > 0) {
										for (DossierFile df : lstFiles) {
											JSONObject dossierFileObj =
												JSONFactoryUtil.createJSONObject();
											dossierFileObj.put(
												DossierFileTerm.REFERENCE_UID,
												df.getReferenceUid());
											dossierFilesArr.put(dossierFileObj);
										}
									}
								}
								else {
									ServiceConfig serviceConfig =
										ServiceConfigLocalServiceUtil.getBySICodeAndGAC(
											groupId, dossier.getServiceCode(),
											dossier.getGovAgencyCode());
									List<ProcessOption> lstOptions =
										ProcessOptionLocalServiceUtil.getByServiceProcessId(
											serviceConfig.getServiceConfigId());

									if (serviceConfig != null) {
										if (lstOptions.size() > 0) {
											ProcessOption processOption =
												lstOptions.get(0);

											DossierTemplate dossierTemplate =
												DossierTemplateLocalServiceUtil.fetchDossierTemplate(
													processOption.getDossierTemplateId());
											List<DossierPart> lstParts =
												DossierPartLocalServiceUtil.getByTemplateNo(
													groupId,
													dossierTemplate.getTemplateNo());

											List<DossierFile> lstFiles =
												DossierFileLocalServiceUtil.findByDID(
													dossier.getOriginDossierId());
											if (lstFiles.size() > 0) {
												for (DossierFile df : lstFiles) {
													boolean flagHslt = false;
													for (DossierPart dp : lstParts) {
														if (dp.getPartNo().equals(
															df.getDossierPartNo())) {
															flagHslt = true;
															break;
														}
													}
													if (flagHslt) {
														JSONObject dossierFileObj =
															JSONFactoryUtil.createJSONObject();
														dossierFileObj.put(
															DossierFileTerm.REFERENCE_UID,
															df.getReferenceUid());
														dossierFilesArr.put(
															dossierFileObj);
													}
												}
											}
										}
									}

								}
							}
							else {
								// Sync result files

							}

							payloadObject.put(DossierSyncTerm.PAYLOAD_SYNC_FILES, dossierFilesArr);

							if (Validator.isNotNull(
								proAction.getReturnDossierFiles())) {
								List<DossierFile> lsDossierFile =
									DossierFileLocalServiceUtil.findByDID(
										dossier.getDossierId());
								dossierFilesArr =
									JSONFactoryUtil.createJSONArray();

								// check return file
								List<String> returnDossierFileTemplateNos =
									ListUtil.toList(
										StringUtil.split(
											proAction.getReturnDossierFiles()));

								for (DossierFile dossierFile : lsDossierFile) {
									if (returnDossierFileTemplateNos.contains(
										dossierFile.getFileTemplateNo())) {
										JSONObject dossierFileObj =
											JSONFactoryUtil.createJSONObject();
										dossierFileObj.put(
											DossierFileTerm.REFERENCE_UID,
											dossierFile.getReferenceUid());
										dossierFilesArr.put(dossierFileObj);

									}

								}
								payloadObject.put(
									DossierSyncTerm.PAYLOAD_SYNC_FILES, dossierFilesArr);
							}

							List<DossierDocument> lstDossierDocuments =
								DossierDocumentLocalServiceUtil.getDossierDocumentList(
									dossier.getDossierId(), QueryUtil.ALL_POS,
									QueryUtil.ALL_POS);
							JSONArray dossierDocumentArr =
								JSONFactoryUtil.createJSONArray();

							for (DossierDocument dossierDocument : lstDossierDocuments) {
								JSONObject dossierDocumentObj =
									JSONFactoryUtil.createJSONObject();
								dossierDocumentObj.put(
									DossierDocumentTerm.REFERENCE_UID,
									dossierDocument.getReferenceUid());
								dossierDocumentArr.put(dossierDocumentObj);
							}
							payloadObject.put(DossierSyncTerm.PAYLOAD_SYNC_FILES, dossierFilesArr);
							payloadObject.put(
								DossierSyncTerm.PAYLOAD_SYNC_DOCUMENTS, dossierDocumentArr);

							// Put dossier note
							payloadObject.put(
								DossierTerm.DOSSIER_NOTE,
								dossier.getDossierNote());

							DossierSyncLocalServiceUtil.updateDossierSync(
								groupId, da.getUserId(), dossier.getDossierId(),
								dossierRefUid, syncRefUid,
								da.getDossierActionId(), da.getActionCode(),
								da.getActionName(), da.getActionUser(),
								da.getActionNote(), ac.getSyncType(),
								ac.getInfoType(), payloadObject.toJSONString(),
								serviceProcess.getServerNo(), state);

							if (state == DossierSyncTerm.STATE_NOT_SYNC &&
								ac != null &&
								ac.getEventType() == ActionConfigTerm.EVENT_TYPE_SENT) {
								publishEvent(dossier);
							}

						}
					}
				}
				return Response.status(HttpURLConnection.HTTP_OK).entity(StringPool.BLANK).build();
			}
			else {
				return Response.status(HttpURLConnection.HTTP_OK).entity(StringPool.BLANK).build();
			}
		}
		catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response resyncDossier(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, String id) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		BackendAuth auth = new BackendAuthImpl();
		try {

			Dossier dossier = DossierUtils.getDossier(id, groupId);

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			List<Role> userRoles = user.getRoles();
			boolean isAdmin = false;
			for (Role r : userRoles) {
				if (r.getName().startsWith(ConstantUtils.ROLE_ADMIN)) {
					isAdmin = true;
					break;
				}
			}

			if (!isAdmin) {
				throw new UnauthenticationException();
			}

			if (dossier != null && dossier.getDossierActionId() != 0) {
				List<DossierSync> lstSyncs =
					DossierSyncLocalServiceUtil.findByG_DID_ST(
						groupId, dossier.getDossierId(),
						DossierSyncTerm.STATE_ACK_ERROR);
				for (DossierSync ds : lstSyncs) {
					ds.setState(DossierSyncTerm.STATE_WAITING_SYNC);
					ds.setRetry(0);

					DossierSyncLocalServiceUtil.updateDossierSync(ds);
				}
			}

			return Response.status(HttpURLConnection.HTTP_OK).entity(StringPool.BLANK).build();
		}
		catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response forceResyncDossier(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, String id) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		BackendAuth auth = new BackendAuthImpl();
		try {

			Dossier dossier = DossierUtils.getDossier(id, groupId);

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			List<Role> userRoles = user.getRoles();
			boolean isAdmin = false;
			for (Role r : userRoles) {
				if (r.getName().startsWith(ConstantUtils.ROLE_ADMIN)) {
					isAdmin = true;
					break;
				}
			}

			if (!isAdmin) {
				throw new UnauthenticationException();
			}

			if (dossier != null && dossier.getDossierActionId() != 0) {
				List<DossierSync> lstSyncs =
					DossierSyncLocalServiceUtil.findByG_DID(
						groupId, dossier.getDossierId());

				for (DossierSync ds : lstSyncs) {
					ds.setState(DossierSyncTerm.STATE_WAITING_SYNC);
					ds.setRetry(0);

					DossierSyncLocalServiceUtil.updateDossierSync(ds);
				}
			}

			return Response.status(HttpURLConnection.HTTP_OK).entity(StringPool.BLANK).build();
		}
		catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response restoreDossier(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, String id) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			List<Role> userRoles = user.getRoles();
			boolean isAdmin = false;
			for (Role r : userRoles) {
				if (r.getName().startsWith(ConstantUtils.ROLE_ADMIN)) {
					isAdmin = true;
					break;
				}
			}

			if (!isAdmin) {
				throw new UnauthenticationException();
			}

			Dossier dossier = DossierUtils.getDossier(id, groupId);
			if (dossier != null) {
				int originality = dossier.getOriginality();
				Dossier removeDossier = null;
				if (originality < 0) {
					dossier.setOriginality(-originality);
					removeDossier =
						DossierLocalServiceUtil.updateDossier(dossier);
				}

				DossierDetailModel result = null;
				if (removeDossier != null) {
					result = DossierUtils.mappingForGetDetail(
						removeDossier, user.getUserId());
				}
				return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();
			}
			else {
				return Response.status(HttpServletResponse.SC_FORBIDDEN).entity(
					MessageUtil.getMessage(ConstantUtils.DOSSIER_MESSAGE_NOTFOUND)).build();
			}
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response republishPublishQueue(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			List<Role> userRoles = user.getRoles();
			boolean isAdmin = false;
			for (Role r : userRoles) {
				if (r.getName().startsWith(ConstantUtils.ROLE_ADMIN)) {
					isAdmin = true;
					break;
				}
			}

			if (!isAdmin) {
				throw new UnauthenticationException();
			}

			List<PublishQueue> lstPqs =
				PublishQueueLocalServiceUtil.getByStatus(
					PublishQueueTerm.STATE_ACK_ERROR, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS);
			for (PublishQueue pq : lstPqs) {
				if (groupId == 0) {
					pq.setStatus(PublishQueueTerm.STATE_WAITING_SYNC);
					pq.setRetry(0);
					PublishQueueLocalServiceUtil.updatePublishQueue(pq);
				}
				else if (pq.getGroupId() == groupId) {
					pq.setStatus(PublishQueueTerm.STATE_WAITING_SYNC);
					pq.setRetry(0);
					PublishQueueLocalServiceUtil.updatePublishQueue(pq);
				}
			}

			return Response.status(HttpURLConnection.HTTP_OK).entity(StringPool.BLANK).build();
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getDossiersByErrorStep(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			List<Role> userRoles = user.getRoles();
			boolean isAdmin = false;
			for (Role r : userRoles) {
				if (r.getName().startsWith(ConstantUtils.ROLE_ADMIN)) {
					isAdmin = true;
					break;
				}
			}

			if (!isAdmin) {
				throw new UnauthenticationException();
			}

			List<Dossier> lstDossiers =
				DossierLocalServiceUtil.findDossierByGroup(groupId);
			List<Dossier> errorDossiers = new ArrayList<>();

			for (Dossier d : lstDossiers) {
				if (d.getDossierActionId() != 0) {
					DossierAction lastDa =
						DossierActionLocalServiceUtil.fetchDossierAction(
							d.getDossierActionId());
					if (lastDa != null) {
						String stepCode = lastDa.getStepCode();
						ProcessStep step =
							ProcessStepLocalServiceUtil.fetchBySC_GID(
								stepCode, groupId,
								lastDa.getServiceProcessId());
						if (step == null) {
							errorDossiers.add(d);
						}
					}
				}
			}

			DossierResultsModel results = new DossierResultsModel();
			results.setTotal(errorDossiers.size());

			for (Dossier d : errorDossiers) {
				DossierDataModel model = new DossierDataModel();
				model.setDossierId((int) d.getDossierId());
				model.setAddress(d.getAddress());
				model.setReferenceUid(d.getReferenceUid());
				model.setServiceCode(d.getServiceCode());
				model.setServiceName(d.getServiceName());

				results.getData().add(model);
			}
			return Response.status(HttpURLConnection.HTTP_OK).entity(results).build();
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response checkStep(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, String id) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			List<Role> userRoles = user.getRoles();
			boolean isAdmin = false;
			for (Role r : userRoles) {
				if (r.getName().startsWith(ConstantUtils.ROLE_ADMIN)) {
					isAdmin = true;
					break;
				}
			}

			if (!isAdmin) {
				throw new UnauthenticationException();
			}

			Dossier dossier = DossierUtils.getDossier(id, groupId);
			if (dossier != null) {
				if (dossier.getDossierActionId() != 0) {
					DossierAction lastDa =
						DossierActionLocalServiceUtil.fetchDossierAction(
							dossier.getDossierActionId());
					if (lastDa != null) {
						String stepCode = lastDa.getStepCode();
						ProcessStep step =
							ProcessStepLocalServiceUtil.fetchBySC_GID(
								stepCode, groupId,
								lastDa.getServiceProcessId());
						do {
							if (step == null) {
								DossierActionLocalServiceUtil.updateState(
									lastDa.getDossierActionId(),
									DossierActionTerm.STATE_ROLLBACK);

								DossierAction previousAction =
									DossierActionLocalServiceUtil.fetchDossierAction(
										lastDa.getPreviousActionId());
								if (previousAction != null) {
									DossierActionLocalServiceUtil.updateState(
										previousAction.getDossierActionId(),
										DossierActionTerm.STATE_WAITING_PROCESSING);
									try {
										DossierActionLocalServiceUtil.updateNextActionId(
											previousAction.getDossierActionId(),
											0);
										dossier.setDossierActionId(
											previousAction.getDossierActionId());
										_log.info(
											"Dossier action id: " +
												dossier.getDossierActionId());
									}
									catch (PortalException e) {
										return BusinessExceptionImpl.processException(
											e);
									}
								}
								else {
									lastDa =
										DossierActionLocalServiceUtil.fetchDossierAction(
											dossier.getDossierActionId());
									stepCode = lastDa.getStepCode();
									step =
										ProcessStepLocalServiceUtil.fetchBySC_GID(
											stepCode, groupId,
											lastDa.getServiceProcessId());
									break;
								}
							}
							else {
								break;
							}

							lastDa =
								DossierActionLocalServiceUtil.fetchDossierAction(
									dossier.getDossierActionId());
							stepCode = lastDa.getStepCode();
							step = ProcessStepLocalServiceUtil.fetchBySC_GID(
								stepCode, groupId,
								lastDa.getServiceProcessId());
						}
						while (step == null);

						_log.info("Dossier step: " + step);
						if (step != null) {
							JSONObject jsonDataStatusText = getStatusText(
								dossier.getGroupId(), DossierManagementConstants.DOSSIER_STATUS_KEY,
								step.getDossierStatus(),
								step.getDossierSubStatus());

							dossier.setDossierStatus(step.getDossierStatus());
							dossier.setDossierStatusText(
								jsonDataStatusText != null
									? jsonDataStatusText.getString(
										step.getDossierStatus())
									: StringPool.BLANK);
							dossier.setDossierSubStatus(
								step.getDossierSubStatus());
							dossier.setDossierSubStatusText(
								jsonDataStatusText != null
									? jsonDataStatusText.getString(
										step.getDossierSubStatus())
									: StringPool.BLANK);

							DossierLocalServiceUtil.updateDossier(dossier);
						}
					}
				}

				return Response.status(HttpURLConnection.HTTP_OK).entity(StringPool.BLANK).build();
			}
			else {
				return Response.status(HttpServletResponse.SC_FORBIDDEN).entity(
					MessageUtil.getMessage(ConstantUtils.DOSSIER_MESSAGE_NOTFOUND)).build();
			}
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	private JSONObject getStatusText(
		long groupId, String collectionCode, String curStatus,
		String curSubStatus) {

		JSONObject jsonData = null;
		DictCollection dc =
			DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(
				collectionCode, groupId);

		if (Validator.isNotNull(dc) && Validator.isNotNull(curStatus)) {
			jsonData = JSONFactoryUtil.createJSONObject();
			DictItem it = DictItemLocalServiceUtil.fetchByF_dictItemCode(
				curStatus, dc.getPrimaryKey(), groupId);
			if (Validator.isNotNull(it)) {
				jsonData.put(curStatus, it.getItemName());
				if (Validator.isNotNull(curSubStatus)) {
					DictItem dItem =
						DictItemLocalServiceUtil.fetchByF_dictItemCode(
							curSubStatus, dc.getPrimaryKey(), groupId);
					if (Validator.isNotNull(dItem)) {
						jsonData.put(curSubStatus, dItem.getItemName());
					}
				}
			}
		}

		return jsonData;
	}

	@Override
	public Response gotoStep(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, String id,
		String stepCode) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			List<Role> userRoles = user.getRoles();
			boolean isAdmin = false;
			for (Role r : userRoles) {
				if (r.getName().startsWith(ConstantUtils.ROLE_ADMIN)) {
					isAdmin = true;
					break;
				}
			}

			if (!isAdmin) {
				throw new UnauthenticationException();
			}

			Dossier dossier = DossierUtils.getDossier(id, groupId);
			if (dossier != null) {
				Date now = new Date();
				if (dossier.getDossierActionId() != 0) {
					DossierAction lastda =
						DossierActionLocalServiceUtil.fetchDossierAction(
							dossier.getDossierActionId());
					if (lastda != null) {
						long serviceProcessId = lastda.getServiceProcessId();
						ProcessStep step =
							ProcessStepLocalServiceUtil.fetchBySC_GID(
								stepCode, groupId, serviceProcessId);
						if (step != null) {
							JSONObject jsonDataStatusText = getStatusText(
								dossier.getGroupId(), DossierManagementConstants.DOSSIER_STATUS_KEY,
								step.getDossierStatus(),
								step.getDossierSubStatus());

							dossier.setDossierStatus(step.getDossierStatus());
							dossier.setDossierStatusText(
								jsonDataStatusText != null
									? jsonDataStatusText.getString(
										step.getDossierStatus())
									: StringPool.BLANK);
							dossier.setDossierSubStatus(
								step.getDossierSubStatus());
							dossier.setDossierSubStatusText(
								jsonDataStatusText != null
									? jsonDataStatusText.getString(
										step.getDossierSubStatus())
									: StringPool.BLANK);

							String curStatus = step.getDossierStatus();
							boolean stateProcessed = false;

							if (DossierTerm.DOSSIER_STATUS_RELEASING.equals(
								curStatus) ||
								DossierTerm.DOSSIER_STATUS_DENIED.equals(
									curStatus) ||
								DossierTerm.DOSSIER_STATUS_UNRESOLVED.equals(
									curStatus) ||
								DossierTerm.DOSSIER_STATUS_CANCELLED.equals(
									curStatus) ||
								DossierTerm.DOSSIER_STATUS_DONE.equals(
									curStatus)) {
								if (Validator.isNull(
									dossier.getReleaseDate())) {
									dossier.setReleaseDate(now);
									stateProcessed = true;
								}
							}
							if (DossierTerm.DOSSIER_STATUS_DENIED.equals(
								curStatus) ||
								DossierTerm.DOSSIER_STATUS_UNRESOLVED.equals(
									curStatus) ||
								DossierTerm.DOSSIER_STATUS_CANCELLED.equals(
									curStatus) ||
								DossierTerm.DOSSIER_STATUS_DONE.equals(
									curStatus)) {
								if (Validator.isNull(dossier.getFinishDate())) {
									if (Validator.isNull(
										dossier.getReleaseDate())) {
										dossier.setReleaseDate(now);
									}
									dossier.setFinishDate(now);
								}
							}

							DossierLocalServiceUtil.updateDossier(dossier);

							DossierAction dossierAction =
								DossierActionLocalServiceUtil.updateDossierAction(
									groupId, 0, dossier.getDossierId(),
									serviceProcessId,
									dossier.getDossierActionId(),
									lastda.getStepCode(), lastda.getStepName(),
									lastda.getSequenceNo(), DossierManagementConstants.SPECIAL_ACTION_CODE,
									user.getFullName(), DossierManagementConstants.SPECIAL_ACTION_NAME,
									StringPool.BLANK, 0, stepCode,
									step.getStepName(), step.getSequenceNo(),
									null, 0l, StringPool.BLANK,
									step.getStepInstruction(),
									DossierActionTerm.STATE_WAITING_PROCESSING,
									DossierActionTerm.EVENT_STATUS_NOT_CREATED,
									serviceContext);

							lastda.setNextActionId(
								dossierAction.getDossierActionId());
							DossierActionLocalServiceUtil.updateDossierAction(
								lastda);
							DossierActionLocalServiceUtil.updateState(
								lastda.getDossierActionId(),
								DossierActionTerm.STATE_ALREADY_PROCESSED);

							if (stateProcessed) {
								DossierActionLocalServiceUtil.updateState(
									dossierAction.getDossierActionId(),
									DossierActionTerm.STATE_ALREADY_PROCESSED);
							}
							DossierDetailModel model = new DossierDetailModel();
							model.setDossierId((int) dossier.getDossierId());
							model.setReferenceUid(dossier.getReferenceUid());

							if (dossier.getDossierActionId() != 0) {
								publishEvent(dossier);
							}

							DossierMgtUtils.processSyncGotoDossier(
								dossier, stepCode);

							return Response.status(
								HttpServletResponse.SC_OK).entity(
									model).build();
						}
						else {
							return Response.status(
								HttpServletResponse.SC_BAD_REQUEST).entity(
									MessageUtil.getMessage(ConstantUtils.DOSSIER_MESSAGE_STEPNOTFOUND)).build();
						}
					}
				}
				return Response.status(
					HttpServletResponse.SC_BAD_REQUEST).entity(
						MessageUtil.getMessage(ConstantUtils.DOSSIER_MESSAGE_DOSSIERNOTINPROCESS)).build();
			}
			else {
				return Response.status(HttpServletResponse.SC_FORBIDDEN).entity(
					MessageUtil.getMessage(ConstantUtils.DOSSIER_MESSAGE_NOTFOUND)).build();
			}
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response reindexDossier(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, String id) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			List<Role> userRoles = user.getRoles();
			boolean isAdmin = false;
			for (Role r : userRoles) {
				if (r.getName().startsWith(ConstantUtils.ROLE_ADMIN)) {
					isAdmin = true;
					break;
				}
			}

			if (!isAdmin) {
				throw new UnauthenticationException();
			}
			Dossier dossier = DossierUtils.getDossier(id, groupId);
			Indexer<Dossier> indexer =
				IndexerRegistryUtil.nullSafeGetIndexer(Dossier.class);
			indexer.reindex(dossier);

			DossierDetailModel result =
				DossierUtils.mappingForGetDetail(dossier, user.getUserId());

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response fixDueDateDossier(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, String id,
		String strReceiveDate) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		BackendAuth auth = new BackendAuthImpl();
		long VALUE_CONVERT_HOUR_TIMESTAMP = 1000 * 60 * 60;

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			List<Role> userRoles = user.getRoles();
			boolean isAdmin = false;
			for (Role r : userRoles) {
				if (r.getName().startsWith(ConstantUtils.ROLE_ADMIN)) {
					isAdmin = true;
					break;
				}
			}

			if (!isAdmin) {
				throw new UnauthenticationException();
			}

			long dossierId = GetterUtil.getLong(id);
			Dossier dossier = null;
			if (dossierId > 0) {
				dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
			}
			else {
				dossier = DossierLocalServiceUtil.getByDossierNo(groupId, id);
			}
			if (dossier != null) {
				Date receiveDate = dossier.getReceiveDate();
				Date dueDate = null;
				if (receiveDate != null) {
					String processNo = StringPool.BLANK;
					ServiceProcess process = null;
					// Process DueDate of dossier
					if (Validator.isNotNull(processNo)) {
						process = ServiceProcessLocalServiceUtil.getByG_PNO(
							groupId, processNo);
						if (process != null) {
							dueDate = HolidayUtils.getDueDate(
								receiveDate, process.getDurationCount(),
								process.getDurationUnit(), groupId);
							if (dueDate != null) {
								dossier.setDueDate(dueDate);
								//
								DossierLocalServiceUtil.updateDossier(dossier);
							}
						}
						// Process dueDate of dossierAction
						long dossierActionId = dossier.getDossierActionId();
						if (dossierActionId > 0) {
							DossierAction dAction =
								DossierActionLocalServiceUtil.fetchDossierAction(
									dossierActionId);
							if (dAction != null && process != null) {
								String stepCode = dAction.getStepCode();
								if (Validator.isNotNull(stepCode)) {
									ProcessStep step =
										ProcessStepLocalServiceUtil.fetchBySC_GID(
											stepCode, groupId,
											process.getServiceProcessId());
									if (step != null) {
										double durationCountStep =
											step.getDurationCount();
										int durationUnit =
											process.getDurationUnit();
										long durationHour = 0;
										boolean checkDueDate = true;
										if (durationCountStep > 0) {
											if (durationUnit == 0) {
												durationHour =
													(long) durationCountStep *
														8;
											}
											else {
												durationHour =
													(long) durationCountStep;
											}

											long createDateTimeStamp =
												dAction.getCreateDate().getTime();
											long dueDateActionTimeStamp =
												dAction.getDueDate() != null
													? dAction.getDueDate().getTime()
													: 0;
											if (dueDateActionTimeStamp > 0) {
												checkDueDate =
													(dueDateActionTimeStamp -
														createDateTimeStamp -
														durationHour *
															VALUE_CONVERT_HOUR_TIMESTAMP) < 0
																? true : false;
											}
										}
										else {
											checkDueDate = false;
										}
										if (checkDueDate) {
											Date dueDateAction =
												HolidayUtils.getDueDate(
													dAction.getCreateDate(),
													step.getDurationCount(),
													process.getDurationUnit(),
													groupId);
											if (dueDateAction != null) {
												dAction.setDueDate(
													dueDateAction);
												//
												DossierActionLocalServiceUtil.updateDossierAction(
													dAction);
											}
										}
									}
								}
							}
						}
					}
					else {
						long dossierActionId = dossier.getDossierActionId();
						if (dossierActionId > 0) {
							DossierAction dAction =
								DossierActionLocalServiceUtil.fetchDossierAction(
									dossierActionId);
							if (dAction != null) {
								process =
									ServiceProcessLocalServiceUtil.fetchServiceProcess(
										dAction.getServiceProcessId());
								if (process != null) {
									dueDate = HolidayUtils.getDueDate(
										receiveDate, process.getDurationCount(),
										process.getDurationUnit(), groupId);
									if (dueDate != null) {
										dossier.setDueDate(dueDate);
										//
										DossierLocalServiceUtil.updateDossier(
											dossier);
									}
								}
								// Update DueDate Action
								String stepCode = dAction.getStepCode();
								if (Validator.isNotNull(stepCode) &&
									process != null) {
									ProcessStep step =
										ProcessStepLocalServiceUtil.fetchBySC_GID(
											stepCode, groupId,
											process.getServiceProcessId());
									if (step != null) {
										double durationCountStep =
											step.getDurationCount();
										int durationUnit =
											process.getDurationUnit();
										long durationHour = 0;
										boolean checkDueDate = true;
										if (durationCountStep > 0) {
											if (durationUnit == 0) {
												durationHour =
													(long) durationCountStep *
														8;
											}
											else {
												durationHour =
													(long) durationCountStep;
											}

											long createDateTimeStamp =
												dAction.getCreateDate().getTime();
											long dueDateActionTimeStamp =
												dAction.getDueDate() != null
													? dAction.getDueDate().getTime()
													: 0;
											if (dueDateActionTimeStamp > 0) {
												checkDueDate =
													(dueDateActionTimeStamp -
														createDateTimeStamp -
														durationHour *
															VALUE_CONVERT_HOUR_TIMESTAMP) < 0
																? true : false;
											}
										}
										else {
											checkDueDate = false;
										}
										if (checkDueDate) {
											Date dueDateAction =
												HolidayUtils.getDueDate(
													dAction.getCreateDate(),
													step.getDurationCount(),
													process.getDurationUnit(),
													groupId);
											if (dueDateAction != null) {
												dAction.setDueDate(
													dueDateAction);
												//
												DossierActionLocalServiceUtil.updateDossierAction(
													dAction);
											}
										}
									}
								}
							}
						}
					}
				}
				else {
					receiveDate = APIDateTimeUtils.convertStringToDate(
						strReceiveDate, APIDateTimeUtils._NORMAL_PARTTERN);
					if (receiveDate != null) {
						String processNo = StringPool.BLANK;
						ServiceProcess process = null;
						// Process DueDate of dossier
						if (Validator.isNotNull(processNo)) {
							process = ServiceProcessLocalServiceUtil.getByG_PNO(
								groupId, processNo);
							if (process != null) {
								dueDate = HolidayUtils.getDueDate(
									receiveDate, process.getDurationCount(),
									process.getDurationUnit(), groupId);
								if (dueDate != null) {
									dossier.setDueDate(dueDate);
									//
									DossierLocalServiceUtil.updateDossier(
										dossier);
								}
							}
							// Process dueDate of dossierAction
							long dossierActionId = dossier.getDossierActionId();
							if (dossierActionId > 0) {
								DossierAction dAction =
									DossierActionLocalServiceUtil.fetchDossierAction(
										dossierActionId);
								if (dAction != null) {
									String stepCode = dAction.getStepCode();
									if (Validator.isNotNull(stepCode) &&
										process != null) {
										ProcessStep step =
											ProcessStepLocalServiceUtil.fetchBySC_GID(
												stepCode, groupId,
												process.getServiceProcessId());
										if (step != null) {
											double durationCountStep =
												step.getDurationCount();
											int durationUnit =
												process.getDurationUnit();
											long durationHour = 0;
											boolean checkDueDate = true;
											if (durationCountStep > 0) {
												if (durationUnit == 0) {
													durationHour =
														(long) durationCountStep *
															8;
												}
												else {
													durationHour =
														(long) durationCountStep;
												}

												long createDateTimeStamp =
													dAction.getCreateDate().getTime();
												long dueDateActionTimeStamp =
													dAction.getDueDate() != null
														? dAction.getDueDate().getTime()
														: 0;
												if (dueDateActionTimeStamp > 0) {
													checkDueDate =
														(dueDateActionTimeStamp -
															createDateTimeStamp -
															durationHour *
																VALUE_CONVERT_HOUR_TIMESTAMP) < 0
																	? true
																	: false;
												}
											}
											else {
												checkDueDate = false;
											}
											if (checkDueDate) {
												Date dueDateAction =
													HolidayUtils.getDueDate(
														dAction.getCreateDate(),
														step.getDurationCount(),
														process.getDurationUnit(),
														groupId);
												if (dueDateAction != null) {
													dAction.setDueDate(
														dueDateAction);
													//
													DossierActionLocalServiceUtil.updateDossierAction(
														dAction);
												}
											}
										}
									}
								}
							}
						}
						else {
							long dossierActionId = dossier.getDossierActionId();
							if (dossierActionId > 0) {
								DossierAction dAction =
									DossierActionLocalServiceUtil.fetchDossierAction(
										dossierActionId);
								if (dAction != null) {
									process =
										ServiceProcessLocalServiceUtil.fetchServiceProcess(
											dAction.getServiceProcessId());
									if (process != null) {
										dueDate = HolidayUtils.getDueDate(
											receiveDate,
											process.getDurationCount(),
											process.getDurationUnit(), groupId);
										if (dueDate != null) {
											dossier.setDueDate(dueDate);
											//
											DossierLocalServiceUtil.updateDossier(
												dossier);
										}
									}
									// Update DueDate Action
									String stepCode = dAction.getStepCode();
									if (Validator.isNotNull(stepCode)) {
										ProcessStep step =
											ProcessStepLocalServiceUtil.fetchBySC_GID(
												stepCode, groupId,
												process.getServiceProcessId());
										if (step != null) {
											double durationCountStep =
												step.getDurationCount();
											int durationUnit =
												process.getDurationUnit();
											long durationHour = 0;
											boolean checkDueDate = true;
											if (durationCountStep > 0) {
												if (durationUnit == 0) {
													durationHour =
														(long) durationCountStep *
															8;
												}
												else {
													durationHour =
														(long) durationCountStep;
												}

												long createDateTimeStamp =
													dAction.getCreateDate().getTime();
												long dueDateActionTimeStamp =
													dAction.getDueDate() != null
														? dAction.getDueDate().getTime()
														: 0;
												if (dueDateActionTimeStamp > 0) {
													checkDueDate =
														(dueDateActionTimeStamp -
															createDateTimeStamp -
															durationHour *
																VALUE_CONVERT_HOUR_TIMESTAMP) < 0
																	? true
																	: false;
												}
											}
											else {
												checkDueDate = false;
											}
											if (checkDueDate) {
												Date dueDateAction =
													HolidayUtils.getDueDate(
														dAction.getCreateDate(),
														step.getDurationCount(),
														process.getDurationUnit(),
														groupId);
												if (dueDateAction != null) {
													dAction.setDueDate(
														dueDateAction);
													//
													DossierActionLocalServiceUtil.updateDossierAction(
														dAction);
												}
											}
										}
									}
								}
							}
						}
					}
				}
				//
				DossierDetailModel result =
					DossierUtils.mappingForGetDetail(dossier, user.getUserId());
				return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();
			}
			else {
				return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(
					MessageUtil.getMessage(ConstantUtils.DOSSIER_MESSAGE_CANNOTUPDATEDUEDATE)).build();
			}

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response fixReAssignedDossier(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, String id,
		String fromEmailUser, String toEmailUser) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		BackendAuth auth = new BackendAuthImpl();
		org.opencps.dossiermgt.action.DossierActionUser actions =
			new DossierActionUserImpl();

		try {

			long dossierId = GetterUtil.getLong(id);
			Dossier dossier = null;
			if (dossierId > 0) {
				dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
			}
			else {
				dossier = DossierLocalServiceUtil.getByDossierNo(groupId, id);
			}

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			List<Role> userRoles = user.getRoles();
			boolean isAdmin = false;
			for (Role r : userRoles) {
				if (r.getName().startsWith(ConstantUtils.ROLE_ADMIN)) {
					isAdmin = true;
					break;
				}
			}

			if (!isAdmin) {
				throw new UnauthenticationException();
			}

			if (dossier != null && dossier.getDossierActionId() != 0) {
				User fromUser = UserLocalServiceUtil.fetchUserByEmailAddress(
					dossier.getCompanyId(), fromEmailUser);
				if (fromUser != null) {
					List<DossierActionUser> fromActionUserList =
						DossierActionUserLocalServiceUtil.getByDOSSIER_UID(
							dossier.getDossierId(), fromUser.getUserId());
					if (fromActionUserList != null &&
						fromActionUserList.size() > 0) {
						DossierActionUser fromActUser =
							fromActionUserList.get(0);
						if (fromActUser != null) {
							User toUser =
								UserLocalServiceUtil.fetchUserByEmailAddress(
									dossier.getCompanyId(), toEmailUser);
							if (toUser != null) {
								// CreateDossierActionUser
								DossierActionUser toActUser =
									new org.opencps.dossiermgt.model.impl.DossierActionUserImpl();
								toActUser.setDossierActionId(
									fromActUser.getDossierActionId());
								toActUser.setUserId(toUser.getUserId());
								toActUser.setModerator(
									fromActUser.getModerator());
								toActUser.setAssigned(
									fromActUser.getAssigned());
								toActUser.setVisited(fromActUser.getVisited());
								toActUser.setDossierId(dossier.getDossierId());
								toActUser.setStepCode(
									fromActUser.getStepCode());
								//
								actions.updateDossierActionUser(toActUser);
								// Create DossierUser
								DossierUser fromDossierUser =
									DossierUserLocalServiceUtil.findByDID_UD(
										dossierId, fromUser.getUserId());
								if (fromDossierUser != null) {
									DossierUser dUser =
										new org.opencps.dossiermgt.model.impl.DossierUserImpl();
									dUser.setDossierId(dossier.getDossierId());
									dUser.setUserId(toUser.getUserId());
									dUser.setModerator(
										fromDossierUser.getModerator());
									dUser.setVisited(
										fromDossierUser.getVisited());
									//
									DossierUserLocalServiceUtil.updateDossierUser(
										dUser);
								}
							}
						}
					}
				}
				// Reindex dossier
				Indexer<Dossier> indexer =
					IndexerRegistryUtil.nullSafeGetIndexer(Dossier.class);
				indexer.reindex(dossier);

				return Response.status(HttpURLConnection.HTTP_OK).entity(
					JSONFactoryUtil.looseSerialize(
						DossierManagementConstants.SET_PERMISSION_SUCCESS)).build();
			}
			else {
				return Response.status(HttpURLConnection.HTTP_OK).entity(StringPool.BLANK).build();
			}
		}
		catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response fixSyncContactDossier(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, String id) {

		_log.info("START=====");
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		BackendAuth auth = new BackendAuthImpl();

		try {

			long dossierId = GetterUtil.getLong(id);
			Dossier dossier = null;
			if (dossierId > 0) {
				dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
			}
			else {
				dossier = DossierLocalServiceUtil.getByDossierNo(groupId, id);
			}

			_log.info("START=====: " + JSONFactoryUtil.looseSerialize(dossier));
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			List<Role> userRoles = user.getRoles();
			boolean isAdmin = false;
			for (Role r : userRoles) {
				if (r.getName().startsWith(ConstantUtils.ROLE_ADMIN)) {
					isAdmin = true;
					break;
				}
			}

			if (!isAdmin) {
				throw new UnauthenticationException();
			}

			_log.info("START=====");
			if (dossier != null && dossier.getDossierActionId() != 0) {
				List<Dossier> dossierList =
					DossierLocalServiceUtil.getByGroupAndOriginDossierNo(
						groupId, dossier.getDossierNo());
				if (dossierList != null && dossierList.size() > 0) {
					int countByGID_NO = dossierList.size();
					int countORI_NO =
						DossierLocalServiceUtil.countByOriginDossierNo(
							dossier.getDossierNo());
					if (countORI_NO == countByGID_NO) {
						// Delete dossier origin
						for (Dossier originDossier : dossierList) {
							// Delete DossierSync
							DossierSyncLocalServiceUtil.removeByDossierId(
								groupId, originDossier.getDossierId());
							// Delete dossier
							DossierLocalServiceUtil.deleteDossier(
								originDossier);

						}
					}
					else {
						// TODO: Don't process
					}
				}
				else {

				}
				_log.info("START=====");
				// Undo hồ sơ liên thông
				DossierAction dossierAction =
					DossierActionLocalServiceUtil.fetchDossierAction(
						dossier.getDossierActionId());
				if (dossierAction != null && dossierAction.isRollbackable()) {
					_log.info("START=====");
					DossierActionLocalServiceUtil.updateState(
						dossierAction.getDossierActionId(),
						DossierActionTerm.STATE_ROLLBACK);

					DossierAction previousAction =
						DossierActionLocalServiceUtil.fetchDossierAction(
							dossierAction.getPreviousActionId());
					_log.info("START=====");
					if (previousAction != null) {
						DossierActionLocalServiceUtil.updateState(
							previousAction.getDossierActionId(),
							DossierActionTerm.STATE_WAITING_PROCESSING);
						try {
							DossierActionLocalServiceUtil.updateNextActionId(
								previousAction.getDossierActionId(), 0);
							DossierLocalServiceUtil.rollback(
								dossier, previousAction);
						}
						catch (PortalException e) {
							_log.error(e);
							return BusinessExceptionImpl.processException(e);
						}
					}
					_log.info("START=====");
					DossierSync ds = DossierSyncLocalServiceUtil.getByDID_DAD(
						groupId, dossier.getDossierId(),
						dossierAction.getDossierActionId());
					if (ds != null &&
						((ds.getSyncType() == DossierSyncTerm.SYNCTYPE_INFORM &&
							dossier.getOriginality() == DossierTerm.ORIGINALITY_LIENTHONG) ||
							(ds.getSyncType() == DossierSyncTerm.SYNCTYPE_REQUEST &&
								dossier.getOriginality() == DossierTerm.ORIGINALITY_DVCTT))) {
						_log.info("START=====");
						DossierMgtUtils.processSyncRollbackDossier(dossier);
					}
					_log.info("START=====");
				}
				else if (dossierAction != null && isAdmin) {
					_log.info("START=====");
					DossierActionLocalServiceUtil.updateState(
						dossierAction.getDossierActionId(),
						DossierActionTerm.STATE_ROLLBACK);
					_log.info("START=====");
					DossierAction previousAction =
						DossierActionLocalServiceUtil.fetchDossierAction(
							dossierAction.getPreviousActionId());
					_log.info("START=====");
					if (previousAction != null) {
						DossierActionLocalServiceUtil.updateState(
							previousAction.getDossierActionId(),
							DossierActionTerm.STATE_WAITING_PROCESSING);
						try {
							DossierActionLocalServiceUtil.updateNextActionId(
								previousAction.getDossierActionId(), 0);
							DossierLocalServiceUtil.rollback(
								dossier, previousAction);
						}
						catch (PortalException e) {
							_log.error(e);
							return BusinessExceptionImpl.processException(e);
						}
					}

					DossierSync ds = DossierSyncLocalServiceUtil.getByDID_DAD(
						groupId, dossier.getDossierId(),
						dossierAction.getDossierActionId());
					if (ds != null &&
						((ds.getSyncType() == DossierSyncTerm.SYNCTYPE_INFORM &&
							dossier.getOriginality() == DossierTerm.ORIGINALITY_LIENTHONG) ||
							(ds.getSyncType() == DossierSyncTerm.SYNCTYPE_REQUEST &&
								dossier.getOriginality() == DossierTerm.ORIGINALITY_DVCTT))) {
						DossierMgtUtils.processSyncRollbackDossier(dossier);
					}
				}

				_log.info("START=====");
				// Process liên thông hồ sơ
				String dossierStatus = dossier.getDossierStatus();
				String dossierSubStatus = dossier.getDossierSubStatus();
				long serviceProcessId = dossierAction.getServiceProcessId();

				ProcessStep step =
					ProcessStepLocalServiceUtil.getByProcessAndStatus(
						groupId, serviceProcessId, dossierStatus,
						dossierSubStatus);
				_log.info("START=====");
				if (step != null) {
					String stepCode = step.getStepCode();
					_log.info("START=====: " + stepCode);
					if (Validator.isNotNull(stepCode)) {
						List<ProcessAction> actionList =
							ProcessActionLocalServiceUtil.getByServiceStepCode(
								groupId, serviceProcessId, stepCode);
						_log.info(
							"actionList=====: " +
								JSONFactoryUtil.looseSerialize(actionList));
						if (actionList != null && actionList.size() > 0) {
							String actionCode = StringPool.BLANK;
							for (ProcessAction processAction : actionList) {
								if (Validator.isNotNull(
									processAction.getCreateDossiers())) {
									actionCode = processAction.getActionCode();
									break;
								}
							}
							_log.info("actionCode=====: " + actionCode);
							if (Validator.isNotNull(actionCode)) {
								ActionConfig actConfig =
									ActionConfigLocalServiceUtil.getByCode(
										groupId, actionCode);
								_log.info("Action config: " + actConfig);
								// String serviceCode =
								// dossier.getServiceCode();
								// String govAgencyCode =
								// dossier.getGovAgencyCode();
								// String dossierTempNo =
								// dossier.getDossierTemplateNo();
								if (actConfig != null) {
									DossierActions actions =
										new DossierActionsImpl();
									ErrorMsgModel errorModel =
										new ErrorMsgModel();
									//
									boolean insideProcess =
										actConfig.getInsideProcess();
									_log.info(
										"insideProcess: " + insideProcess);
									// ProcessOption option =
									// DossierUtils.getProcessOption(serviceCode,
									// govAgencyCode,
									// dossierTempNo, groupId);
									if (insideProcess) {
										if (dossierAction != null) {
											_log.info(
												"dossierAction: " +
													dossierAction);
											DossierTemplate dossierTemplate =
												DossierTemplateLocalServiceUtil.getByTemplateNo(
													groupId,
													dossier.getDossierTemplateNo());

											ProcessOption oldOption =
												ProcessOptionLocalServiceUtil.fetchBySP_DT(
													serviceProcessId,
													dossierTemplate.getDossierTemplateId());

											ProcessAction proAction =
												DossierUtils.getProcessAction(
													user,
													groupId, dossier,
													actionCode,
													serviceProcessId);
											if (proAction != null) {
												_log.info(
													"DO ACTION: " +
														proAction.getActionCode());
												actions.doAction(
													groupId, user.getUserId(),
													dossier, oldOption,
													proAction, actionCode,
													StringPool.BLANK,
													StringPool.BLANK,
													StringPool.BLANK,
													StringPool.BLANK,
													StringPool.BLANK,
													actConfig.getSyncType(),
													serviceContext, errorModel);

												return Response.status(
													HttpURLConnection.HTTP_OK).entity(null).build();
											}
										}
									}
								}
							}
						}
					}
				}
			}
			return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(null).build();
		}
		catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getUrlSiteInfo(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		String referenceUid) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		JSONObject jsonData = JSONFactoryUtil.createJSONObject();
		try {
			Dossier dossier =
				DossierLocalServiceUtil.getByRef(groupId, referenceUid);
			if (dossier != null) {
				jsonData.put(DossierTerm.DOSSIER_ID, dossier.getDossierId());
				// Get info group
				Group group = GroupLocalServiceUtil.getGroup(groupId);
				jsonData.put(
					ConstantUtils.API_JSON_URL, PortalUtil.getPathFriendlyURLPublic() +
						group.getFriendlyURL());
			}

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
		return Response.status(HttpServletResponse.SC_OK).entity(
			JSONFactoryUtil.looseSerialize(jsonData)).build();
	}

	@Override
	public Response getValidateProcess(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, String id,
		String actionCode) {

		BackendAuth auth = new BackendAuthImpl();

		JSONObject results = JSONFactoryUtil.createJSONObject();
		results.put(ConstantUtils.API_JSON_VALUE, false);
		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			long groupId =
				GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			long userId = user.getUserId();

			Dossier dossier = DossierUtils.getDossier(id, groupId);
			if (dossier != null) {
				long serviceProcessId = 0;
				String stepCode = StringPool.BLANK;
				boolean pending = false;
				long dossierActionId = dossier.getDossierActionId();
				// _log.info("dossierActionId: "+dossierActionId);
				DossierAction dossierAction = null;
				if (dossierActionId > 0) {
					dossierAction =
						DossierActionLocalServiceUtil.fetchDossierAction(
							dossierActionId);
				}

				if (dossierAction != null) {
					serviceProcessId = dossierAction.getServiceProcessId();
					stepCode = dossierAction.getStepCode();
					pending = dossierAction.getPending();
				}

				if (Validator.isNotNull(stepCode) && serviceProcessId > 0) {
					DossierActionUser dActionUser =
						DossierActionUserLocalServiceUtil.getByDossierAndUser(
							dossierActionId, userId);
					// _log.info("User id: " + userId);
					int enable = 2;
					if (dossier.getOriginality() == DossierTerm.ORIGINALITY_DVCTT) {
						if (dossier.getUserId() == userId && !pending) {
							enable = 1;
						}
					}
					if (dActionUser != null) {
						int assign = dActionUser.getAssigned();
						if (assign == 1 && !pending)
							enable = 1;
					}
					_log.info("enable: " + enable);
					List<ProcessAction> processActionList =
						ProcessActionLocalServiceUtil.getProcessActionByG_SPID_PRESC(
							groupId, serviceProcessId, stepCode);
					if (processActionList != null &&
						processActionList.size() > 0) {
						StringBuilder sb = new StringBuilder();
						for (ProcessAction processAction : processActionList) {
							if (enable == 1 && (processCheckEnable(
								user,
								processAction.getPreCondition(),
								processAction.getAutoEvent(), dossier,
								actionCode, groupId))) {
								sb.append(processAction.getActionCode());
								sb.append(StringPool.SPACE);
							}
						}
						_log.info("SB: " + sb.toString());
						if (sb != null && sb.toString().contains(actionCode)) {
							results.put("value", true);
						}
					}
				}
			}

			return Response.status(HttpURLConnection.HTTP_OK).entity(results.toJSONString()).build();
		}
		catch (Exception e) {
			_log.info(e);
			return Response.status(HttpURLConnection.HTTP_OK).entity(results.toJSONString()).build();
		}
	}

	// LamTV_Process check permission action
	public static final String AUTO_EVENT_SUBMIT = "submit";
	public static final String AUTO_EVENT_TIMMER = "timer";
	public static final String AUTO_EVENT_LISTENER = "listener";
	public static final String AUTO_EVENT_SPECIAL = "special";

	private boolean processCheckEnable(
		User user, String preCondition, String autoEvent, Dossier dossier,
		String actionCode, long groupId) {

		if (AUTO_EVENT_SUBMIT.equals(autoEvent) ||
			AUTO_EVENT_TIMMER.equals(autoEvent) ||
			AUTO_EVENT_LISTENER.equals(autoEvent) ||
			AUTO_EVENT_SPECIAL.equals(autoEvent)) {
			return false;
		}
		String[] preConditionArr = StringUtil.split(preCondition);
		if (preConditionArr != null && preConditionArr.length > 0) {
			return DossierMgtUtils.checkPreCondition(preConditionArr, dossier, user);
		}

		return true;
	}

	@Override
	public Response removeGroupDossierId(
			HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext,
			long groupDossierId, long dossierId) {
		try {
			String groupDossierIdNew = "";
			Dossier dossier = DossierLocalServiceUtil.fetchDossier(
					GetterUtil.getLong(dossierId));
			if(Validator.isNotNull(dossier)){
				String[] groupDossierIdArr = dossier.getGroupDossierId().split(StringPool.COMMA);
				for (String groupDossierIdStr : groupDossierIdArr) {
					if(!groupDossierIdStr.equals(String.valueOf(groupDossierId))){
						groupDossierIdNew += StringPool.COMMA + groupDossierIdStr;
					}
				}
				groupDossierIdNew = groupDossierIdNew.substring(1);
				DossierLocalServiceUtil.updateGroupDossier(dossier, groupDossierIdNew);
				return Response.status(HttpURLConnection.HTTP_OK).entity("OK").build();
			}
		}catch (Exception e){
			e.printStackTrace();
			_log.error(e.getMessage());
			return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(ConstantUtils.API_JSON_EMPTY_ERROR).build();
		}
		return null;
	}

	@Override
	public Response updateDossierInGroup(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		long groupDossierId, String dossierIds) {

		// long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		try {
			if (dossierIds.contains(StringPool.COMMA)) {
				String[] dossierArr = dossierIds.split(StringPool.COMMA);
				DossierResultsModel results = new DossierResultsModel();

				results.setTotal(dossierArr.length);

				for (String dossierStr : dossierArr) {
					Dossier dossier = DossierLocalServiceUtil.fetchDossier(
						GetterUtil.getLong(dossierStr));
					if (dossier != null) {
						String strGroupDossierId = dossier.getGroupDossierId();
						if (Validator.isNotNull(strGroupDossierId)
								&& !strGroupDossierId.contains(String.valueOf(groupDossierId))) {
							strGroupDossierId += StringPool.COMMA + groupDossierId;
							// dossier.setGroupDossierId(GetterUtil.getString(strGroupDossierId));
							DossierLocalServiceUtil.updateGroupDossier(dossier, strGroupDossierId);
						} else {
							//dossier.setGroupDossierId(GetterUtil.getString(groupDossierId));
							DossierLocalServiceUtil.updateGroupDossier(dossier, String.valueOf(groupDossierId));
						}
						DossierLocalServiceUtil.updateDossier(dossier);

						DossierDataModel dataModel = new DossierDataModel();
						dataModel.setDossierNo(dossier.getDossierNo());
						results.getData().add(dataModel);
					}
				}
				//
				return Response.status(HttpURLConnection.HTTP_OK).entity(results).build();
			}
			else {
				_log.info("dossierIds: "+dossierIds);
				Dossier dossier = DossierLocalServiceUtil.fetchDossier(
					GetterUtil.getLong(dossierIds));
				_log.info("dossier: "+dossier);
				if (dossier != null) {
					String strGroupDossierId = dossier.getGroupDossierId();
					if (Validator.isNotNull(strGroupDossierId)) {
						if(!strGroupDossierId.contains(String.valueOf(groupDossierId))){
							strGroupDossierId += StringPool.COMMA + groupDossierId;
							dossier.setGroupDossierId(strGroupDossierId);
						}else{
							dossier.setGroupDossierId(strGroupDossierId);
						}
					} else {
						_log.info("groupDossierId: "+ String.valueOf(groupDossierId));
						dossier.setGroupDossierId(String.valueOf(groupDossierId));
						
					}
					dossier = DossierLocalServiceUtil.updateDossier(dossier);
					_log.info("dossier group: "+dossier.getGroupDossierId());
				}
				DossierDetailModel result =
					DossierUtils.mappingForGetDetail(dossier, user.getUserId());
				//
				return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();
			}
		}
		catch (Exception e) {
			_log.info(e);
			return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(ConstantUtils.API_JSON_EMPTY_ERROR).build();
		}
	}

	@Override
	public Response addMultipleDossier(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		DossierMultipleInputModel input) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		try {
			_log.info("dossiers: " + input.getDossiers());
			if (Validator.isNotNull(input.getDossiers())) {
				JSONObject dossiers =
					JSONFactoryUtil.createJSONObject(input.getDossiers());
				String applicantName = Validator.isNotNull(
					dossiers.getString(DossierTerm.APPLICANT_NAME))
						? dossiers.getString(DossierTerm.APPLICANT_NAME)
						: StringPool.BLANK;
				String delegateName = Validator.isNotNull(
					dossiers.getString(DossierTerm.DELEGATE_NAME))
						? dossiers.getString(DossierTerm.DELEGATE_NAME)
						: StringPool.BLANK;

				_log.info("applicantName: " + applicantName);
				_log.info("delegateName: " + delegateName);
				String[] statusArr = {
					StringPool.BLANK
				};
				List<Dossier> dossierList =
					DossierLocalServiceUtil.getByGID_GC_SC_DTN_DS_APP_ORI(
						groupId, input.getGovAgencyCode(),
						input.getServiceCode(), input.getDossierTemplateNo(),
						statusArr,
						dossiers.getString(DossierTerm.APPLICANT_ID_TYPE),
						Validator.isNotNull(input.getOriginality())
							? Integer.valueOf(input.getOriginality()) : 0);
				if (dossierList != null && dossierList.size() > 0) {
					for (Dossier dossierImport : dossierList) {
						if (applicantName.equalsIgnoreCase(
							dossierImport.getApplicantName()) &&
							delegateName.equalsIgnoreCase(
								dossierImport.getDelegateName())) {
							return Response.status(
								HttpStatus.SC_CONFLICT).entity(
									ConstantUtils.API_JSON_EMPTY_CONFLICT).build();
						}
					}
				}
			}

			Dossier dossier =
				CPSDossierBusinessLocalServiceUtil.addMultipleDossier(
					groupId, company, user, serviceContext,
					DossierUtils.convertFormModelToMultipleInputModel(input));
			DossierDetailModel result =
				DossierUtils.mappingForGetDetail(dossier, user.getUserId());
			return Response.status(HttpStatus.SC_OK).entity(result).build();
		}
		catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response addFullDossier(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		DossierMultipleInputModel input) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		try {
			_log.info("dossiers: " + input.getDossiers());
			// if (Validator.isNotNull(input.getDossiers())) {
			// JSONObject dossiers =
			// JSONFactoryUtil.createJSONObject(input.getDossiers());
			// String applicantName =
			// Validator.isNotNull(dossiers.getString(DossierTerm.APPLICANT_NAME))
			// ? dossiers.getString(DossierTerm.APPLICANT_NAME)
			// : StringPool.BLANK;
			// String delegateName =
			// Validator.isNotNull(dossiers.getString(DossierTerm.DELEGATE_NAME))
			// ? dossiers.getString(DossierTerm.DELEGATE_NAME)
			// : StringPool.BLANK;
			//
			// _log.info("applicantName: "+applicantName);
			// _log.info("delegateName: "+delegateName);
			// String[] statusArr = {StringPool.BLANK,
			// DossierTerm.DOSSIER_STATUS_NEW};
			// List<Dossier> dossierList =
			// DossierLocalServiceUtil.getByGID_GC_SC_DTN_DS_APP_ORI(groupId,
			// input.getGovAgencyCode(), input.getServiceCode(),
			// input.getDossierTemplateNo(), statusArr,
			// dossiers.getString(DossierTerm.APPLICANT_ID_TYPE),
			// Validator.isNotNull(input.getOriginality()) ?
			// Integer.valueOf(input.getOriginality()) : 0);
			// if (dossierList != null && dossierList.size() > 0) {
			// for (Dossier dossierImport : dossierList) {
			// if
			// (applicantName.equalsIgnoreCase(dossierImport.getApplicantName())
			// &&
			// delegateName.equalsIgnoreCase(dossierImport.getDelegateName())) {
			// return
			// Response.status(HttpStatus.SC_CONFLICT).entity("{CONFLICT}").build();
			// }
			// }
			// }
			// }

			Dossier dossier = CPSDossierBusinessLocalServiceUtil.addFullDossier(
				groupId, company, user, serviceContext,
				DossierUtils.convertFormModelToMultipleInputModel(input));
			DossierDetailModel result =
				DossierUtils.mappingForGetDetail(dossier, user.getUserId());
			return Response.status(HttpStatus.SC_OK).entity(result).build();
		}
		catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getDueDateByProcess(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		DossierSearchModel query) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		try {
			JSONObject jsonData = JSONFactoryUtil.createJSONObject();
			String strReceiveDate = query.getFromReceiveDate();
			Date receiveDate = null;
			if (Validator.isNotNull(strReceiveDate)) {
				SimpleDateFormat sdf =
					new SimpleDateFormat(APIDateTimeUtils._NORMAL_PARTTERN);
				receiveDate = sdf.parse(strReceiveDate);
			}
			else {
				receiveDate = new Date();
			}
			//
			jsonData.put(
				DossierTerm.RECEIVE_DATE, receiveDate != null
					? receiveDate.getTime() : (new Date()).getTime());
			Date dueDate = null;
			ProcessOption option = getProcessOption(
				query.getService(), query.getAgency(), query.getTemplate(),
				groupId);
			if (option != null) {
				ServiceProcess process =
					ServiceProcessLocalServiceUtil.fetchServiceProcess(
						option.getServiceProcessId());
				if (process != null) {
					Double durationCount = process.getDurationCount();
					if (Validator.isNotNull(String.valueOf(durationCount)) &&
						durationCount > 0d) {
						dueDate = HolidayUtils.getDueDate(
							receiveDate, process.getDurationCount(),
							process.getDurationUnit(), groupId);
						jsonData.put(
							DossierTerm.DUE_DATE,
							dueDate != null ? dueDate.getTime() : 0);
					}
					else {
						jsonData.put(DossierTerm.DUE_DATE, 0);
					}
				}
				else {
					jsonData.put(DossierTerm.DUE_DATE, 0);
				}
			}
			else {
				jsonData.put(DossierTerm.DUE_DATE, 0);
			}
			return Response.status(HttpStatus.SC_OK).entity(
				JSONFactoryUtil.looseSerialize(jsonData)).build();
		}
		catch (Exception e) {
			_log.debug(e);
		}
		return Response.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).entity(
			ConstantUtils.API_JSON_EMPTY_NOCONTENT).build();
	}

	@Override
	public Response getOptionDossier(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, String id) {

		return Response.status(HttpStatus.SC_OK).header(HttpHeaders.ALLOW, StringPool.BLANK).build();
	}

	@Override
	public Response generateDossierNo(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		String serviceCode, String govAgencyCode, String templateNo) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		ServiceProcess serviceProcess = null;
		ProcessOption option;
		try {
			option = getProcessOption(
				serviceCode, govAgencyCode, templateNo, groupId);
			_log.debug("Process option: " + option);
			if (option != null) {
				long serviceProcessId = option.getServiceProcessId();
				serviceProcess =
					ServiceProcessLocalServiceUtil.fetchServiceProcess(
						serviceProcessId);

				String dossierRef =
					DossierNumberGenerator.generateDossierNumber(
						groupId, serviceProcess.getDossierNoPattern(),
						serviceCode, govAgencyCode, templateNo,
						serviceProcess.getProcessNo());
				_log.debug("Dossier no: " + dossierRef);
				JSONObject result = JSONFactoryUtil.createJSONObject();
				result.put(DossierTerm.DOSSIER_NO, dossierRef.trim());

				return Response.status(HttpURLConnection.HTTP_OK).entity(
					result.toJSONString()).build();
			}
			else {
				throw new Exception(ConstantUtils.API_JSON_MESSAGE_DONOTHAVESERVICE);
			}
		}
		catch (PortalException e) {
			return BusinessExceptionImpl.processException(e);
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getDelegacyUsers(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, String id, ServiceContext serviceContext) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		BackendAuth auth = new BackendAuthImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			JSONObject result = JSONFactoryUtil.createJSONObject();
			long dossierId = 0;
			dossierId = GetterUtil.getLong(id);
			Dossier dossier = null;
			dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
			if (dossier == null) {
				dossier = DossierLocalServiceUtil.getByRef(groupId, id);
			}
			if (dossier != null) {
				long lastActionId = dossier.getDossierActionId();
				DossierAction da =
					DossierActionLocalServiceUtil.fetchDossierAction(
						lastActionId);
				if (da != null) {
					DossierActionUser checkDau =
						DossierActionUserLocalServiceUtil.getByD_DID_UID_SC(
							dossier.getDossierId(), da.getPreviousActionId(),
							user.getUserId(), da.getFromStepCode());
					if (checkDau == null ||
						checkDau.getAssigned() != DossierActionUserTerm.ASSIGNED_TH) {
						return Response.status(
							HttpURLConnection.HTTP_OK).entity(
								result.toJSONString()).build();
					}

					ProcessAction pa =
						ProcessActionLocalServiceUtil.fetchByF_GID_SID_AC_PRE_POST(
							groupId, da.getServiceProcessId(),
							da.getActionCode(), da.getFromStepCode(),
							da.getStepCode());
					if (pa != null) {
						result.put(
							ProcessActionTerm.ALLOW_ASSIGN_USER,
							pa.getAllowAssignUser());

						ProcessStep processStep =
							ProcessStepLocalServiceUtil.fetchBySC_GID(
								da.getStepCode(), groupId,
								da.getServiceProcessId());
						List<User> lstUser = new ArrayList<>();
						List<Long> lstIds = new ArrayList<>();

						if (processStep != null) {
							List<DossierActionUser> assignedUsers =
								DossierActionUserLocalServiceUtil.getByDossierAndStepCode(
									dossierId, processStep.getStepCode());
							for (DossierActionUser dau : assignedUsers) {
								User u = UserLocalServiceUtil.fetchUser(
									dau.getUserId());
								if (u != null) {
									if (!u.isLockout() && u.isActive() &&
										!lstIds.contains(u.getUserId())) {
										lstUser.add(u);
										lstIds.add(u.getUserId());
									}
								}
							}

							if (processStep != null) {
								List<ProcessStepRole> processStepRoleList =
									ProcessStepRoleLocalServiceUtil.findByP_S_ID(
										processStep.getProcessStepId());
								if (processStepRoleList != null &&
									!processStepRoleList.isEmpty()) {
									List<ProcessStepRole> lstStepRoles =
										new ArrayList<>();
									for (ProcessStepRole psr : processStepRoleList) {
										if (Validator.isNotNull(
											psr.getCondition())) {
											String[] conditions =
												StringUtil.split(
													psr.getCondition());

											if (DossierMgtUtils.checkPreCondition(
												conditions, dossier, user)) {
												lstStepRoles.add(psr);
											}
										}
										else {
											lstStepRoles.add(psr);
										}
									}

									for (ProcessStepRole role : processStepRoleList) {
										List<User> lstUsers =
											UserLocalServiceUtil.getRoleUsers(
												role.getRoleId());
										for (User u : lstUsers) {
											if (!u.isLockout() &&
												u.isActive()) {
												HashMap<String, Object> assigned =
													new HashMap<>();
												assigned.put(
													ProcessStepRoleTerm.ASSIGNED,
													0);
												HashMap<String, Object> moderator =
													new HashMap<>();
												moderator.put(
													ProcessStepRoleTerm.MODERATOR,
													role.getModerator());
												u.setModelAttributes(moderator);
												u.setModelAttributes(assigned);

												if (!lstIds.contains(
													u.getUserId())) {
													lstUser.add(u);
													lstIds.add(u.getUserId());
												}
											}
										}
									}
								}
							}
						}

						JSONArray outputUserArr =
							JSONFactoryUtil.createJSONArray();

						if (lstUser != null && lstUser.size() > 0) {
							boolean moderator = false;
							int assigned = 0;
							for (User u : lstUser) {
								if (!u.isLockout() && u.isActive()) {
									JSONObject userObj =
										JSONFactoryUtil.createJSONObject();

									Map<String, Object> attr =
										u.getModelAttributes();
									long userId =
										GetterUtil.getLong(u.getUserId());
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
												attr.get(
													ProcessStepRoleTerm.ASSIGNED));
										}
									}
									userObj.put(Field.USER_ID, userId);
									Employee emp =
										EmployeeLocalServiceUtil.fetchByF_mappingUserId(
											groupId, userId);
									if (emp != null) {
										userObj.put(
											UserTerm.FULL_NAME,
											emp.getFullName() != null
												? emp.getFullName().toUpperCase()
												: StringPool.BLANK);
									}
									else {
										userObj.put(
											UserTerm.FULL_NAME,
											u.getFullName() != null
												? u.getFullName().toUpperCase()
												: StringPool.BLANK);
									}

									userObj.put(DossierManagementConstants.MODERATOR, moderator);
									userObj.put(DossierManagementConstants.ASSIGNED, assigned);

									outputUserArr.put(userObj);
								}
							}
						}

						result.put(DossierManagementConstants.TO_USERS, outputUserArr);
					}
				}
			}
			else {

			}
			return Response.status(HttpURLConnection.HTTP_OK).entity(
				result.toJSONString()).build();
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response fixExtendDateDossier(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext) {

		boolean isAdmin = false;
		List<Role> roles = RoleLocalServiceUtil.getUserRoles(user.getUserId());
		if (roles != null && roles.size() > 0) {
			for (Role role : roles) {
				if (ConstantUtils.ROLE_ADMIN.equals(role.getName())) {
					isAdmin = true;
					break;
				}
				if (ConstantUtils.ROLE_ADMIN_DATA.equals(role.getName())) {
					isAdmin = true;
					break;
				}
			}
		}
		if (isAdmin && OpenCPSConfigUtil.isAutoBetimes()) {
			List<Group> groups = GroupLocalServiceUtil.getCompanyGroups(
				company.getCompanyId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (Group group : groups) {
				if (group.getType() == 1 && group.isSite()) {
					List<Dossier> lstDossiers =
						DossierLocalServiceUtil.findDossierByGroup(
							group.getGroupId());
					for (Dossier dossier : lstDossiers) {
						if (dossier.getReleaseDate() != null &&
							dossier.getExtendDate() == null) {
							int valueCompareRelease =
								BetimeUtils.getValueCompareRelease(
									group.getGroupId(),
									dossier.getReleaseDate(),
									dossier.getDueDate());
							if (3 == valueCompareRelease) {
								dossier.setExtendDate(dossier.getReleaseDate());
								DossierLocalServiceUtil.updateDossier(dossier);
							}
						}
					}
				}
			}

			return Response.status(HttpURLConnection.HTTP_OK).entity(
				"").build();
		}
		else {
			return Response.status(HttpURLConnection.HTTP_OK).entity(
				"").build();
		}
	}

	@Override
	public Response importDossierFromOldDB(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		Attachment file, String actionCode, String pathBase, long dvcGroupId, long groupId,
		String govAgencyCode, String govAgencyName, String appUser, String appSecret) {

		try {

			System.out.println(
				"================POST===========================");
			JSONObject result = JSONFactoryUtil.createJSONObject();

			DataHandler dataHandle = file.getDataHandler();
			JSONArray dataFile =
				ConvertDossierFromV1Dot9Utils.readExcelFileWithHeaderConfig(
					dataHandle.getInputStream());
			for (int i = 0; i < dataFile.length(); i++) {

				JSONObject dossierJson = dataFile.getJSONObject(i);
				
				if (Validator.isNull(dossierJson.get(ConvertDossierFromV1Dot9Utils.TEMP_SERVICECODE))) {
					
					continue;
				}
				dossierJson.put(
					ConvertDossierFromV1Dot9Utils.TEMP_ONLINE_, true);
				dossierJson.put(DossierTerm.ONLINE, true);
				dossierJson.put(
					ConvertDossierFromV1Dot9Utils.TEMP_ORIGINALITY, 1);
				dossierJson.put(
					ConvertDossierFromV1Dot9Utils.TEMP_ORIGINAL, false);
				// them ho so vao dvc set groupId = dvcGroupId
				if (dvcGroupId > 0) {

					dossierJson.put(
						ConvertDossierFromV1Dot9Utils.TEMP_GROUPID, dvcGroupId);
				}
				JSONObject temp = ConvertDossierFromV1Dot9Utils.getDossierObject(company.getCompanyId(), groupId, user.getUserId(), user.getFullName(), govAgencyCode, govAgencyName);
				JSONObject importD = ConvertDossierFromV1Dot9Utils.mergeJSONObject(temp, dossierJson);
				JSONObject dossier =
					ConvertDossierFromV1Dot9Utils.setDossierObject(importD);
				if (dvcGroupId <= 0) {
					ConvertDossierFromV1Dot9Utils.insertUserDossier(
						dossier.getLong(
							ConvertDossierFromV1Dot9Utils.TEMP_GROUPID),
						dossier.getLong(
							ConvertDossierFromV1Dot9Utils.TEMP_DOSSIERID));
				}

				ConvertDossierFromV1Dot9Utils.callActionDoneDossier(
					pathBase,
					dossier.getLong(ConvertDossierFromV1Dot9Utils.TEMP_GROUPID),
					dossier.getLong(
						ConvertDossierFromV1Dot9Utils.TEMP_DOSSIERID),
					actionCode, appUser, appSecret,serviceContext);
			}

			result.put(ConstantUtils.TOTAL, dataFile.length());

			return Response.status(HttpURLConnection.HTTP_OK).entity(
				JSONFactoryUtil.looseSerialize(result)).build();
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response addDossierFileByDossierNoOldDb(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		Attachment file, long dvcGroupId) {

		try {

			JSONObject result = JSONFactoryUtil.createJSONObject();

			DataHandler dataHandle = file.getDataHandler();
			JSONArray dataFile =
				ConvertDossierFromV1Dot9Utils.readExcelFileWithHeaderConfig(
					dataHandle.getInputStream());

			for (int i = 0; i < dataFile.length(); i++) {

				JSONObject dossierFile = dataFile.getJSONObject(i);

				if (dvcGroupId > 0) {
					dossierFile.put(
						ConvertDossierFromV1Dot9Utils.TEMP_GROUPID, dvcGroupId);
				}
				ConvertDossierFromV1Dot9Utils.setDossierFileObject(
					dossierFile, serviceContext);
			}
			result.put(ConstantUtils.TOTAL, dataFile.length());

			return Response.status(HttpURLConnection.HTTP_OK).entity(
				JSONFactoryUtil.looseSerialize(result)).build();
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response addApplicantOldDb(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		Attachment file) {

		try {

			JSONObject result = JSONFactoryUtil.createJSONObject();

			DataHandler dataHandle = file.getDataHandler();
			JSONArray dataApplicant =
				ConvertDossierFromV1Dot9Utils.readExcelFileWithHeaderConfig(
					dataHandle.getInputStream());

//			long groupId =
//				GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			for (int i = 0; i < dataApplicant.length(); i++) {

				try {

					JSONObject applicant = dataApplicant.getJSONObject(i);
					ApplicantActions actions = new ApplicantActionsImpl();
					actions.importApplicantDB(user.getUserId(), applicant.getLong(Field.GROUP_ID),
							applicant.getString(ApplicantTerm.APPLICANTIDNO),
							applicant.getString(ApplicantTerm.APPLICANTNAME),
							applicant.getString(ApplicantTerm.APPLICANTIDTYPE), null,
							applicant.getString(ApplicantTerm.EMAIL), applicant.getString(ApplicantTerm.TELNO),
							applicant.getString(ApplicantTerm.ADDRESS), applicant.getString(ApplicantTerm.CITYCODE),
							applicant.getString(ApplicantTerm.DISTRICTCODE),
							applicant.getString(ApplicantTerm.WARDCODE),
							applicant.getString(ApplicantTerm.APPLICANTNAME), StringPool.BLANK, false, serviceContext);
				}
				catch (Exception e) {
					_log.error(e);
				}

			}
			result.put(ConstantUtils.TOTAL, dataApplicant.length());

			return Response.status(HttpURLConnection.HTTP_OK).entity(
				JSONFactoryUtil.looseSerialize(result)).build();
		}
		catch (Exception e) {

			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response doConvertDossierWithSql(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		String driveClassName, String connectionUrl, String dbUser,
		String dbSecret, String sqlQuery,
		String actionCode, String pathBase, long dvcGroupId, long groupId,
		String appUser, String appSecret) {

		try {
			JSONObject result = JSONFactoryUtil.createJSONObject();
			result.put(
				ConstantUtils.TOTAL, doImportDossier19(
					driveClassName, connectionUrl, dbUser, dbSecret, sqlQuery,
					actionCode, pathBase, dvcGroupId, groupId, appUser, appSecret,serviceContext));
			return Response.status(HttpURLConnection.HTTP_OK).entity(
				JSONFactoryUtil.looseSerialize(result)).build();
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response doConvertDossierFileWithSql(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		String driveClassName, String connectionUrl, String dbUser,
		String dbSecret, String sqlQuery,
		String actionCode, String pathBase, long dvcGroupId, long groupId) {

		try {
			JSONObject result = JSONFactoryUtil.createJSONObject();
			result.put(
				ConstantUtils.TOTAL, doImportDossierFile19(
					driveClassName, connectionUrl, dbUser, dbSecret, sqlQuery,
					actionCode, pathBase, dvcGroupId, groupId, serviceContext));
			return Response.status(HttpURLConnection.HTTP_OK).entity(
				JSONFactoryUtil.looseSerialize(result)).build();
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	public int doImportDossier19(
		String driveClassName, String connectionUrl, String dbUser,
		String dbSecret, String sqlQuery,
		String actionCode, String pathBase, long dvcGroupId, long groupId,
		String appUser, String appSecret,
		ServiceContext serviceContext)
		throws SQLException {

//		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			Class.forName(driveClassName);
			try (Connection con = DriverManager.getConnection(
				connectionUrl, dbUser,
				dbSecret)) {
				// here sonoo is database name, root is username and password
	//			String query = "select * from thanhnv_dossier_mapped_done";
	//			if (groupId > 0) {
	//
	//				query += " where groupId=" + groupId;
	//			}
				stmt = con.createStatement();
				rs = stmt.executeQuery(sqlQuery);
	
				while (rs.next()) {
//					System.out.println(
//						rs.getString(1) + "  " + rs.getString(2) + "  " +
//							rs.getString(3) + "   " + rs.getString("dossierNo"));
					result++;
	
					JSONObject dossierJson =
						ConvertDossierFromV1Dot9Utils.buildDossierJSONObject(rs);
					dossierJson.put(
						ConvertDossierFromV1Dot9Utils.TEMP_ONLINE_, true);
					dossierJson.put(DossierTerm.ONLINE, true);
					dossierJson.put(
						ConvertDossierFromV1Dot9Utils.TEMP_ORIGINALITY, 1);
					dossierJson.put(
						ConvertDossierFromV1Dot9Utils.TEMP_ORIGINAL, false);
					// them ho so vao dvc set groupId = dvcGroupId
					if (dvcGroupId > 0) {

						if (Validator.isNotNull(dossierJson.getString(ConvertDossierFromV1Dot9Utils.TEMP_CONTACTEMAIL))) {
							Applicant applicant = ApplicantLocalServiceUtil.fetchByF_GID_CTEM(groupId, dossierJson.getString(ConvertDossierFromV1Dot9Utils.TEMP_CONTACTEMAIL));
							if (Validator.isNotNull(applicant) && applicant.getMappingUserId() > 0) {
								dossierJson.put(
										ConvertDossierFromV1Dot9Utils.TEMP_USERNAME, applicant.getApplicantName());
								dossierJson.put(ConvertDossierFromV1Dot9Utils.TEMP_USERID, applicant.getMappingUserId());
							}
						}
						dossierJson.put(
							ConvertDossierFromV1Dot9Utils.TEMP_GROUPID, dvcGroupId);
	
						dossierJson.put(
							ConvertDossierFromV1Dot9Utils.TEMP_DOSSIERSUBSTATUS,
							StringPool.BLANK);
					}
					JSONObject dossier =
						ConvertDossierFromV1Dot9Utils.setDossierObject(dossierJson);
					if (dvcGroupId <= 0) {
						ConvertDossierFromV1Dot9Utils.insertUserDossier(
							dossier.getLong(
								ConvertDossierFromV1Dot9Utils.TEMP_GROUPID),
							dossier.getLong(
								ConvertDossierFromV1Dot9Utils.TEMP_DOSSIERID));
					}
					else {
						ConvertDossierFromV1Dot9Utils.insertUserDossierDvc(
							dossier.getLong(
								ConvertDossierFromV1Dot9Utils.TEMP_USERID),
							dossier.getLong(
								ConvertDossierFromV1Dot9Utils.TEMP_DOSSIERID));
					}
	
					ConvertDossierFromV1Dot9Utils.callActionDoneDossier(
						pathBase,
						dossier.getLong(ConvertDossierFromV1Dot9Utils.TEMP_GROUPID),
						dossier.getLong(
							ConvertDossierFromV1Dot9Utils.TEMP_DOSSIERID),
						actionCode, appUser, appSecret,serviceContext);
				}
			}
			catch (Exception e) {
				_log.debug(e);
			}
		}
		catch (Exception ex) {
			_log.info(ex);
		}
		finally {
			stmt.close();
//			if (con != null) {
//				con.close();
//			}
			if (stmt != null) {
				stmt.close();
			}
			if (rs != null) {
				rs.close();
			}
		}

		return result;
	}

	public int doImportDossierFile19(
		String driveClassName, String connectionUrl, String dbUser,
		String dbSecret, String sqlQuery,
		String actionCode, String pathBase, long dvcGroupId, long groupId,
		ServiceContext serviceContext)
		{

//		Connection con = null;
//		Statement stmt = null;
//		ResultSet rs = null;
		int result = 0;
		try {
			Class.forName(driveClassName);
			try (Connection con = DriverManager.getConnection(
				connectionUrl, dbUser,
				dbSecret)) {
				// here sonoo is database name, root is username and password
				try (Statement stmt = con.createStatement()) {
		//			String query = "select * from thanhnv_dossierPart_mapped_done2";
		//			if (groupId > 0) {
		//
		//				query += " where groupId=" + groupId;
		//			}
	//				stmt = con.createStatement();
					try (ResultSet rs = stmt.executeQuery(
						sqlQuery)) {
						while (rs.next()) {
//							System.out.println(
//								rs.getString(1) + "  " + rs.getString(2) + "  " +
//									rs.getString(3) + "   " + rs.getString("fileUrl"));
							result++;
			
							JSONObject dossierFile =
								ConvertDossierFromV1Dot9Utils.buildDossierFileJSONObject(
									rs);
							if (dvcGroupId > 0) {
								dossierFile.put(
									ConvertDossierFromV1Dot9Utils.TEMP_GROUPID, dvcGroupId);
							}
							ConvertDossierFromV1Dot9Utils.setDossierFileObject(
								dossierFile, serviceContext);
						}
					}
					catch (Exception e) {
						_log.debug(e);
					}
				}
				catch (SQLException e) {
					_log.debug(e);
				}
			}
			catch (Exception e) {
				_log.debug(e);
			}
		}
		catch (Exception ex) {
			_log.debug(ex);
//			System.out.println(ex);
		}
		finally {
//			try {
//				if (con != null) {
//					con.close();
//				}
//				if (stmt != null) {
//					stmt.close();
//				}
//				if (rs != null) {
//					rs.close();
//				}
//			}
//			catch (SQLException e) {
//				_log.debug(e);
//			}
		}

		return result;
	}

	@Override
	public Response getMetaDataDetailDossier(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String id, String key) {
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		BackendAuth auth = new BackendAuthImpl();
		try {
			if (!auth.isAuth(serviceContext)) {
				throw new Exception(MessageUtil.getMessage(ConstantUtils.API_USER_NOTHAVEPERMISSION));
			}
			Dossier dossier = DossierUtils.getDossier(id, groupId);
			if (dossier != null) {
				String[] keys = key.split(ConstantUtils.EXTENSION_SPLIT);
				if (dossier.getMetaData() != null) {
					JSONObject obj = JSONFactoryUtil.createJSONObject(dossier.getMetaData());
					for (int i = 0; i < keys.length - 1; i++) {
						String objectKey = keys[i];
						if (obj.has(objectKey)) {
							obj = obj.getJSONObject(objectKey);
						}
						else {
							return Response.status(HttpURLConnection.HTTP_OK).entity(StringPool.BLANK).build();
						}
					}					
					if (obj != null && obj.has(keys[keys.length - 1])) {
						return Response.status(HttpURLConnection.HTTP_OK).entity(obj.getString(keys[keys.length - 1])).build();						
					}
					else {
						return Response.status(HttpURLConnection.HTTP_OK).entity(StringPool.BLANK).build();						
					}
				}
				else {
					return Response.status(HttpURLConnection.HTTP_OK).entity(StringPool.BLANK).build();					
				}
			}
			else {
				return Response.status(HttpURLConnection.HTTP_OK).entity(StringPool.BLANK).build();				
			}
		}
		catch (Exception e) {
			_log.debug(e);
			return Response.status(
				HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(
				MessageUtil.getMessage(ConstantUtils.API_USER_NOTHAVEPERMISSION)).build();
		}
	}

	@Override
	public Response putMetaDataDetailDossier(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String id, String data) {
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		BackendAuth auth = new BackendAuthImpl();
		try {
			if (!auth.isAuth(serviceContext)) {
				throw new Exception(MessageUtil.getMessage(ConstantUtils.API_USER_NOTHAVEPERMISSION));
			}
			Dossier dossier = DossierUtils.getDossier(id, groupId);
			if (dossier != null) {
				JSONObject obj = JSONFactoryUtil.createJSONObject(dossier.getMetaData());
				if (Validator.isNull(data)) {
					Enumeration<String> keyIt = request.getParameterNames();
					
					while (keyIt.hasMoreElements()) {
						String key = keyIt.nextElement();			
						String[] keys = key.split("\\.");
						JSONObject tempObj = obj;
						int index = 0;
						for (int i = 0; i < keys.length; i++) {
							if (tempObj.has(keys[i]) && tempObj.getJSONObject(keys[i]) != null) {
								tempObj = tempObj.getJSONObject(keys[i]);
							}
							else {
								index = i;
								break;
							}
						}
						if (keys.length == 1) {
							obj.put(key, request.getParameter(key));																		
						}
						else {
							if (index == keys.length - 1) {
								tempObj.put(keys[index], request.getParameter(key));							
							}
							else {
								JSONObject mergeObj = JSONFactoryUtil.createJSONObject();
								mergeObj.put(keys[keys.length - 1], request.getParameter(key));
								for (int i = keys.length - 2; i > index; i--) {
									JSONObject indexObj = JSONFactoryUtil.createJSONObject();
									indexObj.put(keys[i], mergeObj);
									mergeObj = indexObj;
								}
								tempObj.put(keys[index], mergeObj);
							}
						}
					}
					
					dossier.setMetaData(obj.toJSONString());
					DossierLocalServiceUtil.updateDossier(dossier);
					
					return Response.status(HttpURLConnection.HTTP_OK).entity(ConstantUtils.API_JSON_TRUE_EMPTY).build();					
				}
				else {
					JSONObject dataObj = JSONFactoryUtil.createJSONObject(data);
					Iterator<String> keyIt = dataObj.keys();
					
					while (keyIt.hasNext()) {
						String key = keyIt.next();			
						String[] keys = key.split(ConstantUtils.EXTENSION_SPLIT);
						JSONObject tempObj = obj;
						int index = 0;
						for (int i = 0; i < keys.length; i++) {
							if (tempObj.has(keys[i]) && tempObj.getJSONObject(keys[i]) != null) {
								tempObj = tempObj.getJSONObject(keys[i]);
							}
							else {
								index = i;
								break;
							}
						}
						if (keys.length == 1) {
							obj.put(key, dataObj.get(key));																		
						}
						else {
							if (index == keys.length - 1) {
								tempObj.put(keys[index], dataObj.get(key));							
							}
							else {
								JSONObject mergeObj = JSONFactoryUtil.createJSONObject();
								mergeObj.put(keys[keys.length - 1], dataObj.get(key));
								for (int i = keys.length - 2; i > index; i--) {
									JSONObject indexObj = JSONFactoryUtil.createJSONObject();
									indexObj.put(keys[i], mergeObj);
									mergeObj = indexObj;
								}
								tempObj.put(keys[index], mergeObj);
							}
						}
					}
					
					dossier.setMetaData(obj.toJSONString());
					DossierLocalServiceUtil.updateDossier(dossier);
					
					return Response.status(HttpURLConnection.HTTP_OK).entity(ConstantUtils.API_JSON_TRUE_EMPTY).build();					
				}
			}
			else {
				return Response.status(HttpURLConnection.HTTP_OK).entity(ConstantUtils.API_JSON_FALSE_EMPTY).build();				
			}
		}
		catch (Exception e) {
			_log.debug(e);
			return Response.status(
				HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(
				MessageUtil.getMessage(ConstantUtils.API_USER_NOTHAVEPERMISSION)).build();
		}
	}

	@Override
	public Response updateInformDossier(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, String id,
		DossierInputModel input) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		BackendAuth auth = new BackendAuthImpl();

		DossierPermission dossierPermission = new DossierPermission();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			dossierPermission.hasCreateDossier(
				groupId, user.getUserId(), input.getServiceCode(),
				input.getGovAgencyCode(), input.getDossierTemplateNo());

			Dossier oldDossier = DossierUtils.getDossier(id, groupId);
			if (oldDossier != null) {
				if (Validator.isNotNull(input.getDossierNo())) {
					oldDossier.setDossierNo(input.getDossierNo());
				}
				if (Validator.isNotNull(input.getReceiveDate())) {
					oldDossier.setReceiveDate(new Date(GetterUtil.getLong(input.getReceiveDate())));
				}
				if (Validator.isNotNull(input.getDueDate())) {
					oldDossier.setDueDate(new Date(GetterUtil.getLong(input.getDueDate())));
				}
				
				oldDossier = DossierLocalServiceUtil.updateDossier(oldDossier);
				DossierDetailModel result =
						DossierUtils.mappingForGetDetail(oldDossier, user.getUserId());
				//Update DVC_QG
				_log.info("input.getDvcqgIntegration(): "+input.getDvcqgIntegration());
				if (Validator.isNotNull(input.getDvcqgIntegration()) && input.getDvcqgIntegration()) {
					//add by TrungNT Fake
					DVCQGIntegrationActionImpl actionImpl = new DVCQGIntegrationActionImpl();
					String mappingDossierStatus = actionImpl.getMappingStatus(oldDossier.getGroupId(), oldDossier);
					if(Validator.isNotNull(mappingDossierStatus)) {
						List<ServerConfig> lstScs = ServerConfigLocalServiceUtil.getByProtocol(oldDossier.getGroupId(), ServerConfigTerm.DVCQG_INTEGRATION);
						for (ServerConfig sc : lstScs) {
							try {
								List<PublishQueue> lstQueues = PublishQueueLocalServiceUtil.getByG_DID_SN_ST(oldDossier.getGroupId(), oldDossier.getDossierId(), sc.getServerNo(), new int[] { PublishQueueTerm.STATE_WAITING_SYNC, PublishQueueTerm.STATE_ALREADY_SENT });
								if (lstQueues == null || lstQueues.isEmpty()) {
									PublishQueueLocalServiceUtil.updatePublishQueue(oldDossier.getGroupId(), 0, oldDossier.getDossierId(), sc.getServerNo(), PublishQueueTerm.STATE_WAITING_SYNC, 0, serviceContext);
								}
							} catch (PortalException e) {
								_log.debug(e);
							}
						}
					}
				}

				return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();
			}
			else {
				DossierDetailModel result =
						new DossierDetailModel();

				return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();				
			}
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response updateEparDossier(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, DossierPublishModel input) {
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		try {
			_log.info("Updating dossier epar with groupId: "+ groupId );
			Dossier dossier =
				CPSDossierBusinessLocalServiceUtil.eparPublish(
					groupId, company, user, serviceContext, id,
					DossierUtils.convertFormModelToPublishModel(input));
			_log.info("Updated dossier epar with dossierId: "+ dossier.getDossierId() );

			List<ServerConfig> listServerConfig = ServerConfigLocalServiceUtil.getByProtocol(groupId, ServerConfigTerm.DVCQG_INTEGRATION);
			for (ServerConfig serverConfig : listServerConfig) {
				List<PublishQueue> lstQueues = PublishQueueLocalServiceUtil.getByG_DID_SN_ST(dossier.getGroupId(),
						dossier.getDossierId(), serverConfig.getServerNo(),
						new int[] { PublishQueueTerm.STATE_WAITING_SYNC, PublishQueueTerm.STATE_ALREADY_SENT });
				if (lstQueues == null || lstQueues.isEmpty()) {
					PublishQueueLocalServiceUtil.updatePublishQueue(dossier.getGroupId(), 0, dossier.getDossierId(),
							serverConfig.getServerNo(), PublishQueueTerm.STATE_WAITING_SYNC, 0, serviceContext);
				}
			}
			_log.info("Done saving dossier to queue to integrate to DVCQG");

			return Response.status(HttpURLConnection.HTTP_OK).entity(
				JSONFactoryUtil.looseSerializeDeep(dossier)).build();
		}
		catch (Exception e) {
			_log.error("Update epar dossier error: " + e.getMessage());
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getDossierCounterByDay(HttpServletRequest request,HttpHeaders header,Company company,Locale locale,User user,
		ServiceContext serviceContext,String date)
	{
		try {
			List<Dossier> dossiers = DossierLocalServiceUtil.findDossierByDay(date);
			if(Validator.isNotNull(dossiers))
			{
				JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
				JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
				JSONArray jsonDossierId = JSONFactoryUtil.createJSONArray();
				JSONArray jsonGroupId = JSONFactoryUtil.createJSONArray();
				for (Dossier dossier : dossiers)
				{
					String dossierCounter = dossier.getDossierCounter();
					String dossierId = String.valueOf(dossier.getDossierId());
					String groupId = String.valueOf(dossier.getGroupId());
					if(Validator.isNotNull(dossierCounter)){
						jsonArray.put(dossierCounter);
					}
					if(Validator.isNotNull(dossierId)){
						jsonDossierId.put(dossierId);
					}
					if(Validator.isNotNull(groupId)){
						jsonGroupId.put(groupId);
					}
				}
				jsonObject.put("dossierCounter", jsonArray);
				jsonObject.put("dossierId", jsonDossierId);
				jsonObject.put("groupId", jsonGroupId);
				return Response.status(HttpURLConnection.HTTP_OK).entity(jsonObject.toString()).build();
			}
			else
				return Response.status(HttpURLConnection.HTTP_NO_CONTENT).build();
		}catch (Exception e){
			e.printStackTrace();
			_log.info("------ Log Exception ------ " + " " + e.getMessage());
		}
		return Response.status(HttpURLConnection.HTTP_OK).build();

	}

	public DossierDetailModel mappingForGetDetail(Dossier dossier, long userId){
		DossierDetailModel result = new DossierDetailModel();
		if (dossier != null) {
			result = DossierUtils.mappingForGetDetail(dossier, userId);
			_log.info("TRACE_LOG_INFO result Dossier: " + JSONFactoryUtil.looseSerialize(result));
		}
		return result;
	}

	@Override public Response updateState(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
										  ServiceContext serviceContext, long id, String codeNumber, int state)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date now = new Date();
		String codeNumberArray[] = codeNumber.split(",");
		BookingActions bookingActions = new BookingActionsImpl();
		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray updated=JSONFactoryUtil.createJSONArray();
		for (String cn:codeNumberArray )
		{
			Booking booking = bookingActions.getByCodeNumber(cn);
			if (Validator.isNotNull(booking))
			{
				Date checkinDate = booking.getCheckinDate();
				long classPK = booking.getClassPK();
				if (dateFormat.format(checkinDate).equals(dateFormat.format(now)) && id == classPK)
				{
					booking.setState(state);
					Booking bookingResult = BookingLocalServiceUtil.updateBooking(booking);
					if (Validator.isNotNull(bookingResult))
						updated.put(cn);
				}
			}
		}
		result.put("status",true);
		result.put("codeNumberUpdated",updated);
		return Response.status(HttpURLConnection.HTTP_OK).entity(result.toString()).build();
	}

	private static final String VN_FORMAT_H = "dd/MM/yyyy HH:mm:ss";
	@Override
	public Response calculateDueDate(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
									 User user, ServiceContext serviceContext, double days) {
		int durationUnit = 0;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(VN_FORMAT_H);
			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			Date date = new Date();
			Date dateNow =
					new SimpleDateFormat(VN_FORMAT_H).parse(formatter.format(date));
			_log.info(dateNow);
			DueDateUtils dueDateUtils = new DueDateUtils(
					dateNow, days,durationUnit , groupId);
			String dueDate = new SimpleDateFormat(VN_FORMAT_H).format(
					dueDateUtils.getDueDate());
			return Response.status(HttpURLConnection.HTTP_OK).entity(dueDate).build();
		}
		catch (Exception e) {
			_log.debug(e);
		}

		return Response.status(java.net.HttpURLConnection.HTTP_INTERNAL_ERROR).entity(StringPool.BLANK).build();
	}

	@Override
	public Response garbageCollectorDossier(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext) {

		try {
			int[] originalityArr = {0, 9};
			DossierLocalServiceUtil.removeDossierByG_NOTO_DS(originalityArr, StringPool.BLANK);

			return Response.status(HttpURLConnection.HTTP_OK).entity(
				"{ok}").build();
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}


	@Override
	public Response doActionByDossierGroupId(HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, String dossierId, String actionCode, DoActionModel input, Long dueDate) {

		BackendAuth auth = new BackendAuthImpl();
		DossierActions actions = new DossierActionsImpl();
		DossierAction dossierResult = null;
		ErrorMsgModel errorModel = new ErrorMsgModel();
		JSONObject results = JSONFactoryUtil.createJSONObject();
		JSONArray data = JSONFactoryUtil.createJSONArray();
		JSONObject elmData = JSONFactoryUtil.createJSONObject();
		JSONArray options = JSONFactoryUtil.createJSONArray();
		JSONObject element = JSONFactoryUtil.createJSONObject();

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		long userId = user.getUserId();
		String actionUser = input.getActionUser();
		int total = 0;

		Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, user.getUserId());
		if (employee != null) {
			actionUser = employee.getFullName();
		} else {
			if (Validator.isNull(actionUser)) {
				actionUser = user.getFullName();
			}
		}
		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			List<DossierFile> listDossierFile = DossierFileLocalServiceUtil.findByDID_GROUP(groupId, Long.valueOf(dossierId));
			List<Dossier> lstDossiers = DossierLocalServiceUtil.findByG_GDID(groupId, dossierId);
			if(Validator.isNotNull(lstDossiers)) {
				for (Dossier dossier : lstDossiers) {

					_log.info("TRACE_LOG_INFO doAction Dossier: " + JSONFactoryUtil.looseSerialize(dossier));
					_log.info("TRACE_LOG_INFO doAction dueDate: " + dueDate);

					_log.debug("Input: " + JSONFactoryUtil.looseSerialize(input));
					_log.debug("TRACE_LOG_INFO in groupId: " + groupId + "|dossierId: " + dossierId + "userId: " + userId);

					if (dossier != null) {
						_log.debug("Dossier: " + dossier + ", actionCode: " + actionCode);

						if (Validator.isNotNull(dueDate)) {
							DossierLocalServiceUtil.updateDueDate(
									groupId, dossier.getDossierId(),
									dossier.getReferenceUid(), new Date(dueDate),
									serviceContext);
						}
						if (Validator.isNotNull(actionCode)) {
							ActionConfig actConfig = ActionConfigLocalServiceUtil.getByCode(groupId, actionCode);

							_log.debug("Action config: " + actConfig);
							String serviceCode = dossier.getServiceCode();
							String govAgencyCode = dossier.getGovAgencyCode();
							String dossierTempNo = dossier.getDossierTemplateNo();
							if (actConfig != null) {
								boolean insideProcess = actConfig.getInsideProcess();
								ProcessOption option = DossierUtils.getProcessOption(
										serviceCode, govAgencyCode, dossierTempNo, groupId);
								if (insideProcess) {
									if (dossier.getDossierActionId() == 0) {
										if (option != null) {
											long serviceProcessId = option.getServiceProcessId();
											ProcessAction proAction =
													DossierUtils.getProcessAction(
															user, groupId, dossier, actionCode, serviceProcessId);
											if (proAction != null) {
												_log.debug("DO ACTION: " + proAction.getActionCode());
												dossierResult = actions.doAction(
														groupId, userId, dossier, option,
														proAction, actionCode, actionUser,
														input.getActionNote(),
														input.getPayload(),
														input.getAssignUsers(),
														input.getPayment(),
														actConfig.getSyncType(),
														serviceContext, errorModel);
												boolean checkCreateFile = false;
												options = cloneDossierFile(dossier,listDossierFile,proAction,groupId,checkCreateFile,user,company,serviceContext,request,header,locale);
												if(options.length() >0){
													elmData.put(ProcessOptionTerm.OPTIONS, options);
												}
											}
										}
									} else {
										DossierAction dossierAction =
												DossierActionLocalServiceUtil.fetchDossierAction(
														dossier.getDossierActionId());
										if (dossierAction != null) {
											long serviceProcessId =
													dossierAction.getServiceProcessId();
											DossierTemplate dossierTemplate =
													DossierTemplateLocalServiceUtil.getByTemplateNo(
															groupId,
															dossier.getDossierTemplateNo());

											ProcessOption oldOption =
													ProcessOptionLocalServiceUtil.fetchBySP_DT(
															serviceProcessId,
															dossierTemplate.getDossierTemplateId());

											ProcessAction proAction =
													DossierUtils.getProcessAction(
															user, groupId, dossier, actionCode,
															serviceProcessId);
											if (proAction != null) {
												_log.debug(
														"DO ACTION: " +
																proAction.getActionCode());
												dossierResult = actions.doAction(
														groupId, userId, dossier, oldOption,
														proAction, actionCode, actionUser,
														input.getActionNote(),
														input.getPayload(),
														input.getAssignUsers(),
														input.getPayment(),
														actConfig.getSyncType(),
														serviceContext, errorModel);
												boolean checkCreateFile = false;
												options = cloneDossierFile(dossier,listDossierFile,proAction,groupId,checkCreateFile,user,company,serviceContext,request,header,locale);
												if(options.length() >0){
													elmData.put(ProcessOptionTerm.OPTIONS, options);
												}
											}
										}
									}
								} else {
									dossierResult = actions.doAction(
											groupId, userId, dossier, option, null,
											actionCode, actionUser, input.getActionNote(),
											input.getPayload(), input.getAssignUsers(),
											input.getPayment(), actConfig.getSyncType(),
											serviceContext, errorModel);
								}
							} else {
								ProcessOption option = DossierUtils.getProcessOption(
										serviceCode, govAgencyCode, dossierTempNo, groupId);
								if (option != null) {
									long serviceProcessId =
											option.getServiceProcessId();
									ProcessAction proAction =
											DossierUtils.getProcessAction(user,
													groupId, dossier, actionCode,
													serviceProcessId);
									if (proAction != null) {
										dossierResult = actions.doAction(
												groupId, userId, dossier, option, proAction,
												actionCode, actionUser,
												input.getActionNote(), input.getPayload(),
												input.getAssignUsers(), input.getPayment(),
												0, serviceContext, errorModel);
										boolean checkCreateFile = false;
										options = cloneDossierFile(dossier,listDossierFile,proAction,groupId,checkCreateFile,user,company,serviceContext,request,header,locale);
										if(options.length() >0){
											elmData.put(ProcessOptionTerm.OPTIONS, options);
										}
									}
								}
							}
						}
					}
					if (dossierResult != null) {
						long dossierActionId = dossierResult.getDossierActionId();
						DossierDocument doc = DossierDocumentLocalServiceUtil.getByActiocId(groupId, dossierActionId);
						long dossierDocumentId = 0;
						if (doc != null) {
							dossierDocumentId = doc.getDossierDocumentId();
						}
						 elmData = DossierUtils.mappingDossierJSON(dossierResult, dossierDocumentId, element);
						if(Validator.isNotNull(elmData)){
							data.put(elmData);
							total++;
						}

					}
//					Thread.sleep(500);
				}
				results.put(ConstantUtils.DATA,data);
				results.put(ConstantUtils.TOTAL,total);
			}
				return Response.status(HttpURLConnection.HTTP_OK).entity(results.toJSONString()).build();
		}
		catch (Exception e) {
			_log.debug(e);
			return BusinessExceptionImpl.processException(e);
		}
	}
	public JSONArray cloneDossierFile(Dossier dossier, List<DossierFile> listDossierFile, ProcessAction proAction,
									  Long groupId, boolean checkCreateFile, User user,
									  Company company, ServiceContext serviceContext, HttpServletRequest request, HttpHeaders header,
									  Locale locale){
		JSONArray options = JSONFactoryUtil.createJSONArray();
		JSONObject elmentOption = JSONFactoryUtil.createJSONObject();
		long fileEntryId= 0;
//		String fileTemplateNo = "";
		DossierPart dossierPart = null;
		//CreateDossierFile(ProcessAction) : id , id
		//DossierPart : Deliverable !=null ==> tìm được TP hồ sơ
		if (Validator.isNotNull(proAction.getCreateDossierFiles())) {
			String[] proCrDossierFile = proAction.getCreateDossierFiles().split(StringPool.COMMA);
			for (String idDossierFile : proCrDossierFile) {
				dossierPart = DossierPartLocalServiceUtil.getByTempAndFileTempNo(groupId, dossier.getDossierTemplateNo(), idDossierFile);
				_log.info("TRACE_LOG_INFO DossierPart: " + JSONFactoryUtil.looseSerialize(dossierPart));
				if (dossierPart.getPartNo().equals(idDossierFile)) {
					_log.info("TRACE_LOG_INFO dossierPartNo : " + dossierPart.getPartNo() + " ---- createDossierFile : " + idDossierFile);
					checkCreateFile = true;
				}
				if (Validator.isNotNull(dossierPart.getPartNo())) {
					if (checkCreateFile) {
						if ("7".equals(String.valueOf(dossierPart.getPartType()))) {
							if (Validator.isNotNull(listDossierFile)) {
								for (DossierFile item : listDossierFile) {
									_log.info("TRACE_LOG_INFO checkCreateFile " + checkCreateFile);
									DossierFile dossierFile = DossierFileLocalServiceUtil.updateDossierFile(
											0, groupId, company.getCompanyId(), user.getUserId(), user.getFullName(),
											dossier.getDossierId(),
											PortalUUIDUtil.generate(),
											item.getDossierTemplateNo(),
											item.getDossierPartNo(),
											item.getDossierPartType(),
											item.getFileTemplateNo(),
											item.getDisplayName(), item.getFormData(),
											item.getFileEntryId(), false,
											item.getEForm(), item.isNew(),
											item.getRemoved(), item.getSignCheck(),
											item.getSignInfo(), item.getFormScript(),
											item.getFormReport(),
											item.getFormSchema(),
											item.getDeliverableCode());
									_log.info("TRACE_LOG_INFO DossierFile :  " + JSONFactoryUtil.looseSerialize(dossierFile));
									elmentOption.put(DossierFileTerm.DOSSIER_FILE_ID, item.getDossierFileId());
									elmentOption.put(DossierFileTerm.DOSSIER_ID, item.getDossierId());
									elmentOption.put(DossierFileTerm.GROUP_ID, item.getGroupId());
									elmentOption.put(DossierFileTerm.DOSSIER_TEMPLATE_NO, item.getDossierTemplateNo());
									elmentOption.put(DossierFileTerm.DOSSIER_PART_NO, item.getDossierPartNo());
									elmentOption.put(DossierFileTerm.FILE_TEMPLATE_NO, item.getFileTemplateNo());
									_log.info("TRACE_LOG_INFO DossierFileOld :  " + JSONFactoryUtil.looseSerialize(item));
									options.put(elmentOption);
								}
							}
						} else if ("2".equals(String.valueOf(dossierPart.getPartType()))) {
							Attachment file = null;
							addDossierFileByEForm(request, header, company,
									locale, user, serviceContext,
									file, String.valueOf(dossier.getDossierId()), dossierPart.getPartNo(),
									null, null, dossierPart.getSampleData());
						}
					}
				}

				//Tạo Deliverable cho hồ sơ
				List<DossierFile> listDossierHS = DossierFileLocalServiceUtil.findByDID_GROUP(groupId, dossier.getDossierId());
				if (listDossierHS != null && !listDossierHS.isEmpty()) {
					if (Validator.isNotNull(dossierPart.getDeliverableType())) {

						DeliverableType deliverableType = DeliverableTypeLocalServiceUtil.fetchByG_DLT(groupId, dossierPart.getDeliverableType());
						for (DossierFile item : listDossierHS) {
							//Check deliverable của dossierFile == TyCode của DeliverableType thì mới cho tạo Deliverable
							if (Validator.isNotNull(item.getDeliverableCode()) && item.getEForm() == true) {

								boolean eSignature = proAction.getESignature();
								if (eSignature == true) {

									_log.info("TRACE_LOG_INFO Check: " + eSignature + dossierPart.getDeliverableType());
									String deliverableTypes = deliverableType.getTypeCode();
//									String deliverableCode = item.getDeliverableCode();
									String deliverableCode = dossier.getDossierNo();
									String deliverableName = deliverableType.getTypeName();
									String govAgencyCode = dossier.getGovAgencyCode();
									String govAgencyName = dossier.getGovAgencyName();
									String applicationIdNo = dossier.getApplicantIdNo();
									String applicationName = dossier.getApplicantName();
									long dosserId = dossier.getDossierId();
									long formScriptFileId = deliverableType.getFormScriptFileId();
									long formReportFileId = deliverableType.getFormReportFileId();
									String formData = "";
									String deliverableState = "";
									//Xử lý formData cho ds người có công
									// Mapping formData --> với cấu hình của deliverableType
									try {
										if (Validator.isNotNull(item.getFormData())) {

											JSONObject jsonMapping = JSONFactoryUtil.createJSONObject();
											JSONObject jsonFormData = JSONFactoryUtil.createJSONObject(item.getFormData());
											jsonMapping = JSONFactoryUtil.createJSONObject(deliverableType.getMappingData());
											Map<String, Object> jsonMap = AutoFillFormData.jsonToMap(jsonMapping);
											for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {
												String value = String.valueOf(entry.getValue());
												if (value.startsWith(StringPool.POUND) && value.contains(DossierTerm.KQGP)) {

													Iterator<String> keys = jsonFormData.keys();
													while (keys.hasNext()) {
														String key = keys.next();
														String valueMeta = jsonFormData.getString(key);
														if (key.equals(entry.getKey())) {
															if(key.equals(DossierTerm.DELIVERABLE_STATE)){
																deliverableState = valueMeta;
															}else if(key.equals(DossierTerm.DELIVERABLE_CODE)){
																deliverableCode = valueMeta;
															}else if(key.equals(DossierTerm.DELIVERABLE_NAME)){
																deliverableName = valueMeta;
															}else if(key.equals(DossierTerm.GOV_AGENCY_CODE)){
																govAgencyCode = valueMeta;
															}else if(key.equals(DossierTerm.GOV_AGENCY_NAME)){
																govAgencyName = valueMeta;
															}
															jsonMapping.put(entry.getKey(), valueMeta);
															break;
														}
													}
												}
											}
//											for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {
//												if (entry.getValue().getClass().getName().contains(org.opencps.dossiermgt.action.util.ConstantUtils.KEY_JSON_OBJECT)) {
//													jsonMapping.put(entry.getKey(), (JSONObject) entry.getValue());
//												} else {
//													jsonMapping.put(entry.getKey(), entry.getValue() + StringPool.BLANK);
//												}
//											}
											formData = jsonMapping.toJSONString();
										}
									} catch (Exception e) {
										e.getMessage();
										_log.info("EXCEPTION: " + e.getMessage());
									}
									Deliverable deliverable = DeliverableLocalServiceUtil.addDeliverableSign(
											groupId, deliverableTypes, deliverableName,
											deliverableCode, govAgencyCode, govAgencyName,
											applicationIdNo, applicationName, "",
											"", "", "", deliverableState,
											dosserId, item.getFileEntryId(), formScriptFileId, formReportFileId,
											formData, "",
											serviceContext);
									_log.info("TRACE LOG INFO : " + JSONFactoryUtil.looseSerialize(deliverable));
								}
							}
						}
					}
				}
			}
		}

		return options;
	}

	@Override
	public Response getDetailAction(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
	User user, ServiceContext serviceContext, long dossierId, String stepCode, String actionCode) {
		DossierActionNextActionModel model = new DossierActionNextActionModel();
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
		if(Validator.isNotNull(dossier)){
			if(Validator.isNotNull(dossier.getProcessNo()) && Validator.isNotNull(stepCode) && Validator.isNotNull(actionCode)){
				ServiceProcess svProcess = ServiceProcessLocalServiceUtil.getByG_PNO(groupId,dossier.getProcessNo());
				if(Validator.isNotNull(svProcess)){
					ProcessAction processAction = ProcessActionLocalServiceUtil.fetchBySPI_PRESC_AC(svProcess.getServiceProcessId(), stepCode, actionCode);
					if(Validator.isNotNull(processAction)){
						model =org.opencps.api.controller.util.DossierActionUtils.
								mappingToDoActionModel(processAction);
					}
				}
			}
		}
		return Response.status(HttpURLConnection.HTTP_OK).entity(model).build();
	}

	@Override
	public Response getDossierGroups(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user, ServiceContext serviceContext, DossierSearchModel query) {
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		long userId = user.getUserId();
		DossierActions actions = new DossierActionsImpl();
		List<Dossier> lstDossier = new ArrayList<>();
		List<Dossier> lstSearchDossierCV = new ArrayList<>();
		try {
			if (Validator.isNull(query.getEnd()) || query.getEnd() == 0) {
				query.setStart(QueryUtil.ALL_POS);
				query.setEnd(QueryUtil.ALL_POS);
			}
			LinkedHashMap<String, Object> params =
					new LinkedHashMap<>();
			params.put(Field.GROUP_ID, String.valueOf(groupId));
			String keywordSearch = query.getKeyword();
			String keySearch = StringPool.BLANK;
			if (Validator.isNotNull(keywordSearch)) {
				keySearch = SpecialCharacterUtils.splitSpecial(keywordSearch);
			}
			params.put(Field.KEYWORD_SEARCH, keySearch);

			String status = query.getStatus();
			String substatus = query.getSubstatus();
			String agencys = query.getAgency();
			if (ALL_AGENCY.equals(agencys)) {
				agencys = StringPool.BLANK;
			}
			if (Validator.isNull(agencys)) {
				Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, userId);
				if (employee != null && Validator.isNotNull(employee.getScope())) {
					agencys = employee.getScope();
				}
			}

			String serviceCode = query.getService();
			String service = StringPool.BLANK;
			if (Validator.isNotNull(serviceCode)) {
				service = SpecialCharacterUtils.splitSpecial(serviceCode);
			}
			String templateNo = query.getTemplate();
			String template = StringPool.BLANK;
			if (Validator.isNotNull(templateNo)) {
				template = SpecialCharacterUtils.splitSpecial(templateNo);
			}
			String step = query.getStep();
			int year = query.getYear();
			int month = query.getMonth();
			String top = query.getTop();
			if (Validator.isNotNull(top) &&
					DossierTerm.STATISTIC.equals(top.toLowerCase())) {
				Calendar baseDateCal = Calendar.getInstance();
				baseDateCal.setTime(new Date());
				if (month == 0) {
					month = baseDateCal.get(Calendar.MONTH) + 1;
				}
				if (year == 0) {
					year = baseDateCal.get(Calendar.YEAR);
				}
			}

			String dossierIdNo = query.getDossierNo();
			String dossierNoSearch = StringPool.BLANK;
			if (Validator.isNotNull(dossierIdNo)) {
				dossierNoSearch =
						SpecialCharacterUtils.splitSpecial(dossierIdNo);
			}
			String domain = query.getDomain();
			if (Validator.isNotNull(domain)) {
				params.put(DossierTerm.DOMAIN_CODE, domain);
			}
			params.put(DossierTerm.STATUS, status);
			params.put(DossierTerm.SUBSTATUS, substatus);
			params.put(DossierTerm.AGENCYS, agencys);
			params.put(DossierTerm.SERVICE, service);
			params.put(DossierTerm.TEMPLATE, template);
			if (year != 0) {
				params.put(DossierTerm.YEAR, year);
			}
			if (month != 0) {
				params.put(DossierTerm.MONTH, month);
			}
			params.put(DossierTerm.DAY, query.getDay());
			if (Validator.isNotNull(step) && step.contains(DossierTerm.STEP_X)) {
				String stepCode = query.getStep();
 				if (Validator.isNotNull(stepCode)) {
					String[] stepArr = stepCode.split(StringPool.COMMA);
					if (stepArr != null && stepArr.length > 0) {
						List<StepConfig> lstSteps = StepConfigLocalServiceUtil.findByG_SCS(groupId, stepArr);
						StringBuilder stepBuilder = new StringBuilder();
						for (StepConfig sc : lstSteps) {
							if (sc.getStepCode().contains(DossierTerm.STEP_X)) {
								for (int i = 0; i <= 9; i++) {
									String stepCodeRep = sc.getStepCode().replace(DossierTerm.STEP_X, i + StringPool.BLANK);
									if (!StringPool.BLANK.contentEquals(stepBuilder.toString())) {
										stepBuilder.append(StringPool.COMMA);
									}
									stepBuilder.append(stepCodeRep);
								}
							} else {
								if (!StringPool.BLANK.contentEquals(stepBuilder.toString())) {
									stepBuilder.append(StringPool.COMMA);
								}
								stepBuilder.append(sc.getStepCode());
							}
						}
						params.put(DossierTerm.STEP, stepBuilder.toString());
					}
				}
			} else {
				params.put(DossierTerm.STEP, step);
			}
			params.put(DossierTerm.TOP, top);

			backend.auth.api.BackendAuth auth2 =
					new backend.auth.api.BackendAuthImpl();
			if (!auth2.isAdmin(serviceContext, ConstantUtils.ROLE_ADMIN_LOWER)) {
				params.put(DossierTerm.USER_ID, user.getUserId());
			}
			params.put(DossierTerm.DOSSIER_NO, dossierNoSearch);
			params.put(
					PaymentFileTerm.PAYMENT_STATUS, query.getPaymentStatus());
			// Nếu đơn vị nhận == _scope ==> Get Employee lấy được _scope gán giá trị cho param
			Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, userId);
			//Search công văn theo đơn vị nhận
			String searchCongVanTheoDonViNhan = query.getSearchCongVanTheoDonViNhan();
//			if (Validator.isNotNull(searchCongVanTheoDonViNhan)) {
//				String[] congVanArr = searchCongVanTheoDonViNhan.split(StringPool.COMMA);
//				boolean firtScope = false;
//				for (String key : congVanArr) {
//					if (key.equals(DossierTerm.FIRSTSCOPE)) {
//						firtScope = true;
//					}
//				}
//				if (firtScope) {
//					if (Validator.isNotNull(employee.getScope())) {
//						String[] employeeArr = employee.getScope().split(StringPool.COMMA);
//						params.put(DossierTerm.DON_VI_NHAN, employeeArr[0]);
//					}
//				} else {
//					if (query.getSearchCongVanTheoDonViNhan().equals(DossierTerm.SCOPE_)) {
//						if (Validator.isNotNull(employee.getScope())) {
//							params.put(DossierTerm.DON_VI_NHAN, employee.getScope());
//						}
//					} else {
//						params.put(DossierTerm.DON_VI_NHAN, searchCongVanTheoDonViNhan);
//					}
//				}
//			}
			Sort[] sorts = null;
			if (Validator.isNull(query.getSort())) {
				String dateSort = String.format(MessageUtil.getMessage(ConstantUtils.QUERY_NUMBER_SORT), DossierTerm.CREATE_DATE);
				sorts = new Sort[]{
						SortFactoryUtil.create(
								dateSort, Sort.LONG_TYPE,
								GetterUtil.getBoolean(query.getOrder()))
				};
			} else {
				String querySort = String.format(MessageUtil.getMessage(ConstantUtils.QUERY_STRING_SORT), query.getSort());
				sorts = new Sort[]{
						SortFactoryUtil.create(
								querySort, Sort.STRING_TYPE,
								GetterUtil.getBoolean(query.getOrder()))
				};
			}
			DossierResultsModel results = new DossierResultsModel();
			JSONObject jsonData = actions.getDossiers(
					user.getUserId(), company.getCompanyId(), groupId, params,
					sorts, query.getStart(), query.getEnd(), serviceContext);

			List<Long> lstId = DossierUtils.mappingForListCongVan((List<Document>) jsonData.get(ConstantUtils.DATA));
				long[] dossierIds = new long[lstId.size()];
				if(lstId !=null && !lstId.isEmpty()){
					int i = 0;
					for(Long id : lstId){
						dossierIds[i++] = id;
					}
				}
				if(Validator.isNotNull(dossierIds)) {
					if(dossierIds.length > 1) {
						lstDossier = DossierLocalServiceUtil.fetchByD_OR_D(dossierIds);
					}
				}
				if(Validator.isNotNull(searchCongVanTheoDonViNhan)) {
					if (lstDossier != null && !lstDossier.isEmpty()) {
						for (Dossier item : lstDossier){
							if(Validator.isNotNull(item.getMetaData())) {
								String metaData = item.getMetaData();
								JSONObject jsonMetaData = JSONFactoryUtil.createJSONObject(metaData);
								Iterator<String> keys = jsonMetaData.keys();
								while (keys.hasNext()) {
									String key = keys.next();
									String value = jsonMetaData.getString(key);
									if (key.equals(DossierTerm.DON_VI_NHAN)) {
										if (searchCongVanTheoDonViNhan.equals(DossierTerm.SCOPE_)) {
											if (Validator.isNotNull(employee.getScope())) {
												String[] employeeArr = employee.getScope().split(StringPool.COMMA);
 												if (value.equals(employeeArr[0])) {
													lstSearchDossierCV.add(item);
													break;
												}
											}
										} else {
											if (searchCongVanTheoDonViNhan.equals(value)) {
												lstSearchDossierCV.add(item);
												break;
											}
										}
									}
								}
							}
						}
					}
				}
			results.setTotal(lstSearchDossierCV.size());
 			results.getData().addAll(DossierUtils.mappingForListDossier(lstSearchDossierCV));
			return Response.status(HttpURLConnection.HTTP_OK).entity(results).build();
		}catch (Exception e) {
			_log.info(e.getMessage());
			return BusinessExceptionImpl.processException(e);
		}
	}

	private String getActionCode(long groupId, List<String> listActionCode, Dossier dossier, User user) {
		
		String actionCode = null;		
		DossierAction dossierAction = null;
		List<ProcessAction> processActionList = null;		
				
		try {
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
				
				if (Validator.isNotNull(stepCode) && serviceProcessId > 0) {
					processActionList = ProcessActionLocalServiceUtil.getProcessActionByG_SPID_PRESC(groupId,
							serviceProcessId, stepCode);
					if (processActionList != null && processActionList.size() > 0) {
						actionCode = mapActionCode(processActionList, listActionCode, dossier, groupId, user);
					}
				}else {
					ProcessOption option = null;
					if (dossierAction != null) {
						DossierTemplate dossierTemplate = DossierTemplateLocalServiceUtil.getByTemplateNo(dossier.getGroupId(), dossier.getDossierTemplateNo());
						option = ProcessOptionLocalServiceUtil.fetchBySP_DT(dossierAction.getServiceProcessId(), dossierTemplate.getDossierTemplateId());
					}else {
						option = getProcessOption(dossier.getServiceCode(), dossier.getGovAgencyCode(),
							dossier.getDossierTemplateNo(), groupId);
					}

					if (option != null) {
						serviceProcessId = option.getServiceProcessId();
					}
					processActionList = ProcessActionLocalServiceUtil
						.getByServiceStepCode(groupId, serviceProcessId, StringPool.BLANK);
					if (processActionList != null && processActionList.size() > 0) {
						actionCode = mapActionCode(processActionList, listActionCode, dossier, groupId, user);
					}						
				}
				
			}				
		} catch (Exception e) {
			_log.error(e);
		}		
		return actionCode;
	}
	
	private String mapActionCode(List<ProcessAction> processActionList,
			List<String> actionCodes, Dossier dossier, long groupId, User user) {
		String actCode = null;
		List<String> listActionCode = new ArrayList<String>();
		for (ProcessAction processAction : processActionList) {
			String actionCode = processAction.getActionCode();
			String autoEvent = processAction.getAutoEvent();
			String preCondition = processAction.getPreCondition();
			if (processCheckEnable(preCondition, autoEvent, dossier, actionCode, groupId, user)) {
				listActionCode.add(actionCode);
			}
		}
				
		for (String actionCode : actionCodes) {
			if (listActionCode.contains(actionCode)) {								
				actCode = actionCode;
				break;
			}
		}
		return actCode;
	}
	
	private boolean processCheckEnable(String preCondition, String autoEvent, Dossier dossier, String actionCode,
			long groupId, User curUser) {
			if (AUTO_EVENT_SUBMIT.equals(autoEvent) || AUTO_EVENT_TIMMER.equals(autoEvent)
				|| AUTO_EVENT_LISTENER.equals(autoEvent) || AUTO_EVENT_SPECIAL.equals(autoEvent)) {

				return false;
			}
			String[] preConditionArr = StringUtil.split(preCondition);
			if (preConditionArr != null && preConditionArr.length > 0) {

				return DossierMgtUtils.checkPreCondition(preConditionArr, dossier, curUser);
			}
			return true;
		}

	@Override
	public Response getInterconnectionDossier(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String id) {

		DossierResultsModel results = new DossierResultsModel();
		List<Dossier> listDossier = new ArrayList<Dossier>();
		BackendAuth auth = new BackendAuthImpl();
		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			
			// get dossier by dossierId
			Dossier dossier = DossierLocalServiceUtil.getDossier(Long.valueOf(id));
			if (dossier != null) {
				// originDossierNo != null -> hslt or hstg
				if (!StringUtils.isEmpty(dossier.getOriginDossierNo())) {
					getInterDossierFromOriginDossier(dossier, listDossier);
					getConnectDossierFromInterDossier(dossier, listDossier);
				}else {
					// la ho so goc-> tim danh sach hslt
					getInterDossierFromOriginDossier(dossier, listDossier);
				}
			}

			results.setTotal(listDossier.size());

			results.getData().addAll(DossierUtils.mappingForListDossier(listDossier));

			return Response.status(HttpURLConnection.HTTP_OK).entity(results).build();
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	private void getInterDossierFromOriginDossier(Dossier dossier, List<Dossier> listDossier) {
		// Ds ho so trung gian va lien thong tu ho so goc
		if (!StringUtils.isEmpty(dossier.getDossierNo())) {
			List<Dossier> aList = DossierLocalServiceUtil.fetchByORIGIN_NO(dossier.getDossierNo());
			// Lay ho so lien thong la ho so co originDossierId = 0
			Dossier newDossier = null;
			if (aList.size() > 0) {
				newDossier = aList.stream().filter(x -> x.getOriginDossierId()== 0)
						.findAny().orElse(null);
				listDossier.add(newDossier);
				getInterDossierFromOriginDossier(newDossier, listDossier);
			}
		}
	}

	private void getConnectDossierFromInterDossier(Dossier dossier, List<Dossier> listDossier) {
		// Ds ho so goc cua hslt
		List<Dossier> aList = new ArrayList<Dossier>();
		if (!StringUtils.isEmpty(dossier.getOriginDossierNo())) {
		aList = DossierLocalServiceUtil.fetchByNEW_DO_NO(dossier.getOriginDossierNo());
		}
		Dossier newDossier = null;
		if (aList.size() > 0) {
			newDossier = aList.stream().filter(x -> x.getOriginDossierId()== 0)
					.findAny().orElse(null);
			listDossier.add(newDossier);
			getConnectDossierFromInterDossier(newDossier, listDossier);
		}

	}


	@Override
	public Response updateDossierIdByRole(HttpServletRequest request, HttpHeaders header, Company company,
							Locale locale, User user, ServiceContext serviceContext, DoActionModel model) {
		try {
			DossierActions actions = new DossierActionsImpl();
			DossierAction dossierResult = null;
			ErrorMsgModel errorModel = new ErrorMsgModel();

			JSONObject results = JSONFactoryUtil.createJSONObject();
			JSONArray data = JSONFactoryUtil.createJSONArray();
			JSONObject elmData = JSONFactoryUtil.createJSONObject();
			JSONObject element = JSONFactoryUtil.createJSONObject();


			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			long userId = user.getUserId();
			String actionCode = model.getActionCode();
			String actionUser = model.getActionUser();
			Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(
					groupId, user.getUserId());
			if (employee != null) {
				actionUser = employee.getFullName();
			}else {
				if (Validator.isNull(actionUser)) {
					actionUser = user.getFullName();
				}
			}

			List<Role> userRoles = user.getRoles();
			boolean overdue = false;
			for (Role r : userRoles) {
				r.setName(ConstantUtils.ROLE_OVERDUE);
				if (r.getName().startsWith(ConstantUtils.ROLE_OVERDUE)) {
					_log.info("Role TRUE");
					overdue = true;
					break;
				}
			}

			if (!overdue) {
				throw new UnauthenticationException();
			}else {
				_log.info("ActionCode " + actionCode);
				if (actionCode.equals(DossierTerm.ACTION_CODE_SPECIAL)) {
					if (Validator.isNotNull(model.getDossierIds())) {
						String dossierIds = model.getDossierIds();
						_log.info("DossierId :" + dossierIds);
						List<Dossier> lstDossier = new ArrayList<>();
						List<Long> lstId = new ArrayList<>();
						String[] dossierArr = dossierIds.split(StringPool.COMMA);
						for (String dossierId : dossierArr) {
							lstId.add(Long.valueOf(dossierId));
						}
						_log.info("Length Id : " + lstId.size());
						long[] dossierIdsArr = new long[lstId.size()];
						if (lstId != null && !lstId.isEmpty()) {
							int i = 0;
							for (Long id : lstId) {
								dossierIdsArr[i++] = id;
							}
						}
						lstDossier = DossierLocalServiceUtil.fetchByD_OR_D(dossierIdsArr);
						_log.info("Length lstDossierId : " + lstDossier.size());
						if (lstDossier != null && lstDossier.size() > 0) {
							for (Dossier dossier : lstDossier) {
								_log.info("Log dossier : " + dossier.getDossierId());
								// Hồ sơ đã có kq ==> hs quá hạn thì cập nhật bằng thời gian trả hạn
								if (Validator.isNotNull(dossier.getReleaseDate())) {
									Long releaseDate = dossier.getReleaseDate().getTime(); // thời gian trả kq
									Long dueDate = dossier.getDueDate().getTime(); // thời gian hẹn trả
										_log.info("Thoa man thời gian thực hiện cập nhật thời gian cho hồ sơ");
										if (Validator.isNotNull(actionCode)) {
											ActionConfig actConfig =
													ActionConfigLocalServiceUtil.getByCode(
															groupId, actionCode);
											_log.info("Action config: " + actConfig);
											String serviceCode = dossier.getServiceCode();
											String govAgencyCode = dossier.getGovAgencyCode();
											String dossierTempNo = dossier.getDossierTemplateNo();
											if (actConfig != null) {
												boolean insideProcess = actConfig.getInsideProcess();
												ProcessOption option = DossierUtils.getProcessOption(
														serviceCode, govAgencyCode, dossierTempNo, groupId);
												if (!insideProcess) {
													_log.info("Vao outSide");
													dossierResult = actions.doAction(
															groupId, userId, dossier, option, null,
															actionCode, actionUser, model.getActionNote(),
															model.getPayload(), model.getAssignUsers(),
															model.getPayment(), actConfig.getSyncType(),
															serviceContext, errorModel);
												}
											}
//											else {
//												ProcessOption option = DossierUtils.getProcessOption(
//														serviceCode, govAgencyCode, dossierTempNo, groupId);
//												if (option != null) {
//													long serviceProcessId =
//															option.getServiceProcessId();
//													ProcessAction proAction =
//															DossierUtils.getProcessAction(user,
//																	groupId, dossier, actionCode,
//																	serviceProcessId);
//													if (proAction != null) {
//														_log.info("Thực hiện action 3");
//														dossierResult = actions.doAction(
//																groupId, userId, dossier, option, proAction,
//																actionCode, actionUser,
//																model.getActionNote(), model.getPayload(),
//																model.getAssignUsers(), model.getPayment(),
//																0, serviceContext, errorModel);
//													}
//												}
//											}
										}
									if (releaseDate > dueDate) {
										dossier.setReleaseDate(dossier.getDueDate());
									}
								}
								DossierLocalServiceUtil.updateDossier(dossier);
							}
							int total = 0;
							if (dossierResult != null) {
								long dossierActionId = dossierResult.getDossierActionId();
								DossierDocument doc = DossierDocumentLocalServiceUtil.getByActiocId(groupId, dossierActionId);
								long dossierDocumentId = 0;
								if (doc != null) {
									dossierDocumentId = doc.getDossierDocumentId();
								}
								elmData = DossierUtils.mappingDossierJSON(dossierResult, dossierDocumentId, element);
								if(Validator.isNotNull(elmData)){
									data.put(elmData);
									total++;
								}
							}
							results.put(ConstantUtils.DATA,data);
							results.put(ConstantUtils.TOTAL,total);

							return Response.status(HttpURLConnection.HTTP_OK).entity(results.toJSONString()).build();
						} else {
							return Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity(StringPool.BLANK).build();
						}
					} else {
						return Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity(StringPool.BLANK).build();
					}
				} else {
					return Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity(StringPool.BLANK).build();
				}
			}
		}catch (Exception e){
			_log.info(e.getMessage());
			return BusinessExceptionImpl.processException(e);
		}
	}
}
