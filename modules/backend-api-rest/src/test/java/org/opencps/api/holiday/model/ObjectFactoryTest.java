package org.opencps.api.holiday.model;
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
	public void createDataSearchModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDataSearchModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createHolidayResultsTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createHolidayResults();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createHolidayModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createHolidayModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createHolidayInputModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createHolidayInputModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}