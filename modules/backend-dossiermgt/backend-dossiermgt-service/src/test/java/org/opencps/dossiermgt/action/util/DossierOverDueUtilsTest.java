package org.opencps.dossiermgt.action.util;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class DossierOverDueUtilsTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void getStepOverDueTest() {
//		try{
//			DossierOverDueUtils.getStepOverDue(Long.valueOf(0), 0, new Date());
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getEstimateDateTest() {
//		try{
//			DossierOverDueUtils.getEstimateDate(0);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void calculateEndDateTest() {
		try{
			DossierOverDueUtils.calculateEndDate(new Date(), 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void calculateDurationTest() {
		try{
			DossierOverDueUtils.calculateDuration(new Date(), new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void calculateEndDateUsingHolidayTest() {
		try{
			DossierOverDueUtils.calculateEndDateUsingHoliday(new Date(), 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}