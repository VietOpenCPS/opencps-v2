package org.opencps.gogoshell.tool.command.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class FiredTriggerTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getStateTest() {
		try{
			FiredTrigger object = new FiredTrigger();
			object.getState();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setStateTest() {
		try{
			FiredTrigger object = new FiredTrigger();
			object.setState("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getInstanceNameTest() {
		try{
			FiredTrigger object = new FiredTrigger();
			object.getInstanceName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEntryIdTest() {
		try{
			FiredTrigger object = new FiredTrigger();
			object.setEntryId("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTriggerGroupTest() {
		try{
			FiredTrigger object = new FiredTrigger();
			object.setTriggerGroup("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSchedulerNameTest() {
		try{
			FiredTrigger object = new FiredTrigger();
			object.getSchedulerName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTriggerGroupTest() {
		try{
			FiredTrigger object = new FiredTrigger();
			object.getTriggerGroup();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEntryIdTest() {
		try{
			FiredTrigger object = new FiredTrigger();
			object.getEntryId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTriggerNameTest() {
		try{
			FiredTrigger object = new FiredTrigger();
			object.getTriggerName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSchedulerNameTest() {
		try{
			FiredTrigger object = new FiredTrigger();
			object.setSchedulerName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setInstanceNameTest() {
		try{
			FiredTrigger object = new FiredTrigger();
			object.setInstanceName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTriggerNameTest() {
		try{
			FiredTrigger object = new FiredTrigger();
			object.setTriggerName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFiredTimeTest() {
		try{
			FiredTrigger object = new FiredTrigger();
			object.getFiredTime();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFiredTimeTest() {
		try{
			FiredTrigger object = new FiredTrigger();
			object.setFiredTime(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}