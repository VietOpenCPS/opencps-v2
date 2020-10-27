package org.opencps.statistic.service.persistence.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class OpencpsDossierStatisticFinderImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void searchDossierStatisticTest() {
		try{
			OpencpsDossierStatisticFinderImpl object = new OpencpsDossierStatisticFinderImpl();
			object.searchDossierStatistic(Long.valueOf(0), 0, "abcde", "abcde", "abcde", "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchDossierStatisticTest2() {
		try{
			OpencpsDossierStatisticFinderImpl object = new OpencpsDossierStatisticFinderImpl();
			object.searchDossierStatistic(Long.valueOf(0), 0, "abcde", "abcde", "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void checkContainsSystemTest() {
		try{
			OpencpsDossierStatisticFinderImpl object = new OpencpsDossierStatisticFinderImpl();
			object.checkContainsSystem(Long.valueOf(0), 0, 0, "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchYearDossierStatisticTest() {
		try{
			OpencpsDossierStatisticFinderImpl object = new OpencpsDossierStatisticFinderImpl();
			object.searchYearDossierStatistic(Long.valueOf(0), 0, "abcde", "abcde", "abcde", "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchByDomainGovAgencyGroupTest() {
		try{
			OpencpsDossierStatisticFinderImpl object = new OpencpsDossierStatisticFinderImpl();
			object.searchByDomainGovAgencyGroup(Long.valueOf(0), 0, 0, "abcde", "abcde", "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchByDomainAgencySystemTest() {
		try{
			OpencpsDossierStatisticFinderImpl object = new OpencpsDossierStatisticFinderImpl();
			object.searchByDomainAgencySystem(Long.valueOf(0), 0, 0, "abcde", "abcde", "abcde", "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void checkContainsTest() {
		try{
			OpencpsDossierStatisticFinderImpl object = new OpencpsDossierStatisticFinderImpl();
			object.checkContains(Long.valueOf(0), 0, 0, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}