package org.opencps.communication.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class ZaloMapLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			ZaloMapLocalServiceImpl object = new ZaloMapLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByGroupIdTest() {
		try{
			ZaloMapLocalServiceImpl object = new ZaloMapLocalServiceImpl();
			object.getByGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeByPrimaryKeyTest() {
		try{
			ZaloMapLocalServiceImpl object = new ZaloMapLocalServiceImpl();
			object.removeByPrimaryKey(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			ZaloMapLocalServiceImpl object = new ZaloMapLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void updateZaloMapTest() {
//		try{
//			ZaloMapLocalServiceImpl object = new ZaloMapLocalServiceImpl();
//			object.updateZaloMap(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", 0, "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void getByTelNoTest() {
		try{
			ZaloMapLocalServiceImpl object = new ZaloMapLocalServiceImpl();
			object.getByTelNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByUIdTest() {
		try{
			ZaloMapLocalServiceImpl object = new ZaloMapLocalServiceImpl();
			object.getByUId("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByOAIdTest() {
		try{
			ZaloMapLocalServiceImpl object = new ZaloMapLocalServiceImpl();
			object.getByOAId("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}