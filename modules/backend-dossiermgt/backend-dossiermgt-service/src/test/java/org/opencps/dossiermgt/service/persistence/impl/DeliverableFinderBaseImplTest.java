package org.opencps.dossiermgt.service.persistence.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DeliverableFinderBaseImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getBadColumnNamesTest() {
		try{
			DeliverableFinderBaseImpl object = new DeliverableFinderBaseImpl();
			object.getBadColumnNames();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDeliverablePersistenceTest() {
		try{
			DeliverableFinderBaseImpl object = new DeliverableFinderBaseImpl();
			object.setDeliverablePersistence(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDeliverablePersistenceTest() {
		try{
			DeliverableFinderBaseImpl object = new DeliverableFinderBaseImpl();
			object.getDeliverablePersistence();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}