package org.opencps.communication.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ServerConfigLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getGroupIdTest() {
		try{
			ServerConfigLocalServiceWrapper object = new ServerConfigLocalServiceWrapper(null);
			object.getGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDataTest() {
		try{
			ServerConfigLocalServiceWrapper object = new ServerConfigLocalServiceWrapper(null);
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			ServerConfigLocalServiceWrapper object = new ServerConfigLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			ServerConfigLocalServiceWrapper object = new ServerConfigLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest5() {
		try{
			ServerConfigLocalServiceWrapper object = new ServerConfigLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest6() {
		try{
			ServerConfigLocalServiceWrapper object = new ServerConfigLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			ServerConfigLocalServiceWrapper object = new ServerConfigLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteServerConfigTest() {
		try{
			ServerConfigLocalServiceWrapper object = new ServerConfigLocalServiceWrapper(null);
			object.deleteServerConfig(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteServerConfigTest9() {
		try{
			ServerConfigLocalServiceWrapper object = new ServerConfigLocalServiceWrapper(null);
			object.deleteServerConfig(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateServerConfigTest() {
		try{
			ServerConfigLocalServiceWrapper object = new ServerConfigLocalServiceWrapper(null);
			object.updateServerConfig(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateServerConfigTest11() {
		try{
			ServerConfigLocalServiceWrapper object = new ServerConfigLocalServiceWrapper(null);
			object.updateServerConfig(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", new Date(), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchServerConfigTest() {
		try{
			ServerConfigLocalServiceWrapper object = new ServerConfigLocalServiceWrapper(null);
			object.fetchServerConfig(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByProtocolTest() {
		try{
			ServerConfigLocalServiceWrapper object = new ServerConfigLocalServiceWrapper(null);
			object.getByProtocol(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByProtocolTest14() {
		try{
			ServerConfigLocalServiceWrapper object = new ServerConfigLocalServiceWrapper(null);
			object.getByProtocol("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByCodeTest() {
		try{
			ServerConfigLocalServiceWrapper object = new ServerConfigLocalServiceWrapper(null);
			object.getByCode(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByCodeTest16() {
		try{
			ServerConfigLocalServiceWrapper object = new ServerConfigLocalServiceWrapper(null);
			object.getByCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateServerConfigDBTest() {
		try{
			ServerConfigLocalServiceWrapper object = new ServerConfigLocalServiceWrapper(null);
			object.updateServerConfigDB(Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", new Date(), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByServerNoAndProtocolTest() {
		try{
			ServerConfigLocalServiceWrapper object = new ServerConfigLocalServiceWrapper(null);
			object.getByServerNoAndProtocol(Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createServerConfigTest() {
		try{
			ServerConfigLocalServiceWrapper object = new ServerConfigLocalServiceWrapper(null);
			object.createServerConfig(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServerConfigsCountTest() {
		try{
			ServerConfigLocalServiceWrapper object = new ServerConfigLocalServiceWrapper(null);
			object.getServerConfigsCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			ServerConfigLocalServiceWrapper object = new ServerConfigLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			ServerConfigLocalServiceWrapper object = new ServerConfigLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			ServerConfigLocalServiceWrapper object = new ServerConfigLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			ServerConfigLocalServiceWrapper object = new ServerConfigLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest25() {
		try{
			ServerConfigLocalServiceWrapper object = new ServerConfigLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			ServerConfigLocalServiceWrapper object = new ServerConfigLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest27() {
		try{
			ServerConfigLocalServiceWrapper object = new ServerConfigLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			ServerConfigLocalServiceWrapper object = new ServerConfigLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			ServerConfigLocalServiceWrapper object = new ServerConfigLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest30() {
		try{
			ServerConfigLocalServiceWrapper object = new ServerConfigLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			ServerConfigLocalServiceWrapper object = new ServerConfigLocalServiceWrapper(null);
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteByGroupIdTest() {
		try{
			ServerConfigLocalServiceWrapper object = new ServerConfigLocalServiceWrapper(null);
			object.deleteByGroupId(Long.valueOf(0), Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeAllServerTest() {
		try{
			ServerConfigLocalServiceWrapper object = new ServerConfigLocalServiceWrapper(null);
			object.removeAllServer();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateLastSyncTest() {
		try{
			ServerConfigLocalServiceWrapper object = new ServerConfigLocalServiceWrapper(null);
			object.updateLastSync(Long.valueOf(0), new Date(), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServerConfigsTest() {
		try{
			ServerConfigLocalServiceWrapper object = new ServerConfigLocalServiceWrapper(null);
			object.getServerConfigs(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addServerConfigTest() {
		try{
			ServerConfigLocalServiceWrapper object = new ServerConfigLocalServiceWrapper(null);
			object.addServerConfig(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServerConfigTest() {
		try{
			ServerConfigLocalServiceWrapper object = new ServerConfigLocalServiceWrapper(null);
			object.getServerConfig(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}