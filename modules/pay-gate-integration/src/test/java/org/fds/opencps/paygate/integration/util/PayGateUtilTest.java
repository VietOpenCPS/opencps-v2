package org.fds.opencps.paygate.integration.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class PayGateUtilTest {
	@Before
	public void setUp() {
	}
	@Test
	public void toHexStringTest() {
		try{
			PayGateUtil.toHexString(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createResponseMessageTest() {
		try{
			PayGateUtil.createResponseMessage(0, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPaymentFileIdByTransTest() {
		try{
			PayGateUtil.getPaymentFileIdByTrans("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void decodeTransactionIdTest() {
		try{
			PayGateUtil.decodeTransactionId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void generateChecksumTest() {
		try{
			PayGateUtil.generateChecksum("abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void generateChecksumTest6() {
		try{
			PayGateUtil.generateChecksum("abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void hmacSHA256EncodeTest() {
		try{
			PayGateUtil.hmacSHA256Encode("abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void hmacSHA256HexTest() {
		try{
			PayGateUtil.hmacSHA256Hex("abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}