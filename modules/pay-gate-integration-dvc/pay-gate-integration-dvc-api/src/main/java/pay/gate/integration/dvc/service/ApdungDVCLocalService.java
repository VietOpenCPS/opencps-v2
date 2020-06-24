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

package pay.gate.integration.dvc.service;

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

import pay.gate.integration.dvc.model.ApdungDVC;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for ApdungDVC. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see ApdungDVCLocalServiceUtil
 * @see pay.gate.integration.dvc.service.base.ApdungDVCLocalServiceBaseImpl
 * @see pay.gate.integration.dvc.service.impl.ApdungDVCLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface ApdungDVCLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ApdungDVCLocalServiceUtil} to access the apdung dvc local service. Add custom service methods to {@link pay.gate.integration.dvc.service.impl.ApdungDVCLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the apdung dvc to the database. Also notifies the appropriate model listeners.
	*
	* @param apdungDVC the apdung dvc
	* @return the apdung dvc that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ApdungDVC addApdungDVC(ApdungDVC apdungDVC);

	/**
	* Creates a new apdung dvc with the primary key. Does not add the apdung dvc to the database.
	*
	* @param apdungDVCId the primary key for the new apdung dvc
	* @return the new apdung dvc
	*/
	@Transactional(enabled = false)
	public ApdungDVC createApdungDVC(long apdungDVCId);

	/**
	* Deletes the apdung dvc from the database. Also notifies the appropriate model listeners.
	*
	* @param apdungDVC the apdung dvc
	* @return the apdung dvc that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public ApdungDVC deleteApdungDVC(ApdungDVC apdungDVC);

	/**
	* Deletes the apdung dvc with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param apdungDVCId the primary key of the apdung dvc
	* @return the apdung dvc that was removed
	* @throws PortalException if a apdung dvc with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public ApdungDVC deleteApdungDVC(long apdungDVCId)
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link pay.gate.integration.dvc.model.impl.ApdungDVCModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link pay.gate.integration.dvc.model.impl.ApdungDVCModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public ApdungDVC fetchApdungDVC(long apdungDVCId);

	/**
	* Returns the apdung dvc matching the UUID and group.
	*
	* @param uuid the apdung dvc's UUID
	* @param groupId the primary key of the group
	* @return the matching apdung dvc, or <code>null</code> if a matching apdung dvc could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ApdungDVC fetchApdungDVCByUuidAndGroupId(String uuid, long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	/**
	* Returns the apdung dvc with the primary key.
	*
	* @param apdungDVCId the primary key of the apdung dvc
	* @return the apdung dvc
	* @throws PortalException if a apdung dvc with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ApdungDVC getApdungDVC(long apdungDVCId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ApdungDVC getApdungDVCByTTHCCQTHMD(String maTTHC, String maCQTH,
		int mucdo);

	/**
	* Returns the apdung dvc matching the UUID and group.
	*
	* @param uuid the apdung dvc's UUID
	* @param groupId the primary key of the group
	* @return the matching apdung dvc
	* @throws PortalException if a matching apdung dvc could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ApdungDVC getApdungDVCByUuidAndGroupId(String uuid, long groupId)
		throws PortalException;

	/**
	* Returns a range of all the apdung dvcs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link pay.gate.integration.dvc.model.impl.ApdungDVCModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of apdung dvcs
	* @param end the upper bound of the range of apdung dvcs (not inclusive)
	* @return the range of apdung dvcs
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ApdungDVC> getApdungDVCs(int start, int end);

	/**
	* Returns all the apdung dvcs matching the UUID and company.
	*
	* @param uuid the UUID of the apdung dvcs
	* @param companyId the primary key of the company
	* @return the matching apdung dvcs, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ApdungDVC> getApdungDVCsByUuidAndCompanyId(String uuid,
		long companyId);

	/**
	* Returns a range of apdung dvcs matching the UUID and company.
	*
	* @param uuid the UUID of the apdung dvcs
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of apdung dvcs
	* @param end the upper bound of the range of apdung dvcs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching apdung dvcs, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ApdungDVC> getApdungDVCsByUuidAndCompanyId(String uuid,
		long companyId, int start, int end,
		OrderByComparator<ApdungDVC> orderByComparator);

	/**
	* Returns the number of apdung dvcs.
	*
	* @return the number of apdung dvcs
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getApdungDVCsCount();

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
	* Updates the apdung dvc in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param apdungDVC the apdung dvc
	* @return the apdung dvc that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ApdungDVC updateApdungDVC(ApdungDVC apdungDVC);
}