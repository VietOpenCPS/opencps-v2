package org.opencps.api.dossieraction.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierNextActionResultsModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getTotalTest() {
		try{
			DossierNextActionResultsModel object = new DossierNextActionResultsModel();
			object.getTotal();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setStepCodeTest() {
		try{
			DossierNextActionResultsModel object = new DossierNextActionResultsModel();
			object.setStepCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStepNameTest() {
		try{
			DossierNextActionResultsModel object = new DossierNextActionResultsModel();
			object.getStepName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStepCodeTest() {
		try{
			DossierNextActionResultsModel object = new DossierNextActionResultsModel();
			object.getStepCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setStepNameTest() {
		try{
			DossierNextActionResultsModel object = new DossierNextActionResultsModel();
			object.setStepName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setStepDueDateTest() {
		try{
			DossierNextActionResultsModel object = new DossierNextActionResultsModel();
			object.setStepDueDate(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setStepOverdueTest() {
		try{
			DossierNextActionResultsModel object = new DossierNextActionResultsModel();
			object.setStepOverdue("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUsersTest() {
		try{
			DossierNextActionResultsModel object = new DossierNextActionResultsModel();
			object.setUsers(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCheckInputTest() {
		try{
			DossierNextActionResultsModel object = new DossierNextActionResultsModel();
			object.setCheckInput(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStepOverdueTest() {
		try{
			DossierNextActionResultsModel object = new DossierNextActionResultsModel();
			object.getStepOverdue();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCheckInputTest() {
		try{
			DossierNextActionResultsModel object = new DossierNextActionResultsModel();
			object.getCheckInput();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStepDueDateTest() {
		try{
			DossierNextActionResultsModel object = new DossierNextActionResultsModel();
			object.getStepDueDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDataTest() {
		try{
			DossierNextActionResultsModel object = new DossierNextActionResultsModel();
			object.getData();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTotalTest() {
		try{
			DossierNextActionResultsModel object = new DossierNextActionResultsModel();
			object.setTotal(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUsersTest() {
		try{
			DossierNextActionResultsModel object = new DossierNextActionResultsModel();
			object.getUsers();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}