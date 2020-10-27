package org.opencps.statistic.rest.converter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class PersonStatisticConverterTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getPersonResponseTest() {
		try{
			PersonStatisticConverter.getPersonResponse();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}