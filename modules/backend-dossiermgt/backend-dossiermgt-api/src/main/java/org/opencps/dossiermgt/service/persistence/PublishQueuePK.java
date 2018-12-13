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

package org.opencps.dossiermgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Serializable;

/**
 * @author huymq
 * @generated
 */
@ProviderType
public class PublishQueuePK implements Comparable<PublishQueuePK>, Serializable {
	public long publishQueueId;
	public long groupId;
	public long userId;

	public PublishQueuePK() {
	}

	public PublishQueuePK(long publishQueueId, long groupId, long userId) {
		this.publishQueueId = publishQueueId;
		this.groupId = groupId;
		this.userId = userId;
	}

	public long getPublishQueueId() {
		return publishQueueId;
	}

	public void setPublishQueueId(long publishQueueId) {
		this.publishQueueId = publishQueueId;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Override
	public int compareTo(PublishQueuePK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

		if (publishQueueId < pk.publishQueueId) {
			value = -1;
		}
		else if (publishQueueId > pk.publishQueueId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (groupId < pk.groupId) {
			value = -1;
		}
		else if (groupId > pk.groupId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (userId < pk.userId) {
			value = -1;
		}
		else if (userId > pk.userId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PublishQueuePK)) {
			return false;
		}

		PublishQueuePK pk = (PublishQueuePK)obj;

		if ((publishQueueId == pk.publishQueueId) && (groupId == pk.groupId) &&
				(userId == pk.userId)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int hashCode = 0;

		hashCode = HashUtil.hash(hashCode, publishQueueId);
		hashCode = HashUtil.hash(hashCode, groupId);
		hashCode = HashUtil.hash(hashCode, userId);

		return hashCode;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(8);

		sb.append("{");

		sb.append("publishQueueId=");

		sb.append(publishQueueId);
		sb.append(", groupId=");

		sb.append(groupId);
		sb.append(", userId=");

		sb.append(userId);

		sb.append("}");

		return sb.toString();
	}
}