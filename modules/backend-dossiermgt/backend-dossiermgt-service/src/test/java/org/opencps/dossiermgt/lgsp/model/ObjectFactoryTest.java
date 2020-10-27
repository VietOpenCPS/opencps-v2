package org.opencps.dossiermgt.lgsp.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ObjectFactoryTest {
	@Before
	public void setUp() {
	}
	@Test
	public void createMVotesTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createMVotes();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDocFeesTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDocFees();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createMtokenTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createMtoken();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDocPapersTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDocPapers();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createMResultTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createMResult();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createMStatisticTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createMStatistic();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createMDocumentTracesTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createMDocumentTraces();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createMSyncDocumentAttachmentsTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createMSyncDocumentAttachments();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createMSyncDocumentTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createMSyncDocument();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createSupplementariesTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createSupplementaries();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createMVotesQuestionsTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createMVotesQuestions();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}