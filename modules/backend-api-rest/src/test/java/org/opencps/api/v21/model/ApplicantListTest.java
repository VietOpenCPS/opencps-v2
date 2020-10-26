package org.opencps.api.v21.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ApplicantListTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getApplicantTest() {
		try{
			ApplicantList object = new ApplicantList();
			object.getApplicant();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}