
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
import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.StringPool;

/**
compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel", version: "2.0.0"
	compileOnly group: "com.liferay.portal", name: "com.liferay.util.taglib", version: "2.0.0"
	compileOnly group: "javax.portlet", name: "portlet-api", version: "2.0"
	compileOnly group: "javax.servlet", name: "javax.servlet-api", version: "3.0.1"
	compileOnly group: "jstl", name: "jstl", version: "1.2"
	compileOnly group: "org.osgi", name: "osgi.cmpn", version: "6.0.0"
	
 * */

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

		super.init();
	}

	@Override
	protected void doGet(
		HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		if (_log.isInfoEnabled()) {
			_log.info("ZaloHookServlet doGet");
		}

		try {
			_log.info(
				"============================================================");
			_log.info(
				"============================================================");
			_log.info(
				"============================================================");
			_log.info(
				"============================================================");
			Enumeration<String> enumeration = request.getParameterNames();
			Map<String, Object> modelMap = new HashMap<>();
			while (enumeration.hasMoreElements()) {
				String parameterName = enumeration.nextElement();
				modelMap.put(
					parameterName, request.getParameter(parameterName));
				_log.info(
					parameterName +
						"============================================================" +
						request.getParameter(parameterName));
			}
			_log.info(
				"============================================================");
			_log.info(
				"============================================================");
			_log.info(
				"============================================================");
			_log.info(
				"============================================================");
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		_writeSampleHTML(response);
	}

	@Override
	protected void doPost(
		HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		if (_log.isInfoEnabled()) {
			_log.info("ZaloHookServlet doPost");
		}

		doGet(request, response);

	}

	/**
	 * Dummy contents
	 *
	 * @return dummy contents string
	 */
	private String _generateSampleHTML() {

		StringBuffer sb = new StringBuffer();

		sb.append("<html>");
		sb.append("<head><title>Zalo Hook</title></head>");
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
	private void _writeSampleHTML(HttpServletResponse resp) {

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
