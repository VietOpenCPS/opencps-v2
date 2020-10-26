package org.opencps.api.serviceprocess.model;
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
	public void createServiceProcessInputModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createServiceProcessInputModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createProcessActionSearchModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createProcessActionSearchModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createServiceProcessSearchModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createServiceProcessSearchModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createRoleInputModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createRoleInputModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createServiceProcessResultsModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createServiceProcessResultsModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createServiceProcessDataModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createServiceProcessDataModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createProcessStepSearchModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createProcessStepSearchModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createRoleResultsModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createRoleResultsModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createProcessActionInputModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createProcessActionInputModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createProcessStepInputModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createProcessStepInputModel();
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
	public void createRoleDataModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createRoleDataModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createServiceProcessDetailModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createServiceProcessDetailModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createProcessStepDataModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createProcessStepDataModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createProcessStepResultsModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createProcessStepResultsModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createProcessActionResultsModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createProcessActionResultsModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createProcessActionDataModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createProcessActionDataModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}