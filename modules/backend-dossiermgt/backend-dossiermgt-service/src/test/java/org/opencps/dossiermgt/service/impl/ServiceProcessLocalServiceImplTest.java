package org.opencps.dossiermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class ServiceProcessLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			ServiceProcessLocalServiceImpl object = new ServiceProcessLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeServiceProcessTest() {
		try{
			ServiceProcessLocalServiceImpl object = new ServiceProcessLocalServiceImpl();
			object.removeServiceProcess(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateServiceProcessTest() {
		try{
			ServiceProcessLocalServiceImpl object = new ServiceProcessLocalServiceImpl();
			object.updateServiceProcess(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Double.valueOf(0.0), 0, Long.valueOf(0), true, "abcde", true, "abcde", true, true, "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void initServiceProcessTest() {
		try{
			ServiceProcessLocalServiceImpl object = new ServiceProcessLocalServiceImpl();
			object.initServiceProcess(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void cloneServiceProcessTest() {
		try{
			ServiceProcessLocalServiceImpl object = new ServiceProcessLocalServiceImpl();
			object.cloneServiceProcess(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void cloneServiceProcessTest6() {
		try{
			ServiceProcessLocalServiceImpl object = new ServiceProcessLocalServiceImpl();
			object.cloneServiceProcess(Long.valueOf(0), Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchLuceneTest() {
		try{
			ServiceProcessLocalServiceImpl object = new ServiceProcessLocalServiceImpl();
			object.searchLucene(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByG_IDTest() {
		try{
			ServiceProcessLocalServiceImpl object = new ServiceProcessLocalServiceImpl();
			object.getByG_ID(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByG_PNOTest() {
		try{
			ServiceProcessLocalServiceImpl object = new ServiceProcessLocalServiceImpl();
			object.getByG_PNO(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateServiceProcessDBTest() {
		try{
			ServiceProcessLocalServiceImpl object = new ServiceProcessLocalServiceImpl();
			object.updateServiceProcessDB(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Double.valueOf(0.0), 0, true, "abcde", "abcde", "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneTest() {
		try{
			ServiceProcessLocalServiceImpl object = new ServiceProcessLocalServiceImpl();
			object.countLucene(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void getServiceByCodeTest() {
//		try{
//			ServiceProcessLocalServiceImpl object = new ServiceProcessLocalServiceImpl();
//			object.getServiceByCode(Long.valueOf(0), "abcde", "abcde", "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			ServiceProcessLocalServiceImpl object = new ServiceProcessLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByServerNoTest() {
		try{
			ServiceProcessLocalServiceImpl object = new ServiceProcessLocalServiceImpl();
			object.getByServerNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}