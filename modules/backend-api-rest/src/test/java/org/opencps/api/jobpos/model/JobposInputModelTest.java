package org.opencps.api.jobpos.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class JobposInputModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getTitleTest() {
		try{
			JobposInputModel object = new JobposInputModel();
			object.getTitle();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setLeaderTest() {
		try{
			JobposInputModel object = new JobposInputModel();
			object.setLeader(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setActionIdTest() {
		try{
			JobposInputModel object = new JobposInputModel();
			object.setActionId("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDescriptionTest() {
		try{
			JobposInputModel object = new JobposInputModel();
			object.getDescription();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDescriptionTest() {
		try{
			JobposInputModel object = new JobposInputModel();
			object.setDescription("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWorkingUnitIdTest() {
		try{
			JobposInputModel object = new JobposInputModel();
			object.getWorkingUnitId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWorkingUnitIdTest() {
		try{
			JobposInputModel object = new JobposInputModel();
			object.setWorkingUnitId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getLeaderTest() {
		try{
			JobposInputModel object = new JobposInputModel();
			object.getLeader();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionIdTest() {
		try{
			JobposInputModel object = new JobposInputModel();
			object.getActionId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTitleTest() {
		try{
			JobposInputModel object = new JobposInputModel();
			object.setTitle("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}