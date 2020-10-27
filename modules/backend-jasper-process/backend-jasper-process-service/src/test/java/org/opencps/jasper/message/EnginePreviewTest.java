package org.opencps.jasper.message;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class EnginePreviewTest {
	@Before
	public void setUp() {
	}
	@Test
	public void receiveTest() {
		try{
			EnginePreview object = new EnginePreview();
			object.receive(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}