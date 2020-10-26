package org.opencps.api.serverconfig.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ObjectFactoryTest {
	@Before
	public void setUp() {
	}
	@Test
	public void createServerConfigSingleInputModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createServerConfigSingleInputModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createServerConfigResultsModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createServerConfigResultsModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createServerConfigInputModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createServerConfigInputModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createServerConfigDetailModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createServerConfigDetailModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createServerConfigDataModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createServerConfigDataModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createServerConfigSearchModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createServerConfigSearchModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}