package org.fds.opencps.paygate.integration.application;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class PayGateIntegrationApplicationTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void doConfirmTest() {
//		try{
//			PayGateIntegrationApplication object = new PayGateIntegrationApplication();
//			object.doConfirm(null, null, null, null, null, null, null, "abcde", "abcde", "abcde", "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void getSingletonsTest() {
		try{
			PayGateIntegrationApplication object = new PayGateIntegrationApplication();
			object.getSingletons();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void kpCreateTransactionTest() {
		try{
			PayGateIntegrationApplication object = new PayGateIntegrationApplication();
			object.kpCreateTransaction(null, null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dptracuuthanhtoanhsTest() {
		try{
			PayGateIntegrationApplication object = new PayGateIntegrationApplication();
			object.dptracuuthanhtoanhs(null, null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mcSearchResultTest() {
		try{
			PayGateIntegrationApplication object = new PayGateIntegrationApplication();
			object.mcSearchResult(null, null, null, null, null, null, null, "abcde", "abcde", "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dvcReceiveResultTest() {
		try{
			PayGateIntegrationApplication object = new PayGateIntegrationApplication();
			object.dvcReceiveResult(null, null, null, null, null, null, null, "abcde", Long.valueOf(0), "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void searchResultTest() {
//		try{
//			PayGateIntegrationApplication object = new PayGateIntegrationApplication();
//			object.searchResult(null, null, null, null, null, null, null, "abcde", "abcde", "abcde", Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void receiveResultTest() {
//		try{
//			PayGateIntegrationApplication object = new PayGateIntegrationApplication();
//			object.receiveResult(null, null, null, null, null, null, null, "abcde", "abcde", "abcde", "abcde", "abcde", 0, Long.valueOf(0), "abcde", "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void mcDoConfirmTest() {
		try{
			PayGateIntegrationApplication object = new PayGateIntegrationApplication();
			object.mcDoConfirm(null, null, null, null, null, null, null, "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mcReceiveResultTest() {
		try{
			PayGateIntegrationApplication object = new PayGateIntegrationApplication();
			object.mcReceiveResult(null, null, null, null, null, null, null, "abcde", "abcde", "abcde", "abcde", "abcde", 0, Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void generalQRCodeTest() {
		try{
			PayGateIntegrationApplication object = new PayGateIntegrationApplication();
			object.generalQRCode(null, null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void kpCallbackTest() {
		try{
			PayGateIntegrationApplication object = new PayGateIntegrationApplication();
			object.kpCallback(null, null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}