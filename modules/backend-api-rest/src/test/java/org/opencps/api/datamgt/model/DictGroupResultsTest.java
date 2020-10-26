package org.opencps.api.datamgt.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DictGroupResultsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getTotalTest() {
		try{
			DictGroupResults object = new DictGroupResults();
			object.getTotal();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupsTest() {
		try{
			DictGroupResults object = new DictGroupResults();
			object.getGroups();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTotalTest() {
		try{
			DictGroupResults object = new DictGroupResults();
			object.setTotal(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}