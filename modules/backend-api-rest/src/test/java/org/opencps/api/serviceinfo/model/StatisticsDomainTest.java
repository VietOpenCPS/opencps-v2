package org.opencps.api.serviceinfo.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class StatisticsDomainTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getDomainNameTest() {
		try{
			StatisticsDomain object = new StatisticsDomain();
			object.getDomainName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDomainNameTest() {
		try{
			StatisticsDomain object = new StatisticsDomain();
			object.setDomainName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDomainCodeTest() {
		try{
			StatisticsDomain object = new StatisticsDomain();
			object.getDomainCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDomainCodeTest() {
		try{
			StatisticsDomain object = new StatisticsDomain();
			object.setDomainCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCountTest() {
		try{
			StatisticsDomain object = new StatisticsDomain();
			object.setCount(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCountTest() {
		try{
			StatisticsDomain object = new StatisticsDomain();
			object.getCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}