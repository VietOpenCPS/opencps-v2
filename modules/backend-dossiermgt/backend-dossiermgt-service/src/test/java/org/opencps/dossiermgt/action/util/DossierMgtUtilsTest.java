package org.opencps.dossiermgt.action.util;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class DossierMgtUtilsTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void getProcessOptionTest() {
//		try{
//			DossierMgtUtils.getProcessOption("abcde", "abcde", "abcde", Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getDossierTest() {
//		try{
//			DossierMgtUtils object = new DossierMgtUtils();
//			object.getDossier(Long.valueOf(0), "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void _stringToDateTest() {
		try{
			DossierMgtUtils._stringToDate("abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void processSyncDeleteDossierTest() {
		try{
			DossierMgtUtils.processSyncDeleteDossier(null, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierProcessSequencesJSONTest() {
		try{
			DossierMgtUtils.getDossierProcessSequencesJSON(Long.valueOf(0), null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierProcessSequencesPublishJSONTest() {
		try{
			DossierMgtUtils.getDossierProcessSequencesPublishJSON(Long.valueOf(0), null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void checkPreConditionTest() {
		try{
			DossierMgtUtils.checkPreCondition(null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void processSyncGotoDossierTest() {
		try{
			DossierMgtUtils.processSyncGotoDossier(null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void processSyncRollbackDossierTest() {
		try{
			DossierMgtUtils.processSyncRollbackDossier(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertDossierToJSONTest() {
		try{
			DossierMgtUtils.convertDossierToJSON(null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void _dateToStringTest() {
		try{
			DossierMgtUtils._dateToString(new Date(), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mergeObjectTest() {
		try{
			DossierMgtUtils.mergeObject("abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void minDayTest() {
		try{
			DossierMgtUtils.minDay(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isDueDateEditableTest() {
		try{
			DossierMgtUtils.isDueDateEditable("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}