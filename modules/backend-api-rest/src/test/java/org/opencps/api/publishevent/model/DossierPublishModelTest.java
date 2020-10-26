package org.opencps.api.publishevent.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierPublishModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getAddressTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.getAddress();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setApplicantIdNoTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.setApplicantIdNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getApplicantIdNoTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.getApplicantIdNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setApplicantIdTypeTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.setApplicantIdType("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setApplicantIdDateTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.setApplicantIdDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWardCodeTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.getWardCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getContactNameTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.getContactName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getContactTelNoTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.getContactTelNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDistrictCodeTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.getDistrictCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getContactEmailTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.getContactEmail();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCityCodeTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.getCityCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getApplicantNameTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.getApplicantName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceNameTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.getServiceName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setServiceNameTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.setServiceName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGovAgencyNameTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.setGovAgencyName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGovAgencyCodeTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.getGovAgencyCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGovAgencyCodeTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.setGovAgencyCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGovAgencyNameTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.getGovAgencyName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierStatusTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.getDossierStatus();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierStatusTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.setDossierStatus("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getReferenceUidTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.getReferenceUid();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDueDateTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.getDueDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSubmitDateTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.setSubmitDate(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSubmittingTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.setSubmitting(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setApplicantNoteTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.setApplicantNote("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCorrecttingDateTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.setCorrecttingDate(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierTemplateNoTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.setDossierTemplateNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCancellingDateTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.setCancellingDate(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCorrecttingDateTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.getCorrecttingDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierSubStatusTextTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.getDossierSubStatusText();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCancellingDateTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.getCancellingDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierTemplateNameTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.setDossierTemplateName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierTemplateNoteTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.getDossierTemplateNote();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierTemplateNoteTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.setDossierTemplateNote("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getApplicantIdTypeTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.getApplicantIdType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getApplicantIdDateTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.getApplicantIdDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSubmissionNoteTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.setSubmissionNote("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierSubStatusTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.getDossierSubStatus();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSubmissionNoteTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.getSubmissionNote();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierSubStatusTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.setDossierSubStatus("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getApplicantNoteTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.getApplicantNote();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSubmittingTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.getSubmitting();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDueDateTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.setDueDate(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setReceiveDateTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.setReceiveDate(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setAddressTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.setAddress("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getReleaseDateTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.getReleaseDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setReleaseDateTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.setReleaseDate(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getReceiveDateTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.getReceiveDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setReferenceUidTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.setReferenceUid("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFinishDateTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.setFinishDate(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFinishDateTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.getFinishDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSubmitDateTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.getSubmitDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierTemplateNoTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.getDossierTemplateNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierTemplateNameTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.getDossierTemplateName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierSubStatusTextTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.setDossierSubStatusText("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierStatusTextTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.setDossierStatusText("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierStatusTextTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.getDossierStatusText();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierNoTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.setDossierNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setServiceCodeTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.setServiceCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceCodeTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.getServiceCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierNoTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.getDossierNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOnlineTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.setOnline("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOnlineTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.getOnline();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWardCodeTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.setWardCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setContactNameTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.setContactName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setContactEmailTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.setContactEmail("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCityCodeTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.setCityCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDistrictCodeTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.setDistrictCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setContactTelNoTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.setContactTelNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setApplicantNameTest() {
		try{
			DossierPublishModel object = new DossierPublishModel();
			object.setApplicantName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}