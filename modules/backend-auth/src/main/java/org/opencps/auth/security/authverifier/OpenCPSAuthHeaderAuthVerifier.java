package org.opencps.auth.security.authverifier;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.AccessControlContext;
import com.liferay.portal.kernel.security.auth.AuthException;
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.security.auth.http.HttpAuthManagerUtil;
import com.liferay.portal.kernel.security.auth.http.HttpAuthorizationHeader;
import com.liferay.portal.kernel.security.auth.verifier.AuthVerifier;
import com.liferay.portal.kernel.security.auth.verifier.AuthVerifierResult;
import com.liferay.portal.kernel.security.auto.login.AutoLoginException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.auto.login.basic.auth.header.BasicAuthHeaderAutoLogin;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {
		"auth.verifier.OpenCPSAuthHeaderAuthVerifier.urls.includes=/o/rest/*"
})

public class OpenCPSAuthHeaderAuthVerifier extends BasicAuthHeaderAutoLogin
implements AuthVerifier {
//	private static final String AUTHORIZATION_HEADER = "Authorization";
//	private static final String TOKEN_HEADER = "X-CSRF-Token";
	private static final String TOKEN_HEADER = "Token";
	
	@Override
	protected String[] doLogin(
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		long companyId = PortalUtil.getCompanyId(request);

		if (!isEnabled(companyId)) {
			return null;
		}
		String token = request.getHeader(TOKEN_HEADER);
		if (Validator.isNotNull(token)) {
			String[] credentials = new String[3];
			String authToken = AuthTokenUtil.getToken(PortalUtil.getOriginalServletRequest(request));
			if (authToken == null || (authToken != null && !authToken.equals(token))) {
				return null;
			}
				
			User u = PortalUtil.getUser(request);
			if (u != null) {
				credentials[0] = String.valueOf(u.getUserId());
				credentials[1] = u.getPassword();
				credentials[2] = Boolean.TRUE.toString();				
			}
			
			return credentials;
		}
		return null;
	}

	@Override
	public String getAuthType() {
		return HttpServletRequest.BASIC_AUTH;
	}

	@Override
	public AuthVerifierResult verify(AccessControlContext accessControlContext, Properties properties)
			throws AuthException {
		try {

			AuthVerifierResult authVerifierResult = new AuthVerifierResult();

			String[] credentials = login(
				accessControlContext.getRequest(),
				accessControlContext.getResponse());

			if (credentials != null) {

				authVerifierResult.setPassword(credentials[1]);

				authVerifierResult.setPasswordBasedAuthentication(true);
				authVerifierResult.setState(AuthVerifierResult.State.SUCCESS);
				authVerifierResult.setUserId(Long.valueOf(credentials[0]));

			}
			else {

				boolean forcedBasicAuth = MapUtil.getBoolean(
					accessControlContext.getSettings(), "basic_auth");

				if (!forcedBasicAuth) {
					forcedBasicAuth = GetterUtil.getBoolean(
						properties.getProperty("basic_auth"));
				}

				if (forcedBasicAuth) {
					HttpAuthorizationHeader httpAuthorizationHeader =
						new HttpAuthorizationHeader(
							HttpAuthorizationHeader.SCHEME_BASIC);

					HttpAuthManagerUtil.generateChallenge(
						accessControlContext.getRequest(),
						accessControlContext.getResponse(),
						httpAuthorizationHeader);

					authVerifierResult.setState(
						AuthVerifierResult.State.INVALID_CREDENTIALS);
				}
			}

			return authVerifierResult;
		}
		catch (AutoLoginException ale) {
			throw new AuthException(ale);
		}
	}
	
	@Override
	protected boolean isEnabled(long companyId) {
		return true;
	}

//	private Log _log =
//		LogFactoryUtil.getLog(OpenCPSAuthHeaderAuthVerifier.class.getName());

}
