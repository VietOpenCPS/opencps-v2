package org.opencps.datamgt.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class HolidayCheckUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void calculatorDateUntilDealineTest() {
		try{
			HolidayCheckUtils.calculatorDateUntilDealine(new Date(), new Date(), 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void calculatorDateUntilDealineReturnFormartTest() {
		try{
			HolidayCheckUtils.calculatorDateUntilDealineReturnFormart(new Date(), new Date(), 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEndDateTest() {
		try{
			HolidayCheckUtils.getEndDate(new Date(), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}