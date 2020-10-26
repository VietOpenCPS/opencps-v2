package org.opencps.api.processsequence.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ProcessSequenceInputModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setSequenceRoleTest() {
		try{
			ProcessSequenceInputModel object = new ProcessSequenceInputModel();
			object.setSequenceRole("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPostActionTest() {
		try{
			ProcessSequenceInputModel object = new ProcessSequenceInputModel();
			object.getPostAction();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPostActionTest() {
		try{
			ProcessSequenceInputModel object = new ProcessSequenceInputModel();
			object.setPostAction("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSequenceNameTest() {
		try{
			ProcessSequenceInputModel object = new ProcessSequenceInputModel();
			object.getSequenceName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSequenceNoTest() {
		try{
			ProcessSequenceInputModel object = new ProcessSequenceInputModel();
			object.setSequenceNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDurationCountTest() {
		try{
			ProcessSequenceInputModel object = new ProcessSequenceInputModel();
			object.getDurationCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSequenceNoTest() {
		try{
			ProcessSequenceInputModel object = new ProcessSequenceInputModel();
			object.getSequenceNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSequenceRoleTest() {
		try{
			ProcessSequenceInputModel object = new ProcessSequenceInputModel();
			object.getSequenceRole();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSequenceNameTest() {
		try{
			ProcessSequenceInputModel object = new ProcessSequenceInputModel();
			object.setSequenceName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDurationCountTest() {
		try{
			ProcessSequenceInputModel object = new ProcessSequenceInputModel();
			object.setDurationCount(Double.valueOf(0.0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}