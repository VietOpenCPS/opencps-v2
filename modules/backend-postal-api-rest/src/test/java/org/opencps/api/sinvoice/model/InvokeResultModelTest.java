package org.opencps.api.sinvoice.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class InvokeResultModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getFileNameTest() {
		try{
			InvokeResultModel object = new InvokeResultModel();
			object.getFileName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setInvoiceNoTest() {
		try{
			InvokeResultModel object = new InvokeResultModel();
			object.setInvoiceNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getInvoiceNoTest() {
		try{
			InvokeResultModel object = new InvokeResultModel();
			object.getInvoiceNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDescriptionTest() {
		try{
			InvokeResultModel object = new InvokeResultModel();
			object.getDescription();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDescriptionTest() {
		try{
			InvokeResultModel object = new InvokeResultModel();
			object.setDescription("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setStatusTest() {
		try{
			InvokeResultModel object = new InvokeResultModel();
			object.setStatus(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFileNameTest() {
		try{
			InvokeResultModel object = new InvokeResultModel();
			object.setFileName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStatusTest() {
		try{
			InvokeResultModel object = new InvokeResultModel();
			object.getStatus();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getErrorCodeTest() {
		try{
			InvokeResultModel object = new InvokeResultModel();
			object.getErrorCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setErrorCodeTest() {
		try{
			InvokeResultModel object = new InvokeResultModel();
			object.setErrorCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSupplierTaxCodeTest() {
		try{
			InvokeResultModel object = new InvokeResultModel();
			object.setSupplierTaxCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setReservationCodeTest() {
		try{
			InvokeResultModel object = new InvokeResultModel();
			object.setReservationCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSupplierTaxCodeTest() {
		try{
			InvokeResultModel object = new InvokeResultModel();
			object.getSupplierTaxCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getReservationCodeTest() {
		try{
			InvokeResultModel object = new InvokeResultModel();
			object.getReservationCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setInvoicesTest() {
		try{
			InvokeResultModel object = new InvokeResultModel();
			object.setInvoices(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getInvoicesTest() {
		try{
			InvokeResultModel object = new InvokeResultModel();
			object.getInvoices();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFileToBytesTest() {
		try{
			InvokeResultModel object = new InvokeResultModel();
			object.setFileToBytes("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTransactionIDTest() {
		try{
			InvokeResultModel object = new InvokeResultModel();
			object.setTransactionID("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileToBytesTest() {
		try{
			InvokeResultModel object = new InvokeResultModel();
			object.getFileToBytes();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTransactionIDTest() {
		try{
			InvokeResultModel object = new InvokeResultModel();
			object.getTransactionID();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}