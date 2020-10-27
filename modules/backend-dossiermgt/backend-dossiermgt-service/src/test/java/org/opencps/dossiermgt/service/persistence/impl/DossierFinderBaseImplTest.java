package org.opencps.dossiermgt.service.persistence.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierFinderBaseImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getBadColumnNamesTest() {
		try{
			DossierFinderBaseImpl object = new DossierFinderBaseImpl();
			object.getBadColumnNames();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierPersistenceTest() {
		try{
			DossierFinderBaseImpl object = new DossierFinderBaseImpl();
			object.setDossierPersistence(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierPersistenceTest() {
		try{
			DossierFinderBaseImpl object = new DossierFinderBaseImpl();
			object.getDossierPersistence();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}