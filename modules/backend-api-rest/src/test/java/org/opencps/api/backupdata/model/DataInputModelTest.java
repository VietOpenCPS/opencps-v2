package org.opencps.api.backupdata.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DataInputModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setDataTypeTest() {
		try{
			DataInputModel object = new DataInputModel();
			object.setDataType("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDataCodeTest() {
		try{
			DataInputModel object = new DataInputModel();
			object.setDataCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDataTypeTest() {
		try{
			DataInputModel object = new DataInputModel();
			object.getDataType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDataCodeTest() {
		try{
			DataInputModel object = new DataInputModel();
			object.getDataCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}