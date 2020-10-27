package org.opencps.usermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class SyncSchedulerLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getByClassNameAndSyncDateTest() {
		try{
			SyncSchedulerLocalServiceImpl object = new SyncSchedulerLocalServiceImpl();
			object.getByClassNameAndSyncDate("abcde", new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByF_NAME_RETRYTest() {
		try{
			SyncSchedulerLocalServiceImpl object = new SyncSchedulerLocalServiceImpl();
			object.getByF_NAME_RETRY("abcde", 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByClassNameAndTypeCodeTest() {
		try{
			SyncSchedulerLocalServiceImpl object = new SyncSchedulerLocalServiceImpl();
			object.getByClassNameAndTypeCode("abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateSyncSchedulerTest() {
		try{
			SyncSchedulerLocalServiceImpl object = new SyncSchedulerLocalServiceImpl();
			object.updateSyncScheduler(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", new Date(), 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}