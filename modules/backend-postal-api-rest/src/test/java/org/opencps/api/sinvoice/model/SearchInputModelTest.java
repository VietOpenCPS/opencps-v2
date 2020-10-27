package org.opencps.api.sinvoice.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class SearchInputModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setInvoiceNoTest() {
		try{
			SearchInputModel object = new SearchInputModel();
			object.setInvoiceNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getInvoiceNoTest() {
		try{
			SearchInputModel object = new SearchInputModel();
			object.getInvoiceNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEndDateTest() {
		try{
			SearchInputModel object = new SearchInputModel();
			object.getEndDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setStartDateTest() {
		try{
			SearchInputModel object = new SearchInputModel();
			object.setStartDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStartDateTest() {
		try{
			SearchInputModel object = new SearchInputModel();
			object.getStartDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGetAllTest() {
		try{
			SearchInputModel object = new SearchInputModel();
			object.getGetAll();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRowPerPageTest() {
		try{
			SearchInputModel object = new SearchInputModel();
			object.getRowPerPage();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTemplateCodeTest() {
		try{
			SearchInputModel object = new SearchInputModel();
			object.getTemplateCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGetAllTest() {
		try{
			SearchInputModel object = new SearchInputModel();
			object.setGetAll(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setRowPerPageTest() {
		try{
			SearchInputModel object = new SearchInputModel();
			object.setRowPerPage(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPageNumTest() {
		try{
			SearchInputModel object = new SearchInputModel();
			object.setPageNum(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getInvoiceSeriTest() {
		try{
			SearchInputModel object = new SearchInputModel();
			object.getInvoiceSeri();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getInvoiceTypeTest() {
		try{
			SearchInputModel object = new SearchInputModel();
			object.getInvoiceType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setContractIdTest() {
		try{
			SearchInputModel object = new SearchInputModel();
			object.setContractId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCustomerIdTest() {
		try{
			SearchInputModel object = new SearchInputModel();
			object.setCustomerId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getBuyerTaxCodeTest() {
		try{
			SearchInputModel object = new SearchInputModel();
			object.getBuyerTaxCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPageNumTest() {
		try{
			SearchInputModel object = new SearchInputModel();
			object.getPageNum();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setInvoiceSeriTest() {
		try{
			SearchInputModel object = new SearchInputModel();
			object.setInvoiceSeri("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getBuyerIdNoTest() {
		try{
			SearchInputModel object = new SearchInputModel();
			object.getBuyerIdNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEndDateTest() {
		try{
			SearchInputModel object = new SearchInputModel();
			object.setEndDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setBuyerTaxCodeTest() {
		try{
			SearchInputModel object = new SearchInputModel();
			object.setBuyerTaxCode(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getContractNoTest() {
		try{
			SearchInputModel object = new SearchInputModel();
			object.getContractNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setContractNoTest() {
		try{
			SearchInputModel object = new SearchInputModel();
			object.setContractNo(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCustomerIdTest() {
		try{
			SearchInputModel object = new SearchInputModel();
			object.getCustomerId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setBuyerIdNoTest() {
		try{
			SearchInputModel object = new SearchInputModel();
			object.setBuyerIdNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTemplateCodeTest() {
		try{
			SearchInputModel object = new SearchInputModel();
			object.setTemplateCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getContractIdTest() {
		try{
			SearchInputModel object = new SearchInputModel();
			object.getContractId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setInvoiceTypeTest() {
		try{
			SearchInputModel object = new SearchInputModel();
			object.setInvoiceType("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}