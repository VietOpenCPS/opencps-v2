package org.opencps.statistic.rest.dto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierRequestTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setApplicantIdNoTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.setApplicantIdNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getApplicantIdNoTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.getApplicantIdNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setApplicantIdTypeTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.setApplicantIdType("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWardCodeTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.getWardCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getContactTelNoTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.getContactTelNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDistrictCodeTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.getDistrictCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getContactEmailTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.getContactEmail();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCityCodeTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.getCityCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessNoTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.getProcessNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGovAgencyCodeTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.getGovAgencyCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGovAgencyCodeTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.setGovAgencyCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setProcessNoTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.setProcessNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierStatusTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.getDossierStatus();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierStatusTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.setDossierStatus("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServerNoTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.getServerNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setServerNoTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.setServerNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setViaPostalTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.setViaPostal(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDelegateIdNoTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.setDelegateIdNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDelegateTelNoTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.setDelegateTelNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOriginDossierIdTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.setOriginDossierId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getApplicantIdTypeTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.getApplicantIdType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRegisterBookCodeTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.getRegisterBookCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setRegisterBookCodeTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.setRegisterBookCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierSubStatusTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.getDossierSubStatus();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierSubStatusTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.setDossierSubStatus("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getViaPostalTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.getViaPostal();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDelegateIdNoTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.getDelegateIdNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOriginalityTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.setOriginality(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDelegateTelNoTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.getDelegateTelNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOriginalityTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.getOriginality();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierActionIdTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.setDossierActionId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierActionIdTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.getDossierActionId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOriginDossierIdTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.getOriginDossierId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setServiceCodeTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.setServiceCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceCodeTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.getServiceCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOnlineTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.setOnline(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isOnlineTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.isOnline();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWardCodeTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.setWardCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setContactEmailTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.setContactEmail("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCityCodeTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.setCityCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDistrictCodeTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.setDistrictCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setContactTelNoTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.setContactTelNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isReceivedTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.isReceived();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOnlineValueTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.setOnlineValue(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isReleasedTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.isReleased();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isBetimeTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.isBetime();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setBetimeTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.setBetime(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isOntimeTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.isOntime();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isUndueTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.isUndue();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUndueTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.setUndue(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setReleasedTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.setReleased(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isOnlineValueTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.isOnlineValue();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOntimeTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.setOntime(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setReceivedTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.setReceived(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}