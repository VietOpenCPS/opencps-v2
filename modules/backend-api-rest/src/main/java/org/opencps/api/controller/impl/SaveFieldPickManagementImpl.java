package org.opencps.api.controller.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import org.apache.commons.httpclient.util.HttpURLConnection;
import org.opencps.api.controller.SaveFieldPickManagement;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.usermgt.action.FieldPickActions;
import org.opencps.usermgt.action.impl.FieldPickActionsImpl;
import org.opencps.usermgt.model.SavePickField;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.Locale;

public class SaveFieldPickManagementImpl implements SaveFieldPickManagement
{
	Log _log = LogFactoryUtil.getLog(ServerConfigManagementImpl.class);
	@Override
	public Response getFieldPick(HttpServletRequest request,HttpHeaders header,Company company,Locale locale,User user,
		ServiceContext serviceContext,String classPK)
	{
		FieldPickActions actions = new FieldPickActionsImpl();

		String formData = StringPool.BLANK;

		BackendAuth auth = new BackendAuthImpl();
		long groupId ;
		long userID ;
		try
		{
			if (!auth.isAuth(serviceContext))
			{
				throw new UnauthenticationException();
			}
			groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			userID = serviceContext.getUserId();
			if (Validator.isNotNull(classPK))
				formData = actions.getFieldPick(groupId,userID,classPK);
			return Response.status(HttpURLConnection.HTTP_OK).entity(formData).build();

		}
		catch (Exception e)
		{
			return Response.status(HttpURLConnection.HTTP_NO_CONTENT).build();
		}


	}

	@Override
	public Response updateFieldPick(HttpServletRequest request,HttpHeaders header,Company company,Locale locale,User user,
		ServiceContext serviceContext,String classPK,String formData)
	{
		FieldPickActions actions = new FieldPickActionsImpl();
		SavePickField savePickField ;

		BackendAuth auth = new BackendAuthImpl();
		long groupId ;
		long userID ;
		try
		{
			if (!auth.isAuth(serviceContext))
			{
				throw new UnauthenticationException();
			}
			groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			userID = serviceContext.getUserId();
			if (Validator.isNotNull(classPK))
			{
				savePickField = actions.updateFieldPick(userID,groupId,formData,classPK);
				if (Validator.isNotNull(savePickField))
					return Response.status(HttpURLConnection.HTTP_OK).entity(formData).build();
			}
		}
		catch (Exception e)
		{
			_log.error("err" , e);
		}
		return Response.status(HttpURLConnection.HTTP_NO_CONTENT).build();
	}

}
