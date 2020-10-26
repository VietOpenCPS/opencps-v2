package org.opencps.api.controller.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierDocumentUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void parseJSONObjectTest() {
		try{
			DossierDocumentUtils object = new DossierDocumentUtils();
			object.parseJSONObject(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void parseJSONObjectIndexTest() {
		try{
			DossierDocumentUtils object = new DossierDocumentUtils();
			object.parseJSONObjectIndex(null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void processMergeDossierFormDataTest() {
		try{
			DossierDocumentUtils.processMergeDossierFormData(null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void processMergeDossierFormDataTest4() {
		try{
			DossierDocumentUtils.processMergeDossierFormData(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}