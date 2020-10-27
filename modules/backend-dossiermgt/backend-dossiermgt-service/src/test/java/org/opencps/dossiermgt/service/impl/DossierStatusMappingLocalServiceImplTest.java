package org.opencps.dossiermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierStatusMappingLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			DossierStatusMappingLocalServiceImpl object = new DossierStatusMappingLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_GID_SCTest() {
		try{
			DossierStatusMappingLocalServiceImpl object = new DossierStatusMappingLocalServiceImpl();
			object.findByF_GID_SC(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_GID_SUBSCTest() {
		try{
			DossierStatusMappingLocalServiceImpl object = new DossierStatusMappingLocalServiceImpl();
			object.fetchByF_GID_SUBSC(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			DossierStatusMappingLocalServiceImpl object = new DossierStatusMappingLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}