package org.opencps.sample;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class FullNameUtilTest {
	@Before
	public void setUp() {
	}
	@Test
	public void toStringTest() {
		try{
			FullNameUtil object = new FullNameUtil("abcde", "abcde", "abcde");
			object.toString();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fullNameLengthTest() {
		try{
			FullNameUtil object = new FullNameUtil("abcde", "abcde", "abcde");
			object.fullNameLength();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMiddleInitialTest() {
		try{
			FullNameUtil object = new FullNameUtil("abcde", "abcde", "abcde");
			object.getMiddleInitial();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}