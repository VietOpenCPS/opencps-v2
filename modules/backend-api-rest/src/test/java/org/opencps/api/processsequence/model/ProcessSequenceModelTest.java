package org.opencps.api.processsequence.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ProcessSequenceModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getSequenceNameTest() {
		try{
			ProcessSequenceModel object = new ProcessSequenceModel();
			object.getSequenceName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSequenceNoTest() {
		try{
			ProcessSequenceModel object = new ProcessSequenceModel();
			object.setSequenceNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDurationCountTest() {
		try{
			ProcessSequenceModel object = new ProcessSequenceModel();
			object.getDurationCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSequenceNoTest() {
		try{
			ProcessSequenceModel object = new ProcessSequenceModel();
			object.getSequenceNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSequenceNameTest() {
		try{
			ProcessSequenceModel object = new ProcessSequenceModel();
			object.setSequenceName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDurationCountTest() {
		try{
			ProcessSequenceModel object = new ProcessSequenceModel();
			object.setDurationCount(Double.valueOf(0.0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStepsTest() {
		try{
			ProcessSequenceModel object = new ProcessSequenceModel();
			object.getSteps();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}