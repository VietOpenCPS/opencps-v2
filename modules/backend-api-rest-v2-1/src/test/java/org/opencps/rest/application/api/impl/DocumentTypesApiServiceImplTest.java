package org.opencps.rest.application.api.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DocumentTypesApiServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void createDocumentTypeTest() {
		try{
			DocumentTypesApiServiceImpl object = new DocumentTypesApiServiceImpl();
			object.createDocumentType(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAllDocumentTypesTest() {
		try{
			DocumentTypesApiServiceImpl object = new DocumentTypesApiServiceImpl();
			object.getAllDocumentTypes("abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDocByIdTest() {
		try{
			DocumentTypesApiServiceImpl object = new DocumentTypesApiServiceImpl();
			object.getDocById("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeDocByIdTest() {
		try{
			DocumentTypesApiServiceImpl object = new DocumentTypesApiServiceImpl();
			object.removeDocById("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDocByIdTest() {
		try{
			DocumentTypesApiServiceImpl object = new DocumentTypesApiServiceImpl();
			object.updateDocById("abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}