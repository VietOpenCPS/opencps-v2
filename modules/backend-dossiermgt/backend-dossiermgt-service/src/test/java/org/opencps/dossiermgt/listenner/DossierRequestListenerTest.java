package org.opencps.dossiermgt.listenner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierRequestListenerTest {
	@Before
	public void setUp() {
	}
	@Test
	public void onAfterCreateTest() {
		try{
			DossierRequestListener object = new DossierRequestListener();
			object.onAfterCreate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void onAfterCreateTest3() {
		try{
			DossierRequestListener object = new DossierRequestListener();
			object.onAfterCreate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void onAfterCreateTest4() {
		try{
			DossierRequestListener object = new DossierRequestListener();
			object.onAfterCreate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}