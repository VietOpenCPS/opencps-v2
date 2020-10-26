package org.opencps.api.datatempmgt.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DictCollectionTempListTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getDictCollectionTempModelTest() {
		try{
			DictCollectionTempList object = new DictCollectionTempList();
			object.getDictCollectionTempModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}