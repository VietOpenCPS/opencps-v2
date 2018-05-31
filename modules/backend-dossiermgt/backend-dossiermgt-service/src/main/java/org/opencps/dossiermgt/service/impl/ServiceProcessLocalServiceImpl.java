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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;

import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.opencps.dossiermgt.constants.DossierStatusConstants;
import org.opencps.dossiermgt.constants.ServiceProcessTerm;
import org.opencps.dossiermgt.exception.DuplicateProcessNameException;
import org.opencps.dossiermgt.exception.DuplicateProcessNoException;
import org.opencps.dossiermgt.exception.HasChildrenException;
import org.opencps.dossiermgt.exception.RequiredDossierNoPatternException;
import org.opencps.dossiermgt.exception.RequiredDueDatePatternException;
import org.opencps.dossiermgt.exception.RequiredProcessNameException;
import org.opencps.dossiermgt.exception.RequiredProcessNoException;
import org.opencps.dossiermgt.model.ProcessAction;
import org.opencps.dossiermgt.model.ProcessStep;
import org.opencps.dossiermgt.model.ProcessStepRole;
import org.opencps.dossiermgt.model.ServiceProcess;
import org.opencps.dossiermgt.model.ServiceProcessRole;
import org.opencps.dossiermgt.service.base.ServiceProcessLocalServiceBaseImpl;
import org.opencps.dossiermgt.service.persistence.ProcessStepRolePK;
import org.opencps.dossiermgt.service.persistence.ServiceProcessRolePK;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.BooleanQueryFactoryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.IndexSearcherHelperUtil;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.generic.MultiMatchQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the service process local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.dossiermgt.service.ServiceProcessLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see ServiceProcessLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.ServiceProcessLocalServiceUtil
 */
@ProviderType
public class ServiceProcessLocalServiceImpl extends ServiceProcessLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.dossiermgt.service.ServiceProcessLocalServiceUtil} to access
	 * the service process local service.
	 */

	static final String PROCESS_NO = "DEFAULT_SERVICE_PROCESS";
	
	@Indexable(type = IndexableType.REINDEX)
	public void cloneServiceProcess(long serviceProcessId, long groupId, String processNo, ServiceContext serviceContext) throws PortalException {
		
		ServiceProcess originServiceProcess = serviceProcessPersistence.fetchByPrimaryKey(serviceProcessId);
		
		long cloneServiceProcessId = counterLocalService.increment(ServiceProcess.class.getName());
		
		ServiceProcess cloneServiceProcess = serviceProcessPersistence.create(cloneServiceProcessId);

		Date now = new Date();

		User userAction = userLocalService.getUser(serviceContext.getUserId());

		// Add audit fields
		cloneServiceProcess.setCompanyId(serviceContext.getCompanyId());
		cloneServiceProcess.setGroupId(groupId);
		cloneServiceProcess.setCreateDate(now);
		cloneServiceProcess.setModifiedDate(now);
		cloneServiceProcess.setUserId(userAction.getUserId());
		cloneServiceProcess.setUserName(userAction.getFullName());
		
		// Add other fields
//		cloneServiceProcess.setProcessNo(originServiceProcess.getProcessNo() + "_CLONE");
		cloneServiceProcess.setProcessNo(processNo);
		cloneServiceProcess.setProcessName(originServiceProcess.getProcessName());
		cloneServiceProcess.setDescription(originServiceProcess.getDescription());
		cloneServiceProcess.setDurationCount(originServiceProcess.getDurationCount());
		cloneServiceProcess.setDurationUnit(originServiceProcess.getDurationUnit());
		cloneServiceProcess.setCounter(0);
		cloneServiceProcess.setGenerateDossierNo(originServiceProcess.getGenerateDossierNo());
		cloneServiceProcess.setDossierNoPattern(originServiceProcess.getDossierNoPattern());
		cloneServiceProcess.setGenerateDueDate(originServiceProcess.getGenerateDueDate());
		cloneServiceProcess.setDueDatePattern(originServiceProcess.getDueDatePattern());
		cloneServiceProcess.setGeneratePassword(originServiceProcess.getGeneratePassword());
		cloneServiceProcess.setDirectNotification(originServiceProcess.getDirectNotification());
		cloneServiceProcess.setServerNo(originServiceProcess.getServerNo());

		serviceProcessPersistence.update(cloneServiceProcess);
		
		List<ServiceProcessRole> processRoles = serviceProcessRolePersistence.findByP_S_ID(serviceProcessId);
		
		//clone processRole
		for (ServiceProcessRole sp : processRoles) {
			//long roleId = counterLocalService.increment(ServiceProcessRole.class.getName());
			
			ServiceProcessRolePK pk = new ServiceProcessRolePK(cloneServiceProcessId, sp.getRoleId());
			
			ServiceProcessRole cloneRole = serviceProcessRolePersistence.create(pk);
			
			cloneRole.setModerator(sp.getModerator());
			cloneRole.setCondition(sp.getCondition());
			
			
			serviceProcessRolePersistence.update(cloneRole);
		}
		
		Indexer<ServiceProcess> indexer = IndexerRegistryUtil.nullSafeGetIndexer(ServiceProcess.class);
		
		try {
			indexer.reindex(cloneServiceProcess);
		} catch (SearchException se) {
			se.printStackTrace();
		}

		Indexer<ProcessStep> stepindexer = IndexerRegistryUtil.nullSafeGetIndexer(ProcessStep.class);

		Indexer<ProcessAction> actionindexer = IndexerRegistryUtil.nullSafeGetIndexer(ProcessAction.class);

		List<ProcessStep> originSteps = processStepPersistence.findByS_P_ID(serviceProcessId);
		
		for (ProcessStep step : originSteps) {
			long cloneStepId = counterLocalService.increment(ProcessStep.class.getName());
			
			ProcessStep cloneStep = processStepPersistence.create(cloneStepId);
			
			// Add audit fields
			cloneStep.setCompanyId(serviceContext.getCompanyId());
			cloneStep.setGroupId(groupId);
			cloneStep.setCreateDate(now);
			cloneStep.setModifiedDate(now);
			cloneStep.setUserId(userAction.getUserId());
			cloneStep.setUserName(userAction.getFullName());

			// Add other fields

//			cloneStep.setStepCode(step.getStepCode() + "_CLONE");
			
			//Hot fixes in land
			cloneStep.setStepCode(step.getStepCode());
			cloneStep.setServiceProcessId(cloneServiceProcessId);
			cloneStep.setStepName(step.getStepName());
			cloneStep.setSequenceNo(step.getSequenceNo());
			cloneStep.setDossierStatus(step.getDossierStatus());
			cloneStep.setDossierSubStatus(step.getDossierSubStatus());
			cloneStep.setDurationCount(step.getDurationCount());
			cloneStep.setCustomProcessUrl(step.getCustomProcessUrl());
			cloneStep.setStepInstruction(step.getStepInstruction());
			cloneStep.setEditable(step.getEditable());
			
			processStepPersistence.update(cloneStep);
			
			try {
				stepindexer.reindex(cloneStep);
			} catch (SearchException se) {
				se.printStackTrace();
			}

			
			List<ProcessStepRole> stepRoles = processStepRolePersistence.findByP_S_ID(cloneStepId);
			
			for (ProcessStepRole role : stepRoles) {
				ProcessStepRolePK pk = new ProcessStepRolePK(cloneStepId, role.getRoleId());
				
				ProcessStepRole cloneStepRole = processStepRolePersistence.create(pk);
				
				cloneStepRole.setModerator(role.getModerator());
				cloneStepRole.setCondition(role.getCondition());
				
				processStepRolePersistence.update(cloneStepRole);
			}
		}
		
		List<ProcessAction> originActions = processActionPersistence.findByS_P_ID(serviceProcessId);
		
		for (ProcessAction act : originActions) {
			long cloneActionId = counterLocalService.increment(ProcessAction.class.getName());
			
			ProcessAction cloneaction = processActionPersistence.create(cloneActionId);
			
			// Add audit fields
			cloneaction.setCompanyId(serviceContext.getCompanyId());
			cloneaction.setGroupId(groupId);
			cloneaction.setCreateDate(now);
			cloneaction.setModifiedDate(now);
			cloneaction.setUserId(userAction.getUserId());
			cloneaction.setUserName(userAction.getFullName());

			cloneaction.setServiceProcessId(cloneServiceProcessId);
			cloneaction.setAutoEvent(act.getAutoEvent());
			cloneaction.setPreCondition(act.getPreCondition());
			cloneaction.setActionCode(act.getActionCode());
			cloneaction.setActionName(act.getActionName());
			cloneaction.setAllowAssignUser(act.getAllowAssignUser());
			cloneaction.setAssignUserId(act.getAssignUserId());
			cloneaction.setRequestPayment(act.getRequestPayment());
			cloneaction.setMakeBriefNote(act.getMakeBriefNote());
			cloneaction.setRollbackable(act.getRollbackable());
			cloneaction.setPreStepCode(act.getPreStepCode());
			cloneaction.setPostStepCode(act.getPostStepCode());
			cloneaction.setSyncActionCode(act.getSyncActionCode());
			
			processActionPersistence.update(cloneaction);
			
			try {
				actionindexer.reindex(cloneaction);
			} catch (SearchException se) {
				se.printStackTrace();
			}

		}
	}
	
	@Indexable(type = IndexableType.REINDEX)
	public void initServiceProcess(long groupId, ServiceContext context) {

		Properties props = new Properties();

		InputStream input = null;

		Date now = new Date();
		
		try {
			
			User userAction = userLocalService.getUser(context.getUserId());
			
			input = this.getClass().getClassLoader().getResourceAsStream("default_service_process.properties");

			//input = new FileInputStream("default_service_process.properties");

			// load a properties file
			props.load(input);

			Enumeration e = props.propertyNames();

			ServiceProcess object = serviceProcessPersistence
					.create(counterLocalService.increment(ServiceProcess.class.getName()));
			
			// Add audit fields
			object.setCompanyId(context.getCompanyId());
			object.setGroupId(groupId);
			object.setCreateDate(now);
			object.setModifiedDate(now);
			object.setUserId(userAction.getUserId());
			object.setUserName(userAction.getFullName());

			// Add other fields

			object.setProcessNo(PROCESS_NO);
			object.setProcessName(PROCESS_NO);
			object.setDescription(PROCESS_NO);
			
			serviceProcessPersistence.update(object);
			
			Indexer<ServiceProcess> indexer = IndexerRegistryUtil.nullSafeGetIndexer(ServiceProcess.class);
			
			try {
				indexer.reindex(object);
			} catch (SearchException se) {
				se.printStackTrace();
			}

			Indexer<ProcessStep> stepindexer = IndexerRegistryUtil.nullSafeGetIndexer(ProcessStep.class);

			while (e.hasMoreElements()) {
				
				int sequenceNo = 1;
				
				String stepCode = "DEFAULT_STEPCODE" + sequenceNo;
				
				String key = (String) e.nextElement();
				
				ProcessStep step = processStepPersistence.create(counterLocalService.increment(ProcessStep.class.getName()));
				
				// Add audit fields
				step.setCompanyId(context.getCompanyId());
				step.setGroupId(groupId);
				step.setCreateDate(now);
				step.setModifiedDate(now);
				step.setUserId(userAction.getUserId());
				step.setUserName(userAction.getFullName());

				// Add other fields

				step.setStepCode(stepCode);
				step.setServiceProcessId(object.getPrimaryKey());
				step.setStepName(props.getProperty(key));
				step.setSequenceNo(String.valueOf(sequenceNo));
				step.setStepCode(key);
				
				step.setDossierStatus(_getDossierStatus(key));
				
				processStepPersistence.update(step);
				
				try {
					stepindexer.reindex(step);
				} catch (SearchException se) {
					se.printStackTrace();
				}


				sequenceNo++;
				
			}

		} catch (Exception e) {
			_log.info(e);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private String _getDossierStatus(String key) {
		
		String dossierStatus;
		
		switch (key) {
		case "110":
			dossierStatus = DossierStatusConstants.NEW;
			break;

		case "120":
			dossierStatus = DossierStatusConstants.COLLECTING;

			break;

		case "130":
			dossierStatus = DossierStatusConstants.RECEIVING;

			break;

		case "131":
			dossierStatus = DossierStatusConstants.WAITING;

			break;

		case "132":
			dossierStatus = DossierStatusConstants.PAYING;

			break;

		case "200":
			dossierStatus = DossierStatusConstants.PROCESSING;

			break;

		case "201":
			dossierStatus = DossierStatusConstants.WAITING;

			break;

		case "202":
			dossierStatus = DossierStatusConstants.PAYING;

			break;

		case "210":
			dossierStatus = DossierStatusConstants.HANDOVER;

			break;

		case "300":
			dossierStatus = DossierStatusConstants.RELEASING;

			break;

		case "302":
			dossierStatus = DossierStatusConstants.PAYING;

			break;

		case "310":
			dossierStatus = DossierStatusConstants.POSTING;

			break;

		case "400":
			dossierStatus = DossierStatusConstants.DONE;

			break;

		case "410":
			dossierStatus = DossierStatusConstants.CANCELLED;

			break;

		default:
			dossierStatus = StringPool.BLANK;
			break;
		}
		
		return dossierStatus;
	}
	
	private String _getDossierStatusName(String dossierStatus) {
		//TODO: add implement to get dossierStatusName
		
		// if not found, need to create new DicItem with dossierStatus, it has dictCollectionCode = "DOSSIER_STATUS";
		
		return "";
	}

	@Indexable(type = IndexableType.REINDEX)
	public ServiceProcess updateServiceProcess(long groupId, long serviceProcessId, String processNo,
			String processName, String description, int durationCount, int durationUnit, long counter,
			boolean generateDossierNo, String dossierNoPattern, boolean generateDueDate, String dueDatePattern,
			boolean generatePassword, boolean directNotification, String serverNo, ServiceContext context)
			throws PortalException {

		Date now = new Date();

		User userAction = userLocalService.getUser(context.getUserId());

		ServiceProcess object = null;

		validateAdd(groupId, serviceProcessId, processNo, processName, generateDossierNo, dossierNoPattern,
				generateDueDate, dueDatePattern, context);

		if (serviceProcessId == 0) {

			serviceProcessId = counterLocalService.increment(ServiceProcess.class.getName());

			object = serviceProcessPersistence.create(serviceProcessId);

			// Add audit fields
			object.setCompanyId(context.getCompanyId());
			object.setGroupId(groupId);
			object.setCreateDate(now);
			object.setModifiedDate(now);
			object.setUserId(userAction.getUserId());
			object.setUserName(userAction.getFullName());

			// Add other fields
			object.setProcessNo(processNo);
			object.setProcessName(processName);
			object.setDescription(description);
			object.setDurationCount(durationCount);
			object.setDurationUnit(durationUnit);
			object.setCounter(counter);
			object.setGenerateDossierNo(generateDossierNo);
			object.setDossierNoPattern(dossierNoPattern);
			object.setGenerateDueDate(generateDueDate);
			object.setDueDatePattern(dueDatePattern);
			object.setGeneratePassword(generatePassword);
			object.setDirectNotification(directNotification);
			object.setServerNo(serverNo);

		} else {
			object = serviceProcessPersistence.fetchByPrimaryKey(serviceProcessId);

			// Add audit fields
			object.setModifiedDate(now);
			object.setUserId(userAction.getUserId());
			object.setUserName(userAction.getFullName());

			// Add other fields
			object.setProcessNo(processNo);
			object.setProcessName(processName);
			object.setDescription(description);
			object.setDurationCount(durationCount);
			object.setDurationUnit(durationUnit);
			object.setCounter(counter);
			object.setGenerateDossierNo(generateDossierNo);
			object.setDossierNoPattern(dossierNoPattern);
			object.setGenerateDueDate(generateDueDate);
			object.setDueDatePattern(dueDatePattern);
			object.setGeneratePassword(generatePassword);
			object.setDirectNotification(directNotification);
			object.setServerNo(serverNo);
		}

		serviceProcessPersistence.update(object);

		return object;
	}

	@Indexable(type = IndexableType.DELETE)
	public ServiceProcess removeServiceProcess(long serviceProcessId, long groupId) throws PortalException {
		validateRemove(serviceProcessId, groupId);
		
		List<ServiceProcessRole> processRoles = serviceProcessRolePersistence.findByP_S_ID(serviceProcessId);

		for (ServiceProcessRole processRole : processRoles) {
			serviceProcessRolePersistence.remove(processRole);
		}
		
		ServiceProcess serviceProcess = serviceProcessPersistence.fetchByPrimaryKey(serviceProcessId);

		serviceProcessPersistence.remove(serviceProcess);

		return serviceProcess;

	}

	public static final String CLASS_NAME = ServiceProcess.class.getName();

	public Hits searchLucene(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {

		String keywords = (String) params.get(Field.KEYWORD_SEARCH);
		String groupId = (String) params.get(Field.GROUP_ID);

		Indexer<ServiceProcess> indexer = IndexerRegistryUtil.nullSafeGetIndexer(ServiceProcess.class);

		searchContext.addFullQueryEntryClassName(CLASS_NAME);
		searchContext.setEntryClassNames(new String[] { CLASS_NAME });
		searchContext.setAttribute("paginationType", "regular");
		searchContext.setLike(true);
		searchContext.setStart(start);
		searchContext.setEnd(end);
		searchContext.setAndSearch(true);
		searchContext.setSorts(sorts);

		BooleanQuery booleanQuery = null;

		if (Validator.isNotNull(keywords)) {
			booleanQuery = BooleanQueryFactoryUtil.create(searchContext);
		} else {
			booleanQuery = indexer.getFullQuery(searchContext);
		}

		if (Validator.isNotNull(keywords)) {

			String[] keyword = keywords.split(StringPool.SPACE);

			for (String string : keyword) {

				MultiMatchQuery query = new MultiMatchQuery(string);

				query.addFields(ServiceProcessTerm.PROCESS_NAME, ServiceProcessTerm.PROCESS_NO,
						ServiceProcessTerm.DESCRIPTION);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(Field.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);
	}

	public long countLucene(LinkedHashMap<String, Object> params, SearchContext searchContext)
			throws ParseException, SearchException {

		String keywords = (String) params.get(Field.KEYWORD_SEARCH);
		String groupId = (String) params.get(Field.GROUP_ID);

		Indexer<ServiceProcess> indexer = IndexerRegistryUtil.nullSafeGetIndexer(ServiceProcess.class);

		searchContext.addFullQueryEntryClassName(CLASS_NAME);
		searchContext.setEntryClassNames(new String[] { CLASS_NAME });
		searchContext.setAttribute("paginationType", "regular");
		searchContext.setLike(true);
		searchContext.setAndSearch(true);

		BooleanQuery booleanQuery = null;

		if (Validator.isNotNull(keywords)) {
			booleanQuery = BooleanQueryFactoryUtil.create(searchContext);
		} else {
			booleanQuery = indexer.getFullQuery(searchContext);
		}

		if (Validator.isNotNull(keywords)) {

			String[] keyword = keywords.split(StringPool.SPACE);

			for (String string : keyword) {

				MultiMatchQuery query = new MultiMatchQuery(string);

				query.addFields(ServiceProcessTerm.PROCESS_NAME, ServiceProcessTerm.PROCESS_NO,
						ServiceProcessTerm.DESCRIPTION);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(Field.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);
	}

	private void validateAdd(long groupId, long serviceProcessId, String processNo, String processName,
			boolean generateDossierNo, String dossierNoPattern, boolean generateDueDate, String dueDatePattern,
			ServiceContext context) throws PortalException {

		if (Validator.isNull(processNo)) {
			throw new RequiredProcessNoException("RequiredProcessNoException");
		}

		if (Validator.isNull(processNo)) {
			throw new RequiredProcessNameException("RequiredProcessNameException");
		}

		if (serviceProcessId == 0) {
			ServiceProcess serviceProcess = null;

			serviceProcess = serviceProcessPersistence.fetchByG_ID_PNO(groupId, processNo);

			if (Validator.isNotNull(serviceProcess)) {
				throw new DuplicateProcessNoException("DuplicateProcessNoException");
			}

			serviceProcess = serviceProcessPersistence.fetchByG_ID_PNO(groupId, processNo);
			if (Validator.isNotNull(serviceProcess)) {
				throw new DuplicateProcessNameException("DuplicateProcessNameException");
			}

		} else {

			ServiceProcess serviceProcess = null;

			serviceProcess = serviceProcessPersistence.fetchByG_ID_PNO(groupId, processNo);

			if (Validator.isNotNull(serviceProcess) && serviceProcessId != serviceProcess.getPrimaryKey()) {
				throw new DuplicateProcessNoException("DuplicateProcessNoException");
			}

			serviceProcess = serviceProcessPersistence.fetchByG_ID_PNO(groupId, processNo);
			if (Validator.isNotNull(serviceProcess) && serviceProcessId != serviceProcess.getPrimaryKey()) {
				throw new DuplicateProcessNameException("DuplicateProcessNameException");
			}

		}

		if (generateDossierNo && Validator.isNull(dossierNoPattern)) {
			throw new RequiredDossierNoPatternException("RequiredDossierNoPatternException");
		}

		if (generateDueDate && Validator.isNull(dueDatePattern)) {
			throw new RequiredDueDatePatternException("RequiredDueDatePatternException");
		}

	}

	@Deprecated
	private void validateUpdate(long groupId, long serviceProcessId, String processNo, String processName,
			boolean generateDossierNo, String dossierNoPattern, boolean generateDueDate, String dueDatePattern,
			ServiceContext context) throws PortalException {

		ServiceProcess spUpdate = serviceProcessPersistence.fetchByPrimaryKey(serviceProcessId);

		if (!spUpdate.getProcessNo().equals(processNo) || !spUpdate.getProcessName().equals(processName)) {
			validateAdd(groupId, serviceProcessId, processNo, processName, generateDossierNo, dossierNoPattern,
					generateDueDate, dueDatePattern, context);
		}

		if (generateDossierNo && Validator.isNull(dossierNoPattern)) {
			throw new RequiredDossierNoPatternException("RequiredDossierNoPatternException");
		}

		if (generateDueDate && Validator.isNull(dueDatePattern)) {
			throw new RequiredDueDatePatternException("RequiredDueDatePatternException");
		}

	}

	private void validateRemove(long serviceProcessId, long groupId) throws PortalException {

		//List<ServiceProcessRole> processRoles = serviceProcessRolePersistence.findByP_S_ID(serviceProcessId);

		List<ProcessStep> processSteps = processStepPersistence.findByS_P_ID(serviceProcessId);

		List<ProcessAction> processActions = processActionPersistence.findByS_P_ID(serviceProcessId);

		if (processSteps.size() != 0 || processActions.size() != 0) {
			throw new HasChildrenException("HasChildrenException");
		}
	}
	
	
	public List<ServiceProcess> getByServerNo(String serverNo) {
		return serviceProcessPersistence.findBySVR_NO(serverNo);
	}

	Log _log = LogFactoryUtil.getLog(ServiceProcessLocalServiceImpl.class);
}