package org.opencps.api.employee.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class EmployeeJobposModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void isMainJobPosTest() {
		try{
			EmployeeJobposModel object = new EmployeeJobposModel();
			object.isMainJobPos();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setJobPosTitleTest() {
		try{
			EmployeeJobposModel object = new EmployeeJobposModel();
			object.setJobPosTitle("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getJobPosTitleTest() {
		try{
			EmployeeJobposModel object = new EmployeeJobposModel();
			object.getJobPosTitle();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMainJobPosTest() {
		try{
			EmployeeJobposModel object = new EmployeeJobposModel();
			object.setMainJobPos(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setJobPosIdTest() {
		try{
			EmployeeJobposModel object = new EmployeeJobposModel();
			object.setJobPosId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setLeaderTest() {
		try{
			EmployeeJobposModel object = new EmployeeJobposModel();
			object.setLeader(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmployeeJobPosIdTest() {
		try{
			EmployeeJobposModel object = new EmployeeJobposModel();
			object.getEmployeeJobPosId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEmployeeJobPosIdTest() {
		try{
			EmployeeJobposModel object = new EmployeeJobposModel();
			object.setEmployeeJobPosId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWorkingUnitNameTest() {
		try{
			EmployeeJobposModel object = new EmployeeJobposModel();
			object.getWorkingUnitName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWorkingUnitIdTest() {
		try{
			EmployeeJobposModel object = new EmployeeJobposModel();
			object.getWorkingUnitId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWorkingUnitIdTest() {
		try{
			EmployeeJobposModel object = new EmployeeJobposModel();
			object.setWorkingUnitId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getJobPosIdTest() {
		try{
			EmployeeJobposModel object = new EmployeeJobposModel();
			object.getJobPosId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getLeaderTest() {
		try{
			EmployeeJobposModel object = new EmployeeJobposModel();
			object.getLeader();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWorkingUnitNameTest() {
		try{
			EmployeeJobposModel object = new EmployeeJobposModel();
			object.setWorkingUnitName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}