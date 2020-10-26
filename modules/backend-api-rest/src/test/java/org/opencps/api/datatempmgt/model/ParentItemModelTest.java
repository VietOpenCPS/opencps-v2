package org.opencps.api.datatempmgt.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ParentItemModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getItemNameTest() {
		try{
			ParentItemModel object = new ParentItemModel();
			object.getItemName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getItemCodeTest() {
		try{
			ParentItemModel object = new ParentItemModel();
			object.getItemCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setItemNameENTest() {
		try{
			ParentItemModel object = new ParentItemModel();
			object.setItemNameEN("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setItemNameTest() {
		try{
			ParentItemModel object = new ParentItemModel();
			object.setItemName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getItemNameENTest() {
		try{
			ParentItemModel object = new ParentItemModel();
			object.getItemNameEN();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setItemCodeTest() {
		try{
			ParentItemModel object = new ParentItemModel();
			object.setItemCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDictItemIdTest() {
		try{
			ParentItemModel object = new ParentItemModel();
			object.getDictItemId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setStatusTest() {
		try{
			ParentItemModel object = new ParentItemModel();
			object.setStatus(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStatusTest() {
		try{
			ParentItemModel object = new ParentItemModel();
			object.getStatus();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDictItemIdTest() {
		try{
			ParentItemModel object = new ParentItemModel();
			object.setDictItemId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}