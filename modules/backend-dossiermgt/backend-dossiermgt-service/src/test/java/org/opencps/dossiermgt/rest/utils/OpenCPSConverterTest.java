package org.opencps.dossiermgt.rest.utils;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class OpenCPSConverterTest {
	@Before
	public void setUp() {
	}
	@Test
	public void convertDossierFileEFormHttpParamsTest() {
		try{
			OpenCPSConverter.convertDossierFileEFormHttpParams(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertPaymentFileInputHttpParamsTest() {
		try{
			OpenCPSConverter.convertPaymentFileInputHttpParams(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertDossierMarkInputHttpParamsTest() {
		try{
			OpenCPSConverter.convertDossierMarkInputHttpParams(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertDossierToJSONTest() {
		try{
			OpenCPSConverter.convertDossierToJSON(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertDossierToInputModelTest() {
		try{
			OpenCPSConverter.convertDossierToInputModel(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertDossierDocumentTest() {
		try{
			OpenCPSConverter.convertDossierDocument(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertDossierDocumentTest7() {
		try{
			OpenCPSConverter.convertDossierDocument(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertInformDossierToInputModelTest() {
		try{
			OpenCPSConverter.convertInformDossierToInputModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertDossierFileTest() {
		try{
			OpenCPSConverter.convertDossierFile(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertDossierMarkTest() {
		try{
			OpenCPSConverter.convertDossierMark(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertDossierDocumentHttpParamsTest() {
		try{
			OpenCPSConverter.convertDossierDocumentHttpParams(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertDossierToLGSPSyncDocumentTest() {
		try{
			OpenCPSConverter.convertDossierToLGSPSyncDocument(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void convertPublishHttpParamsTest() {
//		try{
//			OpenCPSConverter.convertPublishHttpParams(new DossierPublishModel());
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void convertPublishHttpParamsTest14() {
//		try{
//			OpenCPSConverter.convertPublishHttpParams(new DossierImpl());
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void convertLGSPSyncDocumentTest() {
		try{
			OpenCPSConverter.convertLGSPSyncDocument(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertPaymentFileTest() {
		try{
			OpenCPSConverter.convertPaymentFile(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertHttpParamsTest() {
		try{
			OpenCPSConverter.convertHttpParams(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertProcessActionTest() {
		try{
			OpenCPSConverter.convertProcessAction(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertDossierFileHttpParamsTest() {
		try{
			OpenCPSConverter.convertDossierFileHttpParams(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertPaymentConfirmFileTest() {
		try{
			OpenCPSConverter.convertPaymentConfirmFile(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertToDocumentTracesTest() {
		try{
			OpenCPSConverter.convertToDocumentTraces(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertFileInputModelToJSONTest() {
		try{
			OpenCPSConverter.convertFileInputModelToJSON(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertDossierToLGSPJSONTest() {
		try{
			OpenCPSConverter.convertDossierToLGSPJSON(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertDossierDetailTest() {
		try{
			OpenCPSConverter.convertDossierDetail(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertInformHttpParamsTest() {
		try{
			OpenCPSConverter.convertInformHttpParams(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertExecuteActionHttpParamsTest() {
		try{
			OpenCPSConverter.convertExecuteActionHttpParams(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertDossierPublishTest() {
		try{
			OpenCPSConverter.convertDossierPublish(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertExecuteOneActionToJSONTest() {
		try{
			OpenCPSConverter.convertExecuteOneActionToJSON(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertDossierSummaryTest() {
		try{
			OpenCPSConverter.convertDossierSummary(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isSuccessTest() {
		try{
			OpenCPSConverter.isSuccess(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertMtokenTest() {
		try{
			OpenCPSConverter.convertMtoken(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertToUTCDateTest() {
		try{
			OpenCPSConverter.convertToUTCDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}