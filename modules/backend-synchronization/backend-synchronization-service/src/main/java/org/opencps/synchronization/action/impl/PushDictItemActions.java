package org.opencps.synchronization.action.impl;

import org.opencps.auth.api.exception.NotFoundException;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.synchronization.action.PushDictItemInterface;
import org.opencps.synchronization.exception.NoSuchPushDictItemException;
import org.opencps.synchronization.model.PushDictItem;
import org.opencps.synchronization.service.PushDictItemLocalServiceUtil;

import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;

public class PushDictItemActions implements PushDictItemInterface {

	@Override
	public PushDictItem fetchPublishDictItem(long pushDictItemId) {
		// TODO Auto-generated method stub
		return PushDictItemLocalServiceUtil.fetchPushDictItem(pushDictItemId);
	}

	@Override
	public PushDictItem addPushDictItem(long userId, long groupId, 
			String serverNo,
			String collectionCode, String itemCode,
			String itemName, String itemNameEn, String itemDescription, String parentItemCode, String sibling,
			String method,
			String metaData,
			ServiceContext serviceContext) throws NoSuchUserException, UnauthenticationException,
			UnauthorizationException, NoSuchPushDictItemException {
		// TODO Auto-generated method stub		
		return PushDictItemLocalServiceUtil.addPushDictItem(userId, groupId, serverNo, collectionCode, itemCode, itemName, itemNameEn, itemDescription, parentItemCode, sibling, method, metaData, serviceContext);
	}

	@Override
	public PushDictItem updatePushDictItem(long userId, long groupId, long pushDictItemId, 
			String serverNo,
			String collectionCode,
			String itemCode, String itemName, String itemNameEn, String itemDescription, String parentItemCode,
			String sibling, 
			String method,
			String metaData,
			ServiceContext serviceContext) throws NoSuchUserException, NotFoundException,
			UnauthenticationException, UnauthorizationException, NoSuchPushDictItemException {
		// TODO Auto-generated method stub
		return PushDictItemLocalServiceUtil.updatePushDictItem(userId, groupId, pushDictItemId, serverNo, collectionCode, itemCode, itemName, itemNameEn, itemDescription, parentItemCode, sibling, method, metaData, serviceContext);
	}

	@Override
	public PushDictItem deletePushDictItem(long pushDictItemId, ServiceContext serviceContext)
			throws NotFoundException, UnauthenticationException, UnauthorizationException, NoSuchPushDictItemException, PortalException {
		// TODO Auto-generated method stub
		return PushDictItemLocalServiceUtil.deletePushDictItem(pushDictItemId);
	}


}
