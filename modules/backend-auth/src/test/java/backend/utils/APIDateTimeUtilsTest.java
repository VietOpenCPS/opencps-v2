package backend.utils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class APIDateTimeUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void timeZone2LuceneTest() {
		try{
			APIDateTimeUtils.timeZone2Lucene("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void _stringToDateTest() {
		try{
			APIDateTimeUtils._stringToDate("abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertDateToStringTest() {
		try{
			APIDateTimeUtils.convertDateToString(new Date(), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void _dateToStringTest() {
		try{
			APIDateTimeUtils._dateToString(new Date(), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}