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

package backend.cmc.service.impl;

import java.util.Date;
import java.util.List;


import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import aQute.bnd.annotation.ProviderType;
import backend.cmc.model.Evaluation;
import backend.cmc.service.base.EvaluationLocalServiceBaseImpl;

/**
 * The implementation of the evaluation local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link backend.cmc.service.EvaluationLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author sondt
 * @see EvaluationLocalServiceBaseImpl
 * @see backend.cmc.service.EvaluationLocalServiceUtil
 */
@ProviderType
public class EvaluationLocalServiceImpl extends EvaluationLocalServiceBaseImpl {
	public Evaluation addEvaluation(long groupId, long employeeId, String employeeName, int score,
			ServiceContext serviceContext) throws PortalException, SystemException {

		long userId = serviceContext.getUserId();

		Date now = new Date();

		User userAction = userLocalService.getUser(userId);

		long evaluationId = counterLocalService.increment(Evaluation.class.getName());

		Evaluation object = evaluationPersistence.create(evaluationId);

		/// Add audit fields
		object.setCompanyId(serviceContext.getCompanyId());
		object.setGroupId(groupId);
		object.setCreateDate(now);
		object.setModifiedDate(now);
		object.setUserId(userAction.getUserId());

		// Add other fields
		object.setEmployeeId(employeeId);
		object.setEmployeeName(employeeName);
		object.setScore(score);

		return evaluationPersistence.update(object);
	}

	public List<Evaluation> getEvaluationbyEmployeeId(long employeeId) {

		return evaluationPersistence.findByEM_ID(employeeId);
	}

	public List<Evaluation> getEvaluationbyEmployeeIdScore(long employeeId, int score) {

		return evaluationPersistence.findByEM_ID_S(employeeId, score);
	}
}