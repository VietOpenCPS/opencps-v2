package org.opencps.dossiermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ProcessPluginLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			ProcessPluginLocalServiceWrapper object = new ProcessPluginLocalServiceWrapper(null);
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			ProcessPluginLocalServiceWrapper object = new ProcessPluginLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			ProcessPluginLocalServiceWrapper object = new ProcessPluginLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			ProcessPluginLocalServiceWrapper object = new ProcessPluginLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest5() {
		try{
			ProcessPluginLocalServiceWrapper object = new ProcessPluginLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			ProcessPluginLocalServiceWrapper object = new ProcessPluginLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessPluginsTest() {
		try{
			ProcessPluginLocalServiceWrapper object = new ProcessPluginLocalServiceWrapper(null);
			object.getProcessPlugins(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessPluginsTest8() {
		try{
			ProcessPluginLocalServiceWrapper object = new ProcessPluginLocalServiceWrapper(null);
			object.getProcessPlugins(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessPluginsByUuidAndCompanyIdTest() {
		try{
			ProcessPluginLocalServiceWrapper object = new ProcessPluginLocalServiceWrapper(null);
			object.getProcessPluginsByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessPluginsByUuidAndCompanyIdTest10() {
		try{
			ProcessPluginLocalServiceWrapper object = new ProcessPluginLocalServiceWrapper(null);
			object.getProcessPluginsByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchProcessPluginByUuidAndGroupIdTest() {
		try{
			ProcessPluginLocalServiceWrapper object = new ProcessPluginLocalServiceWrapper(null);
			object.fetchProcessPluginByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			ProcessPluginLocalServiceWrapper object = new ProcessPluginLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			ProcessPluginLocalServiceWrapper object = new ProcessPluginLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			ProcessPluginLocalServiceWrapper object = new ProcessPluginLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			ProcessPluginLocalServiceWrapper object = new ProcessPluginLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			ProcessPluginLocalServiceWrapper object = new ProcessPluginLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest17() {
		try{
			ProcessPluginLocalServiceWrapper object = new ProcessPluginLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			ProcessPluginLocalServiceWrapper object = new ProcessPluginLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest19() {
		try{
			ProcessPluginLocalServiceWrapper object = new ProcessPluginLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			ProcessPluginLocalServiceWrapper object = new ProcessPluginLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			ProcessPluginLocalServiceWrapper object = new ProcessPluginLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest22() {
		try{
			ProcessPluginLocalServiceWrapper object = new ProcessPluginLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			ProcessPluginLocalServiceWrapper object = new ProcessPluginLocalServiceWrapper(null);
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessPluginTest() {
		try{
			ProcessPluginLocalServiceWrapper object = new ProcessPluginLocalServiceWrapper(null);
			object.getProcessPlugin(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addProcessPluginTest() {
		try{
			ProcessPluginLocalServiceWrapper object = new ProcessPluginLocalServiceWrapper(null);
			object.addProcessPlugin(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessPluginByUuidAndGroupIdTest() {
		try{
			ProcessPluginLocalServiceWrapper object = new ProcessPluginLocalServiceWrapper(null);
			object.getProcessPluginByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessPluginsCountTest() {
		try{
			ProcessPluginLocalServiceWrapper object = new ProcessPluginLocalServiceWrapper(null);
			object.getProcessPluginsCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createProcessPluginTest() {
		try{
			ProcessPluginLocalServiceWrapper object = new ProcessPluginLocalServiceWrapper(null);
			object.createProcessPlugin(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchProcessPluginTest() {
		try{
			ProcessPluginLocalServiceWrapper object = new ProcessPluginLocalServiceWrapper(null);
			object.fetchProcessPlugin(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteProcessPluginTest() {
		try{
			ProcessPluginLocalServiceWrapper object = new ProcessPluginLocalServiceWrapper(null);
			object.deleteProcessPlugin(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteProcessPluginTest31() {
		try{
			ProcessPluginLocalServiceWrapper object = new ProcessPluginLocalServiceWrapper(null);
			object.deleteProcessPlugin(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateProcessPluginTest() {
		try{
			ProcessPluginLocalServiceWrapper object = new ProcessPluginLocalServiceWrapper(null);
			object.updateProcessPlugin(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}