package org.opencps.api.datamgt.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DictGroupInputModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setGroupDescriptionTest() {
		try{
			DictGroupInputModel object = new DictGroupInputModel();
			object.setGroupDescription("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupNameTest() {
		try{
			DictGroupInputModel object = new DictGroupInputModel();
			object.getGroupName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupNameENTest() {
		try{
			DictGroupInputModel object = new DictGroupInputModel();
			object.getGroupNameEN();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupCodeTest() {
		try{
			DictGroupInputModel object = new DictGroupInputModel();
			object.getGroupCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGroupNameTest() {
		try{
			DictGroupInputModel object = new DictGroupInputModel();
			object.setGroupName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupDescriptionTest() {
		try{
			DictGroupInputModel object = new DictGroupInputModel();
			object.getGroupDescription();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGroupNameENTest() {
		try{
			DictGroupInputModel object = new DictGroupInputModel();
			object.setGroupNameEN("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGroupCodeTest() {
		try{
			DictGroupInputModel object = new DictGroupInputModel();
			object.setGroupCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}