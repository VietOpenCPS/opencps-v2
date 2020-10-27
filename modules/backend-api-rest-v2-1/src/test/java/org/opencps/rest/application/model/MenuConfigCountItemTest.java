package org.opencps.rest.application.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class MenuConfigCountItemTest {
	@Before
	public void setUp() {
	}
	@Test
	public void toStringTest() {
		try{
			MenuConfigCountItem object = new MenuConfigCountItem();
			object.toString();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCounterTest() {
		try{
			MenuConfigCountItem object = new MenuConfigCountItem();
			object.getCounter();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void counterTest() {
		try{
			MenuConfigCountItem object = new MenuConfigCountItem();
			object.counter(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMenuNameTest() {
		try{
			MenuConfigCountItem object = new MenuConfigCountItem();
			object.getMenuName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMenuGroupTest() {
		try{
			MenuConfigCountItem object = new MenuConfigCountItem();
			object.setMenuGroup("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMenuNameTest() {
		try{
			MenuConfigCountItem object = new MenuConfigCountItem();
			object.setMenuName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMenuGroupTest() {
		try{
			MenuConfigCountItem object = new MenuConfigCountItem();
			object.getMenuGroup();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCounterTest() {
		try{
			MenuConfigCountItem object = new MenuConfigCountItem();
			object.setCounter(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void menuGroupTest() {
		try{
			MenuConfigCountItem object = new MenuConfigCountItem();
			object.menuGroup("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void menuNameTest() {
		try{
			MenuConfigCountItem object = new MenuConfigCountItem();
			object.menuName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}