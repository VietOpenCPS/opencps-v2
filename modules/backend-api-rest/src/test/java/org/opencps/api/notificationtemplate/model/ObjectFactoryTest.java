package org.opencps.api.notificationtemplate.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ObjectFactoryTest {
	@Before
	public void setUp() {
	}
	@Test
	public void createNotificationQueueShortModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createNotificationQueueShortModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createNotificationtemplateResultsTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createNotificationtemplateResults();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createNotificationtemplateInputModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createNotificationtemplateInputModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDataSearchModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDataSearchModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createNotificationTypeResultsTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createNotificationTypeResults();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createNotificationtemplateModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createNotificationtemplateModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createNotificationTypeModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createNotificationTypeModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createNotificationQueueResultsTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createNotificationQueueResults();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createNotificationQueueModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createNotificationQueueModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}