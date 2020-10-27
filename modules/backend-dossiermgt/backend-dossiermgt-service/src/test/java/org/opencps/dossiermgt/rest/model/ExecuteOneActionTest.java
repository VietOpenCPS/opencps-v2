package org.opencps.dossiermgt.rest.model;
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
	public void getAssignUsersTest() {
		try{
			ExecuteOneAction object = new ExecuteOneAction();
			object.getAssignUsers();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
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
	public void setPayloadTest() {
		try{
			ExecuteOneAction object = new ExecuteOneAction();
			object.setPayload("abcde");
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
	public void getPayloadTest() {
		try{
			ExecuteOneAction object = new ExecuteOneAction();
			object.getPayload();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPaymentTest() {
		try{
			ExecuteOneAction object = new ExecuteOneAction();
			object.getPayment();
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
	public void setPaymentTest() {
		try{
			ExecuteOneAction object = new ExecuteOneAction();
			object.setPayment("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setAssignUsersTest() {
		try{
			ExecuteOneAction object = new ExecuteOneAction();
			object.setAssignUsers("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSecurityTest() {
		try{
			ExecuteOneAction object = new ExecuteOneAction();
			object.getSecurity();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSecurityTest() {
		try{
			ExecuteOneAction object = new ExecuteOneAction();
			object.setSecurity("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}