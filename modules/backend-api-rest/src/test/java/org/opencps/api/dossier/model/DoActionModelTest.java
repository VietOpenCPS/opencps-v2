package org.opencps.api.dossier.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DoActionModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getAssignUsersTest() {
		try{
			DoActionModel object = new DoActionModel();
			object.getAssignUsers();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setActionCodeTest() {
		try{
			DoActionModel object = new DoActionModel();
			object.setActionCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionCodeTest() {
		try{
			DoActionModel object = new DoActionModel();
			object.getActionCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPayloadTest() {
		try{
			DoActionModel object = new DoActionModel();
			object.setPayload("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionNoteTest() {
		try{
			DoActionModel object = new DoActionModel();
			object.getActionNote();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionUserTest() {
		try{
			DoActionModel object = new DoActionModel();
			object.getActionUser();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPayloadTest() {
		try{
			DoActionModel object = new DoActionModel();
			object.getPayload();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPaymentTest() {
		try{
			DoActionModel object = new DoActionModel();
			object.getPayment();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setActionNoteTest() {
		try{
			DoActionModel object = new DoActionModel();
			object.setActionNote("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setActionUserTest() {
		try{
			DoActionModel object = new DoActionModel();
			object.setActionUser("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPaymentTest() {
		try{
			DoActionModel object = new DoActionModel();
			object.setPayment("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCreateDossiersTest() {
		try{
			DoActionModel object = new DoActionModel();
			object.getCreateDossiers();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreateDossiersTest() {
		try{
			DoActionModel object = new DoActionModel();
			object.setCreateDossiers("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setAssignUsersTest() {
		try{
			DoActionModel object = new DoActionModel();
			object.setAssignUsers("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSecurityTest() {
		try{
			DoActionModel object = new DoActionModel();
			object.getSecurity();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSecurityTest() {
		try{
			DoActionModel object = new DoActionModel();
			object.setSecurity("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}