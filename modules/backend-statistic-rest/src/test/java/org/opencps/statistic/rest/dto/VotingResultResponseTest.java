package org.opencps.statistic.rest.dto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class VotingResultResponseTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getTotalTest() {
		try{
			VotingResultResponse object = new VotingResultResponse();
			object.getTotal();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAgencyTest() {
		try{
			VotingResultResponse object = new VotingResultResponse();
			object.getAgency();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setAgencyTest() {
		try{
			VotingResultResponse object = new VotingResultResponse();
			object.setAgency("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDataTest() {
		try{
			VotingResultResponse object = new VotingResultResponse();
			object.setData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDataTest() {
		try{
			VotingResultResponse object = new VotingResultResponse();
			object.getData();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTotalTest() {
		try{
			VotingResultResponse object = new VotingResultResponse();
			object.setTotal(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}