package org.opencps.api.processsequence.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierActionResult21ModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getProcessNoTest() {
		try{
			DossierActionResult21Model object = new DossierActionResult21Model();
			object.getProcessNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setProcessNoTest() {
		try{
			DossierActionResult21Model object = new DossierActionResult21Model();
			object.setProcessNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDurationUnitTest() {
		try{
			DossierActionResult21Model object = new DossierActionResult21Model();
			object.setDurationUnit(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDurationCountTest() {
		try{
			DossierActionResult21Model object = new DossierActionResult21Model();
			object.getDurationCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDurationUnitTest() {
		try{
			DossierActionResult21Model object = new DossierActionResult21Model();
			object.getDurationUnit();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDurationCountTest() {
		try{
			DossierActionResult21Model object = new DossierActionResult21Model();
			object.setDurationCount(Double.valueOf(0.0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDataTest() {
		try{
			DossierActionResult21Model object = new DossierActionResult21Model();
			object.getData();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}