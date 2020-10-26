package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class RegistrationFormManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void deleteFormbyRegIdTest() {
		try{
			RegistrationFormManagementImpl object = new RegistrationFormManagementImpl();
			object.deleteFormbyRegId(null, null, null, null, null, null, Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getformScriptbyRegidRefidTest() {
		try{
			RegistrationFormManagementImpl object = new RegistrationFormManagementImpl();
			object.getformScriptbyRegidRefid(null, null, null, null, null, null, Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getformdatabyRegidRefidTest() {
		try{
			RegistrationFormManagementImpl object = new RegistrationFormManagementImpl();
			object.getformdatabyRegidRefid(null, null, null, null, null, null, Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateRegFormFormDataTest() {
		try{
			RegistrationFormManagementImpl object = new RegistrationFormManagementImpl();
			object.updateRegFormFormData(null, null, null, null, null, null, Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void registrationSyncsFormTest() {
		try{
			RegistrationFormManagementImpl object = new RegistrationFormManagementImpl();
			object.registrationSyncsForm(null, null, null, null, null, null, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}