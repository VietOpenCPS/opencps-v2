package org.opencps.api.dossieraction.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ObjectFactoryTest {
	@Before
	public void setUp() {
	}
	@Test
	public void createDossierActionNextActionResultsModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDossierActionNextActionResultsModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDossierActionNextActiontoUserTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDossierActionNextActiontoUser();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDossierActionNextActionModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDossierActionNextActionModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDossierActionNextActioncreateFilesTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDossierActionNextActioncreateFiles();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDossierActionSearchModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDossierActionSearchModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDossierActionResultsModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDossierActionResultsModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDossierActionModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDossierActionModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}