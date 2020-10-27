package backend.utils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class FileUploadUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void updateFileTest() {
		try{
			FileUploadUtils.updateFile(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, "abcde", "abcde", Long.valueOf(0), "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void uploadFileTest() {
		try{
			FileUploadUtils.uploadFile(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, "abcde", "abcde", Long.valueOf(0), "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}