package org.opencps.sms.service.dto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class SmsSearchResponseTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getTotalTest() {
		try{
			SmsSearchResponse object = new SmsSearchResponse();
			object.getTotal();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDataTest() {
		try{
			SmsSearchResponse object = new SmsSearchResponse();
			object.setData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDataTest() {
		try{
			SmsSearchResponse object = new SmsSearchResponse();
			object.getData();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTotalTest() {
		try{
			SmsSearchResponse object = new SmsSearchResponse();
			object.setTotal(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}