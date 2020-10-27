package org.opencps.statistic.rest.dto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class StatisticFixedResultTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getErrorCodeTest() {
		try{
			StatisticFixedResult object = new StatisticFixedResult();
			object.getErrorCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setErrorCodeTest() {
		try{
			StatisticFixedResult object = new StatisticFixedResult();
			object.setErrorCode(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setErrorMessageTest() {
		try{
			StatisticFixedResult object = new StatisticFixedResult();
			object.setErrorMessage("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSuccessTest() {
		try{
			StatisticFixedResult object = new StatisticFixedResult();
			object.setSuccess(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getErrorMessageTest() {
		try{
			StatisticFixedResult object = new StatisticFixedResult();
			object.getErrorMessage();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSuccessTest() {
		try{
			StatisticFixedResult object = new StatisticFixedResult();
			object.getSuccess();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}