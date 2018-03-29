package org.opencps.synchronization.action.impl;

import org.opencps.auth.api.exception.NotFoundException;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.synchronization.action.PushCollectionInterface;
import org.opencps.synchronization.exception.NoSuchPushCollectionException;
import org.opencps.synchronization.model.PushCollection;
import org.opencps.synchronization.service.PushCollectionLocalServiceUtil;

import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;

public class PushCollectionActions implements PushCollectionInterface {

	@Override
	public PushCollection fetchPublishDictItem(long pushCollectionId) {
		// TODO Auto-generated method stub
		return PushCollectionLocalServiceUtil.fetchPushCollection(pushCollectionId);
	}

	@Override
	public PushCollection addPushCollection(long userId, long groupId, 
			String serverNo,
			String collectionCode, String collectionName,
			String collectionNameEN, String description, String method, 
			String dataForm,
			ServiceContext serviceContext)
			throws NoSuchUserException, UnauthenticationException, UnauthorizationException,
			NoSuchPushCollectionException {
		// TODO Auto-generated method stub
		return PushCollectionLocalServiceUtil.addPushCollection(userId, groupId, serverNo, collectionCode, collectionName, collectionNameEN, description, method, dataForm, serviceContext);
	}

	@Override
	public PushCollection updatePushCollection(long userId, long groupId, 
			long pushCollectionId, 
			String serverNo,
			String collectionCode,
			String collectionName, String collectionNameEN, String description, String method,
			String dataForm,
			ServiceContext serviceContext) throws NoSuchUserException, NotFoundException, UnauthenticationException,
			UnauthorizationException, NoSuchPushCollectionException {
		// TODO Auto-generated method stub
		return PushCollectionLocalServiceUtil.updatePushCollection(userId, groupId, pushCollectionId, serverNo, collectionCode, collectionName, collectionNameEN, description, method, dataForm, serviceContext);
	}

	@Override
	public PushCollection deletePushCollection(long pushCollectionId, ServiceContext serviceContext)
			throws NotFoundException, UnauthenticationException, UnauthorizationException,
			NoSuchPushCollectionException, PortalException {
		// TODO Auto-generated method stub
		return PushCollectionLocalServiceUtil.deletePushCollection(pushCollectionId);
	}

}
