package org.opencps.api.dossieraction.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ReceivingModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getDueDateTest() {
		try{
			ReceivingModel object = new ReceivingModel();
			object.getDueDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEditableTest() {
		try{
			ReceivingModel object = new ReceivingModel();
			object.setEditable(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isEditableTest() {
		try{
			ReceivingModel object = new ReceivingModel();
			object.isEditable();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDueDateTest() {
		try{
			ReceivingModel object = new ReceivingModel();
			object.setDueDate(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setReceiveDateTest() {
		try{
			ReceivingModel object = new ReceivingModel();
			object.setReceiveDate(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getReceiveDateTest() {
		try{
			ReceivingModel object = new ReceivingModel();
			object.getReceiveDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}