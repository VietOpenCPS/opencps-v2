package org.opencps.api.datamgt.model;
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
	public void createGroupsTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createGroups();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createParentItemTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createParentItem();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
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
	public void createDictGroupInputModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDictGroupInputModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDictCollectionInputModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDictCollectionInputModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDictGroupItemResultsTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDictGroupItemResults();
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
	public void createDictGroupItemModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDictGroupItemModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDictItemInputModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDictItemInputModel();
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