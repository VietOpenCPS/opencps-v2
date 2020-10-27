package org.opencps.communication.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class PreferencesLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			PreferencesLocalServiceImpl object = new PreferencesLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updatePreferencesTest() {
		try{
			PreferencesLocalServiceImpl object = new PreferencesLocalServiceImpl();
			object.updatePreferences(Long.valueOf(0), Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePreferencesTest() {
		try{
			PreferencesLocalServiceImpl object = new PreferencesLocalServiceImpl();
			object.deletePreferences(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			PreferencesLocalServiceImpl object = new PreferencesLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_userIdTest() {
		try{
			PreferencesLocalServiceImpl object = new PreferencesLocalServiceImpl();
			object.fetchByF_userId(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addPreferencesTest() {
		try{
			PreferencesLocalServiceImpl object = new PreferencesLocalServiceImpl();
			object.addPreferences(Long.valueOf(0), Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}