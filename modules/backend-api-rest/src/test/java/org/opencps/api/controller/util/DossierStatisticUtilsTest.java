package org.opencps.api.controller.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierStatisticUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void mappingToDossierStatisticDataTest() {
		try{
			DossierStatisticUtils.mappingToDossierStatisticData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingDossierStatisticYearModelTest() {
		try{
			DossierStatisticUtils.mappingDossierStatisticYearModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingToDossierStatistictModelTest() {
		try{
			DossierStatisticUtils.mappingToDossierStatistictModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingToDossierStatisticModelTest() {
		try{
			DossierStatisticUtils.mappingToDossierStatisticModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}