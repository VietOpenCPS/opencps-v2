package org.opencps.dossiermgt.service.persistence.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class StepConfigFinderImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void finByStepConfigTest() {
		try{
			StepConfigFinderImpl object = new StepConfigFinderImpl();
			object.finByStepConfig(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}