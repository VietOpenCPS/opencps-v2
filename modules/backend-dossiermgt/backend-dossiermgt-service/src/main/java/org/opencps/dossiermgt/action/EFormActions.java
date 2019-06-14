package org.opencps.dossiermgt.action;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.Date;
import java.util.LinkedHashMap;

import org.opencps.dossiermgt.model.EForm;

public interface EFormActions {

	public JSONObject getEFormList(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext);

	public EForm updateEForm(long userId, long groupId, long eFormId, String eFormNo, long serviceInfoId,
			String fileTemplateNo, String eFormName, Long formScriptFileId, Long formReportFileId, String eFormData,
			String email, String secret, ServiceContext serviceContext);

	public EForm updateDataByEFormNo(long eFormId, String eFormData, ServiceContext serviceContext);

	public EForm deleteEFormById(long eFormId, ServiceContext serviceContext);


}
