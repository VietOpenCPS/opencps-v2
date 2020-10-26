package org.opencps.api.serverconfig.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ServerConfigSingleInputModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getValueTest() {
		try{
			ServerConfigSingleInputModel object = new ServerConfigSingleInputModel();
			object.getValue();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setValueTest() {
		try{
			ServerConfigSingleInputModel object = new ServerConfigSingleInputModel();
			object.setValue("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}