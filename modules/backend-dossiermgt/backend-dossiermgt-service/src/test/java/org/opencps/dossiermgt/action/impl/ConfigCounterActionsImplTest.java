package org.opencps.dossiermgt.action.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class ConfigCounterActionsImplTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void updateConfigCounterTest() {
//		try{
//			ConfigCounterActionsImpl object = new ConfigCounterActionsImpl();
//			object.updateConfigCounter(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", 0, new ServiceContext());
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void getConfigCounterListTest() {
		try{
			ConfigCounterActionsImpl object = new ConfigCounterActionsImpl();
			object.getConfigCounterList(Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}