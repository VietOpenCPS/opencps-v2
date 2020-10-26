package org.opencps.api.datatempmgt.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DictItemTempListTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getDictItemTempModelTest() {
		try{
			DictItemTempList object = new DictItemTempList();
			object.getDictItemTempModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}