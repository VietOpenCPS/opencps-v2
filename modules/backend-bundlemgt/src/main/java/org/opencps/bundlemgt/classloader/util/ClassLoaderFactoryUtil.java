package org.opencps.bundlemgt.classloader.util;

import java.io.File;
import java.util.Map;

import org.opencps.bundlemgt.classloader.ExcelReportClassLoader;
import org.opencps.bundlemgt.classloader.FeedbackClassLoader;
import org.opencps.bundlemgt.classloader.JasperClassLoader;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;

/**
 * @author trungnt
 */
public class ClassLoaderFactoryUtil {

	private static Log _log = LogFactoryUtil.getLog(ClassLoaderFactoryUtil.class);

	public static String exportFileByJasperTemplate(String jrxmlTemplate, String jsonData,
			Map<String, Object> parameters) {

		JasperClassLoader classLoader = new JasperClassLoader();

		try {

			File file = FileUtil.createTempFile("pdf");

			Object object = classLoader.getCreateReportFileMethod().invoke(
					classLoader.getClassLoader(String.class.getName()), jrxmlTemplate, jsonData, parameters,
					file.getCanonicalPath());

			if (object != null) {
				return (String) object;
			}

			return StringPool.BLANK;

		} catch (Exception e) {
			_log.error(e);
			return StringPool.BLANK;
		}

	}

	public static String exportFileByExcelTemplate(String templateFilePath, Map<String, Object> parameters) {

		ExcelReportClassLoader classLoader = new ExcelReportClassLoader();

		try {

			Object object = classLoader.getCreateReportFileMethod()
					.invoke(classLoader.getClassLoader(String.class.getName()), templateFilePath, parameters);

			if (object != null) {
				return (String) object;
			}

			return StringPool.BLANK;

		} catch (Exception e) {
			_log.error(e);
			return StringPool.BLANK;
		}
	}

	public static Object getListComments(long groupId, String className, String classPK) {
		try {
			FeedbackClassLoader classLoader = new FeedbackClassLoader();
			Object object = classLoader.getComments().invoke(classLoader.getClassLoader(String.class.getName()),
					groupId, className, classPK);
			if (object != null) {
				return object;
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}

}
