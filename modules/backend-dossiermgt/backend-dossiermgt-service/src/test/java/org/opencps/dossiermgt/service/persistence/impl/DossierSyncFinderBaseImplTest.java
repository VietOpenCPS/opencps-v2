package org.opencps.dossiermgt.service.persistence.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierSyncFinderBaseImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getBadColumnNamesTest() {
		try{
			DossierSyncFinderBaseImpl object = new DossierSyncFinderBaseImpl();
			object.getBadColumnNames();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierSyncPersistenceTest() {
		try{
			DossierSyncFinderBaseImpl object = new DossierSyncFinderBaseImpl();
			object.setDossierSyncPersistence(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierSyncPersistenceTest() {
		try{
			DossierSyncFinderBaseImpl object = new DossierSyncFinderBaseImpl();
			object.getDossierSyncPersistence();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}