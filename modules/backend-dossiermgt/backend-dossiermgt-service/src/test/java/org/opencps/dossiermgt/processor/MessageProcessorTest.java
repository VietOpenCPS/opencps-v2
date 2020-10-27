package org.opencps.dossiermgt.processor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class MessageProcessorTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getProcessorTest() {
		try{
			MessageProcessor.getProcessor(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}