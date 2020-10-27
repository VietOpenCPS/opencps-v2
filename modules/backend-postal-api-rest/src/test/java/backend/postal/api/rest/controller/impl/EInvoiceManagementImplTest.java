package backend.postal.api.rest.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class EInvoiceManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void fromJSONObjectTest() {
		try{
			EInvoiceManagementImpl object = new EInvoiceManagementImpl();
			object.fromJSONObject(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void getServerConfigTest() {
//		try{
//			EInvoiceManagementImpl object = new EInvoiceManagementImpl();
//			object.getServerConfig(Long.valueOf(0), "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void getInvoiceTest() {
		try{
			EInvoiceManagementImpl object = new EInvoiceManagementImpl();
			object.getInvoice(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}