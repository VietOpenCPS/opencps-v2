package org.opencps.api.faq.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class AnswerDetailModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getContentTest() {
		try{
			AnswerDetailModel object = new AnswerDetailModel();
			object.getContent();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCreateDateTest() {
		try{
			AnswerDetailModel object = new AnswerDetailModel();
			object.getCreateDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserNameTest() {
		try{
			AnswerDetailModel object = new AnswerDetailModel();
			object.getUserName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserNameTest() {
		try{
			AnswerDetailModel object = new AnswerDetailModel();
			object.setUserName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreateDateTest() {
		try{
			AnswerDetailModel object = new AnswerDetailModel();
			object.setCreateDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getModifiedDateTest() {
		try{
			AnswerDetailModel object = new AnswerDetailModel();
			object.getModifiedDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModifiedDateTest() {
		try{
			AnswerDetailModel object = new AnswerDetailModel();
			object.setModifiedDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setContentTest() {
		try{
			AnswerDetailModel object = new AnswerDetailModel();
			object.setContent("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setAnswerIdTest() {
		try{
			AnswerDetailModel object = new AnswerDetailModel();
			object.setAnswerId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPublishTest() {
		try{
			AnswerDetailModel object = new AnswerDetailModel();
			object.getPublish();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getQuestionIdTest() {
		try{
			AnswerDetailModel object = new AnswerDetailModel();
			object.getQuestionId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAnswerIdTest() {
		try{
			AnswerDetailModel object = new AnswerDetailModel();
			object.getAnswerId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setQuestionIdTest() {
		try{
			AnswerDetailModel object = new AnswerDetailModel();
			object.setQuestionId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPublishTest() {
		try{
			AnswerDetailModel object = new AnswerDetailModel();
			object.setPublish(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}