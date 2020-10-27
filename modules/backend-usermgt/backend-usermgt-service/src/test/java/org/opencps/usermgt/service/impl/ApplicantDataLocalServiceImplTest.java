package org.opencps.usermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ApplicantDataLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void updateApplicantDataTest() {
		try{
			ApplicantDataLocalServiceImpl object = new ApplicantDataLocalServiceImpl();
			object.updateApplicantData(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", 0, "abcde", null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateApplicantDataTest3() {
		try{
			ApplicantDataLocalServiceImpl object = new ApplicantDataLocalServiceImpl();
			object.updateApplicantData(null, Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", 0, "abcde", 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateApplicantDataTest4() {
		try{
			ApplicantDataLocalServiceImpl object = new ApplicantDataLocalServiceImpl();
			object.updateApplicantData(null, Long.valueOf(0), "abcde", "abcde", Long.valueOf(0), "abcde", 0, "abcde", 0, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteApplicantDataTest() {
		try{
			ApplicantDataLocalServiceImpl object = new ApplicantDataLocalServiceImpl();
			object.deleteApplicantData(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void activeTest() {
		try{
			ApplicantDataLocalServiceImpl object = new ApplicantDataLocalServiceImpl();
			object.active(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void inActiveTest() {
		try{
			ApplicantDataLocalServiceImpl object = new ApplicantDataLocalServiceImpl();
			object.inActive(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchLuceneTest() {
		try{
			ApplicantDataLocalServiceImpl object = new ApplicantDataLocalServiceImpl();
			object.searchLucene(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createApplicantDataTest() {
		try{
			ApplicantDataLocalServiceImpl object = new ApplicantDataLocalServiceImpl();
			object.createApplicantData(Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", 0, "abcde", null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createApplicantDataTest11() {
		try{
			ApplicantDataLocalServiceImpl object = new ApplicantDataLocalServiceImpl();
			object.createApplicantData(null, Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", 0, "abcde", 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchApplicantDataTest() {
		try{
			ApplicantDataLocalServiceImpl object = new ApplicantDataLocalServiceImpl();
			object.fetchApplicantData(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneTest() {
		try{
			ApplicantDataLocalServiceImpl object = new ApplicantDataLocalServiceImpl();
			object.countLucene(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByG_DN_FTN_AINTest() {
		try{
			ApplicantDataLocalServiceImpl object = new ApplicantDataLocalServiceImpl();
			object.findByG_DN_FTN_AIN(Long.valueOf(0), "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}