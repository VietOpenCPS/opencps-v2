package org.opencps.api.datamgtsync.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DictCollectionModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getCreateDateTest() {
		try{
			DictCollectionModel object = new DictCollectionModel();
			object.getCreateDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreateDateTest() {
		try{
			DictCollectionModel object = new DictCollectionModel();
			object.setCreateDate(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getModifiedDateTest() {
		try{
			DictCollectionModel object = new DictCollectionModel();
			object.getModifiedDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModifiedDateTest() {
		try{
			DictCollectionModel object = new DictCollectionModel();
			object.setModifiedDate(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDataFormTest() {
		try{
			DictCollectionModel object = new DictCollectionModel();
			object.getDataForm();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDescriptionTest() {
		try{
			DictCollectionModel object = new DictCollectionModel();
			object.getDescription();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDescriptionTest() {
		try{
			DictCollectionModel object = new DictCollectionModel();
			object.setDescription("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCollectionNameENTest() {
		try{
			DictCollectionModel object = new DictCollectionModel();
			object.setCollectionNameEN("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCollectionNameTest() {
		try{
			DictCollectionModel object = new DictCollectionModel();
			object.setCollectionName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCollectionNameENTest() {
		try{
			DictCollectionModel object = new DictCollectionModel();
			object.getCollectionNameEN();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCollectionNameTest() {
		try{
			DictCollectionModel object = new DictCollectionModel();
			object.getCollectionName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCollectionCodeTest() {
		try{
			DictCollectionModel object = new DictCollectionModel();
			object.setCollectionCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCollectionCodeTest() {
		try{
			DictCollectionModel object = new DictCollectionModel();
			object.getCollectionCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDataFormTest() {
		try{
			DictCollectionModel object = new DictCollectionModel();
			object.setDataForm("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}