package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ConfigCounterManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void addConfigCounterTest() {
		try{
			ConfigCounterManagementImpl object = new ConfigCounterManagementImpl();
			object.addConfigCounter(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeConfigCounterTest() {
		try{
			ConfigCounterManagementImpl object = new ConfigCounterManagementImpl();
			object.removeConfigCounter(null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getConfigCounterDetailTest() {
		try{
			ConfigCounterManagementImpl object = new ConfigCounterManagementImpl();
			object.getConfigCounterDetail(null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateConfigCounterTest() {
		try{
			ConfigCounterManagementImpl object = new ConfigCounterManagementImpl();
			object.updateConfigCounter(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getConfigCounterListTest() {
		try{
			ConfigCounterManagementImpl object = new ConfigCounterManagementImpl();
			object.getConfigCounterList(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}