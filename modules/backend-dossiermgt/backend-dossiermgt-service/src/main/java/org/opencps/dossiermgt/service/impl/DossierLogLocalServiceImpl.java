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

import aQute.bnd.annotation.ProviderType;

import java.io.InputStream;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.dossiermgt.action.FileUploadUtils;
import org.opencps.dossiermgt.constants.DossierLogTerm;
import org.opencps.dossiermgt.exception.InvalidDossierStatusException;
import org.opencps.dossiermgt.exception.NoSuchDossierPartException;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierLog;
import org.opencps.dossiermgt.model.DossierPart;
import org.opencps.dossiermgt.service.base.DossierLogLocalServiceBaseImpl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
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
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

/**
 * The implementation of the dossier log local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.dossiermgt.service.DossierLogLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see DossierLogLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.DossierLogLocalServiceUtil
 */
@ProviderType
public class DossierLogLocalServiceImpl extends DossierLogLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.dossiermgt.service.DossierLogLocalServiceUtil} to access the dossier log local service.
	 */
    
    @Indexable(type = IndexableType.REINDEX)
    public DossierLog updateDossierLog(long groupId, long dossierId, long dossierLogId, String author,
            String content, String notificationType, String payload, ServiceContext serviceContext) 
        throws PortalException, SystemException {
        
        long userId = serviceContext.getUserId();

        //TODO: validate
        
        Date now = new Date();

        User userAction = userLocalService.getUser(userId);

        DossierLog object = null;
        if(dossierLogId > 0) {
            object = dossierLogPersistence.findByPrimaryKey(dossierLogId); 
        } else {
            dossierLogId = counterLocalService.increment(DossierLog.class.getName());
            
            object = dossierLogPersistence.create(dossierLogId);
            object.setCreateDate(now);
        }
        
        // Add audit fields
        object.setCompanyId(serviceContext.getCompanyId());
        object.setGroupId(groupId);
        object.setModifiedDate(now);
        object.setUserId(userAction.getUserId());
        object.setUserName(userAction.getFullName());

        // Add other fields
        object.setDossierId(dossierId);
        object.setAuthor(author);
        object.setContent(content);
        object.setNotificationType(notificationType);
        object.setPayload(payload);

        return dossierLogPersistence.update(object);
    }
    
    public DossierLog deleteDossierLog(long dossierLogId) 
            throws PortalException {
        DossierLog dossierLog = dossierLogPersistence.findByPrimaryKey(dossierLogId);
        
        return deleteDossierLog(dossierLog);
    }
    
    @Indexable(type = IndexableType.DELETE)
    public DossierLog deleteDossierLog(DossierLog dossierLog) {
        
        return dossierLogPersistence.remove(dossierLog);
    }
    
    public Hits searchLucene(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
            SearchContext searchContext) throws ParseException, SearchException {

        String keywords = (String) params.get(Field.KEYWORD_SEARCH);
        String groupId = (String) params.get(Field.GROUP_ID);

        Indexer<DossierLog> indexer = IndexerRegistryUtil.nullSafeGetIndexer(DossierLog.class);

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

                query.addFields(DossierLogTerm.FILE_TEMPLATE_NO);

                booleanQuery.add(query, BooleanClauseOccur.MUST);

            }
        }

        if (Validator.isNotNull(groupId)) {
            MultiMatchQuery query = new MultiMatchQuery(groupId);

            query.addFields(Field.GROUP_ID);

            booleanQuery.add(query, BooleanClauseOccur.MUST);
        }
        
        String fileTemplateNo = GetterUtil.getString(params.get(DossierLogTerm.FILE_TEMPLATE_NO));
        String dossierPartType = GetterUtil.getString(params.get(DossierLogTerm.DOSSIER_PART_TYPE));
        String user_id = GetterUtil.getString(params.get(DossierLogTerm.USER_ID));
        String original = GetterUtil.getString(params.get(DossierLogTerm.ORIGINAL));
        
        if (Validator.isNotNull(fileTemplateNo)) {
            MultiMatchQuery query = new MultiMatchQuery(fileTemplateNo);

            query.addFields(DossierLogTerm.FILE_TEMPLATE_NO);

            booleanQuery.add(query, BooleanClauseOccur.MUST);
        }
        
        if (Validator.isNotNull(dossierPartType)) {
            MultiMatchQuery query = new MultiMatchQuery(dossierPartType);

            query.addFields(DossierLogTerm.DOSSIER_PART_TYPE);

            booleanQuery.add(query, BooleanClauseOccur.MUST);
        }
        
        if (Validator.isNotNull(user_id)) {
            MultiMatchQuery query = new MultiMatchQuery(user_id);

            query.addFields(DossierLogTerm.USER_ID);

            booleanQuery.add(query, BooleanClauseOccur.MUST);
        }
        
        if (Validator.isNotNull(original)) {
            MultiMatchQuery query = new MultiMatchQuery(original);

            query.addFields(DossierLogTerm.ORIGINAL);

            booleanQuery.add(query, BooleanClauseOccur.MUST);
        }

        booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

        return IndexSearcherHelperUtil.search(searchContext, booleanQuery);
    }

    public long countLucene(LinkedHashMap<String, Object> params, SearchContext searchContext)
            throws ParseException, SearchException {

        String keywords = (String) params.get(Field.KEYWORD_SEARCH);
        String groupId = (String) params.get(Field.GROUP_ID);

        Indexer<DossierLog> indexer = IndexerRegistryUtil.nullSafeGetIndexer(DossierLog.class);

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

                query.addFields(DossierLogTerm.FILE_TEMPLATE_NO);

                booleanQuery.add(query, BooleanClauseOccur.MUST);

            }
        }

        if (Validator.isNotNull(groupId)) {
            MultiMatchQuery query = new MultiMatchQuery(groupId);

            query.addFields(Field.GROUP_ID);

            booleanQuery.add(query, BooleanClauseOccur.MUST);
        }
        
        String fileTemplateNo = GetterUtil.getString(params.get(DossierLogTerm.FILE_TEMPLATE_NO));
        String dossierPartType = GetterUtil.getString(params.get(DossierLogTerm.DOSSIER_PART_TYPE));
        String user_id = GetterUtil.getString(params.get(DossierLogTerm.USER_ID));
        String original = GetterUtil.getString(params.get(DossierLogTerm.ORIGINAL));
        
        if (Validator.isNotNull(fileTemplateNo)) {
            MultiMatchQuery query = new MultiMatchQuery(fileTemplateNo);

            query.addFields(DossierLogTerm.FILE_TEMPLATE_NO);

            booleanQuery.add(query, BooleanClauseOccur.MUST);
        }
        
        if (Validator.isNotNull(dossierPartType)) {
            MultiMatchQuery query = new MultiMatchQuery(dossierPartType);

            query.addFields(DossierLogTerm.DOSSIER_PART_TYPE);

            booleanQuery.add(query, BooleanClauseOccur.MUST);
        }
        
        if (Validator.isNotNull(user_id)) {
            MultiMatchQuery query = new MultiMatchQuery(user_id);

            query.addFields(DossierLogTerm.USER_ID);

            booleanQuery.add(query, BooleanClauseOccur.MUST);
        }
        
        if (Validator.isNotNull(original)) {
            MultiMatchQuery query = new MultiMatchQuery(original);

            query.addFields(DossierLogTerm.ORIGINAL);

            booleanQuery.add(query, BooleanClauseOccur.MUST);
        }

        booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

        return IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);
    }
    
    public static final String CLASS_NAME = DossierLog.class.getName();
}