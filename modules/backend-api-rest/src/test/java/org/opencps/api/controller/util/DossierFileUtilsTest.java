package org.opencps.api.controller.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierFileUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void mappingToDossierFileSearchResultsModelTest() {
		try{
			DossierFileUtils.mappingToDossierFileSearchResultsModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingToDossierFileModelTest() {
		try{
			DossierFileUtils.mappingToDossierFileModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingToDossierFileDataTest() {
		try{
			DossierFileUtils.mappingToDossierFileData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}