package org.opencps.usermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class AnswerLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void dynamicQueryTest() {
		try{
			AnswerLocalServiceWrapper object = new AnswerLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest2() {
		try{
			AnswerLocalServiceWrapper object = new AnswerLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			AnswerLocalServiceWrapper object = new AnswerLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			AnswerLocalServiceWrapper object = new AnswerLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			AnswerLocalServiceWrapper object = new AnswerLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addAnswerTest() {
		try{
			AnswerLocalServiceWrapper object = new AnswerLocalServiceWrapper(null);
			object.addAnswer(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAnswersTest() {
		try{
			AnswerLocalServiceWrapper object = new AnswerLocalServiceWrapper(null);
			object.getAnswers(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByG_CN_CPKTest() {
		try{
			AnswerLocalServiceWrapper object = new AnswerLocalServiceWrapper(null);
			object.fetchByG_CN_CPK(Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteAnswerTest() {
		try{
			AnswerLocalServiceWrapper object = new AnswerLocalServiceWrapper(null);
			object.deleteAnswer(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteAnswerTest10() {
		try{
			AnswerLocalServiceWrapper object = new AnswerLocalServiceWrapper(null);
			object.deleteAnswer(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByG_Q_PLTest() {
		try{
			AnswerLocalServiceWrapper object = new AnswerLocalServiceWrapper(null);
			object.findByG_Q_PL(Long.valueOf(0), Long.valueOf(0), null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countByG_Q_PLTest() {
		try{
			AnswerLocalServiceWrapper object = new AnswerLocalServiceWrapper(null);
			object.countByG_Q_PL(Long.valueOf(0), Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateAnswerTest() {
		try{
			AnswerLocalServiceWrapper object = new AnswerLocalServiceWrapper(null);
			object.updateAnswer(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateAnswerTest14() {
		try{
			AnswerLocalServiceWrapper object = new AnswerLocalServiceWrapper(null);
			object.updateAnswer(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", 0, "abcde", "abcde", 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateAnswerTest15() {
		try{
			AnswerLocalServiceWrapper object = new AnswerLocalServiceWrapper(null);
			object.updateAnswer(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			AnswerLocalServiceWrapper object = new AnswerLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			AnswerLocalServiceWrapper object = new AnswerLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			AnswerLocalServiceWrapper object = new AnswerLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			AnswerLocalServiceWrapper object = new AnswerLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest20() {
		try{
			AnswerLocalServiceWrapper object = new AnswerLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			AnswerLocalServiceWrapper object = new AnswerLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest22() {
		try{
			AnswerLocalServiceWrapper object = new AnswerLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			AnswerLocalServiceWrapper object = new AnswerLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			AnswerLocalServiceWrapper object = new AnswerLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest25() {
		try{
			AnswerLocalServiceWrapper object = new AnswerLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAnswerTest() {
		try{
			AnswerLocalServiceWrapper object = new AnswerLocalServiceWrapper(null);
			object.getAnswer(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByG_P_SYNCTest() {
		try{
			AnswerLocalServiceWrapper object = new AnswerLocalServiceWrapper(null);
			object.findByG_P_SYNC(Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByG_QTest() {
		try{
			AnswerLocalServiceWrapper object = new AnswerLocalServiceWrapper(null);
			object.findByG_Q(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createAnswerTest() {
		try{
			AnswerLocalServiceWrapper object = new AnswerLocalServiceWrapper(null);
			object.createAnswer(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchAnswerTest() {
		try{
			AnswerLocalServiceWrapper object = new AnswerLocalServiceWrapper(null);
			object.fetchAnswer(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAnswersCountTest() {
		try{
			AnswerLocalServiceWrapper object = new AnswerLocalServiceWrapper(null);
			object.getAnswersCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}