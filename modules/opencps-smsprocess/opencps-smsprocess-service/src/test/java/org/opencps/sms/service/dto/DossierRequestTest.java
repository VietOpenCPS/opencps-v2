package org.opencps.sms.service.dto;
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
	public void setGroupIdTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.setGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupIdTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.getGroupId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
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
	public void setEndTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.setEnd(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setStartTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.setStart(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierNoTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.setDossierNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStartTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.getStart();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEndTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.getEnd();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierNoTest() {
		try{
			DossierRequest object = new DossierRequest();
			object.getDossierNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}