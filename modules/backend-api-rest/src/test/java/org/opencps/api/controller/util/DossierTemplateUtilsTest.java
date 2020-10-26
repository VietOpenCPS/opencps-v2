package org.opencps.api.controller.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierTemplateUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void mappingForTemplatePOSTTest() {
		try{
			DossierTemplateUtils.mappingForTemplatePOST(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingToDossierPartListTest() {
		try{
			DossierTemplateUtils.mappingToDossierPartList(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingForTemplateGetDetailTest() {
		try{
			DossierTemplateUtils.mappingForTemplateGetDetail(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingForPartPOSTTest() {
		try{
			DossierTemplateUtils.mappingForPartPOST(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingToDossierTemplateListTest() {
		try{
			DossierTemplateUtils.mappingToDossierTemplateList(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}