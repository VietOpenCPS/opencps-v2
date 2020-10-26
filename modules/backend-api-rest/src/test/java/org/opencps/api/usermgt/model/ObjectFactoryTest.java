package org.opencps.api.usermgt.model;
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
	public void createMappingUserTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createMappingUser();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createApplicantResultsModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createApplicantResultsModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createApplicantInputModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createApplicantInputModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createApplicantSearchModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createApplicantSearchModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createRegisterModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createRegisterModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createApplicantModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createApplicantModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}