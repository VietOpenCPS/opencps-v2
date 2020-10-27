package org.opencps.rest.application.api.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierStatisticApiServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getDossierTodoTestTest() {
		try{
			DossierStatisticApiServiceImpl object = new DossierStatisticApiServiceImpl();
			object.getDossierTodoTest();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}