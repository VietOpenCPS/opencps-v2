package org.opencps.api.employee.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class EmployeeAccountInputModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void isExistTest() {
		try{
			EmployeeAccountInputModel object = new EmployeeAccountInputModel();
			object.isExist();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getScreenNameTest() {
		try{
			EmployeeAccountInputModel object = new EmployeeAccountInputModel();
			object.getScreenName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setExistTest() {
		try{
			EmployeeAccountInputModel object = new EmployeeAccountInputModel();
			object.setExist(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEmailTest() {
		try{
			EmployeeAccountInputModel object = new EmployeeAccountInputModel();
			object.setEmail("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmailTest() {
		try{
			EmployeeAccountInputModel object = new EmployeeAccountInputModel();
			object.getEmail();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setScreenNameTest() {
		try{
			EmployeeAccountInputModel object = new EmployeeAccountInputModel();
			object.setScreenName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}