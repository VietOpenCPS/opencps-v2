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

package backend.feedback.service.persistence;

import aQute.bnd.annotation.ProviderType;

import backend.feedback.model.Evaluation;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the evaluation service. This utility wraps {@link backend.feedback.service.persistence.impl.EvaluationPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author sondt
 * @see EvaluationPersistence
 * @see backend.feedback.service.persistence.impl.EvaluationPersistenceImpl
 * @generated
 */
@ProviderType
public class EvaluationUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(Evaluation evaluation) {
		getPersistence().clearCache(evaluation);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Evaluation> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Evaluation> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Evaluation> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Evaluation> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Evaluation update(Evaluation evaluation) {
		return getPersistence().update(evaluation);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Evaluation update(Evaluation evaluation,
		ServiceContext serviceContext) {
		return getPersistence().update(evaluation, serviceContext);
	}

	/**
	* Returns all the evaluations where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching evaluations
	*/
	public static List<Evaluation> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the evaluations where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of evaluations
	* @param end the upper bound of the range of evaluations (not inclusive)
	* @return the range of matching evaluations
	*/
	public static List<Evaluation> findByUuid(java.lang.String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the evaluations where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of evaluations
	* @param end the upper bound of the range of evaluations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching evaluations
	*/
	public static List<Evaluation> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<Evaluation> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the evaluations where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of evaluations
	* @param end the upper bound of the range of evaluations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching evaluations
	*/
	public static List<Evaluation> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<Evaluation> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first evaluation in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching evaluation
	* @throws NoSuchEvaluationException if a matching evaluation could not be found
	*/
	public static Evaluation findByUuid_First(java.lang.String uuid,
		OrderByComparator<Evaluation> orderByComparator)
		throws backend.feedback.exception.NoSuchEvaluationException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first evaluation in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching evaluation, or <code>null</code> if a matching evaluation could not be found
	*/
	public static Evaluation fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<Evaluation> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last evaluation in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching evaluation
	* @throws NoSuchEvaluationException if a matching evaluation could not be found
	*/
	public static Evaluation findByUuid_Last(java.lang.String uuid,
		OrderByComparator<Evaluation> orderByComparator)
		throws backend.feedback.exception.NoSuchEvaluationException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last evaluation in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching evaluation, or <code>null</code> if a matching evaluation could not be found
	*/
	public static Evaluation fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<Evaluation> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the evaluations before and after the current evaluation in the ordered set where uuid = &#63;.
	*
	* @param evaluationId the primary key of the current evaluation
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next evaluation
	* @throws NoSuchEvaluationException if a evaluation with the primary key could not be found
	*/
	public static Evaluation[] findByUuid_PrevAndNext(long evaluationId,
		java.lang.String uuid, OrderByComparator<Evaluation> orderByComparator)
		throws backend.feedback.exception.NoSuchEvaluationException {
		return getPersistence()
				   .findByUuid_PrevAndNext(evaluationId, uuid, orderByComparator);
	}

	/**
	* Removes all the evaluations where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of evaluations where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching evaluations
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the evaluation where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchEvaluationException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching evaluation
	* @throws NoSuchEvaluationException if a matching evaluation could not be found
	*/
	public static Evaluation findByUUID_G(java.lang.String uuid, long groupId)
		throws backend.feedback.exception.NoSuchEvaluationException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the evaluation where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching evaluation, or <code>null</code> if a matching evaluation could not be found
	*/
	public static Evaluation fetchByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the evaluation where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching evaluation, or <code>null</code> if a matching evaluation could not be found
	*/
	public static Evaluation fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the evaluation where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the evaluation that was removed
	*/
	public static Evaluation removeByUUID_G(java.lang.String uuid, long groupId)
		throws backend.feedback.exception.NoSuchEvaluationException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of evaluations where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching evaluations
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the evaluations where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching evaluations
	*/
	public static List<Evaluation> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the evaluations where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of evaluations
	* @param end the upper bound of the range of evaluations (not inclusive)
	* @return the range of matching evaluations
	*/
	public static List<Evaluation> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the evaluations where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of evaluations
	* @param end the upper bound of the range of evaluations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching evaluations
	*/
	public static List<Evaluation> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<Evaluation> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the evaluations where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of evaluations
	* @param end the upper bound of the range of evaluations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching evaluations
	*/
	public static List<Evaluation> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<Evaluation> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first evaluation in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching evaluation
	* @throws NoSuchEvaluationException if a matching evaluation could not be found
	*/
	public static Evaluation findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<Evaluation> orderByComparator)
		throws backend.feedback.exception.NoSuchEvaluationException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first evaluation in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching evaluation, or <code>null</code> if a matching evaluation could not be found
	*/
	public static Evaluation fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<Evaluation> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last evaluation in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching evaluation
	* @throws NoSuchEvaluationException if a matching evaluation could not be found
	*/
	public static Evaluation findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<Evaluation> orderByComparator)
		throws backend.feedback.exception.NoSuchEvaluationException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last evaluation in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching evaluation, or <code>null</code> if a matching evaluation could not be found
	*/
	public static Evaluation fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<Evaluation> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the evaluations before and after the current evaluation in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param evaluationId the primary key of the current evaluation
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next evaluation
	* @throws NoSuchEvaluationException if a evaluation with the primary key could not be found
	*/
	public static Evaluation[] findByUuid_C_PrevAndNext(long evaluationId,
		java.lang.String uuid, long companyId,
		OrderByComparator<Evaluation> orderByComparator)
		throws backend.feedback.exception.NoSuchEvaluationException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(evaluationId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the evaluations where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of evaluations where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching evaluations
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the evaluations where employeeId = &#63;.
	*
	* @param employeeId the employee ID
	* @return the matching evaluations
	*/
	public static List<Evaluation> findByEM_ID(long employeeId) {
		return getPersistence().findByEM_ID(employeeId);
	}

	/**
	* Returns a range of all the evaluations where employeeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param employeeId the employee ID
	* @param start the lower bound of the range of evaluations
	* @param end the upper bound of the range of evaluations (not inclusive)
	* @return the range of matching evaluations
	*/
	public static List<Evaluation> findByEM_ID(long employeeId, int start,
		int end) {
		return getPersistence().findByEM_ID(employeeId, start, end);
	}

	/**
	* Returns an ordered range of all the evaluations where employeeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param employeeId the employee ID
	* @param start the lower bound of the range of evaluations
	* @param end the upper bound of the range of evaluations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching evaluations
	*/
	public static List<Evaluation> findByEM_ID(long employeeId, int start,
		int end, OrderByComparator<Evaluation> orderByComparator) {
		return getPersistence()
				   .findByEM_ID(employeeId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the evaluations where employeeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param employeeId the employee ID
	* @param start the lower bound of the range of evaluations
	* @param end the upper bound of the range of evaluations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching evaluations
	*/
	public static List<Evaluation> findByEM_ID(long employeeId, int start,
		int end, OrderByComparator<Evaluation> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByEM_ID(employeeId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first evaluation in the ordered set where employeeId = &#63;.
	*
	* @param employeeId the employee ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching evaluation
	* @throws NoSuchEvaluationException if a matching evaluation could not be found
	*/
	public static Evaluation findByEM_ID_First(long employeeId,
		OrderByComparator<Evaluation> orderByComparator)
		throws backend.feedback.exception.NoSuchEvaluationException {
		return getPersistence().findByEM_ID_First(employeeId, orderByComparator);
	}

	/**
	* Returns the first evaluation in the ordered set where employeeId = &#63;.
	*
	* @param employeeId the employee ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching evaluation, or <code>null</code> if a matching evaluation could not be found
	*/
	public static Evaluation fetchByEM_ID_First(long employeeId,
		OrderByComparator<Evaluation> orderByComparator) {
		return getPersistence().fetchByEM_ID_First(employeeId, orderByComparator);
	}

	/**
	* Returns the last evaluation in the ordered set where employeeId = &#63;.
	*
	* @param employeeId the employee ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching evaluation
	* @throws NoSuchEvaluationException if a matching evaluation could not be found
	*/
	public static Evaluation findByEM_ID_Last(long employeeId,
		OrderByComparator<Evaluation> orderByComparator)
		throws backend.feedback.exception.NoSuchEvaluationException {
		return getPersistence().findByEM_ID_Last(employeeId, orderByComparator);
	}

	/**
	* Returns the last evaluation in the ordered set where employeeId = &#63;.
	*
	* @param employeeId the employee ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching evaluation, or <code>null</code> if a matching evaluation could not be found
	*/
	public static Evaluation fetchByEM_ID_Last(long employeeId,
		OrderByComparator<Evaluation> orderByComparator) {
		return getPersistence().fetchByEM_ID_Last(employeeId, orderByComparator);
	}

	/**
	* Returns the evaluations before and after the current evaluation in the ordered set where employeeId = &#63;.
	*
	* @param evaluationId the primary key of the current evaluation
	* @param employeeId the employee ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next evaluation
	* @throws NoSuchEvaluationException if a evaluation with the primary key could not be found
	*/
	public static Evaluation[] findByEM_ID_PrevAndNext(long evaluationId,
		long employeeId, OrderByComparator<Evaluation> orderByComparator)
		throws backend.feedback.exception.NoSuchEvaluationException {
		return getPersistence()
				   .findByEM_ID_PrevAndNext(evaluationId, employeeId,
			orderByComparator);
	}

	/**
	* Removes all the evaluations where employeeId = &#63; from the database.
	*
	* @param employeeId the employee ID
	*/
	public static void removeByEM_ID(long employeeId) {
		getPersistence().removeByEM_ID(employeeId);
	}

	/**
	* Returns the number of evaluations where employeeId = &#63;.
	*
	* @param employeeId the employee ID
	* @return the number of matching evaluations
	*/
	public static int countByEM_ID(long employeeId) {
		return getPersistence().countByEM_ID(employeeId);
	}

	/**
	* Returns all the evaluations where employeeId = &#63; and score = &#63;.
	*
	* @param employeeId the employee ID
	* @param score the score
	* @return the matching evaluations
	*/
	public static List<Evaluation> findByEM_ID_S(long employeeId, int score) {
		return getPersistence().findByEM_ID_S(employeeId, score);
	}

	/**
	* Returns a range of all the evaluations where employeeId = &#63; and score = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param employeeId the employee ID
	* @param score the score
	* @param start the lower bound of the range of evaluations
	* @param end the upper bound of the range of evaluations (not inclusive)
	* @return the range of matching evaluations
	*/
	public static List<Evaluation> findByEM_ID_S(long employeeId, int score,
		int start, int end) {
		return getPersistence().findByEM_ID_S(employeeId, score, start, end);
	}

	/**
	* Returns an ordered range of all the evaluations where employeeId = &#63; and score = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param employeeId the employee ID
	* @param score the score
	* @param start the lower bound of the range of evaluations
	* @param end the upper bound of the range of evaluations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching evaluations
	*/
	public static List<Evaluation> findByEM_ID_S(long employeeId, int score,
		int start, int end, OrderByComparator<Evaluation> orderByComparator) {
		return getPersistence()
				   .findByEM_ID_S(employeeId, score, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the evaluations where employeeId = &#63; and score = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param employeeId the employee ID
	* @param score the score
	* @param start the lower bound of the range of evaluations
	* @param end the upper bound of the range of evaluations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching evaluations
	*/
	public static List<Evaluation> findByEM_ID_S(long employeeId, int score,
		int start, int end, OrderByComparator<Evaluation> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByEM_ID_S(employeeId, score, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first evaluation in the ordered set where employeeId = &#63; and score = &#63;.
	*
	* @param employeeId the employee ID
	* @param score the score
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching evaluation
	* @throws NoSuchEvaluationException if a matching evaluation could not be found
	*/
	public static Evaluation findByEM_ID_S_First(long employeeId, int score,
		OrderByComparator<Evaluation> orderByComparator)
		throws backend.feedback.exception.NoSuchEvaluationException {
		return getPersistence()
				   .findByEM_ID_S_First(employeeId, score, orderByComparator);
	}

	/**
	* Returns the first evaluation in the ordered set where employeeId = &#63; and score = &#63;.
	*
	* @param employeeId the employee ID
	* @param score the score
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching evaluation, or <code>null</code> if a matching evaluation could not be found
	*/
	public static Evaluation fetchByEM_ID_S_First(long employeeId, int score,
		OrderByComparator<Evaluation> orderByComparator) {
		return getPersistence()
				   .fetchByEM_ID_S_First(employeeId, score, orderByComparator);
	}

	/**
	* Returns the last evaluation in the ordered set where employeeId = &#63; and score = &#63;.
	*
	* @param employeeId the employee ID
	* @param score the score
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching evaluation
	* @throws NoSuchEvaluationException if a matching evaluation could not be found
	*/
	public static Evaluation findByEM_ID_S_Last(long employeeId, int score,
		OrderByComparator<Evaluation> orderByComparator)
		throws backend.feedback.exception.NoSuchEvaluationException {
		return getPersistence()
				   .findByEM_ID_S_Last(employeeId, score, orderByComparator);
	}

	/**
	* Returns the last evaluation in the ordered set where employeeId = &#63; and score = &#63;.
	*
	* @param employeeId the employee ID
	* @param score the score
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching evaluation, or <code>null</code> if a matching evaluation could not be found
	*/
	public static Evaluation fetchByEM_ID_S_Last(long employeeId, int score,
		OrderByComparator<Evaluation> orderByComparator) {
		return getPersistence()
				   .fetchByEM_ID_S_Last(employeeId, score, orderByComparator);
	}

	/**
	* Returns the evaluations before and after the current evaluation in the ordered set where employeeId = &#63; and score = &#63;.
	*
	* @param evaluationId the primary key of the current evaluation
	* @param employeeId the employee ID
	* @param score the score
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next evaluation
	* @throws NoSuchEvaluationException if a evaluation with the primary key could not be found
	*/
	public static Evaluation[] findByEM_ID_S_PrevAndNext(long evaluationId,
		long employeeId, int score,
		OrderByComparator<Evaluation> orderByComparator)
		throws backend.feedback.exception.NoSuchEvaluationException {
		return getPersistence()
				   .findByEM_ID_S_PrevAndNext(evaluationId, employeeId, score,
			orderByComparator);
	}

	/**
	* Removes all the evaluations where employeeId = &#63; and score = &#63; from the database.
	*
	* @param employeeId the employee ID
	* @param score the score
	*/
	public static void removeByEM_ID_S(long employeeId, int score) {
		getPersistence().removeByEM_ID_S(employeeId, score);
	}

	/**
	* Returns the number of evaluations where employeeId = &#63; and score = &#63;.
	*
	* @param employeeId the employee ID
	* @param score the score
	* @return the number of matching evaluations
	*/
	public static int countByEM_ID_S(long employeeId, int score) {
		return getPersistence().countByEM_ID_S(employeeId, score);
	}

	/**
	* Caches the evaluation in the entity cache if it is enabled.
	*
	* @param evaluation the evaluation
	*/
	public static void cacheResult(Evaluation evaluation) {
		getPersistence().cacheResult(evaluation);
	}

	/**
	* Caches the evaluations in the entity cache if it is enabled.
	*
	* @param evaluations the evaluations
	*/
	public static void cacheResult(List<Evaluation> evaluations) {
		getPersistence().cacheResult(evaluations);
	}

	/**
	* Creates a new evaluation with the primary key. Does not add the evaluation to the database.
	*
	* @param evaluationId the primary key for the new evaluation
	* @return the new evaluation
	*/
	public static Evaluation create(long evaluationId) {
		return getPersistence().create(evaluationId);
	}

	/**
	* Removes the evaluation with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param evaluationId the primary key of the evaluation
	* @return the evaluation that was removed
	* @throws NoSuchEvaluationException if a evaluation with the primary key could not be found
	*/
	public static Evaluation remove(long evaluationId)
		throws backend.feedback.exception.NoSuchEvaluationException {
		return getPersistence().remove(evaluationId);
	}

	public static Evaluation updateImpl(Evaluation evaluation) {
		return getPersistence().updateImpl(evaluation);
	}

	/**
	* Returns the evaluation with the primary key or throws a {@link NoSuchEvaluationException} if it could not be found.
	*
	* @param evaluationId the primary key of the evaluation
	* @return the evaluation
	* @throws NoSuchEvaluationException if a evaluation with the primary key could not be found
	*/
	public static Evaluation findByPrimaryKey(long evaluationId)
		throws backend.feedback.exception.NoSuchEvaluationException {
		return getPersistence().findByPrimaryKey(evaluationId);
	}

	/**
	* Returns the evaluation with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param evaluationId the primary key of the evaluation
	* @return the evaluation, or <code>null</code> if a evaluation with the primary key could not be found
	*/
	public static Evaluation fetchByPrimaryKey(long evaluationId) {
		return getPersistence().fetchByPrimaryKey(evaluationId);
	}

	public static java.util.Map<java.io.Serializable, Evaluation> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the evaluations.
	*
	* @return the evaluations
	*/
	public static List<Evaluation> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the evaluations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of evaluations
	* @param end the upper bound of the range of evaluations (not inclusive)
	* @return the range of evaluations
	*/
	public static List<Evaluation> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the evaluations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of evaluations
	* @param end the upper bound of the range of evaluations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of evaluations
	*/
	public static List<Evaluation> findAll(int start, int end,
		OrderByComparator<Evaluation> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the evaluations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EvaluationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of evaluations
	* @param end the upper bound of the range of evaluations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of evaluations
	*/
	public static List<Evaluation> findAll(int start, int end,
		OrderByComparator<Evaluation> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the evaluations from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of evaluations.
	*
	* @return the number of evaluations
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static EvaluationPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<EvaluationPersistence, EvaluationPersistence> _serviceTracker =
		ServiceTrackerFactory.open(EvaluationPersistence.class);
}