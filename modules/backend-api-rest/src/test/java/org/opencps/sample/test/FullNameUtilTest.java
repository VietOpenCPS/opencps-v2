package org.opencps.sample.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.opencps.sample.FullNameUtil;


public class FullNameUtilTest {
	@Before
	public void setUp() {
		_fullNameUtil = new FullNameUtil("Brian", "Edward", "Greenwald");
	}

	@Test
	public void testFullNameLength() {
		int length = _fullNameUtil.fullNameLength();

		Assert.assertEquals(20, length);
	}

	@Test
	public void testGetMiddleInitial() {
		String middleInitial = _fullNameUtil.getMiddleInitial();

		Assert.assertEquals("E.", middleInitial);
	}

	@Test
	public void testToString() {
		String fullName = _fullNameUtil.toString();

		Assert.assertEquals("Brian E. Greenwald", fullName);
	}

	private FullNameUtil _fullNameUtil;
}
