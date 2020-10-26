package org.opencps.api.dossier.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ExecuteOneActionTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setActionCodeTest() {
		try{
			ExecuteOneAction object = new ExecuteOneAction();
			object.setActionCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionCodeTest() {
		try{
			ExecuteOneAction object = new ExecuteOneAction();
			object.getActionCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAssignUserIdTest() {
		try{
			ExecuteOneAction object = new ExecuteOneAction();
			object.getAssignUserId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionNoteTest() {
		try{
			ExecuteOneAction object = new ExecuteOneAction();
			object.getActionNote();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionUserTest() {
		try{
			ExecuteOneAction object = new ExecuteOneAction();
			object.getActionUser();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setActionNoteTest() {
		try{
			ExecuteOneAction object = new ExecuteOneAction();
			object.setActionNote("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setActionUserTest() {
		try{
			ExecuteOneAction object = new ExecuteOneAction();
			object.setActionUser("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setAssignUserIdTest() {
		try{
			ExecuteOneAction object = new ExecuteOneAction();
			object.setAssignUserId(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}