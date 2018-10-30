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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for Voting. This utility wraps
 * {@link backend.feedback.service.impl.VotingLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author sondt
 * @see VotingLocalService
 * @see backend.feedback.service.base.VotingLocalServiceBaseImpl
 * @see backend.feedback.service.impl.VotingLocalServiceImpl
 * @generated
 */
@ProviderType
public class VotingLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link backend.feedback.service.impl.VotingLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static backend.feedback.model.Voting addVoting(long userId,
		long groupId, String className, String classPK, String subject,
		String choices, String templateNo, boolean commentable,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.NoSuchUserException {
		return getService()
				   .addVoting(userId, groupId, className, classPK, subject,
			choices, templateNo, commentable, serviceContext);
	}

	/**
	* Adds the voting to the database. Also notifies the appropriate model listeners.
	*
	* @param voting the voting
	* @return the voting that was added
	*/
	public static backend.feedback.model.Voting addVoting(
		backend.feedback.model.Voting voting) {
		return getService().addVoting(voting);
	}

	public static backend.feedback.model.Voting adminProcessData(
		com.liferay.portal.kernel.json.JSONObject objectData) {
		return getService().adminProcessData(objectData);
	}

	public static backend.feedback.model.Voting adminProcessDelete(Long id) {
		return getService().adminProcessDelete(id);
	}

	public static long countLuceneSearchEngine(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return getService().countLuceneSearchEngine(params, searchContext);
	}

	public static long countVotingByClass_Name_PK(String className,
		String classPK) {
		return getService().countVotingByClass_Name_PK(className, classPK);
	}

	/**
	* Creates a new voting with the primary key. Does not add the voting to the database.
	*
	* @param votingId the primary key for the new voting
	* @return the new voting
	*/
	public static backend.feedback.model.Voting createVoting(long votingId) {
		return getService().createVoting(votingId);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	/**
	* @param dictCollectionId
	* @param serviceContext
	* @return
	* @throws NoSuchVotingException
	* @throws NotFoundException
	* @throws Exception
	*/
	public static backend.feedback.model.Voting deleteVote(long votingId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws backend.feedback.exception.NoSuchVotingException {
		return getService().deleteVote(votingId, serviceContext);
	}

	/**
	* Deletes the voting with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param votingId the primary key of the voting
	* @return the voting that was removed
	* @throws PortalException if a voting with the primary key could not be found
	*/
	public static backend.feedback.model.Voting deleteVoting(long votingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteVoting(votingId);
	}

	/**
	* Deletes the voting from the database. Also notifies the appropriate model listeners.
	*
	* @param voting the voting
	* @return the voting that was removed
	*/
	public static backend.feedback.model.Voting deleteVoting(
		backend.feedback.model.Voting voting) {
		return getService().deleteVoting(voting);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static backend.feedback.model.Voting fetchVoting(long votingId) {
		return getService().fetchVoting(votingId);
	}

	/**
	* Returns the voting matching the UUID and group.
	*
	* @param uuid the voting's UUID
	* @param groupId the primary key of the group
	* @return the matching voting, or <code>null</code> if a matching voting could not be found
	*/
	public static backend.feedback.model.Voting fetchVotingByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchVotingByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the voting with the primary key.
	*
	* @param votingId the primary key of the voting
	* @return the voting
	* @throws PortalException if a voting with the primary key could not be found
	*/
	public static backend.feedback.model.Voting getVoting(long votingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getVoting(votingId);
	}

	public static java.util.List<backend.feedback.model.Voting> getVotingByClass_Name_PK(
		String className, String classPK) {
		return getService().getVotingByClass_Name_PK(className, classPK);
	}

	/**
	* Returns the voting matching the UUID and group.
	*
	* @param uuid the voting's UUID
	* @param groupId the primary key of the group
	* @return the matching voting
	* @throws PortalException if a matching voting could not be found
	*/
	public static backend.feedback.model.Voting getVotingByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getVotingByUuidAndGroupId(uuid, groupId);
	}

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
	public static java.util.List<backend.feedback.model.Voting> getVotings(
		int start, int end) {
		return getService().getVotings(start, end);
	}

	/**
	* Returns all the votings matching the UUID and company.
	*
	* @param uuid the UUID of the votings
	* @param companyId the primary key of the company
	* @return the matching votings, or an empty list if no matches were found
	*/
	public static java.util.List<backend.feedback.model.Voting> getVotingsByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService().getVotingsByUuidAndCompanyId(uuid, companyId);
	}

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
	public static java.util.List<backend.feedback.model.Voting> getVotingsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<backend.feedback.model.Voting> orderByComparator) {
		return getService()
				   .getVotingsByUuidAndCompanyId(uuid, companyId, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of votings.
	*
	* @return the number of votings
	*/
	public static int getVotingsCount() {
		return getService().getVotingsCount();
	}

	public static com.liferay.portal.kernel.search.Hits luceneSearchEngine(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.Sort[] sorts, int start, int end,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return getService()
				   .luceneSearchEngine(params, sorts, start, end, searchContext);
	}

	public static backend.feedback.model.Voting updateVote(long userId,
		long votingId, String className, String classPK, String subject,
		String choices, String templateNo, boolean commentable,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.NoSuchUserException {
		return getService()
				   .updateVote(userId, votingId, className, classPK, subject,
			choices, templateNo, commentable, serviceContext);
	}

	/**
	* Updates the voting in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param voting the voting
	* @return the voting that was updated
	*/
	public static backend.feedback.model.Voting updateVoting(
		backend.feedback.model.Voting voting) {
		return getService().updateVoting(voting);
	}

	public static VotingLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<VotingLocalService, VotingLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(VotingLocalService.class);

		ServiceTracker<VotingLocalService, VotingLocalService> serviceTracker = new ServiceTracker<VotingLocalService, VotingLocalService>(bundle.getBundleContext(),
				VotingLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}