package org.opencps.api.controller.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.Validator;
import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.ImageCaptchaService;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.opencps.api.controller.ApplicantManagement;
import org.opencps.api.controller.util.ApplicantUtils;
import org.opencps.api.controller.util.CaptchaServiceSingleton;
import org.opencps.api.controller.util.EmployeeUtils;
import org.opencps.api.controller.util.NGSPRestClient;
import org.opencps.api.employee.model.EmployeeAccountInputModel;
import org.opencps.api.employee.model.EmployeeAccountModel;
import org.opencps.api.usermgt.model.ApplicantInputModel;
import org.opencps.api.usermgt.model.ApplicantInputUpdateModel;
import org.opencps.api.usermgt.model.ApplicantModel;
import org.opencps.api.usermgt.model.ApplicantResultsModel;
import org.opencps.api.usermgt.model.ApplicantSearchModel;
import org.opencps.api.usermgt.model.ProfileInputModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.auth.api.keys.ActionKeys;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.opencps.dossiermgt.constants.ServerConfigTerm;
import org.opencps.usermgt.action.ApplicantActions;
import org.opencps.usermgt.action.EmployeeInterface;
import org.opencps.usermgt.action.impl.ApplicantActionsImpl;
import org.opencps.usermgt.action.impl.EmployeeActions;
import org.opencps.usermgt.constants.ApplicantTerm;
import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.service.ApplicantLocalServiceUtil;

import backend.auth.api.exception.BusinessExceptionImpl;
import backend.auth.api.exception.ErrorMsgModel;
import vn.gov.ngsp.DKDN.GTVT.IDoanhNghiep;
import vn.gov.ngsp.DKDN.GTVT.IToken;
import vn.gov.ngsp.DKDN.GTVT.Models.MToken;

public class ApplicantManagementImpl implements ApplicantManagement {

	@Override
	public Response register(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, ApplicantInputModel input) {

		ApplicantActions actions = new ApplicantActionsImpl();

		ApplicantModel result = new ApplicantModel();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		
		backend.auth.api.BackendAuth auth2 = new backend.auth.api.BackendAuthImpl();


		try {
			String cityName = StringPool.BLANK;
			String districtName = StringPool.BLANK;
			String wardName = StringPool.BLANK;
			
			if (!auth2.checkToken(request, header)) {
				throw new UnauthenticationException();
			}
			String applicantName = HtmlUtil.escape(input.getApplicantName());
			String applicantIdType = HtmlUtil.escape(input.getApplicantIdType());
			String applicantIdNo = HtmlUtil.escape(input.getApplicantIdNo());
			String address = HtmlUtil.escape(input.getAddress());
			String cityCode = HtmlUtil.escape(input.getCityCode());
			String districtCode = HtmlUtil.escape(input.getDistrictCode());
			String wardCode = HtmlUtil.escape(input.getWardCode());
			String contactName = HtmlUtil.escape(input.getContactName());
			String contactTelNo = HtmlUtil.escape(input.getContactTelNo());
			String contactEmail = HtmlUtil.escape(input.getContactEmail());
			
			if (Validator.isNotNull(input.getCityCode())) {
				cityName = getDictItemName(groupId, ADMINISTRATIVE_REGION, input.getCityCode());
				
			}
			if (Validator.isNotNull(input.getDistrictCode())) {
				districtName = getDictItemName(groupId, ADMINISTRATIVE_REGION, input.getDistrictCode());
				
			}
			if (Validator.isNotNull(input.getWardCode())) {
				wardName = getDictItemName(groupId, ADMINISTRATIVE_REGION, input.getWardCode());
				
			}
			Applicant applicant = actions.register(serviceContext, groupId, applicantName, applicantIdType,
					applicantIdNo, input.getApplicantIdDate(), contactEmail, address,
					cityCode, cityName, districtCode, districtName,
					wardCode, wardName, contactName, contactTelNo,
					input.getPassword());
			_log.info("Success register applicant: " + (applicant != null ? applicant.getApplicantName() + "," + applicant.getContactEmail() : "FAILED"));
			result = ApplicantUtils.mappingToApplicantModel(applicant);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}
	
	protected String getDictItemName(long groupId, String collectionCode, String itemCode) {

		DictCollection dc = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(collectionCode, groupId);

		if (Validator.isNotNull(dc)) {
			DictItem it = DictItemLocalServiceUtil.fetchByF_dictItemCode(itemCode, dc.getPrimaryKey(), groupId);

			return it.getItemName();

		} else {
			return StringPool.BLANK;
		}

	}
	
	private static String ADMINISTRATIVE_REGION = "ADMINISTRATIVE_REGION";

	Log _log = LogFactoryUtil.getLog(ApplicantManagementImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public Response getApplicants(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, ApplicantSearchModel query) {
		ApplicantActions actions = new ApplicantActionsImpl();
		ApplicantResultsModel results = new ApplicantResultsModel();
		BackendAuth auth = new BackendAuthImpl();
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

//			if (!auth.hasResource(serviceContext, ServiceInfo.class.getName(), ActionKeys.ADD_ENTRY)) {
//				throw new UnauthorizationException();
//			}

			if (query.getEnd() == 0) {

				query.setStart(-1);

				query.setEnd(-1);

			}

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(Field.KEYWORD_SEARCH, query.getKeywords());
			params.put(ApplicantTerm.APPLICANTIDTYPE, query.getType());
			params.put(ApplicantTerm.LOCK, query.getLock());
			params.put(ApplicantTerm.APPLICANTIDNO, query.getIdNo());

			Sort[] sorts = new Sort[] { SortFactoryUtil.create(query.getSort() + "_sortable", Sort.STRING_TYPE,
					GetterUtil.getBoolean(query.getOrder())) };

			JSONObject jsonData = actions.getApplicants(serviceContext, serviceContext.getUserId(),
					serviceContext.getCompanyId(), groupId, params, sorts, query.getStart(), query.getEnd(),
					serviceContext);

			results.setTotal(jsonData.getInt("total"));
			if (jsonData != null && jsonData.getInt("total") > 0) {
				results.getData().addAll(ApplicantUtils.mappingToApplicantResults((List<Document>) jsonData.get("data")));
			}

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getApplicantDetail(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id) {
		ApplicantActions actions = new ApplicantActionsImpl();
		ApplicantModel results = new ApplicantModel();
		BackendAuth auth = new BackendAuthImpl();
		Applicant applicant = null;
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			User requestUser = ApplicantUtils.getUser(id);

			boolean isAllowed = false;

			if (auth.hasResource(serviceContext, Applicant.class.getName(), ActionKeys.ADD_ENTRY)) {
				isAllowed = true;
			} else {
				if (Validator.isNull(requestUser)) {
					throw new NoSuchUserException();
				} else {
					// check userLogin is equal userRequest get detail
					if (requestUser.getUserId() == user.getUserId()) {
						isAllowed = true;
					}
				}
			}

			if (isAllowed) {
				applicant = actions.getApplicantDetail(serviceContext, id);

				results = ApplicantUtils.mappingToApplicantModel(applicant);

				return Response.status(200).entity(results).build();
			} else {
				throw new UnauthorizationException();
			}

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response updateApplicant(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, ApplicantInputUpdateModel input) {
		ApplicantActions actions = new ApplicantActionsImpl();
		ApplicantModel results = new ApplicantModel();
		BackendAuth auth = new BackendAuthImpl();
		Applicant applicant = null;
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			User requestUser = ApplicantUtils.getUser(id);

			boolean isAllowed = false;

			if (auth.hasResource(serviceContext, Applicant.class.getName(), ActionKeys.ADD_ENTRY)) {
				isAllowed = true;
			} else {
				if (Validator.isNull(requestUser)) {
					throw new NoSuchUserException();
				} else {
					// check userLogin is equal userRequest get detail
					if (requestUser.getUserId() == user.getUserId()) {
						isAllowed = true;
					}
				}
			}
			

			String applicantName = HtmlUtil.escape(input.getApplicantName());
			String address = HtmlUtil.escape(input.getAddress());
			String cityCode = HtmlUtil.escape(input.getCityCode());
			String districtCode = HtmlUtil.escape(input.getDistrictCode());
			String wardCode = HtmlUtil.escape(input.getWardCode());
			String contactName = HtmlUtil.escape(input.getContactName());
			String contactTelNo = HtmlUtil.escape(input.getContactTelNo());
			String contactEmail = HtmlUtil.escape(input.getContactEmail());
			String cityName = HtmlUtil.escape(input.getCityName());
			String districtName = HtmlUtil.escape(input.getDistrictName());
			String wardName = HtmlUtil.escape(input.getWardName());
			
			if (Validator.isNotNull(input.getCityCode()) && Validator.isNull(cityName)) {
				cityName = getDictItemName(groupId, ADMINISTRATIVE_REGION, input.getCityCode());
				
			}
			if (Validator.isNotNull(input.getDistrictCode()) && Validator.isNull(districtName)) {
				districtName = getDictItemName(groupId, ADMINISTRATIVE_REGION, input.getDistrictCode());
				
			}
			if (Validator.isNotNull(input.getWardCode()) && Validator.isNull(wardName)) {
				wardName = getDictItemName(groupId, ADMINISTRATIVE_REGION, input.getWardCode());
				
			}
			
			if (isAllowed) {
				applicant = actions.updateApplicant(serviceContext,groupId, id, applicantName, address, cityCode,
						cityName, districtCode, districtName, wardCode,
						wardName, contactName, contactTelNo, contactEmail);

				results = ApplicantUtils.mappingToApplicantModel(applicant);

				return Response.status(200).entity(results).build();
			} else {
				throw new UnauthorizationException();
			}

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response deleteApplicant(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id) {
		ApplicantActions actions = new ApplicantActionsImpl();
		ApplicantModel results = new ApplicantModel();
		BackendAuth auth = new BackendAuthImpl();
		Applicant applicant = null;
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			boolean isAllowed = false;

			if (auth.hasResource(serviceContext, Applicant.class.getName(), ActionKeys.ADD_ENTRY)) {
				isAllowed = true;
			}

			if (isAllowed) {
				applicant = actions.removeApplicant(serviceContext, id);

				results = ApplicantUtils.mappingToApplicantModel(applicant);

				return Response.status(200).entity(results).build();
			} else {
				throw new UnauthorizationException();
			}

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getApplicantProfile(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id) {
		ApplicantActions actions = new ApplicantActionsImpl();
		BackendAuth auth = new BackendAuthImpl();
		Applicant applicant = null;
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			User requestUser = ApplicantUtils.getUser(id);

			boolean isAllowed = false;

			if (auth.hasResource(serviceContext, Applicant.class.getName(), ActionKeys.ADD_ENTRY)) {
				isAllowed = true;
			} else {
				if (Validator.isNull(requestUser)) {
					throw new NoSuchUserException();
				} else {
					// check userLogin is equal userRequest get detail
					if (requestUser.getUserId() == user.getUserId()) {
						isAllowed = true;
					}
				}
			}

			if (isAllowed) {
				applicant = actions.getApplicantDetail (serviceContext, id);

				JSONObject result = JSONFactoryUtil.createJSONObject(applicant.getProfile());
				
				return Response.status(200).entity(JSONFactoryUtil.looseSerialize(result)).build();
			} else {
				throw new UnauthorizationException();
			}

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response addApplicantProfile(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, ProfileInputModel input) {
		ApplicantActions actions = new ApplicantActionsImpl();
		BackendAuth auth = new BackendAuthImpl();
		Applicant applicant = null;
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			User requestUser = ApplicantUtils.getUser(id);

			boolean isAllowed = false;
			
			

			if (auth.hasResource(serviceContext, Applicant.class.getName(), ActionKeys.ADD_ENTRY)) {
				isAllowed = true;
			} else {
				if (Validator.isNull(requestUser)) {
					throw new NoSuchUserException();
				} else {
					// check userLogin is equal userRequest get detail
					if (requestUser.getUserId() == user.getUserId()) {
						isAllowed = true;
					}
				}
			}

			if (isAllowed) {
				applicant = actions.updateProfile(serviceContext,groupId, id, input.getValue());

				JSONObject result = JSONFactoryUtil.createJSONObject(applicant.getProfile());

				return Response.status(200).entity(JSONFactoryUtil.looseSerialize(result)).build();
			} else {
				throw new UnauthorizationException();
			}

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response updateApplicantProfile(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id, String key, ProfileInputModel input) {
		// TODO Auto-generated method stub
		ApplicantActions actions = new ApplicantActionsImpl();
		BackendAuth auth = new BackendAuthImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			User requestUser = ApplicantUtils.getUser(id);

			boolean isAllowed = false;

			if (auth.hasResource(serviceContext, Applicant.class.getName(), ActionKeys.ADD_ENTRY)) {
				isAllowed = true;
			} else {
				if (Validator.isNull(requestUser)) {
					throw new NoSuchUserException();
				} else {
					// check userLogin is equal userRequest get detail
					if (requestUser.getUserId() == user.getUserId()) {
						isAllowed = true;
					}
				}
			}

			if (isAllowed) {
				
				Applicant applicantUpdated = ApplicantLocalServiceUtil.getApplicant(id);
				
				JSONObject profile = JSONFactoryUtil.createJSONObject(applicantUpdated.getProfile());
				
				profile.put(key, input.getValue());
				
				actions.updateProfile(serviceContext,groupId, id, profile.toString());

				JSONObject result = JSONFactoryUtil.createJSONObject();

				result.put("key", key);
				result.put("value", input.getValue());

				return Response.status(200).entity(JSONFactoryUtil.looseSerialize(result)).build();
			} else {
				throw new UnauthorizationException();
			}

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response lockApplicant(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id) {
		ApplicantActions actions = new ApplicantActionsImpl();
		BackendAuth auth = new BackendAuthImpl();
		ApplicantModel results = new ApplicantModel();

		Applicant applicant = null;
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			User requestUser = ApplicantUtils.getUser(id);

			boolean isAllowed = false;
			List<Role> userRoles = user.getRoles();
			boolean isAdmin = false;
			for (Role r : userRoles) {
				if (r.getName().startsWith("Administrator")) {
					isAdmin = true;
					break;
				}
			}
			
			if (auth.hasResource(serviceContext, Applicant.class.getName(), ActionKeys.ADD_ENTRY)
					|| isAdmin) {
				isAllowed = true;
			} else {
				if (Validator.isNull(requestUser)) {
					throw new NoSuchUserException();
				} else {
					// check userLogin is equal userRequest get detail
					if (requestUser.getUserId() == user.getUserId()) {
						isAllowed = true;
					}
				}
			}

			if (isAllowed) {
				applicant = actions.lockApplicant(serviceContext, id);

				results = ApplicantUtils.mappingToApplicantModel(applicant);

				return Response.status(200).entity(results).build();

			} else {
				throw new UnauthorizationException();
			}

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response activateApplicant(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, String code) {
		ApplicantActions actions = new ApplicantActionsImpl();
//		ApplicantModel results = new ApplicantModel();
		
		long applicantId = 0;
		
		try {
			ApplicantLocalServiceUtil.getApplicant(id);
			
			applicantId = id;
			
		} catch (Exception e) {
			_log.debug(e);
			try {
				Applicant applc = ApplicantLocalServiceUtil.fetchByMappingID(id);
				
				if (Validator.isNotNull(applc)) {
					applicantId = applc.getApplicantId();
				}
			} catch (Exception e2) {
				// TODO: handle exception
				_log.debug(e2);
			}
			
		}

		Applicant applicant = null;
		try {
			
			

			applicant = actions.activationApplicant(serviceContext, applicantId, code);

//			results = ApplicantUtils.mappingToApplicantModel(applicant);
			
			JSONObject resultObj = JSONFactoryUtil.createJSONObject();
			
			resultObj.put("email", applicant.getContactEmail());
			resultObj.put("token", applicant.getTmpPass());

			return Response.status(200).entity(JSONFactoryUtil.looseSerialize(resultObj)).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	private MToken getToken(NGSPRestClient client) throws Exception {
		String tokenUrl = "https://api.ngsp.gov.vn/token";
		String consumer_key = "vDQ9b6f0LEpNeIDAAeZ4ler4mesa";
		String secret_key = "WVN8QUVA15guZdZyuuxhh_Pw1cUa";

		if (client != null) {
			tokenUrl = client.getBaseUrl();
			consumer_key = client.getConsumerKey();
			secret_key = client.getConsumerSecret();
		}
		MToken token = IToken.getToken(tokenUrl, consumer_key, secret_key);
		
		return token;
	}
	   
	@Override
	public Response ngspGetApplicantInfo(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String applicantIdNo) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		String apiUrl = "https://api.ngsp.gov.vn/apiCSDLDKDN/1.0/chiTietDoanhNghiep";
		String access_token = "cd21bef3-e484-3ce9-9045-84c240e9803b";
		
		List<ServerConfig> lstScs = ServerConfigLocalServiceUtil.getByProtocol(groupId, ServerConfigTerm.NGSP_PROTOCOL);
		ServerConfig sc = (lstScs.isEmpty() ? null : lstScs.get(0));
		try {
			if (sc != null) {
				MToken token = getToken(NGSPRestClient.fromJSONObject(JSONFactoryUtil.createJSONObject(sc.getConfigs())));
				access_token = token.getAccessToken();				
			}
			else {
				MToken token = getToken(null);
				access_token = token.getAccessToken();				
			}
		} catch (Exception e) {
			_log.debug(e);
		}
		
//		String msdn = "0100109106";
		String msdn = applicantIdNo;

		try {
			String rs = IDoanhNghiep.chiTietDoanhNghiep(apiUrl, access_token, msdn);
			JSONObject result = JSONFactoryUtil.createJSONObject(rs);
			
			return Response.status(200).entity(result.toJSONString()).build();			
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response verifyApplicantInfo(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String applicantIdNo, String applicantName,
			String contactName) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		String apiUrl = "https://api.ngsp.gov.vn/apiCSDLDKDN/1.0/chiTietDoanhNghiep";
		String access_token = "cd21bef3-e484-3ce9-9045-84c240e9803b";
		
		List<ServerConfig> lstScs = ServerConfigLocalServiceUtil.getByProtocol(groupId, ServerConfigTerm.NGSP_PROTOCOL);
		ServerConfig sc = (lstScs.isEmpty() ? null : lstScs.get(0));
		try {
			if (sc != null) {
				MToken token = getToken(NGSPRestClient.fromJSONObject(JSONFactoryUtil.createJSONObject(sc.getConfigs())));
				access_token = token.getAccessToken();				
			}
			else {
				MToken token = getToken(null);
				access_token = token.getAccessToken();				
			}
		} catch (Exception e) {
			_log.debug(e);
		}
		
		try {
			String rs = IDoanhNghiep.chiTietDoanhNghiep(apiUrl, access_token, applicantIdNo);
			JSONObject result = JSONFactoryUtil.createJSONObject(rs);
			JSONObject data = result.getJSONObject("Data");
			JSONObject returnObj = JSONFactoryUtil.createJSONObject();
			
			if (Validator.isNull(data.getJSONObject("MainInformation"))) {
				returnObj.put("error", true);
				returnObj.put("message", "Không tìm thấy thông tin doanh nghiệp");
				return Response.status(200).entity(returnObj.toJSONString()).build();							
			}
			else {
				JSONObject mainInfoObj = data.getJSONObject("MainInformation");
				if (Validator.isNotNull(mainInfoObj.getString("NAME"))) {
					if (Validator.isNotNull(applicantName) && !applicantName.equals(mainInfoObj.getString("NAME"))) {
						returnObj.put("warning", true);
						returnObj.put("message", "Thông tin tên doanh nghiệp có thể chưa đúng!");
					}
				}
				JSONObject representativesObj = data.getJSONObject("Representatives");
				if (representativesObj != null) {
					if (Validator.isNotNull(contactName) && !contactName.equals(representativesObj.getString("FULL_NAME"))) {
						returnObj.put("warning", true);
						returnObj.put("message", (Validator.isNotNull(returnObj.getString("message")) ? returnObj.getString("message") + "," : "") + "Chủ sở hữu doanh nghiệp có thể thông tin chưa chính xác!");
					}					
				}
				return Response.status(200).entity(returnObj.toJSONString()).build();							
			}
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getJCaptcha(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext) {
		try {
			ImageCaptchaService instance = CaptchaServiceSingleton.getInstance();
			
		    String captchaId = request.getSession().getId();
			File destDir = new File("jcaptcha");
			if (!destDir.exists()) {
				destDir.mkdir();
			}
			File file = new File("jcaptcha/" + captchaId  + ".png");
			if (!file.exists()) {
				file.createNewFile();				
			}
	
			if (file.exists()) {
			    BufferedImage challengeImage = instance.getImageChallengeForID(
			    captchaId, Locale.US );
			    try {
					ImageIO.write( challengeImage, "png", file );
					ResponseBuilder responseBuilder = Response.ok((Object) file);

					responseBuilder.header("Content-Disposition",
							"attachment; filename=\"" + file.getName() + "\"");
					responseBuilder.header("Content-Type", "image/png");

					return responseBuilder.build();
				    
				} catch (IOException e) {
					_log.debug(e);
				}
			}
			return Response.status(HttpURLConnection.HTTP_NO_CONTENT).build();
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response registerWithCaptcha(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, ApplicantInputModel input, String jCaptchaResponse) {
		ApplicantActions actions = new ApplicantActionsImpl();

		ApplicantModel result = new ApplicantModel();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		
		backend.auth.api.BackendAuth auth2 = new backend.auth.api.BackendAuthImpl();


		try {
			String cityName = StringPool.BLANK;
			String districtName = StringPool.BLANK;
			String wardName = StringPool.BLANK;
			
			if (!auth2.checkToken(request, header)) {
				throw new UnauthenticationException();
			}
			List<Role> userRoles = user.getRoles();
			boolean isAdmin = false;
			for (Role r : userRoles) {
				if (r.getName().startsWith("Administrator")) {
					isAdmin = true;
					break;
				}
			}
			if (isAdmin) {
				
			}
			else {
				ImageCaptchaService instance = CaptchaServiceSingleton.getInstance();
				String captchaId = request.getSession().getId();
		        try {
		        	_log.info("Captcha: " + captchaId + "," + jCaptchaResponse);
		        	boolean isResponseCorrect = instance.validateResponseForID(captchaId,
		        			jCaptchaResponse);
		        	_log.info("Check captcha result: " + isResponseCorrect);
		        	if (!isResponseCorrect) {
		        		ErrorMsgModel error = new ErrorMsgModel();
		        		error.setMessage("Captcha incorrect");
		    			error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
		    			error.setDescription("Captcha incorrect");

		    			return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
		        	}
		        } catch (CaptchaServiceException e) {
		        	_log.debug(e);
	        		ErrorMsgModel error = new ErrorMsgModel();
	        		error.setMessage("Captcha incorrect");
	    			error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
	    			error.setDescription("Captcha incorrect");

	    			return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
		        }
			}
			String applicantName = HtmlUtil.escape(input.getApplicantName());
			String applicantIdType = HtmlUtil.escape(input.getApplicantIdType());
			String applicantIdNo = HtmlUtil.escape(input.getApplicantIdNo());
			String address = HtmlUtil.escape(input.getAddress());
			String cityCode = HtmlUtil.escape(input.getCityCode());
			String districtCode = HtmlUtil.escape(input.getDistrictCode());
			String wardCode = HtmlUtil.escape(input.getWardCode());
			String contactName = HtmlUtil.escape(input.getContactName());
			String contactTelNo = HtmlUtil.escape(input.getContactTelNo());
			String contactEmail = HtmlUtil.escape(input.getContactEmail());
			
			if (Validator.isNotNull(input.getCityCode())) {
				cityName = getDictItemName(groupId, ADMINISTRATIVE_REGION, input.getCityCode());
				
			}
			if (Validator.isNotNull(input.getDistrictCode())) {
				districtName = getDictItemName(groupId, ADMINISTRATIVE_REGION, input.getDistrictCode());
				
			}
			if (Validator.isNotNull(input.getWardCode())) {
				wardName = getDictItemName(groupId, ADMINISTRATIVE_REGION, input.getWardCode());
				
			}
			Applicant applicant = actions.register(serviceContext, groupId, applicantName, applicantIdType,
					applicantIdNo, input.getApplicantIdDate(), contactEmail, address,
					cityCode, cityName, districtCode, districtName,
					wardCode, wardName, contactName, contactTelNo,
					input.getPassword());
			_log.info("Success register applicant: " + (applicant != null ? applicant.getApplicantName() + "," + applicant.getContactEmail() : "FAILED"));
			result = ApplicantUtils.mappingToApplicantModel(applicant);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response createApplicantAccount(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id, EmployeeAccountInputModel input) {

		ApplicantActions actions = new ApplicantActionsImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		EmployeeAccountModel employeeAccountModel = new EmployeeAccountModel();
		
		try {

			JSONObject jsonObject = actions.createApplicantAccount(user.getUserId(), company.getCompanyId(), groupId, id,
					input.getScreenName(), input.getEmail(), input.isExist(), serviceContext);

			employeeAccountModel = EmployeeUtils.mapperEmployeeAccountModel(jsonObject);

			if (Validator.isNotNull(jsonObject.getString("duplicate"))
					&& jsonObject.getString("duplicate").equals(Boolean.TRUE.toString())) {

				return Response.status(409).entity(employeeAccountModel).build();

			} else {

				return Response.status(200).entity(employeeAccountModel).build();

			}

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

}
