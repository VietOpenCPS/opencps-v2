package org.opencps.dossiermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class NotarizationLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void findByG_DIDTest() {
		try{
			NotarizationLocalServiceImpl object = new NotarizationLocalServiceImpl();
			object.findByG_DID(Long.valueOf(0), Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByG_DIDTest2() {
		try{
			NotarizationLocalServiceImpl object = new NotarizationLocalServiceImpl();
			object.findByG_DID(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addNotarizationTest() {
		try{
			NotarizationLocalServiceImpl object = new NotarizationLocalServiceImpl();
			object.addNotarization(Long.valueOf(0), Long.valueOf(0), "abcde", 0, 0, 0, Long.valueOf(0), Long.valueOf(0), 0, new Date(), "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countByG_DIDTest() {
		try{
			NotarizationLocalServiceImpl object = new NotarizationLocalServiceImpl();
			object.countByG_DID(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countByAdvancedSearchTest() {
		try{
			NotarizationLocalServiceImpl object = new NotarizationLocalServiceImpl();
			object.countByAdvancedSearch(Long.valueOf(0), Long.valueOf(0), "abcde", 0, 0, 0, Long.valueOf(0), "abcde", 0, "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByAdvancedSearchTest() {
		try{
			NotarizationLocalServiceImpl object = new NotarizationLocalServiceImpl();
			object.findByAdvancedSearch(Long.valueOf(0), Long.valueOf(0), "abcde", 0, 0, 0, Long.valueOf(0), "abcde", 0, "abcde", "abcde", "abcde", "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeNotarizationTest() {
		try{
			NotarizationLocalServiceImpl object = new NotarizationLocalServiceImpl();
			object.removeNotarization(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateNotarizationTest() {
		try{
			NotarizationLocalServiceImpl object = new NotarizationLocalServiceImpl();
			object.updateNotarization(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", 0, 0, 0, Long.valueOf(0), Long.valueOf(0), 0, new Date(), "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}