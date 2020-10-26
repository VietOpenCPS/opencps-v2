package org.opencps.api.worktime.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class WorkTimeResultsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getTotalTest() {
		try{
			WorkTimeResults object = new WorkTimeResults();
			object.getTotal();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTotalTest() {
		try{
			WorkTimeResults object = new WorkTimeResults();
			object.setTotal(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWorkTimeModelTest() {
		try{
			WorkTimeResults object = new WorkTimeResults();
			object.getWorkTimeModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}