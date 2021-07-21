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

package org.opencps.synctracking.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.exportimport.kernel.lar.PortletDataContext;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.synctracking.model.DossierTax;
import org.opencps.synctracking.model.DossierTaxInput;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for DossierTax. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author duongnt
 * @see DossierTaxLocalServiceUtil
 * @see org.opencps.synctracking.service.base.DossierTaxLocalServiceBaseImpl
 * @see org.opencps.synctracking.service.impl.DossierTaxLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface DossierTaxLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DossierTaxLocalServiceUtil} to access the dossier tax local service. Add custom service methods to {@link org.opencps.synctracking.service.impl.DossierTaxLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the dossier tax to the database. Also notifies the appropriate model listeners.
	*
	* @param dossierTax the dossier tax
	* @return the dossier tax that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public DossierTax addDossierTax(DossierTax dossierTax);

	/**
	* Creates a new dossier tax with the primary key. Does not add the dossier tax to the database.
	*
	* @param taxId the primary key for the new dossier tax
	* @return the new dossier tax
	*/
	@Transactional(enabled = false)
	public DossierTax createDossierTax(long taxId);

	public DossierTax createDossierTaxManual(DossierTaxInput dossierTaxInput);

	/**
	* Deletes the dossier tax from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierTax the dossier tax
	* @return the dossier tax that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public DossierTax deleteDossierTax(DossierTax dossierTax);

	/**
	* Deletes the dossier tax with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param taxId the primary key of the dossier tax
	* @return the dossier tax that was removed
	* @throws PortalException if a dossier tax with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public DossierTax deleteDossierTax(long taxId) throws PortalException;

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.synctracking.model.impl.DossierTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.synctracking.model.impl.DossierTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public DossierTax fetchDossierTax(long taxId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DossierTax fetchDossierTaxByDMS(String dossierNo, String maSoThue,
		String soQuyetDinh);

	/**
	* Returns the dossier tax matching the UUID and group.
	*
	* @param uuid the dossier tax's UUID
	* @param groupId the primary key of the group
	* @return the matching dossier tax, or <code>null</code> if a matching dossier tax could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DossierTax fetchDossierTaxByUuidAndGroupId(String uuid, long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DossierTax> getByDossierIdAndStatusCTT(String dossierNo,
		int statuses);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DossierTax> getByDossierIdAndStatusTBT(String dossierNo,
		int statuses);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DossierTax> getByStatusCTT(int statusTBT, int statusCTT,
		int start, int end, OrderByComparator<DossierTax> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DossierTax> getByStatusTBT(int[] statuses, int start, int end,
		OrderByComparator<DossierTax> orderByComparator);

	/**
	* Returns the dossier tax with the primary key.
	*
	* @param taxId the primary key of the dossier tax
	* @return the dossier tax
	* @throws PortalException if a dossier tax with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DossierTax getDossierTax(long taxId) throws PortalException;

	/**
	* Returns the dossier tax matching the UUID and group.
	*
	* @param uuid the dossier tax's UUID
	* @param groupId the primary key of the group
	* @return the matching dossier tax
	* @throws PortalException if a matching dossier tax could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DossierTax getDossierTaxByUuidAndGroupId(String uuid, long groupId)
		throws PortalException;

	/**
	* Returns a range of all the dossier taxs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.synctracking.model.impl.DossierTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier taxs
	* @param end the upper bound of the range of dossier taxs (not inclusive)
	* @return the range of dossier taxs
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DossierTax> getDossierTaxs(int start, int end);

	/**
	* Returns all the dossier taxs matching the UUID and company.
	*
	* @param uuid the UUID of the dossier taxs
	* @param companyId the primary key of the company
	* @return the matching dossier taxs, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DossierTax> getDossierTaxsByUuidAndCompanyId(String uuid,
		long companyId);

	/**
	* Returns a range of dossier taxs matching the UUID and company.
	*
	* @param uuid the UUID of the dossier taxs
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of dossier taxs
	* @param end the upper bound of the range of dossier taxs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching dossier taxs, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DossierTax> getDossierTaxsByUuidAndCompanyId(String uuid,
		long companyId, int start, int end,
		OrderByComparator<DossierTax> orderByComparator);

	/**
	* Returns the number of dossier taxs.
	*
	* @return the number of dossier taxs
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getDossierTaxsCount();

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
	* Updates the dossier tax in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dossierTax the dossier tax
	* @return the dossier tax that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public DossierTax updateDossierTax(DossierTax dossierTax);
}