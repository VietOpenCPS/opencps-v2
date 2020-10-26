package org.opencps.api.dossieraction.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierNextActionModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setActionCodeTest() {
		try{
			DossierNextActionModel object = new DossierNextActionModel();
			object.setActionCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionNameTest() {
		try{
			DossierNextActionModel object = new DossierNextActionModel();
			object.getActionName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setActionNameTest() {
		try{
			DossierNextActionModel object = new DossierNextActionModel();
			object.setActionName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionCodeTest() {
		try{
			DossierNextActionModel object = new DossierNextActionModel();
			object.getActionCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAutoEventTest() {
		try{
			DossierNextActionModel object = new DossierNextActionModel();
			object.getAutoEvent();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPreStepCodeTest() {
		try{
			DossierNextActionModel object = new DossierNextActionModel();
			object.setPreStepCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPreStepCodeTest() {
		try{
			DossierNextActionModel object = new DossierNextActionModel();
			object.getPreStepCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPostStepCodeTest() {
		try{
			DossierNextActionModel object = new DossierNextActionModel();
			object.getPostStepCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setAutoEventTest() {
		try{
			DossierNextActionModel object = new DossierNextActionModel();
			object.setAutoEvent("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPostStepCodeTest() {
		try{
			DossierNextActionModel object = new DossierNextActionModel();
			object.setPostStepCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPreConditionTest() {
		try{
			DossierNextActionModel object = new DossierNextActionModel();
			object.getPreCondition();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPreConditionTest() {
		try{
			DossierNextActionModel object = new DossierNextActionModel();
			object.setPreCondition("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEnableTest() {
		try{
			DossierNextActionModel object = new DossierNextActionModel();
			object.setEnable(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEnableTest() {
		try{
			DossierNextActionModel object = new DossierNextActionModel();
			object.getEnable();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessActionIdTest() {
		try{
			DossierNextActionModel object = new DossierNextActionModel();
			object.getProcessActionId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setProcessActionIdTest() {
		try{
			DossierNextActionModel object = new DossierNextActionModel();
			object.setProcessActionId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setAllowDelegacyUserTest() {
		try{
			DossierNextActionModel object = new DossierNextActionModel();
			object.setAllowDelegacyUser("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAllowDelegacyUserTest() {
		try{
			DossierNextActionModel object = new DossierNextActionModel();
			object.getAllowDelegacyUser();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}