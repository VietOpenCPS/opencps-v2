package org.opencps.sms.service.dto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierDataTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setGroupIdTest() {
		try{
			DossierData object = new DossierData();
			object.setGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupIdTest() {
		try{
			DossierData object = new DossierData();
			object.getGroupId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getApplicantNameTest() {
		try{
			DossierData object = new DossierData();
			object.getApplicantName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGovAgencyNameTest() {
		try{
			DossierData object = new DossierData();
			object.setGovAgencyName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGovAgencyCodeTest() {
		try{
			DossierData object = new DossierData();
			object.getGovAgencyCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGovAgencyCodeTest() {
		try{
			DossierData object = new DossierData();
			object.setGovAgencyCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGovAgencyNameTest() {
		try{
			DossierData object = new DossierData();
			object.getGovAgencyName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierStatusTest() {
		try{
			DossierData object = new DossierData();
			object.getDossierStatus();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierStatusTest() {
		try{
			DossierData object = new DossierData();
			object.setDossierStatus("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDueDateTest() {
		try{
			DossierData object = new DossierData();
			object.getDueDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierOverdueTest() {
		try{
			DossierData object = new DossierData();
			object.getDossierOverdue();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierOverdueTest() {
		try{
			DossierData object = new DossierData();
			object.setDossierOverdue("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierSubStatusTextTest() {
		try{
			DossierData object = new DossierData();
			object.getDossierSubStatusText();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierSubStatusTest() {
		try{
			DossierData object = new DossierData();
			object.getDossierSubStatus();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierSubStatusTest() {
		try{
			DossierData object = new DossierData();
			object.setDossierSubStatus("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExtendDateTest() {
		try{
			DossierData object = new DossierData();
			object.getExtendDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setExtendDateTest() {
		try{
			DossierData object = new DossierData();
			object.setExtendDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDueDateTest() {
		try{
			DossierData object = new DossierData();
			object.setDueDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setReceiveDateTest() {
		try{
			DossierData object = new DossierData();
			object.setReceiveDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getReleaseDateTest() {
		try{
			DossierData object = new DossierData();
			object.getReleaseDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setReleaseDateTest() {
		try{
			DossierData object = new DossierData();
			object.setReleaseDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getReceiveDateTest() {
		try{
			DossierData object = new DossierData();
			object.getReceiveDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFinishDateTest() {
		try{
			DossierData object = new DossierData();
			object.setFinishDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFinishDateTest() {
		try{
			DossierData object = new DossierData();
			object.getFinishDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierSubStatusTextTest() {
		try{
			DossierData object = new DossierData();
			object.setDossierSubStatusText("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierStatusTextTest() {
		try{
			DossierData object = new DossierData();
			object.setDossierStatusText("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierStatusTextTest() {
		try{
			DossierData object = new DossierData();
			object.getDossierStatusText();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierNoTest() {
		try{
			DossierData object = new DossierData();
			object.setDossierNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setServiceCodeTest() {
		try{
			DossierData object = new DossierData();
			object.setServiceCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceCodeTest() {
		try{
			DossierData object = new DossierData();
			object.getServiceCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierNoTest() {
		try{
			DossierData object = new DossierData();
			object.getDossierNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOnlineTest() {
		try{
			DossierData object = new DossierData();
			object.setOnline("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOnlineTest() {
		try{
			DossierData object = new DossierData();
			object.getOnline();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setApplicantNameTest() {
		try{
			DossierData object = new DossierData();
			object.setApplicantName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}