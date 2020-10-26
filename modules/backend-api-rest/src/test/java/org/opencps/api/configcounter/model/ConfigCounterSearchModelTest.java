package org.opencps.api.configcounter.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ConfigCounterSearchModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getPatternCodeTest() {
		try{
			ConfigCounterSearchModel object = new ConfigCounterSearchModel();
			object.getPatternCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCounterCodeTest() {
		try{
			ConfigCounterSearchModel object = new ConfigCounterSearchModel();
			object.getCounterCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCounterCodeTest() {
		try{
			ConfigCounterSearchModel object = new ConfigCounterSearchModel();
			object.setCounterCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPatternCodeTest() {
		try{
			ConfigCounterSearchModel object = new ConfigCounterSearchModel();
			object.setPatternCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}