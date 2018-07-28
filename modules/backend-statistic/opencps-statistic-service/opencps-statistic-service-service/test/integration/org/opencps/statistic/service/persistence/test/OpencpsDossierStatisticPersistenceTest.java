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

package org.opencps.statistic.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.junit.runner.RunWith;

import org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException;
import org.opencps.statistic.model.OpencpsDossierStatistic;
import org.opencps.statistic.service.OpencpsDossierStatisticLocalServiceUtil;
import org.opencps.statistic.service.persistence.OpencpsDossierStatisticPersistence;
import org.opencps.statistic.service.persistence.OpencpsDossierStatisticUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class OpencpsDossierStatisticPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"org.opencps.statistic.service"));

	@Before
	public void setUp() {
		_persistence = OpencpsDossierStatisticUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<OpencpsDossierStatistic> iterator = _opencpsDossierStatistics.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		OpencpsDossierStatistic opencpsDossierStatistic = _persistence.create(pk);

		Assert.assertNotNull(opencpsDossierStatistic);

		Assert.assertEquals(opencpsDossierStatistic.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		OpencpsDossierStatistic newOpencpsDossierStatistic = addOpencpsDossierStatistic();

		_persistence.remove(newOpencpsDossierStatistic);

		OpencpsDossierStatistic existingOpencpsDossierStatistic = _persistence.fetchByPrimaryKey(newOpencpsDossierStatistic.getPrimaryKey());

		Assert.assertNull(existingOpencpsDossierStatistic);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addOpencpsDossierStatistic();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		OpencpsDossierStatistic newOpencpsDossierStatistic = _persistence.create(pk);

		newOpencpsDossierStatistic.setUuid(RandomTestUtil.randomString());

		newOpencpsDossierStatistic.setCompanyId(RandomTestUtil.nextLong());

		newOpencpsDossierStatistic.setGroupId(RandomTestUtil.nextLong());

		newOpencpsDossierStatistic.setUserId(RandomTestUtil.nextLong());

		newOpencpsDossierStatistic.setUserName(RandomTestUtil.randomString());

		newOpencpsDossierStatistic.setCreateDate(RandomTestUtil.nextDate());

		newOpencpsDossierStatistic.setModifiedDate(RandomTestUtil.nextDate());

		newOpencpsDossierStatistic.setMonth(RandomTestUtil.nextInt());

		newOpencpsDossierStatistic.setYear(RandomTestUtil.nextInt());

		newOpencpsDossierStatistic.setTotalCount(RandomTestUtil.nextInt());

		newOpencpsDossierStatistic.setDeniedCount(RandomTestUtil.nextInt());

		newOpencpsDossierStatistic.setCancelledCount(RandomTestUtil.nextInt());

		newOpencpsDossierStatistic.setProcessCount(RandomTestUtil.nextInt());

		newOpencpsDossierStatistic.setRemainingCount(RandomTestUtil.nextInt());

		newOpencpsDossierStatistic.setReceivedCount(RandomTestUtil.nextInt());

		newOpencpsDossierStatistic.setOnlineCount(RandomTestUtil.nextInt());

		newOpencpsDossierStatistic.setReleaseCount(RandomTestUtil.nextInt());

		newOpencpsDossierStatistic.setBetimesCount(RandomTestUtil.nextInt());

		newOpencpsDossierStatistic.setOntimeCount(RandomTestUtil.nextInt());

		newOpencpsDossierStatistic.setOvertimeCount(RandomTestUtil.nextInt());

		newOpencpsDossierStatistic.setDoneCount(RandomTestUtil.nextInt());

		newOpencpsDossierStatistic.setReleasingCount(RandomTestUtil.nextInt());

		newOpencpsDossierStatistic.setUnresolvedCount(RandomTestUtil.nextInt());

		newOpencpsDossierStatistic.setProcessingCount(RandomTestUtil.nextInt());

		newOpencpsDossierStatistic.setUndueCount(RandomTestUtil.nextInt());

		newOpencpsDossierStatistic.setOverdueCount(RandomTestUtil.nextInt());

		newOpencpsDossierStatistic.setPausingCount(RandomTestUtil.nextInt());

		newOpencpsDossierStatistic.setOntimePercentage(RandomTestUtil.nextInt());

		newOpencpsDossierStatistic.setGovAgencyCode(RandomTestUtil.randomString());

		newOpencpsDossierStatistic.setGroupAgencyCode(RandomTestUtil.randomString());

		newOpencpsDossierStatistic.setGovAgencyName(RandomTestUtil.randomString());

		newOpencpsDossierStatistic.setDomainCode(RandomTestUtil.randomString());

		newOpencpsDossierStatistic.setDomainName(RandomTestUtil.randomString());

		newOpencpsDossierStatistic.setReporting(RandomTestUtil.randomBoolean());

		newOpencpsDossierStatistic.setOvertimeInside(RandomTestUtil.nextInt());

		newOpencpsDossierStatistic.setOvertimeOutside(RandomTestUtil.nextInt());

		newOpencpsDossierStatistic.setInteroperatingCount(RandomTestUtil.nextInt());

		newOpencpsDossierStatistic.setWaitingCount(RandomTestUtil.nextInt());

		_opencpsDossierStatistics.add(_persistence.update(
				newOpencpsDossierStatistic));

		OpencpsDossierStatistic existingOpencpsDossierStatistic = _persistence.findByPrimaryKey(newOpencpsDossierStatistic.getPrimaryKey());

		Assert.assertEquals(existingOpencpsDossierStatistic.getUuid(),
			newOpencpsDossierStatistic.getUuid());
		Assert.assertEquals(existingOpencpsDossierStatistic.getDossierStatisticId(),
			newOpencpsDossierStatistic.getDossierStatisticId());
		Assert.assertEquals(existingOpencpsDossierStatistic.getCompanyId(),
			newOpencpsDossierStatistic.getCompanyId());
		Assert.assertEquals(existingOpencpsDossierStatistic.getGroupId(),
			newOpencpsDossierStatistic.getGroupId());
		Assert.assertEquals(existingOpencpsDossierStatistic.getUserId(),
			newOpencpsDossierStatistic.getUserId());
		Assert.assertEquals(existingOpencpsDossierStatistic.getUserName(),
			newOpencpsDossierStatistic.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingOpencpsDossierStatistic.getCreateDate()),
			Time.getShortTimestamp(newOpencpsDossierStatistic.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingOpencpsDossierStatistic.getModifiedDate()),
			Time.getShortTimestamp(newOpencpsDossierStatistic.getModifiedDate()));
		Assert.assertEquals(existingOpencpsDossierStatistic.getMonth(),
			newOpencpsDossierStatistic.getMonth());
		Assert.assertEquals(existingOpencpsDossierStatistic.getYear(),
			newOpencpsDossierStatistic.getYear());
		Assert.assertEquals(existingOpencpsDossierStatistic.getTotalCount(),
			newOpencpsDossierStatistic.getTotalCount());
		Assert.assertEquals(existingOpencpsDossierStatistic.getDeniedCount(),
			newOpencpsDossierStatistic.getDeniedCount());
		Assert.assertEquals(existingOpencpsDossierStatistic.getCancelledCount(),
			newOpencpsDossierStatistic.getCancelledCount());
		Assert.assertEquals(existingOpencpsDossierStatistic.getProcessCount(),
			newOpencpsDossierStatistic.getProcessCount());
		Assert.assertEquals(existingOpencpsDossierStatistic.getRemainingCount(),
			newOpencpsDossierStatistic.getRemainingCount());
		Assert.assertEquals(existingOpencpsDossierStatistic.getReceivedCount(),
			newOpencpsDossierStatistic.getReceivedCount());
		Assert.assertEquals(existingOpencpsDossierStatistic.getOnlineCount(),
			newOpencpsDossierStatistic.getOnlineCount());
		Assert.assertEquals(existingOpencpsDossierStatistic.getReleaseCount(),
			newOpencpsDossierStatistic.getReleaseCount());
		Assert.assertEquals(existingOpencpsDossierStatistic.getBetimesCount(),
			newOpencpsDossierStatistic.getBetimesCount());
		Assert.assertEquals(existingOpencpsDossierStatistic.getOntimeCount(),
			newOpencpsDossierStatistic.getOntimeCount());
		Assert.assertEquals(existingOpencpsDossierStatistic.getOvertimeCount(),
			newOpencpsDossierStatistic.getOvertimeCount());
		Assert.assertEquals(existingOpencpsDossierStatistic.getDoneCount(),
			newOpencpsDossierStatistic.getDoneCount());
		Assert.assertEquals(existingOpencpsDossierStatistic.getReleasingCount(),
			newOpencpsDossierStatistic.getReleasingCount());
		Assert.assertEquals(existingOpencpsDossierStatistic.getUnresolvedCount(),
			newOpencpsDossierStatistic.getUnresolvedCount());
		Assert.assertEquals(existingOpencpsDossierStatistic.getProcessingCount(),
			newOpencpsDossierStatistic.getProcessingCount());
		Assert.assertEquals(existingOpencpsDossierStatistic.getUndueCount(),
			newOpencpsDossierStatistic.getUndueCount());
		Assert.assertEquals(existingOpencpsDossierStatistic.getOverdueCount(),
			newOpencpsDossierStatistic.getOverdueCount());
		Assert.assertEquals(existingOpencpsDossierStatistic.getPausingCount(),
			newOpencpsDossierStatistic.getPausingCount());
		Assert.assertEquals(existingOpencpsDossierStatistic.getOntimePercentage(),
			newOpencpsDossierStatistic.getOntimePercentage());
		Assert.assertEquals(existingOpencpsDossierStatistic.getGovAgencyCode(),
			newOpencpsDossierStatistic.getGovAgencyCode());
		Assert.assertEquals(existingOpencpsDossierStatistic.getGroupAgencyCode(),
			newOpencpsDossierStatistic.getGroupAgencyCode());
		Assert.assertEquals(existingOpencpsDossierStatistic.getGovAgencyName(),
			newOpencpsDossierStatistic.getGovAgencyName());
		Assert.assertEquals(existingOpencpsDossierStatistic.getDomainCode(),
			newOpencpsDossierStatistic.getDomainCode());
		Assert.assertEquals(existingOpencpsDossierStatistic.getDomainName(),
			newOpencpsDossierStatistic.getDomainName());
		Assert.assertEquals(existingOpencpsDossierStatistic.getReporting(),
			newOpencpsDossierStatistic.getReporting());
		Assert.assertEquals(existingOpencpsDossierStatistic.getOvertimeInside(),
			newOpencpsDossierStatistic.getOvertimeInside());
		Assert.assertEquals(existingOpencpsDossierStatistic.getOvertimeOutside(),
			newOpencpsDossierStatistic.getOvertimeOutside());
		Assert.assertEquals(existingOpencpsDossierStatistic.getInteroperatingCount(),
			newOpencpsDossierStatistic.getInteroperatingCount());
		Assert.assertEquals(existingOpencpsDossierStatistic.getWaitingCount(),
			newOpencpsDossierStatistic.getWaitingCount());
	}

	@Test
	public void testCountByUuid() throws Exception {
		_persistence.countByUuid(StringPool.BLANK);

		_persistence.countByUuid(StringPool.NULL);

		_persistence.countByUuid((String)null);
	}

	@Test
	public void testCountByUUID_G() throws Exception {
		_persistence.countByUUID_G(StringPool.BLANK, RandomTestUtil.nextLong());

		_persistence.countByUUID_G(StringPool.NULL, 0L);

		_persistence.countByUUID_G((String)null, 0L);
	}

	@Test
	public void testCountByUuid_C() throws Exception {
		_persistence.countByUuid_C(StringPool.BLANK, RandomTestUtil.nextLong());

		_persistence.countByUuid_C(StringPool.NULL, 0L);

		_persistence.countByUuid_C((String)null, 0L);
	}

	@Test
	public void testCountByG_UID_Y() throws Exception {
		_persistence.countByG_UID_Y(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), RandomTestUtil.nextInt());

		_persistence.countByG_UID_Y(0L, 0L, 0);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		OpencpsDossierStatistic newOpencpsDossierStatistic = addOpencpsDossierStatistic();

		OpencpsDossierStatistic existingOpencpsDossierStatistic = _persistence.findByPrimaryKey(newOpencpsDossierStatistic.getPrimaryKey());

		Assert.assertEquals(existingOpencpsDossierStatistic,
			newOpencpsDossierStatistic);
	}

	@Test(expected = NoSuchOpencpsDossierStatisticException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<OpencpsDossierStatistic> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("opencps_statistic", "uuid",
			true, "dossierStatisticId", true, "companyId", true, "groupId",
			true, "userId", true, "userName", true, "createDate", true,
			"modifiedDate", true, "month", true, "year", true, "totalCount",
			true, "deniedCount", true, "cancelledCount", true, "processCount",
			true, "remainingCount", true, "receivedCount", true, "onlineCount",
			true, "releaseCount", true, "betimesCount", true, "ontimeCount",
			true, "overtimeCount", true, "doneCount", true, "releasingCount",
			true, "unresolvedCount", true, "processingCount", true,
			"undueCount", true, "overdueCount", true, "pausingCount", true,
			"ontimePercentage", true, "govAgencyCode", true, "groupAgencyCode",
			true, "govAgencyName", true, "domainCode", true, "domainName",
			true, "reporting", true, "overtimeInside", true, "overtimeOutside",
			true, "interoperatingCount", true, "waitingCount", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		OpencpsDossierStatistic newOpencpsDossierStatistic = addOpencpsDossierStatistic();

		OpencpsDossierStatistic existingOpencpsDossierStatistic = _persistence.fetchByPrimaryKey(newOpencpsDossierStatistic.getPrimaryKey());

		Assert.assertEquals(existingOpencpsDossierStatistic,
			newOpencpsDossierStatistic);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		OpencpsDossierStatistic missingOpencpsDossierStatistic = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingOpencpsDossierStatistic);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		OpencpsDossierStatistic newOpencpsDossierStatistic1 = addOpencpsDossierStatistic();
		OpencpsDossierStatistic newOpencpsDossierStatistic2 = addOpencpsDossierStatistic();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newOpencpsDossierStatistic1.getPrimaryKey());
		primaryKeys.add(newOpencpsDossierStatistic2.getPrimaryKey());

		Map<Serializable, OpencpsDossierStatistic> opencpsDossierStatistics = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, opencpsDossierStatistics.size());
		Assert.assertEquals(newOpencpsDossierStatistic1,
			opencpsDossierStatistics.get(
				newOpencpsDossierStatistic1.getPrimaryKey()));
		Assert.assertEquals(newOpencpsDossierStatistic2,
			opencpsDossierStatistics.get(
				newOpencpsDossierStatistic2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, OpencpsDossierStatistic> opencpsDossierStatistics = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(opencpsDossierStatistics.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		OpencpsDossierStatistic newOpencpsDossierStatistic = addOpencpsDossierStatistic();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newOpencpsDossierStatistic.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, OpencpsDossierStatistic> opencpsDossierStatistics = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, opencpsDossierStatistics.size());
		Assert.assertEquals(newOpencpsDossierStatistic,
			opencpsDossierStatistics.get(
				newOpencpsDossierStatistic.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, OpencpsDossierStatistic> opencpsDossierStatistics = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(opencpsDossierStatistics.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		OpencpsDossierStatistic newOpencpsDossierStatistic = addOpencpsDossierStatistic();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newOpencpsDossierStatistic.getPrimaryKey());

		Map<Serializable, OpencpsDossierStatistic> opencpsDossierStatistics = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, opencpsDossierStatistics.size());
		Assert.assertEquals(newOpencpsDossierStatistic,
			opencpsDossierStatistics.get(
				newOpencpsDossierStatistic.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = OpencpsDossierStatisticLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<OpencpsDossierStatistic>() {
				@Override
				public void performAction(
					OpencpsDossierStatistic opencpsDossierStatistic) {
					Assert.assertNotNull(opencpsDossierStatistic);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		OpencpsDossierStatistic newOpencpsDossierStatistic = addOpencpsDossierStatistic();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(OpencpsDossierStatistic.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("dossierStatisticId",
				newOpencpsDossierStatistic.getDossierStatisticId()));

		List<OpencpsDossierStatistic> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		OpencpsDossierStatistic existingOpencpsDossierStatistic = result.get(0);

		Assert.assertEquals(existingOpencpsDossierStatistic,
			newOpencpsDossierStatistic);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(OpencpsDossierStatistic.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("dossierStatisticId",
				RandomTestUtil.nextLong()));

		List<OpencpsDossierStatistic> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		OpencpsDossierStatistic newOpencpsDossierStatistic = addOpencpsDossierStatistic();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(OpencpsDossierStatistic.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"dossierStatisticId"));

		Object newDossierStatisticId = newOpencpsDossierStatistic.getDossierStatisticId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("dossierStatisticId",
				new Object[] { newDossierStatisticId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingDossierStatisticId = result.get(0);

		Assert.assertEquals(existingDossierStatisticId, newDossierStatisticId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(OpencpsDossierStatistic.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"dossierStatisticId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("dossierStatisticId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		OpencpsDossierStatistic newOpencpsDossierStatistic = addOpencpsDossierStatistic();

		_persistence.clearCache();

		OpencpsDossierStatistic existingOpencpsDossierStatistic = _persistence.findByPrimaryKey(newOpencpsDossierStatistic.getPrimaryKey());

		Assert.assertTrue(Objects.equals(
				existingOpencpsDossierStatistic.getUuid(),
				ReflectionTestUtil.invoke(existingOpencpsDossierStatistic,
					"getOriginalUuid", new Class<?>[0])));
		Assert.assertEquals(Long.valueOf(
				existingOpencpsDossierStatistic.getGroupId()),
			ReflectionTestUtil.<Long>invoke(existingOpencpsDossierStatistic,
				"getOriginalGroupId", new Class<?>[0]));
	}

	protected OpencpsDossierStatistic addOpencpsDossierStatistic()
		throws Exception {
		long pk = RandomTestUtil.nextLong();

		OpencpsDossierStatistic opencpsDossierStatistic = _persistence.create(pk);

		opencpsDossierStatistic.setUuid(RandomTestUtil.randomString());

		opencpsDossierStatistic.setCompanyId(RandomTestUtil.nextLong());

		opencpsDossierStatistic.setGroupId(RandomTestUtil.nextLong());

		opencpsDossierStatistic.setUserId(RandomTestUtil.nextLong());

		opencpsDossierStatistic.setUserName(RandomTestUtil.randomString());

		opencpsDossierStatistic.setCreateDate(RandomTestUtil.nextDate());

		opencpsDossierStatistic.setModifiedDate(RandomTestUtil.nextDate());

		opencpsDossierStatistic.setMonth(RandomTestUtil.nextInt());

		opencpsDossierStatistic.setYear(RandomTestUtil.nextInt());

		opencpsDossierStatistic.setTotalCount(RandomTestUtil.nextInt());

		opencpsDossierStatistic.setDeniedCount(RandomTestUtil.nextInt());

		opencpsDossierStatistic.setCancelledCount(RandomTestUtil.nextInt());

		opencpsDossierStatistic.setProcessCount(RandomTestUtil.nextInt());

		opencpsDossierStatistic.setRemainingCount(RandomTestUtil.nextInt());

		opencpsDossierStatistic.setReceivedCount(RandomTestUtil.nextInt());

		opencpsDossierStatistic.setOnlineCount(RandomTestUtil.nextInt());

		opencpsDossierStatistic.setReleaseCount(RandomTestUtil.nextInt());

		opencpsDossierStatistic.setBetimesCount(RandomTestUtil.nextInt());

		opencpsDossierStatistic.setOntimeCount(RandomTestUtil.nextInt());

		opencpsDossierStatistic.setOvertimeCount(RandomTestUtil.nextInt());

		opencpsDossierStatistic.setDoneCount(RandomTestUtil.nextInt());

		opencpsDossierStatistic.setReleasingCount(RandomTestUtil.nextInt());

		opencpsDossierStatistic.setUnresolvedCount(RandomTestUtil.nextInt());

		opencpsDossierStatistic.setProcessingCount(RandomTestUtil.nextInt());

		opencpsDossierStatistic.setUndueCount(RandomTestUtil.nextInt());

		opencpsDossierStatistic.setOverdueCount(RandomTestUtil.nextInt());

		opencpsDossierStatistic.setPausingCount(RandomTestUtil.nextInt());

		opencpsDossierStatistic.setOntimePercentage(RandomTestUtil.nextInt());

		opencpsDossierStatistic.setGovAgencyCode(RandomTestUtil.randomString());

		opencpsDossierStatistic.setGroupAgencyCode(RandomTestUtil.randomString());

		opencpsDossierStatistic.setGovAgencyName(RandomTestUtil.randomString());

		opencpsDossierStatistic.setDomainCode(RandomTestUtil.randomString());

		opencpsDossierStatistic.setDomainName(RandomTestUtil.randomString());

		opencpsDossierStatistic.setReporting(RandomTestUtil.randomBoolean());

		opencpsDossierStatistic.setOvertimeInside(RandomTestUtil.nextInt());

		opencpsDossierStatistic.setOvertimeOutside(RandomTestUtil.nextInt());

		opencpsDossierStatistic.setInteroperatingCount(RandomTestUtil.nextInt());

		opencpsDossierStatistic.setWaitingCount(RandomTestUtil.nextInt());

		_opencpsDossierStatistics.add(_persistence.update(
				opencpsDossierStatistic));

		return opencpsDossierStatistic;
	}

	private List<OpencpsDossierStatistic> _opencpsDossierStatistics = new ArrayList<OpencpsDossierStatistic>();
	private OpencpsDossierStatisticPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}