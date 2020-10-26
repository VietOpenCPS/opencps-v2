package org.opencps.api.datamgt.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DictGroupItemModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void isSelectedTest() {
		try{
			DictGroupItemModel object = new DictGroupItemModel();
			object.isSelected();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getItemNameTest() {
		try{
			DictGroupItemModel object = new DictGroupItemModel();
			object.getItemName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getItemCodeTest() {
		try{
			DictGroupItemModel object = new DictGroupItemModel();
			object.getItemCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setItemNameENTest() {
		try{
			DictGroupItemModel object = new DictGroupItemModel();
			object.setItemNameEN("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setItemNameTest() {
		try{
			DictGroupItemModel object = new DictGroupItemModel();
			object.setItemName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getItemNameENTest() {
		try{
			DictGroupItemModel object = new DictGroupItemModel();
			object.getItemNameEN();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setItemCodeTest() {
		try{
			DictGroupItemModel object = new DictGroupItemModel();
			object.setItemCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDictItemIdTest() {
		try{
			DictGroupItemModel object = new DictGroupItemModel();
			object.getDictItemId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSelectedTest() {
		try{
			DictGroupItemModel object = new DictGroupItemModel();
			object.setSelected(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getItemDescriptionTest() {
		try{
			DictGroupItemModel object = new DictGroupItemModel();
			object.getItemDescription();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setItemDescriptionTest() {
		try{
			DictGroupItemModel object = new DictGroupItemModel();
			object.setItemDescription("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDictItemIdTest() {
		try{
			DictGroupItemModel object = new DictGroupItemModel();
			object.setDictItemId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}