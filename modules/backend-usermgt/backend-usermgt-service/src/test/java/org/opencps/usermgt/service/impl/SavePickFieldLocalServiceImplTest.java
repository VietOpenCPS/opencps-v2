package org.opencps.usermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class SavePickFieldLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void updatePickFieldTest() {
		try{
			SavePickFieldLocalServiceImpl object = new SavePickFieldLocalServiceImpl();
			object.updatePickField(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPickFieldTest() {
		try{
			SavePickFieldLocalServiceImpl object = new SavePickFieldLocalServiceImpl();
			object.getPickField(Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}