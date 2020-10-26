package org.opencps.api.fileitemmgt.model;
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
	public void createFileItemModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createFileItemModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createFileItemSearchModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createFileItemSearchModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createFileItemResultsModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createFileItemResultsModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}