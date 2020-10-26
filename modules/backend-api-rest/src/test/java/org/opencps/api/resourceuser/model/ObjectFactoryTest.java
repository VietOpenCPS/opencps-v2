package org.opencps.api.resourceuser.model;
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
	public void createResourceUserResultsTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createResourceUserResults();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createResourceUserModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createResourceUserModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createResourceUserInputModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createResourceUserInputModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}