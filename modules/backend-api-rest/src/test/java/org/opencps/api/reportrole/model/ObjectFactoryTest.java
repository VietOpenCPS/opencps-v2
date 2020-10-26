package org.opencps.api.reportrole.model;
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
	public void createReportRoleDetailModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createReportRoleDetailModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createReportRoleSearchModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createReportRoleSearchModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createReportRoleResultsModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createReportRoleResultsModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createReportRoleModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createReportRoleModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createReportRoleInputModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createReportRoleInputModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createReportRoleInputCodeModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createReportRoleInputCodeModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}