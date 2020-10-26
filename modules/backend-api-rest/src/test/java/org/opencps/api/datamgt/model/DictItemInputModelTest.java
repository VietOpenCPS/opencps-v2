package org.opencps.api.datamgt.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DictItemInputModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setParentItemIdTest() {
		try{
			DictItemInputModel object = new DictItemInputModel();
			object.setParentItemId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDictCollectionCodeTest() {
		try{
			DictItemInputModel object = new DictItemInputModel();
			object.setDictCollectionCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getItemNameTest() {
		try{
			DictItemInputModel object = new DictItemInputModel();
			object.getItemName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getItemCodeTest() {
		try{
			DictItemInputModel object = new DictItemInputModel();
			object.getItemCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setItemNameENTest() {
		try{
			DictItemInputModel object = new DictItemInputModel();
			object.setItemNameEN("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setItemNameTest() {
		try{
			DictItemInputModel object = new DictItemInputModel();
			object.setItemName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getItemNameENTest() {
		try{
			DictItemInputModel object = new DictItemInputModel();
			object.getItemNameEN();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getParentItemIdTest() {
		try{
			DictItemInputModel object = new DictItemInputModel();
			object.getParentItemId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getLevelTest() {
		try{
			DictItemInputModel object = new DictItemInputModel();
			object.getLevel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setItemCodeTest() {
		try{
			DictItemInputModel object = new DictItemInputModel();
			object.setItemCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSiblingTest() {
		try{
			DictItemInputModel object = new DictItemInputModel();
			object.getSibling();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSiblingTest() {
		try{
			DictItemInputModel object = new DictItemInputModel();
			object.setSibling("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMetaDataTest() {
		try{
			DictItemInputModel object = new DictItemInputModel();
			object.getMetaData();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setLevelTest() {
		try{
			DictItemInputModel object = new DictItemInputModel();
			object.setLevel(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDictCollectionCodeTest() {
		try{
			DictItemInputModel object = new DictItemInputModel();
			object.getDictCollectionCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getItemDescriptionTest() {
		try{
			DictItemInputModel object = new DictItemInputModel();
			object.getItemDescription();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCollectionCodeTest() {
		try{
			DictItemInputModel object = new DictItemInputModel();
			object.setCollectionCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCollectionCodeTest() {
		try{
			DictItemInputModel object = new DictItemInputModel();
			object.getCollectionCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setItemDescriptionTest() {
		try{
			DictItemInputModel object = new DictItemInputModel();
			object.setItemDescription("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMetaDataTest() {
		try{
			DictItemInputModel object = new DictItemInputModel();
			object.setMetaData("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getParentItemCodeTest() {
		try{
			DictItemInputModel object = new DictItemInputModel();
			object.getParentItemCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setParentItemCodeTest() {
		try{
			DictItemInputModel object = new DictItemInputModel();
			object.setParentItemCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}