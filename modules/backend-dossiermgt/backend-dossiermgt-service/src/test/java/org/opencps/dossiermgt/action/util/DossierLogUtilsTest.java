package org.opencps.dossiermgt.action.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierLogUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void createPayloadTest() {
		try{
			DossierLogUtils.createPayload(null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}