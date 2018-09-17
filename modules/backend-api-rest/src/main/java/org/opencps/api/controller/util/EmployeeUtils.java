package org.opencps.api.controller.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.ArrayList;
import java.util.List;

import org.opencps.api.employee.model.EmployeeAccountModel;
import org.opencps.api.employee.model.EmployeeJobposModel;
import org.opencps.api.employee.model.EmployeeModel;
import org.opencps.api.employee.model.MappingUser;
import org.opencps.usermgt.constants.EmployeeJobPosTerm;
import org.opencps.usermgt.constants.EmployeeTerm;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.model.EmployeeJobPos;
import org.opencps.usermgt.model.JobPos;
import org.opencps.usermgt.model.WorkingUnit;
import org.opencps.usermgt.service.EmployeeJobPosLocalServiceUtil;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;
import org.opencps.usermgt.service.JobPosLocalServiceUtil;
import org.opencps.usermgt.service.WorkingUnitLocalServiceUtil;

import backend.utils.APIDateTimeUtils;

public class EmployeeUtils {

	public static List<EmployeeModel> mapperEmployeeList(List<Document> listDocument) {

		List<EmployeeModel> results = new ArrayList<>();

		try {

			EmployeeModel ett = null;

			for (Document document : listDocument) {
				ett = new EmployeeModel();

				ett.setEmployeeId(Long.valueOf(document.get("entryClassPK")));
				ett.setUserId(Long.valueOf(document.get(EmployeeTerm.USER_ID)));
				ett.setUserName(document.get(EmployeeTerm.USER_NAME));
				ett.setCreateDate(Validator.isNotNull(document.getDate(EmployeeTerm.CREATE_DATE)) ? APIDateTimeUtils
						.convertDateToString(document.getDate(EmployeeTerm.CREATE_DATE), APIDateTimeUtils._TIMESTAMP)
						: StringPool.BLANK);
				ett.setModifiedDate(
						Validator.isNotNull(document.getDate("modified")) ? APIDateTimeUtils.convertDateToString(
								document.getDate("modified"), APIDateTimeUtils._TIMESTAMP) : StringPool.BLANK);

				ett.setEmployeeNo(document.get(EmployeeTerm.EMPLOYEE_NO));
				ett.setFullName(document.get(EmployeeTerm.FULL_NAME));
				ett.setTitle(document.get(EmployeeTerm.TITLE));
				ett.setGender(document.get(EmployeeTerm.GENDER));
				ett.setBirthdate(document.get(EmployeeTerm.BIRTH_DATE));
				ett.setTelNo(document.get(EmployeeTerm.TELNO));
				ett.setMobile(document.get(EmployeeTerm.MOBILE));
				ett.setEmail(document.get(EmployeeTerm.EMAIL));
				ett.setWorkingStatus(document.get(EmployeeTerm.WORKING_STATUS));
				ett.setBirthdate(
						Validator.isNotNull(document.get(EmployeeTerm.BIRTH_DATE)) ? APIDateTimeUtils.convertDateToString(
								document.getDate(EmployeeTerm.BIRTH_DATE), APIDateTimeUtils._TIMESTAMP) : StringPool.BLANK);
				// TODO
				ett.setPermission("read");

				// mapping userID
				long mappingUserId = GetterUtil.get(document.get(EmployeeTerm.MAPPING_USER_ID), 0);

				User user = UserLocalServiceUtil.fetchUser(mappingUserId);

				MappingUser mappingUser = new MappingUser();

				if (Validator.isNotNull(user)) {
					mappingUser.setUserId(user.getUserId());
					mappingUser.setScreenName(user.getScreenName());
					
					boolean lock = false;
					
					if(user.getStatus() == WorkflowConstants.STATUS_DENIED){
						lock = true;
					}
					
					mappingUser.setLocking(lock);
				}

				ett.getMappingUser().add(mappingUser);

				// Roles
				ett.setWorkingUnitName(document.get(EmployeeTerm.WORKING_UNIT_NAME));
				ett.setJobPosTitle(document.get(EmployeeTerm.JOB_POS_TITLE));

				results.add(ett);
			}

		} catch (Exception e) {
			_log.error(e);
		}

		return results;
	}

	public static List<EmployeeJobposModel> mapperEmployeeJobposList(List<Document> listDocument, long employeeId) {

		List<EmployeeJobposModel> results = new ArrayList<>();

		try {
			Employee employee = EmployeeLocalServiceUtil.fetchEmployee(employeeId);
			
			EmployeeJobposModel ett = null;

			for (Document document : listDocument) {
				ett = new EmployeeJobposModel();

				ett.setEmployeeJobPosId(Long.valueOf(document.get("entryClassPK")));
				ett.setWorkingUnitId(Long.valueOf(document.get(EmployeeJobPosTerm.WORKING_UNIT_ID)));
				ett.setWorkingUnitName(document.get(EmployeeJobPosTerm.WORKING_UNIT_NAME));
				ett.setJobPosId(Long.valueOf(document.get(EmployeeJobPosTerm.JOBPOST_ID)));
				ett.setJobPosTitle(document.get(EmployeeJobPosTerm.JOBPOST_TITLE));
				ett.setLeader(GetterUtil.get(document.get(EmployeeJobPosTerm.LEADER), 0));
				
				boolean isMain = false;
				
				if(Long.valueOf(document.get(EmployeeJobPosTerm.JOBPOST_ID)) == employee.getMainJobPostId() && employee.getMainJobPostId() > 0){
					
					isMain = true;
					
				}
				
				ett.setMainJobPos(isMain);

				results.add(ett);
			}

		} catch (Exception e) {
			_log.error(e);
		}

		return results;
	}

	public static EmployeeJobposModel mapperEmployeeJobposModel(EmployeeJobPos employeeJobPos) {

		EmployeeJobposModel ett = new EmployeeJobposModel();

		try {

			ett.setEmployeeJobPosId(employeeJobPos.getEmployeeJobPosId());
			ett.setWorkingUnitId(employeeJobPos.getWorkingUnitId());

			WorkingUnit workingUnit = WorkingUnitLocalServiceUtil.fetchWorkingUnit(employeeJobPos.getWorkingUnitId());
			JobPos jobPos = JobPosLocalServiceUtil.fetchJobPos(employeeJobPos.getJobPostId());

			String workingUnitName = Validator.isNotNull(workingUnit) ? workingUnit.getName() : StringPool.BLANK;

			ett.setWorkingUnitName(workingUnitName);

			if (Validator.isNotNull(jobPos)) {
				ett.setJobPosId(jobPos.getJobPosId());
				ett.setJobPosTitle(jobPos.getTitle());
				ett.setLeader(jobPos.getLeader());

				Employee employee = EmployeeLocalServiceUtil.fetchEmployee(employeeJobPos.getEmployeeId());

				boolean isMain = false;

				if (Validator.isNotNull(employee) && employee.getMainJobPostId() > 0
						&& employee.getMainJobPostId() == jobPos.getJobPosId()) {
					isMain = true;
				}

				ett.setMainJobPos(isMain);
			}

			// Roles

		} catch (Exception e) {
			_log.error(e);
		}

		return ett;
	}

	public static EmployeeModel mapperEmployeeModel(Employee employee) {

		EmployeeModel ett = new EmployeeModel();

		try {

			ett.setEmployeeId(employee.getEmployeeId());
			ett.setUserId(employee.getUserId());
			ett.setUserName(employee.getUserName());
			ett.setCreateDate(Validator.isNotNull(employee.getCreateDate())
					? APIDateTimeUtils.convertDateToString(employee.getCreateDate(), APIDateTimeUtils._TIMESTAMP)
					: StringPool.BLANK);
			ett.setModifiedDate(Validator.isNotNull(employee.getModifiedDate())
					? APIDateTimeUtils.convertDateToString(employee.getModifiedDate(), APIDateTimeUtils._TIMESTAMP)
					: StringPool.BLANK);

			ett.setEmployeeNo(employee.getEmployeeNo());
			ett.setFullName(employee.getFullName());
			ett.setTitle(employee.getTitle());
			ett.setGender(String.valueOf(employee.getGender()));
			ett.setBirthdate(Validator.isNotNull(employee.getBirthdate())
					? APIDateTimeUtils.convertDateToString(employee.getBirthdate(), APIDateTimeUtils._TIMESTAMP)
					: StringPool.BLANK);
			ett.setTelNo(employee.getTelNo());
			ett.setMobile(employee.getMobile());
			ett.setEmail(employee.getEmail());
			ett.setWorkingStatus(String.valueOf(employee.getWorkingStatus()));
			ett.setBirthdate(Validator.isNotNull(employee.getBirthdate())
					? APIDateTimeUtils.convertDateToString(employee.getBirthdate(), APIDateTimeUtils._TIMESTAMP)
					: StringPool.BLANK);
			// TODO
			ett.setPermission("read");
			
			EmployeeJobPos employeeJobPos = EmployeeJobPosLocalServiceUtil.fetchByF_EmployeeId_jobPostId(
					employee.getGroupId(), employee.getEmployeeId(), employee.getMainJobPostId());
			
			long workingUnitId = Validator.isNotNull(employeeJobPos)?employeeJobPos.getWorkingUnitId():0;
			
			String workingUnitName = StringPool.BLANK;
			
			if(workingUnitId > 0){
				
				WorkingUnit workingUnit = WorkingUnitLocalServiceUtil.fetchWorkingUnit(workingUnitId);
				
				workingUnitName = Validator.isNotNull(workingUnit)?workingUnit.getName():StringPool.BLANK;
				
			}
			
			JobPos jobPos = JobPosLocalServiceUtil.fetchJobPos(employee.getMainJobPostId());
			
			String jobPosTitle = Validator.isNotNull(jobPos)?jobPos.getTitle():StringPool.BLANK;
			
			ett.setWorkingUnitName(workingUnitName);
			ett.setJobPosTitle(jobPosTitle);
			
			User user = UserLocalServiceUtil.fetchUser(employee.getMappingUserId());
			
			MappingUser mappingUser = new MappingUser();
			
			if(Validator.isNotNull(user)){
				mappingUser.setUserId(user.getUserId());
				mappingUser.setScreenName(user.getScreenName());
				
				boolean lock = false;
				
				if(user.getStatus() == WorkflowConstants.STATUS_DENIED){
					lock = true;
				}
				
				mappingUser.setLocking(lock);
				
			}
			
			ett.getMappingUser().add(mappingUser);
			
			
		} catch (Exception e) {
			_log.error(e);
		}

		return ett;
	}
	
	public static EmployeeAccountModel mapperEmployeeAccountModel(JSONObject jsonObject) {

		EmployeeAccountModel ett = new EmployeeAccountModel();

		try {

			if (Validator.isNotNull(jsonObject)) {
				
				ett.setScreenName(jsonObject.getString("screenName"));
				ett.setEmail(jsonObject.getString("email"));
				ett.setExist(jsonObject.getBoolean("exist"));
			}

			// Roles

		} catch (Exception e) {
			_log.error(e);
		}

		return ett;
	}

	static Log _log = LogFactoryUtil.getLog(EmployeeUtils.class);
}
