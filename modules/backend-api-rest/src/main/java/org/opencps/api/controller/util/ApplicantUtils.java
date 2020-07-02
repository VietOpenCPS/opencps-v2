package org.opencps.api.controller.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.opencps.api.constants.ConstantUtils;
import org.opencps.api.usermgt.model.ApplicantModel;
import org.opencps.api.usermgt.model.MappingUser;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.usermgt.constants.ApplicantTerm;
import org.opencps.usermgt.constants.UserRegisterTerm;
import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.service.ApplicantLocalServiceUtil;
import org.opencps.usermgt.service.SyncSchedulerLocalServiceUtil;

public class ApplicantUtils {

	private static Log _log = LogFactoryUtil.getLog(ApplicantUtils.class);

	/**
	 * @author khoavu
	 * @param applicant
	 * @return
	 */
	public static ApplicantModel mappingToApplicantModel(Applicant applicant) {

		ApplicantModel model = new ApplicantModel();
		model.setApplicantId(GetterUtil.getLong(applicant.getPrimaryKey()));
		model.setCreateDate(String.valueOf(applicant.getCreateDate()));
		model.setModifiedDate(String.valueOf(applicant.getModifiedDate()));
		model.setApplicantName(applicant.getApplicantName());
		model.setApplicantIdType(applicant.getApplicantIdType());
		model.setApplicantIdNo(applicant.getApplicantIdNo());
		model.setApplicantIdDate(String.valueOf(applicant.getApplicantIdDate()));
		model.setContactEmail(applicant.getContactEmail());
		model.setAddress(applicant.getAddress());
		model.setCityCode(applicant.getCityCode());
		model.setCityName(applicant.getCityName());
		model.setDistrictCode(applicant.getDistrictCode());
		model.setDistrictName(applicant.getDistrictName());
		model.setWardCode(applicant.getWardCode());
		model.setWardName(applicant.getWardName());
		model.setContactName(applicant.getContactName());
		model.setContactTelNo(applicant.getContactTelNo());
		model.setApplicantProfile(applicant.getProfile());
		model.setVerification(applicant.getVerification());

		long mappingUserId = applicant.getMappingUserId();
		MappingUser mappingUser = processMappingUser(mappingUserId);
		if (mappingUser != null) {
			model.setMappingUser(mappingUser);
		}

//		MappingUser mappingUser = new MappingUser();
//		User user = null;
//		try {
//			user = UserLocalServiceUtil.getUser(mappingUserId);
//		} catch (Exception e) {
//			//_log.error(e);
//			_log.debug(e);
//		}
//		if (user != null) {
//			mappingUser.setUserId(Long.toString(mappingUserId));
//			mappingUser.setScreenName(user.getScreenName());
//			mappingUser.setLocking(user.getLockout());
//		}
//		model.setMappingUser(mappingUser);

		return model;
	}

	/**
	 * @author khoavu
	 * @param documents
	 * @return
	 */
	public static List<ApplicantModel> mappingToApplicantResults(List<Document> documents) {

		List<ApplicantModel> data = new ArrayList<ApplicantModel>();

		for (Document doc : documents) {
			ApplicantModel model = new ApplicantModel();

			model.setApplicantId(GetterUtil.getLong(doc.get(Field.ENTRY_CLASS_PK)));
			model.setCreateDate(GetterUtil.getString(doc.get(Field.CREATE_DATE)));
			model.setModifiedDate(GetterUtil.getString(doc.get(Field.MODIFIED_DATE)));
			model.setApplicantName(GetterUtil.getString(doc.get(ApplicantTerm.APPLICANTNAME)));
			model.setApplicantIdType(GetterUtil.getString(doc.get(ApplicantTerm.APPLICANTIDTYPE)));
			model.setApplicantIdNo(GetterUtil.getString(doc.get(ApplicantTerm.APPLICANTIDNO)));
			if (doc.hasField(ApplicantTerm.APPLICANTIDDATE)
					&& Validator.isNotNull(doc.get(ApplicantTerm.APPLICANTIDDATE))) {
				Date applicantDate = APIDateTimeUtils.convertStringToDate(doc.get(ApplicantTerm.APPLICANTIDDATE),
						APIDateTimeUtils._LUCENE_PATTERN);
				String applicantIdDateText = APIDateTimeUtils.convertDateToString(applicantDate,
						APIDateTimeUtils._NORMAL_DATE);
				model.setApplicantIdDate(applicantIdDateText);
			}
			model.setAddress(GetterUtil.getString(doc.get(ApplicantTerm.ADDRESS)));
			model.setCityCode(GetterUtil.getString(doc.get(ApplicantTerm.CITYCODE)));
			model.setCityName(GetterUtil.getString(doc.get(ApplicantTerm.CITYNAME)));
			model.setDistrictCode(GetterUtil.getString(doc.get(ApplicantTerm.DISTRICTCODE)));
			model.setDistrictName(GetterUtil.getString(doc.get(ApplicantTerm.DISTRICTNAME)));
			model.setWardCode(GetterUtil.getString(doc.get(ApplicantTerm.WARDCODE)));
			model.setWardName(GetterUtil.getString(doc.get(ApplicantTerm.WARDNAME)));
			model.setContactName(GetterUtil.getString(doc.get(ApplicantTerm.CONTACTNAME)));
			model.setContactTelNo(GetterUtil.getString(doc.get(ApplicantTerm.CONTACTTELNO)));
			model.setContactEmail(GetterUtil.getString(doc.get(ApplicantTerm.CONTACTEMAIL)));
			model.setApplicantProfile(doc.get(ApplicantTerm.PROFILE));

			long mappingUserId = GetterUtil.getLong(doc.get(ApplicantTerm.MAPPINGUSERID));

			MappingUser mappingUser = processMappingUser(mappingUserId);
			if (mappingUser != null) {
				model.setMappingUser(mappingUser);
			}

			if (doc.hasField(ApplicantTerm.VERIFICATION)) {
				model.setVerification(GetterUtil.getInteger(doc.get(ApplicantTerm.VERIFICATION)));
			}

			data.add(model);
		}

		return data;
	}

	public static User getUser(long applicantId) {
		User user = null;

		try {
			Applicant applicant = ApplicantLocalServiceUtil.getApplicant(applicantId);

			user = UserLocalServiceUtil.fetchUser(applicant.getMappingUserId());
		} catch (Exception e) {
			// _log.error(e);
			_log.debug(e);
		}

		return user;
	}

	private static MappingUser processMappingUser(long mappingUserId) {

		try {
			User user = UserLocalServiceUtil.getUser(mappingUserId);
			if (user != null) {
				MappingUser mappingUser = new MappingUser();

				mappingUser.setUserId(Long.toString(mappingUserId));
				mappingUser.setScreenName(user.getScreenName());
				mappingUser.setLocking(user.getLockout());
				//
				return mappingUser;
			}
		} catch (Exception e) {
			// _log.error(e);
			_log.debug(e);
		}

		return null;
	}

	public static String getTokenLGSP() {
		StringBuilder sbToken = new StringBuilder();
		try {

			URL urlVal = new URL(UserRegisterTerm.URL_TOKEN);
			StringBuilder postData = new StringBuilder();
			//
			postData.append(UserRegisterTerm.CLIENT_ID);
			postData.append(StringPool.EQUAL);
			postData.append(UserRegisterTerm.VALUE_CLIENT_ID);
			//
			postData.append(StringPool.AMPERSAND);
			postData.append(UserRegisterTerm.CLIENT_SECRECT);
			postData.append(StringPool.EQUAL);
			postData.append(UserRegisterTerm.VALUE_CLIENT_SECRECT);
			//
			postData.append(StringPool.AMPERSAND);
			postData.append(UserRegisterTerm.GRANT_TYPE);
			postData.append(StringPool.EQUAL);
			postData.append(UserRegisterTerm.VALUE_GRANT_TYPE);

			java.net.HttpURLConnection conToken = (java.net.HttpURLConnection) urlVal.openConnection();
			conToken.setRequestMethod(HttpMethod.POST);
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
		} catch (Exception e) {
			_log.debug(e);
		}

		return sbToken.toString();
	}

	public static String getTokenNewLGSP() {

		StringBuilder sbToken = new StringBuilder();
		try {

			URL urlToken = new URL(UserRegisterTerm.NEW_BASE_URL + UserRegisterTerm.NEW_ENDPOINT_TOKEN);

			java.net.HttpURLConnection conToken = (java.net.HttpURLConnection) urlToken.openConnection();
			conToken.setRequestMethod(HttpMethod.POST);
			conToken.setRequestProperty(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
			conToken.setRequestProperty(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
			conToken.setRequestProperty("Auth", "WVdSdGFXND06WVdSdGFXNUFNZz09");
			conToken.setRequestProperty("Content-Length", String.valueOf(0));

			conToken.setUseCaches(false);
			conToken.setDoInput(true);
			conToken.setDoOutput(true);
			
			OutputStream os = conToken.getOutputStream();
			os.close();

			BufferedReader brfToken = new BufferedReader(new InputStreamReader(conToken.getInputStream()));

			int cpToken;
			while ((cpToken = brfToken.read()) != -1) {
				sbToken.append((char) cpToken);
			}
		} catch (Exception e) {
			_log.error(e);
		}

		return sbToken.toString();
	}

//	public static String registerLGSP(String tokenType, String accessToken, String applicantIdType, String contactEmail,
//			String applicantIdNo, String applicantName, String applicantIdDate, String contactTelNo) {
//
//		String strProfile = StringPool.BLANK;
//
//		if (ApplicantTerm.APPLICANTIDTYPE_CITIZEN.equalsIgnoreCase(applicantIdType)) {
//			//
//			String urlRegister = UserRegisterTerm.BASE_URL + UserRegisterTerm.ENDPOINT_CITIZEN;
//			String authStrEnc = tokenType + StringPool.SPACE + accessToken;
//
//			StringBuilder sbReg = new StringBuilder();
//			try {
//				URL urlValRegister = new URL(urlRegister);
//
//				JSONObject jsonBody = JSONFactoryUtil.createJSONObject();
//				jsonBody.put(UserRegisterTerm.EMAIL, contactEmail);
//				jsonBody.put(UserRegisterTerm.SO_CMND, applicantIdNo);
//				jsonBody.put(UserRegisterTerm.NGAY_SINH, "1990-12-01");
//				jsonBody.put(UserRegisterTerm.GIOI_TINH, 0);
//				jsonBody.put(UserRegisterTerm.DIA_CHI_THUONG_TRU, StringPool.BLANK);
//				jsonBody.put(UserRegisterTerm.TINH_THUONG_TRU, 0);
//				jsonBody.put(UserRegisterTerm.HUYEN_THUONG_TRU, 0);
//				jsonBody.put(UserRegisterTerm.XA_THUONG_TRU, 0);
//				jsonBody.put(UserRegisterTerm.DIEN_THOAI, contactTelNo);
//				//
//				if (Validator.isNotNull(applicantName)) {
//					String[] splitAppName = applicantName.split("\\s+");
//					int lengthAppName = splitAppName.length;
//					if (lengthAppName > 3) {
//						jsonBody.put(UserRegisterTerm.HO, splitAppName[0]);
//						jsonBody.put(UserRegisterTerm.TEN, splitAppName[lengthAppName - 1]);
//						String tenDem = StringPool.BLANK;
//						for (int i = 1; i < splitAppName.length - 1; i++) {
//							if (i == 1) {
//								tenDem += splitAppName[1];
//							} else {
//								tenDem += StringPool.SPACE + splitAppName[i];
//							}
//						}
//						jsonBody.put(UserRegisterTerm.DEM, tenDem);
//					} else if (lengthAppName == 3) {
//						jsonBody.put(UserRegisterTerm.HO, splitAppName[0]);
//						jsonBody.put(UserRegisterTerm.DEM, splitAppName[1]);
//						jsonBody.put(UserRegisterTerm.TEN, splitAppName[2]);
//					} else if (lengthAppName == 2) {
//						jsonBody.put(UserRegisterTerm.HO, "Công dân");
//						jsonBody.put(UserRegisterTerm.DEM, splitAppName[0]);
//						jsonBody.put(UserRegisterTerm.TEN, splitAppName[1]);
//					} else {
//						jsonBody.put(UserRegisterTerm.HO, "Công");
//						jsonBody.put(UserRegisterTerm.DEM, "dân");
//						jsonBody.put(UserRegisterTerm.TEN, splitAppName[0]);
//					}
//				}
//
//				java.net.HttpURLConnection conReg = (java.net.HttpURLConnection) urlValRegister.openConnection();
//				conReg.setRequestMethod(HttpMethod.POST);
//				conReg.setRequestProperty(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
//				conReg.setRequestProperty(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");
//				conReg.setRequestProperty(HttpHeaders.AUTHORIZATION, authStrEnc);
//				_log.debug("BASIC AUTHEN: " + authStrEnc);
//				conReg.setRequestProperty(ConstantUtils.CONTENT_LENGTH,
//						StringPool.BLANK + Integer.toString(jsonBody.toString().getBytes().length));
//
//				conReg.setUseCaches(false);
//				conReg.setDoInput(true);
//				conReg.setDoOutput(true);
//				_log.debug("POST DATA: " + jsonBody.toString());
//				OutputStream osReg = conReg.getOutputStream();
//				osReg.write(jsonBody.toString().getBytes());
//				osReg.close();
//
//				BufferedReader brfReg = new BufferedReader(new InputStreamReader(conReg.getInputStream()));
//
//				int cpReg;
//				while ((cpReg = brfReg.read()) != -1) {
//					sbReg.append((char) cpReg);
//				}
//				_log.info("RESULT PROXY: " + sbReg.toString());
//				//
//				if (Validator.isNotNull(sbReg.toString())) {
//					//
//					_log.error("sbReg:" + sbReg.toString());
//					JSONObject jsonReg = JSONFactoryUtil.createJSONObject(sbReg.toString());
//					if (jsonReg.has(UserRegisterTerm.MESSAGE)
//							&& jsonReg.has(UserRegisterTerm.ERROR_CODE)
//							&& Validator.isNotNull(jsonReg.get(UserRegisterTerm.MESSAGE))
//							&& Validator.isNotNull(jsonReg.get(UserRegisterTerm.ERROR_CODE))) {
//						String message = jsonReg.getString(UserRegisterTerm.MESSAGE);
//						int errorCode = jsonReg.getInt(UserRegisterTerm.ERROR_CODE);
//
//						if (errorCode == 0) {
//							JSONObject jsonMessage = JSONFactoryUtil.createJSONObject(message);
//							//
//							String maXacNhan = jsonMessage.getString(UserRegisterTerm.MA_XAC_NHAN);
//							String matKhau = jsonMessage.getString(UserRegisterTerm.MAT_KHAU);
//							String taiKhoan = jsonMessage.getString(UserRegisterTerm.TAI_KHOAN);
//							// Them 1 cot luu pass vs activeCode vào profile
//							JSONObject jsonProfile = JSONFactoryUtil.createJSONObject();
//							jsonProfile.put(UserRegisterTerm.MA_XAC_NHAN, maXacNhan);
//							jsonProfile.put(UserRegisterTerm.MAT_KHAU, matKhau);
//							jsonProfile.put(UserRegisterTerm.TAI_KHOAN, taiKhoan);
//							//
//							_log.error("maXacNhan:" + maXacNhan);
//							_log.error("matKhau:" + matKhau);
//							strProfile = jsonProfile.toJSONString();
//						}
//					}
//				}
//			} catch (Exception e) {
//				_log.error(e);
//				_log.debug("Something went wrong while reading/writing in stream!!");
//			}
//		} else if (ApplicantTerm.APPLICANTIDTYPE_BUSINESS.equalsIgnoreCase(applicantIdType)) {
//			//
//			String urlRegister = UserRegisterTerm.BASE_URL + UserRegisterTerm.ENDPOINT_BUSINESS;
//			String authStrEnc = tokenType + StringPool.SPACE + accessToken;
//
//			StringBuilder sbReg = new StringBuilder();
//			try {
//				URL urlValRegister = new URL(urlRegister);
//
//				String ngayCapGiayPhep = StringPool.BLANK;
//				if (Validator.isNotNull(applicantIdDate)) {
//					String[] splitDate = applicantIdDate.split(StringPool.FORWARD_SLASH);
//					if (splitDate != null && splitDate.length == 3) {
//						ngayCapGiayPhep = splitDate[2] + StringPool.DASH + splitDate[1] + StringPool.DASH
//								+ splitDate[0];
//					}
//				}
//
//				JSONObject jsonBody = JSONFactoryUtil.createJSONObject();
//				jsonBody.put(UserRegisterTerm.EMAIL, contactEmail);
//				jsonBody.put(UserRegisterTerm.MA_SO_THUE, applicantIdNo);
//				jsonBody.put(UserRegisterTerm.NGAY_CAP_GIAY_PHEP, ngayCapGiayPhep);
//				jsonBody.put(UserRegisterTerm.MA_GIAY_PHEP, applicantIdNo);
//				jsonBody.put(UserRegisterTerm.MO_TA_DIA_DIEM_KINH_DOANH, StringPool.BLANK);
//				jsonBody.put(UserRegisterTerm.TINH_DIA_DIEM_KD, 0);
//				jsonBody.put(UserRegisterTerm.HUYEN_DIA_DIEM_KD, 0);
//				jsonBody.put(UserRegisterTerm.XA_DIA_DIEM_KD, 0);
//				jsonBody.put(UserRegisterTerm.DIEN_THOAI, contactTelNo);
//				jsonBody.put(UserRegisterTerm.TEN, applicantName);
//				//
//
//				java.net.HttpURLConnection conReg = (java.net.HttpURLConnection) urlValRegister.openConnection();
//				conReg.setRequestMethod(HttpMethod.POST);
//				conReg.setRequestProperty(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
//				conReg.setRequestProperty(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");
//				conReg.setRequestProperty(HttpHeaders.AUTHORIZATION, authStrEnc);
//				_log.debug("BASIC AUTHEN: " + authStrEnc);
//				conReg.setRequestProperty(ConstantUtils.CONTENT_LENGTH,
//						StringPool.BLANK + Integer.toString(jsonBody.toString().getBytes().length));
//
//				conReg.setUseCaches(false);
//				conReg.setDoInput(true);
//				conReg.setDoOutput(true);
//				_log.debug("POST DATA: " + jsonBody.toString());
//				OutputStream osReg = conReg.getOutputStream();
//				osReg.write(jsonBody.toString().getBytes());
//				osReg.close();
//
//				BufferedReader brfReg = new BufferedReader(new InputStreamReader(conReg.getInputStream()));
//
//				int cpReg;
//				while ((cpReg = brfReg.read()) != -1) {
//					sbReg.append((char) cpReg);
//				}
//				_log.info("RESULT PROXY: " + sbReg.toString());
//				//
//				if (Validator.isNotNull(sbReg.toString())) {
//					//
//					_log.error("sbReg:" + sbReg.toString());
//					JSONObject jsonReg = JSONFactoryUtil.createJSONObject(sbReg.toString());
//					if (jsonReg.has(UserRegisterTerm.MESSAGE)
//							&& jsonReg.has(UserRegisterTerm.ERROR_CODE)
//							&& Validator.isNotNull(jsonReg.get(UserRegisterTerm.MESSAGE))
//							&& Validator.isNotNull(jsonReg.get(UserRegisterTerm.ERROR_CODE))) {
//						String message = jsonReg.getString(UserRegisterTerm.MESSAGE);
//						int errorCode = jsonReg.getInt(UserRegisterTerm.ERROR_CODE);
//
//						if (errorCode == 0) {
//							JSONObject jsonMessage = JSONFactoryUtil.createJSONObject(message);
//							//
//							String maXacNhan = jsonMessage.getString(UserRegisterTerm.MA_XAC_NHAN);
//							String matKhau = jsonMessage.getString(UserRegisterTerm.MAT_KHAU);
//							String taiKhoan = jsonMessage.getString(UserRegisterTerm.TAI_KHOAN);
//							// Them 1 cot luu pass vs activeCode vào profile
//							JSONObject jsonProfile = JSONFactoryUtil.createJSONObject();
//							jsonProfile.put(UserRegisterTerm.MA_XAC_NHAN, maXacNhan);
//							jsonProfile.put(UserRegisterTerm.MAT_KHAU, matKhau);
//							jsonProfile.put(UserRegisterTerm.TAI_KHOAN, taiKhoan);
//							//
//							_log.error("maXacNhan:" + maXacNhan);
//							_log.error("matKhau:" + matKhau);
//							strProfile = jsonProfile.toJSONString();
//						}
//					}
//				}
//			} catch (Exception e) {
//				_log.error(e);
//				_log.debug("Something went wrong while reading/writing in stream!!");
//			}
//		}
//		return strProfile;
//	}

	public static String registerNewLGSP(String tokenType, String accessToken, String contactEmail,
			String applicantIdNo, String applicantName, String contactTelNo, String organizationName,
			String secrectKey) {

		String strProfile = StringPool.BLANK;

		String urlRegister = UserRegisterTerm.NEW_BASE_URL + UserRegisterTerm.NEW_ENDPOINT_REGISTER;
		_log.info("urlRegister: "+urlRegister);
		String authStrEnc = tokenType + StringPool.SPACE + accessToken;

		StringBuilder sbReg = new StringBuilder();
		try {
			URL urlValRegister = new URL(urlRegister);

			JSONObject jsonBody = JSONFactoryUtil.createJSONObject();
			jsonBody.put(UserRegisterTerm.USER_NAME, contactEmail);
			jsonBody.put(UserRegisterTerm.EMAIL, contactEmail);
			jsonBody.put(UserRegisterTerm.PHONE_NUMBER, contactTelNo);
			jsonBody.put(UserRegisterTerm.ORGANIZATION_NAME, organizationName);
			jsonBody.put(UserRegisterTerm.SECRECT_KEY, secrectKey);
			//
			if (Validator.isNotNull(applicantName)) {
				String[] splitAppName = applicantName.split("\\s+");
				int lengthAppName = splitAppName.length;
				if (lengthAppName > 3) {
					jsonBody.put("fisrtName", splitAppName[0]);
					jsonBody.put("lastName", splitAppName[lengthAppName - 1]);
					String tenDem = StringPool.BLANK;
					for (int i = 1; i < splitAppName.length - 1; i++) {
						if (i == 1) {
							tenDem += splitAppName[1];
						} else {
							tenDem += StringPool.SPACE + splitAppName[i];
						}
					}
					jsonBody.put("middleName", tenDem);
				} else if (lengthAppName == 3) {
					jsonBody.put("fisrtName", splitAppName[0]);
					jsonBody.put("middleName", splitAppName[1]);
					jsonBody.put("lastName", splitAppName[2]);
				} else if (lengthAppName == 2) {
					jsonBody.put("fisrtName", "Công dân");
					jsonBody.put("middleName", splitAppName[0]);
					jsonBody.put("lastName", splitAppName[1]);
				} else {
					jsonBody.put("fisrtName", "Công");
					jsonBody.put("middleName", "dân");
					jsonBody.put("lastName", splitAppName[0]);
				}
			}

			java.net.HttpURLConnection conReg = (java.net.HttpURLConnection) urlValRegister.openConnection();
			conReg.setRequestMethod(HttpMethod.POST);
			conReg.setRequestProperty(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
			conReg.setRequestProperty(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
			conReg.setRequestProperty(HttpHeaders.AUTHORIZATION, authStrEnc);
			_log.info("BASIC AUTHEN: " + authStrEnc);
			conReg.setRequestProperty(ConstantUtils.CONTENT_LENGTH,
					StringPool.BLANK + Integer.toString(jsonBody.toString().getBytes().length));

			conReg.setUseCaches(false);
			conReg.setDoInput(true);
			conReg.setDoOutput(true);
			_log.info("POST DATA: " + jsonBody.toString());
			OutputStream osReg = conReg.getOutputStream();
			osReg.write(jsonBody.toString().getBytes());
			osReg.close();

			BufferedReader brfReg = new BufferedReader(new InputStreamReader(conReg.getInputStream()));

			int cpReg;
			while ((cpReg = brfReg.read()) != -1) {
				sbReg.append((char) cpReg);
			}
			_log.info("RESULT PROXY: " + sbReg.toString());
			//
			strProfile = sbReg.toString();
		} catch (Exception e) {
			_log.error(e);
			_log.debug("Something went wrong while reading/writing in stream!!");
		}
		return strProfile;
	}

//	public static boolean activeUserLGSP(JSONObject jsonToken, long groupId, String profile, String tmpSecrect,
//			String contactEmail) throws Exception {
//		String accessToken = jsonToken.getString("access_token");
//		String tokenType = jsonToken.getString("token_type");
//
//		_log.info("accessToken: " + accessToken);
//		_log.info("tokenType: " + tokenType);
//
//		// Dang ky tk cong dan
//		String maXacNhan = StringPool.BLANK;
//		String matKhau = StringPool.BLANK;
//		if (Validator.isNotNull(profile)) {
//			JSONObject jsonProfile = JSONFactoryUtil.createJSONObject(profile);
//			//
//			if (jsonProfile.has("maXacNhan") && jsonProfile.has("matKhau")
//					&& Validator.isNotNull(jsonProfile.getString("maXacNhan"))
//					&& Validator.isNotNull(jsonProfile.getString("matKhau"))) {
//				maXacNhan = jsonProfile.getString("maXacNhan");
//				matKhau = jsonProfile.getString("matKhau");
//
//			}
//		}
//		//
//		String urlActive = UserRegisterTerm.BASE_URL + UserRegisterTerm.ENDPOINT_CITIZEN_ACTIVE
//				+ StringPool.FORWARD_SLASH + maXacNhan;
//		String authStrEnc = tokenType + StringPool.SPACE + accessToken;
//
//		StringBuilder sbActive = new StringBuilder();
//		try {
//			URL urlValActive = new URL(urlActive);
//
//			java.net.HttpURLConnection conActive = (java.net.HttpURLConnection) urlValActive.openConnection();
//			conActive.setRequestMethod(HttpMethod.POST);
//			conActive.setRequestProperty(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
//			conActive.setRequestProperty(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");
//			conActive.setRequestProperty(HttpHeaders.AUTHORIZATION, authStrEnc);
//			_log.debug("BASIC AUTHEN: " + authStrEnc);
//
//			BufferedReader brfAc = new BufferedReader(new InputStreamReader(conActive.getInputStream()));
//
//			int cpActive;
//			while ((cpActive = brfAc.read()) != -1) {
//				sbActive.append((char) cpActive);
//			}
//			_log.info("RESULT PROXY: " + sbActive.toString());
//			//
//			if (Validator.isNotNull(sbActive.toString())) {
//				//
//				_log.error("sbActive:" + sbActive.toString());
//				JSONObject jsonActive = JSONFactoryUtil.createJSONObject(sbActive.toString());
//				if (jsonActive.has("message") && jsonActive.has("error_code")
//						&& Validator.isNotNull(jsonActive.get("message"))
//						&& Validator.isNotNull(jsonActive.get("error_code"))) {
//					int errorCode = jsonActive.getInt("error_code");
//					if (errorCode == 0) {
//						boolean flagChange = changePassLGSP(matKhau, tmpSecrect, contactEmail, authStrEnc);
//						if (!flagChange) {
//							SyncSchedulerLocalServiceUtil.updateSyncScheduler(0, groupId, User.class.getName(),
//									contactEmail, new Date(), 0, new ServiceContext());
//						}
//					}
//				}
//			}
//		} catch (IOException e) {
//			_log.error(e);
//			_log.debug("Something went wrong while reading/writing in stream!!");
//		}
//		return false;
//	}

//	public static boolean changePassLGSP(String matKhau, String tmpSecrect, String contactEmail, String authStrEnc) {
//
//		try {
//			String oldSecrect = PasswordEncrypt.encrypt(matKhau);
//			String newSecrect = PasswordEncrypt.encrypt(tmpSecrect);
//
//			_log.info("oldSecrect: " + oldSecrect);
//			_log.info("newSecrect: " + newSecrect);
//			//
//			String urlChange = UserRegisterTerm.BASE_URL
//					+ UserRegisterTerm.ENDPOINT_CHANGE_SECRECT;
//
//			StringBuilder sbChange = new StringBuilder();
//
//			URL urlValChange = new URL(urlChange);
//
//			JSONObject jsonBody = JSONFactoryUtil.createJSONObject();
//			jsonBody.put(UserRegisterTerm.OLD_SECRECT, oldSecrect);
//			jsonBody.put(UserRegisterTerm.NEW_SECRECT, newSecrect);
//			jsonBody.put(UserRegisterTerm.EMAIL, contactEmail);
//
//			java.net.HttpURLConnection conChange = (java.net.HttpURLConnection) urlValChange.openConnection();
//			conChange.setRequestMethod(HttpMethod.POST);
//			conChange.setRequestProperty(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
//			conChange.setRequestProperty(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");
//			conChange.setRequestProperty(HttpHeaders.AUTHORIZATION, authStrEnc);
//			_log.info("BASIC AUTHEN: " + authStrEnc);
//			conChange.setRequestProperty(ConstantUtils.CONTENT_LENGTH,
//					StringPool.BLANK + Integer.toString(jsonBody.toString().getBytes().length));
//
//			conChange.setUseCaches(false);
//			conChange.setDoInput(true);
//			conChange.setDoOutput(true);
//			_log.info("POST DATA: " + jsonBody.toString());
//			OutputStream osChange = conChange.getOutputStream();
//			osChange.write(jsonBody.toString().getBytes());
//			osChange.close();
//
//			BufferedReader brfChange = new BufferedReader(new InputStreamReader(conChange.getInputStream()));
//
//			int cpChange;
//			while ((cpChange = brfChange.read()) != -1) {
//				sbChange.append((char) cpChange);
//			}
//			_log.info("RESULT PROXY: " + sbChange.toString());
//			//
//			if (Validator.isNotNull(sbChange.toString())) {
//				//
//				JSONObject jsonChange = JSONFactoryUtil.createJSONObject(sbChange.toString());
//				if (jsonChange.has("message") && jsonChange.has("error_code")
//						&& Validator.isNotNull(jsonChange.get("message"))
//						&& Validator.isNotNull(jsonChange.get("error_code"))) {
//					int errorCodeChange = jsonChange.getInt("error_code");
//					if (errorCodeChange == 0) {
//						return true;
//					}
//				}
//			}
//		} catch (Exception e) {
//			_log.error(e);
//			_log.debug("Something went wrong while reading/writing in stream!!");
//		}
//
//		return false;
//	}

}
