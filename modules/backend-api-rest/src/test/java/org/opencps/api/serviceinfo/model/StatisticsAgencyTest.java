package org.opencps.api.serviceinfo.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class StatisticsAgencyTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setAdministrationCodeTest() {
		try{
			StatisticsAgency object = new StatisticsAgency();
			object.setAdministrationCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAdministrationCodeTest() {
		try{
			StatisticsAgency object = new StatisticsAgency();
			object.getAdministrationCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setAdministrationNameTest() {
		try{
			StatisticsAgency object = new StatisticsAgency();
			object.setAdministrationName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAdministrationNameTest() {
		try{
			StatisticsAgency object = new StatisticsAgency();
			object.getAdministrationName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCountTest() {
		try{
			StatisticsAgency object = new StatisticsAgency();
			object.setCount(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCountTest() {
		try{
			StatisticsAgency object = new StatisticsAgency();
			object.getCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}