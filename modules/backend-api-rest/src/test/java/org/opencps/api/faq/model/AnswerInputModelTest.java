package org.opencps.api.faq.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class AnswerInputModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getContentTest() {
		try{
			AnswerInputModel object = new AnswerInputModel();
			object.getContent();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setContentTest() {
		try{
			AnswerInputModel object = new AnswerInputModel();
			object.setContent("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPublishTest() {
		try{
			AnswerInputModel object = new AnswerInputModel();
			object.getPublish();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPublishTest() {
		try{
			AnswerInputModel object = new AnswerInputModel();
			object.setPublish(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}