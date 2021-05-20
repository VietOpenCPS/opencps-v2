package org.opencps.usermgt.action.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import org.opencps.usermgt.action.FieldPickActions;
import org.opencps.usermgt.model.SavePickField;
import org.opencps.usermgt.service.SavePickFieldLocalServiceUtil;

import java.util.Iterator;

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
		JSONObject formDataUpdate ;

		if (Validator.isNotNull(savePickField))
		{
			fieldPickId = savePickField.getFieldPickId();

			try
			{
//				formDataUpdate = JSONFactoryUtil.createJSONObject();
				JSONObject jsonInput = JSONFactoryUtil.createJSONObject(formData);
				Iterator<String> keysInput =jsonInput.keys();

				JSONObject jsonDB = JSONFactoryUtil.createJSONObject(savePickField.getFormData());
				formDataUpdate = jsonDB;
//				Iterator<String> keysDB = jsonDB.keys();

				while (keysInput.hasNext())
				{
					String key = (String) keysInput.next();
					if (jsonDB.has(key))
					{
						String valueOfKey = jsonInput.getString(key);
						formDataUpdate.put(key,valueOfKey);
					}
					else
					{
						String valueOfKey = jsonInput.getString(key);
						formDataUpdate.put(key,valueOfKey);
					}
				}
			}
			catch (JSONException e)
			{
				_log.error("err jsoninput" ,e);
				return null;
			}
		}
		else
		{
			fieldPickId = 0;
			try
			{
				formDataUpdate = JSONFactoryUtil.createJSONObject(formData);
			}
			catch (JSONException e)
			{
				_log.error("err json" , e);
				return null;
			}
		}

		try
		{
			return SavePickFieldLocalServiceUtil.updatePickField(userId, groupId, fieldPickId, formDataUpdate.toString(), classPK);
		}
		catch (PortalException e)
		{
			_log.error(e);
			return null;
		}
	}
}
