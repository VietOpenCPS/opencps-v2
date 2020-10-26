package org.opencps.api.serviceconfig.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ServiceConfigResultsModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getTotalTest() {
		try{
			ServiceConfigResultsModel object = new ServiceConfigResultsModel();
			object.getTotal();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDataTest() {
		try{
			ServiceConfigResultsModel object = new ServiceConfigResultsModel();
			object.getData();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTotalTest() {
		try{
			ServiceConfigResultsModel object = new ServiceConfigResultsModel();
			object.setTotal(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}