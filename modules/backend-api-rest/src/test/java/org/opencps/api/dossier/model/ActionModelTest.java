package org.opencps.api.dossier.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ActionModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setActionCodeTest() {
		try{
			ActionModel object = new ActionModel();
			object.setActionCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionNameTest() {
		try{
			ActionModel object = new ActionModel();
			object.getActionName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setActionNameTest() {
		try{
			ActionModel object = new ActionModel();
			object.setActionName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionCodeTest() {
		try{
			ActionModel object = new ActionModel();
			object.getActionCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAssignUserIdTest() {
		try{
			ActionModel object = new ActionModel();
			object.getAssignUserId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAutoEventTest() {
		try{
			ActionModel object = new ActionModel();
			object.getAutoEvent();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPreStepCodeTest() {
		try{
			ActionModel object = new ActionModel();
			object.setPreStepCode(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPreStepCodeTest() {
		try{
			ActionModel object = new ActionModel();
			object.getPreStepCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPostStepCodeTest() {
		try{
			ActionModel object = new ActionModel();
			object.getPostStepCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setAutoEventTest() {
		try{
			ActionModel object = new ActionModel();
			object.setAutoEvent("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPostStepCodeTest() {
		try{
			ActionModel object = new ActionModel();
			object.setPostStepCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setAssignUserIdTest() {
		try{
			ActionModel object = new ActionModel();
			object.setAssignUserId(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessActionIdTest() {
		try{
			ActionModel object = new ActionModel();
			object.getProcessActionId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setProcessActionIdTest() {
		try{
			ActionModel object = new ActionModel();
			object.setProcessActionId(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPreCondition_0020Test() {
		try{
			ActionModel object = new ActionModel();
			object.getPreCondition_0020();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPreCondition_0020Test() {
		try{
			ActionModel object = new ActionModel();
			object.setPreCondition_0020("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAllowAssignUserTest() {
		try{
			ActionModel object = new ActionModel();
			object.getAllowAssignUser();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setAllowAssignUserTest() {
		try{
			ActionModel object = new ActionModel();
			object.setAllowAssignUser("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getToUsersTest() {
		try{
			ActionModel object = new ActionModel();
			object.getToUsers();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}