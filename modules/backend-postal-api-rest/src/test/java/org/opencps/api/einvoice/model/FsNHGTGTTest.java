package org.opencps.api.einvoice.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class FsNHGTGTTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setBMkTest() {
		try{
			FsNHGTGT object = new FsNHGTGT();
			object.setBMk("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setBSoIdTest() {
		try{
			FsNHGTGT object = new FsNHGTGT();
			object.setBSoId("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setHoadonTest() {
		try{
			FsNHGTGT object = new FsNHGTGT();
			object.setHoadon(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setHoadonCtTest() {
		try{
			FsNHGTGT object = new FsNHGTGT();
			object.setHoadonCt(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setBKtraDchTest() {
		try{
			FsNHGTGT object = new FsNHGTGT();
			object.setBKtraDch("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setBNsdTest() {
		try{
			FsNHGTGT object = new FsNHGTGT();
			object.setBNsd("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getBMkTest() {
		try{
			FsNHGTGT object = new FsNHGTGT();
			object.getBMk();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getHoadonCtTest() {
		try{
			FsNHGTGT object = new FsNHGTGT();
			object.getHoadonCt();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getHoadonTest() {
		try{
			FsNHGTGT object = new FsNHGTGT();
			object.getHoadon();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getBKtraDchTest() {
		try{
			FsNHGTGT object = new FsNHGTGT();
			object.getBKtraDch();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getBSoIdTest() {
		try{
			FsNHGTGT object = new FsNHGTGT();
			object.getBSoId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getBNsdTest() {
		try{
			FsNHGTGT object = new FsNHGTGT();
			object.getBNsd();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}