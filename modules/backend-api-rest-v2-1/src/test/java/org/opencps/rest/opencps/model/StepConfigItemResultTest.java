package org.opencps.rest.opencps.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class StepConfigItemResultTest {
	@Before
	public void setUp() {
	}
	@Test
	public void toStringTest() {
		try{
			StepConfigItemResult object = new StepConfigItemResult();
			object.toString();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTotalTest() {
		try{
			StepConfigItemResult object = new StepConfigItemResult();
			object.getTotal();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void totalTest() {
		try{
			StepConfigItemResult object = new StepConfigItemResult();
			object.total(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDataTest() {
		try{
			StepConfigItemResult object = new StepConfigItemResult();
			object.setData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDataItemTest() {
		try{
			StepConfigItemResult object = new StepConfigItemResult();
			object.addDataItem(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dataTest() {
		try{
			StepConfigItemResult object = new StepConfigItemResult();
			object.data(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDataTest() {
		try{
			StepConfigItemResult object = new StepConfigItemResult();
			object.getData();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTotalTest() {
		try{
			StepConfigItemResult object = new StepConfigItemResult();
			object.setTotal(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}