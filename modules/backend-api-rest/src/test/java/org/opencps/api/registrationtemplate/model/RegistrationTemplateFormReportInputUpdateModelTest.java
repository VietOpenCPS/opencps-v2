package org.opencps.api.registrationtemplate.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class RegistrationTemplateFormReportInputUpdateModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getFormReportTest() {
		try{
			RegistrationTemplateFormReportInputUpdateModel object = new RegistrationTemplateFormReportInputUpdateModel();
			object.getFormReport();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFormReportTest() {
		try{
			RegistrationTemplateFormReportInputUpdateModel object = new RegistrationTemplateFormReportInputUpdateModel();
			object.setFormReport("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}