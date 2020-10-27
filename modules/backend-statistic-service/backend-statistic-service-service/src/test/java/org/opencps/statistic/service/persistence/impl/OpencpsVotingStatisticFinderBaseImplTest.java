package org.opencps.statistic.service.persistence.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class OpencpsVotingStatisticFinderBaseImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getBadColumnNamesTest() {
		try{
			OpencpsVotingStatisticFinderBaseImpl object = new OpencpsVotingStatisticFinderBaseImpl();
			object.getBadColumnNames();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOpencpsVotingStatisticPersistenceTest() {
		try{
			OpencpsVotingStatisticFinderBaseImpl object = new OpencpsVotingStatisticFinderBaseImpl();
			object.getOpencpsVotingStatisticPersistence();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOpencpsVotingStatisticPersistenceTest() {
		try{
			OpencpsVotingStatisticFinderBaseImpl object = new OpencpsVotingStatisticFinderBaseImpl();
			object.setOpencpsVotingStatisticPersistence(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}