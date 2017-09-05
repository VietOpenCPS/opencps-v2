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

package org.opencps.backend.processmgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

/**
 * @author khoavu
 * @generated
 */
@ProviderType
public class DossierActionUserPK implements Comparable<DossierActionUserPK>,
	Serializable {
	public long dossierActionId;
	public long userId;

	public DossierActionUserPK() {
	}

	public DossierActionUserPK(long dossierActionId, long userId) {
		this.dossierActionId = dossierActionId;
		this.userId = userId;
	}

	public long getDossierActionId() {
		return dossierActionId;
	}

	public void setDossierActionId(long dossierActionId) {
		this.dossierActionId = dossierActionId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Override
	public int compareTo(DossierActionUserPK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

		if (dossierActionId < pk.dossierActionId) {
			value = -1;
		}
		else if (dossierActionId > pk.dossierActionId) {
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

		if (!(obj instanceof DossierActionUserPK)) {
			return false;
		}

		DossierActionUserPK pk = (DossierActionUserPK)obj;

		if ((dossierActionId == pk.dossierActionId) && (userId == pk.userId)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int hashCode = 0;

		hashCode = HashUtil.hash(hashCode, dossierActionId);
		hashCode = HashUtil.hash(hashCode, userId);

		return hashCode;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(10);

		sb.append(StringPool.OPEN_CURLY_BRACE);

		sb.append("dossierActionId");
		sb.append(StringPool.EQUAL);
		sb.append(dossierActionId);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("userId");
		sb.append(StringPool.EQUAL);
		sb.append(userId);

		sb.append(StringPool.CLOSE_CURLY_BRACE);

		return sb.toString();
	}
}