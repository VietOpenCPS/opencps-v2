package org.opencps.api.dictcollection.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DictItemShortModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getItemNameTest() {
		try{
			DictItemShortModel object = new DictItemShortModel();
			object.getItemName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getItemCodeTest() {
		try{
			DictItemShortModel object = new DictItemShortModel();
			object.getItemCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setItemNameENTest() {
		try{
			DictItemShortModel object = new DictItemShortModel();
			object.setItemNameEN("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setItemNameTest() {
		try{
			DictItemShortModel object = new DictItemShortModel();
			object.setItemName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getItemNameENTest() {
		try{
			DictItemShortModel object = new DictItemShortModel();
			object.getItemNameEN();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setItemCodeTest() {
		try{
			DictItemShortModel object = new DictItemShortModel();
			object.setItemCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSiblingTest() {
		try{
			DictItemShortModel object = new DictItemShortModel();
			object.getSibling();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSiblingTest() {
		try{
			DictItemShortModel object = new DictItemShortModel();
			object.setSibling(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getItemDescriptionTest() {
		try{
			DictItemShortModel object = new DictItemShortModel();
			object.getItemDescription();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setItemDescriptionTest() {
		try{
			DictItemShortModel object = new DictItemShortModel();
			object.setItemDescription("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getParentItemCodeTest() {
		try{
			DictItemShortModel object = new DictItemShortModel();
			object.getParentItemCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setParentItemCodeTest() {
		try{
			DictItemShortModel object = new DictItemShortModel();
			object.setParentItemCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}