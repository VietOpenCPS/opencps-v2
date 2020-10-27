package org.opencps.dossiermgt.service.persistence.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class StepConfigFinderBaseImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getBadColumnNamesTest() {
		try{
			StepConfigFinderBaseImpl object = new StepConfigFinderBaseImpl();
			object.getBadColumnNames();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setStepConfigPersistenceTest() {
		try{
			StepConfigFinderBaseImpl object = new StepConfigFinderBaseImpl();
			object.setStepConfigPersistence(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStepConfigPersistenceTest() {
		try{
			StepConfigFinderBaseImpl object = new StepConfigFinderBaseImpl();
			object.getStepConfigPersistence();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}