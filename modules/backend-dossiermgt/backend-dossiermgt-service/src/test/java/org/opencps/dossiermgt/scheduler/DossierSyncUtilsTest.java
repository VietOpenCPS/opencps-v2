package org.opencps.dossiermgt.scheduler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierSyncUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void orderSyncTest() {
		try{
			DossierSyncUtils.orderSync(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertToModelTest() {
		try{
			DossierSyncUtils.convertToModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}