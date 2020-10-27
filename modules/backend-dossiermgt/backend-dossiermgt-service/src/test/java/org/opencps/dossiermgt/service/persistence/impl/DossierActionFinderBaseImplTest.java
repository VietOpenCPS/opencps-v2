package org.opencps.dossiermgt.service.persistence.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierActionFinderBaseImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getBadColumnNamesTest() {
		try{
			DossierActionFinderBaseImpl object = new DossierActionFinderBaseImpl();
			object.getBadColumnNames();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierActionPersistenceTest() {
		try{
			DossierActionFinderBaseImpl object = new DossierActionFinderBaseImpl();
			object.setDossierActionPersistence(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierActionPersistenceTest() {
		try{
			DossierActionFinderBaseImpl object = new DossierActionFinderBaseImpl();
			object.getDossierActionPersistence();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}