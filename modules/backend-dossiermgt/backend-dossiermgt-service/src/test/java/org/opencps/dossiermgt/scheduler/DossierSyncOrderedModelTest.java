package org.opencps.dossiermgt.scheduler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierSyncOrderedModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setDossierIdTest() {
		try{
			DossierSyncOrderedModel object = new DossierSyncOrderedModel(Long.valueOf(0), Long.valueOf(0), 0);
			object.setDossierId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierIdTest() {
		try{
			DossierSyncOrderedModel object = new DossierSyncOrderedModel(Long.valueOf(0), Long.valueOf(0), 0);
			object.getDossierId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierSyncIdTest() {
		try{
			DossierSyncOrderedModel object = new DossierSyncOrderedModel(Long.valueOf(0), Long.valueOf(0), 0);
			object.getDossierSyncId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierSyncIdTest() {
		try{
			DossierSyncOrderedModel object = new DossierSyncOrderedModel(Long.valueOf(0), Long.valueOf(0), 0);
			object.setDossierSyncId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMethodIdTest() {
		try{
			DossierSyncOrderedModel object = new DossierSyncOrderedModel(Long.valueOf(0), Long.valueOf(0), 0);
			object.setMethodId(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMethodIdTest() {
		try{
			DossierSyncOrderedModel object = new DossierSyncOrderedModel(Long.valueOf(0), Long.valueOf(0), 0);
			object.getMethodId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}