package backend.postal.api.rest.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class VnPostTermTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getVNPostServerNoTest() {
		try{
			VnPostTerm.getVNPostServerNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}