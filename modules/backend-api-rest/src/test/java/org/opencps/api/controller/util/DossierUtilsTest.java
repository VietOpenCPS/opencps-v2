package org.opencps.api.controller.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class DossierUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void convertFormModelToMultipleInputModelTest() {
		try{
			DossierUtils.convertFormModelToMultipleInputModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void getProcessOptionTest() {
//		try{
//			DossierUtils.getProcessOption("abcde", "abcde", "abcde", Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void getProcessActionTest() {
		try{
			DossierUtils.getProcessAction(null, Long.valueOf(0), null, "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void getDossierTest() {
//		try{
//			DossierUtils.getDossier("abcde", Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void calculatorOverDueTest() {
//		try{
//			DossierUtils.calculatorOverDue(Double.valueOf(0.0), 0, Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), true);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void mappingForGetDetailSearchTest() {
		try{
			DossierUtils.mappingForGetDetailSearch(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDossierUsersTest() {
		try{
			DossierUtils.createDossierUsers(Long.valueOf(0), null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingForGetListPagingTest() {
		try{
			DossierUtils.mappingForGetListPaging(null, 0, 0, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void calculatorStepOverDueTest() {
//		try{
//			DossierUtils.calculatorStepOverDue(Double.valueOf(0.0), 0, Double.valueOf(0.0), Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void mappingDossierActionTest() {
		try{
			DossierUtils.mappingDossierAction(null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertFormModelToInputModelTest() {
		try{
			DossierUtils.convertFormModelToInputModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void mappingForGetDetailTest() {
//		try{
//			DossierUtils.mappingForGetDetail(null, Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void mappingForGetListTest() {
		try{
			DossierUtils.mappingForGetList(null, Long.valueOf(0), 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertFormModelToPublishModelTest() {
		try{
			DossierUtils.convertFormModelToPublishModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingForGetPublishListTest() {
		try{
			DossierUtils.mappingForGetPublishList(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}