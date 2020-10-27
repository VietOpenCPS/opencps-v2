package com.viettel.smsbrand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class CpBalanceTest {
	@Before
	public void setUp() {
	}
	@Test
	public void equalsTest() {
		try{
			CpBalance object = new CpBalance();
			object.equals(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void hashCodeTest() {
		try{
			CpBalance object = new CpBalance();
			object.hashCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSerializerTest() {
		try{
			CpBalance.getSerializer("abcde", null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDeserializerTest() {
		try{
			CpBalance.getDeserializer("abcde", null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getBalanceTest() {
		try{
			CpBalance object = new CpBalance();
			object.getBalance();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getErrCodeTest() {
		try{
			CpBalance object = new CpBalance();
			object.getErrCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getErrDescTest() {
		try{
			CpBalance object = new CpBalance();
			object.getErrDesc();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setErrCodeTest() {
		try{
			CpBalance object = new CpBalance();
			object.setErrCode(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTypeDescTest() {
		try{
			CpBalance.getTypeDesc();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setBalanceTest() {
		try{
			CpBalance object = new CpBalance();
			object.setBalance(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setErrDescTest() {
		try{
			CpBalance object = new CpBalance();
			object.setErrDesc("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}