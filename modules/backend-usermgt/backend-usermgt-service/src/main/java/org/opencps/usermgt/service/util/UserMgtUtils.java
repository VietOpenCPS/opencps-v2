package org.opencps.usermgt.service.util;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.service.ApplicantLocalServiceUtil;

public class UserMgtUtils {

	private static Log _log = LogFactoryUtil.getLog(UserMgtUtils.class);

	public static Date getDefaultBrithday() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy hh:MM:ss");
			return sdf.parse("01/01/1971 00:00::00");
		} catch (Exception e) {
			_log.error(e);
			return new Date();
		}
	}

	public static Date convertDate(String dateString) throws ParseException {
		Date output = null;

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

		output = sdf.parse(dateString);

		return output;
	}

	public static Role getRoleApplicant(long companyId, String roleName) {
		Role roleDefault = null;

		try {
			roleDefault = RoleLocalServiceUtil.getRole(companyId, ServiceProps.APPLICANT_ROLE_NAME);

		} catch (Exception e) {
			_log.error(e);
			roleDefault = RoleLocalServiceUtil.createRole(CounterLocalServiceUtil.increment(Role.class.getName()));

			roleDefault.setName(ServiceProps.APPLICANT_ROLE_NAME);
		}

		return roleDefault;
	}

	public static class SplitName {
		public SplitName(String fullName, String lastName) {

			_firstName = StringPool.BLANK;
			_lastName = StringPool.BLANK;
			_midName = StringPool.BLANK;

			if (Validator.isNotNull(fullName)) {
				this.setLastName(lastName);
				this.setFirstName(fullName);
				this.setMidName(StringPool.BLANK);
			}
		}

		public String getFirstName() {

			return _firstName;
		}

		public void setFirstName(String firstName) {

			this._firstName = firstName;
		}

		public String getLastName() {

			return _lastName;
		}

		public void setLastName(String lastName) {

			this._lastName = lastName;
		}

		public String getMidName() {

			return _midName;
		}

		public void setMidName(String midName) {

			this._midName = midName;
		}

		private String _firstName;
		private String _lastName;
		private String _midName;
	}

	public static SplitName splitName(String fullName, String lastName) {

		return new SplitName(fullName, lastName);
	};
	
	public static Applicant getApplicant(String id) {
		Applicant applicant = null;
		
		applicant = ApplicantLocalServiceUtil.fetchByEmail(id);
		
		if (Validator.isNull(applicant)) {
			applicant = ApplicantLocalServiceUtil.fetchByTelNo(id);
		}
		
		if (Validator.isNull(applicant)) {
			applicant = ApplicantLocalServiceUtil.fetchByAppId(id);
		}
		
		return applicant;
	}
}
