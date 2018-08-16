package org.opencps.usermgt.action;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.LinkedHashMap;

import org.opencps.usermgt.exception.DuplicateEmployeeEmailException;
import org.opencps.usermgt.exception.DuplicateEmployeeNoException;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.model.EmployeeJobPos;

import com.liferay.asset.kernel.exception.DuplicateCategoryException;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;

import backend.auth.api.exception.NotFoundException;
import backend.auth.api.exception.UnauthenticationException;
import backend.auth.api.exception.UnauthorizationException;

public interface EmployeeInterface {

	JSONObject getEmployees(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext);

	Employee create(long userId, long companyId, long groupId, String employeeNo, String fullName, String email,
			String gender, Date birthDate, String telNo, String mobile, String title, String workingStatus,
			Date recruitDate, Date leaveDate, ServiceContext serviceContext)
			throws NoSuchUserException, UnauthenticationException, UnauthorizationException,
			DuplicateEmployeeNoException, DuplicateEmployeeEmailException, PortalException;

	Employee update(long userId, long companyId, long groupId, long id, String employeeNo, String fullName,
			String email, String gender, Date birthDate, String telNo, String mobile, String title,
			String workingStatus, Date recruitDate, Date leaveDate, ServiceContext serviceContext)
			throws NoSuchUserException, NotFoundException, UnauthenticationException, UnauthorizationException,
			DuplicateEmployeeNoException, DuplicateEmployeeEmailException, PortalException;

	File getEmployeePhoto(long id, ServiceContext serviceContext);

	FileEntry getFileEntry(long id, ServiceContext serviceContext);

	File uploadEmployeePhoto(long userId, long companyId, long groupId, long id, InputStream inputStream,
			String fileName, String fileType, long fileSize, String string, String string2,
			ServiceContext serviceContext) throws NoSuchUserException, NotFoundException, UnauthenticationException,
			UnauthorizationException, DuplicateCategoryException, PortalException;

	EmployeeJobPos createEmployeeJobpos(long userId, long companyId, long groupId, long id, long workingUnitId,
			long jobPosId, Boolean valueOf, ServiceContext serviceContext) throws NoSuchUserException,
			UnauthenticationException, UnauthorizationException, DuplicateCategoryException, PortalException;

	EmployeeJobPos updateEmployeeJobpos(long userId, long companyId, long groupId, long id, long employeeJobPosId,
			long workingUnitId, long jobPosId, Boolean valueOf, ServiceContext serviceContext)
			throws NoSuchUserException, NotFoundException, UnauthenticationException, UnauthorizationException,
			DuplicateCategoryException, PortalException;

	JSONObject getEmployeeJobpos(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext);

	JSONObject createEmployeeAccount(long userId, long companyId, long groupId, long id, String screenName,
			String email, boolean exist, ServiceContext serviceContext) throws PortalException;

	JSONObject lockEmployeeAccount(long userId, long companyId, long groupId, long id, boolean locked,
			ServiceContext serviceContext) throws PortalException;
	
	JSONObject lockEmployeeAccount(Employee employee, boolean locked,
		ServiceContext serviceContext) throws PortalException;

	void validateExits(long userId, long companyId, long groupId, String employeeNo, String email,
			ServiceContext serviceContext) throws DuplicateEmployeeEmailException, DuplicateEmployeeNoException;

	void deleteEmployeeJobPos(long id, long employeeJobPosId, ServiceContext serviceContext)
			throws NoSuchUserException, DuplicateEmployeeEmailException, DuplicateEmployeeNoException, PortalException;

	void updateEmployeeDB(long userId, long groupId, String employeeNo, String fullname, String title, Integer gender,
			String birthdate, String telNo, String email, Integer workingStatus, String jobTitle, String roles,
			ServiceContext serviceContext) throws NoSuchUserException, UnauthenticationException,
			UnauthorizationException, DuplicateCategoryException, PortalException;

}
