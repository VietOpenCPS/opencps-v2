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

package org.opencps.backend.dossiermgt.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.service.persistence.impl.TableMapper;
import com.liferay.portal.kernel.service.persistence.impl.TableMapperFactory;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import org.opencps.backend.dossiermgt.exception.NoSuchFileTemplateException;
import org.opencps.backend.dossiermgt.model.FileTemplate;
import org.opencps.backend.dossiermgt.model.impl.FileTemplateImpl;
import org.opencps.backend.dossiermgt.model.impl.FileTemplateModelImpl;
import org.opencps.backend.dossiermgt.service.persistence.FileTemplatePersistence;
import org.opencps.backend.dossiermgt.service.persistence.ServiceInfoPersistence;

import java.io.Serializable;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the file template service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see FileTemplatePersistence
 * @see org.opencps.backend.dossiermgt.service.persistence.FileTemplateUtil
 * @generated
 */
@ProviderType
public class FileTemplatePersistenceImpl extends BasePersistenceImpl<FileTemplate>
	implements FileTemplatePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link FileTemplateUtil} to access the file template persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = FileTemplateImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(FileTemplateModelImpl.ENTITY_CACHE_ENABLED,
			FileTemplateModelImpl.FINDER_CACHE_ENABLED, FileTemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(FileTemplateModelImpl.ENTITY_CACHE_ENABLED,
			FileTemplateModelImpl.FINDER_CACHE_ENABLED, FileTemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(FileTemplateModelImpl.ENTITY_CACHE_ENABLED,
			FileTemplateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(FileTemplateModelImpl.ENTITY_CACHE_ENABLED,
			FileTemplateModelImpl.FINDER_CACHE_ENABLED, FileTemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(FileTemplateModelImpl.ENTITY_CACHE_ENABLED,
			FileTemplateModelImpl.FINDER_CACHE_ENABLED, FileTemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			FileTemplateModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(FileTemplateModelImpl.ENTITY_CACHE_ENABLED,
			FileTemplateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the file templates where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching file templates
	 */
	@Override
	public List<FileTemplate> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the file templates where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FileTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of file templates
	 * @param end the upper bound of the range of file templates (not inclusive)
	 * @return the range of matching file templates
	 */
	@Override
	public List<FileTemplate> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the file templates where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FileTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of file templates
	 * @param end the upper bound of the range of file templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching file templates
	 */
	@Override
	public List<FileTemplate> findByUuid(String uuid, int start, int end,
		OrderByComparator<FileTemplate> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the file templates where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FileTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of file templates
	 * @param end the upper bound of the range of file templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching file templates
	 */
	@Override
	public List<FileTemplate> findByUuid(String uuid, int start, int end,
		OrderByComparator<FileTemplate> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid, start, end, orderByComparator };
		}

		List<FileTemplate> list = null;

		if (retrieveFromCache) {
			list = (List<FileTemplate>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (FileTemplate fileTemplate : list) {
					if (!Objects.equals(uuid, fileTemplate.getUuid())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_FILETEMPLATE_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(FileTemplateModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				if (!pagination) {
					list = (List<FileTemplate>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<FileTemplate>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first file template in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching file template
	 * @throws NoSuchFileTemplateException if a matching file template could not be found
	 */
	@Override
	public FileTemplate findByUuid_First(String uuid,
		OrderByComparator<FileTemplate> orderByComparator)
		throws NoSuchFileTemplateException {
		FileTemplate fileTemplate = fetchByUuid_First(uuid, orderByComparator);

		if (fileTemplate != null) {
			return fileTemplate;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFileTemplateException(msg.toString());
	}

	/**
	 * Returns the first file template in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching file template, or <code>null</code> if a matching file template could not be found
	 */
	@Override
	public FileTemplate fetchByUuid_First(String uuid,
		OrderByComparator<FileTemplate> orderByComparator) {
		List<FileTemplate> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last file template in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching file template
	 * @throws NoSuchFileTemplateException if a matching file template could not be found
	 */
	@Override
	public FileTemplate findByUuid_Last(String uuid,
		OrderByComparator<FileTemplate> orderByComparator)
		throws NoSuchFileTemplateException {
		FileTemplate fileTemplate = fetchByUuid_Last(uuid, orderByComparator);

		if (fileTemplate != null) {
			return fileTemplate;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFileTemplateException(msg.toString());
	}

	/**
	 * Returns the last file template in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching file template, or <code>null</code> if a matching file template could not be found
	 */
	@Override
	public FileTemplate fetchByUuid_Last(String uuid,
		OrderByComparator<FileTemplate> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<FileTemplate> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the file templates before and after the current file template in the ordered set where uuid = &#63;.
	 *
	 * @param fileTemplateId the primary key of the current file template
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next file template
	 * @throws NoSuchFileTemplateException if a file template with the primary key could not be found
	 */
	@Override
	public FileTemplate[] findByUuid_PrevAndNext(long fileTemplateId,
		String uuid, OrderByComparator<FileTemplate> orderByComparator)
		throws NoSuchFileTemplateException {
		FileTemplate fileTemplate = findByPrimaryKey(fileTemplateId);

		Session session = null;

		try {
			session = openSession();

			FileTemplate[] array = new FileTemplateImpl[3];

			array[0] = getByUuid_PrevAndNext(session, fileTemplate, uuid,
					orderByComparator, true);

			array[1] = fileTemplate;

			array[2] = getByUuid_PrevAndNext(session, fileTemplate, uuid,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected FileTemplate getByUuid_PrevAndNext(Session session,
		FileTemplate fileTemplate, String uuid,
		OrderByComparator<FileTemplate> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_FILETEMPLATE_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(FileTemplateModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(fileTemplate);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<FileTemplate> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the file templates where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (FileTemplate fileTemplate : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(fileTemplate);
		}
	}

	/**
	 * Returns the number of file templates where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching file templates
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_FILETEMPLATE_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "fileTemplate.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "fileTemplate.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(fileTemplate.uuid IS NULL OR fileTemplate.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(FileTemplateModelImpl.ENTITY_CACHE_ENABLED,
			FileTemplateModelImpl.FINDER_CACHE_ENABLED, FileTemplateImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			FileTemplateModelImpl.UUID_COLUMN_BITMASK |
			FileTemplateModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(FileTemplateModelImpl.ENTITY_CACHE_ENABLED,
			FileTemplateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the file template where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchFileTemplateException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching file template
	 * @throws NoSuchFileTemplateException if a matching file template could not be found
	 */
	@Override
	public FileTemplate findByUUID_G(String uuid, long groupId)
		throws NoSuchFileTemplateException {
		FileTemplate fileTemplate = fetchByUUID_G(uuid, groupId);

		if (fileTemplate == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchFileTemplateException(msg.toString());
		}

		return fileTemplate;
	}

	/**
	 * Returns the file template where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching file template, or <code>null</code> if a matching file template could not be found
	 */
	@Override
	public FileTemplate fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the file template where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching file template, or <code>null</code> if a matching file template could not be found
	 */
	@Override
	public FileTemplate fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof FileTemplate) {
			FileTemplate fileTemplate = (FileTemplate)result;

			if (!Objects.equals(uuid, fileTemplate.getUuid()) ||
					(groupId != fileTemplate.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_FILETEMPLATE_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				List<FileTemplate> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					FileTemplate fileTemplate = list.get(0);

					result = fileTemplate;

					cacheResult(fileTemplate);

					if ((fileTemplate.getUuid() == null) ||
							!fileTemplate.getUuid().equals(uuid) ||
							(fileTemplate.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, fileTemplate);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (FileTemplate)result;
		}
	}

	/**
	 * Removes the file template where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the file template that was removed
	 */
	@Override
	public FileTemplate removeByUUID_G(String uuid, long groupId)
		throws NoSuchFileTemplateException {
		FileTemplate fileTemplate = findByUUID_G(uuid, groupId);

		return remove(fileTemplate);
	}

	/**
	 * Returns the number of file templates where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching file templates
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_FILETEMPLATE_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "fileTemplate.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "fileTemplate.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(fileTemplate.uuid IS NULL OR fileTemplate.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "fileTemplate.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(FileTemplateModelImpl.ENTITY_CACHE_ENABLED,
			FileTemplateModelImpl.FINDER_CACHE_ENABLED, FileTemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(FileTemplateModelImpl.ENTITY_CACHE_ENABLED,
			FileTemplateModelImpl.FINDER_CACHE_ENABLED, FileTemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			FileTemplateModelImpl.UUID_COLUMN_BITMASK |
			FileTemplateModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(FileTemplateModelImpl.ENTITY_CACHE_ENABLED,
			FileTemplateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the file templates where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching file templates
	 */
	@Override
	public List<FileTemplate> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the file templates where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FileTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of file templates
	 * @param end the upper bound of the range of file templates (not inclusive)
	 * @return the range of matching file templates
	 */
	@Override
	public List<FileTemplate> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the file templates where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FileTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of file templates
	 * @param end the upper bound of the range of file templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching file templates
	 */
	@Override
	public List<FileTemplate> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<FileTemplate> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the file templates where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FileTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of file templates
	 * @param end the upper bound of the range of file templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching file templates
	 */
	@Override
	public List<FileTemplate> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<FileTemplate> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] { uuid, companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] {
					uuid, companyId,
					
					start, end, orderByComparator
				};
		}

		List<FileTemplate> list = null;

		if (retrieveFromCache) {
			list = (List<FileTemplate>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (FileTemplate fileTemplate : list) {
					if (!Objects.equals(uuid, fileTemplate.getUuid()) ||
							(companyId != fileTemplate.getCompanyId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_FILETEMPLATE_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(FileTemplateModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				if (!pagination) {
					list = (List<FileTemplate>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<FileTemplate>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first file template in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching file template
	 * @throws NoSuchFileTemplateException if a matching file template could not be found
	 */
	@Override
	public FileTemplate findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<FileTemplate> orderByComparator)
		throws NoSuchFileTemplateException {
		FileTemplate fileTemplate = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (fileTemplate != null) {
			return fileTemplate;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFileTemplateException(msg.toString());
	}

	/**
	 * Returns the first file template in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching file template, or <code>null</code> if a matching file template could not be found
	 */
	@Override
	public FileTemplate fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<FileTemplate> orderByComparator) {
		List<FileTemplate> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last file template in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching file template
	 * @throws NoSuchFileTemplateException if a matching file template could not be found
	 */
	@Override
	public FileTemplate findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<FileTemplate> orderByComparator)
		throws NoSuchFileTemplateException {
		FileTemplate fileTemplate = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (fileTemplate != null) {
			return fileTemplate;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFileTemplateException(msg.toString());
	}

	/**
	 * Returns the last file template in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching file template, or <code>null</code> if a matching file template could not be found
	 */
	@Override
	public FileTemplate fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<FileTemplate> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<FileTemplate> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the file templates before and after the current file template in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param fileTemplateId the primary key of the current file template
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next file template
	 * @throws NoSuchFileTemplateException if a file template with the primary key could not be found
	 */
	@Override
	public FileTemplate[] findByUuid_C_PrevAndNext(long fileTemplateId,
		String uuid, long companyId,
		OrderByComparator<FileTemplate> orderByComparator)
		throws NoSuchFileTemplateException {
		FileTemplate fileTemplate = findByPrimaryKey(fileTemplateId);

		Session session = null;

		try {
			session = openSession();

			FileTemplate[] array = new FileTemplateImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, fileTemplate, uuid,
					companyId, orderByComparator, true);

			array[1] = fileTemplate;

			array[2] = getByUuid_C_PrevAndNext(session, fileTemplate, uuid,
					companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected FileTemplate getByUuid_C_PrevAndNext(Session session,
		FileTemplate fileTemplate, String uuid, long companyId,
		OrderByComparator<FileTemplate> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_FILETEMPLATE_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_1);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(FileTemplateModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(fileTemplate);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<FileTemplate> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the file templates where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (FileTemplate fileTemplate : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(fileTemplate);
		}
	}

	/**
	 * Returns the number of file templates where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching file templates
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_FILETEMPLATE_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "fileTemplate.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "fileTemplate.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(fileTemplate.uuid IS NULL OR fileTemplate.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "fileTemplate.companyId = ?";

	public FileTemplatePersistenceImpl() {
		setModelClass(FileTemplate.class);
	}

	/**
	 * Caches the file template in the entity cache if it is enabled.
	 *
	 * @param fileTemplate the file template
	 */
	@Override
	public void cacheResult(FileTemplate fileTemplate) {
		entityCache.putResult(FileTemplateModelImpl.ENTITY_CACHE_ENABLED,
			FileTemplateImpl.class, fileTemplate.getPrimaryKey(), fileTemplate);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { fileTemplate.getUuid(), fileTemplate.getGroupId() },
			fileTemplate);

		fileTemplate.resetOriginalValues();
	}

	/**
	 * Caches the file templates in the entity cache if it is enabled.
	 *
	 * @param fileTemplates the file templates
	 */
	@Override
	public void cacheResult(List<FileTemplate> fileTemplates) {
		for (FileTemplate fileTemplate : fileTemplates) {
			if (entityCache.getResult(
						FileTemplateModelImpl.ENTITY_CACHE_ENABLED,
						FileTemplateImpl.class, fileTemplate.getPrimaryKey()) == null) {
				cacheResult(fileTemplate);
			}
			else {
				fileTemplate.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all file templates.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(FileTemplateImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the file template.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(FileTemplate fileTemplate) {
		entityCache.removeResult(FileTemplateModelImpl.ENTITY_CACHE_ENABLED,
			FileTemplateImpl.class, fileTemplate.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((FileTemplateModelImpl)fileTemplate, true);
	}

	@Override
	public void clearCache(List<FileTemplate> fileTemplates) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (FileTemplate fileTemplate : fileTemplates) {
			entityCache.removeResult(FileTemplateModelImpl.ENTITY_CACHE_ENABLED,
				FileTemplateImpl.class, fileTemplate.getPrimaryKey());

			clearUniqueFindersCache((FileTemplateModelImpl)fileTemplate, true);
		}
	}

	protected void cacheUniqueFindersCache(
		FileTemplateModelImpl fileTemplateModelImpl) {
		Object[] args = new Object[] {
				fileTemplateModelImpl.getUuid(),
				fileTemplateModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			fileTemplateModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		FileTemplateModelImpl fileTemplateModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					fileTemplateModelImpl.getUuid(),
					fileTemplateModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((fileTemplateModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					fileTemplateModelImpl.getOriginalUuid(),
					fileTemplateModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new file template with the primary key. Does not add the file template to the database.
	 *
	 * @param fileTemplateId the primary key for the new file template
	 * @return the new file template
	 */
	@Override
	public FileTemplate create(long fileTemplateId) {
		FileTemplate fileTemplate = new FileTemplateImpl();

		fileTemplate.setNew(true);
		fileTemplate.setPrimaryKey(fileTemplateId);

		String uuid = PortalUUIDUtil.generate();

		fileTemplate.setUuid(uuid);

		fileTemplate.setCompanyId(companyProvider.getCompanyId());

		return fileTemplate;
	}

	/**
	 * Removes the file template with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param fileTemplateId the primary key of the file template
	 * @return the file template that was removed
	 * @throws NoSuchFileTemplateException if a file template with the primary key could not be found
	 */
	@Override
	public FileTemplate remove(long fileTemplateId)
		throws NoSuchFileTemplateException {
		return remove((Serializable)fileTemplateId);
	}

	/**
	 * Removes the file template with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the file template
	 * @return the file template that was removed
	 * @throws NoSuchFileTemplateException if a file template with the primary key could not be found
	 */
	@Override
	public FileTemplate remove(Serializable primaryKey)
		throws NoSuchFileTemplateException {
		Session session = null;

		try {
			session = openSession();

			FileTemplate fileTemplate = (FileTemplate)session.get(FileTemplateImpl.class,
					primaryKey);

			if (fileTemplate == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchFileTemplateException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(fileTemplate);
		}
		catch (NoSuchFileTemplateException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected FileTemplate removeImpl(FileTemplate fileTemplate) {
		fileTemplate = toUnwrappedModel(fileTemplate);

		fileTemplateToServiceInfoTableMapper.deleteLeftPrimaryKeyTableMappings(fileTemplate.getPrimaryKey());

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(fileTemplate)) {
				fileTemplate = (FileTemplate)session.get(FileTemplateImpl.class,
						fileTemplate.getPrimaryKeyObj());
			}

			if (fileTemplate != null) {
				session.delete(fileTemplate);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (fileTemplate != null) {
			clearCache(fileTemplate);
		}

		return fileTemplate;
	}

	@Override
	public FileTemplate updateImpl(FileTemplate fileTemplate) {
		fileTemplate = toUnwrappedModel(fileTemplate);

		boolean isNew = fileTemplate.isNew();

		FileTemplateModelImpl fileTemplateModelImpl = (FileTemplateModelImpl)fileTemplate;

		if (Validator.isNull(fileTemplate.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			fileTemplate.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (fileTemplate.getCreateDate() == null)) {
			if (serviceContext == null) {
				fileTemplate.setCreateDate(now);
			}
			else {
				fileTemplate.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!fileTemplateModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				fileTemplate.setModifiedDate(now);
			}
			else {
				fileTemplate.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (fileTemplate.isNew()) {
				session.save(fileTemplate);

				fileTemplate.setNew(false);
			}
			else {
				fileTemplate = (FileTemplate)session.merge(fileTemplate);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !FileTemplateModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((fileTemplateModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						fileTemplateModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { fileTemplateModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((fileTemplateModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						fileTemplateModelImpl.getOriginalUuid(),
						fileTemplateModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						fileTemplateModelImpl.getUuid(),
						fileTemplateModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}
		}

		entityCache.putResult(FileTemplateModelImpl.ENTITY_CACHE_ENABLED,
			FileTemplateImpl.class, fileTemplate.getPrimaryKey(), fileTemplate,
			false);

		clearUniqueFindersCache(fileTemplateModelImpl, false);
		cacheUniqueFindersCache(fileTemplateModelImpl);

		fileTemplate.resetOriginalValues();

		return fileTemplate;
	}

	protected FileTemplate toUnwrappedModel(FileTemplate fileTemplate) {
		if (fileTemplate instanceof FileTemplateImpl) {
			return fileTemplate;
		}

		FileTemplateImpl fileTemplateImpl = new FileTemplateImpl();

		fileTemplateImpl.setNew(fileTemplate.isNew());
		fileTemplateImpl.setPrimaryKey(fileTemplate.getPrimaryKey());

		fileTemplateImpl.setUuid(fileTemplate.getUuid());
		fileTemplateImpl.setFileTemplateId(fileTemplate.getFileTemplateId());
		fileTemplateImpl.setGroupId(fileTemplate.getGroupId());
		fileTemplateImpl.setCompanyId(fileTemplate.getCompanyId());
		fileTemplateImpl.setUserId(fileTemplate.getUserId());
		fileTemplateImpl.setUserName(fileTemplate.getUserName());
		fileTemplateImpl.setCreateDate(fileTemplate.getCreateDate());
		fileTemplateImpl.setModifiedDate(fileTemplate.getModifiedDate());
		fileTemplateImpl.setFileName(fileTemplate.getFileName());
		fileTemplateImpl.setFileNo(fileTemplate.getFileNo());
		fileTemplateImpl.setFileEntryId(fileTemplate.getFileEntryId());

		return fileTemplateImpl;
	}

	/**
	 * Returns the file template with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the file template
	 * @return the file template
	 * @throws NoSuchFileTemplateException if a file template with the primary key could not be found
	 */
	@Override
	public FileTemplate findByPrimaryKey(Serializable primaryKey)
		throws NoSuchFileTemplateException {
		FileTemplate fileTemplate = fetchByPrimaryKey(primaryKey);

		if (fileTemplate == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchFileTemplateException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return fileTemplate;
	}

	/**
	 * Returns the file template with the primary key or throws a {@link NoSuchFileTemplateException} if it could not be found.
	 *
	 * @param fileTemplateId the primary key of the file template
	 * @return the file template
	 * @throws NoSuchFileTemplateException if a file template with the primary key could not be found
	 */
	@Override
	public FileTemplate findByPrimaryKey(long fileTemplateId)
		throws NoSuchFileTemplateException {
		return findByPrimaryKey((Serializable)fileTemplateId);
	}

	/**
	 * Returns the file template with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the file template
	 * @return the file template, or <code>null</code> if a file template with the primary key could not be found
	 */
	@Override
	public FileTemplate fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(FileTemplateModelImpl.ENTITY_CACHE_ENABLED,
				FileTemplateImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		FileTemplate fileTemplate = (FileTemplate)serializable;

		if (fileTemplate == null) {
			Session session = null;

			try {
				session = openSession();

				fileTemplate = (FileTemplate)session.get(FileTemplateImpl.class,
						primaryKey);

				if (fileTemplate != null) {
					cacheResult(fileTemplate);
				}
				else {
					entityCache.putResult(FileTemplateModelImpl.ENTITY_CACHE_ENABLED,
						FileTemplateImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(FileTemplateModelImpl.ENTITY_CACHE_ENABLED,
					FileTemplateImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return fileTemplate;
	}

	/**
	 * Returns the file template with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param fileTemplateId the primary key of the file template
	 * @return the file template, or <code>null</code> if a file template with the primary key could not be found
	 */
	@Override
	public FileTemplate fetchByPrimaryKey(long fileTemplateId) {
		return fetchByPrimaryKey((Serializable)fileTemplateId);
	}

	@Override
	public Map<Serializable, FileTemplate> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, FileTemplate> map = new HashMap<Serializable, FileTemplate>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			FileTemplate fileTemplate = fetchByPrimaryKey(primaryKey);

			if (fileTemplate != null) {
				map.put(primaryKey, fileTemplate);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(FileTemplateModelImpl.ENTITY_CACHE_ENABLED,
					FileTemplateImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (FileTemplate)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_FILETEMPLATE_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append(String.valueOf(primaryKey));

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (FileTemplate fileTemplate : (List<FileTemplate>)q.list()) {
				map.put(fileTemplate.getPrimaryKeyObj(), fileTemplate);

				cacheResult(fileTemplate);

				uncachedPrimaryKeys.remove(fileTemplate.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(FileTemplateModelImpl.ENTITY_CACHE_ENABLED,
					FileTemplateImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the file templates.
	 *
	 * @return the file templates
	 */
	@Override
	public List<FileTemplate> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the file templates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FileTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of file templates
	 * @param end the upper bound of the range of file templates (not inclusive)
	 * @return the range of file templates
	 */
	@Override
	public List<FileTemplate> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the file templates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FileTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of file templates
	 * @param end the upper bound of the range of file templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of file templates
	 */
	@Override
	public List<FileTemplate> findAll(int start, int end,
		OrderByComparator<FileTemplate> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the file templates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FileTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of file templates
	 * @param end the upper bound of the range of file templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of file templates
	 */
	@Override
	public List<FileTemplate> findAll(int start, int end,
		OrderByComparator<FileTemplate> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<FileTemplate> list = null;

		if (retrieveFromCache) {
			list = (List<FileTemplate>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_FILETEMPLATE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_FILETEMPLATE;

				if (pagination) {
					sql = sql.concat(FileTemplateModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<FileTemplate>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<FileTemplate>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the file templates from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (FileTemplate fileTemplate : findAll()) {
			remove(fileTemplate);
		}
	}

	/**
	 * Returns the number of file templates.
	 *
	 * @return the number of file templates
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_FILETEMPLATE);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the primaryKeys of service infos associated with the file template.
	 *
	 * @param pk the primary key of the file template
	 * @return long[] of the primaryKeys of service infos associated with the file template
	 */
	@Override
	public long[] getServiceInfoPrimaryKeys(long pk) {
		long[] pks = fileTemplateToServiceInfoTableMapper.getRightPrimaryKeys(pk);

		return pks.clone();
	}

	/**
	 * Returns all the service infos associated with the file template.
	 *
	 * @param pk the primary key of the file template
	 * @return the service infos associated with the file template
	 */
	@Override
	public List<org.opencps.backend.dossiermgt.model.ServiceInfo> getServiceInfos(
		long pk) {
		return getServiceInfos(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the service infos associated with the file template.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FileTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the file template
	 * @param start the lower bound of the range of file templates
	 * @param end the upper bound of the range of file templates (not inclusive)
	 * @return the range of service infos associated with the file template
	 */
	@Override
	public List<org.opencps.backend.dossiermgt.model.ServiceInfo> getServiceInfos(
		long pk, int start, int end) {
		return getServiceInfos(pk, start, end, null);
	}

	/**
	 * Returns an ordered range of all the service infos associated with the file template.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FileTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the file template
	 * @param start the lower bound of the range of file templates
	 * @param end the upper bound of the range of file templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of service infos associated with the file template
	 */
	@Override
	public List<org.opencps.backend.dossiermgt.model.ServiceInfo> getServiceInfos(
		long pk, int start, int end,
		OrderByComparator<org.opencps.backend.dossiermgt.model.ServiceInfo> orderByComparator) {
		return fileTemplateToServiceInfoTableMapper.getRightBaseModels(pk,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of service infos associated with the file template.
	 *
	 * @param pk the primary key of the file template
	 * @return the number of service infos associated with the file template
	 */
	@Override
	public int getServiceInfosSize(long pk) {
		long[] pks = fileTemplateToServiceInfoTableMapper.getRightPrimaryKeys(pk);

		return pks.length;
	}

	/**
	 * Returns <code>true</code> if the service info is associated with the file template.
	 *
	 * @param pk the primary key of the file template
	 * @param serviceInfoPK the primary key of the service info
	 * @return <code>true</code> if the service info is associated with the file template; <code>false</code> otherwise
	 */
	@Override
	public boolean containsServiceInfo(long pk, long serviceInfoPK) {
		return fileTemplateToServiceInfoTableMapper.containsTableMapping(pk,
			serviceInfoPK);
	}

	/**
	 * Returns <code>true</code> if the file template has any service infos associated with it.
	 *
	 * @param pk the primary key of the file template to check for associations with service infos
	 * @return <code>true</code> if the file template has any service infos associated with it; <code>false</code> otherwise
	 */
	@Override
	public boolean containsServiceInfos(long pk) {
		if (getServiceInfosSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Adds an association between the file template and the service info. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the file template
	 * @param serviceInfoPK the primary key of the service info
	 */
	@Override
	public void addServiceInfo(long pk, long serviceInfoPK) {
		FileTemplate fileTemplate = fetchByPrimaryKey(pk);

		if (fileTemplate == null) {
			fileTemplateToServiceInfoTableMapper.addTableMapping(companyProvider.getCompanyId(),
				pk, serviceInfoPK);
		}
		else {
			fileTemplateToServiceInfoTableMapper.addTableMapping(fileTemplate.getCompanyId(),
				pk, serviceInfoPK);
		}
	}

	/**
	 * Adds an association between the file template and the service info. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the file template
	 * @param serviceInfo the service info
	 */
	@Override
	public void addServiceInfo(long pk,
		org.opencps.backend.dossiermgt.model.ServiceInfo serviceInfo) {
		FileTemplate fileTemplate = fetchByPrimaryKey(pk);

		if (fileTemplate == null) {
			fileTemplateToServiceInfoTableMapper.addTableMapping(companyProvider.getCompanyId(),
				pk, serviceInfo.getPrimaryKey());
		}
		else {
			fileTemplateToServiceInfoTableMapper.addTableMapping(fileTemplate.getCompanyId(),
				pk, serviceInfo.getPrimaryKey());
		}
	}

	/**
	 * Adds an association between the file template and the service infos. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the file template
	 * @param serviceInfoPKs the primary keys of the service infos
	 */
	@Override
	public void addServiceInfos(long pk, long[] serviceInfoPKs) {
		long companyId = 0;

		FileTemplate fileTemplate = fetchByPrimaryKey(pk);

		if (fileTemplate == null) {
			companyId = companyProvider.getCompanyId();
		}
		else {
			companyId = fileTemplate.getCompanyId();
		}

		fileTemplateToServiceInfoTableMapper.addTableMappings(companyId, pk,
			serviceInfoPKs);
	}

	/**
	 * Adds an association between the file template and the service infos. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the file template
	 * @param serviceInfos the service infos
	 */
	@Override
	public void addServiceInfos(long pk,
		List<org.opencps.backend.dossiermgt.model.ServiceInfo> serviceInfos) {
		addServiceInfos(pk,
			ListUtil.toLongArray(serviceInfos,
				org.opencps.backend.dossiermgt.model.ServiceInfo.SERVICE_INFO_ID_ACCESSOR));
	}

	/**
	 * Clears all associations between the file template and its service infos. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the file template to clear the associated service infos from
	 */
	@Override
	public void clearServiceInfos(long pk) {
		fileTemplateToServiceInfoTableMapper.deleteLeftPrimaryKeyTableMappings(pk);
	}

	/**
	 * Removes the association between the file template and the service info. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the file template
	 * @param serviceInfoPK the primary key of the service info
	 */
	@Override
	public void removeServiceInfo(long pk, long serviceInfoPK) {
		fileTemplateToServiceInfoTableMapper.deleteTableMapping(pk,
			serviceInfoPK);
	}

	/**
	 * Removes the association between the file template and the service info. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the file template
	 * @param serviceInfo the service info
	 */
	@Override
	public void removeServiceInfo(long pk,
		org.opencps.backend.dossiermgt.model.ServiceInfo serviceInfo) {
		fileTemplateToServiceInfoTableMapper.deleteTableMapping(pk,
			serviceInfo.getPrimaryKey());
	}

	/**
	 * Removes the association between the file template and the service infos. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the file template
	 * @param serviceInfoPKs the primary keys of the service infos
	 */
	@Override
	public void removeServiceInfos(long pk, long[] serviceInfoPKs) {
		fileTemplateToServiceInfoTableMapper.deleteTableMappings(pk,
			serviceInfoPKs);
	}

	/**
	 * Removes the association between the file template and the service infos. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the file template
	 * @param serviceInfos the service infos
	 */
	@Override
	public void removeServiceInfos(long pk,
		List<org.opencps.backend.dossiermgt.model.ServiceInfo> serviceInfos) {
		removeServiceInfos(pk,
			ListUtil.toLongArray(serviceInfos,
				org.opencps.backend.dossiermgt.model.ServiceInfo.SERVICE_INFO_ID_ACCESSOR));
	}

	/**
	 * Sets the service infos associated with the file template, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the file template
	 * @param serviceInfoPKs the primary keys of the service infos to be associated with the file template
	 */
	@Override
	public void setServiceInfos(long pk, long[] serviceInfoPKs) {
		Set<Long> newServiceInfoPKsSet = SetUtil.fromArray(serviceInfoPKs);
		Set<Long> oldServiceInfoPKsSet = SetUtil.fromArray(fileTemplateToServiceInfoTableMapper.getRightPrimaryKeys(
					pk));

		Set<Long> removeServiceInfoPKsSet = new HashSet<Long>(oldServiceInfoPKsSet);

		removeServiceInfoPKsSet.removeAll(newServiceInfoPKsSet);

		fileTemplateToServiceInfoTableMapper.deleteTableMappings(pk,
			ArrayUtil.toLongArray(removeServiceInfoPKsSet));

		newServiceInfoPKsSet.removeAll(oldServiceInfoPKsSet);

		long companyId = 0;

		FileTemplate fileTemplate = fetchByPrimaryKey(pk);

		if (fileTemplate == null) {
			companyId = companyProvider.getCompanyId();
		}
		else {
			companyId = fileTemplate.getCompanyId();
		}

		fileTemplateToServiceInfoTableMapper.addTableMappings(companyId, pk,
			ArrayUtil.toLongArray(newServiceInfoPKsSet));
	}

	/**
	 * Sets the service infos associated with the file template, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the file template
	 * @param serviceInfos the service infos to be associated with the file template
	 */
	@Override
	public void setServiceInfos(long pk,
		List<org.opencps.backend.dossiermgt.model.ServiceInfo> serviceInfos) {
		try {
			long[] serviceInfoPKs = new long[serviceInfos.size()];

			for (int i = 0; i < serviceInfos.size(); i++) {
				org.opencps.backend.dossiermgt.model.ServiceInfo serviceInfo = serviceInfos.get(i);

				serviceInfoPKs[i] = serviceInfo.getPrimaryKey();
			}

			setServiceInfos(pk, serviceInfoPKs);
		}
		catch (Exception e) {
			throw processException(e);
		}
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return FileTemplateModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the file template persistence.
	 */
	public void afterPropertiesSet() {
		fileTemplateToServiceInfoTableMapper = TableMapperFactory.getTableMapper("opencps_services_filetemplates",
				"companyId", "fileTemplateId", "serviceInfoId", this,
				serviceInfoPersistence);
	}

	public void destroy() {
		entityCache.removeCache(FileTemplateImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		TableMapperFactory.removeTableMapper("opencps_services_filetemplates");
	}

	@ServiceReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	@BeanReference(type = ServiceInfoPersistence.class)
	protected ServiceInfoPersistence serviceInfoPersistence;
	protected TableMapper<FileTemplate, org.opencps.backend.dossiermgt.model.ServiceInfo> fileTemplateToServiceInfoTableMapper;
	private static final String _SQL_SELECT_FILETEMPLATE = "SELECT fileTemplate FROM FileTemplate fileTemplate";
	private static final String _SQL_SELECT_FILETEMPLATE_WHERE_PKS_IN = "SELECT fileTemplate FROM FileTemplate fileTemplate WHERE fileTemplateId IN (";
	private static final String _SQL_SELECT_FILETEMPLATE_WHERE = "SELECT fileTemplate FROM FileTemplate fileTemplate WHERE ";
	private static final String _SQL_COUNT_FILETEMPLATE = "SELECT COUNT(fileTemplate) FROM FileTemplate fileTemplate";
	private static final String _SQL_COUNT_FILETEMPLATE_WHERE = "SELECT COUNT(fileTemplate) FROM FileTemplate fileTemplate WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "fileTemplate.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No FileTemplate exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No FileTemplate exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(FileTemplatePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}