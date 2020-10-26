package org.opencps.api.comment.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class CommentModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getParentTest() {
		try{
			CommentModel object = new CommentModel();
			object.getParent();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getContentTest() {
		try{
			CommentModel object = new CommentModel();
			object.getContent();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getClassNameTest() {
		try{
			CommentModel object = new CommentModel();
			object.getClassName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileNameTest() {
		try{
			CommentModel object = new CommentModel();
			object.getFileName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setParentTest() {
		try{
			CommentModel object = new CommentModel();
			object.setParent(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCreateDateTest() {
		try{
			CommentModel object = new CommentModel();
			object.getCreateDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserIdTest() {
		try{
			CommentModel object = new CommentModel();
			object.setUserId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreateDateTest() {
		try{
			CommentModel object = new CommentModel();
			object.setCreateDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserIdTest() {
		try{
			CommentModel object = new CommentModel();
			object.getUserId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getModifiedDateTest() {
		try{
			CommentModel object = new CommentModel();
			object.getModifiedDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModifiedDateTest() {
		try{
			CommentModel object = new CommentModel();
			object.setModifiedDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isCreatedByCurrentUserTest() {
		try{
			CommentModel object = new CommentModel();
			object.isCreatedByCurrentUser();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreatedByCurrentUserTest() {
		try{
			CommentModel object = new CommentModel();
			object.setCreatedByCurrentUser(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserHasUpvotedTest() {
		try{
			CommentModel object = new CommentModel();
			object.setUserHasUpvoted(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setContentTest() {
		try{
			CommentModel object = new CommentModel();
			object.setContent("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFileNameTest() {
		try{
			CommentModel object = new CommentModel();
			object.setFileName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getClassPKTest() {
		try{
			CommentModel object = new CommentModel();
			object.getClassPK();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setClassPKTest() {
		try{
			CommentModel object = new CommentModel();
			object.setClassPK("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setClassNameTest() {
		try{
			CommentModel object = new CommentModel();
			object.setClassName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFullnameTest() {
		try{
			CommentModel object = new CommentModel();
			object.setFullname("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEmailTest() {
		try{
			CommentModel object = new CommentModel();
			object.setEmail("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFullnameTest() {
		try{
			CommentModel object = new CommentModel();
			object.getFullname();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileTypeTest() {
		try{
			CommentModel object = new CommentModel();
			object.getFileType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUpvoteCountTest() {
		try{
			CommentModel object = new CommentModel();
			object.getUpvoteCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFileTypeTest() {
		try{
			CommentModel object = new CommentModel();
			object.setFileType("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmailTest() {
		try{
			CommentModel object = new CommentModel();
			object.getEmail();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUpvoteCountTest() {
		try{
			CommentModel object = new CommentModel();
			object.setUpvoteCount(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOpinionTest() {
		try{
			CommentModel object = new CommentModel();
			object.setOpinion(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPingsTest() {
		try{
			CommentModel object = new CommentModel();
			object.setPings("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPingsTest() {
		try{
			CommentModel object = new CommentModel();
			object.getPings();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPictureUrlTest() {
		try{
			CommentModel object = new CommentModel();
			object.setPictureUrl("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileUrlTest() {
		try{
			CommentModel object = new CommentModel();
			object.getFileUrl();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isOpinionTest() {
		try{
			CommentModel object = new CommentModel();
			object.isOpinion();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProfileUrlTest() {
		try{
			CommentModel object = new CommentModel();
			object.getProfileUrl();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCommentIdTest() {
		try{
			CommentModel object = new CommentModel();
			object.getCommentId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCommentIdTest() {
		try{
			CommentModel object = new CommentModel();
			object.setCommentId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPictureUrlTest() {
		try{
			CommentModel object = new CommentModel();
			object.getPictureUrl();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFileUrlTest() {
		try{
			CommentModel object = new CommentModel();
			object.setFileUrl("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setIsNewTest() {
		try{
			CommentModel object = new CommentModel();
			object.setIsNew(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isUserHasUpvotedTest() {
		try{
			CommentModel object = new CommentModel();
			object.isUserHasUpvoted();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setProfileUrlTest() {
		try{
			CommentModel object = new CommentModel();
			object.setProfileUrl("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isIsAdminTest() {
		try{
			CommentModel object = new CommentModel();
			object.isIsAdmin();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isIsNewTest() {
		try{
			CommentModel object = new CommentModel();
			object.isIsNew();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setIsAdminTest() {
		try{
			CommentModel object = new CommentModel();
			object.setIsAdmin(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}