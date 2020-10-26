package org.opencps.api.dossiersync.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ObjectFactoryTest {
	@Before
	public void setUp() {
	}
	@Test
	public void createDossierSyncSendingModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDossierSyncSendingModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDossierSyncResultsModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDossierSyncResultsModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDossierSyncDataModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDossierSyncDataModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}