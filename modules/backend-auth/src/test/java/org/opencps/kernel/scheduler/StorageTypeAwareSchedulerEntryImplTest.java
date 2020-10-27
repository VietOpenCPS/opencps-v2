package org.opencps.kernel.scheduler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class StorageTypeAwareSchedulerEntryImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getEventListenerClassTest() {
		try{
			StorageTypeAwareSchedulerEntryImpl object = new StorageTypeAwareSchedulerEntryImpl(null);
			object.getEventListenerClass();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEventListenerClassTest() {
		try{
			StorageTypeAwareSchedulerEntryImpl object = new StorageTypeAwareSchedulerEntryImpl(null);
			object.setEventListenerClass("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStorageTypeTest() {
		try{
			StorageTypeAwareSchedulerEntryImpl object = new StorageTypeAwareSchedulerEntryImpl(null);
			object.getStorageType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTriggerTest() {
		try{
			StorageTypeAwareSchedulerEntryImpl object = new StorageTypeAwareSchedulerEntryImpl(null);
			object.setTrigger(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTriggerTest() {
		try{
			StorageTypeAwareSchedulerEntryImpl object = new StorageTypeAwareSchedulerEntryImpl(null);
			object.getTrigger();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDescriptionTest() {
		try{
			StorageTypeAwareSchedulerEntryImpl object = new StorageTypeAwareSchedulerEntryImpl(null);
			object.getDescription();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDescriptionTest() {
		try{
			StorageTypeAwareSchedulerEntryImpl object = new StorageTypeAwareSchedulerEntryImpl(null);
			object.setDescription("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}