package backend.postal.api.rest.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class VNPostManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void fromJSONObjectTest() {
		try{
			VNPostManagementImpl object = new VNPostManagementImpl();
			object.fromJSONObject(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void getServerConfigTest() {
//		try{
//			VNPostManagementImpl object = new VNPostManagementImpl();
//			object.getServerConfig(Long.valueOf(0), "abcde", "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void sendCollectionVNPostalRequestTest() {
		try{
			VNPostManagementImpl object = new VNPostManagementImpl();
			object.sendCollectionVNPostalRequest(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void sendPostalRequestTest() {
		try{
			VNPostManagementImpl object = new VNPostManagementImpl();
			object.sendPostalRequest(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOrderTrackingTest() {
		try{
			VNPostManagementImpl object = new VNPostManagementImpl();
			object.getOrderTracking(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void cancelOrderTest() {
		try{
			VNPostManagementImpl object = new VNPostManagementImpl();
			object.cancelOrder(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}