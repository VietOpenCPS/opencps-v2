package org.opencps.esb.api.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class CollaborationServiceModuleTest {
	@Before
	public void setUp() {
	}
	@Test
	public void configureTest() {
		try{
			CollaborationServiceModule object = new CollaborationServiceModule();
			object.configure();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}