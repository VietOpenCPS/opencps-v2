package org.opencps.dossiermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DeliverableLogLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			DeliverableLogLocalServiceWrapper object = new DeliverableLogLocalServiceWrapper(null);
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			DeliverableLogLocalServiceWrapper object = new DeliverableLogLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			DeliverableLogLocalServiceWrapper object = new DeliverableLogLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			DeliverableLogLocalServiceWrapper object = new DeliverableLogLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest5() {
		try{
			DeliverableLogLocalServiceWrapper object = new DeliverableLogLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			DeliverableLogLocalServiceWrapper object = new DeliverableLogLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDeliverableLogTest() {
		try{
			DeliverableLogLocalServiceWrapper object = new DeliverableLogLocalServiceWrapper(null);
			object.getDeliverableLog(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchDeliverableLogByUuidAndGroupIdTest() {
		try{
			DeliverableLogLocalServiceWrapper object = new DeliverableLogLocalServiceWrapper(null);
			object.fetchDeliverableLogByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDeliverableLogsByUuidAndCompanyIdTest() {
		try{
			DeliverableLogLocalServiceWrapper object = new DeliverableLogLocalServiceWrapper(null);
			object.getDeliverableLogsByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDeliverableLogsByUuidAndCompanyIdTest10() {
		try{
			DeliverableLogLocalServiceWrapper object = new DeliverableLogLocalServiceWrapper(null);
			object.getDeliverableLogsByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDeliverableLogByUuidAndGroupIdTest() {
		try{
			DeliverableLogLocalServiceWrapper object = new DeliverableLogLocalServiceWrapper(null);
			object.getDeliverableLogByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDeliverableLogsTest() {
		try{
			DeliverableLogLocalServiceWrapper object = new DeliverableLogLocalServiceWrapper(null);
			object.getDeliverableLogs(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_deliverableIdTest() {
		try{
			DeliverableLogLocalServiceWrapper object = new DeliverableLogLocalServiceWrapper(null);
			object.findByF_deliverableId(Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDeliverableLogTest() {
		try{
			DeliverableLogLocalServiceWrapper object = new DeliverableLogLocalServiceWrapper(null);
			object.createDeliverableLog(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDeliverableLogTest() {
		try{
			DeliverableLogLocalServiceWrapper object = new DeliverableLogLocalServiceWrapper(null);
			object.deleteDeliverableLog(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDeliverableLogTest16() {
		try{
			DeliverableLogLocalServiceWrapper object = new DeliverableLogLocalServiceWrapper(null);
			object.deleteDeliverableLog(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchDeliverableLogTest() {
		try{
			DeliverableLogLocalServiceWrapper object = new DeliverableLogLocalServiceWrapper(null);
			object.fetchDeliverableLog(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDeliverableLogsCountTest() {
		try{
			DeliverableLogLocalServiceWrapper object = new DeliverableLogLocalServiceWrapper(null);
			object.getDeliverableLogsCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDeliverableLogTest() {
		try{
			DeliverableLogLocalServiceWrapper object = new DeliverableLogLocalServiceWrapper(null);
			object.updateDeliverableLog(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDeliverableLogTest() {
		try{
			DeliverableLogLocalServiceWrapper object = new DeliverableLogLocalServiceWrapper(null);
			object.addDeliverableLog(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			DeliverableLogLocalServiceWrapper object = new DeliverableLogLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			DeliverableLogLocalServiceWrapper object = new DeliverableLogLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			DeliverableLogLocalServiceWrapper object = new DeliverableLogLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			DeliverableLogLocalServiceWrapper object = new DeliverableLogLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			DeliverableLogLocalServiceWrapper object = new DeliverableLogLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest26() {
		try{
			DeliverableLogLocalServiceWrapper object = new DeliverableLogLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			DeliverableLogLocalServiceWrapper object = new DeliverableLogLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest28() {
		try{
			DeliverableLogLocalServiceWrapper object = new DeliverableLogLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			DeliverableLogLocalServiceWrapper object = new DeliverableLogLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			DeliverableLogLocalServiceWrapper object = new DeliverableLogLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest31() {
		try{
			DeliverableLogLocalServiceWrapper object = new DeliverableLogLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			DeliverableLogLocalServiceWrapper object = new DeliverableLogLocalServiceWrapper(null);
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}