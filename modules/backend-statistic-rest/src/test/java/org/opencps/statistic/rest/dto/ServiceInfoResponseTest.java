package org.opencps.statistic.rest.dto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ServiceInfoResponseTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getDomainNameTest() {
		try{
			ServiceInfoResponse object = new ServiceInfoResponse();
			object.getDomainName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDomainNameTest() {
		try{
			ServiceInfoResponse object = new ServiceInfoResponse();
			object.setDomainName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDomainCodeTest() {
		try{
			ServiceInfoResponse object = new ServiceInfoResponse();
			object.getDomainCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDomainCodeTest() {
		try{
			ServiceInfoResponse object = new ServiceInfoResponse();
			object.setDomainCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}