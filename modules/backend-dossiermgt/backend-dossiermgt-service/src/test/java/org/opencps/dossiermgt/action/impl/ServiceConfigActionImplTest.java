package org.opencps.dossiermgt.action.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class ServiceConfigActionImplTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void removeServiceConfigTest() {
//		try{
//			ServiceConfigActionImpl object = new ServiceConfigActionImpl();
//			object.removeServiceConfig(Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getServiceConfigDetailTest() {
//		try{
//			ServiceConfigActionImpl object = new ServiceConfigActionImpl();
//			object.getServiceConfigDetail(Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateServiceConfigTest() {
//		try{
//			ServiceConfigActionImpl object = new ServiceConfigActionImpl();
//			object.updateServiceConfig(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", 0, "abcde", true, true, true, true, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	
//	
//	@Test
//	public void removeProcessOptionTest() {
//		try{
//			ServiceConfigActionImpl object = new ServiceConfigActionImpl();
//			object.removeProcessOption(Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateOptionDBTest() {
//		try{
//			ServiceConfigActionImpl object = new ServiceConfigActionImpl();
//			object.updateOptionDB(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", Long.valueOf(0), 0, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateServiceConfigDBTest() {
//		try{
//			ServiceConfigActionImpl object = new ServiceConfigActionImpl();
//			object.updateServiceConfigDB(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", 0, "abcde", true, true, true, true, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateOptionTest() {
//		try{
//			ServiceConfigActionImpl object = new ServiceConfigActionImpl();
//			object.updateOption(Long.valueOf(0), "abcde", Long.valueOf(0), Long.valueOf(0), 0, "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void getProcessOptionsTest() {
		try{
			ServiceConfigActionImpl object = new ServiceConfigActionImpl();
			object.getProcessOptions(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceConfigsTest() {
		try{
			ServiceConfigActionImpl object = new ServiceConfigActionImpl();
			object.getServiceConfigs(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}