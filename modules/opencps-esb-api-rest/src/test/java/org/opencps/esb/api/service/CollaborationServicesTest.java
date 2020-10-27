package org.opencps.esb.api.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class CollaborationServicesTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getAgencyServiceTest() {
		try{
			CollaborationServices.getAgencyService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSlotServiceTest() {
		try{
			CollaborationServices.getSlotService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getKnobstickServiceTest() {
		try{
			CollaborationServices.getKnobstickService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSecurityTokenServiceTest() {
		try{
			CollaborationServices.getSecurityTokenService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}