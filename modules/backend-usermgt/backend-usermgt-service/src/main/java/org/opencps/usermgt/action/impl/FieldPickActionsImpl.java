package org.opencps.usermgt.action.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import org.opencps.usermgt.action.FieldPickActions;
import org.opencps.usermgt.model.SavePickField;
import org.opencps.usermgt.service.SavePickFieldLocalServiceUtil;

public class FieldPickActionsImpl implements FieldPickActions
{
	private static Log _log = LogFactoryUtil.getLog(FieldPickActionsImpl.class);

	@Override
	public String getFieldPick(long groupId, long userId,String clsssPK)
	{

		SavePickField savePickField = SavePickFieldLocalServiceUtil.getPickField(groupId, userId, clsssPK);

		if (Validator.isNotNull(savePickField))
			return savePickField.getFormData();
		return null;
	}

	@Override
	public SavePickField updateFieldPick(long userId, long groupId, String formData,String classPK)
	{
		long fieldPickId ;
		SavePickField savePickField = SavePickFieldLocalServiceUtil.getPickField(groupId,userId,classPK);
		if (Validator.isNotNull(savePickField))
			fieldPickId = savePickField.getFieldPickId();
		else
			fieldPickId = 0;
		try
		{
			return SavePickFieldLocalServiceUtil.updatePickField(userId, groupId, fieldPickId, formData, classPK);
		}
		catch (PortalException e)
		{
			return null;
		}
	}
}
