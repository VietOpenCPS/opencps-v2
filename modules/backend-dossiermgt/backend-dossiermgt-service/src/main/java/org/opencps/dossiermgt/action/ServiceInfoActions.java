package org.opencps.dossiermgt.action;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;

import org.opencps.dossiermgt.model.ServiceFileTemplate;
import org.opencps.dossiermgt.model.ServiceInfo;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;

public interface ServiceInfoActions {

	public JSONObject getServiceInfos(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext);

	public ServiceInfo updateServiceInfo(long userId, long groupId, long serviceInfoId, String serviceCode,
			String serviceName, String processText, String methodText, String dossierText, String conditionText,
			String durationText, String applicantText, String resultText, String regularText, String feeText,
			String administrationCode, String domainCode, int maxLevel, boolean public_, ServiceContext serviceContext)
			throws PortalException;

	public ServiceInfo getByCode(String serviceCode, long groupId);

	public ServiceInfo getById(long serviceInfoId);

	public ServiceInfo removeServiceInfo(long serviceInfoId) throws PortalException;

	/**
	 * Get all ServiceFileTemplate of ServiceInfo
	 * 
	 * @author khoavu
	 * @param serviceInfoId
	 * @return
	 */
	public JSONObject getServiceFileTemplate(long serviceInfoId);

	public ServiceFileTemplate updateServiceFileTemplate(long userId, long groupId, long folderId, long serviceInfoId,
			String fileTemplateNo, String templateName, String sourceFileName, InputStream inputStream,
			ServiceContext serviceContext) throws PortalException, IOException;

	public ServiceFileTemplate removeServiceFileTemplate(long serviceInfoId, String fileTemplateNo)
			throws PortalException;

	public ServiceFileTemplate addServiceFileTemplate(long userId, long groupId, long serviceInfoId,
			String fileTemplateNo, String templateName, String fileName, InputStream is, String fileType, int fileSize,
			ServiceContext serviceContext) throws PortalException, IOException;

	public JSONObject getStatisticByAdministration(ServiceContext context, long groupId)  throws ParseException, SearchException;

	public JSONObject getStatisticByDomain(ServiceContext context, long groupId)  throws ParseException, SearchException;

	public JSONObject getStatisticByLevel(ServiceContext context, long groupId) throws ParseException, SearchException;

}
