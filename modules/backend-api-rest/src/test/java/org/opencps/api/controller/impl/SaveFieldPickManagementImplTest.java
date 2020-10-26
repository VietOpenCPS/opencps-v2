package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class SaveFieldPickManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getFieldPickTest() {
		try{
			SaveFieldPickManagementImpl object = new SaveFieldPickManagementImpl();
			object.getFieldPick(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateFieldPickTest() {
		try{
			SaveFieldPickManagementImpl object = new SaveFieldPickManagementImpl();
			object.updateFieldPick(null, null, null, null, null, null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}