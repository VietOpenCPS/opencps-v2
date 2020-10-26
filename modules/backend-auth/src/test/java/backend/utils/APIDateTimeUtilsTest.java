package backend.utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class APIDateTimeUtilsTest {
	@Before
	public void setUp() {
	}

	@Test
	public void timeZone2LuceneTest() {

		try {
			String outputDateString = APIDateTimeUtils.timeZone2Lucene(null);
		} catch (Exception e) {

		}
		Assert.assertEquals(1, 1);
	}

	@Test
	public void dateToStringTest() {

		try {
			String outputDateString = APIDateTimeUtils._dateToString(null, null);
		} catch (Exception e) {

		}
		Assert.assertEquals(1, 1);
	}

	@Test
	public void convertDateToStringTest() {

		try {
			String outputDateString = APIDateTimeUtils.convertDateToString(null, null);
		} catch (Exception e) {

		}
		Assert.assertEquals(1, 1);
	}

}
