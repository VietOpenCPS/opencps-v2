package org.opencps.statistic.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class OpencpsDossierStatisticLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void findByGTest() {
		try{
			OpencpsDossierStatisticLocalServiceImpl object = new OpencpsDossierStatisticLocalServiceImpl();
			object.findByG(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchDossierStatisticTest() {
		try{
			OpencpsDossierStatisticLocalServiceImpl object = new OpencpsDossierStatisticLocalServiceImpl();
			object.fetchDossierStatistic(Long.valueOf(0), 0, 0, "abcde", "abcde", "abcde", "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchDossierStatisticTest3() {
		try{
			OpencpsDossierStatisticLocalServiceImpl object = new OpencpsDossierStatisticLocalServiceImpl();
			object.fetchDossierStatistic(Long.valueOf(0), 0, 0, "abcde", "abcde", "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateStatisticDataTest() {
		try{
			OpencpsDossierStatisticLocalServiceImpl object = new OpencpsDossierStatisticLocalServiceImpl();
			object.updateStatisticData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void createOrUpdateStatisticTest() {
//		try{
//			OpencpsDossierStatisticLocalServiceImpl object = new OpencpsDossierStatisticLocalServiceImpl();
//			object.createOrUpdateStatistic(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", 0, 0, "abcde", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "abcde", "abcde", "abcde", "abcde", true, 0, 0, 0, 0);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void createOrUpdateStatisticTest6() {
//		try{
//			OpencpsDossierStatisticLocalServiceImpl object = new OpencpsDossierStatisticLocalServiceImpl();
//			object.createOrUpdateStatistic(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", 0, 0, "abcde", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "abcde", "abcde", "abcde", "abcde", true, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void createOrUpdateStatisticTest7() {
//		try{
//			OpencpsDossierStatisticLocalServiceImpl object = new OpencpsDossierStatisticLocalServiceImpl();
//			object.createOrUpdateStatistic(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", 0, 0, "abcde", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "abcde", "abcde", "abcde", "abcde", "abcde", true, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void getDossierStatisticByMonthYearTest() {
		try{
			OpencpsDossierStatisticLocalServiceImpl object = new OpencpsDossierStatisticLocalServiceImpl();
			object.getDossierStatisticByMonthYear(Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeByG_M_Y_G_DTest() {
		try{
			OpencpsDossierStatisticLocalServiceImpl object = new OpencpsDossierStatisticLocalServiceImpl();
			object.removeByG_M_Y_G_D(Long.valueOf(0), 0, 0, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateOnlyStatisticTest() {
		try{
			OpencpsDossierStatisticLocalServiceImpl object = new OpencpsDossierStatisticLocalServiceImpl();
			object.updateOnlyStatistic(null, Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "abcde", "abcde", "abcde", "abcde", true, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateOnlyStatisticTest11() {
		try{
			OpencpsDossierStatisticLocalServiceImpl object = new OpencpsDossierStatisticLocalServiceImpl();
			object.updateOnlyStatistic(null, Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "abcde", "abcde", "abcde", "abcde", true, 0, 0, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeDossierStatisticByD_M_YTest() {
		try{
			OpencpsDossierStatisticLocalServiceImpl object = new OpencpsDossierStatisticLocalServiceImpl();
			object.removeDossierStatisticByD_M_Y(Long.valueOf(0), "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierStatisticByYearTest() {
		try{
			OpencpsDossierStatisticLocalServiceImpl object = new OpencpsDossierStatisticLocalServiceImpl();
			object.getDossierStatisticByYear(Long.valueOf(0), Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateBatchStatisticTest() {
		try{
			OpencpsDossierStatisticLocalServiceImpl object = new OpencpsDossierStatisticLocalServiceImpl();
			object.updateBatchStatistic(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createOnlyStatisticTest() {
		try{
			OpencpsDossierStatisticLocalServiceImpl object = new OpencpsDossierStatisticLocalServiceImpl();
			object.createOnlyStatistic(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "abcde", "abcde", "abcde", "abcde", true, 0, 0, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createOnlyStatisticTest16() {
		try{
			OpencpsDossierStatisticLocalServiceImpl object = new OpencpsDossierStatisticLocalServiceImpl();
			object.createOnlyStatistic(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "abcde", "abcde", "abcde", "abcde", true, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeDossierStatisticByYearTest() {
		try{
			OpencpsDossierStatisticLocalServiceImpl object = new OpencpsDossierStatisticLocalServiceImpl();
			object.removeDossierStatisticByYear(Long.valueOf(0), Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchDossierStatisticSystemTest() {
		try{
			OpencpsDossierStatisticLocalServiceImpl object = new OpencpsDossierStatisticLocalServiceImpl();
			object.searchDossierStatisticSystem(Long.valueOf(0), 0, 0, "abcde", "abcde", "abcde", "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void checkNotDuplicateTest() {
		try{
			OpencpsDossierStatisticLocalServiceImpl object = new OpencpsDossierStatisticLocalServiceImpl();
			object.checkNotDuplicate(Long.valueOf(0), "abcde", 0, 0, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByGovMonthYearDomainTest() {
		try{
			OpencpsDossierStatisticLocalServiceImpl object = new OpencpsDossierStatisticLocalServiceImpl();
			object.getByGovMonthYearDomain(Long.valueOf(0), "abcde", 0, 0, "abcde", true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByGovMonthYearTest() {
		try{
			OpencpsDossierStatisticLocalServiceImpl object = new OpencpsDossierStatisticLocalServiceImpl();
			object.getByGovMonthYear(Long.valueOf(0), "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateStatisticTest() {
		try{
			OpencpsDossierStatisticLocalServiceImpl object = new OpencpsDossierStatisticLocalServiceImpl();
			object.updateStatistic(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "abcde", "abcde", "abcde", "abcde", true, 0, 0, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateStatisticTest23() {
		try{
			OpencpsDossierStatisticLocalServiceImpl object = new OpencpsDossierStatisticLocalServiceImpl();
			object.updateStatistic(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "abcde", "abcde", "abcde", "abcde", true, 0, 0, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByG_NM_YTest() {
		try{
			OpencpsDossierStatisticLocalServiceImpl object = new OpencpsDossierStatisticLocalServiceImpl();
			object.findByG_NM_Y(Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void checkExsitTest() {
		try{
			OpencpsDossierStatisticLocalServiceImpl object = new OpencpsDossierStatisticLocalServiceImpl();
			object.checkExsit(Long.valueOf(0), 0, 0, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByG_M_Y_G_DTest() {
		try{
			OpencpsDossierStatisticLocalServiceImpl object = new OpencpsDossierStatisticLocalServiceImpl();
			object.fetchByG_M_Y_G_D(Long.valueOf(0), 0, 0, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void checkExsitSystemTest() {
		try{
			OpencpsDossierStatisticLocalServiceImpl object = new OpencpsDossierStatisticLocalServiceImpl();
			object.checkExsitSystem(Long.valueOf(0), 0, 0, "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierStatisticByMonthYearAndReportTest() {
		try{
			OpencpsDossierStatisticLocalServiceImpl object = new OpencpsDossierStatisticLocalServiceImpl();
			object.getDossierStatisticByMonthYearAndReport(Long.valueOf(0), 0, 0, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeDossierStatisticByMonthYearTest() {
		try{
			OpencpsDossierStatisticLocalServiceImpl object = new OpencpsDossierStatisticLocalServiceImpl();
			object.removeDossierStatisticByMonthYear(Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierStatisticByMonthsYearAndReportTest() {
		try{
			OpencpsDossierStatisticLocalServiceImpl object = new OpencpsDossierStatisticLocalServiceImpl();
			object.getDossierStatisticByMonthsYearAndReport(Long.valueOf(0), null, 0, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}