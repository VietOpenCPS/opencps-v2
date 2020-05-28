package org.opencps.filter;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.security.auto.login.AutoLogin;
import com.liferay.portal.kernel.security.auto.login.BaseAutoLogin;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.settings.CompanyServiceSettingsLocator;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.exportimport.UserImporter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * Participates in every unauthenticated HTTP request to Liferay Portal.
 *
 * <p>
 * CASAutoLogin looks for the CAS_LOGIN HTTP session attribute. If this
 * attribute is found and if the attribute's value matches the ID of an existing
 * Liferay Portal user, then the user is logged in without any further
 * challenge.
 * </p>
 *
 * <p>
 * When identifying a user and Import from LDAP is enabled, then CASAutoLogin
 * always attempts to import or re-import the user from LDAP instead of
 * searching for an existing Liferay Portal user.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @author Jorge Ferrer
 * @author Wesley Gong
 * @author Daeyoung Song
 */
@Component(
	configurationPid = "com.liferay.portal.security.sso.cas.configuration.CASConfiguration",
	immediate = true, service = AutoLogin.class
)
public class CASAutoLogin extends com.liferay.portal.security.sso.cas.internal.auto.login.CASAutoLogin {
	public static final String CAS_FORCE_LOGOUT = "CAS_FORCE_LOGOUT";

	public static final String CAS_LOGIN = "CAS_LOGIN";

	public static final String CAS_NO_SUCH_USER_EXCEPTION =
		"CAS_NO_SUCH_USER_EXCEPTION";
	public static final String COMPANY_SECURITY_AUTH_TYPE = PropsUtil.get(PropsKeys.COMPANY_SECURITY_AUTH_TYPE);

	@Override
	protected String[] doHandleException(
		HttpServletRequest request, HttpServletResponse response, Exception e) {

		if (e instanceof NoSuchUserException) {
			HttpSession session = request.getSession();

			//session.removeAttribute(CASWebKeys.CAS_LOGIN);
			session.removeAttribute(CAS_LOGIN);

			//session.setAttribute(CASWebKeys.CAS_NO_SUCH_USER_EXCEPTION, Boolean.TRUE);
			session.setAttribute(CAS_NO_SUCH_USER_EXCEPTION, Boolean.TRUE);
		}

		_log.error(e, e);

		return null;
	}

	@Override
	protected String[] doLogin(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		System.out.println("AAAAAAAAAA: ");
		System.out.println("request: "+request);

		HttpSession session = request.getSession();

		long companyId = _portal.getCompanyId(request);

//		CASConfiguration casConfiguration =
//			_configurationProvider.getConfiguration(
//				CASConfiguration.class,
//				new CompanyServiceSettingsLocator(
//					companyId, CASConstants.SERVICE_NAME));

//		if (!casConfiguration.enabled()) {
//			return null;
//		}

//		String login = (String)session.getAttribute(CASWebKeys.CAS_LOGIN);
		String login = (String)session.getAttribute(CAS_LOGIN);
		System.out.println("login: "+login);

		if (Validator.isNull(login)) {
//			Object noSuchUserException = session.getAttribute(
//				CASWebKeys.CAS_NO_SUCH_USER_EXCEPTION);
			Object noSuchUserException = session.getAttribute(
					CAS_NO_SUCH_USER_EXCEPTION);

			if (noSuchUserException == null) {
				return null;
			}

//			session.removeAttribute(CASWebKeys.CAS_NO_SUCH_USER_EXCEPTION);
			session.removeAttribute(CAS_NO_SUCH_USER_EXCEPTION);

			//session.setAttribute(CASWebKeys.CAS_FORCE_LOGOUT, Boolean.TRUE);
			session.setAttribute(CAS_FORCE_LOGOUT, Boolean.TRUE);

			//String redirect = casConfiguration.noSuchUserRedirectURL();
			String redirect = "http://183.91.11.60:8080";

			request.setAttribute(AutoLogin.AUTO_LOGIN_REDIRECT, redirect);

			return null;
		}

		User user = null;

		String authType = PrefsPropsUtil.getString(
			companyId, PropsKeys.COMPANY_SECURITY_AUTH_TYPE,
			COMPANY_SECURITY_AUTH_TYPE);

//		if (casConfiguration.importFromLDAP()) {
//			try {
//				if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
//					user = _userImporter.importUser(
//						companyId, StringPool.BLANK, login);
//				}
//				else {
//					user = _userImporter.importUser(
//						companyId, login, StringPool.BLANK);
//				}
//			}
//			catch (SystemException se) {
//
//				// LPS-52675
//
//				if (_log.isDebugEnabled()) {
//					_log.debug(se, se);
//				}
//			}
//		}

		if (user == null) {
			if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
				user = _userLocalService.getUserByScreenName(companyId, login);
			}
			else {
				user = _userLocalService.getUserByEmailAddress(
					companyId, login);
			}
		}

		addRedirect(request);

		String[] credentials = new String[3];

		credentials[0] = String.valueOf(user.getUserId());
		credentials[1] = user.getPassword();
		credentials[2] = Boolean.TRUE.toString();

		return credentials;
	}

	@Reference(unbind = "-")
	protected void setConfigurationProvider(
		ConfigurationProvider configurationProvider) {

		_configurationProvider = configurationProvider;
	}

	@Reference(unbind = "-")
	protected void setUserImporter(UserImporter userImporter) {
		_userImporter = userImporter;
	}

	@Reference(unbind = "-")
	protected void setUserLocalService(UserLocalService userLocalService) {
		_userLocalService = userLocalService;
	}

	private static final Log _log = LogFactoryUtil.getLog(CASAutoLogin.class);

	private ConfigurationProvider _configurationProvider;

	@Reference
	private Portal _portal;

	private UserImporter _userImporter;
	private UserLocalService _userLocalService;

}