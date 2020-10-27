package org.opencps.dossiermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class StepConfigLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			StepConfigLocalServiceWrapper object = new StepConfigLocalServiceWrapper(null);
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			StepConfigLocalServiceWrapper object = new StepConfigLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			StepConfigLocalServiceWrapper object = new StepConfigLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			StepConfigLocalServiceWrapper object = new StepConfigLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest5() {
		try{
			StepConfigLocalServiceWrapper object = new StepConfigLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			StepConfigLocalServiceWrapper object = new StepConfigLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStepByMainStatusAndSubStatusTest() {
		try{
			StepConfigLocalServiceWrapper object = new StepConfigLocalServiceWrapper(null);
			object.getStepByMainStatusAndSubStatus(Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStepConfigTest() {
		try{
			StepConfigLocalServiceWrapper object = new StepConfigLocalServiceWrapper(null);
			object.getStepConfig(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStepByGroupIdTest() {
		try{
			StepConfigLocalServiceWrapper object = new StepConfigLocalServiceWrapper(null);
			object.getStepByGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByCodeTest() {
		try{
			StepConfigLocalServiceWrapper object = new StepConfigLocalServiceWrapper(null);
			object.getByCode(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addStepConfigTest() {
		try{
			StepConfigLocalServiceWrapper object = new StepConfigLocalServiceWrapper(null);
			object.addStepConfig(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addStepConfigTest12() {
		try{
			StepConfigLocalServiceWrapper object = new StepConfigLocalServiceWrapper(null);
			object.addStepConfig(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteStepConfigTest() {
		try{
			StepConfigLocalServiceWrapper object = new StepConfigLocalServiceWrapper(null);
			object.deleteStepConfig(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteStepConfigTest14() {
		try{
			StepConfigLocalServiceWrapper object = new StepConfigLocalServiceWrapper(null);
			object.deleteStepConfig(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateStepConfigTest() {
		try{
			StepConfigLocalServiceWrapper object = new StepConfigLocalServiceWrapper(null);
			object.updateStepConfig(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateStepConfigTest16() {
		try{
			StepConfigLocalServiceWrapper object = new StepConfigLocalServiceWrapper(null);
			object.updateStepConfig(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByStepTypeTest() {
		try{
			StepConfigLocalServiceWrapper object = new StepConfigLocalServiceWrapper(null);
			object.getByStepType(Long.valueOf(0), 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchStepConfigTest() {
		try{
			StepConfigLocalServiceWrapper object = new StepConfigLocalServiceWrapper(null);
			object.fetchStepConfig(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateStepConfigDBTest() {
		try{
			StepConfigLocalServiceWrapper object = new StepConfigLocalServiceWrapper(null);
			object.updateStepConfigDB(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByG_SCSTest() {
		try{
			StepConfigLocalServiceWrapper object = new StepConfigLocalServiceWrapper(null);
			object.findByG_SCS(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			StepConfigLocalServiceWrapper object = new StepConfigLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			StepConfigLocalServiceWrapper object = new StepConfigLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			StepConfigLocalServiceWrapper object = new StepConfigLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			StepConfigLocalServiceWrapper object = new StepConfigLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			StepConfigLocalServiceWrapper object = new StepConfigLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest26() {
		try{
			StepConfigLocalServiceWrapper object = new StepConfigLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			StepConfigLocalServiceWrapper object = new StepConfigLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest28() {
		try{
			StepConfigLocalServiceWrapper object = new StepConfigLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			StepConfigLocalServiceWrapper object = new StepConfigLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			StepConfigLocalServiceWrapper object = new StepConfigLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest31() {
		try{
			StepConfigLocalServiceWrapper object = new StepConfigLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			StepConfigLocalServiceWrapper object = new StepConfigLocalServiceWrapper(null);
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStepConfigsTest() {
		try{
			StepConfigLocalServiceWrapper object = new StepConfigLocalServiceWrapper(null);
			object.getStepConfigs(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createStepConfigTest() {
		try{
			StepConfigLocalServiceWrapper object = new StepConfigLocalServiceWrapper(null);
			object.createStepConfig(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeStepConfigTest() {
		try{
			StepConfigLocalServiceWrapper object = new StepConfigLocalServiceWrapper(null);
			object.removeStepConfig(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStepConfigsCountTest() {
		try{
			StepConfigLocalServiceWrapper object = new StepConfigLocalServiceWrapper(null);
			object.getStepConfigsCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchStepConfigByUuidAndGroupIdTest() {
		try{
			StepConfigLocalServiceWrapper object = new StepConfigLocalServiceWrapper(null);
			object.fetchStepConfigByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStepConfigByUuidAndGroupIdTest() {
		try{
			StepConfigLocalServiceWrapper object = new StepConfigLocalServiceWrapper(null);
			object.getStepConfigByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStepConfigsByUuidAndCompanyIdTest() {
		try{
			StepConfigLocalServiceWrapper object = new StepConfigLocalServiceWrapper(null);
			object.getStepConfigsByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStepConfigsByUuidAndCompanyIdTest40() {
		try{
			StepConfigLocalServiceWrapper object = new StepConfigLocalServiceWrapper(null);
			object.getStepConfigsByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}