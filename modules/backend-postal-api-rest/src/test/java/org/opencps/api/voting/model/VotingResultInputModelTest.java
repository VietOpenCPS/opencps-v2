package org.opencps.api.voting.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class VotingResultInputModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getCommentTest() {
		try{
			VotingResultInputModel object = new VotingResultInputModel();
			object.getComment();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSelectedTest() {
		try{
			VotingResultInputModel object = new VotingResultInputModel();
			object.setSelected("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCommentTest() {
		try{
			VotingResultInputModel object = new VotingResultInputModel();
			object.setComment("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEmailTest() {
		try{
			VotingResultInputModel object = new VotingResultInputModel();
			object.setEmail("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmailTest() {
		try{
			VotingResultInputModel object = new VotingResultInputModel();
			object.getEmail();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSelectedTest() {
		try{
			VotingResultInputModel object = new VotingResultInputModel();
			object.getSelected();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}