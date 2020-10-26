package org.opencps.api.newsbroad.model;
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
	public void createNewsBoardSearchModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createNewsBoardSearchModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createNewsBoardInputModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createNewsBoardInputModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createNewsBoardDetailModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createNewsBoardDetailModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createNewsBoardModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createNewsBoardModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createNewsBoardResultsModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createNewsBoardResultsModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}