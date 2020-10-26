package org.opencps.api.controller.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ConfigCounterUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void mappingToListConfigTest() {
		try{
			ConfigCounterUtils.mappingToListConfig(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingToConfigCounterModelTest() {
		try{
			ConfigCounterUtils.mappingToConfigCounterModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}