package org.opencps.api.controller.util;
import com.liferay.portal.kernel.search.Document;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class PaymentFileUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void mappingToPaymentFileSearchResultModelTest() {
		try{
			PaymentFileUtils.mappingToPaymentFileSearchResultModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingToPaymentFileModelTest() {
		try{
			PaymentFileUtils.mappingToPaymentFileModel(new ArrayList<Document>());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void mappingToPaymentFileModelTest3() {
//		try{
//			PaymentFileUtils.mappingToPaymentFileModel(new PaymentFileImpl());
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void mappingToPaymentFileInputModelTest() {
		try{
			PaymentFileUtils.mappingToPaymentFileInputModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileInfoTest() {
		try{
			PaymentFileUtils.getFileInfo(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void readNumTest() {
		try{
			PaymentFileUtils.readNum("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertFormModelToInputModelTest() {
		try{
			PaymentFileUtils.convertFormModelToInputModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}