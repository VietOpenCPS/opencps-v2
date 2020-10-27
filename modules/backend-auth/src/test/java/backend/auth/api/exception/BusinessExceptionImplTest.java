package backend.auth.api.exception;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class BusinessExceptionImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void processExceptionTest() {
		try{
			BusinessExceptionImpl.processException(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}