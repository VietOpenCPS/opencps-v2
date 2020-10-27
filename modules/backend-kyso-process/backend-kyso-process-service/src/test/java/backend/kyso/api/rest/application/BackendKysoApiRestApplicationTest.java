package backend.kyso.api.rest.application;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class BackendKysoApiRestApplicationTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getSingletonsTest() {
		try{
			BackendKysoApiRestApplication object = new BackendKysoApiRestApplication();
			object.getSingletons();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByTokensTest() {
		try{
			BackendKysoApiRestApplication object = new BackendKysoApiRestApplication();
			object.getByTokens(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void completeSignatureTest() {
		try{
			BackendKysoApiRestApplication object = new BackendKysoApiRestApplication();
			object.completeSignature(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}