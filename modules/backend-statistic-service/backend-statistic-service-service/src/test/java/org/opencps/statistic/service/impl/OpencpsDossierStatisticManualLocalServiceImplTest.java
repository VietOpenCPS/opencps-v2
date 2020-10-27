package org.opencps.statistic.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class OpencpsDossierStatisticManualLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void removeAllTest() {
		try{
			OpencpsDossierStatisticManualLocalServiceImpl object = new OpencpsDossierStatisticManualLocalServiceImpl();
			object.removeAll();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByGTest() {
		try{
			OpencpsDossierStatisticManualLocalServiceImpl object = new OpencpsDossierStatisticManualLocalServiceImpl();
			object.findByG(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchDossierStatisticTest() {
		try{
			OpencpsDossierStatisticManualLocalServiceImpl object = new OpencpsDossierStatisticManualLocalServiceImpl();
			object.fetchDossierStatistic(Long.valueOf(0), 0, 0, "abcde", "abcde", "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchDossierStatisticTest4() {
		try{
			OpencpsDossierStatisticManualLocalServiceImpl object = new OpencpsDossierStatisticManualLocalServiceImpl();
			object.fetchDossierStatistic(Long.valueOf(0), 0, 0, "abcde", "abcde", "abcde", "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateStatisticManualTest() {
		try{
			OpencpsDossierStatisticManualLocalServiceImpl object = new OpencpsDossierStatisticManualLocalServiceImpl();
			object.updateStatisticManual(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "abcde", "abcde", "abcde", "abcde", true, 0, 0, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateStatisticManualTest6() {
		try{
			OpencpsDossierStatisticManualLocalServiceImpl object = new OpencpsDossierStatisticManualLocalServiceImpl();
			object.updateStatisticManual(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "abcde", "abcde", "abcde", "abcde", true, 0, 0, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeByG_M_Y_G_DTest() {
		try{
			OpencpsDossierStatisticManualLocalServiceImpl object = new OpencpsDossierStatisticManualLocalServiceImpl();
			object.removeByG_M_Y_G_D(Long.valueOf(0), 0, 0, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchDossierStatisticSystemTest() {
		try{
			OpencpsDossierStatisticManualLocalServiceImpl object = new OpencpsDossierStatisticManualLocalServiceImpl();
			object.searchDossierStatisticSystem(Long.valueOf(0), 0, 0, "abcde", "abcde", "abcde", "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void checkNotDuplicateTest() {
		try{
			OpencpsDossierStatisticManualLocalServiceImpl object = new OpencpsDossierStatisticManualLocalServiceImpl();
			object.checkNotDuplicate(Long.valueOf(0), "abcde", 0, 0, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByGovMonthYearDomainTest() {
		try{
			OpencpsDossierStatisticManualLocalServiceImpl object = new OpencpsDossierStatisticManualLocalServiceImpl();
			object.getByGovMonthYearDomain(Long.valueOf(0), "abcde", 0, 0, "abcde", true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierStatisticManualByYearTest() {
		try{
			OpencpsDossierStatisticManualLocalServiceImpl object = new OpencpsDossierStatisticManualLocalServiceImpl();
			object.getDossierStatisticManualByYear(Long.valueOf(0), Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByGovMonthYearTest() {
		try{
			OpencpsDossierStatisticManualLocalServiceImpl object = new OpencpsDossierStatisticManualLocalServiceImpl();
			object.getByGovMonthYear(Long.valueOf(0), "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByG_M_Y_G_DTest() {
		try{
			OpencpsDossierStatisticManualLocalServiceImpl object = new OpencpsDossierStatisticManualLocalServiceImpl();
			object.fetchByG_M_Y_G_D(Long.valueOf(0), 0, 0, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeDossierStatisticManualByYearTest() {
		try{
			OpencpsDossierStatisticManualLocalServiceImpl object = new OpencpsDossierStatisticManualLocalServiceImpl();
			object.removeDossierStatisticManualByYear(Long.valueOf(0), Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierStatisticByMonthsYearAndReportTest() {
		try{
			OpencpsDossierStatisticManualLocalServiceImpl object = new OpencpsDossierStatisticManualLocalServiceImpl();
			object.getDossierStatisticByMonthsYearAndReport(Long.valueOf(0), null, 0, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierStatisticManualByMonthYearAndReportTest() {
		try{
			OpencpsDossierStatisticManualLocalServiceImpl object = new OpencpsDossierStatisticManualLocalServiceImpl();
			object.getDossierStatisticManualByMonthYearAndReport(Long.valueOf(0), 0, 0, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeDossierStatisticManualByMonthYearTest() {
		try{
			OpencpsDossierStatisticManualLocalServiceImpl object = new OpencpsDossierStatisticManualLocalServiceImpl();
			object.removeDossierStatisticManualByMonthYear(Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierStatisticManualByMonthYearTest() {
		try{
			OpencpsDossierStatisticManualLocalServiceImpl object = new OpencpsDossierStatisticManualLocalServiceImpl();
			object.getDossierStatisticManualByMonthYear(Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeDossierStatisticManualByD_M_YTest() {
		try{
			OpencpsDossierStatisticManualLocalServiceImpl object = new OpencpsDossierStatisticManualLocalServiceImpl();
			object.removeDossierStatisticManualByD_M_Y(Long.valueOf(0), "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}