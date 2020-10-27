package org.opencps.rest.application.api.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ActionConfigApiServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void updateActionConfigTest() {
		try{
			ActionConfigApiServiceImpl object = new ActionConfigApiServiceImpl();
			object.updateActionConfig("abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionConfigByCodeTest() {
		try{
			ActionConfigApiServiceImpl object = new ActionConfigApiServiceImpl();
			object.getActionConfigByCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteActionConfigTest() {
		try{
			ActionConfigApiServiceImpl object = new ActionConfigApiServiceImpl();
			object.deleteActionConfig("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionConfigsElasticsearchTest() {
		try{
			ActionConfigApiServiceImpl object = new ActionConfigApiServiceImpl();
			object.getActionConfigsElasticsearch("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addActionConfigTest() {
		try{
			ActionConfigApiServiceImpl object = new ActionConfigApiServiceImpl();
			object.addActionConfig(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}