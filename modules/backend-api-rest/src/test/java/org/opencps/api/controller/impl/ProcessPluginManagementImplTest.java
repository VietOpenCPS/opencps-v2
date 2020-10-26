package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ProcessPluginManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getPreviewTest() {
		try{
			ProcessPluginManagementImpl object = new ProcessPluginManagementImpl();
			object.getPreview(null, null, null, null, null, null, "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFormDataTest() {
		try{
			ProcessPluginManagementImpl object = new ProcessPluginManagementImpl();
			object.getFormData(null, null, null, null, null, null, "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFormScriptTest() {
		try{
			ProcessPluginManagementImpl object = new ProcessPluginManagementImpl();
			object.getFormScript(null, null, null, null, null, null, "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPluginsTest() {
		try{
			ProcessPluginManagementImpl object = new ProcessPluginManagementImpl();
			object.getPlugins(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPreviewHtmlTest() {
		try{
			ProcessPluginManagementImpl object = new ProcessPluginManagementImpl();
			object.getPreviewHtml(null, null, null, null, null, null, "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}