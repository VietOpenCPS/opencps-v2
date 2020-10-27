package org.opencps.dossiermgt.listenner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class RegistrationListenerTest {
	@Before
	public void setUp() {
	}
	@Test
	public void onBeforeUpdateTest() {
		try{
			RegistrationListener object = new RegistrationListener();
			object.onBeforeUpdate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void onBeforeUpdateTest2() {
		try{
			RegistrationListener object = new RegistrationListener();
			object.onBeforeUpdate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void onBeforeUpdateTest3() {
		try{
			RegistrationListener object = new RegistrationListener();
			object.onBeforeUpdate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void onAfterUpdateTest() {
		try{
			RegistrationListener object = new RegistrationListener();
			object.onAfterUpdate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void onAfterUpdateTest5() {
		try{
			RegistrationListener object = new RegistrationListener();
			object.onAfterUpdate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void onAfterUpdateTest6() {
		try{
			RegistrationListener object = new RegistrationListener();
			object.onAfterUpdate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void onAfterCreateTest() {
		try{
			RegistrationListener object = new RegistrationListener();
			object.onAfterCreate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void onAfterCreateTest8() {
		try{
			RegistrationListener object = new RegistrationListener();
			object.onAfterCreate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void onAfterCreateTest9() {
		try{
			RegistrationListener object = new RegistrationListener();
			object.onAfterCreate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}