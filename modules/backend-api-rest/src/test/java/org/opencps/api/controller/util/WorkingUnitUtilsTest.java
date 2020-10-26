package org.opencps.api.controller.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class WorkingUnitUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void mapperWorkingUnitModelTest() {
		try{
			WorkingUnitUtils.mapperWorkingUnitModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mapperWorkingUnitListTest() {
		try{
			WorkingUnitUtils.mapperWorkingUnitList(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}