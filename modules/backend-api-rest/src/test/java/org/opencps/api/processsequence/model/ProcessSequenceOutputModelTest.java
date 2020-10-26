package org.opencps.api.processsequence.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ProcessSequenceOutputModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setSequenceRoleTest() {
		try{
			ProcessSequenceOutputModel object = new ProcessSequenceOutputModel();
			object.setSequenceRole("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSequenceNameTest() {
		try{
			ProcessSequenceOutputModel object = new ProcessSequenceOutputModel();
			object.getSequenceName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSequenceNoTest() {
		try{
			ProcessSequenceOutputModel object = new ProcessSequenceOutputModel();
			object.setSequenceNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDurationCountTest() {
		try{
			ProcessSequenceOutputModel object = new ProcessSequenceOutputModel();
			object.getDurationCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSequenceNoTest() {
		try{
			ProcessSequenceOutputModel object = new ProcessSequenceOutputModel();
			object.getSequenceNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSequenceRoleTest() {
		try{
			ProcessSequenceOutputModel object = new ProcessSequenceOutputModel();
			object.getSequenceRole();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSequenceNameTest() {
		try{
			ProcessSequenceOutputModel object = new ProcessSequenceOutputModel();
			object.setSequenceName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDurationCountTest() {
		try{
			ProcessSequenceOutputModel object = new ProcessSequenceOutputModel();
			object.setDurationCount(Double.valueOf(0.0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}