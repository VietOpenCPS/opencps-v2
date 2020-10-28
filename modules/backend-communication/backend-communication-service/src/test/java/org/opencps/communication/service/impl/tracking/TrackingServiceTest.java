package org.opencps.communication.service.impl.tracking;
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
	@Test
	public void dumpLogTracking1() {
		TrackingService1.dumpLogTracking();
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dumpLogTracking2() {
		TrackingService2.dumpLogTracking();
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dumpLogTracking3() {
		TrackingService3.dumpLogTracking();
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dumpLogTracking4() {
		TrackingService4.dumpLogTracking();
		Assert.assertEquals(1, 1);
	}
}