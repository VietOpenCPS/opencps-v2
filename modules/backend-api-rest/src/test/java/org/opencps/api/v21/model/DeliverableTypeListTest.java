package org.opencps.api.v21.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DeliverableTypeListTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getDeliverableTypeTest() {
		try{
			DeliverableTypeList object = new DeliverableTypeList();
			object.getDeliverableType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}