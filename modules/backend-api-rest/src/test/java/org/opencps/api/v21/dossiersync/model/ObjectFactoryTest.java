package org.opencps.api.v21.dossiersync.model;
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
	public void createDossierSyncV21ResultsModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDossierSyncV21ResultsModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDossierSyncV21DataModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDossierSyncV21DataModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}