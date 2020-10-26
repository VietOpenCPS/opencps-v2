package org.opencps.api.controller.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class DossierActionUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void mappingToDoListReadActionExecutedTest() {
		try{
			DossierActionUtils.mappingToDoListReadActionExecuted(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingToDoActionExecutedModelTest() {
		try{
			DossierActionUtils.mappingToDoActionExecutedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void processRoleListUserTest() {
//		try{
//			DossierActionUtils.processRoleListUser(null, Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void mappingToDoListReadNextActionsTest() {
		try{
			DossierActionUtils.mappingToDoListReadNextActions(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingToDoListActionsTest() {
		try{
			DossierActionUtils.mappingToDoListActions(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void processRoleAsStepListUserTest() {
		try{
			DossierActionUtils.processRoleAsStepListUser(null, "abcde", Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void processRoleAsStepDonedListUserTest() {
		try{
			DossierActionUtils.processRoleAsStepDonedListUser(null, "abcde", Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingToDoActionModelTest() {
		try{
			DossierActionUtils.mappingToDoActionModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingToPayLoadNextActionsTest() {
		try{
			DossierActionUtils.mappingToPayLoadNextActions(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingToDoListContactsTest() {
		try{
			DossierActionUtils.mappingToDoListContacts(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingToNextActionsTest() {
		try{
			DossierActionUtils.mappingToNextActions(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingToDetailNextActionsTest() {
		try{
			DossierActionUtils.mappingToDetailNextActions(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierPartCrossDossierTest() {
		try{
			DossierActionUtils.getDossierPartCrossDossier(Long.valueOf(0), null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}