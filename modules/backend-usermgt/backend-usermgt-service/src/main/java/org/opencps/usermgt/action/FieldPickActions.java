package org.opencps.usermgt.action;

import com.liferay.portal.kernel.json.JSONObject;
import org.opencps.usermgt.model.SavePickField;

public interface FieldPickActions
{
	public String getFieldPick(long groupId, long userId,String classPK);

	public SavePickField updateFieldPick(long userId, long groupId, String formData,String classPK);
}
