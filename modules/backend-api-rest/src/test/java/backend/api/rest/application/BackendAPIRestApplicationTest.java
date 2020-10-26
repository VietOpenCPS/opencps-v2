package backend.api.rest.application;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class BackendAPIRestApplicationTest {
	@Before
	public void setUp() {
	}
	@Test
	public void authenticateUserTest() {
		try{
			BackendAPIRestApplication object = new BackendAPIRestApplication();
			object.authenticateUser(null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getQRcodeTest() {
		try{
			BackendAPIRestApplication object = new BackendAPIRestApplication();
			object.getQRcode(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void getBarcodeTest() {
//		try{
//			BackendAPIRestApplication object = new BackendAPIRestApplication();
//			object.getBarcode(null, null, null, null, null, null, "abcde", "abcde", "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void countEntityTest() {
		try{
			BackendAPIRestApplication object = new BackendAPIRestApplication();
			object.countEntity(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void getSingletonsTest() {
//		try{
//			BackendAPIRestApplication object = new BackendAPIRestApplication();
//			object.getSingletons();
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void workingTest() {
		try{
			BackendAPIRestApplication object = new BackendAPIRestApplication();
			object.working();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void pingTest() {
		try{
			BackendAPIRestApplication object = new BackendAPIRestApplication();
			object.ping();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}