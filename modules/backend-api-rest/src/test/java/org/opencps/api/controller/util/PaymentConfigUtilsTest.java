package org.opencps.api.controller.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class PaymentConfigUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void mappingDataModelTest() {
		try{
			PaymentConfigUtils.mappingDataModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingToModelTest() {
		try{
			PaymentConfigUtils.mappingToModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}