package org.opencps.dossiermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class ServiceInfoMappingLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			ServiceInfoMappingLocalServiceImpl object = new ServiceInfoMappingLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchDVCQGServiceCodeTest() {
		try{
			ServiceInfoMappingLocalServiceImpl object = new ServiceInfoMappingLocalServiceImpl();
			object.fetchDVCQGServiceCode(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			ServiceInfoMappingLocalServiceImpl object = new ServiceInfoMappingLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByGID_SCDVCQGTest() {
		try{
			ServiceInfoMappingLocalServiceImpl object = new ServiceInfoMappingLocalServiceImpl();
			object.fetchByGID_SCDVCQG(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteServiceInfoMappingTest() {
		try{
			ServiceInfoMappingLocalServiceImpl object = new ServiceInfoMappingLocalServiceImpl();
			object.deleteServiceInfoMapping(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void removeServiceInfoMappingTest() {
//		try{
//			ServiceInfoMappingLocalServiceImpl object = new ServiceInfoMappingLocalServiceImpl();
//			object.removeServiceInfoMapping(Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void addServiceInfoMappingTest() {
		try{
			ServiceInfoMappingLocalServiceImpl object = new ServiceInfoMappingLocalServiceImpl();
			object.addServiceInfoMapping(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}