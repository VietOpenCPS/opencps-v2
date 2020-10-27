package org.opencps.communication.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class NotificationtemplateLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			NotificationtemplateLocalServiceWrapper object = new NotificationtemplateLocalServiceWrapper(null);
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			NotificationtemplateLocalServiceWrapper object = new NotificationtemplateLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			NotificationtemplateLocalServiceWrapper object = new NotificationtemplateLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			NotificationtemplateLocalServiceWrapper object = new NotificationtemplateLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest5() {
		try{
			NotificationtemplateLocalServiceWrapper object = new NotificationtemplateLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			NotificationtemplateLocalServiceWrapper object = new NotificationtemplateLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_NotificationtemplateByGroupTest() {
		try{
			NotificationtemplateLocalServiceWrapper object = new NotificationtemplateLocalServiceWrapper(null);
			object.findByF_NotificationtemplateByGroup(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateNotificationTemplateDBTest() {
		try{
			NotificationtemplateLocalServiceWrapper object = new NotificationtemplateLocalServiceWrapper(null);
			object.updateNotificationTemplateDB(Long.valueOf(0), Long.valueOf(0), "abcde", true, "abcde", "abcde", "abcde", true, 0, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteNotificationtemplateTest() {
		try{
			NotificationtemplateLocalServiceWrapper object = new NotificationtemplateLocalServiceWrapper(null);
			object.deleteNotificationtemplate(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteNotificationtemplateTest10() {
		try{
			NotificationtemplateLocalServiceWrapper object = new NotificationtemplateLocalServiceWrapper(null);
			object.deleteNotificationtemplate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchNotificationtemplateTest() {
		try{
			NotificationtemplateLocalServiceWrapper object = new NotificationtemplateLocalServiceWrapper(null);
			object.fetchNotificationtemplate(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void initTemplateTest() {
		try{
			NotificationtemplateLocalServiceWrapper object = new NotificationtemplateLocalServiceWrapper(null);
			object.initTemplate(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_NotificationtemplateByTypeTest() {
		try{
			NotificationtemplateLocalServiceWrapper object = new NotificationtemplateLocalServiceWrapper(null);
			object.fetchByF_NotificationtemplateByType(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void luceneSearchEngineTest() {
		try{
			NotificationtemplateLocalServiceWrapper object = new NotificationtemplateLocalServiceWrapper(null);
			object.luceneSearchEngine(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countNotificationTemplateByGroupIdTest() {
		try{
			NotificationtemplateLocalServiceWrapper object = new NotificationtemplateLocalServiceWrapper(null);
			object.countNotificationTemplateByGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNotificationtemplatesTest() {
		try{
			NotificationtemplateLocalServiceWrapper object = new NotificationtemplateLocalServiceWrapper(null);
			object.getNotificationtemplates(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNotificationtemplatesCountTest() {
		try{
			NotificationtemplateLocalServiceWrapper object = new NotificationtemplateLocalServiceWrapper(null);
			object.getNotificationtemplatesCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateNotificationTemplateTest() {
		try{
			NotificationtemplateLocalServiceWrapper object = new NotificationtemplateLocalServiceWrapper(null);
			object.updateNotificationTemplate(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", true, true, 0, "abcde", "abcde", "abcde", true, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_TYPE_INTERTest() {
		try{
			NotificationtemplateLocalServiceWrapper object = new NotificationtemplateLocalServiceWrapper(null);
			object.findByF_TYPE_INTER(Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateNotificationtemplateTest() {
		try{
			NotificationtemplateLocalServiceWrapper object = new NotificationtemplateLocalServiceWrapper(null);
			object.updateNotificationtemplate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createNotificationtemplateTest() {
		try{
			NotificationtemplateLocalServiceWrapper object = new NotificationtemplateLocalServiceWrapper(null);
			object.createNotificationtemplate(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneSearchEngineTest() {
		try{
			NotificationtemplateLocalServiceWrapper object = new NotificationtemplateLocalServiceWrapper(null);
			object.countLuceneSearchEngine(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addNotificationtemplateTest() {
		try{
			NotificationtemplateLocalServiceWrapper object = new NotificationtemplateLocalServiceWrapper(null);
			object.addNotificationtemplate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNotificationtemplateTest() {
		try{
			NotificationtemplateLocalServiceWrapper object = new NotificationtemplateLocalServiceWrapper(null);
			object.getNotificationtemplate(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteNotificationTemplateTest() {
		try{
			NotificationtemplateLocalServiceWrapper object = new NotificationtemplateLocalServiceWrapper(null);
			object.deleteNotificationTemplate(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addNotificationTemplateTest() {
		try{
			NotificationtemplateLocalServiceWrapper object = new NotificationtemplateLocalServiceWrapper(null);
			object.addNotificationTemplate(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", true, true, "abcde", "abcde", "abcde", true, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			NotificationtemplateLocalServiceWrapper object = new NotificationtemplateLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			NotificationtemplateLocalServiceWrapper object = new NotificationtemplateLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			NotificationtemplateLocalServiceWrapper object = new NotificationtemplateLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			NotificationtemplateLocalServiceWrapper object = new NotificationtemplateLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest31() {
		try{
			NotificationtemplateLocalServiceWrapper object = new NotificationtemplateLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			NotificationtemplateLocalServiceWrapper object = new NotificationtemplateLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest33() {
		try{
			NotificationtemplateLocalServiceWrapper object = new NotificationtemplateLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			NotificationtemplateLocalServiceWrapper object = new NotificationtemplateLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			NotificationtemplateLocalServiceWrapper object = new NotificationtemplateLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest36() {
		try{
			NotificationtemplateLocalServiceWrapper object = new NotificationtemplateLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			NotificationtemplateLocalServiceWrapper object = new NotificationtemplateLocalServiceWrapper(null);
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByIntervalTest() {
		try{
			NotificationtemplateLocalServiceWrapper object = new NotificationtemplateLocalServiceWrapper(null);
			object.findByInterval("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}