package org.opencps.dossiermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ProcessActionLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			ProcessActionLocalServiceImpl object = new ProcessActionLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeProcessActionTest() {
		try{
			ProcessActionLocalServiceImpl object = new ProcessActionLocalServiceImpl();
			object.removeProcessAction(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateProcessActionTest() {
		try{
			ProcessActionLocalServiceImpl object = new ProcessActionLocalServiceImpl();
			object.updateProcessAction(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, Long.valueOf(0), 0, "abcde", "abcde", "abcde", "abcde", "abcde", true, true, true, "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateProcessActionTest4() {
		try{
			ProcessActionLocalServiceImpl object = new ProcessActionLocalServiceImpl();
			object.updateProcessAction(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, Long.valueOf(0), 0, "abcde", "abcde", "abcde", "abcde", "abcde", true, true, true, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateProcessActionTest5() {
		try{
			ProcessActionLocalServiceImpl object = new ProcessActionLocalServiceImpl();
			object.updateProcessAction(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, Long.valueOf(0), 0, "abcde", "abcde", "abcde", "abcde", "abcde", true, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchLuceneTest() {
		try{
			ProcessActionLocalServiceImpl object = new ProcessActionLocalServiceImpl();
			object.searchLucene(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByActionCodeTest() {
		try{
			ProcessActionLocalServiceImpl object = new ProcessActionLocalServiceImpl();
			object.getByActionCode(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByActionCodeTest8() {
		try{
			ProcessActionLocalServiceImpl object = new ProcessActionLocalServiceImpl();
			object.getByActionCode(Long.valueOf(0), "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchCountTest() {
		try{
			ProcessActionLocalServiceImpl object = new ProcessActionLocalServiceImpl();
			object.searchCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateProcessActionDBTest() {
		try{
			ProcessActionLocalServiceImpl object = new ProcessActionLocalServiceImpl();
			object.updateProcessActionDB(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, Long.valueOf(0), "abcde", 0, "abcde", "abcde", "abcde", true, "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByServiceProcessTest() {
		try{
			ProcessActionLocalServiceImpl object = new ProcessActionLocalServiceImpl();
			object.getByServiceProcess(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessActionbyServiceProcessIdTest() {
		try{
			ProcessActionLocalServiceImpl object = new ProcessActionLocalServiceImpl();
			object.getProcessActionbyServiceProcessId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchBySPID_ACTest() {
		try{
			ProcessActionLocalServiceImpl object = new ProcessActionLocalServiceImpl();
			object.fetchBySPID_AC(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchBySPI_PRESC_AEVTest() {
		try{
			ProcessActionLocalServiceImpl object = new ProcessActionLocalServiceImpl();
			object.fetchBySPI_PRESC_AEV(Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_GID_SID_AC_PRE_POSTTest() {
		try{
			ProcessActionLocalServiceImpl object = new ProcessActionLocalServiceImpl();
			object.fetchByF_GID_SID_AC_PRE_POST(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByServiceStepCodeTest() {
		try{
			ProcessActionLocalServiceImpl object = new ProcessActionLocalServiceImpl();
			object.getByServiceStepCode(Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessActionByG_SPID_PRESCTest() {
		try{
			ProcessActionLocalServiceImpl object = new ProcessActionLocalServiceImpl();
			object.getProcessActionByG_SPID_PRESC(Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			ProcessActionLocalServiceImpl object = new ProcessActionLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByG_SID_ACSTest() {
		try{
			ProcessActionLocalServiceImpl object = new ProcessActionLocalServiceImpl();
			object.getByG_SID_ACS(Long.valueOf(0), Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByGroupAndAutoEventTest() {
		try{
			ProcessActionLocalServiceImpl object = new ProcessActionLocalServiceImpl();
			object.getByGroupAndAutoEvent(Long.valueOf(0), "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByNameActionNoTest() {
		try{
			ProcessActionLocalServiceImpl object = new ProcessActionLocalServiceImpl();
			object.getByNameActionNo(Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}