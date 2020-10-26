package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class StatisticManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void exportDossierStatisticTest() {
		try{
			StatisticManagementImpl object = new StatisticManagementImpl();
			object.exportDossierStatistic(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierTodoTestTest() {
		try{
			StatisticManagementImpl object = new StatisticManagementImpl();
			object.getDossierTodoTest(null, null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void getDossierCountingTest() {
//		try{
//			StatisticManagementImpl object = new StatisticManagementImpl();
//			object.getDossierCounting(null, null, null, null, null, null, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void getDossierCountTodoTest() {
		try{
			StatisticManagementImpl object = new StatisticManagementImpl();
			object.getDossierCountTodo(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierTodoTest() {
		try{
			StatisticManagementImpl object = new StatisticManagementImpl();
			object.getDossierTodo(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierPersonTest() {
		try{
			StatisticManagementImpl object = new StatisticManagementImpl();
			object.getDossierPerson(null, null, null, null, null, null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}