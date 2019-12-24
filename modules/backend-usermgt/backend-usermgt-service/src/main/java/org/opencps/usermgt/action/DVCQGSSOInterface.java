package org.opencps.usermgt.action;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface DVCQGSSOInterface {

	public String checkAuth(User user, long groupId, ServiceContext serviceContext, int vnconnect, String currentURL);
	
	public String getAuthURL(User user, long groupId, ServiceContext serviceContext, int vnconnect, String currentURL);
	
	public JSONObject getUserInfo(User user, long groupId, HttpServletRequest request,  ServiceContext serviceContext, String authToken);
	
	public JSONObject doAuth(User user, HttpServletRequest request,HttpServletResponse response, ServiceContext serviceContext, String userInfo) throws Exception;
	
	public boolean isValidAccessToken(String accessToken);

}
