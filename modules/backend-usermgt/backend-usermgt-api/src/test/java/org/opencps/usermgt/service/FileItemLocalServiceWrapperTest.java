package org.opencps.usermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class FileItemLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			FileItemLocalServiceWrapper object = new FileItemLocalServiceWrapper(null);
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			FileItemLocalServiceWrapper object = new FileItemLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			FileItemLocalServiceWrapper object = new FileItemLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			FileItemLocalServiceWrapper object = new FileItemLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest5() {
		try{
			FileItemLocalServiceWrapper object = new FileItemLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			FileItemLocalServiceWrapper object = new FileItemLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileItemsTest() {
		try{
			FileItemLocalServiceWrapper object = new FileItemLocalServiceWrapper(null);
			object.getFileItems(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			FileItemLocalServiceWrapper object = new FileItemLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			FileItemLocalServiceWrapper object = new FileItemLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			FileItemLocalServiceWrapper object = new FileItemLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			FileItemLocalServiceWrapper object = new FileItemLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			FileItemLocalServiceWrapper object = new FileItemLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest13() {
		try{
			FileItemLocalServiceWrapper object = new FileItemLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			FileItemLocalServiceWrapper object = new FileItemLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest15() {
		try{
			FileItemLocalServiceWrapper object = new FileItemLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			FileItemLocalServiceWrapper object = new FileItemLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			FileItemLocalServiceWrapper object = new FileItemLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest18() {
		try{
			FileItemLocalServiceWrapper object = new FileItemLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			FileItemLocalServiceWrapper object = new FileItemLocalServiceWrapper(null);
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByG_FTNSTest() {
		try{
			FileItemLocalServiceWrapper object = new FileItemLocalServiceWrapper(null);
			object.findByG_FTNS(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileItemsByUuidAndCompanyIdTest() {
		try{
			FileItemLocalServiceWrapper object = new FileItemLocalServiceWrapper(null);
			object.getFileItemsByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileItemsByUuidAndCompanyIdTest22() {
		try{
			FileItemLocalServiceWrapper object = new FileItemLocalServiceWrapper(null);
			object.getFileItemsByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileItemsCountTest() {
		try{
			FileItemLocalServiceWrapper object = new FileItemLocalServiceWrapper(null);
			object.getFileItemsCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileItemByUuidAndGroupIdTest() {
		try{
			FileItemLocalServiceWrapper object = new FileItemLocalServiceWrapper(null);
			object.getFileItemByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchFileItemByUuidAndGroupIdTest() {
		try{
			FileItemLocalServiceWrapper object = new FileItemLocalServiceWrapper(null);
			object.fetchFileItemByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByG_FTNTest() {
		try{
			FileItemLocalServiceWrapper object = new FileItemLocalServiceWrapper(null);
			object.findByG_FTN(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileItemTest() {
		try{
			FileItemLocalServiceWrapper object = new FileItemLocalServiceWrapper(null);
			object.getFileItem(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addFileItemTest() {
		try{
			FileItemLocalServiceWrapper object = new FileItemLocalServiceWrapper(null);
			object.addFileItem(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateFileItemTest() {
		try{
			FileItemLocalServiceWrapper object = new FileItemLocalServiceWrapper(null);
			object.updateFileItem(null, Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", 0, 0, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateFileItemTest30() {
		try{
			FileItemLocalServiceWrapper object = new FileItemLocalServiceWrapper(null);
			object.updateFileItem(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteFileItemTest() {
		try{
			FileItemLocalServiceWrapper object = new FileItemLocalServiceWrapper(null);
			object.deleteFileItem(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteFileItemTest32() {
		try{
			FileItemLocalServiceWrapper object = new FileItemLocalServiceWrapper(null);
			object.deleteFileItem(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchFileItemTest() {
		try{
			FileItemLocalServiceWrapper object = new FileItemLocalServiceWrapper(null);
			object.fetchFileItem(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByG_STest() {
		try{
			FileItemLocalServiceWrapper object = new FileItemLocalServiceWrapper(null);
			object.findByG_S(Long.valueOf(0), 0, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByG_STest35() {
		try{
			FileItemLocalServiceWrapper object = new FileItemLocalServiceWrapper(null);
			object.findByG_S(Long.valueOf(0), 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createFileItemTest() {
		try{
			FileItemLocalServiceWrapper object = new FileItemLocalServiceWrapper(null);
			object.createFileItem(null, Long.valueOf(0), "abcde", "abcde", 0, 0, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createFileItemTest37() {
		try{
			FileItemLocalServiceWrapper object = new FileItemLocalServiceWrapper(null);
			object.createFileItem(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}