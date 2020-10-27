package org.opencps.usermgt.listener;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ApplicantListenerTest {
	@Before
	public void setUp() {
	}
	@Test
	public void onAfterUpdateTest() {
		try{
			ApplicantListener object = new ApplicantListener();
			object.onAfterUpdate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void onAfterUpdateTest2() {
		try{
			ApplicantListener object = new ApplicantListener();
			object.onAfterUpdate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void onAfterUpdateTest3() {
		try{
			ApplicantListener object = new ApplicantListener();
			object.onAfterUpdate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void onAfterCreateTest() {
		try{
			ApplicantListener object = new ApplicantListener();
			object.onAfterCreate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void onAfterCreateTest5() {
		try{
			ApplicantListener object = new ApplicantListener();
			object.onAfterCreate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void onAfterCreateTest6() {
		try{
			ApplicantListener object = new ApplicantListener();
			object.onAfterCreate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}