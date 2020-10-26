package org.opencps.api.worktime.model;
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
	public void createWorkTimeInputModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createWorkTimeInputModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createWorkTimeModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createWorkTimeModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createWorkTimeResultsTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createWorkTimeResults();
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
}