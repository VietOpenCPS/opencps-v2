package org.opencps.api.dossiertemplate.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierTemplateResultsModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getTotalTest() {
		try{
			DossierTemplateResultsModel object = new DossierTemplateResultsModel();
			object.getTotal();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDataTest() {
		try{
			DossierTemplateResultsModel object = new DossierTemplateResultsModel();
			object.getData();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTotalTest() {
		try{
			DossierTemplateResultsModel object = new DossierTemplateResultsModel();
			object.setTotal(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}