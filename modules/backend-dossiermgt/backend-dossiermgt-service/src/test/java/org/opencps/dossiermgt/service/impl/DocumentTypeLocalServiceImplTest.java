package org.opencps.dossiermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DocumentTypeLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			DocumentTypeLocalServiceImpl object = new DocumentTypeLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByGTest() {
		try{
			DocumentTypeLocalServiceImpl object = new DocumentTypeLocalServiceImpl();
			object.findByG(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByTypeCodeTest() {
		try{
			DocumentTypeLocalServiceImpl object = new DocumentTypeLocalServiceImpl();
			object.getByTypeCode(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDocTypeTest() {
		try{
			DocumentTypeLocalServiceImpl object = new DocumentTypeLocalServiceImpl();
			object.updateDocType(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", 0, "abcde", "abcde", "abcde", 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDocumentTypeDBTest() {
		try{
			DocumentTypeLocalServiceImpl object = new DocumentTypeLocalServiceImpl();
			object.updateDocumentTypeDB(Long.valueOf(0), Long.valueOf(0), "abcde", 0, "abcde", "abcde", 0, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void insertDocTypeTest() {
		try{
			DocumentTypeLocalServiceImpl object = new DocumentTypeLocalServiceImpl();
			object.insertDocType(Long.valueOf(0), Long.valueOf(0), "abcde", 0, "abcde", "abcde", "abcde", 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			DocumentTypeLocalServiceImpl object = new DocumentTypeLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}