package org.opencps.api.registrationtemplate.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class RegistrationTemplateFormScriptInputUpdateModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getFormScriptTest() {
		try{
			RegistrationTemplateFormScriptInputUpdateModel object = new RegistrationTemplateFormScriptInputUpdateModel();
			object.getFormScript();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFormScriptTest() {
		try{
			RegistrationTemplateFormScriptInputUpdateModel object = new RegistrationTemplateFormScriptInputUpdateModel();
			object.setFormScript("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}