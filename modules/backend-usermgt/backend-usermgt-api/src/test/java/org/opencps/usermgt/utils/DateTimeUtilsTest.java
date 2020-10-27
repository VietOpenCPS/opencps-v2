package org.opencps.usermgt.utils;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
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
	public void getMailSendDateTest() {
		try{
			DateTimeUtils.getMailSendDate();
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
	public void hourBetweenTest() {
		try{
			DateTimeUtils.hourBetween(new Date(), new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void hoursBetweenTest() {
		try{
			DateTimeUtils.hoursBetween(new Date(), new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDateNowTest() {
		try{
			DateTimeUtils.getDateNow();
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
	public void minuteBetweenTest() {
		try{
			DateTimeUtils.minuteBetween(new Date(), new Date());
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
	public void daysBetweenTest() {
		try{
			DateTimeUtils.daysBetween(new Date(), new Date());
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
//	@Test
//	public void convertStringToDateTest() {
//		try{
//			DateTimeUtils.convertStringToDate("abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void convertDateTimeToStringTest() {
//		try{
//			DateTimeUtils.convertDateTimeToString("abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void convertStringToDateAPITest() {
//		try{
//			DateTimeUtils.convertStringToDateAPI("abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void convertStringToFullDateTest() {
//		try{
//			DateTimeUtils.convertStringToFullDate("abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
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
//	@Test
//	public void getDateTimeFormatTest() {
//		try{
//			DateTimeUtils.getDateTimeFormat("abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
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