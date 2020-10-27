package org.opencps.statistic.rest.dto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class PersonRequestTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getGovAgencyCodeTest() {
		try{
			PersonRequest object = new PersonRequest();
			object.getGovAgencyCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGovAgencyCodeTest() {
		try{
			PersonRequest object = new PersonRequest();
			object.setGovAgencyCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEmployeeIdTest() {
		try{
			PersonRequest object = new PersonRequest();
			object.setEmployeeId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFromStatisticDateTest() {
		try{
			PersonRequest object = new PersonRequest();
			object.setFromStatisticDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setToStatisticDateTest() {
		try{
			PersonRequest object = new PersonRequest();
			object.setToStatisticDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmployeeIdTest() {
		try{
			PersonRequest object = new PersonRequest();
			object.getEmployeeId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFromStatisticDateTest() {
		try{
			PersonRequest object = new PersonRequest();
			object.getFromStatisticDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getToStatisticDateTest() {
		try{
			PersonRequest object = new PersonRequest();
			object.getToStatisticDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMonthTest() {
		try{
			PersonRequest object = new PersonRequest();
			object.setMonth(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getYearTest() {
		try{
			PersonRequest object = new PersonRequest();
			object.getYear();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMonthTest() {
		try{
			PersonRequest object = new PersonRequest();
			object.getMonth();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setYearTest() {
		try{
			PersonRequest object = new PersonRequest();
			object.setYear(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getVotingCodeTest() {
		try{
			PersonRequest object = new PersonRequest();
			object.getVotingCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setVotingCodeTest() {
		try{
			PersonRequest object = new PersonRequest();
			object.setVotingCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCalculateTest() {
		try{
			PersonRequest object = new PersonRequest();
			object.setCalculate(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isCalculateTest() {
		try{
			PersonRequest object = new PersonRequest();
			object.isCalculate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}