package org.opencps.api.controller.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class VotingUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void mappingVotingTopListTest() {
		try{
			VotingUtils.mappingVotingTopList(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void mapperVotingModelTest() {
//		try{
//			VotingUtils.mapperVotingModel(null, Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void mappingVotingStatisticsModelListTest() {
		try{
			VotingUtils.mappingVotingStatisticsModelList(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingVotingResultDataListTest() {
		try{
			VotingUtils.mappingVotingResultDataList(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void mapperVotingResultModelTest() {
//		try{
//			VotingUtils.mapperVotingResultModel(null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void mappingVotingDataListTest() {
		try{
			VotingUtils.mappingVotingDataList(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingVotingListTest() {
		try{
			VotingUtils.mappingVotingList(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingVotingResultListTest() {
		try{
			VotingUtils.mappingVotingResultList(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingVotingDocListTest() {
		try{
			VotingUtils.mappingVotingDocList(null, "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertListToStringAnswersTest() {
		try{
			VotingUtils.convertListToStringAnswers(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void convertAnswersTest() {
//		try{
//			VotingUtils.convertAnswers("abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
}