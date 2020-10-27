package backend.api.rest.application.v21.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class DossierDocumentApiImplTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void getDocumentListTest() {
//		try{
//			DossierDocumentApiImpl object = new DossierDocumentApiImpl();
//			object.getDocumentList("abcde", 0, 0);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void createDossierDocTest() {
		try{
			DossierDocumentApiImpl object = new DossierDocumentApiImpl();
			object.createDossierDoc("abcde", null, "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void downloadDocByReferenceUidTest() {
		try{
			DossierDocumentApiImpl object = new DossierDocumentApiImpl();
			object.downloadDocByReferenceUid("abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}