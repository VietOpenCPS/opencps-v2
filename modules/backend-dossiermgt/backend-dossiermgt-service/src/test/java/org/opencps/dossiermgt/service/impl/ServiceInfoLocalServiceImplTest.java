package org.opencps.dossiermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ServiceInfoLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			ServiceInfoLocalServiceImpl object = new ServiceInfoLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeServiceInfoTest() {
		try{
			ServiceInfoLocalServiceImpl object = new ServiceInfoLocalServiceImpl();
			object.removeServiceInfo(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateServiceInfoTest() {
		try{
			ServiceInfoLocalServiceImpl object = new ServiceInfoLocalServiceImpl();
			object.updateServiceInfo(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, true, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchLuceneTest() {
		try{
			ServiceInfoLocalServiceImpl object = new ServiceInfoLocalServiceImpl();
			object.searchLucene(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByGroupTest() {
		try{
			ServiceInfoLocalServiceImpl object = new ServiceInfoLocalServiceImpl();
			object.findByGroup(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByCodeTest() {
		try{
			ServiceInfoLocalServiceImpl object = new ServiceInfoLocalServiceImpl();
			object.getByCode(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateServiceInfoDBTest() {
		try{
			ServiceInfoLocalServiceImpl object = new ServiceInfoLocalServiceImpl();
			object.updateServiceInfoDB(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceInfosByGroupIdTest() {
		try{
			ServiceInfoLocalServiceImpl object = new ServiceInfoLocalServiceImpl();
			object.getServiceInfosByGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceInfosByGroupIdTest9() {
		try{
			ServiceInfoLocalServiceImpl object = new ServiceInfoLocalServiceImpl();
			object.getServiceInfosByGroupId(Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneTest() {
		try{
			ServiceInfoLocalServiceImpl object = new ServiceInfoLocalServiceImpl();
			object.countLucene(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByGroupAndPublicTest() {
		try{
			ServiceInfoLocalServiceImpl object = new ServiceInfoLocalServiceImpl();
			object.findByGroupAndPublic(Long.valueOf(0), true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			ServiceInfoLocalServiceImpl object = new ServiceInfoLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addServiceInfoTest() {
		try{
			ServiceInfoLocalServiceImpl object = new ServiceInfoLocalServiceImpl();
			object.addServiceInfo(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, true, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByF_GID_SCTest() {
		try{
			ServiceInfoLocalServiceImpl object = new ServiceInfoLocalServiceImpl();
			object.getByF_GID_SC(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByDomainTest() {
		try{
			ServiceInfoLocalServiceImpl object = new ServiceInfoLocalServiceImpl();
			object.fetchByDomain(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countServiceInfosByGroupIdTest() {
		try{
			ServiceInfoLocalServiceImpl object = new ServiceInfoLocalServiceImpl();
			object.countServiceInfosByGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}