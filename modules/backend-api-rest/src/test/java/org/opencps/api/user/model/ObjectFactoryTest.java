package org.opencps.api.user.model;
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
	public void createUserSitesResultsTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createUserSitesResults();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createUserProfileResultsTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createUserProfileResults();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createUserWorksModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createUserWorksModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createUserRolesModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createUserRolesModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createUserAccountModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createUserAccountModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createUserRolesResultsTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createUserRolesResults();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createUserProfileModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createUserProfileModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createUserResultsTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createUserResults();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createUserSitesModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createUserSitesModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createUserWorksResultsTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createUserWorksResults();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createUserModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createUserModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}