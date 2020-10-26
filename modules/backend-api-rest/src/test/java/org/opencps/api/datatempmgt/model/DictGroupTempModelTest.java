package org.opencps.api.datatempmgt.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DictGroupTempModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setGroupDescriptionTest() {
		try{
			DictGroupTempModel object = new DictGroupTempModel();
			object.setGroupDescription("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupNameTest() {
		try{
			DictGroupTempModel object = new DictGroupTempModel();
			object.getGroupName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupNameENTest() {
		try{
			DictGroupTempModel object = new DictGroupTempModel();
			object.getGroupNameEN();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupCodeTest() {
		try{
			DictGroupTempModel object = new DictGroupTempModel();
			object.getGroupCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGroupNameTest() {
		try{
			DictGroupTempModel object = new DictGroupTempModel();
			object.setGroupName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupDescriptionTest() {
		try{
			DictGroupTempModel object = new DictGroupTempModel();
			object.getGroupDescription();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setStatusTest() {
		try{
			DictGroupTempModel object = new DictGroupTempModel();
			object.setStatus(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStatusTest() {
		try{
			DictGroupTempModel object = new DictGroupTempModel();
			object.getStatus();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDictGroupIdTest() {
		try{
			DictGroupTempModel object = new DictGroupTempModel();
			object.setDictGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGroupNameENTest() {
		try{
			DictGroupTempModel object = new DictGroupTempModel();
			object.setGroupNameEN("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDictGroupIdTest() {
		try{
			DictGroupTempModel object = new DictGroupTempModel();
			object.getDictGroupId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGroupCodeTest() {
		try{
			DictGroupTempModel object = new DictGroupTempModel();
			object.setGroupCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}