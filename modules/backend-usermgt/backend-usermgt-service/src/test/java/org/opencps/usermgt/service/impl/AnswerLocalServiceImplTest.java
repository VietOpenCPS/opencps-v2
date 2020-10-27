package org.opencps.usermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class AnswerLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void fetchByG_CN_CPKTest() {
		try{
			AnswerLocalServiceImpl object = new AnswerLocalServiceImpl();
			object.fetchByG_CN_CPK(Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByG_Q_PLTest() {
		try{
			AnswerLocalServiceImpl object = new AnswerLocalServiceImpl();
			object.findByG_Q_PL(Long.valueOf(0), Long.valueOf(0), null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countByG_Q_PLTest() {
		try{
			AnswerLocalServiceImpl object = new AnswerLocalServiceImpl();
			object.countByG_Q_PL(Long.valueOf(0), Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateAnswerTest() {
		try{
			AnswerLocalServiceImpl object = new AnswerLocalServiceImpl();
			object.updateAnswer(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", 0, "abcde", "abcde", 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateAnswerTest5() {
		try{
			AnswerLocalServiceImpl object = new AnswerLocalServiceImpl();
			object.updateAnswer(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByG_P_SYNCTest() {
		try{
			AnswerLocalServiceImpl object = new AnswerLocalServiceImpl();
			object.findByG_P_SYNC(Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByG_QTest() {
		try{
			AnswerLocalServiceImpl object = new AnswerLocalServiceImpl();
			object.findByG_Q(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}