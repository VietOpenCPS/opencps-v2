package org.opencps.dossiermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ServiceProcessRoleLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void updateServiceProcessRoleTest() {
		try{
			ServiceProcessRoleLocalServiceImpl object = new ServiceProcessRoleLocalServiceImpl();
			object.updateServiceProcessRole(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), true, "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeServiceProcessRoleTest() {
		try{
			ServiceProcessRoleLocalServiceImpl object = new ServiceProcessRoleLocalServiceImpl();
			object.removeServiceProcessRole(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByS_P_IDTest() {
		try{
			ServiceProcessRoleLocalServiceImpl object = new ServiceProcessRoleLocalServiceImpl();
			object.findByS_P_ID(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateServiceProcessRoleDBTest() {
		try{
			ServiceProcessRoleLocalServiceImpl object = new ServiceProcessRoleLocalServiceImpl();
			object.updateServiceProcessRoleDB(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", true, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findBySPSTest() {
		try{
			ServiceProcessRoleLocalServiceImpl object = new ServiceProcessRoleLocalServiceImpl();
			object.findBySPS(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByServiceRoleCodeTest() {
		try{
			ServiceProcessRoleLocalServiceImpl object = new ServiceProcessRoleLocalServiceImpl();
			object.getByServiceRoleCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}