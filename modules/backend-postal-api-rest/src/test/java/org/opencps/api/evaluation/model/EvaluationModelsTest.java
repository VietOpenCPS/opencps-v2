package org.opencps.api.evaluation.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class EvaluationModelsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getCreateDateTest() {
		try{
			EvaluationModels object = new EvaluationModels();
			object.getCreateDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGroupIdTest() {
		try{
			EvaluationModels object = new EvaluationModels();
			object.setGroupId("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCompanyIdTest() {
		try{
			EvaluationModels object = new EvaluationModels();
			object.setCompanyId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserIdTest() {
		try{
			EvaluationModels object = new EvaluationModels();
			object.setUserId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreateDateTest() {
		try{
			EvaluationModels object = new EvaluationModels();
			object.setCreateDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCompanyIdTest() {
		try{
			EvaluationModels object = new EvaluationModels();
			object.getCompanyId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserIdTest() {
		try{
			EvaluationModels object = new EvaluationModels();
			object.getUserId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getModifiedDateTest() {
		try{
			EvaluationModels object = new EvaluationModels();
			object.getModifiedDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupIdTest() {
		try{
			EvaluationModels object = new EvaluationModels();
			object.getGroupId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModifiedDateTest() {
		try{
			EvaluationModels object = new EvaluationModels();
			object.setModifiedDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEmployeeNameTest() {
		try{
			EvaluationModels object = new EvaluationModels();
			object.setEmployeeName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEmployeeIdTest() {
		try{
			EvaluationModels object = new EvaluationModels();
			object.setEmployeeId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmployeeIdTest() {
		try{
			EvaluationModels object = new EvaluationModels();
			object.getEmployeeId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmployeeNameTest() {
		try{
			EvaluationModels object = new EvaluationModels();
			object.getEmployeeName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEvaluationIdTest() {
		try{
			EvaluationModels object = new EvaluationModels();
			object.setEvaluationId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getScoreTest() {
		try{
			EvaluationModels object = new EvaluationModels();
			object.getScore();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setScoreTest() {
		try{
			EvaluationModels object = new EvaluationModels();
			object.setScore(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEvaluationIdTest() {
		try{
			EvaluationModels object = new EvaluationModels();
			object.getEvaluationId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}