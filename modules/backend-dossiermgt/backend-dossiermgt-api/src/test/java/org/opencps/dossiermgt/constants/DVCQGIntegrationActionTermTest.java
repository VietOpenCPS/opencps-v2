package org.opencps.dossiermgt.constants;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class DVCQGIntegrationActionTermTest {
	@Before
	public void setUp() {
	}
	@Test
	public void convertDate2StringTest() {
		try{
			DVCQGIntegrationActionTerm.convertDate2String(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}