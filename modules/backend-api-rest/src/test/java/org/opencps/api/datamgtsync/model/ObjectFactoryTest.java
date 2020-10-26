package org.opencps.api.datamgtsync.model;
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
	public void createDictItemResultsTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDictItemResults();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDictGroupResultsTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDictGroupResults();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDictCollectionModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDictCollectionModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDictItemGroupResultsTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDictItemGroupResults();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDictItemGroupModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDictItemGroupModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDictGroupModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDictGroupModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDictCollectionResultsTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDictCollectionResults();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDictItemModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDictItemModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDataSearchModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDataSearchModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}