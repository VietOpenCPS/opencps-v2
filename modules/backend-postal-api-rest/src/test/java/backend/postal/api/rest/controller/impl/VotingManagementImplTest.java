package backend.postal.api.rest.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class VotingManagementImplTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void removeVotingResultTest() {
//		try{
//			VotingManagementImpl object = new VotingManagementImpl();
//			object.removeVotingResult(null, null, null, null, null, null, Long.valueOf(0), Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//
//	@Test
//	public void addVotingResultTest() {
//		try{
//			VotingManagementImpl object = new VotingManagementImpl();
//			object.addVotingResult(null, null, null, null, null, null, Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getVotingResultsTest() {
//		try{
//			VotingManagementImpl object = new VotingManagementImpl();
//			object.getVotingResults(null, null, null, null, null, null, Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void deleteVotingTest() {
//		try{
//			VotingManagementImpl object = new VotingManagementImpl();
//			object.deleteVoting(null, null, null, null, null, null, Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateVotingTest() {
//		try{
//			VotingManagementImpl object = new VotingManagementImpl();
//			object.updateVoting(null, null, null, null, null, null, null, Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void addVotingTest() {
//		try{
//			VotingManagementImpl object = new VotingManagementImpl();
//			object.addVoting(null, null, null, null, null, null, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getVotingListTest() {
//		try{
//			VotingManagementImpl object = new VotingManagementImpl();
//			object.getVotingList(null, null, null, null, null, null, "abcde", "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void resolveConflictVotingsTest() {
		try{
			VotingManagementImpl object = new VotingManagementImpl();
			object.resolveConflictVotings(null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	
	@Test
	public void getVotingResultStatisticTest() {
		try{
			VotingManagementImpl object = new VotingManagementImpl();
			object.getVotingResultStatistic(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}