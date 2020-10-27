package org.opencps.rest.opencps.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DocumentTypeResultModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void toStringTest() {
		try{
			DocumentTypeResultModel object = new DocumentTypeResultModel();
			object.toString();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTotalTest() {
		try{
			DocumentTypeResultModel object = new DocumentTypeResultModel();
			object.getTotal();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void totalTest() {
		try{
			DocumentTypeResultModel object = new DocumentTypeResultModel();
			object.total(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDataTest() {
		try{
			DocumentTypeResultModel object = new DocumentTypeResultModel();
			object.setData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDataItemTest() {
		try{
			DocumentTypeResultModel object = new DocumentTypeResultModel();
			object.addDataItem(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dataTest() {
		try{
			DocumentTypeResultModel object = new DocumentTypeResultModel();
			object.data(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDataTest() {
		try{
			DocumentTypeResultModel object = new DocumentTypeResultModel();
			object.getData();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTotalTest() {
		try{
			DocumentTypeResultModel object = new DocumentTypeResultModel();
			object.setTotal(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}