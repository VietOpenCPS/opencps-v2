/**
 * Copyright 2000-present Liferay, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.opencps.servlet.filter;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.BaseFilter;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

/**
 * Dynamically deployable ServletFilter sample.
 * Observe the comments all over this module for more information and
 * TODOs when you create your own filter from this template
 *
 * @author Olaf Kock
 */
@Component(immediate = true, property = {

		// To figure out valid values for before-filter, look up Liferay's
		// WEB-INF/liferay-web.xml

		"before-filter=Auto Login Filter",
		//"dispatcher=REQUEST",
		"dispatcher=FORWARD",
		//"dispatcher=ASYNC",
		//"dispatcher=ERROR",
		//"dispatcher=INCLUDE",
		"servlet-context-name=", "servlet-filter-name=Blade Servlet Filter",
		"url-pattern=/web/guest/XrdAdapter/RestService/forward/*",
		"url-pattern=/web/guest/VXPAdapter/RestService/forward/*",
		"url-pattern=/web/guest/nhanhsdvcqg",
		"url-pattern=/web/guest/nhantbthuedvcqg",
		"url-pattern=/web/guest/addtthcKhuyenMai",
		"url-pattern=/web/guest/search/call",
		"url-pattern=/web/guest/downloadfile/*"

}, service = Filter.class)
public class OpenCPSServletFilter extends BaseFilter {

	@Override
	protected Log getLog() {
		return _log;
	}
	private final Integer LENGTH_URL_DOWNLOAD_FILE = 60;
	@Override
	protected void processFilter(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			FilterChain filterChain) throws Exception {
		httpServletRequest.setAttribute("USER_ID", PORTAL_DEFAULT_USERID);
		_log.info("===> httpServletRequest UserId " + httpServletRequest.getAttribute("USER_ID"));
		String requestURI = httpServletRequest.getRequestURI();
		if (Validator.isNotNull(requestURI)
				&& (requestURI.contains("/XrdAdapter/RestService/forward/addtthcKhuyenMai") || requestURI.contains("/VXPAdapter/RestService/forward/addtthcKhuyenMai") || requestURI.contains("addtthcKhuyenMai"))) {
			httpServletRequest.getRequestDispatcher("/o/rest/v2/nps/createdossierfromdvcqg").forward(httpServletRequest,
					httpServletResponse);
		}

		if (Validator.isNotNull(requestURI)
				&& (requestURI.contains("/XrdAdapter/RestService/forward/nhanhsdvcqg") || requestURI.contains("/VXPAdapter/RestService/forward/nhanhsdvcqg") || requestURI.contains("nhanhsdvcqg"))) {
			httpServletRequest.getRequestDispatcher("/o/rest/v2/nps/nhanhsdvcqg").forward(httpServletRequest,
					httpServletResponse);
		}
		if (Validator.isNotNull(requestURI)
				&& (requestURI.contains("/XrdAdapter/RestService/forward/nhantbthuedvcqg") || requestURI.contains("/VXPAdapter/RestService/forward/nhantbthuedvcqg") || requestURI.contains("nhantbthuedvcqg"))) {
			httpServletRequest.getRequestDispatcher("/o/rest/v2/nps/nhantbthuedvcqg").forward(httpServletRequest,
					httpServletResponse);
		}

		if(Validator.isNotNull(requestURI) && (requestURI.contains("search/call"))) {
			httpServletRequest.getRequestDispatcher("/o/rest/v2/dossiers/direct").forward(httpServletRequest,
					httpServletResponse);
		}

		if(Validator.isNotNull(requestURI) && (requestURI.contains("downloadfile"))) {
			String referenceUid;
			try {
				if(requestURI.length() == LENGTH_URL_DOWNLOAD_FILE) {
					referenceUid = requestURI.substring(requestURI.length() - 36);
				} else {
					referenceUid = "";
				}

			} catch (Exception e) {
				_log.error(e);
				referenceUid = "";
			}
			httpServletRequest.getRequestDispatcher("/o/rest/v2/dossiers/downloadfile/" + referenceUid ).forward(httpServletRequest,
					httpServletResponse);
		}



		processFilter(OpenCPSServletFilter.class.getName(), httpServletRequest, httpServletResponse, filterChain);

	}

	public static final long PORTAL_DEFAULT_USERID = GetterUtil.getLong(PropsUtil.get("portal.default.userid"));

	private static final Log _log = LogFactoryUtil.getLog(OpenCPSServletFilter.class);

}