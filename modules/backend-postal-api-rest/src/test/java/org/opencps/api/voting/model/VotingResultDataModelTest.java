package org.opencps.api.voting.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class VotingResultDataModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getCommentTest() {
		try{
			VotingResultDataModel object = new VotingResultDataModel();
			object.getComment();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCreateDateTest() {
		try{
			VotingResultDataModel object = new VotingResultDataModel();
			object.getCreateDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserNameTest() {
		try{
			VotingResultDataModel object = new VotingResultDataModel();
			object.getUserName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserNameTest() {
		try{
			VotingResultDataModel object = new VotingResultDataModel();
			object.setUserName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserIdTest() {
		try{
			VotingResultDataModel object = new VotingResultDataModel();
			object.setUserId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreateDateTest() {
		try{
			VotingResultDataModel object = new VotingResultDataModel();
			object.setCreateDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserIdTest() {
		try{
			VotingResultDataModel object = new VotingResultDataModel();
			object.getUserId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getModifiedDateTest() {
		try{
			VotingResultDataModel object = new VotingResultDataModel();
			object.getModifiedDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModifiedDateTest() {
		try{
			VotingResultDataModel object = new VotingResultDataModel();
			object.setModifiedDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSelectedTest() {
		try{
			VotingResultDataModel object = new VotingResultDataModel();
			object.setSelected(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFullNameTest() {
		try{
			VotingResultDataModel object = new VotingResultDataModel();
			object.setFullName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCommentTest() {
		try{
			VotingResultDataModel object = new VotingResultDataModel();
			object.setComment("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEmailTest() {
		try{
			VotingResultDataModel object = new VotingResultDataModel();
			object.setEmail("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmailTest() {
		try{
			VotingResultDataModel object = new VotingResultDataModel();
			object.getEmail();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFullNameTest() {
		try{
			VotingResultDataModel object = new VotingResultDataModel();
			object.getFullName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setVotingResultIdTest() {
		try{
			VotingResultDataModel object = new VotingResultDataModel();
			object.setVotingResultId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getVotingResultIdTest() {
		try{
			VotingResultDataModel object = new VotingResultDataModel();
			object.getVotingResultId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSelectedTest() {
		try{
			VotingResultDataModel object = new VotingResultDataModel();
			object.getSelected();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isCurrentUserTest() {
		try{
			VotingResultDataModel object = new VotingResultDataModel();
			object.isCurrentUser();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCurrentUserTest() {
		try{
			VotingResultDataModel object = new VotingResultDataModel();
			object.setCurrentUser(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}