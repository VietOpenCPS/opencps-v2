package org.opencps.api.controller.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.security.auth.session.AuthenticatedSessionManagerUtil;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.opencps.api.configuration.WebKeys;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * Rest Controller
 *
 * @author binhth
 */
@RestController
public class LiferayRestController {

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public String doLogin(HttpServletRequest request, HttpServletResponse response) {

		try {

			Enumeration<String> headerNames = request.getHeaderNames();

			String strBasic = StringPool.BLANK;

			if (headerNames != null) {
				while (headerNames.hasMoreElements()) {
					String key = (String) headerNames.nextElement();
					String value = request.getHeader(key);
					if (key.trim().equalsIgnoreCase(WebKeys.AUTHORIZATION)) {
						strBasic = value;
						break;
					}
				}
			}

			// Get encoded user and password, comes after "BASIC "
			String userpassEncoded = strBasic.substring(6);
			String decodetoken = new String(Base64.decode(userpassEncoded), StringPool.UTF8);

			String account[] = decodetoken.split(":");

			String email = account[0];
			String password = account[1];

			long userId = AuthenticatedSessionManagerUtil.getAuthenticatedUserId(request, email, password,
					CompanyConstants.AUTH_TYPE_EA);

			if (userId > 0 && userId != 20103) {

				AuthenticatedSessionManagerUtil.login(request, response, email, password, true,
						CompanyConstants.AUTH_TYPE_EA);

				Employee employee = EmployeeLocalServiceUtil.fetchByFB_MUID(userId);

				if (Validator.isNotNull(employee)) {
					return "/c";
				} else {
					return "ok";
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String uploadFile(MultipartHttpServletRequest request) {
		CommonsMultipartFile multipartFile = null; // multipart file class depends on which class you use assuming you
													// are using
													// org.springframework.web.multipart.commons.CommonsMultipartFile

		Iterator<String> iterator = request.getFileNames();

		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			// create multipartFile array if you upload multiple files
			multipartFile = (CommonsMultipartFile) request.getFile(key);
		}

		System.out.println("LiferayRestController.uploadFile()" + multipartFile.getSize());

		try {
			System.out.println("LiferayRestController.uploadFile()" + multipartFile.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("LiferayRestController.uploadFile()" + request.getParameter("dkm"));
		return "sdfds";
	}
}
