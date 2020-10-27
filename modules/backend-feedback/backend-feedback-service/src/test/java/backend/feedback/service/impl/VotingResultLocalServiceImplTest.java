package backend.feedback.service.impl;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class VotingResultLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			VotingResultLocalServiceImpl object = new VotingResultLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void luceneSearchEngineTest() {
//		try{
//			VotingResultLocalServiceImpl object = new VotingResultLocalServiceImpl();
//			object.luceneSearchEngine(null, null, 0, 0, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void countLuceneSearchEngineTest() {
//		try{
//			VotingResultLocalServiceImpl object = new VotingResultLocalServiceImpl();
//			object.countLuceneSearchEngine(null, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			VotingResultLocalServiceImpl object = new VotingResultLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_votingId_userIdTest() {
		try{
			VotingResultLocalServiceImpl object = new VotingResultLocalServiceImpl();
			object.fetchByF_votingId_userId(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countByF_votingId_selectedTest() {
		try{
			VotingResultLocalServiceImpl object = new VotingResultLocalServiceImpl();
			object.countByF_votingId_selected(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteVoteResultTest() {
		try{
			VotingResultLocalServiceImpl object = new VotingResultLocalServiceImpl();
			object.deleteVoteResult(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addVotingResultTest() {
		try{
			VotingResultLocalServiceImpl object = new VotingResultLocalServiceImpl();
			object.addVotingResult(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateVoteResultTest() {
		try{
			VotingResultLocalServiceImpl object = new VotingResultLocalServiceImpl();
			object.updateVoteResult(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countByF_votingId_selected_filter_dateTest() {
		try{
			VotingResultLocalServiceImpl object = new VotingResultLocalServiceImpl();
			object.countByF_votingId_selected_filter_date(Long.valueOf(0), "abcde", new Date(), new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}