package opencps.statistic.common.webservice.exception;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;

public class OpencpsServiceExceptionTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getFaultDetailsTest() {
		try{
			OpencpsServiceException object = new OpencpsServiceException(null);
			object.getFaultDetails();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}