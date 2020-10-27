package org.opencps.dossiermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierSyncLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getDossierSyncListTest() {
		try{
			DossierSyncLocalServiceImpl object = new DossierSyncLocalServiceImpl();
			object.getDossierSyncList("abcde", 0, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByDID_DADTest() {
		try{
			DossierSyncLocalServiceImpl object = new DossierSyncLocalServiceImpl();
			object.getByDID_DAD(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByG_DIDTest() {
		try{
			DossierSyncLocalServiceImpl object = new DossierSyncLocalServiceImpl();
			object.findByG_DID(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByG_DID_STTest() {
		try{
			DossierSyncLocalServiceImpl object = new DossierSyncLocalServiceImpl();
			object.findByG_DID_ST(Long.valueOf(0), Long.valueOf(0), 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByStateTest() {
		try{
			DossierSyncLocalServiceImpl object = new DossierSyncLocalServiceImpl();
			object.findByState(0, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByStatesTest() {
		try{
			DossierSyncLocalServiceImpl object = new DossierSyncLocalServiceImpl();
			object.findByStates(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByDID_DAD_ACTest() {
		try{
			DossierSyncLocalServiceImpl object = new DossierSyncLocalServiceImpl();
			object.getByDID_DAD_AC(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countDossierSyncListTest() {
		try{
			DossierSyncLocalServiceImpl object = new DossierSyncLocalServiceImpl();
			object.countDossierSyncList("abcde", 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countByDossierAndInfoTypeTest() {
		try{
			DossierSyncLocalServiceImpl object = new DossierSyncLocalServiceImpl();
			object.countByDossierAndInfoType(Long.valueOf(0), "abcde", 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countByDossierAndInfoTypeArrTest() {
		try{
			DossierSyncLocalServiceImpl object = new DossierSyncLocalServiceImpl();
			object.countByDossierAndInfoTypeArr(Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByDossierAndInfoTypeTest() {
		try{
			DossierSyncLocalServiceImpl object = new DossierSyncLocalServiceImpl();
			object.findByDossierAndInfoType(Long.valueOf(0), "abcde", 0, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findForApplicantAndActionCodeTest() {
		try{
			DossierSyncLocalServiceImpl object = new DossierSyncLocalServiceImpl();
			object.findForApplicantAndActionCode(Long.valueOf(0), "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierSyncByIdListTest() {
		try{
			DossierSyncLocalServiceImpl object = new DossierSyncLocalServiceImpl();
			object.getDossierSyncByIdList(Long.valueOf(0), 0, 0, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countForApplicantAndActionCodeTest() {
		try{
			DossierSyncLocalServiceImpl object = new DossierSyncLocalServiceImpl();
			object.countForApplicantAndActionCode(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countDossierSyncByIdListTest() {
		try{
			DossierSyncLocalServiceImpl object = new DossierSyncLocalServiceImpl();
			object.countDossierSyncByIdList(Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByDossierAndInfoTypeArrTest() {
		try{
			DossierSyncLocalServiceImpl object = new DossierSyncLocalServiceImpl();
			object.findByDossierAndInfoTypeArr(Long.valueOf(0), "abcde", null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierSyncTest() {
		try{
			DossierSyncLocalServiceImpl object = new DossierSyncLocalServiceImpl();
			object.updateDossierSync(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", 0, 0, "abcde", "abcde", 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeByDossierIdTest() {
		try{
			DossierSyncLocalServiceImpl object = new DossierSyncLocalServiceImpl();
			object.removeByDossierId(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findAllTest() {
		try{
			DossierSyncLocalServiceImpl object = new DossierSyncLocalServiceImpl();
			object.findAll(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}