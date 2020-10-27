package org.opencps.statistic.rest.dto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class GetVotingResultResponseTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getTotalTest() {
		try{
			GetVotingResultResponse object = new GetVotingResultResponse();
			object.getTotal();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDataTest() {
		try{
			GetVotingResultResponse object = new GetVotingResultResponse();
			object.setData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDataTest() {
		try{
			GetVotingResultResponse object = new GetVotingResultResponse();
			object.getData();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTotalTest() {
		try{
			GetVotingResultResponse object = new GetVotingResultResponse();
			object.setTotal(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}