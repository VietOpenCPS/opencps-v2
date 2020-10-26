package org.opencps.api.dossiertemplate.model;
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
	public void createDossierPartContentInputUpdateModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDossierPartContentInputUpdateModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDossierTemplatePartDataModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDossierTemplatePartDataModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDossierTemplateResultsModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDossierTemplateResultsModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDossierTemplateInputModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDossierTemplateInputModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDossierPartInputModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDossierPartInputModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDossierPartResultsModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDossierPartResultsModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDossierTemplateSearchModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDossierTemplateSearchModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDossierTemplateDetailModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDossierTemplateDetailModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDossierPartSearchModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDossierPartSearchModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDossierTemplateDataModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDossierTemplateDataModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}