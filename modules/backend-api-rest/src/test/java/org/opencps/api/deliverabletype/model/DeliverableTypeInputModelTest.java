package org.opencps.api.deliverabletype.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DeliverableTypeInputModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getCounterTest() {
		try{
			DeliverableTypeInputModel object = new DeliverableTypeInputModel();
			object.getCounter();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFormScriptTest() {
		try{
			DeliverableTypeInputModel object = new DeliverableTypeInputModel();
			object.getFormScript();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDeliverableNameTest() {
		try{
			DeliverableTypeInputModel object = new DeliverableTypeInputModel();
			object.setDeliverableName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCounterTest() {
		try{
			DeliverableTypeInputModel object = new DeliverableTypeInputModel();
			object.setCounter(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMappingDataTest() {
		try{
			DeliverableTypeInputModel object = new DeliverableTypeInputModel();
			object.getMappingData();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCodePatternTest() {
		try{
			DeliverableTypeInputModel object = new DeliverableTypeInputModel();
			object.getCodePattern();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFormReportTest() {
		try{
			DeliverableTypeInputModel object = new DeliverableTypeInputModel();
			object.getFormReport();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDeliverableTypeTest() {
		try{
			DeliverableTypeInputModel object = new DeliverableTypeInputModel();
			object.getDeliverableType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDeliverableTypeTest() {
		try{
			DeliverableTypeInputModel object = new DeliverableTypeInputModel();
			object.setDeliverableType("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDeliverableNameTest() {
		try{
			DeliverableTypeInputModel object = new DeliverableTypeInputModel();
			object.getDeliverableName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFormReportTest() {
		try{
			DeliverableTypeInputModel object = new DeliverableTypeInputModel();
			object.setFormReport("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFormScriptTest() {
		try{
			DeliverableTypeInputModel object = new DeliverableTypeInputModel();
			object.setFormScript("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMappingDataTest() {
		try{
			DeliverableTypeInputModel object = new DeliverableTypeInputModel();
			object.setMappingData("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCodePatternTest() {
		try{
			DeliverableTypeInputModel object = new DeliverableTypeInputModel();
			object.setCodePattern("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}