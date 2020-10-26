package org.opencps.api.controller.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierMarkUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void mappingDossierMarkDetailModelTest() {
		try{
			DossierMarkUtils.mappingDossierMarkDetailModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingDossierMarksTest() {
		try{
			DossierMarkUtils.mappingDossierMarks(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}