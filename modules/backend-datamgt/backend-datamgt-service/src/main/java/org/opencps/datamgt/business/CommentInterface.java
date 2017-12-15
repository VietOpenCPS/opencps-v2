package org.opencps.datamgt.business;

import com.liferay.portal.kernel.json.JSONObject;

public interface CommentInterface {

	//	ResourceWorkspaceListener
	// Create, Update, Delete to Log
	public void writeChangeLog(long userId, long companyId, long groupId, String name, long resourceWorkspaceId,
			String userName, String string, String create, JSONObject createJSONObject);
}
