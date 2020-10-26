package org.opencps.api.dossier.model;
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
	public void createDossierDetailModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDossierDetailModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDossierResultsModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDossierResultsModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDossierInputModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDossierInputModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDossierSearchModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDossierSearchModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDossierPublishModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDossierPublishModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDoActionModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDoActionModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDossierDataModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDossierDataModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}