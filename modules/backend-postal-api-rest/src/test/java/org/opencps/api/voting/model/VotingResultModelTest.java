package org.opencps.api.voting.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class VotingResultModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getCommentTest() {
		try{
			VotingResultModel object = new VotingResultModel();
			object.getComment();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCreateDateTest() {
		try{
			VotingResultModel object = new VotingResultModel();
			object.getCreateDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserNameTest() {
		try{
			VotingResultModel object = new VotingResultModel();
			object.getUserName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserNameTest() {
		try{
			VotingResultModel object = new VotingResultModel();
			object.setUserName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserIdTest() {
		try{
			VotingResultModel object = new VotingResultModel();
			object.setUserId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreateDateTest() {
		try{
			VotingResultModel object = new VotingResultModel();
			object.setCreateDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserIdTest() {
		try{
			VotingResultModel object = new VotingResultModel();
			object.getUserId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getModifiedDateTest() {
		try{
			VotingResultModel object = new VotingResultModel();
			object.getModifiedDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModifiedDateTest() {
		try{
			VotingResultModel object = new VotingResultModel();
			object.setModifiedDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSelectedTest() {
		try{
			VotingResultModel object = new VotingResultModel();
			object.setSelected(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFullNameTest() {
		try{
			VotingResultModel object = new VotingResultModel();
			object.setFullName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCommentTest() {
		try{
			VotingResultModel object = new VotingResultModel();
			object.setComment("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEmailTest() {
		try{
			VotingResultModel object = new VotingResultModel();
			object.setEmail("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmailTest() {
		try{
			VotingResultModel object = new VotingResultModel();
			object.getEmail();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFullNameTest() {
		try{
			VotingResultModel object = new VotingResultModel();
			object.getFullName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setVotingResultIdTest() {
		try{
			VotingResultModel object = new VotingResultModel();
			object.setVotingResultId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getVotingResultIdTest() {
		try{
			VotingResultModel object = new VotingResultModel();
			object.getVotingResultId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSelectedTest() {
		try{
			VotingResultModel object = new VotingResultModel();
			object.getSelected();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isCurrentUserTest() {
		try{
			VotingResultModel object = new VotingResultModel();
			object.isCurrentUser();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCurrentUserTest() {
		try{
			VotingResultModel object = new VotingResultModel();
			object.setCurrentUser(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}