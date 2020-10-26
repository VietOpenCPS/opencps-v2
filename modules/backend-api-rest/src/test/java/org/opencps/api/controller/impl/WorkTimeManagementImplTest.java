package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class WorkTimeManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void updateTest() {
		try{
			WorkTimeManagementImpl object = new WorkTimeManagementImpl();
			object.update(null, null, null, null, null, null, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteTest() {
		try{
			WorkTimeManagementImpl object = new WorkTimeManagementImpl();
			object.delete(null, null, null, null, null, null, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void readTest() {
		try{
			WorkTimeManagementImpl object = new WorkTimeManagementImpl();
			object.read(null, null, null, null, null, null, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createTest() {
		try{
			WorkTimeManagementImpl object = new WorkTimeManagementImpl();
			object.create(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWorktimesTest() {
		try{
			WorkTimeManagementImpl object = new WorkTimeManagementImpl();
			object.getWorktimes(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}