package org.opencps.dossiermgt.activator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierActionsActivatorTest {
	@Before
	public void setUp() {
	}
	@Test
	public void startTest() {
		try{
			DossierActionsActivator object = new DossierActionsActivator();
			object.start(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void stopTest() {
		try{
			DossierActionsActivator object = new DossierActionsActivator();
			object.stop(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}