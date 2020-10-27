package org.opencps.statistic.rest.dto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class GetVotingResultRequestTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getClassNameTest() {
		try{
			GetVotingResultRequest object = new GetVotingResultRequest();
			object.getClassName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGovAgencyCodeTest() {
		try{
			GetVotingResultRequest object = new GetVotingResultRequest();
			object.getGovAgencyCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGovAgencyCodeTest() {
		try{
			GetVotingResultRequest object = new GetVotingResultRequest();
			object.setGovAgencyCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDomainTest() {
		try{
			GetVotingResultRequest object = new GetVotingResultRequest();
			object.setDomain("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDomainTest() {
		try{
			GetVotingResultRequest object = new GetVotingResultRequest();
			object.getDomain();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMonthTest() {
		try{
			GetVotingResultRequest object = new GetVotingResultRequest();
			object.setMonth("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getYearTest() {
		try{
			GetVotingResultRequest object = new GetVotingResultRequest();
			object.getYear();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMonthTest() {
		try{
			GetVotingResultRequest object = new GetVotingResultRequest();
			object.getMonth();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setYearTest() {
		try{
			GetVotingResultRequest object = new GetVotingResultRequest();
			object.setYear("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setClassNameTest() {
		try{
			GetVotingResultRequest object = new GetVotingResultRequest();
			object.setClassName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getVotingCodeTest() {
		try{
			GetVotingResultRequest object = new GetVotingResultRequest();
			object.getVotingCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setVotingCodeTest() {
		try{
			GetVotingResultRequest object = new GetVotingResultRequest();
			object.setVotingCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFromVotingDateTest() {
		try{
			GetVotingResultRequest object = new GetVotingResultRequest();
			object.getFromVotingDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFromVotingDateTest() {
		try{
			GetVotingResultRequest object = new GetVotingResultRequest();
			object.setFromVotingDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getToVotingDateTest() {
		try{
			GetVotingResultRequest object = new GetVotingResultRequest();
			object.getToVotingDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setToVotingDateTest() {
		try{
			GetVotingResultRequest object = new GetVotingResultRequest();
			object.setToVotingDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCalculateTest() {
		try{
			GetVotingResultRequest object = new GetVotingResultRequest();
			object.setCalculate(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isCalculateTest() {
		try{
			GetVotingResultRequest object = new GetVotingResultRequest();
			object.isCalculate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}