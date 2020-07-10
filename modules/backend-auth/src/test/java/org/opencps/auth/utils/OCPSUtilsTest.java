package org.opencps.auth.utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class OCPSUtilsTest {
	@Before
	public void setUp() {
	}

	@Test
	public void toLongArrayTest() {
		List<Long> inputArr = new ArrayList<>();
		inputArr.add(12L);
		inputArr.add(13L);
		inputArr.add(14L);
		long[] outputArr = OCPSUtils.toLongArray(inputArr);
		long[] expectedOutputArr = {12L, 13L, 14L};
		Assert.assertArrayEquals(outputArr, expectedOutputArr);
	}

	@Test
	public void toStringArrayTest() {
		List<String> inputArr = new ArrayList<>();
		inputArr.add("abc");
		inputArr.add("bcd");
		inputArr.add("def");
		String[] outputArr = OCPSUtils.toStringArray(inputArr);
		String[] expectedOutputArr = {"abc", "bcd", "def"};
		Assert.assertArrayEquals(outputArr, expectedOutputArr);
	}
}
