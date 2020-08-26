/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package org.opencps.dossiermgt.service.impl;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import org.opencps.dossiermgt.constants.PublishQueueTerm;
import org.opencps.dossiermgt.model.PostConnect;
import org.opencps.dossiermgt.service.base.PostConnectLocalServiceBaseImpl;

import java.util.List;

/**
 * The implementation of the post connect local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.dossiermgt.service.PostConnectLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see PostConnectLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.PostConnectLocalServiceUtil
 */
public class PostConnectLocalServiceImpl extends PostConnectLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.dossiermgt.service.PostConnectLocalServiceUtil} to access the post connect local service.
	 */
	private Log _log = LogFactoryUtil.getLog(PostConnectLocalServiceImpl.class);
	public PostConnect createOrUpdatePostConnect(long groupId, long userId, long dossierId,
												 Integer postService, Integer postType, String orderNumber,
												 Integer postStatus, String metaData, Integer syncState,
												 Integer retry) {
		try {
			PostConnect postConnect =  postConnectPersistence.fetchByF_ORDER_NUMBER(orderNumber);

			if(Validator.isNotNull(postConnect)) {
				Integer oldPostStatus = postConnect.getPostStatus();
				postConnect.setPostStatus(postStatus);
				if(!postStatus.equals(oldPostStatus)) {
					postConnect.setSyncState(PublishQueueTerm.STATE_WAITING_SYNC);
					postConnect.setRetry(0);
				}
				return postConnectPersistence.update(postConnect);
			}

			if(groupId == 0 || dossierId == 0) {
				throw new Exception("Group id or dossier id not found for case create Post Connect");
			}

			//create post order
			long id = CounterLocalServiceUtil.increment(PostConnect.class.getName());
			PostConnect postConnectNew = postConnectPersistence.create(id);
			postConnectNew.setGroupId(groupId);
			postConnectNew.setDossierId(dossierId);
			postConnectNew.setUserId(userId);
			postConnectNew.setPostService(postService);
			postConnectNew.setPostType(postType);
			postConnectNew.setOrderNumber(orderNumber);
			postConnectNew.setPostStatus(postStatus);
			postConnectNew.setMetadata(metaData);
			postConnectNew.setSyncState(syncState);
			return postConnectPersistence.update(postConnectNew);

		}catch (Exception e) {
			_log.error("CRUD Order number " + orderNumber + " error: " + e.getMessage());
		}
		return null;
	}

	public List<PostConnect> getBySyncState(Integer syncState) {
		return postConnectPersistence.findByF_SYNC_STATE(syncState);
	}
}