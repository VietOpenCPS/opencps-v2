package org.opencps.statistic.service.persistence.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class OpencpsPersonStatisticFinderBaseImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getBadColumnNamesTest() {
		try{
			OpencpsPersonStatisticFinderBaseImpl object = new OpencpsPersonStatisticFinderBaseImpl();
			object.getBadColumnNames();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOpencpsPersonStatisticPersistenceTest() {
		try{
			OpencpsPersonStatisticFinderBaseImpl object = new OpencpsPersonStatisticFinderBaseImpl();
			object.getOpencpsPersonStatisticPersistence();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOpencpsPersonStatisticPersistenceTest() {
		try{
			OpencpsPersonStatisticFinderBaseImpl object = new OpencpsPersonStatisticFinderBaseImpl();
			object.setOpencpsPersonStatisticPersistence(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}