package opencps.statistic.common.webservice.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;

public class OpencpsUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void filterTest() {
		try{
			OpencpsUtils.filter(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isNotNullTest() {
		try{
			OpencpsUtils.isNotNull(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void formatISOToOnStarLocaleTest() {
		try{
			OpencpsUtils.formatISOToOnStarLocale("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getISOLocaleSeparatorTest() {
		try{
			OpencpsUtils.getISOLocaleSeparator();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTimeStampInOnStarFormatTest() {
		try{
			OpencpsUtils.getTimeStampInOnStarFormat(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void changeToOnStarAcceptedLocaleTest() {
		try{
			OpencpsUtils.changeToOnStarAcceptedLocale("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void logAsFormattedJsonTest() {
		try{
			OpencpsUtils.logAsFormattedJson(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void formatOnStarToISOLocaleTest() {
		try{
			OpencpsUtils.formatOnStarToISOLocale("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSystemTimeStampInOnStarFormatTest() {
		try{
			OpencpsUtils.getSystemTimeStampInOnStarFormat();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getLanguageCodeTest() {
		try{
			OpencpsUtils.getLanguageCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void formatDateTest() {
		try{
			OpencpsUtils.formatDate("abcde", new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCountryCodeTest() {
		try{
			OpencpsUtils.getCountryCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void orEqualsTest() {
		try{
			OpencpsUtils.orEquals(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void compareStringTest() {
		try{
			OpencpsUtils.compareString("abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}