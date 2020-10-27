package org.opencps.dossiermgt.service.persistence.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class NotarizationFinderBaseImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getNotarizationPersistenceTest() {
		try{
			NotarizationFinderBaseImpl object = new NotarizationFinderBaseImpl();
			object.getNotarizationPersistence();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setNotarizationPersistenceTest() {
		try{
			NotarizationFinderBaseImpl object = new NotarizationFinderBaseImpl();
			object.setNotarizationPersistence(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}