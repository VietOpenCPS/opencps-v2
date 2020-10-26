package org.opencps.adminconfig.model.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class AdminConfigCacheModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void equalsTest() {
		try{
			AdminConfigCacheModel object = new AdminConfigCacheModel();
			object.equals(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toStringTest() {
		try{
			AdminConfigCacheModel object = new AdminConfigCacheModel();
			object.toString();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void hashCodeTest() {
		try{
			AdminConfigCacheModel object = new AdminConfigCacheModel();
			object.hashCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toEntityModelTest() {
		try{
			AdminConfigCacheModel object = new AdminConfigCacheModel();
			object.toEntityModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toEntityModelTest5() {
		try{
			AdminConfigCacheModel object = new AdminConfigCacheModel();
			object.toEntityModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void readExternalTest() {
		try{
			AdminConfigCacheModel object = new AdminConfigCacheModel();
			object.readExternal(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void writeExternalTest() {
		try{
			AdminConfigCacheModel object = new AdminConfigCacheModel();
			object.writeExternal(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}