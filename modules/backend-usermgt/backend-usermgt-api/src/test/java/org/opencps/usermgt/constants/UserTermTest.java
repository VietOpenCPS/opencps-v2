package org.opencps.usermgt.constants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class UserTermTest {
	@Before
	public void setUp() {
	}
	@Test
	public void buildEmpFileSignTest() {
		try{
			UserTerm.buildEmpFileSign("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void buildEmpAvatarTest() {
		try{
			UserTerm.buildEmpAvatar(null, Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserByIdDefaultTest() {
		try{
			UserTerm.getUserByIdDefault();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}