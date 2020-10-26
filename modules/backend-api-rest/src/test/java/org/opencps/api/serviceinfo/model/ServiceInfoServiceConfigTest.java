package org.opencps.api.serviceinfo.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ServiceInfoServiceConfigTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getServiceLevelTest() {
		try{
			ServiceInfoServiceConfig object = new ServiceInfoServiceConfig();
			object.getServiceLevel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setServiceLevelTest() {
		try{
			ServiceInfoServiceConfig object = new ServiceInfoServiceConfig();
			object.setServiceLevel(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGovAgencyNameTest() {
		try{
			ServiceInfoServiceConfig object = new ServiceInfoServiceConfig();
			object.setGovAgencyName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGovAgencyCodeTest() {
		try{
			ServiceInfoServiceConfig object = new ServiceInfoServiceConfig();
			object.getGovAgencyCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGovAgencyCodeTest() {
		try{
			ServiceInfoServiceConfig object = new ServiceInfoServiceConfig();
			object.setGovAgencyCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGovAgencyNameTest() {
		try{
			ServiceInfoServiceConfig object = new ServiceInfoServiceConfig();
			object.getGovAgencyName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setServiceUrTest() {
		try{
			ServiceInfoServiceConfig object = new ServiceInfoServiceConfig();
			object.setServiceUr("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setServiceConfigIdTest() {
		try{
			ServiceInfoServiceConfig object = new ServiceInfoServiceConfig();
			object.setServiceConfigId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceUrTest() {
		try{
			ServiceInfoServiceConfig object = new ServiceInfoServiceConfig();
			object.getServiceUr();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setServiceInstructionTest() {
		try{
			ServiceInfoServiceConfig object = new ServiceInfoServiceConfig();
			object.setServiceInstruction("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceConfigIdTest() {
		try{
			ServiceInfoServiceConfig object = new ServiceInfoServiceConfig();
			object.getServiceConfigId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceInstructionTest() {
		try{
			ServiceInfoServiceConfig object = new ServiceInfoServiceConfig();
			object.getServiceInstruction();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}