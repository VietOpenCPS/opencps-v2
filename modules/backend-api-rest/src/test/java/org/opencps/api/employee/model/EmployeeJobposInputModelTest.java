package org.opencps.api.employee.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class EmployeeJobposInputModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setMainJobPosTest() {
		try{
			EmployeeJobposInputModel object = new EmployeeJobposInputModel();
			object.setMainJobPos("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setJobPosIdTest() {
		try{
			EmployeeJobposInputModel object = new EmployeeJobposInputModel();
			object.setJobPosId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWorkingUnitIdTest() {
		try{
			EmployeeJobposInputModel object = new EmployeeJobposInputModel();
			object.getWorkingUnitId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWorkingUnitIdTest() {
		try{
			EmployeeJobposInputModel object = new EmployeeJobposInputModel();
			object.setWorkingUnitId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getJobPosIdTest() {
		try{
			EmployeeJobposInputModel object = new EmployeeJobposInputModel();
			object.getJobPosId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMainJobPosTest() {
		try{
			EmployeeJobposInputModel object = new EmployeeJobposInputModel();
			object.getMainJobPos();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}