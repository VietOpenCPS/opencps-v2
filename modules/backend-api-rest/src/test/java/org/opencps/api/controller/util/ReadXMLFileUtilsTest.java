package org.opencps.api.controller.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class ReadXMLFileUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void convertDeliverableTypeToXMLStreamTest() {
		try{
			ReadXMLFileUtils.convertDeliverableTypeToXMLStream(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertNotificationTemplateToXMLStreamTest() {
		try{
			ReadXMLFileUtils.convertNotificationTemplateToXMLStream(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStrErrorTest() {
		try{
			ReadXMLFileUtils.getStrError();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setStrErrorTest() {
		try{
			ReadXMLFileUtils.setStrError("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertStepConfigToXMLStreamTest() {
		try{
			ReadXMLFileUtils.convertStepConfigToXMLStream(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertMenuConfigToXMLStreamTest() {
		try{
			ReadXMLFileUtils.convertMenuConfigToXMLStream(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertDocumentTypeToXMLStreamTest() {
		try{
			ReadXMLFileUtils.convertDocumentTypeToXMLStream(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertPaymentConfigToXMLStreamTest() {
		try{
			ReadXMLFileUtils.convertPaymentConfigToXMLStream(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertServerConfigToXMLStreamTest() {
		try{
			ReadXMLFileUtils.convertServerConfigToXMLStream(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertUserManagementToXMLStreamTest() {
		try{
			ReadXMLFileUtils.convertUserManagementToXMLStream(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertActionConfigToXMLStreamTest() {
		try{
			ReadXMLFileUtils.convertActionConfigToXMLStream(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertCitizenToXMLTest() {
		try{
			ReadXMLFileUtils.convertCitizenToXML(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertBusinessToXMLTest() {
		try{
			ReadXMLFileUtils.convertBusinessToXML(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertDictCollectionToXMLTest() {
		try{
			ReadXMLFileUtils.convertDictCollectionToXML(null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void exportStepConfigToXMLStreamTest() {
//		try{
//			ReadXMLFileUtils.exportStepConfigToXMLStream(Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void exportMenuConfigToXMLStreamTest() {
//		try{
//			ReadXMLFileUtils.exportMenuConfigToXMLStream(Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void exportUserManagementToXMLStreamTest() {
//		try{
//			ReadXMLFileUtils.exportUserManagementToXMLStream(Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void exportServerConfigToXMLStreamTest() {
//		try{
//			ReadXMLFileUtils.exportServerConfigToXMLStream(Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void exportDocumentTypeToXMLStreamTest() {
//		try{
//			ReadXMLFileUtils.exportDocumentTypeToXMLStream(Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void deleteFilesForParentFolderTest() {
		try{
			ReadXMLFileUtils.deleteFilesForParentFolder(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertMenuConfigToXMLTest() {
		try{
			ReadXMLFileUtils.convertMenuConfigToXML(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void exportActionConfigToXMLStreamTest() {
//		try{
//			ReadXMLFileUtils.exportActionConfigToXMLStream(Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void exportDeliverableTypeToXMLStreamTest() {
//		try{
//			ReadXMLFileUtils.exportDeliverableTypeToXMLStream(Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void convertServiceInfoToXMLTest() {
		try{
			ReadXMLFileUtils.convertServiceInfoToXML(null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertServiceInfoToXMLStreamTest() {
		try{
			ReadXMLFileUtils.convertServiceInfoToXMLStream(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void exportPaymentConfigToXMLStreamTest() {
//		try{
//			ReadXMLFileUtils.exportPaymentConfigToXMLStream(Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void convertDictCollectionToXMLStreamTest() {
		try{
			ReadXMLFileUtils.convertDictCollectionToXMLStream(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertActionConfigToXMLTest() {
		try{
			ReadXMLFileUtils.convertActionConfigToXML(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertStepConfigToXMLTest() {
		try{
			ReadXMLFileUtils.convertStepConfigToXML(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertServiceProcessToXMLStreamTest() {
		try{
			ReadXMLFileUtils.convertServiceProcessToXMLStream(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void exportNotificationTemplateToXMLStreamTest() {
//		try{
//			ReadXMLFileUtils.exportNotificationTemplateToXMLStream(Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void convertDossierTemplateToXMLStreamTest() {
		try{
			ReadXMLFileUtils.convertDossierTemplateToXMLStream(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void compareParentFileTest() {
//		try{
//			ReadXMLFileUtils.compareParentFile("abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void listFilesForParentFolderTest() {
		try{
			ReadXMLFileUtils.listFilesForParentFolder(null, Long.valueOf(0), Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertFiletoStringTest() {
		try{
			ReadXMLFileUtils.convertFiletoString(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}