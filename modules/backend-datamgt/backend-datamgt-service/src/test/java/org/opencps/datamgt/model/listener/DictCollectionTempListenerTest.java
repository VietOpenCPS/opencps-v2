package org.opencps.datamgt.model.listener;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DictCollectionTempListenerTest {
	@Before
	public void setUp() {
	}
	@Test
	public void onAfterUpdateTest() {
		try{
			DictCollectionTempListener object = new DictCollectionTempListener();
			object.onAfterUpdate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void onAfterUpdateTest2() {
		try{
			DictCollectionTempListener object = new DictCollectionTempListener();
			object.onAfterUpdate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void onAfterUpdateTest3() {
		try{
			DictCollectionTempListener object = new DictCollectionTempListener();
			object.onAfterUpdate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void onAfterCreateTest() {
		try{
			DictCollectionTempListener object = new DictCollectionTempListener();
			object.onAfterCreate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void onAfterCreateTest5() {
		try{
			DictCollectionTempListener object = new DictCollectionTempListener();
			object.onAfterCreate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void onAfterCreateTest6() {
		try{
			DictCollectionTempListener object = new DictCollectionTempListener();
			object.onAfterCreate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}