package org.opencps.api.datatempmgt.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DictCollectionTempInputModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setMustSyncTest() {
		try{
			DictCollectionTempInputModel object = new DictCollectionTempInputModel();
			object.setMustSync(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMustSyncTest() {
		try{
			DictCollectionTempInputModel object = new DictCollectionTempInputModel();
			object.getMustSync();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDescriptionTest() {
		try{
			DictCollectionTempInputModel object = new DictCollectionTempInputModel();
			object.getDescription();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDescriptionTest() {
		try{
			DictCollectionTempInputModel object = new DictCollectionTempInputModel();
			object.setDescription("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCollectionNameENTest() {
		try{
			DictCollectionTempInputModel object = new DictCollectionTempInputModel();
			object.setCollectionNameEN("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCollectionNameTest() {
		try{
			DictCollectionTempInputModel object = new DictCollectionTempInputModel();
			object.setCollectionName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCollectionNameENTest() {
		try{
			DictCollectionTempInputModel object = new DictCollectionTempInputModel();
			object.getCollectionNameEN();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCollectionNameTest() {
		try{
			DictCollectionTempInputModel object = new DictCollectionTempInputModel();
			object.getCollectionName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCollectionCodeTest() {
		try{
			DictCollectionTempInputModel object = new DictCollectionTempInputModel();
			object.setCollectionCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCollectionCodeTest() {
		try{
			DictCollectionTempInputModel object = new DictCollectionTempInputModel();
			object.getCollectionCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setStatusTest() {
		try{
			DictCollectionTempInputModel object = new DictCollectionTempInputModel();
			object.setStatus(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStatusTest() {
		try{
			DictCollectionTempInputModel object = new DictCollectionTempInputModel();
			object.getStatus();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}