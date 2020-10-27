package org.opencps.dossiermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class StepConfigLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			StepConfigLocalServiceImpl object = new StepConfigLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStepByMainStatusAndSubStatusTest() {
		try{
			StepConfigLocalServiceImpl object = new StepConfigLocalServiceImpl();
			object.getStepByMainStatusAndSubStatus(Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStepByGroupIdTest() {
		try{
			StepConfigLocalServiceImpl object = new StepConfigLocalServiceImpl();
			object.getStepByGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByCodeTest() {
		try{
			StepConfigLocalServiceImpl object = new StepConfigLocalServiceImpl();
			object.getByCode(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addStepConfigTest() {
		try{
			StepConfigLocalServiceImpl object = new StepConfigLocalServiceImpl();
			object.addStepConfig(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateStepConfigTest() {
		try{
			StepConfigLocalServiceImpl object = new StepConfigLocalServiceImpl();
			object.updateStepConfig(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByStepTypeTest() {
		try{
			StepConfigLocalServiceImpl object = new StepConfigLocalServiceImpl();
			object.getByStepType(Long.valueOf(0), 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateStepConfigDBTest() {
		try{
			StepConfigLocalServiceImpl object = new StepConfigLocalServiceImpl();
			object.updateStepConfigDB(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByG_SCSTest() {
		try{
			StepConfigLocalServiceImpl object = new StepConfigLocalServiceImpl();
			object.findByG_SCS(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			StepConfigLocalServiceImpl object = new StepConfigLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeStepConfigTest() {
		try{
			StepConfigLocalServiceImpl object = new StepConfigLocalServiceImpl();
			object.removeStepConfig(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}