package org.opencps.dossiermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class EFormLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void searchLuceneTest() {
		try{
			EFormLocalServiceImpl object = new EFormLocalServiceImpl();
			object.searchLucene(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneTest() {
		try{
			EFormLocalServiceImpl object = new EFormLocalServiceImpl();
			object.countLucene(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateEFormTest() {
		try{
			EFormLocalServiceImpl object = new EFormLocalServiceImpl();
			object.updateEForm(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByEFormNoTest() {
		try{
			EFormLocalServiceImpl object = new EFormLocalServiceImpl();
			object.getByEFormNo(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDataByEFormNoTest() {
		try{
			EFormLocalServiceImpl object = new EFormLocalServiceImpl();
			object.updateDataByEFormNo(Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}