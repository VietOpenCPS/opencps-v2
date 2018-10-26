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

import com.liferay.exportimport.kernel.lar.PortletDataContext;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
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

import org.opencps.dossiermgt.model.DossierMark;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for DossierMark. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author huymq
 * @see DossierMarkLocalServiceUtil
 * @see org.opencps.dossiermgt.service.base.DossierMarkLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.DossierMarkLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface DossierMarkLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DossierMarkLocalServiceUtil} to access the dossier mark local service. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.DossierMarkLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the dossier mark to the database. Also notifies the appropriate model listeners.
	*
	* @param dossierMark the dossier mark
	* @return the dossier mark that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public DossierMark addDossierMark(DossierMark dossierMark);

	public DossierMark addDossierMark(long groupId, long dossierId,
		String dossierPartNo, Integer fileMark, Integer fileCheck,
		String fileComment, ServiceContext serviceContext)
		throws PortalException, SystemException;

	@Indexable(type = IndexableType.REINDEX)
	public DossierMark adminProcessData(JSONObject objectData);

	@Indexable(type = IndexableType.DELETE)
	public DossierMark adminProcessDelete(Long id);

	/**
	* Creates a new dossier mark with the primary key. Does not add the dossier mark to the database.
	*
	* @param dossierMarkId the primary key for the new dossier mark
	* @return the new dossier mark
	*/
	@Transactional(enabled = false)
	public DossierMark createDossierMark(long dossierMarkId);

	/**
	* Deletes the dossier mark from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierMark the dossier mark
	* @return the dossier mark that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public DossierMark deleteDossierMark(DossierMark dossierMark);

	/**
	* Deletes the dossier mark with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierMarkId the primary key of the dossier mark
	* @return the dossier mark that was removed
	* @throws PortalException if a dossier mark with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public DossierMark deleteDossierMark(long dossierMarkId)
		throws PortalException;

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DossierMarkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DossierMarkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public DossierMark fetchDossierMark(long dossierMarkId);

	/**
	* Returns the dossier mark matching the UUID and group.
	*
	* @param uuid the dossier mark's UUID
	* @param groupId the primary key of the group
	* @return the matching dossier mark, or <code>null</code> if a matching dossier mark could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DossierMark fetchDossierMarkByUuidAndGroupId(String uuid,
		long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	/**
	* Returns the dossier mark with the primary key.
	*
	* @param dossierMarkId the primary key of the dossier mark
	* @return the dossier mark
	* @throws PortalException if a dossier mark with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DossierMark getDossierMark(long dossierMarkId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DossierMark getDossierMarkbyDossierId(long groupId, long dossierId,
		String dossierPartNo);

	/**
	* Returns the dossier mark matching the UUID and group.
	*
	* @param uuid the dossier mark's UUID
	* @param groupId the primary key of the group
	* @return the matching dossier mark
	* @throws PortalException if a matching dossier mark could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DossierMark getDossierMarkByUuidAndGroupId(String uuid, long groupId)
		throws PortalException;

	/**
	* Returns a range of all the dossier marks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DossierMarkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier marks
	* @param end the upper bound of the range of dossier marks (not inclusive)
	* @return the range of dossier marks
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DossierMark> getDossierMarks(int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DossierMark> getDossierMarks(long groupId, long dossierId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DossierMark> getDossierMarksByFileMark(long groupId,
		long dossierId, int fileMark);

	/**
	* Returns all the dossier marks matching the UUID and company.
	*
	* @param uuid the UUID of the dossier marks
	* @param companyId the primary key of the company
	* @return the matching dossier marks, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DossierMark> getDossierMarksByUuidAndCompanyId(String uuid,
		long companyId);

	/**
	* Returns a range of dossier marks matching the UUID and company.
	*
	* @param uuid the UUID of the dossier marks
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of dossier marks
	* @param end the upper bound of the range of dossier marks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching dossier marks, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DossierMark> getDossierMarksByUuidAndCompanyId(String uuid,
		long companyId, int start, int end,
		OrderByComparator<DossierMark> orderByComparator);

	/**
	* Returns the number of dossier marks.
	*
	* @return the number of dossier marks
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getDossierMarksCount();

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
	* Updates the dossier mark in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dossierMark the dossier mark
	* @return the dossier mark that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public DossierMark updateDossierMark(DossierMark dossierMark);
}