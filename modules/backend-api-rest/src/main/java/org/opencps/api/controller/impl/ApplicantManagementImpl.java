package org.opencps.api.controller.impl;

import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.ImageCaptchaService;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.activation.DataHandler;
import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.opencps.api.constants.ConstantUtils;
import org.opencps.api.controller.ApplicantManagement;
import org.opencps.api.controller.util.ApplicantUtils;
import org.opencps.api.controller.util.CaptchaServiceSingleton;
import org.opencps.api.controller.util.EmployeeUtils;
import org.opencps.api.controller.util.MessageUtil;
import org.opencps.api.controller.util.NGSPRestClient;
import org.opencps.api.controller.util.PasswordEncrypt;
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
import org.opencps.auth.utils.DLFolderUtil;
import org.opencps.communication.constants.NotificationTemplateTerm;
import org.opencps.communication.model.Notificationtemplate;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.NotificationQueueLocalServiceUtil;
import org.opencps.communication.service.NotificationtemplateLocalServiceUtil;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.opencps.dossiermgt.action.util.ReadFilePropertiesUtils;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.constants.ServerConfigTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.rest.utils.SyncServerTerm;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.kernel.prop.PropValues;
import org.opencps.usermgt.action.ApplicantActions;
import org.opencps.usermgt.action.impl.ApplicantActionsImpl;
import org.opencps.usermgt.constants.ApplicantTerm;
import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.service.ApplicantLocalServiceUtil;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;

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
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

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
					wardCode, wardName, contactName, contactTelNo, StringPool.BLANK,
					input.getPassword());
			_log.info("Success register applicant: " + (applicant != null ? applicant.getApplicantName() + "," + applicant.getContactEmail() : "FAILED"));
			result = ApplicantUtils.mappingToApplicantModel(applicant);

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

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

	private static final String ADMINISTRATIVE_REGION = "ADMINISTRATIVE_REGION";

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

				query.setStart(QueryUtil.ALL_POS);

				query.setEnd(QueryUtil.ALL_POS);

			}

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(Field.KEYWORD_SEARCH, query.getKeywords());
			params.put(ApplicantTerm.APPLICANTIDTYPE, query.getType());
			params.put(ApplicantTerm.LOCK, query.getLock());
			params.put(ApplicantTerm.APPLICANTIDNO, query.getIdNo());
			params.put(ApplicantTerm.APPLICANTNAME, query.getApplicantName());
			params.put(ApplicantTerm.VERIFICATION, query.getVerification());
			params.put(ApplicantTerm.HAVE_ACCOUNT, query.isHaveAccount());
			
			String querySort = String.format(MessageUtil.getMessage(ConstantUtils.QUERY_SORT), query.getSort());
			
			Sort[] sorts = new Sort[] { SortFactoryUtil.create(querySort, Sort.STRING_TYPE,
					GetterUtil.getBoolean(query.getOrder())) };

			JSONObject jsonData = actions.getApplicants(serviceContext, serviceContext.getUserId(),
					serviceContext.getCompanyId(), groupId, params, sorts, query.getStart(), query.getEnd(),
					serviceContext);

			results.setTotal(jsonData.getInt(ConstantUtils.TOTAL));
			if (jsonData != null && jsonData.getInt(ConstantUtils.TOTAL) > 0) {
				results.getData().addAll(ApplicantUtils.mappingToApplicantResults((List<Document>) jsonData.get(ConstantUtils.DATA)));
			}

			return Response.status(HttpURLConnection.HTTP_OK).entity(results).build();

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

				return Response.status(HttpURLConnection.HTTP_OK).entity(results).build();
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
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			User requestUser = ApplicantUtils.getUser(id);

			boolean isAllowed = false;
			Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, user.getUserId());

			if (auth.hasResource(serviceContext, Applicant.class.getName(), ActionKeys.ADD_ENTRY)) {
				isAllowed = true;
			} else {
				if (Validator.isNull(requestUser)) {
//					throw new NoSuchUserException();
				} else {
					// check userLogin is equal userRequest get detail
					if (requestUser.getUserId() == user.getUserId()) {
						isAllowed = true;
					}
				}
			}
			if (employee != null && requestUser == null) {
				isAllowed = true;
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
			String profile = input.getProfile();

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
						wardName, contactName, contactTelNo, contactEmail, profile);

				results = ApplicantUtils.mappingToApplicantModel(applicant);

				return Response.status(HttpURLConnection.HTTP_OK).entity(results).build();
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

				return Response.status(HttpURLConnection.HTTP_OK).entity(results).build();
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
				applicant = actions.getApplicantDetail(serviceContext, id);

				JSONObject result = JSONFactoryUtil.createJSONObject(applicant.getProfile());

				return Response.status(HttpURLConnection.HTTP_OK).entity(JSONFactoryUtil.looseSerialize(result)).build();
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
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
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
				applicant = actions.updateProfile(serviceContext, groupId, id, input.getValue());

				JSONObject result = JSONFactoryUtil.createJSONObject(applicant.getProfile());

				return Response.status(HttpURLConnection.HTTP_OK).entity(JSONFactoryUtil.looseSerialize(result)).build();
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
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
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

				actions.updateProfile(serviceContext, groupId, id, profile.toString());

				JSONObject result = JSONFactoryUtil.createJSONObject();

				result.put(ConstantUtils.KEY, key);
				result.put(ConstantUtils.VALUE, input.getValue());

				return Response.status(HttpURLConnection.HTTP_OK).entity(JSONFactoryUtil.looseSerialize(result)).build();
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
				if (r.getName().startsWith(ReadFilePropertiesUtils.get(ConstantUtils.ROLE_ADMIN))) {
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

				return Response.status(HttpURLConnection.HTTP_OK).entity(results).build();

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
			
			resultObj.put(ConstantUtils.MAIL_AD, applicant.getContactEmail());
			resultObj.put(ConstantUtils.TOKEN, applicant.getTmpPass());

			return Response.status(HttpURLConnection.HTTP_OK).entity(JSONFactoryUtil.looseSerialize(resultObj)).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}
		
	private String getDefaultTokenUrl() {
		return Validator.isNotNull(PropsUtil.get(ConstantUtils.NGSP_TOKEN_URL_KEY)) ? PropsUtil.get(ConstantUtils.NGSP_TOKEN_URL_KEY) : StringPool.BLANK; 
	}
	
	private String getDefaultConsumerKey() {
		return Validator.isNotNull(PropsUtil.get(ConstantUtils.NGSP_CONSUMER_KEY)) ? PropsUtil.get(ConstantUtils.NGSP_CONSUMER_KEY) : StringPool.BLANK; 
	}

	private String getDefaultSecretKey() {
		return Validator.isNotNull(PropsUtil.get(ConstantUtils.NGSP_SECRET_KEY)) ? PropsUtil.get(ConstantUtils.NGSP_SECRET_KEY) : StringPool.BLANK; 
	}

	private MToken getToken(NGSPRestClient client) throws Exception {
		String tokenUrl = getDefaultTokenUrl();
		String consumer_key = getDefaultConsumerKey();
		String secret_key = getDefaultSecretKey();

		if (client != null) {
			tokenUrl = client.getBaseUrl();
			consumer_key = client.getConsumerKey();
			secret_key = client.getConsumerSecret();
		}
		MToken token = IToken.getToken(tokenUrl, consumer_key, secret_key);
		
		return token;
	}
	
	private String CTDN_NGSP_URL = "https://api.ngsp.gov.vn/apiCSDLDKDN/1.0/chiTietDoanhNghiep";
	private String CTDN_TOKEN = "cd21bef3-e484-3ce9-9045-84c240e9803b";
	
	@Override
	public Response ngspGetApplicantInfo(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String applicantIdNo) {
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		String apiUrl = CTDN_NGSP_URL;
		String access_token = CTDN_TOKEN;
		
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
			
			return Response.status(HttpURLConnection.HTTP_OK).entity(result.toJSONString()).build();			
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}
	
	@Override
	public Response verifyApplicantInfo(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String applicantIdNo, String applicantName,
			String contactName) {
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		String apiUrl = CTDN_NGSP_URL;
		String access_token = CTDN_TOKEN;
		
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
			JSONObject data = result.getJSONObject(ConstantUtils.NSGP_JSON_DATA);
			JSONObject returnObj = JSONFactoryUtil.createJSONObject();
			LevenshteinDistance levensh = LevenshteinDistance.getDefaultInstance();
			
			if (Validator.isNull(data.getJSONObject(ConstantUtils.NGSP_JSON_MAIN_INFORMATION))) {
				returnObj.put(ConstantUtils.API_JSON_ERROR, true);
				returnObj.put(ConstantUtils.API_JSON_MESSAGE, MessageUtil.getMessage(ConstantUtils.NGSP_CTDN_MESSAGE_NOT_FOUND));
				return Response.status(HttpURLConnection.HTTP_OK).entity(returnObj.toJSONString()).build();							
			}
			else {
				JSONObject mainInfoObj = data.getJSONObject(ConstantUtils.NGSP_JSON_MAIN_INFORMATION);
				if (Validator.isNotNull(mainInfoObj.getString(ConstantUtils.NGSP_JSON_MAIN_INFORMATION_NAME))) {
					if (Validator.isNotNull(applicantName)) {
						if (mainInfoObj.has(ConstantUtils.NGSP_JSON_MAIN_INFORMATION_NAME)) {
							int countChar = applicantName.replaceAll(ConstantUtils.SPACE_PATTERN, StringPool.BLANK).length();
							
							int distance = levensh.apply(applicantName, mainInfoObj.getString(ConstantUtils.NGSP_JSON_MAIN_INFORMATION_NAME));
							if (distance > (1.0 * countChar / 2)) {
								returnObj.put(ConstantUtils.API_JSON_WARNING, true);
								returnObj.put(ConstantUtils.API_JSON_MESSAGE, MessageUtil.getMessage(ConstantUtils.NGSP_CTDN_MESSAGE_NOT_CORRECT));															
							}
						}
					}
				}
				JSONObject representativesObj = data.getJSONObject(ConstantUtils.NGSP_JSON__REPRESENTATIVES);
				if (representativesObj != null) {
					if (Validator.isNotNull(contactName) && !contactName.equals(representativesObj.getString(ConstantUtils.NGSP_JSON__REPRESENTATIVES_FULLNAME))) {
						if (representativesObj.has(ConstantUtils.NGSP_JSON__REPRESENTATIVES_FULLNAME)) {
							int countChar = contactName.replaceAll(ConstantUtils.SPACE_PATTERN, StringPool.BLANK).length();
							
							int distance = levensh.apply(contactName, mainInfoObj.getString(ConstantUtils.NGSP_JSON__REPRESENTATIVES_FULLNAME));
							if (distance > (1.0 * countChar / 2)) {
								returnObj.put(ConstantUtils.API_JSON_WARNING, true);
								returnObj.put(ConstantUtils.API_JSON_MESSAGE, (Validator.isNotNull(returnObj.getString(ConstantUtils.API_JSON_MESSAGE)) ? returnObj.getString(ConstantUtils.API_JSON_MESSAGE) + StringPool.COMMA : StringPool.BLANK) + MessageUtil.getMessage(ConstantUtils.NGSP_CTDN_MESSAGE_REPRESENTATIVES_NOT_CORRECT));
							}
						}
					}					
				}
				return Response.status(HttpURLConnection.HTTP_OK).entity(returnObj.toJSONString()).build();							
			}
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getJCaptcha(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, Integer width, Integer height) {

                String captchaType = PropValues.CAPTCHA_TYPE;

		File destDir = new File(ConstantUtils.JCAPTCHA_DIR);

		if (!destDir.exists()) {
			destDir.mkdir();
		}

		File file = null;
		try {
            if (Validator.isNotNull(captchaType) && "jcaptcha".contentEquals(captchaType)) {
			ImageCaptchaService instance = CaptchaServiceSingleton.getInstance();
			
		    String captchaId = request.getSession().getId();
//			File destDir = new File(ConstantUtils.JCAPTCHA_DIR);
//			if (!destDir.exists()) {
//				destDir.mkdir();
//			}
			String captchafileName = String.format(MessageUtil.getMessage(ConstantUtils.JCAPTCHA_FILENAME), captchaId);
			
//			File file = new File(captchafileName);
			file = new File(captchafileName);
			if (!file.exists()) {
				file.createNewFile();				
			}
	
			if (file.exists()) {
			    BufferedImage challengeImage = instance.getImageChallengeForID(
			    captchaId, Locale.US );
			    try {
					ImageIO.write( challengeImage, ConstantUtils.PNG, file );
					ResponseBuilder responseBuilder = Response.ok((Object) file);
					String fileName = String.format(MessageUtil.getMessage(ConstantUtils.ATTACHMENT_FILENAME), file.getName());
					
					responseBuilder.header(ConstantUtils.CONTENT_DISPOSITION,
							fileName);
					responseBuilder.header(HttpHeaders.CONTENT_TYPE, ConstantUtils.MEDIA_TYPE_PNG);

					return responseBuilder.build();
				    
				} catch (IOException e) {
					_log.debug(e);
				}
			}
			} else {

				String fileName = System.currentTimeMillis() + ".png";

				file = new File("jcaptcha/" + fileName);
				if (!file.exists()) {
					file.createNewFile();
				}
				ApplicantActionsImpl actionsImpl = new ApplicantActionsImpl();

				String base64Image = actionsImpl.getSimpleCaptcha(request, header, company, locale, user,
						serviceContext, width, height);

				try (FileOutputStream fos = new FileOutputStream(file);) {

					byte[] decoder = Base64.getDecoder().decode(base64Image);

					fos.write(decoder);
					fos.close();
				} catch (Exception e) {
					_log.error(e);
				}
			}

			if (file != null && file.exists()) {

				ResponseBuilder responseBuilder = Response.ok((Object) file);

				responseBuilder.header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
				responseBuilder.header("Content-Type", "image/png");

				return responseBuilder.build();
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

		String captchaType = PropValues.CAPTCHA_TYPE;
		ApplicantModel result = new ApplicantModel();
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		
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
				if (r.getName().startsWith(ConstantUtils.ROLE_ADMIN)) {
					isAdmin = true;
					break;
				}
			}
			if (isAdmin) {
				
			}
			else {
//				ImageCaptchaService instance = CaptchaServiceSingleton.getInstance();
//				String captchaId = request.getSession().getId();
//				try {
//					_log.info("Captcha: " + captchaId + "," + jCaptchaResponse);
//					boolean isResponseCorrect = instance.validateResponseForID(captchaId, jCaptchaResponse);
//					_log.info("Check captcha result: " + isResponseCorrect);
//					if (!isResponseCorrect) {
//						ErrorMsgModel error = new ErrorMsgModel();
//						error.setMessage(MessageUtil.getMessage(ConstantUtils.JCAPTCHA_MESSAGE_NOTCORRECT));
//						error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
//						error.setDescription(MessageUtil.getMessage(ConstantUtils.JCAPTCHA_DESCRIPTION_NOTCORRECT));
//
//						return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
//					}
//				} catch (CaptchaServiceException e) {
//					_log.debug(e);
//					ErrorMsgModel error = new ErrorMsgModel();
//					error.setMessage(MessageUtil.getMessage(ConstantUtils.JCAPTCHA_MESSAGE_NOTCORRECT));
//					error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
//					error.setDescription(MessageUtil.getMessage(ConstantUtils.JCAPTCHA_DESCRIPTION_NOTCORRECT));
//
//					return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
//				}
				if (Validator.isNotNull(captchaType) && "jcaptcha".contentEquals(captchaType)) {
					ImageCaptchaService instance = CaptchaServiceSingleton.getInstance();
					String captchaId = request.getSession().getId();
					try {
						_log.info("Captcha: " + captchaId + "," + jCaptchaResponse);
						boolean isResponseCorrect = instance.validateResponseForID(captchaId, jCaptchaResponse);
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
				} else {
					ApplicantActionsImpl actionsImpl = new ApplicantActionsImpl();
					boolean isValid = actionsImpl.validateSimpleCaptcha(request, header, company, locale, user,
							serviceContext, jCaptchaResponse);

					if (!isValid) {
						ErrorMsgModel error = new ErrorMsgModel();
						error.setMessage("Captcha incorrect");
						error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
						error.setDescription("Captcha incorrect");

						return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
					}
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
					wardCode, wardName, contactName, contactTelNo, StringPool.BLANK,
					input.getPassword());
			_log.info("Success register applicant: " + (applicant != null ? applicant.getApplicantName() + "," + applicant.getContactEmail() : "FAILED"));
			result = ApplicantUtils.mappingToApplicantModel(applicant);

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response registerWithIndentify(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String applicantName,
			String applicantIdType, String applicantIdNo, String applicantIdDate, String contactTelNo,
			String contactEmail, String password, String jCaptchaResponse,
			Attachment indentifyNoFile) {

		ApplicantActions actions = new ApplicantActionsImpl();
		String captchaType = PropValues.CAPTCHA_TYPE;
		ApplicantModel result = new ApplicantModel();
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		backend.auth.api.BackendAuth auth2 = new backend.auth.api.BackendAuthImpl();

		try {

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

			} else {
				if (Validator.isNotNull(captchaType) && "jcaptcha".equals(captchaType)) {
					ImageCaptchaService instance = CaptchaServiceSingleton.getInstance();
					String captchaId = request.getSession().getId();
					try {
						_log.info("Captcha: " + captchaId + "," + jCaptchaResponse);
						boolean isResponseCorrect = instance.validateResponseForID(captchaId, jCaptchaResponse);
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
				} else {
					ApplicantActionsImpl actionsImpl = new ApplicantActionsImpl();
					boolean isValid = actionsImpl.validateSimpleCaptcha(request, header, company, locale, user,
							serviceContext, jCaptchaResponse);

					if (!isValid) {
						ErrorMsgModel error = new ErrorMsgModel();
						error.setMessage("Captcha incorrect");
						error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
						error.setDescription("Captcha incorrect");

						return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
					}
				}

			}

			String profile = StringPool.BLANK;
			if (Validator.isNotNull(indentifyNoFile)) {
				
				JSONObject profileJson = JSONFactoryUtil.createJSONObject();
				serviceContext.setAddGroupPermissions(true);
				serviceContext.setAddGuestPermissions(true);
				
				DataHandler dataHandler = indentifyNoFile.getDataHandler();
				String fileType = MimeTypesUtil.getContentType(dataHandler.getName());
				InputStream inputStream = dataHandler.getInputStream();
				int fileSize = inputStream.available();
				String title = new Date().getTime() + dataHandler.getName();
				String destination = ApplicantTerm.INDENTIFY_DESTINATION + StringPool.SLASH + applicantIdNo;

				DLFolder dlFolder = DLFolderUtil.getTargetFolder(user.getUserId(), groupId, groupId, false, 0, destination,
						StringPool.BLANK, false, serviceContext);
				FileEntry fileEntry = DLAppLocalServiceUtil.addFileEntry(user.getUserId(), groupId, dlFolder.getFolderId(), title,
						fileType, title, title,
						StringPool.BLANK, inputStream, fileSize, serviceContext);

				profileJson.put(ApplicantTerm.INDENTIFY_NO, fileEntry.getFileEntryId());
				profileJson.put(ApplicantTerm.INDENTIFY_NO_URL, ApplicantTerm.DOC_URL + fileEntry.getGroupId() + StringPool.FORWARD_SLASH + fileEntry.getFolderId() + StringPool.FORWARD_SLASH + fileEntry.getTitle());
				profile = profileJson.toString();
			}
			Applicant applicant = actions.register(serviceContext, groupId, applicantName, applicantIdType,
					applicantIdNo, applicantIdDate, contactEmail, StringPool.BLANK, StringPool.BLANK, StringPool.BLANK, StringPool.BLANK,
					StringPool.BLANK, StringPool.BLANK, StringPool.BLANK, StringPool.BLANK, contactTelNo, profile, password);
			_log.info("Success register applicant: "
					+ (applicant != null ? applicant.getApplicantName() + "," + applicant.getContactEmail()
							: "FAILED"));
			result = ApplicantUtils.mappingToApplicantModel(applicant);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response updateIndentifies(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String applicantId,
			Attachment indentifyNoFile) {
		ApplicantActions actions = new ApplicantActionsImpl();

		ApplicantModel result = new ApplicantModel();

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		try {

			Long id = Long.parseLong(applicantId);
			Applicant applc = ApplicantLocalServiceUtil.getApplicant(id);
			JSONObject profileJson = JSONFactoryUtil.createJSONObject(applc.getProfile());
			String destination = ApplicantTerm.INDENTIFY_DESTINATION + StringPool.SLASH + applc.getApplicantIdNo();
			DLFolder dlFolder = DLFolderUtil.getTargetFolder(user.getUserId(), groupId, groupId, false, 0, destination,
					StringPool.BLANK, false, serviceContext);

			if (Validator.isNotNull(indentifyNoFile)) {

				serviceContext.setAddGroupPermissions(true);
				serviceContext.setAddGuestPermissions(true);
				
				DataHandler dataHandler = indentifyNoFile.getDataHandler();
				String fileType = MimeTypesUtil.getContentType(dataHandler.getName());
				InputStream inputStream = dataHandler.getInputStream();
				int fileSize = inputStream.available();
				String title = new Date().getTime() + dataHandler.getName();
				FileEntry fileEntry = DLAppLocalServiceUtil.addFileEntry(user.getUserId(), groupId, dlFolder.getFolderId(), title,
						fileType, title, title,
						StringPool.BLANK, inputStream, fileSize, serviceContext);

				profileJson.put(ApplicantTerm.INDENTIFY_NO, fileEntry.getFileEntryId());
				profileJson.put(ApplicantTerm.INDENTIFY_NO_URL, ApplicantTerm.DOC_URL + fileEntry.getGroupId() + StringPool.FORWARD_SLASH + fileEntry.getFolderId() + StringPool.FORWARD_SLASH + fileEntry.getTitle());
			}
			Applicant applicant = actions.updateProfile(serviceContext, groupId, id, profileJson.toString());
			result = ApplicantUtils.mappingToApplicantModel(applicant);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			e.printStackTrace();
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response createApplicantAccount(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id, EmployeeAccountInputModel input) {

		ApplicantActions actions = new ApplicantActionsImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		EmployeeAccountModel employeeAccountModel = new EmployeeAccountModel();

		try {

			JSONObject jsonObject = actions.createApplicantAccount(user.getUserId(), company.getCompanyId(), groupId, id,
					input.getScreenName(), input.getEmail(), input.isExist(), serviceContext);

			employeeAccountModel = EmployeeUtils.mapperEmployeeAccountModel(jsonObject);

			if (Validator.isNotNull(jsonObject.getString(ConstantUtils.EMPLOYEE_VALID_JSON_DUPLICATE))
					&& jsonObject.getString(ConstantUtils.EMPLOYEE_VALID_JSON_DUPLICATE).equals(Boolean.TRUE.toString())) {

				return Response.status(409).entity(employeeAccountModel).build();

			} else {

				return Response.status(HttpURLConnection.HTTP_OK).entity(employeeAccountModel).build();

			}

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response resolveConflict(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, Boolean reindex) {
		BackendAuth auth = new BackendAuthImpl();
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put(Field.GROUP_ID, String.valueOf(groupId));
			
			Sort[] sorts = new Sort[] { SortFactoryUtil.create(ConstantUtils.ES_MODIFIED_SORTABLE, Sort.STRING_TYPE,
					true) };

			Hits hits = null;
			SearchContext searchContext = new SearchContext();
			searchContext.setCompanyId(serviceContext.getCompanyId());

			try {

				hits = ApplicantLocalServiceUtil.searchLucene(params, sorts, QueryUtil.ALL_POS, QueryUtil.ALL_POS, searchContext);

				List<Document> lstDocs = hits.toList();
				Indexer<Applicant> indexer =
						IndexerRegistryUtil.nullSafeGetIndexer(Applicant.class);
				for (Document document : lstDocs) {
					long dossierId =
						GetterUtil.getLong(document.get(DossierTerm.DOSSIER_ID));
					long companyId =
						GetterUtil.getLong(document.get(Field.COMPANY_ID));
					String uid = document.get(Field.UID);
					if (reindex) {
						indexer.delete(companyId, uid);
					}
					else {
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
				
				List<Applicant> lstApps = ApplicantLocalServiceUtil.getApplicants(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
				for (Applicant applicant : lstApps) {
					indexer.reindex(applicant);
				}
			} catch (Exception e) {
				_log.error(e);
			}


			return Response.status(HttpURLConnection.HTTP_OK).entity(ConstantUtils.API_JSON_EMPTY).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response verifyApplicant(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id) {
		ApplicantActions actions = new ApplicantActionsImpl();
		BackendAuth auth = new BackendAuthImpl();
		ApplicantModel results = new ApplicantModel();

		Applicant applicant = null;
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			applicant = actions.verifyApplicant(id);

			results = ApplicantUtils.mappingToApplicantModel(applicant);

			return Response.status(HttpURLConnection.HTTP_OK).entity(results).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getSimpleCaptcha(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, Integer width, Integer height) {
		try {

			ApplicantActionsImpl actionsImpl = new ApplicantActionsImpl();

			String imageData = actionsImpl.getSimpleCaptcha(request, header, company, locale, user, serviceContext,
					width, height);

			return Response.status(200).entity(imageData).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response validateSimpleCaptcha(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, ApplicantInputModel input, String value) {

		try {

			ApplicantActionsImpl actionsImpl = new ApplicantActionsImpl();

			boolean isValid = actionsImpl.validateSimpleCaptcha(request, header, company, locale, user, serviceContext,
					value);

			return Response.status(200).entity(String.valueOf(isValid)).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response updateEmail(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String oldEmail, String newEmail) {
		try {

			ApplicantActionsImpl actionsImpl = new ApplicantActionsImpl();

			JSONObject object = actionsImpl.updateAccountEmail(request, header, company, locale, user, serviceContext, oldEmail, newEmail);

			return Response.status(200).entity(object.toJSONString()).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response activeApplicant(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		try {
			System.out.println("==========GOOO==========="+id);
			List<Role> userRoles = user.getRoles();
			boolean isAdmin = false;
			for (Role r : userRoles) {
				if (r.getName().startsWith("Administrator")) {
					isAdmin = true;
					break;
				}
			}
			System.out.println("==========admin==========="+isAdmin);
			if (isAdmin) {

				Applicant applicant = ApplicantLocalServiceUtil.activateApplicant(id, serviceContext);

				ApplicantModel result = ApplicantUtils.mappingToApplicantModel(applicant);
				
				Notificationtemplate notiTemplate = NotificationtemplateLocalServiceUtil.fetchByF_NotificationtemplateByType(groupId, NotificationTemplateTerm.APLC_05);

				if (Validator.isNotNull(notiTemplate)) {
					Date now = new Date();
			        Calendar cal = Calendar.getInstance();
			        cal.setTime(now);
			        if ("minutely".equals(notiTemplate.getInterval())) {
				        cal.add(Calendar.MINUTE, notiTemplate.getExpireDuration());					
					}
					else if ("hourly".equals(notiTemplate.getInterval())) {
				        cal.add(Calendar.HOUR, notiTemplate.getExpireDuration());										
					}
					else {
				        cal.add(Calendar.MINUTE, notiTemplate.getExpireDuration());										
					}
					Date expired = cal.getTime();
					JSONObject payloadObj = JSONFactoryUtil.createJSONObject();
					payloadObj.put(
							"Applicant", JSONFactoryUtil.createJSONObject(
								JSONFactoryUtil.looseSerialize(applicant)));
					NotificationQueueLocalServiceUtil.addNotificationQueue(
							user.getUserId(), groupId, 
							notiTemplate.getNotificationType(), 
							Applicant.class.getName(), 
							String.valueOf(applicant.getApplicantId()), 
							payloadObj.toJSONString(), 
							user.getFullName(), 
							applicant.getApplicantName(), 
							applicant.getMappingUserId(), 
							applicant.getContactEmail(), 
							applicant.getContactTelNo(), 
							now, 
							expired, 
							serviceContext);
				}
				return Response.status(200).entity(result).build();
			} else {

				throw new Exception("Permission denied");
			}

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response registerLGSPWithCaptcha(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, ApplicantInputModel input,
			String jCaptchaResponse) {
		ApplicantActions actions = new ApplicantActionsImpl();

		String captchaType = PropValues.CAPTCHA_TYPE;
		ApplicantModel result = new ApplicantModel();
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		
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
				if (r.getName().startsWith(ConstantUtils.ROLE_ADMIN)) {
					isAdmin = true;
					break;
				}
			}
			if (isAdmin) {
				
			}
			else {
				if (Validator.isNotNull(captchaType) && "jcaptcha".contentEquals(captchaType)) {
					ImageCaptchaService instance = CaptchaServiceSingleton.getInstance();
					String captchaId = request.getSession().getId();
					try {
						_log.info("Captcha: " + captchaId + "," + jCaptchaResponse);
						boolean isResponseCorrect = instance.validateResponseForID(captchaId, jCaptchaResponse);
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
				} else {
					ApplicantActionsImpl actionsImpl = new ApplicantActionsImpl();
					boolean isValid = actionsImpl.validateSimpleCaptcha(request, header, company, locale, user,
							serviceContext, jCaptchaResponse);

					if (!isValid) {
						ErrorMsgModel error = new ErrorMsgModel();
						error.setMessage("Captcha incorrect");
						error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
						error.setDescription("Captcha incorrect");

						return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
					}
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

			//
			// Create a trust manager that does not validate certificate chains
			TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
				public void checkClientTrusted(X509Certificate[] certs, String authType) {
				}
				public void checkServerTrusted(X509Certificate[] certs, String authType) {
				}
			} };
			// Install the all-trusting trust manager
			try {
				SSLContext sc = SSLContext.getInstance("SSL");
				sc.init(null, trustAllCerts, new SecureRandom());
				HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			} catch (Exception e) {
			}

			String clientId = "yPTWeD3FuqgYku7SvNJ5VuylmY0a";
			String clientSecret = "lEti1opjJtLecmHcdGf9ogat0SUa";
			String grantType = "client_credentials";
			String endPoitBaseUrl = "https://lgsp.dongthap.gov.vn/taikhoan/1.0.0";
			String strProfile = StringPool.BLANK;
			try {
				/** Get Token */
				String urlToken = "https://lgsp.dongthap.gov.vn/token";

				StringBuilder sbToken = new StringBuilder();
				URL urlVal = new URL(urlToken);
				StringBuilder postData = new StringBuilder();
				//
				postData.append("client_id");
				postData.append(StringPool.EQUAL);
				postData.append(clientId);
				//
				postData.append(StringPool.AMPERSAND);
				postData.append("client_secret");
				postData.append(StringPool.EQUAL);
				postData.append(clientSecret);
				//
				postData.append(StringPool.AMPERSAND);
				postData.append("grant_type");
				postData.append(StringPool.EQUAL);
				postData.append(grantType);

				java.net.HttpURLConnection conToken = (java.net.HttpURLConnection) urlVal.openConnection();
				conToken.setRequestMethod("POST");
				conToken.setRequestProperty(HttpHeaders.ACCEPT, ConstantUtils.CONTENT_TYPE_JSON);
				conToken.setRequestProperty(HttpHeaders.CONTENT_TYPE, ConstantUtils.CONTENT_TYPE_XXX_FORM_URLENCODED);
				conToken.setRequestProperty(ConstantUtils.CONTENT_LENGTH,
						StringPool.BLANK + Integer.toString(postData.toString().getBytes().length));

				conToken.setUseCaches(false);
				conToken.setDoInput(true);
				conToken.setDoOutput(true);
				_log.debug("POST DATA: " + postData.toString());
				OutputStream osToken = conToken.getOutputStream();
				osToken.write(postData.toString().getBytes());
				osToken.close();

				BufferedReader brfToken = new BufferedReader(new InputStreamReader(conToken.getInputStream()));

				int cpToken;
				while ((cpToken = brfToken.read()) != -1) {
					sbToken.append((char) cpToken);
				}
				_log.debug("RESULT PROXY: " + sbToken.toString());
				if (Validator.isNotNull(sbToken.toString())) {
					JSONObject jsonToken = JSONFactoryUtil.createJSONObject(sbToken.toString());
					//
					if (jsonToken.has("access_token") && jsonToken.has("token_type")
							&& Validator.isNotNull(jsonToken.getString("access_token"))
							&& Validator.isNotNull(jsonToken.getString("token_type"))) {
						String accessToken = jsonToken.getString("access_token");
						String tokenType = jsonToken.getString("token_type");
						
						_log.info("accessToken: " + accessToken);
						_log.info("tokenType: " + tokenType);

						// Dang ky tk cong dan
						if ("citizen".equalsIgnoreCase(applicantIdType)) {
							//
							String urlRegister = endPoitBaseUrl + "/congdan/dangky";
							String authStrEnc = tokenType + StringPool.SPACE + accessToken;

							StringBuilder sbReg = new StringBuilder();
							try {
								URL urlValRegister = new URL(urlRegister);

								StringBuilder postDataReg = new StringBuilder();

								JSONObject jsonBody = JSONFactoryUtil.createJSONObject();
								jsonBody.put("email", contactEmail);
								jsonBody.put("soCMND", applicantIdNo);
								jsonBody.put("ngaySinh", "1990-12-01");
								jsonBody.put("gioiTinh", 0);
								jsonBody.put("moTaDiaChiThuongTru", "");
								jsonBody.put("tinhThuongTruId", 0);
								jsonBody.put("huyenThuongTruId", 0);
								jsonBody.put("xaThuongTruId", 0);
								jsonBody.put("dienThoai", contactTelNo);
								jsonBody.put("ho", "Trần");
								jsonBody.put("dem", "Văn");
								jsonBody.put("ten", applicantName);
								
								
//								postDataReg.append("email");
//								postDataReg.append(StringPool.EQUAL);
//								postDataReg.append(contactEmail);
//								postDataReg.append(StringPool.AMPERSAND);
//								postDataReg.append("soCMND");
//								postDataReg.append(StringPool.EQUAL);
//								postDataReg.append(applicantIdNo);
//								postDataReg.append(StringPool.AMPERSAND);
//								postDataReg.append("ngaySinh");
//								postDataReg.append(StringPool.EQUAL);
//								postDataReg.append("1990-12-01");
//								postDataReg.append(StringPool.AMPERSAND);
//								postDataReg.append("gioiTinh");
//								postDataReg.append(StringPool.EQUAL);
//								postDataReg.append(0);
//								postDataReg.append(StringPool.AMPERSAND);
//								postDataReg.append("moTaDiaChiThuongTru");
//								postDataReg.append(StringPool.EQUAL);
//								postDataReg.append("");
//								//
//								postDataReg.append(StringPool.AMPERSAND);
//								postDataReg.append("tinhThuongTruId");
//								postDataReg.append(StringPool.EQUAL);
//								postDataReg.append(0);
//								//
//								postDataReg.append(StringPool.AMPERSAND);
//								postDataReg.append("huyenThuongTruId");
//								postDataReg.append(StringPool.EQUAL);
//								postDataReg.append(0);
//								//
//								postDataReg.append(StringPool.AMPERSAND);
//								postDataReg.append("xaThuongTruId");
//								postDataReg.append(StringPool.EQUAL);
//								postDataReg.append(0);
//								//
//								postDataReg.append(StringPool.AMPERSAND);
//								postDataReg.append("dienThoai");
//								postDataReg.append(StringPool.EQUAL);
//								postDataReg.append(contactTelNo);
//								//
//								postDataReg.append(StringPool.AMPERSAND);
//								postDataReg.append("ho");
//								postDataReg.append(StringPool.EQUAL);
//								postDataReg.append("");
//								//
//								postDataReg.append(StringPool.AMPERSAND);
//								postDataReg.append("dem");
//								postDataReg.append(StringPool.EQUAL);
//								postDataReg.append("");
//								//
//								postDataReg.append(StringPool.AMPERSAND);
//								postDataReg.append("ten");
//								postDataReg.append(StringPool.EQUAL);
//								postDataReg.append(applicantName);

								java.net.HttpURLConnection conReg = (java.net.HttpURLConnection) urlValRegister
										.openConnection();
								conReg.setRequestMethod("POST");
								conReg.setRequestProperty(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
								conReg.setRequestProperty(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");
								conReg.setRequestProperty(HttpHeaders.AUTHORIZATION, authStrEnc);
								_log.debug("BASIC AUTHEN: " + authStrEnc);
								conReg.setRequestProperty(ConstantUtils.CONTENT_LENGTH,
										StringPool.BLANK + Integer.toString(jsonBody.toString().getBytes().length));

								conReg.setUseCaches(false);
								conReg.setDoInput(true);
								conReg.setDoOutput(true);
								_log.debug("POST DATA: " + jsonBody.toString());
								OutputStream osReg = conReg.getOutputStream();
								osReg.write(jsonBody.toString().getBytes());
								osReg.close();

								BufferedReader brfReg = new BufferedReader(
										new InputStreamReader(conReg.getInputStream()));

								int cpReg;
								while ((cpReg = brfReg.read()) != -1) {
									sbReg.append((char) cpReg);
								}
								_log.info("RESULT PROXY: " + sbReg.toString());
								//
								if (Validator.isNotNull(sbReg.toString())) {
									//
									_log.error("sbReg:"+ sbReg.toString());
									JSONObject jsonReg = JSONFactoryUtil.createJSONObject(sbReg.toString());
									if (jsonReg.has("message") && jsonReg.has("error_code")
											&& Validator.isNotNull(jsonReg.get("message"))
											&& Validator.isNotNull(jsonReg.get("error_code"))) {
										String message = jsonReg.getString("message");
										int errorCode = jsonReg.getInt("error_code");
										
										if (errorCode == 0) {
											JSONObject jsonMessage = JSONFactoryUtil.createJSONObject(message);
											//
											String maXacNhan = jsonMessage.getString("maXacNhan");
											String matKhau = jsonMessage.getString("matKhau");
											String taiKhoan = jsonMessage.getString("taiKhoan");
											// Them 1 cot luu pass vs activeCode vào profile
											JSONObject jsonProfile = JSONFactoryUtil.createJSONObject();
											jsonProfile.put("maXacNhan", maXacNhan);
											jsonProfile.put("matKhau", matKhau);
											jsonProfile.put("taiKhoan", taiKhoan);
											//
											_log.error("maXacNhan:"+ maXacNhan);
											_log.error("matKhau:"+ matKhau);
											strProfile = jsonProfile.toJSONString();
										}
									}
								}
							} catch (IOException e) {
								_log.error(e);
								_log.debug("Something went wrong while reading/writing in stream!!");
							}
						}
					}
				}
			} catch (IOException e) {
				_log.error(e);
				_log.debug("Something went wrong while reading/writing in stream!!");
			}


			Applicant applicant = actions.register(serviceContext, groupId, applicantName, applicantIdType,
					applicantIdNo, input.getApplicantIdDate(), contactEmail, address,
					cityCode, cityName, districtCode, districtName,
					wardCode, wardName, contactName, contactTelNo, strProfile,
					input.getPassword());
			_log.info("Success register applicant: " + (applicant != null ? applicant.getApplicantName() + "," + applicant.getContactEmail() : "FAILED"));
			result = ApplicantUtils.mappingToApplicantModel(applicant);

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response activateLGSPApplicant(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, String code) {
		ApplicantActions actions = new ApplicantActionsImpl();

		long applicantId = 0;
		Applicant aplc = ApplicantLocalServiceUtil.fetchApplicant(id);

		if (aplc != null) {
			applicantId = id;
		} else {
			aplc = ApplicantLocalServiceUtil.fetchByMappingID(id);
			if (aplc != null) {
				applicantId = aplc.getApplicantId();
			}
		}

		//Active LGSP and change pass
		if (aplc != null) {
			// Create a trust manager that does not validate certificate chains
			TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
				public void checkClientTrusted(X509Certificate[] certs, String authType) {
				}
				public void checkServerTrusted(X509Certificate[] certs, String authType) {
				}
			} };
			// Install the all-trusting trust manager
			try {
				SSLContext sc = SSLContext.getInstance("SSL");
				sc.init(null, trustAllCerts, new SecureRandom());
				HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			} catch (Exception e) {
			}
	
			String clientId = "yPTWeD3FuqgYku7SvNJ5VuylmY0a";
			String clientSecret = "lEti1opjJtLecmHcdGf9ogat0SUa";
			String grantType = "client_credentials";
			String endPoitBaseUrl = "https://lgsp.dongthap.gov.vn/taikhoan/1.0.0";
			try {
				/** Get Token */
				String urlToken = "https://lgsp.dongthap.gov.vn/token";
	
				StringBuilder sbToken = new StringBuilder();
				URL urlVal = new URL(urlToken);
				StringBuilder postData = new StringBuilder();
				//
				postData.append("client_id");
				postData.append(StringPool.EQUAL);
				postData.append(clientId);
				//
				postData.append(StringPool.AMPERSAND);
				postData.append("client_secret");
				postData.append(StringPool.EQUAL);
				postData.append(clientSecret);
				//
				postData.append(StringPool.AMPERSAND);
				postData.append("grant_type");
				postData.append(StringPool.EQUAL);
				postData.append(grantType);
	
				java.net.HttpURLConnection conToken = (java.net.HttpURLConnection) urlVal.openConnection();
				conToken.setRequestMethod("POST");
				conToken.setRequestProperty(HttpHeaders.ACCEPT, ConstantUtils.CONTENT_TYPE_JSON);
				conToken.setRequestProperty(HttpHeaders.CONTENT_TYPE, ConstantUtils.CONTENT_TYPE_XXX_FORM_URLENCODED);
				conToken.setRequestProperty(ConstantUtils.CONTENT_LENGTH,
						StringPool.BLANK + Integer.toString(postData.toString().getBytes().length));
	
				conToken.setUseCaches(false);
				conToken.setDoInput(true);
				conToken.setDoOutput(true);
				_log.debug("POST DATA: " + postData.toString());
				OutputStream osToken = conToken.getOutputStream();
				osToken.write(postData.toString().getBytes());
				osToken.close();
	
				BufferedReader brfToken = new BufferedReader(new InputStreamReader(conToken.getInputStream()));
	
				int cpToken;
				while ((cpToken = brfToken.read()) != -1) {
					sbToken.append((char) cpToken);
				}
				_log.debug("RESULT PROXY: " + sbToken.toString());
				if (Validator.isNotNull(sbToken.toString())) {
					JSONObject jsonToken = JSONFactoryUtil.createJSONObject(sbToken.toString());
					//
					if (jsonToken.has("access_token") && jsonToken.has("token_type")
							&& Validator.isNotNull(jsonToken.getString("access_token"))
							&& Validator.isNotNull(jsonToken.getString("token_type"))) {
						String accessToken = jsonToken.getString("access_token");
						String tokenType = jsonToken.getString("token_type");
						
						_log.info("accessToken: " + accessToken);
						_log.info("tokenType: " + tokenType);
	
						// Dang ky tk cong dan
						String profile = aplc.getProfile();
						String maXacNhan = StringPool.BLANK;
						String matKhau = StringPool.BLANK;
						if (Validator.isNotNull(profile)) {
							JSONObject jsonProfile = JSONFactoryUtil.createJSONObject(profile);
							//
							if (jsonProfile.has("maXacNhan") && jsonProfile.has("matKhau") &&
									Validator.isNotNull(jsonProfile.getString("maXacNhan"))
									&& Validator.isNotNull(jsonProfile.getString("matKhau"))) {
								maXacNhan = jsonProfile.getString("maXacNhan");
								matKhau = jsonProfile.getString("matKhau");
								
							}
						}
							//
							String urlActive = endPoitBaseUrl + "/congdan/kichhoat" + StringPool.FORWARD_SLASH + maXacNhan;
							String authStrEnc = tokenType + StringPool.SPACE + accessToken;
	
							StringBuilder sbActive = new StringBuilder();
							try {
								URL urlValActive = new URL(urlActive);
	
								java.net.HttpURLConnection conActive = (java.net.HttpURLConnection) urlValActive
										.openConnection();
								conActive.setRequestMethod("POST");
								conActive.setRequestProperty(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
								conActive.setRequestProperty(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");
								conActive.setRequestProperty(HttpHeaders.AUTHORIZATION, authStrEnc);
								_log.debug("BASIC AUTHEN: " + authStrEnc);
	
								BufferedReader brfAc = new BufferedReader(
										new InputStreamReader(conActive.getInputStream()));
	
								int cpActive;
								while ((cpActive = brfAc.read()) != -1) {
									sbActive.append((char) cpActive);
								}
								_log.info("RESULT PROXY: " + sbActive.toString());
								//
								if (Validator.isNotNull(sbActive.toString())) {
									//
									_log.error("sbActive:"+ sbActive.toString());
									JSONObject jsonActive = JSONFactoryUtil.createJSONObject(sbActive.toString());
									if (jsonActive.has("message") && jsonActive.has("error_code")
											&& Validator.isNotNull(jsonActive.get("message"))
											&& Validator.isNotNull(jsonActive.get("error_code"))) {
										int errorCode = jsonActive.getInt("error_code");
										if (errorCode == 0) {
											//
											String oldSecrect = PasswordEncrypt.encrypt(matKhau);
											String newSecrect = PasswordEncrypt.encrypt(aplc.getTmpPass());
											
											_log.info("oldSecrect: " + oldSecrect);
											_log.info("newSecrect: " + newSecrect);
											//
											String urlChange = endPoitBaseUrl + "/doimatkhau";
					
											StringBuilder sbChange = new StringBuilder();
											try {
												URL urlValChange = new URL(urlChange);
					
												JSONObject jsonBody = JSONFactoryUtil.createJSONObject();
												jsonBody.put("matKhauCu", oldSecrect);
												jsonBody.put("matKhauMoi", newSecrect);
												jsonBody.put("email", aplc.getContactEmail());

												java.net.HttpURLConnection conChange = (java.net.HttpURLConnection) urlValChange
														.openConnection();
												conChange.setRequestMethod("POST");
												conChange.setRequestProperty(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
												conChange.setRequestProperty(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");
												conChange.setRequestProperty(HttpHeaders.AUTHORIZATION, authStrEnc);
												_log.info("BASIC AUTHEN: " + authStrEnc);
												conChange.setRequestProperty(ConstantUtils.CONTENT_LENGTH,
														StringPool.BLANK + Integer.toString(jsonBody.toString().getBytes().length));

												conChange.setUseCaches(false);
												conChange.setDoInput(true);
												conChange.setDoOutput(true);
												_log.info("POST DATA: " + jsonBody.toString());
												OutputStream osChange = conChange.getOutputStream();
												osChange.write(jsonBody.toString().getBytes());
												osChange.close();

												BufferedReader brfChange = new BufferedReader(
														new InputStreamReader(conChange.getInputStream()));
					
												int cpChange;
												while ((cpChange = brfChange.read()) != -1) {
													sbChange.append((char) cpChange);
												}
												_log.info("RESULT PROXY: " + sbChange.toString());
												//
												if (Validator.isNotNull(sbChange.toString())) {
													//
													JSONObject jsonChange = JSONFactoryUtil.createJSONObject(sbChange.toString());
													if (jsonChange.has("message") && jsonChange.has("error_code")
															&& Validator.isNotNull(jsonChange.get("message"))
															&& Validator.isNotNull(jsonChange.get("error_code"))) {
														int errorCodeChange = jsonChange.getInt("error_code");
														if (errorCodeChange == 0) {
															//
															
														}
													}
												}
											} catch (IOException e) {
												_log.error(e);
												_log.debug("Something went wrong while reading/writing in stream!!");
												return BusinessExceptionImpl.processException(e);
											}
										} else {
											return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity("{error}").build();
										}
									}
								} else {
									return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity("{error}").build();
								}
							} catch (IOException e) {
								_log.error(e);
								_log.debug("Something went wrong while reading/writing in stream!!");
								return BusinessExceptionImpl.processException(e);
							}
					}
				}
			} catch (Exception e) {
				_log.error(e);
				_log.debug("Something went wrong while reading/writing in stream!!");
				return BusinessExceptionImpl.processException(e);
			}
		}
		Applicant applicant = null;
		try {

			applicant = actions.activationLGSPApplicant(serviceContext, applicantId, code);

			JSONObject resultObj = JSONFactoryUtil.createJSONObject();
			
			resultObj.put(ConstantUtils.MAIL_AD, applicant.getContactEmail());
			resultObj.put(ConstantUtils.TOKEN, applicant.getTmpPass());

			return Response.status(HttpURLConnection.HTTP_OK).entity(JSONFactoryUtil.looseSerialize(resultObj)).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}
}
