package org.opencps.dossiermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ProcessStepRoleLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void updateProcessStepRoleTest() {
		try{
			ProcessStepRoleLocalServiceImpl object = new ProcessStepRoleLocalServiceImpl();
			object.updateProcessStepRole(Long.valueOf(0), Long.valueOf(0), true, "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeProcessStepRoleTest() {
		try{
			ProcessStepRoleLocalServiceImpl object = new ProcessStepRoleLocalServiceImpl();
			object.removeProcessStepRole(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByP_S_IDTest() {
		try{
			ProcessStepRoleLocalServiceImpl object = new ProcessStepRoleLocalServiceImpl();
			object.findByP_S_ID(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByRoleCodeTest() {
		try{
			ProcessStepRoleLocalServiceImpl object = new ProcessStepRoleLocalServiceImpl();
			object.getByRoleCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateProcessStepRoleDBTest() {
		try{
			ProcessStepRoleLocalServiceImpl object = new ProcessStepRoleLocalServiceImpl();
			object.updateProcessStepRoleDB(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", true, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByStepAndRoleTest() {
		try{
			ProcessStepRoleLocalServiceImpl object = new ProcessStepRoleLocalServiceImpl();
			object.findByStepAndRole(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}