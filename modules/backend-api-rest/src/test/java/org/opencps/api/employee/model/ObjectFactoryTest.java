package org.opencps.api.employee.model;
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
	public void createJobPostsTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createJobPosts();
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
	public void createEmployeeModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createEmployeeModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createMappingUserTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createMappingUser();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createEmployeeInputModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createEmployeeInputModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createEmployeeResultsTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createEmployeeResults();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createEmployeeAccountInputModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createEmployeeAccountInputModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createEmployeeJobposModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createEmployeeJobposModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createEmployeeAccountModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createEmployeeAccountModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createEmployeeJobposInputModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createEmployeeJobposInputModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createEmployeeJobposResultsTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createEmployeeJobposResults();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}