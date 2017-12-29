package org.opencps.kyso.utils;

import java.io.File;

import javax.portlet.ActionRequest;
import javax.portlet.ResourceRequest;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class ReportUtils {
	
	public static final String PDF_DEFAULT_FILE_NAME = "moh_report_temp.pdf";
	public static final String XLS_DEFAULT_FILE_NAME = "moh_report_temp.xls";
	
	private static Log log = LogFactoryUtil.getLog(ReportUtils.class);
	
	public static String getTemplateReportFilePath(ResourceRequest resourceRequest, String fileName) {
		return resourceRequest.getPortletSession().getPortletContext().getRealPath("/").replace("/", File.separator)
				.replace(File.separator + ".", "") + "report" + File.separator + fileName;
	}

	public static String getTemplateReportFilePath(ActionRequest request) {
		return request.getPortletSession().getPortletContext().getRealPath("/").replace("/", File.separator).replace(File.separator + ".", "");
		
	}
	
	
	
	public static String getSubTemplatePath(ResourceRequest resourceRequest) {
		return resourceRequest.getPortletSession().getPortletContext().getRealPath("/").replace("/", File.separator)
				.replace(File.separator + ".", "") + "report" + File.separator ;
	}	
	
	public static String getTemplateReportFilePath(ResourceRequest request) {
		return request.getPortletSession().getPortletContext().getRealPath("/").replace("/", File.separator).replace(File.separator + ".", "");
		
	}
	
}
