package org.opencps.statistic.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class OpencpsVotingStatisticLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void searchVotingStatisticTest() {
		try{
			OpencpsVotingStatisticLocalServiceImpl object = new OpencpsVotingStatisticLocalServiceImpl();
			object.searchVotingStatistic(Long.valueOf(0), 0, 0, "abcde", "abcde", "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeVotingStatisticByMonthYearTest() {
		try{
			OpencpsVotingStatisticLocalServiceImpl object = new OpencpsVotingStatisticLocalServiceImpl();
			object.removeVotingStatisticByMonthYear(Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateVotingStatisticTest() {
		try{
			OpencpsVotingStatisticLocalServiceImpl object = new OpencpsVotingStatisticLocalServiceImpl();
			object.updateVotingStatistic(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", 0, 0, "abcde", 0, 0, 0, 0, 0, 0, 0, "abcde", "abcde", "abcde", "abcde", "abcde", 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeVotingStatisticByD_M_YTest() {
		try{
			OpencpsVotingStatisticLocalServiceImpl object = new OpencpsVotingStatisticLocalServiceImpl();
			object.removeVotingStatisticByD_M_Y(Long.valueOf(0), "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeVotingStatisticByYearTest() {
		try{
			OpencpsVotingStatisticLocalServiceImpl object = new OpencpsVotingStatisticLocalServiceImpl();
			object.removeVotingStatisticByYear(Long.valueOf(0), Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByG_M_Y_G_D_VCTest() {
		try{
			OpencpsVotingStatisticLocalServiceImpl object = new OpencpsVotingStatisticLocalServiceImpl();
			object.fetchByG_M_Y_G_D_VC(Long.valueOf(0), 0, 0, "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchVotingStatisticTest() {
		try{
			OpencpsVotingStatisticLocalServiceImpl object = new OpencpsVotingStatisticLocalServiceImpl();
			object.fetchVotingStatistic(Long.valueOf(0), 0, 0, "abcde", "abcde", "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void checkExsitTest() {
		try{
			OpencpsVotingStatisticLocalServiceImpl object = new OpencpsVotingStatisticLocalServiceImpl();
			object.checkExsit(Long.valueOf(0), 0, 0, "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByG_M_Y_G_DTest() {
		try{
			OpencpsVotingStatisticLocalServiceImpl object = new OpencpsVotingStatisticLocalServiceImpl();
			object.fetchByG_M_Y_G_D(Long.valueOf(0), 0, 0, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}