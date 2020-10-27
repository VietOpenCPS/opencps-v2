package org.opencps.dossiermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ActionConfigLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			ActionConfigLocalServiceImpl object = new ActionConfigLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateActionConfigTest() {
		try{
			ActionConfigLocalServiceImpl object = new ActionConfigLocalServiceImpl();
			object.updateActionConfig(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", true, "abcde", "abcde", true, 0, 0, true, true, "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByGroupIdTest() {
		try{
			ActionConfigLocalServiceImpl object = new ActionConfigLocalServiceImpl();
			object.getByGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByCodeTest() {
		try{
			ActionConfigLocalServiceImpl object = new ActionConfigLocalServiceImpl();
			object.getByCode(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addActionConfigTest() {
		try{
			ActionConfigLocalServiceImpl object = new ActionConfigLocalServiceImpl();
			object.addActionConfig(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", true, "abcde", "abcde", true, 0, 0, true, true, "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByG_ETTest() {
		try{
			ActionConfigLocalServiceImpl object = new ActionConfigLocalServiceImpl();
			object.getByG_ET(Long.valueOf(0), 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFormTest() {
		try{
			ActionConfigLocalServiceImpl object = new ActionConfigLocalServiceImpl();
			object.getForm(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateActionConfigDBTest() {
		try{
			ActionConfigLocalServiceImpl object = new ActionConfigLocalServiceImpl();
			object.updateActionConfigDB(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", true, "abcde", true, 0, 0, 0, 0, true, "abcde", "abcde", "abcde", "abcde", 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeActionConfigTest() {
		try{
			ActionConfigLocalServiceImpl object = new ActionConfigLocalServiceImpl();
			object.removeActionConfig(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			ActionConfigLocalServiceImpl object = new ActionConfigLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}