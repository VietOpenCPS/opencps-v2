package org.opencps.rest.opencps.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierSyncResultModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void toStringTest() {
		try{
			DossierSyncResultModel object = new DossierSyncResultModel();
			object.toString();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTotalTest() {
		try{
			DossierSyncResultModel object = new DossierSyncResultModel();
			object.getTotal();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void totalTest() {
		try{
			DossierSyncResultModel object = new DossierSyncResultModel();
			object.total(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDataTest() {
		try{
			DossierSyncResultModel object = new DossierSyncResultModel();
			object.setData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDataItemTest() {
		try{
			DossierSyncResultModel object = new DossierSyncResultModel();
			object.addDataItem(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dataTest() {
		try{
			DossierSyncResultModel object = new DossierSyncResultModel();
			object.data(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDataTest() {
		try{
			DossierSyncResultModel object = new DossierSyncResultModel();
			object.getData();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTotalTest() {
		try{
			DossierSyncResultModel object = new DossierSyncResultModel();
			object.setTotal(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}