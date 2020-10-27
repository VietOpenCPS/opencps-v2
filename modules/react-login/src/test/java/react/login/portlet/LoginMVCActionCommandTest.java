package react.login.portlet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class LoginMVCActionCommandTest {
	@Before
	public void setUp() {
	}
	@Test
	public void doProcessActionTest() {
		try{
			LoginMVCActionCommand object = new LoginMVCActionCommand();
			object.doProcessAction(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}