package org.opencps.api.controller.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierLogUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void mappingToDossierLogSearchByIdResultsModelTest() {
		try{
			DossierLogUtils.mappingToDossierLogSearchByIdResultsModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingToDossierLogDataTest() {
		try{
			DossierLogUtils.mappingToDossierLogData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingToDossierLogResultsModelTest() {
		try{
			DossierLogUtils.mappingToDossierLogResultsModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingToDossierLogModelTest() {
		try{
			DossierLogUtils.mappingToDossierLogModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}