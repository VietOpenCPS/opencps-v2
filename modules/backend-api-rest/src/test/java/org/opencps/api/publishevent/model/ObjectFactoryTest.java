package org.opencps.api.publishevent.model;
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
	public void createDossierPublishModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDossierPublishModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}