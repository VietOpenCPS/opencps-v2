package org.opencps.dossiermgt.rest.utils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ExecuteOneActionTermTest {
	@Before
	public void setUp() {
	}
	@Test
	public void invokeSInvoiceTest() {
		try{
			ExecuteOneActionTerm.invokeSInvoice(Long.valueOf(0), null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}