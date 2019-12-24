
package org.opencps.usermgt.restapi;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.AccessControlContext;
import com.liferay.portal.kernel.security.auth.AuthException;
import com.liferay.portal.kernel.security.auth.http.HttpAuthManagerUtil;
import com.liferay.portal.kernel.security.auth.http.HttpAuthorizationHeader;
import com.liferay.portal.kernel.security.auth.verifier.AuthVerifier;
import com.liferay.portal.kernel.security.auth.verifier.AuthVerifierResult;
import com.liferay.portal.kernel.security.auto.login.AutoLoginException;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.auto.login.basic.auth.header.BasicAuthHeaderAutoLogin;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.opencps.usermgt.action.impl.DVCQGSSOActionImpl;
import org.osgi.service.component.annotations.Component;

/**
 * @author trungnt
 */
@Component(immediate = true, property = { "auth.verifier.AuthHeaderAuthVerifier.urls.includes=*" })
public class AuthHeaderAuthVerifier extends BasicAuthHeaderAutoLogin implements AuthVerifier {

	@Override
	protected String[] doLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {

		long companyId = PortalUtil.getCompanyId(request);
	
		if (!isEnabled(companyId)) {
			return null;
		}

		long userId = GetterUtil.getLong(request.getHeader("userId"));

		if (userId <= 0) {
			throw new AuthException();
		}

		long groupId = GetterUtil.getLong(request.getHeader("groupId"));

		String accessToken = GetterUtil.getString(request.getHeader("accessToken"));

		//TODO validate accessToken

		if (userId == 0 || groupId == 0 || Validator.isNull(accessToken)) {
			return null;
		}
		
		DVCQGSSOActionImpl actionImpl = new DVCQGSSOActionImpl();
		
		boolean isValid = actionImpl.isValidAccessToken(accessToken);
		
		if(!isValid) {
			return null;
		}

		User user = UserLocalServiceUtil.fetchUser(userId);

		String[] credentials = new String[3];

		credentials[0] = String.valueOf(user.getUserId());
		credentials[1] = user.getPassword();
		credentials[2] = Boolean.TRUE.toString();

		return credentials;

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

			String[] credentials = login(accessControlContext.getRequest(), accessControlContext.getResponse());

			if (credentials != null) {

				authVerifierResult.setPassword(credentials[1]);

				authVerifierResult.setPasswordBasedAuthentication(true);
				authVerifierResult.setState(AuthVerifierResult.State.SUCCESS);
				authVerifierResult.setUserId(Long.valueOf(credentials[0]));

			} else {

				boolean forcedBasicAuth = MapUtil.getBoolean(accessControlContext.getSettings(), "basic_auth");

				if (!forcedBasicAuth) {
					forcedBasicAuth = GetterUtil.getBoolean(properties.getProperty("basic_auth"));
				}

				if (forcedBasicAuth) {
					HttpAuthorizationHeader httpAuthorizationHeader = new HttpAuthorizationHeader(
							HttpAuthorizationHeader.SCHEME_BASIC);

					HttpAuthManagerUtil.generateChallenge(accessControlContext.getRequest(),
							accessControlContext.getResponse(), httpAuthorizationHeader);

					authVerifierResult.setState(AuthVerifierResult.State.INVALID_CREDENTIALS);
				}
			}

			return authVerifierResult;
		} catch (AutoLoginException ale) {
			throw new AuthException(ale);
		}
	}

	@Override
	protected boolean isEnabled(long companyId) {

		return true;
	}

}
