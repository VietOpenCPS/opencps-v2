package org.opencps.synchronization.action;

import org.opencps.auth.api.exception.NotFoundException;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.synchronization.exception.NoSuchPushDictGroupException;
import org.opencps.synchronization.model.PushDictGroup;

import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;

public interface PushDictGroupInterface {
	public PushDictGroup fetchPublishDictItem(long pushDictGroupId);
	public PushDictGroup addPushDictGroup(long userId, long groupId, 
			String serverNo,
			String collectionCode,
			String groupCode,
			String groupName,
			String groupNameEN,
			String groupDescription,
			String itemCode,
			String method,
			ServiceContext serviceContext) throws NoSuchUserException,
			UnauthenticationException, UnauthorizationException, NoSuchPushDictGroupException;
	
	public PushDictGroup updatePushDictGroup(long userId, long groupId, 
			long pushDictGroupId,
			String serverNo,
			String collectionCode,
			String groupCode,
			String groupName,
			String groupNameEN,
			String groupDescription,
			String itemCode,
			String method,
			ServiceContext serviceContext)
			throws NoSuchUserException, NotFoundException, UnauthenticationException, UnauthorizationException, NoSuchPushDictGroupException;
	
	public PushDictGroup deletePushDictGroup(long pushDictGroupId, ServiceContext serviceContext)
			throws NotFoundException, UnauthenticationException, UnauthorizationException, NoSuchPushDictGroupException, PortalException;	
}
