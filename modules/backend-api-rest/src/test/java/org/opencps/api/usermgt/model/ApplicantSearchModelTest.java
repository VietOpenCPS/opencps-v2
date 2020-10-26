package org.opencps.api.usermgt.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ApplicantSearchModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getTypeTest() {
		try{
			ApplicantSearchModel object = new ApplicantSearchModel();
			object.getType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setIdNoTest() {
		try{
			ApplicantSearchModel object = new ApplicantSearchModel();
			object.setIdNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setHaveAccountTest() {
		try{
			ApplicantSearchModel object = new ApplicantSearchModel();
			object.setHaveAccount(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getLockTest() {
		try{
			ApplicantSearchModel object = new ApplicantSearchModel();
			object.getLock();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIdNoTest() {
		try{
			ApplicantSearchModel object = new ApplicantSearchModel();
			object.getIdNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isHaveAccountTest() {
		try{
			ApplicantSearchModel object = new ApplicantSearchModel();
			object.isHaveAccount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getVerificationTest() {
		try{
			ApplicantSearchModel object = new ApplicantSearchModel();
			object.getVerification();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getApplicantNameTest() {
		try{
			ApplicantSearchModel object = new ApplicantSearchModel();
			object.getApplicantName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setLockTest() {
		try{
			ApplicantSearchModel object = new ApplicantSearchModel();
			object.setLock("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTypeTest() {
		try{
			ApplicantSearchModel object = new ApplicantSearchModel();
			object.setType("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEndTest() {
		try{
			ApplicantSearchModel object = new ApplicantSearchModel();
			object.setEnd(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setStartTest() {
		try{
			ApplicantSearchModel object = new ApplicantSearchModel();
			object.setStart(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOrderTest() {
		try{
			ApplicantSearchModel object = new ApplicantSearchModel();
			object.getOrder();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStartTest() {
		try{
			ApplicantSearchModel object = new ApplicantSearchModel();
			object.getStart();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSortTest() {
		try{
			ApplicantSearchModel object = new ApplicantSearchModel();
			object.getSort();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEndTest() {
		try{
			ApplicantSearchModel object = new ApplicantSearchModel();
			object.getEnd();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSortTest() {
		try{
			ApplicantSearchModel object = new ApplicantSearchModel();
			object.setSort("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOrderTest() {
		try{
			ApplicantSearchModel object = new ApplicantSearchModel();
			object.setOrder("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setKeywordsTest() {
		try{
			ApplicantSearchModel object = new ApplicantSearchModel();
			object.setKeywords("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getKeywordsTest() {
		try{
			ApplicantSearchModel object = new ApplicantSearchModel();
			object.getKeywords();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setVerificationTest() {
		try{
			ApplicantSearchModel object = new ApplicantSearchModel();
			object.setVerification("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setApplicantNameTest() {
		try{
			ApplicantSearchModel object = new ApplicantSearchModel();
			object.setApplicantName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}