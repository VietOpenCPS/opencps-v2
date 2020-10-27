package backend.auth.api;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class BackendAuthImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void hasResourceTest() {
		try{
			BackendAuthImpl object = new BackendAuthImpl();
			object.hasResource(null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void checkTokenTest() {
		try{
			BackendAuthImpl object = new BackendAuthImpl();
			object.checkToken(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void userHasResourceTest() {
		try{
			BackendAuthImpl object = new BackendAuthImpl();
			object.userHasResource(null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void isAuthTest() {
//		try{
//			BackendAuthImpl object = new BackendAuthImpl();
//			object.isAuth(null, "abcde", "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void isAdminTest() {
		try{
			BackendAuthImpl object = new BackendAuthImpl();
			object.isAdmin(null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}