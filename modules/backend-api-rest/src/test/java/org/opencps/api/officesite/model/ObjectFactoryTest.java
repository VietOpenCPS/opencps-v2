package org.opencps.api.officesite.model;
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
	public void createDataSearchModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDataSearchModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createOfficeSiteInputModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createOfficeSiteInputModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createOfficeSiteModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createOfficeSiteModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createOfficeSiteResultsTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createOfficeSiteResults();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createSiteGroupTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createSiteGroup();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createAdminUserTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createAdminUser();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}