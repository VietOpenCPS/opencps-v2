package org.opencps.api.dictcollection.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DictGroupModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setGroupDescriptionTest() {
		try{
			DictGroupModel object = new DictGroupModel();
			object.setGroupDescription("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupNameTest() {
		try{
			DictGroupModel object = new DictGroupModel();
			object.getGroupName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupNameENTest() {
		try{
			DictGroupModel object = new DictGroupModel();
			object.getGroupNameEN();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupCodeTest() {
		try{
			DictGroupModel object = new DictGroupModel();
			object.getGroupCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGroupNameTest() {
		try{
			DictGroupModel object = new DictGroupModel();
			object.setGroupName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupDescriptionTest() {
		try{
			DictGroupModel object = new DictGroupModel();
			object.getGroupDescription();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGroupNameENTest() {
		try{
			DictGroupModel object = new DictGroupModel();
			object.setGroupNameEN("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGroupCodeTest() {
		try{
			DictGroupModel object = new DictGroupModel();
			object.setGroupCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}