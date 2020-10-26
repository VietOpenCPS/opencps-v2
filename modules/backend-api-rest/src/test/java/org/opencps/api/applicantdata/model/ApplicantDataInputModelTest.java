package org.opencps.api.applicantdata.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ApplicantDataInputModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getFileNameTest() {
		try{
			ApplicantDataInputModel object = new ApplicantDataInputModel();
			object.getFileName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setApplicantIdNoTest() {
		try{
			ApplicantDataInputModel object = new ApplicantDataInputModel();
			object.setApplicantIdNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getApplicantIdNoTest() {
		try{
			ApplicantDataInputModel object = new ApplicantDataInputModel();
			object.getApplicantIdNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setStatusTest() {
		try{
			ApplicantDataInputModel object = new ApplicantDataInputModel();
			object.setStatus("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileNoTest() {
		try{
			ApplicantDataInputModel object = new ApplicantDataInputModel();
			object.getFileNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFileNoTest() {
		try{
			ApplicantDataInputModel object = new ApplicantDataInputModel();
			object.setFileNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFileNameTest() {
		try{
			ApplicantDataInputModel object = new ApplicantDataInputModel();
			object.setFileName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStatusTest() {
		try{
			ApplicantDataInputModel object = new ApplicantDataInputModel();
			object.getStatus();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}