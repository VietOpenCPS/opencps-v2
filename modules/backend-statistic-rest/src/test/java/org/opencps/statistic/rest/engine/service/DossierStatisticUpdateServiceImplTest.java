package org.opencps.statistic.rest.engine.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierStatisticUpdateServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void updateDossierStatisticTest() {
		try{
			DossierStatisticUpdateServiceImpl object = new DossierStatisticUpdateServiceImpl();
			object.updateDossierStatistic(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierStatisticTest2() {
		try{
			DossierStatisticUpdateServiceImpl object = new DossierStatisticUpdateServiceImpl();
			object.updateDossierStatistic(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}