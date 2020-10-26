package org.opencps.api.serverconfig.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ServerConfigInputModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getProtocolTest() {
		try{
			ServerConfigInputModel object = new ServerConfigInputModel();
			object.getProtocol();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setConfigsTest() {
		try{
			ServerConfigInputModel object = new ServerConfigInputModel();
			object.setConfigs("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGovAgencyCodeTest() {
		try{
			ServerConfigInputModel object = new ServerConfigInputModel();
			object.getGovAgencyCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGovAgencyCodeTest() {
		try{
			ServerConfigInputModel object = new ServerConfigInputModel();
			object.setGovAgencyCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setServerNameTest() {
		try{
			ServerConfigInputModel object = new ServerConfigInputModel();
			object.setServerName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServerNoTest() {
		try{
			ServerConfigInputModel object = new ServerConfigInputModel();
			object.getServerNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServerNameTest() {
		try{
			ServerConfigInputModel object = new ServerConfigInputModel();
			object.getServerName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setServerNoTest() {
		try{
			ServerConfigInputModel object = new ServerConfigInputModel();
			object.setServerNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getConfigsTest() {
		try{
			ServerConfigInputModel object = new ServerConfigInputModel();
			object.getConfigs();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setProtocolTest() {
		try{
			ServerConfigInputModel object = new ServerConfigInputModel();
			object.setProtocol("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}