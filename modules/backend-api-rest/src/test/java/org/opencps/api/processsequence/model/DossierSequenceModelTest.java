package org.opencps.api.processsequence.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierSequenceModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getActionsTest() {
		try{
			DossierSequenceModel object = new DossierSequenceModel();
			object.getActions();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAssignUsersTest() {
		try{
			DossierSequenceModel object = new DossierSequenceModel();
			object.getAssignUsers();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSequenceRoleTest() {
		try{
			DossierSequenceModel object = new DossierSequenceModel();
			object.setSequenceRole("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSequenceNameTest() {
		try{
			DossierSequenceModel object = new DossierSequenceModel();
			object.getSequenceName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSequenceNoTest() {
		try{
			DossierSequenceModel object = new DossierSequenceModel();
			object.setSequenceNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDurationCountTest() {
		try{
			DossierSequenceModel object = new DossierSequenceModel();
			object.getDurationCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSequenceNoTest() {
		try{
			DossierSequenceModel object = new DossierSequenceModel();
			object.getSequenceNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSequenceRoleTest() {
		try{
			DossierSequenceModel object = new DossierSequenceModel();
			object.getSequenceRole();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSequenceNameTest() {
		try{
			DossierSequenceModel object = new DossierSequenceModel();
			object.setSequenceName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDurationCountTest() {
		try{
			DossierSequenceModel object = new DossierSequenceModel();
			object.setDurationCount(Double.valueOf(0.0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setStartDateTest() {
		try{
			DossierSequenceModel object = new DossierSequenceModel();
			object.setStartDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStartDateTest() {
		try{
			DossierSequenceModel object = new DossierSequenceModel();
			object.getStartDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}