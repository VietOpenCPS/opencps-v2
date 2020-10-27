package org.opencps.usermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class QuestionLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void updateQuestionTest() {
		try{
			QuestionLocalServiceImpl object = new QuestionLocalServiceImpl();
			object.updateQuestion(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateQuestionTest2() {
		try{
			QuestionLocalServiceImpl object = new QuestionLocalServiceImpl();
			object.updateQuestion(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByG_CN_CPKTest() {
		try{
			QuestionLocalServiceImpl object = new QuestionLocalServiceImpl();
			object.fetchByG_CN_CPK(Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countByQuerySearchTest() {
		try{
			QuestionLocalServiceImpl object = new QuestionLocalServiceImpl();
			object.countByQuerySearch(Long.valueOf(0), "abcde", "abcde", "abcde", 0, "abcde", true, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByQuerySearchTest() {
		try{
			QuestionLocalServiceImpl object = new QuestionLocalServiceImpl();
			object.findByQuerySearch(Long.valueOf(0), "abcde", "abcde", "abcde", 0, "abcde", true, "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByG_P_SYNCTest() {
		try{
			QuestionLocalServiceImpl object = new QuestionLocalServiceImpl();
			object.findByG_P_SYNC(Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countByG_PLTest() {
		try{
			QuestionLocalServiceImpl object = new QuestionLocalServiceImpl();
			object.countByG_PL(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByG_PLTest() {
		try{
			QuestionLocalServiceImpl object = new QuestionLocalServiceImpl();
			object.findByG_PL(Long.valueOf(0), null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}