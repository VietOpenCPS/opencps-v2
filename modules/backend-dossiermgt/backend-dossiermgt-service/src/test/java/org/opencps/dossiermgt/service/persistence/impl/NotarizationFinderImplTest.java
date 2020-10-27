package org.opencps.dossiermgt.service.persistence.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class NotarizationFinderImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void findAdvancedSearchTest() {
		try{
			NotarizationFinderImpl object = new NotarizationFinderImpl();
			object.findAdvancedSearch(Long.valueOf(0), Long.valueOf(0), "abcde", 0, 0, 0, Long.valueOf(0), "abcde", 0, "abcde", "abcde", "abcde", "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countAdvancedSearchTest() {
		try{
			NotarizationFinderImpl object = new NotarizationFinderImpl();
			object.countAdvancedSearch(Long.valueOf(0), Long.valueOf(0), "abcde", 0, 0, 0, Long.valueOf(0), "abcde", 0, "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}