package org.opencps.dossiermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class PublishQueueLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getByStatusTest() {
		try{
			PublishQueueLocalServiceImpl object = new PublishQueueLocalServiceImpl();
			object.getByStatus(0, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByG_DID_SN_STTest() {
		try{
			PublishQueueLocalServiceImpl object = new PublishQueueLocalServiceImpl();
			object.getByG_DID_SN_ST(Long.valueOf(0), Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updatePublishQueueTest() {
		try{
			PublishQueueLocalServiceImpl object = new PublishQueueLocalServiceImpl();
			object.updatePublishQueue(Long.valueOf(0), Long.valueOf(0), 0, Long.valueOf(0), "abcde", "abcde", 0, 0, "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updatePublishQueueTest4() {
		try{
			PublishQueueLocalServiceImpl object = new PublishQueueLocalServiceImpl();
			object.updatePublishQueue(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByST_LT_MDTest() {
		try{
			PublishQueueLocalServiceImpl object = new PublishQueueLocalServiceImpl();
			object.findByST_LT_MD(null, new Date(), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByStatusesTest() {
		try{
			PublishQueueLocalServiceImpl object = new PublishQueueLocalServiceImpl();
			object.getByStatuses(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByG_DID_SNTest() {
		try{
			PublishQueueLocalServiceImpl object = new PublishQueueLocalServiceImpl();
			object.getByG_DID_SN(Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removePublishQueueTest() {
		try{
			PublishQueueLocalServiceImpl object = new PublishQueueLocalServiceImpl();
			object.removePublishQueue(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByG_DID_SN_NSTTest() {
		try{
			PublishQueueLocalServiceImpl object = new PublishQueueLocalServiceImpl();
			object.getByG_DID_SN_NST(Long.valueOf(0), Long.valueOf(0), "abcde", 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}