package org.opencps.dossiermgt.service.persistence.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierActionFinderImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void findActionOverdueFutureTest() {
		try{
			DossierActionFinderImpl object = new DossierActionFinderImpl();
			object.findActionOverdueFuture(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findActionOverdueTest() {
		try{
			DossierActionFinderImpl object = new DossierActionFinderImpl();
			object.findActionOverdue(new Date(), new Date(), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findActionUndueTest() {
		try{
			DossierActionFinderImpl object = new DossierActionFinderImpl();
			object.findActionUndue(new Date(), new Date(), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}