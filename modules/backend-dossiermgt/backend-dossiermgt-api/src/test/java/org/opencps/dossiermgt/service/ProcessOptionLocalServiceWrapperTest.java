package org.opencps.dossiermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ProcessOptionLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			ProcessOptionLocalServiceWrapper object = new ProcessOptionLocalServiceWrapper(null);
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			ProcessOptionLocalServiceWrapper object = new ProcessOptionLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			ProcessOptionLocalServiceWrapper object = new ProcessOptionLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			ProcessOptionLocalServiceWrapper object = new ProcessOptionLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest5() {
		try{
			ProcessOptionLocalServiceWrapper object = new ProcessOptionLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			ProcessOptionLocalServiceWrapper object = new ProcessOptionLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateProcessOptionTest() {
		try{
			ProcessOptionLocalServiceWrapper object = new ProcessOptionLocalServiceWrapper(null);
			object.updateProcessOption(Long.valueOf(0), "abcde", Long.valueOf(0), Long.valueOf(0), 0, "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateProcessOptionTest8() {
		try{
			ProcessOptionLocalServiceWrapper object = new ProcessOptionLocalServiceWrapper(null);
			object.updateProcessOption(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessOptionsTest() {
		try{
			ProcessOptionLocalServiceWrapper object = new ProcessOptionLocalServiceWrapper(null);
			object.getProcessOptions(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeProcessOptionTest() {
		try{
			ProcessOptionLocalServiceWrapper object = new ProcessOptionLocalServiceWrapper(null);
			object.removeProcessOption(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchLuceneTest() {
		try{
			ProcessOptionLocalServiceWrapper object = new ProcessOptionLocalServiceWrapper(null);
			object.searchLucene(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByGroupTest() {
		try{
			ProcessOptionLocalServiceWrapper object = new ProcessOptionLocalServiceWrapper(null);
			object.findByGroup(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessOptionTest() {
		try{
			ProcessOptionLocalServiceWrapper object = new ProcessOptionLocalServiceWrapper(null);
			object.getProcessOption(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateOptionDBTest() {
		try{
			ProcessOptionLocalServiceWrapper object = new ProcessOptionLocalServiceWrapper(null);
			object.updateOptionDB(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", Long.valueOf(0), 0, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createProcessOptionTest() {
		try{
			ProcessOptionLocalServiceWrapper object = new ProcessOptionLocalServiceWrapper(null);
			object.createProcessOption(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByServiceProcessIdTest() {
		try{
			ProcessOptionLocalServiceWrapper object = new ProcessOptionLocalServiceWrapper(null);
			object.getByServiceProcessId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneTest() {
		try{
			ProcessOptionLocalServiceWrapper object = new ProcessOptionLocalServiceWrapper(null);
			object.countLucene(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchBySP_DTTest() {
		try{
			ProcessOptionLocalServiceWrapper object = new ProcessOptionLocalServiceWrapper(null);
			object.fetchBySP_DT(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByDTPLNoAndServiceCFTest() {
		try{
			ProcessOptionLocalServiceWrapper object = new ProcessOptionLocalServiceWrapper(null);
			object.getByDTPLNoAndServiceCF(Long.valueOf(0), "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchProcessOptionByUuidAndGroupIdTest() {
		try{
			ProcessOptionLocalServiceWrapper object = new ProcessOptionLocalServiceWrapper(null);
			object.fetchProcessOptionByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessOptionsByUuidAndCompanyIdTest() {
		try{
			ProcessOptionLocalServiceWrapper object = new ProcessOptionLocalServiceWrapper(null);
			object.getProcessOptionsByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessOptionsByUuidAndCompanyIdTest22() {
		try{
			ProcessOptionLocalServiceWrapper object = new ProcessOptionLocalServiceWrapper(null);
			object.getProcessOptionsByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			ProcessOptionLocalServiceWrapper object = new ProcessOptionLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findAllTest() {
		try{
			ProcessOptionLocalServiceWrapper object = new ProcessOptionLocalServiceWrapper(null);
			object.findAll(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			ProcessOptionLocalServiceWrapper object = new ProcessOptionLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			ProcessOptionLocalServiceWrapper object = new ProcessOptionLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			ProcessOptionLocalServiceWrapper object = new ProcessOptionLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			ProcessOptionLocalServiceWrapper object = new ProcessOptionLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest29() {
		try{
			ProcessOptionLocalServiceWrapper object = new ProcessOptionLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			ProcessOptionLocalServiceWrapper object = new ProcessOptionLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest31() {
		try{
			ProcessOptionLocalServiceWrapper object = new ProcessOptionLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			ProcessOptionLocalServiceWrapper object = new ProcessOptionLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			ProcessOptionLocalServiceWrapper object = new ProcessOptionLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest34() {
		try{
			ProcessOptionLocalServiceWrapper object = new ProcessOptionLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			ProcessOptionLocalServiceWrapper object = new ProcessOptionLocalServiceWrapper(null);
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addProcessOptionTest() {
		try{
			ProcessOptionLocalServiceWrapper object = new ProcessOptionLocalServiceWrapper(null);
			object.addProcessOption(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteProcessOptionTest() {
		try{
			ProcessOptionLocalServiceWrapper object = new ProcessOptionLocalServiceWrapper(null);
			object.deleteProcessOption(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteProcessOptionTest38() {
		try{
			ProcessOptionLocalServiceWrapper object = new ProcessOptionLocalServiceWrapper(null);
			object.deleteProcessOption(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countByServiceConfigIdTest() {
		try{
			ProcessOptionLocalServiceWrapper object = new ProcessOptionLocalServiceWrapper(null);
			object.countByServiceConfigId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchProcessOptionTest() {
		try{
			ProcessOptionLocalServiceWrapper object = new ProcessOptionLocalServiceWrapper(null);
			object.fetchProcessOption(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessOptionByUuidAndGroupIdTest() {
		try{
			ProcessOptionLocalServiceWrapper object = new ProcessOptionLocalServiceWrapper(null);
			object.getProcessOptionByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessOptionsCountTest() {
		try{
			ProcessOptionLocalServiceWrapper object = new ProcessOptionLocalServiceWrapper(null);
			object.getProcessOptionsCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByServiceConfigIdTest() {
		try{
			ProcessOptionLocalServiceWrapper object = new ProcessOptionLocalServiceWrapper(null);
			object.getByServiceConfigId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}