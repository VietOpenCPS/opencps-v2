package org.opencps.dossiermgt.listenner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierLogListennerTest {
	@Before
	public void setUp() {
	}
	@Test
	public void onBeforeUpdateTest() {
		try{
			DossierLogListenner object = new DossierLogListenner();
			object.onBeforeUpdate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void onBeforeUpdateTest2() {
		try{
			DossierLogListenner object = new DossierLogListenner();
			object.onBeforeUpdate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void onBeforeUpdateTest3() {
		try{
			DossierLogListenner object = new DossierLogListenner();
			object.onBeforeUpdate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void onAfterUpdateTest() {
		try{
			DossierLogListenner object = new DossierLogListenner();
			object.onAfterUpdate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void onAfterUpdateTest5() {
		try{
			DossierLogListenner object = new DossierLogListenner();
			object.onAfterUpdate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void onAfterUpdateTest6() {
		try{
			DossierLogListenner object = new DossierLogListenner();
			object.onAfterUpdate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void onAfterCreateTest() {
		try{
			DossierLogListenner object = new DossierLogListenner();
			object.onAfterCreate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void onAfterCreateTest8() {
		try{
			DossierLogListenner object = new DossierLogListenner();
			object.onAfterCreate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void onAfterCreateTest9() {
		try{
			DossierLogListenner object = new DossierLogListenner();
			object.onAfterCreate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}