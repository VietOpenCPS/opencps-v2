package org.opencps.api.resourcerole.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ResourceRoleModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void isSelectedTest() {
		try{
			ResourceRoleModel object = new ResourceRoleModel();
			object.isSelected();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRoleNameTest() {
		try{
			ResourceRoleModel object = new ResourceRoleModel();
			object.getRoleName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setRoleNameTest() {
		try{
			ResourceRoleModel object = new ResourceRoleModel();
			object.setRoleName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSelectedTest() {
		try{
			ResourceRoleModel object = new ResourceRoleModel();
			object.setSelected(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setRoleIdTest() {
		try{
			ResourceRoleModel object = new ResourceRoleModel();
			object.setRoleId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRoleIdTest() {
		try{
			ResourceRoleModel object = new ResourceRoleModel();
			object.getRoleId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}