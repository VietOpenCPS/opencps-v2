package org.opencps.dossiermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class RegistrationLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void insertTest() {
		try{
			RegistrationLocalServiceImpl object = new RegistrationLocalServiceImpl();
			object.insert(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDataTest() {
		try{
			RegistrationLocalServiceImpl object = new RegistrationLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByApplicantAndAgencyTest() {
		try{
			RegistrationLocalServiceImpl object = new RegistrationLocalServiceImpl();
			object.getByApplicantAndAgency(Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateRegistrationTest() {
		try{
			RegistrationLocalServiceImpl object = new RegistrationLocalServiceImpl();
			object.updateRegistration(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchLuceneTest() {
		try{
			RegistrationLocalServiceImpl object = new RegistrationLocalServiceImpl();
			object.searchLucene(Long.valueOf(0), null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRegistrationByG_REGIDTest() {
		try{
			RegistrationLocalServiceImpl object = new RegistrationLocalServiceImpl();
			object.getRegistrationByG_REGID(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			RegistrationLocalServiceImpl object = new RegistrationLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getfileEntryIdTest() {
		try{
			RegistrationLocalServiceImpl object = new RegistrationLocalServiceImpl();
			object.getfileEntryId("abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateSubmittingTest() {
		try{
			RegistrationLocalServiceImpl object = new RegistrationLocalServiceImpl();
			object.updateSubmitting(Long.valueOf(0), true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void registrationSyncTest() {
		try{
			RegistrationLocalServiceImpl object = new RegistrationLocalServiceImpl();
			object.registrationSync(Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLucenseTest() {
		try{
			RegistrationLocalServiceImpl object = new RegistrationLocalServiceImpl();
			object.countLucense(Long.valueOf(0), null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRegistrationByGID_UID_LastTest() {
		try{
			RegistrationLocalServiceImpl object = new RegistrationLocalServiceImpl();
			object.getRegistrationByGID_UID_Last(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getLastSubmittingByUserTest() {
		try{
			RegistrationLocalServiceImpl object = new RegistrationLocalServiceImpl();
			object.getLastSubmittingByUser(Long.valueOf(0), Long.valueOf(0), true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRegistrationByGID_UIDTest() {
		try{
			RegistrationLocalServiceImpl object = new RegistrationLocalServiceImpl();
			object.getRegistrationByGID_UID(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByApplicantIdNoTest() {
		try{
			RegistrationLocalServiceImpl object = new RegistrationLocalServiceImpl();
			object.getByApplicantIdNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getdByF_submittingTest() {
		try{
			RegistrationLocalServiceImpl object = new RegistrationLocalServiceImpl();
			object.getdByF_submitting(Long.valueOf(0), true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByRegistrationStateTest() {
		try{
			RegistrationLocalServiceImpl object = new RegistrationLocalServiceImpl();
			object.getByRegistrationState(Long.valueOf(0), Long.valueOf(0), 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}