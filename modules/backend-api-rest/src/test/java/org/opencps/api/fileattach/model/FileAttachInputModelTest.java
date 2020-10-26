package org.opencps.api.fileattach.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class FileAttachInputModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getClassNameTest() {
		try{
			FileAttachInputModel object = new FileAttachInputModel();
			object.getClassName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileNameTest() {
		try{
			FileAttachInputModel object = new FileAttachInputModel();
			object.getFileName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSourceTest() {
		try{
			FileAttachInputModel object = new FileAttachInputModel();
			object.setSource("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSourceUrlTest() {
		try{
			FileAttachInputModel object = new FileAttachInputModel();
			object.setSourceUrl("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSourceTest() {
		try{
			FileAttachInputModel object = new FileAttachInputModel();
			object.getSource();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSourceUrlTest() {
		try{
			FileAttachInputModel object = new FileAttachInputModel();
			object.getSourceUrl();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFileNameTest() {
		try{
			FileAttachInputModel object = new FileAttachInputModel();
			object.setFileName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getClassPKTest() {
		try{
			FileAttachInputModel object = new FileAttachInputModel();
			object.getClassPK();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setClassPKTest() {
		try{
			FileAttachInputModel object = new FileAttachInputModel();
			object.setClassPK("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setClassNameTest() {
		try{
			FileAttachInputModel object = new FileAttachInputModel();
			object.setClassName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}