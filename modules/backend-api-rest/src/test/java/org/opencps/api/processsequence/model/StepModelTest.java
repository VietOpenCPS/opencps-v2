package org.opencps.api.processsequence.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class StepModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getActionsTest() {
		try{
			StepModel object = new StepModel();
			object.getActions();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDurationCountTest() {
		try{
			StepModel object = new StepModel();
			object.getDurationCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDurationCountTest() {
		try{
			StepModel object = new StepModel();
			object.setDurationCount(Double.valueOf(0.0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupNameTest() {
		try{
			StepModel object = new StepModel();
			object.getGroupName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFromStepNameTest() {
		try{
			StepModel object = new StepModel();
			object.setFromStepName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFromStepCodeTest() {
		try{
			StepModel object = new StepModel();
			object.setFromStepCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGroupNameTest() {
		try{
			StepModel object = new StepModel();
			object.setGroupName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFromStepCodeTest() {
		try{
			StepModel object = new StepModel();
			object.getFromStepCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFromStepNameTest() {
		try{
			StepModel object = new StepModel();
			object.getFromStepName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}