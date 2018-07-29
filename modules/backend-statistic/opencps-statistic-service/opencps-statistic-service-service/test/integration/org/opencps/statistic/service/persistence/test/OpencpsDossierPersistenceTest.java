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
import com.liferay.portal.kernel.test.AssertUtils;
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

import org.opencps.statistic.exception.NoSuchOpencpsDossierException;
import org.opencps.statistic.model.OpencpsDossier;
import org.opencps.statistic.service.OpencpsDossierLocalServiceUtil;
import org.opencps.statistic.service.persistence.OpencpsDossierPersistence;
import org.opencps.statistic.service.persistence.OpencpsDossierUtil;

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
public class OpencpsDossierPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"org.opencps.statistic.service"));

	@Before
	public void setUp() {
		_persistence = OpencpsDossierUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<OpencpsDossier> iterator = _opencpsDossiers.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		OpencpsDossier opencpsDossier = _persistence.create(pk);

		Assert.assertNotNull(opencpsDossier);

		Assert.assertEquals(opencpsDossier.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		OpencpsDossier newOpencpsDossier = addOpencpsDossier();

		_persistence.remove(newOpencpsDossier);

		OpencpsDossier existingOpencpsDossier = _persistence.fetchByPrimaryKey(newOpencpsDossier.getPrimaryKey());

		Assert.assertNull(existingOpencpsDossier);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addOpencpsDossier();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		OpencpsDossier newOpencpsDossier = _persistence.create(pk);

		newOpencpsDossier.setUuid(RandomTestUtil.randomString());

		newOpencpsDossier.setGroupId(RandomTestUtil.nextLong());

		newOpencpsDossier.setCompanyId(RandomTestUtil.nextLong());

		newOpencpsDossier.setUserId(RandomTestUtil.nextLong());

		newOpencpsDossier.setUserName(RandomTestUtil.randomString());

		newOpencpsDossier.setCreateDate(RandomTestUtil.nextDate());

		newOpencpsDossier.setModifiedDate(RandomTestUtil.nextDate());

		newOpencpsDossier.setReferenceUid(RandomTestUtil.randomString());

		newOpencpsDossier.setCounter(RandomTestUtil.nextInt());

		newOpencpsDossier.setRegisterBookCode(RandomTestUtil.randomString());

		newOpencpsDossier.setRegisterBookName(RandomTestUtil.randomString());

		newOpencpsDossier.setDossierRegister(RandomTestUtil.randomString());

		newOpencpsDossier.setProcessNo(RandomTestUtil.randomString());

		newOpencpsDossier.setServiceCode(RandomTestUtil.randomString());

		newOpencpsDossier.setServiceName(RandomTestUtil.randomString());

		newOpencpsDossier.setGovAgencyCode(RandomTestUtil.randomString());

		newOpencpsDossier.setGovAgencyName(RandomTestUtil.randomString());

		newOpencpsDossier.setApplicantName(RandomTestUtil.randomString());

		newOpencpsDossier.setApplicantIdType(RandomTestUtil.randomString());

		newOpencpsDossier.setApplicantIdNo(RandomTestUtil.randomString());

		newOpencpsDossier.setApplicantIdDate(RandomTestUtil.nextDate());

		newOpencpsDossier.setAddress(RandomTestUtil.randomString());

		newOpencpsDossier.setCityCode(RandomTestUtil.randomString());

		newOpencpsDossier.setCityName(RandomTestUtil.randomString());

		newOpencpsDossier.setDistrictCode(RandomTestUtil.randomString());

		newOpencpsDossier.setDistrictName(RandomTestUtil.randomString());

		newOpencpsDossier.setWardCode(RandomTestUtil.randomString());

		newOpencpsDossier.setWardName(RandomTestUtil.randomString());

		newOpencpsDossier.setContactName(RandomTestUtil.randomString());

		newOpencpsDossier.setContactTelNo(RandomTestUtil.randomString());

		newOpencpsDossier.setContactEmail(RandomTestUtil.randomString());

		newOpencpsDossier.setDelegateName(RandomTestUtil.randomString());

		newOpencpsDossier.setDelegateIdNo(RandomTestUtil.randomString());

		newOpencpsDossier.setDelegateTelNo(RandomTestUtil.randomString());

		newOpencpsDossier.setDelegateEmail(RandomTestUtil.randomString());

		newOpencpsDossier.setDelegateAddress(RandomTestUtil.randomString());

		newOpencpsDossier.setDelegateCityCode(RandomTestUtil.randomString());

		newOpencpsDossier.setDelegateCityName(RandomTestUtil.randomString());

		newOpencpsDossier.setDelegateDistrictCode(RandomTestUtil.randomString());

		newOpencpsDossier.setDelegateDistrictName(RandomTestUtil.randomString());

		newOpencpsDossier.setDelegateWardCode(RandomTestUtil.randomString());

		newOpencpsDossier.setDelegateWardName(RandomTestUtil.randomString());

		newOpencpsDossier.setDossierTemplateNo(RandomTestUtil.randomString());

		newOpencpsDossier.setDossierTemplateName(RandomTestUtil.randomString());

		newOpencpsDossier.setDossierNote(RandomTestUtil.randomString());

		newOpencpsDossier.setSubmissionNote(RandomTestUtil.randomString());

		newOpencpsDossier.setApplicantNote(RandomTestUtil.randomString());

		newOpencpsDossier.setBriefNote(RandomTestUtil.randomString());

		newOpencpsDossier.setDossierNo(RandomTestUtil.randomString());

		newOpencpsDossier.setSubmitting(RandomTestUtil.randomBoolean());

		newOpencpsDossier.setProcessDate(RandomTestUtil.nextDate());

		newOpencpsDossier.setSubmitDate(RandomTestUtil.nextDate());

		newOpencpsDossier.setReceiveDate(RandomTestUtil.nextDate());

		newOpencpsDossier.setDueDate(RandomTestUtil.nextDate());

		newOpencpsDossier.setExtendDate(RandomTestUtil.nextDate());

		newOpencpsDossier.setReleaseDate(RandomTestUtil.nextDate());

		newOpencpsDossier.setFinishDate(RandomTestUtil.nextDate());

		newOpencpsDossier.setCancellingDate(RandomTestUtil.nextDate());

		newOpencpsDossier.setCorrecttingDate(RandomTestUtil.nextDate());

		newOpencpsDossier.setDossierStatus(RandomTestUtil.randomString());

		newOpencpsDossier.setDossierStatusText(RandomTestUtil.randomString());

		newOpencpsDossier.setDossierSubStatus(RandomTestUtil.randomString());

		newOpencpsDossier.setDossierSubStatusText(RandomTestUtil.randomString());

		newOpencpsDossier.setFolderId(RandomTestUtil.nextLong());

		newOpencpsDossier.setDossierActionId(RandomTestUtil.nextLong());

		newOpencpsDossier.setViaPostal(RandomTestUtil.nextInt());

		newOpencpsDossier.setPostalServiceCode(RandomTestUtil.randomString());

		newOpencpsDossier.setPostalServiceName(RandomTestUtil.randomString());

		newOpencpsDossier.setPostalAddress(RandomTestUtil.randomString());

		newOpencpsDossier.setPostalCityCode(RandomTestUtil.randomString());

		newOpencpsDossier.setPostalCityName(RandomTestUtil.randomString());

		newOpencpsDossier.setPostalDistrictCode(RandomTestUtil.randomString());

		newOpencpsDossier.setPostalDistrictName(RandomTestUtil.randomString());

		newOpencpsDossier.setPostalWardCode(RandomTestUtil.randomString());

		newOpencpsDossier.setPostalWardName(RandomTestUtil.randomString());

		newOpencpsDossier.setPostalTelNo(RandomTestUtil.randomString());

		newOpencpsDossier.setPassword(RandomTestUtil.randomString());

		newOpencpsDossier.setNotification(RandomTestUtil.randomBoolean());

		newOpencpsDossier.setOnline(RandomTestUtil.randomBoolean());

		newOpencpsDossier.setOriginal(RandomTestUtil.randomBoolean());

		newOpencpsDossier.setServerNo(RandomTestUtil.randomString());

		newOpencpsDossier.setEndorsementDate(RandomTestUtil.nextDate());

		newOpencpsDossier.setLockState(RandomTestUtil.randomString());

		newOpencpsDossier.setOriginality(RandomTestUtil.nextInt());

		newOpencpsDossier.setOriginDossierId(RandomTestUtil.nextLong());

		newOpencpsDossier.setSampleCount(RandomTestUtil.nextLong());

		newOpencpsDossier.setDurationUnit(RandomTestUtil.nextDouble());

		newOpencpsDossier.setDurationCount(RandomTestUtil.nextDouble());

		_opencpsDossiers.add(_persistence.update(newOpencpsDossier));

		OpencpsDossier existingOpencpsDossier = _persistence.findByPrimaryKey(newOpencpsDossier.getPrimaryKey());

		Assert.assertEquals(existingOpencpsDossier.getUuid(),
			newOpencpsDossier.getUuid());
		Assert.assertEquals(existingOpencpsDossier.getDossierId(),
			newOpencpsDossier.getDossierId());
		Assert.assertEquals(existingOpencpsDossier.getGroupId(),
			newOpencpsDossier.getGroupId());
		Assert.assertEquals(existingOpencpsDossier.getCompanyId(),
			newOpencpsDossier.getCompanyId());
		Assert.assertEquals(existingOpencpsDossier.getUserId(),
			newOpencpsDossier.getUserId());
		Assert.assertEquals(existingOpencpsDossier.getUserName(),
			newOpencpsDossier.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingOpencpsDossier.getCreateDate()),
			Time.getShortTimestamp(newOpencpsDossier.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingOpencpsDossier.getModifiedDate()),
			Time.getShortTimestamp(newOpencpsDossier.getModifiedDate()));
		Assert.assertEquals(existingOpencpsDossier.getReferenceUid(),
			newOpencpsDossier.getReferenceUid());
		Assert.assertEquals(existingOpencpsDossier.getCounter(),
			newOpencpsDossier.getCounter());
		Assert.assertEquals(existingOpencpsDossier.getRegisterBookCode(),
			newOpencpsDossier.getRegisterBookCode());
		Assert.assertEquals(existingOpencpsDossier.getRegisterBookName(),
			newOpencpsDossier.getRegisterBookName());
		Assert.assertEquals(existingOpencpsDossier.getDossierRegister(),
			newOpencpsDossier.getDossierRegister());
		Assert.assertEquals(existingOpencpsDossier.getProcessNo(),
			newOpencpsDossier.getProcessNo());
		Assert.assertEquals(existingOpencpsDossier.getServiceCode(),
			newOpencpsDossier.getServiceCode());
		Assert.assertEquals(existingOpencpsDossier.getServiceName(),
			newOpencpsDossier.getServiceName());
		Assert.assertEquals(existingOpencpsDossier.getGovAgencyCode(),
			newOpencpsDossier.getGovAgencyCode());
		Assert.assertEquals(existingOpencpsDossier.getGovAgencyName(),
			newOpencpsDossier.getGovAgencyName());
		Assert.assertEquals(existingOpencpsDossier.getApplicantName(),
			newOpencpsDossier.getApplicantName());
		Assert.assertEquals(existingOpencpsDossier.getApplicantIdType(),
			newOpencpsDossier.getApplicantIdType());
		Assert.assertEquals(existingOpencpsDossier.getApplicantIdNo(),
			newOpencpsDossier.getApplicantIdNo());
		Assert.assertEquals(Time.getShortTimestamp(
				existingOpencpsDossier.getApplicantIdDate()),
			Time.getShortTimestamp(newOpencpsDossier.getApplicantIdDate()));
		Assert.assertEquals(existingOpencpsDossier.getAddress(),
			newOpencpsDossier.getAddress());
		Assert.assertEquals(existingOpencpsDossier.getCityCode(),
			newOpencpsDossier.getCityCode());
		Assert.assertEquals(existingOpencpsDossier.getCityName(),
			newOpencpsDossier.getCityName());
		Assert.assertEquals(existingOpencpsDossier.getDistrictCode(),
			newOpencpsDossier.getDistrictCode());
		Assert.assertEquals(existingOpencpsDossier.getDistrictName(),
			newOpencpsDossier.getDistrictName());
		Assert.assertEquals(existingOpencpsDossier.getWardCode(),
			newOpencpsDossier.getWardCode());
		Assert.assertEquals(existingOpencpsDossier.getWardName(),
			newOpencpsDossier.getWardName());
		Assert.assertEquals(existingOpencpsDossier.getContactName(),
			newOpencpsDossier.getContactName());
		Assert.assertEquals(existingOpencpsDossier.getContactTelNo(),
			newOpencpsDossier.getContactTelNo());
		Assert.assertEquals(existingOpencpsDossier.getContactEmail(),
			newOpencpsDossier.getContactEmail());
		Assert.assertEquals(existingOpencpsDossier.getDelegateName(),
			newOpencpsDossier.getDelegateName());
		Assert.assertEquals(existingOpencpsDossier.getDelegateIdNo(),
			newOpencpsDossier.getDelegateIdNo());
		Assert.assertEquals(existingOpencpsDossier.getDelegateTelNo(),
			newOpencpsDossier.getDelegateTelNo());
		Assert.assertEquals(existingOpencpsDossier.getDelegateEmail(),
			newOpencpsDossier.getDelegateEmail());
		Assert.assertEquals(existingOpencpsDossier.getDelegateAddress(),
			newOpencpsDossier.getDelegateAddress());
		Assert.assertEquals(existingOpencpsDossier.getDelegateCityCode(),
			newOpencpsDossier.getDelegateCityCode());
		Assert.assertEquals(existingOpencpsDossier.getDelegateCityName(),
			newOpencpsDossier.getDelegateCityName());
		Assert.assertEquals(existingOpencpsDossier.getDelegateDistrictCode(),
			newOpencpsDossier.getDelegateDistrictCode());
		Assert.assertEquals(existingOpencpsDossier.getDelegateDistrictName(),
			newOpencpsDossier.getDelegateDistrictName());
		Assert.assertEquals(existingOpencpsDossier.getDelegateWardCode(),
			newOpencpsDossier.getDelegateWardCode());
		Assert.assertEquals(existingOpencpsDossier.getDelegateWardName(),
			newOpencpsDossier.getDelegateWardName());
		Assert.assertEquals(existingOpencpsDossier.getDossierTemplateNo(),
			newOpencpsDossier.getDossierTemplateNo());
		Assert.assertEquals(existingOpencpsDossier.getDossierTemplateName(),
			newOpencpsDossier.getDossierTemplateName());
		Assert.assertEquals(existingOpencpsDossier.getDossierNote(),
			newOpencpsDossier.getDossierNote());
		Assert.assertEquals(existingOpencpsDossier.getSubmissionNote(),
			newOpencpsDossier.getSubmissionNote());
		Assert.assertEquals(existingOpencpsDossier.getApplicantNote(),
			newOpencpsDossier.getApplicantNote());
		Assert.assertEquals(existingOpencpsDossier.getBriefNote(),
			newOpencpsDossier.getBriefNote());
		Assert.assertEquals(existingOpencpsDossier.getDossierNo(),
			newOpencpsDossier.getDossierNo());
		Assert.assertEquals(existingOpencpsDossier.getSubmitting(),
			newOpencpsDossier.getSubmitting());
		Assert.assertEquals(Time.getShortTimestamp(
				existingOpencpsDossier.getProcessDate()),
			Time.getShortTimestamp(newOpencpsDossier.getProcessDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingOpencpsDossier.getSubmitDate()),
			Time.getShortTimestamp(newOpencpsDossier.getSubmitDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingOpencpsDossier.getReceiveDate()),
			Time.getShortTimestamp(newOpencpsDossier.getReceiveDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingOpencpsDossier.getDueDate()),
			Time.getShortTimestamp(newOpencpsDossier.getDueDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingOpencpsDossier.getExtendDate()),
			Time.getShortTimestamp(newOpencpsDossier.getExtendDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingOpencpsDossier.getReleaseDate()),
			Time.getShortTimestamp(newOpencpsDossier.getReleaseDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingOpencpsDossier.getFinishDate()),
			Time.getShortTimestamp(newOpencpsDossier.getFinishDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingOpencpsDossier.getCancellingDate()),
			Time.getShortTimestamp(newOpencpsDossier.getCancellingDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingOpencpsDossier.getCorrecttingDate()),
			Time.getShortTimestamp(newOpencpsDossier.getCorrecttingDate()));
		Assert.assertEquals(existingOpencpsDossier.getDossierStatus(),
			newOpencpsDossier.getDossierStatus());
		Assert.assertEquals(existingOpencpsDossier.getDossierStatusText(),
			newOpencpsDossier.getDossierStatusText());
		Assert.assertEquals(existingOpencpsDossier.getDossierSubStatus(),
			newOpencpsDossier.getDossierSubStatus());
		Assert.assertEquals(existingOpencpsDossier.getDossierSubStatusText(),
			newOpencpsDossier.getDossierSubStatusText());
		Assert.assertEquals(existingOpencpsDossier.getFolderId(),
			newOpencpsDossier.getFolderId());
		Assert.assertEquals(existingOpencpsDossier.getDossierActionId(),
			newOpencpsDossier.getDossierActionId());
		Assert.assertEquals(existingOpencpsDossier.getViaPostal(),
			newOpencpsDossier.getViaPostal());
		Assert.assertEquals(existingOpencpsDossier.getPostalServiceCode(),
			newOpencpsDossier.getPostalServiceCode());
		Assert.assertEquals(existingOpencpsDossier.getPostalServiceName(),
			newOpencpsDossier.getPostalServiceName());
		Assert.assertEquals(existingOpencpsDossier.getPostalAddress(),
			newOpencpsDossier.getPostalAddress());
		Assert.assertEquals(existingOpencpsDossier.getPostalCityCode(),
			newOpencpsDossier.getPostalCityCode());
		Assert.assertEquals(existingOpencpsDossier.getPostalCityName(),
			newOpencpsDossier.getPostalCityName());
		Assert.assertEquals(existingOpencpsDossier.getPostalDistrictCode(),
			newOpencpsDossier.getPostalDistrictCode());
		Assert.assertEquals(existingOpencpsDossier.getPostalDistrictName(),
			newOpencpsDossier.getPostalDistrictName());
		Assert.assertEquals(existingOpencpsDossier.getPostalWardCode(),
			newOpencpsDossier.getPostalWardCode());
		Assert.assertEquals(existingOpencpsDossier.getPostalWardName(),
			newOpencpsDossier.getPostalWardName());
		Assert.assertEquals(existingOpencpsDossier.getPostalTelNo(),
			newOpencpsDossier.getPostalTelNo());
		Assert.assertEquals(existingOpencpsDossier.getPassword(),
			newOpencpsDossier.getPassword());
		Assert.assertEquals(existingOpencpsDossier.getNotification(),
			newOpencpsDossier.getNotification());
		Assert.assertEquals(existingOpencpsDossier.getOnline(),
			newOpencpsDossier.getOnline());
		Assert.assertEquals(existingOpencpsDossier.getOriginal(),
			newOpencpsDossier.getOriginal());
		Assert.assertEquals(existingOpencpsDossier.getServerNo(),
			newOpencpsDossier.getServerNo());
		Assert.assertEquals(Time.getShortTimestamp(
				existingOpencpsDossier.getEndorsementDate()),
			Time.getShortTimestamp(newOpencpsDossier.getEndorsementDate()));
		Assert.assertEquals(existingOpencpsDossier.getLockState(),
			newOpencpsDossier.getLockState());
		Assert.assertEquals(existingOpencpsDossier.getOriginality(),
			newOpencpsDossier.getOriginality());
		Assert.assertEquals(existingOpencpsDossier.getOriginDossierId(),
			newOpencpsDossier.getOriginDossierId());
		Assert.assertEquals(existingOpencpsDossier.getSampleCount(),
			newOpencpsDossier.getSampleCount());
		AssertUtils.assertEquals(existingOpencpsDossier.getDurationUnit(),
			newOpencpsDossier.getDurationUnit());
		AssertUtils.assertEquals(existingOpencpsDossier.getDurationCount(),
			newOpencpsDossier.getDurationCount());
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
	public void testCountByG_() throws Exception {
		_persistence.countByG_(RandomTestUtil.nextLong());

		_persistence.countByG_(0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		OpencpsDossier newOpencpsDossier = addOpencpsDossier();

		OpencpsDossier existingOpencpsDossier = _persistence.findByPrimaryKey(newOpencpsDossier.getPrimaryKey());

		Assert.assertEquals(existingOpencpsDossier, newOpencpsDossier);
	}

	@Test(expected = NoSuchOpencpsDossierException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<OpencpsDossier> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("opencps_dossier", "uuid",
			true, "dossierId", true, "groupId", true, "companyId", true,
			"userId", true, "userName", true, "createDate", true,
			"modifiedDate", true, "referenceUid", true, "counter", true,
			"registerBookCode", true, "registerBookName", true,
			"dossierRegister", true, "processNo", true, "serviceCode", true,
			"serviceName", true, "govAgencyCode", true, "govAgencyName", true,
			"applicantName", true, "applicantIdType", true, "applicantIdNo",
			true, "applicantIdDate", true, "address", true, "cityCode", true,
			"cityName", true, "districtCode", true, "districtName", true,
			"wardCode", true, "wardName", true, "contactName", true,
			"contactTelNo", true, "contactEmail", true, "delegateName", true,
			"delegateIdNo", true, "delegateTelNo", true, "delegateEmail", true,
			"delegateAddress", true, "delegateCityCode", true,
			"delegateCityName", true, "delegateDistrictCode", true,
			"delegateDistrictName", true, "delegateWardCode", true,
			"delegateWardName", true, "dossierTemplateNo", true,
			"dossierTemplateName", true, "dossierNote", true, "submissionNote",
			true, "applicantNote", true, "briefNote", true, "dossierNo", true,
			"submitting", true, "processDate", true, "submitDate", true,
			"receiveDate", true, "dueDate", true, "extendDate", true,
			"releaseDate", true, "finishDate", true, "cancellingDate", true,
			"correcttingDate", true, "dossierStatus", true,
			"dossierStatusText", true, "dossierSubStatus", true,
			"dossierSubStatusText", true, "folderId", true, "dossierActionId",
			true, "viaPostal", true, "postalServiceCode", true,
			"postalServiceName", true, "postalAddress", true, "postalCityCode",
			true, "postalCityName", true, "postalDistrictCode", true,
			"postalDistrictName", true, "postalWardCode", true,
			"postalWardName", true, "postalTelNo", true, "password", true,
			"notification", true, "online", true, "original", true, "serverNo",
			true, "endorsementDate", true, "lockState", true, "originality",
			true, "originDossierId", true, "sampleCount", true, "durationUnit",
			true, "durationCount", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		OpencpsDossier newOpencpsDossier = addOpencpsDossier();

		OpencpsDossier existingOpencpsDossier = _persistence.fetchByPrimaryKey(newOpencpsDossier.getPrimaryKey());

		Assert.assertEquals(existingOpencpsDossier, newOpencpsDossier);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		OpencpsDossier missingOpencpsDossier = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingOpencpsDossier);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		OpencpsDossier newOpencpsDossier1 = addOpencpsDossier();
		OpencpsDossier newOpencpsDossier2 = addOpencpsDossier();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newOpencpsDossier1.getPrimaryKey());
		primaryKeys.add(newOpencpsDossier2.getPrimaryKey());

		Map<Serializable, OpencpsDossier> opencpsDossiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, opencpsDossiers.size());
		Assert.assertEquals(newOpencpsDossier1,
			opencpsDossiers.get(newOpencpsDossier1.getPrimaryKey()));
		Assert.assertEquals(newOpencpsDossier2,
			opencpsDossiers.get(newOpencpsDossier2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, OpencpsDossier> opencpsDossiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(opencpsDossiers.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		OpencpsDossier newOpencpsDossier = addOpencpsDossier();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newOpencpsDossier.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, OpencpsDossier> opencpsDossiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, opencpsDossiers.size());
		Assert.assertEquals(newOpencpsDossier,
			opencpsDossiers.get(newOpencpsDossier.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, OpencpsDossier> opencpsDossiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(opencpsDossiers.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		OpencpsDossier newOpencpsDossier = addOpencpsDossier();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newOpencpsDossier.getPrimaryKey());

		Map<Serializable, OpencpsDossier> opencpsDossiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, opencpsDossiers.size());
		Assert.assertEquals(newOpencpsDossier,
			opencpsDossiers.get(newOpencpsDossier.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = OpencpsDossierLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<OpencpsDossier>() {
				@Override
				public void performAction(OpencpsDossier opencpsDossier) {
					Assert.assertNotNull(opencpsDossier);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		OpencpsDossier newOpencpsDossier = addOpencpsDossier();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(OpencpsDossier.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("dossierId",
				newOpencpsDossier.getDossierId()));

		List<OpencpsDossier> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		OpencpsDossier existingOpencpsDossier = result.get(0);

		Assert.assertEquals(existingOpencpsDossier, newOpencpsDossier);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(OpencpsDossier.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("dossierId",
				RandomTestUtil.nextLong()));

		List<OpencpsDossier> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		OpencpsDossier newOpencpsDossier = addOpencpsDossier();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(OpencpsDossier.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("dossierId"));

		Object newDossierId = newOpencpsDossier.getDossierId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("dossierId",
				new Object[] { newDossierId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingDossierId = result.get(0);

		Assert.assertEquals(existingDossierId, newDossierId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(OpencpsDossier.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("dossierId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("dossierId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		OpencpsDossier newOpencpsDossier = addOpencpsDossier();

		_persistence.clearCache();

		OpencpsDossier existingOpencpsDossier = _persistence.findByPrimaryKey(newOpencpsDossier.getPrimaryKey());

		Assert.assertTrue(Objects.equals(existingOpencpsDossier.getUuid(),
				ReflectionTestUtil.invoke(existingOpencpsDossier,
					"getOriginalUuid", new Class<?>[0])));
		Assert.assertEquals(Long.valueOf(existingOpencpsDossier.getGroupId()),
			ReflectionTestUtil.<Long>invoke(existingOpencpsDossier,
				"getOriginalGroupId", new Class<?>[0]));
	}

	protected OpencpsDossier addOpencpsDossier() throws Exception {
		long pk = RandomTestUtil.nextLong();

		OpencpsDossier opencpsDossier = _persistence.create(pk);

		opencpsDossier.setUuid(RandomTestUtil.randomString());

		opencpsDossier.setGroupId(RandomTestUtil.nextLong());

		opencpsDossier.setCompanyId(RandomTestUtil.nextLong());

		opencpsDossier.setUserId(RandomTestUtil.nextLong());

		opencpsDossier.setUserName(RandomTestUtil.randomString());

		opencpsDossier.setCreateDate(RandomTestUtil.nextDate());

		opencpsDossier.setModifiedDate(RandomTestUtil.nextDate());

		opencpsDossier.setReferenceUid(RandomTestUtil.randomString());

		opencpsDossier.setCounter(RandomTestUtil.nextInt());

		opencpsDossier.setRegisterBookCode(RandomTestUtil.randomString());

		opencpsDossier.setRegisterBookName(RandomTestUtil.randomString());

		opencpsDossier.setDossierRegister(RandomTestUtil.randomString());

		opencpsDossier.setProcessNo(RandomTestUtil.randomString());

		opencpsDossier.setServiceCode(RandomTestUtil.randomString());

		opencpsDossier.setServiceName(RandomTestUtil.randomString());

		opencpsDossier.setGovAgencyCode(RandomTestUtil.randomString());

		opencpsDossier.setGovAgencyName(RandomTestUtil.randomString());

		opencpsDossier.setApplicantName(RandomTestUtil.randomString());

		opencpsDossier.setApplicantIdType(RandomTestUtil.randomString());

		opencpsDossier.setApplicantIdNo(RandomTestUtil.randomString());

		opencpsDossier.setApplicantIdDate(RandomTestUtil.nextDate());

		opencpsDossier.setAddress(RandomTestUtil.randomString());

		opencpsDossier.setCityCode(RandomTestUtil.randomString());

		opencpsDossier.setCityName(RandomTestUtil.randomString());

		opencpsDossier.setDistrictCode(RandomTestUtil.randomString());

		opencpsDossier.setDistrictName(RandomTestUtil.randomString());

		opencpsDossier.setWardCode(RandomTestUtil.randomString());

		opencpsDossier.setWardName(RandomTestUtil.randomString());

		opencpsDossier.setContactName(RandomTestUtil.randomString());

		opencpsDossier.setContactTelNo(RandomTestUtil.randomString());

		opencpsDossier.setContactEmail(RandomTestUtil.randomString());

		opencpsDossier.setDelegateName(RandomTestUtil.randomString());

		opencpsDossier.setDelegateIdNo(RandomTestUtil.randomString());

		opencpsDossier.setDelegateTelNo(RandomTestUtil.randomString());

		opencpsDossier.setDelegateEmail(RandomTestUtil.randomString());

		opencpsDossier.setDelegateAddress(RandomTestUtil.randomString());

		opencpsDossier.setDelegateCityCode(RandomTestUtil.randomString());

		opencpsDossier.setDelegateCityName(RandomTestUtil.randomString());

		opencpsDossier.setDelegateDistrictCode(RandomTestUtil.randomString());

		opencpsDossier.setDelegateDistrictName(RandomTestUtil.randomString());

		opencpsDossier.setDelegateWardCode(RandomTestUtil.randomString());

		opencpsDossier.setDelegateWardName(RandomTestUtil.randomString());

		opencpsDossier.setDossierTemplateNo(RandomTestUtil.randomString());

		opencpsDossier.setDossierTemplateName(RandomTestUtil.randomString());

		opencpsDossier.setDossierNote(RandomTestUtil.randomString());

		opencpsDossier.setSubmissionNote(RandomTestUtil.randomString());

		opencpsDossier.setApplicantNote(RandomTestUtil.randomString());

		opencpsDossier.setBriefNote(RandomTestUtil.randomString());

		opencpsDossier.setDossierNo(RandomTestUtil.randomString());

		opencpsDossier.setSubmitting(RandomTestUtil.randomBoolean());

		opencpsDossier.setProcessDate(RandomTestUtil.nextDate());

		opencpsDossier.setSubmitDate(RandomTestUtil.nextDate());

		opencpsDossier.setReceiveDate(RandomTestUtil.nextDate());

		opencpsDossier.setDueDate(RandomTestUtil.nextDate());

		opencpsDossier.setExtendDate(RandomTestUtil.nextDate());

		opencpsDossier.setReleaseDate(RandomTestUtil.nextDate());

		opencpsDossier.setFinishDate(RandomTestUtil.nextDate());

		opencpsDossier.setCancellingDate(RandomTestUtil.nextDate());

		opencpsDossier.setCorrecttingDate(RandomTestUtil.nextDate());

		opencpsDossier.setDossierStatus(RandomTestUtil.randomString());

		opencpsDossier.setDossierStatusText(RandomTestUtil.randomString());

		opencpsDossier.setDossierSubStatus(RandomTestUtil.randomString());

		opencpsDossier.setDossierSubStatusText(RandomTestUtil.randomString());

		opencpsDossier.setFolderId(RandomTestUtil.nextLong());

		opencpsDossier.setDossierActionId(RandomTestUtil.nextLong());

		opencpsDossier.setViaPostal(RandomTestUtil.nextInt());

		opencpsDossier.setPostalServiceCode(RandomTestUtil.randomString());

		opencpsDossier.setPostalServiceName(RandomTestUtil.randomString());

		opencpsDossier.setPostalAddress(RandomTestUtil.randomString());

		opencpsDossier.setPostalCityCode(RandomTestUtil.randomString());

		opencpsDossier.setPostalCityName(RandomTestUtil.randomString());

		opencpsDossier.setPostalDistrictCode(RandomTestUtil.randomString());

		opencpsDossier.setPostalDistrictName(RandomTestUtil.randomString());

		opencpsDossier.setPostalWardCode(RandomTestUtil.randomString());

		opencpsDossier.setPostalWardName(RandomTestUtil.randomString());

		opencpsDossier.setPostalTelNo(RandomTestUtil.randomString());

		opencpsDossier.setPassword(RandomTestUtil.randomString());

		opencpsDossier.setNotification(RandomTestUtil.randomBoolean());

		opencpsDossier.setOnline(RandomTestUtil.randomBoolean());

		opencpsDossier.setOriginal(RandomTestUtil.randomBoolean());

		opencpsDossier.setServerNo(RandomTestUtil.randomString());

		opencpsDossier.setEndorsementDate(RandomTestUtil.nextDate());

		opencpsDossier.setLockState(RandomTestUtil.randomString());

		opencpsDossier.setOriginality(RandomTestUtil.nextInt());

		opencpsDossier.setOriginDossierId(RandomTestUtil.nextLong());

		opencpsDossier.setSampleCount(RandomTestUtil.nextLong());

		opencpsDossier.setDurationUnit(RandomTestUtil.nextDouble());

		opencpsDossier.setDurationCount(RandomTestUtil.nextDouble());

		_opencpsDossiers.add(_persistence.update(opencpsDossier));

		return opencpsDossier;
	}

	private List<OpencpsDossier> _opencpsDossiers = new ArrayList<OpencpsDossier>();
	private OpencpsDossierPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}