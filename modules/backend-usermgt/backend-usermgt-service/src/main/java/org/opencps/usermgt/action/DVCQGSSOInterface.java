package org.opencps.usermgt.action;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author trungnt
 *
 */
public interface DVCQGSSOInterface {

	public String checkAuth(User user, long groupId, HttpServletRequest request, ServiceContext serviceContext,
			int vnconnect, String currentURL);

	public String getAuthURL(User user, long groupId, HttpServletRequest request, ServiceContext serviceContext,
			String state, String redirectURL);

	public JSONObject getUserInfo(User user, long groupId, HttpServletRequest request, ServiceContext serviceContext,
			String authToken, String state);

	public JSONObject doAuth(User user, HttpServletRequest request, HttpServletResponse response,
			ServiceContext serviceContext, String userInfo) throws Exception;

	public boolean isValidAccessToken(String accessToken);

	public JSONObject doChangeEmail(User user, long companyId, long groupId, HttpServletRequest request, HttpServletResponse response,
			ServiceContext serviceContext, String oldEmail, String newEmail, String techId);

}
