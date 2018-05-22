package org.opencps.synchronization.action.impl;

import org.opencps.auth.api.exception.NotFoundException;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.synchronization.action.PushDictGroupInterface;
import org.opencps.synchronization.exception.NoSuchPushDictGroupException;
import org.opencps.synchronization.model.PushDictGroup;
import org.opencps.synchronization.service.PushDictGroupLocalServiceUtil;

import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;

public class PushDictGroupActions implements PushDictGroupInterface {

	@Override
	public PushDictGroup fetchPublishDictItem(long pushDictGroupId) {
		// TODO Auto-generated method stub
		return PushDictGroupLocalServiceUtil.fetchPushDictGroup(pushDictGroupId);
	}

	@Override
	public PushDictGroup addPushDictGroup(long userId, long groupId, 
			String serverNo,
			String collectionCode, String groupCode,
			String groupName, String groupNameEN, String groupDescription, 
			String itemCode,
			String method, ServiceContext serviceContext)
			throws NoSuchUserException, UnauthenticationException, UnauthorizationException,
			NoSuchPushDictGroupException {
		// TODO Auto-generated method stub
		return PushDictGroupLocalServiceUtil.addPushDictGroup(userId, groupId, serverNo, collectionCode, groupCode, groupName, groupNameEN, groupDescription, itemCode, method, serviceContext);
	}

	@Override
	public PushDictGroup updatePushDictGroup(long userId, long groupId, 
			long pushDictGroupId, 
			String serverNo,
			String collectionCode,
			String groupCode, String groupName, String groupNameEN, String groupDescription,
			String itemCode,
			String method,
			ServiceContext serviceContext) throws NoSuchUserException, NotFoundException, UnauthenticationException,
			UnauthorizationException, NoSuchPushDictGroupException {
		// TODO Auto-generated method stub
		return PushDictGroupLocalServiceUtil.updatePushDictGroup(userId, groupId, pushDictGroupId, serverNo, collectionCode, groupCode, groupName, groupNameEN, groupDescription, itemCode, method, serviceContext);
	}

	@Override
	public PushDictGroup deletePushDictGroup(long pushDictGroupId, ServiceContext serviceContext)
			throws NotFoundException, UnauthenticationException, UnauthorizationException, NoSuchPushDictGroupException,
			PortalException {
		// TODO Auto-generated method stub
		return PushDictGroupLocalServiceUtil.deletePushDictGroup(pushDictGroupId, serviceContext);
	}

}
