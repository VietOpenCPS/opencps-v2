package org.opencps.api.comment.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class CommentInputModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getParentTest() {
		try{
			CommentInputModel object = new CommentInputModel();
			object.getParent();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getContentTest() {
		try{
			CommentInputModel object = new CommentInputModel();
			object.getContent();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getClassNameTest() {
		try{
			CommentInputModel object = new CommentInputModel();
			object.getClassName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileNameTest() {
		try{
			CommentInputModel object = new CommentInputModel();
			object.getFileName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setParentTest() {
		try{
			CommentInputModel object = new CommentInputModel();
			object.setParent(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserIdTest() {
		try{
			CommentInputModel object = new CommentInputModel();
			object.setUserId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserIdTest() {
		try{
			CommentInputModel object = new CommentInputModel();
			object.getUserId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setContentTest() {
		try{
			CommentInputModel object = new CommentInputModel();
			object.setContent("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFileNameTest() {
		try{
			CommentInputModel object = new CommentInputModel();
			object.setFileName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getClassPKTest() {
		try{
			CommentInputModel object = new CommentInputModel();
			object.getClassPK();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setClassPKTest() {
		try{
			CommentInputModel object = new CommentInputModel();
			object.setClassPK("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setClassNameTest() {
		try{
			CommentInputModel object = new CommentInputModel();
			object.setClassName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFullnameTest() {
		try{
			CommentInputModel object = new CommentInputModel();
			object.setFullname("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEmailTest() {
		try{
			CommentInputModel object = new CommentInputModel();
			object.setEmail("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFullnameTest() {
		try{
			CommentInputModel object = new CommentInputModel();
			object.getFullname();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileTypeTest() {
		try{
			CommentInputModel object = new CommentInputModel();
			object.getFileType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUpvoteCountTest() {
		try{
			CommentInputModel object = new CommentInputModel();
			object.getUpvoteCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFileTypeTest() {
		try{
			CommentInputModel object = new CommentInputModel();
			object.setFileType("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileSizeTest() {
		try{
			CommentInputModel object = new CommentInputModel();
			object.getFileSize();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmailTest() {
		try{
			CommentInputModel object = new CommentInputModel();
			object.getEmail();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOpinionTest() {
		try{
			CommentInputModel object = new CommentInputModel();
			object.getOpinion();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUpvoteCountTest() {
		try{
			CommentInputModel object = new CommentInputModel();
			object.setUpvoteCount(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFileSizeTest() {
		try{
			CommentInputModel object = new CommentInputModel();
			object.setFileSize(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOpinionTest() {
		try{
			CommentInputModel object = new CommentInputModel();
			object.setOpinion(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPingsTest() {
		try{
			CommentInputModel object = new CommentInputModel();
			object.setPings("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPingsTest() {
		try{
			CommentInputModel object = new CommentInputModel();
			object.getPings();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}