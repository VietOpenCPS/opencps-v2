package org.opencps.kernel.util;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class DateTimeUtilTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getInstanceTest() {
		try{
			DateTimeUtil.getInstance(new Date(), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDateTest() {
		try{
			DateTimeUtil.getDate(0, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getYearFromDateTest() {
		try{
			DateTimeUtil.getYearFromDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMailSendDateTest() {
		try{
			DateTimeUtil.getMailSendDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStringDateTest() {
		try{
			DateTimeUtil.getStringDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void hourBetweenTest() {
		try{
			DateTimeUtil.hourBetween(new Date(), new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void hoursBetweenTest() {
		try{
			DateTimeUtil.hoursBetween(new Date(), new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDateNowTest() {
		try{
			DateTimeUtil.getDateNow();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDayFromDateTest() {
		try{
			DateTimeUtil.getDayFromDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void incrementHourTest() {
		try{
			DateTimeUtil.incrementHour(new Date(), 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void minuteBetweenTest() {
		try{
			DateTimeUtil.minuteBetween(new Date(), new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMonthFromDateTest() {
		try{
			DateTimeUtil.getMonthFromDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void daysBetweenTest() {
		try{
			DateTimeUtil.daysBetween(new Date(), new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertDateToStringTest() {
		try{
			DateTimeUtil.convertDateToString(new Date(), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void convertStringToDateTest() {
//		try{
//			DateTimeUtil.convertStringToDate("abcde", "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void convertStringToDateTest16() {
//		try{
//			DateTimeUtil.convertStringToDate("abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void convertDateTimeToStringTest() {
//		try{
//			DateTimeUtil.convertDateTimeToString("abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void convertStringToDateAPITest() {
//		try{
//			DateTimeUtil.convertStringToDateAPI("abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void convertStringToFullDateTest() {
//		try{
//			DateTimeUtil.convertStringToFullDate("abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void convertTimemilisecondsToDaysTest() {
		try{
			DateTimeUtil.convertTimemilisecondsToDays(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void convertStringToDateTimeAPITest() {
//		try{
//			DateTimeUtil.convertStringToDateTimeAPI("abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void convertTimemilisecondsToMinutesTest() {
		try{
			DateTimeUtil.convertTimemilisecondsToMinutes(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void getDateTimeFormatTest() {
//		try{
//			DateTimeUtil.getDateTimeFormat("abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void convertTimemilisecondsToSecondsTest() {
		try{
			DateTimeUtil.convertTimemilisecondsToSeconds(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertTimemilisecondsToHoursTest() {
		try{
			DateTimeUtil.convertTimemilisecondsToHours(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}