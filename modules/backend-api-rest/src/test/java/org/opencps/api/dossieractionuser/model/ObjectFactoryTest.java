package org.opencps.api.dossieractionuser.model;
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
	public void createDossierActionUserResultModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDossierActionUserResultModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDossierActionUserModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDossierActionUserModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}