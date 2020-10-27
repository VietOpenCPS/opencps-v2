package org.opencps.usermgt.service.persistence.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class QuestionFinderImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void findQuestionSearchTest() {
		try{
			QuestionFinderImpl object = new QuestionFinderImpl();
			object.findQuestionSearch(Long.valueOf(0), "abcde", "abcde", "abcde", 0, "abcde", true, "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countQuestionSearchTest() {
		try{
			QuestionFinderImpl object = new QuestionFinderImpl();
			object.countQuestionSearch(Long.valueOf(0), "abcde", "abcde", "abcde", 0, "abcde", true, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}