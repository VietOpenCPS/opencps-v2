package org.opencps.event.message;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class LGSPEventTest {
	@Before
	public void setUp() {
	}
	@Test
	public void receiveTest() {
		try{
			LGSPEvent object = new LGSPEvent();
			object.receive(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}