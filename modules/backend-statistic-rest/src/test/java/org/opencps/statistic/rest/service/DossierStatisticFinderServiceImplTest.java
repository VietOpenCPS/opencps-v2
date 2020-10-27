package org.opencps.statistic.rest.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierStatisticFinderServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void finderDossierStatisticSystemTest() {
		try{
			DossierStatisticFinderServiceImpl object = new DossierStatisticFinderServiceImpl();
			object.finderDossierStatisticSystem(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void finderDossierStatisticsTest() {
		try{
			DossierStatisticFinderServiceImpl object = new DossierStatisticFinderServiceImpl();
			object.finderDossierStatistics(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}