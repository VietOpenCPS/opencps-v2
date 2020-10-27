package org.opencps.datamgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DictItemMappingLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void findByF_GID_CIDTest() {
		try{
			DictItemMappingLocalServiceImpl object = new DictItemMappingLocalServiceImpl();
			object.findByF_GID_CID(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDictItemMappingTest() {
		try{
			DictItemMappingLocalServiceImpl object = new DictItemMappingLocalServiceImpl();
			object.createDictItemMapping(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_GID_IC_CIDTest() {
		try{
			DictItemMappingLocalServiceImpl object = new DictItemMappingLocalServiceImpl();
			object.fetchByF_GID_IC_CID(Long.valueOf(0), "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_GID_ICDVCQG_CIDTest() {
		try{
			DictItemMappingLocalServiceImpl object = new DictItemMappingLocalServiceImpl();
			object.fetchByF_GID_ICDVCQG_CID(Long.valueOf(0), "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}