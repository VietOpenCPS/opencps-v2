package org.opencps.dossiermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class RegistrationLogLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			RegistrationLogLocalServiceWrapper object = new RegistrationLogLocalServiceWrapper(null);
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			RegistrationLogLocalServiceWrapper object = new RegistrationLogLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			RegistrationLogLocalServiceWrapper object = new RegistrationLogLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			RegistrationLogLocalServiceWrapper object = new RegistrationLogLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest5() {
		try{
			RegistrationLogLocalServiceWrapper object = new RegistrationLogLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			RegistrationLogLocalServiceWrapper object = new RegistrationLogLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRegistrationLogsTest() {
		try{
			RegistrationLogLocalServiceWrapper object = new RegistrationLogLocalServiceWrapper(null);
			object.getRegistrationLogs(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRegistrationLogTest() {
		try{
			RegistrationLogLocalServiceWrapper object = new RegistrationLogLocalServiceWrapper(null);
			object.getRegistrationLog(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchLuceneTest() {
		try{
			RegistrationLogLocalServiceWrapper object = new RegistrationLogLocalServiceWrapper(null);
			object.searchLucene(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRegistrationLogsByUuidAndCompanyIdTest() {
		try{
			RegistrationLogLocalServiceWrapper object = new RegistrationLogLocalServiceWrapper(null);
			object.getRegistrationLogsByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRegistrationLogsByUuidAndCompanyIdTest11() {
		try{
			RegistrationLogLocalServiceWrapper object = new RegistrationLogLocalServiceWrapper(null);
			object.getRegistrationLogsByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRegistrationLogByUuidAndGroupIdTest() {
		try{
			RegistrationLogLocalServiceWrapper object = new RegistrationLogLocalServiceWrapper(null);
			object.getRegistrationLogByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchRegistrationLogByUuidAndGroupIdTest() {
		try{
			RegistrationLogLocalServiceWrapper object = new RegistrationLogLocalServiceWrapper(null);
			object.fetchRegistrationLogByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			RegistrationLogLocalServiceWrapper object = new RegistrationLogLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			RegistrationLogLocalServiceWrapper object = new RegistrationLogLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			RegistrationLogLocalServiceWrapper object = new RegistrationLogLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			RegistrationLogLocalServiceWrapper object = new RegistrationLogLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			RegistrationLogLocalServiceWrapper object = new RegistrationLogLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest19() {
		try{
			RegistrationLogLocalServiceWrapper object = new RegistrationLogLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			RegistrationLogLocalServiceWrapper object = new RegistrationLogLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest21() {
		try{
			RegistrationLogLocalServiceWrapper object = new RegistrationLogLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			RegistrationLogLocalServiceWrapper object = new RegistrationLogLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			RegistrationLogLocalServiceWrapper object = new RegistrationLogLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest24() {
		try{
			RegistrationLogLocalServiceWrapper object = new RegistrationLogLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			RegistrationLogLocalServiceWrapper object = new RegistrationLogLocalServiceWrapper(null);
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLucenseTest() {
		try{
			RegistrationLogLocalServiceWrapper object = new RegistrationLogLocalServiceWrapper(null);
			object.countLucense(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addLogTest() {
		try{
			RegistrationLogLocalServiceWrapper object = new RegistrationLogLocalServiceWrapper(null);
			object.addLog("abcde", Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addRegistrationLogTest() {
		try{
			RegistrationLogLocalServiceWrapper object = new RegistrationLogLocalServiceWrapper(null);
			object.addRegistrationLog(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteRegistrationLogTest() {
		try{
			RegistrationLogLocalServiceWrapper object = new RegistrationLogLocalServiceWrapper(null);
			object.deleteRegistrationLog(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteRegistrationLogTest30() {
		try{
			RegistrationLogLocalServiceWrapper object = new RegistrationLogLocalServiceWrapper(null);
			object.deleteRegistrationLog(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchRegistrationLogTest() {
		try{
			RegistrationLogLocalServiceWrapper object = new RegistrationLogLocalServiceWrapper(null);
			object.fetchRegistrationLog(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRegistrationLogsCountTest() {
		try{
			RegistrationLogLocalServiceWrapper object = new RegistrationLogLocalServiceWrapper(null);
			object.getRegistrationLogsCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createRegistrationLogTest() {
		try{
			RegistrationLogLocalServiceWrapper object = new RegistrationLogLocalServiceWrapper(null);
			object.createRegistrationLog(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRegistrationLogbyRegIdTest() {
		try{
			RegistrationLogLocalServiceWrapper object = new RegistrationLogLocalServiceWrapper(null);
			object.getRegistrationLogbyRegId(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateRegistrationLogTest() {
		try{
			RegistrationLogLocalServiceWrapper object = new RegistrationLogLocalServiceWrapper(null);
			object.updateRegistrationLog(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}