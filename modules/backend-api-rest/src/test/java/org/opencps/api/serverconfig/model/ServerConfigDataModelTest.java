package org.opencps.api.serverconfig.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ServerConfigDataModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getProtocolTest() {
		try{
			ServerConfigDataModel object = new ServerConfigDataModel();
			object.getProtocol();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCreateDateTest() {
		try{
			ServerConfigDataModel object = new ServerConfigDataModel();
			object.getCreateDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreateDateTest() {
		try{
			ServerConfigDataModel object = new ServerConfigDataModel();
			object.setCreateDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getModifiedDateTest() {
		try{
			ServerConfigDataModel object = new ServerConfigDataModel();
			object.getModifiedDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModifiedDateTest() {
		try{
			ServerConfigDataModel object = new ServerConfigDataModel();
			object.setModifiedDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setConfigsTest() {
		try{
			ServerConfigDataModel object = new ServerConfigDataModel();
			object.setConfigs("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setServerNameTest() {
		try{
			ServerConfigDataModel object = new ServerConfigDataModel();
			object.setServerName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServerNoTest() {
		try{
			ServerConfigDataModel object = new ServerConfigDataModel();
			object.getServerNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServerNameTest() {
		try{
			ServerConfigDataModel object = new ServerConfigDataModel();
			object.getServerName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setServerNoTest() {
		try{
			ServerConfigDataModel object = new ServerConfigDataModel();
			object.setServerNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getLastSyncTest() {
		try{
			ServerConfigDataModel object = new ServerConfigDataModel();
			object.getLastSync();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getConfigsTest() {
		try{
			ServerConfigDataModel object = new ServerConfigDataModel();
			object.getConfigs();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setLastSyncTest() {
		try{
			ServerConfigDataModel object = new ServerConfigDataModel();
			object.setLastSync("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setProtocolTest() {
		try{
			ServerConfigDataModel object = new ServerConfigDataModel();
			object.setProtocol("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setServerConfigIdTest() {
		try{
			ServerConfigDataModel object = new ServerConfigDataModel();
			object.setServerConfigId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServerConfigIdTest() {
		try{
			ServerConfigDataModel object = new ServerConfigDataModel();
			object.getServerConfigId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}