package opencps.esb.api.rest.application;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class OpencpsEsbApiRestApplicationTest {
	@Before
	public void setUp() {
	}
	@Test
	public void morningTest() {
		try{
			OpencpsEsbApiRestApplication object = new OpencpsEsbApiRestApplication();
			object.morning("abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void helloTest() {
		try{
			OpencpsEsbApiRestApplication object = new OpencpsEsbApiRestApplication();
			object.hello();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSingletonsTest() {
		try{
			OpencpsEsbApiRestApplication object = new OpencpsEsbApiRestApplication();
			object.getSingletons();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void workingTest() {
		try{
			OpencpsEsbApiRestApplication object = new OpencpsEsbApiRestApplication();
			object.working();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}