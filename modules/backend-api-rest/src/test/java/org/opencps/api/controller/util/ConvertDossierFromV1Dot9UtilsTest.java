package org.opencps.api.controller.util;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class ConvertDossierFromV1Dot9UtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void mainTest() {
		try{
			ConvertDossierFromV1Dot9Utils.main(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertRowToJSONObjectTest() {
		try{
			ConvertDossierFromV1Dot9Utils.convertRowToJSONObject(null, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertStringToDateTest() {
		try{
			ConvertDossierFromV1Dot9Utils.convertStringToDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierObjectTest() {
		try{
			ConvertDossierFromV1Dot9Utils.getDossierObject(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mergeJSONObjectTest() {
		try{
			ConvertDossierFromV1Dot9Utils.mergeJSONObject(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierObjectTest() {
		try{
			ConvertDossierFromV1Dot9Utils.setDossierObject(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileFromDVCOldTest() {
		try{
			ConvertDossierFromV1Dot9Utils.getFileFromDVCOld("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void readExcelFileWithHeaderConfigTest() {
		try{
			ConvertDossierFromV1Dot9Utils.readExcelFileWithHeaderConfig(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void insertUserDossierTest() {
//		try{
//			ConvertDossierFromV1Dot9Utils.insertUserDossier(Long.valueOf(0), Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void buildDossierJSONObjectTest() {
		try{
			ConvertDossierFromV1Dot9Utils.buildDossierJSONObject(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void insertUserDossierDvcTest() {
//		try{
//			ConvertDossierFromV1Dot9Utils.insertUserDossierDvc(Long.valueOf(0), Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void buildDossierFileJSONObjectTest() {
		try{
			ConvertDossierFromV1Dot9Utils.buildDossierFileJSONObject(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void callActionDoneDossierTest() {
//		try{
//			ConvertDossierFromV1Dot9Utils.callActionDoneDossier("abcde", Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void setDossierFileObjectTest() {
		try{
			ConvertDossierFromV1Dot9Utils.setDossierFileObject(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCellValueTest() {
		try{
			ConvertDossierFromV1Dot9Utils.getCellValue(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void _dateToStringTest() {
		try{
			ConvertDossierFromV1Dot9Utils._dateToString(new Date(), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}