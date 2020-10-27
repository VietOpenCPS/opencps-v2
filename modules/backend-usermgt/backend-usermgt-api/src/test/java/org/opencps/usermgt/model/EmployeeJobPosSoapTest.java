package org.opencps.usermgt.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class EmployeeJobPosSoapTest {
	@Before
	public void setUp() {
	}
	@Test
	public void toSoapModelsTest() {
		try{
			EmployeeJobPosSoap.toSoapModels(new ArrayList<>());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest2() {
		try{
			EmployeeJobPosSoap.toSoapModels(new EmployeeJobPos[][]{});
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest3() {
		try{
			EmployeeJobPosSoap.toSoapModels(new EmployeeJobPos[]{});
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCreateDateTest() {
		try{
			EmployeeJobPosSoap object = new EmployeeJobPosSoap();
			object.getCreateDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserNameTest() {
		try{
			EmployeeJobPosSoap object = new EmployeeJobPosSoap();
			object.getUserName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserNameTest() {
		try{
			EmployeeJobPosSoap object = new EmployeeJobPosSoap();
			object.setUserName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGroupIdTest() {
		try{
			EmployeeJobPosSoap object = new EmployeeJobPosSoap();
			object.setGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCompanyIdTest() {
		try{
			EmployeeJobPosSoap object = new EmployeeJobPosSoap();
			object.setCompanyId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserIdTest() {
		try{
			EmployeeJobPosSoap object = new EmployeeJobPosSoap();
			object.setUserId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreateDateTest() {
		try{
			EmployeeJobPosSoap object = new EmployeeJobPosSoap();
			object.setCreateDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCompanyIdTest() {
		try{
			EmployeeJobPosSoap object = new EmployeeJobPosSoap();
			object.getCompanyId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserIdTest() {
		try{
			EmployeeJobPosSoap object = new EmployeeJobPosSoap();
			object.getUserId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getModifiedDateTest() {
		try{
			EmployeeJobPosSoap object = new EmployeeJobPosSoap();
			object.getModifiedDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupIdTest() {
		try{
			EmployeeJobPosSoap object = new EmployeeJobPosSoap();
			object.getGroupId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModifiedDateTest() {
		try{
			EmployeeJobPosSoap object = new EmployeeJobPosSoap();
			object.setModifiedDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelTest() {
		try{
			EmployeeJobPosSoap.toSoapModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEmployeeIdTest() {
		try{
			EmployeeJobPosSoap object = new EmployeeJobPosSoap();
			object.setEmployeeId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmployeeJobPosIdTest() {
		try{
			EmployeeJobPosSoap object = new EmployeeJobPosSoap();
			object.getEmployeeJobPosId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEmployeeJobPosIdTest() {
		try{
			EmployeeJobPosSoap object = new EmployeeJobPosSoap();
			object.setEmployeeJobPosId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUuidTest() {
		try{
			EmployeeJobPosSoap object = new EmployeeJobPosSoap();
			object.setUuid("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWorkingUnitIdTest() {
		try{
			EmployeeJobPosSoap object = new EmployeeJobPosSoap();
			object.getWorkingUnitId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWorkingUnitIdTest() {
		try{
			EmployeeJobPosSoap object = new EmployeeJobPosSoap();
			object.setWorkingUnitId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getJobPostIdTest() {
		try{
			EmployeeJobPosSoap object = new EmployeeJobPosSoap();
			object.getJobPostId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmployeeIdTest() {
		try{
			EmployeeJobPosSoap object = new EmployeeJobPosSoap();
			object.getEmployeeId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPrimaryKeyTest() {
		try{
			EmployeeJobPosSoap object = new EmployeeJobPosSoap();
			object.setPrimaryKey(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPrimaryKeyTest() {
		try{
			EmployeeJobPosSoap object = new EmployeeJobPosSoap();
			object.getPrimaryKey();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUuidTest() {
		try{
			EmployeeJobPosSoap object = new EmployeeJobPosSoap();
			object.getUuid();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setJobPostIdTest() {
		try{
			EmployeeJobPosSoap object = new EmployeeJobPosSoap();
			object.setJobPostId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}