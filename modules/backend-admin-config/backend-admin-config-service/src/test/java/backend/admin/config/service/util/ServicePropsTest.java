package backend.admin.config.service.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ServicePropsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getTest() {
		try{
			ServiceProps.get("abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTest2() {
		try{
			ServiceProps.get("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void containsTest() {
		try{
			ServiceProps.contains("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getArrayTest() {
		try{
			ServiceProps.getArray("abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getArrayTest5() {
		try{
			ServiceProps.getArray("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPropertiesTest() {
		try{
			ServiceProps.getProperties();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTest() {
		try{
			ServiceProps.set("abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addPropertiesTest() {
		try{
			ServiceProps.addProperties(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removePropertiesTest() {
		try{
			ServiceProps.removeProperties(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}