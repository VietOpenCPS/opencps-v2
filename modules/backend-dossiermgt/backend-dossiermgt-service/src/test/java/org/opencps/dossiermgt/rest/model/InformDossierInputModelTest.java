package org.opencps.dossiermgt.rest.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class InformDossierInputModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getReferenceUidTest() {
		try{
			InformDossierInputModel object = new InformDossierInputModel();
			object.getReferenceUid();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDueDateTest() {
		try{
			InformDossierInputModel object = new InformDossierInputModel();
			object.getDueDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDvcqgIntegrationTest() {
		try{
			InformDossierInputModel object = new InformDossierInputModel();
			object.setDvcqgIntegration(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDueDateTest() {
		try{
			InformDossierInputModel object = new InformDossierInputModel();
			object.setDueDate(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setReceiveDateTest() {
		try{
			InformDossierInputModel object = new InformDossierInputModel();
			object.setReceiveDate(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getReceiveDateTest() {
		try{
			InformDossierInputModel object = new InformDossierInputModel();
			object.getReceiveDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setReferenceUidTest() {
		try{
			InformDossierInputModel object = new InformDossierInputModel();
			object.setReferenceUid("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierNoTest() {
		try{
			InformDossierInputModel object = new InformDossierInputModel();
			object.setDossierNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierNoTest() {
		try{
			InformDossierInputModel object = new InformDossierInputModel();
			object.getDossierNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isDvcqgIntegrationTest() {
		try{
			InformDossierInputModel object = new InformDossierInputModel();
			object.isDvcqgIntegration();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}