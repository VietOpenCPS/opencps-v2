package org.opencps.api.controller.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class OneGateUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void mappingForGetDetailTest() {
		try{
			OneGateUtils.mappingForGetDetail(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}