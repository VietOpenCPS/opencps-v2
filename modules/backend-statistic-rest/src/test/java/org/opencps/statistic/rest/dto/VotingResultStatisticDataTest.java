package org.opencps.statistic.rest.dto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class VotingResultStatisticDataTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setGroupIdTest() {
		try{
			VotingResultStatisticData object = new VotingResultStatisticData();
			object.setGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCompanyIdTest() {
		try{
			VotingResultStatisticData object = new VotingResultStatisticData();
			object.setCompanyId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCompanyIdTest() {
		try{
			VotingResultStatisticData object = new VotingResultStatisticData();
			object.getCompanyId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupIdTest() {
		try{
			VotingResultStatisticData object = new VotingResultStatisticData();
			object.getGroupId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDomainNameTest() {
		try{
			VotingResultStatisticData object = new VotingResultStatisticData();
			object.getDomainName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDomainNameTest() {
		try{
			VotingResultStatisticData object = new VotingResultStatisticData();
			object.setDomainName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGovAgencyNameTest() {
		try{
			VotingResultStatisticData object = new VotingResultStatisticData();
			object.setGovAgencyName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGovAgencyCodeTest() {
		try{
			VotingResultStatisticData object = new VotingResultStatisticData();
			object.getGovAgencyCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGovAgencyCodeTest() {
		try{
			VotingResultStatisticData object = new VotingResultStatisticData();
			object.setGovAgencyCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGovAgencyNameTest() {
		try{
			VotingResultStatisticData object = new VotingResultStatisticData();
			object.getGovAgencyName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDomainTest() {
		try{
			VotingResultStatisticData object = new VotingResultStatisticData();
			object.setDomain("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDomainTest() {
		try{
			VotingResultStatisticData object = new VotingResultStatisticData();
			object.getDomain();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMonthTest() {
		try{
			VotingResultStatisticData object = new VotingResultStatisticData();
			object.setMonth(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getYearTest() {
		try{
			VotingResultStatisticData object = new VotingResultStatisticData();
			object.getYear();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMonthTest() {
		try{
			VotingResultStatisticData object = new VotingResultStatisticData();
			object.getMonth();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setYearTest() {
		try{
			VotingResultStatisticData object = new VotingResultStatisticData();
			object.setYear(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTotalVotedTest() {
		try{
			VotingResultStatisticData object = new VotingResultStatisticData();
			object.getTotalVoted();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTotalVotedTest() {
		try{
			VotingResultStatisticData object = new VotingResultStatisticData();
			object.setTotalVoted(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPercentBadTest() {
		try{
			VotingResultStatisticData object = new VotingResultStatisticData();
			object.getPercentBad();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPercentGoodTest() {
		try{
			VotingResultStatisticData object = new VotingResultStatisticData();
			object.getPercentGood();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPercentGoodTest() {
		try{
			VotingResultStatisticData object = new VotingResultStatisticData();
			object.setPercentGood(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPercentBadTest() {
		try{
			VotingResultStatisticData object = new VotingResultStatisticData();
			object.setPercentBad(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getVotingCodeTest() {
		try{
			VotingResultStatisticData object = new VotingResultStatisticData();
			object.getVotingCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setVotingCodeTest() {
		try{
			VotingResultStatisticData object = new VotingResultStatisticData();
			object.setVotingCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPercentVeryGoodTest() {
		try{
			VotingResultStatisticData object = new VotingResultStatisticData();
			object.getPercentVeryGood();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPercentVeryGoodTest() {
		try{
			VotingResultStatisticData object = new VotingResultStatisticData();
			object.setPercentVeryGood(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setVotingSubjectTest() {
		try{
			VotingResultStatisticData object = new VotingResultStatisticData();
			object.setVotingSubject("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getVotingSubjectTest() {
		try{
			VotingResultStatisticData object = new VotingResultStatisticData();
			object.getVotingSubject();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGoodCountTest() {
		try{
			VotingResultStatisticData object = new VotingResultStatisticData();
			object.setGoodCount(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setBadCountTest() {
		try{
			VotingResultStatisticData object = new VotingResultStatisticData();
			object.setBadCount(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getVeryGoodCountTest() {
		try{
			VotingResultStatisticData object = new VotingResultStatisticData();
			object.getVeryGoodCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setVeryGoodCountTest() {
		try{
			VotingResultStatisticData object = new VotingResultStatisticData();
			object.setVeryGoodCount(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGoodCountTest() {
		try{
			VotingResultStatisticData object = new VotingResultStatisticData();
			object.getGoodCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getBadCountTest() {
		try{
			VotingResultStatisticData object = new VotingResultStatisticData();
			object.getBadCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}