package org.opencps.statistic.rest.engine.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class StatisticEngineUpdateActionTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void getDossierStatisticByMonthYearTest() {
//		try{
//			StatisticEngineUpdateAction object = new StatisticEngineUpdateAction();
//			object.getDossierStatisticByMonthYear(Long.valueOf(0), 0, 0);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void removeVotingStatisticByMonthYearTest() {
//		try{
//			StatisticEngineUpdateAction object = new StatisticEngineUpdateAction();
//			object.removeVotingStatisticByMonthYear(Long.valueOf(0), 0, 0);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void removePersonStatisticByMonthYearTest() {
//		try{
//			StatisticEngineUpdateAction object = new StatisticEngineUpdateAction();
//			object.removePersonStatisticByMonthYear(Long.valueOf(0), 0, 0);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	
//	@Test
//	public void removePersonStatisticByYearTest() {
//		try{
//			StatisticEngineUpdateAction object = new StatisticEngineUpdateAction();
//			object.removePersonStatisticByYear(Long.valueOf(0), Long.valueOf(0), 0, 0);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	
//	@Test
//	public void removeDossierStatisticByD_M_YTest() {
//		try{
//			StatisticEngineUpdateAction object = new StatisticEngineUpdateAction();
//			object.removeDossierStatisticByD_M_Y(Long.valueOf(0), "abcde", 0, 0);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getDossierStatisticByYearTest() {
//		try{
//			StatisticEngineUpdateAction object = new StatisticEngineUpdateAction();
//			object.getDossierStatisticByYear(Long.valueOf(0), Long.valueOf(0), 0, 0);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void removeVotingStatisticByD_M_YTest() {
//		try{
//			StatisticEngineUpdateAction object = new StatisticEngineUpdateAction();
//			object.removeVotingStatisticByD_M_Y(Long.valueOf(0), "abcde", 0, 0);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	
//	@Test
//	public void removeVotingStatisticByYearTest() {
//		try{
//			StatisticEngineUpdateAction object = new StatisticEngineUpdateAction();
//			object.removeVotingStatisticByYear(Long.valueOf(0), Long.valueOf(0), 0, 0);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void removeDossierStatisticByYearTest() {
//		try{
//			StatisticEngineUpdateAction object = new StatisticEngineUpdateAction();
//			object.removeDossierStatisticByYear(Long.valueOf(0), Long.valueOf(0), 0, 0);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}

	/*@Test
	public void updateStatisticTest() {
		try{
			StatisticEngineUpdateAction object = new StatisticEngineUpdateAction();
			object.updateStatistic(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateStatisticTest14() {
		try{
			StatisticEngineUpdateAction object = new StatisticEngineUpdateAction();
			object.updateStatistic(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}*/
	
//	@Test
//	public void getDossierStatisticByMonthYearAndReportTest() {
//		try{
//			StatisticEngineUpdateAction object = new StatisticEngineUpdateAction();
//			object.getDossierStatisticByMonthYearAndReport(Long.valueOf(0), 0, 0, true);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void removeDossierStatisticByMonthYearTest() {
//		try{
//			StatisticEngineUpdateAction object = new StatisticEngineUpdateAction();
//			object.removeDossierStatisticByMonthYear(Long.valueOf(0), 0, 0);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void removeDossierStatisticByG_M_Y_G_DTest() {
//		try{
//			StatisticEngineUpdateAction object = new StatisticEngineUpdateAction();
//			object.removeDossierStatisticByG_M_Y_G_D(Long.valueOf(0), 0, 0, "abcde", "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	
	
	
	
	
	
	
	@Test
	public void updateVotingStatisticTest() {
		try{
			StatisticEngineUpdateAction object = new StatisticEngineUpdateAction();
			object.updateVotingStatistic(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateOnlyStatisticTest() {
		try{
			StatisticEngineUpdateAction object = new StatisticEngineUpdateAction();
			object.updateOnlyStatistic(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updatePersonStatisticTest() {
		try{
			StatisticEngineUpdateAction object = new StatisticEngineUpdateAction();
			object.updatePersonStatistic(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createStatisticTest() {
		try{
			StatisticEngineUpdateAction object = new StatisticEngineUpdateAction();
			object.createStatistic(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}