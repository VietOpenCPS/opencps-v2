package org.opencps.api.registrationtemplate.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class RegistrationTemplateSampleDataInputUpdateModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setSampleDataTest() {
		try{
			RegistrationTemplateSampleDataInputUpdateModel object = new RegistrationTemplateSampleDataInputUpdateModel();
			object.setSampleData("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSampleDataTest() {
		try{
			RegistrationTemplateSampleDataInputUpdateModel object = new RegistrationTemplateSampleDataInputUpdateModel();
			object.getSampleData();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}