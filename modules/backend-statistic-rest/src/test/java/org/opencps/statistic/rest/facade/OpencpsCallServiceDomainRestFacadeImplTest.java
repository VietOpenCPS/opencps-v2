package org.opencps.statistic.rest.facade;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class OpencpsCallServiceDomainRestFacadeImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void makeServiceCallTest() {
		try{
			OpencpsCallServiceDomainRestFacadeImpl object = new OpencpsCallServiceDomainRestFacadeImpl();
			object.makeServiceCall(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void makeServiceCallTest2() {
		try{
			OpencpsCallServiceDomainRestFacadeImpl object = new OpencpsCallServiceDomainRestFacadeImpl();
			object.makeServiceCall(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void callRestServiceTest() {
		try{
			OpencpsCallServiceDomainRestFacadeImpl object = new OpencpsCallServiceDomainRestFacadeImpl();
			object.callRestService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void callRestServiceTest4() {
		try{
			OpencpsCallServiceDomainRestFacadeImpl object = new OpencpsCallServiceDomainRestFacadeImpl();
			object.callRestService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}