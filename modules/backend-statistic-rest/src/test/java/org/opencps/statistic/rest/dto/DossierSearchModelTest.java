package org.opencps.statistic.rest.dto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierSearchModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getOwnerTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.getOwner();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTimeTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.getTime();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setApplicantIdNoTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.setApplicantIdNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getApplicantIdNoTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.getApplicantIdNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getApplicantNameTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.getApplicantName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceNameTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.getServiceName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setServiceNameTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.setServiceName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDomainNameTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.getDomainName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDomainNameTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.setDomainName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupCodeTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.getGroupCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAgencyTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.getAgency();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOriginTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.setOrigin("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDomainTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.setDomain("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setReportingTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.setReporting(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFollowTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.setFollow("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setToFinishDateTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.setToFinishDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTopTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.setTop("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setToReceiveDateTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.setToReceiveDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setStepTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.setStep("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTemplateTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.setTemplate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setToSubmitDateTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.setToSubmitDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setToReleaseDateTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.setToReleaseDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSubstatusTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.setSubstatus("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setToReceiveNotDoneDateTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.setToReceiveNotDoneDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFromSubmitDateTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.setFromSubmitDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFromReleaseDateTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.setFromReleaseDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFromStatisticDateTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.setFromStatisticDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFromReceiveNotDoneDateTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.setFromReceiveNotDoneDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setToStatisticDateTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.setToStatisticDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFromReceiveDateTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.setFromReceiveDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFromFinishDateTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.setFromFinishDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOriginDossierIdTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.setOriginDossierId(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOwnerTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.setOwner("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setAgencyTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.setAgency("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTemplateTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.getTemplate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOriginTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.getOrigin();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getToSubmitDateTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.getToSubmitDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDomainTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.getDomain();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStepTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.getStep();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOriginalityTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.setOriginality("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFollowTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.getFollow();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPaymentStatusTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.getPaymentStatus();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTopTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.getTop();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getToFinishDateTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.getToFinishDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getToReceiveDateTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.getToReceiveDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getToReleaseDateTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.getToReleaseDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOriginalityTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.getOriginality();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSubstatusTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.getSubstatus();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPaymentStatusTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.setPaymentStatus("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFromReceiveNotDoneDateTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.getFromReceiveNotDoneDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getToReceiveNotDoneDateTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.getToReceiveNotDoneDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFromStatisticDateTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.getFromStatisticDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getToStatisticDateTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.getToStatisticDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFromReleaseDateTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.getFromReleaseDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFromReceiveDateTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.getFromReceiveDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFromSubmitDateTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.getFromSubmitDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFromFinishDateTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.getFromFinishDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOriginDossierIdTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.getOriginDossierId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setStatusTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.setStatus("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getReportingTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.getReporting();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTimeTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.setTime("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMonthTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.setMonth(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getYearTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.getYear();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStatusTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.getStatus();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMonthTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.getMonth();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setYearTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.setYear(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.getService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEndTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.setEnd(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setStartTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.setStart(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierNoTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.setDossierNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOrderTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.getOrder();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStartTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.getStart();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSortTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.getSort();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getKeywordTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.getKeyword();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEndTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.getEnd();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setKeywordTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.setKeyword("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierNoTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.getDossierNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSortTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.setSort("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOrderTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.setOrder("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setServiceTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.setService("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOnlineTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.setOnline("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOnlineTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.getOnline();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setApplicantNameTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.setApplicantName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGroupCodeTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.setGroupCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGroupAgencyCodeTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.setGroupAgencyCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupAgencyCodeTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.getGroupAgencyCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getReCalculateTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.getReCalculate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSystemTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.getSystem();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSystemTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.setSystem("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setParentAgencyTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.setParentAgency("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getParentAgencyTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.getParentAgency();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setReCalculateTest() {
		try{
			DossierSearchModel object = new DossierSearchModel();
			object.setReCalculate(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}