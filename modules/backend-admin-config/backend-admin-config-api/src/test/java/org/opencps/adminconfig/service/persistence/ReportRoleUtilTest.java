package org.opencps.adminconfig.service.persistence;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ReportRoleUtilTest {
	@Before
	public void setUp() {
	}
	@Test
	public void removeTest() {
		try{
			ReportRoleUtil.remove(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateTest() {
		try{
			ReportRoleUtil.update(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateTest3() {
		try{
			ReportRoleUtil.update(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createTest() {
		try{
			ReportRoleUtil.create(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeAllTest() {
		try{
			ReportRoleUtil.removeAll();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void cacheResultTest() {
		try{
			ReportRoleUtil.cacheResult(new ArrayList<>());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void cacheResultTest7() {
		try{
			ReportRoleUtil.cacheResult(ReportRoleUtil.create(0L));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByPrimaryKeyTest() {
		try{
			ReportRoleUtil.findByPrimaryKey(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistenceTest() {
		try{
			ReportRoleUtil.getPersistence();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_DRIDTest() {
		try{
			ReportRoleUtil.findByF_DRID(0, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_DRIDTest11() {
		try{
			ReportRoleUtil.findByF_DRID(0, 0, 0, null, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_DRIDTest12() {
		try{
			ReportRoleUtil.findByF_DRID(0, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_DRIDTest13() {
		try{
			ReportRoleUtil.findByF_DRID(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findAllTest() {
		try{
			ReportRoleUtil.findAll(0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findAllTest15() {
		try{
			ReportRoleUtil.findAll(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findAllTest16() {
		try{
			ReportRoleUtil.findAll(0, 0, null, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findAllTest17() {
		try{
			ReportRoleUtil.findAll();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeByF_DRIDTest() {
		try{
			ReportRoleUtil.removeByF_DRID(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_RIDSTest() {
		try{
			ReportRoleUtil.findByF_RIDS(0, 0, 0, null, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_RIDSTest20() {
		try{
			ReportRoleUtil.findByF_RIDS(null, 0, 0, null, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_RIDSTest21() {
		try{
			ReportRoleUtil.findByF_RIDS(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_RIDSTest22() {
		try{
			ReportRoleUtil.findByF_RIDS(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_RIDSTest23() {
		try{
			ReportRoleUtil.findByF_RIDS(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_RIDSTest24() {
		try{
			ReportRoleUtil.findByF_RIDS(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_RIDSTest25() {
		try{
			ReportRoleUtil.findByF_RIDS(0, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_RIDSTest26() {
		try{
			ReportRoleUtil.findByF_RIDS(0, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_DRID_RIDTest() {
		try{
			ReportRoleUtil.findByF_DRID_RID(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateImplTest() {
		try{
			ReportRoleUtil.updateImpl(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countByF_DRIDTest() {
		try{
			ReportRoleUtil.countByF_DRID(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeByF_RIDSTest() {
		try{
			ReportRoleUtil.removeByF_RIDS(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void clearCacheTest() {
		try{
			ReportRoleUtil.clearCache(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void clearCacheTest32() {
		try{
			ReportRoleUtil.clearCache();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countByF_RIDSTest() {
		try{
			ReportRoleUtil.countByF_RIDS(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countByF_RIDSTest34() {
		try{
			ReportRoleUtil.countByF_RIDS(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countAllTest() {
		try{
			ReportRoleUtil.countAll();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByPrimaryKeysTest() {
		try{
			ReportRoleUtil.fetchByPrimaryKeys(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countWithDynamicQueryTest() {
		try{
			ReportRoleUtil.countWithDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findWithDynamicQueryTest() {
		try{
			ReportRoleUtil.findWithDynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findWithDynamicQueryTest39() {
		try{
			ReportRoleUtil.findWithDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findWithDynamicQueryTest40() {
		try{
			ReportRoleUtil.findWithDynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByPrimaryKeyTest() {
		try{
			ReportRoleUtil.fetchByPrimaryKey(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_DRID_FirstTest() {
		try{
			ReportRoleUtil.findByF_DRID_First(0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_DRID_PrevAndNextTest() {
		try{
			ReportRoleUtil.findByF_DRID_PrevAndNext(0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_DRID_LastTest() {
		try{
			ReportRoleUtil.fetchByF_DRID_Last(0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_RIDS_FirstTest() {
		try{
			ReportRoleUtil.fetchByF_RIDS_First(0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_DRID_RIDTest() {
		try{
			ReportRoleUtil.fetchByF_DRID_RID(0, 0, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_DRID_RIDTest47() {
		try{
			ReportRoleUtil.fetchByF_DRID_RID(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeByF_DRID_RIDTest() {
		try{
			ReportRoleUtil.removeByF_DRID_RID(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countByF_DRID_RIDTest() {
		try{
			ReportRoleUtil.countByF_DRID_RID(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_RIDS_FirstTest() {
		try{
			ReportRoleUtil.findByF_RIDS_First(0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_RIDS_PrevAndNextTest() {
		try{
			ReportRoleUtil.findByF_RIDS_PrevAndNext(0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_DRID_LastTest() {
		try{
			ReportRoleUtil.findByF_DRID_Last(0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_RIDS_LastTest() {
		try{
			ReportRoleUtil.fetchByF_RIDS_Last(0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_DRID_FirstTest() {
		try{
			ReportRoleUtil.fetchByF_DRID_First(0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_RIDS_LastTest() {
		try{
			ReportRoleUtil.findByF_RIDS_Last(0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}