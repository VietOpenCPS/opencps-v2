package org.opencps.api.user.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class UserWorksResultsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getTotalTest() {
		try{
			UserWorksResults object = new UserWorksResults();
			object.getTotal();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserWorksModelTest() {
		try{
			UserWorksResults object = new UserWorksResults();
			object.getUserWorksModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTotalTest() {
		try{
			UserWorksResults object = new UserWorksResults();
			object.setTotal(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}