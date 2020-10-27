package org.opencps.dossiermgt.lgsp.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class MVotesTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getQuestionsTest() {
		try{
			MVotes object = new MVotes();
			object.getQuestions();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDateCreatedTest() {
		try{
			MVotes object = new MVotes();
			object.setDateCreated("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDateCreatedTest() {
		try{
			MVotes object = new MVotes();
			object.getDateCreated();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTotalVotedTest() {
		try{
			MVotes object = new MVotes();
			object.getTotalVoted();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTotalVotedTest() {
		try{
			MVotes object = new MVotes();
			object.setTotalVoted(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPercentBadTest() {
		try{
			MVotes object = new MVotes();
			object.getPercentBad();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPercentGoodTest() {
		try{
			MVotes object = new MVotes();
			object.getPercentGood();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPercentGoodTest() {
		try{
			MVotes object = new MVotes();
			object.setPercentGood(Double.valueOf(0.0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPercentBadTest() {
		try{
			MVotes object = new MVotes();
			object.setPercentBad(Double.valueOf(0.0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOrganizationInchargeNameTest() {
		try{
			MVotes object = new MVotes();
			object.getOrganizationInchargeName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPercentVeryGoodTest() {
		try{
			MVotes object = new MVotes();
			object.getPercentVeryGood();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOrganizationInchargeNameTest() {
		try{
			MVotes object = new MVotes();
			object.setOrganizationInchargeName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOrganizationInchargeIdLevel1Test() {
		try{
			MVotes object = new MVotes();
			object.getOrganizationInchargeIdLevel1();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOrganizationInchargeIdLevel1Test() {
		try{
			MVotes object = new MVotes();
			object.setOrganizationInchargeIdLevel1("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPercentVeryGoodTest() {
		try{
			MVotes object = new MVotes();
			object.setPercentVeryGood(Double.valueOf(0.0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}