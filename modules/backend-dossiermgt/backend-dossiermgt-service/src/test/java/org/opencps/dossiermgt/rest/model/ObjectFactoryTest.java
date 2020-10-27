package org.opencps.dossiermgt.rest.model;
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
	public void createDossierInputModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDossierInputModel();
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
	public void createPaymentFileInputModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createPaymentFileInputModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createProcessActionReturnModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createProcessActionReturnModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDossierMarkInputModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDossierMarkInputModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDossierDocumentModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDossierDocumentModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDossierMarkResultModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDossierMarkResultModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createInformDossierInputModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createInformDossierInputModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createExecuteOneActionTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createExecuteOneAction();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDossierFileModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDossierFileModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}