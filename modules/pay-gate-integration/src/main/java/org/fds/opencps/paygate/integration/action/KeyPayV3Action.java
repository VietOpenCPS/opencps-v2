/**
 * 
 */
package org.fds.opencps.paygate.integration.action;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author moon
 *
 */
public interface KeyPayV3Action {

	public String createPaylater(User user, long dossierId, ServiceContext serviceContext, HttpServletRequest request);
	public File getQrCode(User user, long dossierId, ServiceContext serviceContext, HttpServletRequest request, HttpServletResponse response);

	public JSONObject paylaterCallback(User user, ServiceContext serviceContext, String body);
}
