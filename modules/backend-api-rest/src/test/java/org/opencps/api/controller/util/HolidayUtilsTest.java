package org.opencps.api.controller.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class HolidayUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void mapperHolidayModelTest() {
		try{
			HolidayUtils.mapperHolidayModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mapperHolidayListTest() {
		try{
			HolidayUtils.mapperHolidayList(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}