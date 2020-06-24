package org.graphql.api.controller.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.graphql.api.controller.impl.DeliverableService;
import org.opencps.usermgt.constants.UserTerm;
import org.opencps.usermgt.model.EmployeeJobPos;
import org.opencps.usermgt.model.JobPos;
import org.opencps.usermgt.service.EmployeeJobPosLocalServiceUtil;
import org.opencps.usermgt.service.JobPosLocalServiceUtil;

public class GraphQLUtils {

	private static Log _log = LogFactoryUtil.getLog(GraphQLUtils.class);

	public static final String OPENCPS_JWT_SECRET = "opencps.jwt.secret";

	public static String getJWTSecret() {
		String secretKey = PropsUtil.get(OPENCPS_JWT_SECRET);
		return Validator.isNotNull(secretKey) ? secretKey : "secret";
	}

	public static String buildTokenLogin (String userStr, long groupId) {
		try {
			if (Validator.isNotNull(userStr)) {
				JSONObject user = JSONFactoryUtil.createJSONObject(userStr);
				String secret = getJWTSecret();
				Algorithm algorithm = Algorithm.HMAC256(secret);
				long employeeId = user.getLong(UserTerm.CLASS_PK, 0);
				List<EmployeeJobPos> empJobPosList = EmployeeJobPosLocalServiceUtil
						.findByF_EmployeeId(employeeId);
				ArrayList<String> roles = new ArrayList<String>();
				for (EmployeeJobPos empJobPos : empJobPosList) {

					JobPos jobPos = JobPosLocalServiceUtil.fetchJobPos(empJobPos.getJobPostId());
					roles.add(jobPos.getJobPosCode());
				}
				String token = JWT.create()
						.withClaim("screenName", user.getString(UserTerm.SCREEN_NAME))
						.withClaim("fullName", user.getString(UserTerm.EMPLOYEE_FULLNAME))
						.withClaim("email", user.getString(UserTerm.EMPLOYEE_EMAIL))
						.withArrayClaim("role", roles.toArray(new String[0]))
						.sign(algorithm);
				return token;
			}
		} catch (Exception e) {

			_log.debug(e);
			e.printStackTrace();
		}
		return StringPool.BLANK;
	}

	public static String readGrapQL(String fileName) {

		String result = StringPool.BLANK;

		InputStream is = DeliverableService.class.getClassLoader().getResourceAsStream(fileName);
		
		try {
			result = IOUtils.toString(is, StandardCharsets.UTF_8);
			
		} catch (IOException e) {
			_log.debug(e);
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				_log.debug(e);
			}
		}

		return result;

	}

	public static String buildDeliverableSearchDataForm (String formDataKey) {

		String result = StringPool.BLANK;
		try {

			if (Validator.isNull(formDataKey)) {

				return result;
			}

			JSONObject formDataKeyObject = JSONFactoryUtil.createJSONObject(formDataKey);

			for (Iterator<String> iii = formDataKeyObject.keys(); iii.hasNext();) {
				
				String key = iii.next();
				result += " AND " + key + "_data: *" + formDataKeyObject.get(key) + "*";
			}
		}
		catch (Exception e) {
			_log.debug(e);
		}
		
		return result;
	}

}
