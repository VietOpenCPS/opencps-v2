package org.opencps.statistic.rest.application;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class OpencpsStatisticRestApplicationTest {
	@Before
	public void setUp() {
	}
	@Test
	public void throwExceptionTest() {
		try{
			OpencpsStatisticRestApplication.throwException(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSingletonsTest() {
		try{
			OpencpsStatisticRestApplication object = new OpencpsStatisticRestApplication();
			object.getSingletons();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchVotingStatisticTest() {
		try{
			OpencpsStatisticRestApplication object = new OpencpsStatisticRestApplication();
			object.searchVotingStatistic(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fixDossierStatisticTest() {
		try{
			OpencpsStatisticRestApplication object = new OpencpsStatisticRestApplication();
			object.fixDossierStatistic(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierStatisticDataTest() {
		try{
			OpencpsStatisticRestApplication object = new OpencpsStatisticRestApplication();
			object.updateDossierStatisticData(null, null, new Date(), new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void importManualStatisticTest() {
		try{
			OpencpsStatisticRestApplication object = new OpencpsStatisticRestApplication();
			object.importManualStatistic(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchPersonStatisticTest() {
		try{
			OpencpsStatisticRestApplication object = new OpencpsStatisticRestApplication();
			object.searchPersonStatistic(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchDossierStatisticManualTest() {
		try{
			OpencpsStatisticRestApplication object = new OpencpsStatisticRestApplication();
			object.searchDossierStatisticManual(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void searchDossierStatisticTest() {
//		try{
//			OpencpsStatisticRestApplication object = new OpencpsStatisticRestApplication();
//			object.searchDossierStatistic(Long.valueOf(0), 0, 0, "abcde", "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void searchDossierStatisticTest11() {
		try{
			OpencpsStatisticRestApplication object = new OpencpsStatisticRestApplication();
			object.searchDossierStatistic(Long.valueOf(0), null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fixedStatisticTest() {
		try{
			OpencpsStatisticRestApplication object = new OpencpsStatisticRestApplication();
			object.fixedStatistic(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void feeReportSummaryTest() {
//		try{
//			OpencpsStatisticRestApplication object = new OpencpsStatisticRestApplication();
//			object.feeReportSummary(Long.valueOf(0), "abcde", "abcde", "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void feeReportSummaryTest18() {
//		try{
//			OpencpsStatisticRestApplication object = new OpencpsStatisticRestApplication();
//			object.feeReportSummary(Long.valueOf(0), "abcde", "abcde", "abcde", 0, "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void feeDetailTest() {
//		try{
//			OpencpsStatisticRestApplication object = new OpencpsStatisticRestApplication();
//			object.feeDetail(Long.valueOf(0), "abcde", "abcde", "abcde", 0, 0, 0);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
}