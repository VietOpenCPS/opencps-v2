package org.opencps.usermgt.service.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.HttpHeaders;

import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.usermgt.action.impl.EmployeeActions;
import org.opencps.usermgt.constants.CommonTerm;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;



public class KeyCloakUtil {

	private static Log _log = LogFactoryUtil.getLog(KeyCloakUtil.class);

	private static final String TOKEN_URL = "http://sso-bgtvt.fds.vn/auth/realms/sso-demo/protocol/openid-connect/token";
	private static final String URL = "http://sso-bgtvt.fds.vn/vuejx";
	private static final String QUERY_GETEMPLOYEE = "query add($token: String, $db: String, $collection: String, $filter: JSON, $skip: Int, $limit: Int,$sort: JSON, $keys: JSON) {\n      userMany: userMany(token: $token, db: $db, collection: $collection, filter: $filter, skip: $skip, limit: $limit, sort: $sort, keys: $keys)\n    }";
	private static final String USERNAME = "mcdt_data";
	private static final String PASSWORD = "123456a@A#123#123";
	private static final String CLIENT_ID = "sso-admin-app";
	private static final String CLIENT_SECRET = "f9f37a34-e1f5-4f29-a579-95908d97be47";
	private static final String GRANT_TYPE = "password";
	private static final String TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50IjoiYWRtaW4iLCJhdXRob3IiOiJiaW5odGgudnVlanhAZ21haWwuY29tIiwicm9sZSI6WyJhZG1pbiJdLCJpYXQiOjE1OTEzNTU0MzJ9.z1POdOxEogsDlgYJD3vsFFaYUdv9ccCZ4CbhphwVFA4";
	private static final String KEYCLOAK_CONFIG = "KEYCLOAK_CONFIG";
	private static final String EMAIL = "@mt.gov.vn";
	
	public  void main(String[] String) {
		getTokenKeycloak();
//		getEmployeeListKeycloack();
//		syncEmployeeUser();
	}
	

	public  String getTokenKeycloak() {

		String token = StringPool.BLANK;
		
		JsonObject keycloakConfig = getKeyCloakConfig();
		JsonObject jsonToken = null;
		StringBuilder sbToken = new StringBuilder();
		StringBuilder postData = new StringBuilder();
		
		
		if(isActiveKeyCloak()) {
			postData.append("client_id").append(StringPool.EQUAL).append(keycloakConfig.get("client_id").getAsString());
			postData.append(StringPool.AMPERSAND).append("client_secret").append(StringPool.EQUAL).append(keycloakConfig.get("client_secret").getAsString());
			postData.append(StringPool.AMPERSAND).append("username").append(StringPool.EQUAL).append(keycloakConfig.get("username").getAsString());
			postData.append(StringPool.AMPERSAND).append("password").append(StringPool.EQUAL).append(keycloakConfig.get("password").getAsString());
			postData.append(StringPool.AMPERSAND).append("grant_type").append(StringPool.EQUAL).append(keycloakConfig.get("grant_type").getAsString());
			//
	
			try {
	
				URL urlToken = new URL(keycloakConfig.get("token-endpoint").getAsString());
	
				java.net.HttpURLConnection conToken = (java.net.HttpURLConnection) urlToken.openConnection();
				conToken.setRequestMethod(HttpMethod.POST);
				conToken.setRequestProperty(HttpHeaders.ACCEPT, "*/*");
				conToken.setRequestProperty(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
				conToken.setRequestProperty(HttpHeaders.CONTENT_LENGTH,
						StringPool.BLANK + Integer.toString(postData.toString().getBytes().length));
	
				conToken.setUseCaches(false);
				conToken.setDoInput(true);
				conToken.setDoOutput(true);
	
				OutputStream os = conToken.getOutputStream();
				os.write(postData.toString().getBytes());
				os.close();
	
				BufferedReader brfToken = new BufferedReader(new InputStreamReader(conToken.getInputStream()));
	
				int cpToken;
				while ((cpToken = brfToken.read()) != -1) {
					sbToken.append((char) cpToken);
				}
			} catch (Exception e) {
				_log.error(e);
			}
	
			if (sbToken != null && sbToken.toString() != null) {
				try {
	
					_log.debug("**sbToken.toString():" + sbToken);
					
					jsonToken = new JsonParser().parse(sbToken.toString()).getAsJsonObject();
					
					//
					if (jsonToken.has("access_token")) {
	
						token = jsonToken.get("access_token").getAsString();
	
						_log.debug("+++token:" + token);
	
					}
				} catch (Exception e1) {
					_log.error(e1);
				}
			}
		}

		return token;

	}
	
	
	public  JsonObject getEmployeeListKeycloack(int skip) {
		
		JsonObject result = null;
		
		_log.debug("====keycloak scheduler====");
		
		if(isActiveKeyCloak()) {
			
			try {
			
				JsonObject keycloakConfig = getKeyCloakConfig();
	
				String token = keycloakConfig.get("access_token").getAsString();
				
				if(Validator.isNull(token)) {
					token = getTokenKeycloak();
				}
		
				JsonObject postData = new JsonObject();
				JsonObject variables = new JsonObject();
		
				variables.addProperty("token", token);
				variables.addProperty("db", keycloakConfig.get("db").getAsString());
				variables.addProperty("collection", keycloakConfig.get("collection").getAsString());
				variables.addProperty("limit", keycloakConfig.get("limit").getAsInt());
				variables.addProperty("app", keycloakConfig.get("client_id").getAsString());
		
				JsonObject sort = new JsonObject();
				sort.addProperty("modifiedAt", -1);
				variables.add("sort", sort);
				
				skip = keycloakConfig.has("skip") ? keycloakConfig.get("skip").getAsInt(): skip;
				
				variables.addProperty("skip", skip);
		
				postData.addProperty("query",  keycloakConfig.get("query").getAsString());
				postData.add("variables", variables);
		
				OkHttpClient client = new OkHttpClient().newBuilder().build();
				MediaType mediaType = MediaType.parse("application/json");
	
				RequestBody body = RequestBody.create(mediaType, postData.toString());
				Request request = new Request.Builder()
						.url(keycloakConfig.get("sync-url").getAsString())
						.method(HttpMethod.POST, body)
						.addHeader(HttpHeaders.CONTENT_TYPE, "application/json")
						.build();
				Response response = client.newCall(request).execute();
				
				String stringResponse = response.body().string();
				
				boolean isJsonObject = new JsonParser().parse(stringResponse).isJsonObject();
				
				if(isJsonObject) {
				
					result = new JsonParser().parse(stringResponse).getAsJsonObject();
				}
	
			} catch (Exception e) {
				_log.error(e);
			}
		}

		return result;

	}
	
	public  void syncEmployeeUser(int skip) throws InterruptedException {
		
		JsonObject keycloakConfig = getKeyCloakConfig();
		int limit = keycloakConfig.has("limit") ? keycloakConfig.get("limit").getAsInt() : 10;
		
		_log.debug("*start---skip:"+skip);
		
		JsonObject employeeList = getEmployeeListKeycloack(skip);
		
		JsonArray jsonArray = employeeList.getAsJsonObject("data").getAsJsonObject("userMany").getAsJsonObject("hits").getAsJsonArray("hits");
		
		
		int count = jsonArray.size();
		
		_log.debug("==count:"+count);
		
		for(int i=0;i < jsonArray.size();i++) {
			
			_log.debug("++counter:"+i);
			
			JsonObject employeeJson = jsonArray.get(i).getAsJsonObject().getAsJsonObject("_source");
			
			JsonArray viTriChucVu = employeeJson.getAsJsonArray("ViTriChucVu");
			
			List<String> roleName = new ArrayList<String>();
			for (int j = 0; j < viTriChucVu.size(); j++) {
					JsonObject jsonRole = viTriChucVu.get(j).getAsJsonObject();
					
					//_log.debug("===jsonRole:"+jsonRole);
					
					roleName.add(jsonRole.getAsJsonObject("_source") .get("TenMuc").getAsString());
			}
			
			_log.debug("===roleName.size()"+roleName.size());
			
			
			JsonArray coQuanCongTac = employeeJson.has("CoQuanCongTac") ?  employeeJson.getAsJsonArray("CoQuanCongTac") : new JsonArray();

			List<String> coQuanCongTacName = new ArrayList<String>();
			for (int j = 0; j < coQuanCongTac.size(); j++) {
					JsonObject jsonCoQuan = coQuanCongTac.get(j).getAsJsonObject();
					
					String tenCoSoToChuc = jsonCoQuan.getAsJsonObject("_source") .get("TenCoSoToChuc").getAsString();
					
					_log.debug("===tenCoSoToChuc:"+tenCoSoToChuc);
					
					coQuanCongTacName.add(tenCoSoToChuc);
					
					
			}
			
			_log.debug("===coQuanCongTacName.size()"+coQuanCongTacName.size());
			
			
			
			JsonObject taiKhoanDienTu = employeeJson.has("TaiKhoanDienTu") ? employeeJson.get("TaiKhoanDienTu").getAsJsonObject() : new JsonObject();
			
			if(Validator.isNotNull(taiKhoanDienTu)) {
				String hoVaTen = employeeJson.has("HoVaTen") ? employeeJson.get("HoVaTen").getAsString() :StringPool.BLANK;
				String tenDangNhap =taiKhoanDienTu.has("TenDangNhap")? taiKhoanDienTu.get("TenDangNhap").getAsString() : StringPool.BLANK;
				String matKhau =taiKhoanDienTu.has("MatKhau") ? taiKhoanDienTu.get("MatKhau").getAsString() :StringPool.BLANK;
				String thuDienTu =taiKhoanDienTu.has("ThuDienTu") ?  taiKhoanDienTu.get("ThuDienTu").getAsString(): StringPool.BLANK;
				String soHieuCanBo = employeeJson.has("SoHieuCanBo") ? employeeJson.get("SoHieuCanBo").getAsString() :StringPool.BLANK;
				
				_log.debug("===hoVaTen:"+hoVaTen);
				_log.debug("===tenDangNhap:"+tenDangNhap);
				_log.debug("===matKhau:"+matKhau);
				_log.debug("===thuDienTu:"+thuDienTu);
				_log.debug("===soHieuCanBo:"+soHieuCanBo);
				
				createEmployeeUser(roleName, coQuanCongTacName, hoVaTen, thuDienTu, tenDangNhap, matKhau,soHieuCanBo);
			
			}
			
		}
		
		
		if(count == limit) {
			skip = skip + limit;
			
			syncEmployeeUser(skip);
		}
	}
	
	private  void createEmployeeUser(List<String> roleName,List<String> coQuanCongTacName,String hoVaTen,String email,String tenDangNhap,String matKhau,String soHieuCanBo) {
		
		EmployeeActions actions = new EmployeeActions();
		
		User exitedUser = null;
		
		
		for(int i=0;i<coQuanCongTacName.size();i++) {
			
			String groupKey =  StringUtil.toLowerCase(coQuanCongTacName.get(i)) ;
			try {
				
				Group group = null;
				
				try {
					group = GroupLocalServiceUtil.getGroup(PortalUtil.getDefaultCompanyId(), groupKey);
				}catch(Exception e) {

				}
				
				if(Validator.isNotNull(group)) {
					
					ServiceContext serviceContext = new ServiceContext();
					serviceContext.setScopeGroupId(group.getGroupId());
					serviceContext.setCompanyId(group.getCompanyId());
					serviceContext.setUserId(group.getCreatorUserId());
					
					if(Validator.isNull(exitedUser)) {
						exitedUser = checkUser(hoVaTen, tenDangNhap, email,matKhau,serviceContext);
					}
					
					
					Employee employee = null;
					
					employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(serviceContext.getScopeGroupId(), exitedUser.getUserId());
					
					if(Validator.isNull(employee)) {
						
						employee = actions.create(serviceContext.getUserId(), serviceContext.getCompanyId(), serviceContext.getScopeGroupId(), soHieuCanBo,
								hoVaTen, exitedUser.getEmailAddress(), "1", null, StringPool.BLANK, StringPool.BLANK, "Mr", "1", null, null, serviceContext);
						
						employee.setMappingUserId(exitedUser.getUserId());
						EmployeeLocalServiceUtil.updateEmployee(employee);
					
					}else {
						
						employee = actions.update(serviceContext.getUserId(), serviceContext.getCompanyId(), serviceContext.getScopeGroupId(), 
								employee.getEmployeeId(), soHieuCanBo,
								hoVaTen, exitedUser.getEmailAddress(), GetterUtil.getString(employee.getGender()), 
								employee.getBirthdate(), employee.getTelNo(), employee.getMobile(), employee.getTitle(),
								GetterUtil.getString(employee.getWorkingStatus()), employee.getRecruitDate(), 
								employee.getLeaveDate(), serviceContext);
						
						updateUser(exitedUser, matKhau, serviceContext);
						
					}
				}
				
				_log.debug("==done==:");
				
				
				
			} catch (PortalException e) {
				// TODO Auto-generated catch block
				_log.error(e);
			}
		}
		
		
	}
	
	private  String checkEmployeeNo(long groupId) {
		
		String employeeNoTemplate = "EMPQLVTTN";
		StringBuilder result = new StringBuilder();
		
//		List<Employee> employeeList = EmployeeLocalServiceUtil.findEmployeesByGroupEmployeeNo(groupId, employeeNoTemplate);
		
		List<Employee> employeeList = null;
		
		int iArr[] = new int[employeeList.size()];
		
		for(int i=0;i<employeeList.size();i++) {
			
			int test = Integer.valueOf(StringUtil.extractLast(employeeList.get(i).getEmployeeNo(), employeeNoTemplate));
			
			_log.debug("++++test:"+test);
			iArr[i] =  test ;
		}
		Arrays.parallelSort(iArr);
		
		if(Validator.isNotNull(employeeList)) {
			result.append(employeeNoTemplate).append(iArr[0]);
		}else {
			result.append(employeeNoTemplate).append(1);
		}
		
		_log.debug("+++result.toString():"+result.toString());
		
		return result.toString();
	}
	
	private  String checkEmployeeNo2(long groupId,String soHieuCanBo) {
		
		String employeeNoTemplate = "EMPQLVTTN";
		StringBuilder result = new StringBuilder();
		
//		List<Employee> employeeList = EmployeeLocalServiceUtil.findEmployeesByGroupEmployeeNo(groupId, soHieuCanBo);
		
		List<Employee> employeeList = null;
		
		int iArr[] = new int[employeeList.size()];
		
		for(int i=0;i<employeeList.size();i++) {
			
			int test = Integer.valueOf(StringUtil.extractLast(employeeList.get(i).getEmployeeNo(), employeeNoTemplate));
			
			_log.debug("++++test:"+test);
			iArr[i] =  test ;
		}
		Arrays.parallelSort(iArr);
		
		if(Validator.isNotNull(employeeList)) {
			result.append(employeeNoTemplate).append(iArr[0]);
		}else {
			result.append(employeeNoTemplate).append(1);
		}
		
		_log.debug("+++result.toString():"+result.toString());
		
		return result.toString();
	}
	
	private User checkUser(String hoVaTen, String tenDangNhap, String email, String matKhau,
			ServiceContext serviceContext) throws PortalException {

		User exitedUser = null;
		try {

			if (Validator.isNotNull(email)) {
				exitedUser = UserLocalServiceUtil.getUserByEmailAddress(serviceContext.getCompanyId(), email);
			} else {

				email = tenDangNhap + EMAIL;

				exitedUser = UserLocalServiceUtil.getUserByEmailAddress(serviceContext.getCompanyId(), email);
			}

		} catch (PortalException e) {
			// TODO Auto-generated catch block
			
			if( e instanceof NoSuchUserException) {
				
				_log.debug("create new User ");
				exitedUser = createUser(hoVaTen, email, tenDangNhap, matKhau, serviceContext);
			}else {
				_log.error(e);
			}
			

		}

		return exitedUser;
	}
	
	private  User createUser(String hoVaTen,String email,String tenDangNhap,String matKhau,ServiceContext serviceContext) throws PortalException {
		
		String[] fml = getF_M_LastName(hoVaTen);
		
		Role role =
				RoleLocalServiceUtil.fetchRole(serviceContext.getCompanyId(), CommonTerm.EMPLOYEE);
		
		List<Long> roleIds = new ArrayList<>();
		if (Validator.isNotNull(role)) {
			roleIds.add(role.getRoleId());
		}
			
		long[] groupIds = {serviceContext.getScopeGroupId()};
		
		String firstName = fml[0];
		String lastName = fml[0];
		
		if(Validator.isNull(firstName)) {
			firstName = tenDangNhap;
		}
		
		if(Validator.isNull(lastName)) {
			lastName = tenDangNhap;
		}
		
		User user = UserLocalServiceUtil.addUser(serviceContext.getUserId(), serviceContext.getCompanyId(), false, matKhau, matKhau, false, tenDangNhap, email, 0,
				StringPool.BLANK, serviceContext.getLocale(), firstName, fml[1], lastName, 0, 0, true, Calendar.JANUARY, 1,
				1979, StringPool.BLANK, groupIds, null, null, null, false, serviceContext);
		
		UserLocalServiceUtil.authenticateForBasic(serviceContext.getCompanyId(), CompanyConstants.AUTH_TYPE_EA, user.getEmailAddress(), matKhau);
		
		return user;
		
	}
	
	private  User updateUser(User user, String matKhau, ServiceContext serviceContext)
			throws PortalException {

		Role role = RoleLocalServiceUtil.fetchRole(serviceContext.getCompanyId(), CommonTerm.EMPLOYEE);

		List<Long> roleIds = new ArrayList<>();
		if (Validator.isNotNull(role)) {
			roleIds.add(role.getRoleId());
		}

		long[] groupIds = { serviceContext.getScopeGroupId() };

		user = UserLocalServiceUtil.updatePassword(user.getUserId(), matKhau, matKhau, false, true);

		UserLocalServiceUtil.updateGroups(user.getUserId(), groupIds, serviceContext);
		
		UserLocalServiceUtil.authenticateForBasic(serviceContext.getCompanyId(), CompanyConstants.AUTH_TYPE_EA, user.getEmailAddress(), matKhau);

		return user;

	}
	
	private  String[] getF_M_LastName(String fullName) {
		
		
		String[] fml = new String[3];

		String[] splitName =
			StringUtil.split(fullName, StringPool.SPACE);

		if (splitName != null && splitName.length > 0) {
			fml[0] = splitName[0];
			fml[1] = splitName.length >= 3
				? StringUtil.merge(
					ArrayUtil.subset(
						splitName, 1, splitName.length - 1),
					StringPool.SPACE)
				: StringPool.BLANK;
			fml[2] = splitName.length >= 2
				? splitName[splitName.length - 1] : splitName[0];
		}
		else {
			fml[0] = fullName;
			fml[1] = StringPool.BLANK;
			fml[2] = fullName;
		}
		
		return fml;
	}
	
	private  JsonObject getKeyCloakConfig() {

		JsonObject result = new JsonObject();

		try {

			ServerConfig serverConfig = ServerConfigLocalServiceUtil.getByCode(KEYCLOAK_CONFIG);

			if (Validator.isNotNull(serverConfig)) {
				result = new JsonParser().parse(serverConfig.getConfigs()).getAsJsonObject();
			}
		} catch (Exception e) {
			_log.error(e);
		}

		return result;
	}
	
	private  boolean isActiveKeyCloak() {

		JsonObject result = getKeyCloakConfig();
		
		 if(result.has("active")) {
			 
			if (result.get("active").getAsBoolean()) {
				return true;
			}
		 }

		return false;
	}

}
