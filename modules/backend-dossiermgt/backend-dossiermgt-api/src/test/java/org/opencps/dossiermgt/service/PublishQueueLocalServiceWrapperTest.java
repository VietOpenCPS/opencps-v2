package org.opencps.dossiermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class PublishQueueLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void dynamicQueryTest() {
		try{
			PublishQueueLocalServiceWrapper object = new PublishQueueLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest2() {
		try{
			PublishQueueLocalServiceWrapper object = new PublishQueueLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			PublishQueueLocalServiceWrapper object = new PublishQueueLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			PublishQueueLocalServiceWrapper object = new PublishQueueLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			PublishQueueLocalServiceWrapper object = new PublishQueueLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByStatusTest() {
		try{
			PublishQueueLocalServiceWrapper object = new PublishQueueLocalServiceWrapper(null);
			object.getByStatus(0, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByG_DID_SN_STTest() {
		try{
			PublishQueueLocalServiceWrapper object = new PublishQueueLocalServiceWrapper(null);
			object.getByG_DID_SN_ST(Long.valueOf(0), Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchPublishQueueByUuidAndGroupIdTest() {
		try{
			PublishQueueLocalServiceWrapper object = new PublishQueueLocalServiceWrapper(null);
			object.fetchPublishQueueByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updatePublishQueueTest() {
		try{
			PublishQueueLocalServiceWrapper object = new PublishQueueLocalServiceWrapper(null);
			object.updatePublishQueue(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updatePublishQueueTest10() {
		try{
			PublishQueueLocalServiceWrapper object = new PublishQueueLocalServiceWrapper(null);
			object.updatePublishQueue(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updatePublishQueueTest11() {
		try{
			PublishQueueLocalServiceWrapper object = new PublishQueueLocalServiceWrapper(null);
			object.updatePublishQueue(Long.valueOf(0), Long.valueOf(0), 0, Long.valueOf(0), "abcde", "abcde", 0, 0, "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			PublishQueueLocalServiceWrapper object = new PublishQueueLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			PublishQueueLocalServiceWrapper object = new PublishQueueLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			PublishQueueLocalServiceWrapper object = new PublishQueueLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			PublishQueueLocalServiceWrapper object = new PublishQueueLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest16() {
		try{
			PublishQueueLocalServiceWrapper object = new PublishQueueLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			PublishQueueLocalServiceWrapper object = new PublishQueueLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest18() {
		try{
			PublishQueueLocalServiceWrapper object = new PublishQueueLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			PublishQueueLocalServiceWrapper object = new PublishQueueLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			PublishQueueLocalServiceWrapper object = new PublishQueueLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest21() {
		try{
			PublishQueueLocalServiceWrapper object = new PublishQueueLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByST_LT_MDTest() {
		try{
			PublishQueueLocalServiceWrapper object = new PublishQueueLocalServiceWrapper(null);
			object.findByST_LT_MD(null, new Date(), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addPublishQueueTest() {
		try{
			PublishQueueLocalServiceWrapper object = new PublishQueueLocalServiceWrapper(null);
			object.addPublishQueue(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPublishQueuesTest() {
		try{
			PublishQueueLocalServiceWrapper object = new PublishQueueLocalServiceWrapper(null);
			object.getPublishQueues(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByStatusesTest() {
		try{
			PublishQueueLocalServiceWrapper object = new PublishQueueLocalServiceWrapper(null);
			object.getByStatuses(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByG_DID_SNTest() {
		try{
			PublishQueueLocalServiceWrapper object = new PublishQueueLocalServiceWrapper(null);
			object.getByG_DID_SN(Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPublishQueueTest() {
		try{
			PublishQueueLocalServiceWrapper object = new PublishQueueLocalServiceWrapper(null);
			object.getPublishQueue(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createPublishQueueTest() {
		try{
			PublishQueueLocalServiceWrapper object = new PublishQueueLocalServiceWrapper(null);
			object.createPublishQueue(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPublishQueuesCountTest() {
		try{
			PublishQueueLocalServiceWrapper object = new PublishQueueLocalServiceWrapper(null);
			object.getPublishQueuesCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePublishQueueTest() {
		try{
			PublishQueueLocalServiceWrapper object = new PublishQueueLocalServiceWrapper(null);
			object.deletePublishQueue(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePublishQueueTest31() {
		try{
			PublishQueueLocalServiceWrapper object = new PublishQueueLocalServiceWrapper(null);
			object.deletePublishQueue(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPublishQueueByUuidAndGroupIdTest() {
		try{
			PublishQueueLocalServiceWrapper object = new PublishQueueLocalServiceWrapper(null);
			object.getPublishQueueByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchPublishQueueTest() {
		try{
			PublishQueueLocalServiceWrapper object = new PublishQueueLocalServiceWrapper(null);
			object.fetchPublishQueue(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removePublishQueueTest() {
		try{
			PublishQueueLocalServiceWrapper object = new PublishQueueLocalServiceWrapper(null);
			object.removePublishQueue(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByG_DID_SN_NSTTest() {
		try{
			PublishQueueLocalServiceWrapper object = new PublishQueueLocalServiceWrapper(null);
			object.getByG_DID_SN_NST(Long.valueOf(0), Long.valueOf(0), "abcde", 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}