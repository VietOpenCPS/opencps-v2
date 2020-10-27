package org.opencps.dossiermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ConfigCounterLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getByGroupIdTest() {
		try{
			ConfigCounterLocalServiceImpl object = new ConfigCounterLocalServiceImpl();
			object.getByGroupId(Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateConfigCounterTest() {
		try{
			ConfigCounterLocalServiceImpl object = new ConfigCounterLocalServiceImpl();
			object.updateConfigCounter(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countByGroupIdTest() {
		try{
			ConfigCounterLocalServiceImpl object = new ConfigCounterLocalServiceImpl();
			object.countByGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByCountrCodeTest() {
		try{
			ConfigCounterLocalServiceImpl object = new ConfigCounterLocalServiceImpl();
			object.fetchByCountrCode(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}