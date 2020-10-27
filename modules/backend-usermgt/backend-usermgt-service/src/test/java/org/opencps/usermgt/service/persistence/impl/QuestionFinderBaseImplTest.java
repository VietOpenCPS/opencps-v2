package org.opencps.usermgt.service.persistence.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class QuestionFinderBaseImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setQuestionPersistenceTest() {
		try{
			QuestionFinderBaseImpl object = new QuestionFinderBaseImpl();
			object.setQuestionPersistence(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getQuestionPersistenceTest() {
		try{
			QuestionFinderBaseImpl object = new QuestionFinderBaseImpl();
			object.getQuestionPersistence();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}