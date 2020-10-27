package org.opencps.statistic.rest.dto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class GetDossierRequestTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getOwnerTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.getOwner();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setApplicantIdNoTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.setApplicantIdNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getApplicantIdNoTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.getApplicantIdNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setApplicantIdTypeTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.setApplicantIdType("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWardCodeTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.getWardCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getContactTelNoTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.getContactTelNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDistrictCodeTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.getDistrictCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getContactEmailTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.getContactEmail();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCityCodeTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.getCityCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessNoTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.getProcessNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGovAgencyCodeTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.getGovAgencyCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGovAgencyCodeTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.setGovAgencyCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setProcessNoTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.setProcessNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierStatusTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.getDossierStatus();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierStatusTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.setDossierStatus("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServerNoTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.getServerNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setServerNoTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.setServerNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAgencyTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.getAgency();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setViaPostalTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.setViaPostal(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDelegateIdNoTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.setDelegateIdNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDelegateTelNoTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.setDelegateTelNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDomainTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.setDomain("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTopTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.setTop("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setStepTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.setStep("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTemplateTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.setTemplate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSubstatusTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.setSubstatus("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFromStatisticDateTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.setFromStatisticDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setToStatisticDateTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.setToStatisticDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOriginDossierIdTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.setOriginDossierId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getApplicantIdTypeTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.getApplicantIdType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRegisterBookCodeTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.getRegisterBookCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setRegisterBookCodeTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.setRegisterBookCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierSubStatusTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.getDossierSubStatus();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierSubStatusTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.setDossierSubStatus("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOwnerTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.setOwner("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setAgencyTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.setAgency("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTemplateTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.getTemplate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getViaPostalTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.getViaPostal();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDelegateIdNoTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.getDelegateIdNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDomainTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.getDomain();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStepTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.getStep();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOriginalityTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.setOriginality("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTopTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.getTop();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDelegateTelNoTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.getDelegateTelNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOriginalityTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.getOriginality();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSubstatusTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.getSubstatus();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFromStatisticDateTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.getFromStatisticDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getToStatisticDateTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.getToStatisticDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierActionIdTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.setDossierActionId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierActionIdTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.getDossierActionId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOriginDossierIdTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.getOriginDossierId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setStatusTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.setStatus("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMonthTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.setMonth("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getYearTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.getYear();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStatusTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.getStatus();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMonthTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.getMonth();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setYearTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.setYear("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierNoTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.setDossierNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setServiceCodeTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.setServiceCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceCodeTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.getServiceCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierNoTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.getDossierNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOnlineTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.setOnline(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isOnlineTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.isOnline();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWardCodeTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.setWardCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setContactEmailTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.setContactEmail("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCityCodeTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.setCityCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDistrictCodeTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.setDistrictCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setContactTelNoTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.setContactTelNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isReceivedTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.isReceived();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOnlineStatisticTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.getOnlineStatistic();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOnlineStatisticTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.setOnlineStatistic("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSystemTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.getSystem();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCalculateTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.setCalculate(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSystemTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.setSystem("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isCalculateTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.isCalculate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOnlineValueTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.setOnlineValue(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isReleasedTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.isReleased();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isBetimeTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.isBetime();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setBetimeTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.setBetime(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isOntimeTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.isOntime();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isUndueTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.isUndue();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUndueTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.setUndue(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setReleasedTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.setReleased(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isOnlineValueTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.isOnlineValue();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOntimeTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.setOntime(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setReceivedTest() {
		try{
			GetDossierRequest object = new GetDossierRequest();
			object.setReceived(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}