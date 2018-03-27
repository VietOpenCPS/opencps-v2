package org.opencps.synchronization.action;

import org.opencps.auth.api.exception.NotFoundException;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.synchronization.exception.NoSuchPushDictItemException;
import org.opencps.synchronization.model.PushDictItem;

import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;

public interface PushDictItemInterface {
	public PushDictItem fetchPublishDictItem(long pushDictItemId);
	public PushDictItem addPushDictItem(long userId, long groupId, 
			String serverNo,
			String collectionCode,
			String itemCode,
			String itemName, String itemNameEn, String itemDescription, String parentItemCode, String sibling, 
			String method,
			String metaData,
			ServiceContext serviceContext) throws NoSuchUserException,
			UnauthenticationException, UnauthorizationException, NoSuchPushDictItemException;
	
	public PushDictItem updatePushDictItem(long userId, long groupId, 
			long pushDictItemId,
			String serverNo,
			String collectionCode,
			String itemCode,
			String itemName, String itemNameEn, String itemDescription, String parentItemCode, 
			String sibling, 
			String method,
			String metaData,
			ServiceContext serviceContext)
			throws NoSuchUserException, NotFoundException, UnauthenticationException, UnauthorizationException, NoSuchPushDictItemException;
	
	public PushDictItem deletePushDictItem(long pushDictItemId, ServiceContext serviceContext)
			throws NotFoundException, UnauthenticationException, UnauthorizationException, NoSuchPushDictItemException, PortalException;	
}
