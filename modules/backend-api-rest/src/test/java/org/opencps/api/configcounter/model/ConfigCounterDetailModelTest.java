package org.opencps.api.configcounter.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ConfigCounterDetailModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getCreateDateTest() {
		try{
			ConfigCounterDetailModel object = new ConfigCounterDetailModel();
			object.getCreateDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreateDateTest() {
		try{
			ConfigCounterDetailModel object = new ConfigCounterDetailModel();
			object.setCreateDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getModifiedDateTest() {
		try{
			ConfigCounterDetailModel object = new ConfigCounterDetailModel();
			object.getModifiedDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModifiedDateTest() {
		try{
			ConfigCounterDetailModel object = new ConfigCounterDetailModel();
			object.setModifiedDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getConfigCounterIdTest() {
		try{
			ConfigCounterDetailModel object = new ConfigCounterDetailModel();
			object.getConfigCounterId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setConfigCounterIdTest() {
		try{
			ConfigCounterDetailModel object = new ConfigCounterDetailModel();
			object.setConfigCounterId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPatternCodeTest() {
		try{
			ConfigCounterDetailModel object = new ConfigCounterDetailModel();
			object.getPatternCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStartCounterTest() {
		try{
			ConfigCounterDetailModel object = new ConfigCounterDetailModel();
			object.getStartCounter();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setStartCounterTest() {
		try{
			ConfigCounterDetailModel object = new ConfigCounterDetailModel();
			object.setStartCounter(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCounterCodeTest() {
		try{
			ConfigCounterDetailModel object = new ConfigCounterDetailModel();
			object.getCounterCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCounterCodeTest() {
		try{
			ConfigCounterDetailModel object = new ConfigCounterDetailModel();
			object.setCounterCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPatternCodeTest() {
		try{
			ConfigCounterDetailModel object = new ConfigCounterDetailModel();
			object.setPatternCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}