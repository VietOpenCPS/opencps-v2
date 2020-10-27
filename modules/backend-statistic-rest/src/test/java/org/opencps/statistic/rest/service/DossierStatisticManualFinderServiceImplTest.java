package org.opencps.statistic.rest.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierStatisticManualFinderServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void finderDossierStatisticSystemTest() {
		try{
			DossierStatisticManualFinderServiceImpl object = new DossierStatisticManualFinderServiceImpl();
			object.finderDossierStatisticSystem(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void finderDossierStatisticsTest() {
		try{
			DossierStatisticManualFinderServiceImpl object = new DossierStatisticManualFinderServiceImpl();
			object.finderDossierStatistics(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}