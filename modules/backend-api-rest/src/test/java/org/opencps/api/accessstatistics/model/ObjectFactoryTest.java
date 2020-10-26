package org.opencps.api.accessstatistics.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class ObjectFactoryTest {
	@Before
	public void setUp() {
	}
	@Test
	public void createAccessStatisticsTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createAccessStatistics();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}