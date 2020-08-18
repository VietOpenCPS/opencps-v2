
package org.opencps.usermgt.constants;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;

public class UserTerm {

	public static final String USER_ID = "userId";
	public static final String USER_NAME = "userName";
	public static final String CLASS_NAME = "className";
	public static final String CLASS_PK = "classPK";
	public static final String SCREEN_NAME = "screenName";
	public static final String EMAIL = "email";
	public static final String FULL_NAME = "fullName";
	public static final String CONTACT_EMAIL = "contactEmail";
	public static final String CONTACT_TELNO = "contactTelNo";
	public static final String GENDER = "gender";
	public static final String BIRTHDATE = "birthdate";

	public static final String SECRET_KEY = "secretKey";
	public static final String TELNO = "telNo";
	public static final String USER = "User";

	public static final String MAPPING_USER_ID = "mappingUserId";
	public static final String EMPLOYEE_GENDER = "employeeGender";
	public static final String EMPLOYEE_WORKING_STATUS =
		"employeeWorkingStatus";
	public static final String EMPLOYEE_MAIN_JOBPOS_ID =
		"employeeMainJobPostId";
	public static final String EMPLOYEE_MAIN_JOBPOS_NAME =
		"employeeMainJobPostName";

	public static final String EMPLOYEE_PHOTO_FILE_ENTRY_ID =
		"employeePhotoFileEntryId";
	public static final String EMPLOYEE_FILE_CER_ID = "employeeFileCerId";
	public static final String EMPLOYEE_FILE_SIGN_ID = "employeeFileSignId";
	public static final String APPLICANT_LOCK = "applicantLock";
	public static final String AVATAR = "avatar";
	public static final String TITLE = "title";
	public static final String EMPLOYEE_FULLNAME = "employeeFullName";
	public static final String EMPLOYEE_NO = "employeeNo";
	public static final String EMPLOYEE_TELNO = "employeeTelNo";
	public static final String EMPLOYEE_BIRTHDATE = "employeeBirthdate";
	public static final String EMPLOYEE_MOBILE = "employeeMobile";
	public static final String EMPLOYEE_EMAIL = "employeeEmail";

	public static final String APPLICANT_NAME = "applicantName";
	public static final String APPLICANT_TYPE = "applicantType";
	public static final String APPLICANT_ID_NO = "applicantIdNo";
	public static final String APPLICANT_ID_DATE = "applicantIdDate";
	public static final String APPLICANT_ADDRESS = "applicantAddress";
	public static final String APPLICANT_CITY_CODE = "applicantCityCode";
	public static final String APPLICANT_CITY_NAME = "applicantCityName";
	public static final String APPLICANT_DISTRICT_CODE = "applicantDistrictCode";
	public static final String APPLICANT_DISTRICT_NAME = "applicantDistrictName";
	public static final String APPLICANT_WARD_CODE = "applicantWardCode";
	public static final String APPLICANT_WARD_NAME = "applicantWardName";
	public static final String APPLICANT_CONTACT_NAME =
	"applicantContactName";
	public static final String APPLICANT_CONTACT_TELNO = "applicantContactTelNo";
	public static final String APPLICANT_CONTACT_EMAIL = "applicantContactEmail";
	public static final String APPLICANT_ACTIVATION_CODE = "applicantActivationCode";
	public static final String APPLICANT_TMP_SECRET = "applicantTmpPass";

	public static final String TIME_MINUTELY = "minutely";
	public static final String TIME_HOURLY = "hourly";
	public static final String MODERATOR = "moderator";
	public static final String ASSIGNED = "assigned";
	public static final String TO_USERS = "toUsers";
	public static final String ROLE = "role";
	public static final String ROLE_CODE = "roleCode";
	public static final String DEACTIVE_ACCOUNT_FLAG = "deactiveAccountFlag";
	public static final String GOV_AGENCY_CODE = "govAgencyCode";
	public static final String GOV_AGENCY_NAME = "govAgencyName";
	public static final String SCOPE = "scope";
	public static final String GOV_AGENCYS = "govAgencys";
	public static int getUserByIdDefault() {

		return 0;
	}

	public static String buildEmpFileSign(String uuid, long groupId) {

		return "/c/document_library/get_file?uuid=" + uuid + "&groupId=" +
			groupId;
	}

	public static String buildEmpAvatar(
		User user, long portraitId, String tokenId)
		throws PortalException {

		return "/image/user_" +
			((user != null) && user.isFemale() ? "female" : "male") +
			"_portrait?img_id=" + portraitId + "&t=" + tokenId;
	}
}
