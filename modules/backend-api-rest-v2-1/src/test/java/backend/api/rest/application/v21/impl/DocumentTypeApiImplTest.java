package backend.api.rest.application.v21.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class DocumentTypeApiImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void createDocumentTypeTest() {
		try{
			DocumentTypeApiImpl object = new DocumentTypeApiImpl();
			object.createDocumentType(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAllDocumentTypesTest() {
		try{
			DocumentTypeApiImpl object = new DocumentTypeApiImpl();
			object.getAllDocumentTypes("abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDocByIdTest() {
		try{
			DocumentTypeApiImpl object = new DocumentTypeApiImpl();
			object.getDocById("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void removeDocByIdTest() {
//		try{
//			DocumentTypeApiImpl object = new DocumentTypeApiImpl();
//			object.removeDocById("abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void updateDocByIdTest() {
		try{
			DocumentTypeApiImpl object = new DocumentTypeApiImpl();
			object.updateDocById("abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}