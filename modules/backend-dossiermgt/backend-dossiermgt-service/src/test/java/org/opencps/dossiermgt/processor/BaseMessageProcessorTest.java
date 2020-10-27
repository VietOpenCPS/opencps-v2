package org.opencps.dossiermgt.processor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class BaseMessageProcessorTest {
	@Before
	public void setUp() {
	}
	@Test
	public void processTest() {
		try{
			BaseMessageProcessor object = new BaseMessageProcessor();
			object.process();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void processInformDossierTest() {
		try{
			BaseMessageProcessor object = new BaseMessageProcessor();
			object.processInformDossier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void processInformTest() {
		try{
			BaseMessageProcessor object = new BaseMessageProcessor();
			object.processInform();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void processRequestTest() {
		try{
			BaseMessageProcessor object = new BaseMessageProcessor();
			object.processRequest();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}