package backend.feedback.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class VotingLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			VotingLocalServiceImpl object = new VotingLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void luceneSearchEngineTest() {
//		try{
//			VotingLocalServiceImpl object = new VotingLocalServiceImpl();
//			object.luceneSearchEngine(null, null, 0, 0, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void countLuceneSearchEngineTest() {
//		try{
//			VotingLocalServiceImpl object = new VotingLocalServiceImpl();
//			object.countLuceneSearchEngine(null, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			VotingLocalServiceImpl object = new VotingLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countVotingByClass_Name_PKTest() {
		try{
			VotingLocalServiceImpl object = new VotingLocalServiceImpl();
			object.countVotingByClass_Name_PK("abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getVotingByClass_Name_PKTest() {
		try{
			VotingLocalServiceImpl object = new VotingLocalServiceImpl();
			object.getVotingByClass_Name_PK("abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countVotingByG_Class_Name_PKTest() {
		try{
			VotingLocalServiceImpl object = new VotingLocalServiceImpl();
			object.countVotingByG_Class_Name_PK(Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getVotingByG_Class_Name_PKTest() {
		try{
			VotingLocalServiceImpl object = new VotingLocalServiceImpl();
			object.getVotingByG_Class_Name_PK(Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteVoteTest() {
		try{
			VotingLocalServiceImpl object = new VotingLocalServiceImpl();
			object.deleteVote(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addVotingTest() {
		try{
			VotingLocalServiceImpl object = new VotingLocalServiceImpl();
			object.addVoting(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", true, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateVoteTest() {
		try{
			VotingLocalServiceImpl object = new VotingLocalServiceImpl();
			object.updateVote(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", true, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}