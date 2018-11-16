package org.opencps.api.controller.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.opencps.api.jobpos.model.JobposModel;
import org.opencps.api.jobpos.model.JobposPermissionModel;
import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.opencps.usermgt.constants.JobPosTerm;
import org.opencps.usermgt.model.JobPos;
import org.opencps.usermgt.service.JobPosLocalServiceUtil;

import backend.auth.api.keys.Constants;
import backend.auth.api.keys.ModelNameKeys;
import backend.utils.APIDateTimeUtils;

public class JobposUtils {

	public static long getTotal(long groupId) {
		long result = 0;
		
		try {

			DictCollection dictCollection = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(Constants.CHECKLIST_TYPE, groupId);
			
			List<DictItem> list = DictItemLocalServiceUtil.findByF_dictCollectionId(dictCollection.getDictCollectionId());
			
			result = list.size();
			
		} catch (Exception e) {
			_log.error(e);
		}

		return result;
	}
	
	
	public static List<JobposModel> mapperJobposList(List<Document> listDocument) {

		List<JobposModel> results = new ArrayList<>();

		try {

			JobposModel ett = null;

			for (Document document : listDocument) {
				ett = new JobposModel();

				ett.setJobPosId(Long.valueOf(document.get("entryClassPK")));
				ett.setCreateDate(Validator.isNotNull(document.getDate(JobPosTerm.CREATE_DATE)) ? APIDateTimeUtils
						.convertDateToString(document.getDate(JobPosTerm.CREATE_DATE), APIDateTimeUtils._TIMESTAMP)
						: StringPool.BLANK);
				ett.setModifiedDate(
						Validator.isNotNull(document.getDate("modified")) ? APIDateTimeUtils.convertDateToString(
								document.getDate("modified"), APIDateTimeUtils._TIMESTAMP) : StringPool.BLANK);
				ett.setTitle(document.get(JobPosTerm.TITLE));
				ett.setDescription(document.get(JobPosTerm.DESCRIPTION));
				ett.setLeader(Integer.valueOf(document.get(JobPosTerm.LEADER)));
				ett.setRoleId(Long.valueOf(document.get(JobPosTerm.MAPPING_ROLE_ID)));
				ett.setJobPosCode(document.get(JobPosTerm.JOBPOS_CODE));

				results.add(ett);
			}

		} catch (Exception e) {
			_log.error(e);
		}

		return results;
	}

	
	public static JobposModel mapperJobposModel(JobPos jobPos) {

		JobposModel ett = new JobposModel();

		try {

			ett.setJobPosId(jobPos.getJobPosId());
			ett.setCreateDate(Validator.isNotNull(jobPos.getCreateDate())
					? APIDateTimeUtils.convertDateToString(jobPos.getCreateDate(), APIDateTimeUtils._TIMESTAMP)
					: StringPool.BLANK);
			ett.setModifiedDate(Validator.isNotNull(jobPos.getModifiedDate())
					? APIDateTimeUtils.convertDateToString(jobPos.getModifiedDate(), APIDateTimeUtils._TIMESTAMP)
					: StringPool.BLANK);
			ett.setTitle(jobPos.getTitle());
			ett.setDescription(jobPos.getDescription());
			// ett.setWorkingUnitId(jobPos.getWorkingUnitId());

			// WorkingUnit workingUnit =
			// WorkingUnitLocalServiceUtil.fetchWorkingUnit(jobPos.getWorkingUnitId());
			//
			// String workingUnitName = StringPool.BLANK;
			//
			// if (Validator.isNotNull(workingUnit)) {
			//
			// workingUnitName = workingUnit.getName();
			//
			// }
			//
			// ett.setWorkingUnitName(workingUnitName);
			ett.setLeader(jobPos.getLeader());
			ett.setRoleId(jobPos.getMappingRoleId());

		} catch (Exception e) {
			_log.error(e);
		}

		return ett;
	}

	static Log _log = LogFactoryUtil.getLog(JobposUtils.class);

	public static List<JobposPermissionModel> mapperJobposPermissionsList(String[] listPermissions, long userId, long jobPposId,
			ServiceContext serviceContext) {
		
		serviceContext.setUserId(userId);
		
		List<JobposPermissionModel> results = new ArrayList<>();

//		BackendAuthImpl authImpl = new BackendAuthImpl();

		try {
			
			JobPos jobPos = JobPosLocalServiceUtil.fetchJobPos(jobPposId);
			
			JobposPermissionModel ett = null;

			for (String actionKey : listPermissions) {
				ett = new JobposPermissionModel();

				ett.setActionId(actionKey);
				ett.setActionName(LanguageUtil.get(locale, actionKey));

				long mappingRoleId = Validator.isNotNull(jobPos)?jobPos.getMappingRoleId():0;
				
				boolean selected = ResourcePermissionLocalServiceUtil.hasResourcePermission(
						serviceContext.getCompanyId(), ModelNameKeys.WORKINGUNIT_MGT_CENTER,
						ResourceConstants.SCOPE_INDIVIDUAL,
						String.valueOf(mappingRoleId), mappingRoleId,
						actionKey);
						
//						authImpl.userHasResource(serviceContext, ModelNameKeys.WORKINGUNIT_MGT_CENTER,
//						actionKey);
				
				ett.setSelected(selected);

				results.add(ett);
			}

		} catch (Exception e) {
			_log.error(e);
		}

		return results;
	}
	
	public static JobposPermissionModel mapperJobposPermissionModel(String actionId) {

		JobposPermissionModel ett = new JobposPermissionModel();

		try {

			ett.setActionId(actionId);
			ett.setActionName(LanguageUtil.get(locale, actionId));
			ett.setSelected(Boolean.TRUE);

		} catch (Exception e) {
			_log.error(e);
		}

		return ett;
	}
	
	public static final Locale locale = new Locale("vi", "VN");
}
