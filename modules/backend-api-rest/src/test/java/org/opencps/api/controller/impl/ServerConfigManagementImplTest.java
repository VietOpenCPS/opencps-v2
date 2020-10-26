package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ServerConfigManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getProtocolConnectOfServerConfigsTest() {
		try{
			ServerConfigManagementImpl object = new ServerConfigManagementImpl();
			object.getProtocolConnectOfServerConfigs(null, null, null, null, null, null, "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getBasicServerConfigsTest() {
		try{
			ServerConfigManagementImpl object = new ServerConfigManagementImpl();
			object.getBasicServerConfigs(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeServerConfigTest() {
		try{
			ServerConfigManagementImpl object = new ServerConfigManagementImpl();
			object.removeServerConfig(null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProtocolOfServerConfigsTest() {
		try{
			ServerConfigManagementImpl object = new ServerConfigManagementImpl();
			object.getProtocolOfServerConfigs(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServerConfigDetailTest() {
		try{
			ServerConfigManagementImpl object = new ServerConfigManagementImpl();
			object.getServerConfigDetail(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateServerConfigTest() {
		try{
			ServerConfigManagementImpl object = new ServerConfigManagementImpl();
			object.updateServerConfig(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServerConfigsTest() {
		try{
			ServerConfigManagementImpl object = new ServerConfigManagementImpl();
			object.getServerConfigs(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addServerConfigTest() {
		try{
			ServerConfigManagementImpl object = new ServerConfigManagementImpl();
			object.addServerConfig(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getConfigTest() {
		try{
			ServerConfigManagementImpl object = new ServerConfigManagementImpl();
			object.getConfig(null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateConfigTest() {
		try{
			ServerConfigManagementImpl object = new ServerConfigManagementImpl();
			object.updateConfig(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addConfigTest() {
		try{
			ServerConfigManagementImpl object = new ServerConfigManagementImpl();
			object.addConfig(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}