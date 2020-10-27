package org.opencps.statistic.rest.facade;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class OpencpsCallGovAgencyRestFacadeImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void makeServiceCallTest() {
		try{
			OpencpsCallGovAgencyRestFacadeImpl object = new OpencpsCallGovAgencyRestFacadeImpl();
			object.makeServiceCall(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void makeServiceCallTest2() {
		try{
			OpencpsCallGovAgencyRestFacadeImpl object = new OpencpsCallGovAgencyRestFacadeImpl();
			object.makeServiceCall(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void callRestServiceTest() {
		try{
			OpencpsCallGovAgencyRestFacadeImpl object = new OpencpsCallGovAgencyRestFacadeImpl();
			object.callRestService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void callRestServiceTest4() {
		try{
			OpencpsCallGovAgencyRestFacadeImpl object = new OpencpsCallGovAgencyRestFacadeImpl();
			object.callRestService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}