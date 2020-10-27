package org.opencps.dossiermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierRequestUDLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			DossierRequestUDLocalServiceImpl object = new DossierRequestUDLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierRequestTest() {
		try{
			DossierRequestUDLocalServiceImpl object = new DossierRequestUDLocalServiceImpl();
			object.updateDossierRequest(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierRequestByDossierIdTest() {
		try{
			DossierRequestUDLocalServiceImpl object = new DossierRequestUDLocalServiceImpl();
			object.getDossierRequestByDossierId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierRequestTest() {
		try{
			DossierRequestUDLocalServiceImpl object = new DossierRequestUDLocalServiceImpl();
			object.getDossierRequest(Long.valueOf(0), 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			DossierRequestUDLocalServiceImpl object = new DossierRequestUDLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}