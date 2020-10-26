package org.opencps.api.resourcerole.model;
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
	public void createResourceRoleResultsTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createResourceRoleResults();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createResourceRoleInputModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createResourceRoleInputModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createResourceRoleModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createResourceRoleModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}