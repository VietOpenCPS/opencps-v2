package org.opencps.communication.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class NotificationtemplateLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			NotificationtemplateLocalServiceImpl object = new NotificationtemplateLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_NotificationtemplateByGroupTest() {
		try{
			NotificationtemplateLocalServiceImpl object = new NotificationtemplateLocalServiceImpl();
			object.findByF_NotificationtemplateByGroup(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateNotificationTemplateDBTest() {
		try{
			NotificationtemplateLocalServiceImpl object = new NotificationtemplateLocalServiceImpl();
			object.updateNotificationTemplateDB(Long.valueOf(0), Long.valueOf(0), "abcde", true, "abcde", "abcde", "abcde", true, 0, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void initTemplateTest() {
		try{
			NotificationtemplateLocalServiceImpl object = new NotificationtemplateLocalServiceImpl();
			object.initTemplate(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_NotificationtemplateByTypeTest() {
		try{
			NotificationtemplateLocalServiceImpl object = new NotificationtemplateLocalServiceImpl();
			object.fetchByF_NotificationtemplateByType(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void luceneSearchEngineTest() {
//		try{
//			NotificationtemplateLocalServiceImpl object = new NotificationtemplateLocalServiceImpl();
//			object.luceneSearchEngine(null, null, 0, 0, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void countNotificationTemplateByGroupIdTest() {
		try{
			NotificationtemplateLocalServiceImpl object = new NotificationtemplateLocalServiceImpl();
			object.countNotificationTemplateByGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void updateNotificationTemplateTest() {
//		try{
//			NotificationtemplateLocalServiceImpl object = new NotificationtemplateLocalServiceImpl();
//			object.updateNotificationTemplate(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", true, true, 0, "abcde", "abcde", "abcde", true, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void findByF_TYPE_INTERTest() {
		try{
			NotificationtemplateLocalServiceImpl object = new NotificationtemplateLocalServiceImpl();
			object.findByF_TYPE_INTER(Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void countLuceneSearchEngineTest() {
//		try{
//			NotificationtemplateLocalServiceImpl object = new NotificationtemplateLocalServiceImpl();
//			object.countLuceneSearchEngine(null, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void deleteNotificationTemplateTest() {
//		try{
//			NotificationtemplateLocalServiceImpl object = new NotificationtemplateLocalServiceImpl();
//			object.deleteNotificationTemplate(Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void addNotificationTemplateTest() {
		try{
			NotificationtemplateLocalServiceImpl object = new NotificationtemplateLocalServiceImpl();
			object.addNotificationTemplate(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", true, true, "abcde", "abcde", "abcde", true, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			NotificationtemplateLocalServiceImpl object = new NotificationtemplateLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByIntervalTest() {
		try{
			NotificationtemplateLocalServiceImpl object = new NotificationtemplateLocalServiceImpl();
			object.findByInterval("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}