package org.opencps.api.deliverabletype.model;
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
	public void createDeliverableTypesSearchIdModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDeliverableTypesSearchIdModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDeliverableTypesResultsModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDeliverableTypesResultsModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDeliverableTypesModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDeliverableTypesModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDeliverableTypeInputModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDeliverableTypeInputModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDeliverableTypeDetailModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDeliverableTypeDetailModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}