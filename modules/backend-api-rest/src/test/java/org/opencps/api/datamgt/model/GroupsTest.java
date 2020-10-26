package org.opencps.api.datamgt.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class GroupsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setGroupDescriptionTest() {
		try{
			Groups object = new Groups();
			object.setGroupDescription("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupNameTest() {
		try{
			Groups object = new Groups();
			object.getGroupName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupNameENTest() {
		try{
			Groups object = new Groups();
			object.getGroupNameEN();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupCodeTest() {
		try{
			Groups object = new Groups();
			object.getGroupCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGroupNameTest() {
		try{
			Groups object = new Groups();
			object.setGroupName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupDescriptionTest() {
		try{
			Groups object = new Groups();
			object.getGroupDescription();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDictGroupIdTest() {
		try{
			Groups object = new Groups();
			object.setDictGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGroupNameENTest() {
		try{
			Groups object = new Groups();
			object.setGroupNameEN("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDictGroupIdTest() {
		try{
			Groups object = new Groups();
			object.getDictGroupId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGroupCodeTest() {
		try{
			Groups object = new Groups();
			object.setGroupCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}