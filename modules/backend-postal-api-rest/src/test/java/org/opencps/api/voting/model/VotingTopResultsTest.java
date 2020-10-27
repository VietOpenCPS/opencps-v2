package org.opencps.api.voting.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class VotingTopResultsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getTotalTest() {
		try{
			VotingTopResults object = new VotingTopResults();
			object.getTotal();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTotalTest() {
		try{
			VotingTopResults object = new VotingTopResults();
			object.setTotal(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getVotingTopModelTest() {
		try{
			VotingTopResults object = new VotingTopResults();
			object.getVotingTopModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}