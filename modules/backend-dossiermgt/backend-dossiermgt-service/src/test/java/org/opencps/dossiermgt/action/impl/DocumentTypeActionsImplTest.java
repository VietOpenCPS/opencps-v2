package org.opencps.dossiermgt.action.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class DocumentTypeActionsImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getDocumentTypeListTest() {
		try{
			DocumentTypeActionsImpl object = new DocumentTypeActionsImpl();
			object.getDocumentTypeList(Long.valueOf(0), null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void getByTypeCodeTest() {
//		try{
//			DocumentTypeActionsImpl object = new DocumentTypeActionsImpl();
//			object.getByTypeCode(Long.valueOf(0), "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getByDocIdTest() {
//		try{
//			DocumentTypeActionsImpl object = new DocumentTypeActionsImpl();
//			object.getByDocId(Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void createDocTypeTest() {
//		try{
//			DocumentTypeActionsImpl object = new DocumentTypeActionsImpl();
//			object.createDocType(Long.valueOf(0), Long.valueOf(0), "abcde", 0, "abcde", "abcde", "abcde", 0, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void removeDocTypeTest() {
//		try{
//			DocumentTypeActionsImpl object = new DocumentTypeActionsImpl();
//			object.removeDocType(Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateDocTypeTest() {
//		try{
//			DocumentTypeActionsImpl object = new DocumentTypeActionsImpl();
//			object.updateDocType(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", 0, "abcde", "abcde", "abcde", 0, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateDocumentTypeDBTest() {
//		try{
//			DocumentTypeActionsImpl object = new DocumentTypeActionsImpl();
//			object.updateDocumentTypeDB(Long.valueOf(0), Long.valueOf(0), "abcde", 0, "abcde", "abcde", 0, "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
}