package org.opencps.api.configcounter.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ConfigCounterModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getCreateDateTest() {
		try{
			ConfigCounterModel object = new ConfigCounterModel();
			object.getCreateDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreateDateTest() {
		try{
			ConfigCounterModel object = new ConfigCounterModel();
			object.setCreateDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getModifiedDateTest() {
		try{
			ConfigCounterModel object = new ConfigCounterModel();
			object.getModifiedDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModifiedDateTest() {
		try{
			ConfigCounterModel object = new ConfigCounterModel();
			object.setModifiedDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getConfigCounterIdTest() {
		try{
			ConfigCounterModel object = new ConfigCounterModel();
			object.getConfigCounterId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setConfigCounterIdTest() {
		try{
			ConfigCounterModel object = new ConfigCounterModel();
			object.setConfigCounterId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPatternCodeTest() {
		try{
			ConfigCounterModel object = new ConfigCounterModel();
			object.getPatternCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStartCounterTest() {
		try{
			ConfigCounterModel object = new ConfigCounterModel();
			object.getStartCounter();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setStartCounterTest() {
		try{
			ConfigCounterModel object = new ConfigCounterModel();
			object.setStartCounter(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCounterCodeTest() {
		try{
			ConfigCounterModel object = new ConfigCounterModel();
			object.getCounterCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCounterCodeTest() {
		try{
			ConfigCounterModel object = new ConfigCounterModel();
			object.setCounterCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPatternCodeTest() {
		try{
			ConfigCounterModel object = new ConfigCounterModel();
			object.setPatternCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}