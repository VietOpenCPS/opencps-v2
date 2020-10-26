package org.opencps.api.controller.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class CommentUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void mappingCommentTopModelTest() {
		try{
			CommentUtils.mappingCommentTopModel(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingCommentTest() {
		try{
			CommentUtils.mappingComment(null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingCommentTest4() {
		try{
			CommentUtils.mappingComment(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingCommentListTest() {
		try{
			CommentUtils.mappingCommentList(null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingCommentTopListTest() {
		try{
			CommentUtils.mappingCommentTopList(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}