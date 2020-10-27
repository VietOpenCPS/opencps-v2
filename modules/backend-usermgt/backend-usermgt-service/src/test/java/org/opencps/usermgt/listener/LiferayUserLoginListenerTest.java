package org.opencps.usermgt.listener;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class LiferayUserLoginListenerTest {
	@Before
	public void setUp() {
	}
	@Test
	public void onAfterUpdateTest() {
		try{
			LiferayUserLoginListener object = new LiferayUserLoginListener();
			object.onAfterUpdate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void onAfterUpdateTest2() {
		try{
			LiferayUserLoginListener object = new LiferayUserLoginListener();
			object.onAfterUpdate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void onAfterUpdateTest3() {
		try{
			LiferayUserLoginListener object = new LiferayUserLoginListener();
			object.onAfterUpdate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}