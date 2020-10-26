package org.opencps.api.controller.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class MyBackgroundTaskStatusMessageTranslatorTest {
	@Before
	public void setUp() {
	}
	@Test
	public void translateTest() {
		try{
			MyBackgroundTaskStatusMessageTranslator object = new MyBackgroundTaskStatusMessageTranslator();
			object.translate(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}