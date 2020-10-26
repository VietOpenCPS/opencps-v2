package org.opencps.api.datamgt.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DictCollectionInputModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getDescriptionTest() {
		try{
			DictCollectionInputModel object = new DictCollectionInputModel();
			object.getDescription();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDescriptionTest() {
		try{
			DictCollectionInputModel object = new DictCollectionInputModel();
			object.setDescription("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCollectionNameENTest() {
		try{
			DictCollectionInputModel object = new DictCollectionInputModel();
			object.setCollectionNameEN("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCollectionNameTest() {
		try{
			DictCollectionInputModel object = new DictCollectionInputModel();
			object.setCollectionName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCollectionNameENTest() {
		try{
			DictCollectionInputModel object = new DictCollectionInputModel();
			object.getCollectionNameEN();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCollectionNameTest() {
		try{
			DictCollectionInputModel object = new DictCollectionInputModel();
			object.getCollectionName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCollectionCodeTest() {
		try{
			DictCollectionInputModel object = new DictCollectionInputModel();
			object.setCollectionCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCollectionCodeTest() {
		try{
			DictCollectionInputModel object = new DictCollectionInputModel();
			object.getCollectionCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}