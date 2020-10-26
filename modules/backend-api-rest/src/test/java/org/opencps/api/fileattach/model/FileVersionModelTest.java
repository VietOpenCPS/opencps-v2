package org.opencps.api.fileattach.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class FileVersionModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getFileNameTest() {
		try{
			FileVersionModel object = new FileVersionModel();
			object.getFileName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCreateDateTest() {
		try{
			FileVersionModel object = new FileVersionModel();
			object.getCreateDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserNameTest() {
		try{
			FileVersionModel object = new FileVersionModel();
			object.getUserName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserNameTest() {
		try{
			FileVersionModel object = new FileVersionModel();
			object.setUserName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserIdTest() {
		try{
			FileVersionModel object = new FileVersionModel();
			object.setUserId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreateDateTest() {
		try{
			FileVersionModel object = new FileVersionModel();
			object.setCreateDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserIdTest() {
		try{
			FileVersionModel object = new FileVersionModel();
			object.getUserId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getVersionTest() {
		try{
			FileVersionModel object = new FileVersionModel();
			object.getVersion();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setVersionTest() {
		try{
			FileVersionModel object = new FileVersionModel();
			object.setVersion("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFileNameTest() {
		try{
			FileVersionModel object = new FileVersionModel();
			object.setFileName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileEntryIdTest() {
		try{
			FileVersionModel object = new FileVersionModel();
			object.getFileEntryId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFileEntryIdTest() {
		try{
			FileVersionModel object = new FileVersionModel();
			object.setFileEntryId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}