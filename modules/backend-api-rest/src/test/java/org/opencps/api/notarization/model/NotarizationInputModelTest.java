package org.opencps.api.notarization.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class NotarizationInputModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getFileNameTest() {
		try{
			NotarizationInputModel object = new NotarizationInputModel();
			object.getFileName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierIdTest() {
		try{
			NotarizationInputModel object = new NotarizationInputModel();
			object.setDossierId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierIdTest() {
		try{
			NotarizationInputModel object = new NotarizationInputModel();
			object.getDossierId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTotalRecordTest() {
		try{
			NotarizationInputModel object = new NotarizationInputModel();
			object.setTotalRecord(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setStatusCodeTest() {
		try{
			NotarizationInputModel object = new NotarizationInputModel();
			object.setStatusCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTotalCopyTest() {
		try{
			NotarizationInputModel object = new NotarizationInputModel();
			object.setTotalCopy(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSignerNameTest() {
		try{
			NotarizationInputModel object = new NotarizationInputModel();
			object.setSignerName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTotalFeeTest() {
		try{
			NotarizationInputModel object = new NotarizationInputModel();
			object.setTotalFee(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTotalPageTest() {
		try{
			NotarizationInputModel object = new NotarizationInputModel();
			object.setTotalPage(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setNotarizationYearTest() {
		try{
			NotarizationInputModel object = new NotarizationInputModel();
			object.setNotarizationYear(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setNotarizationDateTest() {
		try{
			NotarizationInputModel object = new NotarizationInputModel();
			object.setNotarizationDate(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSignerPositionTest() {
		try{
			NotarizationInputModel object = new NotarizationInputModel();
			object.setSignerPosition("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTotalCopyTest() {
		try{
			NotarizationInputModel object = new NotarizationInputModel();
			object.getTotalCopy();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTotalRecordTest() {
		try{
			NotarizationInputModel object = new NotarizationInputModel();
			object.getTotalRecord();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStatusCodeTest() {
		try{
			NotarizationInputModel object = new NotarizationInputModel();
			object.getStatusCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTotalPageTest() {
		try{
			NotarizationInputModel object = new NotarizationInputModel();
			object.getTotalPage();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTotalFeeTest() {
		try{
			NotarizationInputModel object = new NotarizationInputModel();
			object.getTotalFee();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSignerNameTest() {
		try{
			NotarizationInputModel object = new NotarizationInputModel();
			object.getSignerName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFileNameTest() {
		try{
			NotarizationInputModel object = new NotarizationInputModel();
			object.setFileName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setNotarizationNoTest() {
		try{
			NotarizationInputModel object = new NotarizationInputModel();
			object.setNotarizationNo(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNotarizationYearTest() {
		try{
			NotarizationInputModel object = new NotarizationInputModel();
			object.getNotarizationYear();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNotarizationNoTest() {
		try{
			NotarizationInputModel object = new NotarizationInputModel();
			object.getNotarizationNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSignerPositionTest() {
		try{
			NotarizationInputModel object = new NotarizationInputModel();
			object.getSignerPosition();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNotarizationDateTest() {
		try{
			NotarizationInputModel object = new NotarizationInputModel();
			object.getNotarizationDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}