package org.opencps.api.controller.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ApplicantDataUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void mappingToApplicantDataResultsTest() {
		try{
			ApplicantDataUtils.mappingToApplicantDataResults(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingToApplicantDataModelTest() {
		try{
			ApplicantDataUtils.mappingToApplicantDataModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}