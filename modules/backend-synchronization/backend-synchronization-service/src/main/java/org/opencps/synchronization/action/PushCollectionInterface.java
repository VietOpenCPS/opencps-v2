package org.opencps.synchronization.action;

import org.opencps.auth.api.exception.NotFoundException;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.synchronization.exception.NoSuchPushCollectionException;
import org.opencps.synchronization.model.PushCollection;

import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;

public interface PushCollectionInterface {
	public PushCollection fetchPublishDictItem(long pushCollectionId);
	public PushCollection addPushCollection(long userId, long groupId, 
			String serverNo,
			String collectionCode,
			String collectionName,
			String collectionNameEN,
			String description,
			String method,
			String dataForm,
			ServiceContext serviceContext) throws NoSuchUserException,
			UnauthenticationException, UnauthorizationException, NoSuchPushCollectionException;
	
	public PushCollection updatePushCollection(long userId, long groupId, 
			long pushCollectionId,
			String serverNo,
			String collectionCode,
			String collectionName,
			String collectionNameEN,
			String description,
			String method,
			String dataForm,
			ServiceContext serviceContext)
			throws NoSuchUserException, NotFoundException, UnauthenticationException, UnauthorizationException, NoSuchPushCollectionException;
	
	public PushCollection deletePushCollection(long pushCollectionId, ServiceContext serviceContext)
			throws NotFoundException, UnauthenticationException, UnauthorizationException, NoSuchPushCollectionException, PortalException;	
}
