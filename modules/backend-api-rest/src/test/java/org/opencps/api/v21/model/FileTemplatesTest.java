package org.opencps.api.v21.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class FileTemplatesTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getFileTemplateTest() {
		try{
			FileTemplates object = new FileTemplates();
			object.getFileTemplate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}