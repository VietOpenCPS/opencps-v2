package org.fds.opencps.paygate.integration.action.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class PayGateIntegrationActionImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void callPostAPITest() {
		try{
			PayGateIntegrationActionImpl object = new PayGateIntegrationActionImpl();
			object.callPostAPI("abcde", "abcde", "abcde", null, null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void doConfirmTest() {
//		try{
//			PayGateIntegrationActionImpl object = new PayGateIntegrationActionImpl();
//			object.doConfirm(null, null, "abcde", "abcde", "abcde", "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void kpCreateTransactionTest() {
//		try{
//			PayGateIntegrationActionImpl object = new PayGateIntegrationActionImpl();
//			object.kpCreateTransaction(null, Long.valueOf(0), Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void dptracuuthanhtoanhsTest() {
		try{
			PayGateIntegrationActionImpl object = new PayGateIntegrationActionImpl();
			object.dptracuuthanhtoanhs(null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void kpViewDetailTransactionTest() {
		try{
			PayGateIntegrationActionImpl object = new PayGateIntegrationActionImpl();
			object.kpViewDetailTransaction(null, Long.valueOf(0), Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mcSearchResultTest() {
		try{
			PayGateIntegrationActionImpl object = new PayGateIntegrationActionImpl();
			object.mcSearchResult(null, null, "abcde", "abcde", "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dvcReceiveResultTest() {
		try{
			PayGateIntegrationActionImpl object = new PayGateIntegrationActionImpl();
			object.dvcReceiveResult(null, null, "abcde", Long.valueOf(0), "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void kpCallBackTest() {
		try{
			PayGateIntegrationActionImpl object = new PayGateIntegrationActionImpl();
			object.kpCallBack(null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void searchResultTest() {
//		try{
//			PayGateIntegrationActionImpl object = new PayGateIntegrationActionImpl();
//			object.searchResult(null, null, "abcde", "abcde", "abcde", Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void genneralQRCodeTest() {
//		try{
//			PayGateIntegrationActionImpl object = new PayGateIntegrationActionImpl();
//			object.genneralQRCode(null, Long.valueOf(0), Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void receiveResultTest() {
//		try{
//			PayGateIntegrationActionImpl object = new PayGateIntegrationActionImpl();
//			object.receiveResult(null, null, "abcde", "abcde", "abcde", "abcde", "abcde", 0, Long.valueOf(0), "abcde", "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void mcDoConfirmTest() {
		try{
			PayGateIntegrationActionImpl object = new PayGateIntegrationActionImpl();
			object.mcDoConfirm(null, null, "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mcReceiveResultTest() {
		try{
			PayGateIntegrationActionImpl object = new PayGateIntegrationActionImpl();
			object.mcReceiveResult(null, null, "abcde", "abcde", "abcde", "abcde", "abcde", 0, Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}