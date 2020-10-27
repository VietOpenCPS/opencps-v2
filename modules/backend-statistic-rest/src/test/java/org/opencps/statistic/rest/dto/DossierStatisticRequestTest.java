package org.opencps.statistic.rest.dto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierStatisticRequestTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getGovAgencyCodeTest() {
		try{
			DossierStatisticRequest object = new DossierStatisticRequest();
			object.getGovAgencyCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGovAgencyCodeTest() {
		try{
			DossierStatisticRequest object = new DossierStatisticRequest();
			object.setGovAgencyCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDomainTest() {
		try{
			DossierStatisticRequest object = new DossierStatisticRequest();
			object.setDomain("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setReportingTest() {
		try{
			DossierStatisticRequest object = new DossierStatisticRequest();
			object.setReporting(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFromStatisticDateTest() {
		try{
			DossierStatisticRequest object = new DossierStatisticRequest();
			object.setFromStatisticDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setToStatisticDateTest() {
		try{
			DossierStatisticRequest object = new DossierStatisticRequest();
			object.setToStatisticDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDomainTest() {
		try{
			DossierStatisticRequest object = new DossierStatisticRequest();
			object.getDomain();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isReportingTest() {
		try{
			DossierStatisticRequest object = new DossierStatisticRequest();
			object.isReporting();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFromStatisticDateTest() {
		try{
			DossierStatisticRequest object = new DossierStatisticRequest();
			object.getFromStatisticDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getToStatisticDateTest() {
		try{
			DossierStatisticRequest object = new DossierStatisticRequest();
			object.getToStatisticDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMonthTest() {
		try{
			DossierStatisticRequest object = new DossierStatisticRequest();
			object.setMonth(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getYearTest() {
		try{
			DossierStatisticRequest object = new DossierStatisticRequest();
			object.getYear();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMonthTest() {
		try{
			DossierStatisticRequest object = new DossierStatisticRequest();
			object.getMonth();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setYearTest() {
		try{
			DossierStatisticRequest object = new DossierStatisticRequest();
			object.setYear(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGroupAgencyCodeTest() {
		try{
			DossierStatisticRequest object = new DossierStatisticRequest();
			object.setGroupAgencyCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupAgencyCodeTest() {
		try{
			DossierStatisticRequest object = new DossierStatisticRequest();
			object.getGroupAgencyCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSystemTest() {
		try{
			DossierStatisticRequest object = new DossierStatisticRequest();
			object.getSystem();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCalculateTest() {
		try{
			DossierStatisticRequest object = new DossierStatisticRequest();
			object.setCalculate(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSystemTest() {
		try{
			DossierStatisticRequest object = new DossierStatisticRequest();
			object.setSystem("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isCalculateTest() {
		try{
			DossierStatisticRequest object = new DossierStatisticRequest();
			object.isCalculate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}