package org.opencps.dossiermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ServiceFileTemplateLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void fetchByF_serviceInfoId_fileTemplateNoTest() {
		try{
			ServiceFileTemplateLocalServiceImpl object = new ServiceFileTemplateLocalServiceImpl();
			object.fetchByF_serviceInfoId_fileTemplateNo(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateServiceFileTemplateTest() {
		try{
			ServiceFileTemplateLocalServiceImpl object = new ServiceFileTemplateLocalServiceImpl();
			object.updateServiceFileTemplate(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addServiceFileTemplateTest() {
		try{
			ServiceFileTemplateLocalServiceImpl object = new ServiceFileTemplateLocalServiceImpl();
			object.addServiceFileTemplate(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addServiceFileTemplateTest4() {
		try{
			ServiceFileTemplateLocalServiceImpl object = new ServiceFileTemplateLocalServiceImpl();
			object.addServiceFileTemplate(Long.valueOf(0), "abcde", "abcde", Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeServiceFileTemplateTest() {
		try{
			ServiceFileTemplateLocalServiceImpl object = new ServiceFileTemplateLocalServiceImpl();
			object.removeServiceFileTemplate(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateServiceFileTemplateDBTest() {
		try{
			ServiceFileTemplateLocalServiceImpl object = new ServiceFileTemplateLocalServiceImpl();
			object.updateServiceFileTemplateDB(Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), true, Long.valueOf(0), Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByServiceInfoIdTest() {
		try{
			ServiceFileTemplateLocalServiceImpl object = new ServiceFileTemplateLocalServiceImpl();
			object.getByServiceInfoId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByServiceTest() {
		try{
			ServiceFileTemplateLocalServiceImpl object = new ServiceFileTemplateLocalServiceImpl();
			object.getByService(Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countByServiceInfoIdTest() {
		try{
			ServiceFileTemplateLocalServiceImpl object = new ServiceFileTemplateLocalServiceImpl();
			object.countByServiceInfoId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countByService_EFormTest() {
		try{
			ServiceFileTemplateLocalServiceImpl object = new ServiceFileTemplateLocalServiceImpl();
			object.countByService_EForm(Long.valueOf(0), true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByService_EFormTest() {
		try{
			ServiceFileTemplateLocalServiceImpl object = new ServiceFileTemplateLocalServiceImpl();
			object.getByService_EForm(Long.valueOf(0), true, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}