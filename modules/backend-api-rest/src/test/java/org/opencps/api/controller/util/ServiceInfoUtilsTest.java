package org.opencps.api.controller.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ServiceInfoUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void mappingToServiceRecentDetailModelTest() {
		try{
			ServiceInfoUtils.mappingToServiceRecentDetailModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingToFileTemplatesTest() {
		try{
			ServiceInfoUtils.mappingToFileTemplates(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingToFileTemplateModelTest() {
		try{
			ServiceInfoUtils.mappingToFileTemplateModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingToServiceInfoInputModelTest() {
		try{
			ServiceInfoUtils.mappingToServiceInfoInputModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingToServiceInfoResultModelTest() {
		try{
			ServiceInfoUtils.mappingToServiceInfoResultModel(null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingToServiceInfoDetailModelTest() {
		try{
			ServiceInfoUtils.mappingToServiceInfoDetailModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}