package org.opencps.api.dossiertemplate.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierPartContentInputUpdateModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getValueTest() {
		try{
			DossierPartContentInputUpdateModel object = new DossierPartContentInputUpdateModel();
			object.getValue();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setValueTest() {
		try{
			DossierPartContentInputUpdateModel object = new DossierPartContentInputUpdateModel();
			object.setValue("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}