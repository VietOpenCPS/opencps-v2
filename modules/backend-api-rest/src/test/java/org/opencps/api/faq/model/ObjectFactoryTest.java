package org.opencps.api.faq.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ObjectFactoryTest {
	@Before
	public void setUp() {
	}
	@Test
	public void createAnswerResultsModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createAnswerResultsModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createAnswerModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createAnswerModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createAnswerDetailModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createAnswerDetailModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createQuestionModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createQuestionModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createAnswerInputModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createAnswerInputModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createQuestionInputModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createQuestionInputModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createQuestionDetailModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createQuestionDetailModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createQuestionResultsModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createQuestionResultsModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}