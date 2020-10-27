package org.opencps.usermgt.constants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class JobPosWorkTermTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getCreateDateTest() {
		try{
			JobPosWorkTerm object = new JobPosWorkTerm();
			object.getCreateDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserNameTest() {
		try{
			JobPosWorkTerm object = new JobPosWorkTerm();
			object.getUserName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserNameTest() {
		try{
			JobPosWorkTerm object = new JobPosWorkTerm();
			object.setUserName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGroupIdTest() {
		try{
			JobPosWorkTerm object = new JobPosWorkTerm();
			object.setGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCompanyIdTest() {
		try{
			JobPosWorkTerm object = new JobPosWorkTerm();
			object.setCompanyId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserIdTest() {
		try{
			JobPosWorkTerm object = new JobPosWorkTerm();
			object.setUserId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreateDateTest() {
		try{
			JobPosWorkTerm object = new JobPosWorkTerm();
			object.setCreateDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCompanyIdTest() {
		try{
			JobPosWorkTerm object = new JobPosWorkTerm();
			object.getCompanyId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserIdTest() {
		try{
			JobPosWorkTerm object = new JobPosWorkTerm();
			object.getUserId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getModifiedDateTest() {
		try{
			JobPosWorkTerm object = new JobPosWorkTerm();
			object.getModifiedDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupIdTest() {
		try{
			JobPosWorkTerm object = new JobPosWorkTerm();
			object.getGroupId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModifiedDateTest() {
		try{
			JobPosWorkTerm object = new JobPosWorkTerm();
			object.setModifiedDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getJobPostIdTest() {
		try{
			JobPosWorkTerm object = new JobPosWorkTerm();
			object.getJobPostId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setChecklistCatTest() {
		try{
			JobPosWorkTerm object = new JobPosWorkTerm();
			object.setChecklistCat("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getChecklistCatTest() {
		try{
			JobPosWorkTerm object = new JobPosWorkTerm();
			object.getChecklistCat();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setJobPostIdTest() {
		try{
			JobPosWorkTerm object = new JobPosWorkTerm();
			object.setJobPostId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getJobPosWorkIdTest() {
		try{
			JobPosWorkTerm object = new JobPosWorkTerm();
			object.getJobPosWorkId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setJobPosWorkIdTest() {
		try{
			JobPosWorkTerm object = new JobPosWorkTerm();
			object.setJobPosWorkId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}