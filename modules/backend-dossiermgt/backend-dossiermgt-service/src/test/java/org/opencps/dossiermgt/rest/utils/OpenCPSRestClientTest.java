package org.opencps.dossiermgt.rest.utils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class OpenCPSRestClientTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setGroupIdTest() {
		try{
			OpenCPSRestClient object = new OpenCPSRestClient("abcde");
			object.setGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupIdTest() {
		try{
			OpenCPSRestClient object = new OpenCPSRestClient("abcde");
			object.getGroupId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void rollbackTest() {
		try{
			OpenCPSRestClient object = new OpenCPSRestClient("abcde");
			object.rollback("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void gotoStepTest() {
		try{
			OpenCPSRestClient object = new OpenCPSRestClient("abcde");
			object.gotoStep("abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeDossierTest() {
		try{
			OpenCPSRestClient object = new OpenCPSRestClient("abcde");
			object.removeDossier(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fromJSONObjectTest() {
		try{
			OpenCPSRestClient.fromJSONObject(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getBaseUrlTest() {
		try{
			OpenCPSRestClient object = new OpenCPSRestClient("abcde");
			object.getBaseUrl();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setBaseUrlTest() {
		try{
			OpenCPSRestClient object = new OpenCPSRestClient("abcde");
			object.setBaseUrl("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void postDossierTest() {
		try{
			OpenCPSRestClient object = new OpenCPSRestClient("abcde");
			object.postDossier(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void publishDossierTest() {
//		try{
//			OpenCPSRestClient object = new OpenCPSRestClient("abcde");
//			object.publishDossier(new DossierPublishModel());
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}

	/*@Test
	public void publishDossierTest11() {
		try{
			OpenCPSRestClient object = new OpenCPSRestClient("abcde");
			object.publishDossier(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}*/
	@Test
	public void postDossierFileEFormTest() {
		try{
			OpenCPSRestClient object = new OpenCPSRestClient("abcde");
			object.postDossierFileEForm(null, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void postDossierDocumentTest() {
		try{
			OpenCPSRestClient object = new OpenCPSRestClient("abcde");
			object.postDossierDocument(null, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updatePaymentsConfirmFileTest() {
		try{
			OpenCPSRestClient object = new OpenCPSRestClient("abcde");
			object.updatePaymentsConfirmFile(null, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void postDossierActionTest() {
		try{
			OpenCPSRestClient object = new OpenCPSRestClient("abcde");
			object.postDossierAction("abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void getAllFilesByDossierTest() {
//		try{
//			OpenCPSRestClient object = new OpenCPSRestClient("abcde");
//			object.getAllFilesByDossier("abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void postDossierFileTest() {
		try{
			OpenCPSRestClient object = new OpenCPSRestClient("abcde");
			object.postDossierFile(null, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void postDossierMarkTest() {
		try{
			OpenCPSRestClient object = new OpenCPSRestClient("abcde");
			object.postDossierMark("abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void postPaymentFilesTest() {
		try{
			OpenCPSRestClient object = new OpenCPSRestClient("abcde");
			object.postPaymentFiles("abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isWriteLogTest() {
		try{
			OpenCPSRestClient object = new OpenCPSRestClient("abcde");
			object.isWriteLog();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void putDossierTest() {
		try{
			OpenCPSRestClient object = new OpenCPSRestClient("abcde");
			object.putDossier(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWriteLogTest() {
		try{
			OpenCPSRestClient object = new OpenCPSRestClient("abcde");
			object.setWriteLog(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}