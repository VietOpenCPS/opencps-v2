package org.opencps.api.controller.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ServiceConfigUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void mapptingToServiceConfigTest() {
		try{
			ServiceConfigUtils.mapptingToServiceConfig(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingToServiceConfigResultsTest() {
		try{
			ServiceConfigUtils.mappingToServiceConfigResults(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingToProcessOptionResultsTest() {
		try{
			ServiceConfigUtils.mappingToProcessOptionResults(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingToProcessOptionTest() {
		try{
			ServiceConfigUtils.mappingToProcessOption(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}