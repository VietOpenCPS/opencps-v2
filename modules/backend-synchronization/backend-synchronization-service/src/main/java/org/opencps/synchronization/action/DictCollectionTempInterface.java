package org.opencps.synchronization.action;

import java.util.LinkedHashMap;

import org.opencps.auth.api.exception.NotFoundException;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.synchronization.exception.NoSuchDictItemTempException;
import org.opencps.synchronization.model.DictCollectionTemp;
import org.opencps.synchronization.model.DictGroupTemp;
import org.opencps.synchronization.model.DictItemGroupTemp;
import org.opencps.synchronization.model.DictItemTemp;

import com.liferay.asset.kernel.exception.DuplicateCategoryException;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;

public interface DictCollectionTempInterface {

	public JSONObject getDictCollectionTemp(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext);
	
	public DictCollectionTemp getDictCollectionTempDetail(String dictCollectionCode, long groupId);
	
	public DictCollectionTemp addDictCollectionTemp(long userId, long groupId, String collectionCode, String collectionName,
			String collectionNameEN, String description, 
			int status,
			int mustSync,
			ServiceContext serviceContext) throws NoSuchUserException,
			UnauthenticationException, UnauthorizationException, DuplicateCategoryException;
	
	public DictCollectionTemp updateDictCollectionTemp(long userId, long groupId, String code, String collectionCode,
			String collectionName, String collectionNameEN, String description, 
			int status,
			int mustSync,
			ServiceContext serviceContext)
			throws NoSuchUserException, NotFoundException, UnauthenticationException, UnauthorizationException,
			DuplicateCategoryException;
	
	public boolean deleteDictCollectionTemp(String code, long groupId, ServiceContext serviceContext)
			throws NotFoundException, UnauthenticationException, UnauthorizationException;
	
	public DictCollectionTemp addDataForm(long userId, long groupId, String code, String dataform,
			ServiceContext serviceContext) throws NoSuchUserException, NotFoundException, UnauthenticationException,
			UnauthorizationException, DuplicateCategoryException;
	
	public JSONObject getDictGroupsTemp(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext);
	
	public JSONObject getDictItemsGroupTemp(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext);
	
	public DictGroupTemp addDictGroupsTemp(long userId, long groupId, String code, String groupCode, String groupName,
			String groupNameEN, String groupDescription, 
			int status,
			ServiceContext serviceContext) throws NoSuchUserException,
			UnauthenticationException, UnauthorizationException, DuplicateCategoryException;
	
	public DictGroupTemp updateDictGroupsTemp(long userId, long groupId, String code, String groupCodeRoot, String groupCode,
			String groupName, String groupNameEN, String groupDescription, int status, ServiceContext serviceContext)
			throws NoSuchUserException, UnauthenticationException, UnauthorizationException,
			DuplicateCategoryException, NotFoundException;
	
	public boolean deleteDictGroupsTemp(String groupCode, long groupId, ServiceContext serviceContext)
			throws NotFoundException, UnauthenticationException, UnauthorizationException;
	
	public DictItemGroupTemp addDictGroupsDictItemsTemp(long userId, long groupId, String code, String groupCode,
			String itemCode, ServiceContext serviceContext) throws NoSuchUserException, UnauthenticationException,
			UnauthorizationException, DuplicateCategoryException;
	
	public boolean deleteDictGroupsDictItemsTemp(long groupId, String code, String groupCode, String itemCode,
			ServiceContext serviceContext)
			throws NotFoundException, UnauthenticationException, UnauthorizationException;
	
	public JSONObject getDictGroupsDictItemsTemp(long userId, long companyId, long groupId, String code, String groupCode,
			boolean full, LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			ServiceContext serviceContext);
	
	public JSONObject getDictItemsTemp(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext);
	
	public DictItemTemp addDictItemsTemp(long userId, long groupId, String code, String parentItemCode, String itemCode,
			String itemName, String itemNameEN, String itemDescription, String sibling, int level, String metaData,
			int status,
			ServiceContext serviceContext) throws NoSuchUserException, NoSuchDictItemTempException,
			UnauthenticationException, UnauthorizationException, DuplicateCategoryException, SystemException;
	
	public DictItemTemp updateDictItemTempByItemCode(long userId, long groupId, ServiceContext serviceContext, String code, String itemCode, String itemCodeInput, 
			String itemName, String itemNameEN, String itemDescription, String sibling, String parentItemCode, int status) throws DuplicateCategoryException, UnauthenticationException,
			UnauthorizationException, NoSuchUserException, NotFoundException, PortalException;
	
	public DictItemTemp updateMetaDataByItemCode(long userId, long groupId, ServiceContext serviceContext, String code, String itemCode,
			String metaData) throws DuplicateCategoryException, UnauthenticationException,
			UnauthorizationException, NoSuchUserException, NotFoundException, PortalException;

	public DictItemTemp getDictItemTempByItemCode(String code, String itemCode, long groupId, ServiceContext serviceContext);	
}
