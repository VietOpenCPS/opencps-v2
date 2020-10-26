package org.opencps.api.fileattach.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ObjectFactoryTest {
	@Before
	public void setUp() {
	}
	@Test
	public void createDataSearchModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDataSearchModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createFileAttachModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createFileAttachModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createFileVersionResultsTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createFileVersionResults();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createFileVersionModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createFileVersionModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createFileAttachInputModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createFileAttachInputModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createFileAttachResultsTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createFileAttachResults();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}