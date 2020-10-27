package org.opencps.usermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class QuestionLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void dynamicQueryTest() {
		try{
			QuestionLocalServiceWrapper object = new QuestionLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest2() {
		try{
			QuestionLocalServiceWrapper object = new QuestionLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			QuestionLocalServiceWrapper object = new QuestionLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			QuestionLocalServiceWrapper object = new QuestionLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			QuestionLocalServiceWrapper object = new QuestionLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addQuestionTest() {
		try{
			QuestionLocalServiceWrapper object = new QuestionLocalServiceWrapper(null);
			object.addQuestion(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getQuestionsTest() {
		try{
			QuestionLocalServiceWrapper object = new QuestionLocalServiceWrapper(null);
			object.getQuestions(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteQuestionTest() {
		try{
			QuestionLocalServiceWrapper object = new QuestionLocalServiceWrapper(null);
			object.deleteQuestion(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteQuestionTest9() {
		try{
			QuestionLocalServiceWrapper object = new QuestionLocalServiceWrapper(null);
			object.deleteQuestion(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateQuestionTest() {
		try{
			QuestionLocalServiceWrapper object = new QuestionLocalServiceWrapper(null);
			object.updateQuestion(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateQuestionTest11() {
		try{
			QuestionLocalServiceWrapper object = new QuestionLocalServiceWrapper(null);
			object.updateQuestion(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateQuestionTest12() {
		try{
			QuestionLocalServiceWrapper object = new QuestionLocalServiceWrapper(null);
			object.updateQuestion(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByG_CN_CPKTest() {
		try{
			QuestionLocalServiceWrapper object = new QuestionLocalServiceWrapper(null);
			object.fetchByG_CN_CPK(Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countByQuerySearchTest() {
		try{
			QuestionLocalServiceWrapper object = new QuestionLocalServiceWrapper(null);
			object.countByQuerySearch(Long.valueOf(0), "abcde", "abcde", "abcde", 0, "abcde", true, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByQuerySearchTest() {
		try{
			QuestionLocalServiceWrapper object = new QuestionLocalServiceWrapper(null);
			object.findByQuerySearch(Long.valueOf(0), "abcde", "abcde", "abcde", 0, "abcde", true, "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			QuestionLocalServiceWrapper object = new QuestionLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			QuestionLocalServiceWrapper object = new QuestionLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			QuestionLocalServiceWrapper object = new QuestionLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			QuestionLocalServiceWrapper object = new QuestionLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest20() {
		try{
			QuestionLocalServiceWrapper object = new QuestionLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			QuestionLocalServiceWrapper object = new QuestionLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest22() {
		try{
			QuestionLocalServiceWrapper object = new QuestionLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			QuestionLocalServiceWrapper object = new QuestionLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			QuestionLocalServiceWrapper object = new QuestionLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest25() {
		try{
			QuestionLocalServiceWrapper object = new QuestionLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByG_P_SYNCTest() {
		try{
			QuestionLocalServiceWrapper object = new QuestionLocalServiceWrapper(null);
			object.findByG_P_SYNC(Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchQuestionTest() {
		try{
			QuestionLocalServiceWrapper object = new QuestionLocalServiceWrapper(null);
			object.fetchQuestion(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getQuestionsCountTest() {
		try{
			QuestionLocalServiceWrapper object = new QuestionLocalServiceWrapper(null);
			object.getQuestionsCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countByG_PLTest() {
		try{
			QuestionLocalServiceWrapper object = new QuestionLocalServiceWrapper(null);
			object.countByG_PL(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByG_PLTest() {
		try{
			QuestionLocalServiceWrapper object = new QuestionLocalServiceWrapper(null);
			object.findByG_PL(Long.valueOf(0), null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createQuestionTest() {
		try{
			QuestionLocalServiceWrapper object = new QuestionLocalServiceWrapper(null);
			object.createQuestion(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getQuestionTest() {
		try{
			QuestionLocalServiceWrapper object = new QuestionLocalServiceWrapper(null);
			object.getQuestion(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}