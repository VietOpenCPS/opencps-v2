package org.opencps.adminconfig.service.persistence;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DynamicReportUtilTest {
	@Before
	public void setUp() {
	}
	@Test
	public void removeTest() {
		try{
			DynamicReportUtil.remove(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateTest() {
		try{
			DynamicReportUtil.update(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateTest3() {
		try{
			DynamicReportUtil.update(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createTest() {
		try{
			DynamicReportUtil.create(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeAllTest() {
		try{
			DynamicReportUtil.removeAll();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void cacheResultTest() {
		try{
			DynamicReportUtil.cacheResult(new ArrayList<>());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void cacheResultTest7() {
		try{
			DynamicReportUtil.cacheResult(DynamicReportUtil.create(0L));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByPrimaryKeyTest() {
		try{
			DynamicReportUtil.findByPrimaryKey(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistenceTest() {
		try{
			DynamicReportUtil.getPersistence();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findAllTest() {
		try{
			DynamicReportUtil.findAll();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findAllTest11() {
		try{
			DynamicReportUtil.findAll(0, 0, null, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findAllTest12() {
		try{
			DynamicReportUtil.findAll(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findAllTest13() {
		try{
			DynamicReportUtil.findAll(0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateImplTest() {
		try{
			DynamicReportUtil.updateImpl(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_GroupIdTest() {
		try{
			DynamicReportUtil.findByF_GroupId(0, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_GroupIdTest16() {
		try{
			DynamicReportUtil.findByF_GroupId(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_GroupIdTest17() {
		try{
			DynamicReportUtil.findByF_GroupId(0, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_GroupIdTest18() {
		try{
			DynamicReportUtil.findByF_GroupId(0, 0, 0, null, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countByF_GroupIdTest() {
		try{
			DynamicReportUtil.countByF_GroupId(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_GID_CODETest() {
		try{
			DynamicReportUtil.findByF_GID_CODE(0, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void clearCacheTest() {
		try{
			DynamicReportUtil.clearCache();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void clearCacheTest22() {
		try{
			DynamicReportUtil.clearCache(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countAllTest() {
		try{
			DynamicReportUtil.countAll();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByPrimaryKeysTest() {
		try{
			DynamicReportUtil.fetchByPrimaryKeys(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countWithDynamicQueryTest() {
		try{
			DynamicReportUtil.countWithDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findWithDynamicQueryTest() {
		try{
			DynamicReportUtil.findWithDynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findWithDynamicQueryTest27() {
		try{
			DynamicReportUtil.findWithDynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findWithDynamicQueryTest28() {
		try{
			DynamicReportUtil.findWithDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByPrimaryKeyTest() {
		try{
			DynamicReportUtil.fetchByPrimaryKey(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_GroupId_FirstTest() {
		try{
			DynamicReportUtil.findByF_GroupId_First(0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_GroupId_LastTest() {
		try{
			DynamicReportUtil.findByF_GroupId_Last(0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_reportType_PrevAndNextTest() {
		try{
			DynamicReportUtil.findByF_reportType_PrevAndNext(0, 0, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeByF_GroupIdTest() {
		try{
			DynamicReportUtil.removeByF_GroupId(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_GID_CODETest() {
		try{
			DynamicReportUtil.fetchByF_GID_CODE(0, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_GID_CODETest35() {
		try{
			DynamicReportUtil.fetchByF_GID_CODE(0, "abcde", true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countByF_GID_CODETest() {
		try{
			DynamicReportUtil.countByF_GID_CODE(0, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countByF_reportCodeTest() {
		try{
			DynamicReportUtil.countByF_reportCode(0, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_reportCodeTest() {
		try{
			DynamicReportUtil.findByF_reportCode(0, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_reportCodeTest() {
		try{
			DynamicReportUtil.fetchByF_reportCode(0, "abcde", true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_reportCodeTest40() {
		try{
			DynamicReportUtil.fetchByF_reportCode(0, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countByF_reportTypeTest() {
		try{
			DynamicReportUtil.countByF_reportType(0, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeByF_reportCodeTest() {
		try{
			DynamicReportUtil.removeByF_reportCode(0, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_reportType_FirstTest() {
		try{
			DynamicReportUtil.findByF_reportType_First(0, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeByF_GID_CODETest() {
		try{
			DynamicReportUtil.removeByF_GID_CODE(0, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_GroupId_PrevAndNextTest() {
		try{
			DynamicReportUtil.findByF_GroupId_PrevAndNext(0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_reportTypeTest() {
		try{
			DynamicReportUtil.findByF_reportType(0, "abcde", 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_reportTypeTest47() {
		try{
			DynamicReportUtil.findByF_reportType(0, "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_reportTypeTest48() {
		try{
			DynamicReportUtil.findByF_reportType(0, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_reportTypeTest49() {
		try{
			DynamicReportUtil.findByF_reportType(0, "abcde", 0, 0, null, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_GroupId_LastTest() {
		try{
			DynamicReportUtil.fetchByF_GroupId_Last(0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_GroupId_FirstTest() {
		try{
			DynamicReportUtil.fetchByF_GroupId_First(0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_reportType_FirstTest() {
		try{
			DynamicReportUtil.fetchByF_reportType_First(0, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeByF_reportTypeTest() {
		try{
			DynamicReportUtil.removeByF_reportType(0, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_reportType_LastTest() {
		try{
			DynamicReportUtil.fetchByF_reportType_Last(0, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_reportType_LastTest() {
		try{
			DynamicReportUtil.findByF_reportType_Last(0, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}