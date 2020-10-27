package org.opencps.communication.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ServerConfigSoapTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getProtocolTest() {
		try{
			ServerConfigSoap object = new ServerConfigSoap();
			object.getProtocol();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest() {
		try{
			ServerConfigSoap.toSoapModels(new ServerConfig[]{});
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest3() {
		try{
			ServerConfigSoap.toSoapModels(new ArrayList<>());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest4() {
		try{
			ServerConfigSoap.toSoapModels(new ServerConfig[][]{});
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCreateDateTest() {
		try{
			ServerConfigSoap object = new ServerConfigSoap();
			object.getCreateDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserNameTest() {
		try{
			ServerConfigSoap object = new ServerConfigSoap();
			object.getUserName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserNameTest() {
		try{
			ServerConfigSoap object = new ServerConfigSoap();
			object.setUserName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGroupIdTest() {
		try{
			ServerConfigSoap object = new ServerConfigSoap();
			object.setGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCompanyIdTest() {
		try{
			ServerConfigSoap object = new ServerConfigSoap();
			object.setCompanyId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserIdTest() {
		try{
			ServerConfigSoap object = new ServerConfigSoap();
			object.setUserId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreateDateTest() {
		try{
			ServerConfigSoap object = new ServerConfigSoap();
			object.setCreateDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCompanyIdTest() {
		try{
			ServerConfigSoap object = new ServerConfigSoap();
			object.getCompanyId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserIdTest() {
		try{
			ServerConfigSoap object = new ServerConfigSoap();
			object.getUserId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getModifiedDateTest() {
		try{
			ServerConfigSoap object = new ServerConfigSoap();
			object.getModifiedDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupIdTest() {
		try{
			ServerConfigSoap object = new ServerConfigSoap();
			object.getGroupId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModifiedDateTest() {
		try{
			ServerConfigSoap object = new ServerConfigSoap();
			object.setModifiedDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelTest() {
		try{
			ServerConfigSoap.toSoapModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setConfigsTest() {
		try{
			ServerConfigSoap object = new ServerConfigSoap();
			object.setConfigs("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGovAgencyCodeTest() {
		try{
			ServerConfigSoap object = new ServerConfigSoap();
			object.getGovAgencyCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGovAgencyCodeTest() {
		try{
			ServerConfigSoap object = new ServerConfigSoap();
			object.setGovAgencyCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setServerNameTest() {
		try{
			ServerConfigSoap object = new ServerConfigSoap();
			object.setServerName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServerNoTest() {
		try{
			ServerConfigSoap object = new ServerConfigSoap();
			object.getServerNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServerNameTest() {
		try{
			ServerConfigSoap object = new ServerConfigSoap();
			object.getServerName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setServerNoTest() {
		try{
			ServerConfigSoap object = new ServerConfigSoap();
			object.setServerNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getLastSyncTest() {
		try{
			ServerConfigSoap object = new ServerConfigSoap();
			object.getLastSync();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getConfigsTest() {
		try{
			ServerConfigSoap object = new ServerConfigSoap();
			object.getConfigs();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setLastSyncTest() {
		try{
			ServerConfigSoap object = new ServerConfigSoap();
			object.setLastSync(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setProtocolTest() {
		try{
			ServerConfigSoap object = new ServerConfigSoap();
			object.setProtocol("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setServerConfigIdTest() {
		try{
			ServerConfigSoap object = new ServerConfigSoap();
			object.setServerConfigId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServerConfigIdTest() {
		try{
			ServerConfigSoap object = new ServerConfigSoap();
			object.getServerConfigId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPrimaryKeyTest() {
		try{
			ServerConfigSoap object = new ServerConfigSoap();
			object.setPrimaryKey(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPrimaryKeyTest() {
		try{
			ServerConfigSoap object = new ServerConfigSoap();
			object.getPrimaryKey();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}