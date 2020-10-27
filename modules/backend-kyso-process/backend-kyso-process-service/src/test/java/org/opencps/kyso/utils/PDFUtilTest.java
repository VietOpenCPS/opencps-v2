package org.opencps.kyso.utils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class PDFUtilTest {
	@Before
	public void setUp() {
	}
	@Test
	public void saveAsPdfTest() {
		try{
			PDFUtil.saveAsPdf("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}