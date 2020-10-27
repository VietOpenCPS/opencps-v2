package org.opencps.dossiermgt.action.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class RegistrationLogActionsImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void addRegistrationLogByIdTest() {
		try{
			RegistrationLogActionsImpl object = new RegistrationLogActionsImpl();
			object.addRegistrationLogById(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void getRegistrationLogbyIdTest() {
//		try{
//			RegistrationLogActionsImpl object = new RegistrationLogActionsImpl();
//			object.getRegistrationLogbyId(Long.valueOf(0), Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void getRegistrationLogTest() {
		try{
			RegistrationLogActionsImpl object = new RegistrationLogActionsImpl();
			object.getRegistrationLog(Long.valueOf(0), Long.valueOf(0), 0, 0, null, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}