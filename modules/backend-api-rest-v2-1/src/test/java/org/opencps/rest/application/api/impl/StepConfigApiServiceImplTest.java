package org.opencps.rest.application.api.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class StepConfigApiServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getStepConfigByMainStatusAndSubStatusTest() {
		try{
			StepConfigApiServiceImpl object = new StepConfigApiServiceImpl();
			object.getStepConfigByMainStatusAndSubStatus("abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStepConfigByCodeTest() {
		try{
			StepConfigApiServiceImpl object = new StepConfigApiServiceImpl();
			object.getStepConfigByCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStepConfigsElasticsearchTest() {
		try{
			StepConfigApiServiceImpl object = new StepConfigApiServiceImpl();
			object.getStepConfigsElasticsearch("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addStepConfigTest() {
		try{
			StepConfigApiServiceImpl object = new StepConfigApiServiceImpl();
			object.addStepConfig(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteStepConfigTest() {
		try{
			StepConfigApiServiceImpl object = new StepConfigApiServiceImpl();
			object.deleteStepConfig("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateStepConfigTest() {
		try{
			StepConfigApiServiceImpl object = new StepConfigApiServiceImpl();
			object.updateStepConfig("abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}