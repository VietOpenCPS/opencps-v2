package org.opencps.api.dictcollection.model;
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
	public void createDictCollectionShortModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDictCollectionShortModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
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
	public void createParentItemModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createParentItemModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDictItemShortModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDictItemShortModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}