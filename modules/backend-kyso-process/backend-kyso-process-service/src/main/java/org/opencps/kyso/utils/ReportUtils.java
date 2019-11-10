
package org.opencps.kyso.utils;

import java.io.File;

import javax.portlet.ActionRequest;
import javax.portlet.ResourceRequest;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;

public class ReportUtils {

	public static final String PDF_DEFAULT_FILE_NAME = "moh_report_temp.pdf";
	public static final String XLS_DEFAULT_FILE_NAME = "moh_report_temp.xls";
	public static final String DEFAULT_FILE_NAME_BONUS = "report";

	private static Log log = LogFactoryUtil.getLog(ReportUtils.class);

	public static String getTemplateReportFilePath(
		ResourceRequest resourceRequest, String fileName) {

		return resourceRequest.getPortletSession().getPortletContext().getRealPath(
			StringPool.FORWARD_SLASH).replace(
				StringPool.FORWARD_SLASH, File.separator).replace(
					File.separator + StringPool.PERIOD, StringPool.BLANK) +
			DEFAULT_FILE_NAME_BONUS + File.separator + fileName;
	}

	public static String getTemplateReportFilePath(ActionRequest request) {

		return request.getPortletSession().getPortletContext().getRealPath(
			StringPool.FORWARD_SLASH).replace(
				StringPool.FORWARD_SLASH, File.separator).replace(
					File.separator + StringPool.PERIOD, StringPool.BLANK);

	}

	public static String getSubTemplatePath(ResourceRequest resourceRequest) {

		return resourceRequest.getPortletSession().getPortletContext().getRealPath(
			StringPool.FORWARD_SLASH).replace(
				StringPool.FORWARD_SLASH, File.separator).replace(
					File.separator + StringPool.PERIOD, StringPool.BLANK) +
			DEFAULT_FILE_NAME_BONUS + File.separator;
	}

	public static String getTemplateReportFilePath(ResourceRequest request) {

		return request.getPortletSession().getPortletContext().getRealPath(
			StringPool.FORWARD_SLASH).replace(
				StringPool.FORWARD_SLASH, File.separator).replace(
					File.separator + StringPool.PERIOD, StringPool.BLANK);

	}

}
