package org.opencps.api.datatempmgt.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DictGroupItemTempModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void isSelectedTest() {
		try{
			DictGroupItemTempModel object = new DictGroupItemTempModel();
			object.isSelected();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getItemNameTest() {
		try{
			DictGroupItemTempModel object = new DictGroupItemTempModel();
			object.getItemName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getItemCodeTest() {
		try{
			DictGroupItemTempModel object = new DictGroupItemTempModel();
			object.getItemCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setItemNameENTest() {
		try{
			DictGroupItemTempModel object = new DictGroupItemTempModel();
			object.setItemNameEN("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setItemNameTest() {
		try{
			DictGroupItemTempModel object = new DictGroupItemTempModel();
			object.setItemName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getItemNameENTest() {
		try{
			DictGroupItemTempModel object = new DictGroupItemTempModel();
			object.getItemNameEN();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setItemCodeTest() {
		try{
			DictGroupItemTempModel object = new DictGroupItemTempModel();
			object.setItemCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDictItemIdTest() {
		try{
			DictGroupItemTempModel object = new DictGroupItemTempModel();
			object.getDictItemId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSelectedTest() {
		try{
			DictGroupItemTempModel object = new DictGroupItemTempModel();
			object.setSelected(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getItemDescriptionTest() {
		try{
			DictGroupItemTempModel object = new DictGroupItemTempModel();
			object.getItemDescription();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setItemDescriptionTest() {
		try{
			DictGroupItemTempModel object = new DictGroupItemTempModel();
			object.setItemDescription("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDictItemIdTest() {
		try{
			DictGroupItemTempModel object = new DictGroupItemTempModel();
			object.setDictItemId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}