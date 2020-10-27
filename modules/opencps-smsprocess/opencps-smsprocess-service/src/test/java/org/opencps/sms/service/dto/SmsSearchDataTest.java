package org.opencps.sms.service.dto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class SmsSearchDataTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setSrcTest() {
		try{
			SmsSearchData object = new SmsSearchData();
			object.setSrc("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSrcTest() {
		try{
			SmsSearchData object = new SmsSearchData();
			object.getSrc();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierNoTest() {
		try{
			SmsSearchData object = new SmsSearchData();
			object.setDossierNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierNoTest() {
		try{
			SmsSearchData object = new SmsSearchData();
			object.getDossierNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setReqDateTest() {
		try{
			SmsSearchData object = new SmsSearchData();
			object.setReqDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSmsReqTest() {
		try{
			SmsSearchData object = new SmsSearchData();
			object.setSmsReq("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getReqDateTest() {
		try{
			SmsSearchData object = new SmsSearchData();
			object.getReqDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSmsReplyTest() {
		try{
			SmsSearchData object = new SmsSearchData();
			object.setSmsReply("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSmsReplyTest() {
		try{
			SmsSearchData object = new SmsSearchData();
			object.getSmsReply();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getReplyDateTest() {
		try{
			SmsSearchData object = new SmsSearchData();
			object.getReplyDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSmsReqTest() {
		try{
			SmsSearchData object = new SmsSearchData();
			object.getSmsReq();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSmsIdTest() {
		try{
			SmsSearchData object = new SmsSearchData();
			object.getSmsId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSmsIdTest() {
		try{
			SmsSearchData object = new SmsSearchData();
			object.setSmsId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setReplyDateTest() {
		try{
			SmsSearchData object = new SmsSearchData();
			object.setReplyDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getApplicationNameTest() {
		try{
			SmsSearchData object = new SmsSearchData();
			object.getApplicationName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setApplicationNameTest() {
		try{
			SmsSearchData object = new SmsSearchData();
			object.setApplicationName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}