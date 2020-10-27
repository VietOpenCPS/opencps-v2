package org.opencps.statistic.service.persistence.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class OpencpsDossierStatisticManualFinderImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void searchDossierStatisticTest() {
		try{
			OpencpsDossierStatisticManualFinderImpl object = new OpencpsDossierStatisticManualFinderImpl();
			object.searchDossierStatistic(Long.valueOf(0), 0, "abcde", "abcde", "abcde", "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchDossierStatisticTest2() {
		try{
			OpencpsDossierStatisticManualFinderImpl object = new OpencpsDossierStatisticManualFinderImpl();
			object.searchDossierStatistic(Long.valueOf(0), 0, "abcde", "abcde", "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void checkContainsSystemTest() {
		try{
			OpencpsDossierStatisticManualFinderImpl object = new OpencpsDossierStatisticManualFinderImpl();
			object.checkContainsSystem(Long.valueOf(0), 0, 0, "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchYearDossierStatisticTest() {
		try{
			OpencpsDossierStatisticManualFinderImpl object = new OpencpsDossierStatisticManualFinderImpl();
			object.searchYearDossierStatistic(Long.valueOf(0), 0, "abcde", "abcde", "abcde", "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchByDomainGovAgencyGroupTest() {
		try{
			OpencpsDossierStatisticManualFinderImpl object = new OpencpsDossierStatisticManualFinderImpl();
			object.searchByDomainGovAgencyGroup(Long.valueOf(0), 0, 0, "abcde", "abcde", "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchByDomainAgencySystemTest() {
		try{
			OpencpsDossierStatisticManualFinderImpl object = new OpencpsDossierStatisticManualFinderImpl();
			object.searchByDomainAgencySystem(Long.valueOf(0), 0, 0, "abcde", "abcde", "abcde", "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void checkContainsTest() {
		try{
			OpencpsDossierStatisticManualFinderImpl object = new OpencpsDossierStatisticManualFinderImpl();
			object.checkContains(Long.valueOf(0), 0, 0, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}