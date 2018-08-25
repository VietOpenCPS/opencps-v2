
package org.opencps.kernel.context;

import java.util.Date;

import org.opencps.kernel.prop.PropValues;

import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.TimeZoneUtil;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

public class MBServiceContext extends ServiceContext {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServiceContext _serviceContext;

	public MBServiceContext(long companyId, long groupId, long... userId) {
		ServiceContext serviceContext = new ServiceContext();
		Date now = new Date();

		if (userId != null && userId.length > 0) {
			serviceContext.setUserId(userId[0]);
		}
		else {
			long defaultUserId = GetterUtil.getLong(PropValues.SYSTEM_USER_ID);
			serviceContext.setUserId(defaultUserId);
		}

		serviceContext.setScopeGroupId(groupId);
		serviceContext.setCompanyId(companyId);
		serviceContext.setCreateDate(now);
		serviceContext.setModifiedDate(now);
		serviceContext.setUuid(PortalUUIDUtil.generate());
		serviceContext.setTimeZone(TimeZoneUtil.getDefault());
		this.setServiceContext(serviceContext);
	}
	
	public MBServiceContext(long companyId, long groupId, Object... user) {
		ServiceContext serviceContext = new ServiceContext();
		Date now = new Date();

		if (user != null && user.length > 0) {
			serviceContext.setUserId(GetterUtil.getLong(user[0]));
		}
		else {
			long defaultUserId = GetterUtil.getLong(PropValues.SYSTEM_USER_ID);
			serviceContext.setUserId(defaultUserId);
		}
		
		if (user != null && user.length > 1) {
			serviceContext.setSignedIn(GetterUtil.getBoolean(user[1]));
		}

		serviceContext.setScopeGroupId(groupId);
		serviceContext.setCompanyId(companyId);
		serviceContext.setCreateDate(now);
		serviceContext.setModifiedDate(now);
		serviceContext.setUuid(PortalUUIDUtil.generate());
		serviceContext.setSignedIn(true);
		serviceContext.setTimeZone(TimeZoneUtil.getDefault());
		this.setServiceContext(serviceContext);
	}

	public ServiceContext getServiceContext() {

		return _serviceContext;
	}

	public void setServiceContext(ServiceContext serviceContext) {

		this._serviceContext = serviceContext;
	}

}
