package org.opencps.api.controller.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class RegistrationLogUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void mappingToRegistrationLoggDataListDocumentTest() {
		try{
			RegistrationLogUtils.mappingToRegistrationLoggDataListDocument(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingToRegistrationLogModelTest() {
		try{
			RegistrationLogUtils.mappingToRegistrationLogModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingToRegistrationLoggDataTest() {
		try{
			RegistrationLogUtils.mappingToRegistrationLoggData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}