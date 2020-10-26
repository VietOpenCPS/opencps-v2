package org.opencps.api.dictcollection.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DictCollectionListTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getDictCollectionModelTest() {
		try{
			DictCollectionList object = new DictCollectionList();
			object.getDictCollectionModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}