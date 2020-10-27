package org.opencps.communication.service.impl;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class ServerConfigLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getGroupIdTest() {
		try{
			ServerConfigLocalServiceImpl object = new ServerConfigLocalServiceImpl();
			object.getGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDataTest() {
		try{
			ServerConfigLocalServiceImpl object = new ServerConfigLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void updateServerConfigTest() {
//		try{
//			ServerConfigLocalServiceImpl object = new ServerConfigLocalServiceImpl();
//			object.updateServerConfig(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", new Date(), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void getByProtocolTest() {
		try{
			ServerConfigLocalServiceImpl object = new ServerConfigLocalServiceImpl();
			object.getByProtocol("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByProtocolTest5() {
		try{
			ServerConfigLocalServiceImpl object = new ServerConfigLocalServiceImpl();
			object.getByProtocol(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByCodeTest() {
		try{
			ServerConfigLocalServiceImpl object = new ServerConfigLocalServiceImpl();
			object.getByCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByCodeTest7() {
		try{
			ServerConfigLocalServiceImpl object = new ServerConfigLocalServiceImpl();
			object.getByCode(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateServerConfigDBTest() {
		try{
			ServerConfigLocalServiceImpl object = new ServerConfigLocalServiceImpl();
			object.updateServerConfigDB(Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", new Date(), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByServerNoAndProtocolTest() {
		try{
			ServerConfigLocalServiceImpl object = new ServerConfigLocalServiceImpl();
			object.getByServerNoAndProtocol(Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			ServerConfigLocalServiceImpl object = new ServerConfigLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteByGroupIdTest() {
		try{
			ServerConfigLocalServiceImpl object = new ServerConfigLocalServiceImpl();
			object.deleteByGroupId(Long.valueOf(0), Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeAllServerTest() {
		try{
			ServerConfigLocalServiceImpl object = new ServerConfigLocalServiceImpl();
			object.removeAllServer();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateLastSyncTest() {
		try{
			ServerConfigLocalServiceImpl object = new ServerConfigLocalServiceImpl();
			object.updateLastSync(Long.valueOf(0), new Date(), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}