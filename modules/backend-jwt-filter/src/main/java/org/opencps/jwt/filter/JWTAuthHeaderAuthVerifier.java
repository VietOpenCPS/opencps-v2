package org.opencps.jwt.filter;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
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
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.auto.login.basic.auth.header.BasicAuthHeaderAutoLogin;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.jwt.provider.factory.Auth0JwtTokenProviderFactory;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = { "auth.verifier.JWTAuthHeaderAuthVerifier.urls.includes=*" })
public class JWTAuthHeaderAuthVerifier extends BasicAuthHeaderAutoLogin implements AuthVerifier {

	@Override
	protected String[] doLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {

		long companyId = PortalUtil.getCompanyId(request);

		if (!isEnabled(companyId)) {
			return null;
		}
		
		String secret = getSecretKey();

		String authorization = request.getHeader("Authorization");

		_log.debug("JWTAuthHeaderAuthVerifier secret:" + secret);
		
		_log.debug("Authorization:" + authorization);

		if (Validator.isNotNull(authorization)) {
			String[] schemaData = StringUtil.split(authorization, StringPool.SPACE);

			if (schemaData == null || schemaData.length != 2) {
				return null;
			}

			if (schemaData[0].equals("Bearer")) {
				String token = schemaData[1];

				Auth0JwtTokenProviderFactory auth0JwtTokenProviderFactory = new Auth0JwtTokenProviderFactory();

				if (!auth0JwtTokenProviderFactory.validateToken(token, secret)) {
					return null;
				}

				JSONObject body = auth0JwtTokenProviderFactory.getBodyFromToken(token, secret);

				String email = StringPool.BLANK;

				String username = StringPool.BLANK;

				if (body != null) {
					email = body.getString("email");

					username = body.getString("username");
				}

				_log.debug("===> JWTAuthHeaderAuthVerifier body:" + body);

				_log.debug("===> JWTAuthHeaderAuthVerifier :" + email + "|" + username);

				if (Validator.isNull(email) && Validator.isNull(username)) {
					return null;
				}

				User user = Validator.isNotNull(username)
						? UserLocalServiceUtil.fetchUserByScreenName(companyId, username)
						: UserLocalServiceUtil.fetchUserByEmailAddress(companyId, email);

				if (user == null) {
					return null;
				}

				if (user.isActive()) {

					String[] credentials = new String[3];

					credentials[0] = String.valueOf(user.getUserId());
					credentials[1] = user.getPassword();
					credentials[2] = Boolean.TRUE.toString();

					return credentials;
				} else {
					_log.info("===> JWTAuthHeaderAuthVerifier user is not active");
				}
			}

		}

		HttpAuthorizationHeader httpAuthorizationHeader = HttpAuthManagerUtil.parse(request);

		if (httpAuthorizationHeader == null) {
			return null;
		}

		String scheme = httpAuthorizationHeader.getScheme();

		// We only handle HTTP Basic authentication

		if (!StringUtil.equalsIgnoreCase(scheme, HttpAuthorizationHeader.SCHEME_BASIC)) {

			return null;
		}

		long userId = HttpAuthManagerUtil.getUserId(request, httpAuthorizationHeader);

		if (userId <= 0) {
			throw new AuthException();
		}

		String[] credentials = new String[3];

		credentials[0] = String.valueOf(userId);
		credentials[1] = httpAuthorizationHeader.getAuthParameter(HttpAuthorizationHeader.AUTH_PARAMETER_NAME_PASSWORD);

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
	
	private String getSecretKey() {
		
		ServerConfig config = ServerConfigLocalServiceUtil.getByCode(_SERVER_NO);
		
		//default
		String secret = StringPool.BLANK;
		
		if(config != null) {
			secret = config.getConfigs();
		}
		
		if(Validator.isNull(secret)) {
			secret = "VnVlSlggQmluaHRoIDEyNTQ1ODEyOSBWdWVqcyBWdWV0aWZ5anM=";
		}
		
		return secret;
	}

	private final String _SERVER_NO = "JWT_SECRET_KEY";
	
	private Log _log = LogFactoryUtil.getLog(JWTAuthHeaderAuthVerifier.class.getName());
}
