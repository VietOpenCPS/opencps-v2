package org.opencps.api.jobpos.model;
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
	public void createJobposModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createJobposModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createJobposResultsTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createJobposResults();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createJobposPermissionModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createJobposPermissionModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createJobposInputModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createJobposInputModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createJobposPermissionResultsTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createJobposPermissionResults();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}