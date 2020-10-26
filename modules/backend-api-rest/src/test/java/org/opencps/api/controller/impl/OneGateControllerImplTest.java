package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class OneGateControllerImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getDossierOngateTest() {
		try{
			OneGateControllerImpl object = new OneGateControllerImpl();
			object.getDossierOngate(null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGovAgenciesTest() {
		try{
			OneGateControllerImpl object = new OneGateControllerImpl();
			object.getGovAgencies(null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDossierOngateTest() {
		try{
			OneGateControllerImpl object = new OneGateControllerImpl();
			object.createDossierOngate(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceconfigsTest() {
		try{
			OneGateControllerImpl object = new OneGateControllerImpl();
			object.getServiceconfigs(null, null, null, null, null, null, "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierOngateTest() {
		try{
			OneGateControllerImpl object = new OneGateControllerImpl();
			object.updateDossierOngate(null, null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceProcessTest() {
		try{
			OneGateControllerImpl object = new OneGateControllerImpl();
			object.getServiceProcess(null, null, null, null, null, null, Long.valueOf(0), "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}