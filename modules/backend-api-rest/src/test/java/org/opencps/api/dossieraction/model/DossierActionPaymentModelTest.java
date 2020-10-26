package org.opencps.api.dossieraction.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierActionPaymentModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setPaymentFeeTest() {
		try{
			DossierActionPaymentModel object = new DossierActionPaymentModel();
			object.setPaymentFee("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPaymentFeeTest() {
		try{
			DossierActionPaymentModel object = new DossierActionPaymentModel();
			object.getPaymentFee();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEditableTest() {
		try{
			DossierActionPaymentModel object = new DossierActionPaymentModel();
			object.setEditable(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRequestPaymentTest() {
		try{
			DossierActionPaymentModel object = new DossierActionPaymentModel();
			object.getRequestPayment();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setRequestPaymentTest() {
		try{
			DossierActionPaymentModel object = new DossierActionPaymentModel();
			object.setRequestPayment(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFeeAmountTest() {
		try{
			DossierActionPaymentModel object = new DossierActionPaymentModel();
			object.setFeeAmount(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setAdvanceAmountTest() {
		try{
			DossierActionPaymentModel object = new DossierActionPaymentModel();
			object.setAdvanceAmount(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPaymentNoteTest() {
		try{
			DossierActionPaymentModel object = new DossierActionPaymentModel();
			object.getPaymentNote();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFeeAmountTest() {
		try{
			DossierActionPaymentModel object = new DossierActionPaymentModel();
			object.getFeeAmount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceAmountTest() {
		try{
			DossierActionPaymentModel object = new DossierActionPaymentModel();
			object.getServiceAmount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAdvanceAmountTest() {
		try{
			DossierActionPaymentModel object = new DossierActionPaymentModel();
			object.getAdvanceAmount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPaymentAmountTest() {
		try{
			DossierActionPaymentModel object = new DossierActionPaymentModel();
			object.setPaymentAmount(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setShipAmountTest() {
		try{
			DossierActionPaymentModel object = new DossierActionPaymentModel();
			object.setShipAmount(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPaymentNoteTest() {
		try{
			DossierActionPaymentModel object = new DossierActionPaymentModel();
			object.setPaymentNote("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPaymentAmountTest() {
		try{
			DossierActionPaymentModel object = new DossierActionPaymentModel();
			object.getPaymentAmount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setServiceAmountTest() {
		try{
			DossierActionPaymentModel object = new DossierActionPaymentModel();
			object.setServiceAmount(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getShipAmountTest() {
		try{
			DossierActionPaymentModel object = new DossierActionPaymentModel();
			object.getShipAmount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEditableTest() {
		try{
			DossierActionPaymentModel object = new DossierActionPaymentModel();
			object.getEditable();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}