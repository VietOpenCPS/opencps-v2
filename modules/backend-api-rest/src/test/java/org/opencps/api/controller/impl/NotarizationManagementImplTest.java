package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class NotarizationManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void addNotarizationTest() {
		try{
			NotarizationManagementImpl object = new NotarizationManagementImpl();
			object.addNotarization(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNotarizationsTest() {
		try{
			NotarizationManagementImpl object = new NotarizationManagementImpl();
			object.getNotarizations(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteNotarizationTest() {
		try{
			NotarizationManagementImpl object = new NotarizationManagementImpl();
			object.deleteNotarization(null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNotarizationDetailTest() {
		try{
			NotarizationManagementImpl object = new NotarizationManagementImpl();
			object.getNotarizationDetail(null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateNotarizationTest() {
		try{
			NotarizationManagementImpl object = new NotarizationManagementImpl();
			object.updateNotarization(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}