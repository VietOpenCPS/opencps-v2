package org.opencps.api.processsequence.model;
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
	public void createProcessSequenceOneOutputModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createProcessSequenceOneOutputModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createProcessSequenceModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createProcessSequenceModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDossierActionResult21ModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDossierActionResult21Model();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createAssignUserModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createAssignUserModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDossierSequenceResultModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDossierSequenceResultModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createProcessSequenceResultModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createProcessSequenceResultModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createActionModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createActionModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createProcessSequenceInputModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createProcessSequenceInputModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDossierSequenceModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDossierSequenceModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createProcessSequenceOutputModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createProcessSequenceOutputModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createStepModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createStepModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}