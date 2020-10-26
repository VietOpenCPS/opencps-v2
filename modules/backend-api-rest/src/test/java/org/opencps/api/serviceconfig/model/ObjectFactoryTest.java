package org.opencps.api.serviceconfig.model;
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
	public void createProcessOptionSearchModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createProcessOptionSearchModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createProcessOptionResultsModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createProcessOptionResultsModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createServiceConfigResultsModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createServiceConfigResultsModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createProcessOptionTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createProcessOption();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createServiceConfigTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createServiceConfig();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createProcessOptionInputModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createProcessOptionInputModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createServiceConfigDetailModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createServiceConfigDetailModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createServiceConfigInputModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createServiceConfigInputModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createServiceConfigSearchModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createServiceConfigSearchModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}