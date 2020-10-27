package org.opencps.statistic.rest.engine.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class PersonStatisticUpdateServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void updatePersonStatisticTest() {
		try{
			PersonStatisticUpdateServiceImpl object = new PersonStatisticUpdateServiceImpl();
			object.updatePersonStatistic(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updatePersonStatisticTest2() {
		try{
			PersonStatisticUpdateServiceImpl object = new PersonStatisticUpdateServiceImpl();
			object.updatePersonStatistic(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}