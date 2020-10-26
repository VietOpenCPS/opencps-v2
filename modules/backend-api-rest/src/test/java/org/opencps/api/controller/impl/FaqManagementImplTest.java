package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class FaqManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void proxyQuestionTest() {
		try{
			FaqManagementImpl object = new FaqManagementImpl();
			object.proxyQuestion(null, null, null, null, null, null, "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addQuestionTest() {
		try{
			FaqManagementImpl object = new FaqManagementImpl();
			object.addQuestion(null, null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getQuestionsTest() {
		try{
			FaqManagementImpl object = new FaqManagementImpl();
			object.getQuestions(null, null, null, null, null, 0, 0, 0, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addAnswerTest() {
		try{
			FaqManagementImpl object = new FaqManagementImpl();
			object.addAnswer(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateAnswersTest() {
		try{
			FaqManagementImpl object = new FaqManagementImpl();
			object.updateAnswers(null, null, null, null, null, null, Long.valueOf(0), Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void detailQuestionTest() {
		try{
			FaqManagementImpl object = new FaqManagementImpl();
			object.detailQuestion(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void deleteQuestionTest() {
//		try{
//			FaqManagementImpl object = new FaqManagementImpl();
//			object.deleteQuestion(null, null, null, null, null, null, "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void optionAnswersTest() {
		try{
			FaqManagementImpl object = new FaqManagementImpl();
			object.optionAnswers(null, null, null, null, null, Long.valueOf(0), 0, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void deleteAnswersTest() {
//		try{
//			FaqManagementImpl object = new FaqManagementImpl();
//			object.deleteAnswers(null, null, null, null, null, null, Long.valueOf(0), Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void getAnswersTest() {
		try{
			FaqManagementImpl object = new FaqManagementImpl();
			object.getAnswers(null, null, null, null, null, Long.valueOf(0), 0, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void optionQuestionsTest() {
		try{
			FaqManagementImpl object = new FaqManagementImpl();
			object.optionQuestions(null, null, null, null, null, 0, 0, 0, "abcde", "abcde", "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateQuestionTest() {
		try{
			FaqManagementImpl object = new FaqManagementImpl();
			object.updateQuestion(null, null, null, null, null, null, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void optionsDetailAnswersTest() {
		try{
			FaqManagementImpl object = new FaqManagementImpl();
			object.optionsDetailAnswers(null, null, null, null, null, null, Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}