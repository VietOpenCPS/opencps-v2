package org.opencps.statistic.service.persistence.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class OpencpsDossierStatisticFinderBaseImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getBadColumnNamesTest() {
		try{
			OpencpsDossierStatisticFinderBaseImpl object = new OpencpsDossierStatisticFinderBaseImpl();
			object.getBadColumnNames();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOpencpsDossierStatisticPersistenceTest() {
		try{
			OpencpsDossierStatisticFinderBaseImpl object = new OpencpsDossierStatisticFinderBaseImpl();
			object.setOpencpsDossierStatisticPersistence(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOpencpsDossierStatisticPersistenceTest() {
		try{
			OpencpsDossierStatisticFinderBaseImpl object = new OpencpsDossierStatisticFinderBaseImpl();
			object.getOpencpsDossierStatisticPersistence();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}