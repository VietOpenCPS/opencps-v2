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

package backend.feedback.service;

import aQute.bnd.annotation.ProviderType;

import backend.feedback.exception.NoSuchVotingException;

import backend.feedback.model.Voting;

import com.liferay.exportimport.kernel.lar.PortletDataContext;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Provides the local service interface for Voting. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author sondt
 * @see VotingLocalServiceUtil
 * @see backend.feedback.service.base.VotingLocalServiceBaseImpl
 * @see backend.feedback.service.impl.VotingLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface VotingLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link VotingLocalServiceUtil} to access the voting local service. Add custom service methods to {@link backend.feedback.service.impl.VotingLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Indexable(type = IndexableType.REINDEX)
	public Voting addVoting(long userId, long groupId, String className,
		String classPK, String subject, String choices, String templateNo,
		boolean commentable, ServiceContext serviceContext)
		throws NoSuchUserException;

	/**
	* Adds the voting to the database. Also notifies the appropriate model listeners.
	*
	* @param voting the voting
	* @return the voting that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Voting addVoting(Voting voting);

	@Indexable(type = IndexableType.REINDEX)
	public Voting adminProcessData(JSONObject objectData);

	@Indexable(type = IndexableType.DELETE)
	public Voting adminProcessDelete(Long id);

	public long countLuceneSearchEngine(LinkedHashMap<String, Object> params,
		SearchContext searchContext) throws ParseException, SearchException;

	public long countVotingByClass_Name_PK(String className, String classPK);

	/**
	* Creates a new voting with the primary key. Does not add the voting to the database.
	*
	* @param votingId the primary key for the new voting
	* @return the new voting
	*/
	@Transactional(enabled = false)
	public Voting createVoting(long votingId);

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	/**
	* @param dictCollectionId
	* @param serviceContext
	* @return
	* @throws NoSuchVotingException
	* @throws NotFoundException
	* @throws Exception
	*/
	@Indexable(type = IndexableType.DELETE)
	public Voting deleteVote(long votingId, ServiceContext serviceContext)
		throws NoSuchVotingException;

	/**
	* Deletes the voting with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param votingId the primary key of the voting
	* @return the voting that was removed
	* @throws PortalException if a voting with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public Voting deleteVoting(long votingId) throws PortalException;

	/**
	* Deletes the voting from the database. Also notifies the appropriate model listeners.
	*
	* @param voting the voting
	* @return the voting that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public Voting deleteVoting(Voting voting);

	public DynamicQuery dynamicQuery();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link backend.feedback.model.impl.VotingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link backend.feedback.model.impl.VotingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Voting fetchVoting(long votingId);

	/**
	* Returns the voting matching the UUID and group.
	*
	* @param uuid the voting's UUID
	* @param groupId the primary key of the group
	* @return the matching voting, or <code>null</code> if a matching voting could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Voting fetchVotingByUuidAndGroupId(String uuid, long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public String getOSGiServiceIdentifier();

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Returns the voting with the primary key.
	*
	* @param votingId the primary key of the voting
	* @return the voting
	* @throws PortalException if a voting with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Voting getVoting(long votingId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Voting> getVotingByClass_Name_PK(String className,
		String classPK);

	/**
	* Returns the voting matching the UUID and group.
	*
	* @param uuid the voting's UUID
	* @param groupId the primary key of the group
	* @return the matching voting
	* @throws PortalException if a matching voting could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Voting getVotingByUuidAndGroupId(String uuid, long groupId)
		throws PortalException;

	/**
	* Returns a range of all the votings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link backend.feedback.model.impl.VotingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of votings
	* @param end the upper bound of the range of votings (not inclusive)
	* @return the range of votings
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Voting> getVotings(int start, int end);

	/**
	* Returns all the votings matching the UUID and company.
	*
	* @param uuid the UUID of the votings
	* @param companyId the primary key of the company
	* @return the matching votings, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Voting> getVotingsByUuidAndCompanyId(String uuid, long companyId);

	/**
	* Returns a range of votings matching the UUID and company.
	*
	* @param uuid the UUID of the votings
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of votings
	* @param end the upper bound of the range of votings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching votings, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Voting> getVotingsByUuidAndCompanyId(String uuid,
		long companyId, int start, int end,
		OrderByComparator<Voting> orderByComparator);

	/**
	* Returns the number of votings.
	*
	* @return the number of votings
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getVotingsCount();

	public Hits luceneSearchEngine(LinkedHashMap<String, Object> params,
		Sort[] sorts, int start, int end, SearchContext searchContext)
		throws ParseException, SearchException;

	@Indexable(type = IndexableType.REINDEX)
	public Voting updateVote(long userId, long votingId, String className,
		String classPK, String subject, String choices, String templateNo,
		boolean commentable, ServiceContext serviceContext)
		throws NoSuchUserException;

	/**
	* Updates the voting in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param voting the voting
	* @return the voting that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Voting updateVoting(Voting voting);
}