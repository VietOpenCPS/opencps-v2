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

package org.mobilink.backend.datamgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.mobilink.backend.datamgt.exception.NoSuchLabelException;
import org.mobilink.backend.datamgt.model.Label;

/**
 * The persistence interface for the label service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Binhth
 * @see org.mobilink.backend.datamgt.service.persistence.impl.LabelPersistenceImpl
 * @see LabelUtil
 * @generated
 */
@ProviderType
public interface LabelPersistence extends BasePersistence<Label> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LabelUtil} to access the label persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the labels where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching labels
	*/
	public java.util.List<Label> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the labels where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LabelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of labels
	* @param end the upper bound of the range of labels (not inclusive)
	* @return the range of matching labels
	*/
	public java.util.List<Label> findByUuid(java.lang.String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the labels where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LabelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of labels
	* @param end the upper bound of the range of labels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching labels
	*/
	public java.util.List<Label> findByUuid(java.lang.String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Label> orderByComparator);

	/**
	* Returns an ordered range of all the labels where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LabelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of labels
	* @param end the upper bound of the range of labels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching labels
	*/
	public java.util.List<Label> findByUuid(java.lang.String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Label> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first label in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching label
	* @throws NoSuchLabelException if a matching label could not be found
	*/
	public Label findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Label> orderByComparator)
		throws NoSuchLabelException;

	/**
	* Returns the first label in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching label, or <code>null</code> if a matching label could not be found
	*/
	public Label fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Label> orderByComparator);

	/**
	* Returns the last label in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching label
	* @throws NoSuchLabelException if a matching label could not be found
	*/
	public Label findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Label> orderByComparator)
		throws NoSuchLabelException;

	/**
	* Returns the last label in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching label, or <code>null</code> if a matching label could not be found
	*/
	public Label fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Label> orderByComparator);

	/**
	* Returns the labels before and after the current label in the ordered set where uuid = &#63;.
	*
	* @param labelId the primary key of the current label
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next label
	* @throws NoSuchLabelException if a label with the primary key could not be found
	*/
	public Label[] findByUuid_PrevAndNext(long labelId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Label> orderByComparator)
		throws NoSuchLabelException;

	/**
	* Removes all the labels where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of labels where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching labels
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the label where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchLabelException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching label
	* @throws NoSuchLabelException if a matching label could not be found
	*/
	public Label findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchLabelException;

	/**
	* Returns the label where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching label, or <code>null</code> if a matching label could not be found
	*/
	public Label fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the label where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching label, or <code>null</code> if a matching label could not be found
	*/
	public Label fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the label where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the label that was removed
	*/
	public Label removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchLabelException;

	/**
	* Returns the number of labels where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching labels
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the labels where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching labels
	*/
	public java.util.List<Label> findByUuid_C(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of all the labels where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LabelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of labels
	* @param end the upper bound of the range of labels (not inclusive)
	* @return the range of matching labels
	*/
	public java.util.List<Label> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the labels where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LabelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of labels
	* @param end the upper bound of the range of labels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching labels
	*/
	public java.util.List<Label> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Label> orderByComparator);

	/**
	* Returns an ordered range of all the labels where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LabelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of labels
	* @param end the upper bound of the range of labels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching labels
	*/
	public java.util.List<Label> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Label> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first label in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching label
	* @throws NoSuchLabelException if a matching label could not be found
	*/
	public Label findByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Label> orderByComparator)
		throws NoSuchLabelException;

	/**
	* Returns the first label in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching label, or <code>null</code> if a matching label could not be found
	*/
	public Label fetchByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Label> orderByComparator);

	/**
	* Returns the last label in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching label
	* @throws NoSuchLabelException if a matching label could not be found
	*/
	public Label findByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Label> orderByComparator)
		throws NoSuchLabelException;

	/**
	* Returns the last label in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching label, or <code>null</code> if a matching label could not be found
	*/
	public Label fetchByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Label> orderByComparator);

	/**
	* Returns the labels before and after the current label in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param labelId the primary key of the current label
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next label
	* @throws NoSuchLabelException if a label with the primary key could not be found
	*/
	public Label[] findByUuid_C_PrevAndNext(long labelId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Label> orderByComparator)
		throws NoSuchLabelException;

	/**
	* Removes all the labels where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of labels where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching labels
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Caches the label in the entity cache if it is enabled.
	*
	* @param label the label
	*/
	public void cacheResult(Label label);

	/**
	* Caches the labels in the entity cache if it is enabled.
	*
	* @param labels the labels
	*/
	public void cacheResult(java.util.List<Label> labels);

	/**
	* Creates a new label with the primary key. Does not add the label to the database.
	*
	* @param labelId the primary key for the new label
	* @return the new label
	*/
	public Label create(long labelId);

	/**
	* Removes the label with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param labelId the primary key of the label
	* @return the label that was removed
	* @throws NoSuchLabelException if a label with the primary key could not be found
	*/
	public Label remove(long labelId) throws NoSuchLabelException;

	public Label updateImpl(Label label);

	/**
	* Returns the label with the primary key or throws a {@link NoSuchLabelException} if it could not be found.
	*
	* @param labelId the primary key of the label
	* @return the label
	* @throws NoSuchLabelException if a label with the primary key could not be found
	*/
	public Label findByPrimaryKey(long labelId) throws NoSuchLabelException;

	/**
	* Returns the label with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param labelId the primary key of the label
	* @return the label, or <code>null</code> if a label with the primary key could not be found
	*/
	public Label fetchByPrimaryKey(long labelId);

	@Override
	public java.util.Map<java.io.Serializable, Label> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the labels.
	*
	* @return the labels
	*/
	public java.util.List<Label> findAll();

	/**
	* Returns a range of all the labels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LabelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of labels
	* @param end the upper bound of the range of labels (not inclusive)
	* @return the range of labels
	*/
	public java.util.List<Label> findAll(int start, int end);

	/**
	* Returns an ordered range of all the labels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LabelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of labels
	* @param end the upper bound of the range of labels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of labels
	*/
	public java.util.List<Label> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Label> orderByComparator);

	/**
	* Returns an ordered range of all the labels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LabelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of labels
	* @param end the upper bound of the range of labels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of labels
	*/
	public java.util.List<Label> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Label> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the labels from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of labels.
	*
	* @return the number of labels
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}