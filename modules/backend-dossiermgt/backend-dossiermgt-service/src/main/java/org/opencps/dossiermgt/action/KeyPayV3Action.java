/**
 * 
 */
package org.opencps.dossiermgt.action;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * @author moon
 *
 */
public interface KeyPayV3Action {

	public JSONObject createPaylater(User user, long dossierId, ServiceContext serviceContext, HttpServletRequest request);
	public File getQrCode(User user, long dossierId, ServiceContext serviceContext, HttpServletRequest request, HttpServletResponse response, String imageStr);

	public JSONObject paylaterCallback(User user, ServiceContext serviceContext, String body);
}
