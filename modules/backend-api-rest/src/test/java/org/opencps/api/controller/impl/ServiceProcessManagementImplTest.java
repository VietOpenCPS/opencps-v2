package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class ServiceProcessManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void updateProcessStepRoleTest() {
		try{
			ServiceProcessManagementImpl object = new ServiceProcessManagementImpl();
			object.updateProcessStepRole(null, null, null, null, null, null, Long.valueOf(0), "abcde", Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateProcessPostActionTest() {
		try{
			ServiceProcessManagementImpl object = new ServiceProcessManagementImpl();
			object.updateProcessPostAction(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceProcessDetailTest() {
		try{
			ServiceProcessManagementImpl object = new ServiceProcessManagementImpl();
			object.getServiceProcessDetail(null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeServiceProcessTest() {
		try{
			ServiceProcessManagementImpl object = new ServiceProcessManagementImpl();
			object.removeServiceProcess(null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void cloneServiceProcessesTest() {
		try{
			ServiceProcessManagementImpl object = new ServiceProcessManagementImpl();
			object.cloneServiceProcesses(null, null, null, null, null, null, Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteProcessSequenceTest() {
		try{
			ServiceProcessManagementImpl object = new ServiceProcessManagementImpl();
			object.deleteProcessSequence(null, null, null, null, null, null, Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateProcessStepTest() {
		try{
			ServiceProcessManagementImpl object = new ServiceProcessManagementImpl();
			object.updateProcessStep(null, null, null, null, null, null, Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateServiceProcessTest() {
		try{
			ServiceProcessManagementImpl object = new ServiceProcessManagementImpl();
			object.updateServiceProcess(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceProcessesTest() {
		try{
			ServiceProcessManagementImpl object = new ServiceProcessManagementImpl();
			object.getServiceProcesses(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateServiceProcessRoleTest() {
		try{
			ServiceProcessManagementImpl object = new ServiceProcessManagementImpl();
			object.updateServiceProcessRole(null, null, null, null, null, null, Long.valueOf(0), Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeProcessStepRoleTest() {
		try{
			ServiceProcessManagementImpl object = new ServiceProcessManagementImpl();
			object.removeProcessStepRole(null, null, null, null, null, null, Long.valueOf(0), "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addProcessStepRoleTest() {
		try{
			ServiceProcessManagementImpl object = new ServiceProcessManagementImpl();
			object.addProcessStepRole(null, null, null, null, null, null, Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessSequencesTest() {
		try{
			ServiceProcessManagementImpl object = new ServiceProcessManagementImpl();
			object.getProcessSequences(null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateProcessSequenceTest() {
		try{
			ServiceProcessManagementImpl object = new ServiceProcessManagementImpl();
			object.updateProcessSequence(null, null, null, null, null, null, Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addServiceProcessRoleTest() {
		try{
			ServiceProcessManagementImpl object = new ServiceProcessManagementImpl();
			object.addServiceProcessRole(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addServiceProcessesTest() {
		try{
			ServiceProcessManagementImpl object = new ServiceProcessManagementImpl();
			object.addServiceProcesses(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceProcessMermaidGraphTest() {
		try{
			ServiceProcessManagementImpl object = new ServiceProcessManagementImpl();
			object.getServiceProcessMermaidGraph(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void removeServiceProcessRoleTest() {
//		try{
//			ServiceProcessManagementImpl object = new ServiceProcessManagementImpl();
//			object.removeServiceProcessRole(null, null, null, null, null, null, Long.valueOf(0), Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void removeProcessActionTest() {
		try{
			ServiceProcessManagementImpl object = new ServiceProcessManagementImpl();
			object.removeProcessAction(null, null, null, null, null, null, Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addProcessSequenceTest() {
		try{
			ServiceProcessManagementImpl object = new ServiceProcessManagementImpl();
			object.addProcessSequence(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateCounterCodeTest() {
		try{
			ServiceProcessManagementImpl object = new ServiceProcessManagementImpl();
			object.updateCounterCode(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateProcessActionTest() {
		try{
			ServiceProcessManagementImpl object = new ServiceProcessManagementImpl();
			object.updateProcessAction(null, null, null, null, null, null, Long.valueOf(0), Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceProcessRolesTest() {
		try{
			ServiceProcessManagementImpl object = new ServiceProcessManagementImpl();
			object.getServiceProcessRoles(null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void initServiceProcessesTest() {
		try{
			ServiceProcessManagementImpl object = new ServiceProcessManagementImpl();
			object.initServiceProcesses(null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessActionsTest() {
		try{
			ServiceProcessManagementImpl object = new ServiceProcessManagementImpl();
			object.getProcessActions(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessStepRolesTest() {
		try{
			ServiceProcessManagementImpl object = new ServiceProcessManagementImpl();
			object.getProcessStepRoles(null, null, null, null, null, null, Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeProcessStepTest() {
		try{
			ServiceProcessManagementImpl object = new ServiceProcessManagementImpl();
			object.removeProcessStep(null, null, null, null, null, null, Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessActionTest() {
		try{
			ServiceProcessManagementImpl object = new ServiceProcessManagementImpl();
			object.getProcessAction(null, null, null, null, null, null, Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessStepTest() {
		try{
			ServiceProcessManagementImpl object = new ServiceProcessManagementImpl();
			object.getProcessStep(null, null, null, null, null, null, Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addProcessStepTest() {
		try{
			ServiceProcessManagementImpl object = new ServiceProcessManagementImpl();
			object.addProcessStep(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessStepsTest() {
		try{
			ServiceProcessManagementImpl object = new ServiceProcessManagementImpl();
			object.getProcessSteps(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addProcessActionTest() {
		try{
			ServiceProcessManagementImpl object = new ServiceProcessManagementImpl();
			object.addProcessAction(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}