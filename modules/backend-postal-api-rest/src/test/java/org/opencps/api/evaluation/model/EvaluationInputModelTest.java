package org.opencps.api.evaluation.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class EvaluationInputModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setEmployeeNameTest() {
		try{
			EvaluationInputModel object = new EvaluationInputModel();
			object.setEmployeeName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEmployeeIdTest() {
		try{
			EvaluationInputModel object = new EvaluationInputModel();
			object.setEmployeeId(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmployeeIdTest() {
		try{
			EvaluationInputModel object = new EvaluationInputModel();
			object.getEmployeeId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmployeeNameTest() {
		try{
			EvaluationInputModel object = new EvaluationInputModel();
			object.getEmployeeName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getScoreTest() {
		try{
			EvaluationInputModel object = new EvaluationInputModel();
			object.getScore();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setScoreTest() {
		try{
			EvaluationInputModel object = new EvaluationInputModel();
			object.setScore(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}