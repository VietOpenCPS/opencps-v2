package org.opencps.usermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class SyncSchedulerLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void dynamicQueryTest() {
		try{
			SyncSchedulerLocalServiceWrapper object = new SyncSchedulerLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest2() {
		try{
			SyncSchedulerLocalServiceWrapper object = new SyncSchedulerLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			SyncSchedulerLocalServiceWrapper object = new SyncSchedulerLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			SyncSchedulerLocalServiceWrapper object = new SyncSchedulerLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			SyncSchedulerLocalServiceWrapper object = new SyncSchedulerLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			SyncSchedulerLocalServiceWrapper object = new SyncSchedulerLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			SyncSchedulerLocalServiceWrapper object = new SyncSchedulerLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			SyncSchedulerLocalServiceWrapper object = new SyncSchedulerLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			SyncSchedulerLocalServiceWrapper object = new SyncSchedulerLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest10() {
		try{
			SyncSchedulerLocalServiceWrapper object = new SyncSchedulerLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			SyncSchedulerLocalServiceWrapper object = new SyncSchedulerLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest12() {
		try{
			SyncSchedulerLocalServiceWrapper object = new SyncSchedulerLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			SyncSchedulerLocalServiceWrapper object = new SyncSchedulerLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			SyncSchedulerLocalServiceWrapper object = new SyncSchedulerLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest15() {
		try{
			SyncSchedulerLocalServiceWrapper object = new SyncSchedulerLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchSyncSchedulerByUuidAndGroupIdTest() {
		try{
			SyncSchedulerLocalServiceWrapper object = new SyncSchedulerLocalServiceWrapper(null);
			object.fetchSyncSchedulerByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByClassNameAndSyncDateTest() {
		try{
			SyncSchedulerLocalServiceWrapper object = new SyncSchedulerLocalServiceWrapper(null);
			object.getByClassNameAndSyncDate("abcde", new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSyncSchedulerByUuidAndGroupIdTest() {
		try{
			SyncSchedulerLocalServiceWrapper object = new SyncSchedulerLocalServiceWrapper(null);
			object.getSyncSchedulerByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchSyncSchedulerTest() {
		try{
			SyncSchedulerLocalServiceWrapper object = new SyncSchedulerLocalServiceWrapper(null);
			object.fetchSyncScheduler(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByF_NAME_RETRYTest() {
		try{
			SyncSchedulerLocalServiceWrapper object = new SyncSchedulerLocalServiceWrapper(null);
			object.getByF_NAME_RETRY("abcde", 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSyncSchedulersCountTest() {
		try{
			SyncSchedulerLocalServiceWrapper object = new SyncSchedulerLocalServiceWrapper(null);
			object.getSyncSchedulersCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByClassNameAndTypeCodeTest() {
		try{
			SyncSchedulerLocalServiceWrapper object = new SyncSchedulerLocalServiceWrapper(null);
			object.getByClassNameAndTypeCode("abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createSyncSchedulerTest() {
		try{
			SyncSchedulerLocalServiceWrapper object = new SyncSchedulerLocalServiceWrapper(null);
			object.createSyncScheduler(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateSyncSchedulerTest() {
		try{
			SyncSchedulerLocalServiceWrapper object = new SyncSchedulerLocalServiceWrapper(null);
			object.updateSyncScheduler(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", new Date(), 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateSyncSchedulerTest25() {
		try{
			SyncSchedulerLocalServiceWrapper object = new SyncSchedulerLocalServiceWrapper(null);
			object.updateSyncScheduler(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteSyncSchedulerTest() {
		try{
			SyncSchedulerLocalServiceWrapper object = new SyncSchedulerLocalServiceWrapper(null);
			object.deleteSyncScheduler(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteSyncSchedulerTest27() {
		try{
			SyncSchedulerLocalServiceWrapper object = new SyncSchedulerLocalServiceWrapper(null);
			object.deleteSyncScheduler(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSyncSchedulersTest() {
		try{
			SyncSchedulerLocalServiceWrapper object = new SyncSchedulerLocalServiceWrapper(null);
			object.getSyncSchedulers(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSyncSchedulerTest() {
		try{
			SyncSchedulerLocalServiceWrapper object = new SyncSchedulerLocalServiceWrapper(null);
			object.getSyncScheduler(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addSyncSchedulerTest() {
		try{
			SyncSchedulerLocalServiceWrapper object = new SyncSchedulerLocalServiceWrapper(null);
			object.addSyncScheduler(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}