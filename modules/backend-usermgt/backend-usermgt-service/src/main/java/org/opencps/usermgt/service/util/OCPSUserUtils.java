package org.opencps.usermgt.service.util;

import java.util.ArrayList;
import java.util.List;

import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.service.ApplicantLocalServiceUtil;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.Validator;

import backend.auth.api.exception.NotFoundException;

public class OCPSUserUtils {

	public static final String APPLICANT_01 = "citizen";
	public static final String APPLICANT_02 = "business";
	public static final String EMPLOYEE_01 = "employee";
	public static final String EMPLOYEE_02 = "manager";

	public static List<String> getUserTypes(long groupId, long userId) throws PortalException {

		List<String> types = new ArrayList<String>();

		Applicant applicant = null;

		Employee employee = null;

		applicant = ApplicantLocalServiceUtil.fetchByMappingID(userId);

		employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, userId);

		if (Validator.isNotNull(applicant)) {
			types.add(applicant.getApplicantIdType());
		}

		if (Validator.isNotNull(employee)) {
			types.add(EMPLOYEE_01);
		}

		if (Validator.isNull(applicant) && Validator.isNull(employee)) {
			throw new NotFoundException("NotPermissionForUser");
		}
		
		return types;
	}
}
