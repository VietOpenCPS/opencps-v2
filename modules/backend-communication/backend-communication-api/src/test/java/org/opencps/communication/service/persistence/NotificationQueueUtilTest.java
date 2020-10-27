package org.opencps.communication.service.persistence;
import org.junit.Before;
public class NotificationQueueUtilTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void removeTest() {
//		try{
//			NotificationQueueUtil.remove(Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateTest() {
//		try{
//			NotificationQueueUtil.update(null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateTest3() {
//		try{
//			NotificationQueueUtil.update(null, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void createTest() {
//		try{
//			NotificationQueueUtil.create(Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void removeAllTest() {
//		try{
//			NotificationQueueUtil.removeAll();
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void clearCacheTest() {
//		try{
//			NotificationQueueUtil.clearCache();
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void clearCacheTest7() {
//		try{
//			NotificationQueueUtil.clearCache(NotificationQueueUtil.create(0L));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void cacheResultTest() {
//		try{
//			NotificationQueueUtil.cacheResult(NotificationQueueUtil.create(0L));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void cacheResultTest9() {
//		try{
//			NotificationQueueUtil.cacheResult(new ArrayList<>());
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void findByGTest() {
//		try{
//			NotificationQueueUtil.findByG(Long.valueOf(0), 0, 0);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void findByGTest11() {
//		try{
//			NotificationQueueUtil.findByG(Long.valueOf(0), 0, 0, null, true);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void findByGTest12() {
//		try{
//			NotificationQueueUtil.findByG(Long.valueOf(0), 0, 0, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void findByGTest13() {
//		try{
//			NotificationQueueUtil.findByG(Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void findByF_notificationType_LessThanExpireDateTest() {
//		try{
//			NotificationQueueUtil.findByF_notificationType_LessThanExpireDate("abcde", new Date(), 0, 0, null, true);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void findByF_notificationType_LessThanExpireDateTest15() {
//		try{
//			NotificationQueueUtil.findByF_notificationType_LessThanExpireDate("abcde", new Date(), 0, 0);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void findByF_notificationType_LessThanExpireDateTest16() {
//		try{
//			NotificationQueueUtil.findByF_notificationType_LessThanExpireDate("abcde", new Date(), 0, 0, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void findByF_notificationType_LessThanExpireDateTest17() {
//		try{
//			NotificationQueueUtil.findByF_notificationType_LessThanExpireDate("abcde", new Date());
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void findByF_GreaterThan_ExpireDate_PrevAndNextTest() {
//		try{
//			NotificationQueueUtil.findByF_GreaterThan_ExpireDate_PrevAndNext(Long.valueOf(0), new Date(), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void findByF_LessThan_ExpireDate_FirstTest() {
//		try{
//			NotificationQueueUtil.findByF_LessThan_ExpireDate_First(new Date(), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void findByF_GreaterThan_ExpireDate_FirstTest() {
//		try{
//			NotificationQueueUtil.findByF_GreaterThan_ExpireDate_First(new Date(), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void countByF_notificationType_LessThanExpireDateTest() {
//		try{
//			NotificationQueueUtil.countByF_notificationType_LessThanExpireDate("abcde", new Date());
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void fetchByF_LessThan_ExpireDate_FirstTest() {
//		try{
//			NotificationQueueUtil.fetchByF_LessThan_ExpireDate_First(new Date(), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void findByF_GreaterThan_ExpireDate_LastTest() {
//		try{
//			NotificationQueueUtil.findByF_GreaterThan_ExpireDate_Last(new Date(), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void findByF_LessThan_ExpireDate_PrevAndNextTest() {
//		try{
//			NotificationQueueUtil.findByF_LessThan_ExpireDate_PrevAndNext(Long.valueOf(0), new Date(), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void fetchByF_GreaterThan_ExpireDate_FirstTest() {
//		try{
//			NotificationQueueUtil.fetchByF_GreaterThan_ExpireDate_First(new Date(), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void fetchByF_LessThan_ExpireDate_LastTest() {
//		try{
//			NotificationQueueUtil.fetchByF_LessThan_ExpireDate_Last(new Date(), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void removeByF_notificationType_LessThanExpireDateTest() {
//		try{
//			NotificationQueueUtil.removeByF_notificationType_LessThanExpireDate("abcde", new Date());
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void fetchByF_GreaterThan_ExpireDate_LastTest() {
//		try{
//			NotificationQueueUtil.fetchByF_GreaterThan_ExpireDate_Last(new Date(), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void findByF_notificationType_LessThanExpireDate_LastTest() {
//		try{
//			NotificationQueueUtil.findByF_notificationType_LessThanExpireDate_Last("abcde", new Date(), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void findByF_notificationType_LessThanExpireDate_PrevAndNextTest() {
//		try{
//			NotificationQueueUtil.findByF_notificationType_LessThanExpireDate_PrevAndNext(Long.valueOf(0), "abcde", new Date(), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void fetchByF_notificationType_LessThanExpireDate_LastTest() {
//		try{
//			NotificationQueueUtil.fetchByF_notificationType_LessThanExpireDate_Last("abcde", new Date(), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void fetchByF_notificationType_LessThanExpireDate_FirstTest() {
//		try{
//			NotificationQueueUtil.fetchByF_notificationType_LessThanExpireDate_First("abcde", new Date(), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void findByF_notificationType_LessThanExpireDate_FirstTest() {
//		try{
//			NotificationQueueUtil.findByF_notificationType_LessThanExpireDate_First("abcde", new Date(), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void findByF_GreaterThan_ExpireDateTest() {
//		try{
//			NotificationQueueUtil.findByF_GreaterThan_ExpireDate(new Date());
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void findByF_GreaterThan_ExpireDateTest35() {
//		try{
//			NotificationQueueUtil.findByF_GreaterThan_ExpireDate(new Date(), 0, 0);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void findByF_GreaterThan_ExpireDateTest36() {
//		try{
//			NotificationQueueUtil.findByF_GreaterThan_ExpireDate(new Date(), 0, 0, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void findByF_GreaterThan_ExpireDateTest37() {
//		try{
//			NotificationQueueUtil.findByF_GreaterThan_ExpireDate(new Date(), 0, 0, null, true);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void fetchByF_NT_CN_CPK_EMAILTest() {
//		try{
//			NotificationQueueUtil.fetchByF_NT_CN_CPK_EMAIL(Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", true);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void fetchByF_NT_CN_CPK_EMAILTest39() {
//		try{
//			NotificationQueueUtil.fetchByF_NT_CN_CPK_EMAIL(Long.valueOf(0), "abcde", "abcde", "abcde", "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void countByF_NT_CN_CPK_EMAILTest() {
//		try{
//			NotificationQueueUtil.countByF_NT_CN_CPK_EMAIL(Long.valueOf(0), "abcde", "abcde", "abcde", "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void findByF_LessThan_ExpireDate_LastTest() {
//		try{
//			NotificationQueueUtil.findByF_LessThan_ExpireDate_Last(new Date(), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void removeByF_GreaterThan_ExpireDateTest() {
//		try{
//			NotificationQueueUtil.removeByF_GreaterThan_ExpireDate(new Date());
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void removeByF_NT_CN_CPK_EMAILTest() {
//		try{
//			NotificationQueueUtil.removeByF_NT_CN_CPK_EMAIL(Long.valueOf(0), "abcde", "abcde", "abcde", "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void findByF_NT_CN_CPK_EMAILTest() {
//		try{
//			NotificationQueueUtil.findByF_NT_CN_CPK_EMAIL(Long.valueOf(0), "abcde", "abcde", "abcde", "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void findByF_LessThan_ExpireDateTest() {
//		try{
//			NotificationQueueUtil.findByF_LessThan_ExpireDate(new Date(), 0, 0);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void findByF_LessThan_ExpireDateTest46() {
//		try{
//			NotificationQueueUtil.findByF_LessThan_ExpireDate(new Date());
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void findByF_LessThan_ExpireDateTest47() {
//		try{
//			NotificationQueueUtil.findByF_LessThan_ExpireDate(new Date(), 0, 0, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void findByF_LessThan_ExpireDateTest48() {
//		try{
//			NotificationQueueUtil.findByF_LessThan_ExpireDate(new Date(), 0, 0, null, true);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void findByG_PrevAndNextTest() {
//		try{
//			NotificationQueueUtil.findByG_PrevAndNext(Long.valueOf(0), Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void countByF_GreaterThan_ExpireDateTest() {
//		try{
//			NotificationQueueUtil.countByF_GreaterThan_ExpireDate(new Date());
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void removeByF_LessThan_ExpireDateTest() {
//		try{
//			NotificationQueueUtil.removeByF_LessThan_ExpireDate(new Date());
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void countByF_LessThan_ExpireDateTest() {
//		try{
//			NotificationQueueUtil.countByF_LessThan_ExpireDate(new Date());
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void findByPrimaryKeyTest() {
//		try{
//			NotificationQueueUtil.findByPrimaryKey(Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateImplTest() {
//		try{
//			NotificationQueueUtil.updateImpl(null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void findAllTest() {
//		try{
//			NotificationQueueUtil.findAll(0, 0);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void findAllTest56() {
//		try{
//			NotificationQueueUtil.findAll(0, 0, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void findAllTest57() {
//		try{
//			NotificationQueueUtil.findAll();
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void findAllTest58() {
//		try{
//			NotificationQueueUtil.findAll(0, 0, null, true);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getPersistenceTest() {
//		try{
//			NotificationQueueUtil.getPersistence();
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void countAllTest() {
//		try{
//			NotificationQueueUtil.countAll();
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void fetchByPrimaryKeysTest() {
//		try{
//			NotificationQueueUtil.fetchByPrimaryKeys(null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void findWithDynamicQueryTest() {
//		try{
//			NotificationQueueUtil.findWithDynamicQuery(null, 0, 0);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void findWithDynamicQueryTest63() {
//		try{
//			NotificationQueueUtil.findWithDynamicQuery(null, 0, 0, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void findWithDynamicQueryTest64() {
//		try{
//			NotificationQueueUtil.findWithDynamicQuery(null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void fetchByPrimaryKeyTest() {
//		try{
//			NotificationQueueUtil.fetchByPrimaryKey(Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void countWithDynamicQueryTest() {
//		try{
//			NotificationQueueUtil.countWithDynamicQuery(null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void fetchByG_LastTest() {
//		try{
//			NotificationQueueUtil.fetchByG_Last(Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void findByG_LastTest() {
//		try{
//			NotificationQueueUtil.findByG_Last(Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void countByGTest() {
//		try{
//			NotificationQueueUtil.countByG(Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void findByG_FirstTest() {
//		try{
//			NotificationQueueUtil.findByG_First(Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void fetchByG_FirstTest() {
//		try{
//			NotificationQueueUtil.fetchByG_First(Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void removeByGTest() {
//		try{
//			NotificationQueueUtil.removeByG(Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
}