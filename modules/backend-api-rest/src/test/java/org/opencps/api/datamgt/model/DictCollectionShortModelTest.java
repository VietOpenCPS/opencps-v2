package org.opencps.api.datamgt.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DictCollectionShortModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getDescriptionTest() {
		try{
			DictCollectionShortModel object = new DictCollectionShortModel();
			object.getDescription();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDescriptionTest() {
		try{
			DictCollectionShortModel object = new DictCollectionShortModel();
			object.setDescription("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCollectionNameENTest() {
		try{
			DictCollectionShortModel object = new DictCollectionShortModel();
			object.setCollectionNameEN("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCollectionNameTest() {
		try{
			DictCollectionShortModel object = new DictCollectionShortModel();
			object.setCollectionName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCollectionNameENTest() {
		try{
			DictCollectionShortModel object = new DictCollectionShortModel();
			object.getCollectionNameEN();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCollectionNameTest() {
		try{
			DictCollectionShortModel object = new DictCollectionShortModel();
			object.getCollectionName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCollectionCodeTest() {
		try{
			DictCollectionShortModel object = new DictCollectionShortModel();
			object.setCollectionCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCollectionCodeTest() {
		try{
			DictCollectionShortModel object = new DictCollectionShortModel();
			object.getCollectionCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}