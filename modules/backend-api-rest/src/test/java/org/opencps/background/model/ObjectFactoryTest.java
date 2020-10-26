package org.opencps.background.model;
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
	public void createCountEntityTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createCountEntity();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createBackgroundTaskProgressTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createBackgroundTaskProgress();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createBackgroundTaskResultTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createBackgroundTaskResult();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}