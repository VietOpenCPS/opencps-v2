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

package org.opencps.dossiermgt.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.dossiermgt.exception.NoSuchNotarizationException;
import org.opencps.dossiermgt.model.Notarization;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

/**
 * Provides the local service interface for Notarization. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author huymq
 * @see NotarizationLocalServiceUtil
 * @see org.opencps.dossiermgt.service.base.NotarizationLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.NotarizationLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface NotarizationLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link NotarizationLocalServiceUtil} to access the notarization local service. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.NotarizationLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Indexable(type = IndexableType.REINDEX)
	public Notarization addNotarization(long groupId, long dossierId,
		String fileName, int totalRecord, int totalPage, int totalCopy,
		long totalFee, long notarizationNo, int notarizationYear,
		Date notarizationDate, String signerName, String signerPosition,
		String statusCode, ServiceContext serviceContext);

	/**
	* Adds the notarization to the database. Also notifies the appropriate model listeners.
	*
	* @param notarization the notarization
	* @return the notarization that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Notarization addNotarization(Notarization notarization);

	public int countByG_DID(long groupId, long dossierId);

	/**
	* Creates a new notarization with the primary key. Does not add the notarization to the database.
	*
	* @param notarizationId the primary key for the new notarization
	* @return the new notarization
	*/
	@Transactional(enabled = false)
	public Notarization createNotarization(long notarizationId);

	/**
	* Deletes the notarization with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param notarizationId the primary key of the notarization
	* @return the notarization that was removed
	* @throws PortalException if a notarization with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public Notarization deleteNotarization(long notarizationId)
		throws PortalException;

	/**
	* Deletes the notarization from the database. Also notifies the appropriate model listeners.
	*
	* @param notarization the notarization
	* @return the notarization that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public Notarization deleteNotarization(Notarization notarization);

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.NotarizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.NotarizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public Notarization fetchNotarization(long notarizationId);

	public List<Notarization> findByG_DID(long groupId, long dossierId);

	public List<Notarization> findByG_DID(long groupId, long dossierId,
		int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* Returns the notarization with the primary key.
	*
	* @param notarizationId the primary key of the notarization
	* @return the notarization
	* @throws PortalException if a notarization with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Notarization getNotarization(long notarizationId)
		throws PortalException;

	/**
	* Returns a range of all the notarizations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.NotarizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of notarizations
	* @param end the upper bound of the range of notarizations (not inclusive)
	* @return the range of notarizations
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Notarization> getNotarizations(int start, int end);

	/**
	* Returns the number of notarizations.
	*
	* @return the number of notarizations
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getNotarizationsCount();

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

	@Indexable(type = IndexableType.DELETE)
	public void removeNotarization(long notarizationId)
		throws NoSuchNotarizationException;

	@Indexable(type = IndexableType.REINDEX)
	public Notarization updateNotarization(long groupId, long notarizationId,
		long dossierId, String fileName, int totalRecord, int totalPage,
		int totalCopy, long totalFee, long notarizationNo,
		int notarizationYear, Date notarizationDate, String signerName,
		String signerPosition, String statusCode, ServiceContext serviceContext);

	/**
	* Updates the notarization in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param notarization the notarization
	* @return the notarization that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Notarization updateNotarization(Notarization notarization);
}