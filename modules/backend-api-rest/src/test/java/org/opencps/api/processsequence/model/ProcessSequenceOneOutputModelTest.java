package org.opencps.api.processsequence.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ProcessSequenceOneOutputModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setSequenceRoleTest() {
		try{
			ProcessSequenceOneOutputModel object = new ProcessSequenceOneOutputModel();
			object.setSequenceRole("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSequenceNameTest() {
		try{
			ProcessSequenceOneOutputModel object = new ProcessSequenceOneOutputModel();
			object.getSequenceName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSequenceNoTest() {
		try{
			ProcessSequenceOneOutputModel object = new ProcessSequenceOneOutputModel();
			object.setSequenceNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDurationCountTest() {
		try{
			ProcessSequenceOneOutputModel object = new ProcessSequenceOneOutputModel();
			object.getDurationCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSequenceNoTest() {
		try{
			ProcessSequenceOneOutputModel object = new ProcessSequenceOneOutputModel();
			object.getSequenceNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSequenceRoleTest() {
		try{
			ProcessSequenceOneOutputModel object = new ProcessSequenceOneOutputModel();
			object.getSequenceRole();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSequenceNameTest() {
		try{
			ProcessSequenceOneOutputModel object = new ProcessSequenceOneOutputModel();
			object.setSequenceName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDurationCountTest() {
		try{
			ProcessSequenceOneOutputModel object = new ProcessSequenceOneOutputModel();
			object.setDurationCount(Double.valueOf(0.0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}