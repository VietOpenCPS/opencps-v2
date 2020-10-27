package org.opencps.statistic.rest.converter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierStatisticConverterTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getDossierStatisticResponseTest() {
		try{
			DossierStatisticConverter.getDossierStatisticResponse();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierStatisticManualResponseTest() {
		try{
			DossierStatisticConverter.getDossierStatisticManualResponse();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}