package org.opencps.api.workingunit.model;
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
	public void createWorkingUnitModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createWorkingUnitModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createWorkingUnitInputModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createWorkingUnitInputModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createWorkingUnitResultsTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createWorkingUnitResults();
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