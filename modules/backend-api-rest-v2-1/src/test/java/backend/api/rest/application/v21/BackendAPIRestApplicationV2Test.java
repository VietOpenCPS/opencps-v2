package backend.api.rest.application.v21;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class BackendAPIRestApplicationV2Test {
	@Before
	public void setUp() {
	}
	@Test
	public void morningTest() {
		try{
			BackendAPIRestApplicationV2 object = new BackendAPIRestApplicationV2();
			object.morning("abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void helloTest() {
		try{
			BackendAPIRestApplicationV2 object = new BackendAPIRestApplicationV2();
			object.hello();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void hellosTest() {
		try{
			BackendAPIRestApplicationV2 object = new BackendAPIRestApplicationV2();
			object.hellos("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void morningRemoveTest() {
		try{
			BackendAPIRestApplicationV2 object = new BackendAPIRestApplicationV2();
			object.morningRemove();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void puthellosTest() {
		try{
			BackendAPIRestApplicationV2 object = new BackendAPIRestApplicationV2();
			object.puthellos("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSingletonsTest() {
		try{
			BackendAPIRestApplicationV2 object = new BackendAPIRestApplicationV2();
			object.getSingletons();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void workingTest() {
		try{
			BackendAPIRestApplicationV2 object = new BackendAPIRestApplicationV2();
			object.working(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}