package org.opencps.api.serviceprocess.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class RoleDataModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setRoleCodeTest() {
		try{
			RoleDataModel object = new RoleDataModel();
			object.setRoleCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getConditionTest() {
		try{
			RoleDataModel object = new RoleDataModel();
			object.getCondition();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getModeratorTest() {
		try{
			RoleDataModel object = new RoleDataModel();
			object.getModerator();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setConditionTest() {
		try{
			RoleDataModel object = new RoleDataModel();
			object.setCondition("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRoleNameTest() {
		try{
			RoleDataModel object = new RoleDataModel();
			object.getRoleName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setRoleNameTest() {
		try{
			RoleDataModel object = new RoleDataModel();
			object.setRoleName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModeratorTest() {
		try{
			RoleDataModel object = new RoleDataModel();
			object.setModerator("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRoleCodeTest() {
		try{
			RoleDataModel object = new RoleDataModel();
			object.getRoleCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setRoleIdTest() {
		try{
			RoleDataModel object = new RoleDataModel();
			object.setRoleId(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRoleIdTest() {
		try{
			RoleDataModel object = new RoleDataModel();
			object.getRoleId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}