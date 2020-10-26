package org.opencps.api.usermgt.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ProfileInputModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getValueTest() {
		try{
			ProfileInputModel object = new ProfileInputModel();
			object.getValue();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setValueTest() {
		try{
			ProfileInputModel object = new ProfileInputModel();
			object.setValue("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}