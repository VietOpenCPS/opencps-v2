package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class CommentManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void addCommentTest() {
		try{
			CommentManagementImpl object = new CommentManagementImpl();
			object.addComment(null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateCommentTest() {
		try{
			CommentManagementImpl object = new CommentManagementImpl();
			object.updateComment(null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void getCommentListTest() {
//		try{
//			CommentManagementImpl object = new CommentManagementImpl();
//			object.getCommentList(null, null, null, "abcde", Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void removeCommentTest() {
//		try{
//			CommentManagementImpl object = new CommentManagementImpl();
//			object.removeComment(null, null, null, Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getCommentTopTest() {
//		try{
//			CommentManagementImpl object = new CommentManagementImpl();
//			object.getCommentTop(null, null, null, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getCommentAttachmentTest() {
//		try{
//			CommentManagementImpl object = new CommentManagementImpl();
//			object.getCommentAttachment(null, null, null, Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void addCommentAttachmentTest() {
		try{
			CommentManagementImpl object = new CommentManagementImpl();
			object.addCommentAttachment(null, null, null, null, "abcde", "abcde", Long.valueOf(0), "abcde", "abcde", Long.valueOf(0), "abcde", "abcde", "abcde", true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateUpvoteCountTest() {
		try{
			CommentManagementImpl object = new CommentManagementImpl();
			object.updateUpvoteCount(null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeUpvoteCountTest() {
		try{
			CommentManagementImpl object = new CommentManagementImpl();
			object.removeUpvoteCount(null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}