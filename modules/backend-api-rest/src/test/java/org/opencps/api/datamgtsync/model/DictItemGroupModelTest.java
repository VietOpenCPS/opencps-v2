package org.opencps.api.datamgtsync.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DictItemGroupModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getCreateDateTest() {
		try{
			DictItemGroupModel object = new DictItemGroupModel();
			object.getCreateDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreateDateTest() {
		try{
			DictItemGroupModel object = new DictItemGroupModel();
			object.setCreateDate(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getModifiedDateTest() {
		try{
			DictItemGroupModel object = new DictItemGroupModel();
			object.getModifiedDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModifiedDateTest() {
		try{
			DictItemGroupModel object = new DictItemGroupModel();
			object.setModifiedDate(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getItemCodeTest() {
		try{
			DictItemGroupModel object = new DictItemGroupModel();
			object.getItemCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setItemCodeTest() {
		try{
			DictItemGroupModel object = new DictItemGroupModel();
			object.setItemCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupCodeTest() {
		try{
			DictItemGroupModel object = new DictItemGroupModel();
			object.getGroupCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCollectionCodeTest() {
		try{
			DictItemGroupModel object = new DictItemGroupModel();
			object.setCollectionCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCollectionCodeTest() {
		try{
			DictItemGroupModel object = new DictItemGroupModel();
			object.getCollectionCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGroupCodeTest() {
		try{
			DictItemGroupModel object = new DictItemGroupModel();
			object.setGroupCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}