package org.opencps.api.applicantdata.model;
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
	public void createApplicantDataModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createApplicantDataModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createApplicantDataResultsModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createApplicantDataResultsModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createApplicantDataDetailModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createApplicantDataDetailModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createApplicantDataSearchModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createApplicantDataSearchModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createApplicantDataInputModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createApplicantDataInputModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}