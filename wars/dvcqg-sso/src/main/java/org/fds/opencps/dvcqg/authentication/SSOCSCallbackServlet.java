
package org.fds.opencps.dvcqg.authentication;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author trungnt
 */
public class SSOCSCallbackServlet extends HttpServlet {

	/**
	 * Default constructor.
	 */

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println(request.getRequestURI() + "=========================request.getRequestURI();");
		Map<String, String[]> params = request.getParameterMap();
		for(Entry<String, String[]> entry: params.entrySet()) {
			System.out.println(entry.getKey() + entry.getValue()[0] + "===================================");
		}
		String code = request.getParameter("code");

		String state = request.getParameter("state");
		
		String data = request.getParameter("data");

		request.setAttribute("code", code);
		request.setAttribute("state", state);
		request.setAttribute("redirect", new String(Base64.getDecoder().decode(data.getBytes())));
		request.getRequestDispatcher("/ssocs_index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.getWriter().write("NO CONTENT!");
	}
	
}
