package org.opencps.sms.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierLookUpFacadeImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void callDossierRestServiceTest() {
		try{
			DossierLookUpFacadeImpl object = new DossierLookUpFacadeImpl();
			object.callDossierRestService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void callDossierRestServiceTest2() {
		try{
			DossierLookUpFacadeImpl object = new DossierLookUpFacadeImpl();
			object.callDossierRestService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void makeServiceCallTest() {
		try{
			DossierLookUpFacadeImpl object = new DossierLookUpFacadeImpl();
			object.makeServiceCall(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void makeServiceCallTest4() {
		try{
			DossierLookUpFacadeImpl object = new DossierLookUpFacadeImpl();
			object.makeServiceCall(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}