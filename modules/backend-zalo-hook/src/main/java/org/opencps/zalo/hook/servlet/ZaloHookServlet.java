
package org.opencps.zalo.hook.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.opencps.zalo.hook.constants.ZaloHookConstantKeys;
import org.opencps.zalo.hook.utils.ZaloMapUtilsV2;
import org.osgi.service.component.annotations.Component;

import com.liferay.petra.string.StringPool;
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

		_log.debug("ZaloHookServlet init success");

		super.init();
	}

//	@Override
//	protected void doGet(
//		HttpServletRequest request, HttpServletResponse response)
//		throws IOException, ServletException {
//
//		JSONObject result = JSONFactoryUtil.createJSONObject();
//
//		_log.debug("ZaloHookServlet doGet opencps");
//
//		try {
//
//			Enumeration<String> enumeration = request.getParameterNames();
//			Map<String, Object> zaloInfo = new HashMap<>();
//			while (enumeration.hasMoreElements()) {
//				String parameterName = enumeration.nextElement();
//				zaloInfo.put(
//					parameterName, request.getParameter(parameterName));
//				_log.debug(
//					parameterName + "=" + request.getParameter(parameterName));
//			}
//
//			ZaloMapUtils zaloMapUtils = new ZaloMapUtils(zaloInfo);
//			if (Validator.isNotNull(
//				zaloInfo.get(ZaloHookConstantKeys.ZALO_PARAM_EVENT)) &&
//				zaloInfo.get(ZaloHookConstantKeys.ZALO_PARAM_EVENT).equals(
//					ZaloHookConstantKeys.ZALO_ACTION_UNFOLLOW)) {
//
//				result = zaloMapUtils.unfollow();
//			}
//			else if (Validator.isNotNull(
//				zaloInfo.get(ZaloHookConstantKeys.ZALO_PARAM_EVENT)) &&
//				zaloInfo.get(ZaloHookConstantKeys.ZALO_PARAM_EVENT).equals(
//					ZaloHookConstantKeys.ZALO_ACTION_FOLLOW)) {
//
//				result = zaloMapUtils.follow();
//			}
//			else if (Validator.isNotNull(
//				zaloInfo.get(ZaloHookConstantKeys.ZALO_PARAM_EVENT)) &&
//				zaloInfo.get(ZaloHookConstantKeys.ZALO_PARAM_EVENT).equals(
//					ZaloHookConstantKeys.ZALO_ACTION_SEND_MSG)) {
//
//				result = zaloMapUtils.sendmsg();
//			}
//			else if (Validator.isNotNull(
//				zaloInfo.get(ZaloHookConstantKeys.ZALO_PARAM_EVENT)) &&
//				zaloInfo.get(ZaloHookConstantKeys.ZALO_PARAM_EVENT).equals(
//					ZaloHookConstantKeys.ZALO_ACTION_ADD_PHONE)) {
//
//				result = zaloMapUtils.addPhone();
//			}
//			else {
//
//				_log.debug("======action invalid======");
//			}
//
//		}
//		catch (Exception e) {
//			_log.error(e);
//		}
//
//		_writeSampleHTML(response, result);
//	}

	@Override
	protected void doPost(
		HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		_log.debug("ZaloHookServlet doPost");

		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;
		try {
			
			InputStream inputStream = request.getInputStream();
			char[] charBuffer = new char[1024];
			int bytesRead = -1;
			if (Validator.isNotNull(inputStream)) {
				
				bufferedReader =
								new BufferedReader(new InputStreamReader(inputStream));
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}

				ZaloMapUtilsV2.doAction(stringBuilder.toString());
			}
			
		}
		catch (IOException ex) {
			
			_log.error(ex);
		}
		finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				}
				catch (IOException ex) {
					_log.error(ex);
				}
			}
		}

		_writeSampleHTML(response, null);

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
