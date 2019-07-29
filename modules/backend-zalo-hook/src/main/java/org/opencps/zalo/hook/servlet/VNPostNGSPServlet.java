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

import org.osgi.service.component.annotations.Component;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ContentTypes;

import vn.mitc.ngsp.sdk.VNPost_N_GSP.IToken;
import vn.mitc.ngsp.sdk.models.MToken;

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
	"osgi.http.whiteboard.servlet.pattern=/vnposttest/*"
}, service = Servlet.class)

public class VNPostNGSPServlet extends HttpServlet {

	@Override
	public void init()
		throws ServletException {

		if (_log.isInfoEnabled()) {
			_log.info("VNPostNGSPServlet init success");
		} else {
			System.out.println("VNPostNGSPServlet init success sys");
		}

		super.init();
	}

	@Override
	protected void doGet(
		HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		if (_log.isInfoEnabled()) {
			_log.info("VNPostNGSPServlet doGet");
		} else {
			System.out.println("VNPostNGSPServlet doGet sys");
		}

		try {
			Enumeration<String> enumeration = request.getParameterNames();
			Map<String, Object> modelMap = new HashMap<>();
			while (enumeration.hasMoreElements()) {
				String parameterName = enumeration.nextElement();
				modelMap.put(
					parameterName, request.getParameter(parameterName));
				_log.info(
					parameterName +
						"=" +
						request.getParameter(parameterName));
			}
			String tokenUrl = request.getParameter("tokenUrl");
			String consumer_key = request.getParameter("consumer_key");
			String secret_key = request.getParameter("secret_key");
			System.out.println(tokenUrl + consumer_key + secret_key);
			getToken(tokenUrl, consumer_key, secret_key);
		}
		catch (Exception e) {
			_log.error(e);
		}

		_writeSampleHTML(response);
	}

	@Override
	protected void doPost(
		HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		if (_log.isInfoEnabled()) {
			_log.info("VNPostNGSPServlet doPost");
		} else {
			System.out.println("VNPostNGSPServlet doPost sys");
		}

		try {
			doGet(request, response);
		}
		catch (Exception e) {
			_log.error(e);
		}

	}
	
	public static void getToken(String tokenUrl, String consumer_key, String secret_key) throws Exception {

		MToken token = IToken.getToken(tokenUrl, consumer_key, secret_key);

		System.out.println("Access token:" + token.getAccessToken());
		System.out.println("Token type:" + token.getTokenType());
		System.out.println("Expires in: " + token.getExpiresIn() + " ms");
	}

	/**
	 * Dummy contents
	 *
	 * @return dummy contents string
	 */
	private String _generateSampleHTML() {

		StringBuffer sb = new StringBuffer();

		sb.append("<html>");
		sb.append("<head><title>VNPOST TEST</title></head>");
		sb.append("<body>");
		sb.append("<h2>VNPostNGSPServlet</h2>");
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
		LogFactoryUtil.getLog(VNPostNGSPServlet.class);

	private static final long serialVersionUID = 1L;

}
