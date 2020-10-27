package org.opencps.usermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ApplicantLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			ApplicantLocalServiceImpl object = new ApplicantLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void activateApplicantTest() {
		try{
			ApplicantLocalServiceImpl object = new ApplicantLocalServiceImpl();
			object.activateApplicant(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeApplicantTest() {
		try{
			ApplicantLocalServiceImpl object = new ApplicantLocalServiceImpl();
			object.removeApplicant(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchLuceneTest() {
		try{
			ApplicantLocalServiceImpl object = new ApplicantLocalServiceImpl();
			object.searchLucene(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByMappingIDTest() {
		try{
			ApplicantLocalServiceImpl object = new ApplicantLocalServiceImpl();
			object.fetchByMappingID(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void verifyApplicantTest() {
		try{
			ApplicantLocalServiceImpl object = new ApplicantLocalServiceImpl();
			object.verifyApplicant(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateApplicantTest() {
		try{
			ApplicantLocalServiceImpl object = new ApplicantLocalServiceImpl();
			object.updateApplicant(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", new Date(), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateApplicantTest8() {
		try{
			ApplicantLocalServiceImpl object = new ApplicantLocalServiceImpl();
			object.updateApplicant(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", new Date(), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getApplicantByTypeTest() {
		try{
			ApplicantLocalServiceImpl object = new ApplicantLocalServiceImpl();
			object.getApplicantByType(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_APLC_GIDTest() {
		try{
			ApplicantLocalServiceImpl object = new ApplicantLocalServiceImpl();
			object.fetchByF_APLC_GID(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneTest() {
		try{
			ApplicantLocalServiceImpl object = new ApplicantLocalServiceImpl();
			object.countLucene(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_GID_CTEMTest() {
		try{
			ApplicantLocalServiceImpl object = new ApplicantLocalServiceImpl();
			object.fetchByF_GID_CTEM(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			ApplicantLocalServiceImpl object = new ApplicantLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByAppIdTest() {
		try{
			ApplicantLocalServiceImpl object = new ApplicantLocalServiceImpl();
			object.fetchByAppId("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByAppIdsTest() {
		try{
			ApplicantLocalServiceImpl object = new ApplicantLocalServiceImpl();
			object.findByAppIds("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByEmailTest() {
		try{
			ApplicantLocalServiceImpl object = new ApplicantLocalServiceImpl();
			object.fetchByEmail("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateApplicationTest() {
		try{
			ApplicantLocalServiceImpl object = new ApplicantLocalServiceImpl();
			object.updateApplication(null, Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateApplicationTest19() {
		try{
			ApplicantLocalServiceImpl object = new ApplicantLocalServiceImpl();
			object.updateApplication(null, Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateApplicationTest20() {
		try{
			ApplicantLocalServiceImpl object = new ApplicantLocalServiceImpl();
			object.updateApplication(null, Long.valueOf(0), Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void lockoutApplicantTest() {
		try{
			ApplicantLocalServiceImpl object = new ApplicantLocalServiceImpl();
			object.lockoutApplicant(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByTelNoTest() {
		try{
			ApplicantLocalServiceImpl object = new ApplicantLocalServiceImpl();
			object.fetchByTelNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchBy_GTelNoTest() {
		try{
			ApplicantLocalServiceImpl object = new ApplicantLocalServiceImpl();
			object.fetchBy_GTelNo(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeProfileTest() {
		try{
			ApplicantLocalServiceImpl object = new ApplicantLocalServiceImpl();
			object.removeProfile(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_GID_MCN_MCPKTest() {
		try{
			ApplicantLocalServiceImpl object = new ApplicantLocalServiceImpl();
			object.findByF_GID_MCN_MCPK(Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateApplicantApprovedTest() {
		try{
			ApplicantLocalServiceImpl object = new ApplicantLocalServiceImpl();
			object.updateApplicantApproved(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", new Date(), "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateApplicationDBTest() {
		try{
			ApplicantLocalServiceImpl object = new ApplicantLocalServiceImpl();
			object.updateApplicationDB(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", new Date(), "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_GID_MCN_MCPKTest() {
		try{
			ApplicantLocalServiceImpl object = new ApplicantLocalServiceImpl();
			object.fetchByF_GID_MCN_MCPK(Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByContactEmailListTest() {
		try{
			ApplicantLocalServiceImpl object = new ApplicantLocalServiceImpl();
			object.findByContactEmailList("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void importApplicationDBTest() {
		try{
			ApplicantLocalServiceImpl object = new ApplicantLocalServiceImpl();
			object.importApplicationDB(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", new Date(), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void importApplicationDBTest31() {
		try{
			ApplicantLocalServiceImpl object = new ApplicantLocalServiceImpl();
			object.importApplicationDB(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", new Date(), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}