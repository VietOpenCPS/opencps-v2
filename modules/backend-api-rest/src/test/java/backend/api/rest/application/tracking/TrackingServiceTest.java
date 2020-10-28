package backend.api.rest.application.tracking;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class TrackingServiceTest {
	@Before
	public void setUp() {
	}
	@Test
	public void dumpLogTracking0() {
		TrackingService0.dumpLogTracking();
		Assert.assertEquals(1, 1);
	}
}