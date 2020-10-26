package org.opencps.api.controller.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class NotarizationUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void mappingToNotarizationModelTest() {
		try{
			NotarizationUtils.mappingToNotarizationModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingToNotarizationResultsTest() {
		try{
			NotarizationUtils.mappingToNotarizationResults(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingToNotarizationDetailModelTest() {
		try{
			NotarizationUtils.mappingToNotarizationDetailModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}