package org.opencps.dossiermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierActionUserLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void addDossierActionUserTest() {
		try{
			DossierActionUserLocalServiceImpl object = new DossierActionUserLocalServiceImpl();
			object.addDossierActionUser(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", 0, 0, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDossierActionUserTest2() {
		try{
			DossierActionUserLocalServiceImpl object = new DossierActionUserLocalServiceImpl();
			object.addDossierActionUser(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", 0, 0, true, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByDOSSIER_UIDTest() {
		try{
			DossierActionUserLocalServiceImpl object = new DossierActionUserLocalServiceImpl();
			object.getByDOSSIER_UID(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getListUserTest() {
		try{
			DossierActionUserLocalServiceImpl object = new DossierActionUserLocalServiceImpl();
			object.getListUser(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByDossierIdTest() {
		try{
			DossierActionUserLocalServiceImpl object = new DossierActionUserLocalServiceImpl();
			object.getByDossierId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByDossierAndStepCodeTest() {
		try{
			DossierActionUserLocalServiceImpl object = new DossierActionUserLocalServiceImpl();
			object.getByDossierAndStepCode(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierActionUserTest() {
		try{
			DossierActionUserLocalServiceImpl object = new DossierActionUserLocalServiceImpl();
			object.updateDossierActionUser(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", 0, 0, true, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierActionUserTest8() {
		try{
			DossierActionUserLocalServiceImpl object = new DossierActionUserLocalServiceImpl();
			object.updateDossierActionUser(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", 0, 0, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByD_DID_UID_SCTest() {
		try{
			DossierActionUserLocalServiceImpl object = new DossierActionUserLocalServiceImpl();
			object.getByD_DID_UID_SC(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByDID_DAIDTest() {
		try{
			DossierActionUserLocalServiceImpl object = new DossierActionUserLocalServiceImpl();
			object.getByDID_DAID(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByDIDTest() {
		try{
			DossierActionUserLocalServiceImpl object = new DossierActionUserLocalServiceImpl();
			object.getByDID(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getListUserByUserIdTest() {
		try{
			DossierActionUserLocalServiceImpl object = new DossierActionUserLocalServiceImpl();
			object.getListUserByUserId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByDossierUserAndStepCodeTest() {
		try{
			DossierActionUserLocalServiceImpl object = new DossierActionUserLocalServiceImpl();
			object.getByDossierUserAndStepCode(Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByDID_DAI_SC_ASTest() {
		try{
			DossierActionUserLocalServiceImpl object = new DossierActionUserLocalServiceImpl();
			object.getByDID_DAI_SC_AS(Long.valueOf(0), Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addOrUpdateDossierActionUserTest() {
		try{
			DossierActionUserLocalServiceImpl object = new DossierActionUserLocalServiceImpl();
			object.addOrUpdateDossierActionUser(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", 0, 0, true, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addOrUpdateDossierActionUserTest16() {
		try{
			DossierActionUserLocalServiceImpl object = new DossierActionUserLocalServiceImpl();
			object.addOrUpdateDossierActionUser(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", 0, 0, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteByDossierActionTest() {
		try{
			DossierActionUserLocalServiceImpl object = new DossierActionUserLocalServiceImpl();
			object.deleteByDossierAction(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteByDossierAndStepCodeTest() {
		try{
			DossierActionUserLocalServiceImpl object = new DossierActionUserLocalServiceImpl();
			object.deleteByDossierAndStepCode(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByDossierAndUserTest() {
		try{
			DossierActionUserLocalServiceImpl object = new DossierActionUserLocalServiceImpl();
			object.getByDossierAndUser(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}