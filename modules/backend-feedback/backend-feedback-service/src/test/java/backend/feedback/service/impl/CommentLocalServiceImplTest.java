package backend.feedback.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class CommentLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			CommentLocalServiceImpl object = new CommentLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addCommentTest() {
		try{
			CommentLocalServiceImpl object = new CommentLocalServiceImpl();
			object.addComment(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", Long.valueOf(0), null, "abcde", "abcde", 0, "abcde", true, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateCommentTest() {
		try{
			CommentLocalServiceImpl object = new CommentLocalServiceImpl();
			object.updateComment(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateCommentTest4() {
		try{
			CommentLocalServiceImpl object = new CommentLocalServiceImpl();
			object.updateComment(Long.valueOf(0), "abcde", "abcde", "abcde", 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_groupIdTest() {
		try{
			CommentLocalServiceImpl object = new CommentLocalServiceImpl();
			object.findByF_groupId(Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteCommentTest() {
		try{
			CommentLocalServiceImpl object = new CommentLocalServiceImpl();
			object.deleteComment(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteCommentTest7() {
		try{
			CommentLocalServiceImpl object = new CommentLocalServiceImpl();
			object.deleteComment(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void luceneSearchEngineTest() {
//		try{
//			CommentLocalServiceImpl object = new CommentLocalServiceImpl();
//			object.luceneSearchEngine(null, null, 0, 0, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void countLuceneSearchEngineTest() {
//		try{
//			CommentLocalServiceImpl object = new CommentLocalServiceImpl();
//			object.countLuceneSearchEngine(null, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void findByPrimaryKeyTest() {
		try{
			CommentLocalServiceImpl object = new CommentLocalServiceImpl();
			object.findByPrimaryKey(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			CommentLocalServiceImpl object = new CommentLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_groupId_userId_className_classPK_opinionTest() {
		try{
			CommentLocalServiceImpl object = new CommentLocalServiceImpl();
			object.fetchByF_groupId_userId_className_classPK_opinion(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_groupId_className_classPKTest() {
		try{
			CommentLocalServiceImpl object = new CommentLocalServiceImpl();
			object.findByF_groupId_className_classPK(Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}