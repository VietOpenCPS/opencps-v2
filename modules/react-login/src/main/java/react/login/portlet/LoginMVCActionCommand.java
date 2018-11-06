/**
 * 
 */

package react.login.portlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.service.ApplicantLocalServiceUtil;
import org.opencps.usermgt.service.util.UserMgtUtils;
import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.security.auth.session.AuthenticatedSessionManagerUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

/**
 * @author phucnv
 * @date Sep 12, 2017
 */
@Component(property = {
	"javax.portlet.name=npmreactlogin",
	"mvc.command.name=/login/login"
}, service = MVCActionCommand.class)
public class LoginMVCActionCommand extends BaseMVCActionCommand {

	/*
	 * (non-Javadoc)
	 * @see com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand#
	 * doProcessAction(javax.portlet.ActionRequest,
	 * javax.portlet.ActionResponse)
	 */
	@Override
	protected void doProcessAction(
		ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {
		
		ThemeDisplay themeDisplay =
			(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		HttpServletRequest request = PortalUtil.getOriginalServletRequest(
			PortalUtil.getHttpServletRequest(actionRequest));

		HttpServletResponse response =
			PortalUtil.getHttpServletResponse(actionResponse);

		String login = ParamUtil.getString(actionRequest, "login");
		String password = ParamUtil.getString(actionRequest, "password");
		String action = ParamUtil.getString(actionRequest, "action");
		boolean rememberMe = ParamUtil.getBoolean(actionRequest, "rememberMe");
		String authType = CompanyConstants.AUTH_TYPE_EA;
		
		if (!Validator.isEmailAddress(login)) {
			
			Applicant app = ApplicantLocalServiceUtil.fetchByAppId(login);
			
			if (Validator.isNotNull(app)) {
				login = app.getContactEmail();
			}
		}
		
		Applicant applicant = UserMgtUtils.getApplicant(login);

		login = applicant != null ? applicant.getContactEmail() : login;

		User user = UserLocalServiceUtil.getUserByEmailAddress(
			themeDisplay.getCompanyId(), login);

		hideDefaultSuccessMessage(actionRequest);

		if (user != null &&
			user.getStatus() == WorkflowConstants.STATUS_PENDING) {

			actionResponse.sendRedirect(themeDisplay.getURLHome() +
				"/register#/xac-thuc-tai-khoan?active_user_id=" + user.getUserId() +
					"&redirectURL=" + themeDisplay.getURLCurrent());
		}
		else {
			AuthenticatedSessionManagerUtil.login(
				request, response, login, password, rememberMe, authType);

			if (action != null && "confirm_account".equals(action)) {
				actionResponse.sendRedirect(
						themeDisplay.getURLHome() + "/profile");
			}
			else {
				actionResponse.sendRedirect(themeDisplay.getPathMain());
			}
		}

	}

}
