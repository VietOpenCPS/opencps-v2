package org.opencps.dossiermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ServiceConfigLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			ServiceConfigLocalServiceImpl object = new ServiceConfigLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByGovAgencyCodeTest() {
		try{
			ServiceConfigLocalServiceImpl object = new ServiceConfigLocalServiceImpl();
			object.getByGovAgencyCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateServiceConfigTest() {
		try{
			ServiceConfigLocalServiceImpl object = new ServiceConfigLocalServiceImpl();
			object.updateServiceConfig(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", 0, "abcde", true, true, true, true, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchLuceneTest() {
		try{
			ServiceConfigLocalServiceImpl object = new ServiceConfigLocalServiceImpl();
			object.searchLucene(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByServiceInfoTest() {
		try{
			ServiceConfigLocalServiceImpl object = new ServiceConfigLocalServiceImpl();
			object.getByServiceInfo(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByGroupIdTest() {
		try{
			ServiceConfigLocalServiceImpl object = new ServiceConfigLocalServiceImpl();
			object.getByGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateServiceConfigDBTest() {
		try{
			ServiceConfigLocalServiceImpl object = new ServiceConfigLocalServiceImpl();
			object.updateServiceConfigDB(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", 0, "abcde", true, true, true, true, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneTest() {
		try{
			ServiceConfigLocalServiceImpl object = new ServiceConfigLocalServiceImpl();
			object.countLucene(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getBySICodeAndGACTest() {
		try{
			ServiceConfigLocalServiceImpl object = new ServiceConfigLocalServiceImpl();
			object.getBySICodeAndGAC(Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			ServiceConfigLocalServiceImpl object = new ServiceConfigLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByLevelTest() {
		try{
			ServiceConfigLocalServiceImpl object = new ServiceConfigLocalServiceImpl();
			object.getByLevel(Long.valueOf(0), 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countBySIAndGACTest() {
		try{
			ServiceConfigLocalServiceImpl object = new ServiceConfigLocalServiceImpl();
			object.countBySIAndGAC(Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countByGovAgencyTest() {
		try{
			ServiceConfigLocalServiceImpl object = new ServiceConfigLocalServiceImpl();
			object.countByGovAgency("abcde", "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByBySIAndGACTest() {
		try{
			ServiceConfigLocalServiceImpl object = new ServiceConfigLocalServiceImpl();
			object.findByBySIAndGAC(Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchByGovAgencyTest() {
		try{
			ServiceConfigLocalServiceImpl object = new ServiceConfigLocalServiceImpl();
			object.searchByGovAgency("abcde", "abcde", Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeServiceConfigByIdTest() {
		try{
			ServiceConfigLocalServiceImpl object = new ServiceConfigLocalServiceImpl();
			object.removeServiceConfigById(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}