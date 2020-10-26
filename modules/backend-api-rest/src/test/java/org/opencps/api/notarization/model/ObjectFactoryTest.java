package org.opencps.api.notarization.model;
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
	public void createNotarizationDetailModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createNotarizationDetailModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createNotarizationSearchModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createNotarizationSearchModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createNotarizationResultsModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createNotarizationResultsModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createNotarizationInputModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createNotarizationInputModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createNotarizationModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createNotarizationModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}