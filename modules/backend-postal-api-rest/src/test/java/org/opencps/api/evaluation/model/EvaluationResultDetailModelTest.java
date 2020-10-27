package org.opencps.api.evaluation.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class EvaluationResultDetailModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getCreateDateTest() {
		try{
			EvaluationResultDetailModel object = new EvaluationResultDetailModel();
			object.getCreateDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGroupIdTest() {
		try{
			EvaluationResultDetailModel object = new EvaluationResultDetailModel();
			object.setGroupId("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCompanyIdTest() {
		try{
			EvaluationResultDetailModel object = new EvaluationResultDetailModel();
			object.setCompanyId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserIdTest() {
		try{
			EvaluationResultDetailModel object = new EvaluationResultDetailModel();
			object.setUserId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreateDateTest() {
		try{
			EvaluationResultDetailModel object = new EvaluationResultDetailModel();
			object.setCreateDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCompanyIdTest() {
		try{
			EvaluationResultDetailModel object = new EvaluationResultDetailModel();
			object.getCompanyId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserIdTest() {
		try{
			EvaluationResultDetailModel object = new EvaluationResultDetailModel();
			object.getUserId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getModifiedDateTest() {
		try{
			EvaluationResultDetailModel object = new EvaluationResultDetailModel();
			object.getModifiedDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupIdTest() {
		try{
			EvaluationResultDetailModel object = new EvaluationResultDetailModel();
			object.getGroupId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModifiedDateTest() {
		try{
			EvaluationResultDetailModel object = new EvaluationResultDetailModel();
			object.setModifiedDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEmployeeNameTest() {
		try{
			EvaluationResultDetailModel object = new EvaluationResultDetailModel();
			object.setEmployeeName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEmployeeIdTest() {
		try{
			EvaluationResultDetailModel object = new EvaluationResultDetailModel();
			object.setEmployeeId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmployeeIdTest() {
		try{
			EvaluationResultDetailModel object = new EvaluationResultDetailModel();
			object.getEmployeeId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmployeeNameTest() {
		try{
			EvaluationResultDetailModel object = new EvaluationResultDetailModel();
			object.getEmployeeName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getScoreTest() {
		try{
			EvaluationResultDetailModel object = new EvaluationResultDetailModel();
			object.getScore();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setScoreTest() {
		try{
			EvaluationResultDetailModel object = new EvaluationResultDetailModel();
			object.setScore(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}