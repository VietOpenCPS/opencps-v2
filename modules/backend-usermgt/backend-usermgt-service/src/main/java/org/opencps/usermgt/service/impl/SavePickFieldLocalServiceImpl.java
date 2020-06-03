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

package org.opencps.usermgt.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;
import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.model.SavePickField;
import org.opencps.usermgt.service.base.SavePickFieldLocalServiceBaseImpl;

import java.util.Date;

/**
 * The implementation of the save pick field local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.usermgt.service.SavePickFieldLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author khoavu
 * @see SavePickFieldLocalServiceBaseImpl
 * @see org.opencps.usermgt.service.SavePickFieldLocalServiceUtil
 */
public class SavePickFieldLocalServiceImpl
	extends SavePickFieldLocalServiceBaseImpl {


	public SavePickField updatePickField(long userId, long groupId, long fieldPickId, String formData,String classPK)
		throws PortalException, SystemException
	{
		Date now = new Date();
		SavePickField savePickField = null;

		if (fieldPickId == 0)
		{
			fieldPickId  = counterLocalService.increment(SavePickField.class.getName());

			savePickField = savePickFieldPersistence.create(fieldPickId);

			savePickField.setCreateDate(now);
			savePickField.setModifiedDate(now);
			savePickField.setGroupId(groupId);
			savePickField.setUserId(userId);

			savePickField.setFormData(formData);
			savePickField.setClassPK(classPK);
		}
		else
		{
			savePickField = savePickFieldPersistence.fetchByPrimaryKey(fieldPickId);

			savePickField.setModifiedDate(now);

			if(Validator.isNotNull(formData))
				savePickField.setFormData(formData);
		}

		return savePickFieldPersistence.update(savePickField);
	}

	public SavePickField getPickField(long groupId, long userId,String classPK)
	{
		return savePickFieldPersistence.fetchByG_U_ClassPK(groupId,userId,classPK);
	}

}