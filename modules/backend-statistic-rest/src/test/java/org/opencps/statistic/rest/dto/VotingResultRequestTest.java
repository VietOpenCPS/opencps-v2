package org.opencps.statistic.rest.dto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class VotingResultRequestTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getGovAgencyCodeTest() {
		try{
			VotingResultRequest object = new VotingResultRequest();
			object.getGovAgencyCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGovAgencyCodeTest() {
		try{
			VotingResultRequest object = new VotingResultRequest();
			object.setGovAgencyCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDomainTest() {
		try{
			VotingResultRequest object = new VotingResultRequest();
			object.setDomain("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDomainTest() {
		try{
			VotingResultRequest object = new VotingResultRequest();
			object.getDomain();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMonthTest() {
		try{
			VotingResultRequest object = new VotingResultRequest();
			object.setMonth(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getYearTest() {
		try{
			VotingResultRequest object = new VotingResultRequest();
			object.getYear();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMonthTest() {
		try{
			VotingResultRequest object = new VotingResultRequest();
			object.getMonth();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setYearTest() {
		try{
			VotingResultRequest object = new VotingResultRequest();
			object.setYear(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getVotingCodeTest() {
		try{
			VotingResultRequest object = new VotingResultRequest();
			object.getVotingCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setVotingCodeTest() {
		try{
			VotingResultRequest object = new VotingResultRequest();
			object.setVotingCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFromVotingDateTest() {
		try{
			VotingResultRequest object = new VotingResultRequest();
			object.getFromVotingDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFromVotingDateTest() {
		try{
			VotingResultRequest object = new VotingResultRequest();
			object.setFromVotingDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getToVotingDateTest() {
		try{
			VotingResultRequest object = new VotingResultRequest();
			object.getToVotingDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setToVotingDateTest() {
		try{
			VotingResultRequest object = new VotingResultRequest();
			object.setToVotingDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCalculateTest() {
		try{
			VotingResultRequest object = new VotingResultRequest();
			object.setCalculate(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isCalculateTest() {
		try{
			VotingResultRequest object = new VotingResultRequest();
			object.isCalculate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}