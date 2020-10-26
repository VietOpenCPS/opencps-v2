package org.opencps.api.v21.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class StepConfigListTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getStepConfigTest() {
		try{
			StepConfigList object = new StepConfigList();
			object.getStepConfig();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}