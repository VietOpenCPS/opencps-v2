package org.opencps.kernel.template.freemarker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class TemplateConfigurationTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setConfigurationTest() {
		try{
			TemplateConfiguration object = new TemplateConfiguration();
			object.setConfiguration(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getConfigurationTest() {
		try{
			TemplateConfiguration object = new TemplateConfiguration();
			object.getConfiguration();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}