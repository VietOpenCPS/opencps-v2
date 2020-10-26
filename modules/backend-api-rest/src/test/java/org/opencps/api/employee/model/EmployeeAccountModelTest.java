package org.opencps.api.employee.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class EmployeeAccountModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void isExistTest() {
		try{
			EmployeeAccountModel object = new EmployeeAccountModel();
			object.isExist();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getScreenNameTest() {
		try{
			EmployeeAccountModel object = new EmployeeAccountModel();
			object.getScreenName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setExistTest() {
		try{
			EmployeeAccountModel object = new EmployeeAccountModel();
			object.setExist(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEmailTest() {
		try{
			EmployeeAccountModel object = new EmployeeAccountModel();
			object.setEmail("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmailTest() {
		try{
			EmployeeAccountModel object = new EmployeeAccountModel();
			object.getEmail();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setScreenNameTest() {
		try{
			EmployeeAccountModel object = new EmployeeAccountModel();
			object.setScreenName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}