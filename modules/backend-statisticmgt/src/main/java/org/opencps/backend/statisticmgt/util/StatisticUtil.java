package org.opencps.backend.statisticmgt.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.opencps.backend.statisticmgt.constant.Constants;
import org.opencps.backend.statisticmgt.constant.PropValues;
import org.opencps.backend.statisticmgt.exception.TemplateDirException;
import org.opencps.backend.statisticmgt.exception.TemplateFileExeption;

/**
 * @author trungnt
 *
 */
public class StatisticUtil {
	public static JSONObject createResponseSchema(long groupId, String fromDate, String toDate, int[] originalities,
			String[] domainCodes, String[] govAgencyCodes, String[] serviceCodes, String[] dossierStatus, int type,
			String subType, String... msg) {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		JSONObject condition = JSONFactoryUtil.createJSONObject();

		condition.put(Constants.GROUPID, groupId);
		condition.put(Constants.FROM_DATE, fromDate);
		condition.put(Constants.TO_DATE, toDate);
		condition.put(Constants.ORIGINALITIES, StringUtil.merge(originalities));
		condition.put(Constants.DOMAIN_CODES, StringUtil.merge(domainCodes));
		condition.put(Constants.DOSSIER_STATUS, StringUtil.merge(dossierStatus));

		result.put(Constants.CONDITIONS, condition);
		result.put(Constants.TOTAL, 0);
		result.put(Constants.DATA, JSONFactoryUtil.createJSONArray());
		result.put(Constants.TYPE, type);
		result.put(Constants.SUBTYPE, subType);

		if (msg != null) {
			result.put(Constants.MESSAGES, StringUtil.merge(msg));
		}

		return result;
	}

	public static String getTemplateFilePath(String fileName) throws Exception {

		String dir = PropsUtil.get(PropsKeys.LIFERAY_HOME) + PropValues.TEMPLATES_REPORTS_DIR;

		Path path = Paths.get(dir);

		if (!Files.exists(path)) {
			throw new TemplateDirException(dir + " does not exist.");
		}

		String filePath = dir + StringPool.SLASH + fileName;

		File file = new File(filePath);
		if (!file.exists()) {
			throw new TemplateFileExeption(filePath + " does not exist.");
		}

		return filePath;
	}
}
