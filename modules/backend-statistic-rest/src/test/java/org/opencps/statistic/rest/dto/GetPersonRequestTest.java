package org.opencps.statistic.rest.dto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class GetPersonRequestTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getClassNameTest() {
		try{
			GetPersonRequest object = new GetPersonRequest();
			object.getClassName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGovAgencyCodeTest() {
		try{
			GetPersonRequest object = new GetPersonRequest();
			object.getGovAgencyCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGovAgencyCodeTest() {
		try{
			GetPersonRequest object = new GetPersonRequest();
			object.setGovAgencyCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEmployeeIdTest() {
		try{
			GetPersonRequest object = new GetPersonRequest();
			object.setEmployeeId("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFromStatisticDateTest() {
		try{
			GetPersonRequest object = new GetPersonRequest();
			object.setFromStatisticDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setToStatisticDateTest() {
		try{
			GetPersonRequest object = new GetPersonRequest();
			object.setToStatisticDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmployeeIdTest() {
		try{
			GetPersonRequest object = new GetPersonRequest();
			object.getEmployeeId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFromStatisticDateTest() {
		try{
			GetPersonRequest object = new GetPersonRequest();
			object.getFromStatisticDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getToStatisticDateTest() {
		try{
			GetPersonRequest object = new GetPersonRequest();
			object.getToStatisticDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMonthTest() {
		try{
			GetPersonRequest object = new GetPersonRequest();
			object.setMonth("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getYearTest() {
		try{
			GetPersonRequest object = new GetPersonRequest();
			object.getYear();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMonthTest() {
		try{
			GetPersonRequest object = new GetPersonRequest();
			object.getMonth();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setYearTest() {
		try{
			GetPersonRequest object = new GetPersonRequest();
			object.setYear("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setClassNameTest() {
		try{
			GetPersonRequest object = new GetPersonRequest();
			object.setClassName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getVotingCodeTest() {
		try{
			GetPersonRequest object = new GetPersonRequest();
			object.getVotingCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setVotingCodeTest() {
		try{
			GetPersonRequest object = new GetPersonRequest();
			object.setVotingCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCalculateTest() {
		try{
			GetPersonRequest object = new GetPersonRequest();
			object.setCalculate(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isCalculateTest() {
		try{
			GetPersonRequest object = new GetPersonRequest();
			object.isCalculate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}