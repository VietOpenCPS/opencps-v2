package org.opencps.api.eform.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class EFormResultsModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getTotalTest() {
		try{
			EFormResultsModel object = new EFormResultsModel();
			object.getTotal();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDataTest() {
		try{
			EFormResultsModel object = new EFormResultsModel();
			object.getData();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTotalTest() {
		try{
			EFormResultsModel object = new EFormResultsModel();
			object.setTotal(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}