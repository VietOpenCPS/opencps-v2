package org.opencps.rest.application.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierDocumentResultModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void toStringTest() {
		try{
			DossierDocumentResultModel object = new DossierDocumentResultModel();
			object.toString();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTotalTest() {
		try{
			DossierDocumentResultModel object = new DossierDocumentResultModel();
			object.getTotal();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void totalTest() {
		try{
			DossierDocumentResultModel object = new DossierDocumentResultModel();
			object.total(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDataTest() {
		try{
			DossierDocumentResultModel object = new DossierDocumentResultModel();
			object.setData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDataItemTest() {
		try{
			DossierDocumentResultModel object = new DossierDocumentResultModel();
			object.addDataItem(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dataTest() {
		try{
			DossierDocumentResultModel object = new DossierDocumentResultModel();
			object.data(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDataTest() {
		try{
			DossierDocumentResultModel object = new DossierDocumentResultModel();
			object.getData();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTotalTest() {
		try{
			DossierDocumentResultModel object = new DossierDocumentResultModel();
			object.setTotal(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}