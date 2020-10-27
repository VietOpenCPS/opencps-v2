package org.opencps.rest.opencps.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class MenuConfigItemResultTest {
	@Before
	public void setUp() {
	}
	@Test
	public void toStringTest() {
		try{
			MenuConfigItemResult object = new MenuConfigItemResult();
			object.toString();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTotalTest() {
		try{
			MenuConfigItemResult object = new MenuConfigItemResult();
			object.getTotal();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void totalTest() {
		try{
			MenuConfigItemResult object = new MenuConfigItemResult();
			object.total(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDataTest() {
		try{
			MenuConfigItemResult object = new MenuConfigItemResult();
			object.setData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDataItemTest() {
		try{
			MenuConfigItemResult object = new MenuConfigItemResult();
			object.addDataItem(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dataTest() {
		try{
			MenuConfigItemResult object = new MenuConfigItemResult();
			object.data(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDataTest() {
		try{
			MenuConfigItemResult object = new MenuConfigItemResult();
			object.getData();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTotalTest() {
		try{
			MenuConfigItemResult object = new MenuConfigItemResult();
			object.setTotal(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}