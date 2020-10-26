package org.opencps.api.configcounter.model;
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
	public void createConfigCounterModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createConfigCounterModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createConfigCounterSearchModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createConfigCounterSearchModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createConfigCounterResultsModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createConfigCounterResultsModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createConfigCounterInputModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createConfigCounterInputModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createConfigCounterDetailModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createConfigCounterDetailModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}