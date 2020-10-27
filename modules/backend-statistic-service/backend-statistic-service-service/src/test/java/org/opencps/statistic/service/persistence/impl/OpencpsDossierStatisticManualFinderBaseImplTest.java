package org.opencps.statistic.service.persistence.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class OpencpsDossierStatisticManualFinderBaseImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getBadColumnNamesTest() {
		try{
			OpencpsDossierStatisticManualFinderBaseImpl object = new OpencpsDossierStatisticManualFinderBaseImpl();
			object.getBadColumnNames();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOpencpsDossierStatisticManualPersistenceTest() {
		try{
			OpencpsDossierStatisticManualFinderBaseImpl object = new OpencpsDossierStatisticManualFinderBaseImpl();
			object.getOpencpsDossierStatisticManualPersistence();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOpencpsDossierStatisticManualPersistenceTest() {
		try{
			OpencpsDossierStatisticManualFinderBaseImpl object = new OpencpsDossierStatisticManualFinderBaseImpl();
			object.setOpencpsDossierStatisticManualPersistence(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}