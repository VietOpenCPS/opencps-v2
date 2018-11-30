package org.opencps.api.controller.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;

import org.opencps.api.workingunit.model.WorkingUnitModel;
import org.opencps.datamgt.constants.WorkspaceTerm;
import org.opencps.usermgt.constants.WorkingUnitTerm;
import org.opencps.usermgt.model.WorkingUnit;

import backend.utils.APIDateTimeUtils;

public class WorkingUnitUtils {

	public static List<WorkingUnitModel> mapperWorkingUnitList(List<Document> listDocument) {

		List<WorkingUnitModel> results = new ArrayList<>();

		try {

			WorkingUnitModel ett = null;

			for (Document document : listDocument) {
				ett = new WorkingUnitModel();

				ett.setWorkingUnitId(Long.valueOf(document.get("entryClassPK")));
				ett.setCreateDate(Validator.isNotNull(document.getDate(WorkspaceTerm.CREATE_DATE)) ? APIDateTimeUtils
						.convertDateToString(document.getDate(WorkspaceTerm.CREATE_DATE), APIDateTimeUtils._TIMESTAMP)
						: StringPool.BLANK);
				ett.setModifiedDate(
						Validator.isNotNull(document.getDate("modified")) ? APIDateTimeUtils.convertDateToString(
								document.getDate("modified"), APIDateTimeUtils._TIMESTAMP) : StringPool.BLANK);

				ett.setName(document.get(WorkingUnitTerm.NAME));
				ett.setEnName(document.get(WorkingUnitTerm.NAME));
				ett.setGovAgencyCode(document.get(WorkingUnitTerm.GOV_AGENCY_CODE));
				ett.setParentWorkingUnitId(Long.valueOf(document.get(WorkingUnitTerm.PARENT_WORKING_UNIT_ID)));
				ett.setAddress(document.get(WorkingUnitTerm.ADDRESS));
				ett.setTelNo(document.get(WorkingUnitTerm.TEL_NO));
				ett.setFaxNo(document.get(WorkingUnitTerm.FAX_NO));
				ett.setEmail(document.get(WorkingUnitTerm.EMAIL));
				ett.setWebsite(document.get(WorkingUnitTerm.WEBSITE));
				ett.setSibling(GetterUtil.get(document.get(WorkingUnitTerm.SIBLING), 0));
				ett.setTreeIndex(document.get(WorkingUnitTerm.TREEINDEX));
				ett.setLevel(StringUtil.count(document.get(WorkingUnitTerm.TREEINDEX), StringPool.PERIOD));

				results.add(ett);
			}

		} catch (Exception e) {
			_log.error(e);
		}

		return results;
	}

	public static WorkingUnitModel mapperWorkingUnitModel(WorkingUnit workingUnit) {

		WorkingUnitModel ett = new WorkingUnitModel();

		try {

			ett.setWorkingUnitId(workingUnit.getWorkingUnitId());
			ett.setCreateDate(Validator.isNotNull(workingUnit.getCreateDate())
					? APIDateTimeUtils.convertDateToString(workingUnit.getCreateDate(), APIDateTimeUtils._TIMESTAMP)
					: StringPool.BLANK);
			ett.setModifiedDate(Validator.isNotNull(workingUnit.getModifiedDate())
					? APIDateTimeUtils.convertDateToString(workingUnit.getModifiedDate(), APIDateTimeUtils._TIMESTAMP)
					: StringPool.BLANK);
			ett.setName(workingUnit.getName());
			ett.setEnName(workingUnit.getEnName());
			ett.setGovAgencyCode(workingUnit.getGovAgencyCode());
			ett.setParentWorkingUnitId(workingUnit.getParentWorkingUnitId());
			ett.setAddress(workingUnit.getAddress());
			ett.setTelNo(workingUnit.getTelNo());
			ett.setFaxNo(workingUnit.getFaxNo());
			ett.setEmail(workingUnit.getEmail());
			ett.setWebsite(workingUnit.getWebsite());
			ett.setSibling(GetterUtil.get(workingUnit.getSibling(), 0));
			ett.setTreeIndex(workingUnit.getTreeIndex());
			ett.setLevel(StringUtil.count(workingUnit.getTreeIndex(), StringPool.PERIOD));

		} catch (Exception e) {
			_log.error(e);
		}

		return ett;
	}

	static Log _log = LogFactoryUtil.getLog(WorkingUnitUtils.class);
}
