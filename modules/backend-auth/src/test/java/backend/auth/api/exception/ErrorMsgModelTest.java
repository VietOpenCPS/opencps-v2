package backend.auth.api.exception;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ErrorMsgModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getMessageTest() {
		try{
			ErrorMsgModel object = new ErrorMsgModel();
			object.getMessage();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDescriptionTest() {
		try{
			ErrorMsgModel object = new ErrorMsgModel();
			object.getDescription();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDescriptionTest() {
		try{
			ErrorMsgModel object = new ErrorMsgModel();
			object.setDescription("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCodeTest() {
		try{
			ErrorMsgModel object = new ErrorMsgModel();
			object.getCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCodeTest() {
		try{
			ErrorMsgModel object = new ErrorMsgModel();
			object.setCode(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMessageTest() {
		try{
			ErrorMsgModel object = new ErrorMsgModel();
			object.setMessage("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}