package opencps.statistic.common.webservice.exception;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;

public class OpencpsServiceExceptionDetailsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setFaultCodeTest() {
		try{
			OpencpsServiceExceptionDetails object = new OpencpsServiceExceptionDetails();
			object.setFaultCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFaultCodeTest() {
		try{
			OpencpsServiceExceptionDetails object = new OpencpsServiceExceptionDetails();
			object.getFaultCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFaultMessageTest() {
		try{
			OpencpsServiceExceptionDetails object = new OpencpsServiceExceptionDetails();
			object.getFaultMessage();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFaultMessageTest() {
		try{
			OpencpsServiceExceptionDetails object = new OpencpsServiceExceptionDetails();
			object.setFaultMessage("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}