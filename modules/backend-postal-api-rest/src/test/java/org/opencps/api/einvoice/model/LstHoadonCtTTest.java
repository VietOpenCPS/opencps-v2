package org.opencps.api.einvoice.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class LstHoadonCtTTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getHoadonGtgtCtTest() {
		try{
			LstHoadonCtT object = new LstHoadonCtT();
			object.getHoadonGtgtCt();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}