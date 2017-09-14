package org.opencps.datamgt.action;

import java.util.LinkedHashMap;

import org.opencps.datamgt.exception.NoSuchDictItemException;
import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictGroup;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.model.DictItemGroup;

import com.liferay.asset.kernel.exception.DuplicateCategoryException;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;

import org.opencps.auth.api.exception.NotFoundException;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;

public interface DictcollectionInterface {

	public JSONObject getDictCollection(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext);
	
	public DictCollection getDictCollectionDetail(String dictCollectionCode, long groupId);
	
	public DictCollection addDictCollection(long userId, long groupId, String collectionCode, String collectionName,
			String collectionNameEN, String description, ServiceContext serviceContext) throws NoSuchUserException,
			UnauthenticationException, UnauthorizationException, DuplicateCategoryException;
	
	public DictCollection updateDictCollection(long userId, long groupId, String code, String collectionCode,
			String collectionName, String collectionNameEN, String description, ServiceContext serviceContext)
			throws NoSuchUserException, NotFoundException, UnauthenticationException, UnauthorizationException,
			DuplicateCategoryException;
	
	public boolean deleteDictCollection(String code, long groupId, ServiceContext serviceContext)
			throws NotFoundException, UnauthenticationException, UnauthorizationException;
	
	public DictCollection addDataForm(long userId, long groupId, String code, String dataform,
			ServiceContext serviceContext) throws NoSuchUserException, NotFoundException, UnauthenticationException,
			UnauthorizationException, DuplicateCategoryException;
	
	public JSONObject getDictgroups(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext);
	
	public JSONObject getDictItemsGroup(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext);
	
	public DictGroup addDictgroups(long userId, long groupId, String code, String groupCode, String groupName,
			String groupNameEN, String groupDescription, ServiceContext serviceContext) throws NoSuchUserException,
			UnauthenticationException, UnauthorizationException, DuplicateCategoryException;
	
	public DictGroup updateDictgroups(long userId, long groupId, String code, String groupCodeRoot, String groupCode,
			String groupName, String groupNameEN, String groupDescription, ServiceContext serviceContext)
			throws NoSuchUserException, UnauthenticationException, UnauthorizationException,
			DuplicateCategoryException;
	
	public boolean deleteDictgroups(String groupCode, long groupId, ServiceContext serviceContext)
			throws NotFoundException, UnauthenticationException, UnauthorizationException;
	
	public DictItemGroup addDictgroupsDictItems(long userId, long groupId, String code, String groupCode,
			String itemCode, ServiceContext serviceContext) throws NoSuchUserException, UnauthenticationException,
			UnauthorizationException, DuplicateCategoryException;
	
	public boolean deleteDictgroupsDictItems(long groupId, String code, String groupCode, String itemCode,
			ServiceContext serviceContext)
			throws NotFoundException, UnauthenticationException, UnauthorizationException;
	
	public JSONObject getDictgroupsDictItems(long userId, long companyId, long groupId, String code, String groupCode,
			boolean full, LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			ServiceContext serviceContext);
	
	public JSONObject getDictItems(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext);
	
	public DictItem addDictItems(long userId, long groupId, String code, String parentItemCode, String itemCode,
			String itemName, String itemNameEN, String itemDescription, String sibling, int level, String metaData,
			ServiceContext serviceContext) throws NoSuchUserException, NoSuchDictItemException,
			UnauthenticationException, UnauthorizationException, DuplicateCategoryException, SystemException;
	
	public DictItem updateDictItemByItemCode(long userId, long groupId, ServiceContext serviceContext, String code, String itemCode,
			String itemName, String itemNameEN, String itemDescription, String sibling) throws DuplicateCategoryException, UnauthenticationException,
			UnauthorizationException, NoSuchUserException, NotFoundException, PortalException;
	
	public DictItem updateMetaDataByItemCode(long userId, long groupId, ServiceContext serviceContext, String code, String itemCode,
			String metaData) throws DuplicateCategoryException, UnauthenticationException,
			UnauthorizationException, NoSuchUserException, NotFoundException, PortalException;

	public DictItem getDictItemByItemCode(String code, String itemCode, long groupId, ServiceContext serviceContext);
	
}
