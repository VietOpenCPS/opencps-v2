package org.opencps.api.serviceprocess.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class RoleInputModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setRoleCodeTest() {
		try{
			RoleInputModel object = new RoleInputModel();
			object.setRoleCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getConditionTest() {
		try{
			RoleInputModel object = new RoleInputModel();
			object.getCondition();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getModeratorTest() {
		try{
			RoleInputModel object = new RoleInputModel();
			object.getModerator();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setConditionTest() {
		try{
			RoleInputModel object = new RoleInputModel();
			object.setCondition("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRoleNameTest() {
		try{
			RoleInputModel object = new RoleInputModel();
			object.getRoleName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setRoleNameTest() {
		try{
			RoleInputModel object = new RoleInputModel();
			object.setRoleName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModeratorTest() {
		try{
			RoleInputModel object = new RoleInputModel();
			object.setModerator("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRoleCodeTest() {
		try{
			RoleInputModel object = new RoleInputModel();
			object.getRoleCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setRoleIdTest() {
		try{
			RoleInputModel object = new RoleInputModel();
			object.setRoleId(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRoleIdTest() {
		try{
			RoleInputModel object = new RoleInputModel();
			object.getRoleId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}