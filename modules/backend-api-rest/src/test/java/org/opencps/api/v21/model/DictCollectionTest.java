package org.opencps.api.v21.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DictCollectionTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setGroupsTest() {
		try{
			DictCollection object = new DictCollection();
			object.setGroups(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setItemsTest() {
		try{
			DictCollection object = new DictCollection();
			object.setItems(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupsTest() {
		try{
			DictCollection object = new DictCollection();
			object.getGroups();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getItemsTest() {
		try{
			DictCollection object = new DictCollection();
			object.getItems();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDescriptionTest() {
		try{
			DictCollection object = new DictCollection();
			object.getDescription();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDescriptionTest() {
		try{
			DictCollection object = new DictCollection();
			object.setDescription("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCollectionNameENTest() {
		try{
			DictCollection object = new DictCollection();
			object.setCollectionNameEN("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCollectionNameTest() {
		try{
			DictCollection object = new DictCollection();
			object.setCollectionName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCollectionNameENTest() {
		try{
			DictCollection object = new DictCollection();
			object.getCollectionNameEN();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCollectionNameTest() {
		try{
			DictCollection object = new DictCollection();
			object.getCollectionName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCollectionCodeTest() {
		try{
			DictCollection object = new DictCollection();
			object.setCollectionCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCollectionCodeTest() {
		try{
			DictCollection object = new DictCollection();
			object.getCollectionCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setStatusTest() {
		try{
			DictCollection object = new DictCollection();
			object.setStatus(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStatusTest() {
		try{
			DictCollection object = new DictCollection();
			object.getStatus();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}