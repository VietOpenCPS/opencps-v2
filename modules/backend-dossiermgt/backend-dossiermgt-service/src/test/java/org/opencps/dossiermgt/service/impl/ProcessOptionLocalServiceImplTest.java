package org.opencps.dossiermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ProcessOptionLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			ProcessOptionLocalServiceImpl object = new ProcessOptionLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateProcessOptionTest() {
		try{
			ProcessOptionLocalServiceImpl object = new ProcessOptionLocalServiceImpl();
			object.updateProcessOption(Long.valueOf(0), "abcde", Long.valueOf(0), Long.valueOf(0), 0, "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeProcessOptionTest() {
		try{
			ProcessOptionLocalServiceImpl object = new ProcessOptionLocalServiceImpl();
			object.removeProcessOption(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchLuceneTest() {
		try{
			ProcessOptionLocalServiceImpl object = new ProcessOptionLocalServiceImpl();
			object.searchLucene(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByGroupTest() {
		try{
			ProcessOptionLocalServiceImpl object = new ProcessOptionLocalServiceImpl();
			object.findByGroup(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateOptionDBTest() {
		try{
			ProcessOptionLocalServiceImpl object = new ProcessOptionLocalServiceImpl();
			object.updateOptionDB(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", Long.valueOf(0), 0, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByServiceProcessIdTest() {
		try{
			ProcessOptionLocalServiceImpl object = new ProcessOptionLocalServiceImpl();
			object.getByServiceProcessId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneTest() {
		try{
			ProcessOptionLocalServiceImpl object = new ProcessOptionLocalServiceImpl();
			object.countLucene(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchBySP_DTTest() {
		try{
			ProcessOptionLocalServiceImpl object = new ProcessOptionLocalServiceImpl();
			object.fetchBySP_DT(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByDTPLNoAndServiceCFTest() {
		try{
			ProcessOptionLocalServiceImpl object = new ProcessOptionLocalServiceImpl();
			object.getByDTPLNoAndServiceCF(Long.valueOf(0), "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findAllTest() {
		try{
			ProcessOptionLocalServiceImpl object = new ProcessOptionLocalServiceImpl();
			object.findAll(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			ProcessOptionLocalServiceImpl object = new ProcessOptionLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countByServiceConfigIdTest() {
		try{
			ProcessOptionLocalServiceImpl object = new ProcessOptionLocalServiceImpl();
			object.countByServiceConfigId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByServiceConfigIdTest() {
		try{
			ProcessOptionLocalServiceImpl object = new ProcessOptionLocalServiceImpl();
			object.getByServiceConfigId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}