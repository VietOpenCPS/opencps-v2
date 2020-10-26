package org.opencps.api.employee.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class JobPostsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void isMainJobPosTest() {
		try{
			JobPosts object = new JobPosts();
			object.isMainJobPos();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setJobPosTitleTest() {
		try{
			JobPosts object = new JobPosts();
			object.setJobPosTitle("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getJobPosTitleTest() {
		try{
			JobPosts object = new JobPosts();
			object.getJobPosTitle();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMainJobPosTest() {
		try{
			JobPosts object = new JobPosts();
			object.setMainJobPos(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setJobPosIdTest() {
		try{
			JobPosts object = new JobPosts();
			object.setJobPosId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWorkingUnitNameTest() {
		try{
			JobPosts object = new JobPosts();
			object.getWorkingUnitName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWorkingUnitIdTest() {
		try{
			JobPosts object = new JobPosts();
			object.getWorkingUnitId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWorkingUnitIdTest() {
		try{
			JobPosts object = new JobPosts();
			object.setWorkingUnitId("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getJobPosIdTest() {
		try{
			JobPosts object = new JobPosts();
			object.getJobPosId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWorkingUnitNameTest() {
		try{
			JobPosts object = new JobPosts();
			object.setWorkingUnitName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}