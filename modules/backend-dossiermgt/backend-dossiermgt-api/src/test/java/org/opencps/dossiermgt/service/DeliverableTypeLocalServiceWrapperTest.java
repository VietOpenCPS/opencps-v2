package org.opencps.dossiermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DeliverableTypeLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			DeliverableTypeLocalServiceWrapper object = new DeliverableTypeLocalServiceWrapper(null);
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			DeliverableTypeLocalServiceWrapper object = new DeliverableTypeLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			DeliverableTypeLocalServiceWrapper object = new DeliverableTypeLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			DeliverableTypeLocalServiceWrapper object = new DeliverableTypeLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest5() {
		try{
			DeliverableTypeLocalServiceWrapper object = new DeliverableTypeLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			DeliverableTypeLocalServiceWrapper object = new DeliverableTypeLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByGTest() {
		try{
			DeliverableTypeLocalServiceWrapper object = new DeliverableTypeLocalServiceWrapper(null);
			object.findByG(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByTypeCodeTest() {
		try{
			DeliverableTypeLocalServiceWrapper object = new DeliverableTypeLocalServiceWrapper(null);
			object.getByTypeCode("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByCodeTest() {
		try{
			DeliverableTypeLocalServiceWrapper object = new DeliverableTypeLocalServiceWrapper(null);
			object.getByCode(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAllDeliverableTypesTest() {
		try{
			DeliverableTypeLocalServiceWrapper object = new DeliverableTypeLocalServiceWrapper(null);
			object.getAllDeliverableTypes(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDeliverableTypeDBTest() {
		try{
			DeliverableTypeLocalServiceWrapper object = new DeliverableTypeLocalServiceWrapper(null);
			object.updateDeliverableTypeDB(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDeliverableTypeTest() {
		try{
			DeliverableTypeLocalServiceWrapper object = new DeliverableTypeLocalServiceWrapper(null);
			object.updateDeliverableType(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDeliverableTypeTest13() {
		try{
			DeliverableTypeLocalServiceWrapper object = new DeliverableTypeLocalServiceWrapper(null);
			object.updateDeliverableType(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDeliverableTypeTest() {
		try{
			DeliverableTypeLocalServiceWrapper object = new DeliverableTypeLocalServiceWrapper(null);
			object.addDeliverableType(Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDeliverableTypeTest15() {
		try{
			DeliverableTypeLocalServiceWrapper object = new DeliverableTypeLocalServiceWrapper(null);
			object.addDeliverableType(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDeliverableTypesTest() {
		try{
			DeliverableTypeLocalServiceWrapper object = new DeliverableTypeLocalServiceWrapper(null);
			object.getDeliverableTypes(Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDeliverableTypesTest17() {
		try{
			DeliverableTypeLocalServiceWrapper object = new DeliverableTypeLocalServiceWrapper(null);
			object.getDeliverableTypes(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateFormReportTest() {
		try{
			DeliverableTypeLocalServiceWrapper object = new DeliverableTypeLocalServiceWrapper(null);
			object.updateFormReport(Long.valueOf(0), Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateFormScriptTest() {
		try{
			DeliverableTypeLocalServiceWrapper object = new DeliverableTypeLocalServiceWrapper(null);
			object.updateFormScript(Long.valueOf(0), Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDeliverableTypeTest() {
		try{
			DeliverableTypeLocalServiceWrapper object = new DeliverableTypeLocalServiceWrapper(null);
			object.getDeliverableType(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDeliverableTypebyIdTest() {
		try{
			DeliverableTypeLocalServiceWrapper object = new DeliverableTypeLocalServiceWrapper(null);
			object.getDeliverableTypebyId(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeDeliverableTypeTest() {
		try{
			DeliverableTypeLocalServiceWrapper object = new DeliverableTypeLocalServiceWrapper(null);
			object.removeDeliverableType(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDeliverableTypeByUuidAndGroupIdTest() {
		try{
			DeliverableTypeLocalServiceWrapper object = new DeliverableTypeLocalServiceWrapper(null);
			object.getDeliverableTypeByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchDeliverableTypeByUuidAndGroupIdTest() {
		try{
			DeliverableTypeLocalServiceWrapper object = new DeliverableTypeLocalServiceWrapper(null);
			object.fetchDeliverableTypeByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDeliverableTypesByUuidAndCompanyIdTest() {
		try{
			DeliverableTypeLocalServiceWrapper object = new DeliverableTypeLocalServiceWrapper(null);
			object.getDeliverableTypesByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDeliverableTypesByUuidAndCompanyIdTest26() {
		try{
			DeliverableTypeLocalServiceWrapper object = new DeliverableTypeLocalServiceWrapper(null);
			object.getDeliverableTypesByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchDeliverableTypeTest() {
		try{
			DeliverableTypeLocalServiceWrapper object = new DeliverableTypeLocalServiceWrapper(null);
			object.fetchDeliverableType(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateMappingDataTest() {
		try{
			DeliverableTypeLocalServiceWrapper object = new DeliverableTypeLocalServiceWrapper(null);
			object.updateMappingData(Long.valueOf(0), Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDeliverableTypesCountTest() {
		try{
			DeliverableTypeLocalServiceWrapper object = new DeliverableTypeLocalServiceWrapper(null);
			object.getDeliverableTypesCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDeliverableTypeTest() {
		try{
			DeliverableTypeLocalServiceWrapper object = new DeliverableTypeLocalServiceWrapper(null);
			object.createDeliverableType(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDeliverableTypeTest() {
		try{
			DeliverableTypeLocalServiceWrapper object = new DeliverableTypeLocalServiceWrapper(null);
			object.deleteDeliverableType(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDeliverableTypeTest32() {
		try{
			DeliverableTypeLocalServiceWrapper object = new DeliverableTypeLocalServiceWrapper(null);
			object.deleteDeliverableType(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			DeliverableTypeLocalServiceWrapper object = new DeliverableTypeLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			DeliverableTypeLocalServiceWrapper object = new DeliverableTypeLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			DeliverableTypeLocalServiceWrapper object = new DeliverableTypeLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			DeliverableTypeLocalServiceWrapper object = new DeliverableTypeLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			DeliverableTypeLocalServiceWrapper object = new DeliverableTypeLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest38() {
		try{
			DeliverableTypeLocalServiceWrapper object = new DeliverableTypeLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			DeliverableTypeLocalServiceWrapper object = new DeliverableTypeLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest40() {
		try{
			DeliverableTypeLocalServiceWrapper object = new DeliverableTypeLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			DeliverableTypeLocalServiceWrapper object = new DeliverableTypeLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			DeliverableTypeLocalServiceWrapper object = new DeliverableTypeLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest43() {
		try{
			DeliverableTypeLocalServiceWrapper object = new DeliverableTypeLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			DeliverableTypeLocalServiceWrapper object = new DeliverableTypeLocalServiceWrapper(null);
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}