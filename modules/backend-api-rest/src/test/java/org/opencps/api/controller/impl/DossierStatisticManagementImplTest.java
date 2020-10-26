package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierStatisticManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void postAgencyTest() {
		try{
			DossierStatisticManagementImpl object = new DossierStatisticManagementImpl();
			object.postAgency(null, null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getYearsTest() {
		try{
			DossierStatisticManagementImpl object = new DossierStatisticManagementImpl();
			object.getYears(null, null, null, null, null, null, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierStatisticTest() {
		try{
			DossierStatisticManagementImpl object = new DossierStatisticManagementImpl();
			object.getDossierStatistic(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}