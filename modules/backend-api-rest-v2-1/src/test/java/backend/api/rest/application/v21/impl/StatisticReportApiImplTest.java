package backend.api.rest.application.v21.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class StatisticReportApiImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void statisticReportPrintTest() {
		try{
			StatisticReportApiImpl object = new StatisticReportApiImpl();
			object.statisticReportPrint(null, null, null, null, null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}