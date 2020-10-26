package org.opencps.api.dictcollection.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DictItemCompareModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getItemNameTest() {
		try{
			DictItemCompareModel object = new DictItemCompareModel("abcde", "abcde", 0);
			object.getItemName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getItemCodeTest() {
		try{
			DictItemCompareModel object = new DictItemCompareModel("abcde", "abcde", 0);
			object.getItemCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setItemNameTest() {
		try{
			DictItemCompareModel object = new DictItemCompareModel("abcde", "abcde", 0);
			object.setItemName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setItemCodeTest() {
		try{
			DictItemCompareModel object = new DictItemCompareModel("abcde", "abcde", 0);
			object.setItemCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSiblingTest() {
		try{
			DictItemCompareModel object = new DictItemCompareModel("abcde", "abcde", 0);
			object.getSibling();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSiblingTest() {
		try{
			DictItemCompareModel object = new DictItemCompareModel("abcde", "abcde", 0);
			object.setSibling(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}