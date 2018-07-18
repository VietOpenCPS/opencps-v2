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

import java.util.Date;
import java.util.List;

import org.opencps.dossiermgt.exception.DuplicateActionCodeException;
import org.opencps.dossiermgt.model.StepConfig;
import org.opencps.dossiermgt.service.base.StepConfigLocalServiceBaseImpl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.Validator;

/**
 * The implementation of the step config local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.dossiermgt.service.StepConfigLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see StepConfigLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.StepConfigLocalServiceUtil
 */
public class StepConfigLocalServiceImpl extends StepConfigLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.dossiermgt.service.StepConfigLocalServiceUtil} to access the
	 * step config local service.
	 */
	@Indexable(type = IndexableType.REINDEX)
	public StepConfig addStepConfig(long userId, long groupId, String stepCode, String stepName, Integer stepType,
			String dossierStatus, String dossierSubStatus, String menuGroup, String menuStepName, String buttonConfig)
			throws PortalException {

		validate(groupId, stepCode, 0);

		User user = userLocalService.getUser(userId);

		Date now = new Date();

		long stepConfigId = counterLocalService.increment(StepConfig.class.getName());

		StepConfig object = stepConfigPersistence.create(stepConfigId);

		object.setGroupId(groupId);
		object.setCompanyId(user.getCompanyId());
		object.setUserId(user.getUserId());
		object.setCreateDate(now);
		object.setModifiedDate(now);

		object.setStepCode(stepCode);
		object.setStepName(stepName);
		object.setStepType(Validator.isNotNull(stepType) ? stepType : 0);
		object.setDossierStatus(dossierStatus);
		object.setDossierSubStatus(dossierSubStatus);
		object.setMenuGroup(menuGroup);
		object.setMenuStepName(menuStepName);
		object.setButtonConfig(buttonConfig);

		stepConfigPersistence.update(object);

		return object;

	}

	@Indexable(type = IndexableType.REINDEX)
	public StepConfig updateStepConfig(long stepConfigId, long userId, long groupId, String stepCode, String stepName,
			Integer stepType, String dossierStatus, String dossierSubStatus, String menuGroup, String menuStepName,
			String buttonConfig) throws PortalException {

		validate(groupId, stepCode, stepConfigId);

		User user = userLocalService.getUser(userId);

		Date now = new Date();

		StepConfig object = stepConfigPersistence.findByPrimaryKey(stepConfigId);

		object.setUserId(user.getUserId());
		object.setModifiedDate(now);

		if (Validator.isNotNull(stepCode)) {
			object.setStepCode(stepCode);
		}
		if (Validator.isNotNull(stepName)) {
			object.setStepName(menuStepName);
		}
		if (Validator.isNotNull(stepType)) {
			object.setStepType(stepType);
		}
		if (Validator.isNotNull(dossierStatus)) {
			object.setDossierStatus(dossierStatus);
		}
		if (Validator.isNotNull(dossierSubStatus)) {
			object.setDossierSubStatus(dossierSubStatus);
		}
		if (Validator.isNotNull(menuGroup)) {
			object.setMenuGroup(menuGroup);
		}
		if (Validator.isNotNull(menuStepName)) {
			object.setMenuStepName(menuStepName);
		}
		if (Validator.isNotNull(buttonConfig)) {
			object.setButtonConfig(buttonConfig);
		}

		stepConfigPersistence.update(object);

		return object;

	}

	@Indexable(type = IndexableType.DELETE)
	public StepConfig removeStepConfig(long stepConfigId) throws PortalException {
		StepConfig object = stepConfigPersistence.findByPrimaryKey(stepConfigId);

		object = stepConfigPersistence.remove(object);

		return object;
	}

	public StepConfig getByCode(long groupId, String stepCode) {

		return stepConfigPersistence.fetchByF_BY_stepCode(groupId, stepCode);

	}

	private void validate(long groupId, String stepCode, long stepConfigId) throws PortalException {

		StepConfig stepConfig = stepConfigPersistence.fetchByF_BY_stepCode(groupId, stepCode);

		if (Validator.isNull(stepCode)) {
			throw new DuplicateActionCodeException("DuplicateStepCodeException");
		}

		if (Validator.isNotNull(stepConfig) && stepConfigId == 0) {
			throw new DuplicateActionCodeException("DuplicateStepCodeException");
		}
		
		if (Validator.isNotNull(stepConfig) && stepConfigId != stepConfig.getStepConfigId()) {
			throw new DuplicateActionCodeException("DuplicateStepCodeException");
		}

	}

	public List<StepConfig> getByStepType(long groupId, int stepType) {
		return stepConfigFinder.finByStepConfig(stepType);
	}

	@Indexable(type = IndexableType.REINDEX)
	public StepConfig updateStepConfigDB(long userId, long groupId, String stepCode, String stepName, Integer stepType,
			String dossierStatus, String dossierSubStatus, String menuGroup, String menuStepName, String buttonConfig) {

		try {
			User user = userLocalService.getUser(userId);
			Date now = new Date();

			long stepConfigId = counterLocalService.increment(StepConfig.class.getName());
			StepConfig object = stepConfigPersistence.create(stepConfigId);

			object.setGroupId(groupId);
			object.setCompanyId(user.getCompanyId());
			object.setUserId(user.getUserId());
			object.setCreateDate(now);
			object.setModifiedDate(now);

			object.setStepCode(stepCode);
			object.setStepName(stepName);
			object.setStepType(Validator.isNotNull(stepType) ? stepType : 0);
			object.setDossierStatus(dossierStatus);
			object.setDossierSubStatus(dossierSubStatus);
			object.setMenuGroup(menuGroup);
			object.setMenuStepName(menuStepName);
			object.setButtonConfig(buttonConfig);

			return stepConfigPersistence.update(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<StepConfig> getStepByGroupId(long groupId) {
		return stepConfigPersistence.findByF_GID(groupId);
	}
}