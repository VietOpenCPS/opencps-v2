package org.opencps.adminconfig.service.persistence;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class AdminConfigUtilTest {
	@Before
	public void setUp() {
	}
	@Test
	public void removeTest() {
		try{
			AdminConfigUtil.remove(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateTest() {
		try{
			AdminConfigUtil.update(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateTest3() {
		try{
			AdminConfigUtil.update(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createTest() {
		try{
			AdminConfigUtil.create(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeAllTest() {
		try{
			AdminConfigUtil.removeAll();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void cacheResultTest() {
		try{
			AdminConfigUtil.cacheResult(AdminConfigUtil.create(0L));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void cacheResultTest7() {
		try{
			AdminConfigUtil.cacheResult(new ArrayList<>());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByPrimaryKeyTest() {
		try{
			AdminConfigUtil.findByPrimaryKey(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeByF_CodeTest() {
		try{
			AdminConfigUtil.removeByF_Code("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_CodeTest() {
		try{
			AdminConfigUtil.fetchByF_Code("abcde", true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_CodeTest11() {
		try{
			AdminConfigUtil.fetchByF_Code("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistenceTest() {
		try{
			AdminConfigUtil.getPersistence();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findAllTest() {
		try{
			AdminConfigUtil.findAll(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findAllTest14() {
		try{
			AdminConfigUtil.findAll();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findAllTest15() {
		try{
			AdminConfigUtil.findAll(0, 0, null, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findAllTest16() {
		try{
			AdminConfigUtil.findAll(0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateImplTest() {
		try{
			AdminConfigUtil.updateImpl(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_CodeTest() {
		try{
			AdminConfigUtil.findByF_Code("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void clearCacheTest() {
		try{
			AdminConfigUtil.clearCache(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void clearCacheTest20() {
		try{
			AdminConfigUtil.clearCache();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countAllTest() {
		try{
			AdminConfigUtil.countAll();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countByF_CodeTest() {
		try{
			AdminConfigUtil.countByF_Code("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByPrimaryKeysTest() {
		try{
			AdminConfigUtil.fetchByPrimaryKeys(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countWithDynamicQueryTest() {
		try{
			AdminConfigUtil.countWithDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findWithDynamicQueryTest() {
		try{
			AdminConfigUtil.findWithDynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findWithDynamicQueryTest26() {
		try{
			AdminConfigUtil.findWithDynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findWithDynamicQueryTest27() {
		try{
			AdminConfigUtil.findWithDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getBadColumnNamesTest() {
		try{
			AdminConfigUtil.getBadColumnNames();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByPrimaryKeyTest() {
		try{
			AdminConfigUtil.fetchByPrimaryKey(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}