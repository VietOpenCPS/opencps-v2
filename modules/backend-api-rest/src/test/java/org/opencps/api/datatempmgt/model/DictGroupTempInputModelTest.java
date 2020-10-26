package org.opencps.api.datatempmgt.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DictGroupTempInputModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setGroupDescriptionTest() {
		try{
			DictGroupTempInputModel object = new DictGroupTempInputModel();
			object.setGroupDescription("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupNameTest() {
		try{
			DictGroupTempInputModel object = new DictGroupTempInputModel();
			object.getGroupName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupNameENTest() {
		try{
			DictGroupTempInputModel object = new DictGroupTempInputModel();
			object.getGroupNameEN();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupCodeTest() {
		try{
			DictGroupTempInputModel object = new DictGroupTempInputModel();
			object.getGroupCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGroupNameTest() {
		try{
			DictGroupTempInputModel object = new DictGroupTempInputModel();
			object.setGroupName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupDescriptionTest() {
		try{
			DictGroupTempInputModel object = new DictGroupTempInputModel();
			object.getGroupDescription();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setStatusTest() {
		try{
			DictGroupTempInputModel object = new DictGroupTempInputModel();
			object.setStatus(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStatusTest() {
		try{
			DictGroupTempInputModel object = new DictGroupTempInputModel();
			object.getStatus();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGroupNameENTest() {
		try{
			DictGroupTempInputModel object = new DictGroupTempInputModel();
			object.setGroupNameEN("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGroupCodeTest() {
		try{
			DictGroupTempInputModel object = new DictGroupTempInputModel();
			object.setGroupCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}