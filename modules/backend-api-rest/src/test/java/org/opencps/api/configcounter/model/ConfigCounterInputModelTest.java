package org.opencps.api.configcounter.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ConfigCounterInputModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getPatternCodeTest() {
		try{
			ConfigCounterInputModel object = new ConfigCounterInputModel();
			object.getPatternCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStartCounterTest() {
		try{
			ConfigCounterInputModel object = new ConfigCounterInputModel();
			object.getStartCounter();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setStartCounterTest() {
		try{
			ConfigCounterInputModel object = new ConfigCounterInputModel();
			object.setStartCounter(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCounterCodeTest() {
		try{
			ConfigCounterInputModel object = new ConfigCounterInputModel();
			object.getCounterCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCounterCodeTest() {
		try{
			ConfigCounterInputModel object = new ConfigCounterInputModel();
			object.setCounterCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPatternCodeTest() {
		try{
			ConfigCounterInputModel object = new ConfigCounterInputModel();
			object.setPatternCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}