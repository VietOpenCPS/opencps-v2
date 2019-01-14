package org.opencps.usermgt.listener;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.User;

import java.util.Date;

import org.opencps.usermgt.service.UserLoginLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

//@Component(immediate = true, service = ModelListener.class)
public class LiferayUserLoginListener extends BaseModelListener<User> {
	@Override
	public void onAfterUpdate(User model) throws ModelListenerException {
		try {
			UserLoginLocalServiceUtil.updateUserLogin(model.getCompanyId(), model.getGroupId(), model.getUserId(), model.getScreenName(), 
					model.getLoginDate(), new Date(),
					0l, "", 0, null, model.getLoginIP());
		} catch (SystemException e) {
			_log.debug(e);
		} catch (PortalException e) {
			_log.debug(e);
		}
	}
	
	
	Log _log = LogFactoryUtil.getLog(LiferayUserLoginListener.class);
}
