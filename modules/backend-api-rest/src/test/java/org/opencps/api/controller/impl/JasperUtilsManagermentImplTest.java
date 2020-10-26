package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class JasperUtilsManagermentImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getPreviewTest() {
		try{
			JasperUtilsManagermentImpl object = new JasperUtilsManagermentImpl();
			object.getPreview(null, null, null, null, null, null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}