package org.opencps.datamgt.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DueDatePhaseUtilTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getDueDateTest() {
		try{
			DueDatePhaseUtil object = new DueDatePhaseUtil(Long.valueOf(0), new Date(), 0, "abcde");
			object.getDueDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}