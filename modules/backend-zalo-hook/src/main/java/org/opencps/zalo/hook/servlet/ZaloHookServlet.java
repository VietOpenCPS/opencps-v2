
package org.opencps.zalo.hook.servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.opencps.zalo.hook.constants.ZaloHookConstantKeys;
import org.opencps.zalo.hook.utils.ZaloMapUtils;
import org.osgi.service.component.annotations.Component;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author thanhnv
 */
@Component(immediate = true, property = {
	"osgi.http.whiteboard.context.path=/",
	"osgi.http.whiteboard.servlet.pattern=" + ZaloHookConstantKeys.SERVLET_URL
}, service = Servlet.class)

public class ZaloHookServlet extends HttpServlet {

	@Override
	public void init()
		throws ServletException {

		if (_log.isInfoEnabled()) {
			_log.info("ZaloHookServlet init success");
		}
		else {
			System.out.println("ZaloHookServlet init success sys");
		}

		super.init();
	}

	@Override
	protected void doGet(
		HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		if (_log.isInfoEnabled()) {
			_log.info("ZaloHookServlet doGet opencps");
		}
		else {
			System.out.println("ZaloHookServlet doGet opencps Sys");
		}

		try {

			Enumeration<String> enumeration = request.getParameterNames();
			Map<String, Object> zaloInfo = new HashMap<>();
			while (enumeration.hasMoreElements()) {
				String parameterName = enumeration.nextElement();
				zaloInfo.put(
					parameterName, request.getParameter(parameterName));
				_log.info(
					parameterName + "=" + request.getParameter(parameterName));
				System.out.println(
					parameterName + "=" + request.getParameter(parameterName));
			}

			ZaloMapUtils zaloMapUtils = new ZaloMapUtils(zaloInfo);
			if (Validator.isNotNull(
				zaloInfo.get(ZaloHookConstantKeys.ZALO_PARAM_EVENT)) &&
				zaloInfo.get(ZaloHookConstantKeys.ZALO_PARAM_EVENT).equals(
					ZaloHookConstantKeys.ZALO_ACTION_UNFOLLOW)) {

				result = zaloMapUtils.unfollow();
			}
			else if (Validator.isNotNull(
				zaloInfo.get(ZaloHookConstantKeys.ZALO_PARAM_EVENT)) &&
				zaloInfo.get(ZaloHookConstantKeys.ZALO_PARAM_EVENT).equals(
					ZaloHookConstantKeys.ZALO_ACTION_FOLLOW)) {

				result = zaloMapUtils.follow();
			}
			else if (Validator.isNotNull(
				zaloInfo.get(ZaloHookConstantKeys.ZALO_PARAM_EVENT)) &&
				zaloInfo.get(ZaloHookConstantKeys.ZALO_PARAM_EVENT).equals(
					ZaloHookConstantKeys.ZALO_ACTION_SEND_MSG)) {

				result = zaloMapUtils.sendmsg();
			}
			else if (Validator.isNotNull(
				zaloInfo.get(ZaloHookConstantKeys.ZALO_PARAM_EVENT)) &&
				zaloInfo.get(ZaloHookConstantKeys.ZALO_PARAM_EVENT).equals(
					ZaloHookConstantKeys.ZALO_ACTION_ADD_PHONE)) {

				result = zaloMapUtils.addPhone();
			}
			else {

				_log.info("======action invalid======");
			}

		}
		catch (Exception e) {
			_log.error(e);
		}

		_writeSampleHTML(response, result);
	}

	@Override
	protected void doPost(
		HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		if (_log.isInfoEnabled()) {
			_log.info("ZaloHookServlet doPost");
		}
		else {
			System.out.println("ZaloHookServlet doPost Sys");
		}

		try {
			doGet(request, response);
		}
		catch (Exception e) {
			_log.error(e);
		}

	}

	/**
	 * Dummy contents
	 *
	 * @return dummy contents string
	 */
	private String _generateSampleHTML() {

		StringBuffer sb = new StringBuffer();

		sb.append("<html>");
		sb.append("<head><title>Zalo o Hook</title></head>");
		sb.append("<body>");
		sb.append("<h2>ZaloHookServlet</h2>");
		sb.append("</body>");
		sb.append("</html>");

		return new String(sb);
	}

	/**
	 * Write sample HTML
	 *
	 * @param resp
	 */
	private void _writeSampleHTML(HttpServletResponse resp, JSONObject result) {

		resp.setCharacterEncoding(StringPool.UTF8);
		resp.setContentType(ContentTypes.TEXT_HTML_UTF8);
		resp.setStatus(HttpServletResponse.SC_OK);

		try {
			ServletResponseUtil.write(resp, _generateSampleHTML());
		}
		catch (Exception e) {
			_log.warn(e.getMessage(), e);

			resp.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
		}
	}

	private static final Log _log =
		LogFactoryUtil.getLog(ZaloHookServlet.class);

	private static final long serialVersionUID = 1L;

}
