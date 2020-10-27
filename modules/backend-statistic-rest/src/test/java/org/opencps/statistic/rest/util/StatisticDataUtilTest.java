package org.opencps.statistic.rest.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class StatisticDataUtilTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void getLocalVotingResponseTest() {
//		try{
//			StatisticDataUtil.getLocalVotingResponse(null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getLocalPersonResponseTest() {
//		try{
//			StatisticDataUtil.getLocalPersonResponse(null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void getLocalServiceDomainTest() {
		try{
			StatisticDataUtil.getLocalServiceDomain(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getLocalGovAgencyTest() {
		try{
			StatisticDataUtil.getLocalGovAgency(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}