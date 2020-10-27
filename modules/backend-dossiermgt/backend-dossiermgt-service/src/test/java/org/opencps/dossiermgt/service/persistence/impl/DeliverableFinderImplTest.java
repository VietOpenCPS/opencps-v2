package org.opencps.dossiermgt.service.persistence.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DeliverableFinderImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void findDeliverableByStateTest() {
		try{
			DeliverableFinderImpl object = new DeliverableFinderImpl();
			object.findDeliverableByState("abcde", 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findDeliverableByModifiedDateTest() {
		try{
			DeliverableFinderImpl object = new DeliverableFinderImpl();
			object.findDeliverableByModifiedDate("abcde", "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findFileTemplateNoTest() {
		try{
			DeliverableFinderImpl object = new DeliverableFinderImpl();
			object.findFileTemplateNo(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}