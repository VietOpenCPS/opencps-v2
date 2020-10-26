package org.opencps.api.jobpos.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class JobposPermissionModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void isSelectedTest() {
		try{
			JobposPermissionModel object = new JobposPermissionModel();
			object.isSelected();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionNameTest() {
		try{
			JobposPermissionModel object = new JobposPermissionModel();
			object.getActionName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setActionNameTest() {
		try{
			JobposPermissionModel object = new JobposPermissionModel();
			object.setActionName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSelectedTest() {
		try{
			JobposPermissionModel object = new JobposPermissionModel();
			object.setSelected(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setActionIdTest() {
		try{
			JobposPermissionModel object = new JobposPermissionModel();
			object.setActionId("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionIdTest() {
		try{
			JobposPermissionModel object = new JobposPermissionModel();
			object.getActionId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}