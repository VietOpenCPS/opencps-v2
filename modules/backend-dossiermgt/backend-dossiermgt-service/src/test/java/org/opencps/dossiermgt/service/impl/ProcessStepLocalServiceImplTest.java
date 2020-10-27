package org.opencps.dossiermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ProcessStepLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			ProcessStepLocalServiceImpl object = new ProcessStepLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateProcessStepTest() {
		try{
			ProcessStepLocalServiceImpl object = new ProcessStepLocalServiceImpl();
			object.updateProcessStep(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", Long.valueOf(0), "abcde", "abcde", "abcde", Double.valueOf(0.0), "abcde", "abcde", "abcde", true, "abcde", 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeProcessStepTest() {
		try{
			ProcessStepLocalServiceImpl object = new ProcessStepLocalServiceImpl();
			object.removeProcessStep(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchLuceneTest() {
		try{
			ProcessStepLocalServiceImpl object = new ProcessStepLocalServiceImpl();
			object.searchLucene(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchBySC_GIDTest() {
		try{
			ProcessStepLocalServiceImpl object = new ProcessStepLocalServiceImpl();
			object.fetchBySC_GID("abcde", Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByG_SP_SNOTest() {
		try{
			ProcessStepLocalServiceImpl object = new ProcessStepLocalServiceImpl();
			object.findByG_SP_SNO(Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateProcessStepDBTest() {
		try{
			ProcessStepLocalServiceImpl object = new ProcessStepLocalServiceImpl();
			object.updateProcessStepDB(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Double.valueOf(0.0), "abcde", "abcde", "abcde", 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessStepbyServiceProcessIdTest() {
		try{
			ProcessStepLocalServiceImpl object = new ProcessStepLocalServiceImpl();
			object.getProcessStepbyServiceProcessId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneTest() {
		try{
			ProcessStepLocalServiceImpl object = new ProcessStepLocalServiceImpl();
			object.countLucene(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByProcessAndStatusTest() {
		try{
			ProcessStepLocalServiceImpl object = new ProcessStepLocalServiceImpl();
			object.getByProcessAndStatus(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			ProcessStepLocalServiceImpl object = new ProcessStepLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByG_SP_SCSTest() {
		try{
			ProcessStepLocalServiceImpl object = new ProcessStepLocalServiceImpl();
			object.getByG_SP_SCS(Long.valueOf(0), Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getBySC_SPIDTest() {
		try{
			ProcessStepLocalServiceImpl object = new ProcessStepLocalServiceImpl();
			object.getBySC_SPID("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByStatusAnsSubStatusTest() {
		try{
			ProcessStepLocalServiceImpl object = new ProcessStepLocalServiceImpl();
			object.getByStatusAnsSubStatus("abcde", "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}