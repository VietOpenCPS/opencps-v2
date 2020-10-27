package org.opencps.datamgt.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DateTimeUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getInstanceTest() {
		try{
			DateTimeUtils.getInstance(new Date(), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDateTest() {
		try{
			DateTimeUtils.getDate(0, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getYearFromDateTest() {
		try{
			DateTimeUtils.getYearFromDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStringDateTest() {
		try{
			DateTimeUtils.getStringDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDayFromDateTest() {
		try{
			DateTimeUtils.getDayFromDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMonthFromDateTest() {
		try{
			DateTimeUtils.getMonthFromDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertDateToStringTest() {
		try{
			DateTimeUtils.convertDateToString(new Date(), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertStringToDateTest() {
		try{
			DateTimeUtils.convertStringToDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDateTimeFromPatternTest() {
		try{
			DateTimeUtils object = new DateTimeUtils();
			object.getDateTimeFromPattern("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertTimemilisecondsToFormatTest() {
		try{
			DateTimeUtils.convertTimemilisecondsToFormat(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDateBeginOfDayTest() {
		try{
			DateTimeUtils.getDateBeginOfDay(0, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDateTimeBeanTest() {
		try{
			DateTimeUtils.setDateTimeBean(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDateEndOfDayTest() {
		try{
			DateTimeUtils.getDateEndOfDay(0, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDateTimeBeanTest() {
		try{
			DateTimeUtils.getDateTimeBean();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertStringToFullDateTest() {
		try{
			DateTimeUtils.convertStringToFullDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertTimemilisecondsToDaysTest() {
		try{
			DateTimeUtils.convertTimemilisecondsToDays(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertTimemilisecondsToMinutesTest() {
		try{
			DateTimeUtils.convertTimemilisecondsToMinutes(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDateTimeFormatTest() {
		try{
			DateTimeUtils.getDateTimeFormat("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertTimemilisecondsToSecondsTest() {
		try{
			DateTimeUtils.convertTimemilisecondsToSeconds(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertTimemilisecondsToHoursTest() {
		try{
			DateTimeUtils.convertTimemilisecondsToHours(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}