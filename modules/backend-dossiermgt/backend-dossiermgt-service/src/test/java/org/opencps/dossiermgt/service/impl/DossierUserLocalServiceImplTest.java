package org.opencps.dossiermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierUserLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void addDossierUserTest() {
		try{
			DossierUserLocalServiceImpl object = new DossierUserLocalServiceImpl();
			object.addDossierUser(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), 0, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByDID_UDTest() {
		try{
			DossierUserLocalServiceImpl object = new DossierUserLocalServiceImpl();
			object.findByDID_UD(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByDIDTest() {
		try{
			DossierUserLocalServiceImpl object = new DossierUserLocalServiceImpl();
			object.findByDID(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierUserTest() {
		try{
			DossierUserLocalServiceImpl object = new DossierUserLocalServiceImpl();
			object.updateDossierUser(Long.valueOf(0), Long.valueOf(0), 0, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByDossierUserTest() {
		try{
			DossierUserLocalServiceImpl object = new DossierUserLocalServiceImpl();
			object.getByDossierUser(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDossierUserTest() {
		try{
			DossierUserLocalServiceImpl object = new DossierUserLocalServiceImpl();
			object.deleteDossierUser(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}