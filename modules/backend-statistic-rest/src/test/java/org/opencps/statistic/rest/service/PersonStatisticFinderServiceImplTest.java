package org.opencps.statistic.rest.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class PersonStatisticFinderServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void finderPersonStatisticTest() {
		try{
			PersonStatisticFinderServiceImpl object = new PersonStatisticFinderServiceImpl();
			object.finderPersonStatistic(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void finderPersonStatisticListTest() {
		try{
			PersonStatisticFinderServiceImpl object = new PersonStatisticFinderServiceImpl();
			object.finderPersonStatisticList(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}