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

package org.opencps.usermgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Serializable;

/**
 * @author khoavu
 * @generated
 */
@ProviderType
public class UserTrackPathPK implements Comparable<UserTrackPathPK>,
	Serializable {
	public long userTrackPathId;
	public long userLoginId;

	public UserTrackPathPK() {
	}

	public UserTrackPathPK(long userTrackPathId, long userLoginId) {
		this.userTrackPathId = userTrackPathId;
		this.userLoginId = userLoginId;
	}

	public long getUserTrackPathId() {
		return userTrackPathId;
	}

	public void setUserTrackPathId(long userTrackPathId) {
		this.userTrackPathId = userTrackPathId;
	}

	public long getUserLoginId() {
		return userLoginId;
	}

	public void setUserLoginId(long userLoginId) {
		this.userLoginId = userLoginId;
	}

	@Override
	public int compareTo(UserTrackPathPK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

		if (userTrackPathId < pk.userTrackPathId) {
			value = -1;
		}
		else if (userTrackPathId > pk.userTrackPathId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (userLoginId < pk.userLoginId) {
			value = -1;
		}
		else if (userLoginId > pk.userLoginId) {
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

		if (!(obj instanceof UserTrackPathPK)) {
			return false;
		}

		UserTrackPathPK pk = (UserTrackPathPK)obj;

		if ((userTrackPathId == pk.userTrackPathId) &&
				(userLoginId == pk.userLoginId)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int hashCode = 0;

		hashCode = HashUtil.hash(hashCode, userTrackPathId);
		hashCode = HashUtil.hash(hashCode, userLoginId);

		return hashCode;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(6);

		sb.append("{");

		sb.append("userTrackPathId=");

		sb.append(userTrackPathId);
		sb.append(", userLoginId=");

		sb.append(userLoginId);

		sb.append("}");

		return sb.toString();
	}
}