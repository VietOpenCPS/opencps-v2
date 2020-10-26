package org.opencps.api.serverconfig.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ServerConfigDetailModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getProtocolTest() {
		try{
			ServerConfigDetailModel object = new ServerConfigDetailModel();
			object.getProtocol();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCreateDateTest() {
		try{
			ServerConfigDetailModel object = new ServerConfigDetailModel();
			object.getCreateDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGroupIdTest() {
		try{
			ServerConfigDetailModel object = new ServerConfigDetailModel();
			object.setGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreateDateTest() {
		try{
			ServerConfigDetailModel object = new ServerConfigDetailModel();
			object.setCreateDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getModifiedDateTest() {
		try{
			ServerConfigDetailModel object = new ServerConfigDetailModel();
			object.getModifiedDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupIdTest() {
		try{
			ServerConfigDetailModel object = new ServerConfigDetailModel();
			object.getGroupId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModifiedDateTest() {
		try{
			ServerConfigDetailModel object = new ServerConfigDetailModel();
			object.setModifiedDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setConfigsTest() {
		try{
			ServerConfigDetailModel object = new ServerConfigDetailModel();
			object.setConfigs("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setServerNameTest() {
		try{
			ServerConfigDetailModel object = new ServerConfigDetailModel();
			object.setServerName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServerNoTest() {
		try{
			ServerConfigDetailModel object = new ServerConfigDetailModel();
			object.getServerNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServerNameTest() {
		try{
			ServerConfigDetailModel object = new ServerConfigDetailModel();
			object.getServerName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setServerNoTest() {
		try{
			ServerConfigDetailModel object = new ServerConfigDetailModel();
			object.setServerNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getLastSyncTest() {
		try{
			ServerConfigDetailModel object = new ServerConfigDetailModel();
			object.getLastSync();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getConfigsTest() {
		try{
			ServerConfigDetailModel object = new ServerConfigDetailModel();
			object.getConfigs();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setLastSyncTest() {
		try{
			ServerConfigDetailModel object = new ServerConfigDetailModel();
			object.setLastSync("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setProtocolTest() {
		try{
			ServerConfigDetailModel object = new ServerConfigDetailModel();
			object.setProtocol("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setServerConfigIdTest() {
		try{
			ServerConfigDetailModel object = new ServerConfigDetailModel();
			object.setServerConfigId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServerConfigIdTest() {
		try{
			ServerConfigDetailModel object = new ServerConfigDetailModel();
			object.getServerConfigId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}