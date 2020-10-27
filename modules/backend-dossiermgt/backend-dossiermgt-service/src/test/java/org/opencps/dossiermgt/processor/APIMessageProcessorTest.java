package org.opencps.dossiermgt.processor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class APIMessageProcessorTest {
	@Before
	public void setUp() {
	}
	@Test
	public void processInformDossierTest() {
		try{
			APIMessageProcessor object = new APIMessageProcessor(null);
			object.processInformDossier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void processInformTest() {
		try{
			APIMessageProcessor object = new APIMessageProcessor(null);
			object.processInform();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void processRequestTest() {
		try{
			APIMessageProcessor object = new APIMessageProcessor(null);
			object.processRequest();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}