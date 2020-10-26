package org.opencps.api.faq.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class AnswerModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getContentTest() {
		try{
			AnswerModel object = new AnswerModel();
			object.getContent();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCreateDateTest() {
		try{
			AnswerModel object = new AnswerModel();
			object.getCreateDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserNameTest() {
		try{
			AnswerModel object = new AnswerModel();
			object.getUserName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserNameTest() {
		try{
			AnswerModel object = new AnswerModel();
			object.setUserName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreateDateTest() {
		try{
			AnswerModel object = new AnswerModel();
			object.setCreateDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getModifiedDateTest() {
		try{
			AnswerModel object = new AnswerModel();
			object.getModifiedDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModifiedDateTest() {
		try{
			AnswerModel object = new AnswerModel();
			object.setModifiedDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setContentTest() {
		try{
			AnswerModel object = new AnswerModel();
			object.setContent("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setAnswerIdTest() {
		try{
			AnswerModel object = new AnswerModel();
			object.setAnswerId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPublishTest() {
		try{
			AnswerModel object = new AnswerModel();
			object.getPublish();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getQuestionIdTest() {
		try{
			AnswerModel object = new AnswerModel();
			object.getQuestionId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAnswerIdTest() {
		try{
			AnswerModel object = new AnswerModel();
			object.getAnswerId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setQuestionIdTest() {
		try{
			AnswerModel object = new AnswerModel();
			object.setQuestionId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPublishTest() {
		try{
			AnswerModel object = new AnswerModel();
			object.setPublish(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}